<%-- 
    Document   : adminoptions.jsp
    Created on : 24 Jan, 2022, 12:17:33 PM
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
       

        <title>Admin Options</title>
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
            <div class='subcandidate'>Admin Actions Page</div><br><br>
            <div class='logout'><a href='LoginControllerServlet?logout=logout'>logout</a></div>
        </div>
        <div class='container'>
            <div id='dv1' onclick='redirectadministratorpage()' style='cursor: pointer'><img src='images/administrator.png' height='300px' width='300px'><br>
                <h3> Admin Option</h3>
            </div>
            <div id='dv2' onclick='redirectvotingpage()' style='cursor: pointer'><img src='images/voteadmin.jpg' height='300px' width='300px' ><br>
                <h3> Voting Page</h3>
            </div>
        </div>
        <br><br>
        <div align='center' id='result'></div>
    </body>
</html>  
