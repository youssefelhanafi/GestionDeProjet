package com.gestionTemps.dao;

import java.util.List;

import com.gestionTemps.beans.Liste;
import com.gestionTemps.beans.Tableau;
import com.gestionTemps.beans.TableauCommit;
import com.gestionTemps.beans.Tache;

public interface TableauDAO {
	
	public Tableau ajouterTableau(Tableau tableau);
	public void supprimerTableau(Long tableauID);
	public void ajouterListeAuTableau(Liste liste);
	public void supprimerListeDuTableau(Long listID);
	public Tableau recupererTableau(Long tableauID);
	public List<Liste> recupererToutesLesListesDuTableau(Long tableauID);
	public List<TableauCommit> recupererToutesLesCommitesDuTableau(Long tableauID);
	List<Tableau> recpererTousLesTableaux(Long idUtilisateur);
	
	public void ajouterTacheAuTableau(Tache tache);
	public void supprimerTacheDuTableau(Long tacheID);
	public List<Tache> recupererToutesLesTachesDuTableau(Long tableauID);

}
