/**
 * 
 */
package gov.nih.nci.cabig.caaers.web.security;

import java.io.ByteArrayInputStream;

import javax.servlet.http.HttpServletRequest;

import org.acegisecurity.Authentication;
import org.acegisecurity.AuthenticationException;
import org.acegisecurity.BadCredentialsException;
import org.globus.gsi.GlobusCredential;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 *
 */
public class GlobusCredentialAuthenticationRequestPopulator implements
		AuthenticationRequestPopulator {
	
	private String gridProxyParameterName;

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.web.security.AuthenticationRequestPopulator#populate(javax.servlet.http.HttpServletRequest)
	 */
	public Authentication populate(HttpServletRequest request) throws AuthenticationException {
		
		Authentication auth = null;
		
		String gridProxyStr = request.getParameter(getGridProxyParameterName());
		if(gridProxyStr == null){
			throw new BadCredentialsException("No grid proxy found in request.");
		}else{
			try{
				GlobusCredential cred = new GlobusCredential(new ByteArrayInputStream(gridProxyStr.getBytes()));
				auth = new GlobusCredentialAuthenticationToken(cred);
			}catch(Exception ex){
				throw new RuntimeException("Error parsing grid proxy: " + ex.getMessage(), ex);
			}
			
		}
		
		return auth;
	}

	public String getGridProxyParameterName() {
		return gridProxyParameterName;
	}

	public void setGridProxyParameterName(String gridProxyParameterName) {
		this.gridProxyParameterName = gridProxyParameterName;
	}

}
