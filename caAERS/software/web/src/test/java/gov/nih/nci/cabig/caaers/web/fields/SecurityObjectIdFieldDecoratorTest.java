package gov.nih.nci.cabig.caaers.web.fields;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.Study;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

/**
 * SecurityObjectIdFieldDecorator Tester.
 *
 * @author Biju Joseph
 * @since <pre>06/21/2010</pre>
 * 
 */
public class SecurityObjectIdFieldDecoratorTest extends AbstractTestCase {
    InputField f1;
    InputField f2;

    SecurityObjectIdFieldDecorator d1;
    SecurityObjectIdFieldDecorator d2;

    public void setUp() throws Exception {
        super.setUp();
        f1 = InputFieldFactory.createTextField("a", "A", true);
        f2 = InputFieldFactory.createTextField("b", "B", true);
        f2.setPrivilegeToModify("x");
        f2.setPrivilegeToRead("y");

        d1 = new SecurityObjectIdFieldDecorator(Integer.class);
        d2 = new SecurityObjectIdFieldDecorator(String.class, "Hello","Hai");
    }

    public void testDecorateWithDefaultPrivileges(){
        d1.decorate(f1);
        d1.decorate(f2);

        assertEquals("java.lang.Integer:READ", f1.getPrivilegeToRead());
        assertEquals("java.lang.Integer:UPDATE", f1.getPrivilegeToModify());

        assertEquals("x", f2.getPrivilegeToModify());
        assertEquals("y", f2.getPrivilegeToRead());

    }


    public void testDecorateWithSpecificPrivileges(){
        d2.decorate(f1);
        d2.decorate(f2);

        assertEquals("java.lang.String:Hello", f1.getPrivilegeToRead());
        assertEquals("java.lang.String:Hai", f1.getPrivilegeToModify());

        assertEquals("x", f2.getPrivilegeToModify());
        assertEquals("y", f2.getPrivilegeToRead());

    }

}
