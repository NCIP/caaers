/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import org.apache.commons.collections15.functors.InstantiateFactory;
import gov.nih.nci.cabig.caaers.utils.DateUtils;

public class StudyOrganizationChildInstantiateFactory<T extends StudyOrganizationChild> extends InstantiateFactory<T> {
    private StudyOrganization studyOrganization;

    public StudyOrganizationChildInstantiateFactory(StudyOrganization studyOrg, Class<T> classToInit) {
        super(classToInit);
        this.studyOrganization = studyOrg;
    }

    @Override
    public T create() {
        T child = super.create();
        child.setStudyOrganization(studyOrganization);
        child.setStartDate(DateUtils.today());
        return child;
    }
}
