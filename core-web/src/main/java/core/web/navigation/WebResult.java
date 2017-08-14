package core.web.navigation;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

public class WebResult extends ModelAndView {
    public static final String ALERT_PARAM_NAME = "ALERT_INFO";
    public static final String ERROR_PARAM_NAME = "ERROR_INFO";

    public WebResult() {
        super();
    }

    public WebResult(String viewName) {
        super(viewName);
    }

    public static WebResult forward(String url) {
        return forward(url, null);
    }

    public static WebResult forward(String url, HttpServletRequest request) {
        WebResult result = new WebResult("forward:" + url);

        if (request != null) {
            Enumeration<String> params = request.getParameterNames();
            while (params.hasMoreElements()) {
                String param = params.nextElement();
                result.addObject(param, request.getParameter(param));
            }
        }

        return result;
    }

    public static WebResult show(String viewName) {
        return new WebResult(viewName);
    }

    /**
     * Ask browser redirect to an url
     * 
     * @param url
     * @return
     */
    public static WebResult redirect(String url) {
        return new WebResult("redirect:" + url);
    }

    public void addAlert(Object alert) {
        this.addObject(ALERT_PARAM_NAME, alert);
    }

    public void error(Object error) {
        this.addObject(ERROR_PARAM_NAME, error);
    }
    
    public Object getObject(String key) {
        Object obj = this.getModelMap().get(key);
        return obj;
    }
}
