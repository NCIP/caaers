package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractImmutableDomainObject;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

 
/**
 * This class represents the DiseaseTerm domain object associated with the Adverse event report.
 * 
 * @author Krikor Krumlian
 */
@Entity
public class DiseaseTerm extends AbstractImmutableDomainObject {
    
    /** The term. */
    private String term;

    /** The ctep term. */
    private String ctepTerm;

    /** The medra code. */
    private String medraCode;

    /** The category. */
    private DiseaseCategory category;

    // //// LOGIC

    // //// BEAN PROPERTIES

    /**
     * Gets the term.
     *
     * @return the term
     */
    public String getTerm() {
        return term;
    }

    /**
     * Sets the term.
     *
     * @param term the new term
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * Gets the ctep term.
     *
     * @return the ctep term
     */
    public String getCtepTerm() {
        return ctepTerm;
    }

    /**
     * Sets the ctep term.
     *
     * @param ctepTerm the new ctep term
     */
    public void setCtepTerm(String ctepTerm) {
        this.ctepTerm = ctepTerm;
    }

    /**
     * Gets the medra code.
     *
     * @return the medra code
     */
    public String getMedraCode() {
        return medraCode;
    }

    /**
     * Sets the medra code.
     *
     * @param medraCode the new medra code
     */
    public void setMedraCode(String medraCode) {
        this.medraCode = medraCode;
    }

    /**
     * Gets the meddra code.
     *
     * @return the meddra code
     */
    @Transient
    public String getMeddraCode() {
        return medraCode;
    }

    /**
     * Sets the meddra code.
     *
     * @param meddraCode the new meddra code
     */
    public void setMeddraCode(String meddraCode) {
        this.medraCode = meddraCode;
    }

    /**
     * Gets the category.
     *
     * @return the category
     */
    @ManyToOne
    public DiseaseCategory getCategory() {
        return category;
    }

    /**
     * Sets the category.
     *
     * @param category the new category
     */
    public void setCategory(DiseaseCategory category) {
        this.category = category;
    }

    /**
     * Gets the full name.
     *
     * @return the full name
     */
    @Transient
    public String getFullName() {
        return term;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final DiseaseTerm that = (DiseaseTerm) o;

        if (ctepTerm != null ? !ctepTerm.equals(that.ctepTerm) : that.ctepTerm != null) return false;
        if (medraCode != null ? !medraCode.equals(that.medraCode) : that.medraCode != null) return false;

        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        int result;
        result = (ctepTerm != null ? ctepTerm.hashCode() : 0);
        result = 29 * result + (medraCode != null ? medraCode.hashCode() : 0);
        return result;
    }

}
