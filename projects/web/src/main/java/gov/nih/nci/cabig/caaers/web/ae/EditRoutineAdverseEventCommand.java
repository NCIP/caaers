package gov.nih.nci.cabig.caaers.web.ae;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.TreatmentInformation;
import gov.nih.nci.cabig.caaers.domain.RoutineAdverseEventReport;
import gov.nih.nci.cabig.caaers.rules.runtime.RuleExecutionService;

/**
 * @author Krikor Krumlian
 */
public class EditRoutineAdverseEventCommand implements RoutineAdverseEventInputCommand {
    private ExpeditedAdverseEventReport aeReport;
    private RoutineAdverseEventReport aeRoutineReport;
    private Map<String, List<List<Attribution>>> attributionMap;
    
    private List<CtcCategory> categories;
    private String[] ctcCatIds;
    private String[] cats;
    private String[] ctcTermIds;

    private RuleExecutionService ruleExecutionService;

    ////// LOGIC

    public EditRoutineAdverseEventCommand(RuleExecutionService ruleExecutionService) {
    	this.categories = new ArrayList<CtcCategory>();
        //setRuleExecutionService(ruleExecutionService);
    }

    public StudyParticipantAssignment getAssignment() {
        return aeRoutineReport.getAssignment();
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

	public RoutineAdverseEventReport getAeRoutineReport() {
		return aeRoutineReport;
	}

	public void setAeRoutineReport(RoutineAdverseEventReport aeRoutineReport) {
		this.aeRoutineReport = aeRoutineReport;
	}

	public List<CtcCategory> getCategories() {
		return categories;
	}

	public void setCategories(List<CtcCategory> categories) {
		this.categories = categories;
	}

	public String[] getCats() {
		return cats;
	}

	public void setCats(String[] cats) {
		this.cats = cats;
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
	
	

    
}
