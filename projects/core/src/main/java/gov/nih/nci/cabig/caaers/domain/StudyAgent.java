package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Embedded;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;


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
public class StudyAgent extends AbstractMutableDomainObject {
	
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

    @Column(name="ind_identifier")
    public String getInvestigationalNewDrugIdentifier() {
		return investigationalNewDrugIdentifier;
	}

	public void setInvestigationalNewDrugIdentifier(
			String investigationalNewDrugIdentifier) {
		this.investigationalNewDrugIdentifier = investigationalNewDrugIdentifier;
	}
	
	@Column(name="ind_indicator")
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
    // We should never create new agents here, so no cascades
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
	
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		final StudyAgent that = (StudyAgent) o;

		if (investigationalNewDrugIdentifier != null ? !investigationalNewDrugIdentifier.equals(that.investigationalNewDrugIdentifier) : that.investigationalNewDrugIdentifier != null)
			return false;
		if (study != null ? !study.equals(that.study)
				: that.study != null)
			return false;
		if (agent != null ? !agent.equals(that.agent)
				: that.agent != null)
			return false;

		return true;
	}

	public int hashCode() {
		int result;
		result = (investigationalNewDrugIdentifier != null ? investigationalNewDrugIdentifier.hashCode() : 0);
		result = 29 * result
				+ (agent != null ? agent.hashCode() : 0);
		result = 29 * result + (study != null ? study.hashCode() : 0);
		return result;
	}
	

	
	
	
	
	
	

}
