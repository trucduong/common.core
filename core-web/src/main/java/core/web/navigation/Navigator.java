package core.web.navigation;

import org.springframework.web.servlet.ModelAndView;

public abstract class Navigator {
	public static ModelAndView create(String viewName) {

		return new ModelAndView(viewName);
	}

	public static ModelAndView redirect(String url) {
		return new ModelAndView("redirect:" + url);
	}
}
