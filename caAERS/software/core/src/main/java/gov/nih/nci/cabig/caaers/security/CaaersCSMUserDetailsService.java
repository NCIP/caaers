package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.acegi.csm.authorization.CSMUserDetailsService;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.acegisecurity.AuthenticationServiceException;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.userdetails.User;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;

public class CaaersCSMUserDetailsService extends CSMUserDetailsService{
	
	private static final Log logger = LogFactory.getLog(CaaersCSMUserDetailsService.class);
	
	public UserDetails loadUserByUsername(String userName)
    throws UsernameNotFoundException, DataAccessException {
	logger.debug((new StringBuilder()).append("Getting user details for ").append(userName).toString());
    GrantedAuthority authorities[] = null;
    UserProvisioningManager mgr = getCsmUserProvisioningManager();
    Set groups;
    boolean accountNonExpired = true;
    Date today = new Date();
    try {
        gov.nih.nci.security.authorization.domainobjects.User loadedUser = mgr.getUser(userName);
        if(loadedUser == null){
            throw new UsernameNotFoundException("User does not exist in CSM.");
        }else{
        	if(loadedUser.getEndDate() != null){
        		if(DateUtils.compareDate(today,loadedUser.getEndDate()) <= 0 ){
        			accountNonExpired = true;
        		}else{
        			accountNonExpired = false;
        		}
        	}
        }
        logger.debug((new StringBuilder()).append("Retrieved user obj ").append(loadedUser).append(" with ID ").append(loadedUser != null ? ((Object) (loadedUser.getUserId())) : "<null>").toString());
        groups = mgr.getGroups(loadedUser.getUserId().toString());
    }
    catch(CSObjectNotFoundException ex) {
        throw new AuthenticationServiceException((new StringBuilder()).append("Error getting groups: ").append(ex.getMessage()).toString(), ex);
    }
    if(groups == null || groups.size() == 0) {
        authorities = new GrantedAuthority[0];
    } else {
        String prefix = getRolePrefix();
        if(prefix == null)
            prefix = "";
        authorities = new GrantedAuthority[groups.size()];
        int idx = 0;
        for(Iterator i = groups.iterator(); i.hasNext();) {
            Group group = (Group)i.next();
            authorities[idx] = new GrantedAuthorityImpl((new StringBuilder()).append(prefix).append(group.getGroupName()).toString());
            idx++;
        }

    }
    return new User(userName, "ignored", true, accountNonExpired, true, true, authorities);
}

	
	
}
