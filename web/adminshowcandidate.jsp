<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="evoting.dto.CandidateDetails" %>
<%@page import="org.json.JSONObject" %>
<%
	String userid=(String)session.getAttribute("userid");
    if(userid==null){
    	session.invalidate();
        response.sendRedirect("accessdenied.html");
        return;
    }
    String result=(String)request.getAttribute("result");
    StringBuffer displayBlock=new StringBuffer();
    if(result!=null && result.equalsIgnoreCase("candidatelist")){
    	ArrayList<String> candidateList=(ArrayList)request.getAttribute("candidateid");
    	displayBlock.append("<option value=''>Choose Id</option>");
    	for(String c:candidateList){
    		displayBlock.append("<option value='"+c+"'>"+c+"</option>");
    	}
    	JSONObject json = new JSONObject();
    	json.put("cid", displayBlock.toString());
    	out.println(json);
    	System.out.println("In adminshowcand");
    	System.out.println(displayBlock);
    	
    }else if(result!=null && result.equalsIgnoreCase("details")) {
    	CandidateDetails cd =(CandidateDetails)request.getAttribute("candidate");
    	String str="<img src='data:image/png;base64,"+cd.getSymbol()+"' style='width:200px;height=130px;'/>";
    	displayBlock.append("<table><tr><th>User Id:</th><td>"+cd.getUserId()+"</td></tr>"
    			+"<tr><th>Candidate Name:</th><td>"+cd.getCandidateName()+"</td></tr>"
    			+"<tr><th>City:</th><td>"+cd.getCity()+"</td></tr>"
    			+"<tr><th>Party:</th><td>"+cd.getParty()+"</td></tr>"
    			+"<tr><th>Symbol:</th><td id='image'>"+str+"</td></tr></table>");
    	JSONObject json=new JSONObject();
    	json.put("subdetails", displayBlock.toString());
    	out.println(json);
        System.out.println("in admin show candidate");
        System.out.println(displayBlock);
    }

%>
