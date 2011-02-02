package gov.nih.nci.cabig.caaers.domain;

import org.apache.commons.collections15.functors.InstantiateFactory;

 
/**
 * A factory for creating ExpeditedAdverseEventReportChild objects.
 *
 * @param <T> the generic type
 * @author Rhett Sutphin
 */
public class ExpeditedAdverseEventReportChildFactory<T extends ExpeditedAdverseEventReportChild>
                extends InstantiateFactory<T> {
    
    /** The report. */
    private ExpeditedAdverseEventReport report;

    /**
     * Instantiates a new expedited adverse event report child factory.
     *
     * @param classToInstantiate the class to instantiate
     * @param parent the parent
     */
    public ExpeditedAdverseEventReportChildFactory(Class<T> classToInstantiate, ExpeditedAdverseEventReport parent) {
        super(classToInstantiate);
        this.report = parent;
    }

    /* (non-Javadoc)
     * @see org.apache.commons.collections15.functors.InstantiateFactory#create()
     */
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
