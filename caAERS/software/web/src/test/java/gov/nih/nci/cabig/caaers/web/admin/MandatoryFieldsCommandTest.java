package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.dao.CaaersFieldDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.CaaersFieldDefinition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ion C. Olaru
 */
public class MandatoryFieldsCommandTest extends AbstractTestCase {

    private MandatoryFieldsCommand c;
    private List<CaaersFieldDefinition> mandatoryFields;
    private CaaersFieldDefinitionDao caaersFieldDefinitionDao;
    
    public void setUp() {
        caaersFieldDefinitionDao = registerDaoMockFor(CaaersFieldDefinitionDao.class);
        c = new MandatoryFieldsCommand(caaersFieldDefinitionDao);
        mandatoryFields = new ArrayList<CaaersFieldDefinition>();
        mandatoryFields.add(new CaaersFieldDefinition("T1", "command.field1.name"));
        mandatoryFields.add(new CaaersFieldDefinition("T1", "command.field2.name"));
        mandatoryFields.add(new CaaersFieldDefinition("T2", "command.field9.name"));
    }

    public void testInitializeMandatoryFieldMap() {
        c.setMandatoryFields(mandatoryFields);
        c.initializeMandatoryFieldMap();
        assertEquals(3, c.getMandatoryFieldMap().size());
        assertEquals(0, c.getMandatoryFieldMap().get("command.field1.name").intValue());
        assertEquals(1, c.getMandatoryFieldMap().get("command.field2.name").intValue());
        assertEquals(2, c.getMandatoryFieldMap().get("command.field9.name").intValue());
    }

}