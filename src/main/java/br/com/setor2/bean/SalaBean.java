package br.com.setor2.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.setor2.dao.ArDAO;
import br.com.setor2.dao.DatashowDAO;
import br.com.setor2.dao.EstabilizadorDAO;
import br.com.setor2.dao.SalaDAO;
import br.com.setor2.dao.SomDAO;
import br.com.setor2.domain.Ar;
import br.com.setor2.domain.Datashow;
import br.com.setor2.domain.Estabilizador;
import br.com.setor2.domain.Sala;
import br.com.setor2.domain.Som;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class SalaBean implements Serializable {
	private Sala sala;
	private List<Sala> listaSalas;
	private List<Datashow> datashows;
	private List<Ar> ares;
	private List<Estabilizador> estabilizadores;
	private List<Som> sons;

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public List<Sala> getListaSalas() {
		return listaSalas;
	}

	public void setListaSalas(List<Sala> listaSalas) {
		this.listaSalas = listaSalas;
	}

	public List<Datashow> getDatashows() {
		return datashows;
	}

	public void setDatashows(List<Datashow> datashows) {
		this.datashows = datashows;
	}

	public List<Ar> getAres() {
		return ares;
	}

	public void setAres(List<Ar> ares) {
		this.ares = ares;
	}

	public List<Estabilizador> getEstabilizadores() {
		return estabilizadores;
	}

	public void setEstabilizadores(List<Estabilizador> estabilizadores) {
		this.estabilizadores = estabilizadores;
	}

	public List<Som> getSons() {
		return sons;
	}

	public void setSons(List<Som> sons) {
		this.sons = sons;
	}

	@PostConstruct
	public void listar() {
		try {
			SalaDAO salaDAO = new SalaDAO();
			listaSalas = salaDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar os salas");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			sala = new Sala();

			ArDAO arDAO = new ArDAO();
			ares = arDAO.listar();

			SomDAO somDAO = new SomDAO();
			sons = somDAO.listar();

			EstabilizadorDAO estabilizadorDAO = new EstabilizadorDAO();
			estabilizadores = estabilizadorDAO.listar();

			DatashowDAO datashowDAO = new DatashowDAO();
			datashows = datashowDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar gerar uma nova sala");
			erro.printStackTrace();
		}
	}

	public void salvar() throws SQLException {
		try {
			SalaDAO salaDAO = new SalaDAO();
			
			salaDAO.salvar(sala);
			
			novo();

			listaSalas = salaDAO.listar();
			Messages.addGlobalInfo("Sala salva com sucesso");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar salvar o sala");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			sala = (Sala) evento.getComponent().getAttributes().get("salaSelecionado");

			SalaDAO salaDAO = new SalaDAO();
			salaDAO.excluir(sala);

			listaSalas = salaDAO.listar();

			Messages.addGlobalInfo("Sala excluído com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar excluir o sala");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			sala = (Sala) evento.getComponent().getAttributes().get("salaSelecionada");

			ArDAO arDAO = new ArDAO();
			ares = arDAO.listar();

			SomDAO somDAO = new SomDAO();
			sons = somDAO.listar();

			EstabilizadorDAO estabilizadorDAO = new EstabilizadorDAO();
			estabilizadores = estabilizadorDAO.listar();

			DatashowDAO datashowDAO = new DatashowDAO();
			datashows = datashowDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar gerar uma nova sala");
			erro.printStackTrace();
		}
	}
}
