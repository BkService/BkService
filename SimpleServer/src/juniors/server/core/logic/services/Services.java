package juniors.server.core.logic.services;


/**
 * Фабрика сервисов
 * @author Dmitrii Shakshin (trueCoder)<d.shakshin@gmail.com>
 * 
 */
public class Services {

	private static final Services instance;
	
	static{
		instance = new Services();
	}
	
	private Services() {
		
	}

	public static Services getInstance() {
		return instance;
	}
	
	public AccountsService getAccountsService() {
		return new AccountsService();
	}
	
	public EventService getEventService() {
		return new EventService();
	}
}
