package com.saliou.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ContextResultat extends HttpServlet {
public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		
		
	}
public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
	
	HttpSession session =request.getSession();
	HttpSession session2 = request.getSession();
	
	request.setAttribute("a",session);
	request.setAttribute("b", session2);
	
	this.getServletContext().getRequestDispatcher("/WEB-INF/contextResultat.jsp").forward(request, response);
	}



}
