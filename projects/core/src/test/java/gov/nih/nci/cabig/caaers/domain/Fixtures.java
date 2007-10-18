package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.DomainObject;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import gov.nih.nci.cabig.caaers.domain.report.Report;

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
		return assignParticipant(createParticipant("D", "C"), createStudy("DC"), createOrganization("N/A"));
	}

	public static StudyParticipantAssignment assignParticipant(final Participant p, final Study study,
			final Organization organization) {
		StudySite ss = new StudySite();
		ss.setOrganization(organization);
		study.addStudySite(ss);
		organization.addStudySite(ss);

		StudyParticipantAssignment assignment = new StudyParticipantAssignment();
		ss.addAssignment(assignment);
		p.addAssignment(assignment);

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

	public static ReportDefinition createReportDefinition(final String name) {
		ReportDefinition def = new ReportDefinition();
		def.setName(name);
		Organization org = new Organization();
		org.setName("Test");
		def.setOrganization(org);
		return def;
	}

	public static void createReportVersion(final Report report) {
		ReportVersion reportVersion = new ReportVersion();
		reportVersion.setCreatedOn(new Timestamp(106));
		reportVersion.setReportStatus(ReportStatus.PENDING);
		report.addReportVersion(reportVersion);
	}

	public static Terminology createCtcV3Terminology(final Study s) {
		Terminology t = s.getTerminology();
		Ctc v3 = setId(3, new Ctc());
		t.setTerm(Term.CTC);
		t.setCtcVersion(v3);
		return t;
	}

	public static Terminology createMedDRATerminology(final Study s) {
		Terminology t = s.getTerminology();
		t.setTerm(Term.MEDDRA);
		return t;
	}

	public static AdverseEventCtcTerm createAdverseEventCtcTerm(final AdverseEvent adverseEvent, final CtcTerm ctcTerm) {
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

	public static OrganizationAssignedIdentifier createOrganizationAssignedIdentifier(final String value,
			final Organization organization) {
		OrganizationAssignedIdentifier organizationAssignedIdentifier = new OrganizationAssignedIdentifier();
		organizationAssignedIdentifier.setOrganization(organization);
		organizationAssignedIdentifier.setType("type");
		organizationAssignedIdentifier.setValue("value:" + value);
		return organizationAssignedIdentifier;
	}

}
