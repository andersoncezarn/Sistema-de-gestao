package br.com.setor2.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.setor2.dao.EstabilizadorDAO;
import br.com.setor2.dao.SalaDAO;
import br.com.setor2.domain.Estabilizador;
import br.com.setor2.domain.Sala;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EstabilizadorBean implements Serializable {
	private Estabilizador estabilizador;
	private List<Estabilizador> listaEstabilizadores;
	private List<Sala> salas;

	public Estabilizador getEstabilizador() {
		return estabilizador;
	}

	public void setEstabilizador(Estabilizador estabilizador) {
		this.estabilizador = estabilizador;
	}

	public List<Estabilizador> getListaEstabilizadores() {
		return listaEstabilizadores;
	}

	public void setListaEstabilizadores(List<Estabilizador> listaEstabilizadores) {
		this.listaEstabilizadores = listaEstabilizadores;
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
			EstabilizadorDAO estabilizadorDAO = new EstabilizadorDAO();
			listaEstabilizadores = estabilizadorDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar os estabilizadors");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			estabilizador = new Estabilizador();

			SalaDAO salaDAO = new SalaDAO();
			salas = salaDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar gerar uma nova sala");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			EstabilizadorDAO estabilizadorDAO = new EstabilizadorDAO();
			estabilizadorDAO.merge(estabilizador);

			novo();
			listaEstabilizadores = estabilizadorDAO.listar();
			Messages.addGlobalInfo("Estabilizador salvo com sucesso");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar salvar o estabilizador");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			estabilizador = (Estabilizador) evento.getComponent().getAttributes().get("estabilizadorSelecionado");

			EstabilizadorDAO estabilizadorDAO = new EstabilizadorDAO();
			estabilizadorDAO.excluir(estabilizador);

			listaEstabilizadores = estabilizadorDAO.listar();

			Messages.addGlobalInfo("Estabilizador excluído com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar excluir o estabilizador");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			estabilizador = (Estabilizador) evento.getComponent().getAttributes().get("estabilizadorSelecionado");

			SalaDAO salaDAO = new SalaDAO();
			salas = salaDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar gerar uma nova sala");
			erro.printStackTrace();
		}
	}
}
