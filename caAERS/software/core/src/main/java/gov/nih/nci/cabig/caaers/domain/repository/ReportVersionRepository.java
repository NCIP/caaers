/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.dao.query.ReportVersionDTOQuery;
import gov.nih.nci.cabig.caaers.dao.report.ReportVersionDao;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersionDTO;
import gov.nih.nci.cabig.caaers.esb.client.ResponseMessageProcessor;
import gov.nih.nci.cabig.ctms.lang.NowFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


 
/*
* @author Ion C. Olaru
* 
* */
/**
 * The Class ReportVersionRepository.
 */
public class ReportVersionRepository {

    /** The report version dao. */
    private ReportVersionDao reportVersionDao;

    private ResponseMessageProcessor responseMessageProcessor;
    /**
     * Update in process reports.
     */
    @Transactional(readOnly = false)
    public void updateInProcessReports() {
        List<ReportVersion> rvs = reportVersionDao.getAllInProcessReports();
        NowFactory nowFactory = new NowFactory();
        for (ReportVersion rv : rvs) {
            Date submittedOrAmendedDate = null;
            if (rv.getAmendedOn() != null) {
                submittedOrAmendedDate = rv.getAmendedOn();
            } else if (rv.getSubmittedOn() != null) {
                submittedOrAmendedDate = rv.getSubmittedOn();
            }
            if (submittedOrAmendedDate != null) {
                long timeDiff = (nowFactory.getNowTimestamp().getTime() - rv.getSubmittedOn().getTime()) / 60000;
                if (timeDiff > 5) {
                    rv.setReportStatus(ReportStatus.FAILED);
                    rv.setSubmissionMessage("Submission failed for unknown reason , Please resubmit");
                    reportVersionDao.save(rv);
                    sendFailureXMLMessage(rv);
                }

            }
        }
    }


    /**
     * Will send a fake AdEERS failure message so that the post process like E2B ack etc will be sent out successfully.
     * @param reportVersion
     */
    private void sendFailureXMLMessage(ReportVersion reportVersion) {

        if(responseMessageProcessor == null) return;

        String responseXMLTemplate = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><soapenv:Body>" +
                "<submitAEDataXMLAsAttachmentResponse xmlns=\"http://types.ws.adeers.ctep.nci.nih.gov\">" +
                "<ns1:AEReportJobInfo xmlns:ns1=\"http://localhost:8080/AdEERSWSMap/services/AEReportXMLService\"><jobID xsi:type=\"xsd:string\" xmlns=\"\">0000</jobID>" +
                "<patientID xsi:type=\"xsd:string\" xmlns=\"\">${subjectId}</patientID><protocolNumber xsi:type=\"xsd:string\" xmlns=\"\">${protocolId}</protocolNumber>" +
                "<jobExceptions xmlns=\"\"><code>Error</code><description>Report is stuck at submission, so forcefully marking it as failed</description>" +
                "</jobExceptions><reportStatus xsi:nil=\"true\" xmlns=\"\"/><comments xsi:type=\"xsd:string\" xmlns=\"\">Report is stuck at submission, so forcefully marking it as failed</comments>" +
                "<CAEERS_AEREPORT_ID>${aeReportId}</CAEERS_AEREPORT_ID><REPORT_ID>${reportId}</REPORT_ID><SUBMITTER_EMAIL>${submitterEmail}</SUBMITTER_EMAIL><MESSAGE_COMBO_ID>${messageComboId}</MESSAGE_COMBO_ID>" +
                "</ns1:AEReportJobInfo></submitAEDataXMLAsAttachmentResponse>" +
                "</soapenv:Body></soapenv:Envelope>";
        Report report = reportVersion.getReport();
        Map<String, String> replacementMap = new HashMap<String, String>();
        replacementMap.put("${reportId}",  "" + report.getId());
        replacementMap.put("${aeReportId}",  "" + report.getAeReport().getId());
        replacementMap.put("${protocolId}",  report.getAeReport().getStudy().getPrimaryIdentifierValue());
        replacementMap.put("${subjectId}",   report.getAeReport().getParticipant().getPrimaryIdentifierValue());
        replacementMap.put("${messageComboId}",    report.getCaseNumber() + "::" + report.getCreatedOn().getTime());
        replacementMap.put("${submitterEmail}", report.getSubmitter().getEmailAddress());

        String xmlMessage =  responseXMLTemplate;
        for(Map.Entry<String, String> entry : replacementMap.entrySet()) {
           xmlMessage =  StringUtils.replace(xmlMessage, entry.getKey(), entry.getValue());
        }
        responseMessageProcessor.processMessage(xmlMessage);
    }


    /**
     * Gets the report activity.
     *
     * @return the report activity
     */
    public List<ReportVersionDTO> getReportActivity(Date startDate, Date endDate) {
        ReportVersionDTOQuery q = new ReportVersionDTOQuery();
        q.filterByDatesBetween(startDate, endDate);
        q.orderBy("coalesce(rv.dueOn, rv.submittedOn,rv.withdrawnOn)");
        
/*
        q.andWhere("rv.dueOn >= :dueOn");
        q.orWhere("rv.reportStatus = :status1");
        q.orWhere("rv.reportStatus = :status2");
        q.orWhere("rv.reportStatus = :status3");
        q.orWhere("rv.reportStatus = :status4");
        q.orWhere("rv.reportStatus = :status5");
        q.setParameter("status1", ReportStatus.INPROCESS);
        q.setParameter("status2", ReportStatus.PENDING);
        q.setParameter("status3", ReportStatus.WITHDRAWN);
        q.setParameter("status4", ReportStatus.AMENDED);
        q.setParameter("status5", ReportStatus.COMPLETED);
        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        q.setParameter("dueOn", today);
*/
        List<ReportVersionDTO> l = removeDuplicates(reportVersionDao.searchDTO(q));
        return l;
    }

    private List<ReportVersionDTO> removeDuplicates(List<ReportVersionDTO> l) {
        List<ReportVersionDTO> uniqueList = new ArrayList<ReportVersionDTO>();
        Set<Integer> idSet = new HashSet<Integer>();
        for (ReportVersionDTO rv : l) {
            if (idSet.add(rv.getRv().getId())) {
                uniqueList.add(rv);
            }
        }
        return uniqueList;
    }

    /**
     * Gets the all submitted reports in last given number of days.
     *
     * @param days the days
     * @return the all submitted reports in last given number of days
     */
    public List<ReportVersion> getAllSubmittedReportsInLastGivenNumberOfDays(int days) {
        return reportVersionDao.getAllSubmittedReportsInLastGivenNumberOfDays(days);
    }

    /**
     * Sets the report version dao.
     *
     * @param reportVersionDao the new report version dao
     */
    public void setReportVersionDao(ReportVersionDao reportVersionDao) {
        this.reportVersionDao = reportVersionDao;
    }

    /**
     * An AdeersSubmissionResponseMessageProcessor
     * @param responseMessageProcessor
     */
    public void setResponseMessageProcessor(ResponseMessageProcessor responseMessageProcessor) {
        this.responseMessageProcessor = responseMessageProcessor;
    }
}
