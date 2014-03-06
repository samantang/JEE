package com.saliou.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saliiou.beans.Cellule;
import com.saliou.metier.TraiteNombres;

public class Traiter_vue extends HttpServlet {
	
public static final String VUE_TRAITER = "/WEB-INF/vue_traiter.jsp";

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		
	}
public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
	
	// recuperation des nombres pour leur mise en session
		String [] nombresS = request.getParameterValues("nombre");
		// création d'une arraylist pour les mettre les nombres de dans
		ArrayList<String>nombresA = new ArrayList<String>();
		for(int i=0; i<nombresS.length; i++){
			nombresA.add(nombresS[i]);
		}
	
	String nomsA [];
	String nomsO  [];
	nomsA=request.getParameterValues("caseA");
	nomsO=request.getParameterValues("caseO");
	
	String a="attributs_C1";
	String o="objets_C1";
	
	ArrayList<String>nomsAA=new ArrayList<String>();
	for(int i=0; i<nomsA.length;i++){
		
		nomsAA.add(nomsA[i]);
	}
	nomsAA.add(0, a);
	ArrayList<String>nomsOA= new ArrayList<String>();
	for(int i=0; i<nomsO.length; i++){
		
		nomsOA.add(nomsO[i]);
	}
	nomsOA.add(0, o);
	for(int i=0; i<nomsOA.size(); i++) System.out.println(nomsOA.get(i));
	for(int i=0; i<nomsAA.size(); i++) System.out.println(nomsAA.get(i));
	
	// on récupère les nombres et les met dans une ArrayList
	ArrayList<String>nombreAr=new ArrayList<String>();
	String [] nombreT;
	nombreT=request.getParameterValues("nombre");
	for(int i=0; i<nombreT.length; i++){
		nombreAr.add(nombreT[i]);
		
	}
	System.out.println("les nombres dans le nombreAr: "+nombreAr);
	
	// ArrayList d'ArrayList qui va contenir les nombres, objets et attributs et qui sera envoyée à la vue
	ArrayList<ArrayList<Cellule>>formate = new ArrayList<ArrayList<Cellule>>();
	
	// on fait intervenir le metier pour traiter les objets, les attributs et les nombres afin de créer des ArrayList "ligne" 
	// qui vont contenir toutes les lignes que nous allons mettre dans une autre ArrayList "toutesLignes" et qu'on va afficher dans la vue
	TraiteNombres trN = new TraiteNombres();
	formate=trN.Traiter(nomsOA, nomsAA, nombresA);
	
	// creation de la session et insertion des objects
		HttpSession session2 = request.getSession();
		session2.setAttribute("nomsOA", nomsOA);
		session2.setAttribute("nomsAA", nomsAA);
		session2.setAttribute("nombresA", nombresA);
		session2.setAttribute("nombreAr", nombreAr);
		session2.setAttribute("formate", formate);
		
		request.setAttribute("nomsOA", nomsOA);
		request.setAttribute("nomsAA", nomsAA);
		request.setAttribute("formate", formate);
	
	// on oblige l'utilisateur à ne saisir que des 1 ou 0	
	Iterator itr= nombreAr.iterator();
		if(nombreAr.contains("2") || nombreAr.contains("3") || nombreAr.contains("4") || nombreAr.contains("5") || nombreAr.contains("6")
				|| nombreAr.contains("7") || nombreAr.contains("8") || nombreAr.contains("9")){
			this.getServletContext().getRequestDispatcher("/inc/menuM.jsp").forward(request, response);
		}else{
			this.getServletContext().getRequestDispatcher(VUE_TRAITER).forward(request, response);
		}
	}		
	
}
