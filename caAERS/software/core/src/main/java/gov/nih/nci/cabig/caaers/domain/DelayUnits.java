package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.EnumHelper.sentenceCasedName;

import java.math.BigDecimal;

/**
 * @author Rhett Sutphin
 */
public enum DelayUnits {
    MINUTES(1), HOURS(60), DAYS(24 * 60);

    private BigDecimal factor;

    DelayUnits(int factor) {
        this.factor = new BigDecimal(factor);
    }

    public String getDisplayName() {
        return sentenceCasedName(this);
    }

    public BigDecimal toMinutes(BigDecimal unitedValue) {
        return unitedValue.multiply(factor);
    }

    public BigDecimal fromMinutes(BigDecimal minuteValue) {
        return minuteValue.divide(factor);
    }

    public boolean isExact(BigDecimal minuteValue) {
        return minuteValue.scale() <= 0 && 0 == minuteValue.remainder(factor).intValueExact();
    }
}
