/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.repository.ajax;

import gov.nih.nci.cabig.caaers.dao.query.ajax.AjaxableDomainObjectQuery;

import java.util.List;

 
/**
 * The Class AjaxableDomainObjectRepository.
 */
public class AjaxableDomainObjectRepository extends AbstractAjaxableDomainObjectRepository {
	
	/**
	 * Find objects.
	 *
	 * @param query the query
	 * @return the list
	 */
	public List<Object[]> findObjects(AjaxableDomainObjectQuery query) {
	        List<Object[]> objects = super.find(query);
	        
	        return objects;
	}

}
