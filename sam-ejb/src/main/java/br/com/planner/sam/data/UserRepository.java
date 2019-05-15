package br.com.planner.sam.data;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.planner.sam.model.Usuario;
import br.com.planner.sam.util.ConvertStringToMd5;

@RequestScoped
public class UserRepository {

	@Inject
	private EntityManager em;
	
	@Inject
    private Logger log;

	public Usuario isUsuarioReadyToLogin(String email, String senha) {
		log.info("Entrando na validação de Login...");
		try {
			email = email.toLowerCase().trim();
			log.info("Verificando login por email : " + email);
			
			List<Usuario> usuarios = findByNamedQuery(email, senha);
			
			if(usuarios.size() == 1) {
				Usuario userFound = usuarios.get(0);
				return userFound;
			}
			
			return null;
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private List<Usuario> findByNamedQuery(String email, String senha) {
		TypedQuery<Usuario> usuarios = em.createNamedQuery(Usuario.FIND_BY_EMAIL_SENHA, Usuario.class);
		usuarios.setParameter("email", email);
		usuarios.setParameter("senha", ConvertStringToMd5.convert(senha));
		
		return usuarios.getResultList();
	}

}
