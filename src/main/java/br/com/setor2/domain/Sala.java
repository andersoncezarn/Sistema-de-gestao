package br.com.setor2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity
public class Sala extends GenericDomain{
	@Column(length = 20, nullable = false)
	private String nome; //Identificação da sala A1, A2...

	private boolean acessibilidade; //indica se a sala possui acessibilidade
	
	private int capacidade; //Capacidade da sala
	
	@OneToOne
	@JoinColumn(unique = true)
	private Datashow datashow;
	
	@OneToOne
	@JoinColumn(unique = true)
	private Som som;
	
	@OneToOne
	@JoinColumn(unique = true)
	private Estabilizador estabilizador;
	
	@OneToOne
	@JoinColumn(unique = true)
	private Ar arEsq;
	
	@OneToOne
	@JoinColumn(unique = true)
	private Ar arDir;
	
	@OneToOne
	@JoinColumn(unique = true)
	private Ar arMeio;
 
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isAcessibilidade() {
		return acessibilidade;
	}

	public void setAcessibilidade(boolean acessibilidade) {
		this.acessibilidade = acessibilidade;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public Datashow getDatashow() {
		return datashow;
	}

	public void setDatashow(Datashow datashow) {
		this.datashow = datashow;
	}

	public Som getSom() {
		return som;
	}

	public void setSom(Som som) {
		this.som = som;
	}

	public Estabilizador getEstabilizador() {
		return estabilizador;
	}

	public void setEstabilizador(Estabilizador estabilizador) {
		this.estabilizador = estabilizador;
	}
	
	
	public Ar getArEsq() {
		return arEsq;
	}

	public void setArEsq(Ar arEsq) {
		this.arEsq = arEsq;
	}

	public Ar getArDir() {
		return arDir;
	}

	public void setArDir(Ar arDir) {
		this.arDir = arDir;
	}

	public Ar getArMeio() {
		return arMeio;
	}

	public void setArMeio(Ar arMeio) {
		this.arMeio = arMeio;
	}
}
