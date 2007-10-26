package gov.nih.nci.cagrid.caaers.service.test;

import gov.nih.nci.cagrid.caaers.client.CaaersClient;
import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.cagrid.data.utilities.CQLQueryResultsIterator;

import java.io.File;
import java.io.FileInputStream;

import junit.framework.TestCase;

import org.globus.wsrf.encoding.ObjectDeserializer;
import org.xml.sax.InputSource;

public class TestAe extends TestCase{
	
	public void setUp() throws Exception {
		super.setUp();
	}
	
	public void  testGetAllAes() {
		System.out.println("All Aes ");
		try{ 
			CaaersClient client = new CaaersClient("http://10.10.10.2:18030/wsrf/services/cagrid/Caaers");


            java.lang.Object qryObj = ObjectDeserializer.deserialize(new InputSource(new FileInputStream("test/resources/all_aes.xml")),CQLQuery.class);

			CQLQuery cqlQuery = (CQLQuery)qryObj;
            CQLQueryResults results = client.query(cqlQuery);

			CQLQueryResultsIterator iter = new CQLQueryResultsIterator(results, new FileInputStream(new File("src/gov/nih/nci/cagrid/caaers/client/client-config.wsdd")));

			System.out.println(" ");
			System.out.println(" - - - - Results - - - ");
			System.out.println(" ");
			
	           while (iter.hasNext()) {

	        	   gov.nih.nci.cabig.caaers.domain.AdverseEvent obj = (gov.nih.nci.cabig.caaers.domain.AdverseEvent) iter.next();

				   System.out.println(obj.getId() + " | " + obj.getComments());

				//   System.out.println( "Version is " + obj.getExpected() );

	           }
	           
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	public void  testGetAesFilterOnGradeAndHospitalization() {
		System.out.println("Adverse Events Grade > 3 and Hosp is hosp");
		try{ 
			CaaersClient client = new CaaersClient("http://10.10.10.2:18030/wsrf/services/cagrid/Caaers");


            java.lang.Object qryObj = ObjectDeserializer.deserialize(new InputSource(new FileInputStream("test/resources/aes_grade_hosp.xml")),CQLQuery.class);

			CQLQuery cqlQuery = (CQLQuery)qryObj;
            CQLQueryResults results = client.query(cqlQuery);

			CQLQueryResultsIterator iter = new CQLQueryResultsIterator(results, new FileInputStream(new File("src/gov/nih/nci/cagrid/caaers/client/client-config.wsdd")));

			System.out.println(" ");
			System.out.println(" - - - - Results - - - ");
			System.out.println(" ");
			
	           while (iter.hasNext()) {

	        	   gov.nih.nci.cabig.caaers.domain.AdverseEvent obj = (gov.nih.nci.cabig.caaers.domain.AdverseEvent) iter.next();

				   System.out.println(obj.getId() + " | " + obj.getComments());

				//   System.out.println( "Version is " + obj.getExpected() );

	           }
	           
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
