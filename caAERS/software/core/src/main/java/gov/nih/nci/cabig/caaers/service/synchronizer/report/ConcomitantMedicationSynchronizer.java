
/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.synchronizer.report;

import gov.nih.nci.cabig.caaers.domain.ConcomitantMedication;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;
import org.apache.commons.collections15.CollectionUtils;
import org.apache.commons.collections15.Predicate;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Biju Joseph
 * @since 1.5
 */
public class ConcomitantMedicationSynchronizer implements Migrator<ExpeditedAdverseEventReport> {
    public void migrate(ExpeditedAdverseEventReport src, ExpeditedAdverseEventReport dest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
        List<ConcomitantMedication> newConmeds = new ArrayList<ConcomitantMedication>();
        List<ConcomitantMedication> existingConmeds = new ArrayList<ConcomitantMedication>();
        if(dest.getConcomitantMedications() != null) existingConmeds.addAll(dest.getConcomitantMedications());

        if(src.getConcomitantMedications() != null){
            for(ConcomitantMedication conMed : src.getConcomitantMedications()){
                final ConcomitantMedication xmlConmed = conMed;
                ConcomitantMedication found = CollectionUtils.find(existingConmeds, new Predicate<ConcomitantMedication>(){
                    public boolean evaluate(ConcomitantMedication concomitantMedication) {
                        return StringUtils.equals(concomitantMedication.getAgentName(), xmlConmed.getAgentName());
                    }
                });
                if(found != null){
                    found.setEndDate(xmlConmed.getEndDate());
                    found.setStartDate(xmlConmed.getStartDate());
                    found.setStillTakingMedications(xmlConmed.getStillTakingMedications());
                    //remove the pre-existing condition found
                    existingConmeds.remove(found);
                }   else {
                    newConmeds.add(xmlConmed);
                }
            }
        }

        //remove unwanted
        for(ConcomitantMedication unwanted : existingConmeds){
            dest.getConcomitantMedications().remove(unwanted);
        }

        //add newly added
        for(ConcomitantMedication newPc: newConmeds){
            dest.addConcomitantMedication(newPc);
        }

    }
}
