/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.accesscontrol.aspects;

import gov.nih.nci.cabig.caaers.RoleMembership;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.User;
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
        User user = userRepository.getUserByLoginName(userName);
        RoleMembership roleMembership = user.getRoleMembershipMap().get(UserGroupType.user_administrator);
        if(roleMembership == null){
            csmUserList.clear();
        }else if(!roleMembership.isAllSite()){
            //BJ: need to filter the records correctly rather than removing everything.
        }

        
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
