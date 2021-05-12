package br.com.setor2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity
public class Som extends GenericDomain{
	@Column(length = 15, unique = true, nullable = false)
	private String tombo;
	
	@Column(length = 20)
	private String marca;
	
	@OneToOne
	private Sala sala;

	public String getTombo() {
		return tombo;
	}

	public void setTombo(String tombo) {
		this.tombo = tombo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	@Override
	public String toString() {
		return "Som [tombo=" + tombo + ", marca=" + marca + ", sala=" + sala + "]";
	}

}
