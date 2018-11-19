package com.gestionTemps.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.gestionTemps.beans.Liste;
import com.gestionTemps.beans.Tache;
import com.gestionTemps.dao.ListeDAO;
import com.mysql.jdbc.PreparedStatement;

public class ListeDAOImpl implements ListeDAO {

	@Override
	public Liste ajouterListe(Liste liste) {
		Connection conn = DatabaseUtility.loadDatabase();
		String sql = "INSERT INTO `listes`(`nom_liste`, `description_liste`, `tableau_id`) VALUES (?, ?, ?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = (PreparedStatement) conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, liste.getNomListe());
			preparedStatement.setString(2, liste.getDescriptionListe());
			preparedStatement.setLong(3, liste.getTableauID());
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
		    rs.next();
		    Long listeID = rs.getLong(1);
		    liste.setIdListe(listeID);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return liste;
	}

	@Override
	public void supprimerListe(Long listeID) {
		
		Connection conn = DatabaseUtility.loadDatabase();
		ListeDAOImpl listeDAOImpl = new ListeDAOImpl();
		TacheDAOImpl tacheDAOImpl = new TacheDAOImpl();
		List<Tache> taches = listeDAOImpl.recupereToutesLesTachesDeLaListe(listeID);
		Iterator<Tache> it = taches.iterator();
		while(it.hasNext()) {
			tacheDAOImpl.supprimerTache(it.next().getIdTache());
		}
		String sql = "DELETE FROM `listes` WHERE `id_liste` = ?";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
			preparedStatement.setLong(1, listeID);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public Liste recupererListe(Long listeID) {
		List<Liste> listes = new ArrayList<Liste>();
		Connection conn = DatabaseUtility.loadDatabase();
        Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet resultat = statement.executeQuery("SELECT * FROM `listes` WHERE `id_liste` = " + listeID.toString());
			while(resultat.next()) {
				Long listeId = resultat.getLong("id_liste");
				String listeNom = resultat.getString("nom_liste");
				String listeDescritption = resultat.getString("description_liste");
				Long tableauID = resultat.getLong("tableau_id");
				Liste liste = new Liste();
				liste.setIdListe(listeId);
				liste.setNomListe(listeNom);
				liste.setDescriptionListe(listeDescritption);
				liste.setTableauID(tableauID);
				listes.add(liste);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(listes.isEmpty())
			return null;
		else
			return listes.get(0);
	}

	@Override
	public void ajouterTacheDansListe(Tache tache) {
		TacheDAOImpl tacheDAOImpl = new TacheDAOImpl();
		Connection conn = DatabaseUtility.loadDatabase();
		String sql;
		tache = tacheDAOImpl.ajouterTache(tache);
		sql = "INSERT INTO `listes_taches`(`liste`, `tache`) VALUES (?, ?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
			preparedStatement.setLong(1, tache.getListeID());
			preparedStatement.setLong(2, tache.getIdTache());
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
	public void supprimerTacheDeListe(Long tacheID) {
		
		TacheDAOImpl tacheDAOImpl = new TacheDAOImpl();
		Tache t = tacheDAOImpl.recupererTache(tacheID);
		
		if(t == null) return;
		
		Connection conn = DatabaseUtility.loadDatabase();
		String sql = "DELETE FROM `listes_taches` WHERE `liste` = ? and `tache` = ?";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
			preparedStatement.setLong(1, t.getListeID());
			preparedStatement.setLong(2, t.getIdTache());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Liste> recupererToutesLesListes() {
		List<Liste> listes = new ArrayList<Liste>();
		Connection conn = DatabaseUtility.loadDatabase();
        Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet resultat = statement.executeQuery("SELECT * FROM `listes`");
			while(resultat.next()) {
				Long listeId = resultat.getLong("id_liste");
				String listeNom = resultat.getString("nom_liste");
				String listeDescritption = resultat.getString("description_liste");
				Long tableauID = resultat.getLong("tableau_id");
				Liste liste = new Liste();
				liste.setIdListe(listeId);
				liste.setNomListe(listeNom);
				liste.setDescriptionListe(listeDescritption);
				liste.setTableauID(tableauID);
				listes.add(liste);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listes;
	}

	@Override
	public List<Tache> recupereToutesLesTachesDeLaListe(Long listeID) {
		List<Tache> taches = new ArrayList<Tache>();
		Connection conn = DatabaseUtility.loadDatabase();
        Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet resultat = statement.executeQuery("SELECT * FROM `listes_taches` WHERE `liste` = " + listeID.toString());
			Set<Long> taches_id = new HashSet<Long>();
			while(resultat.next()) {
				taches_id.add(resultat.getLong("tache"));
			}
			Iterator<Long> it = taches_id.iterator();
			TacheDAOImpl tacheDAOImpl = new TacheDAOImpl();
			while(it.hasNext()) {
				taches.add(tacheDAOImpl.recupererTache(it.next()));
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
		return taches;
	}

}
