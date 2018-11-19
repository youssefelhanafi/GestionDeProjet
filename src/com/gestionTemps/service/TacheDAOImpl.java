package com.gestionTemps.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.gestionTemps.beans.Marque;
import com.gestionTemps.beans.Tache;
import com.gestionTemps.dao.TacheDAO;
import com.mysql.jdbc.PreparedStatement;

public class TacheDAOImpl implements TacheDAO {

	@Override
	public Tache ajouterTache(Tache tache) {
		Connection conn = DatabaseUtility.loadDatabase();
		String sql = "INSERT INTO `taches`(`nom_tache`, `description_tache`, `date_creation`, `date_limite`, `priorite`, `tableau_id`) VALUES (?, ?, ?, ?, ?, ?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = (PreparedStatement) conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, tache.getNomTache());
			preparedStatement.setString(2, tache.getDescriptionTache());
			preparedStatement.setString(3, sdf.format(tache.getDateDeCreationDeTache()));
			preparedStatement.setString(4, sdf.format(tache.getDateLimiteDeTache()));
			preparedStatement.setInt(5, tache.getPriorite());
			preparedStatement.setLong(6, tache.getTableauID());
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			rs.next();
			Long tacheID = rs.getLong(1);
			tache.setIdTache(tacheID);
			sql = "INSERT INTO `commits_tache`(`text_commit`, `date_commit`, `tache_id`) VALUES (?, ?, ?)";
			String textCommit = "Tache " + tache.getNomTache() + " a été ajouté";
			preparedStatement = (PreparedStatement) conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, textCommit);
			preparedStatement.setString(2, sdf.format(new Date()));
			preparedStatement.setLong(3, tache.getIdTache());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return tache;
	}

	@Override
	public void supprimerTache(Long tacheID) {
		Connection conn = DatabaseUtility.loadDatabase();
		String sql = "DELETE FROM `taches` WHERE `id_tache` = ?";
		PreparedStatement preparedStatement = null;
		List<Marque> marques = recupererToutesLesMarquesDeLaTache(tacheID);
		MarqueDAOImpl marqueDAOImpl = new MarqueDAOImpl();
		try {
			preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
			preparedStatement.setLong(1, tacheID);
			preparedStatement.executeUpdate();
			for (Marque marque : marques) {
				marqueDAOImpl.supprimerMarque(marque.getIdMarque());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void ajouterMarqueDansTache(Long tacheID, Marque marque) {
		MarqueDAOImpl marqueDAOImpl = new MarqueDAOImpl();
		Connection conn = DatabaseUtility.loadDatabase();
		String sql;
		marque = marqueDAOImpl.ajouterMarque(marque);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Tache tache = recupererTache(tacheID);
		sql = "INSERT INTO `taches_marques`(`tache`, `marque`) VALUES (?, ?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
			preparedStatement.setLong(1, tacheID);
			preparedStatement.setLong(2, marque.getIdMarque());
			preparedStatement.executeUpdate();
			sql = "INSERT INTO `commits_tache`(`text_commit`, `date_commit`, `tache_id`) VALUES (?, ?, ?)";
			String textCommit = "Marque " + marque.getNomMarque() + " a été ajouté dans cette tache";
			preparedStatement = (PreparedStatement) conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, textCommit);
			preparedStatement.setString(2, sdf.format(new Date()));
			preparedStatement.setLong(3, tache.getIdTache());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void supprimerMarqueDeTache(Long tacheID, Long marqueID) {
		MarqueDAOImpl marqueDAOImpl = new MarqueDAOImpl();
		Marque m = marqueDAOImpl.recupererMarque(marqueID);
		
		if(m == null) return;
		
		Connection conn = DatabaseUtility.loadDatabase();
		String sql = "DELETE FROM `taches_marques` WHERE `tache` = ? and `marque` = ?";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
			preparedStatement.setLong(1, tacheID);
			preparedStatement.setLong(2, marqueID);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Tache recupererTache(Long tacheID) {
		List<Tache> taches = new ArrayList<Tache>();
		Connection conn = DatabaseUtility.loadDatabase();
        Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet resultat = statement.executeQuery("SELECT * FROM `taches` WHERE `id_tache` = " + tacheID.toString());
			while(resultat.next()) {
				Long tacheId = resultat.getLong("id_tache");
				String tacheNom = resultat.getString("nom_tache");
				String tacheDesription = resultat.getString("description_tache");
				Date dateCreation = resultat.getDate("date_creation");
				Date dateLimite = resultat.getDate("date_limite");
				int priorite = resultat.getInt("priorite");
				Long tableauID = resultat.getLong("tableau_id");
				Tache tache = new Tache();
				tache.setIdTache(tacheId);
				tache.setNomTache(tacheNom);
				tache.setDescriptionTache(tacheDesription);
				tache.setDateDeCreationDeTache(dateCreation);
				tache.setDateLimiteDeTache(dateLimite);
				tache.setPriorite(priorite);
				tache.setTableauID(tableauID);
				taches.add(tache);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(taches.isEmpty())
			return null;
		else
			return taches.get(0);
	}

	@Override
	public List<Tache> recupererToutesLesTaches() {
		List<Tache> taches = new ArrayList<Tache>();
		Connection conn = DatabaseUtility.loadDatabase();
        Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet resultat = statement.executeQuery("SELECT * FROM `taches`");
			while(resultat.next()) {
				Long tacheID = resultat.getLong("id_tache");
				String tacheNom = resultat.getString("nom_tache");
				String tacheDesription = resultat.getString("description_tache");
				Date dateCreation = resultat.getDate("date_creation");
				Date dateLimite = resultat.getDate("date_limite");
				int priorite = resultat.getInt("priorite");
				Long tableauID = resultat.getLong("tableau_id");
				Tache tache = new Tache();
				tache.setIdTache(tacheID);
				tache.setNomTache(tacheNom);
				tache.setDescriptionTache(tacheDesription);
				tache.setDateDeCreationDeTache(dateCreation);
				tache.setDateLimiteDeTache(dateLimite);
				tache.setPriorite(priorite);
				tache.setTableauID(tableauID);
				taches.add(tache);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
		return taches;
	}
	
	@Override
	public Set<Long> recupererToutesLesMarquesIDDeLaTache(Long tacheID) {
		Connection conn = DatabaseUtility.loadDatabase();
        Statement statement;
        Set<Long> marques_id = new HashSet<Long>();
		try {
			statement = conn.createStatement();
			ResultSet resultat = statement.executeQuery("SELECT * FROM `taches_marques` WHERE `tache` = " + tacheID.toString());
			while(resultat.next()) {
				marques_id.add(resultat.getLong("marque"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return marques_id;
	}

	@Override
	public List<Marque> recupererToutesLesMarquesDeLaTache(Long tacheID) {
		List<Marque> marques = new ArrayList<Marque>();
		Connection conn = DatabaseUtility.loadDatabase();
        Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet resultat = statement.executeQuery("SELECT * FROM `taches_marques` WHERE `tache` = " + tacheID.toString());
			Set<Long> marques_id = new HashSet<Long>();
			while(resultat.next()) {
				marques_id.add(resultat.getLong("marque"));
			}
			Iterator<Long> it = marques_id.iterator();
			MarqueDAOImpl marqueDAOImpl = new MarqueDAOImpl();
			while(it.hasNext()) {
				marques.add(marqueDAOImpl.recupererMarque(it.next()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(marques.isEmpty())
			return null;
		else
			return marques;
	}

}
