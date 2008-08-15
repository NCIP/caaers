package gov.nih.nci.cabig.caaers.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * This class represents the ConcomitantMedication domain object associated with the Adverse event
 * report.
 * 
 * @author Rhett Sutphin
 */
@Entity
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_concomitant_medications_id") })
public class ConcomitantMedication extends AbstractExpeditedReportCollectionElementChild {
    private String agentName;
    
    private Date startDate;
    
    private Date endDate;

    // //// LOGIC

    

	@Transient
    public String getName() {
        return agentName;
    }

    // //// BOUND PROPERTIES

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }
    // srini
    @Transient
    public Date getEndDate() {
		return endDate;
	}
    @Transient
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
    @Transient
	public Date getStartDate() {
		return startDate;
	}
    @Transient
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	//srini
}
