package juniors.server.core.logic;

import junior.server.core.feed.FeedLoader;
import juniors.server.core.logic.services.Services;

/**
 * 
 * @author Dmitrii Shakshin (trueCoder)<d.shakshin@gmail.com>
 */
public class ServerFacade {

	private boolean started = false;

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
	
	public ServerFacade() {
		//executorServices = Executors.newFixedThreadPool(COUNT_SERVICES);
	}
	
	private void runServices() {
		FeedLoader fl = new FeedLoader();
		Thread t = new Thread(fl);
		t.setDaemon(true); 
		t.start();
	}
	
	private void stopServices() {}
	
	public synchronized void start() {
		if(!started) {		//alex fixed
			runServices();
			started = true;
		}
	}
	
	public boolean getStatusFL() {		//alex add
		return started;
	}
	
	public synchronized void stop() {
		stopServices();
		started = false;
	}
	
	public Services getServices() {
		Services services = null;
		if (started)
			services = new Services();
		return services;
	}
}
