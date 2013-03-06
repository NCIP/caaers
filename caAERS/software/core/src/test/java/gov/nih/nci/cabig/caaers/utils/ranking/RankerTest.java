/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.utils.ranking;

import junit.framework.TestCase;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Biju Joseph
 */
public class RankerTest extends TestCase {

    
    
    public void testRank() throws Exception {

     
        Ranker ranker = new Ranker("biju Joseph");

        List<RankedObject<String>> rankedList = new ArrayList<RankedObject<String>>();

        Serializer<String> serializer = new Serializer<String>(){
            public String serialize(String object) {
                return object;
            }
        };

        assertEquals(100000, ranker.rank("Biju jOSEph", serializer).getRank());
        assertEquals(9995, ranker.rank("Joel Biju Joseph", serializer).getRank());
        assertEquals(9986, ranker.rank("Some one name Biju Joseph has it", serializer).getRank());
        assertEquals(485, ranker.rank("Last day was rebiju joseph day in here",  serializer).getRank());
        assertEquals(474, ranker.rank("Come here for rebiju on rebiju joseph day sometime in the evening says biju today.", serializer).getRank());
        assertEquals(50000, ranker.rank("Biju jOSEph's world",  serializer).getRank());
        assertEquals(0, ranker.rank("some one stop me",  serializer).getRank());
        assertEquals(982, ranker.rank("This sentence has biju joseph's name mentioned biju joseph says again biju joseph and cannot say biju jose",serializer).getRank());
        assertEquals(0, ranker.rank("hello can I call biju jose for a quick meeting joseph said", serializer).getRank());

        ranker = new Ranker("MD39");

        assertEquals(9977, ranker.rank("M.D Anderson Hospital (MD39)",serializer).getRank());

        ranker = new Ranker("5876");
        assertEquals(49999, ranker.rank("(5876) Test Study",  serializer).getRank());
        assertEquals(9995, ranker.rank("jai (5876) Test Study",  serializer).getRank());
        assertEquals(9990, ranker.rank("jai hind (5876)",  serializer).getRank());

        ranker = new Ranker("study");
        assertEquals(9988, ranker.rank("(5876) Test Study",  serializer).getRank());

        ranker = new Ranker("(5");
        assertEquals(50000, ranker.rank("(5876) Test Study",  serializer).getRank());


        ranker = new Ranker("76)");
        assertEquals(497, ranker.rank("(5876) Test Study",  serializer).getRank());

        ranker = new Ranker("md5");
        assertEquals(49999, ranker.rank("(MD53) hello biju",  serializer).getRank());

    }
}
