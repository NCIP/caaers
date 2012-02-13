package gov.nih.nci.cabig.caaers.service.adverseevent;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.api.AdverseEventManagementService;
import gov.nih.nci.cabig.caaers.dao.AdverseEventDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventCtcTerm;
import gov.nih.nci.cabig.caaers.domain.AdverseEventMeddraLowLevelTerm;
import gov.nih.nci.cabig.caaers.webservice.adverseevent.AdverseEventType;
import gov.nih.nci.cabig.caaers.webservice.adverseevent.AdverseEventsInputMessage;
import gov.nih.nci.cabig.caaers.webservice.adverseevent.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.webservice.adverseevent.Responses.Response;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.core.io.ClassPathResource;

import com.semanticbits.rules.impl.RuleDeploymentServiceImpl;

public class AdverseEventManagementServiceTest_FAILED extends CaaersDbNoSecurityTestCase {
	
	private AdverseEventManagementService adverseEventManagementService = null;
	//private Unmarshaller aeUnmarshaller = null;
	//private JAXBContext aeJaxbContext = null;

	private Unmarshaller unmarshaller = null;
	private JAXBContext jaxbContext = null;
	private AdverseEventDao adverseEventDao = null;
	protected RuleDeploymentServiceImpl deploymetService;
	
    @Override
    protected void setUp() throws Exception {
        super.setUp();

       // aeJaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.webservice.adverseevent");
        //aeUnmarshaller = aeJaxbContext.createUnmarshaller();
        
        jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.webservice.adverseevent");
        unmarshaller = jaxbContext.createUnmarshaller();
        
        adverseEventManagementService = (AdverseEventManagementService)getApplicationContext().getBean("adverseEventManagementServiceImpl");
        adverseEventDao = (AdverseEventDao)getApplicationContext().getBean("adverseEventDao");
        deploymetService = (RuleDeploymentServiceImpl)getDeployedApplicationContext().getBean("ruleDeploymentService");
        
        try {
            unregisterRule();
        } catch (Exception e) {
        }
        registerRule();
        

    }
    
    @Override
    protected void tearDown() throws Exception {
    	// TODO Auto-generated method stub
    	super.tearDown();
    	unregisterRule();
    }
	public void testCreateWithOnlyVerbatim() throws Exception{
		//String criteriaXmlFile = "AdverseeventCriteria.xml";
		String xmlFile = "CreateVerbatim.xml";;

		AdverseEventsInputMessage adverseEventsInputMessage = (AdverseEventsInputMessage)unmarshaller.unmarshal(getFile(xmlFile));
		//AdverseEvents xmlAdverseEvents = (AdverseEvents)aeUnmarshaller.unmarshal(getFile(aeXmlFile));
		CaaersServiceResponse resp = adverseEventManagementService.createAdverseEvent(adverseEventsInputMessage);

		
		assertEquals("Adverse Event(test) created Successfully.",resp.getResponses().getResponse().get(0).getDescription());
		assertEquals("1",resp.getResponses().getResponse().get(0).getCorrelationId());
	}

	public void testCreate() throws Exception{
		//String criteriaXmlFile = "AdverseeventCriteria.xml";
		String xmlFile = "Grade3_HospitalizationNONE.xml";;

		AdverseEventsInputMessage adverseEventsInputMessage = (AdverseEventsInputMessage)unmarshaller.unmarshal(getFile(xmlFile));
		//AdverseEvents xmlAdverseEvents = (AdverseEvents)aeUnmarshaller.unmarshal(getFile(aeXmlFile));
		CaaersServiceResponse resp = adverseEventManagementService.createAdverseEvent(adverseEventsInputMessage);

		
		assertEquals("1",resp.getResponses().getResponse().get(0).getCorrelationId());
		assertEquals("2",resp.getResponses().getResponse().get(1).getCorrelationId());
		assertEquals("3",resp.getResponses().getResponse().get(2).getCorrelationId());
		assertEquals("gov.nih.nci.cabig.caaers.CaaersSystemException: gov.nih.nci.cabig.caaers.CaaersSystemException: Other(MedDRA) missing.",resp.getResponses().getResponse().get(0).getDescription());
		assertEquals("gov.nih.nci.cabig.caaers.CaaersSystemException: gov.nih.nci.cabig.caaers.CaaersSystemException: Grade 30 is not allowed for this Term -18",resp.getResponses().getResponse().get(1).getDescription());
		assertEquals("Adverse Event(Burn - ) created Successfully.",resp.getResponses().getResponse().get(2).getDescription());
	}

	public void testStartDateMustBeLessThanOrEqualToCourseDate() throws Exception{
		//String criteriaXmlFile = "AdverseeventCriteria.xml";
		String xmlFile = "StartDateFirstCourseDate.xml";;

		AdverseEventsInputMessage adverseEventsInputMessage = (AdverseEventsInputMessage)unmarshaller.unmarshal(getFile(xmlFile));
		CaaersServiceResponse resp = adverseEventManagementService.createAdverseEvent(adverseEventsInputMessage);
		assertEquals("gov.nih.nci.cabig.caaers.CaaersSystemException: gov.nih.nci.cabig.caaers.CaaersSystemException: adverseEvent start date 2005-07-29 cannot be before startDateOfFirstCourse Sun Mar 29 00:00:00 EDT 2009",resp.getResponses().getResponse().get(0).getDescription());
		assertEquals("1",resp.getResponses().getResponse().get(0).getCorrelationId());	}

	public void testEndDateMustBeGreaterThanOrEqualToStartDate() throws Exception{
		//String criteriaXmlFile = "AdverseeventCriteria.xml";
		String xmlFile = "StartDateEndDate.xml";;

		AdverseEventsInputMessage adverseEventsInputMessage = (AdverseEventsInputMessage)unmarshaller.unmarshal(getFile(xmlFile));
		CaaersServiceResponse resp = adverseEventManagementService.createAdverseEvent(adverseEventsInputMessage);

		assertEquals("'End date' must be greater than or equal to 'Start date' for adverse event (Dry skin - )",resp.getResponses().getResponse().get(0).getDescription());
	}

    
	public void testAECreateAndUpdateSameAE() throws Exception{
		
		String xmlFile = "SucessAE.xml";;

		AdverseEventsInputMessage adverseEventsInputMessage = (AdverseEventsInputMessage)unmarshaller.unmarshal(getFile(xmlFile));
		CaaersServiceResponse resp = adverseEventManagementService.createAdverseEvent(adverseEventsInputMessage);

		String id = resp.getResponses().getResponse().get(0).getDataBaseId();
		AdverseEvent ae = adverseEventDao.getById(Integer.parseInt(id));
//		assertEquals("Burn",((AdverseEventCtcTerm)ae.getAdverseEventTerm()).getTerm().getTerm());
		assertEquals("YES",ae.getHospitalization().name());
		assertEquals("3",ae.getGrade().getCode()+"");
		
		//update AE ....
		xmlFile = "SucessAEUpdate.xml";
		adverseEventsInputMessage = (AdverseEventsInputMessage)unmarshaller.unmarshal(getFile(xmlFile));
		//update by ID 
		AdverseEventType e = adverseEventsInputMessage.getAdverseEvents().getAdverseEvent().get(0);		
		e.setId(BigInteger.valueOf(Long.parseLong(id+"")));
		adverseEventsInputMessage.getAdverseEvents().getAdverseEvent().add(0, e);
		resp = adverseEventManagementService.createOrUpdateAdverseEvent(adverseEventsInputMessage);

		ae = adverseEventDao.getById(Integer.parseInt(id));
//		assertEquals("Burn",((AdverseEventCtcTerm)ae.getAdverseEventTerm()).getTerm().getTerm());
		assertEquals("YES",ae.getHospitalization().name());
		assertEquals("4",ae.getGrade().getCode()+"");	
		
		//update AE by ctepCode....
		xmlFile = "SucessAEUpdate2.xml";
		adverseEventsInputMessage = (AdverseEventsInputMessage)unmarshaller.unmarshal(getFile(xmlFile));
		
		resp = adverseEventManagementService.createOrUpdateAdverseEvent(adverseEventsInputMessage);

		ae = adverseEventDao.getById(Integer.parseInt(id));
//		assertEquals("Burn",((AdverseEventCtcTerm)ae.getAdverseEventTerm()).getTerm().getTerm());
		assertEquals("YES",ae.getHospitalization().name());
		assertEquals("2",ae.getGrade().getCode()+"");	
		
		
		
	}


	public void testAECreateAndCreateUpdate() throws Exception{
		//String criteriaXmlFile = "AdverseeventCriteria.xml";
		String xmlFile = "SucessAE2.xml";;

		AdverseEventsInputMessage adverseEventsInputMessage = (AdverseEventsInputMessage)unmarshaller.unmarshal(getFile(xmlFile));
		CaaersServiceResponse resp = adverseEventManagementService.createAdverseEvent(adverseEventsInputMessage);
		String id =  resp.getResponses().getResponse().get(0).getDataBaseId();
		AdverseEvent ae = adverseEventDao.getById(Integer.parseInt(id));
		/*
		assertEquals("Burn",((AdverseEventCtcTerm)ae.getAdverseEventTerm()).getTerm().getTerm());
		assertEquals("YES",ae.getHospitalization().name());
		assertEquals("3",ae.getGrade().getCode()+"");*/
		
		//create / update AE ....
		xmlFile = "SucessAE4.xml";
		adverseEventsInputMessage = (AdverseEventsInputMessage)unmarshaller.unmarshal(getFile(xmlFile));
		resp = adverseEventManagementService.createOrUpdateAdverseEvent(adverseEventsInputMessage);
		id = resp.getResponses().getResponse().get(0).getDataBaseId();
		ae = adverseEventDao.getById(Integer.parseInt(id));
		/*
		assertEquals("Burn",((AdverseEventCtcTerm)ae.getAdverseEventTerm()).getTerm().getTerm());
		assertEquals("YES",ae.getHospitalization().name());
		assertEquals("4",ae.getGrade().getCode()+"");	*/
		
		id =  resp.getResponses().getResponse().get(1).getDataBaseId();
		ae = adverseEventDao.getById(Integer.parseInt(id));
		/*
		assertEquals("Auditory/Ear - Other (Specify, __)",((AdverseEventCtcTerm)ae.getAdverseEventTerm()).getTerm().getTerm());
		assertEquals("YES",ae.getHospitalization().name());
		assertEquals("3",ae.getGrade().getCode()+"");	*/
		
	}
	
	public void testAECreateDuplicate() throws Exception{
		//String criteriaXmlFile = "AdverseeventCriteria.xml";
		String xmlFile = "SucessAE2.xml";;

		AdverseEventsInputMessage adverseEventsInputMessage = (AdverseEventsInputMessage)unmarshaller.unmarshal(getFile(xmlFile));
		CaaersServiceResponse resp = adverseEventManagementService.createAdverseEvent(adverseEventsInputMessage);
		String id =resp.getResponses().getResponse().get(0).getDataBaseId();
		AdverseEvent ae = adverseEventDao.getById(Integer.parseInt(id));
		assertEquals("Burn",((AdverseEventCtcTerm)ae.getAdverseEventTerm()).getTerm().getTerm());
		assertEquals("YES",ae.getHospitalization().name());
		assertEquals("3",ae.getGrade().getCode()+"");
		
		//update AE ....
		xmlFile = "SucessAE2.xml";
		adverseEventsInputMessage = (AdverseEventsInputMessage)unmarshaller.unmarshal(getFile(xmlFile));
		resp = adverseEventManagementService.createAdverseEvent(adverseEventsInputMessage);
		String msg = resp.getResponses().getResponse().get(0).getDescription().toString();
		//System.out.println(msg);
		assertEquals("This AE Term(Burn - ) already exists for given course.",msg);
		
	}
	
	public void testAECreateWithOtherMeddra() throws Exception{
		//String criteriaXmlFile = "AdverseeventCriteria.xml";
		String xmlFile = "SucessAE3.xml";;

		AdverseEventsInputMessage adverseEventsInputMessage = (AdverseEventsInputMessage)unmarshaller.unmarshal(getFile(xmlFile));
		CaaersServiceResponse resp = adverseEventManagementService.createAdverseEvent(adverseEventsInputMessage);
		String id = resp.getResponses().getResponse().get(0).getDataBaseId();
		AdverseEvent ae = adverseEventDao.getById(Integer.parseInt(id));
		assertEquals("Auditory/Ear - Other (Specify, __)",((AdverseEventCtcTerm)ae.getAdverseEventTerm()).getTerm().getTerm());
		assertEquals("mc1",ae.getLowLevelTerm().getMeddraCode());
		assertEquals("YES",ae.getHospitalization().name());
		assertEquals("3",ae.getGrade().getCode()+"");
		
	}
	
	public void testAECreateWithOutcomes() throws Exception{
		//String criteriaXmlFile = "AdverseeventCriteria.xml";
		String xmlFile = "AEwithOutcomes.xml";;

		AdverseEventsInputMessage adverseEventsInputMessage = (AdverseEventsInputMessage)unmarshaller.unmarshal(getFile(xmlFile));
		CaaersServiceResponse resp = adverseEventManagementService.createAdverseEvent(adverseEventsInputMessage);
		String id = resp.getResponses().getResponse().get(0).getDataBaseId();
		AdverseEvent ae = adverseEventDao.getById(Integer.parseInt(id));
		assertEquals("Auditory/Ear - Other (Specify, __)",((AdverseEventCtcTerm)ae.getAdverseEventTerm()).getTerm().getTerm());
		assertEquals("mc1",ae.getLowLevelTerm().getMeddraCode());
		assertEquals("YES",ae.getHospitalization().name());
		assertEquals("3",ae.getGrade().getCode()+"");
		assertEquals(2,ae.getOutcomes().size());
		assertEquals("home",ae.getEventLocation());
		assertEquals("02",ae.getEventApproximateTime().getHourString());
		
		
	}
	
	public void testAECreateMeddra() throws Exception{
		//String criteriaXmlFile = "AdverseeventCriteria.xml";
		String xmlFile = "SuccessMeddraAE.xml";;
		//make study a meddra study for meddra tests
		getJdbcTemplate().execute("update terminologies set meddra_version_id=9 , term_code=2 , ctc_id = null where id =-1");
		AdverseEventsInputMessage adverseEventsInputMessage = (AdverseEventsInputMessage)unmarshaller.unmarshal(getFile(xmlFile));
		CaaersServiceResponse resp = adverseEventManagementService.createAdverseEvent(adverseEventsInputMessage);
		String id = resp.getResponses().getResponse().get(0).getDataBaseId();
		AdverseEvent ae = adverseEventDao.getById(Integer.parseInt(id));
		assertEquals("mt1",((AdverseEventMeddraLowLevelTerm)ae.getAdverseEventTerm()).getLowLevelTerm().getMeddraTerm());
		assertEquals("YES",ae.getHospitalization().name());
		assertEquals("3",ae.getGrade().getCode()+"");
	}

	
    public void testAEDeleteMeddra() throws Exception {
		String xmlFile = "SuccessMeddraAE.xml";;
		//make study a meddra study for meddra tests
		getJdbcTemplate().execute("update terminologies set meddra_version_id=9 , term_code=2 , ctc_id = null where id =-1");
		AdverseEventsInputMessage adverseEventsInputMessage = (AdverseEventsInputMessage)unmarshaller.unmarshal(getFile(xmlFile));
		CaaersServiceResponse resp = adverseEventManagementService.createAdverseEvent(adverseEventsInputMessage);
		String id = resp.getResponses().getResponse().get(0).getDataBaseId();
		AdverseEvent ae = adverseEventDao.getById(Integer.parseInt(id));
		assertEquals("mt1",((AdverseEventMeddraLowLevelTerm)ae.getAdverseEventTerm()).getLowLevelTerm().getMeddraTerm());
		assertEquals("YES",ae.getHospitalization().name());
		assertEquals("3",ae.getGrade().getCode()+"");

		xmlFile = "DeleteMeddraAE.xml";
		adverseEventsInputMessage = (AdverseEventsInputMessage)unmarshaller.unmarshal(getFile(xmlFile));
		adverseEventManagementService.deleteAdverseEvent(adverseEventsInputMessage);
		
		ae = adverseEventDao.getById(Integer.parseInt(id));
		assertNull(ae);
		
    }
    
	public void testAEDelete() throws Exception{
		//String criteriaXmlFile = "AdverseeventCriteria.xml";
		String xmlFile = "DeleteTest.xml";
		//revert  study to ctc 
		getJdbcTemplate().execute("update terminologies set meddra_version_id=null , term_code=1 , ctc_id = 3 where id =-1");
		AdverseEventsInputMessage adverseEventsInputMessage = (AdverseEventsInputMessage)unmarshaller.unmarshal(getFile(xmlFile));
		
		CaaersServiceResponse resp = adverseEventManagementService.createAdverseEvent(adverseEventsInputMessage);

		String id = resp.getResponses().getResponse().get(0).getDataBaseId();
		AdverseEvent ae = adverseEventDao.getById(Integer.parseInt(id));
		/*
		assertEquals("Burn",((AdverseEventCtcTerm)ae.getAdverseEventTerm()).getTerm().getTerm());
		assertEquals("YES",ae.getHospitalization().name());
		assertEquals("4",ae.getGrade().getCode()+"");
*/
		String id2 = resp.getResponses().getResponse().get(1).getDataBaseId();
		AdverseEvent dryae = adverseEventDao.getById(Integer.parseInt(id2));
		/*
		assertEquals("Dry skin",((AdverseEventCtcTerm)dryae.getAdverseEventTerm()).getTerm().getTerm());
		assertEquals("YES",dryae.getHospitalization().name());
		assertEquals("3",dryae.getGrade().getCode()+"");*/

		xmlFile = "DeleteAE.xml";
		adverseEventsInputMessage = (AdverseEventsInputMessage)unmarshaller.unmarshal(getFile(xmlFile));
		
		resp=adverseEventManagementService.deleteAdverseEvent(adverseEventsInputMessage);

		ae = adverseEventDao.getById(Integer.parseInt(id));
		assertNull(ae);
		ae = adverseEventDao.getById(Integer.parseInt(id2));
		assertNull(ae);
		
		
	}
	

	
	public void testInvalidTreatmentType() throws Exception{
		String xmlFile = "CriteriaInvalidTreatmentType.xml";
		//String xmlFile = "SucessAE.xml";;

		AdverseEventsInputMessage adverseEventsInputMessage = (AdverseEventsInputMessage)unmarshaller.unmarshal(getFile(xmlFile));
		CaaersServiceResponse resp = adverseEventManagementService.createAdverseEvent(adverseEventsInputMessage);
		assertEquals("TreatmentType(TreatmentX) is not valid.",resp.getResponses().getResponse().get(0).getDescription().toString());
	}
	
	public void testInvalidCourseDates() throws Exception{
		String xmlFile = "CriteriaInvalidCourseDates.xml";
		//String xmlFile = "SucessAE.xml";;

		AdverseEventsInputMessage adverseEventsInputMessage = (AdverseEventsInputMessage)unmarshaller.unmarshal(getFile(xmlFile));
		CaaersServiceResponse resp = adverseEventManagementService.createAdverseEvent(adverseEventsInputMessage);
		assertEquals("Course End date cannot be earlier than Start date.",resp.getResponses().getResponse().get(0).getDescription().toString());
	}
	
	public void testInvalidTAC() throws Exception{
		String xmlFile = "InvalidTAC.xml";
		//String xmlFile = "SucessAE.xml";;

		AdverseEventsInputMessage adverseEventsInputMessage = (AdverseEventsInputMessage)unmarshaller.unmarshal(getFile(xmlFile));
		CaaersServiceResponse resp = adverseEventManagementService.createAdverseEvent(adverseEventsInputMessage);
		assertEquals("TreatmentAssignment(TAC1X) is not valid.",resp.getResponses().getResponse().get(0).getDescription().toString());
	}


	private File getFile(String fileName) throws IOException{
		File testFile = new ClassPathResource("/gov/nih/nci/cabig/caaers/service/adverseevent/testdata/" + fileName).getFile();
		return testFile;
	}
	
	
	 public void registerRule() throws Exception {
		String ruleXml = getFileContext("rules_reporting_adverse_events.xml");
		try {
			deploymetService
					.deregisterRuleSet("gov.nih.nci.cabig.caaers.rules.reporting_basics_section");
		} catch (Exception e) {
			System.out.println("registering for first time");
		}
		deploymetService.registerRuleXml(
				"gov.nih.nci.cabig.caaers.rules.reporting_basics_section",
				ruleXml);
		assertTrue("Rule deployed", true);
	}

	public void unregisterRule() throws Exception {
		deploymetService
				.deregisterRuleSet("gov.nih.nci.cabig.caaers.rules.reporting_basics_section");
		assertTrue("Rule undeployed", true);
	}

	public String getFileContext(String fileName) throws Exception {
		File testFile = new ClassPathResource(
				"/gov/nih/nci/cabig/caaers/rules/deploy/" + fileName).getFile();
		BufferedReader ds = new BufferedReader(new FileReader(testFile));
		String line = null;
		StringBuffer xml = new StringBuffer();
		while ((line = ds.readLine()) != null) {
			xml.append(line);
		}
		assertTrue("Content of the xml should not be null", xml.toString()
				.length() > 0);
		return xml.toString();
	}
}
