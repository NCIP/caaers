/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import com.semanticbits.rules.brxml.*;
import com.semanticbits.rules.brxml.Condition;
import gov.nih.nci.cabig.caaers.domain.dto.AdverseEventDTO;
import gov.nih.nci.cabig.caaers.domain.dto.AeMergeDTO;
import gov.nih.nci.cabig.caaers.domain.dto.TaskNotificationDTO;
import gov.nih.nci.cabig.caaers.domain.dto.TermDTO;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.report.*;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.CombinationPolicy;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.LoginPolicy;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordCreationPolicy;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordPolicy;
import gov.nih.nci.cabig.caaers.domain.workflow.ReportReviewComment;
import gov.nih.nci.cabig.caaers.domain.workflow.ReportingPeriodReviewComment;
import gov.nih.nci.cabig.caaers.domain.workflow.TaskConfig;
import gov.nih.nci.cabig.caaers.domain.workflow.WorkflowConfig;
import gov.nih.nci.cabig.caaers.integration.schema.common.*;
import gov.nih.nci.cabig.caaers.integration.schema.common.ConfigPropertyType;
import gov.nih.nci.cabig.caaers.integration.schema.study.InvestigationalNewDrugType;
import gov.nih.nci.cabig.caaers.integration.schema.study.StudyDeviceINDAssociationType;
import gov.nih.nci.cabig.caaers.integration.schema.study.StudyDeviceType;
import gov.nih.nci.cabig.caaers.rules.common.AdverseEventEvaluationResult;
import gov.nih.nci.cabig.caaers.rules.common.RuleLevel;
import gov.nih.nci.cabig.caaers.rules.common.RuleType;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.ctms.domain.DomainObject;
import gov.nih.nci.cabig.ctms.lang.NowFactory;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
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
    public static Organization createRemoteOrganization(final String organizationName, final int organizationId) {
        final Organization organization = createRemoteOrganization(organizationName);
        organization.setId(organizationId);
        return organization;
    }
    
    public static RemoteInvestigator createRemoteInvestigator(String externalId){
        RemoteInvestigator i = new RemoteInvestigator();
        i.setFirstName("FN_"+ externalId);
        i.setLastName("LN_"+externalId);

        return i;
    }

    public static <T extends DomainObject> T setId(final int id, final T target) {
        target.setId(id);
        return target;
    }

    public static Participant createParticipant(final String first, final String last) {
        Participant p = new Participant();
        p.setFirstName(first);
        p.setLastName(last);
        p.setEthnicity("White");
        p.setGender("Male");
        p.setRace("White");
        p.getIdentifiersLazy().add(new SystemAssignedIdentifier());
        p.getIdentifiersLazy().get(0).setValue("x");
        p.getIdentifiersLazy().get(0).setPrimaryIndicator(true);
        return p;
    }

    public static Study createStudy(final String shortTitle) {
        Study s = new LocalStudy();
        s.setShortTitle(shortTitle);
        s.setLongTitle(shortTitle);
        return s;
    }
    
    
    public static Study createCTEPStudy(final String shortTitle) {
        Study s = new LocalStudy();
        s.setShortTitle(shortTitle);
        s.setLongTitle(shortTitle);
        SystemAssignedIdentifier id = new SystemAssignedIdentifier();
        id.setType(SystemAssignedIdentifier.CTEP_ESYS_NAME);
        id.setSystemName(SystemAssignedIdentifier.CTEP_ESYS_NAME);
        id.setValue("x");
        s.addIdentifier(id);
        return s;
    }
    
    public static Study createRemoteStudy(final String shortTitle){
    	Study s = new RemoteStudy();
        s.setShortTitle(shortTitle);
        s.setLongTitle(shortTitle);
        return s;
    }

    public static Organization createOrganization(final String name) {
        Organization organization = new LocalOrganization();
        organization.setId(1);
        organization.setName(name);
        organization.setDescriptionText("dec:" + name);
        organization.setNciInstituteCode("NCI333:" + name);
        return organization;
    }
    
    public static Organization createRemoteOrganization(final String name) {
        Organization organization = new RemoteOrganization();
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

    public static StudyAgent createStudyAgent(Integer id, String agentName){
        StudyAgent sa = createStudyAgent(agentName);
        sa.setId(id);
        return sa;
    }
    public static StudyAgent createStudyAgent(final String agentName) {
        StudyAgent agent = new StudyAgent();
        agent.setPartOfLeadIND(false);
        agent.setAgent(new Agent());
        agent.getAgent().setName(agentName);
        return agent;
    }
    
    public static StudyAgentINDAssociation createStudyAgentIndAssociation(String number, String nciCode){
        StudyAgentINDAssociation saa = new StudyAgentINDAssociation();
        INDHolder holder = Fixtures.createOrganizationINDHolder(Fixtures.createOrganization(nciCode, nciCode));
        InvestigationalNewDrug ind = createInvestigationalNewDrug(holder,number ) ;
        saa.setInvestigationalNewDrug(ind);
        return saa;
    }


    public static StudyDeviceINDAssociation createStudyDeviceIndAssociation(String number, String nciCode){
        StudyDeviceINDAssociation saa = new StudyDeviceINDAssociation();
        INDHolder holder = Fixtures.createOrganizationINDHolder(Fixtures.createOrganization(nciCode, nciCode));
        InvestigationalNewDrug ind = createInvestigationalNewDrug(holder,number ) ;
        saa.setInvestigationalNewDrug(ind);
        return saa;
    }

    public static StudyAgent createStudyAgent(String agentName, InvestigationalNewDrug ind, INDType indType){

        StudyAgent sa = Fixtures.createStudyAgent(agentName);

        StudyAgentINDAssociation a = new StudyAgentINDAssociation();
        a.setStudyAgent(sa);
        sa.setIndType(indType);
        a.setInvestigationalNewDrug(ind);
        sa.addStudyAgentINDAssociation(a);
        return sa;
    }
    
    public static INDHolder createOrganizationINDHolder(Organization org ){
    	OrganizationHeldIND holder = new OrganizationHeldIND();
    	holder.setOrganization(org);
    	return holder;
    }
    
    public static InvestigationalNewDrug createInvestigationalNewDrug(INDHolder holder, String no){
    	InvestigationalNewDrug ind = new InvestigationalNewDrug();
    	ind.setStrINDNo(no);
    	ind.setINDHolder(holder);
    	
    	return ind;
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
        def.setLabel(name);
        Organization org = new LocalOrganization();
        org.setName("Test");
        def.setOrganization(org);
        def.addPlannedNotification(createPlannedEmailNotification());
        def.setGroup(createConfigProperty("RT_AdEERS"));
        return def;
    }
    
    public static ReportDefinition createReportDefinition(String name, Organization org, ConfigProperty reportType) {
        ReportDefinition def = new ReportDefinition();
        def.setName(name);
        def.setOrganization(org);
        def.addPlannedNotification(createPlannedEmailNotification());
        def.setGroup(reportType);
        return def;
    }
    
    public static ReportMandatoryFieldDefinition  createMandatoryField(String path, RequirednessIndicator m){
    	return new ReportMandatoryFieldDefinition(path, m);
    	
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
        rep.getLastVersion().setReportedAdversEvents(new ArrayList<ReportedAdverseEvent>());
        rep.getLastVersion().addReportTracking(new ReportTracking()); //initialize reportversions
        rep.getLastVersion().setId(10);
        rep.setAeReport(createSavableExpeditedReport());
        return rep;
    }

    //updates the mandatory fields in Report, based on Reportdefinition.
    public static void updateMandatoryFields(ReportDefinition rd, Report r){
        ArrayList<ReportMandatoryField> mfList = new ArrayList<ReportMandatoryField>();
        for(ReportMandatoryFieldDefinition d : rd.getMandatoryFields())
            mfList.add(new ReportMandatoryField(d.getFieldPath(), Mandatory.valueOf(d.getMandatory().name())));
        r.setMandatoryFields(mfList);
    }
    
    public static ConfigProperty createConfigProperty(String code){
    	ConfigProperty cp = new ConfigProperty();
    	cp.setCode(code);
    	cp.setName(code);
    	return cp;
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

    public static ReportVersion createReportVersion() {
        ReportVersion reportVersion = new ReportVersion();
        reportVersion.setReportVersionId("5");
        reportVersion.setAmendmentNumber(5);
        reportVersion.setCreatedOn(new Timestamp(106));
        reportVersion.setReportStatus(ReportStatus.PENDING);
        return reportVersion;
    }

    public static AeTerminology createCtcV3Terminology(final Study s) {
        AeTerminology t = s.getAeTerminology();
        Ctc v3 = new Ctc();
        v3.setId(3);
        v3.setName("3.0");
        t.setTerm(Term.CTC);
        t.setGridId("444444aaaa444");
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
        cTerm.setGridId("aaabbb8889a");
    	cTerm.setCtepTerm(ctepTerm);
    	cTerm.setTerm(ctepTerm);
    	cTerm.setCtepCode(ctepCode);
    	cTerm.setContextualGrades(new ArrayList<CtcGrade>());
    	return cTerm;
    }
    
    public static LowLevelTerm createLowLevelTerm(String code, String name){
        LowLevelTerm llt = new LowLevelTerm();
        llt.setId(-1);
        llt.setMeddraCode(code);
        llt.setMeddraTerm(name);
        return  llt;
    }

    public static Ctc createCtcaeV3() {
        Ctc v3 = new Ctc();
        v3.setName("3.0");
        v3.setId(3);
        v3.setGridId("aaabbb3332");
        // this is only partial, of course
        v3.setCategories(new ArrayList<CtcCategory>());
        v3.getCategories().add(createCtcCategory(v3, "ALLERGY/IMMUNOLOGY"));
        v3.getCategories().add(createCtcCategory(v3, "AUDITORY/EAR"));
        v3.getCategories().add(createCtcCategory(v3, "BLOOD/BONE MARROW"));
        return v3;
    }
    
    public static MeddraVersion createMeddraV9(){
        MeddraVersion mv = new MeddraVersion();
        mv.setName("MeDDRA v9");
        mv.setId(-1);
        return mv;
    }

    private static CtcCategory createCtcCategory(final Ctc ctc, final String name) {
        CtcCategory category = new CtcCategory();
        category.setGridId("aaa555aaa");
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

        SiteResearchStaff siteResearchStaff = new SiteResearchStaff();
        siteResearchStaff.setAddress(new Address());
        siteResearchStaff.setOrganization(organization);
        siteResearchStaff.setResearchStaff(researchStaff);
        researchStaff.getSiteResearchStaffs().add(siteResearchStaff);
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
        loginPolicy.setAllowedFailedLoginAttempts(3);
        loginPolicy.setLockOutDuration(0);
        loginPolicy.setMaxPasswordAge(60 * 60 * 24 * 7);

        CombinationPolicy combinationPolicy = new CombinationPolicy();
        combinationPolicy.setMinimumRequired(5);
        combinationPolicy.setUpperCaseAlphabetRequired(true);
        combinationPolicy.setLowerCaseAlphabetRequired(true);
        combinationPolicy.setNonAlphaNumericRequired(true);
        combinationPolicy.setBaseTenDigitRequired(true);
        combinationPolicy.setMaxSubstringLength(3);

        PasswordCreationPolicy passwordCreationPolicy = new PasswordCreationPolicy();
        passwordCreationPolicy.setCombinationPolicy(combinationPolicy);
        passwordCreationPolicy.setMinPasswordAge(60 * 60 * 24);
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
    
    public static TreatmentAssignmentAgent createTreatementAssignmentStudyIntervention(TreatmentAssignment tac, StudyAgent sa){
        TreatmentAssignmentAgent ts = new TreatmentAssignmentAgent();
        ts.setTreatmentAssignment(tac);
        ts.setStudyAgent(sa);
        return ts;
    }
    public static TreatmentAssignmentDevice createTreatementAssignmentStudyIntervention(TreatmentAssignment tac, StudyDevice sa){
        TreatmentAssignmentDevice ts = new TreatmentAssignmentDevice();
        ts.setTreatmentAssignment(tac);
        ts.setStudyDevice(sa);
        return ts;
    }
    public static TreatmentAssignmentOtherIntervention createTreatementAssignmentStudyIntervention(TreatmentAssignment tac, OtherIntervention sa){
        TreatmentAssignmentOtherIntervention ts = new TreatmentAssignmentOtherIntervention();
        ts.setTreatmentAssignment(tac);
        ts.setOtherIntervention(sa);
        return ts;
    }

    public static StudyFundingSponsor createStudyFundingSponsor(final Organization organization) {
        StudyFundingSponsor studyFundingSponsor = new StudyFundingSponsor();
        studyFundingSponsor.setOrganization(organization);
        return studyFundingSponsor;
    }
    
    public static StudySite createStudySite(final Organization organization, int identifier){
    	StudySite studySite = new StudySite();
    	studySite.setId(identifier);
    	studySite.setOrganization(organization);
    	return studySite;
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
    public static StudyCoordinatingCenter createStudyCoordinatingCenter(final Organization organization) {
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
    
    public static SiteResearchStaff createSiteResearchStaff(Organization org, ResearchStaff rStaff){
    	SiteResearchStaff siteResearchStaff = new SiteResearchStaff();
        rStaff.addSiteResearchStaff(siteResearchStaff);
    	siteResearchStaff.setOrganization(org);
    	return siteResearchStaff;
    }

    public static SiteResearchStaffRole createSiteResearchStaffRole(String roleCode, Date start, Date end){
       SiteResearchStaffRole role =  new SiteResearchStaffRole();
       role.setRoleCode(roleCode);
       role.setStartDate(start);
       role.setEndDate(end);
       return role;
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
    	SiteResearchStaff siteResearchStaff = new SiteResearchStaff();
    	siteResearchStaff.setResearchStaff(staff);
    	sp.setSiteResearchStaff(siteResearchStaff);
    	sp.setRoleCode("role");
    	sp.setStartDate(DateUtils.yesterday());
    	return sp;
    }
   
    public static Agent createAgent(String name){
        Agent agent = new Agent();
        agent.setDescription("abcd");
        agent.setName(name);
        return agent;
    }
    public static Agent createAgent(String name, List<StudyAgent> studyAgents){
    	Agent agent = createAgent(name);
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
		meddraStudyDisease.setTerm(lowLevelTerm);
		meddraStudyDisease.setStudy(s);
		return meddraStudyDisease;
		
    }
    

    public static StudyCondition createStudyCondition(Study s, gov.nih.nci.cabig.caaers.domain.Condition c){
    	StudyCondition sc = new StudyCondition();
    	sc.setTerm(c);
    	s.addStudyCondition(sc);
    	return sc;
    }


    public static StudyCondition createStudyCondition(Study s, String name){
        StudyCondition sc = new StudyCondition();
        sc.setTerm(new gov.nih.nci.cabig.caaers.domain.Condition());
        sc.getTerm().setConditionName(name);
        s.addStudyCondition(sc);
        return sc;
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
        ctcTerm.setGridId("aaaabbb444aaa");
		return createAdverseEvent(id, grade, ctcTerm);
    }

    public static AdverseEvent createAdverseEvent(int id, Grade grade, CtcTerm ctcTerm){
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
    
    public static Organization createRemoteOrganization(final String name, final String nciInstituteCode) {
        Organization organization = new RemoteOrganization();
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
    
    public static ReportDefinition createReportDefinition(String name, String nciInstituteCode, int duration, TimeScaleUnit timeScale) {
        ReportDefinition def = new ReportDefinition();
        def.setName(name);
        Organization org = createOrganization("testOrg", nciInstituteCode);
        def.setOrganization(org);
        def.setAmendable(true);
        def.setTimeScaleUnitType(timeScale);
        def.setDuration(duration);
        def.setEnabled(true);
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
   
  public static ChemoAgent createChemoAgent(String name, String genericName){
	  ChemoAgent a = new ChemoAgent();
	  a.setName(name);
	  a.setGenericName(genericName);
	  return a;
  }

  public static Rule createRule(Condition c){
      Rule r = new Rule();
      r.setCondition(c);
      r.setMetaData(new MetaData());
      return r;
  }

  public static Condition createCondition(String... columns) {
      Condition c = new Condition();
      List<Column> cols = new ArrayList<Column>();
      for(String col : columns){
          cols.add(createColumn(col));
      }
      c.setColumn(cols);
      return c;
  }

  public static Column createColumn(String name){
      Column c = new Column();
      c.setExpression(name);
      c.setObjectType(name);
      c.setIdentifier(name);
      c.setMarkedDelete(false);

      c.setFieldConstraint(Arrays.asList(createFieldConstraint()));

      return c;
  }

  public static FieldConstraint createFieldConstraint(){
      FieldConstraint fc = new FieldConstraint();
      fc.setDisplayUri("undefined");
      fc.setFieldName("fldName");
      fc.setGrammerPostfix("");
      fc.setGrammerPrefix(" prefix ");

      LiteralRestriction lr = new LiteralRestriction();
      lr.setDisplayUri("is ");
      lr.setEvaluator("==");
      lr.setReadableValue("A");
      lr.setReadableValue("B");
      fc.setLiteralRestriction(Arrays.asList(lr));
      return fc;
  }


  public static Device createDevice(){
      Device d = new Device();
      d.setBrandName("Brand");
      d.setCommonName("CName");
      d.setType("type");
      return d;
  }

  public static StudyDevice createStudyDevice(Integer id){
     StudyDevice sd = createStudyDevice();
      sd.setId(id);
      return sd;
  }
  public static StudyDevice createStudyDevice(){
      Device d = createDevice();
      StudyDevice sd = new StudyDevice();
      sd.setCatalogNumber("c1");
      sd.setManufacturerCity("mc");
      sd.setManufacturerName("ma");
      sd.setManufacturerState("va");
      sd.setModelNumber("999");
      ArrayList<StudyDeviceINDAssociation> l = new ArrayList<StudyDeviceINDAssociation>();
      StudyDeviceINDAssociation sinda = new StudyDeviceINDAssociation();
      l.add(sinda);
      INDHolder holder = createOrganizationINDHolder(createOrganization("CTEP"));
      sinda.setInvestigationalNewDrug(createInvestigationalNewDrug(holder,  "1"));
      sd.setStudyDeviceINDAssociations(l);
      sd.setDevice(d);
      return sd;
  }
    
  
  public static OtherIntervention createOtherIntervention(Integer id, String name, StudyTherapyType  therapy){
      OtherIntervention oi = new OtherIntervention();
      oi.setName(name);
      oi.setId(id);
      oi.setStudyTherapyType(therapy);
      return oi;
  }

  public static User createUser(String loginId, String name){
     User u = new User();
     u.setFirstName(name);
     u.setLoginName(loginId);
     return u;
  }
    
  public static gov.nih.nci.security.authorization.domainobjects.User createCSMUser(long id, String loginId, String name){
      gov.nih.nci.security.authorization.domainobjects.User u = new gov.nih.nci.security.authorization.domainobjects.User();
      u.setUserId(id);
      u.setLoginName(loginId);
      u.setFirstName(name);
      u.setLastName(name);
      u.setEmailId(loginId+ "@test.com");
      return u;
  }

  public static SAEReportPreExistingCondition createSAEReportPreExistingCondition(String name){
    SAEReportPreExistingCondition pec =  new SAEReportPreExistingCondition();
    pec.setPreExistingCondition(createPreExistingCondtion(name));
    return pec;
  }

  public static PreExistingCondition createPreExistingCondtion(String name){
      PreExistingCondition pec = new PreExistingCondition(name)   ;
      pec.setRetiredIndicator(false);
      return pec ;
  }
    
  public static PreExistingCondition createPreExistingCondition(String name, String lltCode, String llt, String hlgt )  {
      PreExistingCondition preCondition = new PreExistingCondition();
      preCondition.setMeddraHlgt(hlgt);
      preCondition.setMeddraLlt(llt);
      preCondition.setMeddraLltCode(lltCode);
      preCondition.setText(name);
      return preCondition;
  }
    
  public static PreExistingConditionType createPreExistingConditionType(String name, String lltCode, String llt, String hlgt ) {
      PreExistingConditionType preConditionType = new PreExistingConditionType();
      preConditionType.setMeddraHlgt(hlgt);
      preConditionType.setMeddraLlt(llt);
      preConditionType.setMeddraLltCode(lltCode);
      preConditionType.setText(name);
      return preConditionType;
  }
  public static AeTerminology createAeTerminology(Term t){
      AeTerminology aet  = new AeTerminology();
      aet.setTerm(t);
      if(t == Term.CTC) {
        aet.setCtcVersion( Fixtures.createCtcaeV3());     
      }else {
          aet.setMeddraVersion(Fixtures.createMeddraV9());
      }
      aet.setId(-32);
      return aet;
  }
  
  public static AgentSpecificTerm createAgentSpecificCtcTerm(Agent a, CtcTerm term){
	  AgentSpecificCtcTerm agentSpecificCtcTerm = new AgentSpecificCtcTerm();
	  agentSpecificCtcTerm.setCtcTerm(term);
	  agentSpecificCtcTerm.setAgent(a);
	  a.getAgentSpecificTerms().add(agentSpecificCtcTerm);
	  return agentSpecificCtcTerm;
	  
  }
  
  public static RuleSet createRuleSet(){
	  return createRuleSet(RuleType.REPORT_SCHEDULING_RULES);
  }
  
  public static RuleSet createRuleSet(RuleType ruleType){
      RuleSet rs = new RuleSet();
      rs.setRuleType(ruleType);
      rs.setStatus(RuleSet.STATUS_ENABLED);
      rs.setOrganization(createOrganization("CTEP", "CTEP"));
      rs.setRuleLevel(RuleLevel.Sponsor);
      return rs;
  }
  
  public static AbstractStudyInterventionExpectedAE createAbstractStudyInterventionExpectedAE(){
	  AbstractStudyInterventionExpectedAE exp = new StudyInterventionExpectedCtcTerm();
	  exp.setExpected(true);
	  exp.setExpectednessFrequency(1.0);
	  exp.setGrade1Frequency(2.0);
	  exp.setGrade2Frequency(3.0);
	  exp.setGrade3Frequency(4.0);
	  exp.setGrade4Frequency(5.0);
	  exp.setGrade5Frequency(6.0);
	  return exp;
  }
    
    public static PriorTherapy createPriorTherapy(String therapy){
        PriorTherapy pt = new PriorTherapy();
        pt.setRetiredIndicator(false);
        pt.setText(therapy);
        return pt;
    }
    
    public static PriorTherapyType createPriorTherapyType(String meddraCode, String text, String meddraTerm){
        PriorTherapyType xmlTherapy = new PriorTherapyType();
        xmlTherapy.setMeddraCode(meddraCode);
        xmlTherapy.setText(text);
        xmlTherapy.setMeddraTerm(meddraTerm);
        xmlTherapy.setTherapyType("OYT");
        return xmlTherapy;
    }
    
    public static DeviceType createDeviceType(String cn, String bn, String dt, String ctepId){
        DeviceType d= new DeviceType();
        d.setCommonName(cn);
        d.setBrandName(bn);
        d.setType(dt);
        d.setCtepDbIdentifier(ctepId);
        d.setStatus(ActiveInactiveStatusType.ACTIVE);
        return d;
    }


    public static StudyDeviceType createStudyDeviceType(){
        StudyDeviceType t = new StudyDeviceType();
        t.setDevice(createDeviceType("cn", "bn", "dt", "1"));
        t.setStudyDeviceINDAssociations(new StudyDeviceType.StudyDeviceINDAssociations());
        t.getStudyDeviceINDAssociations().setStudyDeviceINDAssociation(createStudyDeviceINDAssociationType());
        return t;
    }

    public static StudyDeviceINDAssociationType createStudyDeviceINDAssociationType(){
        StudyDeviceINDAssociationType t = new StudyDeviceINDAssociationType();
        t.setInvestigationalNewDrug( createInvestigationalNewDrugType() );
        return t;
    }

    public static InvestigationalNewDrugType createInvestigationalNewDrugType(){
       return createInvestigationalNewDrugType("test", BigInteger.ONE);
    }

    public static InvestigationalNewDrugType createInvestigationalNewDrugType(String holder, BigInteger i){
        InvestigationalNewDrugType t = new InvestigationalNewDrugType();
        t.setHolderName(holder);
        t.setIndNumber(i);
        return t;
    }

    public static ConfigProperties createConfigProperties(){
        ConfigProperties p =  new ConfigProperties();
        p.setName("AGENT_UOM");
        p.getConfigProperty().add(Fixtures.createConfigPropertyType("c"));
        return p;
    }
    
    public static  ConfigPropertyType createConfigPropertyType(String code){
        ConfigPropertyType t = new ConfigPropertyType();
        t.setCode(code);
        t.setName(code);
        return t;
    }
    
    public static AnatomicSite createAnatomicSite(String name, String category){
        AnatomicSite a = new AnatomicSite();
        a.setName(name);
        a.setCategory(category);
        return a;
    }
    public static MetastaticDiseaseSite createMetastaticDiseaseSite(String name){
       MetastaticDiseaseSite m = new MetastaticDiseaseSite();
        m.setCodedSite(Fixtures.createAnatomicSite(name, name));
        return m;
    }
    
    public static StudyParticipantMetastaticDiseaseSite createStudyParticipantMetastaticDiseaseSite(String name){
        StudyParticipantMetastaticDiseaseSite m = new StudyParticipantMetastaticDiseaseSite();
        m.setCodedSite(createAnatomicSite(name, name));
        return m    ;
    }
    
    public ConcomitantMedication createConcomitantMedication(String agentName){
        ConcomitantMedication c = new ConcomitantMedication();
        c.setAgentName(agentName);
        return c;
    }
    
    public static StudyParticipantConcomitantMedication createStudyParticipantConcomitantMedication(String n){
        StudyParticipantConcomitantMedication c = new StudyParticipantConcomitantMedication();
        c.setAgentName(n);
        return c;
    }
    
    public static AdverseEventEvaluationResult createAdverseEventEvaluationResult(String... reportDefNames){
        String msg = "";
        AdverseEventEvaluationResult result = AdverseEventEvaluationResult.noRulesFound("test");
        for(String s : reportDefNames){
            msg  = msg + s;
            result.getRuleEvaluationResult().addResponse(s);
        }
        result.getRuleEvaluationResult().setMessage(msg);
        return result;
    }
    
    public static TaskNotificationDTO createTaskNotificationDTO(String description) {
        TaskNotificationDTO dto = new TaskNotificationDTO();
        dto.setDescription(description);
        return dto;
    }

    public static OrganizationType createOrganizationType(String n){
        OrganizationType o = new OrganizationType();
        o.setName(n);
        o.setCity(n);
        o.setNciInstituteCode(n);
        o.setStatus(ActiveInactiveStatusType.ACTIVE);
        return o;
    }
    
    public static AeMergeDTO createAeMergeDTO(){
        AeMergeDTO d = new AeMergeDTO();
        d.setMerges(new int[]{1,2,1,2,2,1,1});
        d.setExternalAeId(11);
        d.setInteralAeId(15);
        return d;
    }

    public static AdverseEventDTO createAdverseEventDTO(){
        return  Fixtures.createAdverseEventDTO(12, 102, "5490", "Nausea", "Severe", "10/09/2011", "10/11/2011", "Redness in left eye", "", "Probable");
    }
    public static AdverseEventDTO createAdverseEventDTO(int id, int termId, String termCode, String term, String grade, String startDate, String endDate, String verbatim, String whySerious, String attibution){
        AdverseEventDTO dto = new AdverseEventDTO();
        dto.setId(id);
        TermDTO t = new TermDTO();
        t.setId(termId);
        t.setCode(termCode);
        t.setName(term);
        dto.setTerm(t);
        dto.setGrade(grade);
        dto.setStartDate(startDate);
        dto.setEndDate(endDate);
        dto.setVerbatim(verbatim);
        dto.setWhySerious(whySerious);
        dto.setAttribution(attibution);
        return dto;
    }

    public static Lab createLab(String labTerm, String labCategory){
        Lab l = new Lab();
        LabCategory lc = new LabCategory();
        lc.setName(labCategory);
        LabTerm lt = new LabTerm();
        lt.setTerm(labTerm);
        lt.setCategory(lc);
        l.setLabTerm(lt);
        return l;
    }

    public static MedicalDevice createMedicalDevice(boolean reprocessed, boolean returned){
        MedicalDevice medicalDevice = new MedicalDevice();
        if(reprocessed) medicalDevice.setDeviceReprocessed(ReprocessedDevice.YES);
        if(returned) medicalDevice.setEvaluationAvailability(Availability.RETURNED);
        return medicalDevice;
    }
    public static ConcomitantMedication createConcomitantMedication(boolean stillTakingMedication){
        ConcomitantMedication conMed = new ConcomitantMedication();
        conMed.setStillTakingMedications(stillTakingMedication);
        return conMed;
    }
}
