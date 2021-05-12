package br.com.setor2.bean;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.setor2.dao.UsuarioDAO;
import br.com.setor2.domain.Usuario;

@ManagedBean
@SessionScoped
public class AutenticacaoBean {
	private Usuario usuario;
	private Usuario usuarioLogado;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	@PostConstruct
	public void iniciar() {
		usuario = new Usuario();
	}

	public void autenticar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioLogado = usuarioDAO.autenticar(usuario.getLogin(), usuario.getSenha());

			if (usuarioLogado == null) {
				Messages.addGlobalError("Login e/ou senha incorretos.");
				return;
			}

			Faces.redirect("./pages/principal.xhtml");
		} catch (IOException e) {
			Messages.addGlobalError(e.getMessage());
		}
	}

	public void deslogar() {
		try {
			usuarioLogado = null;
			Faces.redirect("./pages/login.xhtml");
		} catch (IOException e) {
			Messages.addGlobalError(e.getMessage());
		}
	}
}
