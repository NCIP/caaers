package gov.nih.nci.cabig.caaers.coppa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.semanticbits.coppasimulator.util.CoppaPAObjectFactory;

import edu.duke.cabig.c3pr.esb.Metadata;
import edu.duke.cabig.c3pr.esb.OperationNameEnum;
import edu.duke.cabig.c3pr.esb.ServiceTypeEnum;
import edu.duke.cabig.c3pr.esb.impl.CaXchangeMessageBroadcasterImpl;
import edu.emory.mathcs.backport.java.util.Arrays;
import gov.nih.nci.cabig.caaers.esb.client.BroadcastException;
import gov.nih.nci.cabig.caaers.utils.XMLUtil;

public class StudyProtocolServiceTest extends TestCase {
	
	
//	public void testStudyProtocol_search() throws Exception{
//		List<String> payLoads = new ArrayList<String>();
//		String limitOffSetXml = CoppaPAObjectFactory.getLimitOffsetXML(5, 0);
//		String studyProtocolSearchXml = CoppaPAObjectFactory.getStudyProtocolSearchXML();
//		payLoads.add(studyProtocolSearchXml);
//		payLoads.add(limitOffSetXml);
//		Metadata mData = new Metadata(OperationNameEnum.search.getName(), "caAERS", ServiceTypeEnum.STUDY_PROTOCOL.getName());
//		String paServiceResponse = sendMessage(payLoads,mData);
//		assertNotNull(paServiceResponse);
//		System.out.println("################### StudyProtocolService.search Start#################");
//		System.out.println(paServiceResponse);
//		System.out.println("################### StudyProtocolService.search End#################");
//		List<String> results = XMLUtil.getObjectsFromCoppaResponse(paServiceResponse);
//		for (String result:results) {
//			gov.nih.nci.coppa.services.pa.StudyProtocol studyProtocol = CoppaPAObjectFactory.getStudyProtocolObject(result);
//			System.out.println(studyProtocol.getAssignedIdentifier().getExtension());
//		}
//	}
	
	public void testStudyProtocol_search() throws Exception{
		
		List<String> payLoads = new ArrayList<String>();
		String payLoad1 = getPayloadForFile("classpath*:gov/nih/nci/cabig/caaers/pa/testdata/STUDY_PROTOCOL_SEARCH.xml");
		payLoads.add(payLoad1);
		String payLoad2 = getPayloadForFile("classpath*:gov/nih/nci/cabig/caaers/pa/testdata/LIMIT_OFFSET.xml");
		payLoads.add(payLoad2);
		
		Metadata mData = new Metadata(OperationNameEnum.search.getName(), "caAERS", ServiceTypeEnum.STUDY_PROTOCOL.getName());
		String paServiceResponse = sendMessage(payLoads,mData);
		assertNotNull(paServiceResponse);
		System.out.println("################### StudyProtocolService.search Start#################");
		System.out.println(paServiceResponse);
		System.out.println("################### StudyProtocolService.search End#################");
		System.out.print("");
	}
	
	public void testStudyProtocol_getStudyProtocol() throws Exception{

		String payLoad = getPayloadForFile("classpath*:gov/nih/nci/cabig/caaers/pa/testdata/STUDY_PROTOCOL_ID.xml");
		Metadata mData = new Metadata(OperationNameEnum.getStudyProtocol.getName(), "extId", ServiceTypeEnum.STUDY_PROTOCOL.getName());
		String paServiceResponse = sendMessage(payLoad,mData);
		assertNotNull(paServiceResponse);
		System.out.print("");
		System.out.println("################### StudyProtocolService.getStudyProtocol Start#################");
		System.out.println(paServiceResponse);
		System.out.println("################### StudyProtocolService.getStudyProtocol End#################");
		System.out.print("");
		
	}
	
	public void testStudyProtocol_getInterventionalStudyProtocol() throws Exception{

		Metadata mData = new Metadata(OperationNameEnum.getInterventionalStudyProtocol.getName(), "extId", ServiceTypeEnum.STUDY_PROTOCOL.getName());
		String srsPayLoad = getPayloadForFile("classpath*:gov/nih/nci/cabig/caaers/pa/testdata/SRS_STUDY_PROTOCOL_ID.xml");
		String srsPaServiceResponse = sendMessage(srsPayLoad,mData);
		assertNotNull(srsPaServiceResponse);
		System.out.print("");
		//System.out.println("###################SRS InterventionalStudyProtocol  START#################");
		System.out.println(srsPaServiceResponse);
		//System.out.println("###################SRS InterventionalStudyProtocol  End#################");
		System.out.print("");
		
	}
	
	public static Resource[] getResources(String pattern) throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(pattern);
        return resources;
    }
	
	/**
	 * Gets the payload xml as string for specified file.
	 * 
	 * @param filename the filename
	 * @return the payload for file
	 */
	public String getPayloadForFile(String fileLocation){
		String payloadXml = "";
        BufferedReader fr = null;
        try {
			File f = getResources(fileLocation)[0].getFile();
	        fr = new BufferedReader(new FileReader(f));
	        String temp = "";
			while ((temp = fr.readLine()) != null) {
				payloadXml += temp;
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			fail();
		} catch (IOException e2) {
			e2.printStackTrace();
			fail();
		} catch (Exception e3) {
			e3.printStackTrace();
			fail();
		}
        return payloadXml;
	}
	
	public String sendMessage(String message,Metadata metaData) throws gov.nih.nci.cabig.caaers.esb.client.BroadcastException {  
		return sendMessage(Arrays.asList(new String[]{message}), metaData);
		
    }
	
	public String sendMessage(List<String> messages,Metadata metaData) throws gov.nih.nci.cabig.caaers.esb.client.BroadcastException {    	
        String result = null;
        try {
        	CaXchangeMessageBroadcasterImpl broadCaster =  new CaXchangeMessageBroadcasterImpl();
        	broadCaster.setCaXchangeURL("https://ncias-c278-v.nci.nih.gov:58445/wsrf-caxchange/services/cagrid/CaXchangeRequestProcessor");
        	result = broadCaster.broadcastCoppaMessage(messages, metaData);
		} catch (edu.duke.cabig.c3pr.esb.BroadcastException e) {

            throw new gov.nih.nci.cabig.caaers.esb.client.BroadcastException(e);
		}
    	return result;
    }
		
}
