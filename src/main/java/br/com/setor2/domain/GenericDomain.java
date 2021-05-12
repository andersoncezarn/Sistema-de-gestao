package br.com.setor2.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

///*
//Esta classe contém o atributo código que será utilizado por todas as outras classes e tabelas do BD
//Nesta classe fica as anotações do Hibernate.
//Os frameworks serializam a camada domain, por isso é necessário o implements
//
//@MappedSuperClass - Anotação pra dizer q esta classe não corrensponde a uma tabela, mas será utilizada para criar outras tabelas.
//@GeneratedValue - O bd vai controlar o autoincremento do id de forma automatica 
//*/

@SuppressWarnings("serial")
@MappedSuperclass 
public class GenericDomain implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; 
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
	    return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenericDomain other = (GenericDomain) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
