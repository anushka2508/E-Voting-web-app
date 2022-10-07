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

/**
 * Servlet implementation class RemoveCandidateControllerServlet
 */
@WebServlet("/RemoveCandidateControllerServlet")
public class RemoveCandidateControllerServlet extends HttpServlet {
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
            String cid=request.getParameter("data");
            System.out.println(cid);
            boolean result=CandidateDAO.removeCandidate(cid);
            if(result){
                rd=request.getRequestDispatcher("success.jsp");
            }else{
                rd=request.getRequestDispatcher("failure.jsp");
            }
        }catch(Exception ex){
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
