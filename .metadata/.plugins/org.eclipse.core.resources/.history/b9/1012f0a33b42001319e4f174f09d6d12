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
	public Event createEvent(Event new_event);
	
	/**
	 * Получить ссылку на событие по id
	 * @param event_id
	 * @return сссылка на событе. Если такого не существует - возвращает null
	 */
	public Event getEvent(int event_id);
	
	/**
	 * Удалить событие по id
	 * @param event_id
	 * @return удалённый объект. Null - если не найден 
	 */
	public Event removeEvent(int event_id);
}
