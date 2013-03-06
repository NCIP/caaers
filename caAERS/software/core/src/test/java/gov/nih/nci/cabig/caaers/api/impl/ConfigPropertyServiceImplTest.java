/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.ConfigProperty;
import gov.nih.nci.cabig.caaers.domain.ConfigPropertyType;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.repository.ConfigPropertyRepository;
import gov.nih.nci.cabig.caaers.integration.schema.common.ConfigProperties;
import junit.framework.TestCase;
import org.easymock.EasyMock;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Biju Joseph
 */
public class ConfigPropertyServiceImplTest extends AbstractTestCase {
    ConfigPropertyServiceImpl impl;
    ConfigPropertyRepository repo; 
    public void testCreateOrUpdateConfigProperties() throws Exception {
        repo = registerMockFor(ConfigPropertyRepository.class);
        impl = new ConfigPropertyServiceImpl();
        impl.setConfigPropertyRepository(repo);

        List<ConfigProperty> l = new ArrayList<ConfigProperty>();

        EasyMock.expect(repo.getByType(ConfigPropertyType.AGENT_UOM)).andReturn(l).anyTimes();
        repo.removeAll(ConfigPropertyType.AGENT_UOM);
        repo.saveOrUpdate((ConfigProperty) EasyMock.anyObject());
        replayMocks();

       ConfigProperties c =  Fixtures.createConfigProperties();

        impl.createOrUpdateConfigProperties(c);

        verifyMocks();

    }
}
