package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.report.DeliveryStatus;
import gov.nih.nci.cabig.caaers.domain.report.Mandatory;
import gov.nih.nci.cabig.caaers.domain.report.PlannedEmailNotification;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportDeliveryDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryFieldDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import gov.nih.nci.cabig.caaers.domain.report.ScheduledEmailNotification;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.CombinationPolicy;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.LoginPolicy;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordCreationPolicy;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordPolicy;
import gov.nih.nci.cabig.caaers.domain.workflow.ReportReviewComment;
import gov.nih.nci.cabig.caaers.domain.workflow.ReportingPeriodReviewComment;
import gov.nih.nci.cabig.caaers.domain.workflow.TaskConfig;
import gov.nih.nci.cabig.caaers.domain.workflow.WorkflowConfig;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.ctms.domain.DomainObject;
import gov.nih.nci.cabig.ctms.lang.NowFactory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Rhett Sutphin
 */
public class Fixtures {

    public static final Organization SITE = Fixtures.createOrganization("Round abouts here", 1);

    public static Organization createOrganization(final String organizationName, final int organizationId) {
        final Organization organization = createOrganization(organizationName);
        organization.setId(organizationId);
        return organization;
    }

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
        Organization organization = new LocalOrganization();
        organization.setName(name);
        organization.setDescriptionText("dec:" + name);
        organization.setNciInstituteCode("NCI333:" + name);
        return organization;
    }

    /**
     * Creates an assignment and the associated Study, Participant, StudySite, and Site objs
     */
    public static StudyParticipantAssignment createAssignment() {
        return assignParticipant(createParticipant("D", "C"), createStudy("DC"),
               SITE);
    }

    public static StudyParticipantAssignment assignParticipant(final Participant participant,
                                                               final Study study, final Organization organization) {
        StudySite studySite = new StudySite();
        studySite.setId(study.getId() != null ? study.getId() : organization.getId());
        studySite.setOrganization(organization);
        study.addStudySite(studySite);
        organization.addStudySite(studySite);

        StudyParticipantAssignment assignment = new StudyParticipantAssignment();
        studySite.addAssignment(assignment);
        participant.addAssignment(assignment);

        return assignment;
    }

    public static StudyParticipantAssignment assignParticipant(final Participant participant,
                                                               final Study study, final Organization organization, final Integer studySiteId) {
        StudySite studySite = new StudySite();
        studySite.setId(studySiteId);
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
    
    public static Submitter createSubmitter(){
    	return makeExpeditedReportPersonSavable(new Submitter());
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
        Organization org = new LocalOrganization();
        org.setName("Test");
        def.setOrganization(org);
        def.addPlannedNotification(createPlannedEmailNotification());
        return def;
    }
    
    public static ReportMandatoryFieldDefinition  createMandatoryField(String path, Mandatory m){
    	ReportMandatoryFieldDefinition mf = new ReportMandatoryFieldDefinition("", Mandatory.OPTIONAL);
    	mf.setFieldPath(path);
    	mf.setMandatory(m);
    	return mf;
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
        Fixtures.createReportVersion(rep);
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
        reportVersion.setReportVersionId("5");
        reportVersion.setCreatedOn(new Timestamp(106));
        reportVersion.setReportStatus(ReportStatus.PENDING);
        report.addReportVersion(reportVersion);
    }

    public static AeTerminology createCtcV3Terminology(final Study s) {
        AeTerminology t = s.getAeTerminology();
        Ctc v3 = setId(3, new Ctc());
        v3.setName(4 + "");
        t.setTerm(Term.CTC);
        t.setCtcVersion(v3);
        return t;
    }

    public static AeTerminology createMedDRATerminology(final Study s) {
        AeTerminology t = s.getAeTerminology();
        t.setTerm(Term.MEDDRA);
        MeddraVersion meddraVersion = setId(4, new MeddraVersion());
        meddraVersion.setName(5 + "");
        t.setMeddraVersion(meddraVersion);

        return t;
    }

    public static AdverseEventCtcTerm createAdverseEventCtcTerm(final AdverseEvent adverseEvent,final CtcTerm ctcTerm) {
        AdverseEventCtcTerm adverseEventCtcTerm = adverseEvent.getAdverseEventCtcTerm();
        adverseEventCtcTerm.setCtcTerm(ctcTerm);
        return adverseEventCtcTerm;
    }
    
    public static CtcTerm createCtcTerm(final String ctepTerm, final String ctepCode){
    	CtcTerm cTerm = new CtcTerm();
    	cTerm.setCtepTerm(ctepTerm);
    	cTerm.setCtepCode(ctepCode);
    	return cTerm;
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
        ResearchStaff researchStaff = new LocalResearchStaff();
        researchStaff.setFirstName("FN"+name);
        researchStaff.setLastName("LN"+name);
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

    public static TreatmentAssignment createTreatmentAssignment() {
        TreatmentAssignment treatmentAssignment = new TreatmentAssignment();
        treatmentAssignment.setCode("code");
        treatmentAssignment.setComments("comments");
        treatmentAssignment.setDescription("description");
        treatmentAssignment.setDoseLevelOrder(Integer.valueOf(2));
        return treatmentAssignment;
    }
    
    public static TreatmentAssignment createTreatmentAssignment(String arg) {
        TreatmentAssignment treatmentAssignment = new TreatmentAssignment();
        treatmentAssignment.setCode(arg);
        treatmentAssignment.setComments("comments_"+arg);
        treatmentAssignment.setDescription("description_"+arg);
        treatmentAssignment.setDoseLevelOrder(Integer.valueOf(2));
        return treatmentAssignment;
    }

    public static StudyFundingSponsor createStudyFundingSponsor(final Organization organization) {
        StudyFundingSponsor studyFundingSponsor = new StudyFundingSponsor();
        studyFundingSponsor.setOrganization(organization);
        return studyFundingSponsor;

    }

    public static FundingSponsor createFundingSponsor(final Organization organization, final OrganizationAssignedIdentifier organizationAssignedIdentifier) {
        FundingSponsor fundingSponsor = new FundingSponsor();

        StudyFundingSponsor studyFundingSponsor = createStudyFundingSponsor(organization);
        fundingSponsor.setOrganizationAssignedIdentifier(organizationAssignedIdentifier);
        fundingSponsor.setStudyFundingSponsor(studyFundingSponsor);
        return fundingSponsor;
    }

    public static CoordinatingCenter createCoordinatingCenter(final Organization organization, final OrganizationAssignedIdentifier organizationAssignedIdentifier) {
        CoordinatingCenter coordinatingCenter = new CoordinatingCenter();
        coordinatingCenter.setOrganizationAssignedIdentifier(organizationAssignedIdentifier);
        StudyCoordinatingCenter studyCoordinatingCenter = createStudyCoordinatingCenter(organization);
        coordinatingCenter.setStudyCoordinatingCenter(studyCoordinatingCenter);
        return coordinatingCenter;
    }
    
    public static DiseaseTerminology createDiseaseTerminology(Study s){
    	DiseaseTerminology dTerminology = new DiseaseTerminology();
    	dTerminology.setDiseaseCodeTerm(DiseaseCodeTerm.CTEP);
    	dTerminology.setGridId("xxxxx");
    	dTerminology.setStudy(s);
    	return dTerminology;
    }
    private static StudyCoordinatingCenter createStudyCoordinatingCenter(final Organization organization) {
        StudyCoordinatingCenter studyCoordinatingCenter = new StudyCoordinatingCenter();
        studyCoordinatingCenter.setOrganization(organization);
        return studyCoordinatingCenter;
    }
    
    public static Investigator createInvestigator(String name){
    	Investigator inv = new LocalInvestigator();
    	inv.setFirstName("FN" + name);
    	inv.setLastName("LN" + name);
    	inv.setEmailAddress("abc@kk.com");
    	return inv;
    }
    
    public static SiteInvestigator createSiteInvestigator(Organization org, Investigator inv){
    	SiteInvestigator si = new SiteInvestigator();
    	si.setInvestigator(inv);
    	si.setOrganization(org);
    	return si;
    }
    
    public static StudyInvestigator createStudyInvestigator(String name, Organization org){
    	Investigator inv = createInvestigator(name);
    	SiteInvestigator si = createSiteInvestigator(org, inv);
    	
    	StudyInvestigator sti = new StudyInvestigator();
    	sti.setRoleCode("role");
    	sti.setSiteInvestigator(si);
    	return sti;
    }
    
    public static StudyPersonnel createStudyPersonnel(ResearchStaff staff){
    	StudyPersonnel sp = new StudyPersonnel();
    	sp.setResearchStaff(staff);
    	sp.setRoleCode("role");
    	sp.setStatusCode("code");
    	return sp;
    }
    
    public static Agent createAgent(String name, List<StudyAgent> studyAgents){
    	Agent agent = new Agent();
    	agent.setDescription("abcd");
    	agent.setName(name);
    	for(StudyAgent sa : studyAgents) sa.setAgent(agent);
    	return agent;
    }
    
    public static CtepStudyDisease createCtepStudyDisease(Study s, DiseaseTerm term){
    	CtepStudyDisease d = new CtepStudyDisease();
    	d.setDiseaseTerm(term);
    	d.setStudy(s);
    	s.addCtepStudyDisease(d);
    	d.setLeadDisease(true);
    	return d;
    }
    
    public static MeddraStudyDisease createMeddraStudyDisease(Study s, LowLevelTerm lowLevelTerm){
    	MeddraStudyDisease meddraStudyDisease = new MeddraStudyDisease();
		meddraStudyDisease.setMeddraCode("medra");
		meddraStudyDisease.setTerm(lowLevelTerm);
		meddraStudyDisease.setStudy(s);
		return meddraStudyDisease;
		
    }
    
    public static AdverseEventReportingPeriod createReportingPeriod(){
    	AdverseEventReportingPeriod rp = new AdverseEventReportingPeriod();
    	Study s = createStudy("Test");
    	Organization ctep = createOrganization("Ctep");
    	Organization nci = createOrganization("NCI");
    	StudySite ss = new StudySite();
    	ss.setStudy(s);
    	ss.setOrganization(nci);
    	
    	StudyFundingSponsor sponsor = createStudyFundingSponsor(ctep);
    	s.addStudyOrganization(sponsor);
    	
    	StudyParticipantAssignment assignment = Fixtures.createAssignment();
    	assignment.setStudySite(ss);
    	rp.setAssignment(assignment);
    	return rp;
    }
    
    public static AdverseEventReportingPeriod createReportingPeriod(Integer id, String startDate, String endDate ){
		Date stDate = (startDate == null)? null: DateUtils.parseDateString(startDate).toDate();
		Date enDate = (endDate == null) ? null : DateUtils.parseDateString(endDate).toDate();
		AdverseEventReportingPeriod rp = Fixtures.createReportingPeriod();
		rp.setId(id);
		rp.setStartDate(stDate);
		rp.setEndDate(enDate);
		return rp;
	}
    
    public static AdverseEvent createAdverseEvent(int id, Grade grade){
    	CtcTerm ctcTerm = Fixtures.createCtcTerm("abcd", "123");
		AdverseEvent ae = new AdverseEvent();
		ae.setId(id);
		ae.setGrade(grade);
		Fixtures.createAdverseEventCtcTerm(ae, ctcTerm);
		ae.setSignature(ae.getCurrentSignature());
		return ae;
    }
    
    public static Organization createOrganization(final String name, final String nciInstituteCode) {
        Organization organization = new LocalOrganization();
        organization.setName(name);
        organization.setDescriptionText("dec:" + name);
        organization.setNciInstituteCode(nciInstituteCode);
        return organization;
    }
    
    public static ReportDefinition createReportDefinition(String name, String nciInstituteCode) {
        ReportDefinition def = new ReportDefinition();
        def.setName(name);
        Organization org = createOrganization("testOrg", nciInstituteCode);
        def.setOrganization(org);
        def.setAmendable(true);
        return def;
    }
    
   public static ReportDeliveryDefinition createReportDeliveryDefinition(String endPointType, int entityType){
	   ReportDeliveryDefinition rd = new ReportDeliveryDefinition();
	   rd.setEndPoint("abcd");
	   rd.setEndPointType(endPointType);
	   rd.setEntityType(entityType);
	   return rd;
   }
   
   public static TaskConfig createTaskConfig(String taskNodeName, Boolean applicable){
	   TaskConfig tc = new TaskConfig();
	   tc.setTaskName(taskNodeName);
	   tc.setApplicable(applicable);
	   return tc;
	   
   }
   
   public static WorkflowConfig createWorkflowConfig(String wfDefName){
	   WorkflowConfig wfConfig = new WorkflowConfig();
	   wfConfig.setWorkflowDefinitionName(wfDefName);
	   wfConfig.addTaskConfigs(createTaskConfig("a1", true));
	   wfConfig.addTaskConfigs(createTaskConfig("b1", false));
	   wfConfig.addTaskConfigs(createTaskConfig("c1", true));
	   return wfConfig;
   }
   
   public static ReportingPeriodReviewComment createReportingPeriodReviewComment(Integer id, String comment){
	   ReportingPeriodReviewComment reviewComment = new ReportingPeriodReviewComment();
	   reviewComment.setUserComment(comment);
	   reviewComment.setId(id);
	   return reviewComment;
   }
   
   public static ReportReviewComment createReportReviewComment(Integer id, String comment){
	   ReportReviewComment reviewComment = new ReportReviewComment();
	   reviewComment.setUserComment(comment);
	   reviewComment.setId(id);
	   return reviewComment;
   }
   
   public static Epoch createEpoch(int id, String name){
	   Epoch e = new Epoch();
	   e.setId(5);
	   e.setName(name);
	   e.setArms(new ArrayList<Arm>());
	   return e;
   }
   
   public static ExpectedAECtcTerm createExpectedAECtcTerm(int id, final String ctepTerm, final String ctepCode){
	   CtcTerm ctcTerm = createCtcTerm(ctepTerm, ctepCode);
	   ctcTerm.setId(id);
	   ExpectedAECtcTerm eTerm = new ExpectedAECtcTerm();
	   eTerm.setCtcTerm(ctcTerm);
	   return eTerm;
   }
   
   public static Outcome createOutcome(int id, OutcomeType type){
	 Outcome o = new Outcome();
	 o.setId(id);
	 o.setOutcomeType(type);
	 return o;
   }
   
   public static TreatmentInformation createTreatmentInformation(int id){
	   TreatmentInformation ti = new TreatmentInformation();
	   ti.setId(id);
	   return ti;
   }
   
   public static AdverseEventResponseDescription createAdverseEventResponseDescription(){
	   AdverseEventResponseDescription description = new AdverseEventResponseDescription();
       TimeValue primaryTreatmentApproximateTime = new TimeValue();
       primaryTreatmentApproximateTime.setHour(2);
       description.setAutopsyPerformed(true);
       description.setBlindBroken(true);
       description.setCauseOfDeath("cause of death");
       description.setDateRemovedFromProtocol(new Date());
       description.setDaysNotGiven(2);
       description.setEventAbate(EventStatus.YES);
       description.setEventDescription("event desc");
       description.setEventReappear(EventStatus.YES);
       description.setGridId("grid id");
       description.setId(2);
       description.setPresentStatus(PostAdverseEventStatus.DEAD);
       description.setRecoveryDate( new Date());
       description.setReducedDate( new Date());
       description.setReducedDose("reduce dose");
       description.setRetreated(true);
       description.setReport(new ExpeditedAdverseEventReport());
       description.setStudyDrugInterrupted(true);
       description.setVersion(3);
       description.setPrimaryTreatment("primaryTreatment");
       description.setPrimaryTreatmentApproximateTime(primaryTreatmentApproximateTime);   
       return description;
   }
   
   public static DiseaseHistory createDiseaseHistory(){
	   
	   DiseaseHistory diseaseHistory = new DiseaseHistory();
	   diseaseHistory.setId(4);
	   diseaseHistory.setDiagnosisDate(new Date());
       diseaseHistory.setOtherPrimaryDisease("Other primary");
       
       return diseaseHistory;
  }
   
}
