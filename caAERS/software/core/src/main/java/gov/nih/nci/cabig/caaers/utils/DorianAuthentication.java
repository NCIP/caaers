package gov.nih.nci.cabig.caaers.utils;

import gov.nih.nci.cagrid.authentication.bean.BasicAuthenticationCredential;
import gov.nih.nci.cagrid.authentication.bean.Credential;
import gov.nih.nci.cagrid.authentication.client.AuthenticationClient;
import gov.nih.nci.cagrid.dorian.client.IFSUserClient;
import gov.nih.nci.cagrid.dorian.ifs.bean.ProxyLifetime;
import gov.nih.nci.cagrid.opensaml.SAMLAssertion;

import org.globus.gsi.GlobusCredential;

public class DorianAuthentication {
	
	/**
	 * Authenticate to any Dorian and obtain proxy . 
	 * @param dorianURL
	 * @param userId
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static GlobusCredential authenticate(String dorianURL, String userId, String password) throws Exception {
		
			Credential credential = new Credential();
			BasicAuthenticationCredential bac = new BasicAuthenticationCredential();
			bac.setUserId(userId);
			bac.setPassword(password);
			credential.setBasicAuthenticationCredential(bac);
			
			AuthenticationClient client = new AuthenticationClient(dorianURL, credential);
			SAMLAssertion saml = client.authenticate();

			//Create a IFS Client for authorization
			IFSUserClient ifsClient = new IFSUserClient(dorianURL);

			//Create a lifetime for the proxy, 12 hours in this case
			ProxyLifetime lifetime = new ProxyLifetime();
			lifetime.setHours(12);
			lifetime.setMinutes(0);
			lifetime.setSeconds(0);

			//specify delegation, use 0 for now. 0 indicates that the credential cannot be delegated
			int delegation = 0;
			try {
			    delegation = Integer.valueOf(1);        
			}
			catch (Exception e) {            
			    // Display oppropriate client error           
			    return null;        
			}

			//obtain your proxy and save it for use in invoking grid services
			GlobusCredential cred = ifsClient.createProxy(saml, lifetime, delegation);

			
			return cred;
	    }

}
