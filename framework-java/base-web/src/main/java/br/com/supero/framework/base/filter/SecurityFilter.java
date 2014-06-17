package br.com.supero.framework.base.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.supero.framework.security.entity.LoggedUser;


public interface SecurityFilter extends Filter {

	/***
	 * 
	 * @param HttpServletRequest
	 * @return void
	 * */
	void setUserParamSecurityContext(HttpServletRequest request);

	/***
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ApplicationContext
	 * @return boolean
	 * @throws IOException
	 * */
	boolean verifySecurityContext(HttpServletRequest request,
			HttpServletResponse response) throws IOException;

	/***
	 * 
	 * @param HttpServletRequest
	 * @param String
	 * @param String
	 * @return LoggedUser
	 * */
	LoggedUser setLoggedUser(HttpServletRequest request,
			String checkLogin);

	/* ATRIBUTOS DE SEGURANÇA */
	static String LOGIN_ATTRIBUTE_NAME = "LOGIN";
	static String LOGGED_USER_ATTRIBUTE_NAME = "LOGGED_USER";

}