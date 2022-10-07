<%-- 
    Document   : showexceptions
    Created on : 22 Jan, 2022, 6:17:50 PM
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Exception ex=(Exception)request.getAttribute("Exception");
    System.out.println("Exception is:"+ex);
    out.println("Some Exception occurred:"+ex.getMessage());
%> 