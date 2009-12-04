package gov.nih.nci.cabig.caaers.coppa;

import edu.duke.cabig.c3pr.esb.Metadata;
import edu.duke.cabig.c3pr.esb.ServiceTypeEnum;

import java.util.ArrayList;
import java.util.List;

public class PersonServiceTest extends StudyProtocolServiceTest{
	
	public void testPerson_query() throws Exception{
		
		List<String> payLoads = new ArrayList<String>();
		String payLoad1 = getPayloadForFile("classpath*:gov/nih/nci/cabig/caaers/pa/testdata/PERSON_SEARCH.xml");
		payLoads.add(payLoad1);
		String payLoad2 = getPayloadForFile("classpath*:gov/nih/nci/cabig/caaers/pa/testdata/LIMIT_OFFSET.xml");
		payLoads.add(payLoad2);
		
		Metadata mData = new Metadata("query", "caAERS", ServiceTypeEnum.PERSON.getName());
		String paServiceResponse = sendMessage(payLoads,mData);
		assertNotNull(paServiceResponse);
		System.out.println("################### Person.query Start#################");
		System.out.println(paServiceResponse);
		System.out.println("################### Person.query End#################");
		System.out.print("");
	}
	

}
