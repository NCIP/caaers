package gov.nih.nci.cabig.caaers.domain;

import org.apache.commons.collections.functors.InstantiateFactory;

/**
 * @author Rhett Sutphin
 */
public class AdverseEventReportChildFactory extends InstantiateFactory {
    private AdverseEventReport report;

    public <T extends AdverseEventReportChild> AdverseEventReportChildFactory(
        Class<T> classToInstantiate, AdverseEventReport parent
    ) {
        super(classToInstantiate);
        this.report = parent;
    }

    @Override
    public Object create() {
        AdverseEventReportChild child = (AdverseEventReportChild) super.create();
        child.setReport(report);
        return child;
    }
}
