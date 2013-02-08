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
    @OneToMany(mappedBy = "studySite", orphanRemoval = true)
    @Cascade(value = { CascadeType.ALL })
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
    @OneToMany(mappedBy = "studySite", orphanRemoval = true)
    @Cascade(value = { CascadeType.ALL  })
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

	@Transient
	public SiteInvestigator findSiteInvestigatorByIdentifier(String identifier) {
		for (StudyInvestigator investigator: this.getActiveStudyInvestigators()) {
			if ( investigator.getSiteInvestigator() != null && investigator.getSiteInvestigator().getInvestigator() != null) {
				if ( identifier.equals(investigator.getSiteInvestigator().getInvestigator().getPrimaryIdentifierValue())) {
					// Found a match and return the Investigator Object.
					return investigator.getSiteInvestigator();
				}
			}
		}
		return null;
	}
	
	@Transient
	public SiteInvestigator findSiteInvestigatorByEmail(String email) {
		for (StudyInvestigator investigator: this.getActiveStudyInvestigators()) {
			if ( investigator.getSiteInvestigator() != null) {
				if ( email.equals(investigator.getSiteInvestigator().getEmailAddress())) {
					// Found a match and return the Investigator Object.
					return investigator.getSiteInvestigator();
				}
			}
		}
		return null;
	}
	
	@Transient
	public SiteInvestigator findSiteInvestigatorByName(String fName,String lName) {
		for (StudyInvestigator investigator: this.getActiveStudyInvestigators()) {
			if ( investigator.getSiteInvestigator() != null) {
				if ( fName.equals(investigator.getSiteInvestigator().getFirstName()) && lName.equals(investigator.getSiteInvestigator().getLastName())) {
					// Found a match and return the Investigator Object.
					return investigator.getSiteInvestigator();
				}
			}
		}
		return null;
	}
	
	@Transient
	public SiteResearchStaff findSiteResearchStaffByIdentifier(String identifier) {
		for (StudyPersonnel rs: this.getActiveStudyPersonnel()) {
			if ( rs.getSiteResearchStaff() != null && rs.getSiteResearchStaff().getResearchStaff() != null && rs.getSiteResearchStaff().getResearchStaff().getPrimaryIdentifierValue() != null) {
				if ( identifier.equals(rs.getSiteResearchStaff().getResearchStaff().getPrimaryIdentifierValue())) {
					// Found a match and return the Investigator Object.
					return rs.getSiteResearchStaff();
				}
			}
		}
		return null;
	}
	
	@Transient
	public SiteResearchStaff findSiteResearchStaffByEmail(String email) {
		for (StudyPersonnel rs: this.getActiveStudyPersonnel()) {
			if ( rs.getSiteResearchStaff() != null && rs.getSiteResearchStaff().getResearchStaff() != null) {
				if ( email.equals(rs.getSiteResearchStaff().getEmailAddress())) {
					// Found a match and return the Investigator Object.
					return rs.getSiteResearchStaff();
				}
			}
		}
		return null;
	}
	
	@Transient
	public SiteResearchStaff findSiteResearchStaffByName(String firstName,String lastName) {
		for (StudyPersonnel rs: this.getActiveStudyPersonnel()) {
			if ( rs.getSiteResearchStaff() != null && rs.getSiteResearchStaff().getResearchStaff() != null) {
				if ( (firstName.equals(rs.getSiteResearchStaff().getFirstName()) && lastName.equals(rs.getSiteResearchStaff().getLastName()) )) {
					// Found a match and return the Investigator Object.
					return rs.getSiteResearchStaff();
				}
			}
		}
		return null;
	}

}
