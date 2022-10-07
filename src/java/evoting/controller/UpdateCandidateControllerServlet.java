package evoting.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import evoting.dao.CandidateDAO;
import evoting.dto.CandidateDTO;
import evoting.dto.CandidateDetails;

/**
 * Servlet implementation class UpdateCandidateControllerServlet
 */
@WebServlet("/UpdateCandidateControllerServlet")
public class UpdateCandidateControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String fileName=null;
	        RequestDispatcher rd=null;
	        HttpSession sess=request.getSession();
	        String userid=(String)sess.getAttribute("userid");
	        if(userid==null){
	            sess.invalidate();
	            response.sendRedirect("accessdenied.html");
	            return;
	        }
	        try{
	            DiskFileItemFactory df=new DiskFileItemFactory();
	            ServletFileUpload sfu=new ServletFileUpload(df);
	            ServletRequestContext src= new ServletRequestContext(request);
	            List<FileItem> multiList=sfu.parseRequest(src);
	            InputStream inp=null;
	            ArrayList<String> objValues = new ArrayList<>();
	            for(FileItem fit:multiList){
	                if(fit.isFormField()){
	                    String fname=fit.getFieldName();
	                    String value=fit.getString();
	                    System.out.println("Inside if");
	                    System.out.println(fname+" : "+value);
	                    
	                    objValues.add(value);
	                }else{
	                    inp =fit.getInputStream();
	                    String key=fit.getFieldName();
	                    fileName=fit.getName();
	                    System.out.println("Inside else");
	                    System.out.println(key+" : "+fileName);
	                }
	            }
	            if(fileName.equalsIgnoreCase("")) {
	            	inp=CandidateDAO.getSymbolById(objValues.get(0));
	            	System.out.println("if fileNmae==null");
	            }
	            CandidateDTO candidateDTO = new CandidateDTO(objValues.get(0),objValues.get(3),objValues.get(4),objValues.get(1),inp);
	            boolean result=CandidateDAO.updateCandidate(candidateDTO);
	            if(result){
	                rd=request.getRequestDispatcher("success.jsp");
	            }else{
	                rd=request.getRequestDispatcher("failure.jsp");
	            }
	        }catch(Exception ex){
	            System.out.println("Exception in AddNewCandidateServlet:"+ex.getMessage());
	            rd=request.getRequestDispatcher("showexception.jsp");
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
	    }

	}
