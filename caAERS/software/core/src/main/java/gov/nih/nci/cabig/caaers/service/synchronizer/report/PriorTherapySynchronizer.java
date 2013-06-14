package gov.nih.nci.cabig.caaers.service.synchronizer.report;

import com.aparzev.lang.StringUtils;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.PriorTherapy;
import gov.nih.nci.cabig.caaers.domain.PriorTherapyAgent;
import gov.nih.nci.cabig.caaers.domain.SAEReportPriorTherapy;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.collections15.CollectionUtils;
import org.apache.commons.collections15.Predicate;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Biju Joseph
 * @since 1.5
 */
public class PriorTherapySynchronizer implements Migrator<ExpeditedAdverseEventReport> {

    public void migrate(ExpeditedAdverseEventReport xmlAeReport, ExpeditedAdverseEventReport dbAeReport, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {


        List<SAEReportPriorTherapy> newlyAddedPriorTherapies = new ArrayList<SAEReportPriorTherapy>();

        //create a temp list of existing prior therapies
        List<SAEReportPriorTherapy> existingPriorTherapies = new ArrayList<SAEReportPriorTherapy>();
        if(dbAeReport.getSaeReportPriorTherapies() != null){   existingPriorTherapies.addAll(dbAeReport.getSaeReportPriorTherapies()); }
        if(xmlAeReport.getSaeReportPriorTherapies() != null){
           for(SAEReportPriorTherapy xmlPt : xmlAeReport.getSaeReportPriorTherapies()){
               int i = existingPriorTherapies.indexOf(xmlPt);
               SAEReportPriorTherapy found = (i >=0 ? existingPriorTherapies.get(i) : null);
               if(found != null){
                   existingPriorTherapies.remove(found);
                   synchronize(xmlPt, found);
               }else{
                   newlyAddedPriorTherapies.add(xmlPt);
               }
           }
        }


        //remove unwanted
        for(SAEReportPriorTherapy unwanted : existingPriorTherapies){
            dbAeReport.getSaeReportPriorTherapies().remove(unwanted);
        }
        //add newly added
        for(SAEReportPriorTherapy newPt : newlyAddedPriorTherapies){
            dbAeReport.getSaeReportPriorTherapies().add(newPt);
        }

    }

    public void synchronize(SAEReportPriorTherapy xmlPriorTherapy, SAEReportPriorTherapy dbPriorTherapy){
        dbPriorTherapy.setOther(xmlPriorTherapy.getOther());
        dbPriorTherapy.setPriorTherapy(xmlPriorTherapy.getPriorTherapy());
        dbPriorTherapy.setStartDate(xmlPriorTherapy.getStartDate());
        dbPriorTherapy.setEndDate(xmlPriorTherapy.getEndDate());
        dbPriorTherapy.setPriorTherapy(xmlPriorTherapy.getPriorTherapy());

        List<PriorTherapyAgent> existingAgents = new ArrayList<PriorTherapyAgent>();
        if(dbPriorTherapy.getPriorTherapyAgents()!= null) existingAgents.addAll(dbPriorTherapy.getPriorTherapyAgents());
        List<PriorTherapyAgent> newlyAddedAgents = new ArrayList<PriorTherapyAgent>();

        if(xmlPriorTherapy.getPriorTherapyAgents() != null){

            for(PriorTherapyAgent a : xmlPriorTherapy.getPriorTherapyAgents()){
                final PriorTherapyAgent xmlAgent = a;
                PriorTherapyAgent found = CollectionUtils.find(existingAgents, new Predicate<PriorTherapyAgent>(){
                    public boolean evaluate(PriorTherapyAgent priorTherapyAgent) {
                        if(priorTherapyAgent.getAgent() != null && xmlAgent.getAgent() != null){
                            return StringUtils.equals(priorTherapyAgent.getAgent().getNscNumber(), xmlAgent.getAgent().getNscNumber());
                        }
                        return false;
                    }
                }) ;

                if(found != null){
                    //agent is the only modifiable field, so nothing else to update.
                    existingAgents.remove(found);
                }else {
                    newlyAddedAgents.add(xmlAgent);
                }
            }

        }

        //remove the agents that are not present in the xmlPriorTherapy
        for(PriorTherapyAgent unwanted : existingAgents){
            dbPriorTherapy.removePriorTherapyAgent(unwanted);
        }
        //add the newly added agents
        for(PriorTherapyAgent newAgent : newlyAddedAgents){
            dbPriorTherapy.addPriorTherapyAgent(newAgent);
        }

    }
}
