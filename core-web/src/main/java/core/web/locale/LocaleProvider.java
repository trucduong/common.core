package core.web.locale;

import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;

public class LocaleProvider {
	public static void setLocale(String locate) {
//		HttpServletRequest request = WebContext.getRequest();
//		HttpServletResponse response = WebContext.getResponse();
//		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
//		localeResolver.setLocale(request, response, StringUtils.parseLocaleString(locate));
	}

	public static Locale getLocale() {
		return LocaleContextHolder.getLocale();
	}
}
