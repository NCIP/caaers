package gov.nih.nci.cabig.caaers.utils.ranking;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Biju Joseph
 */
public class RankBasedSorterUtilsTest extends TestCase {

    
    public void testSort() throws Exception {
        List<String> l = new ArrayList<String>();
          l.add("hello can I call biju jose for a quick meeting joseph said");
          l.add("Joel Biju Joseph");
          l.add("Some one name Biju Joseph has it");
          l.add("Come here for rebiju on rebiju joseph day sometime in the evening says biju today.");
          l.add("biju joseph's world");
          l.add("some body stop me");
          l.add("Biju Joseph");
          l.add("This sentence has biju joseph's name mentioned biju joseph says again biju joseph and cannot say biju jose");
          l.add("Last day was rebiju joseph day in here.");

        List<String> sortedList = RankBasedSorterUtils.sort(l, "biju joseph", new Serializer<String>(){
            public String serialize(String object) {
                return object;  
            }
        });

        assertEquals("Biju Joseph", sortedList.get(0));
        assertEquals("biju joseph's world", sortedList.get(1));
        assertEquals("some body stop me", sortedList.get(8));
        assertEquals("Some one name Biju Joseph has it", sortedList.get(3));
    }
}
