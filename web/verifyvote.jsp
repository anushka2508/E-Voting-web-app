<%-- 
    Document   : verifyvote
    Created on : 13 Feb, 2022, 11:44:58 AM
    Author     : hp
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userid=(String)session.getAttribute("userid");
	if(userid==null){
		session.invalidate();
		response.sendRedirect("accessdenied.html");
		return;
	}
	boolean result=(boolean)request.getAttribute("result");
	if(result==true)
		out.println("success");
	else
		out.println("failed");
%>
