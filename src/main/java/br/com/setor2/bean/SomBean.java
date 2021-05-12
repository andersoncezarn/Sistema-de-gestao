package br.com.setor2.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.setor2.dao.SalaDAO;
import br.com.setor2.dao.SomDAO;
import br.com.setor2.domain.Sala;
import br.com.setor2.domain.Som;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class SomBean implements Serializable{
	private Som som;
	private List<Som> listaSons;
	private List<Sala> salas;

	public Som getSom() {
		return som;
	}

	public void setSom(Som som) {
		this.som = som;
	}

	public List<Som> getListaSons() {
		return listaSons;
	}

	public void setListaSons(List<Som> listaSons) {
		this.listaSons = listaSons;
	}

	public List<Sala> getSalas() {
		return salas;
	}

	public void setSalas(List<Sala> salas) {
		this.salas = salas;
	}

	@PostConstruct
	public void listar() {
		try {
			SomDAO somDAO = new SomDAO();
			listaSons = somDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar os soms");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			som = new Som();

			SalaDAO salaDAO = new SalaDAO();
			salas = salaDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar gerar uma nova sala");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			SomDAO somDAO = new SomDAO();
			somDAO.merge(som);

			novo();
			listaSons = somDAO.listar();
			Messages.addGlobalInfo("Som salvo com sucesso");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar salvar o som");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			som = (Som) evento.getComponent().getAttributes().get("somSelecionado");

			SomDAO somDAO = new SomDAO();
			somDAO.excluir(som);

			listaSons = somDAO.listar();

			Messages.addGlobalInfo("Som excluído com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar excluir o som");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			som = (Som) evento.getComponent().getAttributes().get("somSelecionado");

			SalaDAO salaDAO = new SalaDAO();
			salas = salaDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar gerar uma nova sala");
			erro.printStackTrace();
		}
	}
}
