package gov.nih.nci.cabig.caaers.testdata.generator.researchstaff;

import gov.nih.nci.cabig.caaers.integration.schema.common.OrganizationRefType;
import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.*;
import gov.nih.nci.cabig.caaers.testdata.NCICode;
import gov.nih.nci.cabig.caaers.testdata.TestDataFileUtils;
import gov.nih.nci.cabig.caaers.testdata.generator.XMLGenerator;

import javax.xml.bind.JAXBContext;
import java.util.ArrayList;
import java.util.List;

/**
 * This class generates an XML file which can be used to import ResearchStaff into caAERS for load / performance testing purposes.
 * @author Monish
 *
 */
public class ResearchStaffForMultipleSitesXMLGenerator extends XMLGenerator {

	public static String templateXML = "researchstaff_template.xml";
	public static int researchStaffPerSite = 10;

	private ObjectFactory objectFactory;



	/**
	 * Default Constructor which initializes JaxbContext,Unmarshaller, Marshaller & ObjectFactory
	 * @throws Exception
	 */
	public ResearchStaffForMultipleSitesXMLGenerator(String templateFileName) throws Exception{
		jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.integration.schema.researchstaff");
		unmarshaller = jaxbContext.createUnmarshaller();
		marshaller = jaxbContext.createMarshaller();
		objectFactory = new ObjectFactory();
        templateXML = templateFileName;
	}

	/**
	 * Default Constructor which initializes JaxbContext,Unmarshaller, Marshaller & ObjectFactory
	 * @throws Exception
	 */
	public ResearchStaffForMultipleSitesXMLGenerator() throws Exception{
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
        System.out.println("Reading the template file: " + templateXML);
		Staff templateStaff = (Staff)unmarshaller.unmarshal(createInputStream(ResearchStaffForMultipleSitesXMLGenerator.class.getPackage(),templateXML));
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

        String key = nciCode + ".RS_" + index + "_All_Sites";
        rsType.setLoginId(key);
        rsType.setFirstName(key + ".FN");
        rsType.setLastName(key + ".LN");
        rsType.setEmailAddress(key + "@null.net");
        rsType.setNciIdentifier(key);
        rsType.getSiteResearchStaffs().getSiteResearchStaff().get(0).setEmailAddress(key+"@null.net");
        rsType.getSiteResearchStaffs().getSiteResearchStaff().get(0).getOrganizationRef().setNciInstituteCode(nciCode);

        for (String org : NCICode.ORGANIZATION_LIST) {
            if (org.equals(nciCode)) continue;

            key = org + ".RS";
            SiteResearchStaffType srst = new SiteResearchStaffType();
            srst.setOrganizationRef(new OrganizationRefType());
            srst.getOrganizationRef().setNciInstituteCode(org);
            srst.setEmailAddress(key + "@null.net");
            srst.setAssociateAllStudies(true);
            srst.setCity("Herndon");
            srst.setZip("20171");
            srst.setState("VA");
            srst.setStreet("13921 Park Center rd.");
            srst.setPhoneNumber("703-555-2222");
            srst.setFaxNumber("703-555-1111");
            srst.setSiteResearchStaffRoles(new SiteResearchStaffType.SiteResearchStaffRoles());
            srst.getSiteResearchStaffRoles().setSiteResearchStaffRole(new ArrayList<SiteResearchStaffRoleType>());

            List<SiteResearchStaffRoleType> srsrt = rsType.getSiteResearchStaffs().getSiteResearchStaff().get(0).getSiteResearchStaffRoles().getSiteResearchStaffRole();
            for(SiteResearchStaffRoleType sRsRoleType : srsrt) {
                SiteResearchStaffRoleType newSRSRT = new SiteResearchStaffRoleType();
                newSRSRT.setRole(sRsRoleType.getRole());
                srst.getSiteResearchStaffRoles().getSiteResearchStaffRole().add(newSRSRT);
            }
            rsType.getSiteResearchStaffs().getSiteResearchStaff().add(srst);
        }

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

    @Override
    public void generate() throws Exception {
        String nciCode = NCICode.ORGANIZATION_LIST.get(0);
        Staff staff = getLoadedStaff(nciCode);
        marshal(staff, TestDataFileUtils.getResearchStaffTestDataFolder(), "rs_" + nciCode + ".xml");
    }

    /**
	 * Main method
	 * @param args
	 */
	public static void main(String args[]){
		try{
			ResearchStaffForMultipleSitesXMLGenerator rsXmlGenerator = new ResearchStaffForMultipleSitesXMLGenerator("researchstaff_template_for_multipleSites.xml");
            rsXmlGenerator.generate();
            System.out.print("Done");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
