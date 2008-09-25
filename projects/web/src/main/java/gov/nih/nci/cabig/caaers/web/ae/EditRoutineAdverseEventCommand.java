package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.RoutineAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.RoutineAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.TreatmentInformation;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.ctms.lang.NowFactory;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Krikor Krumlian
 */
public class EditRoutineAdverseEventCommand implements RoutineAdverseEventInputCommand {
	private static final Log log = LogFactory.getLog(EditRoutineAdverseEventCommand.class);
    private ExpeditedAdverseEventReport aeReport;
    private RoutineAdverseEventReport aeRoutineReport;
    private Map<String, List<List<Attribution>>> attributionMap;
    private NowFactory nowFactory;
    private EvaluationService evaluationService;

    private List<CtcCategory> categories;
    private String[] ctcCatIds;
    private String[] cats;
    private String[] ctcTermIds;

    private RoutineAdverseEventReportDao routineReportDao;
    private ExpeditedAdverseEventReportDao reportDao;

    ////// LOGIC

    public EditRoutineAdverseEventCommand(RoutineAdverseEventReportDao routineReportDao,
    									  ExpeditedAdverseEventReportDao reportDao,
    									  NowFactory nowFactory,
    									  EvaluationService evaluationService) {
        this.categories = new ArrayList<CtcCategory>();
        this.routineReportDao = routineReportDao;
        this.reportDao = reportDao;
        this.nowFactory = nowFactory;
        this.evaluationService = evaluationService;
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

    public void save() {
        routineReportDao.save(getAeRoutineReport());
    }
    

    private void prepareExpeditedReport()
    {
    	this.aeReport = new ExpeditedAdverseEventReport();
        this.aeReport.setAssignment(getAssignment());
        this.aeReport.setCreatedAt(nowFactory.getNowTimestamp());
        this.aeReport.getTreatmentInformation().setTreatmentAssignment(this.aeRoutineReport.getTreatmentAssignment());
    }
    
    
    /*
     * Will try to find serious AEs if found they will be reported as expedited.
     * An AE might be MedDRA or CTC based Rules should take that into consideration
     *
     * @see gov.nih.nci.cabig.caaers.web.ae.AdverseEventInputCommand#save()
     */
    public void checkRoutineAesForExpeditedness() {
    	//StudyParticipantAssignment assignment = getAssignment();
    	
    	//assignment.addRoutineReport(aeRoutineReport);
        
        //create the expedited report, container for AEs
    	
    	// Get the Report Object from one of the expedited AEs already in ths collection. 
    	for(AdverseEvent ae : aeRoutineReport.getAdverseEvents()){
     	  if (ae.getReport() != null ){
     		  this.aeReport = ae.getReport();
     		  break;
     	  }
        }
        // If report still null create a new one. 
    	if (aeReport == null ){
    		prepareExpeditedReport();
    	}
    	
        //check if the event reported is an SAE.
        for(AdverseEvent ae : aeRoutineReport.getAdverseEvents()){
    	   if(ae.getReport() == null && evaluationService.isSevere(aeRoutineReport.getAssignment(), ae)){
    		   if(log.isDebugEnabled()){
    			   log.debug("The AE " + String.valueOf(ae) +  " is identified as SAE, for the assignment " +  String.valueOf(aeRoutineReport.getAssignment()));
    		   }
    		   this.aeReport.addAdverseEvent(ae);
    	   }
       }
       
       //if there are SAEs, then save the aeReport,then identify mandatory report schedules
       if(!aeReport.getAdverseEvents().isEmpty()){
    	   reportDao.save(aeReport);
    	   List<ReportDefinition> reportDefs = evaluationService.findRequiredReportDefinitions(aeReport, aeReport.getAdverseEvents(), aeReport.getStudy());
    	   evaluationService.addOptionalReports(aeReport, reportDefs, true);
       }
       
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
	public String getTreatmentDescriptionType() {
	 	// TODO Check if we need this for routine adverse events
	   	return null;
	}
	public void setTreatmentDescriptionType(String type) {
	  	// TODO Check if this is needed for routine adverse events
	}
	public boolean getIgnoreCompletedStudy() {
		return true;
	}
}
