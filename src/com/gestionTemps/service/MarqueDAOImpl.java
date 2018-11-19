package com.gestionTemps.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gestionTemps.beans.Marque;
import com.gestionTemps.dao.MarqueDAO;
import com.mysql.jdbc.PreparedStatement;

public class MarqueDAOImpl implements MarqueDAO {

	@Override
	public Marque ajouterMarque(Marque marque) {
		Marque m = marqueExisteDeja(marque.getNomMarque());
		if(m != null) return m;
		Connection conn = DatabaseUtility.loadDatabase();
		String sql = "INSERT INTO `marques`(`nom_marque`) VALUES (?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = (PreparedStatement) conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, marque.getNomMarque());
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			rs.next();
			Long marqueID = rs.getLong(1);
			marque.setIdMarque(marqueID);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return marque;
	}
	
	private void supprimerMarqueDuMapping(Long marqueID) {
		Connection conn = DatabaseUtility.loadDatabase();
		String sql = "DELETE FROM `taches_marques` WHERE `marque` = ?";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
			preparedStatement.setLong(1, marqueID);
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
	public void supprimerMarque(Long marqueID) {
		Connection conn = DatabaseUtility.loadDatabase();
		String sql = "DELETE FROM `marques` WHERE `id_marque` = ?";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
			preparedStatement.setLong(1, marqueID);
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
		supprimerMarqueDuMapping(marqueID);
	}
	
	@Override
	public Marque recupererMarque(Long marqueID) {
		List<Marque> marques = new ArrayList<Marque>();
		Connection conn = DatabaseUtility.loadDatabase();
        Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet resultat = statement.executeQuery("SELECT * FROM `marques` WHERE `id_marque` = " + marqueID.toString());
			while(resultat.next()) {
				Long marqueId = resultat.getLong("id_marque");
				String marqueNom = resultat.getString("nom_marque");
				Marque marque = new Marque();
				marque.setIdMarque(marqueId);
				marque.setNomMarque(marqueNom);
				marques.add(marque);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(marques.isEmpty())
			return null;
		else
			return marques.get(0);
	}

	@Override
	public List<Marque> recupererToutesLesMarques() {
		List<Marque> marques = new ArrayList<Marque>();
		Connection conn = DatabaseUtility.loadDatabase();
        Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet resultat = statement.executeQuery("SELECT * FROM `marques`");
			while(resultat.next()) {
				Long idMarque = resultat.getLong("id_marque");
				String nomMarque = resultat.getString("nom_marque");
				Marque marque = new Marque();
				marque.setIdMarque(idMarque);
				marque.setNomMarque(nomMarque);
				marques.add(marque);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
		return marques;
	}

	private Marque marqueExisteDeja(String nomMarque) {
		List<Marque> marques = recupererToutesLesMarques();
		for (Marque marque : marques) {
			if(marque.getNomMarque().equals(nomMarque))
				return marque;
		}
		return null;
	}

}
