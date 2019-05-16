package br.com.planner.sam.util;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class JSFHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4502663581096264251L;

	@Inject
	private FacesContext facesContext;

	private static JSFHelper INSTANCE;

	private JSFHelper() {
	}

	public static JSFHelper getCurrentInstance() {
		if (null == INSTANCE) {
			INSTANCE = new JSFHelper();

		}
		return INSTANCE;
	}

	public void addInfoMsg(String msgInfo) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msgInfo, msgInfo);
		facesContext.addMessage(null, facesMsg);
	}

	public void addWarnMsg(String msgWarn) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, msgWarn, msgWarn);
		facesContext.addMessage(null, facesMsg);
	}

	public void addErrorMsg(String msgError) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgError, msgError);
		facesContext.addMessage(null, facesMsg);
	}

}
