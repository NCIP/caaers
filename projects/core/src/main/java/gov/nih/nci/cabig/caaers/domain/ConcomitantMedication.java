package gov.nih.nci.cabig.caaers.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * This class represents the ConcomitantMedication domain object associated with the Adverse event report.
 * @author Rhett Sutphin
 */
@Entity
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="seq_concomitant_medications_id")
    }
)
public class ConcomitantMedication extends AbstractExpeditedReportCollectionElementChild {
    private String agentName;

    ////// LOGIC

    @Transient
    public String getName() {
       return agentName;
    }

    ////// BOUND PROPERTIES

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }
}
