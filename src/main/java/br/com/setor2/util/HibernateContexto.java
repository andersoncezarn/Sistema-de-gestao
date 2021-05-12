package br.com.setor2.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/* Classe responsável por iniciar o hibernate quando o tomcat for iniciado,
 * dessa forma melhora o tempo de espera do usuário.
 */

public class HibernateContexto implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		HibernateUtil.getFabricaDeSessoes();
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		HibernateUtil.getFabricaDeSessoes().close();
	}

}
