package gov.nih.nci.cabig.caaers.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

 
/**
 * The Class StudyAgentINDAssociation.
 */
@Entity
@Table(name = "study_agent_inds")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_study_agent_inds_id") })
public class StudyAgentINDAssociation extends AbstractIdentifiableDomainObject implements Serializable, StudyAgentChild {

    /** The study agent. */
    private StudyAgent studyAgent;
    
    /** The investigational new drug. */
    private InvestigationalNewDrug investigationalNewDrug;

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.StudyAgentChild#getStudyAgent()
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "study_agent_id")
    @Cascade(value = {CascadeType.LOCK})
    public StudyAgent getStudyAgent() {
        return studyAgent;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.StudyAgentChild#setStudyAgent(gov.nih.nci.cabig.caaers.domain.StudyAgent)
     */
    public void setStudyAgent(StudyAgent studyAgent) {
        this.studyAgent = studyAgent;
    }

    /**
     * Gets the investigational new drug.
     *
     * @return the investigational new drug
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "ind_id")
    @Cascade(value = {CascadeType.LOCK})
    public InvestigationalNewDrug getInvestigationalNewDrug() {
        return investigationalNewDrug;
    }

    /**
     * Sets the investigational new drug.
     *
     * @param investigationalNewDrug the new investigational new drug
     */
    public void setInvestigationalNewDrug(InvestigationalNewDrug investigationalNewDrug) {
        this.investigationalNewDrug = investigationalNewDrug;
    }

}
