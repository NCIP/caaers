/**
 * 
 */
package gov.nih.nci.cabig.caaers.tools.accesscontrol;

import gov.nih.nci.cabig.caaers.web.security.GridGroupSearch;
import gov.nih.nci.cabig.ctms.web.sso.DefaultGridProxyRetrievalStrategy;
import gov.nih.nci.cabig.ctms.web.sso.Utils;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com>Joshua Phillips</a>
 * 
 */
public class CaaersGridProxyRetrievalStrategy extends DefaultGridProxyRetrievalStrategy {

    private static final Log logger = LogFactory.getLog(CaaersGridProxyRetrievalStrategy.class);
    
    private GridGroupSearch gridGroupSearch;

	public GridGroupSearch getGridGroupSearch() {
		return gridGroupSearch;
	}

	public void setGridGroupSearch(GridGroupSearch gridGroupSearch) {
		this.gridGroupSearch = gridGroupSearch;
	}

	public String processRequest(HttpServletRequest request) {
        String proxy = super.processRequest(request);
        if (proxy != null) {
            logger.debug("Proxy found. Getting identity...");
            try{
                String identity = Utils.getGridIdentity(proxy);
                logger.debug("Identity is: " + identity);
                
                List<String> groupNames = getGridGroupSearch().getGridGroupNames(identity);
                GrantedAuthority[] auths = new GrantedAuthority[groupNames.size()];
                int idx = 0;
                for(Iterator i = groupNames.iterator(); i.hasNext(); idx++){
                	String groupName = (String)i.next();
                	auths[idx] = new GrantedAuthorityImpl(groupName);
                }
                Authentication auth = new UsernamePasswordAuthenticationToken(identity, proxy, auths);
                SecurityContextHolder.getContext().setAuthentication(auth);
                
            }catch(Exception ex){
                logger.warn("Couldn't get identity from proxy string: " + ex.getMessage(), ex);
            }
            
        }else{
            logger.debug("No proxy found.");
        }
        return proxy;
    }
	
	







}
