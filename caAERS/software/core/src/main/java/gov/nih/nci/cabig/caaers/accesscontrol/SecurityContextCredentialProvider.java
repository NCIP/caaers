/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.accesscontrol;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.apache.log4j.Logger;

import edu.duke.cabig.c3pr.esb.DelegatedCredential;
import edu.duke.cabig.c3pr.esb.DelegatedCredentialProvider;
import edu.duke.cabig.c3pr.esb.impl.DelegatedCredentialImpl;
import gov.nih.nci.cabig.caaers.security.WebSSOUser;

/**
 * Acts a credential provider and will provide the grid credential saved in the session by Acegi.
 * <p/> The credential is only available in webSSO mode <p/> Created by IntelliJ IDEA. User: kherm
 * User: Biju (Cut pasted from c3pr) Date: Dec 14, 2007 Time: 5:31:38 PM To change this template use
 * File | Settings | File Templates.
 */
public class SecurityContextCredentialProvider implements DelegatedCredentialProvider {
    Logger log = Logger.getLogger(getClass());

    /**
     * @return Will retreive the credential retreived from cds when running in webSSO mode or null
     *         if running in local mode
     */
    public DelegatedCredential provideDelegatedCredentials() {
        DelegatedCredential cred = null;
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        WebSSOUser user = (WebSSOUser) auth.getPrincipal();
        cred = new DelegatedCredentialImpl(user.getGridCredential(), user.getDelegatedEPR());
        log.info("Providing valid credential for user");
        return cred;
    }
}
