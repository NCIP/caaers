package gov.nih.nci.cabig.caaers2adeers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;

/**
 * Will resolve the files included files from classpath
 */
public class CustomXSLTResolver implements URIResolver {

    protected static final Log log = LogFactory.getLog(CustomXSLTResolver.class);
    public Source resolve(String href, String base) throws TransformerException {

        log.debug("href=" + href + " : base=" + base);
        return null;
    }
}
