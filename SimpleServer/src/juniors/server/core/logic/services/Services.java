package juniors.server.core.logic.services;

import juniors.server.core.logic.ServerFacade;

/**
 * Фабрика сервисов
 * @author Dmitrii Shakshin (trueCoder)<d.shakshin@gmail.com>
 * 
 */
public class Services {

	private static volatile ServerFacade instance;

	public static ServerFacade getInstance() {
		ServerFacade localInstance = instance;
		if (localInstance == null) {
			synchronized (ServerFacade.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new ServerFacade();
				}
			}
		}
		return localInstance;
	}
	
	public AccountsService getAccountsService() {
		return new AccountsService();
	}
}
