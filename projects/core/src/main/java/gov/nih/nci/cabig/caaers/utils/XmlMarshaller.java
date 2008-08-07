package gov.nih.nci.cabig.caaers.utils;

import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Marshaller;
import org.xml.sax.InputSource;

public class XmlMarshaller {

    /**
     * Will serialize a caaers adverse event report domain object into XML
     * 
     * @param beanObject ,
     *                Object to be serailzed
     * @return XML String
     * @throws XMLUtilityException
     */
    public String toXML(Object beanObject, String mappingFile) throws Exception {
        Mapping map = getMapping(mappingFile);
        StringWriter sw = new StringWriter();
        this.toXML(beanObject, sw, map);
        return sw.toString();
    }

    /**
     * Will serialize a domain object into XML
     * 
     * @param beanObject
     * @throws XMLUtilityException
     */
    private void toXML(Object beanObject, Writer stream, Mapping map) throws Exception {
        // set mapping before marshalling

        try {
            // write it out as XML

            Marshaller marshaller = new Marshaller(stream);
            marshaller.setMapping(map);
            marshaller.setValidation(true);
            marshaller.setSupressXMLDeclaration(true);
            marshaller.marshal(beanObject);

        } catch (Exception ex) {
            throw new Exception(ex);
        }

    }

    private Mapping getMapping(String mappingFile) throws Exception {
        Mapping map = new Mapping();
        try {
            InputStream stream = Thread.currentThread().getContextClassLoader()
                            .getResourceAsStream(mappingFile);

            InputSource mappingSource = new InputSource(stream);

            map.loadMapping(mappingSource);

        } catch (Exception e) {
            throw new Exception("Error with castor mapping  " + e);
        }

        return map;
    }

}
