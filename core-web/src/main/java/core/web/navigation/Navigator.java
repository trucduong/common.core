package core.web.navigation;

import org.springframework.web.servlet.ModelAndView;

public abstract class Navigator {
	/**
	 * Server forward to an url
	 * @param viewName
	 * @return
	 */
	public static ModelAndView forward(String viewName) {

		return new ModelAndView(viewName);
	}

	/**
	 * Ask browser redirect to an url
	 * @param url
	 * @return
	 */
	public static ModelAndView redirect(String url) {
		return new ModelAndView("redirect:" + url);
	}
}
