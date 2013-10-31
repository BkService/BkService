package junior.server.core.data.events;

import java.util.HashMap;

/**
 * Реализация интерфейса EventManagerInterface
 * @author kovalev
 *
 */
public class EventManager implements EventManagerInterface {
	HashMap<Integer, Event> eventsMap;	
	
	@Override
	public Event addEvent(Event new_event) {
		return eventsMap.put(new_event.getEventId(), new_event);
	}

	@Override
	public Event getEvent(int event_id) {
		return eventsMap.get(event_id);
	}

	@Override
	public Event removeEvent(int event_id) {
		return eventsMap.remove(event_id);
	}
	
}
