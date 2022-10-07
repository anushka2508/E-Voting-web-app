<%-- 
    Document   : votedenied
    Created on : 12 Feb, 2022, 11:11:32 AM
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="evoting.dto.CandidateInfo" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="jsscript/adminoptions.js"></script>
        <script src="jsscript/jquery.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <link href="stylesheet/admin.css" rel="stylesheet">
        <link href="stylesheet/result.css" rel="stylesheet">
       

        <title>Vote Denied Page</title>
    </head>
    <body>
        <%
            String userid=(String)session.getAttribute("userid");
            if(userid==null){
                session.invalidate();
                response.sendRedirect("accessdenied.html");
                return;
            }  
            CandidateInfo cd = (CandidateInfo)request.getAttribute("candidate");
        	StringBuffer displayBlock = new StringBuffer("");
        		
        	displayBlock.append("<div class='sticky'>");
        	displayBlock.append("<div class='candidate'>VOTE FOR CHANGE</div><br>");
        	displayBlock.append("<div class='subcandidate'>Your Vote Already Taken</div><br><br>");
        	displayBlock.append("<div class='logout'><a href='LoginControllerServlet?logout=logout'>logout</a></div></div>");
			displayBlock.append("<br><br><br><br><br><br><div align='center'><table>");
			displayBlock.append("<tr><th>Candidate Id:</th><td>"+cd.getCandidateId()+"</td></tr>");
			displayBlock.append("<tr><th>Candidate Name:</th><td>"+cd.getCandidateName()+"</td></tr>");
			displayBlock.append("<tr><th>Party:</th><td>"+cd.getParty()+"</td></tr>");
			displayBlock.append("<tr><th>Symbol:</th><td><img src='data:image/jpg;base64,"+cd.getSymbol()+"' style='width:300px;height:200px;'");
			displayBlock.append("</table></div>");
			out.println(displayBlock);
        %>  
    </body>
</html>
