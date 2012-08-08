package gov.nih.nci.cabig.caaers.web.dashboard;

import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.ajax.UserAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.repository.UserRepositoryImpl;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.security.authorization.domainobjects.User;
import junit.framework.TestCase;
import org.easymock.EasyMock;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Biju Joseph
 */
public class UsersDashboardControllerTest extends WebTestCase {
    UsersDashboardController controller;
    UserRepositoryImpl userRepository;
    List<User> emptyUserList = new ArrayList<User>();
    List<User> csmUserList = new ArrayList<User>();

    public void setUp() throws Exception {
        controller = new UsersDashboardController();
        userRepository = registerMockFor(UserRepositoryImpl.class);
        controller.setUserRepository(userRepository);
        csmUserList.add(Fixtures.createCSMUser(1, "biju", "Joseph"));
        csmUserList.add(Fixtures.createCSMUser(2, "joel", "Biju"));
        csmUserList.add(Fixtures.createCSMUser(3 , "julie", "Mary"));
    }

    public void testHandleRequestInternalNoCSMUserFound() throws Exception {
        EasyMock.expect(userRepository.searchCsmUser("", "", "") ).andReturn(emptyUserList);
        replayMocks();
        ModelAndView mv = controller.handleRequestInternal(request, response);

        List<UserAjaxableDomainObject> ajaxableUserList = ( List<UserAjaxableDomainObject>)mv.getModel().get("users");
        assertTrue(ajaxableUserList.isEmpty());
        verifyMocks();
    }
    public void testHandleRequestInternal() throws Exception {
        EasyMock.expect(userRepository.searchCsmUser("", "", "") ).andReturn(csmUserList);
        replayMocks();
        ModelAndView mv = controller.handleRequestInternal(request, response);

        List<UserAjaxableDomainObject> ajaxableUserList = ( List<UserAjaxableDomainObject>)mv.getModel().get("users");
        assertEquals(3, ajaxableUserList.size());
        assertEquals("Joseph", ajaxableUserList.get(0).getFirstName());
        assertEquals("Biju", ajaxableUserList.get(1).getFirstName());
        assertEquals("Mary", ajaxableUserList.get(2).getFirstName());

        assertEquals("biju", ajaxableUserList.get(0).getUserName());
        assertEquals("joel", ajaxableUserList.get(1).getUserName());
        assertEquals("julie", ajaxableUserList.get(2).getUserName());
        
        UserAjaxableDomainObject u = new UserAjaxableDomainObject();
        u.setId(1);
        u.setUserName("biju");
        u.setFirstName("Joseph");
        u.setLastName("Joseph");
        u.setEmailAddress("biju@test.com");
        assertTrue(u.equals(ajaxableUserList.get(0)));
        assertEquals(u.hashCode(), ajaxableUserList.get(0).hashCode());

        verifyMocks();
    }
}
