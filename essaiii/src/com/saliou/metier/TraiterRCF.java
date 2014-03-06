package com.saliou.metier;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

//import galicia.algorithms.RCA.multiFCA;
//import galicia.core.IoKernel;
//import galicia.core.Kernel;
//import galicia.core.Notifier;
//import galicia.core.Resource;
//import galicia.core.TaskControler;
//import galicia.core.UserSession;
//import galicia.io.AbstractReader;
//import galicia.io.AbstractWriter;
//import galicia.io.XMLGaliciaReader;
//import galicia.ui.miniGui.miniGui;
//import galicia.util.relationalStructures.RelationalContextFamily;

public class TraiterRCF {
	
	
//	Integer userID= new Integer(1);
//	UserSession mySession=null;
//	Resource myRCFResource = null;
//	RelationalContextFamily myRCF = null;
//	String myRCFPath="";
//	
//	public TraiterRCF (String path){
//		myRCFPath = path;
//		Kernel.startKernel();
//		miniGui.start();
//		mySession = UserSession.connect(null, this); 
//	}
//	
//	public String getRCFPath(){
//		return myRCFPath;
//	}
//	
//	/**
//	 * Notifier management, there is only one Notifier in this GUI
//	 */
//	public void notifyMessage(String theMessage) {
//		//System.out.println("coucou");
//	}
//
//	/**
//	 * Notifier management, there is only one Notifier in this GUI
//	 */
//	public void endTask(){
//		//System.out.println("coucou coucou");
//	}
//	
//	
//	public void setTaskControler(TaskControler theTaskControler) {
//		//System.out.println("coucou coucou coucou");
//		
//	}
//	public UserSession getSession(){
//		//UserSession n = new miniGui().getTheSession()
//		return mySession;
//		
//	}
//	
//	public Resource CreateResource(String path){
//		
//		File rcfFile = new File(path);
//		myRCFResource = mySession.createFileResource(IoKernel.RCF_DATA,path,IoKernel.getEncodingOfFile(rcfFile)," mon commentaire ");
//		return myRCFResource;
//	
//	}
//	
//	public File runMulitFCAonRCFResource(String rcfFilePath){
//		
//		File rcfFile = new File(rcfFilePath);
//        
//        AbstractReader reader = null;
//        try {
//			reader = new XMLGaliciaReader(new FileInputStream(rcfFile));
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        ((XMLGaliciaReader)reader).setExpectedDataType(IoKernel.RCF_DATA);
//        reader.setMuted();
//        reader.read();
//
//        final RelationalContextFamily rcf = (RelationalContextFamily) reader.getResult();
//        
//        multiFCA algoRCA = new multiFCA(rcf,true);
//    	algoRCA.exec();
//    	
//        
//    	// Writing output
//		AbstractWriter writer=null;
//		
//		File theRLFFile = new File(rcfFilePath.concat("RLF"));
//		try {
//			writer=IoKernel.getWriter(IoKernel.RLF_DATA,IoKernel.XML,IoKernel.FILE_STORAGE,theRLFFile,algoRCA.getResult());
//			writer.setMuted();
//			writer.write();
//		} catch (Exception writingResultException) {
//			System.err.println("Can not write the resulting lattice !");
//		}
//		
//		return theRLFFile;
//	}
}
