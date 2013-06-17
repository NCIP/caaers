package gov.nih.nci.cabig.caaers.service.synchronizer.report;

import gov.nih.nci.cabig.caaers.domain.BiologicalIntervention;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.GeneticIntervention;

import java.util.List;

/**
 * @author Biju Joseph
 * @since 1.5
 */
public class BiologicalInterventionSynchronizer extends AbstractAEInterventionSynchronizer {
    @Override
    public List<BiologicalIntervention> existingInterventions(ExpeditedAdverseEventReport aeReport) {
        return aeReport.getBiologicalInterventions();
    }

    @Override
    public List<BiologicalIntervention> xmlInterventions(ExpeditedAdverseEventReport aeReport) {
        return aeReport.getBiologicalInterventions();
    }
}
