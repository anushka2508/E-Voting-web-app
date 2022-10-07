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
import evoting.dto.CandidateDetails;

/**
 * Servlet implementation class ShowUpdateCandidateControllerServlet
 */
@WebServlet("/ShowUpdateCandidateControllerServlet")
public class ShowUpdateCandidateControllerServlet extends HttpServlet {
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
        String data=request.getParameter("data");
        System.out.println("In Show update Candidate:"+data);
        try {
        	if(data!=null && data.equalsIgnoreCase("cid")) {
        		ArrayList<String> candidateList=CandidateDAO.getCandidateId();
        		request.setAttribute("candidateid", candidateList);
        		request.setAttribute("result", "candidatelist");
        	}else {
        		CandidateDetails cd=CandidateDAO.getDetailsById(data);
        		ArrayList<String> cities=CandidateDAO.getCity();
        		request.setAttribute("cities", cities);
        		request.setAttribute("candidate", cd);
        		request.setAttribute("result", "details");
        	}
        	rd=request.getRequestDispatcher("adminshowupdatecandidate.jsp");
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