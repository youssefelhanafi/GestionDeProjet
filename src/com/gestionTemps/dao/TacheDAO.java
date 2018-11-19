package com.gestionTemps.dao;

import java.util.List;
import java.util.Set;

import com.gestionTemps.beans.Marque;
import com.gestionTemps.beans.Tache;

public interface TacheDAO {
	
	public Tache ajouterTache(Tache tache);
	public void supprimerTache(Long tacheID);
	public void ajouterMarqueDansTache(Long tacheID, Marque marque);
	public void supprimerMarqueDeTache(Long tacheID, Long marqueID);
	public Tache recupererTache(Long tacheID);
	public List<Tache> recupererToutesLesTaches();
	public List<Marque> recupererToutesLesMarquesDeLaTache(Long tacheID);
	Set<Long> recupererToutesLesMarquesIDDeLaTache(Long tacheID);

}
