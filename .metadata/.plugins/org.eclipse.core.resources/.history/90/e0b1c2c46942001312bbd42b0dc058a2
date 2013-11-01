package junior.server.core.data.users;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class UserManager implements UserManagerInterface{
	
	private Map<String, User> userMap; // Контейнер с пользователями. Ключь для объекта - логин. 	
	private Map<Integer, User> authorizedUsers; // контейнер с авторизованными пользователями
	
	public UserManager(){
		userMap = new HashMap<String, User>();
		authorizedUsers = new HashMap<Integer, User>();
	}
	
	/**
	 * 
	 * @param login
	 * @return user с указанным логином 
	 */
	public User getUser(String login){
		return userMap.get(login);
	}
	
	/**
	 * Добавляет нового пользователя с заданными параметрами.
	 * Логин у каждого пользователя уникален.
	 * Так же создаёт для него новый банковский счёт (ещё не реализовано) 
	 * 
	 * @param new_login
	 * @param new_name
	 * @param new_surname
	 * @param new_password
	 * @param new_bank_account
	 * @return true - пользователь создан. False - произошла ошибка при создании.
	 */
	public boolean createUser(String new_login, String new_name, String new_surname, 
			String new_password, String new_bank_account) 
	{
		// такой логин существует
		if (userMap.containsKey(new_login)){
			return false;
		}
			
		// имя с фамилией - пустые строки (а эта проверка нужна?)
		if (new_name.length() == 0 || new_surname.length() == 0){
			return false;
		}
		
		userMap.put(new_login, 
				new User(new_login, new_name, new_surname, new_bank_account));
		
		return true;
	}
	
	/**
	 * Заменить login на new_login
	 * 
	 * @param login - старый логин
	 * @param new_login - новый логин
	 * @return true - замена выполнена. false - произошла ошибка
	 * @throws LoginIsBusyException
	 * @throws LengthLoginException 
	 */
	public boolean changeLogin(String login, String new_login)
		{
		// проверка данных
		// новый логин уже занят
		if (userMap.containsKey(new_login)){ 
			return false;
		}
		
		// не существует пользователя с указанным старым логином
		if (userMap.containsKey(login)){
			return false;
		}
						
		// замена логина. Надо доделать
		User user = userMap.get(login);
		user.setLogin(new_login);
		userMap.remove(login);
		userMap.put(new_login, user);
		
		return true;
	}
	
	/**
	 * @param login
	 * @return true - пользователь существует. false - пользователь не существует.
	 */
	public boolean containsUser(String login){
		return userMap.containsKey(login);
	}
	
	
	/*
	public boolean changePassword(String password, String new_password){
		
	}*/
	
	/**
	 * Задать пользователю с указаным логином новые имя, фамилию, номер счёти и пароль
	 */
	@Override
	public boolean changeUserData(String login, String new_name,
			String new_surname, String new_password, String new_bank_account) {
		User user = userMap.get(login);
		
		if (user == null){
			return false;
		}
		
		user.setSurname(new_surname);
		user.setBankAccount(new_bank_account);
		user.setName(new_name);
		user.setPassword(new_password);
		
		return true;
	}
	
	
	/**
	 * Авторизовать пользователя
	 * @param user_id - ключ
	 * @return true - авторизован. false - невозможно авторизовать
	 */
	@Override
	public boolean authorizeUser(Integer user_id) {
		User user = userMap.get(user_id);
		
		// если пользователя не существует или пользователь уже авторизован
		if (user == null || user.isAuthorized){
			return false;
		}
		
		user.lastTimeActive = Calendar.getInstance();
		user.isAuthorized = true;
		
		// user_id заменим на какой то ключ
		authorizedUsers.put(user_id, user);
		
		return true;
	}
	
	
	/**
	 * для проведения тестов
	 * @param args - не используется
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
}
