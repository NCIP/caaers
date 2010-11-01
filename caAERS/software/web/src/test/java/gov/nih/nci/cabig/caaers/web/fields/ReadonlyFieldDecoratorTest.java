package gov.nih.nci.cabig.caaers.web.fields;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacadeImpl;
import gov.nih.nci.cabig.caaers.security.SecurityTestUtils;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;
import org.acegisecurity.Authentication;
import org.easymock.classextension.EasyMock;

/**
 * ReadonlyFieldDecorator Tester.
 *
 * @author Biju Joseph
 * @since <pre>06/21/2010</pre>
 * 
 */
public class ReadonlyFieldDecoratorTest extends AbstractTestCase {
    InputField f1;
    InputField f2;

    CaaersSecurityFacade facadeImpl;

    public void setUp() throws Exception {
        super.setUp();
        f1 = InputFieldFactory.createTextField("a", "A", true);
        f1.setPrivilegeToModify("i:READ");
        f1.setPrivilegeToRead("m:READ");
        
        f2 = InputFieldFactory.createTextField("b", "B", true);
        f2.setPrivilegeToModify("x");
        f2.setPrivilegeToRead("y");

        facadeImpl = registerMockFor(CaaersSecurityFacade.class);
        SecurityTestUtils.switchToCaaersSecurityFacadeMock(facadeImpl);

    }

    @Override
    protected void tearDown() throws Exception {

        SecurityTestUtils.switchToCaaersSecurityFacade();
         super.tearDown();
    }
    

    public void testDecorate(){
        assertNotNull(CaaersSecurityFacadeImpl.getInstance());
        ReadonlyFieldDecorator d1 = new ReadonlyFieldDecorator();
        Authentication a = SecurityUtils.getAuthentication();

        EasyMock.expect(facadeImpl.checkAuthorization(a, "i:READ")).andReturn(true);
        EasyMock.expect(facadeImpl.checkAuthorization(a, "m:READ")).andReturn(true);

        EasyMock.expect(facadeImpl.checkAuthorization(a, "x")).andReturn(false);
        EasyMock.expect(facadeImpl.checkAuthorization(a, "y")).andReturn(false);

        replayMocks();
        d1.decorate(f1);
        d1.decorate(f2);

        assertTrue(f1.isModifiable());
        assertTrue(f1.isReadable());

        assertFalse(f2.isModifiable());
        assertFalse(f2.isReadable());

        verifyMocks();
    }



    public void testDecorateWhenAlreadyNotReadableOrModifiable(){
        assertNotNull(CaaersSecurityFacadeImpl.getInstance());
        ReadonlyFieldDecorator d1 = new ReadonlyFieldDecorator();
        Authentication a = SecurityUtils.getAuthentication();

        f1.setReadable(false);
        f1.setModifiable(false);
        
        EasyMock.expect(facadeImpl.checkAuthorization(a, "x")).andReturn(false);
        EasyMock.expect(facadeImpl.checkAuthorization(a, "y")).andReturn(false);

        replayMocks();
        d1.decorate(f1);
        d1.decorate(f2);

        assertFalse(f1.isModifiable());
        assertFalse(f1.isReadable());

        assertFalse(f2.isModifiable());
        assertFalse(f2.isReadable());

        verifyMocks();
    }


}
