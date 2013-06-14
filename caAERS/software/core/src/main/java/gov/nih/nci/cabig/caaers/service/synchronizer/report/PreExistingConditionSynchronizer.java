
/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.synchronizer.report;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.SAEReportPreExistingCondition;
import gov.nih.nci.cabig.caaers.domain.SAEReportPriorTherapy;
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
public class PreExistingConditionSynchronizer implements Migrator<ExpeditedAdverseEventReport> {
    public void migrate(ExpeditedAdverseEventReport src, ExpeditedAdverseEventReport dest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
        List<SAEReportPreExistingCondition> newPreConditions = new ArrayList<SAEReportPreExistingCondition>();
        List<SAEReportPreExistingCondition> existingConditions = new ArrayList<SAEReportPreExistingCondition>();
        if(dest.getSaeReportPreExistingConditions() != null) existingConditions.addAll(dest.getSaeReportPreExistingConditions());

        if(src.getSaeReportPreExistingConditions() != null){
            for(SAEReportPreExistingCondition pc: src.getSaeReportPreExistingConditions()){
                final SAEReportPreExistingCondition xmlPreCondition = pc;
                SAEReportPreExistingCondition found = CollectionUtils.find(existingConditions, new Predicate<SAEReportPreExistingCondition>(){
                    public boolean evaluate(SAEReportPreExistingCondition saeReportPreExistingCondition) {
                        if(saeReportPreExistingCondition.getPreExistingCondition() == null && xmlPreCondition.getPreExistingCondition() == null){
                           return StringUtils.equals(saeReportPreExistingCondition.getOther(), xmlPreCondition.getOther());
                        }
                        return xmlPreCondition.getPreExistingCondition().getId().equals(saeReportPreExistingCondition.getPreExistingCondition().getId());
                    }
                });
                if(found != null){
                    //nothing to sync, just remove the pre-existing condition found
                    existingConditions.remove(found);
                }   else {
                    newPreConditions.add(xmlPreCondition);
                }
            }
        }

        //remove unwanted
        for(SAEReportPreExistingCondition unwanted : existingConditions){
            dest.getSaeReportPreExistingConditions().remove(unwanted);
        }

        //add newly added
        for(SAEReportPreExistingCondition newPc: newPreConditions){
            dest.addSaeReportPreExistingCondition(newPc);
        }

    }
}
