package gov.nih.nci.cabig.caaers.web.tags.csm;

import gov.nih.nci.cabig.caaers.web.WebTestCase;
import junit.framework.TestCase;

import javax.servlet.jsp.JspException;

/**
 * @author: Biju Joseph
 */
public class CSMAccessControlTagTest extends WebTestCase {
    CSMAccessControlTag tag;
    public void setUp() throws Exception {
        tag = new CSMAccessControlTag();
    }

    public void testDoStartTagInternalBlankAuthorizationCheckNameAndPrivilege() throws Exception {
        try{
           tag.doStartTagInternal();
            fail("Must throw JspException");
        }catch (JspException e) {

        }
    }

}
