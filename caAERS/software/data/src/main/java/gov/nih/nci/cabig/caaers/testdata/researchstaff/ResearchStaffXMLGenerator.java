package gov.nih.nci.cabig.caaers.testdata.researchstaff;

import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.ObjectFactory;
import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.ResearchStaffType;
import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.SiteResearchStaffRoleType;
import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.SiteResearchStaffType;
import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Staff;
import gov.nih.nci.cabig.caaers.testdata.NCICode;
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
 * This class generates an XML file which can be used to import ResearchStaff into caAERS for load / performance testing purposes.
 * @author Monish
 *
 */
public class ResearchStaffXMLGenerator extends XMLGenerator {
	
	public static String templateXML = "researchstaff_template.xml";
	public static int researchStaffPerSite = 10;
	
	private ObjectFactory objectFactory;
	

	
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
		for(String nciCode : NCICode.ORGANIZATION_LIST){
			for(int index=1;index<=researchStaffPerSite;index++){
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
		
		String key = nciCode + ".RS" + index;
		rsType.setLoginId(key);
		rsType.setFirstName(key+".FN");
		rsType.setLastName(key+ ".LN");
		rsType.setEmailAddress(key+"@null.net");
		rsType.setNciIdentifier(key);
		rsType.getSiteResearchStaffs().getSiteResearchStaff().get(0).setEmailAddress(key+"@null.net");
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

		
		List<SiteResearchStaffType> siteRsTypeList;
		List<SiteResearchStaffRoleType> siteRsRoleTypeList;
		siteRsTypeList = rsType.getSiteResearchStaffs().getSiteResearchStaff();
		for(SiteResearchStaffType sRsType : siteRsTypeList){
			siteRsRoleTypeList = sRsType.getSiteResearchStaffRoles().getSiteResearchStaffRole();
			for(SiteResearchStaffRoleType sRsRoleType : siteRsRoleTypeList){
				sRsRoleType.setStartDate(toDay());
				sRsRoleType.setEndDate(nextYear());
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
			marshal(staff, "ResearchStaffData");
			System.out.print("Done");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
