package gov.nih.nci.cabig.caaers.domain;

import org.apache.commons.collections15.functors.InstantiateFactory;

/**
 * @author Rhett Sutphin
 */
public class ExpeditedAdverseEventReportChildFactory<T extends ExpeditedAdverseEventReportChild>
                extends InstantiateFactory<T> {
    private ExpeditedAdverseEventReport report;

    public ExpeditedAdverseEventReportChildFactory(Class<T> classToInstantiate, ExpeditedAdverseEventReport parent) {
        super(classToInstantiate);
        this.report = parent;
    }

    @Override
    public T create() {
        T child = super.create();
        child.setReport(report);
        
        if(child instanceof AdverseEvent){
        	AdverseEvent ae = (AdverseEvent)child;
        	ae.setReportingPeriod(report.getReportingPeriod());
        }
        return child;
    }
}
