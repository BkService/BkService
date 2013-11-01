package junior.server.core.data.events;
/**
 * Описывает взаимодействие с событиями. 
 * @author kovalev
 *
 */
public interface EventManagerInterface {
	/**
	 * Создаётся и добавляется событие
	 * @param new_event
	 * @return ссылка на созданное событие
	 */
	public Event addEvent(Event newEvent);
	
	/**
	 * Получить ссылку на событие по id
	 * @param event_id
	 * @return cсылка на событе. Если такого не существует - возвращает null
	 */
	public Event getEvent(int eventId);
	
	/**
	 * Удалить событие по id
	 * @param event_id
	 * @return удалённый объект. Null - если не найден 
	 */
	public Event removeEvent(int eventId);
}
