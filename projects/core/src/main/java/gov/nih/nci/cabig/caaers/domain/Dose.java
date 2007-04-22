package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.Embeddable;
import javax.persistence.Column;
import java.math.BigDecimal;

/**
 * @author Rhett Sutphin
 */
@Embeddable
public class Dose {
    private BigDecimal amount;
    private String units; // TODO: source from caDSR
    private String route; // caDSR?

    @Column(name = "dose_amount")
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
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
}
