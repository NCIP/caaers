/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.repository.ajax;

import gov.nih.nci.cabig.caaers.dao.query.ajax.AjaxableDomainObjectQuery;

import java.util.List;

public class AjaxableDomainObjectRepository extends AbstractAjaxableDomainObjectRepository {
	
	public List<Object[]> findObjects(AjaxableDomainObjectQuery query) {
	        List<Object[]> objects = super.find(query);
	        
	        return objects;
	}

}
