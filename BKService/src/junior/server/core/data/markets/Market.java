package junior.server.core.data.markets;

import java.util.HashMap;

import junior.server.core.data.bets.Bet;
import junior.server.core.data.users.User;

public class Market {
	private final Integer marketId;
	private HashMap<Integer, Outcome> outcomesMap;
	private String description;
	
	public Market(Integer id){
		marketId = id;
		description = "No available description.";
		outcomesMap = new HashMap<Integer, Outcome>();
	}
	
	public Market(Integer id, String newDescription){
		marketId = id;
		description = newDescription;
		outcomesMap = new HashMap<Integer, Outcome>();
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
	public boolean setDescription(String newDescription){
		description = newDescription;
		
		return true;
	}
	
	/**
	 * 
	 * @return
	 */
	public Integer getMarketId(){
		return marketId;
	}
	
	/**
	 * Добавить новый исход. Id исхода в пределах маркета должен быть уникальным
	 * @param new_outcome
	 * @return true - добавил. false - не добавил
	 */
	public boolean addOutcome(Outcome newOutcome){
		int id = newOutcome.getOutcomeId();
		if (outcomesMap.containsKey(id)){
			return false;
		}
		else{
			outcomesMap.put(id, newOutcome);
			return true;
		}
	}
	
	// нужна ли такая возможность и как её лучше реализовать?
	public HashMap<Integer, Outcome> getOutcomeMap(){
		return outcomesMap;
	}
	
	/**
	 * Пока заглушка
	 * 
	 * @param betId
	 * @param user
	 * @param outcomeId
	 * @return
	 */
	public Bet makeBet(Integer betId, User user, Integer outcomeId){
		return null;
	}
	
	/**
	 * заглушка
	 * @param idWinOutcame
	 * @return
	 */
	public boolean calculateMarket(int idWinOutcame){
		return false;
	}
}
























