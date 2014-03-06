package com.saliou.metier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Vector;
import java.util.Enumeration;
import java.util.Hashtable;



public class Exports {

	
	/**
	 * The Context name
	 */
	private String name;
	
	/**
	 * The Context type
	 */
	private String type;//type de la cellule (int, String, boolean...)
	
	private Vector<String> objects_c1;//vecteur d'objets du context 1
	
	
	private Vector<String> attributes_c1;//vecteur d'attributs du context 1
	
	private Vector<String> objects_c2; //vecteur d'attributs du context 2
	
	private Vector<String>attributes_c2; //vecteur d'attributs du context 2
	
	private Vector<String>nombreV; // les nombres du context 1
	private Vector<String>nombreV2; // les nombres du context 2
	
	private Vector<String>nombreRV; // les nombres du context relation
	
	
	public Vector<String> getNombreRV() {
		return nombreRV;
	}

	public void setNombreRV(Vector<String> nombreRV) {
		this.nombreRV = nombreRV;
	}

	public Vector<String> getAttributes_c1() {
		return attributes_c1;
	}

	public Vector<String> getObjects_c2() {
		return objects_c2;
	}

	public Vector<String> getAttributes_c2() {
		return attributes_c2;
	}

	public Vector<String> getNombreV() {
		return nombreV;
	}

	public Vector<String> getNombreV2() {
		return nombreV2;
	}

	public void setNombreV2(Vector<String> nombreV2) {
		this.nombreV2 = nombreV2;
	}

	public void setNombreV(Vector<String> nombreV) {
		this.nombreV = nombreV;
	}

	public void setObjects_c2(Vector<String> objects_c2) {
		this.objects_c2 = objects_c2;
	}

	public void setAttributes_c2(Vector<String> attributes_c2) {
		this.attributes_c2 = attributes_c2;
	}

	public void setAttributes_c1(Vector<String> attributes_c1) {
		this.attributes_c1 = attributes_c1;
	}





	/**
	 * The similarity thresholds of the attributes
	 */
	private Hashtable<String,Double> similarity_thesholds;//table de hachage des similarit√©s (= contraintes)
	
	/**
	 * The threshold mode : == 1 : general (one threshold for all attributes)  
	 *                      == 2 : specific (one specific threshold for each attribute)
	 */
	private int thresholdMode;
	
	/**
	 * The rows of this Context (link attributes to objects)
	 */
	private Vector<MVContextRow> rows;//vecteur qui contient toutes les lignes (compos√©es des valeurs de chaque attribut pour chaque ligne)
	
	private long time;
		
	/**
	 * Default constructor
	 */
	public Exports() {
		name = "Many Valued context";
		type = "General";
		objects_c1 = new Vector<String>();
		attributes_c1 = new Vector<String>();
		objects_c2 = new Vector<String>();
		attributes_c2 = new Vector<String>();
		rows = new Vector<MVContextRow>();
		similarity_thesholds = new Hashtable<String,Double>();
	}
	
	/**
	 * 
	 * @return
	 */
	public Vector<String> getObjects_c1() {
		return objects_c1;
	}

	/**
	 * 
	 * @param objects
	 */
	public void setObjects_c1(Vector<String> objects) {
		this.objects_c1 = objects;
	}

	/**
	 * Main constructor
	 * @param ContextName the name of the context
	 */
	public Exports(String ContextName, String ContextType) {
		name = ContextName;
		type = ContextType;
		objects_c1 = new Vector<String>();
		attributes_c1 = new Vector<String>();
		rows = new Vector<MVContextRow>();
		similarity_thesholds = new Hashtable<String,Double>();
	}
	
	/**
	 * Main constructor
	 * @param ContextName the name of the context
	 */
/*	public Exports(String ContextName, String ContextType, Vector<String> Objects, Vector<String> Attributes, Vector<MVContextRow> Rows) {
		name = ContextName;
		type = ContextType;
		objects_c1 = Objects;
		attributes_c1 = Attributes;
		rows = Rows;
		similarity_thesholds = new Hashtable<String,Double>();
	}
	
	/**
	 * 
	 * @param aName
	 */
	public void setName(String aName){
		this.name = aName;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * 
	 * @param time
	 */
	public void setTime(long time) {
		this.time = time;
	}
	
	/**
	 * 
	 * @return
	 */
	public long getTime(){
		return time;
	}
	
	/**
	 * 
	 * @return
	 */
	public Vector<MVContextRow> getRows(){
		return rows;
	}
	
	/**
	 * 
	 * @return
	 */
	public Vector<String> getAttributes(){
		return this.attributes_c1;
	}
	
	/**
	 * 
	 */
	public void printMVContextRows(){
		Enumeration<MVContextRow> RowsEnu = rows.elements();
		while(RowsEnu.hasMoreElements()){
			System.out.println((RowsEnu.nextElement()).toString());
		}
	}
	
	
	public int getThresholdMode(){
		return thresholdMode;
	}
	
	/**
	 * 
	 * @param mode
	 */
	
	public void exportXMLFormat(){
		//TODO exporter un contexte dans un format xml connu
	}
	
	/**
	 * 
	 */
	public void exportEXCELFormat(){
		//TODO exporter un contexte dans un fichier excel format xml connu
	}
	
	
	/***
	 * Exports the MV Context as RCF file : input format of Galicia
	 * @param att_c2 
	 * @param obj_c2 
	 * @param nombreV 
	 * @param nombreV2 
	 * @param nombreRV 
	 * @throws IOException 
	 */
	@SuppressWarnings("unused")
	// methode pour exporter un fichier au format choisi (RCF, ou TEXT)
	public void exportRCFFormat(Vector<String> obj_c1, Vector<String> att_c1, Vector<String> obj_c2, Vector<String> att_c2, Vector<String> nombreV, Vector<String> nombreV2, Vector<String> nombreRV) throws IOException{
		System.out.println("debut de l'export en format RCF ....");
		String outputFile ="C:\\Users\\SALIOU\\Desktop\\enregistre.txt";
		FileOutputStream fout;
		PrintStream ps;
		fout = new FileOutputStream(new File(outputFile));
		ps = new PrintStream(fout);
		setObjects_c1(obj_c1);
		setAttributes_c1(att_c1);
		setObjects_c2(obj_c2);
		setAttributes_c2(att_c2);
		setNombreV(nombreV);
		setNombreV2(nombreV2);
		setNombreRV(nombreRV);
		System.out.println("taille obj_c1 "+obj_c1.size());
		System.out.println("taille att_c1 "+att_c1.size());
		System.out.println("taille obj_c2 "+obj_c2.size());
		System.out.println("taille att_c2 "+att_c2.size());
		System.out.println("taille nombreV "+nombreV.size());
		System.out.println("taille nombreV2 "+nombreV2.size());
		System.out.println("taille nombreRV "+nombreRV.size());
		// l'entÍte du fichier
		ps.println("[Relational Context]"); 
		ps.println(this.name);
		ps.println("[Binary Relation]"); 
		ps.println(this.name);
		
		
		// saisie des objets du context 1
		ps.println("C1");
		String ObjLine = new String("");
		for (Iterator iterator = this.objects_c1.iterator(); iterator.hasNext();) {
			ObjLine = ObjLine.concat((String) iterator.next());
			ObjLine = ObjLine.concat(" | ");
		}
		ps.println(ObjLine);
		
		// saisie des attributs du context 1
		String AttrLine = new String("");
		for (Iterator iterator = this.attributes_c1.iterator(); iterator.hasNext();) {
			AttrLine = AttrLine.concat((String) iterator.next());
			AttrLine = AttrLine.concat(" | ");
		}
		ps.println(AttrLine);
		
		// representation du context 1
		formate( obj_c1, att_c1, nombreV,ps );
		
		
		// saisie des objets du context 2
				ps.println("C2");
				String ObjLine_2 = new String("");
				for (Iterator iterator = this.objects_c2.iterator(); iterator.hasNext();) {
					ObjLine_2 = ObjLine_2.concat((String) iterator.next());
					ObjLine_2 = ObjLine_2.concat(" | ");
				}
				ps.println(ObjLine_2);
				
				// saisie des attributs du context 2
				String AttrLine_2 = new String("");
				for (Iterator iterator = this.attributes_c2.iterator(); iterator.hasNext();) {
					AttrLine_2 = AttrLine_2.concat((String) iterator.next());
					AttrLine_2 = AttrLine_2.concat(" | ");
				}
				ps.println(AttrLine_2);
				
				// representation du context 2
				formate( obj_c2, att_c2, nombreV2,ps );
		
		// le context relation ßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßßß
				ps.println("[le context relation]");
				ps.println();
				
				// saisie des objets du context 1
				
				String ObjLineR = new String("");
				for (Iterator iterator = this.objects_c1.iterator(); iterator.hasNext();) {
					ObjLineR = ObjLineR.concat((String) iterator.next());
					ObjLineR = ObjLineR.concat(" | ");
				}
				ps.println(ObjLineR);
				
				// saisie des objets du context 2
				String ObjLine_2R = new String("");
				for (Iterator iterator = this.objects_c2.iterator(); iterator.hasNext();) {
					ObjLine_2R = ObjLine_2R.concat((String) iterator.next());
					ObjLine_2R = ObjLine_2R.concat(" | ");
				}
				ps.println(ObjLine_2R);
				
				// saisie des nombres du context relation
				formate( obj_c1, obj_c2, nombreRV,ps );
				
				
/*	
		// prints incidence matrix
		for (Iterator iterator = this.rows.iterator(); iterator.hasNext();) {
			MVContextRow row = (MVContextRow) iterator.next();
			
			for (int i = 0; i < this.attributes.size(); i++) {
				//this.attributes.get(i)
				MVAttribute attr = row.getAttributes().get(i);
				if (attr != null) {
					ps.print("1 ");
				} else {
					ps.print("0 ");
				}
			}
			ps.println();
		}		*/
		
		ps.println("[END Relational Context]");
		System.out.println("........ fin de l'export en format RCF");
		
		fout.flush();
		fout.close();
	}
// methode gÈnÈraliste qu'on appelle pour les trois contexts
	private void formate(Vector<String> obj, Vector<String> att, Vector<String> nombre, PrintStream ps) {
		int coef=1;
		int indiceDep=0;
			for(int i=0; i<obj.size()-1; i++){
				String n="";
				for(int j=indiceDep; j<(att.size()-1)*coef; j++){
					n=n.concat(nombre.get(indiceDep))+" ";
					indiceDep++;
				}
				ps.println(n);
				coef++;
			}
		
	}

}
