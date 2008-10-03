package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import gov.nih.nci.cabig.ctms.domain.DomainObject;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

/**
 * @author Krikor Krumlian
 */

@Entity
@Table(name = "ae_terms")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "term_type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("ABSTRACT_TERM")
// should be ignored
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_ae_terms_id")})
public abstract class AbstractAdverseEventTerm<T extends DomainObject> extends
        AbstractMutableDomainObject {
    private T term;

    private AdverseEvent adverseEvent;

    // //// BEAN PROPERTIES

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adverse_event_id")
    public AdverseEvent getAdverseEvent() {
        return adverseEvent;
    }

    public void setAdverseEvent(AdverseEvent adverseEvent) {
        this.adverseEvent = adverseEvent;
    }

    @Transient
    public abstract String getUniversalTerm();

    /**
     * Will tell whether the term is an Otherspecify
     */
    @Transient
    public abstract boolean isOtherRequired();

    @Transient
    /*
     * this is only transient here -- subclasses need to override it and specify what it refers to
     * This should work: @ManyToOne @JoinColumn(name = "cause_id", nullable = false)
     */
    public T getTerm() {
        return term;
    }

    public void setTerm(T term) {
        this.term = term;
    }
    
    @Transient
    public abstract boolean isMedDRA();
    
    @Transient
    public abstract String getFullName();

    public AbstractAdverseEventTerm copy() {
        AbstractAdverseEventTerm abstractAdverseEventTerm = (AbstractAdverseEventTerm) BeanUtils.instantiateClass(getClass());
        BeanUtils.copyProperties(this, abstractAdverseEventTerm,
                new String[]{"id", "gridId", "version", "adverseEvent"});

        return abstractAdverseEventTerm;

    }
}
