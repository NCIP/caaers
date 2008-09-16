package gov.nih.nci.cabig.caaers.domain.repository.ajax;

import gov.nih.nci.cabig.caaers.dao.query.ajax.StudyAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.domain.ajax.StudyAjaxableDomainObject;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Biju Joseph
 */

@Transactional(readOnly = true)
public class StudyAjaxableDomainObjectRepository extends AbstractAjaxableDomainObjectRepository {
    public List<StudyAjaxableDomainObject> findStudies(StudyAjaxableDomainObjectQuery query) {
        List<Object[]> objects = super.find(query);
        List<StudyAjaxableDomainObject> studyAjaxableDomainObjects = new ArrayList<StudyAjaxableDomainObject>();

        for (Object[] o : objects) {
            StudyAjaxableDomainObject studyAjaxableDomainObject = (StudyAjaxableDomainObject) getObjectById(studyAjaxableDomainObjects, (Integer) o[0]);

            if (studyAjaxableDomainObject == null) {
                studyAjaxableDomainObject = new StudyAjaxableDomainObject();
                studyAjaxableDomainObject.setId((Integer) o[0]);
                studyAjaxableDomainObject.setShortTitle((String) o[1]);
                if (o[3] != null && (Boolean) o[3]) {
                    studyAjaxableDomainObject.setPrimaryIdentifierValue((String) o[2]);
                }
                studyAjaxableDomainObjects.add(studyAjaxableDomainObject);

            } else if (studyAjaxableDomainObject != null && o[3] != null && (Boolean) o[3]) {
                studyAjaxableDomainObject.setPrimaryIdentifierValue((String) o[2]);
            }


        }
        return studyAjaxableDomainObjects;

    }


}