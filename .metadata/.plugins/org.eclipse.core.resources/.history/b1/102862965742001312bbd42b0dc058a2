package junior.server.core.data.markets;

import java.util.LinkedList;
import junior.server.core.data.bets.*;

public class ResultMarket {
	private final Integer result_id;
	private Integer coefficient; //всегда больше 1
	private String description;
	private LinkedList<Bet> bets; // контейнер со ставками на данный исход
	
	public ResultMarket(Integer id){
		result_id = id;
		coefficient = 1;
		description = "No available description.";
		bets = new LinkedList<Bet>();
	}
	
	public ResultMarket(Integer id, Integer coefficient){
		result_id = id;
		this.coefficient = coefficient;
		description = "No available description.";
		bets = new LinkedList<Bet>();
	}
	
	public ResultMarket(Integer id, Integer coefficient, String description){
		result_id = id;
		this.coefficient = coefficient;
		this.description = description;
		bets = new LinkedList<Bet>();
	}
	
	/**
	 * 
	 * @return коэффициент для исхода
	 */
	public Integer getCoefficient(){
		return coefficient;
	}
	
	/**
	 * Коэффициент должен быть больше 1 (а надо ли это проверять?)
	 * @param new_coefficient
	 * @return true - коэффициент задан. false - не удалось задать
	 */
	public boolean setCoefficient(Integer new_coefficient){
		if (new_coefficient <= 1){
			return false;
		}
		
		coefficient = new_coefficient;
		return true;
	}
	
	/**
	 * 
	 * @return
	 */
	public Integer getResultId(){
		return result_id;
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
	 * Доработать проверку корректности ставки 
	 * Создаёт ставку на данный исход 
	 */
	public boolean createBet(Bet new_bet){
		bets.add(new_bet);
		
		return true;
	}
	
	/**
	 * 
	 * @return контейнер со ставками
	 */
	public LinkedList<Bet> getBets(){
		return bets;
	}
}














