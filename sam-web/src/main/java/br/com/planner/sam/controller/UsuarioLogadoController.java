package br.com.planner.sam.controller;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.planner.sam.data.UserRepository;
import br.com.planner.sam.model.Usuario;
import br.com.planner.sam.security.SessionUserContext;
import br.com.planner.sam.util.JSFHelper;

@Named
@SessionScoped
public class UsuarioLogadoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 992749148440494261L;

	/**
	 * 
	 */

	@Inject
	private SessionUserContext sessionUserContext;
	
	@Inject
	private JSFHelper jsfHelper;

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
		return sessionUserContext.getLoggedUser();
	}

	public String doLogin() {
		try {
			log.info("Tentando logar com o usuario : " + email);
			Usuario usuario = userRepository.isUsuarioReadyToLogin(email, senha);

			if (usuario == null) {
				jsfHelper.addErrorMsg("Login ou Senha errado, tente novamente");
				return "";
			}

			log.info("Usuário logado com Sucesso!");
			sessionUserContext.addLoggedUserOnSession(usuario);

			return "/pages/admin/teste_apos_filter.xhtml?faces-redirect=true";
		} catch (Exception e) {
			jsfHelper.addErrorMsg(e.getMessage());
			e.printStackTrace();
			return "";
		}
	}
	
	public String doLogout() {
		log.info("Fazendo logout com usuário "
				+ (sessionUserContext.getLoggedUser() == null ? "Usuario não logado" : sessionUserContext.getLoggedUser().getEmail()));
		sessionUserContext.invalidateSession();
		log.info("Logout realizado com sucesso !");
		return "/login.xhtml?faces-redirect=true";
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
