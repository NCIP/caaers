package gov.nih.nci.cabig.caaers.coppa;

import edu.duke.cabig.c3pr.esb.Metadata;
import edu.duke.cabig.c3pr.esb.ServiceTypeEnum;
import edu.duke.cabig.c3pr.esb.impl.CaXchangeMessageBroadcasterImpl;
import edu.emory.mathcs.backport.java.util.Arrays;
import gov.nih.nci.cabig.caaers.esb.client.BroadcastException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import junit.framework.TestCase;

public class PersonServiceTest extends TestCase{
	
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
	
	//Will access the person query concurrently. 
	public void testPersonQueryConcurrently() throws Exception{
		List<String> payLoads = new ArrayList<String>();
		String payLoad1 = getPayloadForFile("classpath*:gov/nih/nci/cabig/caaers/pa/testdata/PERSON_SEARCH.xml");
		payLoads.add(payLoad1);
		String payLoad2 = getPayloadForFile("classpath*:gov/nih/nci/cabig/caaers/pa/testdata/LIMIT_OFFSET.xml");
		payLoads.add(payLoad2);
		
		List<PersonJobDetails> pjDetails = new ArrayList<PersonJobDetails>();
		int n = 10;
		for(int i =0; i < n; i++){
			PersonJobDetails pjDetail = new PersonJobDetails(" Search "+ i, payLoads);
			pjDetails.add(pjDetail);
			
			PersonSearchJob personJob = new PersonSearchJob(pjDetail);
			
			if(i > (n-5)) personJob.worker.join();
		}
		
		System.out.println("=========================Result=================================");
		for(PersonJobDetails pjDetail: pjDetails){
			if(pjDetail.failed()){
				System.out.println(pjDetail.getName() + " failed");
				System.out.println(pjDetail);
			}
			else{
				System.out.println(pjDetail.getName() + " passed");
			}
		}
		System.out.println("=========================--------=================================");
		
	}
	
	
	/**
	 * @author Biju Joseph
	 */
	class PersonSearchJob implements Runnable {
		public Thread worker;
		public PersonJobDetails pjDetail;
		public PersonSearchJob(PersonJobDetails pjDetail){
			this.pjDetail = pjDetail;
		}
		public void run() {
			try {
				Thread.sleep(1000);
				Metadata mData = new Metadata("query", "caAERS", ServiceTypeEnum.PERSON.getName());
				String paServiceResponse = sendMessage(pjDetail.getInput(),mData);
				pjDetail.setResultXML(paServiceResponse);
			} catch (Exception e) {
				pjDetail.setException(e);
			}
		}
	}
	
	/**
	 * @author Biju Joseph
	 */
	class PersonJobDetails {
		String name;
		List<String> input;
		String resultXML;
		Exception e;
		public PersonJobDetails(String name, List<String> input){
			this.name = name;
			this.input = input;
		}
		public boolean failed(){
			return e != null;
		}
		public List<String> getInput() {
			return input;
		}
		public Exception getException(){
			return e;
		}
		public void setException(Exception e){
			this.e = e;
		}
		public String getResultXML(){
			return resultXML;
		}
		public void setResultXML(String resultXML) {
			this.resultXML = resultXML;
		}
		public String getName(){
			return name;
		}
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder("PersonJobDetails {\r\n").append("name : ").append(name).append("\r\n");
			sb.append("Input : ").append(String.valueOf(input)).append("\r\n")
			  .append("resultXML :" ).append(String.valueOf(resultXML)).append("\r\n");
			if(e != null){
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				sb.append("Exception : ").append(sw.toString());
			}
			sb.append("\n}");
			return sb.toString();
		}
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
	

}
