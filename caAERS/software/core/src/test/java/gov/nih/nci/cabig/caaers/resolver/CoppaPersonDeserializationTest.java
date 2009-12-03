package gov.nih.nci.cabig.caaers.resolver;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.utils.XMLUtil;
import gov.nih.nci.coppa.po.Person;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;

import com.semanticbits.coppasimulator.util.CoppaObjectFactory;

public class CoppaPersonDeserializationTest extends AbstractTestCase {
	
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testSerializationOfCoppaPersonXML() throws Exception{
		String  xmlContent = getTestXMLFile("CoppaPersonSearchResult.xml");
		Thread currentThread = Thread.currentThread();
		
		final List<Throwable> exceptions = new ArrayList<Throwable>();
		
		for(int i = 0; i < 30; i++ ){
			
			if(currentThread.isInterrupted()) fail("The report serailzation failed, in child thread");
			
			DelayedSerializer delayedSeralizer = new DelayedSerializer(currentThread, xmlContent, "Serializer - " + i ,  1000, exceptions);
			
			if(i > 25)	delayedSeralizer.worker.join(); //make sure this thread waits for the others to complete
		}
		
		System.out.println("============ ************************* ++++++++++++++++");
		for(Throwable t : exceptions){
			t.printStackTrace();
		}
		assertTrue(exceptions.isEmpty());
		System.out.println("============ ************************* ++++++++++++++++");
	}
	
	public class DelayedSerializer  implements Runnable {
		private Thread parent;
		public Thread worker;
		private String xmlContent;
		private int sleepTime;
		String name; 
		private List<Throwable> throwables;
		
		public DelayedSerializer(Thread parentThread, String xmlContent, String name, int sleepTime, List<Throwable> throwables){
			this.parent = parentThread;
			this.xmlContent = xmlContent;
			this.worker = new Thread(this, name);
			this.worker.start();
			this.name = name;
		}

		public void run() {
			try {
				Thread.sleep(this.sleepTime);
				
				List<String> coppaPersons = XMLUtil.getObjectsFromCoppaResponse(xmlContent);
				assertNotNull(coppaPersons);
				assertFalse(coppaPersons.isEmpty());
				Person coppaPerson;
				for(String coppaPersonXml: coppaPersons){
					coppaPerson = CoppaObjectFactory.getCoppaPerson(coppaPersonXml);
					String personName = "null";
					if(coppaPerson.getName() != null){
						if(coppaPerson.getName().getPart() != null && coppaPerson.getName().getPart().size() > 0){
							personName = coppaPerson.getName().getPart().get(0).getValue();
						}
					}
					System.out.println("==========================================================================================");
					System.out.println();
					System.out.println( name + " :" +  personName);
					assertNotNull(coppaPerson.getIdentifier());
					System.out.println();
					System.out.println("==========================================================================================");
					
				}
				
				
			}catch(Throwable e){
				this.parent.interrupt();
				e.printStackTrace();
				throwables.add(e);
			}
		}
	}
	
	//load the xml file from classpath. 
	 private String getTestXMLFile(String fileName) throws Exception {
	    	
	        File testFile = new ClassPathResource("/gov/nih/nci/cabig/caaers/resolver/xml/" + fileName).getFile();
	        BufferedReader ds = new BufferedReader(new FileReader(testFile));
	        String line = null;
	        StringBuffer xml = new StringBuffer();
	        while ((line = ds.readLine()) != null) {
	            xml.append(line);
	        }
	        assertTrue("Content of the xml should not be null", xml.toString().length() > 0);
	        return xml.toString();
	}

}
