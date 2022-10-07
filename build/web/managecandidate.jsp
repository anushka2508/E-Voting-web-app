<%-- 
    Document   : managecandidate
    Created on : 31 Jan, 2022, 11:52:29 AM
    Author     : hp
--%>
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
       

        <title>Manage Candidate</title>
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
            <div id='dv1' onclick='showaddcandidateform()' style='cursor: pointer'><img src='images/addcandidate.png' height='300px' width='300px'><br>
                <h3> Add Candidater</h3>
            </div>
            <div id='dv2' onclick='showupdatecandidateform()' style='cursor: pointer'><img src='images/update1.jpg' height='300px' width='300px' ><br>
                <h3> Update Candidate</h3>
            </div>
            <div id='dv3' onclick='showcandidate()' style='cursor: pointer'><img src='images/candidate.jpg' height='300px' width='300px' ><br>
                <h3> Show Candidate</h3>
            </div>
            <div id='dv4' onclick='deletecandidate()' style='cursor: pointer'><img src='images/update3.jpg' height='300px' width='300px' ><br>
                <h3> Delete Candidate</h3>
            </div>
        </div>
        <br><br>
        <div align='center' id='result'></div>
    </body>
</html>

