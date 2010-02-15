package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

import java.util.Map;

/**
 * @author Ion C. Olaru
 */
public class BasicsTabTest extends WebTestCase {

    private final Log log = LogFactory.getLog(getClass());
    protected BasicsTab tab;
    protected Errors errors;
    protected ReportDefinitionCommand command;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        tab = createTab();
        command = createCommand();
        errors = new BindException(command, "command");
    }

    protected ReportDefinitionCommand createCommand() {
        ReportDefinitionCommand command = new ReportDefinitionCommand();
        return command;
    }

    protected BasicsTab createTab() {
        tab = new BasicsTab();
        return tab;
    }

    public BasicsTab getTab() {
        return tab;
    }

    public Errors getErrors() {
        return errors;
    }

    public void testCreateFieldGroups() {
        Map<String, InputFieldGroup> fieldMap = tab.createFieldGroups(command);
        assertEquals(2, fieldMap.size());

        InputFieldGroup g = fieldMap.get("reportDefinitionFieldGroup");
        assertNotNull(g);
        assertEquals("reportDefinition.header", g.getFields().get(3).getPropertyName());
        assertEquals("reportDefinition.footer", g.getFields().get(4).getPropertyName());
    }

}