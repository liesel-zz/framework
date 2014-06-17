package br.com.supero.framework.security.facade;

import java.security.Principal;

import javax.ejb.SessionContext;

import br.com.supero.framework.security.entity.User;

public interface SecurityContext {

	public Principal getPrincipal();
	
	public User getUser();
	
	public SessionContext getContext();

}
