package br.com.setor2.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Almoxarifado extends GenericDomain{
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date horario;
	
	@Column(nullable = false, precision = 7, scale = 2)
	private Double precoTotal;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Colaborador colaborador;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario usuario;

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public Double getPrecoTotal() {
		return precoTotal;
	}
	
	public void setPrecoTotal(Double precoTotal) {
		this.precoTotal = precoTotal;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
