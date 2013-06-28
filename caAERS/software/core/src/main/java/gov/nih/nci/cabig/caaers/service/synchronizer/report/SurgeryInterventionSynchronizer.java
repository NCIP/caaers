/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.synchronizer.report;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.SurgeryIntervention;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Ramakrishna Gundala
 */
public class SurgeryInterventionSynchronizer implements Migrator<ExpeditedAdverseEventReport> {
    public void migrate(ExpeditedAdverseEventReport xmlAeReport, ExpeditedAdverseEventReport dbAeReport, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {

        List<SurgeryIntervention> newlyFoundRIs = new ArrayList<SurgeryIntervention>();

        if (xmlAeReport.getSurgeryInterventions() == null) {
            outcome.addWarning("LS-SI-1", "The input for Surgery Interventions is null, so not performing any operation.");
            return;
        }

        //create a map for Surgery Interventions
        HashMap<Integer, SurgeryIntervention> riIndex = new HashMap<Integer, SurgeryIntervention>();
        for(SurgeryIntervention si : dbAeReport.getSurgeryInterventions()){ riIndex.put(si.getId(), si);}

        //try to find the Surgery Intervention in source , if found synchronize it.
        for(SurgeryIntervention si : xmlAeReport.getSurgeryInterventions()){
        	SurgeryIntervention riFound = dbAeReport.findReportSurgeryInterventionBySiteAndDate(si);
            if(riFound != null) {
            	synchronizeSurgeryIntervention(si, riFound);
                riIndex.remove(riFound.getId());
            }else {
                newlyFoundRIs.add(si);
            }
        }

        //delete the Surgery Interventions in destination, which are not present in source
        for(SurgeryIntervention si : riIndex.values()){
            dbAeReport.removeSurgeryIntervention(si);
        }
        //add the new Surgery Interventions that are present in source.
        for(SurgeryIntervention si : newlyFoundRIs){
            dbAeReport.addSurgeryIntervention(si);
        }
    }

    /**
     *  Copy Surgery Details from UserInput.
     * @param xmlRi
     * @param dbRi
     */
	private void synchronizeSurgeryIntervention(SurgeryIntervention xmlSi, SurgeryIntervention dbSi) {

        if (xmlSi.getDescription() != null)
        	dbSi.setDescription(xmlSi.getDescription());
		
	}
}
