package gov.nih.nci.cabig.caaers.esb.client;

import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;

import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.CallbackHandler;

import org.springframework.beans.factory.FactoryBean;

public class CaaersWSS4JPropetyBean implements FactoryBean {
	private CallbackHandler passwordCallbackRef;
	
	public Object getObject() throws Exception {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("action", "UsernameToken Timestamp");
		String userName = Configuration.LAST_LOADED_CONFIGURATION.get(Configuration.CAEXCHANGE_NONGRID_USERNAME);
		map.put("user", userName);
		map.put("passwordType", "PasswordText");
		map.put("signaturePropFile", "cxfClientCrypto.properties");
		map.put("passwordCallbackRef", passwordCallbackRef);
		
		return map;
	}

	public Class getObjectType() {
		// TODO Auto-generated method stub
		return Map.class;
	}

	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return true;
	}

	public CallbackHandler getPasswordCallbackRef() {
		return passwordCallbackRef;
	}

	public void setPasswordCallbackRef(CallbackHandler passwordCallbackRef) {
		this.passwordCallbackRef = passwordCallbackRef;
	}

}
