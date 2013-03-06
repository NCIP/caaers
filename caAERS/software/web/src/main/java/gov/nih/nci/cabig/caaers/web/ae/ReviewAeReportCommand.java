/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.domain.report.Report;

import java.util.List;
import java.util.Map;
import java.util.*;

/**
 * This is the command class for ReviewAeReportController
 * @author Sameer Sawant
 */

public class ReviewAeReportCommand extends EditExpeditedAdverseEventCommand{
	
	private Integer reportId;
	private ReportDao rpDao;
    private Map<String, List> pngFiles = new TreeMap<String,List>();

    public Map<String, List> getPngFiles() {
        return pngFiles;
    }

    public void setPngFiles(Map<String, List> pngFiles) {
        this.pngFiles = pngFiles;
    }

    public void addFiles(String aeReportId, String reportId, List files) {
        pngFiles.put(aeReportId + "_" + reportId, files);
    }

	public ReviewAeReportCommand(ExpeditedAdverseEventReportDao reportDao, ReportDao rpDao){
		super(reportDao);
		this.rpDao = rpDao;
	}

	public void setReportId(Integer reportId){
		this.reportId = reportId;
	}
	
	public Integer getReportId(){
		return reportId;
	}
}
