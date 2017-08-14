package core.web.event;

import core.common.event.CommonEvent;
import core.common.event.CommonListener;

public abstract class TemplateListener implements CommonListener {
    @Override
    public void execute(CommonEvent event) {
        execute((TemplateEvent) event);
    }

    public abstract void execute(TemplateEvent event);
}
