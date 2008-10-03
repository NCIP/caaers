package gov.nih.nci.cabig.caaers.domain.repository.ajax;

import gov.nih.nci.cabig.caaers.dao.query.ajax.StudyAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.dao.query.ajax.StudySearchableAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.domain.ajax.StudyAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySearchableAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySiteAjaxableDomainObject;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Biju Joseph
 */

@Transactional(readOnly = true)
public class StudySearchableAjaxableDomainObjectRepository extends StudyAjaxableDomainObjectRepository<StudySearchableAjaxableDomainObject> {


    @Override
    protected void addAdditionalProperties(StudySearchableAjaxableDomainObject studySearchableAjaxableDomainObject, Object[] o) {
        super.addAdditionalProperties(studySearchableAjaxableDomainObject, o);
        studySearchableAjaxableDomainObject.setPhaseCode((String) o[4]);
        studySearchableAjaxableDomainObject.setStatus((String) o[5]);
        updateFundingSponsor(studySearchableAjaxableDomainObject, o);

        updateStudySite(studySearchableAjaxableDomainObject, o);
    }

    private void updateStudySite(StudySearchableAjaxableDomainObject studySearchableAjaxableDomainObject, Object[] o) {
        if (!StringUtils.isBlank((String) o[7]) && StringUtils.equals((String) o[9], "SST")) {
            StudySiteAjaxableDomainObject studySiteAjaxableDomainObject = new StudySiteAjaxableDomainObject();
            studySiteAjaxableDomainObject.setId((Integer) o[8]);
            studySiteAjaxableDomainObject.setName((String) o[7]);
            studySearchableAjaxableDomainObject.addStudySite(studySiteAjaxableDomainObject);
        }
    }

    private void updateFundingSponsor(StudySearchableAjaxableDomainObject studySearchableAjaxableDomainObject, Object[] o) {
        if (!StringUtils.isBlank((String) o[6])) {
            studySearchableAjaxableDomainObject.setPrimarySponsorCode((String) o[6]);
        }
    }

    @Override
    protected void updateAdditionalProperties(StudySearchableAjaxableDomainObject studySearchableAjaxableDomainObject, Object[] o) {
        super.updateAdditionalProperties(studySearchableAjaxableDomainObject, o);

        updateFundingSponsor(studySearchableAjaxableDomainObject, o);
        updateStudySite(studySearchableAjaxableDomainObject, o);

    }

    @Override
    protected Class getObjectClass() {
        return StudySearchableAjaxableDomainObject.class;


    }
}