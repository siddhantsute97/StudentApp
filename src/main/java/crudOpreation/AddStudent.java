package crudOpreation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/addlink")
public class AddStudent extends HttpServlet{
	Connection con = null;

	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/1eja7", "root", "sql123");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//fetch the data from html
		String id = req.getParameter("studentid");
		String name = req.getParameter("studentname");
		String stream = req.getParameter("studentstream");
		String dob = (String) req.getParameter("studentdob");
		//parsing
		int sid = Integer.parseInt(id);
		
		PreparedStatement pstmt = null;
		
		String query = "insert into student_info values(?,?,?,?)";
		
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date dob1 = format.parse(dob);
			java.sql.Date bdate = new java.sql.Date(dob1.getDate());
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, sid);
			pstmt.setString(2, name);
			pstmt.setString(3, stream);
			pstmt.setDate(4, bdate);
			
			int count = pstmt.executeUpdate();
			
			PrintWriter pw = resp.getWriter();
			
			pw.print("<h1>"+count+" RECORD INSERT SECCESSFULLY</h1>");
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}
