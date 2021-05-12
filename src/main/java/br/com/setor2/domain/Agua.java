package br.com.setor2.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Agua extends GenericDomain{
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dia;
	
	@Column(nullable = false)
	private int manha = 0;
	
	@Column(nullable = false)
	private int tarde = 0;
	
	@Column(nullable = false)
	private int noite = 0;
	
	@Column(nullable = false)
	private int total = 0;

	public Date getDia() {
		return dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}

	public int getManha() {
		return manha;
	}

	public void setManha(int manha) {
		this.manha = manha;
	}

	public int getTarde() {
		return tarde;
	}

	public void setTarde(int tarde) {
		this.tarde = tarde;
	}

	public int getNoite() {
		return noite;
	}

	public void setNoite(int noite) {
		this.noite = noite;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
}
