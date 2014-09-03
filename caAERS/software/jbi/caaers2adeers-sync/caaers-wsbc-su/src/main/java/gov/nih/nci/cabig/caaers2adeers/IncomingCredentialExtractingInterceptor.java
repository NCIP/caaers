/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers2adeers;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;

public class IncomingCredentialExtractingInterceptor extends WSS4JOutInterceptor {
	
   private static final String INITIATE_SAFETY_REPORT_OPERATION_NAME = "initiateSafetyReportAction";
   private static final String SAVE_AND_EVALUATE_OPERATION_NAME = "saveAndEvaluateAEs";
   private static final String GENERATE_SAFETY_REPORT_ID_OPERATION_NAME = "generateSafetyReportId";

	public static ThreadLocal<String> user = new ThreadLocal<String>() {
		@Override
		protected String initialValue() {
			return "";
		}
	};
	public static String getUser() {
		return user.get();
	}
	
	public static void setUser(String user1) {
		user.set(user1);
	}
	
	public static String getPwd() {
		return pwd.get();
	}
	
	public static void setPwd(String pwd1) {
		pwd.set(pwd1);
	}
	
	public static ThreadLocal<String> pwd = new ThreadLocal<String>() {
		@Override
		protected String initialValue() {
			return "";
		}
	};
	
	public static ThreadLocal<Boolean> isrsContext = new ThreadLocal<Boolean>() {
		@Override
		protected Boolean initialValue() {
			return false;
		}
	};
	
	
	public static Boolean getIsrsContext() {
		return isrsContext.get();
	}

	public static void setIsrsContext(Boolean isrsContext1) {
		isrsContext.set(isrsContext1);
	}

	public static void removeUser(){
		user.remove();
	}
	
	public static void removePwd(){
		pwd.remove();
	}
	
	public static void removeIsrsContext(){
		isrsContext.remove();
	}
	
	@Override
	public void handleMessage(SoapMessage mc) throws Fault {
		try {

			String body = mc.getExchange().toString();
			int userNameStartIndex = body.indexOf("<wsse:Username>") + 15 ;
			int userNameEndIndex = body.indexOf("</wsse:Username>");
			String username = body.substring(userNameStartIndex,userNameEndIndex);
			setUser(username);
			int passwordStartIndex = body.indexOf("<wsse:Password>") + 15 ;
			int passwordEndIndex = body.indexOf("</wsse:Password>");
			String password = body.substring(passwordStartIndex,passwordEndIndex);
			setPwd(password);
			// set isrs context true for isrs messages
			if(body.contains(INITIATE_SAFETY_REPORT_OPERATION_NAME) || body.contains(GENERATE_SAFETY_REPORT_ID_OPERATION_NAME) ||
					body.contains(SAVE_AND_EVALUATE_OPERATION_NAME)) {
			      setIsrsContext(true);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}