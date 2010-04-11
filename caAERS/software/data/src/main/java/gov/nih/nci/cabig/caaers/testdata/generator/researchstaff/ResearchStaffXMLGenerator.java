package gov.nih.nci.cabig.caaers.testdata.generator.researchstaff;

import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.ObjectFactory;
import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.ResearchStaffType;
import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.SiteResearchStaffRoleType;
import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.SiteResearchStaffType;
import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Staff;
import gov.nih.nci.cabig.caaers.testdata.TestDataFileUtils;
import gov.nih.nci.cabig.caaers.testdata.generator.NCICode;
import gov.nih.nci.cabig.caaers.testdata.generator.XMLGenerator;

import java.util.List;

import javax.xml.bind.JAXBContext;

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
	public Staff getLoadedStaff(String nciCode) throws Exception{
		
		Staff loadedStaff = objectFactory.createStaff();
		int staffCounter = 1;
        for(int index=1;index<=researchStaffPerSite;index++){
			ResearchStaffType rsType = changeValues(getTemplateResearchStaff(),nciCode,staffCounter);
			loadedStaff.getResearchStaff().add(rsType);
			staffCounter++;
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
            for(String nciCode : NCICode.ORGANIZATION_LIST){
                Staff staff = rsXmlGenerator.getLoadedStaff(nciCode);
                marshal(staff, TestDataFileUtils.getResearchStaffTestDataFolder(), "rs_" + nciCode + ".xml");
            }

            System.out.print("Done");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
