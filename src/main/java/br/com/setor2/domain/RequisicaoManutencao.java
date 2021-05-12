package br.com.setor2.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class RequisicaoManutencao extends GenericDomain {
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	
	@Column(length = 15, nullable = false)
	private String numero;
	
	@Column(length = 30)
	private String tipoServico;
	
	@Column(nullable = false)
	private String descricao;
	
	@Column(length = 30)
	private String local;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAtendimento;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataServico;
	
	private String observacao;
	
	@Column(nullable = false)
	private Boolean finalizado = false;

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Date getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(Date dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public Date getDataServico() {
		return dataServico;
	}

	public void setDataServico(Date dataServico) {
		this.dataServico = dataServico;
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
		return "RequisicaoManutencao [dataCadastro=" + dataCadastro + ", tipoServico=" + tipoServico + ", descricao="
				+ descricao + ", local=" + local + ", dataAtendimento=" + dataAtendimento + ", dataServico="
				+ dataServico + ", observacao=" + observacao + ", finalizado=" + finalizado + "]";
	}
}
