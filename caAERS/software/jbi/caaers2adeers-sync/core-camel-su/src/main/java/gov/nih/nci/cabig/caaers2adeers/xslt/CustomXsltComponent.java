/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers2adeers.xslt;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.builder.xml.ResultHandlerFactory;
import org.apache.camel.builder.xml.XsltBuilder;
import org.apache.camel.builder.xml.XsltUriResolver;
import org.apache.camel.component.xslt.XsltComponent;
import org.apache.camel.converter.jaxp.XmlConverter;
import org.apache.camel.impl.ProcessorEndpoint;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.URIResolver;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

/**
 * Will extend the XSLT component, provided by spring to load referenced (included/imported) XSLT files from 
 * classpath (including JARS).
 *
 * Note this is a direct copy of org.apache.camel.component.xslt.XsltComponent, with minor modification to use  ThreadSafeXsltBuilder instead of XsltBuilder
 */
public class CustomXsltComponent extends XsltComponent {

    protected static final Log log = LogFactory.getLog(CustomXsltComponent.class);

    private Boolean contentCache = false;

    protected Endpoint createEndpoint(String uri, final String remaining, Map<String, Object> parameters) throws Exception {
        final Resource resource = resolveMandatoryResource(remaining);
        if (log.isDebugEnabled()) {
            log.debug(this + " using schema resource: " + resource);
        }
        final XsltBuilder xslt = getCamelContext().getInjector().newInstance(ThreadSafeXsltBuilder.class);

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

        // lookup custom resolver to use
        URIResolver resolver = resolveAndRemoveReferenceParameter(parameters, "uriResolver", URIResolver.class);
        if (resolver == null) {
            // not in endpoint then use component specific resolver
            resolver = getUriResolver();
        }
        if (resolver == null) {
            // fallback to use a Camel specific resolver
            resolver = new XsltUriResolver(getCamelContext().getClassResolver(), remaining);
        }
        // set resolver before input stream as resolver is used when loading the input stream
        xslt.setUriResolver(resolver);

        ResultHandlerFactory resultHandlerFactory = resolveAndRemoveReferenceParameter(parameters, "resultHandlerFactory", ResultHandlerFactory.class);
        if (resultHandlerFactory != null) {
            xslt.setResultHandlerFactory(resultHandlerFactory);
        }

        Boolean failOnNullBody = getAndRemoveParameter(parameters, "failOnNullBody", Boolean.class);
        if (failOnNullBody != null) {
            xslt.setFailOnNullBody(failOnNullBody);
        }
        String output = getAndRemoveParameter(parameters, "output", String.class);
        configureOutput(xslt, output);

        configureXslt(xslt, uri, remaining, parameters);
        loadResource(xslt, resource);

        // default to use the cache option from the component if the endpoint did not have the contentCache parameter
        boolean cache = getAndRemoveParameter(parameters, "contentCache", Boolean.class, contentCache);
        if (!cache) {
            return new ProcessorEndpoint(uri, this, xslt) {
                @Override
                protected void onExchange(Exchange exchange) throws Exception {
                    // force to load the resource on each exchange as we are not cached
                    loadResource(xslt, resource);
                    super.onExchange(exchange);
                }
            };
        } else {
            // we have already loaded xslt so we are cached
            return new ProcessorEndpoint(uri, this, xslt);
        }
    }


    private void loadResource(XsltBuilder xslt, Resource resource) throws TransformerConfigurationException {
        log.debug("Loading resource by :" + this + " , resource : " + resource);
        try {
            if (resource instanceof UrlResource) {
                // prefer to use file when a file based url
                File file = resource.getFile();
                if (file != null) {
                    // check if the file exists and report a better error as the XSLT
                    // will just say it cannot compile the stylesheet file
                    if (!file.exists()) {
                        throw new FileNotFoundException("File: " + file + " not found.");
                    }
                    xslt.setTransformerFile(file);
                } else {
                    xslt.setTransformerURL(resource.getURL());
                }
            } else {
                // fallback and use input stream
                xslt.setTransformerInputStream(resource.getInputStream());
            }
        } catch (Exception e) {
            // include information about the resource in the caused exception, so its easier for
            // end users to know which resource failed
            throw new TransformerConfigurationException(e.getMessage() + " " + resource.toString(), e);
        }
    }



}
