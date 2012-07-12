package gov.nih.nci.cabig.caaers.domain;

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
    /** The code. */
    private int code; //to force hibernate to load the object
    /** The amount. */
    private String amount;

    /** The units. */
    private String units; // TODO: source from caDSR

    /** The route. */
    private String route; // caDSR?
    /**
     * Gets the code.
     *
     * @return the code
     */
    @Column(name = "dose_amount")
    public int getCode(){
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
    // //// LOGIC

    /**
     * Gets the display name.
     *
     * @return the display name
     */
    @Transient
    public String getDisplayName() {
        StringBuilder sb = new StringBuilder().append(getAmount());
        if (getUnits() != null) sb.append(getUnits());
        if (getRoute() != null) sb.append(' ').append(getRoute());
        return sb.toString();
    }

    // //// BEAN PROPERTIES

    /**
     * Gets the amount.
     *
     * @return the amount
     */
    @Column(name = "dose_amount")
    public String getAmount() {
        return amount;
    }

    /**
     * Sets the amount.
     *
     * @param amount the new amount
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * Gets the units.
     *
     * @return the units
     */
    @Column(name = "dose_units")
    public String getUnits() {
        return units;
    }

    /**
     * Sets the units.
     *
     * @param units the new units
     */
    public void setUnits(String units) {
        this.units = units;
    }

    /**
     * Gets the route.
     *
     * @return the route
     */
    @Column(name = "dose_route")
    public String getRoute() {
        return route;
    }

    /**
     * Sets the route.
     *
     * @param route the new route
     */
    public void setRoute(String route) {
        this.route = route;
    }

    // //// OBJECT METHODS

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
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

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result;
        result = (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (units != null ? units.hashCode() : 0);
        result = 31 * result + (route != null ? route.hashCode() : 0);
        return result;
    }


    /**
     * Copy.
     *
     * @return the dose
     */
    public Dose copy() {
        Dose anotherDose = new Dose();
        BeanUtils.copyProperties(this, anotherDose);
        return anotherDose;

    }
}
