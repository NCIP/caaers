package gov.nih.nci.cabig.caaers.security;

import com.westat.ctsu.sso.ctep.CTEPSSOUser;
import com.westat.ctsu.sso.domain.PersonRoster;
import gov.nih.nci.cabig.caaers.RoleMembership;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.repository.UserRepository;
import org.acegisecurity.Authentication;
import org.acegisecurity.AuthenticationException;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.acegisecurity.ui.AbstractProcessingFilter;
import org.acegisecurity.ui.webapp.AuthenticationProcessingFilter;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: vijendhar
 * Date: 5/7/13
 * Time: 9:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class CTEPIAMAuthenticationProcessingFilter extends AuthenticationProcessingFilter {

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserRepository userRepository;

    public String obtainUsername(HttpServletRequest request) {

        return request.getSession().getAttribute("userName").toString();
    }

    public String obtainPassword(HttpServletRequest request) {
        return "dummyPassword";
    }

    public Authentication attemptAuthentication(HttpServletRequest request) throws AuthenticationException {

        String username = obtainUsername(request);
        String password = obtainPassword(request);

        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        username = username.trim();

        createCSMUser(request);

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

        // Place the last username attempted into HttpSession for views
        request.getSession().setAttribute(ACEGI_SECURITY_FORM_USERNAME_KEY, username);
        request.getSession().setAttribute(ACEGI_SECURITY_FORM_PASSWORD_KEY, password);
        request.getSession().setAttribute(ACEGI_SECURITY_LAST_USERNAME_KEY, username);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private void createCSMUser(HttpServletRequest request) {


        CTEPSSOUser ssoUser = (CTEPSSOUser)request.getSession().getAttribute("SSOUser");

        User user = userRepository.getUserByLoginName(ssoUser.getUserName());

        if ( user == null) { // If the user doesn't exists then create CSM User.

            // Create the Equivalent User in the CSM.
            user = new User();

            user.setFirstName(ssoUser.getFirstName());
            user.setLastName(ssoUser.getLastName());
            user.setLoginName(ssoUser.getUserName());

            List<String> passwordHistory = new ArrayList<String>();
            passwordHistory.add("dummyPassword");
            user.setPasswordHistory(passwordHistory);

            Set<String> nciCodes = new HashSet<String>();
            for (PersonRoster personRoster: ssoUser.getPersonRosters()) {
                nciCodes.add(personRoster.getInstitutionCode());
            }

            Map<UserGroupType, RoleMembership> roleMembershipMap = new HashMap<UserGroupType, RoleMembership>();

            RoleMembership rm = new RoleMembership(UserGroupType.ae_reporter);
            rm.setOrganizationNCICodes(nciCodes);
            rm.setAllStudy(true);
            roleMembershipMap.put(UserGroupType.ae_reporter, rm);

            user.setRoleMembershipMap(roleMembershipMap);

            gov.nih.nci.security.authorization.domainobjects.User csmUser = new gov.nih.nci.security.authorization.domainobjects.User();
            csmUser.setFirstName(ssoUser.getFirstName());
            csmUser.setLastName(ssoUser.getLastName());
            csmUser.setLoginName(ssoUser.getUserName());
            //csmUser.setDepartment("Test Department");
            csmUser.setEmailId(ssoUser.getEmailAddress());
            user.setCsmUser(csmUser);


            userRepository.createOrUpdateUser(user, null);
            userRepository.userChangePassword(user,"dummyPassword",1);
            userRepository.provisionUser(user);
        }

    }

}
