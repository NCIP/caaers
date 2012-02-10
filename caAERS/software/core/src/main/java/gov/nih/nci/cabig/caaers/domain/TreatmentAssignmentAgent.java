package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.*;

/**
 * @author Ion C. Olaru
 *         Date: 1/5/12 -1:45 PM
 */
@Entity
@Table(name = "ta_agents")
public class TreatmentAssignmentAgent extends TreatmentAssignmentStudyIntervention {

    private StudyAgent studyAgent;

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
}
