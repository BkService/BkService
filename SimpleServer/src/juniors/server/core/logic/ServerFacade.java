package juniors.server.core.logic;

import junior.server.core.feed.FeedLoader;
import juniors.server.core.logic.services.Services;
import juniors.server.core.logic.services.StaticService;

/**
 * 
 * @author Dmitrii Shakshin (trueCoder)<d.shakshin@gmail.com>
 */
public class ServerFacade {

	private boolean started = false;

	private static volatile ServerFacade instance;

	private Thread feedLoaderThread, staticServiceThread;

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
		FeedLoader fl = new FeedLoader();
		feedLoaderThread = new Thread(fl);
		feedLoaderThread.setDaemon(true);
		StaticService stService = new StaticService();
		staticServiceThread = new Thread(stService);
	}

	private void runServices() {
		feedLoaderThread.start();
		staticServiceThread.start();
	}

	private void stopServices() {
		staticServiceThread.interrupt();
	}

	public synchronized void start() {
		if (!started) {
			runServices();
			started = true;
		}
	}

	public boolean getStatusFL() {
		return started;
	}

	public synchronized void stop() {
		stopServices();
		started = false;
	}

	public Services getServices() {
		Services services = null;
		if (started)
			services = Services.getInstance();
		return services;
	}
}
