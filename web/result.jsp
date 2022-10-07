

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
       

        <title>Result Page</title>
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
            <div class='subcandidate'>Result Page</div><br><br>
            <div class='logout'><a href='LoginControllerServlet?logout=logout'>logout</a></div>
        </div>
        <div class='container'>
            <div id='dv1' onclick='electionresult()' style='cursor: pointer'><img src='images/resultcity.jpeg' height='250px' width='250px'><br>
                <h3> City-wise User</h3>
            </div>
            <div id='dv2' onclick='partywiseresult()' style='cursor: pointer'><img src='images/party.jpeg' height='250px' width='250px' ><br>
                <h3> Partywise Result </h3>
            </div>
            <div id='dv3' onclick='genderpercentage()' style='cursor: pointer'><img src='images/gender.png' height='250px' width='250px' ><br>
                <h3> Gender-wise Vote %</h3>
            </div>
        </div>
        <br><br>
        <div align='center' id='result'></div>
    </body>
</html>