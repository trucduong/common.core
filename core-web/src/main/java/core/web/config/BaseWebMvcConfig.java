package core.web.config;

import java.util.Locale;
import java.util.Properties;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

public abstract class BaseWebMvcConfig extends WebMvcConfigurerAdapter {
	/**
	 * Configure TilesConfigurer.
	 */
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions(getTilesDefinitions());
		tilesConfigurer.setCheckRefresh(true);
		return tilesConfigurer;
	}

	protected String[] getTilesDefinitions() {
		return new String[] { "/WEB-INF/views/tiles/tiles.xml" };
	}

	/**
	 * Configure ViewResolvers to deliver preferred views.
	 */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		if (useTilesViewResolvers()) {
			TilesViewResolver viewResolver = new TilesViewResolver();
			registry.viewResolver(viewResolver);
		}
	}

	protected boolean useTilesViewResolvers() {
		return true;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if (hasWebResources()) {
			registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		}
	}

	protected boolean hasWebResources() {
		return true;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename(getMessageSourceLocation());
		messageSource.setCacheSeconds(5);
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	protected String getMessageSourceLocation() {
		return "/i18n/message";
	}

	@Bean
	public LocaleResolver localeResolver() {
		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setDefaultLocale(getDefaultLocale());
		resolver.setCookieName("userLanguage");
		resolver.setCookieMaxAge(4800);
		return resolver;
	}

	protected Locale getDefaultLocale() {
		return new Locale("en");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("language");
		registry.addInterceptor(interceptor);
	}

	@Bean
	public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
		SimpleMappingExceptionResolver b = new SimpleMappingExceptionResolver();
		Properties mappings = new Properties();
		mappings.put("org.springframework.dao.DataAccessException", getErrorViewName());
		b.setExceptionMappings(mappings);
		return b;
	}

	protected String getErrorViewName() {
		return "error";
	}
}
