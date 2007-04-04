/**
 * 
 */
package gov.nih.nci.security.acegi.grid.authorization;

import gov.nih.nci.cagrid.authorization.GridGroupName;
import gov.nih.nci.security.acegi.csm.authorization.CSMGroupAuthorizationCheck;

import java.net.MalformedURLException;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 *
 */
public class CSMGridGroupAuthorizationCheck extends CSMGroupAuthorizationCheck {

	private static final Log logger = LogFactory.getLog(CSMGridGroupAuthorizationCheck.class);
	
	protected boolean isMember(Authentication authentication, String groupName){
		boolean isMember = false;
	
		logger.debug("Checking membership in '" + groupName + "' for '" + authentication.getName() + "'");
		
		isMember = super.isMember(authentication, groupName);
		if(!isMember && GridGroupName.isGridGroupName(groupName)){
			GridGroupName gridGroupName = null;
			try {
				gridGroupName = new GridGroupName(groupName);
			} catch (MalformedURLException ex) {
				throw new RuntimeException("Error parsing '" + groupName + "': " + ex.getMessage(), ex);
			}
			logger.debug("Checking grid group membership");
			for(GrantedAuthority authority : authentication.getAuthorities()){
				logger.debug("comparing '" + gridGroupName.getName() + "' with '" + authority.getAuthority() + "'");
				if(authority.getAuthority().equals(gridGroupName.getName())){
					isMember = true;
					break;
				}
			}
		}
		
		return isMember;
	}

}
