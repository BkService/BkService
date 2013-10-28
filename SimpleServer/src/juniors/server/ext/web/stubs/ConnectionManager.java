package juniors.server.ext.web.stubs;

import java.util.Random;

public class ConnectionManager {

	public static ConnectionManager getConnection() {
		return new ConnectionManager();
	}
	
	public boolean createUser(String ... strings) {
		return true;
	}
	
	public boolean checkUser(String ... strings) {
		return new Random().nextBoolean();
	}
}
