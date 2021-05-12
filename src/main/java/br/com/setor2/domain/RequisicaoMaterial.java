package br.com.setor2.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class RequisicaoMaterial extends GenericDomain {

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;

	@Column(length = 15, nullable = false)
	private String numero;

	@Column(length = 30)
	private String tipoRequisicao;

	@Column(precision = 7, scale = 2)
	private BigDecimal valor;

	@Column()
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

	public String getTipoRequisicao() {
		return tipoRequisicao;
	}

	public void setTipoRequisicao(String tipoRequisicao) {
		this.tipoRequisicao = tipoRequisicao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
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
		return "RequisicaoMaterial [dataCadastro=" + dataCadastro + ", numero=" + numero + ", tipoRequisicao="
				+ tipoRequisicao + ", valor=" + valor + ", observação=" + observacao + ", finalizado=" + finalizado
				+ "]";
	}

}
