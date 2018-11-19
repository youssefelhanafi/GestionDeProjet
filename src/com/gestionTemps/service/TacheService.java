package com.gestionTemps.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.gestionTemps.beans.Marque;
import com.gestionTemps.beans.Tache;

public class TacheService {
	
	private TacheDAOImpl tacheDAOImpl = new TacheDAOImpl();
	
	private Tache retournerTache(Long tacheID) {
		return tacheDAOImpl.recupererTache(tacheID);
	}
	
	public Tache retournerTache(HttpServletRequest request) {
		Long tacheID = Long.parseLong(request.getParameter("id"));
		return retournerTache(tacheID);
	}
	
	public List<Marque> retournerTouesLesMarquesDeLaTache(Long tacheID) {
		return tacheDAOImpl.recupererToutesLesMarquesDeLaTache(tacheID);
	}

	public void ajouterMarqueALaTache(HttpServletRequest request) {
		String nomMarque = request.getParameter("nom");
		Long tacheID = Long.parseLong(request.getParameter("id"));
		Marque marque = new Marque(nomMarque);
		tacheDAOImpl.ajouterMarqueDansTache(tacheID, marque);
	}
	
	public void ajouterTache(HttpServletRequest request) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String nomTache = request.getParameter("titre");
		String descriptionTache = request.getParameter("desc");
		int priorite = Integer.parseInt(request.getParameter("priorite"));
		Date dateDebut = null;
		Date dateFin = null;
		try {
			dateDebut = format.parse(request.getParameter("dateDebut"));
			dateFin = format.parse(request.getParameter("dateFin"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//String[] checkListes = request.getParameterValues("checkListes");
		Long tableauID = Long.parseLong(request.getParameter("tableauID"));
		Tache tache = new Tache(nomTache, descriptionTache, dateDebut, dateFin, priorite, tableauID);
		tache = tacheDAOImpl.ajouterTache(tache);
		List<String> marques = new ArrayList<String>(Arrays.asList(request.getParameter("marques").split(",")));
		for (String marqueNom : marques) {
			tacheDAOImpl.ajouterMarqueDansTache(tache.getIdTache(), new Marque(marqueNom));
		}
	}
	
	public void supprimerTache(HttpServletRequest request) {
		tacheDAOImpl.supprimerTache(Long.parseLong(request.getParameter("id")));
	}
	
}
