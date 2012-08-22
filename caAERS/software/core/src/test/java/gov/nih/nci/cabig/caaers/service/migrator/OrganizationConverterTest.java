package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.integration.schema.common.OrganizationType;
import junit.framework.TestCase;

/**
 * @author: Biju Joseph
 */
public class OrganizationConverterTest extends TestCase {
    OrganizationConverter c;
    public void setUp() throws Exception {
        c = new OrganizationConverter();
    }

    public void testConvertOrganizationDtoToDomainOrganization() throws Exception {
        OrganizationType dto =  Fixtures.createOrganizationType("x");
        LocalOrganization org = new LocalOrganization();
        c.convertOrganizationDtoToDomainOrganization(dto, org);
        assertEquals("x", org.getName());

    }
}
