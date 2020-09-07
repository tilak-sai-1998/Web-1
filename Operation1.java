package com.tilak.www;
import java.sql.*;
import javax.servlet.*;
import java.util.*;
import java.text.*;
import javax.servlet.http.*;
import java.io.*;
public class Operation1 extends HttpServlet
{
	protected void doGet(HttpServletRequest  req,HttpServletResponse res)throws ServletException,IOException,NullPointerException{
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		String input=req.getParameter("input");
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","admin");
			Statement st=con.createStatement();
			String mode=null;
			String qry=null;
		     if(input.equalsIgnoreCase("all cars")){
			     mode=req.getParameter("output");
			     qry="select * from car";
				ResultSet rs=st.executeQuery(qry);
				while(rs.next()){
					pw.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getInt(5)+" "+rs.getString(6)+" "+rs.getString(7)+" "+rs.getInt(8));

                }
			 }
			else if(input.equalsIgnoreCase("model")){
				   String opt=req.getParameter("opt");
				    mode=req.getParameter("output");
				      if(opt.equalsIgnoreCase("Select a car")){
						  ResultSet rs=st.executeQuery("select * from car where to_char(Date of purchase,'yyyy')="+mode);
                        while(rs.next()){
					pw.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getInt(5)+" "+rs.getString(6)+" "+rs.getString(7)+" "+rs.getInt(8));
						}
					  }
						else if(opt.equalsIgnoreCase("Delete a car")){
						  int result=st.executeUpdate("delete  from car  where to_char(Date of purchase,'yyyy')="+mode);
						  if(result==0)
							pw.println("No records deleted");
						  else
							pw.println("Records deleted");
						}
						else if(opt.equalsIgnoreCase("Update a car")){
						  int result=st.executeUpdate("update table  car set model="+mode+" where model=='old'");
                          if(result==0)
							pw.println("No records updated");
						  else
							pw.println("Records updated");
						}
					}
			else if(input.equalsIgnoreCase("Year of purchase")){
				   String opt=req.getParameter("opt");
				   mode=req.getParameter("output");
				      if(opt.equalsIgnoreCase("Select a car")){
						  ResultSet rs=st.executeQuery("select * from car where model="+mode);
                        while(rs.next()){
					pw.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getInt(5)+" "+rs.getString(6)+" "+rs.getString(7)+" "+rs.getInt(8));
						}
					}
					else if(opt.equalsIgnoreCase("Delete a car")){
						  int result=st.executeUpdate("delete  from car where model="+mode);
						  if(result==0)
							pw.println("No records deleted");
						  else
							pw.println("Records deleted");
						}
						
			          }
			else if(input.equalsIgnoreCase("color")){
                String opt=req.getParameter("opt");
				   mode=req.getParameter("output");
				      if(opt.equalsIgnoreCase("Select a car")){
						  ResultSet rs=st.executeQuery("select * from car where color="+mode);
                        while(rs.next()){
					pw.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getInt(5)+" "+rs.getString(6)+" "+rs.getString(7)+" "+rs.getInt(8));
						}
					  }
						else if(opt.equalsIgnoreCase("Delete a car")){
						  int result=st.executeUpdate("delete  from car color="+mode);
						  if(result==0)
							pw.println("No records deleted");
						  else
							pw.println("Records deleted");
						}
						else if(opt.equalsIgnoreCase("Update a car")){
						  int result=st.executeUpdate("update table  car set color="+mode+" where color=='green'");
						  if(result==0)
							pw.println("No records updated");
						  else
							pw.println("Records updated");
						}
					}
					else if(input.equalsIgnoreCase("Price Range")){
                String opt=req.getParameter("opt");
				    mode=req.getParameter("output");
				   String[] l=mode.split("-");
				    long p1=Integer.parseInt("l[0]");
					long p2=Integer.parseInt("l[1]");
				      if(opt.equalsIgnoreCase("Select a car")){
						  ResultSet rs=st.executeQuery("select * from car where price between"+p1+
						  "and"+p2);
                        while(rs.next()){
					pw.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getInt(5)+" "+rs.getString(6)+" "+rs.getString(7)+" "+rs.getInt(8));
						}
					  }
						else if(opt.equalsIgnoreCase("Delete a car")){
						  int result=st.executeUpdate("delete  from car where price between"+p1+
						  "and"+p2);
						  if(result==0)
							pw.println("No records deleted");
						  else
							pw.println("Records deleted");
						}
						else if(opt.equalsIgnoreCase("Update a car")){
						  int result=st.executeUpdate("update table  car set price="+p2+" where price<="+p1);
						  if(result==0)
							pw.println("No records updated");
						  else
							pw.println("Records updated");
						}
					}
             }
			   catch(Exception e)
		       {
				   pw.println("Exception occured" +e);
			   }
	}
}



