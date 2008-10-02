package gov.nih.nci.cabig.caaers.domain.repository.ajax;

import gov.nih.nci.cabig.caaers.dao.query.ajax.StudyAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.dao.query.ajax.StudySearchableAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.domain.ajax.StudyAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySearchableAjaxableDomainObject;
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

    }

    @Override
    protected Class getObjectClass() {
        return StudySearchableAjaxableDomainObject.class;


    }
}