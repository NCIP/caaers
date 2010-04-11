package gov.nih.nci.cabig.caaers.testdata.generator.investigator;

import gov.nih.nci.cabig.caaers.integration.schema.investigator.InvestigatorType;
import gov.nih.nci.cabig.caaers.integration.schema.investigator.ObjectFactory;
import gov.nih.nci.cabig.caaers.integration.schema.investigator.SiteInvestigatorType;
import gov.nih.nci.cabig.caaers.integration.schema.investigator.Staff;
import gov.nih.nci.cabig.caaers.testdata.TestDataFileUtils;
import gov.nih.nci.cabig.caaers.testdata.NCICode;
import gov.nih.nci.cabig.caaers.testdata.generator.XMLGenerator;

import javax.xml.bind.JAXBContext;

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
	public Staff getLoadedInvestigator(String nciCode) throws Exception{
		
		Staff loadedStaff = objectFactory.createStaff();
		int invCounter = 1;
        for(int index=1;index<=investigatorsPerSite;index++){
            InvestigatorType invType = changeValues(getTemplateInvestigator(),nciCode,invCounter);
            loadedStaff.getInvestigator().add(invType);
            invCounter++;
        }
		return loadedStaff;
	}
	
	
	/**
	 * This method changes certain values in the InvestigatorType object and return it.
	 * @param invType
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

		for(SiteInvestigatorType siType : invType.getSiteInvestigator()){
			siType.setStartDate(toDay());
			siType.setEndDate(nextYear());
		}
	}

    @Override
    public void generate() throws Exception {
        for(String nciCode : NCICode.ORGANIZATION_LIST){
			    Staff staff = getLoadedInvestigator(nciCode);
			    marshal(staff, TestDataFileUtils.getInvestigatorTestDataFolder(), "inv_"+nciCode+".xml");
            }
    }

    /**
	 * Main method
	 * @param args
	 */
	public static void main(String args[]){
		try{
			InvestigatorXMLGenerator invXmlGenerator = new InvestigatorXMLGenerator();
            invXmlGenerator.generate();
			System.out.print("Done");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
