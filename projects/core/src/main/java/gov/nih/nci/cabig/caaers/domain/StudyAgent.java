package gov.nih.nci.cabig.caaers.domain;

import java.util.List;

import gov.nih.nci.cabig.ctms.collections.LazyListHelper;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class StudyAgent extends AbstractMutableDomainObject implements StudyChild{

	private LazyListHelper lazyListHelper;
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
		participation = new Participation();
		lazyListHelper = new LazyListHelper();
		lazyListHelper.add(StudyAgentINDAssociation.class, new StudyAgentChildInstantiateFactory<StudyAgentINDAssociation>(this,StudyAgentINDAssociation.class));
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

	@OneToMany(mappedBy="studyAgent", fetch=FetchType.EAGER)
	 @Cascade({ CascadeType.ALL,CascadeType.DELETE_ORPHAN})
	public List<StudyAgentINDAssociation> getStudyAgentINDAssociationsInternal() {
		return lazyListHelper.getInternalList(StudyAgentINDAssociation.class);
	}

	public void setStudyAgentINDAssociationsInternal(
			List<StudyAgentINDAssociation> studyAgentINDAssociations) {
		lazyListHelper.setInternalList(StudyAgentINDAssociation.class, studyAgentINDAssociations);

	}

	@Transient
	public List<StudyAgentINDAssociation> getStudyAgentINDAssociations() {
		return lazyListHelper.getLazyList(StudyAgentINDAssociation.class);
	}
	@Transient
	public void setStudyAgentINDAssociations(
			List<StudyAgentINDAssociation> studyAgentINDAssociations) {
		setStudyAgentINDAssociationsInternal(studyAgentINDAssociations);
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



	@Transient
    public boolean getInvestigationalNewDrugIndicator(){
    	return (getStudyAgentINDAssociations() != null) &&
    	 getStudyAgentINDAssociations().size() > 0;
    }

	@Transient
	public void addStudyAgentINDAssociation(StudyAgentINDAssociation ass){
		getStudyAgentINDAssociations().add(ass);
		ass.setStudyAgent(this);
	}

}
