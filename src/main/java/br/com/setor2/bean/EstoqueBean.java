package br.com.setor2.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.setor2.dao.EstoqueDAO;
import br.com.setor2.dao.UnidadeDAO;
import br.com.setor2.domain.Estoque;
import br.com.setor2.domain.Unidade;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EstoqueBean implements Serializable {

	private Estoque estoque;

	private List<Estoque> listaEstoque;

	private List<Unidade> listaUnidades;

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public List<Estoque> getListaEstoque() {
		return listaEstoque;
	}

	public void setListaEstoque(List<Estoque> listaEstoque) {
		this.listaEstoque = listaEstoque;
	}

	public List<Unidade> getListaUnidades() {
		return listaUnidades;
	}

	public void setListaUnidades(List<Unidade> listaUnidades) {
		this.listaUnidades = listaUnidades;
	}

	@PostConstruct
	public void listar() {
		try {
			EstoqueDAO estoqueDAO = new EstoqueDAO();
			listaEstoque = estoqueDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar o estoque.");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			estoque = new Estoque();

			UnidadeDAO unidadeDAO = new UnidadeDAO();
			listaUnidades = unidadeDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar um novo estoque");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			EstoqueDAO estoqueDAO = new EstoqueDAO();
			estoqueDAO.merge(estoque);

			estoque = new Estoque();
			listaEstoque = estoqueDAO.listar(); // Recarrega a pesquisa quando um novo estoque é incluído.

			UnidadeDAO unidadeDAO = new UnidadeDAO();
			listaUnidades = unidadeDAO.listar();

			Messages.addGlobalInfo("Prouto salvo com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o produto.");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			estoque = (Estoque) evento.getComponent().getAttributes().get("estoqueSelecionado");

			EstoqueDAO estoqueDAO = new EstoqueDAO();
			estoqueDAO.excluir(estoque);

			listaEstoque = estoqueDAO.listar(); // Recarrega a pesquisa quando um novo estoque é incluído.

			Messages.addGlobalInfo("Estoque excluído com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o estoque.");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			estoque = (Estoque) evento.getComponent().getAttributes().get("estoqueSelecionado");
			
			UnidadeDAO unidadeDAO = new UnidadeDAO();
			listaUnidades = unidadeDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar um novo estoque");
			erro.printStackTrace();
		}
	}
	
	//FAZER DEPOIS
	public void imprimir() {
	}
}
