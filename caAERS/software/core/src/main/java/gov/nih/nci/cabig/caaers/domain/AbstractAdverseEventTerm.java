/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import gov.nih.nci.cabig.ctms.domain.DomainObject;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.BeanUtils;

 
/**
 * The Class AbstractAdverseEventTerm.
 *
 * @param <T> the generic type
 * @author Krikor Krumlian
 */

@Entity
@Table(name = "ae_terms")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "term_type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("ABSTRACT_TERM")
// should be ignored
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_ae_terms_id")})
public abstract class AbstractAdverseEventTerm<T extends DomainObject> extends AbstractMutableDomainObject {
    
    /** The term. */
    private T term;
    
    /** The adverse event. */
    private AdverseEvent adverseEvent;

    // //// BEAN PROPERTIES

    /**
     * Gets the adverse event.
     *
     * @return the adverse event
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adverse_event_id")
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
     * Gets the universal term.
     *
     * @return the universal term
     */
    @Transient
    public abstract String getUniversalTerm();

    /**
     * Will tell whether the term is an Otherspecify.
     *
     * @return true, if is other required
     */
    @Transient
    public abstract boolean isOtherRequired();

    /**
     * Gets the term.
     *
     * @return the term
     */
    @Transient
    /*
     * this is only transient here -- subclasses need to override it and specify what it refers to
     * This should work: @ManyToOne @JoinColumn(name = "cause_id", nullable = false)
     */
    public T getTerm() {
        return term;
    }

    /**
     * Sets the term.
     *
     * @param term the new term
     */
    public void setTerm(T term) {
        this.term = term;
    }
    
    /**
     * Checks if is med dra.
     *
     * @return true, if is med dra
     */
    @Transient
    public abstract boolean isMedDRA();
    
    /**
     * Gets the full name.
     *
     * @return the full name
     */
    @Transient
    public abstract String getFullName();

    /**
     * Copy.
     *
     * @return the abstract adverse event term
     */
    public AbstractAdverseEventTerm copy() {
        AbstractAdverseEventTerm abstractAdverseEventTerm = (AbstractAdverseEventTerm) BeanUtils.instantiateClass(getClass());
        BeanUtils.copyProperties(this, abstractAdverseEventTerm, new String[]{"id", "gridId", "version", "adverseEvent"});
        return abstractAdverseEventTerm;
    }
}
