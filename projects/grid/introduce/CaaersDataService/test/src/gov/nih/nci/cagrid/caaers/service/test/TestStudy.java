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

public class TestStudy extends TestCase{
	
	public void setUp() throws Exception {
		super.setUp();
	}
	
	public void  testGetAllStudies() {
		System.out.println("Running the Grid Service Client");
		try{ 
			CaaersClient client = new CaaersClient("http://cagrid-cccwfu.wfubmc.edu:8080/wsrf/services/cagrid/Caaers");


            java.lang.Object qryObj = ObjectDeserializer.deserialize(new InputSource(new FileInputStream("test/resources/all_studies.xml")),CQLQuery.class);

			CQLQuery cqlQuery = (CQLQuery)qryObj;
            CQLQueryResults results = client.query(cqlQuery);

			CQLQueryResultsIterator iter = new CQLQueryResultsIterator(results, new FileInputStream(new File("src/gov/nih/nci/cagrid/caaers/client/client-config.wsdd")));

			System.out.println(" ");
			System.out.println(" - - - - Results - - - ");
			System.out.println(" ");
			
	           while (iter.hasNext()) {

	        	   gov.nih.nci.cabig.caaers.domain.Study obj = (gov.nih.nci.cabig.caaers.domain.Study) iter.next();

				   System.out.println(obj.getShortTitle() + " | " + obj.getLongTitle() + " | " + obj.getPhaseCode() + " | " + obj.getStatus());

				//   System.out.println( "Version is " + obj.getExpected() );

	           }
	           
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}