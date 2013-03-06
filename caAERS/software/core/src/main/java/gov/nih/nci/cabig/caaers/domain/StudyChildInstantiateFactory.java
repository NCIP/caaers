/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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
