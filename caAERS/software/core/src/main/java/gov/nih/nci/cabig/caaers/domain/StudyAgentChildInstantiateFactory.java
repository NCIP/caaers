package gov.nih.nci.cabig.caaers.domain;

import org.apache.commons.collections15.functors.InstantiateFactory;

 
/**
 * A factory for creating StudyAgentChildInstantiate objects.
 *
 * @param <T> the generic type
 */
public class StudyAgentChildInstantiateFactory<T extends StudyAgentChild> extends
                InstantiateFactory<T> {
    
    /** The study agent. */
    private StudyAgent studyAgent;

    /**
     * Instantiates a new study agent child instantiate factory.
     *
     * @param studyAgent the study agent
     * @param classToInit the class to init
     */
    public StudyAgentChildInstantiateFactory(StudyAgent studyAgent, Class<T> classToInit) {
        super(classToInit);
        this.studyAgent = studyAgent;
    }

    /* (non-Javadoc)
     * @see org.apache.commons.collections15.functors.InstantiateFactory#create()
     */
    @Override
    public T create() {
        T child = super.create();
        child.setStudyAgent(studyAgent);
        return child;
    }
}
