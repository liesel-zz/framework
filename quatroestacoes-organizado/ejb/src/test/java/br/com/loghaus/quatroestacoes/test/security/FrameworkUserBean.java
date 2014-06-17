package br.com.loghaus.quatroestacoes.test.security;

import java.util.concurrent.Callable;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RunAs;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.jboss.ejb3.annotation.RunAsPrincipal;

import br.com.supero.framework.security.facade.UserFacade;

@Stateless
// CREATE FAKE LOGIN
@RunAs("Admin")
@RunAsPrincipal("test@mksdev.com")
public class FrameworkUserBean {

	@Inject
	private UserFacade facade;

	@PermitAll
	public UserFacade getFacade() {
		return facade;
	}

	@PermitAll
	public <V> V call(final Callable<V> callable) throws Exception {
		return callable.call();
	}
}
