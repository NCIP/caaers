/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.PriorTherapyDao;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.PriorTherapy;
import gov.nih.nci.cabig.caaers.integration.schema.common.PriorTherapies;
import gov.nih.nci.cabig.caaers.integration.schema.common.PriorTherapyType;
import junit.framework.TestCase;

import java.util.List;

/**
 * @author Biju Joseph
 */
public class PriorTherapyManagementServiceImplTest extends CaaersDbNoSecurityTestCase {
    PriorTherapyManagementServiceImpl impl;
    PriorTherapyDao dao;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        impl = (PriorTherapyManagementServiceImpl) getApplicationContext().getBean("priorTherapyManagementService");
        dao = impl.getPriorTherapyDao();
    }

    public void testImportPriorTherapies() throws Exception {


        {
            List<PriorTherapy> dbTherapyList = dao.getAll();
            assertEquals(2, dbTherapyList.size());
            PriorTherapy p1 = fetchTherapy(dbTherapyList, "112");
            assertEquals("Head Pain", p1.getMeddraTerm());
            

            PriorTherapies xmlTherapies = new PriorTherapies();
            xmlTherapies.getPriorTherapy().add(Fixtures.createPriorTherapyType("111", "Nausea", "Nausea or Fatigue"));
            xmlTherapies.getPriorTherapy().add(Fixtures.createPriorTherapyType("112", "Headace", "Head Pain or ace"));
            xmlTherapies.getPriorTherapy().add(Fixtures.createPriorTherapyType("113", "Tooth pain", "Toothace"));

            impl.importPriorTherapies(xmlTherapies);


        }
        interruptSession();
        {
            List<PriorTherapy> dbTherapyList = dao.getAll();
            assertEquals(3, dbTherapyList.size());
            PriorTherapy p0 = fetchTherapy(dbTherapyList, "111");
            assertEquals("Nausea or Fatigue", p0.getMeddraTerm());
            PriorTherapy p1 = fetchTherapy(dbTherapyList, "112");
            assertEquals("Head Pain or ace", p1.getMeddraTerm());
            PriorTherapy p2 = fetchTherapy(dbTherapyList, "113");
            assertEquals("Toothace", p2.getMeddraTerm());
        }

    }

    public void testImportPriorTherapiesEmptyList() throws Exception {


        {
            List<PriorTherapy> dbTherapyList = dao.getAll();
            assertEquals(2, dbTherapyList.size());
            PriorTherapy p1 = fetchTherapy(dbTherapyList, "112");
            assertEquals("Head Pain", p1.getMeddraTerm());

            PriorTherapies xmlTherapies = new PriorTherapies();
            impl.importPriorTherapies(xmlTherapies);


        }
        interruptSession();
        {
            List<PriorTherapy> dbTherapyList = dao.getAll();
            assertEquals(2, dbTherapyList.size());
            PriorTherapy p1 = fetchTherapy(dbTherapyList, "112");
            assertEquals("Head Pain", p1.getMeddraTerm());
        }

    }
    
    private PriorTherapy fetchTherapy(List<PriorTherapy> l, String meddraCode){
        for(PriorTherapy p : l) if(p.getMeddraCode().equals(meddraCode)) return p;
        return null;
    }

}
