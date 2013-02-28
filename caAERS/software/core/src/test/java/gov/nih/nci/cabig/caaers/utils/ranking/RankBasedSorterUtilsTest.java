/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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


    public void testSort2() throws Exception {
        List<String> l = new ArrayList<String>();
        l.add("hello biju");
        l.add("( MD5 ) hello biju");
        l.add("(MD53) hello biju");
        l.add("Some one name Biju Joseph has it");
        l.add("There is a problem with MD5 checksum");
        l.add("checksum (MD5)");
        l.add("hello ( MD5 )");
        l.add("(MD5) cannot be empty");

        List<String> sortedList = RankBasedSorterUtils.sort(l, "md5", new Serializer<String>(){
            public String serialize(String object) {
                return object;
            }
        });


        assertEquals("(MD53) hello biju", sortedList.get(0));
        assertEquals("(MD5) cannot be empty", sortedList.get(1));
        assertEquals("Some one name Biju Joseph has it", sortedList.get(7));
        assertEquals("( MD5 ) hello biju", sortedList.get(2));
        assertEquals("hello ( MD5 )", sortedList.get(3));
    }

    public void testSort3() throws Exception {
        List<String> l = new ArrayList<String>();
        l.add("Some one name Biju Joseph has it");
        l.add("james said ( MD5 ) hello biju");
        l.add("Hello (MD53) hello biju");
        l.add("Oh my god");
        l.add("There is a problem with MD5 checksum");
        l.add("checksum (MD5)");
        l.add("do you see what i see");
        l.add("hello ( MD5 )");
        l.add("The (MD54) cannot be empty");
        l.add("comeon lets go");

        List<String> sortedList = RankBasedSorterUtils.sort(l, "md5", new Serializer<String>(){
            public String serialize(String object) {
                return object;
            }
        });
        assertEquals("hello ( MD5 )", sortedList.get(0));
        assertEquals("checksum (MD5)", sortedList.get(1));
        assertEquals("james said ( MD5 ) hello biju", sortedList.get(2));
        assertEquals("There is a problem with MD5 checksum",sortedList.get(3));
        assertEquals("The (MD54) cannot be empty",sortedList.get(4));
        assertEquals("Hello (MD53) hello biju",sortedList.get(5));

    }
}
