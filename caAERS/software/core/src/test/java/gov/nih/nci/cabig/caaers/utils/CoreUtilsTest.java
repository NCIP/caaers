package gov.nih.nci.cabig.caaers.utils;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

public class CoreUtilsTest extends AbstractTestCase {

	private static Logger log = Logger.getLogger(CoreUtilsTest.class);

    public void setUp() {
    }

    public void testFindFile() {
        File f = CoreUtils.findFile("xslt/CaaersCustom.xslt");
        assertNotNull(f);
        assertEquals("CaaersCustom.xslt", f.getName());
    }

}