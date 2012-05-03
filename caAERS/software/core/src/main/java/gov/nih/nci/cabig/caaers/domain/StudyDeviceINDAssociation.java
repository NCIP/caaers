package gov.nih.nci.cabig.caaers.domain;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 *@author Biju Joseph
 */
@Entity
@Table(name = "study_device_inds")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_study_device_inds_id") })
public class StudyDeviceINDAssociation extends AbstractIdentifiableDomainObject implements Serializable, StudyDeviceChild {

    private StudyDevice studyDevice;
    private InvestigationalNewDrug investigationalNewDrug;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "study_device_id")
    @Cascade(value = {CascadeType.LOCK})
    public StudyDevice getStudyDevice() {
        return studyDevice;
    }

    public void setStudyDevice(StudyDevice studyDevice) {
        this.studyDevice = studyDevice;
    }

    /**
     * Gets the investigational new drug.
     *
     * @return the investigational new drug
     */
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "ind_id")
    @Cascade(value = {org.hibernate.annotations.CascadeType.LOCK})
    public InvestigationalNewDrug getInvestigationalNewDrug() {
        return investigationalNewDrug;
    }

    /**
     * Sets the investigational new drug.
     *
     * @param investigationalNewDrug the new investigational new drug
     */
    public void setInvestigationalNewDrug(InvestigationalNewDrug investigationalNewDrug) {
        this.investigationalNewDrug = investigationalNewDrug;
    }

}
