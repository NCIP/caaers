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
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.annotations.MapKey;

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

    private List<StudyParticipantAssignment> studyParticipantAssignments = new ArrayList<StudyParticipantAssignment>();
    
    private List<StudySiteWorkflowConfig> studySiteWorkflowConfigs;
    
    // TODO : to be removed.
    private Date irbApprovalDate;

    
    // ////LOGIC
    
    public void addStudySiteWorkflowConfig(StudySiteWorkflowConfig studySiteWorkflowConfig){
    	getStudySiteWorkflowConfigs().add(studySiteWorkflowConfig);
    	studySiteWorkflowConfig.setStudySite(this);
    }

    public void addAssignment(StudyParticipantAssignment assignment) {
        getStudyParticipantAssignments().add(assignment);
        assignment.setStudySite(this);
    }

    /** Are there any assignments using this relationship? */
    @Transient
    public boolean isUsed() {
        return getStudyParticipantAssignments().size() > 0;
    }

    @Transient
    public String getSiteStudyNames() {
        return getStudy().getShortTitle() + " : " + getOrganization().getName();
    }

    // / BEAN PROPERTIES

    public void setStudyParticipantAssignments(List<StudyParticipantAssignment> studyParticipantAssignments) {
        this.studyParticipantAssignments = studyParticipantAssignments;
    }

    @OneToMany(mappedBy = "studySite")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    public List<StudyParticipantAssignment> getStudyParticipantAssignments() {
        return studyParticipantAssignments;
    }


    @Override
    @Transient
    public String getRoleName() {
        return "Site";
    }
    
   

    @OneToMany(mappedBy = "studySite")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	public List<StudySiteWorkflowConfig> getStudySiteWorkflowConfigs() {
    	if(studySiteWorkflowConfigs == null) studySiteWorkflowConfigs = new ArrayList<StudySiteWorkflowConfig>();
		return studySiteWorkflowConfigs;
	}

	public void setStudySiteWorkflowConfigs(List<StudySiteWorkflowConfig> studySiteWorkflowConfigs) {
		this.studySiteWorkflowConfigs = studySiteWorkflowConfigs;
	}
    
	@Transient
    public WorkflowConfig getReportingPeriodWorkflowConfig(){
		return findWorkflowConfig("reportingPeriod");
    }
	
	
	@Transient
	public WorkflowConfig getReportWorkflowConfig(){
		return findWorkflowConfig("report");
	}
	
	public WorkflowConfig findWorkflowConfig(String name){
		for(StudySiteWorkflowConfig ssWfCfg : getStudySiteWorkflowConfigs()){
    		if(StringUtils.equals(name, ssWfCfg.getName())) return ssWfCfg.getWorkflowConfig();
    	}
		return null;
	}
	
}
