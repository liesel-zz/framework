package br.com.supero.framework.base.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.supero.framework.base.filter.SecurityFilter;
import br.com.supero.framework.base.i18n.LocaleConfig;
import br.com.supero.framework.security.entity.LoggedUser;

@Named
@SessionScoped
public class HomeController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private LocaleConfig localeConfig;

	@Inject
	private FacesContext facesContext;
	
	//
	private HttpServletRequest request = (HttpServletRequest) FacesContext
			.getCurrentInstance().getExternalContext().getRequest();
	private HttpSession session = (HttpSession) FacesContext
			.getCurrentInstance().getExternalContext().getSession(false);

	/**
	 * 
	 * 
	 * */
	public String logout() {
		if (session != null)
			session.invalidate();

		System.out.println("HomeController.logout()");

		// volta pro index
		return request.getContextPath() + "/index/?faces-redirect=true";
	}

	// FIXME JOGAR EM ALGUM RESOURCE DOS FILHOS
	public String getTextoUsuario() {
		// String texto = I18nUtils.getValue(Constants.TRANSLATIONS,
		// "usuarios");
		// return texto;
		return "";
	}

	// funcções de retorno de dados do usuário
	/**
	 * Return sessionID Value
	 * 
	 * */
	public String getSessionID() {
		return null;
	}

	public LoggedUser getLoggedUser() {
		
		FacesContext contexto = FacesContext.getCurrentInstance();
		HttpSession sessao = (HttpSession) contexto.getExternalContext()
				.getSession(true);

		LoggedUser currentUser = (LoggedUser) sessao
				.getAttribute(SecurityFilter.LOGGED_USER_ATTRIBUTE_NAME);

		return currentUser;
	}

	public void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}