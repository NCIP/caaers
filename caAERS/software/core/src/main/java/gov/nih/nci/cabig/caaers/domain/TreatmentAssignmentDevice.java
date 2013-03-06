/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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
