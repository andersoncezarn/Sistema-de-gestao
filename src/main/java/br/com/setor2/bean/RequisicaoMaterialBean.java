package br.com.setor2.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.setor2.dao.RequisicaoMaterialDAO;
import br.com.setor2.domain.RequisicaoMaterial;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class RequisicaoMaterialBean implements Serializable {
	private RequisicaoMaterial requisicaoMat;
	private List<RequisicaoMaterial> requisicoes;

	public RequisicaoMaterial getRequisicaoMat() {
		return requisicaoMat;
	}

	public void setRequisicaoMat(RequisicaoMaterial requisicaoMat) {
		this.requisicaoMat = requisicaoMat;
	}

	public List<RequisicaoMaterial> getRequisicoes() {
		return requisicoes;
	}

	public void setRequisicoes(List<RequisicaoMaterial> requisicoes) {
		this.requisicoes = requisicoes;
	}

	@PostConstruct
	public void listar() {
		try {
			RequisicaoMaterialDAO requisicaoMaterialDAO = new RequisicaoMaterialDAO();
			requisicoes = requisicaoMaterialDAO.listar("finalizado", "dataCadastro");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar as requisições de material.");
			erro.printStackTrace();
		}
	}

	public void novo() {
			requisicaoMat = new RequisicaoMaterial();
	}
	
	public void salvar() {
		try {
			RequisicaoMaterialDAO RequisicaoMaterialDAO = new RequisicaoMaterialDAO();
			RequisicaoMaterialDAO.merge(requisicaoMat);

			novo();
			requisicoes = RequisicaoMaterialDAO.listar("finalizado", "dataCadastro"); //Recarrega a pesquisa quando um novo funcionário é incluído.
			
			Messages.addGlobalInfo("Pedido de material salvo com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar salvar o pedido de material");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento) {
		try {
			requisicaoMat = (RequisicaoMaterial) evento.getComponent().getAttributes().get("RequisicaoMatSelecionada");

			RequisicaoMaterialDAO RequisicaoMaterialDAO = new RequisicaoMaterialDAO();
			RequisicaoMaterialDAO.excluir(requisicaoMat);
			
			requisicoes = RequisicaoMaterialDAO.listar("finalizado", "dataCadastro"); // Recarrega a pesquisa quando um novo funcionário é excluído.

			Messages.addGlobalInfo("Pedido excluído com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar excluir o pedido.");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
		requisicaoMat = (RequisicaoMaterial) evento.getComponent().getAttributes().get("RequisicaoMatSelecionada");
	}
}