package br.com.setor2.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.setor2.domain.Usuario;

public class UsuarioDAOTest {
	
	@Test
	@Ignore
	public void salvar() {
		Usuario usuario = new Usuario();
		usuario.setLogin("anderson3");
		usuario.setSenha("1234");
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		usuarioDAO.salvar(usuario);
	}
	
	@Test
	@Ignore
	public void listar() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		List<Usuario> resultado = usuarioDAO.listar();
		
		System.out.println(resultado);
	}
	
	@Test
	@Ignore
	public void buscar() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = new Usuario();
		
		usuario = usuarioDAO.buscar(3L);
		
		System.out.println(usuario);
		
	}
	
	@Test
	@Ignore
	public void excluir() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		Usuario usuario = usuarioDAO.buscar(4L);
		
		usuarioDAO.excluir(usuario);
	}
	
	@Test
	public void editar() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		Usuario usuario = usuarioDAO.buscar(4L);
				
		usuarioDAO.editar(usuario);
	}
}
