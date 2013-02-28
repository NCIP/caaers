/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao.query.ajax;

import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;

/**
 * @author Biju Joseph
 */
public class AbstractAjaxableDomainObjectQuery extends AbstractQuery {
    public AbstractAjaxableDomainObjectQuery(final String queryString) {
        super(queryString);
    }
}
