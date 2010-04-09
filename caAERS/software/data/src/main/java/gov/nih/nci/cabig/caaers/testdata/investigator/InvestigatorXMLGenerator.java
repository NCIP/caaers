package gov.nih.nci.cabig.caaers.testdata.investigator;

import gov.nih.nci.cabig.caaers.integration.schema.investigator.InvestigatorType;
import gov.nih.nci.cabig.caaers.integration.schema.investigator.ObjectFactory;
import gov.nih.nci.cabig.caaers.integration.schema.investigator.SiteInvestigatorType;
import gov.nih.nci.cabig.caaers.integration.schema.investigator.Staff;
import gov.nih.nci.cabig.caaers.testdata.XMLGenerator;

import java.io.File;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * This class generates an Xml file which conforms to Investigator.xsd. 
 * Used to generate data for load / performance testing
 * @author Monish
 *
 */
public class InvestigatorXMLGenerator extends XMLGenerator{
	
	public static String templateXML = "investigator_template.xml";
	
	private JAXBContext jaxbContext = null;
	private Unmarshaller unmarshaller = null;
	private static Marshaller marshaller = null;
	private ObjectFactory objectFactory;
	
	private static final List<String> ORGANIZATION_LIST = Arrays.asList(
			"02001","02002","02003","02004","02005","02006","02007","02008","02009","02010"
			);

	/**
	 * Default constructor. Initializes JaxbContext, Marshaller, Unmarshaller & ObjectFactory.
	 * @throws Exception
	 */
	public InvestigatorXMLGenerator() throws Exception{
		jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.integration.schema.investigator");
		unmarshaller = jaxbContext.createUnmarshaller();
		marshaller = jaxbContext.createMarshaller();
		objectFactory = new ObjectFactory();
	}

	/**
	 * This method return a Investigator read from the template xml.
	 * @return
	 * @throws Exception
	 */
	private InvestigatorType getTemplateInvestigator() throws Exception{
		Staff templateStaff = (Staff)unmarshaller.unmarshal(createInputStream(InvestigatorXMLGenerator.class.getPackage(),templateXML));
		return templateStaff.getInvestigator().get(0);
	}
	
	
	/**
	 * For each organization in ORGANIZATION_LIST. 10 INVESTIGATOR will be associated.
	 * @return
	 * @throws Exception
	 */
	public Staff getLoadedStaff() throws Exception{
		
		Staff loadedStaff = objectFactory.createStaff();
		int invCounter = 1;
		for(String nciCode : ORGANIZATION_LIST){
			for(int index=1;index<=10;index++){
				InvestigatorType invType = changeValues(getTemplateInvestigator(),nciCode,invCounter);
				loadedStaff.getInvestigator().add(invType);
				invCounter++;
			}
		}
		return loadedStaff;
	}
	
	
	/**
	 * This method changes certain values in the InvestigatorType object and return it.
	 * @param rsType
	 * @param nciCode
	 * @return
	 */
	private InvestigatorType changeValues(InvestigatorType invType,String nciCode,int index) throws Exception{
		
		String key = nciCode + "-INV" + index;
		invType.setLoginId(key);
		invType.setFirstName(key+"-FN");
		invType.setLastName(key+ "-LN");
		invType.setEmailAddress(key+"@example.com");
		invType.setNciIdentifier(key);
		invType.getSiteInvestigator().get(0).setEmailAddress(key+"@example.com");
		invType.getSiteInvestigator().get(0).getOrganizationRef().setNciInstituteCode(nciCode);
		modifyDates(invType);
		return invType;
		
	}
	
	/**
	 * Modifies the StartDate and EndDate of SiteInvestigator.
	 * StartDate will be today's date & EndDate will be 1 year from today's date. 
	 * @param invType
	 * @throws Exception
	 */
	private void modifyDates(InvestigatorType invType) throws Exception{
		
		DatatypeFactory df = DatatypeFactory.newInstance();
		Calendar gcNow = GregorianCalendar.getInstance();
		int year = gcNow.get(Calendar.YEAR); 
		int month = gcNow.get(Calendar.MONTH)+1;
		int day = gcNow.get(Calendar.DAY_OF_MONTH);
		int tz = DatatypeConstants.FIELD_UNDEFINED;
		
		XMLGregorianCalendar currXmlCal = df.newXMLGregorianCalendarDate(year, month, day, tz);
		XMLGregorianCalendar furXmlCal = df.newXMLGregorianCalendarDate(year+1, month, day, tz);
		
		for(SiteInvestigatorType siType : invType.getSiteInvestigator()){
			siType.setStartDate(currXmlCal);
			siType.setEndDate(furXmlCal);
		}
	}
		
	
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String args[]){
		try{
			InvestigatorXMLGenerator invXmlGenerator = new InvestigatorXMLGenerator();
			Staff staff = invXmlGenerator.getLoadedStaff();
			System.out.print(staff.getInvestigator().size());
			marshaller.marshal(staff, new File("/Users/Moni/Misc/InvestigatorData.xml"));
			System.out.println("Done");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
