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
public class ParticipantHistory extends AbstractExpeditedReportSingleChild {
    // TODO: switch to BigDecimals, put measure & unit in a component
    private float height;
    private float weight;
    private String baselinePerformanceStatus;
    private String heightUnitOfMeasure;
    private String weightUnitOfMeasure;

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getBaselinePerformanceStatus() {
        return baselinePerformanceStatus;
    }

    public void setBaselinePerformanceStatus(String baselinePerformance) {
        this.baselinePerformanceStatus = baselinePerformance;
    }

    public String getHeightUnitOfMeasure() {
        return heightUnitOfMeasure;
    }

    public void setHeightUnitOfMeasure(String heightUnitOfMeasure) {
        this.heightUnitOfMeasure = heightUnitOfMeasure;
    }

    public String getWeightUnitOfMeasure() {
        return weightUnitOfMeasure;
    }

    public void setWeightUnitOfMeasure(String weightUnitOfMeasure) {
        this.weightUnitOfMeasure = weightUnitOfMeasure;
    }
}

