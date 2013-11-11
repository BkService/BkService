package juniors.server.ext.web.xshell;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import juniors.server.core.logic.ServerFacade;

public class XShell extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public XShell() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String command = request.getParameter("command");
		String path = "/console.jsp";
		if(command.equals("exit")) 
			path = "/LogoutHandler";
		String oldLines = (String)request.getSession().getAttribute("shell");
		String br = (oldLines == "") ? oldLines : "<br>";
		oldLines = oldLines + br + "admin@simpleserver ~ $ " + command;
		oldLines += execute(command, request);
			if(command.equals("clear")) oldLines="";
		request.getSession().setAttribute("shell", oldLines);
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service(request, response);
	}
	
	protected String execute(String cmd, HttpServletRequest request) {
		String result = "<br>command not found. input 'man' for get help";
		if(cmd.equals("startfl")) {
			ServerFacade facade = ServerFacade.getInstance();
			if(facade.getStatusService(ServerFacade.TypeRunService.SERVICE_FEEDLOADER))
				facade.start();
			result = "<br>start feed loader.... success. Date start " + new Date().toString() + "<br>";
			return result;
		}
		if(cmd.equals("stopfl")) {
			ServerFacade facade = ServerFacade.getInstance();
			if(!facade.getStatusService(ServerFacade.TypeRunService.SERVICE_FEEDLOADER))
				facade.stop();
			result = "<br>stop feed loader.... success. Date stop " + new Date().toString();
			return result;
		}
		if(cmd.equals("statefl")) {
			ServerFacade facade = ServerFacade.getInstance();
			result = facade.getStatusService(ServerFacade.TypeRunService.SERVICE_FEEDLOADER) ? "<br>feed is working" : "<br>feed is stoped";
		}
		if(cmd.equals("info")) {
			result = "<br>Server name: " + request.getServerName() + ". " +
					"Server port: " + request.getServerPort() + ". " +
							" Scheme: " + request.getScheme();
		}
		if(cmd.equals("block")) {
			result = "<br><div id=\"g" +getNextId()+ "\" class=\"grInfo\">" +
					 "description graphic. Some graphic will be here." +
					 "<div class=\"close\" onclick=\"closeGr('g"+ getBack() +"');\">-</div>" +
					 "<br><img class=\"graphic\" href=\"/GrServlet?type=test\" />" +
					 "</div><br>";
		}
		if(cmd.equals("man")) return this.manual;
		if(cmd.equals("")) return cmd;
		return result;
	}
	
	private final String manual = "<br>Manual of the xshell interpretator. Commands:<br>" +
			"clear 		- delete history.<br>" +
			"startfl 	- start the feed loader<br>" +
			"stopfl		- stoop the feed loader<br>" +
			"statefl	- get state of feed loader" +
			"info 		- get information about server<br>" +
			"block 		- test graphics information<br>" +
			"exit 		- exit from xshell";
	
	int nextId = 1;
	private int getNextId() {
		return nextId++;
	}
	private int getBack() {
		return this.nextId - 1;
	}
	
}
