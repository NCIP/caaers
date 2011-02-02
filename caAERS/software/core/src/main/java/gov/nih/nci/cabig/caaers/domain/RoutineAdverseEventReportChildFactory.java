package gov.nih.nci.cabig.caaers.domain;

import org.apache.commons.collections15.functors.InstantiateFactory;

 
/**
 * A factory for creating RoutineAdverseEventReportChild objects.
 *
 * @param <T> the generic type
 * @author Rhett Sutphin
 */
public class RoutineAdverseEventReportChildFactory<T extends RoutineAdverseEventReportChild>
                extends InstantiateFactory<T> {
    
    /** The report. */
    private RoutineAdverseEventReport report;

    /**
     * Instantiates a new routine adverse event report child factory.
     *
     * @param classToInstantiate the class to instantiate
     * @param parent the parent
     */
    public RoutineAdverseEventReportChildFactory(Class<T> classToInstantiate,
                    RoutineAdverseEventReport parent) {
        super(classToInstantiate);
        this.report = parent;
    }

    /* (non-Javadoc)
     * @see org.apache.commons.collections15.functors.InstantiateFactory#create()
     */
    @Override
    public T create() {
        T child = super.create();
        child.setRoutineReport(report);
        return child;
    }
}
