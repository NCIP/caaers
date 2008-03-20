package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.DomainObject;
import gov.nih.nci.cabig.ctms.lang.NowFactory;
import gov.nih.nci.cabig.caaers.domain.report.DeliveryStatus;
import gov.nih.nci.cabig.caaers.domain.report.PlannedEmailNotification;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ScheduledEmailNotification;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordPolicy;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordCreationPolicy;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.CombinationPolicy;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.LoginPolicy;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rhett Sutphin
 */
public class Fixtures {
    public static <T extends DomainObject> T setId(final int id, final T target) {
        target.setId(id);
        return target;
    }

    public static Participant createParticipant(final String first, final String last) {
        Participant p = new Participant();
        p.setFirstName(first);
        p.setLastName(last);
        return p;
    }

    public static Study createStudy(final String shortTitle) {
        Study s = new Study();
        s.setShortTitle(shortTitle);
        s.setLongTitle(shortTitle);
        return s;
    }

    public static Organization createOrganization(final String name) {
        Organization organization = new Organization();
        organization.setName(name);
        organization.setDescriptionText("dec:" + name);
        organization.setNciInstituteCode("NCI333:" + name);
        return organization;
    }

    /** Creates an assignment and the associated Study, Participant, StudySite, and Site objs */
    public static StudyParticipantAssignment createAssignment() {
        return assignParticipant(createParticipant("D", "C"), createStudy("DC"),
                        createOrganization("N/A"));
    }

    public static StudyParticipantAssignment assignParticipant(final Participant participant,
                    final Study study, final Organization organization) {
        StudySite studySite = new StudySite();
        studySite.setId(123);
        studySite.setOrganization(organization);
        study.addStudySite(studySite);
        organization.addStudySite(studySite);

        StudyParticipantAssignment assignment = new StudyParticipantAssignment();
        studySite.addAssignment(assignment);
        participant.addAssignment(assignment);

        return assignment;
    }

    public static StudyAgent createStudyAgent(final String agentName) {
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

    private static <T extends ReportPerson> T makeExpeditedReportPersonSavable(final T person) {
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
        def.addPlannedNotification(createPlannedEmailNotification());
        return def;
    }

    public static PlannedEmailNotification createPlannedEmailNotification() {
        PlannedEmailNotification penf = new PlannedEmailNotification();
        penf.setIndexOnTimeScale(1);
        penf.setSubjectLine("Test subject");
        return penf;
    }

    public static Report createReport(String name) {
        ReportDefinition def = createReportDefinition(name);
        Report rep = new Report();
        rep.setReportDefinition(def);
        rep.addScheduledNotification(createScheduledEmailNotification());
        return rep;
    }

    public static ScheduledEmailNotification createScheduledEmailNotification() {
        ScheduledEmailNotification senf = new ScheduledEmailNotification();
        senf.setId(-332);
        senf.setBody("this is my body");
        senf.setFromAddress("biju@test.com");
        senf.setDeliveryStatus(DeliveryStatus.CREATED);
        senf.setScheduledOn(new NowFactory().getNow());
        senf.setToAddress("biju.joseph@semanticbits.com");
        return senf;
    }

    public static void createReportVersion(final Report report) {
        ReportVersion reportVersion = new ReportVersion();
        reportVersion.setCreatedOn(new Timestamp(106));
        reportVersion.setReportStatus(ReportStatus.PENDING);
        report.addReportVersion(reportVersion);
    }

    public static AeTerminology createCtcV3Terminology(final Study s) {
        AeTerminology t = s.getAeTerminology();
        Ctc v3 = setId(3, new Ctc());
        t.setTerm(Term.CTC);
        t.setCtcVersion(v3);
        return t;
    }

    public static AeTerminology createMedDRATerminology(final Study s) {
        AeTerminology t = s.getAeTerminology();
        t.setTerm(Term.MEDDRA);
        return t;
    }

    public static AdverseEventCtcTerm createAdverseEventCtcTerm(final AdverseEvent adverseEvent,
                    final CtcTerm ctcTerm) {
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

    private static CtcCategory createCtcCategory(final Ctc ctc, final String name) {
        CtcCategory category = new CtcCategory();
        category.setName(name);
        category.setCtc(ctc);
        return category;
    }

    public static ResearchStaff createResearchStaff(final Organization organization,
                    final List<UserGroupType> userGroupTypes, final String name) {
        ResearchStaff researchStaff = new ResearchStaff();
        researchStaff.setFirstName("Jeff");
        researchStaff.setLastName("Someone");
        researchStaff.setEmailAddress(name + "@def.com");
        researchStaff.setPhoneNumber("123-5-789");
        researchStaff.setNciIdentifier("nci id");

        for (UserGroupType userGroupType : userGroupTypes) {
            researchStaff.addUserGroupType(userGroupType);
        }

        researchStaff.setOrganization(organization);
        return researchStaff;
    }

    public static SystemAssignedIdentifier createSystemAssignedIdentifier(final String value) {
        SystemAssignedIdentifier systemAssignedIdentifier = new SystemAssignedIdentifier();
        systemAssignedIdentifier.setSystemName("system assigned identifier:");
        systemAssignedIdentifier.setType("type");
        systemAssignedIdentifier.setValue("value:" + value);
        return systemAssignedIdentifier;
    }

    public static OrganizationAssignedIdentifier createOrganizationAssignedIdentifier(
                    final String value, final Organization organization) {
        OrganizationAssignedIdentifier organizationAssignedIdentifier = new OrganizationAssignedIdentifier();
        organizationAssignedIdentifier.setOrganization(organization);
        organizationAssignedIdentifier.setType("type");
        organizationAssignedIdentifier.setValue("value:" + value);
        return organizationAssignedIdentifier;
    }

    public static PasswordPolicy createPasswordPolicy() {
        LoginPolicy loginPolicy = new LoginPolicy();
        loginPolicy.setAllowedFailedLoginAttempts(1);
        loginPolicy.setLockOutDuration(0);
        loginPolicy.setMaxPasswordAge(60 * 60 * 24);

        CombinationPolicy combinationPolicy = new CombinationPolicy();
        combinationPolicy.setMinimumRequired(5);
        combinationPolicy.setUpperCaseAlphabetRequired(true);
        combinationPolicy.setLowerCaseAlphabetRequired(true);
        combinationPolicy.setNonAlphaNumericRequired(true);
        combinationPolicy.setBaseTenDigitRequired(true);
        combinationPolicy.setMaxSubstringLength(3);

        PasswordCreationPolicy passwordCreationPolicy = new PasswordCreationPolicy();
        passwordCreationPolicy.setCombinationPolicy(combinationPolicy);
        passwordCreationPolicy.setMinPasswordAge(1);
        passwordCreationPolicy.setPasswordHistorySize(1);
        passwordCreationPolicy.setMinPasswordLength(6);

        PasswordPolicy passwordPolicy = new PasswordPolicy();
        passwordPolicy.setLoginPolicy(loginPolicy);
        passwordPolicy.setPasswordCreationPolicy(passwordCreationPolicy);
        return passwordPolicy;
    }
}
