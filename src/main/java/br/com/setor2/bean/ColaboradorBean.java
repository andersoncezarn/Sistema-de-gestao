package br.com.setor2.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.setor2.dao.ColaboradorDAO;
import br.com.setor2.dao.FuncionarioDAO;
import br.com.setor2.domain.Colaborador;
import br.com.setor2.domain.Funcionario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ColaboradorBean implements Serializable {

	private Colaborador colaborador;
	private List<Colaborador> listaColaboradores;

	private List<Funcionario> funcionarios;

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public List<Colaborador> getListaColaboradores() {
		return listaColaboradores;
	}

	public void setListaColaboradores(List<Colaborador> listaColaboradores) {
		this.listaColaboradores = listaColaboradores;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	@PostConstruct
	public void listar() {
		try {
			ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
			listaColaboradores = colaboradorDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar os colaboradores");
		}
	}

	public void novo() {
		try {
			colaborador = new Colaborador();

			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar("nome");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar um novo colaborador");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
			colaboradorDAO.merge(colaborador);

			colaborador = new Colaborador();
			listaColaboradores = colaboradorDAO.listar(); // Recarrega a pesquisa quando um novo funcionário é incluído.

			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar();

			Messages.addGlobalInfo("Colaborador salvo com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar salvar o colaborador");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			colaborador = (Colaborador) evento.getComponent().getAttributes().get("colaboradorSelecionado");

			ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
			colaboradorDAO.excluir(colaborador);

			listaColaboradores = colaboradorDAO.listar(); // Recarrega a pesquisa quando um novo funcionário é excluído.

			Messages.addGlobalInfo("Colaborador excluído com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar excluir o colaborador.");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			colaborador = (Colaborador) evento.getComponent().getAttributes().get("colaboradorSelecionado");

			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar selecionar um funcionário");
			erro.printStackTrace();
		}
	}
}
