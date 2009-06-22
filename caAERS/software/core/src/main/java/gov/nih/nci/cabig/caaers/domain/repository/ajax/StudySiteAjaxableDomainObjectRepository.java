package gov.nih.nci.cabig.caaers.domain.repository.ajax;

import gov.nih.nci.cabig.caaers.dao.query.ajax.StudySiteAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySiteAjaxableDomainObject;

import java.util.ArrayList;
import java.util.List;

public class StudySiteAjaxableDomainObjectRepository extends AbstractAjaxableDomainObjectRepository{
	
	public List<StudySiteAjaxableDomainObject> findStudySites(StudySiteAjaxableDomainObjectQuery query){
		List<Object[]> objects = super.find(query);
		List<StudySiteAjaxableDomainObject> results = new ArrayList<StudySiteAjaxableDomainObject>();
		for(Object[] o : objects){
			StudySiteAjaxableDomainObject result = new StudySiteAjaxableDomainObject();
			result.setId((Integer) o[0]);
			result.setName((String)o[1]);
			results.add(result);
		}
		
		return results;		
	}
	
	
}
