/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import org.apache.commons.collections15.functors.InstantiateFactory;

 
/**
 * A factory for creating StudyChildInstantiate objects.
 *
 * @param <T> the generic type
 */
public class StudyChildInstantiateFactory<T extends StudyChild> extends InstantiateFactory<T> {
    
    /** The study. */
    private Study study;

    /**
     * Instantiates a new study child instantiate factory.
     *
     * @param study the study
     * @param classToInit the class to init
     */
    public StudyChildInstantiateFactory(Study study, Class<T> classToInit) {
        super(classToInit);
        this.study = study;
    }

    /* (non-Javadoc)
     * @see org.apache.commons.collections15.functors.InstantiateFactory#create()
     */
    @Override
    public T create() {
        T child = super.create();
        child.setStudy(study);
        if(child instanceof  Retireable){
            ((Retireable) child).setRetiredIndicator(false);
        }
        return child;
    }
}
