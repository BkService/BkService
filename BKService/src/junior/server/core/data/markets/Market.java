package junior.server.core.data.markets;

import java.util.HashMap;

public class Market {
	private final Integer marked_id;
	private HashMap<Integer, ResultMarket> resultsMap;
	private String description;
	
	public Market(Integer id){
		marked_id = id;
		description = "No available description.";
	}
	
	public Market(Integer id, String new_description){
		marked_id = id;
		description = new_description;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getDescription(){
		return description;
	}
	
	/**
	 * 
	 * @param new_description
	 * @return true - описание задано. False - произошла ошибка.
	 */
	public boolean setDescription(String new_description){
		description = new_description;
		
		return true;
	}
	
	/**
	 * 
	 * @return
	 */
	public Integer getMarketId(){
		return marked_id;
	}
	
	/**
	 * Добавить новый исход. Id исхода в пределах маркета должен быть уникальным
	 * @param new_result
	 * @return true - добавил. false - не добавил
	 */
	public boolean addResult(ResultMarket new_result){
		if (resultsMap.put(new_result.getResultId(), new_result) == null){
			return true;
		}
		else{
			return false;
		}
	}
	
	// нужна ли такая возможность и как её лучше реализовать?
	public HashMap<Integer, ResultMarket> getResultMap(){
		return resultsMap;
	}
}
























