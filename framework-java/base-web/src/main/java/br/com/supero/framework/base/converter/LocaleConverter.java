package br.com.supero.framework.base.converter;

import java.util.Locale;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

@RequestScoped
@Named
public class LocaleConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        for (Locale locale : Locale.getAvailableLocales()) {
            if (locale.toString().equals(value)) {
                return locale;
            }
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value.toString();
    }

}