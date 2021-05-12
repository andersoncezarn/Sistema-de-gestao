package br.com.setor2.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.setor2.domain.Ar;
import br.com.setor2.domain.Sala;

public class ArDAOTest {
	@Test
	@Ignore
	public void salvar() {
		Ar ar = new Ar();
		ar.setMarca("Philco");
		ar.setBtu("60000");
		ar.setTomboCond("0000000000");
		ar.setTomboEvap("11111111111");
		
		SalaDAO salaDAO = new SalaDAO();
		
		Sala sala = salaDAO.buscar(1L); 
	
		ar.setSala(sala);
		
		ArDAO arDAO = new ArDAO();
		
		arDAO.salvar(ar);
	}
	
	@Test
	@Ignore
	public void listar() {
		ArDAO arDAO = new ArDAO();
		
		List<Ar> resultado = arDAO.listar();
		
		System.out.println(resultado);
	}
	
	@Test
	@Ignore
	public void buscar() {
		ArDAO arDAO = new ArDAO();
		Ar ar = new Ar();
		
		ar = arDAO.buscar(4L);
		
		System.out.println(ar);
		
	}
	
	@Test
	@Ignore
	public void excluir() {
		ArDAO arDAO = new ArDAO();
		
		Ar ar = arDAO.buscar(4L);
		
		arDAO.excluir(ar);
	}
	
	@Test
	@Ignore
	public void editar() {
		ArDAO arDAO = new ArDAO();
		
		Ar ar = arDAO.buscar(5L);
		
		ar.setMarca("Samsung");
		ar.setBtu("400000");
		
		arDAO.editar(ar);
	}

}
