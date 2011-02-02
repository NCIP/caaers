package gov.nih.nci.cabig.caaers.domain;

import org.apache.commons.collections15.functors.InstantiateFactory;

 
/**
 * A factory for creating SAEReportPriorTherapy objects.
 *
 * @param <T> the generic type
 * @author Rhett Sutphin
 */
public class SAEReportPriorTherapyFactory<T> extends InstantiateFactory<T> {
    
    /** The sae report prior therapy. */
    private SAEReportPriorTherapy saeReportPriorTherapy;

    /**
     * Instantiates a new sAE report prior therapy factory.
     *
     * @param classToInstantiate the class to instantiate
     * @param parent the parent
     */
    public SAEReportPriorTherapyFactory(Class<T> classToInstantiate, SAEReportPriorTherapy parent) {
        super(classToInstantiate);
        this.saeReportPriorTherapy = parent;
    }

    /* (non-Javadoc)
     * @see org.apache.commons.collections15.functors.InstantiateFactory#create()
     */
    @Override
    public T create() {
        T child = super.create();
        ((PriorTherapyAgent) child).setSaeReportPriorTherapy(saeReportPriorTherapy);
        return child;
    }
}
