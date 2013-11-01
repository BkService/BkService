package junior.server.core.data;

import junior.server.core.data.events.*;
import junior.server.core.data.users.*;

public class Data implements UserManagerInterface, EventManagerInterface{
	
	@SuppressWarnings("unused")
	private UserManagerInterface userManager;
	@SuppressWarnings("unused")
	private EventManagerInterface eventManager;
	
	public Data(){
		userManager = new UserManager();
		eventManager = new EventManager();
	}
	
	@Override
	public Event addEvent(Event new_event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Event getEvent(int event_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Event removeEvent(int event_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean containsUser(String login) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeLogin(String login, String new_login) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createUser(String new_login, String new_name,
			String new_surname, String new_password, String new_bank_account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUser(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean changeUserData(String login, String new_name,
			String new_surname, String new_password, String new_bank_account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean authorizeUser(Integer user_id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getCountUsers() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCountAuthorizedUsers() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
