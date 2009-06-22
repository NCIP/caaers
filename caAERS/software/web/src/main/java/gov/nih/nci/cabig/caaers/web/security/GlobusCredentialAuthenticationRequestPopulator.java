/**
 * 
 */
package gov.nih.nci.cabig.caaers.web.security;

import gov.nih.nci.security.acegi.grid.Utils;
import gov.nih.nci.security.acegi.grid.authentication.GlobusCredentialAuthenticationToken;

import java.io.ByteArrayInputStream;

import javax.servlet.http.HttpServletRequest;

import org.acegisecurity.Authentication;
import org.acegisecurity.AuthenticationException;
import org.acegisecurity.BadCredentialsException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.globus.gsi.GlobusCredential;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 * 
 */
public class GlobusCredentialAuthenticationRequestPopulator implements
                AuthenticationRequestPopulator {

    private static final Log logger = LogFactory
                    .getLog(GlobusCredentialAuthenticationRequestPopulator.class);

    private String gridProxyParameterName;

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.cabig.caaers.web.security.AuthenticationRequestPopulator#populate(javax.servlet.http.HttpServletRequest)
     */
    public Authentication populate(HttpServletRequest request) throws AuthenticationException {

        Authentication auth = null;

        // logger.debug("Looking for grid proxy...");
        String gridProxyStr = Utils.findParameter(request, getGridProxyParameterName());
        if (gridProxyStr == null) {
            // logger.debug("...didn't find it.");
            throw new BadCredentialsException("No grid proxy found in request.");
        } else {
            // logger.debug("...found it.");
            try {
                // logger.debug("Creating GlobusCredential from gridProxy...");
                GlobusCredential cred = new GlobusCredential(new ByteArrayInputStream(gridProxyStr
                                .getBytes()));
                auth = new GlobusCredentialAuthenticationToken(cred);
            } catch (Exception ex) {
                // logger.debug("...error encountered: " + ex.getMessage());
                throw new BadCredentialsException("Error parsing grid proxy: " + ex.getMessage(),
                                ex);
            }
            // logger.debug("...GlobusCredential created.");
        }
        // logger.debug("Returning GlobusCredentialAuthenticationToken");
        return auth;
    }

    public String getGridProxyParameterName() {
        return gridProxyParameterName;
    }

    public void setGridProxyParameterName(String gridProxyParameterName) {
        this.gridProxyParameterName = gridProxyParameterName;
    }

}
