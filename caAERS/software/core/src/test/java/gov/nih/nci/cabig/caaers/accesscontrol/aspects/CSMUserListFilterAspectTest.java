/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.accesscontrol.aspects;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.RoleMembership;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.repository.UserRepository;
import gov.nih.nci.cabig.caaers.security.SecurityTestUtils;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.easymock.EasyMock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Biju Joseph
 */
public class CSMUserListFilterAspectTest extends AbstractTestCase {
    CSMUserListFilterAspect csmUserListFilterAspect;

    UserRepository userRepository;
    public void setUp() throws Exception {
        csmUserListFilterAspect = new CSMUserListFilterAspect();
        userRepository = registerMockFor(UserRepository.class);
        csmUserListFilterAspect.setUserRepository(userRepository);
        SecurityContextHolder.getContext().setAuthentication(null);
        switchToSuperUser();
    }

    public void testApplyFilter() throws Exception {
        assertSame(userRepository, csmUserListFilterAspect.getUserRepository());
        User user = new User() ;
        Map<UserGroupType, RoleMembership> roleMembershipMap = new HashMap<UserGroupType, RoleMembership>();
        user.setRoleMembershipMap(roleMembershipMap);
        EasyMock.expect(userRepository.getUserByLoginName("SYSTEM_ADMIN")).andReturn(user);
        replayMocks();
        List<String> l = new ArrayList<String>();
        l.add("x");
        csmUserListFilterAspect.applyFilter(l);
        assertTrue(l.isEmpty());
        verifyMocks();
    }
}
