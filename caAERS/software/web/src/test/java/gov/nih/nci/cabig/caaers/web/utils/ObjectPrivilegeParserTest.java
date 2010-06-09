package gov.nih.nci.cabig.caaers.web.utils;

import junit.framework.TestCase;
import org.apache.taglibs.standard.lang.jstl.BinaryOperatorExpression;
import org.apache.taglibs.standard.lang.jstl.ExpressionString;
import org.apache.taglibs.standard.lang.jstl.parser.ELParser;
import org.apache.taglibs.standard.lang.jstl.parser.ParseException;

import javax.servlet.jsp.el.Expression;
import java.io.StringReader;

/**
 *
 * @author Ion C. Olaru
 *
 */
public class ObjectPrivilegeParserTest extends TestCase {

    public void testParserComposite() {
        ObjectPrivilegeParser opp = new ObjectPrivilegeParser("gov.AdverseEvent#startDate:CREATE && (gov.Study:DELETE || gov.Participant:EDIT)");
        assertEquals(3, opp.getDomainObjectPrivileges().length);

        assertEquals("gov.AdverseEvent#startDate", opp.getDomainObjectPrivileges()[0][0]);
        assertEquals("CREATE", opp.getDomainObjectPrivileges()[0][1]);

        assertEquals("gov.Study", opp.getDomainObjectPrivileges()[1][0]);
        assertEquals("DELETE", opp.getDomainObjectPrivileges()[1][1]);

        assertEquals("gov.Participant", opp.getDomainObjectPrivileges()[2][0]);
        assertEquals("EDIT", opp.getDomainObjectPrivileges()[2][1]);
    }

    public void testParserSimple() {
        ObjectPrivilegeParser opp = new ObjectPrivilegeParser("gov.AdverseEvent:EDIT");
        assertEquals(1, opp.getDomainObjectPrivileges().length);

        assertEquals("gov.AdverseEvent", opp.getDomainObjectPrivileges()[0][0]);
        assertEquals("EDIT", opp.getDomainObjectPrivileges()[0][1]);
    }
}