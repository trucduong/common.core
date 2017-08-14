package core.common.event;

public class CommonEvent {
    private String eventId;
    private Object target;
    private String message;

    public CommonEvent(String eventId) {
        this.eventId = eventId;
    }

    public CommonEvent(String eventId, Object target) {
        this.eventId = eventId;
        this.target = target;
    }

    public CommonEvent(String eventId, Object target, String message) {
        this.eventId = eventId;
        this.target = target;
        this.message = message;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}
