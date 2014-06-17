package br.com.loghaus.quatroestacoes.test;

import java.util.concurrent.Callable;
import java.util.logging.Logger;

import javax.inject.Inject;

import junit.framework.Assert;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.security.auth.spi.Util;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.runner.RunWith;

import br.com.loghaus.quatroestacoes.test.security.FrameworkRoleBean;

import br.com.supero.framework.base.dao.CrudDAO;
import br.com.supero.framework.base.dao.exception.DaoException;
import br.com.supero.framework.base.dao.impl.CrudDAOImpl;
import br.com.supero.framework.base.entity.BaseEntity;
import br.com.supero.framework.base.entity.impl.BaseEntityImpl;
import br.com.supero.framework.base.entity.impl.LogBaseEntityImpl;
import br.com.supero.framework.base.facade.CrudFacade;
import br.com.supero.framework.base.facade.impl.CrudFacadeImpl;
import br.com.supero.framework.base.filter.FilterData;
import br.com.supero.framework.base.mail.Mail;
import br.com.supero.framework.base.mail.impl.MailImpl;
import br.com.supero.framework.base.paging.DataPage;
import br.com.supero.framework.base.paging.Page;
import br.com.supero.framework.base.resources.Resources;
import br.com.supero.framework.security.dao.RoleDAO;
import br.com.supero.framework.security.dao.impl.RoleDAOImpl;
import br.com.supero.framework.security.entity.Role;
import br.com.supero.framework.security.facade.RoleFacade;
import br.com.supero.framework.security.facade.impl.RoleFacadeImpl;
import br.com.supero.framework.security.filter.RoleFilterData;

/**
 * 
 * Testa a classe de Roles do framework
 * 
 * @author Fernando <fernando@mksdev.com>
 * @version 0.0.1-SNAPSHOT
 * 
 * */
@RunWith(Arquillian.class)
public class FrameworkRoleTest {

	@Inject
	private Logger log;

	@Inject
	private FrameworkRoleBean roleBean;

	/**
	 * Create a test.war file for deploy in JBoss
	 * 
	 * @return Archive<?>
	 * 
	 * */
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addClasses(FrameworkRoleBean.class)
				.addClasses(Role.class)
				.addClasses(Role.class)
				.addClasses(RoleFacade.class)
				.addClasses(RoleFacadeImpl.class)
				.addClasses(RoleDAO.class)
				.addClasses(RoleDAOImpl.class)
				.addClasses(RoleFilterData.class)
				// Framewrok Class
				.addClasses(CrudFacade.class)
				.addClasses(CrudFacadeImpl.class)
				.addClasses(CrudDAO.class)
				.addClasses(CrudDAOImpl.class)
				.addClasses(DataPage.class)
				.addClasses(Page.class)
				.addClasses(DaoException.class)
				.addClasses(LogBaseEntityImpl.class)
				.addClasses(BaseEntityImpl.class)
				.addClasses(BaseEntity.class)
				.addClasses(FilterData.class)
				.addClasses(Mail.class)
				.addClasses(MailImpl.class)
				.addClasses(Util.class)

				.addClasses(Resources.class)
				.addAsResource("META-INF/test-persistence.xml",
						"META-INF/persistence.xml")
				.addAsWebInfResource("test-jboss-web.xml", "jboss-web.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				// Deploy our test datasource
				.addAsWebInfResource("test-ds.xml", "test-ds.xml");
	}

	//@Test
	//@InSequence(1)
	public void testPersist() throws Exception {
		roleBean.call(new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				Role entity = getEntity();
				entity = roleBean.getFacade().persist(entity);
				Assert.assertNotNull(entity.getId());
				log.info(entity.getDescription() + " was persisted with id "
						+ entity.getId());
				return null;
			}

		});
	}

	//@Test
	//@InSequence(2)
	public void testMerge() throws Exception {
		roleBean.call(new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				Role entity = getEntity();
				entity = roleBean.getFacade().findById(entity.getId());
				Assert.assertNotNull(entity.getId());
				log.info(entity.getDescription() + " was persisted with id "
						+ entity.getId());
				return null;
			}

		});
	}
	
	//@Test
	//@InSequence(3)
	public void testRemove() throws Exception {
		roleBean.call(new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				Role entity = getEntity();
				entity = roleBean.getFacade().findById(entity.getId());
				roleBean.getFacade().remove(entity);

				Assert.assertNotNull(entity.getId());
				log.info(entity.getDescription() + " was persisted with id "
						+ entity.getId());
				return null;
			}

		});
	}

	//@Test
	//@InSequence(4)
	public void testStress() throws Exception {
		roleBean.call(new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				
				Role stressRole = null;
				for (int i = 0; i < 10000; i++) {
					stressRole = new Role();
					stressRole.setId("UnitTest"+i);
					stressRole.setDescription("UnitTestDesc"+i);
					roleBean.getFacade().save(stressRole);
				}
				return null;
			}
		});
	}

	/**
	 * Get Entity For Test
	 * */
	private Role getEntity() {
		Role entity = new Role();
		entity.setId("UnitTest");
		entity.setDescription("UnitTestDesc");

		return entity;
	}

}
