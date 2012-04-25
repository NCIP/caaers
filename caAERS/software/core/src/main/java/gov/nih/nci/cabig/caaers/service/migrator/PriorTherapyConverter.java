package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.domain.PriorTherapy;
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
        return priorTherapy;
    }
    
    public PriorTherapyType convert(PriorTherapy priorTherapy){
        PriorTherapyType priorTherapyType = new PriorTherapyType();
        priorTherapyType.setMeddraCode(priorTherapy.getMeddraCode());
        priorTherapyType.setMeddraTerm(priorTherapy.getMeddraTerm());
        priorTherapyType.setText(priorTherapy.getText());
        priorTherapyType.setTherapyType(priorTherapy.getTherapyType());
        return priorTherapyType;
    }
}
