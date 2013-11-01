package junior.server.core.logic.services;

import junior.server.core.logic.ServerFacade;

/**
 * Фабрика сервисов
 * @author Dmitrii Shakshin (trueCoder)<d.shakshin@gmail.com>
 * 
 */
public class Services {

	private static volatile Services instance;

	public static Services getInstance() {
		Services localInstance = instance;
		if (localInstance == null) {
			synchronized (ServerFacade.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new Services();
				}
			}
		}
		return localInstance;
	}
	
	public AccountsService getAccountsService() {
		return new AccountsService();
	}
	
}
