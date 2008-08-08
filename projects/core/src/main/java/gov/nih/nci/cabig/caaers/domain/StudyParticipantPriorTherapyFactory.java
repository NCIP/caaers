package gov.nih.nci.cabig.caaers.domain;

import org.apache.commons.collections15.functors.InstantiateFactory;

/**
 * @author Sameer Sawant
 */
public class StudyParticipantPriorTherapyFactory<T> extends InstantiateFactory<T> {
    private StudyParticipantPriorTherapy priorTherapy;

    public StudyParticipantPriorTherapyFactory(Class<T> classToInstantiate, StudyParticipantPriorTherapy parent) {
        super(classToInstantiate);
        this.priorTherapy = parent;
    }

    @Override
    public T create() {
        T child = super.create();
        ((StudyParticipantPriorTherapyAgent) child).setPriorTherapy(priorTherapy);
        return child;
    }
}
