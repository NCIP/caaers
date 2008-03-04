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
    private String term;

    private String ctepTerm;

    private String medraCode;

    private DiseaseCategory category;

    // //// LOGIC

    // //// BEAN PROPERTIES

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getCtepTerm() {
        return ctepTerm;
    }

    public void setCtepTerm(String ctepTerm) {
        this.ctepTerm = ctepTerm;
    }

    public String getMedraCode() {
        return medraCode;
    }

    public void setMedraCode(String medraCode) {
        this.medraCode = medraCode;
    }

    @Transient
    public String getMeddraCode() {
        return medraCode;
    }

    public void setMeddraCode(String meddraCode) {
        this.medraCode = meddraCode;
    }

    @ManyToOne
    public DiseaseCategory getCategory() {
        return category;
    }

    public void setCategory(DiseaseCategory category) {
        this.category = category;
    }

    @Transient
    public String getFullName() {
        return term;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final DiseaseTerm that = (DiseaseTerm) o;

        if (ctepTerm != null ? !ctepTerm.equals(that.ctepTerm) : that.ctepTerm != null) return false;
        if (medraCode != null ? !medraCode.equals(that.medraCode) : that.medraCode != null) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (ctepTerm != null ? ctepTerm.hashCode() : 0);
        result = 29 * result + (medraCode != null ? medraCode.hashCode() : 0);
        return result;
    }

}
