package gov.nih.nci.cabig.caaers.testdata.researchstaff;

import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.ObjectFactory;
import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.ResearchStaffType;
import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.SiteResearchStaffRoleType;
import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.SiteResearchStaffType;
import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Staff;
import gov.nih.nci.cabig.caaers.testdata.XMLGenerator;

import java.io.File;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * This class generates an XML file which can be used to import ResearchStaff into caAERS for load / performance testing purposes.
 * @author Monish
 *
 */
public class ResearchStaffXMLGenerator extends XMLGenerator {
	
	public static String templateXML = "researchstaff_template.xml";
	
	private ObjectFactory objectFactory;
	
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
	 * Default Constructor which initializes JaxbContext,Unmarshaller, Marshaller & ObjectFactory  
	 * @throws Exception
	 */
	public ResearchStaffXMLGenerator() throws Exception{
		jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.integration.schema.researchstaff");
		unmarshaller = jaxbContext.createUnmarshaller();
		marshaller = jaxbContext.createMarshaller();
		objectFactory = new ObjectFactory();
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	private ResearchStaffType getTemplateResearchStaff() throws Exception{
		Staff templateStaff = (Staff)unmarshaller.unmarshal(createInputStream(ResearchStaffXMLGenerator.class.getPackage(),templateXML));
		return templateStaff.getResearchStaff().get(0);
	}
	
	/**
	 * For each organization in ORGANIZATION_LIST. 10 ResearchStaff will be associated.
	 * @return
	 * @throws Exception
	 */
	public Staff getLoadedStaff() throws Exception{
		
		Staff loadedStaff = objectFactory.createStaff();
		int staffCounter = 1;
		for(String nciCode : ORGANIZATION_LIST){
			for(int index=1;index<=10;index++){
				ResearchStaffType rsType = changeValues(getTemplateResearchStaff(),nciCode,staffCounter);
				loadedStaff.getResearchStaff().add(rsType);
				staffCounter++;
			}
		}
		return loadedStaff;
	}
	
	/**
	 * This method changes certain values in the ResearchStaffType object and return it.
	 * @param rsType
	 * @param nciCode
	 * @return
	 */
	private ResearchStaffType changeValues(ResearchStaffType rsType,String nciCode,int index) throws Exception{
		
		String key = nciCode + "-RS" + index;
		rsType.setLoginId(key);
		rsType.setFirstName(key+"-FN");
		rsType.setLastName(key+ "-LN");
		rsType.setEmailAddress(key+"@example.com");
		rsType.setNciIdentifier(key);
		rsType.getSiteResearchStaffs().getSiteResearchStaff().get(0).setEmailAddress(key+"@example.com");
		rsType.getSiteResearchStaffs().getSiteResearchStaff().get(0).getOrganizationRef().setNciInstituteCode(nciCode);
		modifyDates(rsType);
		return rsType;
		
	}
	
	/**
	 * Modified the StartDate and EndDate of SiteResearchStafRole.
	 * StartDate will be today's date & EndDate will be 1 year from today's date. 
	 * @param rsType
	 * @throws Exception
	 */
	private void modifyDates(ResearchStaffType rsType) throws Exception{
		
		DatatypeFactory df = DatatypeFactory.newInstance();
		Calendar gcNow = GregorianCalendar.getInstance();
		int year = gcNow.get(Calendar.YEAR); 
		int month = gcNow.get(Calendar.MONTH)+1;
		int day = gcNow.get(Calendar.DAY_OF_MONTH);
		int tz = DatatypeConstants.FIELD_UNDEFINED;
		
		XMLGregorianCalendar currXmlCal = df.newXMLGregorianCalendarDate(year, month, day, tz);
		XMLGregorianCalendar furXmlCal = df.newXMLGregorianCalendarDate(year+1, month, day, tz);
		
		List<SiteResearchStaffType> siteRsTypeList;
		List<SiteResearchStaffRoleType> siteRsRoleTypeList;
		siteRsTypeList = rsType.getSiteResearchStaffs().getSiteResearchStaff();
		for(SiteResearchStaffType sRsType : siteRsTypeList){
			siteRsRoleTypeList = sRsType.getSiteResearchStaffRoles().getSiteResearchStaffRole();
			for(SiteResearchStaffRoleType sRsRoleType : siteRsRoleTypeList){
				sRsRoleType.setStartDate(currXmlCal);
				sRsRoleType.setEndDate(furXmlCal);
			}
		}
	}

	/**
	 * Main method
	 * @param args
	 */
	public static void main(String args[]){
		try{
			ResearchStaffXMLGenerator rsXmlGenerator = new ResearchStaffXMLGenerator();
			Staff staff = rsXmlGenerator.getLoadedStaff();
			System.out.print(staff.getResearchStaff().size());
			marshaller.marshal(staff, new File("/Users/Moni/Misc/ResearchStaffData.xml"));
			System.out.print("Done");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
