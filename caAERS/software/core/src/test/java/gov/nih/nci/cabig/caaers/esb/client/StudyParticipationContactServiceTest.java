package gov.nih.nci.cabig.caaers.esb.client;

import java.util.ArrayList;
import java.util.List;

import edu.duke.cabig.c3pr.esb.Metadata;
import edu.duke.cabig.c3pr.esb.OperationNameEnum;
import edu.duke.cabig.c3pr.esb.ServiceTypeEnum;

public class StudyParticipationContactServiceTest extends StudyProtocolServiceTest {
	
	public void testStudyParticipationContact_get() throws Exception{

		String payLoad = getPayloadForFile("classpath*:gov/nih/nci/cabig/caaers/pa/testdata/STUDY_PARTICIPATION_CONTACT_ID.xml");
		Metadata mData = new Metadata(OperationNameEnum.get.getName(), "extId", ServiceTypeEnum.STUDY_SITE_CONTACT.getName());
		String paServiceResponse = sendMessage(payLoad,mData);
		assertNotNull(paServiceResponse);
		System.out.println("###################  StudyParticipationContactService.get Start#################");
		System.out.println(paServiceResponse);
		System.out.println("###################  StudyParticipationContactService.get End#################");
		System.out.print("");
		
	}
	
	public void testStudyParticipationContact_getByStudyProtocol() throws Exception{

		String payLoad = getPayloadForFile("classpath*:gov/nih/nci/cabig/caaers/pa/testdata/SRS_STUDY_PROTOCOL_ID.xml");
		Metadata mData = new Metadata(OperationNameEnum.getByStudyProtocol.getName(), "extId", ServiceTypeEnum.STUDY_SITE_CONTACT.getName());
		String paServiceResponse = sendMessage(payLoad,mData);
		assertNotNull(paServiceResponse);
		//System.out.println("###################  StudyParticipationContactService.getByStudyProtocol Start#################");
		System.out.println(paServiceResponse);
		//System.out.println("###################  StudyParticipationContactService.getByStudyProtocol End#################");
		System.out.print("");
		
	}
	
	public void testStudyContact_GetByStudyProtocolAndRole() throws Exception{
		List<String> payLoads = new ArrayList<String>();
		String payLoad1 = getPayloadForFile("classpath*:gov/nih/nci/cabig/caaers/pa/testdata/SRS_STUDY_PROTOCOL_ID.xml");
		payLoads.add(payLoad1);
		String payLoad2 = getPayloadForFile("classpath*:gov/nih/nci/cabig/caaers/pa/testdata/STUDY_PARTICIPATION_CONTACT_ROLE_CODE.xml");
		payLoads.add(payLoad2);
		
		Metadata mData = new Metadata(OperationNameEnum.getByStudyProtocolAndRole.getName(), "caAERS", ServiceTypeEnum.STUDY_SITE_CONTACT.getName());
		String paServiceResponse = sendMessage(payLoads,mData);
		assertNotNull(paServiceResponse);
		System.out.println("################### StudyProtocolService.getByStudyProtocolAndRole Start#################");
		System.out.println(paServiceResponse);
		System.out.println("################### StudyProtocolService.getByStudyProtocolAndRole End#################");
		System.out.print("");
		

		
	}
	
	public void testHealthCareProvider_getById(){
		String healthCareProviderIdPayLoad = getPayloadForFile("classpath*:gov/nih/nci/cabig/caaers/pa/testdata/HEALTH_CARE_PROVIDER_ID.xml");
		Metadata healthCareProviderMData = new Metadata(OperationNameEnum.getById.getName(), "extId", ServiceTypeEnum.HEALTH_CARE_PROVIDER.getName());
		String poServiceResponse = sendMessage(healthCareProviderIdPayLoad,healthCareProviderMData);
		assertNotNull(poServiceResponse);
		System.out.println("################### HEALTH_CARE_PROVIDER Start#################");
		System.out.println(poServiceResponse);
		System.out.println("################### HEALTH_CARE_PROVIDER End#################");
		System.out.print("");
	}
}
