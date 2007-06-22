package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.ctms.domain.EnumHelper;

/**
 * @author Rhett Sutphin
 */
public class EnumHelperTest extends CaaersTestCase {
    public void testSentenceCaseSingleWordName() throws Exception {
        assertEquals("Frank", EnumHelper.sentenceCasedName(Zappas.FRANK));
    }

    public void testSentenceCaseNameWithUnderscores() throws Exception {
        assertEquals("Moon unit", EnumHelper.sentenceCasedName(Zappas.MOON_UNIT));
    }

    private static enum Zappas {
        FRANK, MOON_UNIT
    }
}
