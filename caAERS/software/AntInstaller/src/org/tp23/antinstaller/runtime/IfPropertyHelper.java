/* 
 * Copyright 2005 Paul Hinds
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tp23.antinstaller.runtime;



import gov.nih.nci.cagrid.antinstaller.utils.JavaExpressionEvaluator;

import java.io.File;
import java.util.ArrayList;

import org.tp23.antinstaller.InstallerContext;
import org.tp23.antinstaller.page.Page;
import org.tp23.antinstaller.page.SimpleInputPage;

import java.util.logging.Logger;
import java.util.logging.*;
import gov.nih.nci.cagrid.antinstaller.utils.ClassPathModifier;


/**
 * <p>Encapsulates code fo the ifProperty feature</p>
 * N.B.  It is the installer generator's responsibility to ensure that properties passed
 * to the less than or greater than test are valid Numbers.
 * The internal Java format used is a Double so avalid regex would be
 * something like [0-9]+\.*[0-9]*  or [0-9]+ for an Integer.
 * The rather strange -=  and += syntax is used because &gt; and &lt;
 * must be escaped to &amp;gt; and &amp;lt; in XML attributes and the legibility
 * of the configuration files would be impared.
 * REF: 1145496
 * @author Paul Hinds
 * @version $Id: IfPropertyHelper.java,v 1.5 2006/10/27 18:23:46 kumarvi Exp $
 */
public class IfPropertyHelper {
	
	//private static Logger logger = Logger.getLogger(IfPropertyHelper.class.getName());
	

	InstallerContext ctx = null;
	protected IfPropertyHelper(InstallerContext ctx){
		this.ctx = ctx;
		
		
		
		 
		 
		 try{
			 	File installRoot = InstallerContext.getLatestInstallDir();
				File resources = new File(installRoot,"resources");
				File custom_libs = new File(resources,"custom_libs");
				File bsh_jar = new File(custom_libs,"bsh-2.0b4.jar");
				ClassPathModifier.addFile(bsh_jar);
			 
		 }catch(Exception e){
			 //logger.info("Could not configure the classpath"+e.getMessage());
			 e.printStackTrace();
		 }
	}
	
	/**
	 * This is a new method introduced for a better control of flow of pages.
	 * @param next
	 * @return
	 */
	
	public boolean ifProperty(Page next){
		//logger.info("Called ifproperty method");
		boolean retValue = true;
		
		if(next instanceof SimpleInputPage){
			SimpleInputPage conditionalPage = (SimpleInputPage) next;
			String ifProperty = conditionalPage.getIfProperty();
			//logger.info("Value of the ifproperty:"+ifProperty);
			//System.out.println("IFProperty2:"+ifProperty);
			if (ifProperty != null) {
				ArrayList variables = extractVariables(ifProperty);
				JavaExpressionEvaluator jee = new JavaExpressionEvaluator(ifProperty, variables);
				retValue = jee.evaluate();
			}
		}
		return retValue; // show the page by default
	}
	
	private ArrayList extractVariables(String str){
		ArrayList keys = new ArrayList();
		
		int currindex = 0;
		int startPos =0;
		
		int counter=0;
		String currStr = str;
		boolean done = false;
		while(!done){
			currindex = currStr.indexOf("$");
			startPos = currindex+2;
			int endPos = currStr.indexOf("}", startPos);
			if(currindex==-1){
				done=true;
				break;
			}else{
				counter++;
				String name = currStr.substring(currindex,endPos+1);
				//System.out.println("Name:"+name);
				//logger.info("Name:"+name);
				String propValue = ctx.getInstaller().getResultContainer().getDefaultValue(name);
				//logger.info("propValue:"+propValue);
				String key = currStr.substring(startPos,endPos);
				//logger.info("Key:"+key);
				//System.out.println(key);
				ReferenceVariable rf = new ReferenceVariable();
				rf.setValue(propValue);
				rf.setKey(key);
				rf.setName(name);
				rf.setReplaceableKey("var"+counter);
				keys.add(rf);
			}
			
			currStr= currStr.substring(endPos+1,currStr.length());
			
		}
		
		return keys;
	}

	/**
	 * Returns true if the ifProperty is set and the test fails so the page should
	 * be skipped
	 * @return boolean true to SHOW the page
	 */
	public boolean ifPropertyXX(Page next){
		System.out.println("IFProperty1:"+"called!!!!!!!");
		// show page if ifProperty is specified and property is correct
		if(next instanceof SimpleInputPage){
			SimpleInputPage conditionalPage = (SimpleInputPage) next;
			String ifProperty = conditionalPage.getIfProperty();
			System.out.println("IFProperty2:"+ifProperty);
			if (ifProperty != null) {
				String propExpectedValue = null;
				
				// this big if list is to support the old syntax it can be optimised
				// when the old syntax is finally depretated
				
				int operatorIndex = ifProperty.indexOf("!=null");
				if(operatorIndex!=-1){
					return ifProperty(ifProperty, operatorIndex, "!=null");
				}
				operatorIndex = ifProperty.indexOf("==null");
				if(operatorIndex!=-1){
					return ifProperty(ifProperty, operatorIndex, "==null");
				} 
				operatorIndex = ifProperty.indexOf("$="); // ends with
				if (operatorIndex!=-1){
					return ifProperty(ifProperty, operatorIndex, "$="); // ends with
				}
				operatorIndex = ifProperty.indexOf("^=");  // starts with
				if (operatorIndex!=-1){
					return ifProperty(ifProperty, operatorIndex, "^="); // starts with
				}
				operatorIndex = ifProperty.indexOf("==");  // Java syntax equals for those that prefer it
				if (operatorIndex!=-1){
					return ifProperty(ifProperty, operatorIndex, "==");
				}
				operatorIndex = ifProperty.indexOf("!=");  
				if (operatorIndex!=-1){
					return ifProperty(ifProperty, operatorIndex, "!=");
				}
				operatorIndex = ifProperty.indexOf("-=");  // Java syntax equals for thos that prefer it
				if (operatorIndex!=-1){
					return ifProperty(ifProperty, operatorIndex, "-=");
				}
				operatorIndex = ifProperty.indexOf("+=");  // Java syntax equals for thos that prefer it
				if (operatorIndex!=-1){
					return ifProperty(ifProperty, operatorIndex, "+=");
				}
				operatorIndex = ifProperty.indexOf("=");  // normal equals (NOT ASSIGNMENT)
				if (operatorIndex!=-1){
					return ifProperty(ifProperty, operatorIndex, "=");
				}
			}
		}
		return true; // show the page by default
	}
	
	private boolean ifProperty(String ifProperty, int operatorIndex, String operator){
		System.out.println("IFProperty:"+ifProperty);
		int operatorLen = operator.length();
		String propKey = ifProperty.substring(0, operatorIndex);
		String propExpectedValue = ifProperty.substring(operatorIndex + operatorLen);
		// resolve ${references} 
		String propValue = null;
		// this test will be removed when the old name=value syntax is deprecated
		if(propKey.startsWith("${")){
			propValue = ctx.getInstaller().getResultContainer().getDefaultValue(propKey);
		} else {
			propValue = ctx.getInstaller().getResultContainer().getProperty(propKey);
		}
		if(operator.equals("="))          return ifPropertyEquals (propValue,propExpectedValue);
		if(operator.equals("=="))         return ifPropertyEquals (propValue,propExpectedValue);
		if(operator.equals("!="))         return ifPropertyNotEquals (propValue,propExpectedValue);
		else if(operator.equals("$="))    return ifPropertyEndsWith (propValue,propExpectedValue);
		else if(operator.equals("^="))    return ifPropertyStartsWith (propValue,propExpectedValue);
		else if(operator.equals("-="))    return ifPropertyLessThanOrEqual (propValue,propExpectedValue);
		else if(operator.equals("+="))    return ifPropertyGreaterThanOrEqual (propValue,propExpectedValue);
		else if(operator.equals("!=null"))return ifPropertyNotNull (propValue);
		else if(operator.equals("==null"))return ifPropertyIsNull (propValue);
		else return false;
	}
	
	private boolean ifPropertyEquals(String propValue,String propExpectedValue){
		if(propValue!=null && propValue.equals(propExpectedValue))return true; // show
		return false;// skip		
	}
	private boolean ifPropertyNotEquals(String propValue,String propExpectedValue){
		return propValue==null || !propValue.equals(propExpectedValue);	
	}
	private boolean ifPropertyEndsWith(String propValue,String propExpectedValue){
		return propValue!=null && propValue.endsWith(propExpectedValue);	
	}
	private boolean ifPropertyStartsWith(String propValue,String propExpectedValue){
		return propValue!=null && propValue.startsWith(propExpectedValue); 
	}
	private boolean ifPropertyLessThanOrEqual(String propValue,String propExpectedValue){
		return Double.parseDouble(propValue) <= Double.parseDouble(propExpectedValue); 	
	}
	private boolean ifPropertyGreaterThanOrEqual(String propValue,String propExpectedValue){
		return Double.parseDouble(propValue) >= Double.parseDouble(propExpectedValue); 	
	}
	private boolean ifPropertyNotNull(String propValue){
		return propValue!=null && !propValue.equals("");
	}
	private boolean ifPropertyIsNull(String propValue){
		return propValue==null || propValue.equals("");
	}
}
