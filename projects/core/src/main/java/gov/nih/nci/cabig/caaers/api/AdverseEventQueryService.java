package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.report.Report;

import java.util.List;

/**
 * 
 * @author sakkala
 * This is a Query API to query Adverse Events based on different Criterion
 *
 */
public interface AdverseEventQueryService {
		
	/**
	 * Search for Adverse Events for a given participant
	 * @param participant
	 * @return
	 */
	public List<AdverseEvent> getByParticipant(Participant participant) ;

	
	/**
	 * Search for Adverse Events for a given participant and adverseEvent
	 * @param participant
	 * @param adverseEvent
	 * @return
	 */

	public List<AdverseEvent> getByParticipant(Participant participant, AdverseEvent adverseEvent);

	/**
	 * Search for Adverse Events based on a given Study
	 * @param study
	 * @return
	 */
	public List<AdverseEvent> getByStudy(Study study);
	
	/**
	 * Search for Adverse Events based on a given Study and adverseEvent
	 * @param study
	 * @param adverseEvent
	 * @return
	 */	
	public List<AdverseEvent> getByStudy(Study study, AdverseEvent adverseEvent);
	

	/**
	 * Search for Adverse Events based on a given Study , Participant
	 * @param study
	 * @parm participant
	 * @return
	 */	
	public List<AdverseEvent> getByStudyParticipant(Study study , Participant participant);

	/**
	 * Search for Adverse Events based on a given Study , Participant and adverseEvent
	 * @param study
	 * @parm participant
	 * @param adverseEvent
	 * @return
	 */	
	public List<AdverseEvent> getByStudyParticipant(Study study , Participant participant, AdverseEvent adverseEvent);
	
	/**
	 * Search for Adverse Events based on a given Report
	 * @param report
	 * @return
	 */
	public List<AdverseEvent> getByReport(Report report);
	
	/**
	 * Search for Adverse Events based on a given Report and adverseEvent
	 * @param report
	 * @param adverseEvent
	 * @return
	 */
	public List<AdverseEvent> getByReport(Report report, AdverseEvent adverseEvent);
	
	/**
	 * Search for AdverseEvents based on a given ReportingPeriod, Study, Participant
	 * @param Study
	 * @param Participant
	 * @param ReportingPeriod
	 */
	public List<AdverseEvent> getByAdverseEventReportingPeriod(Study study, Participant participant, AdverseEventReportingPeriod adverseEventReportingPeriod);
	
	/**
	 * Search for AdverseEvents based on a given ReportingPeriod, Study, Participant and AdverseEvent
	 * @param study
	 * @param participant
	 * @param reportingPeriod
	 * @param adverseEvent
	 * @return
	 */
	public List<AdverseEvent> getByAdverseEventReportingPeriod(Study study, Participant participant, AdverseEventReportingPeriod adverseEventReportingPeriod, AdverseEvent adverseEvent);
	
	/**
	 * Convert result AEs to XML
	 * @param adverseEvents
	 * @return
	 */
	public String getXML(List<AdverseEvent> adverseEvents) throws Exception ;
	
	/**
	 * transform from xml to plain text
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public String getText(String xml) throws Exception ;
	
	//public List<AdverseEvent> getByStudyParticipantAssignment(StudyParticipantAssignment studyParticipantAssignment);
		


}
