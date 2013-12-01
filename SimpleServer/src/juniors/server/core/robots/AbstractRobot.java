package juniors.server.core.robots;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import juniors.server.core.data.events.Event;
import juniors.server.core.data.markets.Market;
import juniors.server.core.data.markets.Outcome;
import juniors.server.core.data.users.User;
import juniors.server.core.logic.DaemonThreadFactory;

/**
 * Implementation of robot.
 * It has one abstract method generateBet(), which has different implementation depends on strategy of robot.
 * @author watson
 *
 */

public abstract class AbstractRobot {
	/**
	 * User to make a bets.
	 */
	User user;
	
	/**
	 * State, which shows launch robot or not. 
	 */
	boolean isStarted;
	
	/**
	 * Service to make activity every minute.  
	 */
	ScheduledExecutorService service;
	
	/**
	 * Delay in seconds between activities.
	 */
	int delaySec;
	
	/**
	 * Sum of bet. It is constant to all bets.
	 */
	int sum;
	
	/**
	 *	Events, received from server. 
	 */
	Map<Integer, Event> events;
	
	/**
	 * It is provide interface to interact with server.
	 */
	ConnectorHelper connector;
	
	/**
	 * Set of markets, which contains all markets on that we have bet.
	 * (In greedy and safety strategy we don't allow robot to bet on one market twice). 
	 */
	Set<Market> hasBets;
	
	/**
	 * Current market on that we want to bet.
	 */
	Market curMarket;
	
	
	/**
	 * Constructs a robot.
	 * @param user - user to make a bets.
	 */
	public AbstractRobot(User user) {
		connector = new ConnectorHelper();
		this.user = user;
		service = Executors.newSingleThreadScheduledExecutor();
		delaySec = 60;
		sum = 100;
	}
	
	
	/**
	 * Runs robot.
	 */
	public void run() {
		if (!isStarted) {
			service = Executors.newSingleThreadScheduledExecutor(new DaemonThreadFactory());
			isStarted = true;
			service.scheduleAtFixedRate(new Runnable() {
				@Override
				public void run() {
					getEvents();
					if (makeBet(generateOutcome())) {
						markMarket();
					}
				}
			}, 0, delaySec, TimeUnit.SECONDS);
		}
	}
	
	/**
	 * Stops robot.
	 */
	public void stop() {
		if (isStarted) {
			service.shutdown();
			isStarted = false;
		}
	}
	public boolean isStarted() {
		return isStarted;
	}
	
	/**
	 * Tries to make a bet.
	 * Sends request to server to make a bet with defined outcome.
	 * @return - true - if we make a bet, false - otherwise(we don't make a bet) .  
	 */
	
	public boolean makeBet(Outcome outcome) { 
		if (outcome != null) {
			return connector.sendMakeBetRequest(user.getLogin(), user.getName(), outcome.getOutcomeId(), 100);
		}
		return false;
	}
	
	/**
	 * Tries to get all events.
	 * Sends request to server to get all events.   
	 */
	void getEvents() {	
		events = connector.sendGetBetsRequest();
	}
	
	
	/**
	 * Adds current market to set of markets.
	 * This set of markets indicates in what markets we have bets.
	 */
	void markMarket() {
		hasBets.add(curMarket);
	}
	
	/**
	 * Generates outcome to bet.
	 * @return - outcome to make a bet, or null - it indicates that now we don't want/can to bet. 
	 */
	public abstract Outcome generateOutcome();	
}
