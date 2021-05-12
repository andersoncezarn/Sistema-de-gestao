package br.com.setor2.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.omnifaces.util.Messages;

import br.com.setor2.domain.Almoxarifado;
import br.com.setor2.domain.Estoque;
import br.com.setor2.domain.ProdSelecionado;
import br.com.setor2.util.HibernateUtil;

public class AlmoxarifadoDAO extends GenericDAO<Almoxarifado> {
	@SuppressWarnings("deprecation")
	public void salvar(Almoxarifado almoxarifado, List<ProdSelecionado> listaSelecionados) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();

			sessao.save(almoxarifado);

			for (int posicao = 0; posicao < listaSelecionados.size(); posicao++) {
				ProdSelecionado prodSelecionado = listaSelecionados.get(posicao);
				prodSelecionado.setAlmoxarifado(almoxarifado);

				sessao.save(prodSelecionado);

				Estoque estoque = prodSelecionado.getEstoque();

				/*
				 * qtd recebe a diferença entre a quantidade do estoque e quantidade entregue
				 * caso o valor seja maior ou igual a 0, a entrega é realizada, caso contrário
				 * um erro é disparado.
				 */
				int qtd = estoque.getQuantidade() - prodSelecionado.getQuantidade();

				if (qtd >= 0) {
					estoque.setQuantidade(new Short((estoque.getQuantidade() - prodSelecionado.getQuantidade()) + ""));
					sessao.update(estoque);
				} else {
					Messages.addGlobalError("Quantidade no estoque insuficiente.");
					throw new RuntimeException("Quantidade no estoque insuficiente.");
				}
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
