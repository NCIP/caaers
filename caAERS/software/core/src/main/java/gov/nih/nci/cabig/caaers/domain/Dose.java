package gov.nih.nci.cabig.caaers.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.springframework.beans.BeanUtils;

/**
 * This class represents the Dose domain object associated with the Adverse event report.
 *
 * @author Rhett Sutphin
 */
@Embeddable
public class Dose {
    private String amount;

    private String units; // TODO: source from caDSR

    private String route; // caDSR?

    // //// LOGIC

    @Transient
    public String getDisplayName() {
        StringBuilder sb = new StringBuilder().append(getAmount());
        if (getUnits() != null) sb.append(getUnits());
        if (getRoute() != null) sb.append(' ').append(getRoute());
        return sb.toString();
    }

    // //// BEAN PROPERTIES

    @Column(name = "dose_amount")
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Column(name = "dose_units")
    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    @Column(name = "dose_route")
    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    // //// OBJECT METHODS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dose dose = (Dose) o;

        if (amount != null ? !amount.equals(dose.amount) : dose.amount != null) return false;
        if (route != null ? !route.equals(dose.route) : dose.route != null) return false;
        if (units != null ? !units.equals(dose.units) : dose.units != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        result = (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (units != null ? units.hashCode() : 0);
        result = 31 * result + (route != null ? route.hashCode() : 0);
        return result;
    }


    public Dose copy() {
        Dose anotherDose = new Dose();
        BeanUtils.copyProperties(this, anotherDose);
        return anotherDose;

    }
}
