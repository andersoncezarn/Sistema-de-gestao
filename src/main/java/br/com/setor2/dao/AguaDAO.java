package br.com.setor2.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.setor2.domain.Agua;
import br.com.setor2.util.HibernateUtil;

public class AguaDAO extends GenericDAO<Agua> {

	public Agua buscar(Date dia) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Criteria consulta = sessao.createCriteria(Agua.class);
			consulta.add(Restrictions.eq("dia", dia));
			Agua resultado = (Agua) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	public List<Agua> buscar(Date inicial, Date fim) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Criteria consulta = sessao.createCriteria(Agua.class);
			consulta.add(Restrictions.between("dia", inicial, fim));
			@SuppressWarnings("unchecked")
			List<Agua> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
