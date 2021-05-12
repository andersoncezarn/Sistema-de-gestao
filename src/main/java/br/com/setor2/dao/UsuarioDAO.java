package br.com.setor2.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.setor2.domain.Usuario;
import br.com.setor2.util.HibernateUtil;

/*
 * SimpleHash serve para criptografar a senha, pois a senha está salva no BD 
 * de forma criptografada. Criptografica realizada com algoritmo md5.
 */

public class UsuarioDAO extends GenericDAO<Usuario> {
	
	public Usuario autenticar(String login, String senha) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Criteria consulta = sessao.createCriteria(Usuario.class);
			consulta.add(Restrictions.eq("login", login));
			
			SimpleHash hash = new SimpleHash("md5", senha);
			consulta.add(Restrictions.eq("senha", hash.toHex()));
			
			Usuario resultado = (Usuario) consulta.uniqueResult();
			
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}

	}

}
