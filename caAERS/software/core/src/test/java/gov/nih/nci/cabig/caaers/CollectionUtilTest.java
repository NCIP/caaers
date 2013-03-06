/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers;

import gov.nih.nci.cabig.caaers.domain.Retireable;
import gov.nih.nci.cabig.caaers.domain.StudyCondition;
import gov.nih.nci.cabig.caaers.domain.Condition;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import junit.framework.TestCase;

/**
 * @author Ion C. Olaru
 */
public class CollectionUtilTest extends TestCase {
    public static void testRetiredObjects() {
        List<StudyCondition> l = new ArrayList<StudyCondition>();

        Condition condition = new Condition();
        condition.setConditionName("A Name");

        StudyCondition sc1 = new StudyCondition();
        sc1.setTerm(condition);
        l.add(sc1);

        StudyCondition sc2 = new StudyCondition();
        sc2.setTerm(condition);
        l.add(sc2);

        StudyCondition sc3 = new StudyCondition();
        sc3.setTerm(condition);
        l.add(sc3);

        assertEquals(3, CollectionUtil.getActiveObjects(l).size());

        sc3.setRetiredIndicator(true);
        assertEquals(2, CollectionUtil.getActiveObjects(l).size());

        sc2.setRetiredIndicator(true);
        assertEquals(1, CollectionUtil.getActiveObjects(l).size());

    }

    public void testSubtract(){
        List<Integer> a = Arrays.asList(new Integer[]{1,2,3});
        List<Integer> b = Arrays.asList(new Integer[]{1,2,4});
        assertEquals(Arrays.asList(new Integer[] {3}), CollectionUtil.subtract(a, b));
        assertEquals(Arrays.asList(new Integer[] {4}), CollectionUtil.subtract(b, a));
        assertEquals(Arrays.asList(new Integer[] {}), CollectionUtil.subtract(a, a));
        try{
            CollectionUtil.subtract(null, a);
            fail("should throw exception");
        }catch(NullPointerException e){

        }
    }
}
