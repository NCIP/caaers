package gov.nih.nci.cabig.caaers.domain;

import java.util.Date;

public interface StudyOrganizationChild {
    public void setStudyOrganization(StudyOrganization studyOrg);
    public StudyOrganization getStudyOrganization();
    public void setStartDate(Date termStartDate);
    public void setEndDate(Date termStartDate);
}
