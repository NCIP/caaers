/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.domain.PreExistingCondition;
import gov.nih.nci.cabig.caaers.integration.schema.common.ActiveInactiveStatusType;
import gov.nih.nci.cabig.caaers.integration.schema.common.PreExistingConditionType;

/**
 * @author Biju Joseph
 */
public class PreExistingConditionConverter {

    public PreExistingCondition convert(PreExistingConditionType preCondtionType) {
        PreExistingCondition preCondition = new PreExistingCondition();
        preCondition.setMeddraHlgt(preCondtionType.getMeddraHlgt());
        preCondition.setMeddraLlt(preCondtionType.getMeddraLlt());
        preCondition.setMeddraLltCode(preCondtionType.getMeddraLltCode());
        preCondition.setText(preCondtionType.getText());
        if (preCondtionType.getStatus() != null) {
            preCondition.setRetiredIndicator(preCondtionType.getStatus() == ActiveInactiveStatusType.INACTIVE);
        }
        return preCondition;
    }

    public PreExistingConditionType convert(PreExistingCondition preCondtion) {
        PreExistingConditionType preConditionType = new PreExistingConditionType();
        preConditionType.setMeddraHlgt(preCondtion.getMeddraHlgt());
        preConditionType.setMeddraLlt(preCondtion.getMeddraLlt());
        preConditionType.setMeddraLltCode(preCondtion.getMeddraLltCode());
        preConditionType.setText(preCondtion.getText());
        preConditionType.setStatus(preCondtion.getRetiredIndicator() ? ActiveInactiveStatusType.INACTIVE : ActiveInactiveStatusType.ACTIVE);
        return preConditionType;
    }
}
