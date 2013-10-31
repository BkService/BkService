package junior.server.core.data.events;

//import java.sql.Time;
import java.util.HashMap;
import java.util.LinkedList;

import junior.server.core.data.markets.*;
/**
 * Описывает событие (любое). Есть строковое описание события (пока что только так)
 * Так же есть контейнер с маркетами, время начала и время конца (предполагаемое) событие
 * 
 * @author kovalev
 *
 */
public class Event {
	private HashMap<Integer, Market> markets; // контейнер с маркетами. У каждого маркета свой ID
	private String description; // описание события
	private final int id; // идентефикатор события
//	private Time startTime;	// время начала
//	private Time finishTime;	// время окончания
	
	public Event(int id){
		this.id = id;
		markets = new HashMap<Integer, Market>();
		description = "No description available.";
	}
	
	public Event(int id, String new_description){
		this.id = id;
		markets = new HashMap<Integer, Market>();
		description = new_description;
	}
	
	// а нужен ли такой конструктор
	public Event(int id, HashMap<Integer, Market> new_markets, String new_description){
		this.id = id;
		markets = new_markets;
		description = new_description;
	}
	
	/**
	 * Получить маркет по id. (Пока будет какой то id, далее может заменим чем-нибудь)
	 * @param id
	 * @return
	 */
	public Market getMarket(Integer id){
		return markets.get(id);
	}
	
	/**
	 * Добавить новый маркет.
	 * @param new_market
	 * @return ссылка на созданый объект
	 */
	public Market addMarket(Market new_market){
		return markets.put(new_market.getMarketId(), new_market);
	}
	
	/**
	 * Заменить описание
	 * @param new_description
	 * @return
	 */
	public boolean createDescription(String new_description){
		description = new_description;
		return true;
	}
	
	/**
	 * 
	 * @return описание события
	 */
	public String getDescription(){
		return description;
	}
	
	/**
	 * 
	 * @return id события
	 */
	public Integer getEventId(){
		return id;
	}
	
	/**
	 * 
	 * @return количество маркетов
	 */
	public int getMarketsCount(){
		return markets.size();
	}
}
















