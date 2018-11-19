package com.gestionTemps.dao;

import java.util.List;

import com.gestionTemps.beans.Tableau;
import com.gestionTemps.beans.Utilisateur;

public interface UtilisateurDAO {
	
	public Utilisateur ajouterUtilisateur(Utilisateur utilisateur);
	public void supprimerUtilisateur(Long idUtilisateur);
	public Utilisateur recupererUtilisateur(Long idUtilisateur);
	public Utilisateur recupererUtilisateur(String email, String pass);
	public boolean emailEstValide(String email);
	public List<Tableau> tableauxDeLUtilisateur(Long uilisateurID);
	
}
