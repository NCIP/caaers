package gov.nih.nci.cabig.caaers.rules.common;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

/**
 * CategoryConfiguration Tester.
 *
 * @author Biju Joseph
 * @since <pre>05/18/2010</pre>
 * 
 */
public class CategoryConfigurationTest extends TestCase {
    public CategoryConfigurationTest(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
    }

    public void testFindByPath(){
        CategoryConfiguration c = CategoryConfiguration.findByPath("/CAAERS_BASE/SPONSOR");
        assertSame(CategoryConfiguration.SPONSOR_BASE, c);
    }

    public void testFindByPackagePrefix(){
       CategoryConfiguration c = CategoryConfiguration.findByPackagePrefix("gov.nih.nci.cabig.caaers.rules.sponsor");
        assertSame(CategoryConfiguration.SPONSOR_BASE, c);
    }
}
