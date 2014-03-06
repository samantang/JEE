package com.saliou.metier;


public class NumericMVAttribute extends MVAttribute implements Cloneable{
	
	private double min, max;
	
	/**
	 * Constructeur par défaut
	 * @param theAttId : l'indice de l'attribut
	 */
	public NumericMVAttribute(int theAttId) {
		super(theAttId);
	}

	/**
	 * Constructeur principale
	 * @param theAttId : l'indice de l'attribut
	 * @param value : valeur à affecter
	 */
	public NumericMVAttribute(int theAttId, double value) {
		super(theAttId);
		min = value;
		max = value;
	}

	/**
	 * Constructeur principale
	 * @param theAttId : l'indice de l'attribut
	 * @param minValue : valeur minimale de l'intervalle
	 * @param maxValue : valeur maximale de l'intervalle
	 */
	public NumericMVAttribute(int theAttId, double minValue, double maxValue) {
		super(theAttId);
		min = minValue;
		max = maxValue;
	}
	
	/**
	 * Changer l'intervalle
	 * @param aMin : une valeur minimale
	 * @param aMax : une valeur maximale
	 */
	public void setIntervall(double aMin, double aMax){
		min = aMin;
		max = aMax;
	}
	
	/**
	 * Obtenir la valeur minimale de l'intervalle
	 * @return la valeur minimale
	 */
	public double getMin(){
		return min;
	}
	
	/**
	 * Obtenir la valeur maximale de l'intervalle
	 * @return la valeur maximale
	 */
	public double getMax(){
		return max;
	}
	
	/**
	 * Obtenir les valeurs de l'intervalle de façon chaînée
	 * @return un intervalle (ou une valeur si min == max)
	 */
	public String getValueToString(){
		if (min == max)
			return "" + min;
		else
			return "[" + min + "," + max + "]";
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
