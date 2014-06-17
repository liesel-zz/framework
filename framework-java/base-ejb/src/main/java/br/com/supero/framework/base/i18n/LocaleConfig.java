package br.com.supero.framework.base.i18n;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class LocaleConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	private Locale locale = new Locale("pt", "BR");

	private Map<String, Locale> availableLocales;
	
	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public Map<String, Locale> getAvailableLocales() {
		if (availableLocales != null && availableLocales.size() != 0) {
			return availableLocales;
		}
		
		availableLocales = new TreeMap<String, Locale>();
		availableLocales.put("Português (Brasil)", new Locale("pt", "BR"));
		availableLocales.put("English (US)", new Locale("en", "US"));
//		availableLocales.put("Español (España)", new Locale("es", "ES"));
//		availableLocales.put("普通話", Locale.TRADITIONAL_CHINESE);
//		availableLocales.put("日本語", Locale.JAPANESE);
		
		return availableLocales;
	}

	public void setAvailableLocales(Map<String, Locale> availableLocales) {
		this.availableLocales = availableLocales;
	}
}