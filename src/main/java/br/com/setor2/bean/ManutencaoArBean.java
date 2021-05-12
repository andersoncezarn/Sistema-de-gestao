package br.com.setor2.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.setor2.dao.ArDAO;
import br.com.setor2.dao.ManutencaoArDAO;
import br.com.setor2.dao.SalaDAO;
import br.com.setor2.domain.Ar;
import br.com.setor2.domain.ManutencaoAr;
import br.com.setor2.domain.Sala;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ManutencaoArBean implements Serializable {

	private ManutencaoAr manutencaoAr;
	private List<ManutencaoAr> manutAres;
	private List<Sala> salas;
	private List<Ar> ares;

	public ManutencaoAr getManutencaoAr() {
		return manutencaoAr;
	}

	public void setManutencaoAr(ManutencaoAr manutencaoAr) {
		this.manutencaoAr = manutencaoAr;
	}

	public List<ManutencaoAr> getManutAres() {
		return manutAres;
	}

	public void setManutAres(List<ManutencaoAr> manutAres) {
		this.manutAres = manutAres;
	}

	public List<Sala> getSalas() {
		return salas;
	}

	public void setSalas(List<Sala> salas) {
		this.salas = salas;
	}

	public List<Ar> getAres() {
		return ares;
	}

	public void setAres(List<Ar> ares) {
		this.ares = ares;
	}

	@PostConstruct
	public void listar() {
		try {
			ManutencaoArDAO ManutencaoArDAO = new ManutencaoArDAO();
			manutAres = ManutencaoArDAO.listar("finalizado", "dataCadastro");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar as requisições de material.");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			manutencaoAr = new ManutencaoAr();

			SalaDAO salaDAO = new SalaDAO();
			salas = salaDAO.listar("nome");

			ares = new ArrayList<Ar>();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar gerar uma nova sala");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			ManutencaoArDAO ManutencaoArDAO = new ManutencaoArDAO();
			ManutencaoArDAO.merge(manutencaoAr);

			novo();
			manutAres = ManutencaoArDAO.listar("finalizado", "dataCadastro"); // Recarrega a pesquisa quando um novo
																				// funcionário é incluído.

			Messages.addGlobalInfo("Manutenção de ar salva com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar salvar manutenção de ar.");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			manutencaoAr = (ManutencaoAr) evento.getComponent().getAttributes().get("manutArSelecionada");

			ManutencaoArDAO ManutencaoArDAO = new ManutencaoArDAO();
			ManutencaoArDAO.excluir(manutencaoAr);

			manutAres = ManutencaoArDAO.listar("finalizado", "dataCadastro"); // Recarrega a pesquisa quando um novo
																				// funcionário é excluído.

			Messages.addGlobalInfo("Manutenção excluída com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar excluir a manutenção.");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			ArDAO arDAO = new ArDAO();
			ares = arDAO.listar();
			
			SalaDAO salaDAO = new SalaDAO();
			salas = salaDAO.listar();
			
			manutencaoAr = (ManutencaoAr) evento.getComponent().getAttributes().get("manutArSelecionada");
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar gerar uma nova sala");
			erro.printStackTrace();
		}
	}

	public void popular() {
		try {
			if (manutencaoAr.getSala() != null) {
				ArDAO arDAO = new ArDAO();
				ares = arDAO.buscarPorSala(manutencaoAr.getSala().getId());
			}
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar filtrar os ares da sala");
			erro.printStackTrace();
		}
	}

}
