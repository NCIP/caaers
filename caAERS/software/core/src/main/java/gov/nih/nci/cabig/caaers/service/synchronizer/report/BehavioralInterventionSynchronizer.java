package gov.nih.nci.cabig.caaers.service.synchronizer.report;

import gov.nih.nci.cabig.caaers.domain.BehavioralIntervention;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.OtherAEIntervention;

import java.util.List;

/**
 * @author Biju Joseph
 * @since 1.5
 */
public class BehavioralInterventionSynchronizer extends AbstractAEInterventionSynchronizer {
    @Override
    public List<BehavioralIntervention> existingInterventions(ExpeditedAdverseEventReport aeReport) {
        return aeReport.getBehavioralInterventions();
    }

    @Override
    public List<BehavioralIntervention> xmlInterventions(ExpeditedAdverseEventReport aeReport) {
        return aeReport.getBehavioralInterventions();
    }
}
