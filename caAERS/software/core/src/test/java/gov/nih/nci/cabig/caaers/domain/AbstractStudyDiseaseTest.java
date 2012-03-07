package gov.nih.nci.cabig.caaers.domain;

import junit.framework.TestCase;

/**
 * @author Biju Joseph
 */
public class AbstractStudyDiseaseTest extends TestCase {

    
    public void testEquals(){
        Study s = Fixtures.createStudy("test");
        s.setId(1);

        CtepStudyDisease cd1 = Fixtures.createCtepStudyDisease(s, null);
        StudyCondition   sd1 =Fixtures.createStudyCondition(s, "abcd");
        MeddraStudyDisease md1 = Fixtures.createMeddraStudyDisease(s,null);
        
        String s1 = "hello";
        
        assertTrue(cd1.equals(cd1));
        assertTrue(!cd1.equals(s1));
        assertTrue(sd1.equals(sd1));
        assertTrue(!cd1.equals(sd1));
        assertTrue(!sd1.equals(md1));
        assertTrue(!cd1.equals(md1));
        assertTrue(!md1.equals(s1));
    }
}
