package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.integration.schema.asael.ASAELAgentType;
import gov.nih.nci.cabig.caaers.integration.schema.asael.ASAELType;
import gov.nih.nci.cabig.caaers.integration.schema.asael.ExpectedAECtcTermType;
import gov.nih.nci.cabig.caaers.integration.schema.common.ActiveInactiveStatusType;
import gov.nih.nci.cabig.caaers.integration.schema.common.AgentType;
import gov.nih.nci.cabig.caaers.integration.schema.common.EntityErrorMessageType;
import org.dbunit.operation.DatabaseOperation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ion C. Olaru
 *         Date: 4/3/12 -11:38 AM
 */
public class ASAELServiceImplTest extends DaoTestCase {

    StudyDao studyDao;
    ASAELServiceImpl asaelServiceImpl;
    ASAELType asaelType;

    public void testNotFoundAgent() {
        List<EntityErrorMessageType> l = asaelServiceImpl.execute(asaelType);
        assertNotNull(l);
        assertEquals(2, l.size());
        assertEquals("gov.nih.nci.cabig.caaers.domain.Agent", l.get(0).getKlassName());
        assertEquals("725280", l.get(0).getBusinessIdentifier().substring(0, 6));
        assertEquals("Agent not found.", l.get(0).getMessage().get(0));
    }

    public void testAddOneTerm() throws Exception {
        asaelType.getAsaelAgent().remove(1);
        asaelType.getAsaelAgent().get(0).getAgent().setNscNumber("nsc990");
        asaelType.getAsaelAgent().get(0).getExpectedAECtcTerm().remove(0);
        asaelType.getAsaelAgent().get(0).getExpectedAECtcTerm().remove(0);
        asaelType.getAsaelAgent().get(0).getExpectedAECtcTerm().get(0).setCtepTerm("Ear pain");
        asaelType.getAsaelAgent().get(0).getExpectedAECtcTerm().get(0).setCategory("Category - 01");
        asaelType.getAsaelAgent().get(0).getExpectedAECtcTerm().get(0).setCtcVersion("3");

        Study s = studyDao.getById(-2);
        assertEquals(2, s.getExpectedAECtcTerms().size());

        List<EntityErrorMessageType> l = asaelServiceImpl.execute(asaelType);

        assertEquals(1, l.size());
        assertEquals("", l.get(0).getMessage().get(0));

        assertEquals(3, s.getExpectedAECtcTerms().size());
    }

    public void testDeactivateTerms() throws Exception {
        asaelType.getAsaelAgent().remove(1);
        asaelType.getAsaelAgent().get(0).getAgent().setNscNumber("nsc990");
        asaelType.getAsaelAgent().get(0).getExpectedAECtcTerm().remove(0);
        asaelType.getAsaelAgent().get(0).getExpectedAECtcTerm().remove(0);
        asaelType.getAsaelAgent().get(0).getExpectedAECtcTerm().get(0).setCtepTerm("Nausea");
        asaelType.getAsaelAgent().get(0).getExpectedAECtcTerm().get(0).setStatus(ActiveInactiveStatusType.IN);
        asaelType.getAsaelAgent().get(0).getExpectedAECtcTerm().get(0).setCategory("Category - 01");
        asaelType.getAsaelAgent().get(0).getExpectedAECtcTerm().get(0).setCtcVersion("3");

        Study s = studyDao.getById(-2);
        assertEquals(2, s.getExpectedAECtcTerms().size());

        List<EntityErrorMessageType> l = asaelServiceImpl.execute(asaelType);

        assertEquals(1, l.size());
        assertEquals("", l.get(0).getMessage().get(0));
        assertEquals(1, s.getExpectedAECtcTerms().size());
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        studyDao = (StudyDao)applicationContext.getBean("studyDao");
        asaelServiceImpl = (ASAELServiceImpl)applicationContext.getBean("asaelServiceImpl");
        asaelType = new ASAELType();
        asaelType.setAsaelAgent(new ArrayList<ASAELAgentType>(2));
        asaelType.getAsaelAgent().add(createASAELAgentType());
        asaelType.getAsaelAgent().add(createASAELAgentType());
    }

    private Agent getAnAgent() {
        Agent a = new Agent();
        a.setName("Calcium carbonate");
        a.setNscNumber("725280");
        a.setDescription("Calcium carbonate is a dietary supplement used when the amount of calcium taken in the diet is not enough.");
        return a;
    }

    private ASAELAgentType createASAELAgentType() {
        ASAELAgentType a = new ASAELAgentType();
        a.setAgent(createAnAgentType());
        a.setExpectedAECtcTerm(new ArrayList<ExpectedAECtcTermType>(3));
        a.getExpectedAECtcTerm().add(createExpectedAECtcTermType());
        a.getExpectedAECtcTerm().add(createExpectedAECtcTermType());
        a.getExpectedAECtcTerm().add(createExpectedAECtcTermType());
        return a;
    }

    private ExpectedAECtcTermType createExpectedAECtcTermType() {
        ExpectedAECtcTermType t = new ExpectedAECtcTermType();
        t.setCategory("C1");
        t.setCtcVersion("4");
        t.setCtepTerm("Nausea:" + System.currentTimeMillis());
        t.setStatus(ActiveInactiveStatusType.AC);
        return t;
    }

    private AgentType createAnAgentType() {
        AgentType a = new AgentType();
        a.setName("Calcium carbonate");
        a.setNscNumber("725280:" + System.currentTimeMillis());
        a.setDescriptionText("Calcium carbonate is a dietary supplement used when the amount of calcium taken in the diet is not enough.");
        return a;
    }

/*
    @Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.REFRESH;
    }
*/
}
