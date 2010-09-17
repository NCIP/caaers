package gov.nih.nci.cabig.caaers.utils.ranking;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Biju Joseph
 */
public class RankerTest extends TestCase {

    
    
    public void testRank() throws Exception {

     
        Ranker ranker = new Ranker();

        List<RankedObject<String>> rankedList = new ArrayList<RankedObject<String>>();

        Serializer<String> serializer = new Serializer<String>(){
            public String serialize(String object) {
                return object;
            }
        };

        assertEquals(100000, ranker.rank("Biju jOSEph", "biju Joseph", serializer).getRank());
        assertEquals(995, ranker.rank("Joel Biju Joseph", "biju Joseph", serializer).getRank());
        assertEquals(986, ranker.rank("Some one name Biju Joseph has it", "biju Joseph", serializer).getRank());
        assertEquals(485, ranker.rank("Last day was rebiju joseph day in here", "biju Joseph", serializer).getRank());
        assertEquals(474, ranker.rank("Come here for rebiju on rebiju joseph day sometime in the evening says biju today.", "biju Joseph", serializer).getRank());
        assertEquals(50000, ranker.rank("Biju jOSEph's world", "biju Joseph", serializer).getRank());
        assertEquals(0, ranker.rank("some one stop me", "biju Joseph", serializer).getRank());
        assertEquals(982, ranker.rank("This sentence has biju joseph's name mentioned biju joseph says again biju joseph and cannot say biju jose", "biju Joseph", serializer).getRank());
        assertEquals(0, ranker.rank("hello can I call biju jose for a quick meeting joseph said", "biju Joseph", serializer).getRank());
        assertEquals(977, ranker.rank("M.D Anderson Hospital (MD39)", "MD39", serializer).getRank());
        assertEquals(49999, ranker.rank("(5876) Test Study", "5876", serializer).getRank());

    }
}
