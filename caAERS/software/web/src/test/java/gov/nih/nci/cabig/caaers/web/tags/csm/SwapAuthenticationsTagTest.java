package gov.nih.nci.cabig.caaers.web.tags.csm;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.security.OriginalAuthenticationHolder;
import gov.nih.nci.cabig.caaers.security.SecurityTestUtils;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;

import gov.nih.nci.cabig.caaers.web.WebTestCase;
import org.acegisecurity.Authentication;

import java.io.IOException;
import java.io.Writer;

/**
 * @author: Biju Joseph
 */
public class SwapAuthenticationsTagTest extends WebTestCase {
    SwapAuthenticationsTag tag;
    Authentication auth, orgAuth;
    JspFragment fragment;

    public void setUp() throws Exception {
        super.setUp();

        tag = new SwapAuthenticationsTag();
        tag.setJspContext(pageContext);

        SecurityTestUtils.switchUser("test", "abcd");
        orgAuth = SecurityUtils.getAuthentication();
        OriginalAuthenticationHolder.setAuthentication(orgAuth);

        switchToSuperUser();
        auth = SecurityUtils.getAuthentication();
    }

    public void testIsUseOriginal() throws Exception {
        tag = new SwapAuthenticationsTag();
        assertFalse(tag.isUseOriginal());
        tag.setUseOriginal(true);
        assertTrue(tag.isUseOriginal());
    }

    public void testDoTag() throws Exception {
        {
            fragment = new JspFragment() {
                @Override
                public void invoke(Writer writer) throws JspException, IOException {
                   assertSame(auth, SecurityUtils.getAuthentication());
                }

                @Override
                public JspContext getJspContext() {
                    return pageContext;
                }
            };

            tag.setJspBody(fragment);
            tag.setUseOriginal(false);
            tag.doTag();
            assertSame(auth, SecurityUtils.getAuthentication());
        }

        //use original auth
        {
            fragment = new JspFragment() {
                @Override
                public void invoke(Writer writer) throws JspException, IOException {
                    assertSame(orgAuth, SecurityUtils.getAuthentication());
                }

                @Override
                public JspContext getJspContext() {
                    return pageContext;
                }
            };
            tag.setJspBody(fragment);
            tag.setUseOriginal(true);
            tag.doTag();
            assertSame(auth, SecurityUtils.getAuthentication());
        }
    }
}
