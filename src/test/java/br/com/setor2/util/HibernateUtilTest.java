package br.com.setor2.util;

import org.hibernate.Session;
import org.junit.Test;

import br.com.setor2.dao.FuncionarioDAO;
import br.com.setor2.dao.UsuarioDAO;
import br.com.setor2.domain.Funcionario;
import br.com.setor2.domain.Usuario;

public class HibernateUtilTest {
	
	@Test
	public void conectar() {
		
//		Usuario usuario = new Usuario();
//		usuario.setNome("Anderson");
//		usuario.setSenha("1234444444444");
//		System.out.println(" \n\n\n\n\n\n " + usuario.toString());
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Anderson");
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarioDAO.salvar(funcionario);
		
		Usuario usuario = new Usuario();
		usuario.setAtivo(true);
		usuario.setLogin("anderson");
		usuario.setFuncionario(funcionario);
		usuario.setSenha("C4CA4238A0B923820DCC509A6F75849B");
		
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
		
		sessao.close();
		HibernateUtil.getFabricaDeSessoes().close();
	
	}
}
