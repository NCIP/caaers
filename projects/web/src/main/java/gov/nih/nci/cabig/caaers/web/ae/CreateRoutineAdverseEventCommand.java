package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.RoutineAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.RoutineAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.TreatmentInformation;
import gov.nih.nci.cabig.caaers.rules.business.service.AdverseEventEvaluationService;
import gov.nih.nci.cabig.caaers.rules.business.service.AdverseEventEvaluationServiceImpl;
import gov.nih.nci.cabig.ctms.lang.NowFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Krikor Krumlian
 */
public class CreateRoutineAdverseEventCommand implements RoutineAdverseEventInputCommand  {
    private static final Log log = LogFactory.getLog(CreateRoutineAdverseEventCommand.class);

    private ExpeditedAdverseEventReport aeReport;
    private RoutineAdverseEventReport aeRoutineReport;

    private Participant participant;
    private Study study;

    private ExpeditedAdverseEventReportDao reportDao;
    private RoutineAdverseEventReportDao routineReportDao;
    private StudyParticipantAssignmentDao assignmentDao;

    private AdverseEventEvaluationService adverseEventEvaluationService;
    private Map<String, List<List<Attribution>>> attributionMap;
    private NowFactory nowFactory;

    private List<CtcCategory> categories;
    private String[] ctcCatIds;
    private String[] cats;
    private String[] ctcTermIds;

    public CreateRoutineAdverseEventCommand(
        StudyParticipantAssignmentDao assignmentDao, RoutineAdverseEventReportDao routineReportDao,
        ExpeditedAdverseEventReportDao reportDao, NowFactory nowFactory
    ) {
        this.assignmentDao = assignmentDao;
        this.aeRoutineReport = new RoutineAdverseEventReport();
        this.reportDao = reportDao;
        this.routineReportDao = routineReportDao;
        this.categories = new ArrayList<CtcCategory>();
        this.nowFactory = nowFactory;
        this.adverseEventEvaluationService = new AdverseEventEvaluationServiceImpl();
    }

    ////// LOGIC

    public StudyParticipantAssignment getAssignment() {
        if (getParticipant() != null && getStudy() != null) {
            return assignmentDao.getAssignment(getParticipant(), getStudy());
        } else {
            return null;
        }
    }

    private void prepareExpeditedReport()
    {
    	this.aeReport = new ExpeditedAdverseEventReport();
        this.aeReport.setAssignment(getAssignment());
        this.aeReport.setCreatedAt(nowFactory.getNowTimestamp());
    }

    public void setAeRoutineReport(RoutineAdverseEventReport aeRoutineReport) {
        this.aeRoutineReport = aeRoutineReport;
    }


    // This method deliberately sets only one side of the bidirectional link.
    // This is so hibernate will not discover the link from the persistent side
    // (assignment) and try to save the report before we want it to.
    private void updateReportAssignmentLink() {
        getAeRoutineReport().setAssignment(getAssignment());
    }

    /*
     * Will try to find serious AEs if found they will be reported as expedited.
     * An AE might be MedDRA or CTC based Rules should take that into consideration
     *
     * @see gov.nih.nci.cabig.caaers.web.ae.AdverseEventInputCommand#save()
     */
    public void save() {
        getAssignment().addRoutineReport(getAeRoutineReport());
        prepareExpeditedReport();
        boolean isExpedited = findExpedited(getAeRoutineReport());
        routineReportDao.save(getAeRoutineReport());

        if (isExpedited) {
			reportDao.save(this.aeReport);
		}

    }

    ////// BOUND PROPERTIES

    public ExpeditedAdverseEventReport getAeReport() {
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

	@SuppressWarnings("finally")
	public boolean findExpedited(RoutineAdverseEventReport raer ){
		log.debug("Checking for expedited AEs");
    	boolean isPopulated = false;
    	try {
    	for(AdverseEvent ae : raer.getAdverseEvents() )
    	{
    		String message = adverseEventEvaluationService.assesAdverseEvent(ae,study);
    		if (message.equals("SERIOUS_ADVERSE_EVENT")){
    			aeReport.addAdverseEvent(ae);
    			isPopulated = true;
    		}
    	}
    		return isPopulated;
    	}
    	catch(Exception e){
    		throw new RuntimeException("Class Not found Exception", e);
    	}
    	finally {
    		return isPopulated;
    	}
    }

    public void setAeReport(ExpeditedAdverseEventReport aeReport) {
        this.aeReport = aeReport;
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

    public String getTreatmentDescriptionType() {
    	// TODO Check if we need this for routine adverse events
    	return null;
    }
    public void setTreatmentDescriptionType(String type) {
    	// TODO Check if this is needed for routine adverse events

    }

}
