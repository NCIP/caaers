package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

 
/**
 * This class represents the Submitter domain object associated with the Adverse event report.
 * 
 * @author Krikor Krumlian
 */
@Entity
@DiscriminatorValue("S")
public class Submitter extends ReportPerson {
	
	/** The report version. */
	private ReportVersion reportVersion;
	
	/**
	 * Gets the report version.
	 *
	 * @return the report version
	 */
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "report_version_id")
	public ReportVersion getReportVersion() {
		return reportVersion;
	}
	
	/**
	 * Sets the report version.
	 *
	 * @param reportVersion the new report version
	 */
	public void setReportVersion(ReportVersion reportVersion) {
		this.reportVersion = reportVersion;
	}

}
