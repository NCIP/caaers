package gov.nih.nci.cabig.caaers.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 * @author Rhett Sutphin
 */
@Entity
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="seq_concomitant_medications_id")
    }
)
public class ConcomitantMedication extends AbstractAdverseEventReportCollectionElementChild {
    private Agent agent;
    private String other;

    ////// LOGIC

    @Transient
    public String getName() {
        if (getAgent() != null) {
            return getAgent().getName();
        } else if (getOther() != null) {
            return "Other: " + getOther();
        } else {
            return null;
        }
    }

    ////// BOUND PROPERTIES

    @ManyToOne
    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
