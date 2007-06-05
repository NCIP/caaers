package gov.nih.nci.cabig.caaers.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.xml.Marshaller;


public class XmlMarshaller {

    // Override the default by calling setMappingFile
    private String mappingFile = "caaers-castor-mapping.xml";
    
    /**
     * Will serialize a caaers adverse event report domain object
     * into XML
     *
     * @param beanObject ,  Object to be serailzed
     * @return XML String
     * @throws XMLUtilityException
     */
    public String toXML(Object beanObject) throws Exception{
        StringWriter sw = new StringWriter();
        this.toXML(beanObject, sw);
        return sw.toString();
    }
    
    /**
     * Will serialize a domain object
     * into XML
     *
     * @param beanObject
     * @throws XMLUtilityException
     */
    private void toXML(Object beanObject, Writer stream) throws Exception{
        //set mapping before marshalling
    	
        try {        	  
            // write it out as XML
        	Mapping map = getMapping();
            Marshaller marshaller = new Marshaller(stream);
            marshaller.setMapping(map);
            marshaller.marshal(beanObject);

            System.out.println("done");
                
        } catch (Exception ex) {
            throw new Exception (ex);
        }

    }
    
    public void setMappingFile(String mappingFile) {
        this.mappingFile = mappingFile;
    }
    
    private Mapping getMapping()  throws Exception{
        Mapping map = new Mapping();
        try {
			map.loadMapping(mappingFile);
		} catch (IOException e) {
			throw new Exception ("Error with castor mapping file " + e);
		} catch (MappingException e) {
			throw new Exception ("Error with castor mapping  " + e);
		}
        
        return map;
    }
    
	
}
