package com.tilak.www;
import java.sql.*;
import javax.servlet.*;
import java.util.*;
import java.text.*;
import javax.servlet.http.*;
import java.io.*;
public class Enter extends HttpServlet
{
	protected void doGet(HttpServletRequest  req,HttpServletResponse res)throws ServletException,IOException,NullPointerException{
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		//reading input from form
		String comp=req.getParameter("company");
		String model=req.getParameter("model");
		String color=req.getParameter("color");
		String  dt=req.getParameter("date");
		//convert string date to util date
		String p=req.getParameter("price");
		String eng=req.getParameter("engineCapacity");
		String lic=req.getParameter("plateNumber");
		String s=req.getParameter("seatingCapacity");
		int result=0;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","admin");
			String qry="insert into car values(?,?,?,?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(qry);
			System.out.println("4");

			ps.setString(1,comp);
			ps.setString(2,model);
			ps.setString(3,color);
			SimpleDateFormat  sdf=new SimpleDateFormat("dd-mm-yyyy");
		    java.util.Date  udoj=sdf.parse(dt);
		    long ms=udoj.getTime();
		    java.sql.Date sqld=new java.sql.Date(ms);
			ps.setDate(4,sqld);
		    int price=Integer.parseInt(p);
			ps.setInt(5,price);
			ps.setString(6,eng);
			ps.setString(7,lic);
			int seat=Integer.parseInt(s);
			System.out.println("1");
			ps.setInt(8,seat);
		    result=ps.executeUpdate();
            if(result==0){
				System.out.println("2");
				pw.println("<b><i><center><Records not inserted></center></i></b>");
			}
			else{
				System.out.println("3");
				pw.println("<b><i><center><Records inserted></center></i></b>");
			}
		}
		catch(Exception e){
				pw.println("Sorry Exception occured" +e);
				e.printStackTrace();
			
			}
			pw.close();
		}


}