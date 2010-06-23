package gov.nih.nci.cabig.caaers.web.security;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.web.study.AgentsTab;
import gov.nih.nci.cabig.caaers.web.study.DetailsTab;
import gov.nih.nci.cabig.ctms.web.chrome.Section;
import org.easymock.classextension.EasyMock;

import java.util.HashMap;

/**
 * TabObjectPrivilegeGenerator Tester.
 *
 * @author Biju Joseph
 * @since <pre>06/23/2010</pre>
 * 
 */
public class TabObjectPrivilegeGeneratorTest extends AbstractTestCase {
    TabObjectPrivilegeGenerator gen;

    public void testResolve(){
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("gov.nih.nci.cabig.caaers.web.study.DetailsTab","y");
        gen = new TabObjectPrivilegeGenerator();
        gen.setObjectPrivilegeMap(map);

        DetailsTab t = new DetailsTab();

        assertEquals("y", gen.resolve(t));

        AgentsTab a = new AgentsTab();
        assertNull(gen.resolve(a)) ;
    }

}
