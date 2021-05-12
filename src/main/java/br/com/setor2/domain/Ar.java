package br.com.setor2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Ar extends GenericDomain{
	@Column(length = 15, unique = true, nullable = false)
	private String tomboCond;

	@Column(length = 15, unique = true, nullable = false)
	private String tomboEvap;

	@Column(length = 30)
	private String marca;

	@Column(length = 10)
	private String btu;

	@ManyToOne
	private Sala sala;
	
	@Column(length = 15, nullable = true)
	private String local;

	public String getTomboCond() {
		return tomboCond;
	}

	public void setTomboCond(String tomboCond) {
		this.tomboCond = tomboCond;
	}

	public String getTomboEvap() {
		return tomboEvap;
	}

	public void setTomboEvap(String tomboEvap) {
		this.tomboEvap = tomboEvap;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getBtu() {
		return btu;
	}

	public void setBtu(String btu) {
		this.btu = btu;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}
	
	public String getLocal() {
		return local;
	}
	
	public void setLocal(String local) {
		this.local = local;
	}
}
