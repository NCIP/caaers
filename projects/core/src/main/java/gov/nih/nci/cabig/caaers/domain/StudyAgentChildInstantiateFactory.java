package gov.nih.nci.cabig.caaers.domain;

import org.apache.commons.collections15.functors.InstantiateFactory;

public class StudyAgentChildInstantiateFactory<T extends StudyAgentChild> extends
                InstantiateFactory<T> {
    private StudyAgent studyAgent;

    public StudyAgentChildInstantiateFactory(StudyAgent studyAgent, Class<T> classToInit) {
        super(classToInit);
        this.studyAgent = studyAgent;
    }

    @Override
    public T create() {
        T child = super.create();
        child.setStudyAgent(studyAgent);
        return child;
    }
}
