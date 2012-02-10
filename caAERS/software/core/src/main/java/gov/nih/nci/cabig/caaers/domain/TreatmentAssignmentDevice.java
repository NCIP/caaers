package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.*;

/**
 * @author Ion C. Olaru
 *         Date: 1/5/12 -1:45 PM
 */
@Entity
@Table(name = "ta_devices")
public class TreatmentAssignmentDevice extends TreatmentAssignmentStudyIntervention {

    private StudyDevice studyDevice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_device_id", nullable = false)
    public StudyDevice getStudyDevice() {
        return studyDevice;
    }

    public void setStudyDevice(StudyDevice studyDevice) {
        this.studyDevice = studyDevice;
    }

    @Override
    @Transient
    public StudyIntervention getStudyIntervention() {
        return this.getStudyDevice();
    }
}
