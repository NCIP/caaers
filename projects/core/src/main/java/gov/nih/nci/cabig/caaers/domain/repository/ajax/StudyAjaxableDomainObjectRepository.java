package gov.nih.nci.cabig.caaers.domain.repository.ajax;

import gov.nih.nci.cabig.caaers.dao.query.ajax.StudyAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.dao.query.ajax.AbstractAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.domain.ajax.StudyAjaxableDomainObject;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Biju Joseph
 */

@Transactional(readOnly = true)
public class StudyAjaxableDomainObjectRepository<T extends StudyAjaxableDomainObject> extends AbstractAjaxableDomainObjectRepository {

    public List<T> findStudies(final AbstractAjaxableDomainObjectQuery query) {

        List<Object[]> objects = super.find(query);
        List<T> studyAjaxableDomainObjects = new ArrayList<T>();

        for (Object[] o : objects) {
            T studyAjaxableDomainObject = (T) getObjectById(studyAjaxableDomainObjects, (Integer) o[0]);

            if (studyAjaxableDomainObject == null) {
                studyAjaxableDomainObject = (T) BeanUtils.instantiateClass(getObjectClass());
                studyAjaxableDomainObject.setId((Integer) o[0]);
                studyAjaxableDomainObject.setShortTitle((String) o[1]);
                if (o[3] != null && (Boolean) o[3]) {
                    studyAjaxableDomainObject.setPrimaryIdentifierValue((String) o[2]);
                }

                addAdditionalProperties(studyAjaxableDomainObject, o);

                studyAjaxableDomainObjects.add(studyAjaxableDomainObject);

            } else {
                updateAdditionalProperties(studyAjaxableDomainObject, o);

            }


        }
        return studyAjaxableDomainObjects;

    }

    protected void updateAdditionalProperties(T studyAjaxableDomainObject, Object[] o) {
        if (o[3] != null && (Boolean) o[3]) {
            studyAjaxableDomainObject.setPrimaryIdentifierValue((String) o[2]);
        }

    }

    protected Class getObjectClass() {
        return StudyAjaxableDomainObject.class;
    }

    protected void addAdditionalProperties(T studyAjaxableDomainObject, Object[] objects) {
    }


}