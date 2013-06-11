/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.synchronizer.report;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Lab;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author medaV
 */
public class LabSynchronizer implements Migrator<ExpeditedAdverseEventReport> {
    public void migrate(ExpeditedAdverseEventReport xmlAeReport, ExpeditedAdverseEventReport dbAeReport, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {

        List<Lab> newlyFoundLabs = new ArrayList<Lab>();

        if (xmlAeReport.getLabs() == null) {
            outcome.addWarning("LS-WR-1", "The input for Labs is null, so not performing any operation.");
            return;
        }

        //create an index of AEs
        HashMap<Integer, Lab> labsIndex = new HashMap<Integer, Lab>();
        for(Lab lab : dbAeReport.getLabs()){ labsIndex.put(lab.getId(), lab);}

        //try to find the AE in source , if found synchronize it.
        for(Lab lab : xmlAeReport.getLabs()){
            Lab labFound = dbAeReport.findLabsByIDTerm(lab);
            if(labFound != null) {
                synchronizeLab(lab, labFound);
                labsIndex.remove(lab.getId());
            }else {
                newlyFoundLabs.add(lab);
            }
        }

        //delete the AE in destination, which are not present in source
        for(Lab lab : labsIndex.values()){
            dbAeReport.removeLab(lab);
        }
        //add the new AEs that are present in source.
        for(Lab lab : newlyFoundLabs){
            dbAeReport.addLab(lab);
        }
    }

    /**
     * Copy the values from the XML input to db Lab value.
     * @param xmlLab
     * @param dbLab
     */
    public void synchronizeLab(Lab xmlLab, Lab dbLab){

        dbLab.setUnits(xmlLab.getUnits());
        dbLab.setBaseline(xmlLab.getBaseline());
        dbLab.setNadir(xmlLab.getNadir());
        dbLab.setRecovery(xmlLab.getRecovery());
        dbLab.setOther(xmlLab.getOther());
        dbLab.setInfectiousAgent(xmlLab.getInfectiousAgent());
        dbLab.setLabDate(xmlLab.getLabDate());
        dbLab.setSite(xmlLab.getSite());
        dbLab.setNormalRange(xmlLab.getNormalRange());

        // Set the Term details.
        dbLab.getLabTerm().setTerm(xmlLab.getLabTerm().getTerm());
        dbLab.getLabTerm().setCategory(xmlLab.getLabTerm().getCategory());
    }
}
