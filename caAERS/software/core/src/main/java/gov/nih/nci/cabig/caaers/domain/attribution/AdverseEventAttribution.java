/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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
 * The Class AdverseEventAttribution.
 *
 * @param <T> the generic type
 * @author Rhett Sutphin
 */
@Entity
@Table(name = "ae_attributions")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "cause_type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("ABSTRACT_BASE")
// should be ignored
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_ae_attributions_id")})
public abstract class AdverseEventAttribution<T extends DomainObject> extends AbstractMutableDomainObject {

    public AdverseEventAttribution(T cause) {
        this.cause = cause;
    }

    public AdverseEventAttribution() {
    }

    /** The cause. */
    private T cause;
    
    /** The attribution. */
    private Attribution attribution;
    
    /** The adverse event. */
    private AdverseEvent adverseEvent;

    // //// BEAN PROPERTIES

    /**
     * Gets the adverse event.
     *
     * @return the adverse event
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(insertable = false, updatable = false, nullable = false)
    public AdverseEvent getAdverseEvent() {
        return adverseEvent;
    }

    /**
     * Sets the adverse event.
     *
     * @param adverseEvent the new adverse event
     */
    public void setAdverseEvent(AdverseEvent adverseEvent) {
        this.adverseEvent = adverseEvent;
    }

    /**
     * Gets the attribution.
     *
     * @return the attribution
     */
    @Type(type = "attribution")
    @Column(name = "attribution_code")
    public Attribution getAttribution() {
        return attribution;
    }

    /**
     * Sets the attribution.
     *
     * @param attribution the new attribution
     */
    public void setAttribution(Attribution attribution) {
        this.attribution = attribution;
    }

    /**
     * Gets the cause.
     *
     * @return the cause
     */
    @Transient
    /*
     * this is only transient here -- subclasses need to override it and specify what it refers to
     * This should work: @ManyToOne @JoinColumn(name = "cause_id", nullable = false)
     */
    public T getCause() {
        return cause;
    }

    /**
     * Sets the cause.
     *
     * @param cause the new cause
     */
    public void setCause(T cause) {
        this.cause = cause;
    }

    /**
     * Copy.
     *
     * @param <R> the generic type
     * @return the r
     */
    public <R extends AdverseEventAttribution> R copy() {
        R adverseEventAttribution = (R) BeanUtils.instantiateClass(getClass());
        BeanUtils.copyProperties(this, adverseEventAttribution, new String[]{"id", "gridId", "version", "adverseEvent"});
        adverseEventAttribution.setCause(cause);
        return adverseEventAttribution;

    }


    /**
     * Will return true if it is attributed with the specific attribution. 
     * @param a - Attribution
     * @return - true or false
     */
    @Transient
    public boolean isAttributedWith(Attribution a){
        if(a == null || getAttribution() == null) return false;
        return getAttribution().equals(a);
    }

}
