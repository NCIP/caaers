/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.dashboard;

import gov.nih.nci.cabig.caaers.domain.ajax.UserAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.repository.UserRepositoryImpl;
import gov.nih.nci.security.authorization.domainobjects.User;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ion C. Olaru
 * 
 */
public class UsersDashboardController extends DashboardController {

    UserRepositoryImpl userRepository;

    public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("dashboard/dashboard_Users");
        List<User> csmUserList = userRepository.searchCsmUser("", "", "");
        List<UserAjaxableDomainObject> ajaxableUserList = new ArrayList<UserAjaxableDomainObject>();
        UserAjaxableDomainObject ajaxableUser = null;
        for (User csmUser : csmUserList) {
            ajaxableUser = new UserAjaxableDomainObject();
            ajaxableUser.setId(csmUser.getUserId().intValue());
            ajaxableUser.setFirstName(csmUser.getFirstName());
            ajaxableUser.setLastName(csmUser.getLastName());
            ajaxableUser.setUserName(csmUser.getLoginName());
            ajaxableUser.setEmailAddress(csmUser.getEmailId());
            ajaxableUserList.add(ajaxableUser);
        }
        mv.addObject("users", ajaxableUserList);
        return mv;
    }

    public UserRepositoryImpl getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }
}
