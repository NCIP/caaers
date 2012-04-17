package gov.nih.nci.cabig.caaers2adeers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamSource;

/**
 * Will resolve a file from classpath
 */
public class ClasspathURIResolver implements URIResolver  {
    
    protected static final Log log = LogFactory.getLog(ClasspathURIResolver.class);
    private ResourceLoader resourceLoader;
    private String baseFolder;

    public ClasspathURIResolver(ResourceLoader resourceLoader, String baseFolder) {
        this.resourceLoader = resourceLoader;
        this.baseFolder = baseFolder;
    }

    public Source resolve(String href, String base) throws TransformerException {
        if(log.isDebugEnabled()) log.debug("resolving (" + baseFolder + href + ") the resource [base : " + base + ", href :"+ href + "]");

        try{
            Resource resource = resourceLoader.getResource(baseFolder + href);
            if(resource != null && resource.exists()) return new StreamSource(resource.getInputStream());
        }catch (Exception e){
            log.error("Unable to load resource [base : " + base + ", href: " + href + "]", e);
        }
        if(log.isDebugEnabled()) log.debug("unable to find resource [base : " + base + ", href :"+ href + "], " +
                "so instructing to use default lookup");
        return null; //use default lookup
    }
}
