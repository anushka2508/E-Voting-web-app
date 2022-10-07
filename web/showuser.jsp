<%-- 
    Document   : showuser
    Created on : 21 Feb, 2022, 7:56:19 PM
    Author     : hp
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="evoting.dto.UserDetails, java.util.ArrayList" %>
<%
	String userid=(String)session.getAttribute("userid");
	if(userid==null){
		session.invalidate();
		response.sendRedirect("accessdenied.html");
		return;
	}
	ArrayList<UserDetails> userList=(ArrayList<UserDetails>)request.getAttribute("userList");
	StringBuffer displayBlock = new StringBuffer("<table class='showUser'>");
	displayBlock.append("<tr><th>User Id</th><th>Username</th><th>Address</th><th>City</th><th>Email</th><th>Mobile No</th></tr>");
	for(UserDetails user:userList){
		displayBlock.append("<tr><td>"+user.getUserid()+"</td><td>"+user.getUsername()+"</td><td>"+user.getAddress()+"</td><td>"+user.getCiry()+"</td><td>"+user.getEmail()+"</td><td>"+user.getMobile()+"</td><tr>");
	}
	displayBlock.append("</table>");
	out.println(displayBlock);
	System.out.println(displayBlock);
%>