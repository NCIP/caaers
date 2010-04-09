package gov.nih.nci.cabig.caaers.testdata.investigator;

import gov.nih.nci.cabig.caaers.integration.schema.investigator.InvestigatorType;
import gov.nih.nci.cabig.caaers.integration.schema.investigator.ObjectFactory;
import gov.nih.nci.cabig.caaers.integration.schema.investigator.SiteInvestigatorType;
import gov.nih.nci.cabig.caaers.integration.schema.investigator.Staff;
import gov.nih.nci.cabig.caaers.testdata.XMLGenerator;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.bind.JAXBContext;
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
	public static int investigatorsPerSite = 3;
	
	private ObjectFactory objectFactory;
	
	//You will find the Org names of these NCI CODES down below in this class.
	private static final List<String> ORGANIZATION_LIST = Arrays.asList(
			"02001","02002","02003","02004","02005","02006","02007","02008","02009","02010",
			"02011","02012","02013","02014","02015","02016","02017","02018","02019","02020",
			"02021","02022","03001","03002","03003","03004","03005","03006","03007","03008",
			"03009","03010","03011","03012","03013","03014","03015","03016","03017","03018",
			"03019","03020","03021","03022","03023","03024","03025","03026","03027","03028",
			"03029","03030","03031","03032","03033","03035","03036","03037","03038","03039",
			"03040","03041","03044","03045","03046","03048","03049","03050","03051","03052",
			"03053","03055","03056","03057","03058","03059","03060","03061","03062","03064",
			"03065","03066","03067","03068","03069","03070","03071","03072","03073","03074",
			"03075","03076","03077","03078","03079","03080","03081","03082","03083","03084"
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
			for(int index=1;index<=investigatorsPerSite;index++){
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
		
		String key = nciCode + ".INV" + index;
		invType.setLoginId(key);
		invType.setFirstName(key+".FN");
		invType.setLastName(key+ ".LN");
		invType.setEmailAddress(key+"@example.com");
		invType.setNciIdentifier(key);
		invType.getSiteInvestigator().get(0).setEmailAddress(key+"@null.net");
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
			marshal(staff, "InvestigatorData");
			System.out.print("Done");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}


/*
"02001";"Catedra De Oncologia Univ-Salvador"
"02002";"Hospital Militar Central"
"02003";"Hospitales Privado Guemes"
"02004";"Hosp. Rawson"
"02005";"Hosp. Teodoro Alvarez"
"02006";"Hosp. Ramos Mejia, Sala 18"
"02007";"Deutsches Hosp."
"02008";"Universidad Nacional Del Sur"
"02009";"Hosp. Frances"
"02010";"Hosp. Policial"
"02011";"Hosp. Israelita"
"02012";"Hosp. Municipal F. Santojanni"
"02013";"Instituto De Oncologia"
"02014";"Hosp. Interzonal Gen. San Martin"
"02015";"Hospital Italiano of Buenos Aires"
"02016";"San Martin De Tours 2980"
"02017";"Hematologia"
"02018";"Academia Nacional De Medicina"
"02019";"Lab/Reproduction and Lactation"
"02020";"Alexander Fleming Cancer Center"
"02021";"Hospital Universitario Austral"
"02022";"CERIM"
"03001";"WP Holman Clinic - Launceston General Hospital"
"03002";"Concord Repatriation General Hospital"
"03003";"Peter MacCallum Cancer Centre"
"03004";"Flinders Medical Center"
"03005";"Royal Prince Alfred Hospital"
"03006";"University of Melbourne"
"03007";"Mount Hospital"
"03008";"Sacred Heart Hospice"
"03009";"Woden Valley Hospital"
"03010";"Sir Charles Gairdner Hospital"
"03011";"Princess Alexandra Hospital"
"03012";"Woden Vallery Hospital"
"03013";"Royal Children's Hospital"
"03014";"Royal Perth Hospital"
"03015";"Alfred Hospital Melbourne"
"03016";"Saint. Vincent Hospital, Sydney"
"03017";"Royal North Shore Hospital"
"03018";"Royal Alexandra Hospital/Children"
"03019";"University of Tasmania"
"03020";"Adelaide Childrens Hospital"
"03021";"University of Sydney"
"03022";"Prince of Wales Hospital"
"03023";"Royal Newcastle Hospital"
"03024";"University of New South Wales"
"03025";"Sydney Hospital"
"03026";"Royal Adelaide Hospital"
"03027";"Royal North Shore Hospital of Sydney"
"03028";"Royal Childrens Hospital"
"03029";"Sydney West Area Health Service-Westmead Hospital"
"03030";"Prince Henrys Hospital"
"03031";"Saint Vincent's Hospital"
"03032";"Queen Elizabeth II Medical Center"
"03033";"Royal Hobart Hospital"
"03035";"Royal Melbourne Hospital"
"03036";"Saint George Hospital"
"03037";"The Alfred Hospital"
"03038";"The Prince Charles Hospital"
"03039";"Department of Health"
"03040";"Saint Vincent's Hospital"
"03041";"Ludwig Institute for Cancer Research"
"03044";"Austin Health"
"03045";"Royal Melbourne Hospital"
"03046";"Institute of Medical and Vet Science"
"03048";"Calvary Mater Newcastle Hospital"
"03049";"Austin and Repatriation Medical Center"
"03050";"The Geelong Hospital"
"03051";"Royal Brisbane Hospital"
"03052";"Mercy Hospital for Women"
"03053";"Queen Elizabeth Hospital"
"03055";"Princess Margaret Hospital for Children"
"03056";"Wesley Clinic for Haematology Oncology"
"03057";"The Canberra Hospital"
"03058";"Liverpool Cancer Therapy Center-Liverpool Hospital"
"03059";"Repatriation General Hosp."
"03060";"Latrobe Regional Hospital"
"03061";"Princess Alexandra Hospital"
"03062";"Western Hospital"
"03064";"Mater Private Hospital - Brisbane"
"03065";"Institute for Child Health Research"
"03066";"Bendigo Hospital"
"03067";"Royal Brisbane and Women's Hospital"
"03068";"Townsville General Hospital"
"03069";"Cancer Council of Western Australia"
"03070";"Gosford Hospital"
"03071";"Sydney Breast Cancer Institute"
"03072";"Orange Base Hospital"
"03073";"Fremantle Hospital"
"03074";"Monash Medical Center, Clayton Campus"
"03075";"Lidcombe-Bankstown Hospital"
"03076";"Australasian Gastro-Intestinal Trials Group Coordinating Center"
"03077";"The Canberra Hospital"
"03078";"Box Hill Hospital"
"03079";"Frankston Hospital"
"03080";"Saint Andrew's Medical Centre"
"03081";"John Hunter Children's Hospital"
"03082";"Lingard Private Hospital"
"03083";"Port Macquarie Base Hospital"
"03084";"Murray Valley Private Hospital"
*/