package gov.nih.nci.cabig.caaers.rules.common;

import junit.framework.TestCase;

import com.semanticbits.rules.utils.NumberUtil;

public class NumberUtilTest extends TestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCompare() {
        int i1 = 5;
        int i2 = 3;

        int r1 = NumberUtil.compare(i1, i2);
        int r2 = NumberUtil.compare(i2, i1);
        int r3 = NumberUtil.compare(3, 3);

        assertTrue(r1 < 0);
        assertTrue(r2 > 0);
        assertTrue(r3 == 0);

    }

}
