package br.com.setor2.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.setor2.domain.Ar;
import br.com.setor2.util.HibernateUtil;

public class ArDAO extends GenericDAO<Ar>{
	
	@SuppressWarnings("unchecked")
	public List<Ar> buscarPorSala(Long salaId) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Ar.class);
			consulta.add(Restrictions.eq("sala.id", salaId));	
			List<Ar> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
