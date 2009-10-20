package gov.nih.nci.cabig.caaers.coppa;

import edu.duke.cabig.c3pr.esb.Metadata;
import edu.duke.cabig.c3pr.esb.OperationNameEnum;
import edu.duke.cabig.c3pr.esb.ServiceTypeEnum;

public class StudyIndIdeServiceTest extends StudyProtocolServiceTest {
	
	public void testStudyIndIde_get() throws Exception{

		String payLoad = getPayloadForFile("classpath*:gov/nih/nci/cabig/caaers/pa/testdata/STUDY_IND_IDE_ID.xml");
		Metadata mData = new Metadata(OperationNameEnum.get.getName(), "extId", ServiceTypeEnum.STUDY_IND_IDE.getName());
		String paServiceResponse = sendMessage(payLoad,mData);
		assertNotNull(paServiceResponse);
		System.out.println("###################  StudyIndIdeService.get Start#################");
		System.out.println(paServiceResponse);
		System.out.println("###################  StudyIndIdeService.get End#################");
		System.out.print("");
		
	}
	
	public void testStudyIndIde_getByStudyProtocol() throws Exception{

		String payLoad = getPayloadForFile("classpath*:gov/nih/nci/cabig/caaers/pa/testdata/SRS_STUDY_PROTOCOL_ID.xml");
		Metadata mData = new Metadata(OperationNameEnum.getByStudyProtocol.getName(), "extId", ServiceTypeEnum.STUDY_IND_IDE.getName());
		String paServiceResponse = sendMessage(payLoad,mData);
		assertNotNull(paServiceResponse);
		//System.out.println("###################  StudyIndIdeService.getByStudyProtocol Start#################");
		System.out.println(paServiceResponse);
		//System.out.println("###################  StudyIndIdeService.getByStudyProtocol End#################");
		System.out.print("");
		
	}
}
