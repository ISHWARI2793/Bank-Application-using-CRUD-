package bank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.dao.RegisterDaoImpl;
import bank.model.Register;

/**
 * Servlet implementation class SearchController
 */
@WebServlet("/SearchController")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int accNumber=Integer.parseInt(request.getParameter("accNumber"));
		RegisterDaoImpl rdao=new RegisterDaoImpl();
		
		List<Register> lstrecord=rdao.retriveRecord(accNumber);
		PrintWriter pw=response.getWriter();
		if(lstrecord!=null)
		{
			Register robj=lstrecord.get(0);
			pw.println("<a href=\"Home.html\">Home</a>");

			pw.println("<table><tr><td>");
			pw.print(robj.getAccNumber()+"</td><td>"+robj.getAccFname()+"</td><td>"+robj.getAccBal()+"</td><tr>");
			pw.print("</table>");
		}
		else
		{
			response.sendRedirect("Error.html");
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
