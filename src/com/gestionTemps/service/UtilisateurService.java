package com.gestionTemps.service;

import javax.servlet.http.HttpServletRequest;

import com.gestionTemps.beans.Utilisateur;

public class UtilisateurService {
	
	private UtilisateurDOAImpl utilisateurDOAImpl = new UtilisateurDOAImpl();
	
	public boolean estEmailValide(String email) {
		return utilisateurDOAImpl.emailEstValide(email);
	}
	
	public Utilisateur verifierIdentifiant(HttpServletRequest request) throws Exception {
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		Utilisateur utilisateur = utilisateurDOAImpl.recupererUtilisateur(email, pass);
		return utilisateur;
	}
	
	public Utilisateur ajouterUtilisateur(HttpServletRequest request) {
		String prenom = request.getParameter("prenom");
		String nom = request.getParameter("nom");
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		Utilisateur utilisateur = new Utilisateur(nom, prenom, email, pass);
		return utilisateurDOAImpl.ajouterUtilisateur(utilisateur);
	}

}
