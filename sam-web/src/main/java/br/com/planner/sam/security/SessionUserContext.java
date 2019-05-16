package br.com.planner.sam.security;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.planner.sam.model.Usuario;

@SessionScoped
public class SessionUserContext implements Serializable{
	
	@Inject
	private FacesContext facesContext;

	/**
	 * a
	 */
	private static final long serialVersionUID = 4933212592354896928L;

	public void addLoggedUserOnSession(Usuario usuario) {
		facesContext.getExternalContext().getSessionMap().put("usuarioLogado", usuario);
	}
	
	public Usuario getLoggedUser() {
		Object usuario = facesContext.getExternalContext().getSessionMap().get("usuarioLogado");
		return usuario == null ? null : (Usuario) usuario;
	}
	
	public void invalidateSession() {
		facesContext.getExternalContext().invalidateSession();
	}
	
}
