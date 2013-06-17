package gov.nih.nci.cabig.caaers.service.synchronizer.report;

import gov.nih.nci.cabig.caaers.domain.BiologicalIntervention;
import gov.nih.nci.cabig.caaers.domain.DietarySupplementIntervention;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;

import java.util.List;

/**
 * @author Biju Joseph
 * @since 1.5
 */
public class DietarySupplementInterventionSynchronizer extends AbstractAEInterventionSynchronizer {
    @Override
    public List<DietarySupplementIntervention> existingInterventions(ExpeditedAdverseEventReport aeReport) {
        return aeReport.getDietaryInterventions();
    }

    @Override
    public List<DietarySupplementIntervention> xmlInterventions(ExpeditedAdverseEventReport aeReport) {
        return aeReport.getDietaryInterventions();
    }
}
