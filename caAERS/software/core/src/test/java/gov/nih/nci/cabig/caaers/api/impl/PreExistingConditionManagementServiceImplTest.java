package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.PreExistingConditionDao;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.PreExistingCondition;
import gov.nih.nci.cabig.caaers.integration.schema.common.PreExistingConditions;
import junit.framework.TestCase;

import java.util.List;

/**
 * @author Biju Joseph
 */
public class PreExistingConditionManagementServiceImplTest extends CaaersDbNoSecurityTestCase {
    PreExistingConditionManagementServiceImpl impl;
    PreExistingConditionDao dao;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        impl = (PreExistingConditionManagementServiceImpl) getDeployedApplicationContext().getBean("preExistingConditionLOVService");
        dao = impl.getPreExistingConditionDao();
    }

    public void testImportPreExistingConditionsAdd() throws Exception {
        {
            List<PreExistingCondition> l = dao.getAll();
            assertEquals(1, l.size());
            PreExistingConditions xmlPreConds = new PreExistingConditions();
            xmlPreConds.getPreExistingCondition().add(Fixtures.createPreExistingConditionType("Nausea", "111", "Nausea",  "Nausea"));
            xmlPreConds.getPreExistingCondition().add(Fixtures.createPreExistingConditionType("Vomiting", "222", "Vomiting",  "Vomiting"));
            impl.importPreExistingConditions(xmlPreConds);

        }
        interruptSession();
        {
            List<PreExistingCondition> l = dao.getAll();
            assertEquals(2, l.size());
            PreExistingCondition p = fetchByCode(l, "111");
            assertEquals("Nausea", p.getMeddraLlt());
            assertEquals("Nausea", p.getText());
            assertEquals("Nausea", p.getMeddraHlgt());

            p = fetchByCode(l, "222");
            assertEquals("Vomiting", p.getMeddraLlt());
            assertEquals("Vomiting", p.getText());
            assertEquals("Vomiting", p.getMeddraHlgt());
        }
        
    }


    public void testImportPreExistingConditionsEmpty() throws Exception {
        {
            List<PreExistingCondition> l = dao.getAll();
            assertEquals(1, l.size());
            PreExistingConditions xmlPreConds = new PreExistingConditions();
            impl.importPreExistingConditions(xmlPreConds);

        }
        interruptSession();
        {
            List<PreExistingCondition> l = dao.getAll();
            assertEquals(1, l.size());
            PreExistingCondition p = fetchByCode(l, "111");
            assertEquals("Nausea", p.getMeddraLlt());
            assertEquals("Nausea", p.getText());
            assertEquals("Nausea", p.getMeddraHlgt());

        }

    }

    public void testImportPreExistingConditionsUpdate() throws Exception {
        {
            List<PreExistingCondition> l = dao.getAll();
            assertEquals(1, l.size());
            PreExistingConditions xmlPreConds = new PreExistingConditions();
            xmlPreConds.getPreExistingCondition().add(Fixtures.createPreExistingConditionType("Nausea2", "111", "Nausea2",  "Nausea2"));
            impl.importPreExistingConditions(xmlPreConds);

        }
        interruptSession();
        {
            List<PreExistingCondition> l = dao.getAll();
            assertEquals(1, l.size());
            PreExistingCondition p = fetchByCode(l, "111");
            assertEquals("Nausea2", p.getMeddraLlt());
            assertEquals("Nausea2", p.getText());
            assertEquals("Nausea2", p.getMeddraHlgt());


        }

    }

    public PreExistingCondition fetchByCode(List<PreExistingCondition> l, String code){
        for(PreExistingCondition p : l) if(p.getMeddraLltCode().equals(code)) return p;
        return null;
    }

}
