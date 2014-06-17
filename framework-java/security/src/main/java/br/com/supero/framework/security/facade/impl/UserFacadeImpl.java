package br.com.supero.framework.security.facade.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;

import br.com.supero.framework.base.dao.exception.DaoException;
import br.com.supero.framework.base.facade.impl.CrudFacadeImpl;
import br.com.supero.framework.base.mail.Mail;
import br.com.supero.framework.base.paging.DataPage;
import br.com.supero.framework.base.paging.Page;
import br.com.supero.framework.security.dao.UserDAO;
import br.com.supero.framework.security.entity.PasswordReset;
import br.com.supero.framework.security.entity.QUser;
import br.com.supero.framework.security.entity.User;
import br.com.supero.framework.security.entity.UserConfirm;
import br.com.supero.framework.security.facade.PasswordResetFacade;
import br.com.supero.framework.security.facade.UserConfirmFacade;
import br.com.supero.framework.security.facade.UserFacade;
import br.com.supero.framework.security.filter.UserFilterData;

@Named
public class UserFacadeImpl extends CrudFacadeImpl<Long, User, QUser> implements
		UserFacade {

	@Inject
	private Validator validator;

	@Inject
	private Mail mail;

	@Inject
	private PasswordResetFacade passwordResetFacade;
	
	@Inject
	private UserConfirmFacade userConfirmFacade;

	private static final long serialVersionUID = 1L;

	@Inject
	public UserFacadeImpl(UserDAO dao) {
		super(dao);
	}

	@Override
	public List<User> findAll() {
		return ((UserDAO) super.getDAO()).findAll();
	}

	@Override
	public User findByLogin(String login) {
		return ((UserDAO) super.getDAO()).findByLogin(login);
	}

	@Override
	public DataPage<User> findByFilter(UserFilterData filter, Page page) {
		return ((UserDAO) super.getDAO()).findByFilter(filter, page);
	}

	@Override
	public User save(User t) throws DaoException {
		validate(t);
		return super.save(t);
	}
	
	@Override
	public User register(User user) throws Exception {
		User currentUser = persist(user);

		if (currentUser != null) {
			String uuid = UUID.randomUUID().toString();

			UserConfirm userConfirm = new UserConfirm();
			userConfirm.setId(uuid);
			userConfirm.setUser(currentUser);
			userConfirmFacade.save(userConfirm);

			mail.sendEmailHtml("contato@mksdev.com", currentUser.getEmail(), "Teste",
					"http://localhost:8080/Transporte/public/confirmarCadastro/confirmarCadastro.jsf?key="
							+ uuid);
		}
		
		return currentUser;
	}

	@Override
	public User confirmRegister(String uuid) throws Exception {
		return null;
	}

	@Override
	public boolean resetPassword(String uuid, String newPassword)
			throws Exception {
		try {
			PasswordReset passwordReset = passwordResetFacade.findById(uuid);
			User user = passwordReset.getUser();
			// seta nova senha
			user.setPassword(newPassword);
			// seta status para ativo
			user.setStatus(1L);
			merge(user);
			passwordResetFacade.remove(passwordReset);
		} catch (NoResultException nr) {
			throw new Exception(
					"Login não encontrado! Favor solicitar novo pedido de alteração de senha!!");
		} catch (NullPointerException ne) {
			throw new Exception(
					"Login não encontrado! Favor solicitar novo pedido de alteração de senha!!");
		}
		return true;
	}

	@Override
	public boolean lostPassword(String login) throws Exception {
		try {
			User currentUser = findByLogin(login);
			
			if (currentUser != null) {
				String uuid = UUID.randomUUID().toString();

				PasswordReset newPasswordReset = new PasswordReset();
				newPasswordReset.setId(uuid);
				newPasswordReset.setUser(currentUser);
				passwordResetFacade.save(newPasswordReset);

				mail.sendEmailHtml("contato@mksdev.com", login, "Teste",
						"Senha + http://localhost:8080/Transporte/public/resetarSenha/resetarSenha.jsf?key="
								+ uuid);
			}else{
				throw new Exception("E-mail não cadastrado!");
			}
		} catch (NoResultException ne) {
			throw new Exception("E-mail não cadastrado!");
		} catch (NullPointerException np) {
			throw new Exception("E-mail não cadastrado!");
		} catch (Exception e) {
			throw e;
		}

		return true;
	}

	private void validate(User user) throws ConstraintViolationException,
			ValidationException {
		// Create a bean validator and check for issues.
		Set<ConstraintViolation<User>> violations = validator.validate(user);

		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(
					new HashSet<ConstraintViolation<?>>(violations));
		}

		// Check the uniqueness of the email address
		if (loginAlreadyExists(user.getEmail())) {
			throw new ValidationException("Unique Email Violation");
		}
	}

	private boolean loginAlreadyExists(String email) {
		User member = null;
		try {
			member = this.findByLogin(email);
		} catch (NoResultException e) {
			// ignore
		}
		return member != null;
	}

	
}