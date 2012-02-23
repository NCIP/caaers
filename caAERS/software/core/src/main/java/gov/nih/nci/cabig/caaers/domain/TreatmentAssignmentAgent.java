package gov.nih.nci.cabig.caaers.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;

/**
 * @author Ion C. Olaru
 *         Date: 1/5/12 -1:45 PM
 */
@Entity
@Table(name = "ta_agents")
public class TreatmentAssignmentAgent extends TreatmentAssignmentStudyIntervention {

    private StudyAgent studyAgent;
    protected List<AbstractStudyInterventionExpectedAE> abstractStudyInterventionExpectedAEs = new ArrayList<AbstractStudyInterventionExpectedAE>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_agent_id", nullable = false)
    public StudyAgent getStudyAgent() {
        return studyAgent;
    }

    public void setStudyAgent(StudyAgent studyAgent) {
        this.studyAgent = studyAgent;
    }

    @Override
    @Transient
    public StudyIntervention getStudyIntervention() {
        return this.getStudyAgent();
    }
    
    @ManyToMany(mappedBy = "treatmentAssignmentAgents", fetch = FetchType.LAZY)
    @OrderBy
    @Cascade(value = {CascadeType.ALL})
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<AbstractStudyInterventionExpectedAE> getAbstractStudyInterventionExpectedAEs() {
        return abstractStudyInterventionExpectedAEs;
    }

    public void setAbstractStudyInterventionExpectedAEs(List<AbstractStudyInterventionExpectedAE> abstractStudyInterventionExpectedAEs) {
        this.abstractStudyInterventionExpectedAEs = abstractStudyInterventionExpectedAEs;
    }
}
