package com.gestionTemps.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.gestionTemps.beans.Tableau;
import com.gestionTemps.beans.TableauCommit;
import com.gestionTemps.beans.Tache;

public class TableauxService {
	
	private TableauDAOImplem tableauDAOImplem = new TableauDAOImplem();
	
	public List<Tableau> retournerTousLesTableaux(HttpServletRequest request){
		TacheDAOImpl tacheDAOImpl = new TacheDAOImpl();
		List<Tableau> tableaux = tableauDAOImplem.recpererTousLesTableaux(Long.parseLong(request.getSession().getAttribute("userID").toString()));
		for (Tableau tableau : tableaux) {
			List<Tache> taches = tableauDAOImplem.recupererToutesLesTachesDuTableau(tableau.getIdTableau());
			tableau.setNbrTaches(taches.size());
			List<TableauCommit> commits = tableauDAOImplem.recupererToutesLesCommitesDuTableau(tableau.getIdTableau());
			tableau.setNbrCommits(commits.size());
			Set<Long> marques_id = new HashSet<Long>();
			for (Tache tache : taches) {
				marques_id.addAll(tacheDAOImpl.recupererToutesLesMarquesIDDeLaTache(tache.getIdTache()));
			}
			tableau.setNbrTags(marques_id.size());
		}
		return tableaux;
	}
	
	public void ajouterTableau(HttpServletRequest request) {
		String nom = request.getParameter("nom");
		String desc = request.getParameter("desc");
		Tableau tableau = new Tableau(nom, desc);
		tableau.setUserID(Long.parseLong(request.getSession().getAttribute("userID").toString()));
		tableauDAOImplem.ajouterTableau(tableau);
	}
	
	public void supprimerTableau(HttpServletRequest request) {
		tableauDAOImplem = new TableauDAOImplem();
		Long tableauID = Long.parseLong(request.getParameter("id"));
		if(tableauDAOImplem.recupererTableau(tableauID).getUserID() == Long.parseLong(request.getSession().getAttribute("userID").toString()))
			tableauDAOImplem.supprimerTableau(tableauID);
	}

}
