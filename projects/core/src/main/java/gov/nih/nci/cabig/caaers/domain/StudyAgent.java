package gov.nih.nci.cabig.caaers.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Embedded;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


/**
 * @author Krikor Krumlian
 */

@Entity
@Table (name = "study_agents")
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="seq_study_agents_id")
    }
)
public class StudyAgent extends AbstractDomainObject{
	
	private Boolean investigationalNewDrugIndicator;
	private String investigationalNewDrugIdentifier;
	private Study study;
	private Agent agent;
	private String agentAsString;
	@Embedded
	private Participation participation;


	/*
	 * Constructor -- Initializes participation at create time 
	 * 
	 */
	public StudyAgent() {
		this.participation = new Participation(); 
	}
	
	public String getInvestigationalNewDrugIdentifier() {
		return investigationalNewDrugIdentifier;
	}

	public void setInvestigationalNewDrugIdentifier(
			String investigationalNewDrugIdentifier) {
		this.investigationalNewDrugIdentifier = investigationalNewDrugIdentifier;
	}
	
	public Boolean getInvestigationalNewDrugIndicator() {
		return investigationalNewDrugIndicator;
	}

	public void setInvestigationalNewDrugIndicator(
			Boolean investigationalNewDrugIndicator) {
		this.investigationalNewDrugIndicator = investigationalNewDrugIndicator;
	}
	
	@ManyToOne
    @JoinColumn(name = "study_id")
	public Study getStudy() {
		return study;
	}

	public void setStudy(Study study) {
		this.study = study;
	}
	
	@ManyToOne
    @JoinColumn(name = "agent_id")
    @Cascade(value = { CascadeType.ALL })
	public Agent getAgent() {
		return agent;
	}
	
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	
	@Transient
	public String getAgentAsString() {
		return agentAsString;
	}

	public void setAgentAsString(String agentAsString) {
		this.agentAsString = agentAsString;
	}
	
	public Participation getParticipation() {
		return participation;	}

	public void setParticipation(Participation participation) {
		this.participation = participation;
	}
	

	
	
	
	
	
	

}
