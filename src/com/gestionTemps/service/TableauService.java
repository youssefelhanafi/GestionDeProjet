package com.gestionTemps.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.gestionTemps.beans.Liste;
import com.gestionTemps.beans.Marque;
import com.gestionTemps.beans.Tableau;
import com.gestionTemps.beans.TableauCommit;
import com.gestionTemps.beans.Tache;
import com.google.gson.Gson;

public class TableauService {
	private TableauDAOImplem tableauDAOImplem = new TableauDAOImplem();
	
	private Tableau retournerTableau(Long tableauID) {
		Tableau tableau = tableauDAOImplem.recupererTableau(tableauID);
		return tableau;
	}
	
	public Tableau retournerTableau(HttpServletRequest request) {
		return retournerTableau(Long.parseLong(request.getParameter("id")));
	}
	
	public List<Liste> retournerListesDuTableau(Long tableauID){
		return tableauDAOImplem.recupererToutesLesListesDuTableau(tableauID);
	}
	
	public void ajouterListeDansLeTableau(HttpServletRequest request) {
		String nom = request.getParameter("nom");
		String desc = request.getParameter("desc");
		Long id = Long.parseLong(request.getParameter("id"));
		Liste liste = new Liste(nom, desc, id);
		tableauDAOImplem.ajouterListeAuTableau(liste);
	}
	
	public List<TableauCommit> recupererToutesLesCommitesDuTableau(HttpServletRequest request){
		return tableauDAOImplem.recupererToutesLesCommitesDuTableau(Long.parseLong(request.getParameter("id")));
	}
	
	public List<Tache> retournerToutesLesTachesDuTableau(Long tableauID){
		List<Tache> taches = tableauDAOImplem.recupererToutesLesTachesDuTableau(tableauID);
		TacheDAOImpl tacheDAOImpl = new TacheDAOImpl();
		Gson gson = new Gson();
		for (Tache tache : taches) {
			List<Marque> marques = tacheDAOImpl.recupererToutesLesMarquesDeLaTache(tache.getIdTache());
			if(marques != null)
				tache.setNbrMarques(marques.size());
			String m = "";
			for (Marque marque : marques) {
				m += marque.getNomMarque() + ",";
			}
			tache.setMarques(m);
			Double b = (double) Math.abs(tache.getDateLimiteDeTache().getTime()*1.0 - tache.getDateDeCreationDeTache().getTime()*1.0);
			Double a = (double) Math.abs(new Date().getTime()*1.0 - tache.getDateDeCreationDeTache().getTime()*1.0);
			tache.setPourcentage(Math.floor( (100*(a/b) * 100) / 100));
			tache.setJsonData(gson.toJson(tache));
		}
		return taches;
	}
	
	public List<Marque> retournerToutesLesMarquesDuTableau(Long tableauID){
		MarqueDAOImpl marqueDAOImpl = new MarqueDAOImpl();
		List<Tache> taches = retournerToutesLesTachesDuTableau(tableauID);
		TacheDAOImpl tacheDAOImpl = new TacheDAOImpl();
		List<Marque> marques = new ArrayList<Marque>();
		Set<Long> marques_id = new HashSet<Long>();
		for (Tache tache : taches) {
			marques_id.addAll(tacheDAOImpl.recupererToutesLesMarquesIDDeLaTache(tache.getIdTache()));
		}
		for (Long marqueID : marques_id) {
			marques.add(marqueDAOImpl.recupererMarque(marqueID));
		}
		return marques;
	}
	
	public Date getStartDate(List<Tache> taches) {
		if(taches.isEmpty()) return null;
		Date ans = taches.get(0).getDateDeCreationDeTache();
		for (Tache tache : taches) {
			if(ans.compareTo(tache.getDateDeCreationDeTache()) > 0)
				ans = tache.getDateDeCreationDeTache();
		}
		return ans;
	}
	
	public Date getEndDate(List<Tache> taches) {
		if(taches.isEmpty()) return null;
		Date ans = taches.get(0).getDateLimiteDeTache();
		for (Tache tache : taches) {
			if(ans.compareTo(tache.getDateLimiteDeTache()) < 0)
				ans = tache.getDateLimiteDeTache();
		}
		return ans;
	}
	
	public int getHighPriorityCount(List<Tache> taches) {
		int ans = 0;
		for (Tache tache : taches) {
			if(tache.getPriorite() >= 3) ans++;
		}
		return ans;
	}
	
	public int getLowPriorityCount(List<Tache> taches) {
		int ans = 0;
		for (Tache tache : taches) {
			if(tache.getPriorite() <= 1) ans++;
		}
		return ans;
	}
	
	public int getNormalPriorityCount(List<Tache> taches) {
		int ans = 0;
		for (Tache tache : taches) {
			if(tache.getPriorite() == 2) ans++;
		}
		return ans;
	}

}
