package gov.nih.nci.cabig.caaers.domain.repository.ajax;

import gov.nih.nci.cabig.caaers.dao.query.ajax.AbstractAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySearchableAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySiteAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Biju Joseph
 */

@Transactional(readOnly = true)
public class StudySearchableAjaxableDomainObjectRepository<T extends StudySearchableAjaxableDomainObject> extends AbstractAjaxableDomainObjectRepository {

	private StudyRepository studyRepository;
	
    public List<T> findStudies(final AbstractAjaxableDomainObjectQuery query) {
        return findStudies(query, null, null);

    }
    
	@Transactional(readOnly = false)
    public List<T> findStudies(final AbstractAjaxableDomainObjectQuery query,String type, String text) {

    	List<Object[]> objects = studyRepository.search(query,type,text);
        Map<Integer, T> existingStudyMap = new LinkedHashMap<Integer, T>();


        for (Object[] o : objects) {
        	
        	
            T studySearchableAjaxableDomainObject = existingStudyMap.get((Integer) o[0]);

            if (studySearchableAjaxableDomainObject == null) {
                studySearchableAjaxableDomainObject = (T) BeanUtils.instantiateClass(getObjectClass());
                studySearchableAjaxableDomainObject.setId((Integer) o[0]);
                studySearchableAjaxableDomainObject.setShortTitle((String) o[1]);
                studySearchableAjaxableDomainObject.setPrimaryIdentifierValue((String) o[2]);
                studySearchableAjaxableDomainObject.setPhaseCode((String) o[4]);
                studySearchableAjaxableDomainObject.setStatus((String) o[5]);
                studySearchableAjaxableDomainObject.setExternalId((String)o[6]);
                studySearchableAjaxableDomainObject.setPrimarySponsorCode((String)o[7]);

                existingStudyMap.put(studySearchableAjaxableDomainObject.getId(), studySearchableAjaxableDomainObject);

            } else {
                //update the primary identifier
                 if(BooleanUtils.toBoolean((String)o[3])){
                    studySearchableAjaxableDomainObject.setPrimaryIdentifierValue((String) o[2]);
                 }
            }
        }
        return new ArrayList<T>(existingStudyMap.values());

    }


  

    protected Class getObjectClass() {
        return StudySearchableAjaxableDomainObject.class;
    }
    
	public void setStudyRepository(StudyRepository studyRepository) {
		this.studyRepository = studyRepository;
	}
}