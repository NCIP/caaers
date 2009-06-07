package gov.nih.nci.cabig.caaers.service.adverseevent;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.api.AdverseEventManagementService;
import gov.nih.nci.cabig.caaers.dao.AdverseEventDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventCtcTerm;
import gov.nih.nci.cabig.caaers.domain.AdverseEventMeddraLowLevelTerm;
import gov.nih.nci.cabig.caaers.webservice.adverseevent.AdverseEventsInputMessage;
import gov.nih.nci.cabig.caaers.webservice.adverseevent.CaaersServiceResponse;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.core.io.ClassPathResource;

public class AdverseEventManagementServiceTest extends CaaersDbNoSecurityTestCase {
	
	private AdverseEventManagementService adverseEventManagementService = null;
	//private Unmarshaller aeUnmarshaller = null;
	//private JAXBContext aeJaxbContext = null;

	private Unmarshaller unmarshaller = null;
	private JAXBContext jaxbContext = null;
	private AdverseEventDao adverseEventDao = null;
	
    @Override
    protected void setUp() throws Exception {
        super.setUp();

       // aeJaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.webservice.adverseevent");
        //aeUnmarshaller = aeJaxbContext.createUnmarshaller();
        
        jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.webservice.adverseevent");
        unmarshaller = jaxbContext.createUnmarshaller();
        
        adverseEventManagementService = (AdverseEventManagementService)getApplicationContext().getBean("adverseEventManagementServiceImpl");
        adverseEventDao = (AdverseEventDao)getApplicationContext().getBean("adverseEventDao");
        

    }

	public void testGrade3_HospitalizationNONE() throws Exception{
		//String criteriaXmlFile = "AdverseeventCriteria.xml";
		String xmlFile = "Grade3_HospitalizationNONE.xml";;

		AdverseEventsInputMessage adverseEventsInputMessage = (AdverseEventsInputMessage)unmarshaller.unmarshal(getFile(xmlFile));
		//AdverseEvents xmlAdverseEvents = (AdverseEvents)aeUnmarshaller.unmarshal(getFile(aeXmlFile));
		CaaersServiceResponse resp = adverseEventManagementService.createAdverseEvent(adverseEventsInputMessage);
		assertEquals("'Hospitalization' must be provided if 'Grade' greater than 2 (Burn - )",resp.getResponse().getMessage().get(0));
	}

	public void testEndDateMustBeGreaterThanOrEqualToStartDate() throws Exception{
		//String criteriaXmlFile = "AdverseeventCriteria.xml";
		String xmlFile = "StartDateEndDate.xml";;

		AdverseEventsInputMessage adverseEventsInputMessage = (AdverseEventsInputMessage)unmarshaller.unmarshal(getFile(xmlFile));
		CaaersServiceResponse resp = adverseEventManagementService.createAdverseEvent(adverseEventsInputMessage);
		assertEquals("'End date' must be greater than or equal to 'Start date' for adverse event (Burn - )",resp.getResponse().getMessage().get(0));
	}

	public void testAECreateAndUpdateSameAE() throws Exception{
		//String criteriaXmlFile = "AdverseeventCriteria.xml";
		String xmlFile = "SucessAE.xml";;

		AdverseEventsInputMessage adverseEventsInputMessage = (AdverseEventsInputMessage)unmarshaller.unmarshal(getFile(xmlFile));
		CaaersServiceResponse resp = adverseEventManagementService.createAdverseEvent(adverseEventsInputMessage);
		int id = getAeId(resp.getResponse().getMessage().get(0).toString());
		AdverseEvent ae = adverseEventDao.getById(id);
		assertEquals("Burn",((AdverseEventCtcTerm)ae.getAdverseEventTerm()).getTerm().getTerm());
		assertEquals("YES",ae.getHospitalization().name());
		assertEquals("3",ae.getGrade().getCode()+"");
		
		//update AE ....
		xmlFile = "SucessAEUpdate.xml";
		adverseEventsInputMessage = (AdverseEventsInputMessage)unmarshaller.unmarshal(getFile(xmlFile));
		resp = adverseEventManagementService.createOrUpdateAdverseEvent(adverseEventsInputMessage);
		ae = adverseEventDao.getById(id);
		assertEquals("Burn",((AdverseEventCtcTerm)ae.getAdverseEventTerm()).getTerm().getTerm());
		assertEquals("YES",ae.getHospitalization().name());
		assertEquals("4",ae.getGrade().getCode()+"");		
	}


	public void testAECreateAndCreateUpdate() throws Exception{
		//String criteriaXmlFile = "AdverseeventCriteria.xml";
		String xmlFile = "SucessAE2.xml";;

		AdverseEventsInputMessage adverseEventsInputMessage = (AdverseEventsInputMessage)unmarshaller.unmarshal(getFile(xmlFile));
		CaaersServiceResponse resp = adverseEventManagementService.createAdverseEvent(adverseEventsInputMessage);
		int id = getAeId(resp.getResponse().getMessage().get(0).toString());
		AdverseEvent ae = adverseEventDao.getById(id);
		assertEquals("Burn",((AdverseEventCtcTerm)ae.getAdverseEventTerm()).getTerm().getTerm());
		assertEquals("YES",ae.getHospitalization().name());
		assertEquals("3",ae.getGrade().getCode()+"");
		
		//create / update AE ....
		xmlFile = "SucessAE4.xml";
		adverseEventsInputMessage = (AdverseEventsInputMessage)unmarshaller.unmarshal(getFile(xmlFile));
		resp = adverseEventManagementService.createOrUpdateAdverseEvent(adverseEventsInputMessage);
		id = getAeId(resp.getResponse().getMessage().get(0).toString());
		ae = adverseEventDao.getById(id);
		assertEquals("Burn",((AdverseEventCtcTerm)ae.getAdverseEventTerm()).getTerm().getTerm());
		assertEquals("YES",ae.getHospitalization().name());
		assertEquals("4",ae.getGrade().getCode()+"");	
		
		id = getAeId(resp.getResponse().getMessage().get(1).toString());
		ae = adverseEventDao.getById(id);
		assertEquals("Auditory/Ear - Other (Specify, __)",((AdverseEventCtcTerm)ae.getAdverseEventTerm()).getTerm().getTerm());
		assertEquals("YES",ae.getHospitalization().name());
		assertEquals("3",ae.getGrade().getCode()+"");	
		
	}
	
	public void testAECreateDuplicate() throws Exception{
		//String criteriaXmlFile = "AdverseeventCriteria.xml";
		String xmlFile = "SucessAE2.xml";;

		AdverseEventsInputMessage adverseEventsInputMessage = (AdverseEventsInputMessage)unmarshaller.unmarshal(getFile(xmlFile));
		CaaersServiceResponse resp = adverseEventManagementService.createAdverseEvent(adverseEventsInputMessage);
		int id = getAeId(resp.getResponse().getMessage().get(0).toString());
		AdverseEvent ae = adverseEventDao.getById(id);
		assertEquals("Burn",((AdverseEventCtcTerm)ae.getAdverseEventTerm()).getTerm().getTerm());
		assertEquals("YES",ae.getHospitalization().name());
		assertEquals("3",ae.getGrade().getCode()+"");
		
		//update AE ....
		xmlFile = "SucessAE2.xml";
		adverseEventsInputMessage = (AdverseEventsInputMessage)unmarshaller.unmarshal(getFile(xmlFile));
		resp = adverseEventManagementService.createAdverseEvent(adverseEventsInputMessage);
		String msg = resp.getResponse().getMessage().get(0).toString();
		assertEquals("This AE Term(Burn - ) already exists for given course.",msg);
		
	}
	public void testAECreateWithOtherMeddra() throws Exception{
		//String criteriaXmlFile = "AdverseeventCriteria.xml";
		String xmlFile = "SucessAE3.xml";;

		AdverseEventsInputMessage adverseEventsInputMessage = (AdverseEventsInputMessage)unmarshaller.unmarshal(getFile(xmlFile));
		CaaersServiceResponse resp = adverseEventManagementService.createAdverseEvent(adverseEventsInputMessage);
		int id = getAeId(resp.getResponse().getMessage().get(0).toString());
		AdverseEvent ae = adverseEventDao.getById(id);
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
		int id = getAeId(resp.getResponse().getMessage().get(0).toString());
		AdverseEvent ae = adverseEventDao.getById(id);
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

		AdverseEventsInputMessage adverseEventsInputMessage = (AdverseEventsInputMessage)unmarshaller.unmarshal(getFile(xmlFile));
		CaaersServiceResponse resp = adverseEventManagementService.createAdverseEvent(adverseEventsInputMessage);
		int id = getAeId(resp.getResponse().getMessage().get(0).toString());
		AdverseEvent ae = adverseEventDao.getById(id);
		assertEquals("mt1",((AdverseEventMeddraLowLevelTerm)ae.getAdverseEventTerm()).getLowLevelTerm().getMeddraTerm());
		assertEquals("YES",ae.getHospitalization().name());
		assertEquals("3",ae.getGrade().getCode()+"");
	
	}

	
    public void testAEDeleteMeddra() throws Exception {
		String xmlFile = "SuccessMeddraAE.xml";;

		AdverseEventsInputMessage adverseEventsInputMessage = (AdverseEventsInputMessage)unmarshaller.unmarshal(getFile(xmlFile));
		CaaersServiceResponse resp = adverseEventManagementService.createAdverseEvent(adverseEventsInputMessage);
		int id = getAeId(resp.getResponse().getMessage().get(0).toString());
		AdverseEvent ae = adverseEventDao.getById(id);
		assertEquals("mt1",((AdverseEventMeddraLowLevelTerm)ae.getAdverseEventTerm()).getLowLevelTerm().getMeddraTerm());
		assertEquals("YES",ae.getHospitalization().name());
		assertEquals("3",ae.getGrade().getCode()+"");

		xmlFile = "DeleteMeddraAE.xml";
		adverseEventsInputMessage = (AdverseEventsInputMessage)unmarshaller.unmarshal(getFile(xmlFile));
		adverseEventManagementService.deleteAdverseEvent(adverseEventsInputMessage);
		
		ae = adverseEventDao.getById(id);
		assertNull(ae);
		
    }
	public void testAEDelete() throws Exception{
		//String criteriaXmlFile = "AdverseeventCriteria.xml";
		String xmlFile = "SucessAEUpdate.xml";
		
		AdverseEventsInputMessage adverseEventsInputMessage = (AdverseEventsInputMessage)unmarshaller.unmarshal(getFile(xmlFile));
		CaaersServiceResponse resp = adverseEventManagementService.createAdverseEvent(adverseEventsInputMessage);
		
		int id = getAeId(resp.getResponse().getMessage().get(0).toString());
		AdverseEvent ae = adverseEventDao.getById(id);
		assertEquals("Burn",((AdverseEventCtcTerm)ae.getAdverseEventTerm()).getTerm().getTerm());
		assertEquals("YES",ae.getHospitalization().name());
		assertEquals("4",ae.getGrade().getCode()+"");

		int id2 = getAeId(resp.getResponse().getMessage().get(1).toString());
		ae = adverseEventDao.getById(id2);
		assertEquals("Dry skin",((AdverseEventCtcTerm)ae.getAdverseEventTerm()).getTerm().getTerm());
		assertEquals("YES",ae.getHospitalization().name());
		assertEquals("3",ae.getGrade().getCode()+"");

		xmlFile = "DeleteAE.xml";
		adverseEventsInputMessage = (AdverseEventsInputMessage)unmarshaller.unmarshal(getFile(xmlFile));
		adverseEventManagementService.deleteAdverseEvent(adverseEventsInputMessage);
		
		ae = adverseEventDao.getById(id);
		assertNull(ae);
		ae = adverseEventDao.getById(id2);
		assertNotNull(ae);	
		assertEquals("Dry skin",((AdverseEventCtcTerm)ae.getAdverseEventTerm()).getTerm().getTerm());
		assertEquals("YES",ae.getHospitalization().name());
		assertEquals("3",ae.getGrade().getCode()+"");
		
	}
	
	public void testAEDeleteMultiple() throws Exception{
		//String criteriaXmlFile = "AdverseeventCriteria.xml";
		String xmlFile = "SucessAEUpdate.xml";
		
		AdverseEventsInputMessage adverseEventsInputMessage = (AdverseEventsInputMessage)unmarshaller.unmarshal(getFile(xmlFile));
		CaaersServiceResponse resp = adverseEventManagementService.createAdverseEvent(adverseEventsInputMessage);
		
		int id = getAeId(resp.getResponse().getMessage().get(0).toString());
		AdverseEvent ae = adverseEventDao.getById(id);
		assertEquals("Burn",((AdverseEventCtcTerm)ae.getAdverseEventTerm()).getTerm().getTerm());
		assertEquals("YES",ae.getHospitalization().name());
		assertEquals("4",ae.getGrade().getCode()+"");

		int id2 = getAeId(resp.getResponse().getMessage().get(1).toString());
		ae = adverseEventDao.getById(id2);
		assertEquals("Dry skin",((AdverseEventCtcTerm)ae.getAdverseEventTerm()).getTerm().getTerm());
		assertEquals("YES",ae.getHospitalization().name());
		assertEquals("3",ae.getGrade().getCode()+"");

		xmlFile = "DeleteMultipleAEs.xml";
		adverseEventsInputMessage = (AdverseEventsInputMessage)unmarshaller.unmarshal(getFile(xmlFile));
		adverseEventManagementService.deleteAdverseEvent(adverseEventsInputMessage);
		
		ae = adverseEventDao.getById(id);
		assertNull(ae);
		ae = adverseEventDao.getById(id2);
		assertNull(ae);	

		
	}
	
	public void testInvalidTreatmentType() throws Exception{
		String xmlFile = "CriteriaInvalidTreatmentType.xml";
		//String xmlFile = "SucessAE.xml";;

		AdverseEventsInputMessage adverseEventsInputMessage = (AdverseEventsInputMessage)unmarshaller.unmarshal(getFile(xmlFile));
		CaaersServiceResponse resp = adverseEventManagementService.createAdverseEvent(adverseEventsInputMessage);
		assertEquals("TreatmentType(TreatmentX) is not valid.",resp.getResponse().getMessage().get(0));
	}
	
	public void testInvalidCourseDates() throws Exception{
		String xmlFile = "CriteriaInvalidCourseDates.xml";
		//String xmlFile = "SucessAE.xml";;

		AdverseEventsInputMessage adverseEventsInputMessage = (AdverseEventsInputMessage)unmarshaller.unmarshal(getFile(xmlFile));
		CaaersServiceResponse resp = adverseEventManagementService.createAdverseEvent(adverseEventsInputMessage);
		assertEquals("Course End date cannot be earlier than Start date.",resp.getResponse().getMessage().get(0));
	}
	
	public void testInvalidTAC() throws Exception{
		String xmlFile = "InvalidTAC.xml";
		//String xmlFile = "SucessAE.xml";;

		AdverseEventsInputMessage adverseEventsInputMessage = (AdverseEventsInputMessage)unmarshaller.unmarshal(getFile(xmlFile));
		CaaersServiceResponse resp = adverseEventManagementService.createAdverseEvent(adverseEventsInputMessage);
		assertEquals("TreatmentAssignment(TAC1X) is not valid.",resp.getResponse().getMessage().get(0));
	}

	private int getAeId(String msg) {
		String id= msg.substring(0, msg.indexOf(">"));
		return Integer.parseInt(id);
	}
	private File getFile(String fileName) throws IOException{
		File testFile = new ClassPathResource("/gov/nih/nci/cabig/caaers/service/adverseevent/testdata/" + fileName).getFile();
		return testFile;
	}
}
