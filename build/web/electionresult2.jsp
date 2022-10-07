<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="evoting.dto.CandidateDetails,java.util.LinkedHashMap, java.util.Iterator, java.util.Map" %>
<%
	String userid=(String)session.getAttribute("userid");
	if(userid==null){
		session.invalidate();
		response.sendRedirect("accessdenied.html");
		return;
	}
	String data=(String)request.getAttribute("status");
	StringBuffer displayBlock = new StringBuffer("<table>");
	
	if(data.equalsIgnoreCase("party")){
		LinkedHashMap <String, Integer> result=(LinkedHashMap)request.getAttribute("result");
		int votecount = (int)request.getAttribute("votecount");
		Iterator it=result.entrySet().iterator();
		int pos=0;
		displayBlock.append("<tr><th>Position</th><th>Party</th><th>Vote Count</th><th>Vote %</th></tr>");
		while(it.hasNext()){
			pos++;
			Map.Entry<String, Integer> e=(Map.Entry)it.next();
			String party=e.getKey();
			float voteper=(e.getValue()*100.0f)/votecount;
			displayBlock.append("<tr><td>"+pos+"</td><td>"+party+"</td><td>"+e.getValue()+"</td><td>"+voteper+"</td></tr>");	
		}
		displayBlock.append("</table>");
	}else if(data.equalsIgnoreCase("gender")){
		LinkedHashMap <String, Integer> result=(LinkedHashMap)request.getAttribute("result");
		int votecount = (int)request.getAttribute("votecount");
		Iterator it=result.entrySet().iterator();
		int pos=0;
		displayBlock.append("<tr><th>Position</th><th>Gender</th><th>Vote Count</th><th>Vote %</th></tr>");
		while(it.hasNext()){
			pos++;
			Map.Entry<String, Integer> e=(Map.Entry)it.next();
			String gender=e.getKey();
			System.out.println(gender);
			float voteper=(e.getValue()*100.0f)/votecount;
			displayBlock.append("<tr><td>"+pos+"</td><td>"+gender+"</td><td>"+e.getValue()+"</td><td>"+voteper+"</td></tr>");	
		}
		displayBlock.append("</table>");
	}
	
	out.println(displayBlock);
%>
