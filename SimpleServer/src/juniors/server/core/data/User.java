package juniors.server.core.data;


/*
 * @description Test-class User for simple test server
 * @author 		Alex 
 */
public class User {
	
	public static final String DEFAULT_USERNAME = "Mr.Green";
	
	Long userId;
	String userName;
	
	public User() {
		this.userId = getNextId();
		this.userName = DEFAULT_USERNAME;
	}
	
	public User(String name) {
		this();
		this.userName = name;
	}
	
	public void setName(String name) {
		this.userName = name;
	}
	
	public Long getUserId() {
		return this.userId;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	private static Long nextId = 0L;
	
	private static Long getNextId() {
		return nextId++;
	}

}
