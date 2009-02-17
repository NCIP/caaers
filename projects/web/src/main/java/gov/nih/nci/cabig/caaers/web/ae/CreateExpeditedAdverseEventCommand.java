package gov.nih.nci.cabig.caaers.web.ae;

import java.util.ArrayList;
import java.util.Map;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.DiseaseCodeTerm;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepository;
import gov.nih.nci.cabig.caaers.web.RenderDecisionManager;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;

/**
 *
 * @author Sameer Sawant
 */
public class CreateExpeditedAdverseEventCommand extends AbstractExpeditedAdverseEventInputCommand {
	
	private Study study;
	private AdverseEventReportingPeriod adverseEventReportingPeriod;
	private Participant participant;
	@Override
    public void reassociate() {
        //super.reassociate();
        //assignmentDao.reassociate(getAssignment());
    }
	
	public CreateExpeditedAdverseEventCommand(ExpeditedAdverseEventReportDao reportDao, ReportDefinitionDao reportDefinitionDao, 
			AdverseEventReportingPeriodDao reportingPeriodDao, ExpeditedReportTree expeditedReportTree) {
		super();
		this.reportingPeriodDao = reportingPeriodDao;
        this.reportDao = reportDao;
        this.reportDefinitionDao = reportDefinitionDao;
        this.expeditedReportTree = expeditedReportTree;
        this.outcomeOtherDetails = new ArrayList<String>();
        this.outcomes = new ArrayList<Map<Integer,Boolean>>();
        this.selectedReportDefinitions = new ArrayList<ReportDefinition>();
        
	}
	
	
	
	public Map<Object, Object> getStudyDiseasesOptions(DiseaseCodeTerm diseaseCodingTerm){
		return null;
    }
	
	public Term getStudyTerminologyTerm() {
		return null;
	}
	
	@Override
    public void save() {
        //reportDao.save(getAeReport());
    }
	
	@Override
    public Participant getParticipant() {
		return participant;
    }
	
	@Override
    public Study getStudy() {
		return study;
    }

	@Override
    public StudyParticipantAssignment getAssignment() {
		if(getAeReport() != null)
			return getAeReport().getAssignment();
		return null;
    }
	
	public void setStudy(Study study){
		this.study = study;
	}
	
	public void setParticipant(Participant participant){
		this.participant = participant;
	}
	
	public AdverseEventReportingPeriod getAdverseEventReportingPeriod(){
		return adverseEventReportingPeriod;
	}
	
	public void setAdverseEventReportingPeriod(AdverseEventReportingPeriod adverseEventReportingPeriod){
		this.adverseEventReportingPeriod = adverseEventReportingPeriod;
	}
	
	@Override
    public void flush() {
    	reportDao.flush();
    }
	
	@Override
	public boolean isAdditionAllowed() {
		return true;
	}
	
	public void saveReportingPeriod() {
		if(adverseEventReportingPeriod != null)
			reportingPeriodDao.save(adverseEventReportingPeriod);
		
	}
}