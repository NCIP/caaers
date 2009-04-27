package gov.nih.nci.cabig.caaers.testreports;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.api.AdverseEventReportSerializer;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.List;

import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * 
 * @author sakkala Generate a data report for a given query Objects Involved Participant , Study,
 *         StudySite, StudyParticipantAssignment, ExpeditedAdverseEventReport ,
 *         AdverseEventResponseDescription, Physician, ContactMechanism, Reporter DiseaseHistory,
 *         CtepStudyDisease, DiseaseTerm, AnatomicSite, ParticipantHistory, RadiationIntervention,
 *         SurgeryIntervention, MedicalDevice, ConcomitantMedication AdverseEvent,
 *         ConcomitantMedicationAttribution, OtherCauseAttribution, CourseAgentAttribution,
 *         CourseAgent, Dose, StudyAgent, DiseaseAttribution, DiseaseHistory SurgeryAttribution,
 *         RadiationAttribution, DeviceAttribution, AdverseEventCtcTerm, Lab, SAEReportPriorTherapy,
 *         TreatmentInformation, AdverseEventCourse, CourseAgent, SAEReportPreExistingCondition
 * 
 */
public class ParticipantDaoTest extends DaoTestCase {

    private ParticipantDao dao;

    @Override
    protected void setUp() throws Exception {
        // TODO Auto-generated method stub
        super.setUp();
        dao = (ParticipantDao) getApplicationContext().getBean("participantDao");
    }

    public void testForReport() {

        try {
            String outputFileName = "ParticipantTestReport.txt";
            // Create FileReader Object
            FileWriter outputFileReader = new FileWriter(outputFileName);
            PrintWriter outputStream = new PrintWriter(outputFileReader);
            outputStream
                            .println("+---------- Auto generated report based on data retrieved from the caAERS database. ----------+");
            outputStream.println("");
            outputStream.println("");
            outputStream.println("+---------- INPUT QUERY. ----------+");
            outputStream
                            .println("Get all Particpant whose subname (first name or last name) is 'Jack'");
            outputStream.println("");
            outputStream.println("");
            outputStream.println("+------------Objects Involed . -------+");
            outputStream
                            .println("Participant , Study, StudySite, StudyParticipantAssignment, ExpeditedAdverseEventReport , AdverseEventResponseDescription, Physician, ContactMechanism, Reporter, ");
            outputStream
                            .println("DiseaseHistory, CtepStudyDisease, DiseaseTerm, AnatomicSite, ParticipantHistory, RadiationIntervention, SurgeryIntervention, MedicalDevice, ConcomitantMedication");
            outputStream
                            .println("AdverseEvent, ConcomitantMedicationAttribution, OtherCauseAttribution, CourseAgentAttribution, CourseAgent, Dose, StudyAgent, DiseaseAttribution, DiseaseHistory");
            outputStream
                            .println("SurgeryAttribution, RadiationAttribution, DeviceAttribution, AdverseEventCtcTerm, Lab, SAEReportPriorTherapy, TreatmentInformation, AdverseEventCourse, CourseAgent, SAEReportPreExistingCondition");
            outputStream.println("");
            outputStream.println("");
            outputStream.println("--- Retrieving  and looping thru Participants ---");

            String[] subNames = { "Jack" };

            List<Participant> participantList = dao.getBySubnames(subNames);
            Participant participant = null;
            outputStream.println("--- Total number of Participants # " + participantList.size());

            for (int j = 0; j < participantList.size(); j++) {
                participant = participantList.get(j);
                outputStream.println("");
                outputStream.println("");
                outputStream.println("--- Participant # " + (j + 1) + " Details---");
                outputStream.println("Name: " + participant.getFullName());
                outputStream.println("Gender: " + participant.getGender());
                outputStream.println("Race: " + participant.getRace());
                outputStream.println("Ethnicity: " + participant.getEthnicity());
                outputStream.println("D.O.B: " + participant.getBirthDate());

                outputStream.println("");
                outputStream.println("");
                outputStream
                                .println("--- Retrieving the Study details for the selected participant. ---");
                outputStream.println("--- Participant has " + participant.getStudies().size()
                                + " Studies. ---");
                outputStream.println("");

                outputStream.println("--- Looping thru Studies . ---");
                List<Study> studies = participant.getStudies();
                Study study = null;
                for (int i = 0; i < studies.size(); i++) {
                    study = studies.get(i);
                    outputStream.println("--- Study # " + (i + 1) + " Details . ---");
                    outputStream.println("Study Short Title: " + study.getShortTitle());
                    outputStream.println("Study Long Title: " + study.getLongTitle());
                    outputStream.println("Study Phase code: " + study.getPhaseCode());
                    outputStream.println("Study Status: " + study.getStatus());
                    outputStream.println("Study Terminology Version: "
                                    + study.getCtcVersion().getName());
                    outputStream.println("Primary Sponsor: "
                                    + study.getPrimaryFundingSponsor().getOrganization().getName());
                }

                outputStream.println("");
                outputStream.println("");
                outputStream
                                .println("--- Retrieving the Expedited Adverse Event Report details for the selected participant. ---");
                List<StudyParticipantAssignment> assignments = participant.getAssignments();
                StudyParticipantAssignment spa = null;
                for (int i = 0; i < assignments.size(); i++) {
                    spa = assignments.get(i);
                    outputStream.println("--- Participant has " + spa.getAeReports().size()
                                    + " Expedited Reports. ---");
                    outputStream.println("");

                    for (ExpeditedAdverseEventReport aeReport : spa.getAeReports()) {
                        AdverseEventReportSerializer ser = (AdverseEventReportSerializer)this.getApplicationContext().getBean("adverseEventReportSerializer");

                        String xml = ser.serialize(aeReport);
                        String newXml = new XMLOutputter(Format.getPrettyFormat())
                                        .outputString(new SAXBuilder().build(new StringReader(xml)));
                        outputStream.println(newXml);
                    }
                }
            }
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
