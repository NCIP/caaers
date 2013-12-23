package gov.nih.nci.cabig.caaers.security;

import com.westat.ctsu.sso.ctep.CTEPSAMLRequest;
import com.westat.ctsu.sso.ctep.CTEPSAMLResponse;
import com.westat.ctsu.sso.ctep.CTEPSSOAuthenticator;
import com.westat.ctsu.sso.ctep.CTEPSSOUser;
import com.westat.ctsu.sso.domain.PersonRoster;
import com.westat.ctsu.sso.framework.ZException;
import gov.nih.nci.cabig.caaers.RoleMembership;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.repository.UserRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: vijendhar
 * Date: 4/17/13
 * Time: 2:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class CTEPIAMAuthenEntryPoint {
    private  String idpUrl;
    private  String issuer;
    private  String cssURL;
    private  String CaaersURL;
    private UserRepository userRepository;

    public UserRepository getUserRepository() {
      return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
      this.userRepository = userRepository;
    }

    public CTEPIAMAuthenEntryPoint() {
        idpUrl="https://iapps-ctep.nci.nih.gov:443/sso-web/singleSignOn.action";
        issuer="http://test.sp.com";
        cssURL="https://www.ctsu.org/ctsusso/css/miniLogin.css";
        CaaersURL="https://localhost:8443/caaers";
    }


    public void authenticateRequest(HttpServletRequest request,
                                    HttpServletResponse response) throws IOException{
        CTEPSAMLRequest samlRequest = new CTEPSAMLRequest();
        samlRequest.setResponseURL(CaaersURL+"/j_acegi_security_check?rand="+System.currentTimeMillis()); // Response URL.
        samlRequest.setHttpRequest(request);
        samlRequest.setHttpResponse(response);
        samlRequest.setIdpURL(idpUrl);
        samlRequest.setIssuer(issuer);
        samlRequest.setCustomCSSURL(cssURL);
        samlRequest.setNeedPersonRoster(true);
        samlRequest.setMiniLoginScreen(true);
        CTEPSSOAuthenticator sso = new CTEPSSOAuthenticator();
        try {
            sso.sendLoginRequest(samlRequest);
        } catch (ZException e) {
            response.sendRedirect(CaaersURL+"/login");
        }

    }

    public boolean  receiveResponse(HttpServletRequest request,
                                 HttpServletResponse response) throws IOException{
        boolean retval = true;
    	try {

             CTEPSSOAuthenticator sso = new CTEPSSOAuthenticator();
            CTEPSAMLResponse samlResponse = sso.getSAMLResponse(request);
            CTEPSSOUser ssoUser = sso.getSSOUser(samlResponse);

            if (ssoUser != null) {
                String ctepId = String.valueOf(ssoUser.getCtepId());
                // Get the details
                request.getSession().setAttribute("SSOUser", ssoUser);
                request.getSession().setAttribute("userName", ssoUser.getUserName());

            }else{
                request.getSession().setAttribute("loginErrorMessage","Error during CTEP login, contact Caaers help desk for assistance!");
                response.sendRedirect(CaaersURL+"/login");
                retval = false;
            }
        } catch (ZException ze) {
            request.getSession().setAttribute("loginErrorMessage","Error loading CTEP login page, contact Caaers help desk for assistance!");
            response.sendRedirect(CaaersURL+"/login");
            retval = false;
        }
    	return retval;

    }

}
