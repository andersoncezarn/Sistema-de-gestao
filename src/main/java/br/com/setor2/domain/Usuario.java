package br.com.setor2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/* AULA 10
 * extends - herdando da classe GerericDomain, é necessário para utilizar
 * a chave primária codigo que é atribito da classe herdada.
 * 
 * Lembrando: Os atributos dessa classe são todos privates e devem possuir get/set.
 * 
 * Entity - Serve pra dizer que a classe é uma entidade do Hibernate, ou seja, a classe
 * é uma tabela do meu BD. O nome das colunas serão os atributos.
 * 
 * A entidade deve ser mapeada no cfg do hibernate.
 * 
 * @Transient é um campo que eu utilizo, mas não será gravado no banco
 */

@SuppressWarnings("serial")
@Entity
public class Usuario extends GenericDomain {
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Funcionario funcionario;

	@Column(nullable = false, length = 45, unique = true)
	private String login;

	@Column(length = 32, nullable = false)
	private String senha;
	
	@Transient
	private String senhaSemCriptografia;

	@Column(nullable = true)
	private Boolean ativo = true;

	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}
	
	public String getSenhaSemCriptografia() {
		return senhaSemCriptografia;
	}
	
	public void setSenhaSemCriptografia(String senhaSemCriptografia) {
		this.senhaSemCriptografia = senhaSemCriptografia;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		return "Usuario [login=" + login + ", senha=" + senha + ", ativo=" + ativo + "]";
	}

}
