package gov.nih.nci.cabig.caaers.coppa;

import org.iso._21090.II;

import com.semanticbits.coppasimulator.util.CoppaObjectFactory;

import edu.duke.cabig.c3pr.esb.Metadata;
import edu.duke.cabig.c3pr.esb.OperationNameEnum;
import edu.duke.cabig.c3pr.esb.ServiceTypeEnum;


public class StudyParticipationServiceTest extends StudyProtocolServiceTest {
	
	public void testStudyParticipation_get() throws Exception{

		String payLoad = getPayloadForFile("classpath*:gov/nih/nci/cabig/caaers/pa/testdata/STUDY_PARTICIPATION_ID.xml");
		Metadata mData = new Metadata(OperationNameEnum.get.getName(), "extId", ServiceTypeEnum.STUDY_SITE.getName());
		String paServiceResponse = sendMessage(payLoad,mData);
		assertNotNull(paServiceResponse);
		System.out.println("###################  StudyParticipationService.get Start#################");
		System.out.println(paServiceResponse);
		System.out.println("###################  StudyParticipationService.get End#################");
		System.out.print("");
		
	}
	
	public void testStudyParticipation_getByStudyProtocol() throws Exception{

		String payLoad = getPayloadForFile("classpath*:gov/nih/nci/cabig/caaers/pa/testdata/SRS_STUDY_PROTOCOL_ID.xml");
		Metadata mData = new Metadata(OperationNameEnum.getByStudyProtocol.getName(), "extId", ServiceTypeEnum.STUDY_SITE.getName());
		String paServiceResponse = sendMessage(payLoad,mData);
		assertNotNull(paServiceResponse);
		//System.out.println("###################  StudyParticipationService.getByStudyProtocol Start#################");
		System.out.println(paServiceResponse);
		//System.out.println("###################  StudyParticipationService.getByStudyProtocol End#################");
		System.out.print("");
		
	}
	
	
	public void testGetHCP(){
		String payLoad = getPayloadForFile("classpath*:gov/nih/nci/cabig/caaers/pa/testdata/HEALTH_CARE_PROVIDER_ID.xml");
		Metadata healthCareProviderMData = new Metadata(OperationNameEnum.getById.getName(), "extId", ServiceTypeEnum.HEALTH_CARE_PROVIDER.getName());
		String poServiceResponse = sendMessage(payLoad,healthCareProviderMData);
		System.out.println("###################  HEALTH_CARE_PROVIDER Start#################");
		System.out.println(poServiceResponse);
		System.out.println("###################  HEALTH_CARE_PROVIDER End#################");
		System.out.print("");
	}
	
	
	public void testGetResearchOrg(){
		II ii = CoppaObjectFactory.getIISearchCriteria("3583312");
		String iiXml = CoppaObjectFactory.getCoppaIIXml(ii);
		Metadata mData = new Metadata(OperationNameEnum.getById.getName(), "externalId", "RESEARCH_ORGANIZATION");
		String poServiceResponse = sendMessage(iiXml, mData);
		assertNotNull(poServiceResponse);
		System.out.println(poServiceResponse);
	}
	
	public void testGetOrg(){
		II ii = CoppaObjectFactory.getIISearchCriteria("18653");
		String iiXml = CoppaObjectFactory.getCoppaIIXml(ii);
		Metadata mData = new Metadata(OperationNameEnum.getById.getName(), "externalId", ServiceTypeEnum.ORGANIZATION.getName());
		String poServiceResponse = sendMessage(iiXml, mData);
		assertNotNull(poServiceResponse);
		System.out.println(poServiceResponse);
	}
	
}
