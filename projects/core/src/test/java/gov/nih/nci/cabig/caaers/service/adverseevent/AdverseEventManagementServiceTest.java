package gov.nih.nci.cabig.caaers.service.adverseevent;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.api.AdverseEventManagementService;
import gov.nih.nci.cabig.caaers.dao.AdverseEventDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventCtcTerm;
import gov.nih.nci.cabig.caaers.webservice.adverseeventcriteria.ImportAdverseEvents;

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
        
        jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.webservice.adverseeventcriteria");
        unmarshaller = jaxbContext.createUnmarshaller();
        
        adverseEventManagementService = (AdverseEventManagementService)getApplicationContext().getBean("adverseEventManagementService");
        adverseEventDao = (AdverseEventDao)getApplicationContext().getBean("adverseEventDao");
        

    }
    
	public void testGrade3_HospitalizationNONE() throws Exception{
		//String criteriaXmlFile = "AdverseeventCriteria.xml";
		String xmlFile = "Grade3_HospitalizationNONE.xml";;

		ImportAdverseEvents importAdverseEvents = (ImportAdverseEvents)unmarshaller.unmarshal(getFile(xmlFile));
		//AdverseEvents xmlAdverseEvents = (AdverseEvents)aeUnmarshaller.unmarshal(getFile(aeXmlFile));
		gov.nih.nci.cabig.caaers.webservice.CaaersServiceResponse resp = adverseEventManagementService.createAdverseEvent(importAdverseEvents);
		assertEquals("'Hospitalization' must be provided if 'Grade' greater than 2",resp.getResponse().getMessage().get(0));
	}

	public void testEndDateMustBeGreaterThanOrEqualToStartDate() throws Exception{
		//String criteriaXmlFile = "AdverseeventCriteria.xml";
		String xmlFile = "StartDateEndDate.xml";;

		ImportAdverseEvents importAdverseEvents = (ImportAdverseEvents)unmarshaller.unmarshal(getFile(xmlFile));
		gov.nih.nci.cabig.caaers.webservice.CaaersServiceResponse resp = adverseEventManagementService.createAdverseEvent(importAdverseEvents);
		assertEquals("'End date' must be greater than or equal to 'Start date' for adverse event",resp.getResponse().getMessage().get(0));
	}

	public void testSuccessAE() throws Exception{
		//String criteriaXmlFile = "AdverseeventCriteria.xml";
		String xmlFile = "SucessAE.xml";;

		ImportAdverseEvents importAdverseEvents = (ImportAdverseEvents)unmarshaller.unmarshal(getFile(xmlFile));
		gov.nih.nci.cabig.caaers.webservice.CaaersServiceResponse resp = adverseEventManagementService.createAdverseEvent(importAdverseEvents);
		String id = resp.getResponse().getMessage().get(0).toString();
		AdverseEvent ae = adverseEventDao.getById(Integer.parseInt(id));
		assertEquals("Burn",((AdverseEventCtcTerm)ae.getAdverseEventTerm()).getTerm().getTerm());
		assertEquals("YES",ae.getHospitalization().name());
		assertEquals("3",ae.getGrade().getCode()+"");
	}
	
	public void testInvalidTreatmentType() throws Exception{
		String xmlFile = "CriteriaInvalidTreatmentType.xml";
		//String xmlFile = "SucessAE.xml";;

		ImportAdverseEvents importAdverseEvents = (ImportAdverseEvents)unmarshaller.unmarshal(getFile(xmlFile));
		gov.nih.nci.cabig.caaers.webservice.CaaersServiceResponse resp = adverseEventManagementService.createAdverseEvent(importAdverseEvents);
		assertEquals("TreatmentType not valid - TreatmentX",resp.getResponse().getMessage().get(0));
	}
	
	public void testInvalidCourseDates() throws Exception{
		String xmlFile = "CriteriaInvalidCourseDates.xml";
		//String xmlFile = "SucessAE.xml";;

		ImportAdverseEvents importAdverseEvents = (ImportAdverseEvents)unmarshaller.unmarshal(getFile(xmlFile));
		gov.nih.nci.cabig.caaers.webservice.CaaersServiceResponse resp = adverseEventManagementService.createAdverseEvent(importAdverseEvents);
		assertEquals("Course End date cannot be earlier than Start date",resp.getResponse().getMessage().get(0));
	}
	
	public void testInvalidTAC() throws Exception{
		String xmlFile = "InvalidTAC.xml";
		//String xmlFile = "SucessAE.xml";;

		ImportAdverseEvents importAdverseEvents = (ImportAdverseEvents)unmarshaller.unmarshal(getFile(xmlFile));
		gov.nih.nci.cabig.caaers.webservice.CaaersServiceResponse resp = adverseEventManagementService.createAdverseEvent(importAdverseEvents);
		assertEquals("TreatmentAssignment is not valid - TAC1X",resp.getResponse().getMessage().get(0));
	}
	
	private File getFile(String fileName) throws IOException{
		File testFile = new ClassPathResource("/gov/nih/nci/cabig/caaers/service/adverseevent/testdata/" + fileName).getFile();
		return testFile;
	}
}
