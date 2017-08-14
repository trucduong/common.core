package core.common.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CommonListenerHelper {

    private static final Map<String, List<Listener>> maps = new HashMap<String, List<Listener>>();

    public static void add(String eventId, CommonListener listener) {
        List<Listener> listeners = maps.get(eventId);
        if (listeners == null) {
            listeners = new ArrayList<Listener>();
            maps.put(eventId, listeners);
        }
        listeners.add(new Listener(listener));
    }

    public static void remove(String eventId) {
        if (maps.containsKey(eventId)) {
            maps.remove(eventId);
        }
    }

    public static void remove(String eventId, CommonListener listener) {
        List<Listener> listeners = maps.get(eventId);
        if (listeners != null && listeners.size() > 0) {
            Iterator<Listener> iterator = listeners.iterator();
            while (iterator.hasNext()) {
                Listener li = iterator.next();
                if (li.equals(listener)) {
                    listeners.remove(li);
                    break;
                }
            }
        }
    }

    public static void setEnabled(String eventId, boolean enabled) {
        List<Listener> listeners = maps.get(eventId);
        if (listeners != null && listeners.size() > 0) {
            for (Listener listener : listeners) {
                listener.setActive(enabled);
            }
        }
    }

    public static void setEnabled(String eventId, CommonListener listener, boolean enabled) {
        List<Listener> listeners = maps.get(eventId);
        if (listeners != null && listeners.size() > 0) {
            for (Listener li : listeners) {
                if (li.equals(listener)) {
                    li.setActive(enabled);
                    break;
                }
            }
        }
    }

    public static void fire(String eventId, Object target) {
        fire(new CommonEvent(eventId, target), true);
    }

    public static void fire(CommonEvent event, boolean sync) {
        // TODO: support sync option
        List<Listener> listeners = maps.get(event.getEventId());
        if (listeners != null && listeners.size() > 0) {
            Iterator<Listener> iterator = listeners.iterator();
            while (iterator.hasNext()) {
                iterator.next().execute(event);
            }
        }
    }
}
