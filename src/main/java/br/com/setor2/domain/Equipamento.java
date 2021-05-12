package br.com.setor2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Equipamento extends GenericDomain{
	
	@Column(length = 15, unique = true, nullable = false)
	private String tombo;
	
	@Column(length = 20)
	private String marca;
	
	@Column(length = 20)
	private String tipo;
	
	@ManyToOne
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	@Override
	public String toString() {
		return "Equipamento [tombo=" + tombo + ", marca=" + marca + ", tipo=" + tipo + ", sala=" + sala + "]";
	}
	
	
}
