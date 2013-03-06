/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.domain.PriorTherapy;
import gov.nih.nci.cabig.caaers.integration.schema.common.ActiveInactiveStatusType;
import gov.nih.nci.cabig.caaers.integration.schema.common.PriorTherapyType;

/**
 * @author Biju Joseph
 */
public class PriorTherapyConverter {
    
    public PriorTherapy convert(PriorTherapyType priorTherapyType){
        PriorTherapy priorTherapy = new PriorTherapy();
        priorTherapy.setMeddraCode(priorTherapyType.getMeddraCode());
        priorTherapy.setMeddraTerm(priorTherapyType.getMeddraTerm());
        priorTherapy.setText(priorTherapyType.getText());
        priorTherapy.setTherapyType(priorTherapyType.getTherapyType());
        if (priorTherapyType.getStatus() != null) {
			priorTherapy.setRetiredIndicator(priorTherapyType.getStatus() == ActiveInactiveStatusType.INACTIVE);
		}
        return priorTherapy;
    }
    
    public PriorTherapyType convert(PriorTherapy priorTherapy){
        PriorTherapyType priorTherapyType = new PriorTherapyType();
        priorTherapyType.setMeddraCode(priorTherapy.getMeddraCode());
        priorTherapyType.setMeddraTerm(priorTherapy.getMeddraTerm());
        priorTherapyType.setText(priorTherapy.getText());
        priorTherapyType.setTherapyType(priorTherapy.getTherapyType());
        priorTherapyType.setStatus(priorTherapy.getRetiredIndicator() ? ActiveInactiveStatusType.INACTIVE : ActiveInactiveStatusType.ACTIVE);
        return priorTherapyType;
    }
}
