<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="evoting.dto.CandidateInfo" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <link href="stylesheet/showcandidate.css" rel="stylesheet">
        <title>Voting Details</title>
</head>
	<body>
		<%
			String userid=(String)session.getAttribute("userid");
        	if(userid==null){
            	session.invalidate();
            	response.sendRedirect("accessdenied.html");
            	return;
        	}
        	CandidateInfo candidate=(CandidateInfo)session.getAttribute("candidate");
        	StringBuffer displayBlock = new StringBuffer("");
        	displayBlock.append("<div class='sticky'><div class='candidate'>VOTE FOR CHANGE</div><br>");
        	if(candidate==null){
        		displayBlock.append("<div class='subcandidate'>Sorry! Your vote could not be casted</div><br><br>");
        		displayBlock.append("<div><h4 id='logout'><a href='LoginControllerServlet?logout=logout'>Logout</a></h4></div></div>");
        		out.println(displayBlock);
        	}else{
        		displayBlock.append("<div class='subcandidate'>Thank you for voting!</div><br><br>");
        		displayBlock.append("<div class='subcandidate'>Your vote addedd successfully<br>");
        		displayBlock.append("<div class='logout'><a href='LoginControllerServlet?logout=logout'>logout</a></div></div>");	
        		displayBlock.append("<div class='candidateprofile'><p>Candidate Id:"+candidate.getCandidateId()+"</p><br>");
        		displayBlock.append("<strong>You voted for:</strong><br><br><div><img src='data:image/jpg;base64,"+candidate.getSymbol()+"' style='widht:300px;height:200px;'/><br>");
        		displayBlock.append("<p>Candidate Name:"+candidate.getCandidateName()+"<br>");
        		displayBlock.append("Party:"+candidate.getParty()+"<br></p></div></div>");
				out.println(displayBlock);
        	}
		%>
	</body>
</html>