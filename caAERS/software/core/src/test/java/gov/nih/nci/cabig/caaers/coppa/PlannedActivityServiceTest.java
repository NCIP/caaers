package gov.nih.nci.cabig.caaers.coppa;

import edu.duke.cabig.c3pr.esb.Metadata;
import edu.duke.cabig.c3pr.esb.OperationNameEnum;
import edu.duke.cabig.c3pr.esb.ServiceTypeEnum;

public class PlannedActivityServiceTest extends StudyProtocolServiceTest {
	
	public void testPlannedActivity_getByStudyProtocol() throws Exception{

		String payLoad = getPayloadForFile("classpath*:gov/nih/nci/cabig/caaers/pa/testdata/SRS_STUDY_PROTOCOL_ID.xml");
		Metadata mData = new Metadata(OperationNameEnum.getByStudyProtocol.getName(), "extId", ServiceTypeEnum.PLANNED_ACTIVITY.getName());
		String paServiceResponse = sendMessage(payLoad,mData);
		assertNotNull(paServiceResponse);
		//System.out.println("###################  StudyOverAllStatus.getByStudyProtocol Start#################");
		System.out.println(paServiceResponse);
		//System.out.println("###################  StudyOverAllStatus.getByStudyProtocol End#################");
		System.out.print("");
	}

}
