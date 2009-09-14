package gov.nih.nci.cabig.caaers.domain.workflow;

import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
/**
 * This class associates {@link StudySite} and {@link WorkflowConfig}
 * 
 * @author Biju Joseph
 *
 */
@Entity
@Table(name = "study_site_wf_cfgs")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_study_site_wf_cfgs_id")})
public class StudySiteWorkflowConfig extends AbstractMutableDomainObject{
	
	private String name;
	private StudySite studySite;
	private WorkflowConfig workflowConfig;
	
	
	public StudySiteWorkflowConfig() {
		this(null, null, null);
	}
	
	public StudySiteWorkflowConfig(String name, StudySite site, WorkflowConfig workflowConfig){
		this.name = name;
		this.studySite = site;
		this.workflowConfig = workflowConfig;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne
    @JoinColumn(name = "study_organization_id", nullable=false)
	public StudySite getStudySite() {
		return studySite;
	}
	public void setStudySite(StudySite studySite) {
		this.studySite = studySite;
	}
	
	@ManyToOne
    @JoinColumn(name = "workflow_cfg_id", nullable=false)
	public WorkflowConfig getWorkflowConfig() {
		return workflowConfig;
	}
	public void setWorkflowConfig(WorkflowConfig workflowConfig) {
		this.workflowConfig = workflowConfig;
	}
	
}
