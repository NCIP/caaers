package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import gov.nih.nci.cabig.ctms.domain.DomainObject;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
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
import org.springframework.beans.BeanUtils;

 
/**
 * The Class AbstractExpectedAE.
 *
 * @param <T> the generic type
 * @author Ion C. Olaru
 */

@Entity
@Table(name = "expected_aes")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_expected_aes_id")})
@DiscriminatorColumn(name = "term_type", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class AbstractExpectedAE<T extends DomainObject> extends AbstractMutableDomainObject {
    
    /** The term. */
    private T term;
    
    /** The study. */
    private Study study;
    
    /** The other toxicity. */
    private String otherToxicity;

    /**
     * Gets the study.
     *
     * @return the study
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id", insertable = false, updatable = false)
    public Study getStudy() {
        return study;
    }

    /**
     * Sets the study.
     *
     * @param study the new study
     */
    public void setStudy(Study study) {
        this.study = study;
    }

    /**
     * Gets the term.
     *
     * @return the term
     */
    @Transient
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
     * Checks if is other required.
     *
     * @return true, if is other required
     */
    @Transient
    public abstract boolean isOtherRequired();
    
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
     * @return the abstract expected ae
     */
    public AbstractExpectedAE copy() {
        AbstractExpectedAE abstractAdverseEventTerm = (AbstractExpectedAE) BeanUtils.instantiateClass(getClass());
        BeanUtils.copyProperties(this, abstractAdverseEventTerm, new String[]{"id", "gridId", "version", "study"});
        return abstractAdverseEventTerm;
    }

    /**
     * Gets the other toxicity.
     *
     * @return the other toxicity
     */
    public String getOtherToxicity() {
        return otherToxicity;
    }

    /**
     * Sets the other toxicity.
     *
     * @param otherToxicity the new other toxicity
     */
    public void setOtherToxicity(String otherToxicity) {
        this.otherToxicity = otherToxicity;
    }

}