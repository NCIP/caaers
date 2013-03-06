/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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
 * This class represents the DiseaseTerminology domain object associated with the Adverse event
 * report.
 * 
 * @author Krikor Krumlian
 */
@Entity
@Table(name = "disease_terminologies")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_disease_terminologies_id") })
public class DiseaseTerminology extends AbstractMutableDomainObject {
    
    /** The disease code term. */
    private DiseaseCodeTerm diseaseCodeTerm;

    // In the future include versions something like this perhaps
    // private Ctep ctepVersion
    // private MeddraVersion meddraVersion;
    
    // This is included to hold the correct meddra_version.
    /** The meddra version. */
    private MeddraVersion meddraVersion;
    
    /** The study. */
    private Study study;

    // //// BEAN PROPERTIES

    /**
     * Gets the disease code term.
     *
     * @return the disease code term
     */
    @Column(name = "term_code")
    @Type(type = "diseaseCodeTerm")
    public DiseaseCodeTerm getDiseaseCodeTerm() {
        return diseaseCodeTerm;
    }

    /**
     * Sets the disease code term.
     *
     * @param diseaseCodeTerm the new disease code term
     */
    public void setDiseaseCodeTerm(DiseaseCodeTerm diseaseCodeTerm) {
        this.diseaseCodeTerm = diseaseCodeTerm;
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
