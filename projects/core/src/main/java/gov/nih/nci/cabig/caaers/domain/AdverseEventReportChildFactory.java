package gov.nih.nci.cabig.caaers.domain;

import org.apache.commons.collections15.functors.InstantiateFactory;

/**
 * @author Rhett Sutphin
 */
public class AdverseEventReportChildFactory<T extends AdverseEventReportChild> extends InstantiateFactory<T> {
    private AdverseEventReport report;

    public AdverseEventReportChildFactory(
        Class<T> classToInstantiate, AdverseEventReport parent
    ) {
        super(classToInstantiate);
        this.report = parent;
    }

    @Override
    public T create() {
        T child = super.create();
        child.setReport(report);
        return child;
    }
}
