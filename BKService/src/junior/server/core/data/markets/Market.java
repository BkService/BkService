package junior.server.core.data.markets;

import java.util.HashMap;

import junior.server.core.data.users.User;

public class Market {
	private final Integer marked_id;
	private HashMap<Integer, Outcome> outcomesMap;
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
	 * @param new_outcome
	 * @return true - добавил. false - не добавил
	 */
	public boolean addOutcome(Outcome new_outcome){
		if (outcomesMap.put(new_outcome.getOutcomeId(), new_outcome) == null){
			return true;
		}
		else{
			return false;
		}
	}
	
	// нужна ли такая возможность и как её лучше реализовать?
	public HashMap<Integer, Outcome> getOutcomeMap(){
		return outcomesMap;
	}
	
	/**
	 * 
	 * @param bet_id
	 * @param user
	 * @param outcome_id
	 * @return
	 */
	public Bet makeBet(Integer bet_id, User user, Integer outcome_id){
		
	}
}
























