<%-- 
    Document   : adminactions
    Created on : 25 Jan, 2022, 7:05:44 AM
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
        <link href="stylesheet/result.css" rel="stylesheet">
       

        <title>Admin Actions Page</title>
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
            <div id='dv1' onclick='manageuser()' style='cursor: pointer'><img src='images/muser.png' height='250px' width='250px'><br>
                <h3> Manage User</h3>
            </div>
            <div id='dv2' onclick='managecandidate()' style='cursor: pointer'><img src='images/ManageCandLists.jpg' height='250px' width='250px' ><br>
                <h3> Manage Candidate</h3>
            </div>
            <div id='dv3' onclick='resultpage()' style='cursor: pointer'><img src='images/resultgraph.jpg' height='250px' width='250px' ><br>
                <h3> Result</h3>
            </div>
        </div>
        <br><br>
        <div align='center' id='result'></div>
    </body>
</html>
