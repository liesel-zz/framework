package br.com.supero.framework.base.resources;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Esta classe utiliza CDI para criar aliases de recursos do Java EE nos
 * beans CDI.
 * 
 * <p>
 * Exemplo de injeção de um managed bean:
 * </p>
 * 
 * <pre>
 * &#064;Inject
 * private EntityManager em;
 * </pre>
 */
public class Resources {
	
	@Produces
	@PersistenceContext(unitName= "primary")
	private EntityManager em;

	/**
	 * Obtém o logger.
	 */
	@Produces
	public Logger produceLog(InjectionPoint injectionPoint) {
		return Logger.getLogger(injectionPoint.getMember().getDeclaringClass()
				.getName());
	}
}
