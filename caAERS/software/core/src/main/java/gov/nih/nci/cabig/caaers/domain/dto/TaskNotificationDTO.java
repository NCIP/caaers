/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.dto;

import gov.nih.nci.cabig.caaers.domain.ReportStatus;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

 
/*
* @author Ion C. Olaru
*
* */
/**
 * The Class TaskNotificationDTO.
 */
public class TaskNotificationDTO implements Serializable {

    /** The reporting period id. */
    private Integer reportingPeriodId;
    
    /** The description. */
    private String description;
    
    /** The status. */
    private String status;
    
    /** The task. */
    private String task;
    
    /** The subject full name. */
    private String subjectFullName;
    
    /** The primary study identifier. */
    private String primaryStudyIdentifier;
    
    /** The date. */
    private Date date;
    
    /** The possible actions. */
    private List<String> possibleActions;
    
    private Integer aeReportId;
    
//  could be AdverseEventReportingPeriod workflowId or Report workflowId
    
    public Integer getAeReportId() {
		return aeReportId;
	}

	public void setAeReportId(Integer aeReportId) {
		this.aeReportId = aeReportId;
	}

    // either 'report' or 'reportingPeriod'
    
    private String entityType;
    
    //  could be AdverseEventReportingPeriod Id or Report Id
    private Integer entityId;
    
	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public Integer getEntityId() {
		return entityId;
	}

	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}

	public ReportStatus getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(ReportStatus reportStatus) {
		this.reportStatus = reportStatus;
	}

	/** The status. */
	private ReportStatus reportStatus;

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the task.
     *
     * @return the task
     */
    public String getTask() {
        return task;
    }

    /**
     * Sets the task.
     *
     * @param task the new task
     */
    public void setTask(String task) {
        this.task = task;
    }

    /**
     * Gets the subject full name.
     *
     * @return the subject full name
     */
    public String getSubjectFullName() {
        return subjectFullName;
    }

    /**
     * Sets the subject full name.
     *
     * @param subjectFullName the new subject full name
     */
    public void setSubjectFullName(String subjectFullName) {
        this.subjectFullName = subjectFullName;
    }

    /**
     * Gets the date.
     *
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Gets the primary study identifier.
     *
     * @return primary study identifier
     */
    public String getPrimaryStudyIdentifier() {
		return primaryStudyIdentifier;
	}

    /**
     * Sets primary study identifier.
     *
     * @param primaryStudyIdentifier the primary study identifier
     */
	public void setPrimaryStudyIdentifier(String primaryStudyIdentifier) {
		this.primaryStudyIdentifier = primaryStudyIdentifier;
	}

    /**
     * Sets the date.
     *
     * @param date the new date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets the status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status the new status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the reporting period id.
     *
     * @return the reporting period id
     */
    public Integer getReportingPeriodId() {
        return reportingPeriodId;
    }

    /**
     * Sets the reporting period id.
     *
     * @param reportingPeriodId the new reporting period id
     */
    public void setReportingPeriodId(Integer reportingPeriodId) {
        this.reportingPeriodId = reportingPeriodId;
    }

    /**
     * Gets the possible actions.
     *
     * @return the possible actions
     */
    public List<String> getPossibleActions() {
        return possibleActions;
    }

    /**
     * Sets the possible actions.
     *
     * @param possibleActions the new possible actions
     */
    public void setPossibleActions(List<String> possibleActions) {
        this.possibleActions = possibleActions;
    }
}
