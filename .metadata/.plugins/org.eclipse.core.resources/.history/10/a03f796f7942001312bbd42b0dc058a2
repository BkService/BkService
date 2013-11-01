package junior.server.core.data.events;

import java.util.HashMap;
import java.util.Map;

/**
 * Реализация интерфейса EventManagerInterface
 * @author kovalev
 *
 */
public class EventManager implements EventManagerInterface {
	Map<Integer, Event> eventsMap;	
	
	public EventManager(){
		eventsMap = new HashMap<Integer, Event>();
	}
	
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
