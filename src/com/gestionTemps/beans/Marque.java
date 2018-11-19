package com.gestionTemps.beans;

import java.util.List;

public class Marque {
	
	private Long idMarque;
	private String nomMarque;
	private List<Tache> tachesDeMarque;
	public Long getIdMarque() {
		return idMarque;
	}
	public void setIdMarque(Long idMarque) {
		this.idMarque = idMarque;
	}
	public String getNomMarque() {
		return nomMarque;
	}
	public void setNomMarque(String nomMarque) {
		this.nomMarque = nomMarque;
	}
	public List<Tache> getTachesDeMarque() {
		return tachesDeMarque;
	}
	public void setTachesDeMarque(List<Tache> tachesDeMarque) {
		this.tachesDeMarque = tachesDeMarque;
	}
	public Marque() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Marque(String nomMarque) {
		super();
		this.nomMarque = nomMarque;
	}
	
	

}
