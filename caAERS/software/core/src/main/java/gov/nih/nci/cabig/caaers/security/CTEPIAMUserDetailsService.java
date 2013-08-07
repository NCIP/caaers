package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.security.acegi.authentication.CSMUserDetailsService;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.springframework.dao.DataAccessException;

/**
 * Created with IntelliJ IDEA.
 * User: vijendhar
 * Date: 5/6/13
 * Time: 3:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class CTEPIAMUserDetailsService extends CSMUserDetailsService {

   public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException, DataAccessException {
       /*
       CaaersUser caaersUser = new CaaersUser(userName, "ignored", true, accountNonExpired, true, true, authorities);
       caaersUser.setFirstName(loadedUser.getFirstName());
       caaersUser.setLastName(loadedUser.getLastName());
       return caaersUser;
       */
       return null;
   }

}
