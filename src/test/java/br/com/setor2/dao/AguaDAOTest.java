package br.com.setor2.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.setor2.domain.Agua;

public class AguaDAOTest {
	@SuppressWarnings("deprecation")
	@Test
	@Ignore
	public void salvar() throws ParseException {

		Agua agua = new Agua();
		int valor = 0;
		Date data = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2021");
		for (int i = 0; i <= 10; i++) {

			agua.setDia(data);
			agua.setManha(valor);
			agua.setTarde(valor);
			agua.setNoite(valor);
			agua.setTotal(valor*3);
			AguaDAO aguaDAO = new AguaDAO();
			aguaDAO.salvar(agua);
			valor++;
			data.setDate(data.getDate() + 1);
			System.out.println(data.getDate() + "  " + valor);
			
		}
	}

	@Test
	public void buscar() throws ParseException {
		AguaDAO aguaDAO = new AguaDAO();
		Agua agua = aguaDAO.buscar(new SimpleDateFormat("dd/MM/yyyy").parse("11/01/2021"));
		System.out.println(agua);
		
		List<Agua> aguas = aguaDAO.buscar(new SimpleDateFormat("dd/MM/yyyy").parse("03/01/2021"), new SimpleDateFormat("dd/MM/yyyy").parse("04/01/2021"));
		System.out.println(aguas);
		
	}
}
