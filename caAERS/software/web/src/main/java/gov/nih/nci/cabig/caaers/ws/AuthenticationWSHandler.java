/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.ws;



import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.repository.UserRepository;
import gov.nih.nci.cabig.caaers.security.CaaersCSMAuthenticationProvider;
import gov.nih.nci.security.acegi.authentication.CSMAuthenticationProvider;
import gov.nih.nci.security.exceptions.CSLoginException;
import org.acegisecurity.*;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.TestingAuthenticationToken;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.binding.soap.SoapFault;
import org.apache.cxf.interceptor.Fault;
import org.apache.ws.security.WSPasswordCallback;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;
import java.util.List;

/**
 * Will authenticate the user with the username and password.
 *
 * @author Biju Joseph
 */
public class AuthenticationWSHandler implements CallbackHandler {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private static final Log logger = LogFactory.getLog(AuthenticationWSHandler.class);

    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {

        //find the passwrod callback
        WSPasswordCallback pc = null;
        for (Callback callback : callbacks) {
            if (callback instanceof WSPasswordCallback) {
                pc = (WSPasswordCallback)callback; break;
            }
        }
        
        String userName = pc.getIdentifer();
        String password = pc.getPassword();

        try{
            Authentication authz = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
            logger.debug(authz);
        }catch(AuthenticationException e) {
            logger.error("Invalid Username/Password.", e);
            throw new SoapFault("Invalid Username/Password [", Fault.FAULT_CODE_SERVER);
        }catch(Exception e) {
            logger.error(e.getMessage());
            throw new SoapFault(e.getMessage(), Fault.FAULT_CODE_SERVER);
        }

        //populate the granted authorities

        List<UserGroupType> groups = userRepository.getUserByLoginName(userName).getUserGroupTypes();
        GrantedAuthority[] authorities = new GrantedAuthority[groups.size()];
        int idx = 0;
        for(UserGroupType group : groups) {
            authorities[idx] = new GrantedAuthorityImpl(group.getSecurityRoleName());
            idx = idx + 1;
        }
        // populate groups ...
        Authentication authentication = new TestingAuthenticationToken(userName, "ignored", authorities);
        authentication.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        logger.debug("Populated authentication in SecurityHolder : " + authentication.toString());
    }

    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
