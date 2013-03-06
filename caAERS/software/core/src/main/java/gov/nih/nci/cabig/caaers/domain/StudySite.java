/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.domain.workflow.StudySiteWorkflowConfig;
import gov.nih.nci.cabig.caaers.domain.workflow.WorkflowConfig;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.*;

 
/**
 * This class represents the StudySite domain object associated with the Adverse event report.
 * 
 * @author Krikor Krumlian
 * @author Kulasekaran
 * @author Biju Joseph
 */
@Entity
@DiscriminatorValue(value = "SST")
public class StudySite extends StudyOrganization {

    /** The study participant assignments. */
    private List<StudyParticipantAssignment> studyParticipantAssignments = new ArrayList<StudyParticipantAssignment>();
    
    /** The study site workflow configs. */
    private List<StudySiteWorkflowConfig> studySiteWorkflowConfigs;
    
    // TODO : to be removed.
    /** The irb approval date. */
    private Date irbApprovalDate;

    
    // ////LOGIC
    
    /**
     * Adds the study site workflow config.
     *
     * @param studySiteWorkflowConfig the study site workflow config
     */
    public void addStudySiteWorkflowConfig(StudySiteWorkflowConfig studySiteWorkflowConfig){
    	getStudySiteWorkflowConfigs().add(studySiteWorkflowConfig);
    	studySiteWorkflowConfig.setStudySite(this);
    }

    /**
     * Adds the assignment.
     *
     * @param assignment the assignment
     */
    public void addAssignment(StudyParticipantAssignment assignment) {
        getStudyParticipantAssignments().add(assignment);
        assignment.setStudySite(this);
    }

    /**
     * Are there any assignments using this relationship?.
     *
     * @return true, if is used
     */
    @Transient
    public boolean isUsed() {
        return getStudyParticipantAssignments().size() > 0;
    }

    /**
     * Gets the site study names.
     *
     * @return the site study names
     */
    @Transient
    public String getSiteStudyNames() {
        return getStudy().getShortTitle() + " : " + getOrganization().getName();
    }

    // / BEAN PROPERTIES

    /**
     * Sets the study participant assignments.
     *
     * @param studyParticipantAssignments the new study participant assignments
     */
    public void setStudyParticipantAssignments(List<StudyParticipantAssignment> studyParticipantAssignments) {
        this.studyParticipantAssignments = studyParticipantAssignments;
    }

    /**
     * Gets the study participant assignments.
     *
     * @return the study participant assignments
     */
    @OneToMany(mappedBy = "studySite")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<StudyParticipantAssignment> getStudyParticipantAssignments() {
        return studyParticipantAssignments;
    }


    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.StudyOrganization#getRoleName()
     */
    @Override
    @Transient
    public String getRoleName() {
        return "Site";
    }
    
   

    /**
     * Gets the study site workflow configs.
     *
     * @return the study site workflow configs
     */
    @OneToMany(mappedBy = "studySite")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
	public List<StudySiteWorkflowConfig> getStudySiteWorkflowConfigs() {
    	if(studySiteWorkflowConfigs == null) studySiteWorkflowConfigs = new ArrayList<StudySiteWorkflowConfig>();
		return studySiteWorkflowConfigs;
	}

	/**
	 * Sets the study site workflow configs.
	 *
	 * @param studySiteWorkflowConfigs the new study site workflow configs
	 */
	public void setStudySiteWorkflowConfigs(List<StudySiteWorkflowConfig> studySiteWorkflowConfigs) {
		this.studySiteWorkflowConfigs = studySiteWorkflowConfigs;
	}
    
	/**
	 * Gets the reporting period workflow config.
	 *
	 * @return the reporting period workflow config
	 */
	@Transient
    public WorkflowConfig getReportingPeriodWorkflowConfig(){
		return findWorkflowConfig("reportingPeriod");
    }
	
	
	/**
	 * Gets the report workflow config.
	 *
	 * @return the report workflow config
	 */
	@Transient
	public WorkflowConfig getReportWorkflowConfig(){
		return findWorkflowConfig("report");
	}
	
	/**
	 * Find workflow config.
	 *
	 * @param name the name
	 * @return the workflow config
	 */
	public WorkflowConfig findWorkflowConfig(String name){
		for(StudySiteWorkflowConfig ssWfCfg : getStudySiteWorkflowConfigs()){
    		if(StringUtils.equals(name, ssWfCfg.getName())) return ssWfCfg.getWorkflowConfig();
    	}
		return null;
	}
	
}
