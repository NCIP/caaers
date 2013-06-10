package gov.nih.nci.cabig.caaers.service.synchronizer.report;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Biju Joseph
 * @since 1.5
 */
public class ExpeditedAdverseEventSynchronizer implements Migrator<ExpeditedAdverseEventReport> {
    @Override
    public void migrate(ExpeditedAdverseEventReport xmlAeReport, ExpeditedAdverseEventReport dbAeReport, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {

        List<AdverseEvent> newlyFoundAEs = new ArrayList<AdverseEvent>();

        //create an index of AEs
        HashMap<Integer, AdverseEvent> aeIndex = new HashMap<Integer, AdverseEvent>();
        for(AdverseEvent ae : dbAeReport.getAdverseEvents()){ aeIndex.put(ae.getId(), ae);}

        //try to find the AE in source , if found synchronize it.
        for(AdverseEvent ae : xmlAeReport.getAdverseEvents()){
            AdverseEvent aeFound = dbAeReport.findAdverseEventByIdTermAndDates(ae);
            if(aeFound != null) {
                synchronizeAdverseEvent(ae, aeFound);
                aeIndex.remove(aeFound.getId());
            }else {
                newlyFoundAEs.add(ae);
            }
        }

        //delete the AE in destination, which are not present in source
        for(AdverseEvent ae : aeIndex.values()){
            dbAeReport.removeAdverseEvent(ae);
        }
        //add the new AEs that are present in source.
        for(AdverseEvent ae : newlyFoundAEs){
            dbAeReport.addAdverseEvent(ae);
        }
    }

    public void synchronizeAdverseEvent(AdverseEvent xmlAe, AdverseEvent dbAe){
        dbAe.setStartDate(xmlAe.getStartDate());
        dbAe.setEndDate(xmlAe.getEndDate());
    }
}
