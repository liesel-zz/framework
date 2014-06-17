package br.com.loghaus.quatroestacoes.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.supero.framework.base.controller.HomeController;

@SessionScoped
@Named
public class DefaultController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private HomeController homeController;

	@Inject
	private FacesContext facesContext;

}
