package gov.nih.nci.cabig.caaers.coppa;

import edu.duke.cabig.c3pr.esb.Metadata;
import edu.duke.cabig.c3pr.esb.OperationNameEnum;
import edu.duke.cabig.c3pr.esb.ServiceTypeEnum;

import java.util.ArrayList;
import java.util.List;


public class StudyContactServiceTest extends StudyProtocolServiceTest {
	
	public void testStudyContact_get() throws Exception{

		String payLoad = getPayloadForFile("classpath*:gov/nih/nci/cabig/caaers/pa/testdata/STUDY_CONTACT_ID.xml");
		Metadata mData = new Metadata(OperationNameEnum.get.getName(), "extId", ServiceTypeEnum.STUDY_CONTACT.getName());
		String paServiceResponse = sendMessage(payLoad,mData);
		assertNotNull(paServiceResponse);
		System.out.println("###################  StudyContact.get Start#################");
		System.out.println(paServiceResponse);
		System.out.println("###################  StudyContact.get End#################");
		System.out.print("");
		
	}
	
	public void testStudyContact_getByStudyProtocol() throws Exception{
		String payLoad = getPayloadForFile("classpath*:gov/nih/nci/cabig/caaers/pa/testdata/SRS_STUDY_PROTOCOL_ID.xml");
		Metadata mData = new Metadata(OperationNameEnum.getByStudyProtocol.getName(), "extId", ServiceTypeEnum.STUDY_CONTACT.getName());
		String paServiceResponse = sendMessage(payLoad,mData);
		assertNotNull(paServiceResponse);
		//System.out.println("###################  StudyStudyContact.getByStudyProtocol Start#################");
		System.out.println(paServiceResponse);
		//System.out.println("###################  StudyStudyContact.getByStudyProtocol End#################");
		System.out.print("");
	}
	
	public void testStudyContact_GetByStudyProtocolAndRole() throws Exception{
		List<String> payLoads = new ArrayList<String>();
		String payLoad1 = getPayloadForFile("classpath*:gov/nih/nci/cabig/caaers/pa/testdata/SRS_STUDY_PROTOCOL_ID.xml");
		payLoads.add(payLoad1);
		String payLoad2 = getPayloadForFile("classpath*:gov/nih/nci/cabig/caaers/pa/testdata/STUDY_CONTACT_ROLE_CODE_1.xml");
		payLoads.add(payLoad2);
		
		Metadata mData = new Metadata(OperationNameEnum.getByStudyProtocolAndRole.getName(), "caAERS", ServiceTypeEnum.STUDY_CONTACT.getName());
		String paServiceResponse = sendMessage(payLoads,mData);
		assertNotNull(paServiceResponse);
		System.out.println("################### StudyContact.getByStudyProtocolAndRole Start#################");
		System.out.println(paServiceResponse);
		System.out.println("################### StudyContact.getByStudyProtocolAndRole End#################");
		System.out.print("");
		
	}
}
