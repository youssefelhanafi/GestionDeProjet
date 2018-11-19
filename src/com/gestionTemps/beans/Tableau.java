package com.gestionTemps.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tableau {
	
	private Long idTableau;
	private String nomTableau;
	private String descriptionTableau;
	private int nombreDesListesDansLeTableau;
	private int nbrTags;
	private int nbrCommits;
	private int nbrTaches;
	private Long userID;
	private Date dateCreation;
	private Long tachesSupprimes;

	public Long getTachesSupprimes() {
		return tachesSupprimes;
	}
	public void setTachesSupprimes(Long tachesSupprimes) {
		this.tachesSupprimes = tachesSupprimes;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public int getNbrCommits() {
		return nbrCommits;
	}
	public void setNbrCommits(int nbrCommits) {
		this.nbrCommits = nbrCommits;
	}
	public int getNombreDesListesDansLeTableau() {
		return nombreDesListesDansLeTableau;
	}
	public void setNombreDesListesDansLeTableau(int nombreDesListesDansLeTableau) {
		this.nombreDesListesDansLeTableau = nombreDesListesDansLeTableau;
	}
	public String getDescriptionTableau() {
		return descriptionTableau;
	}
	public void setDescriptionTableau(String descriptionTableau) {
		this.descriptionTableau = descriptionTableau;
	}
	private List<Liste> listesDuTableau;
	
	public Long getIdTableau() {
		return idTableau;
	}
	public void setIdTableau(Long idTableau) {
		this.idTableau = idTableau;
	}
	public String getNomTableau() {
		return nomTableau;
	}
	public void setNomTableau(String nomTableau) {
		this.nomTableau = nomTableau;
	}
	public List<Liste> getListesDuTableau() {
		return listesDuTableau;
	}
	public void setListesDuTableau(List<Liste> listesDuTableau) {
		this.listesDuTableau = listesDuTableau;
	}
	public Tableau() {
		super();
		this.nombreDesListesDansLeTableau = 0;
		this.listesDuTableau = new ArrayList<Liste>();
		this.nbrTags = 0;
		this.nbrCommits = 0;
		this.nombreDesListesDansLeTableau = 0;
		this.tachesSupprimes = 0L;
	}
	public Tableau(String nomTableau, String descriptionTableau) {
		super();
		this.nomTableau = nomTableau;
		this.descriptionTableau = descriptionTableau;
		this.nombreDesListesDansLeTableau = 0;
		this.listesDuTableau = new ArrayList<Liste>();
		this.nbrTags = 0;
		this.nbrCommits = 0;
		this.nombreDesListesDansLeTableau = 0;
		this.tachesSupprimes = 0L;
	}
	public Tableau(Tableau t) {
		super();
		this.nomTableau = t.getNomTableau();
		this.descriptionTableau = t.descriptionTableau;
		this.nbrTags = 0;
		this.nombreDesListesDansLeTableau = t.getNombreDesListesDansLeTableau();
		this.listesDuTableau = new ArrayList<>(t.getListesDuTableau());
		this.tachesSupprimes = 0L;
	}
	public int getNbrTags() {
		return nbrTags;
	}
	public void setNbrTags(int nbrTags) {
		this.nbrTags = nbrTags;
	}
	public int getNbrTaches() {
		return nbrTaches;
	}
	public void setNbrTaches(int nbrTaches) {
		this.nbrTaches = nbrTaches;
	}
	
	

}
