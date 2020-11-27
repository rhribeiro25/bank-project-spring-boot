package br.com.rhribeiro25.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * @author Renan Ribeiro
 * @date 12/11/2020.
 */

@Configuration
public class LocaleConfig {

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localResolver=new SessionLocaleResolver();
        localResolver.setDefaultLocale(new Locale("pt", "BR"));
        return localResolver;
    }

}