package gov.nih.nci.cabig.caaers.coppa;

import edu.duke.cabig.c3pr.esb.Metadata;
import edu.duke.cabig.c3pr.esb.OperationNameEnum;
import edu.duke.cabig.c3pr.esb.ServiceTypeEnum;

public class StudyOverAllStatusServiceTest extends StudyProtocolServiceTest {

	public void testStudyOverAllStatus_get() throws Exception{

		String payLoad = getPayloadForFile("classpath*:gov/nih/nci/cabig/caaers/pa/testdata/STUDY_OVERALL_STATUS_ID.xml");
		Metadata mData = new Metadata(OperationNameEnum.get.getName(), "extId", ServiceTypeEnum.STUDY_OVERALL_STATUS.getName());
		String paServiceResponse = sendMessage(payLoad,mData);
		assertNotNull(paServiceResponse);
		System.out.println("###################  StudyOverAllStatus.get Start#################");
		System.out.println(paServiceResponse);
		System.out.println("###################  StudyOverAllStatus.get End#################");
		System.out.print("");
		
	}
	
	
	public void testStudyOverAllStatus_getByStudyProtocol() throws Exception{

		String payLoad = getPayloadForFile("classpath*:gov/nih/nci/cabig/caaers/pa/testdata/SRS_STUDY_PROTOCOL_ID.xml");
		Metadata mData = new Metadata(OperationNameEnum.getByStudyProtocol.getName(), "extId", ServiceTypeEnum.STUDY_OVERALL_STATUS.getName());
		String paServiceResponse = sendMessage(payLoad,mData);
		assertNotNull(paServiceResponse);
		//System.out.println("###################  StudyOverAllStatus.getByStudyProtocol Start#################");
		System.out.println(paServiceResponse);
		//System.out.println("###################  StudyOverAllStatus.getByStudyProtocol End#################");
		System.out.print("");
		
	}
}
