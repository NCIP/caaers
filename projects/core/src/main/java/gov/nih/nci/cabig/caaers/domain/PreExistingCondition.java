package gov.nih.nci.cabig.caaers.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

import gov.nih.nci.cabig.ctms.domain.AbstractImmutableDomainObject;

/**
 * This class represents the PreExistingCondition domain object associated with the Adverse event
 * report.
 * 
 * @author Krikor Krumlian
 */
@Entity
@Table(name = "pre_existing_conditions")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_pre_existing_conditions_id") })
public class PreExistingCondition extends AbstractImmutableDomainObject {
    private String text;

    private String meddraLlt; // MedDRA v9.0 Lower Level Term (LLT)

    private String meddraLltCode; // MedDRA v9.0 LLT/CTEP Provisional Code

    private String meddraHlgt; // MedDRA v9.0 High Level Group Term (HLGT)/CTEP Condition Category

    @Column(name = "condition_text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column(name = "meddra_llt_code")
    public String getMeddraLltCode() {
        return meddraLltCode;
    }

    public void setMeddraLltCode(String meddraLltCode) {
        this.meddraLltCode = meddraLltCode;
    }

    @Column(name = "meddra_hlgt")
    public String getMeddraHlgt() {
        return meddraHlgt;
    }

    public void setMeddraHlgt(String meddraHlgt) {
        this.meddraHlgt = meddraHlgt;
    }

    @Column(name = "meddra_llt")
    public String getMeddraLlt() {
        return meddraLlt;
    }

    public void setMeddraLlt(String meddraLlt) {
        this.meddraLlt = meddraLlt;
    }

}
