/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.rules.common;

import com.semanticbits.rules.brxml.Category;
import com.semanticbits.rules.utils.RuleUtil;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.rules.business.service.CaaersRulesEngineService;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * CaaersRuleUtil Tester.
 *
 * @author Biju Joseph
 * @since <pre>03/09/2010</pre>
 * 
 */
public class CaaersRuleUtilTest extends CaaersTestCase {
    CaaersRulesEngineService caaersRulesEngineService;

    public void setUp() throws Exception {
        super.setUp();
        caaersRulesEngineService = (CaaersRulesEngineService)getDeployedApplicationContext().getBean("caaersRulesEngineService");
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }


    public void testCreateCategory() throws Exception {
        CaaersRuleUtil.createCategory(caaersRulesEngineService.getRuleAuthoringService(), "/A/B/C/D");
        assertTrue(RuleUtil.categoryExist(caaersRulesEngineService.getRuleAuthoringService(), "/A"));
        assertTrue(RuleUtil.categoryExist(caaersRulesEngineService.getRuleAuthoringService(), "/A/B"));
        assertTrue(RuleUtil.categoryExist(caaersRulesEngineService.getRuleAuthoringService(), "/A/B/C"));
    }


    public void testCreateCategoryMultipleTimes() throws Exception {
       Category c1 =  CaaersRuleUtil.createCategory(caaersRulesEngineService.getRuleAuthoringService(), "/A/B/C/D/E");
       Category c2 =  CaaersRuleUtil.createCategory(caaersRulesEngineService.getRuleAuthoringService(), "/A/B/C/D/E");

       assertEquals(c1.getPath(), c2.getPath());
    }


    public void testMultiplexAndEvaluate(){
        B b = new B();
        List<C> cList = new ArrayList<C>();
        cList.add(new C("x"));
        cList.add(new C("y"));
        cList.add(new C("z"));
        b.setcList(cList);

        Z z = new Z(b);

        Map<String , Object> map = CaaersRuleUtil.multiplexAndEvaluate(z, "b.cList[].name");
        assertSame(cList.get(0), map.get("b.cList[0].name"));
        assertSame(cList.get(1), map.get("b.cList[1].name"));
        assertSame(cList.get(2), map.get("b.cList[2].name"));

        B b2 = new B();
        b2.setcList(cList);

        B b3 = new B();
        b3.setcList(cList);

        List<B> bList = new ArrayList<B>();
        bList.add(b);
        bList.add(b2);
        bList.add(b3);

        A a = new A();
        a.setbList(bList);
        map = CaaersRuleUtil.multiplexAndEvaluate(a, "bList[].cList[].name");
        assertEquals(9, map.size());
        assertSame(cList.get(0), map.get("bList[0].cList[0].name"));
        assertSame(cList.get(0), map.get("bList[1].cList[0].name"));
        assertSame(cList.get(0), map.get("bList[2].cList[0].name"));
        assertSame(cList.get(1), map.get("bList[0].cList[1].name"));
        assertSame(cList.get(1), map.get("bList[1].cList[1].name"));
        assertSame(cList.get(1), map.get("bList[2].cList[1].name"));
        assertSame(cList.get(2), map.get("bList[0].cList[2].name"));
        assertSame(cList.get(2), map.get("bList[1].cList[2].name"));
        assertSame(cList.get(2), map.get("bList[2].cList[2].name"));
    }

    class Z {
        B b;
        public Z(B b){ this.b = b;}

        public B getB() {
            return b;
        }

        public void setB(B b) {
            this.b = b;
        }
    }
    class A {
        List<B> bList;

        public List<B> getbList() {
            return bList;
        }

        public void setbList(List<B> bList) {
            this.bList = bList;
        }
    }
    class B {
       List<C> cList;

        public List<C> getcList() {
            return cList;
        }

        public void setcList(List<C> cList) {
            this.cList = cList;
        }
    }
    class C {
        String name;
        public C(String name) { this.name = name;}
        public String getName() {
            return name;
        }
        public void setName(String s){ this.name = s;}
    }


}
