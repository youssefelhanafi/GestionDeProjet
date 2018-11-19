package com.gestionTemps.beans;

import java.util.List;

public class Liste {
	
	private Long idListe;
	private String nomListe;
	private String descriptionListe;
	private List<Tache> tachesDeLaListe;
	private Long tableauID;
	public Long getIdListe() {
		return idListe;
	}
	public void setIdListe(Long idListe) {
		this.idListe = idListe;
	}
	public String getNomListe() {
		return nomListe;
	}
	public void setNomListe(String nomListe) {
		this.nomListe = nomListe;
	}
	public String getDescriptionListe() {
		return descriptionListe;
	}
	public void setDescriptionListe(String descriptionListe) {
		this.descriptionListe = descriptionListe;
	}
	public List<Tache> getTachesDeLaListe() {
		return tachesDeLaListe;
	}
	public void setTachesDeLaListe(List<Tache> tachesDeLaListe) {
		this.tachesDeLaListe = tachesDeLaListe;
	}
	public Long getTableauID() {
		return tableauID;
	}
	public void setTableauID(Long tableauID) {
		this.tableauID = tableauID;
	}
	public Liste() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Liste(String nomListe, String descriptionListe, Long tableauID) {
		super();
		this.nomListe = nomListe;
		this.descriptionListe = descriptionListe;
		this.tableauID = tableauID;
	}
	
	

}
