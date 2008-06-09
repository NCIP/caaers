package gov.nih.nci.cabig.caaers.web.ae;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.tools.spring.tabbedflow.AutomaticSaveAjaxableFormController;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

public class CaptureAdverseEventController extends AutomaticSaveAjaxableFormController<CaptureAdverseEventInputCommand, AdverseEventReportingPeriod, AdverseEventReportingPeriodDao> {
	
	private ParticipantDao participantDao;
	private StudyDao studyDao;
	private StudyParticipantAssignmentDao assignmentDao;
	
	@Override
	protected AdverseEventReportingPeriodDao getDao() {
		return null;
	}

	@Override
	protected AdverseEventReportingPeriod getPrimaryDomainObject(CaptureAdverseEventInputCommand cmd) {
		//TODO should be refined.
		return new AdverseEventReportingPeriod();
	}

	@Override
	protected ModelAndView processFinish(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, BindException arg3) throws Exception {
		return null;
	}
	
	@Override
	protected void initBinder(HttpServletRequest request,ServletRequestDataBinder binder) throws Exception {
		ControllerTools.registerDomainObjectEditor(binder, "participant", participantDao);
        ControllerTools.registerDomainObjectEditor(binder, "study", studyDao);
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request)	throws Exception {
		CaptureAdverseEventInputCommand cmd = new CaptureAdverseEventInputCommand(assignmentDao);
		
		return cmd;
	}
	
	@Override
	public FlowFactory<CaptureAdverseEventInputCommand> getFlowFactory() {
		return new FlowFactory<CaptureAdverseEventInputCommand>() {
			public Flow<CaptureAdverseEventInputCommand> createFlow(CaptureAdverseEventInputCommand cmd) {
				Flow<CaptureAdverseEventInputCommand> flow = new Flow<CaptureAdverseEventInputCommand>("CaptureAdverseEventFlow");
				flow.addTab(new BeginTab<CaptureAdverseEventInputCommand>());
				flow.addTab(new AdverseEventCaptureTab());
				return flow;
			}
		};
	}
	
	@Override
	protected boolean shouldSave(HttpServletRequest request,CaptureAdverseEventInputCommand command,Tab<CaptureAdverseEventInputCommand> tab) {
		return false;
	}

	public ParticipantDao getParticipantDao() {
		return participantDao;
	}

	public void setParticipantDao(ParticipantDao participantDao) {
		this.participantDao = participantDao;
	}

	public StudyDao getStudyDao() {
		return studyDao;
	}

	public void setStudyDao(StudyDao studyDao) {
		this.studyDao = studyDao;
	}
	public void setAssignmentDao(StudyParticipantAssignmentDao assignmentDao){
		this.assignmentDao = assignmentDao;
	}
	
 
}
