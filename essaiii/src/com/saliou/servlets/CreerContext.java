package com.saliou.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreerContext extends HttpServlet {
	
public static final String VUE_CONTEXTE = "/WEB-INF/vue_context.jsp";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
	}
public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
	// récupération des nombres d'objets et d'attributs saisis par l'utilisateur
	int objet_o = Integer.parseInt(request.getParameter("objet"));
	int attribut_o=Integer.parseInt(request.getParameter("attribut"));
	
	// creation de la session pour stocker le nombre de lignes et colonnes
	HttpSession session=request.getSession();
	session.setAttribute("lignes", objet_o);
	session.setAttribute("colonnes", attribut_o);
	
	// envoie des nombres de lignes et de colonnes à la  vue
	request.setAttribute("lignes", objet_o);
	request.setAttribute("colonnes", attribut_o);
	
	this.getServletContext().getRequestDispatcher(VUE_CONTEXTE).forward(request, response);
	}

}
