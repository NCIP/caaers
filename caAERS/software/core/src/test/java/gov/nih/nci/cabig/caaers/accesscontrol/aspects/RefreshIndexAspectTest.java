package gov.nih.nci.cabig.caaers.accesscontrol.aspects;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.accesscontrol.dataproviders.FilteredDataLoader;
import gov.nih.nci.cabig.caaers.security.SecurityTestUtils;
import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;

/**
 * @author: Biju Joseph
 */
public class RefreshIndexAspectTest extends AbstractTestCase {
    FilteredDataLoader filteredDataLoader;
    RefreshIndexAspect aspect;
    public void setUp() throws Exception {
        aspect = new RefreshIndexAspect();
        filteredDataLoader = registerMockFor(FilteredDataLoader.class);
        aspect.setFilteredDataLoader(filteredDataLoader);
        SecurityTestUtils.switchToSuperuser();
    }

    public void testUpdateIndexByUserName() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        filteredDataLoader.updateIndexByUserName(authentication);
        replayMocks();
        aspect.updateIndexByUserName();
        verifyMocks();
    }


    public void testUpdateIndexByUserNameNoAuthentication() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(null);

        replayMocks();
        aspect.updateIndexByUserName();
        verifyMocks();
    }
}
