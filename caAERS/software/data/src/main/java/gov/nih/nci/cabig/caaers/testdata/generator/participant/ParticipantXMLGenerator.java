package gov.nih.nci.cabig.caaers.testdata.generator.participant;

import gov.nih.nci.cabig.caaers.testdata.TestDataFileUtils;
import gov.nih.nci.cabig.caaers.testdata.NCICode;
import gov.nih.nci.cabig.caaers.testdata.generator.XMLGenerator;
import gov.nih.nci.cabig.caaers.webservice.participant.ObjectFactory;
import gov.nih.nci.cabig.caaers.webservice.participant.ParticipantType;
import gov.nih.nci.cabig.caaers.webservice.participant.Participants;

import javax.xml.bind.JAXBContext;


/**
 * This class generates an XML file which can be used to import Subject/Participant 
 * into caAERS for load / performance testing purposes.
 * @author Monish
 * @author Biju Joseph
 *
 */
public class ParticipantXMLGenerator extends XMLGenerator {
	
	public static String templateXML = "subject_template.xml";
	public static int subjectsPerSite = 40;
	private ObjectFactory objectFactory;

    private int startStudyIndex = 1;
    private int endStudyIndex = 1;
	


	/**
	 * Default Constructor which initializes JaxbContext,Unmarshaller, Marshaller & ObjectFactory  
	 * @throws Exception
	 */
	public ParticipantXMLGenerator() throws Exception{
		jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.webservice.participant");
		unmarshaller = jaxbContext.createUnmarshaller();
		marshaller = jaxbContext.createMarshaller();
		objectFactory = new ObjectFactory();
	}

    /**
	 * calls the default constructor
	 * @throws Exception
	 */
	public ParticipantXMLGenerator(int startStudyIndex, int endStudyIndex) throws Exception{
		this();
        this.startStudyIndex = startStudyIndex;
        this.endStudyIndex = endStudyIndex;
	}


    /**
	 * 
	 * @return
	 * @throws Exception
	 */
	private ParticipantType getTemplateParticipant() throws Exception{
		Participants templateParticipants = (Participants)unmarshaller.unmarshal(createInputStream(ParticipantXMLGenerator.class.getPackage(),templateXML));
		return templateParticipants.getParticipant().get(0);
	}
	

	
	/**
	 * This method changes certain values in the ParticipantType object and return it.
	 * @param pType
	 * @param nciCode
	 * @param index
	 * @return
	 */
	private ParticipantType changeValues(ParticipantType pType,String studyPrimaryId, String nciCode,int index) throws Exception{


        //modify study primary id.
		pType.getAssignments().getAssignment().get(0).getStudySite().getStudy().getIdentifiers().getIdentifier().setValue(studyPrimaryId);

        //First name 	: FNx
		//Last name 	: LNx
		//Maiden Name 	: MDNx
		//Middle Name 	: MNx
		//Subject Identifier : NCICode_SIx
		//Study Subject Identifier : NCICode_StudyPrimaryID_SSIx
		//(Note:- x is a running number)

        String idPattern = nciCode + "_" + studyPrimaryId +"_" ;
		StringBuilder studySubjectId = new StringBuilder(idPattern).append("SSI").append(index);
		pType.setFirstName("FN"+index);
		pType.setLastName("LN"+index);
		pType.setMaidenName("MDN"+index);
		pType.setMiddleName("MN"+index);
		pType.getIdentifiers().getOrganizationAssignedIdentifier().get(0).setValue(nciCode + "_SI"+index);
		pType.getIdentifiers().getOrganizationAssignedIdentifier().get(0).getOrganization().setNciInstituteCode(nciCode);
		pType.getAssignments().getAssignment().get(0).getStudySite().getOrganization().setNciInstituteCode(nciCode);
		pType.getAssignments().getAssignment().get(0).setStudySubjectIdentifier(studySubjectId.toString());
		return pType;
		
	}

    @Override
    public void generate() throws Exception {
         String studyPrimaryIDPattern = "C5876";

        for(int i =startStudyIndex; i <=endStudyIndex; i++ ){
            for(String siteNCICode : NCICode.ORGANIZATION_LIST){
                String studyPrimaryID = studyPrimaryIDPattern + "." +i;

                System.out.println("Generating participants for " + siteNCICode +".");
                for(int k = 1; k <= subjectsPerSite; k++){
                   Participants participants = objectFactory.createParticipants();
                   ParticipantType pType = changeValues(getTemplateParticipant(), studyPrimaryID,siteNCICode,k);
                   participants.getParticipant().add(pType);
                   String fileName = "sub_" + studyPrimaryID + "_" + siteNCICode + "_" + k +".xml" ;
                   marshal(participants, TestDataFileUtils.getSubjectTestDataFolder(), fileName);
                }
                
            }
        }
    }

    /**
	 * Main method
	 * @param args
	 */
	public static void main(String args[]){
		try{



            ParticipantXMLGenerator sXmlGenerator = new ParticipantXMLGenerator();
            sXmlGenerator.generate();

            System.out.print("Done...........");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}