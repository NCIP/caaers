package gov.nih.nci.cabig.caaers.coppa;

import edu.duke.cabig.c3pr.esb.Metadata;
import edu.duke.cabig.c3pr.esb.OperationNameEnum;
import edu.duke.cabig.c3pr.esb.ServiceTypeEnum;

public class DocumentWorkFlowStatusServiceTest extends StudyProtocolServiceTest{

	public void testDocumentWorkFlowStatus_getByStudyProtocol() throws Exception{

		String payLoad = getPayloadForFile("classpath*:gov/nih/nci/cabig/caaers/pa/testdata/SRS_STUDY_PROTOCOL_ID.xml");
		Metadata mData = new Metadata(OperationNameEnum.getByStudyProtocol.getName(), "extId", ServiceTypeEnum.DOCUMENT_WORKFLOW_STATUS.getName());
		String paServiceResponse = sendMessage(payLoad,mData);
		assertNotNull(paServiceResponse);
		//System.out.println("###################  DOCUMENT_WORKFLOW_STATUS.getByStudyProtocol Start#################");
		System.out.println(paServiceResponse);
		//System.out.println("###################  DOCUMENT_WORKFLOW_STATUS.getByStudyProtocol End#################");
		System.out.print("");
		
	}
	
}
