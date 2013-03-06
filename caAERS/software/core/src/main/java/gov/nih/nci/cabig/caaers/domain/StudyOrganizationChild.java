/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import java.util.Date;

public interface StudyOrganizationChild {
    public void setStudyOrganization(StudyOrganization studyOrg);
    public StudyOrganization getStudyOrganization();
    public void setStartDate(Date termStartDate);
    public void setEndDate(Date termStartDate);
}
