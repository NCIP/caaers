package gov.nih.nci.cabig.caaers.testdata.subject;

import gov.nih.nci.cabig.caaers.testdata.NCICode;
import gov.nih.nci.cabig.caaers.testdata.XMLGenerator;
import gov.nih.nci.cabig.caaers.webservice.participant.ObjectFactory;
import gov.nih.nci.cabig.caaers.webservice.participant.ParticipantType;
import gov.nih.nci.cabig.caaers.webservice.participant.Participants;

import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBContext;


/**
 * This class generates an XML file which can be used to import Subject/Participant 
 * into caAERS for load / performance testing purposes.
 * @author Monish
 *
 */
public class SubjectXMLGenerator extends XMLGenerator{
	
	public static String templateXML = "subject_template.xml";
	public static int subjectsPerSite = 80;
	private ObjectFactory objectFactory;
	


	/**
	 * Default Constructor which initializes JaxbContext,Unmarshaller, Marshaller & ObjectFactory  
	 * @throws Exception
	 */
	public SubjectXMLGenerator() throws Exception{
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
		Participants templateParticipants = (Participants)unmarshaller.unmarshal(createInputStream(SubjectXMLGenerator.class.getPackage(),templateXML));
		return templateParticipants.getParticipant().get(0);
	}
	
	/**
	 * For each organization in ORGANIZATION_LIST. 80 Participants will be assigned.
	 * @return
	 * @throws Exception
	 */
	public Participants getLoadedParticipants() throws Exception{
		
		Participants loadedParticipants = objectFactory.createParticipants();
		int subjectCounter = 1;
		for(String nciCode : NCICode.ORGANIZATION_LIST){
			for(int index=1;index<=subjectsPerSite;index++){
				ParticipantType pType = changeValues(getTemplateParticipant(),nciCode,subjectCounter);
				loadedParticipants.getParticipant().add(pType);
				subjectCounter++;
			}
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
	private ParticipantType changeValues(ParticipantType pType,String nciCode,int index) throws Exception{
		//First name 	: FNx
		//Last name 	: LNx
		//Maiden Name 	: MDNx
		//Middle Name 	: MNx
		//Subject Identifier : SIx
		//Study Subject Identifier : NCICode.StudyPrimaryID.SSIx
		//(Note:- x is a running number)
		
		String studyPrimaryId = pType.getAssignments().getAssignment().get(0).getStudySite().getStudy().getIdentifiers().getIdentifier().getValue();
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
			SubjectXMLGenerator sXmlGenerator = new SubjectXMLGenerator();
			Participants participants = sXmlGenerator.getLoadedParticipants();
			marshal(participants, "SubjectData");
			System.out.print("Done");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}