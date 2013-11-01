package juniors.server.core.logic.services;

import java.util.logging.Logger;

import junior.server.core.data.DataManager;

public class StaticService implements Runnable {

	private static int countsLogin = 0;
	private static int countsLogout = 0;
	private static int countsUsers = 0;
	
	private Logger log = Logger.getLogger(StaticService.class.getSimpleName());

	private static final int DELAY = 1000;

	public int getCountsLogin() {
		return countsLogin;
	}

	public int getCountsLogout() {
		return countsLogout;
	}

	public int getCountsUsers() {
		return countsUsers;
	}

	@Override
	public void run() {
		while (!Thread.interrupted()) {
			countsUsers = DataManager.getInstance().getCountUsers();
				try {
					Thread.sleep(DELAY);
				} catch (InterruptedException e) {
					log.info("Stop static service.");
				}
		}
	}
}
