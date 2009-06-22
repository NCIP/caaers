package gov.nih.nci.cagrid.antinstaller.utils;



import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;


import org.tp23.antinstaller.runtime.ReferenceVariable;

import bsh.Interpreter;


public class JavaExpressionEvaluator {
	//private static Logger logger = Logger.getLogger(JavaExpressionEvaluator.class.getName());
	private String expression;
	private ArrayList variables;
	
	
	
	public JavaExpressionEvaluator(String expression, ArrayList variables)  {
		super();
		this.expression = expression;
		this.variables = variables;
		/**
		FileHandler handler=null;
		try {
			handler = new FileHandler("/tmp/caaers_installer.log");
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.addHandler(handler);
		*/
		
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public ArrayList getVariables() {
		return variables;
	}

	public void setVariables(ArrayList variables) {
		this.variables = variables;
	}

	private String getParsableString(String str, ArrayList variables){
		String rStr = str;
		for(int i=0;i<variables.size();i++){
			ReferenceVariable rf = (ReferenceVariable)variables.get(i);
			rStr = StringUtilities.replaceInString(rStr,rf.getName(),rf.getReplaceableKey());
			//rStr = StringUtilities.replaceInString(rStr,"'","\\\"");
			rStr = StringUtilities.replaceInString(rStr,"'","\"");
			rStr = StringUtilities.replaceInString(rStr,"**","&&");
		}
		
		//logger.info("The parsed string is:"+rStr);
		
		return rStr;
		
	}
   public boolean evaluate(){
	  // logger.info("Called evaluate:");
	   try{
		   Class.forName("bsh.Interpreter");
	   }catch(Exception ex){
		   //logger.info("EXception took place looking for class:"+ex.getMessage());
	   }
	   Interpreter bsh = new Interpreter();
	   boolean retValue = true;
	   try{
		   for(int i=0;i<variables.size();i++){
			   ReferenceVariable rv = (ReferenceVariable)variables.get(i);
			   //logger.info("Value of the prop:"+rv.getValue());
			   bsh.set(rv.getReplaceableKey(), rv.getValue());
		   }
		   //logger.info("Expression is:"+expression);
		   String str = this.getParsableString(expression,variables);
		   //logger.info("Parsed String:"+str);
		   
		   Boolean val = (Boolean)(bsh.eval(str));
		   retValue = val.booleanValue();
		   
	   }catch(Exception ex){
		   //logger.info("EXception took place:"+ex);
		   
		   ex.printStackTrace();
	   }
	   //logger.info("Evaluated Value:"+retValue);
	   return retValue;
   }

	
	
}
