package gov.nih.nci.cabig.caaers.testdata.generator.ae;

import gov.nih.nci.cabig.caaers.integration.schema.investigator.ObjectFactory;
import gov.nih.nci.cabig.caaers.testdata.NCICode;
import gov.nih.nci.cabig.caaers.testdata.TestDataFileUtils;
import gov.nih.nci.cabig.caaers.testdata.generator.XMLGenerator;
import gov.nih.nci.cabig.caaers.webservice.adverseevent.AdverseEventCtcTermType;
import gov.nih.nci.cabig.caaers.webservice.adverseevent.AdverseEventType;
import gov.nih.nci.cabig.caaers.webservice.adverseevent.AdverseEvents;
import gov.nih.nci.cabig.caaers.webservice.adverseevent.AdverseEventsInputMessage;

import javax.xml.bind.JAXBContext;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/**
 * This generator will create data load XML files, containing Adverse events.
 * 
 * @author: Biju Joseph
 */
public class AdverseEventXMLGenerator extends XMLGenerator {

	private ObjectFactory objectFactory;
	private static final String[] COURSE_FILE_TEMPLATES = {
			"adverse_event_course_1.xml", "adverse_event_course_2.xml",
			"adverse_event_course_3.xml", "adverse_event_course_4.xml",
			"adverse_event_course_5.xml", "adverse_event_course_6.xml" };
	/*
	 * CTC terms obtained from the following DB query select * from ctc_terms
	 * where category_id in (Select id from ctc_categories where version_id =
	 * (select id from ctc_versions where name='CTCAE v3.0')) AND term NOT like
	 * '%Specify%'
	 */

	private static final String[] CTEP_TERMS = { "Hypersensitivity",
			"Allergic rhinitis", "Autoimmune disorder", "Serum sickness",
			"Vasculitis", "Hearing test abnormal", "Hearing loss",
			"External ear inflammation", "Middle ear inflammation", "Tinnitus",
			"Bone marrow hypocellular", "CD4 lymphocytes decreased",
			"Haptoglobin decreased", "Hemoglobin decreased", "Hemolysis",
			"Iron increased", "Leukopenia", "Lymphopenia", "Myelodysplasia",
			"Neutrophil count decreased", "Platelet count decreased",
			"Spleen disorder", "Atrioventricular block first degree",
			"Mobitz type I", "Mobitz (type) II atrioventricular block",
			"Atrioventricular block complete", "Asystole",
			"Conduction disorder", "Sick sinus syndrome",
			"Stokes-Adams syndrome", "Wolff-Parkinson-White syndrome",
			"Palpitations", "Electrocardiogram QTc interval prolonged",
			"Atrial fibrillation", "Atrial flutter", "Atrial tachycardia",
			"Nodal arrhythmia", "Sinus arrhythmia", "Sinus bradycardia",
			"Sinus tachycardia", "Arrhythmia supraventricular",
			"Supraventricular extrasystoles", "Supraventricular tachycardia",
			"Syncope vasovagal", "Ventricular bigeminy",
			"Rhythm idioventricular", "Premature ventricular contractions",
			"Torsade de pointes", "Ventricular trigeminy",
			"Ventricular arrhythmia", };

	public AdverseEventXMLGenerator() throws Exception {
		jaxbContext = JAXBContext
				.newInstance("gov.nih.nci.cabig.caaers.webservice.adverseevent");
		unmarshaller = jaxbContext.createUnmarshaller();
		marshaller = jaxbContext.createMarshaller();
		objectFactory = new ObjectFactory();
	}

	/**
	 * Will read the adverse event message from the file.
	 * 
	 * @param fileName
	 * @return
	 */
	public AdverseEventsInputMessage getAdverseEventInput(String fileName)
			throws Exception {
		return (AdverseEventsInputMessage) unmarshaller
				.unmarshal(createInputStream(AdverseEventXMLGenerator.class
						.getPackage(), fileName));
	}

	/**
	 * Will modify the study and subject identifiers
	 * 
	 * @param msg
	 */
	public void modifyAdverseEvenInput(AdverseEventsInputMessage msg,
			String studyPrimaryId, String subjectPrimaryId) {
		msg.getCriteria().setParticipantIdentifier(subjectPrimaryId);
		msg.getCriteria().setStudyIdentifier(studyPrimaryId);
		addAdverseEvents(msg);
	}

	public void addAdverseEvents(AdverseEventsInputMessage msg) {
		AdverseEventType templateAdversEventType = msg.getAdverseEvents()
				.getAdverseEvent().get(0);
		for (String ctepTerm : CTEP_TERMS) {
			msg.getAdverseEvents().getAdverseEvent().add(
					createAdverseEventType(templateAdversEventType, ctepTerm));
		}
	}

	private AdverseEventType createAdverseEventType(
			AdverseEventType templateAdversEventType, String ctepTerm) {

		AdverseEventType newAdversEventType = new AdverseEventType();
		newAdversEventType.setStartDate(templateAdversEventType.getStartDate());
		newAdversEventType.setEndDate(templateAdversEventType.getEndDate());
		newAdversEventType.setGrade(templateAdversEventType.getGrade());
		newAdversEventType.setHospitalization(templateAdversEventType
				.getHospitalization());
		newAdversEventType.setExpected(templateAdversEventType.isExpected());
		AdverseEventCtcTermType adverseEventCtcTermType = new AdverseEventCtcTermType();
		adverseEventCtcTermType.setCtepTerm(ctepTerm);
		newAdversEventType.setAdverseEventCtcTerm(adverseEventCtcTermType);
		return newAdversEventType;
	}

	/**
	 * Will generate a file for the course , on the study and subject, based on
	 * the template.
	 * 
	 * @param studyPrimaryId
	 * @param subjectPrimaryId
	 * @param templateFileName
	 * @param destinationFolder
	 * @throws Exception
	 */
	public void generateAdverseEventFile(String studyPrimaryId,
			String subjectPrimaryId, String templateFileName,
			File destinationFolder) throws Exception {
		AdverseEventsInputMessage aeMsg = getAdverseEventInput(templateFileName);
		modifyAdverseEvenInput(aeMsg, studyPrimaryId, subjectPrimaryId);

		marshal(aeMsg, destinationFolder, subjectPrimaryId + "_"
				+ templateFileName);

	}

	public void generate() throws Exception {

		int particpantIndexStart = 1;
		int participantIndexEnd = 80;

		int studyIndexStart = 1;
		int studyIndexEnd = 100;

		List<String> siteNCICodes = NCICode.ORGANIZATION_LIST;
		String studyPrimaryIdPattern = "C5876";
		File dataFolder = TestDataFileUtils.getAdverseEventTestDataFolder();
		for (int i = studyIndexStart; i <= studyIndexEnd; i++) {
			String studyPrimaryId = studyPrimaryIdPattern + "." + i;

			for (String siteNCICode : siteNCICodes) {
				for (int j = particpantIndexStart; j <= participantIndexEnd; j++) {
					String subjectPrimaryId = siteNCICode  + "_SI" + j;

					for (String courseTemplate : COURSE_FILE_TEMPLATES) {
						System.out.println("Generating ... ["
								+ subjectPrimaryId + " : " + courseTemplate
								+ "]....");
						generateAdverseEventFile(studyPrimaryId,
								subjectPrimaryId, courseTemplate, dataFolder);

					}
				}
			}
		}
	}

	public static void main(String[] args) {

		try {
			AdverseEventXMLGenerator generator = new AdverseEventXMLGenerator();
			generator.generate();

			System.out.println("Done..........");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
