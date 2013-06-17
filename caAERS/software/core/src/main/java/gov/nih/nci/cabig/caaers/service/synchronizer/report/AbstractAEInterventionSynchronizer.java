package gov.nih.nci.cabig.caaers.service.synchronizer.report;

import gov.nih.nci.cabig.caaers.domain.AbstractAEIntervention;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;
import org.apache.commons.collections15.CollectionUtils;
import org.apache.commons.collections15.Predicate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Biju Joseph
 * @since 1.5
 */
public abstract class AbstractAEInterventionSynchronizer implements Migrator<ExpeditedAdverseEventReport> {
    public void migrate(ExpeditedAdverseEventReport src, ExpeditedAdverseEventReport dest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
        List<AbstractAEIntervention>  newlyAddedInterventions = new ArrayList<AbstractAEIntervention>();
        List<AbstractAEIntervention>  existingInterventions = new ArrayList<AbstractAEIntervention>();
        if(existingInterventions(dest) != null) existingInterventions.addAll(existingInterventions(dest));
        if(xmlInterventions(src) != null){
            for(AbstractAEIntervention ai : xmlInterventions(src)){
                final AbstractAEIntervention xmlAeIntervention = ai;
                AbstractAEIntervention found = CollectionUtils.find(existingInterventions, new Predicate<AbstractAEIntervention>() {
                    public boolean evaluate(AbstractAEIntervention abstractAEIntervention) {
                        if(xmlAeIntervention.getStudyIntervention() == null || abstractAEIntervention.getStudyIntervention() == null) return false;
                        return xmlAeIntervention.getStudyIntervention().getId().equals(abstractAEIntervention.getStudyIntervention().getId());
                    }
                });
                if(found != null){
                    found.setDescription(xmlAeIntervention.getDescription());
                    existingInterventions.remove(found);
                } else {
                    newlyAddedInterventions.add(xmlAeIntervention);
                }
            }
        }

        for(AbstractAEIntervention ai : existingInterventions){
            dest.cascaeDeleteToAttributions(ai);
            existingInterventions(dest).remove(ai);
        }

        for(AbstractAEIntervention ai : newlyAddedInterventions){
           dest.addAbstractAEIntervention(ai);
        }
    }

    public abstract List<? extends AbstractAEIntervention> existingInterventions(ExpeditedAdverseEventReport aeReport);
    public abstract List<? extends AbstractAEIntervention> xmlInterventions(ExpeditedAdverseEventReport aeReport);
}
