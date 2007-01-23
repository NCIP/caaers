/**
 * 
 */
package gov.nih.nci.cabig.caaers.tools.accesscontrol;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import gov.nih.nci.cabig.ctms.web.sso.DefaultGridProxyRetrievalStrategy;
import gov.nih.nci.cabig.ctms.web.sso.Utils;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com>Joshua Phillips</a>
 * 
 */
public class CaaersGridProxyRetrievalStrategy extends DefaultGridProxyRetrievalStrategy {

    private static final Log logger = LogFactory.getLog(CaaersGridProxyRetrievalStrategy.class);
    
    public String processRequest(HttpServletRequest request) {
        String proxy = super.processRequest(request);
        if (proxy != null) {
            logger.debug("Proxy found. Getting identity...");
            try{
                String identity = Utils.getGridIdentity(proxy);
                logger.debug("Identity is: " + identity);
                ApplicationSecurityManager.setUser(request, identity);
            }catch(Exception ex){
                logger.warn("Couldn't get identity from proxy string: " + ex.getMessage(), ex);
            }
            
        }else{
            logger.debug("No proxy found.");
        }
        return proxy;
    }

}
