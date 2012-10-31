package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.dto.AdverseEventDTO;
import gov.nih.nci.cabig.caaers.domain.dto.TermDTO;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: Biju Joseph
 */
public class AdverseEventReconciliationCommandTest extends TestCase {

    public void testFind(){

        AdverseEventDTO ae1 =   Fixtures.createAdverseEventDTO(1, 101, "4490", "Pain", "Mild", "10/10/2011", "10/11/2011", "Redness in eye", "Hospitalized", "Likely");
        ae1.setSource("Force");
        ae1.setExternalID("91");

        AdverseEventDTO ae2 = Fixtures.createAdverseEventDTO(14, 104, "5492", "Lymphopenia", "Moderate", "10/09/2011", "10/13/2011", "", "", "Unrelated");
        ae2.setSource("Force");
        ae1.setExternalID("92");

        AdverseEventDTO ae3 =   Fixtures.createAdverseEventDTO(22, 1043, "5491", "Vomiting", "Mild", "10/10/2011", "10/11/2011", "Redness", "Hospitalized", "Likely");
        ae1.setSource("Force");
        ae1.setExternalID("93");

        AdverseEventDTO ae4 =   Fixtures.createAdverseEventDTO(1, 101, "4490", "Pain", "Mild", "10/10/2011", "10/11/2011", "Redness in eyes", "Hospitalized", "Unrelated");
        ae4.setSource("Force");
        ae4.setExternalID("91");

        AdverseEventDTO ae =   Fixtures.createAdverseEventDTO(1, 101, "4490", "Pain", "Mild", "10/10/2011", "10/11/2011", "Redness in eye", "Hospitalized", "Likely");
        ae.setSource("caAERS");
        ae.setExternalID("91");

        AdverseEventDTO junkAE =   Fixtures.createAdverseEventDTO(1, 101, "44907", "Painx", "Severe", "10/10/2011", "10/11/2011", "Redness in eye", "Hospitalized", "Likely");
        junkAE.setSource("caAERS");
        junkAE.setExternalID("941");

        {
            List<AdverseEventDTO> list = Arrays.asList(ae1, ae2, ae3);
            AdverseEventDTO found = AdverseEventReconciliationCommand.find(ae, list);
            assertSame(ae1, found);
        }
        {
            List<AdverseEventDTO> list = Arrays.asList(ae1, ae2, ae3, ae4);
            AdverseEventDTO found = AdverseEventReconciliationCommand.find(ae, list);
            assertNull(found);
        }

        {
            List<AdverseEventDTO> list = Arrays.asList(ae1, ae2, ae3);
            AdverseEventDTO found = AdverseEventReconciliationCommand.find(junkAE, list);
            assertNull(found);
        }

    }

}
