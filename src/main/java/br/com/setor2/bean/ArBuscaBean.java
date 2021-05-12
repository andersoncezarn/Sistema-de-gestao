package br.com.setor2.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.setor2.dao.ArDAO;
import br.com.setor2.domain.Ar;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ArBuscaBean implements Serializable{
	private Ar ar;
	private Boolean exibirPainelDados;

	public Ar getAr() {
		return ar;
	}

	public void setAr(Ar ar) {
		this.ar = ar;
	}
	
	public Boolean getExibirPainelDados() {
		return exibirPainelDados;
	}
	
	public void setExibirPainelDados(Boolean exibirPainelDados) {
		this.exibirPainelDados = exibirPainelDados;
	}
	
	@PostConstruct
	public void novo() {
		ar = new Ar();
		exibirPainelDados = false;
	}
	
	public void buscar() {
		try {
			ArDAO arDAO = new ArDAO();
			Ar resultado = arDAO.buscar(ar.getId());

			if (resultado == null) {
				Messages.addGlobalWarn("Não existe ar condicionado com o tombo informado");
				exibirPainelDados = false;
			} else {
				ar = resultado;
				exibirPainelDados = true;
			}
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar buscar o ar");
			erro.printStackTrace();
		}
	}
}
