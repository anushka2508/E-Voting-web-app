<%-- 
    Document   : showcandidate
    Created on : 12 Feb, 2022, 11:02:53 AM
    Author     : hp
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="evoting.dto.CandidateInfo" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="jsscript/vote.js"></script>
        <script src="jsscript/jquery.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <link href="stylesheet/showcandidate.css" rel="stylesheet">
       

        <title>Show Candidate</title>
    </head>
    <body>
        <%
            String userid=(String)session.getAttribute("userid");
            if(userid==null){
                session.invalidate();
                response.sendRedirect("accessdenied.html");
                return;
            }
        %>
        <div class='sticky'>
            <div class='candidate'>VOTE FOR CHANGE</div><br>
            <div class='subcandidate'>Whom do you want to vote ?</div><br><br>
            <div class='logout'><a href='LoginControllerServlet?logout=logout'>logout</a></div>
         </div>
		
        <%
        	ArrayList<CandidateInfo> candidateList=(ArrayList<CandidateInfo>)request.getAttribute("candidateList");
        	StringBuffer displayBlock=new StringBuffer("");
        	displayBlock.append("<div class='buttons'>");
        	for(CandidateInfo c:candidateList){
        		System.out.println(c.getCandidateId());
        		displayBlock.append("<input type='radio' name='flat' id='"+c.getCandidateId()+"' value='"+c.getCandidateId()+"' onclick='addvote()'/>");
        		displayBlock.append("<label for='"+c.getCandidateId()+"'><img src='data:image/jpg;base64,"+c.getSymbol()+"' id='"+c.getCandidateId()+"' style='width:300px;height:200px; cursor:pointer;'/></label>");
        		displayBlock.append("<br><div class='candidateprofile'><p>Candidate Id:"+c.getCandidateId()+"<br>");
        		displayBlock.append("Candidate Name:"+c.getCandidateName()+"<br>");
        		displayBlock.append("Party:"+c.getParty()+"<br></p></div>");
        	}
        	displayBlock.append("</div>");
        	out.println(displayBlock);
        %>
        <br><br>
        <div align='center' id='result'></div>
    </body>
</html>
