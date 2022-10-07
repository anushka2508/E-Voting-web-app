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

import evoting.dao.CandidateDAO;
import evoting.dao.UserDAO;
import evoting.dto.CandidateDetails;
import evoting.dto.UserDetails;

/**
 * Servlet implementation class ShowUserControllerServlet
 */
@WebServlet("/ShowUserControllerServlet")
public class ShowUserControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd=null;
        HttpSession sess=request.getSession();
        String userid=(String)sess.getAttribute("userid");
        if(userid==null) {
        	sess.invalidate();
        	response.sendRedirect("accessdenied.html");
        	return;
        }
        try {
        	ArrayList<UserDetails> userList= UserDAO.getAllUser();
        	request.setAttribute("userList", userList);
        	rd=request.getRequestDispatcher("showuser.jsp");
        }catch(Exception ex) {
        	ex.printStackTrace();
        	rd=request.getRequestDispatcher("showexception.jsp");
        	request.setAttribute("Exception", ex);
        }finally {
        	if(rd!=null) {
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
    }
}
