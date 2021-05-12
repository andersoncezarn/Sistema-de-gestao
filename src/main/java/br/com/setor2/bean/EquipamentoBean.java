package br.com.setor2.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.setor2.dao.EquipamentoDAO;
import br.com.setor2.dao.SalaDAO;
import br.com.setor2.domain.Equipamento;
import br.com.setor2.domain.Sala;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EquipamentoBean implements Serializable {
	private Equipamento equipamento;
	private List<Equipamento> listaEquipamentos;
	private List<Sala> salas;

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public List<Equipamento> getListaEquipamentos() {
		return listaEquipamentos;
	}

	public void setListaEquipamentos(List<Equipamento> listaEquipamentos) {
		this.listaEquipamentos = listaEquipamentos;
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
			EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
			listaEquipamentos = equipamentoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar os equipamentos");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			equipamento = new Equipamento();

			SalaDAO salaDAO = new SalaDAO();
			salas = salaDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar gerar uma nova sala");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
			equipamentoDAO.merge(equipamento);

			novo();
			listaEquipamentos = equipamentoDAO.listar();
			Messages.addGlobalInfo("Equipamento salvo com sucesso");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar salvar o equipamento");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			equipamento = (Equipamento) evento.getComponent().getAttributes().get("equipamentoSelecionado");

			EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
			equipamentoDAO.excluir(equipamento);

			listaEquipamentos = equipamentoDAO.listar();

			Messages.addGlobalInfo("Equipamento excluído com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar excluir o equipamento");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			equipamento = (Equipamento) evento.getComponent().getAttributes().get("equipamentoSelecionado");

			SalaDAO salaDAO = new SalaDAO();
			salas = salaDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar gerar uma nova sala");
			erro.printStackTrace();
		}
	}
}
