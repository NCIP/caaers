/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.CaaersNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.domain.ConfigProperty;
import gov.nih.nci.cabig.caaers.domain.ConfigPropertyType;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import junit.framework.TestCase;

/**
 * @author: Biju Joseph
 */
public class ConfigPropertyRepositoryImplTest extends CaaersNoSecurityTestCase {
    ConfigPropertyRepositoryImpl impl = null;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        impl = (ConfigPropertyRepositoryImpl)  getDeployedApplicationContext().getBean("configPropertyRepositoryImpl");
    }

    public void testRemoveAll() throws Exception {
        for(int i = 1 ; i < 5 ; i++){
            ConfigProperty cp = Fixtures.createConfigProperty("test" + i);
            cp.setConfigType(ConfigPropertyType.UNKNOWN);
            impl.saveOrUpdate(cp);
        }

        assertEquals(4, impl.getByType(ConfigPropertyType.UNKNOWN).size());
        impl.removeAll(ConfigPropertyType.UNKNOWN);
        assertEquals(0, impl.getByType(ConfigPropertyType.UNKNOWN).size());
    }
}
