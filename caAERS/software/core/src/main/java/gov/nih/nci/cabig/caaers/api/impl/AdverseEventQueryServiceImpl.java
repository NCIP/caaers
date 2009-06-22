package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.api.AdverseEventQueryService;
import gov.nih.nci.cabig.caaers.api.AdverseEventSerializer;
import gov.nih.nci.cabig.caaers.dao.AdverseEventDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.utils.XsltTransformer;

import java.util.List;


public class AdverseEventQueryServiceImpl implements AdverseEventQueryService {

	private AdverseEventDao adverseEventDao;
	
	public List<AdverseEvent> getByParticipant(Participant participant) {
		return adverseEventDao.getByParticipant(participant);
		
	}

	public List<AdverseEvent> getByParticipant(Participant participant, AdverseEvent adverseEvent){
		return adverseEventDao.getByParticipant(participant,adverseEvent);
	}

	
	public List<AdverseEvent> getByStudy(Study study) {
		return adverseEventDao.getByStudy(study);
	}

	public List<AdverseEvent> getByStudy(Study study, AdverseEvent adverseEvent) {
		return adverseEventDao.getByStudy(study, adverseEvent);
	}

	public List<AdverseEvent> getByStudyParticipant(Study study , Participant participant, AdverseEvent adverseEvent) {
		return adverseEventDao.getByStudyParticipant(study, participant, adverseEvent);
	}

	public List<AdverseEvent> getByStudyParticipant(Study study , Participant participant) {
		return adverseEventDao.getByStudyParticipant(study, participant);
	}
	
	public List<AdverseEvent> getByReport(Report report){
		return adverseEventDao.getByReport(report);
	}
	
	public List<AdverseEvent> getByReport(Report report, AdverseEvent adverseEvent){
		return adverseEventDao.getByReport(report, adverseEvent);
	}
	
	public List<AdverseEvent> getByAdverseEventReportingPeriod(Study study, Participant participant, AdverseEventReportingPeriod adverseEventReportingPeriod){
		return adverseEventDao.getByAdverseEventReportingPeriod(adverseEventReportingPeriod, study, participant);
	}
	
	public List<AdverseEvent> getByAdverseEventReportingPeriod(Study study, Participant participant, AdverseEventReportingPeriod adverseEventReportingPeriod, AdverseEvent adverseEvent){
		return adverseEventDao.getByAdverseEventReportingPeriod(adverseEventReportingPeriod, study, participant, adverseEvent);
	}
	
	public void setAdverseEventDao(AdverseEventDao adverseEventDao) {
		this.adverseEventDao = adverseEventDao;
	}

	public String getXML(List<AdverseEvent> adverseEvents) throws Exception {
		// TODO Auto-generated method stub
		AdverseEventSerializer aes = new AdverseEventSerializer();
		
		StringBuilder aeList = new StringBuilder();
		aeList.append("<AdverseEvents>");
		for (AdverseEvent ae:adverseEvents) {
			System.out.println(ae.getId());
			aeList.append(aes.serialize(ae));
		}
		aeList.append("</AdverseEvents>");
		return aeList.toString();
	}
	
	public String getText(String xml) throws Exception  {
		String xsltFile = "xslt/AdverseEvent-xml-text.xslt";
		XsltTransformer xsltTransformer = new XsltTransformer();
		return xsltTransformer.toText(xml, xsltFile);
	}
	
}
