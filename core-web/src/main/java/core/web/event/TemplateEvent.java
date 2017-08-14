package core.web.event;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.common.event.CommonEvent;
import core.web.navigation.WebResult;

public class TemplateEvent extends CommonEvent {

    private HttpServletRequest request;
    private HttpServletResponse response;
    
    public TemplateEvent(String eventId, WebResult mv, HttpServletRequest request, HttpServletResponse response) {
        super(eventId, mv);
        this.request = request;
        this.response = response;
    }
    
    public HttpServletRequest getRequest() {
        return request;
    }
    
    public HttpServletResponse getResponse() {
        return response;
    }

}
