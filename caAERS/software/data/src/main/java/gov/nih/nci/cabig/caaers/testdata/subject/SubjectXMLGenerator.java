package gov.nih.nci.cabig.caaers.testdata.subject;

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
		for(String nciCode : ORGANIZATION_LIST){
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