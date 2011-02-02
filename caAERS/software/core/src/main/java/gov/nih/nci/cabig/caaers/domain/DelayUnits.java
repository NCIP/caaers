package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.EnumHelper.sentenceCasedName;

import java.math.BigDecimal;

 
/**
 * The Enum DelayUnits.
 *
 * @author Rhett Sutphin
 */
public enum DelayUnits {
    
    /** The MINUTES. */
    MINUTES(1), 
 /** The HOURS. */
 HOURS(60), 
 /** The DAYS. */
 DAYS(24 * 60);

    /** The factor. */
    private BigDecimal factor;

    /**
     * Instantiates a new delay units.
     *
     * @param factor the factor
     */
    DelayUnits(int factor) {
        this.factor = new BigDecimal(factor);
    }

    /**
     * Gets the display name.
     *
     * @return the display name
     */
    public String getDisplayName() {
        return sentenceCasedName(this);
    }

    /**
     * To minutes.
     *
     * @param unitedValue the united value
     * @return the big decimal
     */
    public BigDecimal toMinutes(BigDecimal unitedValue) {
        return unitedValue.multiply(factor);
    }

    /**
     * From minutes.
     *
     * @param minuteValue the minute value
     * @return the big decimal
     */
    public BigDecimal fromMinutes(BigDecimal minuteValue) {
        return minuteValue.divide(factor);
    }

    /**
     * Checks if is exact.
     *
     * @param minuteValue the minute value
     * @return true, if is exact
     */
    public boolean isExact(BigDecimal minuteValue) {
        return minuteValue.scale() <= 0 && 0 == minuteValue.remainder(factor).intValueExact();
    }
}
