package gov.nih.nci.cabig.caaers.resolver;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.coppa.iso.DSet;
import gov.nih.nci.coppa.iso.Ii;
import gov.nih.nci.coppa.po.HealthCareProvider;
import gov.nih.nci.coppa.po.Id;
import gov.nih.nci.coppa.po.grid.dto.transform.po.IdTransformer;
import gov.nih.nci.coppa.services.grid.dto.transform.iso.DSETIITransformer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;

import org.iso._21090.DSETII;
import org.iso._21090.II;
import org.springframework.core.io.ClassPathResource;

import com.semanticbits.coppasimulator.util.CoppaObjectFactory;
import com.semanticbits.coppasimulator.util.XMLUtilities;
/**
 * 
 * @author Biju Joseph
 *
 */
public class HealthCareProviderDeserialzationTest extends AbstractTestCase {
	
	protected void setUp() throws Exception {
		super.setUp();
		
	}
	//tests a valid HealthcareProvider xml island serialization. 
	public void testSerializationOf_HealthcareProviderXML() throws Exception{
		String filename = "EachHealthCareProvider.xml";
		Reader reader = getTestXMLFile(filename);
		assertNotNull(reader);
		HealthCareProvider hp = (HealthCareProvider)new XMLUtilities().deserializeObject(HealthCareProvider.class, reader, com.semanticbits.coppasimulator.util.CoppaObjectFactory.COPPA_WSDD_FILE);
		assertEquals("NCI person entity identifier", hp.getPlayerIdentifier().getIdentifierName());
		assertEquals("NCI organization entity identifier", hp.getScoperIdentifier().getIdentifierName());
	}
	
	//Tests an xml, without having scoperIdentifier element.
	public void testSerializationOf_HealthcareProviderXML_WithNoScoperIdentifier() throws Exception{
		String filename = "EachHealthCareProvider_NoScoperIdentifier.xml";
		Reader reader = getTestXMLFile(filename);
		assertNotNull(reader);
		HealthCareProvider hp = (HealthCareProvider)new XMLUtilities().deserializeObject(HealthCareProvider.class, reader, com.semanticbits.coppasimulator.util.CoppaObjectFactory.COPPA_WSDD_FILE);
		assertEquals("NCI person entity identifier", hp.getPlayerIdentifier().getIdentifierName());
		assertNull(hp.getScoperIdentifier());
	}
	
	//throws NPE from XMLUtilities 
	public void testGetCoppaIIXML_WithNullAsInput() throws Exception {
		try {
			String orgIiXml = CoppaObjectFactory.getCoppaIIXml((II)null);
		} catch (NullPointerException e) {
			//testing the stack trace.... 
			//at com.semanticbits.coppasimulator.util.XMLUtilities.serializeObject(XMLUtilities.java:15)
			assertEquals(15, e.getStackTrace()[0].getLineNumber());
		}
	}
	
	//check to see that Idtransformer returns null, if input is null
	public void testScoperIdentifierBeingNull(){
		DSETIITransformer dsetIITransformer = DSETIITransformer.INSTANCE;
		DSETII dsetii = new DSETII();
		dsetii.getItem().add(null);
		DSet<Ii> dsetIi= dsetIITransformer.toDto(dsetii);
		Id id = IdTransformer.INSTANCE.toXml(dsetIi.getItem().iterator().next());
		assertNull(id);
	}
	
	
	
	//load the xml file from classpath. 
	 private Reader getTestXMLFile(String fileName) throws Exception {
	    	
	        File testFile = new ClassPathResource("/gov/nih/nci/cabig/caaers/resolver/xml/" + fileName).getFile();
	        BufferedReader ds = new BufferedReader(new FileReader(testFile));
	        return ds;
	      
	    }

}
