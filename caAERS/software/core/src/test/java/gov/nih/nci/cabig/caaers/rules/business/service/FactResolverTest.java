package gov.nih.nci.cabig.caaers.rules.business.service;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import com.semanticbits.rules.objectgraph.FactResolver;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.INDHolder;
import gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug;
import gov.nih.nci.cabig.caaers.domain.LocalStudy;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.StudyAgentINDAssociation;
import gov.nih.nci.cabig.caaers.domain.StudyFundingSponsor;
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
	
	protected void setUp() throws Exception {
		super.setUp();
		ae = new AdverseEvent();
		ae.setAttributionSummary(Attribution.DEFINITE);
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
	
	private Study createCGLIBProxyStudy(Class<? extends Study> klass, String shortTitle) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(klass);
        enhancer.setCallback(NoOp.INSTANCE);
        Study study =  (Study)enhancer.create();
        study.setShortTitle(shortTitle);
        return study;
   }

}
