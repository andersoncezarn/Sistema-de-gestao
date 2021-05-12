package br.com.setor2.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.setor2.domain.Sala;

public class SalaDAOTest {
	@Test
	@Ignore
	public void salvar() {
		Sala sala = new Sala();
		sala.setAcessibilidade(true);
		sala.setCapacidade(60);
		sala.setNome("A2");

		Sala sala2 = new Sala();
		sala2.setAcessibilidade(false);
		sala2.setCapacidade(10);
		sala2.setNome("E1");
		
		SalaDAO salaDAO = new SalaDAO();
		
		salaDAO.salvar(sala);
		salaDAO.salvar(sala2);
	}
	
	
	@Test
	@Ignore
	public void listsala() {
		SalaDAO salaDAO = new SalaDAO();
		
		List<Sala> resultado = salaDAO.listar();
		
		System.out.println(resultado);
	}
	
	@Test
	@Ignore
	public void buscar() {
		SalaDAO salaDAO = new SalaDAO();
		Sala sala = salaDAO.buscar(3L);
		
		System.out.println(sala);
		
	}
	
	@Test
	@Ignore
	public void excluir() {
		SalaDAO salaDAO = new SalaDAO();
		
		Sala sala = salaDAO.buscar(4L);
		
		salaDAO.excluir(sala);
	}
	
	@Test
	@Ignore
	public void editar() {
		SalaDAO salaDAO = new SalaDAO();
		
		Sala sala = salaDAO.buscar(3L);
		
		sala.setCapacidade(5);
		
		sala.setNome("F1");
		
		sala.setAcessibilidade(false);
		
		salaDAO.editar(sala);
	}

}
