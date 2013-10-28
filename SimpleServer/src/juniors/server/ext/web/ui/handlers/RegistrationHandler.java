package juniors.server.ext.web.ui.handlers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import juniors.server.ext.web.stubs.ConnectionManager;

public class RegistrationHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public RegistrationHandler() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = (String) request.getParameter("firstName");
		String lname = (String) request.getParameter("lastName");
		String login = (String) request.getParameter("login");
		String passwd  = (String) request.getParameter("passwd");
		String rpasswd  = (String) request.getParameter("rpasswd");
		String visa = (String) request.getParameter("count");

		/*
		 * Если юзер не существует - добавляем его
		 * иначе идем на начальную страницу с сообщение об ошибке
		 */
		if(!ConnectionManager.getConnection().checkUser(login))
			ConnectionManager.getConnection().createUser(name, lname, login, passwd, rpasswd, visa);
		else
			request.getSession().setAttribute("msg", new String("User with login <b>" + login + "</b> exists"));
		request.getRequestDispatcher("/").forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service(request, response);
	}

}
