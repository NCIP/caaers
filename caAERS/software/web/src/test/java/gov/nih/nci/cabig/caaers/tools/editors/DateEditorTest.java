package gov.nih.nci.cabig.caaers.tools.editors;

import gov.nih.nci.cabig.caaers.utils.DateUtils;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

import java.util.Date;

/**
 * DateEditor Tester.
 *
 * @author Biju Joseph
 * @since <pre>04/05/2010</pre>
 * 
 */
public class DateEditorTest extends TestCase {

    DateEditor de;

    public void setUp() throws Exception {
        super.setUp();
        de = new DateEditor();
    }



    public void testSetAsText() throws Exception {
        de.setAsText("09/09/2009");
        assertEquals("09/09/2009", DateUtils.formatDate((Date)de.getValue()));

        de.setValue(null);
        de.setAsText("09/9/2009");
        assertEquals("09/09/2009", DateUtils.formatDate((Date)de.getValue()));


        de.setValue(null);
        de.setAsText("09/09/09");
        assertEquals("09/09/2009", DateUtils.formatDate((Date)de.getValue()));


        de.setValue(null);
        de.setAsText("09/9/09");
        assertEquals("09/09/2009", DateUtils.formatDate((Date)de.getValue()));


        de.setValue(null);
        de.setAsText("9/9/09");
        assertEquals("09/09/2009", DateUtils.formatDate((Date)de.getValue()));


        de.setValue(null);
        de.setAsText("9/09/09");
        assertEquals("09/09/2009", DateUtils.formatDate((Date)de.getValue()));


        de.setValue(null);
        de.setAsText("9/9/2009");
        assertEquals("09/09/2009", DateUtils.formatDate((Date)de.getValue()));


        de.setValue(null);
        de.setAsText("9/09/09");
        assertEquals("09/09/2009", DateUtils.formatDate((Date)de.getValue()));

         de.setValue(null);
        de.setAsText("9/09/75");
        assertEquals("09/09/1975", DateUtils.formatDate((Date)de.getValue()));

        try {
            de.setValue(null);
            de.setAsText("09/09/9");
            System.out.println(de.getAsText());
            fail("must throw error");
        } catch (IllegalArgumentException e) {
        }


        try {
            de.setValue(null);

            de.setAsText("09/999/09");
            System.out.println(de.getAsText());
            fail("must throw error");
        } catch (IllegalArgumentException e) {
        }



        try {
            de.setValue(null);
            de.setAsText("090/09/2009");
            fail("must throw error");
        } catch (IllegalArgumentException e) {
        }

         try {
            de.setValue(null);
            de.setAsText("090/09/2009/2008");
            fail("must throw error");
        } catch (IllegalArgumentException e) {
        }


    }

    public void testGetAsText() throws Exception {
        de.setAsText("09/09/2009");
        assertEquals("09/09/2009", de.getAsText());
    }

}
