/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.rules.business.service;

import gov.nih.nci.cabig.caaers.domain.*;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import com.semanticbits.rules.objectgraph.FactResolver;

import junit.framework.TestCase;
/**
 * Test cover the following requirement. 
 * CAAERS-2861 - "If attribution is NULL in capture AE, run rules as if it were "possible"
 * CAAERS-3212 - Verifying all the Study related conditions present in CTEP SAE Rule #7
 * 
 * @author Biju Joseph
 *
 */
public class FactResolverTest extends TestCase {
	FactResolver resolver;
	AdverseEvent ae;
	Study localStudy;
	Study remoteStudy;
	
	Organization localOrganization;
	Organization remoteOrganization;
	
	StudyFundingSponsor localSponsor;
	StudyFundingSponsor remoteSponsor;
    ExpeditedAdverseEventReport aeReport;
	
	protected void setUp() throws Exception {
		super.setUp();
		ae = new AdverseEvent();
		ae.setAttributionSummary(Attribution.DEFINITE);
        aeReport = Fixtures.createSavableExpeditedReport();
		resolver = new FactResolver();
		
		localStudy = Fixtures.createStudy("test");
		localOrganization = Fixtures.createOrganization("Cancer Therapy Evaluation Program", "CTEP");
		localSponsor = Fixtures.createStudyFundingSponsor(localOrganization);
		
		remoteStudy = Fixtures.createRemoteStudy("test");
		remoteOrganization = Fixtures.createRemoteOrganization("Cancer Therapy Evaluation Program", "CTEP");
		remoteSponsor = Fixtures.createStudyFundingSponsor(remoteOrganization);
	}
	
	
	public void testAssertFact() throws Exception{
		boolean fact = resolver.assertFact(ae,"gov.nih.nci.cabig.caaers.domain.Attribution","name","DEFINITE","==");
		assertTrue(fact);
	}

    public void testAssertFactOnOutcome() throws Exception {
        Outcome o = Fixtures.createOutcome(2, OutcomeType.LIFE_THREATENING) ;
        ae.addOutcome(o);
        boolean fact = resolver.assertFact(ae, "gov.nih.nci.cabig.caaers.domain.OutcomeType", "code", "2" , "==" );
        assertTrue(fact);
        fact = resolver.assertFact(ae, "gov.nih.nci.cabig.caaers.domain.OutcomeType", "code", "3" , "==" );
        assertFalse(fact);
    }
	

	public void testAssertFact_Negative() throws Exception{
		boolean fact = resolver.assertFact(ae,"gov.nih.nci.cabig.caaers.domain.Attribution","name","UNLIKELY","!=");
		assertTrue(fact);
	}
	
	public void testAssertFact_TargetObjectIsNull() throws Exception{
		ae.setAttributionSummary(null);
		boolean fact = resolver.assertFact(ae,"gov.nih.nci.cabig.caaers.domain.Attribution","name","DEFINITE","==");
		assertFalse(fact);
	}
	
	public void testAssertFact_Negative_TargetObjectIsNull() throws Exception{
		ae.setAttributionSummary(null);
		boolean fact = resolver.assertFact(ae,"gov.nih.nci.cabig.caaers.domain.Attribution","name","UNLIKELY","!=");
		assertTrue(fact);
	}
	
	
	/*
	 * Testing : assertFact(studySDO,"gov.nih.nci.cabig.caaers.domain.Organization","name","Cancer Therapy Evaluation Program","==")
	 * On LocalStudy whose Sponsor is LocalOrganization
	 */
	public void testAssertFact_OnLocalStudy_LocalSponsorOrganization() throws Exception{
		//factResolver.assertFact(studySDO,"gov.nih.nci.cabig.caaers.domain.Organization","name","Cancer Therapy Evaluation Program","==")
		localStudy.addStudyOrganization(localSponsor);
		boolean fact = resolver.assertFact(localStudy,"gov.nih.nci.cabig.caaers.domain.Organization","name","Cancer Therapy Evaluation Program","==");
		assertTrue(fact);
		
		fact = resolver.assertFact(localStudy,"gov.nih.nci.cabig.caaers.domain.Organization","name","Wrong Name","==");
		assertFalse(fact);
	}
	
	/*
	 * Testing : assertFact(studySDO,"gov.nih.nci.cabig.caaers.domain.Organization","name","Cancer Therapy Evaluation Program","==")
	 * On LocalStudy whose Sponsor is RemoteOrganization
	 */
	public void testAssertFact_OnLocalStudy_RemoteSponsorOrganization() throws Exception{
		//factResolver.assertFact(studySDO,"gov.nih.nci.cabig.caaers.domain.Organization","name","Cancer Therapy Evaluation Program","==")
		localStudy.addStudyOrganization(remoteSponsor);
		boolean fact = resolver.assertFact(localStudy,"gov.nih.nci.cabig.caaers.domain.Organization","name","Cancer Therapy Evaluation Program","==");
		assertTrue(fact);
		
		fact = resolver.assertFact(localStudy,"gov.nih.nci.cabig.caaers.domain.Organization","name","Wrong Name","==");
		assertFalse(fact);
	}
	
	/*
	 * Testing : assertFact(studySDO,"gov.nih.nci.cabig.caaers.domain.Organization","name","Cancer Therapy Evaluation Program","==")
	 * On RemoteStudy whose Sponsor is LocalOrganization
	 */
	public void testAssertFact_OnRemoteStudy_LocalSponsorOrganization() throws Exception{
		//factResolver.assertFact(studySDO,"gov.nih.nci.cabig.caaers.domain.Organization","name","Cancer Therapy Evaluation Program","==")
		remoteStudy.addStudyOrganization(localSponsor);
		boolean fact = resolver.assertFact(remoteStudy,"gov.nih.nci.cabig.caaers.domain.Organization","name","Cancer Therapy Evaluation Program","==");
		assertTrue(fact);
		
		fact = resolver.assertFact(localStudy,"gov.nih.nci.cabig.caaers.domain.Organization","name","Wrong Name","==");
		assertFalse(fact);
	}
	
	/*
	 * Testing : assertFact(studySDO,"gov.nih.nci.cabig.caaers.domain.Organization","name","Cancer Therapy Evaluation Program","==")
	 * On RemoteStudy whose Sponsor is RemoteOrganization
	 */
	public void testAssertFact_OnRemoteStudy_RemoteSponsorOrganization() throws Exception{
		//factResolver.assertFact(studySDO,"gov.nih.nci.cabig.caaers.domain.Organization","name","Cancer Therapy Evaluation Program","==")
		remoteStudy.addStudyOrganization(remoteSponsor);
		boolean fact = resolver.assertFact(remoteStudy,"gov.nih.nci.cabig.caaers.domain.Organization","name","Cancer Therapy Evaluation Program","==");
		assertTrue(fact);
		
		fact = resolver.assertFact(localStudy,"gov.nih.nci.cabig.caaers.domain.Organization","name","Wrong Name","==");
		assertFalse(fact);
	}
	
	
	/*
	 * Testing : assertFact(study,null,'phaseCode','Phase III Trial','==')
	 * On LocalStudy phase code check
	 */
	public void testAssertFact_OnLocalStudy_VerifyPhaseCode() throws Exception{
		//factResolver.assertFact(study,null,'phaseCode','Phase III Trial','==')
		localStudy.setPhaseCode("Phase III Trial");
		boolean fact = resolver.assertFact(localStudy,null,"phaseCode","Phase III Trial","==");
		assertTrue(fact);
		
		fact = resolver.assertFact(localStudy,null, "phaseCode","Wrong Name","==");
		assertFalse(fact);
	}
	
	/*
	 * Testing : assertFact(study,null,'phaseCode','Phase III Trial','==')
	 * On LocalStudy phase code check
	 */
	public void testAssertFact_OnRemoteStudy_VerifyPhaseCode() throws Exception{
		//factResolver.assertFact(study,null,'phaseCode','Phase III Trial','==')
		remoteStudy.setPhaseCode("Phase III Trial");
		boolean fact = resolver.assertFact(remoteStudy,null,"phaseCode","Phase III Trial","==");
		assertTrue(fact);
		
		fact = resolver.assertFact(localStudy,null, "phaseCode","Wrong Name","==");
		assertFalse(fact);
	}
	
	/*
	 * Testing : factResolver.assertFact(study,'gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug','holderName','runTimeValue','runTimeOperator')
	 * On LocalStudy IND Holder check (LocalHolder)
	 */
	public void testAssertFact_OnLocalStudy_LocalINDHolder() throws Exception{
		//factResolver.assertFact(study,'gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug','holderName','runTimeValue','runTimeOperator')
		StudyAgent agent = Fixtures.createStudyAgent("test");
		INDHolder indHolder = Fixtures.createOrganizationINDHolder(localOrganization);
		InvestigationalNewDrug ind = Fixtures.createInvestigationalNewDrug(indHolder, "-2");
	
		StudyAgentINDAssociation studyAgentIndAssociation = new StudyAgentINDAssociation();
		studyAgentIndAssociation.setStudyAgent(agent);
		studyAgentIndAssociation.setInvestigationalNewDrug(ind);
		
		agent.addStudyAgentINDAssociation(studyAgentIndAssociation);
		
		localStudy.addStudyAgent(agent);
		
		boolean fact = resolver.assertFact(localStudy,"gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug","holderName","Cancer Therapy Evaluation Program","==");
		assertTrue(fact);
		
		fact = resolver.assertFact(localStudy,"gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug","holderName","Wrong Name","==");
		assertFalse(fact);
	}


    public void testIsOnlyYesOnInvestigationalNewDrug() throws  Exception{


        {
            StudyAgent agent = Fixtures.createStudyAgent("test");
            INDHolder indHolder = Fixtures.createOrganizationINDHolder(localOrganization);
            InvestigationalNewDrug ind = Fixtures.createInvestigationalNewDrug(indHolder, "-2");

            StudyAgentINDAssociation studyAgentIndAssociation = new StudyAgentINDAssociation();
            studyAgentIndAssociation.setStudyAgent(agent);
            studyAgentIndAssociation.setInvestigationalNewDrug(ind);

            agent.addStudyAgentINDAssociation(studyAgentIndAssociation);

            localStudy.addStudyAgent(agent);
            assertTrue(agent.getInvestigationalNewDrugIndicator());
            boolean fact = resolver.assertFact(localStudy,"gov.nih.nci.cabig.caaers.domain.StudyAgent","investigationalNewDrugIndicator","true","==");
            assertTrue(fact);
            fact = resolver.assertFact(localStudy,"gov.nih.nci.cabig.caaers.domain.StudyAgent","investigationalNewDrugIndicator","true","isOnly");
            assertTrue(fact)   ;
        }

        {
            StudyAgent agent = Fixtures.createStudyAgent("test");
            INDHolder indHolder = Fixtures.createOrganizationINDHolder(localOrganization);
            InvestigationalNewDrug ind = Fixtures.createInvestigationalNewDrug(indHolder, "-2");

            StudyAgentINDAssociation studyAgentIndAssociation = new StudyAgentINDAssociation();
            studyAgentIndAssociation.setStudyAgent(agent);
            studyAgentIndAssociation.setInvestigationalNewDrug(ind);

            agent.addStudyAgentINDAssociation(studyAgentIndAssociation);

            localStudy.addStudyAgent(agent);
            localStudy.addStudyAgent(agent);
            assertTrue(agent.getInvestigationalNewDrugIndicator());
            boolean fact = resolver.assertFact(localStudy,"gov.nih.nci.cabig.caaers.domain.StudyAgent","investigationalNewDrugIndicator","true","==");
            assertTrue(fact);
            fact = resolver.assertFact(localStudy,"gov.nih.nci.cabig.caaers.domain.StudyAgent","investigationalNewDrugIndicator","true","isOnly");
            assertTrue(fact)   ;
        }

        {
            StudyAgent agent = Fixtures.createStudyAgent("test");
            INDHolder indHolder = Fixtures.createOrganizationINDHolder(localOrganization);
            InvestigationalNewDrug ind = Fixtures.createInvestigationalNewDrug(indHolder, "-2");

            StudyAgentINDAssociation studyAgentIndAssociation = new StudyAgentINDAssociation();
            studyAgentIndAssociation.setStudyAgent(agent);
            studyAgentIndAssociation.setInvestigationalNewDrug(ind);

            agent.addStudyAgentINDAssociation(studyAgentIndAssociation);

            localStudy.addStudyAgent(agent);
            localStudy.addStudyAgent(Fixtures.createStudyAgent("x"));
            assertTrue(agent.getInvestigationalNewDrugIndicator());
            boolean fact = resolver.assertFact(localStudy,"gov.nih.nci.cabig.caaers.domain.StudyAgent","investigationalNewDrugIndicator","true","==");
            assertTrue(fact);

            fact = resolver.assertFact(localStudy,"gov.nih.nci.cabig.caaers.domain.StudyAgent","investigationalNewDrugIndicator","true","isOnly");
//            assertTrue(fact);
        }


    }
	
	/*
	 * Testing : factResolver.assertFact(study,'gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug','holderName','runTimeValue','runTimeOperator')
	 * On LocalStudy IND Holder check(Remote Holder)
	 */
	public void testAssertFact_OnLocalStudy_RemoteINDHolder() throws Exception{
		//factResolver.assertFact(study,'gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug','holderName','runTimeValue','runTimeOperator')
		StudyAgent agent = Fixtures.createStudyAgent("test");
		INDHolder indHolder = Fixtures.createOrganizationINDHolder(remoteOrganization);
		InvestigationalNewDrug ind = Fixtures.createInvestigationalNewDrug(indHolder, "-2");
	
		StudyAgentINDAssociation studyAgentIndAssociation = new StudyAgentINDAssociation();
		studyAgentIndAssociation.setStudyAgent(agent);
		studyAgentIndAssociation.setInvestigationalNewDrug(ind);
		
		agent.addStudyAgentINDAssociation(studyAgentIndAssociation);
		
		localStudy.addStudyAgent(agent);
		
		boolean fact = resolver.assertFact(localStudy,"gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug","holderName","Cancer Therapy Evaluation Program","==");
		assertTrue(fact);
		
		fact = resolver.assertFact(localStudy,"gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug","holderName","Wrong Name","==");
		assertFalse(fact);
	}
	

	/*
	 * Testing : factResolver.assertFact(study,'gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug','holderName','runTimeValue','runTimeOperator')
	 * On RemoteStudy IND Holder check (LocalHolder)
	 */
	public void testAssertFact_OnRemoteStudy_LocalINDHolder() throws Exception{
		//factResolver.assertFact(study,'gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug','holderName','runTimeValue','runTimeOperator')
		StudyAgent agent = Fixtures.createStudyAgent("test");
		INDHolder indHolder = Fixtures.createOrganizationINDHolder(localOrganization);
		InvestigationalNewDrug ind = Fixtures.createInvestigationalNewDrug(indHolder, "-2");
	
		StudyAgentINDAssociation studyAgentIndAssociation = new StudyAgentINDAssociation();
		studyAgentIndAssociation.setStudyAgent(agent);
		studyAgentIndAssociation.setInvestigationalNewDrug(ind);
		
		agent.addStudyAgentINDAssociation(studyAgentIndAssociation);
		
		remoteStudy.addStudyAgent(agent);
		
		boolean fact = resolver.assertFact(remoteStudy,"gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug","holderName","Cancer Therapy Evaluation Program","==");
		assertTrue(fact);
		
		fact = resolver.assertFact(remoteStudy,"gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug","holderName","Wrong Name","==");
		assertFalse(fact);
	}
	
	/*
	 * Testing : factResolver.assertFact(study,'gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug','holderName','runTimeValue','runTimeOperator')
	 * On RemoteStudy IND Holder check(Remote Holder)
	 */
	public void testAssertFact_OnRemoteStudy_RemoteINDHolder() throws Exception{
		//factResolver.assertFact(study,'gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug','holderName','runTimeValue','runTimeOperator')
		StudyAgent agent = Fixtures.createStudyAgent("test");
		INDHolder indHolder = Fixtures.createOrganizationINDHolder(remoteOrganization);
		InvestigationalNewDrug ind = Fixtures.createInvestigationalNewDrug(indHolder, "-2");
	
		StudyAgentINDAssociation studyAgentIndAssociation = new StudyAgentINDAssociation();
		studyAgentIndAssociation.setStudyAgent(agent);
		studyAgentIndAssociation.setInvestigationalNewDrug(ind);
		
		agent.addStudyAgentINDAssociation(studyAgentIndAssociation);
		
		remoteStudy.addStudyAgent(agent);
		
		boolean fact = resolver.assertFact(remoteStudy,"gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug","holderName","Cancer Therapy Evaluation Program","==");
		assertTrue(fact);
		
		fact = resolver.assertFact(remoteStudy,"gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug","holderName","Wrong Name","==");
		assertFalse(fact);
	}
	
	/*
	 * Testing : factResolver.assertFact(study,'gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug','holderName','runTimeValue','runTimeOperator')
	 * On CGLIBEnancedStudy IND Holder check (LocalHolder)
	 */
	public void testAssertFact_CGLIBStudy_LocalINDHolder() throws Exception{
		//factResolver.assertFact(study,'gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug','holderName','runTimeValue','runTimeOperator')
		Study cglibStudy = createCGLIBProxyStudy(Study.class, "cglib test");
		StudyAgent agent = Fixtures.createStudyAgent("test");
		INDHolder indHolder = Fixtures.createOrganizationINDHolder(localOrganization);
		InvestigationalNewDrug ind = Fixtures.createInvestigationalNewDrug(indHolder, "-2");
	
		StudyAgentINDAssociation studyAgentIndAssociation = new StudyAgentINDAssociation();
		studyAgentIndAssociation.setStudyAgent(agent);
		studyAgentIndAssociation.setInvestigationalNewDrug(ind);
		
		agent.addStudyAgentINDAssociation(studyAgentIndAssociation);
		
		cglibStudy.addStudyAgent(agent);
		
		boolean fact = resolver.assertFact(cglibStudy,"gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug","holderName","Cancer Therapy Evaluation Program","==");
		assertTrue(fact);
		
		fact = resolver.assertFact(cglibStudy,"gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug","holderName","Wrong Name","==");
		assertFalse(fact);
	}
	

	/*
	 * Testing : factResolver.assertFact(study,'gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug','holderName','runTimeValue','runTimeOperator')
	 * On CGLIBEnanced LocalStudy IND Holder check (LocalHolder)
	 */
	public void testAssertFact_CGLIBLocalStudy_LocalINDHolder() throws Exception{
		//factResolver.assertFact(study,'gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug','holderName','runTimeValue','runTimeOperator')
		Study cglibStudy = createCGLIBProxyStudy(LocalStudy.class, "cglib test");
		StudyAgent agent = Fixtures.createStudyAgent("test");
		INDHolder indHolder = Fixtures.createOrganizationINDHolder(localOrganization);
		InvestigationalNewDrug ind = Fixtures.createInvestigationalNewDrug(indHolder, "-2");
	
		StudyAgentINDAssociation studyAgentIndAssociation = new StudyAgentINDAssociation();
		studyAgentIndAssociation.setStudyAgent(agent);
		studyAgentIndAssociation.setInvestigationalNewDrug(ind);
		
		agent.addStudyAgentINDAssociation(studyAgentIndAssociation);
		
		cglibStudy.addStudyAgent(agent);
		
		boolean fact = resolver.assertFact(cglibStudy,"gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug","holderName","Cancer Therapy Evaluation Program","==");
		assertTrue(fact);
		
		fact = resolver.assertFact(cglibStudy,"gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug","holderName","Wrong Name","==");
		assertFalse(fact);
	}


    public void testAssertFact_OnObservedAEProfile() throws  Exception {

        TreatmentAssignment ta = Fixtures.createTreatmentAssignment();
        Study s = Fixtures.createStudy("x");
        s.setId(2);
        s.addTreatmentAssignment(ta);

        ObservedAdverseEventProfile observedAE1 = new ObservedAdverseEventProfile();
        observedAE1.setLowLevelTerm(Fixtures.createLowLevelTerm("abcd","efg"));
        observedAE1.setGrade(Grade.LIFE_THREATENING);
        observedAE1.setObservedSignificance(0.9);
        observedAE1.setpValue(0.9);
        observedAE1.setTreatmentAssignment(ta);

        boolean fact = resolver.assertFact(observedAE1, "gov.nih.nci.cabig.caaers.domain.Grade","code","3",">=");
        assertTrue(fact);

        boolean fact2 = resolver.assertFact(observedAE1, "gov.nih.nci.cabig.caaers.domain.ObservedAdverseEventSignificanceLevel","significance","0.8",">");
        assertTrue(fact2);

        fact2 = resolver.assertFact(observedAE1, "gov.nih.nci.cabig.caaers.domain.ObservedAdverseEventSignificanceLevel","significance","0.9","==");
        assertTrue(fact2);

        fact2 = resolver.assertFact(observedAE1, "gov.nih.nci.cabig.caaers.domain.ObservedAdverseEventSignificanceLevel","significance","0.91","!=");
        assertTrue(fact2);


        fact2 = resolver.assertFact(observedAE1, "gov.nih.nci.cabig.caaers.domain.ObservedAdverseEventSignificanceLevel","significance","0.91","<");
        assertTrue(fact2);

        fact2 = resolver.assertFact(observedAE1, "gov.nih.nci.cabig.caaers.domain.ObservedAdverseEventSignificanceLevel","significance","0.91","<=");
        assertTrue(fact2);


    }

    public void testInterventionTypesOnReport() throws Exception{
        boolean fact = resolver.assertFact(aeReport, "gov.nih.nci.cabig.caaers.domain.StudyTherapyType","studyTherapyType","Drug Administration","==");
        assertFalse(fact);
        aeReport.getTreatmentInformation().addCourseAgent(new CourseAgent());
        fact = resolver.assertFact(aeReport, "gov.nih.nci.cabig.caaers.domain.StudyTherapyType","studyTherapyType","Drug Administration","==");
        assertTrue(fact);
        fact = resolver.assertFact(aeReport, "gov.nih.nci.cabig.caaers.domain.StudyTherapyType","studyTherapyType","Radiation","==");
        assertFalse(fact);
        fact = resolver.assertFact(aeReport, "gov.nih.nci.cabig.caaers.domain.StudyTherapyType","studyTherapyType","Surgery","!=");
        assertTrue(fact);
    }

    public void testNciHeldIndOnReport() throws Exception {
        boolean fact = resolver.assertFact(aeReport,null,"hasNciIndAgent","true","==");
        assertFalse(fact);

        StudyAgent sa  = Fixtures.createStudyAgent("x");
        sa.addStudyAgentINDAssociation(Fixtures.createStudyAgentIndAssociation("6", "CTEP"));
        CourseAgent ca = new CourseAgent();
        aeReport.getTreatmentInformation().addCourseAgent(ca);
        fact = resolver.assertFact(aeReport,null,"hasNciIndAgent","true","==");
        assertFalse(fact);
        ca.setStudyAgent(sa);
        fact = resolver.assertFact(aeReport,null,"hasNciIndAgent","true","==");
        assertTrue(fact);
    }


    public void testIndOnReport() throws Exception {
        boolean fact = resolver.assertFact(aeReport,"gov.nih.nci.cabig.caaers.domain.StudyAgent","investigationalNewDrugIndicator","true","==");
        assertFalse(fact);

        StudyAgent sa  = Fixtures.createStudyAgent("x");
        sa.addStudyAgentINDAssociation(Fixtures.createStudyAgentIndAssociation("6", "CTEP"));
        CourseAgent ca = new CourseAgent();
        aeReport.getTreatmentInformation().addCourseAgent(ca);
        fact = resolver.assertFact(aeReport,"gov.nih.nci.cabig.caaers.domain.StudyAgent","investigationalNewDrugIndicator","true","==");
        assertFalse(fact);
        ca.setStudyAgent(sa);
        fact = resolver.assertFact(aeReport,"gov.nih.nci.cabig.caaers.domain.StudyAgent","investigationalNewDrugIndicator","true","==");
        assertTrue(fact);
        fact = resolver.assertFact(aeReport,"gov.nih.nci.cabig.caaers.domain.StudyAgent","investigationalNewDrugIndicator","true","isOnly");
        assertTrue(fact);

        ca = new CourseAgent();
        aeReport.getTreatmentInformation().addCourseAgent(ca);
        fact = resolver.assertFact(aeReport,"gov.nih.nci.cabig.caaers.domain.StudyAgent","investigationalNewDrugIndicator","true","==");
        assertTrue(fact);
        fact = resolver.assertFact(aeReport,"gov.nih.nci.cabig.caaers.domain.StudyAgent","investigationalNewDrugIndicator","true","isOnly");
        assertTrue(fact);

        ca.setStudyAgent(Fixtures.createStudyAgent("non-ctep-ind"));

        fact = resolver.assertFact(aeReport,"gov.nih.nci.cabig.caaers.domain.StudyAgent","investigationalNewDrugIndicator","true","==");
        assertTrue(fact);
        fact = resolver.assertFact(aeReport,"gov.nih.nci.cabig.caaers.domain.StudyAgent","investigationalNewDrugIndicator","true","isOnly");
        assertFalse(fact);
    }


    public void testNciHeldIdeOnReport() throws Exception {
        boolean fact = resolver.assertFact(aeReport,null,"hasNciIdeDevice","true","==");
        assertFalse(fact);

        StudyDevice sd = Fixtures.createStudyDevice(1);
        sd.getStudyDeviceINDAssociations().add(Fixtures.createStudyDeviceIndAssociation("9", "DCP"));
        MedicalDevice md = new MedicalDevice();
        aeReport.addMedicalDevice(md);
        fact = resolver.assertFact(aeReport,null,"hasNciIdeDevice","true","==");
        assertFalse(fact);
        fact = resolver.assertFact(aeReport,null,"hasNciIdeDevice","false","==");
        assertTrue(fact);
        fact = resolver.assertFact(aeReport,null,"hasNciIdeDevice","true","!=");
        assertTrue(fact);
        md.setStudyDevice(sd);
        fact = resolver.assertFact(aeReport,null,"hasNciIdeDevice","true","==");
        assertTrue(fact);
    }



    public void testIdeOnReport() throws Exception {
        boolean fact = resolver.assertFact(aeReport,"gov.nih.nci.cabig.caaers.domain.StudyDevice","investigationalNewDeviceIndicator","true","==");
        assertFalse(fact);

        StudyDevice sd = Fixtures.createStudyDevice(1);
        sd.getStudyDeviceINDAssociations().add(Fixtures.createStudyDeviceIndAssociation("9", "DCP"));
        MedicalDevice md = new MedicalDevice();
        aeReport.addMedicalDevice(md);
        fact = resolver.assertFact(aeReport,"gov.nih.nci.cabig.caaers.domain.StudyDevice","investigationalNewDeviceIndicator","true","==");
        assertFalse(fact);
        md.setStudyDevice(sd);
        fact = resolver.assertFact(aeReport,"gov.nih.nci.cabig.caaers.domain.StudyDevice","investigationalNewDeviceIndicator","true","==");
        assertTrue(fact);

        fact = resolver.assertFact(aeReport,"gov.nih.nci.cabig.caaers.domain.StudyDevice","investigationalNewDeviceIndicator","true","isOnly");
        assertTrue(fact);

        md = new MedicalDevice();
        sd = Fixtures.createStudyDevice();
        sd.getStudyDeviceINDAssociations().clear();
        md.setStudyDevice(sd);
        aeReport.addMedicalDevice(md);

        fact = resolver.assertFact(aeReport,"gov.nih.nci.cabig.caaers.domain.StudyDevice","investigationalNewDeviceIndicator","true","==");
        assertTrue(fact);
        fact = resolver.assertFact(aeReport,"gov.nih.nci.cabig.caaers.domain.StudyDevice","investigationalNewDeviceIndicator","true","isOnly");
        assertFalse(fact);

    }


    private Study createCGLIBProxyStudy(Class<? extends Study> klass, String shortTitle) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(klass);
        enhancer.setCallback(NoOp.INSTANCE);
        Study study =  (Study)enhancer.create();
        study.setShortTitle(shortTitle);
        return study;
   }



}
