package gov.nih.nci.cabig.caaers.testreports;

import gov.nih.nci.cabig.caaers.DaoNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.CtepStudyDisease;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

/**
 * 
 * @author sakkala Generate a data report for a given query Objects Involved Study , StudyAgent,
 *         TreatmentAssignment, CtepStudyDisease, CtepStudyDisease, StudySite, Organization
 * 
 */

public class StudyDaoTest extends DaoNoSecurityTestCase {

    private StudyDao studyDao;

    @Override
    protected void setUp() throws Exception {
        // TODO Auto-generated method stub
        super.setUp();
        studyDao = (StudyDao) getApplicationContext().getBean("studyDao");
    }

    public void testForReport() {
        try {

            String outputFileName = "StudyTestReport.txt";
            FileWriter outputFileReader = new FileWriter(outputFileName);
            PrintWriter outputStream = new PrintWriter(outputFileReader);
            outputStream
                            .println("+---------- Auto generated report based on data retrieved from the caAERS database. ----------+");
            outputStream.println("");
            outputStream.println("");
            outputStream.println("+---------- INPUT QUERY. ----------+");
            outputStream.println("Get all Studies ");
            outputStream.println("");
            outputStream.println("");
            outputStream.println("+------------Objects Involed . -------+");
            outputStream
                            .println("Study , StudyAgent, TreatmentAssignment, CtepStudyDisease, CtepStudyDisease, StudySite, Organization");
            outputStream.println("");
            outputStream.println("");
            outputStream.println("--- Retrieving  and looping thru Studies ---");

            List<Study> studies = studyDao.getAllStudies();
            Study study = null;
            outputStream.println("--- Total number of Studies # " + studies.size());

            for (int j = 0; j < studies.size(); j++) {
                study = studies.get(j);
                outputStream.println("");
                outputStream.println("");
                outputStream.println("--- Study # " + (j + 1) + " Details---");
                outputStream.println("Study Short Title: " + study.getShortTitle());
                outputStream.println("Study Long Title: " + study.getLongTitle());
                outputStream.println("Study Phase code: " + study.getPhaseCode());
                outputStream.println("Study Status: " + study.getStatus());
                outputStream.println("Study Terminology Version: "
                                + study.getCtcVersion().getName());
                outputStream.println("Primary Sponsor: "
                                + study.getPrimaryFundingSponsor().getOrganization().getName());

                outputStream.println("");
                outputStream.println("");
                outputStream.println("--- Study Agent(s)");

                List<StudyAgent> agents = study.getStudyAgents();
                for (StudyAgent agent : agents) {
                    outputStream.println(" Agent name: " + agent.getAgentName());
                    outputStream.println(" IND type: " + agent.getIndType().getDisplayName());
                }
                outputStream.println("");
                outputStream.println("");
                outputStream.println("--- Treatment Assignment(s)");

                List<TreatmentAssignment> tas = study.getTreatmentAssignments();
                for (TreatmentAssignment ta : tas) {
                    outputStream.println(" Code: " + ta.getCode());
                    outputStream.println(" Description: " + ta.getDescription());
                }
                outputStream.println("");
                outputStream.println("");
                outputStream.println("--- Disease(s) ");
                List<CtepStudyDisease> diseases = study.getCtepStudyDiseases();
                for (CtepStudyDisease disease : diseases) {
                    outputStream.println(" Disease Name: " + disease.getTermName());
                    outputStream.println(" Is lead disease ? : " + disease.getLeadDisease());
                }
                outputStream.println("");
                outputStream.println("");

                outputStream.println("--- Study Sites(s) ");
                List<StudySite> sites = study.getStudySites();
                for (StudySite site : sites) {
                    outputStream.println(" Organization Name: " + site.getOrganization().getName());
                    outputStream.println(" Description : "
                                    + site.getOrganization().getDescriptionText());
                    outputStream.println(" NCI Identifier : "
                                    + site.getOrganization().getNciInstituteCode());
                }
                outputStream.println("");
                outputStream.println("");

            }

            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
