package evoting.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import evoting.dao.UserDAO;
import evoting.dto.UserDetails;

/**
 * Servlet implementation class ShowRemoveUserControllerServlet
 */
@WebServlet("/ShowRemoveUserControllerServlet")
public class ShowRemoveUserControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher rd=null;
        HttpSession sess=request.getSession();
        String userid=(String)sess.getAttribute("userid");
        if(userid==null){
            sess.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }
        try{
            String uid=(String)request.getParameter("data");
            System.out.println(uid);
            if(uid!=null && uid.equalsIgnoreCase("getId")) {
            	ArrayList<String> userId=UserDAO.getAllUserId();
            	request.setAttribute("data", "id");
            	request.setAttribute("userId", userId);
            	System.out.println("In");
            }else {
            	request.setAttribute("data", "user");
            	UserDetails user=UserDAO.getUserById(uid);
            	request.setAttribute("user", user);
            }
            rd=request.getRequestDispatcher("showremoveuser.jsp");
        }catch(Exception ex){
        	request.setAttribute("Exception", ex);
        	rd=request.getRequestDispatcher("showexception.jsp");
            System.out.println("Exception in RemoveCandidateControllerServlet:"+ex.getMessage());
            ex.printStackTrace();
        }finally{
            if(rd!=null){
                rd.forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}