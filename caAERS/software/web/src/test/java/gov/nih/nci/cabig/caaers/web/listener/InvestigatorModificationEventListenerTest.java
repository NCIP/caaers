/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.listener;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.accesscontrol.dataproviders.FilteredDataLoader;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.LocalInvestigator;
import gov.nih.nci.cabig.caaers.event.InvestigatorModificationEvent;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade;
import gov.nih.nci.cabig.caaers.security.SecurityTestUtils;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import org.acegisecurity.Authentication;

/**
 * @author: Biju Joseph
 */
public class InvestigatorModificationEventListenerTest extends AbstractTestCase {
    InvestigatorModificationEventListener listener;
    FilteredDataLoader loader;
    CaaersSecurityFacade caaersSecurityFacade;


    public void setUp() throws Exception {
        loader = registerMockFor(FilteredDataLoader.class);
        caaersSecurityFacade = registerMockFor(CaaersSecurityFacade.class);
        listener = new InvestigatorModificationEventListener();
        listener.setEventMonitor(new EventMonitor());
        listener.setFilteredDataLoader(loader);
        listener.setCaaersSecurityFacade(caaersSecurityFacade);

        SecurityTestUtils.switchToSuperuser();
    }

    public void testOnApplicationEvent() throws Exception {
        Authentication a = SecurityUtils.getAuthentication();
        loader.updateIndexByUserName(a);
        caaersSecurityFacade.clearUserCache("test");
        replayMocks();
        Investigator investigator = Fixtures.createInvestigator("test");
        investigator.setCaaersUser(Fixtures.createUser("test", "test"));
        listener.onApplicationEvent(new InvestigatorModificationEvent(a, investigator));
        verifyMocks();
    }


    public void testOnApplicationEventInvestigatorPerson() throws Exception {
        Authentication a = SecurityUtils.getAuthentication();
        loader.updateIndexByUserName(a);
        replayMocks();
        listener.onApplicationEvent(new InvestigatorModificationEvent(a, new LocalInvestigator()));
        verifyMocks();
    }
}
