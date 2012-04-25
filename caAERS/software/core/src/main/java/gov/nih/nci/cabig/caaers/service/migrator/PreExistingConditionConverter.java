package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.domain.PreExistingCondition;
import gov.nih.nci.cabig.caaers.integration.schema.common.PreExistingConditionType;

/**
 *@author Biju Joseph
 */
public class PreExistingConditionConverter {

    public PreExistingCondition convert(PreExistingConditionType preCondtionType){
         PreExistingCondition preCondition = new PreExistingCondition();
         preCondition.setMeddraHlgt(preCondtionType.getMeddraHlgt());
         preCondition.setMeddraLlt(preCondtionType.getMeddraLlt());
         preCondition.setMeddraLltCode(preCondtionType.getMeddraLltCode());
         preCondition.setText(preCondtionType.getText());
         return preCondition;
    }

    public PreExistingConditionType convert(PreExistingCondition preCondtion){
        PreExistingConditionType preConditionType = new PreExistingConditionType();
        preConditionType.setMeddraHlgt(preCondtion.getMeddraHlgt());
        preConditionType.setMeddraLlt(preCondtion.getMeddraLlt());
        preConditionType.setMeddraLltCode(preCondtion.getMeddraLltCode());
        preConditionType.setText(preCondtion.getText());
        return preConditionType;
    }
}
