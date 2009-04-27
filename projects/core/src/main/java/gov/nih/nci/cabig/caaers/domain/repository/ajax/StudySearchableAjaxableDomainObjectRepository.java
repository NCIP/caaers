package gov.nih.nci.cabig.caaers.domain.repository.ajax;

import gov.nih.nci.cabig.caaers.dao.query.ajax.AbstractAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySearchableAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySiteAjaxableDomainObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Biju Joseph
 */

@Transactional(readOnly = true)
public class StudySearchableAjaxableDomainObjectRepository<T extends StudySearchableAjaxableDomainObject> extends AbstractAjaxableDomainObjectRepository {

    public List<T> findStudies(final AbstractAjaxableDomainObjectQuery query) {

        List<Object[]> objects = super.find(query);
        Map<Integer, T> existingStudyMap = new LinkedHashMap<Integer, T>();
        for (Object[] o : objects) {
        	
        	
            T studySearchableAjaxableDomainObject = existingStudyMap.get((Integer) o[0]);

            if (studySearchableAjaxableDomainObject == null) {
                studySearchableAjaxableDomainObject = (T) BeanUtils.instantiateClass(getObjectClass());
                studySearchableAjaxableDomainObject.setId((Integer) o[0]);
                studySearchableAjaxableDomainObject.setShortTitle((String) o[1]);
                if (o[3] != null && (Boolean) o[3]) {
                    studySearchableAjaxableDomainObject.setPrimaryIdentifierValue((String) o[2]);
                }

                addAdditionalProperties(studySearchableAjaxableDomainObject, o);

                existingStudyMap.put(studySearchableAjaxableDomainObject.getId(), studySearchableAjaxableDomainObject);
                

            } else {
                updateAdditionalProperties(studySearchableAjaxableDomainObject, o);

            }


        }
        return new ArrayList<T>(existingStudyMap.values());

    }

    protected void updateAdditionalProperties(T studySearchableAjaxableDomainObject, Object[] o) {
        if (o[3] != null && (Boolean) o[3]) {
            studySearchableAjaxableDomainObject.setPrimaryIdentifierValue((String) o[2]);
        }
        updateFundingSponsor(studySearchableAjaxableDomainObject, o);
        updateCoordinatingCenter(studySearchableAjaxableDomainObject, o);
        updateStudySite(studySearchableAjaxableDomainObject, o);
        updateStudyPersonnel(studySearchableAjaxableDomainObject, o);


    }

    protected void addAdditionalProperties(StudySearchableAjaxableDomainObject studySearchableAjaxableDomainObject, Object[] o) {
        studySearchableAjaxableDomainObject.setPhaseCode((String) o[4]);
        studySearchableAjaxableDomainObject.setStatus((String) o[5]);
        updateFundingSponsor(studySearchableAjaxableDomainObject, o);
        updateCoordinatingCenter(studySearchableAjaxableDomainObject, o);

        updateStudySite(studySearchableAjaxableDomainObject, o);
        updateStudyPersonnel(studySearchableAjaxableDomainObject, o);
    }

    private void updateStudySite(StudySearchableAjaxableDomainObject studySearchableAjaxableDomainObject, Object[] o) {
        //if (!StringUtils.isBlank((String) o[7]) && (StringUtils.equals((String) o[9], "SST") || StringUtils.equals((String) o[9], "SCC"))) {
        if (!StringUtils.isBlank((String) o[7]) && (StringUtils.equals((String) o[9], "SST"))) {
            StudySiteAjaxableDomainObject studySiteAjaxableDomainObject = new StudySiteAjaxableDomainObject();
            studySiteAjaxableDomainObject.setId((Integer) o[8]);
            studySiteAjaxableDomainObject.setName((String) o[7]);
            studySiteAjaxableDomainObject.setNciInstituteCode((String) o[10]);
            studySearchableAjaxableDomainObject.addStudySite(studySiteAjaxableDomainObject);
        }
    }

    private void updateStudyPersonnel(StudySearchableAjaxableDomainObject studySearchableAjaxableDomainObject, Object[] o) {
    	if (o[11] != null) {
            studySearchableAjaxableDomainObject.addStudyPersonnelId((Integer) o[11]);
        }
    }
    
    private void updateFundingSponsor(StudySearchableAjaxableDomainObject studySearchableAjaxableDomainObject, Object[] o) {
        if (!StringUtils.isBlank((String) o[6])) {
            studySearchableAjaxableDomainObject.setPrimarySponsorCode((String) o[6]);
        }
    }

    private void updateCoordinatingCenter(StudySearchableAjaxableDomainObject studySearchableAjaxableDomainObject, Object[] o) {
        if (!StringUtils.isBlank((String) o[12])) {
            studySearchableAjaxableDomainObject.setCoordinatingCenterCode((String) o[12]);
        }
    }    

    protected Class getObjectClass() {
        return StudySearchableAjaxableDomainObject.class;


    }


}