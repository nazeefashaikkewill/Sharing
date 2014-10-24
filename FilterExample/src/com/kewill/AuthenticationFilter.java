package com.kewill;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
public class AuthenticationFilter implements Filter {

    /**
     * Default constructor. 
     */
	Connection con;
    public AuthenticationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		
			/*String uname = request.getParameter("t1");
			String pwd = request.getParameter("t2");
			String usrname = null, password = null;
			try {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select * from user");
				if(rs.next()) {
					 usrname = rs.getString(1);
					password = rs.getString(2);				
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(usrname.equals(uname) && password.equals(pwd))
				chain.doFilter(request, response);
			else
				request.getRequestDispatcher("/Error.html").forward(request, response);	*/	
		String uname = request.getParameter("t1");
		String pwd = request.getParameter("t2");
		System.out.println(uname + "" + pwd);
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select age from age where user = '" + uname + "' and pwd = '" + pwd + "'");
			if(rs.next()) {
				request.setAttribute("age", rs.getString(1));
				chain.doFilter(request, response);
			}
			else
				request.getRequestDispatcher("/Error.html").forward(request, response);	
				
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println(fConfig.getFilterName());
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Swapna", "root", "root");
			System.out.println("Connection Established");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
