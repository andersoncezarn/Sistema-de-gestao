package br.com.setor2.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.setor2.dao.UnidadeDAO;
import br.com.setor2.domain.Unidade;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UnidadeBean implements Serializable {

	private Unidade unidade;

	private List<Unidade> listaUnidades;

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
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
			UnidadeDAO unidadeDAO = new UnidadeDAO();
			listaUnidades = unidadeDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar as unidadees");
		}
	}

	public void novo() {
		unidade = new Unidade();
	}

	public void salvar() {
		try {
			UnidadeDAO unidadeDAO = new UnidadeDAO();
			unidadeDAO.merge(unidade);

			unidade = new Unidade();
			listaUnidades = unidadeDAO.listar(); // Recarrega a pesquisa quando um novo funcionário é incluído.

			Messages.addGlobalInfo("Unidade salva com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar salvar a unidade");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			unidade = (Unidade) evento.getComponent().getAttributes().get("unidadeSelecionada");

			UnidadeDAO unidadeDAO = new UnidadeDAO();
			unidadeDAO.excluir(unidade);

			listaUnidades = unidadeDAO.listar(); // Recarrega a pesquisa quando um novo funcionário é excluído.

			Messages.addGlobalInfo("Unidade excluída com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar excluir o unidade.");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
			unidade = (Unidade) evento.getComponent().getAttributes().get("unidadeSelecionada");
	}
}
