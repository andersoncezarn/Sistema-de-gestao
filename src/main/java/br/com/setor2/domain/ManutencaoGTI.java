package br.com.setor2.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class ManutencaoGTI extends GenericDomain {

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	
	@ManyToOne
	private Sala sala;
	
	@Column(nullable = false)
	private String descricao;
	
	@ManyToOne
	private Equipamento equipamento;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEnvio;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataRetorno;
	
	private String observacao;
	
	@Column(nullable = false)
	private Boolean finalizado = false;

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public Date getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public Date getDataRetorno() {
		return dataRetorno;
	}

	public void setDataRetorno(Date dataRetorno) {
		this.dataRetorno = dataRetorno;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Boolean getFinalizado() {
		return finalizado;
	}

	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}

	@Override
	public String toString() {
		return "ManutencaoGTI [dataCadastro=" + dataCadastro + ", sala=" + sala + ", descricao=" + descricao
				+ ", equipamento=" + equipamento + ", dataEnvio=" + dataEnvio + ", dataRetorno=" + dataRetorno
				+ ", observacao=" + observacao + ", finalizado=" + finalizado + "]";
	}
	
}
