package br.com.setor2.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.setor2.dao.DatashowDAO;
import br.com.setor2.dao.SalaDAO;
import br.com.setor2.domain.Datashow;
import br.com.setor2.domain.Sala;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class DatashowBean implements Serializable {
	private Datashow datashow;
	private List<Datashow> listaDatashows;
	private List<Sala> salas;

	public Datashow getDatashow() {
		return datashow;
	}

	public void setDatashow(Datashow datashow) {
		this.datashow = datashow;
	}

	public List<Datashow> getListaDatashows() {
		return listaDatashows;
	}

	public void setListaDatashows(List<Datashow> listaDatashows) {
		this.listaDatashows = listaDatashows;
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
			DatashowDAO datashowDAO = new DatashowDAO();
			listaDatashows = datashowDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar os datashows");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			datashow = new Datashow();

			SalaDAO salaDAO = new SalaDAO();
			salas = salaDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar gerar uma nova sala");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			DatashowDAO datashowDAO = new DatashowDAO();
			datashowDAO.merge(datashow);

			datashow = new Datashow();
			
			SalaDAO salaDAO = new SalaDAO();
			salas = salaDAO.listar();
			
			listaDatashows = datashowDAO.listar();
			
			Messages.addGlobalInfo("Datashow salvo com sucesso");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar salvar o datashow");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			datashow = (Datashow) evento.getComponent().getAttributes().get("datashowSelecionado");

			DatashowDAO datashowDAO = new DatashowDAO();
			datashowDAO.excluir(datashow);

			listaDatashows = datashowDAO.listar();

			Messages.addGlobalInfo("Datashow excluído com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar excluir o datashow");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			datashow = (Datashow) evento.getComponent().getAttributes().get("datashowSelecionado");

			SalaDAO salaDAO = new SalaDAO();
			salas = salaDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar gerar uma nova sala");
			erro.printStackTrace();
		}
	}
}
