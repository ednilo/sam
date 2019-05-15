package br.com.planner.sam.controller;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.planner.sam.data.UserRepository;
import br.com.planner.sam.model.Usuario;

@Named
@SessionScoped
public class UsuarioLogadoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;

	@Inject
	private Logger log;

	@Inject
	private UserRepository userRepository;

	private String email;
	private String senha;

	@PostConstruct
	public void init() {
		log.info("Inicializado...");
	}

	public Usuario getUsuario() {
		Object usuario = facesContext.getExternalContext().getSessionMap().get("usuarioLogado");
		return usuario == null ? null : (Usuario) usuario;
	}

	public String doLogin() {
		try {
			log.info("Tentando logar com o usuario : " + email);
			Usuario usuario = userRepository.isUsuarioReadyToLogin(email, senha);

			if (usuario == null) {
				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login ou Senha errado, tente novamente !",
						"Login ou Senha errado, tente novamente !");
				facesContext.addMessage(null, facesMsg);
				facesContext.validationFailed();
				return "";
			}

			log.info("Usuário logado com Sucesso!");
			facesContext.getExternalContext().getSessionMap().put("usuarioLogado", usuario);

			return "teste.xhtml?faces-redirect=true";
		} catch (Exception e) {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			facesContext.addMessage(null, facesMsg);
			facesContext.validationFailed();
			e.printStackTrace();
			return "";
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
