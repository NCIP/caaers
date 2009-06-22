package gov.nih.nci.cabig.caaers.domain;

import org.apache.commons.collections15.functors.InstantiateFactory;

public class StudyChildInstantiateFactory<T extends StudyChild> extends InstantiateFactory<T> {
    private Study study;

    public StudyChildInstantiateFactory(Study study, Class<T> classToInit) {
        super(classToInit);
        this.study = study;
    }

    @Override
    public T create() {
        T child = super.create();
        child.setStudy(study);
        return child;
    }
}
