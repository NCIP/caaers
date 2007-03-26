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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Rhett Sutphin
 */
public class CreateAdverseEventCommand implements AdverseEventInputCommand {
    private AdverseEventReport aeReport;

    private Participant participant;
    private Study study;

    private AdverseEventReportDao reportDao;
    private StudyParticipantAssignmentDao assignmentDao;
    
    private RuleExecutionService ruleExecutionService;

    public CreateAdverseEventCommand(
            StudyParticipantAssignmentDao assignmentDao, AdverseEventReportDao reportDao
        ) {
    	this(assignmentDao, reportDao, null);
    }

    
    public CreateAdverseEventCommand(
        StudyParticipantAssignmentDao assignmentDao, AdverseEventReportDao reportDao, RuleExecutionService ruleExecutionService
    ) {
        this.assignmentDao = assignmentDao;
        this.reportDao = reportDao;
        aeReport = new AdverseEventReport();
        aeReport.setPrimaryAdverseEvent(new AdverseEvent());

        // TODO temporary
        aeReport.getLabs().add(new Lab());
        
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
    
    public void fireAERules() {
    	String bindUri = "CAAERS_AE_RULES";
		ArrayList<AdverseEventSDO> list = new ArrayList<AdverseEventSDO>();
		
		AdverseEvent adverseEvent = aeReport.getPrimaryAdverseEvent(); 
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
		CtcTerm ctcTerm = aeReport.getPrimaryAdverseEvent().getCtcTerm();
		adverseEventSDO.setTerm(ctcTerm.getFullName());
		
		//HOSPITALIZATION
		int hospitalization = aeReport.getPrimaryAdverseEvent().getHospitalization().getCode();
		Boolean isHospitalization = (hospitalization == Hospitalization.NONE.getCode()) ? Boolean.FALSE : Boolean.TRUE ;
		
		adverseEventSDO.setHospitalization(isHospitalization.toString());
		try {
			getRuleExecutionService().fireRules(bindUri, studySDO, list);
		}catch(Exception e) {
			Logger.getLogger(this.toString()).info("Exception while firing rules" + e.getMessage());
			Logger.getLogger(this.toString()).log(Level.FINE, "Exception while firing rules" + e.getMessage());
		}
    }

	public RuleExecutionService getRuleExecutionService() {
		return ruleExecutionService;
	}

	public void setRuleExecutionService(RuleExecutionService ruleExecutionService) {
		this.ruleExecutionService = ruleExecutionService;
	}    
}
