package bank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.dao.RegisterDao;
import bank.dao.RegisterDaoImpl;
import bank.model.Register;

/**
 * Servlet implementation class DisplayAllController
 */
@WebServlet("/DisplayAllController")
public class DisplayAllController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DisplayAllController() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RegisterDao rdao=new RegisterDaoImpl();
		List<Register> lstallrecord=rdao.displayAll();
		
		PrintWriter pw=response.getWriter();
		
		pw.println("<a href=\"Home.html\">Home</a>");
		pw.println("<div style='text-align: center;'>");
		pw.println("<table style='border: 3px solid black; margin: auto;'>");
		pw.println("<tr>");
		pw.println("<th style='border: 1px solid black; padding: 8px; background-color: lightgray;'>Account Number</th>");
		pw.println("<th style='border: 1px solid black; padding: 8px; background-color: lightgray;'>Account Name</th>");
		pw.println("<th style='border: 1px solid black; padding: 8px; background-color: lightgray;'>Account Balance</th>");
		pw.println("</tr>");

		for(Register robj: lstallrecord) {
		    pw.print("<tr><td style='border: 1px solid black; padding: 8px;'>" + robj.getAccNumber() + "</td><td style='border: 1px solid black; padding: 8px;'>" + robj.getAccFname() + "</td><td style='border: 1px solid black; padding: 8px;'>" + robj.getAccBal() + "</td></tr>");
		}

		pw.println("</table>");
		pw.println("</div>");


	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
