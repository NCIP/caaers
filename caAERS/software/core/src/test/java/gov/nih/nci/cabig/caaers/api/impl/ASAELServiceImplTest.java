/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.integration.schema.asael.ASAELAgentType;
import gov.nih.nci.cabig.caaers.integration.schema.asael.Asael;
import gov.nih.nci.cabig.caaers.integration.schema.asael.ExpectedAECtcTermType;
import gov.nih.nci.cabig.caaers.integration.schema.common.ActiveInactiveStatusType;
import gov.nih.nci.cabig.caaers.integration.schema.common.AgentType;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.EntityProcessingOutcomeType;
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
    Asael asaelType;

    public void testNotFoundAgent() {
        CaaersServiceResponse csr  = asaelServiceImpl.createOrUpdateASAEL(  asaelType);
        List<EntityProcessingOutcomeType> l = csr.getServiceResponse().getEntityProcessingOutcomes().getEntityProcessingOutcome();
        assertNotNull(l);
        assertEquals(2, l.size());
        assertEquals("gov.nih.nci.cabig.caaers.domain.AgentSpecificTerm", l.get(0).getKlassName());
        assertEquals("725280", l.get(0).getBusinessIdentifier().substring(0, 6));
        assertTrue(l.get(0).getMessage().get(0).contains("Unable to find Agent by NSC :725280"));
    }

    public void testAddOneTerm() throws Exception {
        asaelType.getAsaelAgent().remove(1);
        asaelType.getAsaelAgent().get(0).getAgent().setNscNumber("nsc990");
        asaelType.getAsaelAgent().get(0).getExpectedAECtcTerm().remove(0);
        asaelType.getAsaelAgent().get(0).getExpectedAECtcTerm().remove(0);
        asaelType.getAsaelAgent().get(0).getExpectedAECtcTerm().get(0).setCtepTerm("Ear pain");
        asaelType.getAsaelAgent().get(0).getExpectedAECtcTerm().get(0).setCtepCode("200");
        asaelType.getAsaelAgent().get(0).getExpectedAECtcTerm().get(0).setCategory("Category - 01");
        asaelType.getAsaelAgent().get(0).getExpectedAECtcTerm().get(0).setCtcVersion("3.0");
        asaelType.getAsaelAgent().get(0).getExpectedAECtcTerm().get(0).setOtherToxicity("SOME OTHER TOXICITY TEXT");

        Study s = studyDao.getById(-2);
        assertEquals(2, s.getExpectedAECtcTerms().size());
        CaaersServiceResponse csr = asaelServiceImpl.createOrUpdateASAEL( asaelType);
        List<EntityProcessingOutcomeType> l = csr.getServiceResponse().getEntityProcessingOutcomes().getEntityProcessingOutcome();

        assertEquals(1, l.size());
        assertEquals("Added to add to agent (nsc990) expected term : Ear pain", l.get(0).getMessage().get(0));

        assertEquals(3, s.getExpectedAECtcTerms().size());
    }

    public void testDeactivateTerms() throws Exception {
        asaelType.getAsaelAgent().remove(1);
        asaelType.getAsaelAgent().get(0).getAgent().setNscNumber("nsc990");
        asaelType.getAsaelAgent().get(0).getExpectedAECtcTerm().remove(0);
        asaelType.getAsaelAgent().get(0).getExpectedAECtcTerm().remove(0);
        asaelType.getAsaelAgent().get(0).getExpectedAECtcTerm().get(0).setCtepTerm("Nausea");
        asaelType.getAsaelAgent().get(0).getExpectedAECtcTerm().get(0).setStatus(ActiveInactiveStatusType.INACTIVE);
        asaelType.getAsaelAgent().get(0).getExpectedAECtcTerm().get(0).setCategory("auditory/ear");
        asaelType.getAsaelAgent().get(0).getExpectedAECtcTerm().get(0).setCtcVersion("3.0");

        Study s = studyDao.getById(-2);
        assertEquals(2, s.getExpectedAECtcTerms().size());

        CaaersServiceResponse csr = asaelServiceImpl.createOrUpdateASAEL( asaelType);
        List<EntityProcessingOutcomeType> l = csr.getServiceResponse().getEntityProcessingOutcomes().getEntityProcessingOutcome();

        assertEquals(1, l.size());
        assertEquals("Removed from agent (nsc990) expected term : Nausea", l.get(0).getMessage().get(0));
        assertEquals(1, s.getExpectedAECtcTerms().size());
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        studyDao = (StudyDao)applicationContext.getBean("studyDao");
        asaelServiceImpl = (ASAELServiceImpl)applicationContext.getBean("asaelServiceImpl");
        asaelType = new Asael();
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
        t.setCategory("auditory/ear");
        t.setCtcVersion("3.0");
        t.setCtepTerm("Nausea:" + System.currentTimeMillis());
        t.setStatus(ActiveInactiveStatusType.ACTIVE);
        return t;
    }

    private AgentType createAnAgentType() {
        AgentType a = new AgentType();
        a.setName("Calcium carbonate");
        a.setNscNumber("725280:" + System.currentTimeMillis());
        a.setDescriptionText("Calcium carbonate is a dietary supplement used when the amount of calcium taken in the diet is not enough.");
        return a;
    }


}
