/**
 * 
 */
package juniors.server.ext.web.listeners;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author Dmitrii Shakshin (trueCoder)<d.shakshin@gmail.com>
 *
 */
@WebListener
public class SessionsListener implements HttpSessionListener {
	
	private static AtomicInteger countsLogin, countsLogout;
	static {
		countsLogin = new AtomicInteger(0);
		countsLogout = new AtomicInteger(0);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		countsLogin.incrementAndGet();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		countsLogout.incrementAndGet();
	}
	
	public static int getCountLogin() {
		return countsLogin.get();
	}
	
	public static int getCountsLogout() {
		return countsLogout.get();
	}

}
