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
public class ManutencaoAr extends GenericDomain{
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Sala sala;
	
	@ManyToOne
	@JoinColumn
	private Ar ar;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	
	@Column(nullable = false)
	private String descricao;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAtendimento;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataConserto;
	
	@Column(nullable = false)
	private Boolean finalizado = false;
	
	@Column
	private String observacao;

	@Column
	private String tecnico;

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataConserto() {
		return dataConserto;
	}

	public void setDataConserto(Date dataConserto) {
		this.dataConserto = dataConserto;
	}

	public Boolean getFinalizado() {
		return finalizado;
	}

	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getTecnico() {
		return tecnico;
	}

	public void setTecnico(String tecnico) {
		this.tecnico = tecnico;
	}
	
	public Ar getAr() {
		return ar;
	}

	public void setAr(Ar ar) {
		this.ar = ar;
	}

	public Date getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(Date dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	@Override
	public String toString() {
		return "ManutencaoAr [sala=" + sala + ", ar=" + ar + ", dataCadastro=" + dataCadastro + ", descricao="
				+ descricao + ", dataAtendimento=" + dataAtendimento + ", dataConserto=" + dataConserto
				+ ", finalizado=" + finalizado + ", observacao=" + observacao + ", tecnico=" + tecnico + "]";
	}

}
