package br.com.setor2.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.setor2.dao.FuncionarioDAO;
import br.com.setor2.domain.Funcionario;

/* Messages.addGlobalInfo utilizado para mostrar mensagem na tela: omnifaces
 * o método novo serve para iniciar o estado, pois caso contrário aparecerá nullpointer;
 * lembrar de colocar o @ViewScoped
 * 
 * A variável listaFuncionarios é utilizada para listar os funcionários na tela de pesquisa;
 * 
 * No método Excluir o ActionEvent serve para capturar o que foi enviado da visão, ou seja,
 * a linha selecionada em tela.
 * 
 * O método editar recebe apenas a linha selecionada na tela, a edição é feita pelo comando merge
 * no método salvar. Caso não exista funcionário selecionado é realizada a inclusão, caso contrário
 * é feito a edição.
 */

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FuncionarioBean implements Serializable {
	private Funcionario funcionario;
	private List<Funcionario> listaFuncionarios;

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}
	
	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}
	
	@PostConstruct
	public void listar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			listaFuncionarios = funcionarioDAO.listar();
		}catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os funcionários.");
			erro.printStackTrace();
		} 
	}
	
	public void novo() {
		funcionario = new Funcionario();
	}

	public void salvar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.merge(funcionario);

			novo();
			listaFuncionarios = funcionarioDAO.listar(); //Recarrega a pesquisa quando um novo funcionário é incluído.
			
			Messages.addGlobalInfo("Funcionário salvo com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar salvar o funcionário");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento) {
		try {
			funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");

			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.excluir(funcionario);
			
			listaFuncionarios = funcionarioDAO.listar(); // Recarrega a pesquisa quando um novo funcionário é excluído.

			Messages.addGlobalInfo("Funcionário excluído com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar excluir o funcionário.");
			erro.printStackTrace();
		}
	}
	public void editar(ActionEvent evento) {
		funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");
	}
}
