/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import org.apache.commons.collections15.functors.InstantiateFactory;
import gov.nih.nci.cabig.caaers.utils.DateUtils;

 
/**
 * A factory for creating StudyOrganizationChildInstantiate objects.
 *
 * @param <T> the generic type
 */
public class StudyOrganizationChildInstantiateFactory<T extends StudyOrganizationChild> extends InstantiateFactory<T> {
    
    /** The study organization. */
    private StudyOrganization studyOrganization;

    /**
     * Instantiates a new study organization child instantiate factory.
     *
     * @param studyOrg the study org
     * @param classToInit the class to init
     */
    public StudyOrganizationChildInstantiateFactory(StudyOrganization studyOrg, Class<T> classToInit) {
        super(classToInit);
        this.studyOrganization = studyOrg;
    }

    /* (non-Javadoc)
     * @see org.apache.commons.collections15.functors.InstantiateFactory#create()
     */
    @Override
    public T create() {
        T child = super.create();
        child.setStudyOrganization(studyOrganization);
        child.setStartDate(DateUtils.today());
        return child;
    }
}
