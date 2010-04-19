package gov.nih.nci.cabig.caaers.domain.repository.ajax;

import gov.nih.nci.cabig.caaers.dao.query.ajax.AjaxableDomainObjectQuery;

import java.util.List;

public class AjaxableDomainObjectRepository extends AbstractAjaxableDomainObjectRepository {
	
	public List<Object[]> findObjects(AjaxableDomainObjectQuery query) {
	        List<Object[]> objects = super.find(query);
	        
	        return objects;
	}

}
