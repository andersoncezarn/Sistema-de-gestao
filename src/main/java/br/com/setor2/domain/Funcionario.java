package br.com.setor2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Funcionario extends GenericDomain {

	@Column(length = 50, nullable = false)
	private String nome;

	@Column(length = 14)
	private String telefone;

	@Column(length = 50)
	private String email;

	@Column(length = 14)
	private String cpf;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "Funcionario [nome=" + nome + ", telefone=" + telefone + ", ativo=" + "]";
	}

}
