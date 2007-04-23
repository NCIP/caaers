package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * @author Kulasekaran
 * @version 1.0
 */
@Entity
@Table (name="participant_history")
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="seq_participant_history_id")
    }
)
public class ParticipantHistory extends AbstractIdentifiableDomainObject
{			
	private int height;
    private int weight;
    private String baselinePerformanceStatus;

    public int getHeight() {
		return height;
	}
    
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}	

	public String getBaselinePerformanceStatus() {
		return baselinePerformanceStatus;
	}

	public void setBaselinePerformanceStatus(String baselinePerformance) {
		this.baselinePerformanceStatus = baselinePerformance;
	}
}

