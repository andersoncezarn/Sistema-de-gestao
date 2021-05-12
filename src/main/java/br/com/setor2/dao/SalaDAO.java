package br.com.setor2.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.setor2.domain.Sala;
import br.com.setor2.util.HibernateUtil;

public class SalaDAO extends GenericDAO<Sala> {

	public void salvar(Sala sala) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			Sala salaAux = new Sala();
			transacao = sessao.beginTransaction();
			if (sala.getId() == null) {
				sessao.save(sala);
			} else {
				SalaDAO salaDAO = new SalaDAO();
				salaAux = salaDAO.buscar(sala.getId());

				if (salaAux.getDatashow() != null) {
					salaAux.getDatashow().setSala(null);
					sessao.merge(salaAux.getDatashow());
				}

				if (salaAux.getSom() != null) {
					salaAux.getSom().setSala(null);
					sessao.merge(salaAux.getSom());
				}
				if (salaAux.getEstabilizador() != null) {
					salaAux.getEstabilizador().setSala(null);
					sessao.merge(salaAux.getEstabilizador());
				}
				if (salaAux.getArEsq() != null) {
					salaAux.getArEsq().setSala(null);
					sessao.merge(salaAux.getArEsq());
				}
				if (salaAux.getArDir() != null) {
					salaAux.getArDir().setSala(null);
					sessao.merge(salaAux.getArDir());
				}
				if (salaAux.getArMeio() != null) {
					salaAux.getArMeio().setSala(null);
					sessao.merge(salaAux.getArMeio());
				}
				sessao.merge(sala);
			}

			if (sala.getDatashow() != null) {
				sala.getDatashow().setSala(sala);

				sessao.merge(sala.getDatashow());
			}
			if (sala.getSom() != null) {
				sala.getSom().setSala(sala);

				sessao.merge(sala.getSom());
			}
			if (sala.getEstabilizador() != null) {
				sala.getEstabilizador().setSala(sala);

				sessao.merge(sala.getEstabilizador());
			}
			if (sala.getArEsq() != null) {
				sala.getArEsq().setSala(sala);

				sessao.merge(sala.getArEsq());
			}
			if (sala.getArDir() != null) {
				sala.getArDir().setSala(sala);

				sessao.merge(sala.getArDir());
			}
			if (sala.getArMeio() != null) {
				sala.getArMeio().setSala(sala);

				sessao.merge(sala.getArMeio());
			}

			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
