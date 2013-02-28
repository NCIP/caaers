/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.DaoTestCase;

import java.util.List;

/**
 * @author Ion C. Olaru
 *         Date: 5/16/12 -2:05 PM
 */
public class PreExistingConditionRepositoryTest extends DaoTestCase {
    PreExistingConditionRepository preExistingConditionRepository;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        preExistingConditionRepository = (PreExistingConditionRepository)getDeployedApplicationContext().getBean("preExistingConditionRepository");
    }

    public void testGetAll() {
        List l = preExistingConditionRepository.getAll(false);
        assertEquals(4, l.size());
    }

    public void testGetAllActive() {
        List l = preExistingConditionRepository.getAll(true);
        assertEquals(2, l.size());
    }

}
