package gov.nih.nci.cabig.caaers.testdata;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;

import edu.nwu.bioinformatics.commons.ResourceRetriever;
import gov.nih.nci.cabig.caaers.utils.DateUtils;

/**
 * 
 * @author Monish
 *
 */
public abstract class XMLGenerator {
	
	
	protected JAXBContext jaxbContext = null;
	protected Unmarshaller unmarshaller = null;
	protected static Marshaller marshaller = null;
	public static final String USER_HOME = System.getProperty("user.home");
	
	
	/**
	 * This method creates a InputStream for a given fileName
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 */
	protected InputStream createInputStream(Package p,String fileName) throws FileNotFoundException {
        InputStream testDataStream = ResourceRetriever.getResource(p, fileName);
        return testDataStream;
    }

    /**
     * Return a XML date, which is Today
     * @return
     * @throws Exception
     */
    public XMLGregorianCalendar toDay() throws Exception{
        Calendar c = Calendar.getInstance();

		return DatatypeFactory.newInstance().newXMLGregorianCalendarDate(c.get(Calendar.YEAR),
                c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED);
    }

    /**
     * Return a XML date, which is an year from Today
     * @return
     * @throws Exception
     */
    public XMLGregorianCalendar nextYear() throws Exception{
        Calendar c = Calendar.getInstance();
        return DatatypeFactory.newInstance().newXMLGregorianCalendarDate(c.get(Calendar.YEAR),
                c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED);
    }

    
	/**
	 * Generates a xml file of the object given, under $USER_HOME/testdatafiles/
	 * @param object
	 * @param fileName
	 * @throws Exception
	 */
	protected static void marshal(Object object,String fileName) throws Exception{
		StringBuilder sb = new StringBuilder(USER_HOME);
		try{
			sb.append("/testdatafiles");
			File file = new File(sb.toString());
			if(!file.isDirectory()){
				file.mkdir();
			}
			sb.append("/").append(fileName).append("_").append(DateUtils.today()).append(".xml");
			marshaller.marshal(object, new File(sb.toString()));
		}catch(Exception e){
			throw e;
		}
	}



}
