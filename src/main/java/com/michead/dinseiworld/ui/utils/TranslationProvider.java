package com.michead.dinseiworld.ui.utils;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.i18n.I18NProvider;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.*;

@Component
public class TranslationProvider implements I18NProvider {

    public static final String BUNDLE_PREFIX = "translate";

    public final Locale LOCALE_ES = new Locale("es", "ES");
    public final Locale LOCALE_EN = new Locale("en", "US");

    private List<Locale> locales = Collections
            .unmodifiableList(Arrays.asList(LOCALE_ES, LOCALE_EN));

    @Override
    public List<Locale> getProvidedLocales() {
        return locales;
    }

    @Override
    public String getTranslation(String key, Locale locale, Object... params) {
        if (key == null) {
            LoggerFactory.getLogger(TranslationProvider.class.getName())
                    .warn("Got lang request for key with null value!");
            return "";
        }

        final ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_PREFIX, locale);

        String value;
        try {
            value = bundle.getString(key);
        } catch (final MissingResourceException e) {
            LoggerFactory.getLogger(TranslationProvider.class.getName())
                    .warn("Missing resource", e);
            return "!" + locale.getLanguage() + ": " + key;
        }
        if (params.length > 0) {
            value = MessageFormat.format(value, params);
        }
        return value;
    }

    public LoginI18n createLoginI18n() {
        LoginI18n i18n = LoginI18n.createDefault();

        i18n.setHeader(new LoginI18n.Header());
        i18n.setForm(new LoginI18n.Form());
        i18n.setErrorMessage(new LoginI18n.ErrorMessage());

        // define all visible Strings to the values you want
        // this code is copied from above-linked example codes for Login
        // in a truly international application you would use i.e. `getTranslation(USERNAME)` instead of hardcoded string values. Make use of your I18nProvider
        i18n.getHeader().setTitle("");
        i18n.getHeader().setDescription("");
        i18n.getForm().setUsername("Usuario");
        i18n.getForm().setTitle(UI.getCurrent().getTranslation("user.access"));
        i18n.getForm().setSubmit("Entrar");
        i18n.getForm().setPassword("Password");
        i18n.getForm().setForgotPassword("Olvide el password");
        i18n.getErrorMessage().setTitle("Usuario/Contraseña invalidos");
        i18n.getErrorMessage()
                .setMessage("Revise la informacion e intente nuevamene.");
        i18n.setAdditionalInformation("");
        return i18n;
    }
}
