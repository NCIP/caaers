package gov.nih.nci.cabig.caaers.service.synchronizer.report;

import gov.nih.nci.cabig.caaers.domain.AbstractAEIntervention;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.OtherAEIntervention;

import java.util.List;

/**
 * @author Biju Joseph
 * @since 1.5
 */
public class OtherAEInterventionSynchronizer extends AbstractAEInterventionSynchronizer {
    @Override
    public List<OtherAEIntervention> existingInterventions(ExpeditedAdverseEventReport aeReport) {
        return aeReport.getOtherAEInterventions();
    }

    @Override
    public List<OtherAEIntervention> xmlInterventions(ExpeditedAdverseEventReport aeReport) {
        return aeReport.getOtherAEInterventions();
    }
}
