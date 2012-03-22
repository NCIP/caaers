package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import gov.nih.nci.cabig.ctms.domain.DomainObject;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author Ion C. Olaru
 *         Date: 1/5/12 -1:45 PM
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "study_interventions_exp_aes")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@org.hibernate.annotations.Parameter(name = "sequence", value = "seq_si_aes_id")})
@DiscriminatorColumn(name = "term_type", discriminatorType = DiscriminatorType.STRING)
public abstract class AbstractStudyInterventionExpectedAE<T extends DomainObject> extends AbstractMutableDomainObject implements AbstractTerm  {

    private T term;

    private List<TreatmentAssignmentAgent> treatmentAssignmentAgents = new ArrayList<TreatmentAssignmentAgent>();
    private Double expectednessFrequency;
    private Double grade1Frequency;
    private Double grade2Frequency;
    private Double grade3Frequency;
    private Double grade4Frequency;
    private Double grade5Frequency;
    /** The expected. */
    private boolean expected = true;

    public AbstractStudyInterventionExpectedAE(TreatmentAssignmentAgent treatmentAssignmentAgent, AgentSpecificTerm agentSpecificTerm){
    	this.treatmentAssignmentAgents.add(treatmentAssignmentAgent);
    	this.term = (T)agentSpecificTerm.getTerm();
    	this.expected = agentSpecificTerm.isExpected();
    	this.expectednessFrequency = agentSpecificTerm.getExpectednessFrequency();
    	this.grade1Frequency = agentSpecificTerm.getGrade1Frequency();
    	this.grade2Frequency = agentSpecificTerm.getGrade2Frequency();
    	this.grade3Frequency = agentSpecificTerm.getGrade3Frequency();
    	this.grade4Frequency = agentSpecificTerm.getGrade4Frequency();
    	this.grade5Frequency = agentSpecificTerm.getGrade5Frequency();
    }
    
	public AbstractStudyInterventionExpectedAE() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Double getExpectednessFrequency() {
        return expectednessFrequency;
    }

    public void setExpectednessFrequency(Double expectednessFrequency) {
        this.expectednessFrequency = expectednessFrequency;
    }

    public Double getGrade1Frequency() {
        return grade1Frequency;
    }

    public void setGrade1Frequency(Double grade1Frequency) {
        this.grade1Frequency = grade1Frequency;
    }

    public Double getGrade2Frequency() {
        return grade2Frequency;
    }

    public void setGrade2Frequency(Double grade2Frequency) {
        this.grade2Frequency = grade2Frequency;
    }

    public Double getGrade3Frequency() {
        return grade3Frequency;
    }

    public void setGrade3Frequency(Double grade3Frequency) {
        this.grade3Frequency = grade3Frequency;
    }

    public Double getGrade4Frequency() {
        return grade4Frequency;
    }

    public void setGrade4Frequency(Double grade4Frequency) {
        this.grade4Frequency = grade4Frequency;
    }

    public Double getGrade5Frequency() {
        return grade5Frequency;
    }

    public void setGrade5Frequency(Double grade5Frequency) {
        this.grade5Frequency = grade5Frequency;
    }

    
    @Transient
    public T getTerm() {
        return term;
    }

    public void setTerm(T term) {
        this.term = term;
    }

	public boolean isExpected() {
		return expected;
	}

	public void setExpected(boolean expected) {
		this.expected = expected;
	}
	
	/**
     * Checks if is med dra.
     *
     * @return true, if is med dra
     */
    @Transient
    public abstract boolean isMedDRA();
    
    /**
     * Gets the full name.
     *
     * @return the full name
     */
    @Transient
    public abstract String getFullName();

    @ManyToMany
    @JoinTable(
        name="ta_expected_ae_intervention",
        joinColumns=@JoinColumn(name="ta_expected_id"),
        inverseJoinColumns=@JoinColumn(name="ta_agent_id")
    )
    @Cascade(value = {CascadeType.LOCK})
	public List<TreatmentAssignmentAgent> getTreatmentAssignmentAgents() {
		return treatmentAssignmentAgents;
	}

	public void setTreatmentAssignmentAgents(
			List<TreatmentAssignmentAgent> treatmentAssignmentAgents) {
		this.treatmentAssignmentAgents = treatmentAssignmentAgents;
	}
	
	public void recalculateExpectedness(AgentSpecificTerm agentSpecificTerm, boolean expected){
		//Run the general addition rule: P(A or B) = P(A) + P(B) - P(A * B)
		if(this.grade1Frequency!=null && agentSpecificTerm.getGrade1Frequency()!=null){
			this.grade1Frequency = (this.grade1Frequency/100 + agentSpecificTerm.getGrade1Frequency()/100 - this.grade1Frequency*agentSpecificTerm.getGrade1Frequency()/(100*100)) * 100;
		}else if(agentSpecificTerm.getGrade1Frequency()!=null){
			this.grade1Frequency = agentSpecificTerm.getGrade1Frequency();
		}
		if(this.grade2Frequency!=null && agentSpecificTerm.getGrade2Frequency()!=null){
			this.grade2Frequency = (this.grade2Frequency/100 + agentSpecificTerm.getGrade2Frequency()/100 - this.grade2Frequency*agentSpecificTerm.getGrade2Frequency()/(100*100)) * 100;
		}else if(agentSpecificTerm.getGrade2Frequency()!=null){
			this.grade2Frequency = agentSpecificTerm.getGrade2Frequency();
		}
		if(this.grade3Frequency!=null && agentSpecificTerm.getGrade3Frequency()!=null){
			this.grade3Frequency = (this.grade3Frequency/100 + agentSpecificTerm.getGrade3Frequency()/100 - this.grade3Frequency*agentSpecificTerm.getGrade3Frequency()/(100*100)) * 100;
		}else if(agentSpecificTerm.getGrade3Frequency()!=null){
			this.grade3Frequency = agentSpecificTerm.getGrade3Frequency();
		}
		if(this.grade4Frequency!=null && agentSpecificTerm.getGrade4Frequency()!=null){
			this.grade4Frequency = (this.grade4Frequency/100 + agentSpecificTerm.getGrade4Frequency()/100 - this.grade4Frequency*agentSpecificTerm.getGrade4Frequency()/(100*100)) * 100;
		}else if(agentSpecificTerm.getGrade4Frequency()!=null){
			this.grade4Frequency = agentSpecificTerm.getGrade4Frequency();
		}
		if(this.grade5Frequency!=null && agentSpecificTerm.getGrade5Frequency()!=null){
			this.grade5Frequency = (this.grade5Frequency/100 + agentSpecificTerm.getGrade5Frequency()/100 - this.grade5Frequency*agentSpecificTerm.getGrade5Frequency()/(100*100)) * 100;
		}else if(agentSpecificTerm.getGrade5Frequency()!=null){
			this.grade5Frequency = agentSpecificTerm.getGrade5Frequency();
		}
		if(this.expectednessFrequency!=null && agentSpecificTerm.getExpectednessFrequency()!=null){
			this.expectednessFrequency = (this.expectednessFrequency/100 + agentSpecificTerm.getExpectednessFrequency()/100 - this.expectednessFrequency*agentSpecificTerm.getExpectednessFrequency()/(100*100)) * 100;
		}else if(agentSpecificTerm.getExpectednessFrequency()!=null){
			this.expectednessFrequency = agentSpecificTerm.getExpectednessFrequency();
		}
		this.expected = this.expected || expected;
	}
	
	public void resetAndRecalculateExpectedness(){
		//resetExpectedness
		this.expected = false;
    	this.expectednessFrequency = null;
    	this.grade1Frequency = null;
    	this.grade2Frequency = null;
    	this.grade3Frequency = null;
    	this.grade4Frequency = null;
    	this.grade5Frequency = null;
    	
		for(TreatmentAssignmentAgent treatmentAssignmentAgent: treatmentAssignmentAgents){
			AgentSpecificTerm agentSpecificTerm= treatmentAssignmentAgent.getStudyAgent().getAgent().getAgentSpecificTerm(getTerm());
			recalculateExpectedness(agentSpecificTerm, treatmentAssignmentAgent.getStudyAgent().shouldHonor() && agentSpecificTerm.isExpected());
		}
	}
	
	public void removeTreatmentAssignmentAgent(TreatmentAssignmentAgent treatmentAssignmentAgent){
		getTreatmentAssignmentAgents().remove(treatmentAssignmentAgent);
		resetAndRecalculateExpectedness();
	}
	
	public void addTreatmentAssignmentAgent(TreatmentAssignmentAgent treatmentAssignmentAgent, AgentSpecificTerm agentSpecificTerm){
		//Check if the ae is already added for this treatment assignment
		if(treatmentAssignmentAgents.contains(treatmentAssignmentAgent)){
			//TODO: For now just return. Maybe we update the expectedness values and copy them from agentspecificterm to abstractstudyinterventionAE
			return;
		}
		//Add the new source of AE and recalculate expectedness.
		// shouldHonor = true if study has a CTEP Ind and given studyAgent is CTEP Ind
    	// shouldHonor = true if study has no CTEP Ind and given studyAgent is not CTEP Ind
    	// shouldHonor = false if study has a CTEP Ind and given studyAgent is not CTEP Ind
    	// All other combinations are invalid
		treatmentAssignmentAgents.add(treatmentAssignmentAgent);
		recalculateExpectedness(agentSpecificTerm, treatmentAssignmentAgent.getStudyAgent().shouldHonor() && agentSpecificTerm.isExpected());
	}

}
