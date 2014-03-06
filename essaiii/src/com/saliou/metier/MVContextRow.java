package com.saliou.metier;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;


public class MVContextRow {

	/**
	 * The identifier of the object
	 */
	private int object;//indice de la ligne
	
	/**
	 * The attributes linked to the object
	 * Hashtable<attribute_Id, attribute_Value>
	 */
	private Hashtable<Integer,MVAttribute> attributes;//indice de l'attribut et l'attribut lui-même pour la ligne donnée
	
		
	/**
	 * Default constructor : no attribute 
	 * @param obj the object's id
	 */
	public MVContextRow(int obj) {
		super();
		object = obj;
		attributes = new Hashtable<Integer,MVAttribute>();
	}
	
	/**
	 * Main constructor
	 * @param obj the object
	 * @param attr the attributes to link to the object
	 */
	public MVContextRow(int obj, Hashtable attr){
		super();
		object = obj;
		attributes = attr;
	}
	
	/**
	 * Change the object
	 * @param obj the new object
	 */
	public void setObject(int obj){
		object = obj;
	}
	
	/**
	 * Get the object of this context row
	 * @return this row's object
	 */
	public int getObject(){
		return object;
	}
	
	/**
	 * Change  the attributes and consequently the number of crosses
	 * @param attr the new attributes
	 */
	public void setAttributes(Hashtable<Integer,MVAttribute> attr){
		attributes = attr;
	}
	
	/**
	 * Return this row's attributes
	 * @return the attributes
	 */
	public Hashtable<Integer, MVAttribute> getAttributes(){
		return attributes;
	}
	
	/**
	 * Adds a new attribute
	 * @param attr the attribute to add
	 */
	public void addAttribute(int theAttribute, MVAttribute theAttributeValue){
		attributes.put(theAttribute, theAttributeValue);
	}
	
	@Override
	public String toString(){
		String rowString = new String("");
		
		Enumeration attrEnu = attributes.keys();
		while (attrEnu.hasMoreElements()) {
			int aKey = (Integer) attrEnu.nextElement();

			rowString = ("{" + aKey + " : ["
					+ (attributes.get(aKey).toString()) + "]} ")
					.concat(rowString);
		}
		rowString = ("{"+object+"} "+"{ ").concat(rowString.concat("}"));
		return rowString;
	}
	/*
	// cette methode ne peut pas �tre faite ici car on a pas l'information sur le nombre total d'attribut
	//on peut avoir cette info mais ...
	
	public Vector<String> exportAsTableLine(){
		
		Vector<String> aTableLine = new Vector<String>();
		
		return new Vector<String>();
	}
	*/
	
//	public static MVContextRow fromString(int objId, int attrId, String [] values, Class<MVAttribute> attrType){
//		MVContextRow ret = new MVContextRow(objId);
//		
//		Vector<MVAttribute> valuesCollection = new Vector<MVAttribute>();
//		
//		for (int i = 0; i < values.length; i++) {
//			
//			if (attrType.getName().equals("BooleanMVAttribute")) {
//				valuesCollection.add(new BooleanMVAttribute(theAttId, present))
//			}
//			
//			
//		}
//
//		ret.attributes.put(attrId, valuesCollection);
//		
//		return ret;
//	}
	

}
