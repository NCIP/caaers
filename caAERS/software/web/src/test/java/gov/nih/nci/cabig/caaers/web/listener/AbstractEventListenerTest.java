/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.listener;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.accesscontrol.dataproviders.FilteredDataLoader;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.event.*;
import gov.nih.nci.cabig.caaers.security.SecurityTestUtils;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import org.acegisecurity.Authentication;
import org.acegisecurity.event.authentication.AuthenticationSuccessEvent;
import org.easymock.EasyMock;
import org.springframework.context.ApplicationEvent;

/**
 * @author: Biju Joseph
 */
public class AbstractEventListenerTest extends AbstractTestCase {

    AuthenticationSuccessListener listener;
    CourseModificationEventListener courseListener;
    FilteredDataLoader loader;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        loader = registerMockFor(FilteredDataLoader.class);
        listener = new AuthenticationSuccessListener();
        courseListener = new CourseModificationEventListener();

        listener.setEventMonitor(new EventMonitor());
        listener.setFilteredDataLoader(loader);
        courseListener.setEventMonitor(new EventMonitor());
        courseListener.setFilteredDataLoader(loader);
        SecurityTestUtils.switchToSuperuser();
    }

    public void testIsSupported() throws Exception {
        AuthenticationSuccessEvent event = new AuthenticationSuccessEvent(SecurityUtils.getAuthentication());
        assertTrue(listener.isSupported(event));
        assertFalse(listener.isSupported(new StudyModificationEvent(SecurityUtils.getAuthentication(), new LocalStudy())));
    }

    public void testIsSupportedForAllListeners(){


        Authentication a = SecurityUtils.getAuthentication();
        assertTrue(new StudyModificationEventListener().isSupported(new StudyModificationEvent(a, new LocalStudy())));
        assertTrue(new CourseModificationEventListener().isSupported(new CourseModificationEvent(a, new AdverseEvent())));
        assertTrue(new AdverseEventModificationEventListener().isSupported(new AdverseEventModificationEvent(a, new LocalStudy())));
        assertTrue(new ExpeditedReportModificationEventListener().isSupported(new ExpeditedReportModificationEvent(a, new ExpeditedAdverseEventReport())));
        assertTrue(new InvestigatorModificationEventListener().isSupported(new InvestigatorModificationEvent(a, new LocalInvestigator())));
        assertTrue(new OrganizationModificationEventListener().isSupported(new OrganizationModificationEvent(a, new LocalOrganization())));
        assertTrue(new ReportModificationEventListener().isSupported(new ReportModificationEvent(a, new LocalOrganization())));
        assertTrue(new ResearchStaffModificationEventListener().isSupported(new ResearchStaffModificationEvent(a, new LocalOrganization())));
        assertTrue(new SubjectModificationEventListener().isSupported(new SubjectModificationEvent(a, new LocalOrganization())));

        assertFalse(new StudyModificationEventListener().isSupported( new AuthenticationSuccessEvent(SecurityUtils.getAuthentication())));
        assertFalse(new CourseModificationEventListener().isSupported( new AuthenticationSuccessEvent(SecurityUtils.getAuthentication())));
        assertFalse(new AdverseEventModificationEventListener().isSupported( new AuthenticationSuccessEvent(SecurityUtils.getAuthentication())));
        assertFalse(new ExpeditedReportModificationEventListener().isSupported( new AuthenticationSuccessEvent(SecurityUtils.getAuthentication())));
        assertFalse(new InvestigatorModificationEventListener().isSupported( new AuthenticationSuccessEvent(SecurityUtils.getAuthentication())));
        assertFalse(new OrganizationModificationEventListener().isSupported( new AuthenticationSuccessEvent(SecurityUtils.getAuthentication())));
        assertFalse(new ReportModificationEventListener().isSupported( new AuthenticationSuccessEvent(SecurityUtils.getAuthentication())));
        assertFalse(new ResearchStaffModificationEventListener().isSupported( new AuthenticationSuccessEvent(SecurityUtils.getAuthentication())));
        assertFalse(new SubjectModificationEventListener().isSupported( new AuthenticationSuccessEvent(SecurityUtils.getAuthentication())));

    }

    public void testOnApplicationEventUnsupportedEvent() throws  Exception{
        Authentication a = SecurityUtils.getAuthentication();
        replayMocks();
        listener.onApplicationEvent(new EntityModificationEvent(a, new Lab(), null));
        verifyMocks();
    }

    public void testOnApplicationEventCourseModification() throws  Exception{
        Authentication a = SecurityUtils.getAuthentication();
        loader.updateIndexByUserName(a);
        replayMocks();
        courseListener.onApplicationEvent(new CourseModificationEvent(a, new AdverseEventReportingPeriod()));
        verifyMocks();
    }


    public void testOnApplicationEventOnAuthentication() throws  Exception{
        Authentication a = SecurityUtils.getAuthentication();
        AuthenticationSuccessEvent event = new AuthenticationSuccessEvent(a);
        loader.updateIndexByUserName(a);
        replayMocks();
        listener.onApplicationEvent(event);
        verifyMocks();
    }


}
