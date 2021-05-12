package br.com.setor2.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.setor2.dao.AlmoxarifadoDAO;
import br.com.setor2.dao.ColaboradorDAO;
import br.com.setor2.dao.EstoqueDAO;
import br.com.setor2.dao.UsuarioDAO;
import br.com.setor2.domain.Almoxarifado;
import br.com.setor2.domain.Colaborador;
import br.com.setor2.domain.Estoque;
import br.com.setor2.domain.ProdSelecionado;
import br.com.setor2.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class AlmoxarifadoBean implements Serializable {
	private Almoxarifado almoxarifado;
	
	private List<ProdSelecionado> listaSelecionados;
	private List<Estoque> listaEstoque;
	private List<Colaborador> listaColaboradores;
	private List<Usuario> listaUsuarios;

	public List<ProdSelecionado> getListaSelecionados() {
		return listaSelecionados;
	}

	public void setListaSelecionados(List<ProdSelecionado> listaSelecionados) {
		this.listaSelecionados = listaSelecionados;
	}

	public List<Estoque> getListaEstoque() {
		return listaEstoque;
	}

	public void setListaEstoque(List<Estoque> listaEstoque) {
		this.listaEstoque = listaEstoque;
	}

	public Almoxarifado getAlmoxarifado() {
		return almoxarifado;
	}

	public void setAlmoxarifado(Almoxarifado almoxarifado) {
		this.almoxarifado = almoxarifado;
	}

	public List<Colaborador> getListaColaboradores() {
		return listaColaboradores;
	}

	public void setListaColaboradores(List<Colaborador> listaColaboradores) {
		this.listaColaboradores = listaColaboradores;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	@PostConstruct
	public void novo() {
		try {
			almoxarifado = new Almoxarifado();
			almoxarifado.setPrecoTotal(0.00);

			EstoqueDAO estoqueDAO = new EstoqueDAO();
			listaEstoque = estoqueDAO.listar("produto");
			
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			listaUsuarios = usuarioDAO.listar();
			
			ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
			listaColaboradores = colaboradorDAO.listar();

			listaSelecionados = new ArrayList<>();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar o estoque");
			erro.printStackTrace();
		}
	}
	
	
	public void salvar() {
		try {
			if(almoxarifado.getPrecoTotal() == 0) {
				Messages.addGlobalError("Adicione algum material para entrega.");
				return;
			}
			if(almoxarifado.getColaborador() == null) {
				Messages.addGlobalError("Informe a pessoa que receberá o material.");
				return;
			}
			if(almoxarifado.getUsuario() == null) {
				Messages.addGlobalError("Informe o servidor responsável pela entrega.");
				return;
			}
			almoxarifado.setHorario(new Date());
			
			AlmoxarifadoDAO almoxarifadoDAO = new AlmoxarifadoDAO();
			almoxarifadoDAO.salvar(almoxarifado, listaSelecionados);
			
			novo();
			
			Messages.addGlobalInfo("Entrega realizada com sucesso.");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a entrega de material.");
			erro.printStackTrace();
		}
	}

	/*
	 * Soma mais um produto caso este produto já tenha sido selecionado
	 */
	public void adicionar(ActionEvent evento) {
		Estoque estoque = (Estoque) evento.getComponent().getAttributes().get("estoqueSelecionado");

		int achou = -1;

		for (int posicao = 0; posicao < listaSelecionados.size(); posicao++) {
			if (listaSelecionados.get(posicao).getEstoque().equals(estoque)) {
				achou = posicao;
			}
		}

		if (achou < 0) {
			ProdSelecionado prodSelecionado = new ProdSelecionado();
			prodSelecionado.setPrecoParcial(estoque.getPreco());
			prodSelecionado.setEstoque(estoque);
			prodSelecionado.setQuantidade(1);

			listaSelecionados.add(prodSelecionado);
		} else {
			ProdSelecionado prodSelecionado = listaSelecionados.get(achou);
			prodSelecionado.setQuantidade(prodSelecionado.getQuantidade() + 1);
			prodSelecionado.setPrecoParcial(estoque.getPreco() * prodSelecionado.getQuantidade());
		}
		calcular();
	}

	/*
	 * Remove um provduto selecionado
	 */
	public void remover(ActionEvent evento) {
		ProdSelecionado prodSelecionado = (ProdSelecionado) evento.getComponent().getAttributes()
				.get("prodSelecionado");

		int achou = -1;
		for (int posicao = 0; posicao < listaSelecionados.size(); posicao++) {
			if (listaSelecionados.get(posicao).getEstoque().equals(prodSelecionado.getEstoque()))
				;
			achou = posicao;
		}

		if (achou > -1) {
			listaSelecionados.remove(achou);
		}

		calcular();
	}

	public void calcular() {
		almoxarifado.setPrecoTotal(0.00);

		for (int posicao = 0; posicao < listaSelecionados.size(); posicao++) {
			ProdSelecionado prodSelecionado = listaSelecionados.get(posicao);
			almoxarifado.setPrecoTotal(almoxarifado.getPrecoTotal() + prodSelecionado.getPrecoParcial());
		}
	}
	
	public void finalizar() {
		try {
			almoxarifado.setHorario(new Date());
			
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			listaUsuarios = usuarioDAO.listar();
			
			ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
			listaColaboradores = colaboradorDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar finalizar a venda");
		}
	}
}
