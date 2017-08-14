package core.common.event;

public class Listener {
    private boolean active;
    private CommonListener listener;

    public Listener(CommonListener listener) {
        this.listener = listener;
        active = true;
    }

    public void execute(CommonEvent event) {
        if (active) {
            this.listener.execute(event);
        }
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean equals(CommonListener obj) {
        return this.listener == obj;
    }
}
