package com.gestionTemps.service;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.gestionTemps.beans.Tableau;
import com.gestionTemps.beans.Utilisateur;
import com.gestionTemps.dao.UtilisateurDAO;
import com.mysql.jdbc.PreparedStatement;

public class UtilisateurDOAImpl implements UtilisateurDAO {

	@Override
	public Utilisateur ajouterUtilisateur(Utilisateur utilisateur) {
		Connection conn = DatabaseUtility.loadDatabase();
		String sql = "INSERT INTO `utilisateurs`(`nom_utilisateur`, `prenom_utilisateur`, `email_utilisateur`, `password_utilisateur`) VALUES (?, ?, ?, ?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = (PreparedStatement) conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, utilisateur.getNomUtilisateur());
			preparedStatement.setString(2, utilisateur.getPrenomUtilisateur());
			preparedStatement.setString(3, utilisateur.getEmailUtilisateur());
			preparedStatement.setString(4, utilisateur.getMotDePasse());
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			rs.next();
			utilisateur.setIdUtilisateur(rs.getLong(1));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return utilisateur;
	}

	@Override
	public void supprimerUtilisateur(Long idUtilisateur) {
		
		TableauDAOImplem tableauDAOImplem = new TableauDAOImplem();
		List<Tableau> tableaux = tableauxDeLUtilisateur(idUtilisateur);
		Iterator<Tableau> it = tableaux.iterator();
		while(it.hasNext()) {
			tableauDAOImplem.supprimerTableau(it.next().getIdTableau());
		}
		
		Connection conn = DatabaseUtility.loadDatabase();
		String sql = "DELETE FROM `utilisateurs` WHERE `id_utilisateur` = ?";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
			preparedStatement.setLong(1, idUtilisateur);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
		}
		
	}

	@Override
	public Utilisateur recupererUtilisateur(Long idUtilisateur) {
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		Connection conn = DatabaseUtility.loadDatabase();
        Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet resultat = statement.executeQuery("SELECT * FROM `utilisateurs` WHERE `id_utilisateur` = " + idUtilisateur);
			while(resultat.next()) {
				Long id_utilisateur = resultat.getLong("id_utilisateur");
				String nom_utilisateur = resultat.getString("nom_utilisateur");
				String prenom_utilisateur = resultat.getString("prenom_utilisateur");
				String email_utilisateur = resultat.getString("email_utilisateur");
				String password_utilisateur = resultat.getString("password_utilisateur");
				Utilisateur utilisateur = new Utilisateur(nom_utilisateur, prenom_utilisateur, email_utilisateur, password_utilisateur);
				utilisateur.setIdUtilisateur(id_utilisateur);
				utilisateurs.add(utilisateur);
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
        
		if(utilisateurs.isEmpty()) return null;
		return utilisateurs.get(0);
	}

	@Override
	public boolean emailEstValide(String email) {
		List<String> emails = new ArrayList<String>();
		Connection conn = DatabaseUtility.loadDatabase();
        Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet resultat = statement.executeQuery("SELECT `email_utilisateur` FROM `utilisateurs` WHERE `email_utilisateur` = '"+email.toString()+"'");
			while(resultat.next()) {
				String emailUtilisateur = resultat.getString(1);
				emails.add(emailUtilisateur);
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
        
		if(emails.isEmpty()) return true;
		return false;
	}

	@Override
	public List<Tableau> tableauxDeLUtilisateur(Long uilisateurID) {
		List<Tableau> tableaux = new ArrayList<Tableau>();
		Connection conn = DatabaseUtility.loadDatabase();
        Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet resultat = statement.executeQuery("SELECT * FROM `tableaux` WHERE `utilisateur_id` = " + uilisateurID);
			while(resultat.next()) {
				Long id_tableau = resultat.getLong("id_tableau");
				String nom_tableau = resultat.getString("nom_tableau");
				String desc_tableau = resultat.getString("desc_tableau");
				Tableau tableau = new Tableau(nom_tableau, desc_tableau);
				tableau.setIdTableau(id_tableau);
				tableaux.add(tableau);
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
        
		return tableaux;
	}

	@Override
	public Utilisateur recupererUtilisateur(String email, String pass) {
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		Connection conn = DatabaseUtility.loadDatabase();
        Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet resultat = statement.executeQuery("SELECT * FROM `utilisateurs` WHERE "
					+ "`email_utilisateur` = '" + email +"' AND `password_utilisateur` = '" + pass + "'");
			while(resultat.next()) {
				Long id_utilisateur = resultat.getLong("id_utilisateur");
				String nom_utilisateur = resultat.getString("nom_utilisateur");
				String prenom_utilisateur = resultat.getString("prenom_utilisateur");
				String email_utilisateur = resultat.getString("email_utilisateur");
				String password_utilisateur = resultat.getString("password_utilisateur");
				Utilisateur utilisateur = new Utilisateur(nom_utilisateur, prenom_utilisateur, email_utilisateur, password_utilisateur);
				utilisateur.setIdUtilisateur(id_utilisateur);
				utilisateurs.add(utilisateur);
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
        
		if(utilisateurs.isEmpty()) return null;
		return utilisateurs.get(0);
	}
}
