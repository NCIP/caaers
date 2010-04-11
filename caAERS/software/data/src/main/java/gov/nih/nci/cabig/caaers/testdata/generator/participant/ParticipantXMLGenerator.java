package gov.nih.nci.cabig.caaers.testdata.generator.participant;

import gov.nih.nci.cabig.caaers.testdata.TestDataFileUtils;
import gov.nih.nci.cabig.caaers.testdata.generator.NCICode;
import gov.nih.nci.cabig.caaers.testdata.generator.XMLGenerator;
import gov.nih.nci.cabig.caaers.webservice.participant.ObjectFactory;
import gov.nih.nci.cabig.caaers.webservice.participant.ParticipantType;
import gov.nih.nci.cabig.caaers.webservice.participant.Participants;

import javax.xml.bind.JAXBContext;


/**
 * This class generates an XML file which can be used to import Subject/Participant 
 * into caAERS for load / performance testing purposes.
 * @author Monish
 *
 */
public class ParticipantXMLGenerator extends XMLGenerator {
	
	public static String templateXML = "subject_template.xml";
	public static int subjectsPerSite = 80;
	private ObjectFactory objectFactory;
	


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
	 * 
	 * @return
	 * @throws Exception
	 */
	private ParticipantType getTemplateParticipant() throws Exception{
		Participants templateParticipants = (Participants)unmarshaller.unmarshal(createInputStream(ParticipantXMLGenerator.class.getPackage(),templateXML));
		return templateParticipants.getParticipant().get(0);
	}
	
	/**
	 * For each organization in ORGANIZATION_LIST. 80 Participants will be assigned.
	 * @return
	 * @throws Exception
	 */
	public Participants getLoadedParticipants(String studyPrimaryId, String siteNCICode) throws Exception{
		
		Participants loadedParticipants = objectFactory.createParticipants();
		int subjectCounter = 1;
        for(int index=1;index<=subjectsPerSite;index++){
			ParticipantType pType = changeValues(getTemplateParticipant(), studyPrimaryId,siteNCICode,subjectCounter);
			loadedParticipants.getParticipant().add(pType);
			subjectCounter++;
	    }
		
		return loadedParticipants;
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
		//Subject Identifier : SIx
		//Study Subject Identifier : NCICode.StudyPrimaryID.SSIx
		//(Note:- x is a running number)
		StringBuilder studySubjectId = new StringBuilder(nciCode).append(".").append(studyPrimaryId).append(".SSI").append(index);
		pType.setFirstName("FN"+index);
		pType.setLastName("LN"+index);
		pType.setMaidenName("MDN"+index);
		pType.setMiddleName("MN"+index);
		pType.getIdentifiers().getOrganizationAssignedIdentifier().get(0).setValue("SI"+index);
		pType.getIdentifiers().getOrganizationAssignedIdentifier().get(0).getOrganization().setNciInstituteCode(nciCode);
		pType.getAssignments().getAssignment().get(0).getStudySite().getOrganization().setNciInstituteCode(nciCode);
		pType.getAssignments().getAssignment().get(0).setStudySubjectIdentifier(studySubjectId.toString());
		return pType;
		
	}
	
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String args[]){
		try{

            String studyPrimaryIDPattern = "C5876";
            int studyStartIndex = 1;
            int studyEndIndex = 100;

            ParticipantXMLGenerator sXmlGenerator = new ParticipantXMLGenerator();

            for(int i =studyStartIndex; i <=studyEndIndex; i++ ){
                for(String siteNCICode : NCICode.ORGANIZATION_LIST){
                    String studyPrimaryID = studyPrimaryIDPattern + "." +i;
                    Participants participants = sXmlGenerator.getLoadedParticipants(studyPrimaryID,siteNCICode );
                    String fileName = "sub_" + studyPrimaryID + "_" + siteNCICode + ".xml" ;
                    marshal(participants, TestDataFileUtils.getSubjectTestDataFolder(), fileName);
                }
            }
            System.out.print("Done");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}