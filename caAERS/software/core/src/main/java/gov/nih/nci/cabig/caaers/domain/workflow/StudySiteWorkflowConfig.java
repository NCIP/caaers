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
 * This class associates {@link StudySite} and {@link WorkflowConfig}.
 *
 * @author Biju Joseph
 */
@Entity
@Table(name = "study_site_wf_cfgs")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_study_site_wf_cfgs_id")})
public class StudySiteWorkflowConfig extends AbstractMutableDomainObject{
	
	/** The name. */
	private String name;
	
	/** The study site. */
	private StudySite studySite;
	
	/** The workflow config. */
	private WorkflowConfig workflowConfig;
	
	
	/**
	 * Instantiates a new study site workflow config.
	 */
	public StudySiteWorkflowConfig() {
		this(null, null, null);
	}
	
	/**
	 * Instantiates a new study site workflow config.
	 *
	 * @param name the name
	 * @param site the site
	 * @param workflowConfig the workflow config
	 */
	public StudySiteWorkflowConfig(String name, StudySite site, WorkflowConfig workflowConfig){
		this.name = name;
		this.studySite = site;
		this.workflowConfig = workflowConfig;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the study site.
	 *
	 * @return the study site
	 */
	@ManyToOne
    @JoinColumn(name = "study_organization_id", nullable=false)
	public StudySite getStudySite() {
		return studySite;
	}
	
	/**
	 * Sets the study site.
	 *
	 * @param studySite the new study site
	 */
	public void setStudySite(StudySite studySite) {
		this.studySite = studySite;
	}
	
	/**
	 * Gets the workflow config.
	 *
	 * @return the workflow config
	 */
	@ManyToOne
    @JoinColumn(name = "workflow_cfg_id", nullable=false)
	public WorkflowConfig getWorkflowConfig() {
		return workflowConfig;
	}
	
	/**
	 * Sets the workflow config.
	 *
	 * @param workflowConfig the new workflow config
	 */
	public void setWorkflowConfig(WorkflowConfig workflowConfig) {
		this.workflowConfig = workflowConfig;
	}
	
}
