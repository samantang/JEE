package com.saliou.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saliiou.beans.Cellule;
import com.saliou.metier.TraiteNombres;

public class Traiter_vue2 extends HttpServlet {
	
public static final String VUE_TRAITER2 = "/WEB-INF/vue_traiter2.jsp";

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		
	}
public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
	
	
	String nomsA2 [];
	String nomsO2  [];
	nomsA2=request.getParameterValues("caseA2");
	nomsO2=request.getParameterValues("caseO2");
	
	String a="attributs_c_2";
	String o="objets_c_2";
	
	// reccupération des attributs pour les mettre dans une ArrayList
	ArrayList<String>nomsAA2=new ArrayList<String>();
	for(int i=0; i<nomsA2.length;i++){
		nomsAA2.add(nomsA2[i]);
	}
	// ajout du String à l'indice 0 de l'arrayList
	nomsAA2.add(0, a);
	
	// reccupération des objets pour les mettre dans une ArrayList
	ArrayList<String>nomsOA2= new ArrayList<String>();
	for(int i=0; i<nomsO2.length; i++){
		
		nomsOA2.add(nomsO2[i]);
	}
	// ajout du String à l'indice 0 de l'arrayList
	nomsOA2.add(0, o);
	
	
	
	// récupération des nombres, et on les met dans une ArrayList 
		ArrayList<String>nombreAr2=new ArrayList<String>();
		String [] nombreT;
		nombreT=request.getParameterValues("nombre2");
		for(int i=0; i<nombreT.length; i++){
			nombreAr2.add(nombreT[i]);
		}
		System.out.println("les nombres dans le nombreAr2: "+nombreAr2);
		
		
		// ArrayList d'ArrayList qui va contenir les nombres, objets et attributs et qui sera envoyée à la vue
		ArrayList<ArrayList<Cellule>>formate2 = new ArrayList<ArrayList<Cellule>>();
		
		// on fait intervenir le metier 
		TraiteNombres trN = new TraiteNombres();
		formate2=trN.Traiter(nomsOA2, nomsAA2, nombreAr2);
		
		
		// création d'une sessio pour le stockage des objets
		HttpSession session3 = request.getSession();
		session3.setAttribute("nomsOA2", nomsOA2);
		session3.setAttribute("nomsAA2", nomsAA2);
		session3.setAttribute("nombreAr2", nombreAr2);
		session3.setAttribute("formate2", formate2);
		
		request.setAttribute("nomsOA2", nomsOA2);
		request.setAttribute("nomsAA2", nomsAA2);
		request.setAttribute("formate2", formate2);
		
			
	this.getServletContext().getRequestDispatcher(VUE_TRAITER2).forward(request, response);
	}


}
