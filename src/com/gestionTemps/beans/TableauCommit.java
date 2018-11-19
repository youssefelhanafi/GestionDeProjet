package com.gestionTemps.beans;

import java.util.Date;

public class TableauCommit {
	
	private Long idCommit;
	private String textCommit;
	private Date dateCommit;
	private Long tableauId;
	public Long getIdCommit() {
		return idCommit;
	}
	public void setIdCommit(Long idCommit) {
		this.idCommit = idCommit;
	}
	public String getTextCommit() {
		return textCommit;
	}
	public TableauCommit(Long idCommit, String textCommit, Date dateCommit, Long tableauId) {
		super();
		this.textCommit = textCommit;
		this.dateCommit = dateCommit;
		this.tableauId = tableauId;
		this.idCommit = idCommit;
	}
	public TableauCommit() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setTextCommit(String textCommit) {
		this.textCommit = textCommit;
	}
	public Date getDateCommit() {
		return dateCommit;
	}
	public void setDateCommit(Date dateCommit) {
		this.dateCommit = dateCommit;
	}
	public Long getTableauId() {
		return tableauId;
	}
	public void setTableauId(Long tableauId) {
		this.tableauId = tableauId;
	}

}
