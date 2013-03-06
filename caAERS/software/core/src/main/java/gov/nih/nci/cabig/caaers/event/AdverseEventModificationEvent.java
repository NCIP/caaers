/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.event;

import gov.nih.nci.cabig.ctms.domain.DomainObject;

import org.acegisecurity.Authentication;

public class AdverseEventModificationEvent extends EntityModificationEvent {

    public AdverseEventModificationEvent(Authentication authentication, DomainObject entity) {
        super(authentication, entity, EventType.AE);
    }
}
