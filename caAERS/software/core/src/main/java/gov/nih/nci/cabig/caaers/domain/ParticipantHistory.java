/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

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
 * The Class ParticipantHistory.
 *
 * @author Kulasekaran
 * @version 1.0
 */
@Entity
@Table(name = "participant_histories")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_participant_histories_id")})
public class ParticipantHistory extends AbstractExpeditedReportSingleChild {
    
    /** The baseline performance status. */
    private String baselinePerformanceStatus;

    /** The height. */
    private Measure height;

    /** The weight. */
    private Measure weight;
    
    /** The bsa. */
    private String bsa;

    /**
     * Gets the height.
     *
     * @return the height
     */
    @AttributeOverrides({
    		@AttributeOverride(name = "code", column = @Column(name = "height_code")),
            @AttributeOverride(name = "quantity", column = @Column(name = "height")),
            @AttributeOverride(name = "unit", column = @Column(name = "height_unit"))})
    public Measure getHeight() {
        if (height == null) setHeight(new Measure());
        return height;
    }

    /**
     * Sets the height.
     *
     * @param height the new height
     */
    public void setHeight(Measure height) {
        this.height = height;
    }

    /**
     * Gets the weight.
     *
     * @return the weight
     */
    @AttributeOverrides({
    	    @AttributeOverride(name = "code", column = @Column(name = "weight_code")),
            @AttributeOverride(name = "quantity", column = @Column(name = "weight")),
            @AttributeOverride(name = "unit", column = @Column(name = "weight_unit"))})
    public Measure getWeight() {
        if (weight == null) setWeight(new Measure());
        return weight;
    }

    /**
     * Sets the weight.
     *
     * @param weight the new weight
     */
    public void setWeight(Measure weight) {
        this.weight = weight;
    }

    /**
     * Gets the baseline performance status.
     *
     * @return the baseline performance status
     */
    public String getBaselinePerformanceStatus() {
        return baselinePerformanceStatus;
    }

    /**
     * Sets the baseline performance status.
     *
     * @param baselinePerformance the new baseline performance status
     */
    public void setBaselinePerformanceStatus(String baselinePerformance) {
        this.baselinePerformanceStatus = baselinePerformance;
    }

    /**
     * Gets the body surface area.
     *
     * @return the body surface area
     */
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
    /**
     * Gets the bsa.
     *
     * @return the bsa
     */
    @Transient
    public String getBsa(){
    	return this.bsa;
    }
    
    /**
     * Sets the bsa.
     *
     * @param bsa the new bsa
     */
    public void setBsa(String bsa){
    	this.bsa=bsa;
    }
    
    /**
     * Copy.
     *
     * @return the participant history
     */
    public ParticipantHistory copy() {
        ParticipantHistory participantHistory = new ParticipantHistory();
        BeanUtils.copyProperties(this, participantHistory, new String[]{"id", "gridId", "version", "report"});

        return participantHistory;
    }

    /**
     * The Class Measure.
     */
    @Embeddable
    public static class Measure {
    	
    	/** The code. */
	    private int code;
    	
        /** The quantity. */
        private Double quantity;

        /** The unit. */
        private String unit;

        /**
         * Gets the code.
         *
         * @return the code
         */
        public int getCode() {
			return code;
		}
        
        /**
         * Sets the code.
         *
         * @param code the new code
         */
        public void setCode(int code) {
			this.code = code;
		}
        
        /**
         * Gets the quantity.
         *
         * @return the quantity
         */
        public Double getQuantity() {
            return quantity;
        }

        /**
         * Sets the quantity.
         *
         * @param quantity the new quantity
         */
        public void setQuantity(Double quantity) {
            this.quantity = quantity;
        }

        /**
         * Gets the unit.
         *
         * @return the unit
         */
        public String getUnit() {
            return unit;
        }

        /**
         * Sets the unit.
         *
         * @param unit the new unit
         */
        public void setUnit(String unit) {
            this.unit = unit;
        }
    }

    /**
     * Will calculate the body surface area using Mosteller formula.
     *
     * @param height the height
     * @param heightUOM the height uom
     * @param weight the weight
     * @param weightUOM the weight uom
     * @return the double
     */
    @Transient
    public static double bodySuraceArea(double height, String heightUOM, double weight, String weightUOM) {

        ParticipantHistory participantHistory = new ParticipantHistory();
        ParticipantHistory.Measure ht = new ParticipantHistory.Measure();
        ht.setQuantity(height);
        ht.setUnit(heightUOM);

        participantHistory.setHeight(ht);

        ParticipantHistory.Measure wt = new ParticipantHistory.Measure();
        wt.setQuantity(weight);
        wt.setUnit(weightUOM);

        participantHistory.setWeight(wt);

        return participantHistory.getBodySurfaceArea();
    }
}
