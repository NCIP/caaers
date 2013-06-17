package gov.nih.nci.cabig.caaers.service.synchronizer.report;

import gov.nih.nci.cabig.caaers.domain.BehavioralIntervention;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.GeneticIntervention;

import java.util.List;

/**
 * @author Biju Joseph
 * @since 1.5
 */
public class GeneticInterventionSynchronizer extends AbstractAEInterventionSynchronizer {
    @Override
    public List<GeneticIntervention> existingInterventions(ExpeditedAdverseEventReport aeReport) {
        return aeReport.getGeneticInterventions();
    }

    @Override
    public List<GeneticIntervention> xmlInterventions(ExpeditedAdverseEventReport aeReport) {
        return aeReport.getGeneticInterventions();
    }
}
