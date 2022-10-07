package evoting.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import evoting.dao.CandidateDAO;
import evoting.dao.VoteDAO;
import evoting.dto.CandidateDetails;

/**
 * Servlet implementation class ElectionResultControllerServlet
 */
@WebServlet("/ElectionResultControllerServlet")
public class ElectionResultControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		HttpSession sess=request.getSession();
        String userid=(String)sess.getAttribute("userid");
        RequestDispatcher rd=null;
        if(userid==null){
            sess.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }
        try {
        	String data=request.getParameter("data");
        	if(data.equalsIgnoreCase("result")) {
        		Map <String,Integer> result = VoteDAO.getResult();
        		Set s=result.entrySet();
        		Iterator it=s.iterator();
        		LinkedHashMap<CandidateDetails, Integer> resultDetails=new LinkedHashMap<>();
        		while(it.hasNext()) {
        			Map.Entry<String, Integer> e=(Map.Entry)it.next();
        			CandidateDetails cd=CandidateDAO.getDetailsById(e.getKey());
        			resultDetails.put(cd, e.getValue());
        		}
        		request.setAttribute("result", resultDetails);
        		request.setAttribute("votecount", VoteDAO.getVoteCount());
        		rd=request.getRequestDispatcher("electionresult.jsp");
        	}else if(data.equalsIgnoreCase("party")) {
        		request.setAttribute("status", "party");
        		request.setAttribute("result", VoteDAO.getResultByParty());
        		request.setAttribute("votecount", VoteDAO.getVoteCount());
        		rd=request.getRequestDispatcher("electionresult2.jsp");
        	}else if(data.equalsIgnoreCase("gender")){
        		request.setAttribute("status", "gender");
        		request.setAttribute("result", VoteDAO.getResultByGender());
        		request.setAttribute("votecount", VoteDAO.getVoteCount());
        		rd=request.getRequestDispatcher("electionresult2.jsp");
        	}
        }catch(Exception ex) {
        	ex.printStackTrace();
            request.setAttribute("Exception", ex);
            rd=request.getRequestDispatcher("showexception.jsp");
        }finally {
        	if(rd!=null)
        		rd.forward(request, response);
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
 