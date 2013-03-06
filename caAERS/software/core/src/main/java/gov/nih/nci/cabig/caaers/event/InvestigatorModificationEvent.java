/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.event;

import gov.nih.nci.cabig.ctms.domain.DomainObject;
import org.acegisecurity.Authentication;

/**
 * Raised when Study is modified.
 * @author: Biju Joseph
 */
public class InvestigatorModificationEvent extends EntityModificationEvent {

    public InvestigatorModificationEvent(Authentication authentication, DomainObject entity) {
        super(authentication, entity, EventType.INVESTIGATOR);
    }
}
