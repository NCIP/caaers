package gov.nih.nci.cabig.caaers.domain;

import org.apache.commons.collections15.functors.InstantiateFactory;

/**
 * @author Rhett Sutphin
 */
public class AdverseEventPriorTherapyFactory<T> extends InstantiateFactory<T> {
    private AdverseEventPriorTherapy adverseEventPriorTherapy;

    public AdverseEventPriorTherapyFactory(
        Class<T> classToInstantiate, AdverseEventPriorTherapy parent
    ) {
        super(classToInstantiate);
        this.adverseEventPriorTherapy = parent;
    }

    @Override
    public T create() {
        T child = super.create();
        ((PriorTherapyAgent)child).setAdverseEventPriorTherapy(adverseEventPriorTherapy);
        return child;
    }
}
