package br.com.setor2.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

//Classe criada seguindo o manual do Hibernate, com algumas modificações.
//Basta seguir passo a passo.
public class HibernateUtil {
	private static SessionFactory fabricaDeSessoes = criarFabricaDeSessoes(); // Retorna uma fábrica de sessões

	public static SessionFactory getFabricaDeSessoes() {
		return fabricaDeSessoes;
	}
	
	private static SessionFactory criarFabricaDeSessoes() {
		try {
			Configuration configuracao = new Configuration().configure();	//Busca o arquivo Hibernate.cfg e carrega.
			
			ServiceRegistry registro = new StandardServiceRegistryBuilder().applySettings(configuracao.getProperties()).build();	//Registra o serviço
			
			SessionFactory fabrica = configuracao.buildSessionFactory(registro);
			
			return fabrica;
		}catch (Throwable ex) {
			System.err.println("A fábrica de sessões não pode ser criada." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
}
