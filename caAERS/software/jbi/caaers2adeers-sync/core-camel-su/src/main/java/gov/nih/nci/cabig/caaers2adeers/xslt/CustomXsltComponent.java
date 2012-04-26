package gov.nih.nci.cabig.caaers2adeers.xslt;

import org.apache.camel.Endpoint;
import org.apache.camel.builder.xml.XsltBuilder;
import org.apache.camel.component.xslt.XsltComponent;
import org.apache.camel.converter.jaxp.XmlConverter;
import org.apache.camel.impl.ProcessorEndpoint;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;

import javax.xml.transform.TransformerFactory;
import java.util.Map;

/**
 * Will extend the XSLT component, provided by spring to load referenced (included/imported) XSLT files from 
 * classpath (including JARS). 
 */
public class CustomXsltComponent extends XsltComponent {

    protected static final Log log = LogFactory.getLog(CustomXsltComponent.class);


    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        Resource resource = resolveMandatoryResource(remaining);
        if (log.isDebugEnabled()) {
            log.debug(this + " using schema resource: " + resource);
        }
        XsltBuilder xslt = getCamelContext().getInjector().newInstance(XsltBuilder.class);

        // lets allow the converter to be configured
        XmlConverter converter = resolveAndRemoveReferenceParameter(parameters, "converter", XmlConverter.class);
        if (converter == null) {
            converter = getXmlConverter();
        }
        if (converter != null) {
            xslt.setConverter(converter);
        }

        String transformerFactoryClassName = getAndRemoveParameter(parameters, "transformerFactoryClass", String.class);
        TransformerFactory factory = null;
        if (transformerFactoryClassName != null) {
            // provide the class loader of this component to work in OSGi environments
            Class<?> factoryClass = getCamelContext().getClassResolver().resolveClass(transformerFactoryClassName, XsltComponent.class.getClassLoader());
            if (factoryClass != null) {
                factory = (TransformerFactory) getCamelContext().getInjector().newInstance(factoryClass);
            } else {
                log.warn("Cannot find the TransformerFactoryClass with the class name: " + transformerFactoryClassName);
            }
        }

        if (parameters.get("transformerFactory") != null) {
            factory = resolveAndRemoveReferenceParameter(parameters, "transformerFactory", TransformerFactory.class);
        }

        if (factory != null) {
            xslt.getConverter().setTransformerFactory(factory);
        }

        log.info("Transformer factory  : " + xslt.getConverter().getTransformerFactory().getClass().getName());
        xslt.getConverter()
                .getTransformerFactory()
                .setURIResolver(new ClasspathURIResolver(getResourceLoader(),
                remaining.substring(0, remaining.lastIndexOf('/') + 1) ));
        xslt.setTransformerInputStream(resource.getInputStream());

        configureXslt(xslt, uri, remaining, parameters);
        return new ProcessorEndpoint(uri, this, xslt);
    }


}
