package gov.nih.nci.cabig.caaers.domain.attribution;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import gov.nih.nci.cabig.ctms.domain.DomainObject;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.springframework.beans.BeanUtils;

/**
 * @author Rhett Sutphin
 */
@Entity
@Table(name = "ae_attributions")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "cause_type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("ABSTRACT_BASE")
// should be ignored
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_ae_attributions_id")})
public abstract class AdverseEventAttribution<T extends DomainObject> extends
        AbstractMutableDomainObject {
    private T cause;

    private Attribution attribution;

    private AdverseEvent adverseEvent;

    // //// BEAN PROPERTIES

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(insertable = false, updatable = false, nullable = false)
    public AdverseEvent getAdverseEvent() {
        return adverseEvent;
    }

    public void setAdverseEvent(AdverseEvent adverseEvent) {
        this.adverseEvent = adverseEvent;
    }

    @Type(type = "attribution")
    @Column(name = "attribution_code")
    public Attribution getAttribution() {
        return attribution;
    }

    public void setAttribution(Attribution attribution) {
        this.attribution = attribution;
    }

    @Transient
    /*
     * this is only transient here -- subclasses need to override it and specify what it refers to
     * This should work: @ManyToOne @JoinColumn(name = "cause_id", nullable = false)
     */
    public T getCause() {
        return cause;
    }

    public void setCause(T cause) {
        this.cause = cause;
    }

    public <R extends AdverseEventAttribution> R copy() {
        R adverseEventAttribution = (R) BeanUtils.instantiateClass(getClass());
        BeanUtils.copyProperties(this, adverseEventAttribution, new String[]{"id", "gridId", "version", "adverseEvent"});
        adverseEventAttribution.setCause(cause);
        return adverseEventAttribution;

    }

}
