package com.saliiou.beans;

public class Cellule {
	private String valeur;
	
	public Cellule(String valeur) {
		this.valeur=valeur;
	}

	public String getValeur() {
		return valeur;
	}

	@Override
	public String toString() {
		return  valeur;
	}
	

}
