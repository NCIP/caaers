package gov.nih.nci.cabig.caaers.domain.repository.ajax;

import gov.nih.nci.cabig.caaers.dao.query.ajax.ParticipantAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.domain.ajax.AbstractAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.ajax.ParticipantAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySearchableAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySiteAjaxableDomainObject;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Biju Joseph
 */

@Transactional(readOnly = true)
public class ParticipantAjaxableDomainObjectRepository<T extends ParticipantAjaxableDomainObject> extends AbstractAjaxableDomainObjectRepository {
    
	public List<T> findParticipants(ParticipantAjaxableDomainObjectQuery query) {
        List<Object[]> objects = super.find(query);
        List<T> participantAjaxableDomainObjects = new ArrayList<T>();

        for (Object[] o : objects) {
            T participantAjaxableDomainObject = (T) getObjectById(participantAjaxableDomainObjects, (Integer) o[0]);

            if (participantAjaxableDomainObject == null) {
                participantAjaxableDomainObject = (T) BeanUtils.instantiateClass(getObjectClass());
                participantAjaxableDomainObject.setId((Integer) o[0]);
                participantAjaxableDomainObject.setFirstName((String) o[1]);
                participantAjaxableDomainObject.setLastName((String) o[2]);
                participantAjaxableDomainObject.setGender((String) o[3]);
                participantAjaxableDomainObject.setRace((String) o[4]);
                participantAjaxableDomainObject.setEthnicity((String) o[5]);
                if (o[7] != null && (Boolean) o[7]) {
                    participantAjaxableDomainObject.setPrimaryIdentifierValue((String) o[6]);
                }
                addAdditionalProperties(participantAjaxableDomainObject, o);
                participantAjaxableDomainObjects.add(participantAjaxableDomainObject);
                

            } else  {
            	updateAdditionalProperties(participantAjaxableDomainObject, o);
            	
            }

        }
        return participantAjaxableDomainObjects;

    }
    
    protected void updateAdditionalProperties(T participantAjaxableDomainObject, Object[] o) {
    	if (o[7] != null && (Boolean) o[7]) {
            participantAjaxableDomainObject.setPrimaryIdentifierValue((String) o[6]);
        }
    	updateStudySite(participantAjaxableDomainObject, o);
    	updateStudy(participantAjaxableDomainObject, o);


    }

    protected void addAdditionalProperties(T participantAjaxableDomainObject, Object[] o) {
    	updateStudySite(participantAjaxableDomainObject, o);
    	updateStudy(participantAjaxableDomainObject, o);
    }

    private void updateStudySite(T participantAjaxableDomainObject, Object[] o) {
        if (!StringUtils.isBlank((String) o[12]) && StringUtils.equals((String) o[14], "SST")) {
            StudySiteAjaxableDomainObject studySiteAjaxableDomainObject = new StudySiteAjaxableDomainObject();
            studySiteAjaxableDomainObject.setId((Integer) o[13]);
            studySiteAjaxableDomainObject.setName((String) o[12]);
            studySiteAjaxableDomainObject.setNciInstituteCode((String) o[15]);
            participantAjaxableDomainObject.addStudySite(studySiteAjaxableDomainObject);
        }
    }
    
    private void updateStudy(T participantAjaxableDomainObject, Object[] o) {
        if (!StringUtils.isBlank((String) o[8])) {
        	StudySearchableAjaxableDomainObject studySearchableAjaxableDomainObject = new StudySearchableAjaxableDomainObject();
        	studySearchableAjaxableDomainObject.setId((Integer) o[9]);
        	studySearchableAjaxableDomainObject.setShortTitle((String) o[8]);
            if (o[11] != null && (Boolean) o[11]) {
            	studySearchableAjaxableDomainObject.setPrimaryIdentifierValue((String) o[10]);
            }
            if (o[16] != null) {
            	studySearchableAjaxableDomainObject.addStudyPersonnelId((Integer) o[16]);
            }
            participantAjaxableDomainObject.addStudy(studySearchableAjaxableDomainObject);
        }
    }  
    
    protected Class getObjectClass() {
        return ParticipantAjaxableDomainObject.class;
    }
    
   
}