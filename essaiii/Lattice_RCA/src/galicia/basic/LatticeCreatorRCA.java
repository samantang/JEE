package galicia.basic;

//import java.io.File;
//import galicia.*;
import galicia.algorithms.RCA.multiFCA;
import galicia.core.IoKernel;
import galicia.core.Kernel;
import galicia.core.Notifier;
import galicia.core.Resource;
import galicia.core.TaskControler;
import galicia.core.UserSession;
import galicia.io.AbstractReader;
import galicia.io.AbstractWriter;
import galicia.io.XMLGaliciaReader;
import galicia.ui.miniGui.miniGui;
import galicia.util.conceptualStructures.Concept;
import galicia.util.conceptualStructures.DefaultFormalAttribute;
import galicia.util.conceptualStructures.DefaultFormalObject;
import galicia.util.conceptualStructures.Extent;
import galicia.util.conceptualStructures.FormalAttribute;
import galicia.util.conceptualStructures.FormalObject;
import galicia.util.conceptualStructures.Intent;
import galicia.util.orderedStructures.BottomUpIterator;
import galicia.util.orderedStructures.ConceptualJoinSemiLattice;
import galicia.util.orderedStructures.ConceptualPartialyOrderedSet;
import galicia.util.orderedStructures.OrderedNode;
import galicia.util.relationalStructures.AbstractContext;
import galicia.util.relationalStructures.BinaryContext;
import galicia.util.relationalStructures.InterContextObjectRelations;
import galicia.util.relationalStructures.RelationalContextFamily;
import galicia.util.relationalStructures.RelationalLatticeFamily;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;

public class LatticeCreatorRCA implements Notifier{



	/**
	 * @param args
	 */
	static Hashtable<Integer,String> arretesAttributesTable,arretesObjectsTable;
	static Hashtable<Integer,String> decretsAttributesTable,decretsObjectsTable;
	static Vector<Hashtable> allRelations;
	
	
	
//	Kernel myrunningKernel;

	public LatticeCreatorRCA(){
		Kernel.startKernel();
		miniGui.start();
	}
	
	
	/**
	 * Notifier management, there is only one Notifier in this GUI
	 */
	public void notifyMessage(String theMessage) {
		//System.out.println("coucou");
	}

	/**
	 * Notifier management, there is only one Notifier in this GUI
	 */
	public void endTask(){
		//System.out.println("coucou coucou");
	}
	
	@Override
	public void setTaskControler(TaskControler theTaskControler) {
		//System.out.println("coucou coucou coucou");
		
	}
	
    public static void main(String[] args) throws Exception{


        LatticeCreatorRCA lcrca = new LatticeCreatorRCA();

        //UserSession n = new miniGui().getTheSession()
        UserSession mySession = UserSession.connect(null, lcrca);

        /** Get RLF resource**/
        Resource r = mySession.getResource(3);
        System.out.println("comment resource : " + r.getComment());
        System.out.println("type name resource : " + r.getDataTypeName());
        System.out.println("ID resource : " + r.getID());
        System.out.println("path resource : " + r.getPath());


        System.out.println("here");
        String rlfFilePath = r.getPath();
        System.out.println("here here");
        File rlfFile = new File(rlfFilePath);
        System.out.println("here here here");

        AbstractReader reader = null;
        reader=new XMLGaliciaReader(new FileInputStream(rlfFile));
        ((XMLGaliciaReader)reader).setExpectedDataType(IoKernel.RLF_DATA);
        reader.setMuted();
        reader.read();

        RelationalLatticeFamily rlf = (RelationalLatticeFamily) reader.getResult();

        System.out.println("the name of the rlf is : " + rlf.getNameId());

        String latticeNameID;
        Concept top;
        for(Enumeration allStructureNameId = rlf.getAllStructureNameId(); allStructureNameId.hasMoreElements();){
            latticeNameID = (String)allStructureNameId.nextElement();
            System.out.println("the name of the structure is : " + latticeNameID);
            ConceptualPartialyOrderedSet aConceptualStructure =  rlf.getConceptualStructure(latticeNameID);
            //Enumeration<ConceptualPartialyOrderedSet> elementsOrderedSet = aConceptualStructure.;

            BottomUpIterator bottomUpLatticeIt = aConceptualStructure.getBottomUpIterator();
            while(bottomUpLatticeIt.hasNext()){
                OrderedNode latticeOrderedNode = bottomUpLatticeIt.next();
                System.out.println("Ordered node nb "+latticeOrderedNode.getRankNumber()+" is "+latticeOrderedNode.toString());

                top=(Concept)((ConceptualJoinSemiLattice)aConceptualStructure).getTop();
                Concept aConcept = (Concept)(latticeOrderedNode);
                Extent extent = aConcept.getExtent();
                Intent intent = aConcept.getIntent();
                System.out.println("Concept number "+latticeOrderedNode.toString()+"\nExtent "+extent.toString()+"\nIntent "+intent.toString());
            }
            System.out.println("You should have "+aConceptualStructure.getAbsoluteNumberOfAddedNodes()+ "nodes");

        }//end for RLF



        /** Get RCF resource**/
        Resource r2 = mySession.getResource(2);
        System.out.println("comment resource : " + r2.getComment());
        System.out.println("type name resource : " + r2.getDataTypeName());
        System.out.println("ID resource : " + r2.getID());
        System.out.println("path resource : " + r2.getPath());


        System.out.println("here");
        String rcfFilePath = r2.getPath();
        System.out.println("here here");
        File rcfFile = new File(rcfFilePath);
        System.out.println("here here here");

        AbstractReader reader2 = null;
        reader2=new XMLGaliciaReader(new FileInputStream(rcfFile));
        ((XMLGaliciaReader)reader2).setExpectedDataType(IoKernel.RCF_DATA);
        reader2.setMuted();
        reader2.read();

        final RelationalContextFamily rcf = (RelationalContextFamily) reader2.getResult();
        
        
        
        //AbstractReader reader3 = null;
        //reader3=new XMLGaliciaReader(new FileInputStream(rcfFile));
        //((XMLGaliciaReader)reader3).setExpectedDataType(IoKernel.RCF_DATA);
        //reader3.setMuted();
        //reader3.read();
        //final RelationalContextFamily rcfQuery = (RelationalContextFamily) reader3.getResult();
        final RelationalContextFamily rcfQuery = new RelationalContextFamily("QUERY_Relational_Context_Family");

        System.out.println("the name of the rcf is : " + rcf.getNameId());

        String contextNameID;
        for(Enumeration allContextNameId = rcf.getAllContextNameId(); allContextNameId.hasMoreElements();){           
            contextNameID = (String)allContextNameId.nextElement();
            System.out.println("the name of the context is : " + contextNameID);
            AbstractContext aContext =  rcf.getContext(contextNameID);
           
          //ici on n'a que deux contextes "arr" et "dec" mais ce passage doit être générique pour tous les contextNameID
            if(contextNameID.contains("arr")){
	            arretesObjectsTable = new Hashtable<Integer, String>();
	            int objectNumber = aContext.getObjectNumber();
	            for(int j=0;j<objectNumber;j++){
	                FormalObject anObject = aContext.getObject(j);
	                arretesObjectsTable.put(j, anObject.toString());
	                System.out.println("object "+j+" :"+anObject);
	            }
	           
	            arretesAttributesTable = new Hashtable<Integer, String>();
	            int attributeNumber = aContext.getAttributeNumber();
	            for(int i=0;i<attributeNumber;i++){
	                FormalAttribute anAttribute = aContext.getAttribute(i);
	                arretesAttributesTable.put(i, anAttribute.toString());
	                System.out.println("attribute "+i+" :"+anAttribute);
	            }
            }
            else if(contextNameID.contains("dec")){
	            	 decretsObjectsTable = new Hashtable<Integer, String>();
	 	            int objectNumber = aContext.getObjectNumber();
	 	            for(int j=0;j<objectNumber;j++){
	 	                FormalObject anObject = aContext.getObject(j);
	 	               decretsObjectsTable.put(j, anObject.toString());
	 	                System.out.println("object "+j+" :"+anObject);
	 	            }
	 	           
	 	            decretsAttributesTable = new Hashtable<Integer, String>();
	 	            int attributeNumber = aContext.getAttributeNumber();
	 	            for(int i=0;i<attributeNumber;i++){
	 	                FormalAttribute anAttribute = aContext.getAttribute(i);
	 	               decretsAttributesTable.put(i, anAttribute.toString());
	 	                System.out.println("attribute "+i+" :"+anAttribute);
	 	            }
	             }
   
        }//end for RCF
       
        
        /** Get relational contexts*/ 
        
		  String interCtxRelationNameID;
		  Hashtable<FormalObject,Vector<FormalObject>> aRelationHashtable;
		  Vector<FormalObject> objectRelationsVector;
		  
		  allRelations = new Vector<Hashtable>();
		  
	     for(Enumeration allInterCtxRelationNameID = rcf.getAllInterCtxRelationNameId(); allInterCtxRelationNameID.hasMoreElements();){
            interCtxRelationNameID = (String)allInterCtxRelationNameID.nextElement();
            System.out.println("the name of the inter context relation is: "+interCtxRelationNameID);
            //AbstractContext aRelationContext = rcf.getContext(interCtxRelationNameID);
	           
            InterContextObjectRelations interCtxRelation = rcf.getInterCtxRelation(interCtxRelationNameID);

            int relationSourceObjectsNumber = interCtxRelation.getSource().getObjectNumber();
            
            aRelationHashtable = new Hashtable<FormalObject,Vector<FormalObject>>();           
           for(int i=0;i<relationSourceObjectsNumber;i++){
        	   
        	   objectRelationsVector = new Vector<FormalObject>();
        	   
        	   FormalObject aRelationSourceObject = interCtxRelation.getSource().getObject(i);        	   
                    
        	   Extent objectRelations = interCtxRelation.getObjectRelations(aRelationSourceObject);
        	   
        	   for(Iterator objectRelationsIterator = objectRelations.iterator(); objectRelationsIterator.hasNext();){
        		   objectRelationsVector.add((FormalObject) objectRelationsIterator.next());
        	   }
        	   
        	   aRelationHashtable.put(aRelationSourceObject, objectRelationsVector);                   
        	   
        	   System.out.println("relation between "+ aRelationSourceObject+ " and "+objectRelations);
            }
           allRelations.add(aRelationHashtable);
        } 
	     System.out.println(allRelations.toString());
            
            
                       
            //1-move these lines into one or more new methods which return the objects, the attributes and the relations
            //2-create a new class which uses these methods to create a user interface, in which the user could select
            // among all the attributes, the objects and relations which will be used to create his query
            //3-insert the query extension and intension into the original contexts, run again to show lattices and
            // fetch then return the concepts containing the query virtual object 
        
       
       
       
       
       
/**Query**/
        System.out.println("Please choose your query among the following elements:");
       
        for(Enumeration arretesAttributes = arretesAttributesTable.elements(); arretesAttributes.hasMoreElements();)
            System.out.println(arretesAttributes.nextElement().toString());
        for(Enumeration decretsAttributes = decretsAttributesTable.elements(); decretsAttributes.hasMoreElements();)
            System.out.println(decretsAttributes.nextElement().toString());
        //for(int l=0;l<allRelations.size();l++)
        	//System.out.println(allRelations.elementAt(l));
        System.out.println("ref");
       
       
       
		 Collection<String> arretesListData = arretesAttributesTable.values();
		 Collection<String> decretsListData = decretsAttributesTable.values();
		 //allRelations has only one element since our example contains only one relation
		 /**get only the name of the relation*///Collection<String> relationsListData =   allRelations.elementAt(0).values();
		 String[] relationsListData = {"ref"}; 
		 
		    final JList arretesList,decretsList,referenceList;
		    //JLabel etiquette = new JLabel("                   ");

		    //construction des conteneurs pour l'interface requête
		    arretesList = new JList(arretesListData.toArray());
		    JPanel arretesPanel = new JPanel(); arretesPanel.add(arretesList); 
		    arretesPanel.setBorder(BorderFactory.createTitledBorder("Arretes attributes" ));
		    arretesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		    
		    decretsList = new JList(decretsListData.toArray());
		    JPanel decretsPanel = new JPanel(); decretsPanel.add(decretsList); 
		    decretsPanel.setBorder(BorderFactory.createTitledBorder("Decrets attributes" ));
		    decretsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		    
		    referenceList = new JList(relationsListData);
		    JPanel referencePanel = new JPanel(); referencePanel.add(referenceList); 
		    referencePanel.setBorder(BorderFactory.createTitledBorder("Relation: has-got-reference-to" ));
		    referenceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		    
		    JPanel allListsPanel = new JPanel();
		    allListsPanel.setLayout(new GridLayout(1,3));
		    allListsPanel.add(arretesPanel);
		    allListsPanel.add(decretsPanel);
		    allListsPanel.add(referencePanel);
		    
		    
		    
		  //We should use "BinaryContext" instead of "AbstractContext" to add binary relations (object x attributes) 
      	  final BinaryContext arretesBinaryContext =  (BinaryContext)rcf.getContext("arretes");	
      	  
		  arretesList.addListSelectionListener(new ListSelectionListener() {
		          public void valueChanged(ListSelectionEvent e) {
		        	  
		        	 
		        	  //Create the virtual query formal object for context arretes
		        	  //**FormalObject queryVirtualObjectArretes = (FormalObject) new Object();
		        	  //**queryVirtualObjectArretes.setName("Qvoa");
		        	  //Add the virtual query formal object to the context arretes 
		        	  //**arretesBinaryContext.addObject(queryVirtualObjectArretes);
		        	  //Create the virtual query object for context arretes using the method createNewObject of BinaryContext
		        	  int indexQueryObj = arretesBinaryContext.createNewObject();
		        	  arretesBinaryContext.getObject(indexQueryObj).setName("Qvoa");
		        	  System.out.println("heeeeeeeeeeeeeeere action arretesList: the query index = "+indexQueryObj);
		        	  // Create the list of attributes of the Qvoa with the selected items from the JList 
		        	  FormalAttribute aQueryAttributeArretes;
		        	  int[] selectedIx = arretesList.getSelectedIndices();
		        	  // Get all the selected items using the indices
		   		      for (int i=0; i<selectedIx.length; i++) {
		   		    	  Object sel = arretesList.getModel().getElementAt(selectedIx[i]);
		   		    	  aQueryAttributeArretes = new DefaultFormalAttribute(sel.toString());
		   		    	  //We don't add the attribute to the context, it is already there
		   		    	  //**arretesBinaryContext.addAttribute(aQueryAttributeArretes);
		   		    	  int indexQueryAtt = arretesBinaryContext.getAttributeIndex(aQueryAttributeArretes);
		   		    	  arretesBinaryContext.setRelation(indexQueryObj, indexQueryAtt, true);
		   		      }
		   		      // When some items have been selected in the list of arretes attributes
		   		      //1-Remove the old context
		   		      //rcfQuery.removeContext((BinaryContext)rcfQuery.getContext("arretes"));
		   		      //2-Add the new context to which the query object Qvoa has been added
		   		      rcfQuery.addContext(arretesBinaryContext);
		          }
		        });
		    
		    // The same for decrets
    	  //We should use "BinaryContext" instead of "AbstractContext" to add binary relations (object x attributes) 
    	  final BinaryContext decretsBinaryContext =  (BinaryContext)rcf.getContext("decrets");	
    	  /**
    	   * rcf.removeContext and rcf.removeInterContextRelation then recf.addContext and rcf.addInterContextRelation
    	   */
		    decretsList.addListSelectionListener(new ListSelectionListener() {
		          public void valueChanged(ListSelectionEvent e) {
		        	

		        	  //Create the virtual query formal object for context decrets
		        	  //**FormalObject queryVirtualObjectDecrets = (FormalObject) new Object();
		        	  //**queryVirtualObjectDecrets.setName("Qvod");
		        	  //Add the virtual query formal object to the context decrets 
		        	  //**decretsBinaryContext.addObject(queryVirtualObjectDecrets);
		        	  //Create the virtual query object for context decrets using the method createNewObject of BinaryContext
		        	  int indexQueryObj = decretsBinaryContext.createNewObject();
		        	  decretsBinaryContext.getObject(indexQueryObj).setName("Qvod");
		        	  System.out.println("heeeeeeeeeeeeeeere action decretsList: the query index = "+indexQueryObj);
		        	  // Create the list of attributes of the Qvod with the selected items from the JList 
		        	  FormalAttribute aQueryAttributeDecrets;
		        	  int[] selectedIx = decretsList.getSelectedIndices();
		        	  // Get all the selected items using the indices
		   		      for (int i=0; i<selectedIx.length; i++) {
		   		    	  Object sel = decretsList.getModel().getElementAt(selectedIx[i]);
		   		    	  aQueryAttributeDecrets = new DefaultFormalAttribute(sel.toString());
		   		    	  //We don't add the attribute to the context, it is already there
		   		    	  //**decretsBinaryContext.addAttribute(aQueryAttributeDecrets);
		   		    	  int indexQueryAtt = decretsBinaryContext.getAttributeIndex(aQueryAttributeDecrets);
		   		    	  decretsBinaryContext.setRelation(indexQueryObj, indexQueryAtt, true);
		   		      }
		   		      // When some items have been selected in the list of decrets attributes
		   		      //1-Remove the old context
		   		      //rcfQuery.removeContext((BinaryContext)rcfQuery.getContext("decrets"));
		   		      //2-Add the new context to which the query object Qvod has been added
		   		      rcfQuery.addContext(decretsBinaryContext);
		          }
		    });
    
		    // Mise à jour de la relation avec ajout Qvoa en ligne, Qvod en colonne et true dans la case (Qvoa,Qvod)
		    referenceList.addListSelectionListener(new ListSelectionListener() {
		          public void valueChanged(ListSelectionEvent e) {
		        	  
		        	  //if we have many relations selected, we use the code hereafter; but in our specific case, we have only one relation
		        	  //String  interCtxRelationNameID = null;
		        	  //int[] selectedIx = referenceList.getSelectedIndices();
			        	// Get all the selected items using the indices
			   		    //for (int i=0; i<selectedIx.length; i++) {
			   		     //Object sel = referenceList.getModel().getElementAt(selectedIx[i]);
			   		     //String selectedRelation = sel.toString();
			   		     //if(selectedRelation.equals("ref"))  interCtxRelationNameID=selectedRelation;
          		  		//}
			   		   
		        	  int selectedIndex = referenceList.getSelectedIndex();
			   		    String interCtxRelationNameID = (String) referenceList.getModel().getElementAt(selectedIndex);
		        	  
		        	  //Rq. in case we have many selected relations, we add two QueryVirtualObject each corresponding to a specific
		        	  //type (arretes, decrets, etc.) to the corresponding InterContextObjectRelation defining the relation between these 
		        	  //two specific types (or objects)
		        	  
		        	 
		        	 //System.out.println("RCF nb ctx "+rcf.getNumberOfContext()+" -- RCF nb relation "+rcf.getNumberOfRelation()+" -- RCF name relation "+rcf.getAllInterCtxRelationNameId().toString());
		        	 InterContextObjectRelations refInterCtxRelation = rcf.getInterCtxRelation(interCtxRelationNameID);
		        	  System.out.println("The value of the refInterCrxRelation : "+refInterCtxRelation.toString());
			     	    System.out.println("The value of the arretesBinaryContext : "+arretesBinaryContext.toString());
			     	   System.out.println("The value of the decretsBinaryContext : "+decretsBinaryContext.toString());
		     	     //for(Enumeration allInterCtxRelationNameID = rcf.getAllInterCtxRelationNameId(); allInterCtxRelationNameID.hasMoreElements();){
		                 //interCtxRelationNameID = (String)allInterCtxRelationNameID.nextElement();
		                 
		                 //if(interCtxRelationNameID.equals("ref")){
		                	 //System.out.println("heeeeeeeeeeeeeeere the name of the inter context relation is: "+interCtxRelationNameID);
		                	 //refInterCtxRelation = rcf.getInterCtxRelation(interCtxRelationNameID);//referenceList.getModel().getElementAt(0).toString());  
		     	     		//}
		     	     //}
		     	     
		     	     System.out.println("The value of the refInterCrxRelation : "+refInterCtxRelation.toString());
		     	    System.out.println("Object number of the arretesBinaryContext : "+arretesBinaryContext.getObjectNumber()+" "+arretesBinaryContext.getObjectIndex(new DefaultFormalObject("Qvoa")));
		     	   System.out.println("Object number of decretsBinaryContext : "+decretsBinaryContext.getObjectNumber()+" "+decretsBinaryContext.getObjectIndex(new DefaultFormalObject("Qvod")));
		     	   
		     	  
		     	   
		     	   InterContextObjectRelations refInterCtxRelationNew = new InterContextObjectRelations("rf",refInterCtxRelation.getSource(),refInterCtxRelation.getDestination());
		        	  //refInterCtxRelationNew.setSource(refInterCtxRelation.getSource());
		        	  //refInterCtxRelationNew.setDestination(refInterCtxRelation.getDestination());
		        	  System.out.println("Destination of the refInterCtxRelationNew: "+refInterCtxRelationNew.getDestination().getName()+" object nb"+refInterCtxRelationNew.getDestination().getObjectNumber());
		        	  System.out.println("Source of the refInterCtxRelationNew : "+refInterCtxRelationNew.getSource().getName()+" object nb"+refInterCtxRelationNew.getSource().getObjectNumber());


		        	  Extent sourceObjExtent = null;
		        	  FormalObject destinationObj = null;
		        	  //for(int i=0;i<refInterCtxRelationNew.getSource().getObjectNumber();i++){
		        	  for(int i=0;i<4;i++){
		        		  sourceObjExtent = refInterCtxRelation.getObjectRelations(i);
		        		  Iterator it = sourceObjExtent.iterator();
		        		  while(it.hasNext())
		        		  {
		        			destinationObj = (FormalObject)it.next();
		        			int indexDestinationObj = refInterCtxRelation.getDestination().getObjectIndex(destinationObj);
		        			refInterCtxRelationNew.addObjectRelation(i, indexDestinationObj);
		        		  }
		        	  }
		        	  int indexQueryVirtualObjectArretes = refInterCtxRelation.getSource().getObjectIndex(new DefaultFormalObject("Qvoa"));
		        	  int indexQueryVirtualObjectDecrets = refInterCtxRelation.getDestination().getObjectIndex(new DefaultFormalObject("Qvod"));
		        	  refInterCtxRelationNew.addObjectRelation(indexQueryVirtualObjectArretes , indexQueryVirtualObjectDecrets);

		        	  //refInterCtxRelationNew.addObjectRelation(new DefaultFormalObject("Qvoa") , new DefaultFormalObject("Qvod"));
		        	  //refInterCtxRelation.addObjectRelation(arretesBinaryContext.getObject(4) , decretsBinaryContext.getObject(4));
		        	  
		        	  
		        	  // When a relation has been selected in the list of inter context relations
		        	  // 1-Remove the old interContextRelation
 		        	  //rcfQuery.removeInterCtxRelation(rcf.getInterCtxRelation("ref"));
 		        	  // 2-Add the new context having the new source (new context arretes) and the new destination (new context decrets) 		        	  
		        	  rcfQuery.addInterCtxRelation(refInterCtxRelationNew);
		        	  
		        	  System.out.println("arretes objects number = "+arretesBinaryContext.getObjectNumber());
		        	  System.out.println("decrets objects number = "+decretsBinaryContext.getObjectNumber());
		        	  
		          }
		        });
		    
		    
		    //Create a JTextArea to display the answer to the query
		    JTextArea showAnswerArea = new JTextArea();
		    showAnswerArea.setEditable(true); 
		    showAnswerArea.setAutoscrolls(true);
		    showAnswerArea.setBackground(Color.WHITE);
		    showAnswerArea.setSize(500, 500);
		    showAnswerArea.setToolTipText("Text area to display the answer");
		    JPanel answerPanel = new JPanel();
		    answerPanel.add(showAnswerArea); 
		    answerPanel.setBorder(BorderFactory.createTitledBorder("Display Answer" ));
		    answerPanel.setSize(600, 700);

		    
		    //Create a button to execute the query: rerun the RCA algorithm on the modified contexts
		    JButton answerMyQueryButton = new JButton("Answer My Query");
		    JPanel buttonPanel = new JPanel();
		    buttonPanel.add(answerMyQueryButton);
		    buttonPanel.setSize(800,100);
		    
		    final String resultRlfFilePath = rlfFile.getAbsolutePath();
		    
		    answerMyQueryButton.addActionListener(new ActionListener() {		    	 
	            public void actionPerformed(ActionEvent e)
	            {
	            	//Execute when button is pressed
	                System.out.println("You clicked the button");
	                
	            	multiFCA algoRCA = new multiFCA(rcfQuery,true);
	            	algoRCA.exec();
	            	
	            	// Writing output
	        		AbstractWriter writer=null;
	        		
	        		File theResultFile = new File(resultRlfFilePath.replace(".r", "Result.r"));
	        		try {
	        			writer=IoKernel.getWriter(IoKernel.RLF_DATA,IoKernel.XML,IoKernel.FILE_STORAGE,theResultFile,algoRCA.getResult());
	        			writer.setMuted();
	        			writer.write();
	        		} catch (Exception writingResultException) {
	        			System.err.println("Can not write the resulting lattice !");
	        		}
	        		
	        		// Get the resulting RLF to fetch concepts containing relevant answers to the query 
	            	RelationalLatticeFamily resultRLF = (RelationalLatticeFamily) algoRCA.getResult();
	            	resultRLF.getAllStructureNameId();
	            	//RelationalLatticeFamily rlf = (RelationalLatticeFamily) reader.getResult();

	                System.out.println("the name of the RESULTING rlf is : " + resultRLF.getNameId());

	                String resultLatticeNameID;
	                Concept top;
	                for(Enumeration allStructureNameId = resultRLF.getAllStructureNameId(); allStructureNameId.hasMoreElements();){
	                    resultLatticeNameID = (String)allStructureNameId.nextElement();
	                    System.out.println("the name of the RESULTING structure is : " + resultLatticeNameID);
	                    //answerPanel.
	                }
	            }
	        });      
		 

		    
		    JFrame fr = new JFrame("Relational Query - Mini GUI - Galicia 3");
		    
		    fr.setLayout(new GridLayout(3,1));
		    fr.add(allListsPanel);
		    fr.add(buttonPanel);
		    fr.add(answerPanel);
		    
		    //fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    //fr.pack();
		    fr.setSize(1000, 800);
		    fr.setVisible(true);
		
			    
		
       
}//end main()









        //int resourceID = 3;
        //Class c = Kernel.class;
        //Field field = Kernel.class.getDeclaredField("runningKernel");

        //Field field1 = c.getDeclaredField("runningKernel");
        //field1.setAccessible(true);
        //Kernel myKernel = (Kernel) field1.get("runningKernel");
        //System.out.println("Value of runningKernel: " + field1.getDeclaringClass().getDeclaredField("runningKernel")); // prints "runningKernel"
        //System.out.println("Value of runningKernel: " + field1.get("runningKernel")); // prints "runningKernel"

        //Field field2 = c.getDeclaredField("setOfResouces");
        //field2.setAccessible(true);

        //Resource mySetOfResources[] = new Resource[10];
        //System.out.println("type of my resource id " + resourceID + " is " + mySetOfResources[3].getDataTypeName());


/*        Class k = field2.getClass();
        Class[] parameterTypes = new Class[] {int.class};
        Method getResource;
        File myRlfFile = null;
        try {
        getResource = k.getMethod("getResource", parameterTypes );
        myRlfFile = (File)getResource.invoke(myKernel , resourceID);
        } catch (NoSuchMethodException e) {
              System.out.println(e);
          } catch (IllegalAccessException e) {
              System.out.println(e);
          } catch (InvocationTargetException e) {
              System.out.println(e);
          }*/


        //Class<miniGui> newGui = (Class<miniGui>) miniGui.class;
        //Object o = newGui.getClass();
        //Method getTheSession = newGui.getMethod("getTheSession", null);
        //UserSession theSession = (UserSession) getTheSession.invoke(newGui, null);
        //UserSession mySession = (UserSession) newGui.getDeclaredField("theSession").get(theSession);
        //Resource r = theSession.getResource(3);
        //System.out.println("comment resource : " + r.getComment());



        //Resource mySetOfResources0[] = null;
          //Field resourcesField;
          //Class kernel = myKernel.getClass();
          //try {
              //resourcesField = kernel.getField("setOfResouces");
              //resourcesField.setAccessible(true);
              //mySetOfResources0 = (Resource[]) resourcesField.get(myKernel);
              //System.out.println("Number of resources: " + mySetOfResources0.length);
              //Resource myRlfResource = mySetOfResources0[resourceID];
              //File myRlfFile = new File(myRlfResource.getPath());
          //}catch (NoSuchFieldException e) {
          //    System.out.println(e);
          //} catch (SecurityException e) {
          //    System.out.println(e);
          //} catch (IllegalAccessException e) {
          //    System.out.println(e);
          //}





        //field.getDeclaringClass().getDeclaredField("runningKernel").mySetOfResources[resourceID];

        //myKernel.getResource(3);



        //Resource r = mySetOfResources[resourceID];


        //File myRlfFile =

        //Resource R = Kernel.getResource(resourceID);
        //if(R!=null) return R.getValue();
        //else return null;

        //Kernel.
        //UserSession us = new UserSession();




/**
 * My source on the net
 * http://xpose.avenir.asso.fr/viewxpose.php?site=6&subpage=/manip.php
 */

         /**
          *           static void printHeight(Rectangle r) {
              Field heightField;
              Integer heightValue;
              Class c = r.getClass();
              try {
                heightField = c.getField("height");
                heightValue = (Integer) heightField.get(r);
                System.out.println("Height: " + heightValue.toString());
              } catch (NoSuchFieldException e) {
                  System.out.println(e);
              } catch (SecurityException e) {
                  System.out.println(e);
              } catch (IllegalAccessException e) {
                  System.out.println(e);
              }
           }

          */




/**        class SimpleKeyPair {
            private String privateKey = "Cafe Babe"; // private field
        }
        public class PrivateMemberAccessTest {
            public static void main(String[] args) throws Exception {
                SimpleKeyPair keyPair = new SimpleKeyPair();
                Class c = keyPair.getClass();

                // get the reflected object
                Field field = c.getDeclaredField("privateKey");
                // set accessible true
                field.setAccessible(true);
                System.out.println("Value of privateKey: " + field.get(keyPair)); // prints "Cafe Babe"
                // modify the member varaible
                field.set(keyPair, "Java Duke");
                System.out.println("Value of privateKey: " + field.get(keyPair)); // prints "Java Duke"
            }
        }
**/
/*******************************************************************************************/
/**        public static String append(String firstWord, String secondWord) {
          String result = null;
          Class c = String.class;
          Class[] parameterTypes = new Class[] {String.class};
          Method concatMethod;
          Object[] arguments = new Object[] {secondWord};
          try {
            concatMethod = c.getMethod("concat", parameterTypes);
            result = (String) concatMethod.invoke(firstWord, arguments);
          } catch (NoSuchMethodException e) {
              System.out.println(e);
          } catch (IllegalAccessException e) {
              System.out.println(e);
          } catch (InvocationTargetException e) {
              System.out.println(e);
          }
          return result;
       }
**/









}//end class
