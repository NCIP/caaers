package gov.nih.nci.cabig.caaers.testdata;

import java.io.FileNotFoundException;
import java.io.InputStream;

import edu.nwu.bioinformatics.commons.ResourceRetriever;

/**
 * 
 * @author Monish
 *
 */
public abstract class XMLGenerator {
	
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
