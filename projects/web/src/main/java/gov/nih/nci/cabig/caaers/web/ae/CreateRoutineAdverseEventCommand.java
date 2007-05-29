package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.RoutineAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.AdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.RoutineAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.ContactMechanism;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.DiseaseHistory;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.Lab;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ParticipantHistory;
import gov.nih.nci.cabig.caaers.domain.Physician;
import gov.nih.nci.cabig.caaers.domain.Reporter;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.StudyPersonnel;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.TreatmentInformation;
import gov.nih.nci.cabig.caaers.rules.domain.AdverseEventSDO;
import gov.nih.nci.cabig.caaers.rules.domain.StudySDO;
import gov.nih.nci.cabig.caaers.rules.runtime.RuleExecutionService;
import gov.nih.nci.cabig.ctms.lang.NowFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Rhett Sutphin
 */
public class CreateRoutineAdverseEventCommand implements RoutineAdverseEventInputCommand  {
    private static final Log log = LogFactory.getLog(CreateRoutineAdverseEventCommand.class);

    private AdverseEventReport aeReport;
    private RoutineAdverseEventReport aeRoutineReport;

    private Participant participant;
    private Study study;

    private AdverseEventReportDao reportDao;
    private RoutineAdverseEventReportDao routineReportDao;
    private StudyParticipantAssignmentDao assignmentDao;

    private RuleExecutionService ruleExecutionService;
    private Map<String, List<List<Attribution>>> attributionMap;

    private List<CtcCategory> categories;
    private String[] ctcCatIds;
    private String[] cats;
    private String[] ctcTermIds;

    public CreateRoutineAdverseEventCommand(
        StudyParticipantAssignmentDao assignmentDao, RoutineAdverseEventReportDao routineReportDao,
        RuleExecutionService ruleExecutionService, NowFactory nowFactory
    ) {
        this.assignmentDao = assignmentDao;
        this.aeRoutineReport = new RoutineAdverseEventReport();
        //this.reportDao = reportDao; TODO fix this we will need it 
        this.routineReportDao = routineReportDao;
        //this.aeRoutineReport = new RoutineAdverseEventReport();
        //this.aeReport.setCreatedAt(nowFactory.getNowTimestamp());
        // ensure there's at least one before the fields are generated
        this.categories = new ArrayList<CtcCategory>();

        setRuleExecutionService(ruleExecutionService);
    }

  
    ////// LOGIC

    public StudyParticipantAssignment getAssignment() {
        if (getParticipant() != null && getStudy() != null) {
            return assignmentDao.getAssignment(getParticipant(), getStudy());
        } else {
            return null;
        }
    }

    // This method deliberately sets only one side of the bidirectional link.
    // This is so hibernate will not discover the link from the persistent side
    // (assignment) and try to save the report before we want it to.
    private void updateReportAssignmentLink() {
        getAeRoutineReport().setAssignment(getAssignment());
    }

    public void save() {
        getAssignment().addRoutineReport(getAeRoutineReport());
        routineReportDao.save(getAeRoutineReport());
        fireAERules();
    }

    ////// BOUND PROPERTIES

    public AdverseEventReport getAeReport() {
        return aeReport;
    }

    public Map<String, List<List<Attribution>>> getAttributionMap() {
        return attributionMap;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
        updateReportAssignmentLink();
    }

    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
        // TODO: this is temporary -- need a cleaner way to force this to load
        // in same session as study is loaded and/or reassociate study with hib session later
        if (study != null) {
            this.study.getStudyAgents().size();
            this.study.getCtepStudyDiseases().size();
            this.study.getStudySites().size();
            for(StudySite site : study.getStudySites()){
                site.getStudyPersonnels().size();
            }
        }
        updateReportAssignmentLink();
    }

    // TODO:
    //   - This only handles the first AE
    public void fireAERules() {
        String bindUri = "CAAERS_AE_RULES";
        ArrayList<AdverseEventSDO> list = new ArrayList<AdverseEventSDO>();

        AdverseEvent adverseEvent = aeReport.getAdverseEvents().get(0);
        StudySDO studySDO = new StudySDO();
        Study study = aeReport.getAssignment().getStudySite().getStudy();
        studySDO.setShortTitle(study.getShortTitle());

        AdverseEventSDO adverseEventSDO = new AdverseEventSDO();

        // ATTRIBUTION
        //adverseEventSDO.setAttribution(adverseEventReport.get); // Where to get this from -- ask Rhett

        //PHASE -- // Where to get this from -- ask Rhett
        String phase = aeReport.getAssignment().getStudySite().getStudy().getPhaseCode();
        adverseEventSDO.setPhase(phase);

        //EXPECTED
        boolean expected = adverseEvent.getExpected();
        adverseEventSDO.setExpected((String.valueOf(expected)));

        //GRADE
        int grade = adverseEvent.getGrade().getCode();
        //adverseEventSDO.setGrade(String.valueOf(grade));
        adverseEventSDO.setGrade(new Integer(grade));

        //CATEGORY
        CtcCategory category = adverseEvent.getCtcTerm().getCategory();
        adverseEventSDO.setCategory(category.getName());

        //CTC TERM
        CtcTerm ctcTerm = adverseEvent.getCtcTerm();
        adverseEventSDO.setTerm(ctcTerm.getFullName());

        //HOSPITALIZATION
        int hospitalization = adverseEvent.getHospitalization().getCode();
        Boolean isHospitalization = (hospitalization == Hospitalization.NONE.getCode()) ? Boolean.FALSE : Boolean.TRUE ;

        adverseEventSDO.setHospitalization(isHospitalization.toString());
        list.add(adverseEventSDO);
        try {
            getRuleExecutionService().fireRules(bindUri, studySDO, list);
        } catch(Exception e) {
            log.error("Exception while firing rules: " + e.getMessage(), e);
            // TODO: why is this exception swallowed?
        }
    }

    public RuleExecutionService getRuleExecutionService() {
        return ruleExecutionService;
    }

    public void setRuleExecutionService(RuleExecutionService ruleExecutionService) {
        this.ruleExecutionService = ruleExecutionService;
    }

    public void setAeReport(AdverseEventReport aeReport) {
        this.aeReport = aeReport;
    }

    public void setAeRoutineReport(RoutineAdverseEventReport aeRoutineReport) {
		this.aeRoutineReport = aeRoutineReport;
	}

	public RoutineAdverseEventReport getAeRoutineReport() {
		return aeRoutineReport;
	}


	public List<CtcCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<CtcCategory> categories) {
        this.categories = categories;
    }

    public String[] getCtcCatIds() {
        return ctcCatIds;
    }

    public void setCtcCatIds(String[] ctcCatIds) {
        this.ctcCatIds = ctcCatIds;
    }

    public String[] getCtcTermIds() {
        return ctcTermIds;
    }

    public void setCtcTermIds(String[] ctcTermIds) {
        this.ctcTermIds = ctcTermIds;
    }

    public String[] getCats() {
        return cats;
    }

    public void setCats(String[] cats) {
        this.cats = cats;
    }


}
