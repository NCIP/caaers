package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.domain.LocalStudy;
import junit.framework.TestCase;

/**
 * @author: Biju Joseph
 */
public class CurrentEntityHolderTest extends TestCase {

    public void testGetEntityCacheKeyDiscriminator_bare() throws Exception {
        LocalStudy s = new LocalStudy();
        s.setId(1);
        CurrentEntityHolder.setEntity(s);
        assertEquals("gov.nih.nci.cabig.caaers.domain.LocalStudy_1", CurrentEntityHolder.getEntityCacheKeyDiscriminator());

    }

    public void testGetEntityCacheKeyDiscriminator() throws Exception {

        assertEquals("java.lang.String", CurrentEntityHolder.getEntityCacheKeyDiscriminator(""));
        LocalStudy s = new LocalStudy();
        s.setId(1);

        assertEquals("gov.nih.nci.cabig.caaers.domain.LocalStudy_1", CurrentEntityHolder.getEntityCacheKeyDiscriminator(s));

    }
}
