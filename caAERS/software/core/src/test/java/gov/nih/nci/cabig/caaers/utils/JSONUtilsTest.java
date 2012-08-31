package gov.nih.nci.cabig.caaers.utils;

import gov.nih.nci.cabig.caaers.domain.dto.AdverseEventDTO;
import gov.nih.nci.cabig.caaers.domain.dto.TermDTO;
import junit.framework.TestCase;

/**
 * @author: Biju Joseph
 */
public class JSONUtilsTest extends TestCase {
    public void testToJSON() throws Exception {
        AdverseEventDTO ae = new AdverseEventDTO();
        TermDTO t = new TermDTO();
        t.setCode("1001");
        t.setId(1);
        t.setName("Nausea");
        ae.setTerm(t);
        ae.setGrade("NORMAL");
        ae.setHosptialization("YES");
        ae.setExpected("true");
        ae.setAttribution("LIKELY");
        ae.setVerbatim("Sick to Stomach");
        
        String s = JSONUtils.toJSON(ae);
        System.out.println(s);

    }
}
