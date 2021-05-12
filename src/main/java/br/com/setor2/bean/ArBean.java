package br.com.setor2.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.setor2.dao.ArDAO;
import br.com.setor2.dao.SalaDAO;
import br.com.setor2.domain.Ar;
import br.com.setor2.domain.Sala;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ArBean implements Serializable {
	private Ar ar;
	private List<Ar> listaAres;
	private List<Sala> salas;

	public Ar getAr() {
		return ar;
	}

	public void setAr(Ar ar) {
		this.ar = ar;
	}

	public List<Ar> getListaAres() {
		return listaAres;
	}

	public void setListaAres(List<Ar> listaAres) {
		this.listaAres = listaAres;
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
			ArDAO arDAO = new ArDAO();
			listaAres = arDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar os ares");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			ar = new Ar();

			SalaDAO salaDAO = new SalaDAO();
			salas = salaDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar gerar uma nova sala");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			ArDAO arDAO = new ArDAO();
			arDAO.merge(ar);

			novo();
			listaAres = arDAO.listar();

			Messages.addGlobalInfo("Ar salvo com sucesso");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar salvar o ar");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			ar = (Ar) evento.getComponent().getAttributes().get("arSelecionado");

			ArDAO arDAO = new ArDAO();
			arDAO.excluir(ar);

			listaAres = arDAO.listar();
			Messages.addGlobalInfo("Ar excluído com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar excluir o ar");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			ar = (Ar) evento.getComponent().getAttributes().get("arSelecionado");

			SalaDAO salaDAO = new SalaDAO();
			salas = salaDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar gerar uma nova sala");
			erro.printStackTrace();
		}
	}
}
