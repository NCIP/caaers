/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.dto;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.report.Report;

import java.util.ArrayList;
import java.util.List;
 

/**
 * The Class ManageReportsRepotingPeriodDTO.
 *
 * @author Ramakrishna Gundala
 */
public class ManageReportsRepotingPeriodDTO {
	private AdverseEventReportingPeriod adverseEventReportingPeriod;
	
	private Integer numberOfReports = 0;
	
	public ManageReportsRepotingPeriodDTO(
			AdverseEventReportingPeriod adverseEventReportingPeriod) {
		super();
		this.adverseEventReportingPeriod = adverseEventReportingPeriod;
	}

	private List<Report> reports = new ArrayList<Report>();

	public List<Report> getReports() {
		return reports;
	}

	public Integer getNumberOfReports() {
		return numberOfReports;
	}

	public void setNumberOfReports(Integer numberOfReports) {
		this.numberOfReports = numberOfReports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	public AdverseEventReportingPeriod getAdverseEventReportingPeriod() {
		return adverseEventReportingPeriod;
	}

	public void setAdverseEventReportingPeriod(
			AdverseEventReportingPeriod adverseEventReportingPeriod) {
		this.adverseEventReportingPeriod = adverseEventReportingPeriod;
	}
	
}
