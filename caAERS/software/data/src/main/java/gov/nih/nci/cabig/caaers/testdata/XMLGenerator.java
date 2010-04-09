package gov.nih.nci.cabig.caaers.testdata;

import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import edu.nwu.bioinformatics.commons.ResourceRetriever;

/**
 * 
 * @author Monish
 *
 */
public abstract class XMLGenerator {
	
	
	protected JAXBContext jaxbContext = null;
	protected Unmarshaller unmarshaller = null;
	protected static Marshaller marshaller = null;

	
	
	/**
	 * This method creates a InputStream for a given fileName
	 * @param testDataFileName
	 * @return
	 * @throws FileNotFoundException
	 */
    public InputStream createInputStream(Package p,String fileName) throws FileNotFoundException {
        InputStream testDataStream = ResourceRetriever.getResource(p, fileName);
        return testDataStream;
    }

}
