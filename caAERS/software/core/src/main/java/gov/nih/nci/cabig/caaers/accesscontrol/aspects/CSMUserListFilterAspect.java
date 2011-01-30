package gov.nih.nci.cabig.caaers.accesscontrol.aspects;

import gov.nih.nci.cabig.caaers.RoleMembership;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain._User;
import gov.nih.nci.cabig.caaers.domain.repository.UserRepository;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;

import java.util.List;

/**
 * @author: Biju Joseph
 */
public class CSMUserListFilterAspect {

    private UserRepository userRepository;

    public void applyFilter(Object list){
        List csmUserList = (List) list;
        String userName = SecurityUtils.getUserLoginName();
        _User user = userRepository.getUserByLoginName(userName);
        RoleMembership roleMembership = user.getRoleMembershipMap().get(UserGroupType.user_administrator);
        if(roleMembership == null){
            csmUserList.clear();
        }else if(!roleMembership.isAllSite()){
            //BJ: need to filter the records correctly rather than removing everything.
            csmUserList.clear();
        }

        
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
