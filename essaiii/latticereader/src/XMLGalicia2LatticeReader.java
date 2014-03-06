import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 * 
 */

/**
 * @author 
 *
 */
public class XMLGalicia2LatticeReader {

	private Hashtable<Integer,String> Objects;
	private Hashtable<Integer,String> Attributes;
	private Hashtable<Integer,Concept> Concepts;
	
	private Document LatticeDocument;
	private Element rootElement;
	
	/**
	 * 
	 */
	public XMLGalicia2LatticeReader(String XMLFileName) throws IOException, JDOMException {
		
		//Build the XML Document
		SAXBuilder builder = new SAXBuilder();
		LatticeDocument = builder.build(new File(XMLFileName));

		// Get the root element
		rootElement = LatticeDocument.getRootElement();
		
		Objects = getObjects();
		Attributes = getAttributes();
		Concepts = getConcepts();
		
	}
	
	public Hashtable<Integer,String> getObjects(){
		Hashtable<Integer,String> Objs = new Hashtable<Integer,String>();
		
		List<Element> ObjElts = rootElement.getChild("OBJS").getChildren("OBJ");
		Iterator<Element> ObjsIterator = ObjElts.iterator();
		while (ObjsIterator.hasNext()) {
			Element anObj = (Element) ObjsIterator.next();		
			int anObjId = new Integer(anObj.getAttributeValue("id"));
			Objs.put(anObjId, anObj.getText());
		}
		
		return Objs;
	}

	public Hashtable<Integer,String> getAttributes(){
		
		Hashtable<Integer,String> Attrs = new Hashtable<Integer,String>();
		
		List<Element> AttrElts = rootElement.getChild("ATTS").getChildren("ATT");
		Iterator<Element> AttrsIterator = AttrElts.iterator();
		while (AttrsIterator.hasNext()) {
			Element anAttr = (Element) AttrsIterator.next();			
			int anAttrId = new Integer(anAttr.getAttributeValue("id"));
			Attrs.put(anAttrId, anAttr.getText());
		}
		
		return Attrs;
	}
	
	public Hashtable<Integer,Concept> getConcepts(){
		
		Hashtable<Integer,Concept> Conpts = new Hashtable<Integer,Concept>();
		
		List<Element> NodeElts = rootElement.getChild("NODS").getChildren("NOD");
		Iterator<Element> NodeIterator = NodeElts.iterator();
		while (NodeIterator.hasNext()) {
			Element aNode = (Element) NodeIterator.next();
			int aNodeId = new Integer(aNode.getAttributeValue("id"));
			Conpts.put(aNodeId, buildConceptFromNodeElt(aNode));
		}
		
		return Conpts;
	}
	
	public Concept buildConceptFromNodeElt(Element nodeElt){
		
		int NodeId = new Integer(nodeElt.getAttributeValue("id"));
		
		Vector<String> NodeExt = new Vector<String>();
		
		List<Element> NodeExts = nodeElt.getChild("EXT").getChildren("OBJ");

		Iterator<Element> NodeObjIterator = NodeExts.iterator();
		
		while (NodeObjIterator.hasNext()) {
			
			Element aNode = (Element) NodeObjIterator.next();
		
			int aNodeId = new Integer(aNode.getAttributeValue("id"));
			
			NodeExt.add(Objects.get(aNodeId));
		}

		Vector<String> NodeInt = new Vector<String>();
		List<Element> NodeInts = nodeElt.getChild("INT").getChildren("ATT");

		Iterator<Element> NodeAttIterator = NodeInts.iterator();
		
		while (NodeAttIterator.hasNext()) {
			
			Element aNode = (Element) NodeAttIterator.next();
		
			int aNodeId = new Integer(aNode.getAttributeValue("id"));
			
			NodeInt.add(Attributes.get(aNodeId));
		}
		
		Vector<Integer> NodePar = new Vector<Integer>();
		List<Element> NodePars = nodeElt.getChild("SUP_NOD").getChildren("PARENT");

		Iterator<Element> NodeParIterator = NodePars.iterator();
		
		while (NodeParIterator.hasNext()) {
			
			Element aNode = (Element) NodeParIterator.next();
			NodePar.add(new Integer(aNode.getAttributeValue("id")));
		}
		
		return new Concept(NodeId,NodeExt,NodeInt,NodePar);
	}
	
	public Concept getConcept(int conceptID){
		return Concepts.get(conceptID);
	}
	
	//public 
}
