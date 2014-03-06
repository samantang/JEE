package com.saliou.metier;

import java.util.ArrayList;

import com.saliiou.beans.Cellule;

public class TraiteNombres {
	private ArrayList<String>nomsOA;
	private ArrayList<String>nomsAA;
	private ArrayList<String>nombresA;
	
	
	// ArrayList d'ArrayList qui va contenir toutes les lignes
	ArrayList<ArrayList<Cellule>>toutesLignes = new ArrayList<ArrayList<Cellule>>();
	

	// création de la méthode chargée de traiter les objets, attributs et les nombres des contexts
	public ArrayList Traiter(ArrayList<String> obj, ArrayList<String> att,ArrayList<String> nombres) {
		// arrayList pour la première ligne
		ArrayList<Cellule>ligne0 = new ArrayList<Cellule>();
		ligne0.add(new Cellule(" "));
			for(int i=1; i<att.size(); i++){
				ligne0.add(new Cellule(att.get(i)));
			}
			toutesLignes.add(ligne0);		
			
			//  boucles pour les lignes suivantes
			int dep=0;
			int coef=1;
			for(int i=1; i<obj.size(); i++){
				ArrayList<Cellule>ligne=new ArrayList<Cellule>();
				ligne.add(new Cellule(obj.get(i)));
				for(int j=dep; j<(att.size()-1)*coef; j++){
					ligne.add(new Cellule(nombres.get(j)));
					dep++;
				}
				toutesLignes.add(ligne);
				coef++;
				System.out.println(" TOUTES LIGNES "+toutesLignes);
			}
		return toutesLignes;
		
	}
	


}
