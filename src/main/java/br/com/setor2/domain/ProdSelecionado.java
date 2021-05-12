package br.com.setor2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class ProdSelecionado extends GenericDomain {
	@Column(nullable = false)
	private int quantidade;

	@Column(nullable = false, precision = 7, scale = 2)
	private Double precoParcial;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Estoque estoque;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Almoxarifado almoxarifado;

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPrecoParcial() {
		return precoParcial;
	}

	public void setPrecoParcial(Double precoParcial) {
		this.precoParcial = precoParcial;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public Almoxarifado getAlmoxarifado() {
		return almoxarifado;
	}

	public void setAlmoxarifado(Almoxarifado almoxarifado) {
		this.almoxarifado = almoxarifado;
	}
}
