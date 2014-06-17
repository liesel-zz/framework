package br.com.supero.framework.base.i18n.util;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.supero.framework.base.i18n.LocaleConfig;


/**
 * Classe utilitaria para ler o arquivo de internacionalizacao.
 * 
 * @author Diego
 * 
 */
@Named
@RequestScoped
public class I18nUtils {

	@Inject
	private LocaleConfig localeConfig;
	
	/**
	 * Retorna o valor da chave informada conforme o Bundle informado.
	 * 
	 * @param bundleName
	 * @param key
	 * @return
	 */
	public String getValue(String bundleName, String key) {
		Locale currentLocale = localeConfig.getLocale();

		System.out.println("I18nUtils.currentLocale=" + currentLocale);

		ResourceBundle resourceBundle = ResourceBundle.getBundle(bundleName,
				currentLocale);
		return resourceBundle.getString(key);
	}
}