package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.Lab;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.rules.domain.AdverseEventSDO;
import gov.nih.nci.cabig.caaers.rules.domain.StudySDO;
import gov.nih.nci.cabig.caaers.rules.runtime.RuleExecutionService;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Rhett Sutphin
 */
public class CreateAdverseEventCommand implements AdverseEventInputCommand {
    private static final Log log = LogFactory.getLog(CreateAdverseEventCommand.class);

    private AdverseEventReport aeReport;

    private Participant participant;
    private Study study;

    private AdverseEventReportDao reportDao;
    private StudyParticipantAssignmentDao assignmentDao;
    
    private RuleExecutionService ruleExecutionService;

    public CreateAdverseEventCommand(
        StudyParticipantAssignmentDao assignmentDao, AdverseEventReportDao reportDao, RuleExecutionService ruleExecutionService
    ) {
        this.assignmentDao = assignmentDao;
        this.reportDao = reportDao;
        this.aeReport = new AdverseEventReport();
        // ensure there's at least one before the fields are generated
        this.aeReport.addAdverseEvent(new AdverseEvent());

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

    public void save() {
        getAssignment().addReport(getAeReport());
        reportDao.save(getAeReport());
        fireAERules();
    }

    ////// BOUND PROPERTIES

    public AdverseEventReport getAeReport() {
        return aeReport;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
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
		adverseEventSDO.setGrade(String.valueOf(grade));
				
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
}
