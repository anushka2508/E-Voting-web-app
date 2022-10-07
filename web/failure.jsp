<%-- 
    Document   : failure
    Created on : 28 Jan, 2022, 10:21:24 AM
    Author     : hp
--%>

<%
    String userid=(String)session.getAttribute("userid");
    if(userid==null)
    {
        session.invalidate();
        response.sendRedirect("accessdenied.html");
        return;
    }
    out.println("failure!");
%>