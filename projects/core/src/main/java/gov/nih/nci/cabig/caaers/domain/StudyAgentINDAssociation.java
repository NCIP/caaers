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

@Entity
@Table(name = "study_agent_inds")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_study_agent_inds_id") })
public class StudyAgentINDAssociation extends AbstractIdentifiableDomainObject implements
                Serializable, StudyAgentChild {
    private StudyAgent studyAgent;

    private InvestigationalNewDrug investigationalNewDrug;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "study_agent_id")
    @Cascade(value = {CascadeType.LOCK})
    public StudyAgent getStudyAgent() {
        return studyAgent;
    }

    public void setStudyAgent(StudyAgent studyAgent) {
        this.studyAgent = studyAgent;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "ind_id")
    @Cascade(value = {CascadeType.LOCK})
    public InvestigationalNewDrug getInvestigationalNewDrug() {
        return investigationalNewDrug;
    }

    public void setInvestigationalNewDrug(InvestigationalNewDrug investigationalNewDrug) {
        this.investigationalNewDrug = investigationalNewDrug;
    }

}
