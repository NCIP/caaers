/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.synchronizer.report;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.RadiationIntervention;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Ramakrishna Gundala
 */
public class RadiationInterventionSynchronizer implements Migrator<ExpeditedAdverseEventReport> {
    public void migrate(ExpeditedAdverseEventReport xmlAeReport, ExpeditedAdverseEventReport dbAeReport, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {

        List<RadiationIntervention> newlyFoundRIs = new ArrayList<RadiationIntervention>();

        if (xmlAeReport.getRadiationInterventions() == null) {
            outcome.addWarning("LS-RI-1", "The input for Radiation Interventions is null, so not performing any operation.");
            return;
        }

        //create a map for Radiation Interventions
        HashMap<Integer, RadiationIntervention> riIndex = new HashMap<Integer, RadiationIntervention>();
        for(RadiationIntervention ri : dbAeReport.getRadiationInterventions()){ riIndex.put(ri.getId(), ri);}

        //try to find the Radiation Interventions in source , if found synchronize it.
        for(RadiationIntervention ri : xmlAeReport.getRadiationInterventions()){
        	RadiationIntervention riFound = dbAeReport.findReportRadiationInterventionByAdministrationAndLastTreatmentDate(ri);
            if(riFound != null) {
            	synchronizeRadiationIntervention(ri, riFound);
                riIndex.remove(riFound.getId());
            }else {
                newlyFoundRIs.add(ri);
            }
        }

        //delete the Radiation Interventions in destination, which are not present in source
        for(RadiationIntervention ri : riIndex.values()){
            dbAeReport.removeRadiaitonIntervention(ri);
        }
        //add the new Radiation Interventions that are present in source.
        for(RadiationIntervention ri : newlyFoundRIs){
            dbAeReport.addRadiationIntervention(ri);
        }
    }

    /**
     *  Copy Radiation Details from UserInput.
     * @param xmlRi
     * @param dbRi
     */
	private void synchronizeRadiationIntervention(RadiationIntervention xmlRi, RadiationIntervention dbRi) {

        if (xmlRi.getFractionNumber() != null)
		    dbRi.setFractionNumber(xmlRi.getFractionNumber());
        if (xmlRi.getDosageUnit() != null)
		    dbRi.setDosageUnit(xmlRi.getDosageUnit());
        if (xmlRi.getDosage() != null)
		    dbRi.setDosage(xmlRi.getDosage());
        if (xmlRi.getDaysElapsed() != null)
		    dbRi.setDaysElapsed(xmlRi.getDaysElapsed());
        if (xmlRi.getAdjustment() != null)
		    dbRi.setAdjustment(xmlRi.getAdjustment());
        if (xmlRi.getDescription() != null)
		    dbRi.setDescription(xmlRi.getDescription());
        if (xmlRi.getTreatmentArm() != null)
		    dbRi.setTreatmentArm(xmlRi.getTreatmentArm());
		
	}
}
