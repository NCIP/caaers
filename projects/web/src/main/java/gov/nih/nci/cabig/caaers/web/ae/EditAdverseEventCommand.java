package gov.nih.nci.cabig.caaers.web.ae;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.TreatmentInformation;
import gov.nih.nci.cabig.caaers.rules.domain.AdverseEventSDO;
import gov.nih.nci.cabig.caaers.rules.domain.StudySDO;
import gov.nih.nci.cabig.caaers.rules.runtime.RuleExecutionService;

/**
 * @author Rhett Sutphin
 */
public class EditAdverseEventCommand implements AdverseEventInputCommand {
    private ExpeditedAdverseEventReport aeReport;
    private Map<String, List<List<Attribution>>> attributionMap;

    private RuleExecutionService ruleExecutionService;

    ////// LOGIC

    public EditAdverseEventCommand(RuleExecutionService ruleExecutionService) {
        setRuleExecutionService(ruleExecutionService);
    }

    public StudyParticipantAssignment getAssignment() {
        return aeReport.getAssignment();
    }

    public Participant getParticipant() {
        return getAssignment().getParticipant();
    }

    public Study getStudy() {
        return getAssignment().getStudySite().getStudy();
    }

    ////// BEAN PROPERTIES

    public ExpeditedAdverseEventReport getAeReport() {
        return aeReport;
    }

    public Map<String, List<List<Attribution>>> getAttributionMap() {
        return attributionMap;
    }

    public void setAeReport(ExpeditedAdverseEventReport aeReport) {
        this.aeReport = aeReport;
        if (aeReport.getAdverseEvents().size() == 0) {
            aeReport.addAdverseEvent(new AdverseEvent());
        }
        if (aeReport.getTreatmentInformation() == null) {
            aeReport.setTreatmentInformation(new TreatmentInformation());
        }
        this.attributionMap = new AttributionMap(aeReport);
    }

    // TODO: this code is *exactly* the same as in CreateAdverseEventCommand
    // Put it somewhere shared
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
