<%@ page import="juniors.server.ext.web.stubs.*" %>
<%@ page import="java.util.*" %>
<br>
<div style="font-size: xx-large; color: rgb(255, 100, 100);">
	<p>Actual events</p>
	<!-- 
	
			ITS ALL IS STUBS !!!!
			ONLY FOR DEMO VERSION!!!
			
	 -->
	<%
		Event e1 = new Event(new Date(2013, 11, 2), new Date(2013, 11, 3));
		Event e2 = new Event(new Date(2013, 11, 5), new Date(2013, 11, 5));
		e1.generateTestData("Poker womens =)");
		e1.generateTestData("Sport programming. Gils. ");
	%>
		<div name="event" class="eventItem" onclick="showChild(); viewall();"><%= e1.toString() %></div>
		<div id="all">
	<%
		for(int i = 0; i < e1.getMarkets().size(); ++i){
	%>
			
			<div id="m<%= i %>" name="market" class="itemMarket" onclick="showc(this); ">Market_<%= i %></div>
			
	
	<%
			for(int j = 0; j < e1.getMarkets().get(i).getResults().size(); ++j) {
	%>
				<div id="c<%= i %>" name="coeff" class="cell"><%= e1.getMarkets().get(i).getResults().get(j).getCoeff() %></div>
	<%
			}
		}
	%>
	<div id="all">		
</div>