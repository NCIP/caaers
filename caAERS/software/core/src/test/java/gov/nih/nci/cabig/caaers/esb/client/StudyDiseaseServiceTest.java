package gov.nih.nci.cabig.caaers.esb.client;

import edu.duke.cabig.c3pr.esb.Metadata;
import edu.duke.cabig.c3pr.esb.OperationNameEnum;
import edu.duke.cabig.c3pr.esb.ServiceTypeEnum;

public class StudyDiseaseServiceTest extends StudyProtocolServiceTest {

	public void testStudyDisease_get() throws Exception{

		String payLoad = getPayloadForFile("classpath*:gov/nih/nci/cabig/caaers/pa/testdata/STUDY_DISEASE_ID.xml");
		Metadata mData = new Metadata(OperationNameEnum.get.getName(), "extId", ServiceTypeEnum.STUDY_DISEASE.getName());
		String paServiceResponse = sendMessage(payLoad,mData);
		assertNotNull(paServiceResponse);
		System.out.println("###################  StudyDisease.get Start#################");
		System.out.println(paServiceResponse);
		System.out.println("###################  StudyDisease.get End#################");
		System.out.print("");
		
	}
	
	public void testStudyDisease_getByStudyProtocol() throws Exception{

		String payLoad = getPayloadForFile("classpath*:gov/nih/nci/cabig/caaers/pa/testdata/SRS_STUDY_PROTOCOL_ID.xml");
		Metadata mData = new Metadata(OperationNameEnum.getByStudyProtocol.getName(), "extId", ServiceTypeEnum.STUDY_DISEASE.getName());
		String paServiceResponse = sendMessage(payLoad,mData);
		assertNotNull(paServiceResponse);
		System.out.println("###################  StudyDisease.getByStudyProtocol Start#################");
		System.out.println(paServiceResponse);
		System.out.println("###################  StudyDisease.getByStudyProtocol End#################");
		System.out.print("");
		
	}
}
