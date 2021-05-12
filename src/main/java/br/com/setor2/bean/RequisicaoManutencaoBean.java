package br.com.setor2.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.setor2.dao.RequisicaoManutencaoDAO;
import br.com.setor2.domain.RequisicaoManutencao;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class RequisicaoManutencaoBean implements Serializable{
	
	private RequisicaoManutencao requisicaoManut;
	private List<RequisicaoManutencao> requisicoes;
	
	public RequisicaoManutencao getRequisicaoManut() {
		return requisicaoManut;
	}
	public void setRequisicaoManut(RequisicaoManutencao requisicaoManut) {
		this.requisicaoManut = requisicaoManut;
	}
	public List<RequisicaoManutencao> getRequisicoes() {
		return requisicoes;
	}
	public void setRequisicoes(List<RequisicaoManutencao> requisicoes) {
		this.requisicoes = requisicoes;
	}
	
	@PostConstruct
	public void listar() {
		try {
			RequisicaoManutencaoDAO requisicaoManutencaoDAO = new RequisicaoManutencaoDAO();
			requisicoes = requisicaoManutencaoDAO.listar("finalizado", "dataCadastro");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar as requisições de Manutencao.");
			erro.printStackTrace();
		}
	}

	public void novo() {
			requisicaoManut = new RequisicaoManutencao();
	}
	
	public void salvar() {
		try {
			RequisicaoManutencaoDAO RequisicaoManutencaoDAO = new RequisicaoManutencaoDAO();
			RequisicaoManutencaoDAO.merge(requisicaoManut);

			novo();
			requisicoes = RequisicaoManutencaoDAO.listar("finalizado", "dataCadastro"); //Recarrega a pesquisa quando um novo funcionário é incluído.
			
			Messages.addGlobalInfo("Requisição de serviços salva com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar salvar a requisição de manutencao");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento) {
		try {
			requisicaoManut = (RequisicaoManutencao) evento.getComponent().getAttributes().get("RequisicaoManutSelecionada");

			RequisicaoManutencaoDAO RequisicaoManutencaoDAO = new RequisicaoManutencaoDAO();
			RequisicaoManutencaoDAO.excluir(requisicaoManut);
			
			requisicoes = RequisicaoManutencaoDAO.listar("finalizado", "dataCadastro"); // Recarrega a pesquisa quando um novo funcionário é excluído.

			Messages.addGlobalInfo("Requisição excluída com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar excluir a requisição.");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
		requisicaoManut = (RequisicaoManutencao) evento.getComponent().getAttributes().get("RequisicaoManutSelecionada");
	}


}
