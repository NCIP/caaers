package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.DomainObject;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * @author Rhett Sutphin
 */
public class Fixtures {
    public static <T extends DomainObject> T setId(int id, T target) {
        target.setId(id);
        return target;
    }

    public static Participant createParticipant(String first, String last) {
        Participant p = new Participant();
        p.setFirstName(first);
        p.setLastName(last);
        return p;
    }

    public static Study createStudy(String shortTitle) {
        Study s = new Study();
        s.setShortTitle(shortTitle);
        return s;
    }

    public static Organization createOrganization(String name) {
        Organization organization = new Organization();
        organization.setName(name);
        organization.setNciInstituteCode("NCI333");
        return organization;
    }

    /** Creates an assignment and the associated Study, Participant, StudySite, and Site objs */
    public static StudyParticipantAssignment createAssignment() {
        return assignParticipant(
            createParticipant("D", "C"),
            createStudy("DC"),
            createOrganization("N/A")
        );
    }

    public static StudyParticipantAssignment assignParticipant(Participant p, Study study, Organization organization) {
        StudySite ss = new StudySite();
        ss.setOrganization(organization);
        study.addStudySite(ss);
        organization.addStudySite(ss);

        StudyParticipantAssignment assignment = new StudyParticipantAssignment();
        ss.addAssignment(assignment);
        p.addAssignment(assignment);

        return assignment;
    }

    public static StudyAgent createStudyAgent(String agentName) {
        StudyAgent agent = new StudyAgent();
        agent.setAgent(new Agent());
        agent.getAgent().setName(agentName);
        return agent;
    }

    public static RoutineAdverseEventReport createSavableRoutineReport() {
        RoutineAdverseEventReport report = new RoutineAdverseEventReport();
        report.setStartDate(new Timestamp(103));
        report.setEndDate(new Timestamp(106));
        return report;
    }

    public static ExpeditedAdverseEventReport createSavableExpeditedReport() {
        ExpeditedAdverseEventReport report = new ExpeditedAdverseEventReport();
        report.setCreatedAt(new Timestamp(103));
        report.setReporter(makeExpeditedReportPersonSavable(new Reporter()));
        report.setPhysician(makeExpeditedReportPersonSavable(new Physician()));
        return report;
    }

    private static <T extends ReportPerson> T makeExpeditedReportPersonSavable(T person) {
        person.setFirstName("Frank");
        person.setLastName("Just Frank");
        person.getContactMechanisms().put(ReportPerson.EMAIL, "just@frank.net");
        return person;
    }

    public static ReportDefinition createReportDefinition(String name) {
        ReportDefinition def = new ReportDefinition();
        def.setName(name);
        Organization org = new Organization();
        org.setName("Test");
        def.setOrganization(org);
        return def;
    }

    public static Terminology createCtcV3Terminology(Study s)
    {
    	Terminology t = s.getTerminology();
    	Ctc v3 = setId(3, new Ctc());
    	t.setTerm(Term.CTC);
    	t.setCtcVersion(v3);
    	return t;
    }

    public static Terminology createMedDRATerminology(Study s)
    {
    	Terminology t = s.getTerminology();
    	t.setTerm(Term.MEDDRA);
    	return t;
    }

    public static AdverseEventCtcTerm createAdverseEventCtcTerm(AdverseEvent adverseEvent,CtcTerm ctcTerm)
    {
    	AdverseEventCtcTerm adverseEventCtcTerm = adverseEvent.getAdverseEventCtcTerm();
    	adverseEventCtcTerm.setCtcTerm(ctcTerm);
    	return adverseEventCtcTerm;
    }

    public static Ctc createCtcaeV3() {
        Ctc v3 = setId(3, new Ctc());
        // this is only partial, of course
        v3.setCategories(new ArrayList<CtcCategory>());
        v3.getCategories().add(createCtcCategory(v3, "ALLERGY/IMMUNOLOGY"));
        v3.getCategories().add(createCtcCategory(v3, "AUDITORY/EAR"));
        v3.getCategories().add(createCtcCategory(v3, "BLOOD/BONE MARROW"));
        return v3;
    }

    private static CtcCategory createCtcCategory(Ctc ctc, String name) {
        CtcCategory category = new CtcCategory();
        category.setName(name);
        category.setCtc(ctc);
        return category;
    }
}
