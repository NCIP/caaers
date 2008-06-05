package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public class AbstractMutableDeletableDomainObject extends AbstractMutableDomainObject {

    private String retiredIndicator = "false";

    public String getRetiredIndicator() {
        return retiredIndicator;
    }

    private void setRetiredIndicator(String retiredIndicator) {
        this.retiredIndicator = retiredIndicator;
    }

    public void setRetiredIndicatorAsTrue() {
        this.setRetiredIndicator("true");
    }

    public void setRetiredIndicatorAsFalse() {
        this.setRetiredIndicator("false");
    }

    @Transient
    public int getHashCode() {
        return this.hashCode();
    }
}
