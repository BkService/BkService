package juniors.server.core.data.bets;

import juniors.server.core.data.markets.*;
import juniors.server.core.data.users.*;


/**
 * 
 * @author kovalev
 *
 */
public class Bet{
	private final User user;
	private final Outcome outcome;
	private final Double coefficient;
	private final Float sum; // сумма ставки
	
	public Bet(User user, Outcome outcome, double current_coefficient, Float sum){
		this.user = user;
		this.outcome = outcome;
		this.coefficient = current_coefficient;
		this.sum = sum;
	}
	
	/**
	 * 
	 * @return User - который сделал эту ставку
	 */
	public User getUser(){
		return user;
	}
	
	/**
	 *  
	 * @return - исход, на который сделана ставка
	 */
	public Outcome getOutcome(){
		return outcome;
	}
	
	/**
	 * 
	 * @return - коеффициент исхода, во время создания ставки.
	 */
	public Double getCoefficient(){
		return coefficient;
	}
	
	/**
	 * 
	 * @return - сумма ставки
	 */
	public Float getSum(){
	    return sum;
	}
}
