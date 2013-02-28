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
public class PriorTherapyRepositoryTest extends DaoTestCase {
    PriorTherapyRepository priorTherapyRepository;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        priorTherapyRepository = (PriorTherapyRepository)getDeployedApplicationContext().getBean("priorTherapyRepository");
    }

    public void testGetAll() {
        List l = priorTherapyRepository.getAll(false, false);
        assertEquals(4, l.size());
    }

    public void testGetAllActive() {
        List l = priorTherapyRepository.getAll(false, true);
        assertEquals(2, l.size());
    }

    public void testGetAllValid() {
        List l = priorTherapyRepository.getAll(true, false);
        assertEquals(3, l.size());
    }

    public void testGetAllActiveAndValid() {
        List l = priorTherapyRepository.getAll(true, true);
        assertEquals(1, l.size());
    }
}
