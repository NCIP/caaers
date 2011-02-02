package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

 
/**
 * This class represents the AeTerminology domain object associated with the Adverse event report.
 * 
 * @author Krikor Krumlian
 */
@Entity
@Table(name = "terminologies")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_terminologies_id") })
public class AeTerminology extends AbstractMutableDomainObject {
    
    /** The term. */
    private Term term;

    /** The ctc version. */
    private Ctc ctcVersion;

    /** The meddra version. */
    private MeddraVersion meddraVersion;

    /** The study. */
    private Study study;

    // //// BEAN PROPERTIES

    /**
     * Gets the term.
     *
     * @return the term
     */
    @Column(name = "term_code")
    @Type(type = "term")
    public Term getTerm() {
        return term;
    }

    /**
     * Sets the term.
     *
     * @param term the new term
     */
    public void setTerm(Term term) {
        this.term = term;
    }

    /**
     * Gets the ctc version.
     *
     * @return the ctc version
     */
    @OneToOne
    @JoinColumn(name = "ctc_id")
    @Cascade(value = { CascadeType.LOCK })
    public Ctc getCtcVersion() {
        return ctcVersion;
    }

    /**
     * Sets the ctc version.
     *
     * @param ctcVersion the new ctc version
     */
    public void setCtcVersion(Ctc ctcVersion) {
        this.ctcVersion = ctcVersion;
    }

    /**
     * Gets the meddra version.
     *
     * @return the meddra version
     */
    @OneToOne
    @JoinColumn(name = "meddra_version_id")
    public MeddraVersion getMeddraVersion() {
        return meddraVersion;
    }

    /**
     * Sets the meddra version.
     *
     * @param meddraVersion the new meddra version
     */
    public void setMeddraVersion(MeddraVersion meddraVersion) {
        this.meddraVersion = meddraVersion;
    }

    /**
     * Gets the study.
     *
     * @return the study
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    @Cascade(value = {CascadeType.EVICT})
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

}
