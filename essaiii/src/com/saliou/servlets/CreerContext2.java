package com.saliou.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreerContext2 extends HttpServlet {
	
public static final String VUE_CONTEXTE2 = "/WEB-INF/vue_context2.jsp";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		
		this.getServletContext().getRequestDispatcher("/inc/menu2.jsp").forward(request, response);
		
	}
public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
	// récupération des nombres d'objets et d'attributs pour le second context
	int objet_oa = Integer.parseInt(request.getParameter("objeta"));
	int attribut_oa=Integer.parseInt(request.getParameter("attributa"));
	
	// creation de la session pour stocker les objets et les attributs
	HttpSession session2=request.getSession();
	session2.setAttribute("lignesa", objet_oa);
	session2.setAttribute("colonnesa", attribut_oa);
	
	// envoie des objets et des attributs à la vue
	request.setAttribute("lignesa", objet_oa);
	request.setAttribute("colonnesa", attribut_oa);
	
	this.getServletContext().getRequestDispatcher(VUE_CONTEXTE2).forward(request, response);
	}


}
