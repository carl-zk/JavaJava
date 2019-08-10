package com.carl.web.support;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.AbstractLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @author carl
 */
@Component
public class LocaleResolver extends AbstractLocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        return resolveFromHeader(httpServletRequest);
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest,
                          HttpServletResponse httpServletResponse, Locale locale) {
        LocaleContextHolder.setLocale(locale == null ? Locale.CHINA : locale);
    }

    private Locale resolveFromHeader(HttpServletRequest request) {
        String localeStr = request.getHeader("locale");
        for (String loc : SUPPORT_LOCALE) {
            if (loc.equals(localeStr)) {
                return new Locale(loc);
            }
        }
        return Locale.CHINA;
    }

    private static final String[] SUPPORT_LOCALE = {"zh_CN", "en_US"};
}
