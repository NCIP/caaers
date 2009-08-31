package gov.nih.nci.cabig.caaers.domain;

import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.BeanUtils;

/**
 * @author Kulasekaran
 * @version 1.0
 */
@Entity
@Table(name = "participant_histories")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_participant_histories_id")})
public class ParticipantHistory extends AbstractExpeditedReportSingleChild {
    private String baselinePerformanceStatus;

    private Measure height;

    private Measure weight;
    
    private String bsa;

    @AttributeOverrides({
            @AttributeOverride(name = "quantity", column = @Column(name = "height")),
            @AttributeOverride(name = "unit", column = @Column(name = "height_unit"))})
    public Measure getHeight() {
        if (height == null) setHeight(new Measure());
        return height;
    }

    public void setHeight(Measure height) {
        this.height = height;
    }

    @AttributeOverrides({
            @AttributeOverride(name = "quantity", column = @Column(name = "weight")),
            @AttributeOverride(name = "unit", column = @Column(name = "weight_unit"))})
    public Measure getWeight() {
        if (weight == null) setWeight(new Measure());
        return weight;
    }

    public void setWeight(Measure weight) {
        this.weight = weight;
    }

    public String getBaselinePerformanceStatus() {
        return baselinePerformanceStatus;
    }

    public void setBaselinePerformanceStatus(String baselinePerformance) {
        this.baselinePerformanceStatus = baselinePerformance;
    }

    @Transient
    public double getBodySurfaceArea() {
        if (weight == null || height == null) return 0;
        if (weight.quantity == null || height.quantity == null) return 0;
        if (weight.unit == null || height.unit == null) return 0;

        double wt = (weight.unit.equalsIgnoreCase("Pound")) ? weight.quantity.doubleValue() / 2.20462262185 : weight.quantity.doubleValue();
        double ht = (height.unit.equalsIgnoreCase("Inch")) ? height.quantity.doubleValue() * 2.54 : height.quantity.doubleValue();

        // also round the result to 4 decimals
        return ((double)Math.round(0.20247 * Math.pow(ht/100, 0.725) * Math.pow(wt, 0.425) * 10000)) / 10000;
    }

    /*
     * This body surface area is a fabricated method of xml/pdf report.
     */
    @Transient
    public String getBsa(){
    	return this.bsa;
    }
    
    public void setBsa(String bsa){
    	this.bsa=bsa;
    }
    
    public ParticipantHistory copy() {
        ParticipantHistory participantHistory = new ParticipantHistory();
        BeanUtils.copyProperties(this, participantHistory, new String[]{"id", "gridId", "version", "report"});

        return participantHistory;
    }

    @Embeddable
    public static class Measure {
        private BigDecimal quantity;

        private String unit;

        public BigDecimal getQuantity() {
            return quantity;
        }

        public void setQuantity(BigDecimal quantity) {
            this.quantity = quantity;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }

    /**
     * Will calculate the body surface area using Mosteller formula
     */
    @Transient
    public static double bodySuraceArea(double height, String heightUOM, double weight, String weightUOM) {

        ParticipantHistory participantHistory = new ParticipantHistory();
        ParticipantHistory.Measure ht = new ParticipantHistory.Measure();
        ht.setQuantity(new BigDecimal(height));
        ht.setUnit(heightUOM);

        participantHistory.setHeight(ht);

        ParticipantHistory.Measure wt = new ParticipantHistory.Measure();
        wt.setQuantity(new BigDecimal(weight));
        wt.setUnit(weightUOM);

        participantHistory.setWeight(wt);

        return participantHistory.getBodySurfaceArea();
    }
}
