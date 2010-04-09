package gov.nih.nci.cabig.caaers.testdata.study;

import gov.nih.nci.cabig.caaers.testdata.NCICode;
import gov.nih.nci.cabig.caaers.testdata.XMLGenerator;
import gov.nih.nci.cabig.caaers.webservice.*;

import javax.xml.bind.JAXBContext;

public class StudyXMLGenerator extends XMLGenerator {
	
	public static String templateXML = "exportedstudy_5876.xml";
	private ObjectFactory objectFactory;

    public StudyXMLGenerator() throws Exception{
        jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.webservice");
		unmarshaller = jaxbContext.createUnmarshaller();
		marshaller = jaxbContext.createMarshaller();
		objectFactory = new ObjectFactory();
    }


	/**
	 *
	 * @return StudyType, template read from the file. 
	 * @throws Exception
	 */
	private Study getTemplateStudy() throws Exception{
		Studies template = (Studies)unmarshaller.unmarshal(createInputStream(StudyXMLGenerator.class.getPackage(),templateXML));
		return template.getStudy().get(0);
	}

    /**
     * Will generate a study that can be loaded into caAERS, after modifying all the parameters.
     * @param i
     * @return
     * @throws Exception
     */
    private Study createAStudy( int i) throws Exception{
        Study study = getTemplateStudy();

        //cc id
        String idPattern = study.getCoordinatingCenter().getOrganizationAssignedIdentifier().getValue();
        String newIdPattern = idPattern +"." +i;

        //sponsor id
        String sIdPattern = study.getFundingSponsor().getOrganizationAssignedIdentifier().getValue();
        String newSIdPattern = sIdPattern + "." + i;

        //1. Modify the short and long title
        study.setShortTitle("TEST." + newIdPattern);
        study.setShortTitle("TEST.LongTitle." + newIdPattern);

        //2. Modify the sponsor and coodinating center identifier values
        study.getCoordinatingCenter().getOrganizationAssignedIdentifier().setValue(newIdPattern);
        study.getFundingSponsor().getOrganizationAssignedIdentifier().setValue(newSIdPattern);

        //3. Add dummy study sites and their investigators
        for(String siteNCICode : NCICode.ORGANIZATION_LIST){
            StudySiteType siteType = createStudySite(siteNCICode );
            siteType.getStudyInvestigators().getStudyInvestigator().add(createStudyInvestigator(siteNCICode, "SI", 1));
            siteType.getStudyInvestigators().getStudyInvestigator().add(createStudyInvestigator(siteNCICode, "PI", 2));
            study.getStudyOrganizations().getStudySite().add(siteType);
        }


        return study;
    }

//    private StudyPersonnelType createStudyPersonnel(String siteNCICode, int i) throws Exception{
//        SiteResearchStaffType siteRSType = new SiteResearchStaffType();
//
//        ResearchStaffType staffType = new ResearchStaffType();
//        staffType.setSiteResearchStaffs();
//        StudyPersonnelType rsType = new StudyPersonnelType();
//        rsType.setStartDate(toDay());
//        rsType.setRoleCode(PersonnelRoleCodeType.CAAERS_CENTRAL_OFFICE_SAE_CD);
//        rsType.setResearchStaff(staffType);
//    }

    /**
     * Will create a study Site.
     * @param stieNCICode
     * @return
     * @throws Exception
     */
    private StudySiteType createStudySite(String stieNCICode) throws Exception {
        OrganizationType orgType = new OrganizationType();
        orgType.setNciInstituteCode(stieNCICode);
        orgType.setName("Derived from " + stieNCICode);
        StudySiteType sType = new StudySiteType();
        sType.setOrganization(orgType);
        sType.setStudyInvestigators(new StudySiteType.StudyInvestigators());
        sType.setStudyPersonnels(new StudySiteType.StudyPersonnels());
        return sType;
    }

    /**
     * Creates a Study Investitgator
     * @param siteNCICode
     * @param roleCode
     * @param i
     * @return
     * @throws Exception
     */
    private StudyInvestigatorType createStudyInvestigator(String siteNCICode, String roleCode, int i) throws Exception{
        InvestigatorType invType = new InvestigatorType();
        invType.setFirstName(siteNCICode + ".FN." + i);
        invType.setLastName(siteNCICode + ".LN." + i);
        invType.setNciIdentifier(siteNCICode + ".inv" + i);

        SiteInvestigatorType siteInvType = new SiteInvestigatorType();
        siteInvType.setInvestigator(invType);
        
        StudyInvestigatorType sInvType = new StudyInvestigatorType();
        sInvType.setRoleCode(RoleCodeType.fromValue(roleCode));
        sInvType.setStartDate(toDay());
        sInvType.setSiteInvestigator(siteInvType);


        return sInvType;
    }

    /**
     *  Will return a collection of Study engulfed in Studies object
     * @return
     */
    public Studies createStudies(int start, int end) throws Exception {
       Studies studies = objectFactory.createStudies();

        for(int i = start ; i <= end; i++){
            studies.getStudy().add(createAStudy(i));
            i++;
        }

        return studies;
    }


	/**
	 * Main method
	 * @param args
	 */
	public static void main(String args[]){
		try{
			StudyXMLGenerator generator = new StudyXMLGenerator();
            Studies studies = generator.createStudies(1,100);
			marshal(studies, "Studies");
            
			System.out.print("Done");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
