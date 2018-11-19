package com.gestionTemps.dao;

import java.util.List;

import com.gestionTemps.beans.Marque;

public interface MarqueDAO {
	
	public Marque ajouterMarque(Marque marque);
	public void supprimerMarque(Long marqueID);
	public Marque recupererMarque(Long marqueID);
	public List<Marque> recupererToutesLesMarques();
}
