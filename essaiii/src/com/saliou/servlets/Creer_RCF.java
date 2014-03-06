package com.saliou.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saliou.metier.Exports;

public class Creer_RCF extends HttpServlet {
	
public static final String VUE_VIDE = "/WEB-INF/vide.jsp";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		
		
	}
public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
/*	String[] nombres=request.getParameterValues("nombre");
	System.out.println(nombres.length);
	for(int i=0; i<nombres.length; i++) {
		if(nombres[i].equalsIgnoreCase("0")) System.out.println("rien");
		else System.out.println("bon");
	}		*/
	
	// récupération des nombres du context relation
	String nombreR []= request.getParameterValues("nombreR");
	Vector <String>nombreRV=new Vector<String>();
	for(int i=0; i<nombreR.length; i++){
		nombreRV.add(nombreR[i]);
	}
	
	
	// on recupère les objets du context 1
	Vector<String>obj_c1=new Vector<String>();
	ArrayList<String>arro_c1=new ArrayList<String>();
	arro_c1 =(ArrayList<String>) request.getSession().getAttribute("nomsOA");
	obj_c1.addAll(arro_c1);
	
	
	// on recupère les attributs du context 1
	Vector<String>att_c1=new Vector<String>();
	ArrayList<String>arra_c1=new ArrayList<String>();
	arra_c1=(ArrayList<String>) request.getSession().getAttribute("nomsAA");
	att_c1.addAll(arra_c1);
	
	
	// on recupère les objets du context 2
		Vector<String>obj_c2=new Vector<String>();
		ArrayList<String>arro_c2=new ArrayList<String>();
		arro_c2 = (ArrayList<String>) request.getSession().getAttribute("nomsOA2");
		obj_c2.addAll(arro_c2);
		System.out.println("les objets du context 2"+obj_c2);
		
		// on recupère les attributs du context 2
		Vector<String>att_c2=new Vector<String>();
		ArrayList<String>arra_c2=new ArrayList<String>();
		arra_c2 = (ArrayList<String>) request.getSession().getAttribute("nomsAA2");
		att_c2.addAll(arra_c2);
		System.out.println("les attributs du context 2"+att_c2);
		
		//------------------------------------------------------------------
	
	Vector<String>objj=new Vector<String>();
	ArrayList<String>ar=new ArrayList<String>();
	ar=(ArrayList<String>) request.getSession().getAttribute("nomsOA");
	int taille=ar.size();
	String []obj = new String [taille];
    for(int i=0; i<taille; i++){
    	obj[i]=ar.get(i);
    	System.out.println("ceci est un objet nomsOA "+obj[i]);
    }
   objj.addAll(ar);
	
   
   
	Vector<String>objj2=new Vector<String>();

    ArrayList<String>ar1=new ArrayList<String>();
	ar1=(ArrayList<String>) request.getSession().getAttribute("nomsOA2");
	int taille1=ar1.size();
	String []obj1 = new String [taille1];
    for(int i=0; i<taille1;i++){
    	obj1[i]=ar1.get(i);
    }
	objj2.addAll(ar1);
	
	// on récuprère l'arrayList des nombres du premier context présent en session et on le met dans un vecteur
	ArrayList<String>nombreAr=new ArrayList<String>();
	nombreAr=(ArrayList<String>) request.getSession().getAttribute("nombreAr");
	Vector<String> nombreV=new Vector<String>();
	nombreV.addAll(nombreAr);
	
	// on récuprère l'arrayList des nombres du deuxième context présent en session et on le met dans un vecteur
		ArrayList<String>nombreAr2=new ArrayList<String>();
		nombreAr2=(ArrayList<String>) request.getSession().getAttribute("nombreAr2");
		Vector<String> nombreV2=new Vector<String>();
		nombreV2.addAll(nombreAr2);
	
	
	
	Exports exports = new Exports();
	
	try {
		exports.exportRCFFormat(obj_c1,att_c1,obj_c2,att_c2,nombreV,nombreV2,nombreRV);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
//	request.setAttribute("export", exports);
	
	this.getServletContext().getRequestDispatcher(VUE_VIDE).forward(request, response);
	}


}
