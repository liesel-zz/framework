package br.com.supero.framework.security.facade.impl;

import java.security.Principal;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.supero.framework.security.entity.User;
import br.com.supero.framework.security.facade.SecurityContext;
import br.com.supero.framework.security.facade.UserFacade;

@Stateless
/**
 * 
 * Classe responsável por trazer o Principal do contexto de segurança
 * */
public class SecurityContextImpl implements SecurityContext {

	@Resource
	private SessionContext context;

	@Inject
	private UserFacade facade;

	@Override
	public Principal getPrincipal() {
		return context.getCallerPrincipal();
	}

	public User getUser() {
		return facade.findByLogin(context.getCallerPrincipal().getName());
	}
	
	public SessionContext getContext() {
		return context;
	}

}
