package br.com.setor2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Estoque extends GenericDomain{
	
	@Column(length = 80, nullable = false)
	private String produto;
	
	@Column(nullable = false)
	private Short quantidade;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Unidade unidade;
	
	@Column(nullable = false, precision = 7, scale = 2)
	private Double preco;

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public Short getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Short quantidade) {
		this.quantidade = quantidade;
	}
	
	public Unidade getUnidade() {
		return unidade;
	}
	
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	
	public Double getPreco() {
		return preco;
	}
	
	public void setPreco(Double preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "Estoque [produto=" + produto + ", quantidade=" + quantidade + ", unidade=" + unidade + ", preco="
				+ preco + "]";
	}
	
	
}
