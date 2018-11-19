package com.gestionTemps.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.gestionTemps.beans.Liste;
import com.gestionTemps.beans.Tache;

public class ListeService {
	
	private ListeDAOImpl listeDAOImpl = new ListeDAOImpl();
	
	private Liste retournerListe(Long listeID) {
		return listeDAOImpl.recupererListe(listeID);
	}
	
	public Liste retournerListe(HttpServletRequest request) {
		Long listeID = Long.parseLong(request.getParameter("id"));
		return retournerListe(listeID);
	}
	
	public List<Tache> retournerTachesDeLaListe(Long listeID){
		return listeDAOImpl.recupereToutesLesTachesDeLaListe(listeID);
	}
	
	public void ajouterTacheDansListe(HttpServletRequest request) {
		String nomTache = request.getParameter("nom");
		String descriptionTache = request.getParameter("desc");
		Date dateDeCreationDeTache = new Date();
		Date dateLimiteDeTache = null;
		try {
			dateLimiteDeTache = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int priorite = Integer.parseInt(request.getParameter("priorite"));
		Long listeID = Long.parseLong(request.getParameter("id"));
		Tache tache = new Tache(nomTache, descriptionTache, dateDeCreationDeTache, dateLimiteDeTache, priorite, listeID);
		listeDAOImpl.ajouterTacheDansListe(tache);
	}

}
