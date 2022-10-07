package evoting.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import evoting.dao.VoteDAO;
import evoting.dto.CandidateInfo;
import evoting.dto.VoteDTO;

/**
 * Servlet implementation class AddVoteControllerServlet
 */
@WebServlet("/AddVoteControllerServlet")
public class AddVoteControllerServlet extends HttpServlet {
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
		try {
			String candidateId=(String)request.getParameter("candidateid");
			VoteDTO vote = new VoteDTO(candidateId,userid);
			boolean result=VoteDAO.addVote(vote);
			CandidateInfo candidate=VoteDAO.getVote(candidateId);
			if(result==true)
				sess.setAttribute("candidate", candidate);
			request.setAttribute("result", result);
			rd=request.getRequestDispatcher("verifyvote.jsp");
		}catch(Exception ex) {
			ex.printStackTrace();
			request.setAttribute("Exception", ex);
			rd=request.getRequestDispatcher("showexception.jsp");
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
    }// </editor-fold>

   

}
