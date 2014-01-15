/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class XsltTransformer {
	
	  /**
     * 
     * @param inXml
     * @param xsltFile
     * @return
     * @throws Exception
     */
    public String toText(String inXml, String xsltFile) throws Exception {
    	// Use this code if loading by classpath resource but loading lookup.xml is an issue by this method.
        //  Resource[] resources = getResources(xsltFile);
        //  InputStream stream =  resources[0].getInputStream();
        //  Source xsltSource = new StreamSource(stream);
           
    		Source src = new StreamSource(new File(inXml));
            Source xsltSource = new StreamSource(new File(xsltFile));

            StringWriter outStr = new StringWriter();
            StreamResult result = new StreamResult(outStr);

            // the factory pattern supports different XSLT processors
            TransformerFactory transFact = TransformerFactory.newInstance();
        //    transFact.setURIResolver(new ClasspathResourceURIResolver()); // use this if loading as resource
            Transformer trans = transFact.newTransformer(xsltSource);

            trans.transform(src, result);// transform(xmlSource, new StreamResult(System.out));

            return outStr.toString();
    	
    }
    
    public Resource[] getResources(String pattern) throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(pattern);
        return resources;
    }

}

// Use this code if loading xslt as classpath resource.
/*class ClasspathResourceURIResolver implements URIResolver {
	  public Source resolve(String href, String base) throws TransformerException {
	    return new StreamSource(ClasspathResourceURIResolver.class.getClassLoader().getResourceAsStream(href));
	  }
}*/
