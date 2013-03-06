/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.tags.csm;

import gov.nih.nci.cabig.caaers.domain.LocalStudy;
import gov.nih.nci.cabig.caaers.web.study.DetailsTab;
import gov.nih.nci.cabig.caaers.web.study.StudyDetailsTabTestCase;
import net.sf.ehcache.CacheManager;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

/**
 * AuthorizationDecisionCache Tester.
 *
 * @author Biju Joseph
 * @since <pre>06/30/2010</pre>
 * 
 */
public class AuthorizationDecisionCacheTest extends AbstractTestCase {
    AuthorizationDecisionCache cache;
    public void setUp() throws Exception {
       super.setUp();
       cache = new AuthorizationDecisionCache();
       cache.setMaxElementsToCache(5);
    }

    public void testGetEnityContextCacheKey() throws Exception {
        String s = "hello";
        assertSame(s, cache.getEnityContextCacheKey(s));

        LocalStudy study = new LocalStudy();
        study.setId(3);
        assertEquals("gov.nih.nci.cabig.caaers.domain.LocalStudy_3", cache.getEnityContextCacheKey(study));

        DetailsTab t = new DetailsTab();
        assertEquals("gov.nih.nci.cabig.caaers.web.study.DetailsTab", cache.getEnityContextCacheKey(t));
        
        assertNull(cache.getEnityContextCacheKey(null));
        
    }


    public void testIsAuthorized(){
      assertNull( cache.isAuthorized("","hi", "hello") );
    }

     public void testIsAuthorizedAfterAdd(){
      assertNull( cache.isAuthorized("x","hi", "hello") );
      cache.addDecision("x","hi", "hello", true);
      assertTrue(cache.isAuthorized("x","hi", "hello"));
    }

    public void testAddDecision(){
      
      cache.addDecision("x","hi", "hello", true);
      cache.addDecision("x","hi","man", false);
      
      assertTrue(cache.isAuthorized("x","hi", "hello"));
      assertFalse(cache.isAuthorized("x","hi", "man"));
      assertNull(cache.isAuthorized("x","hi","boy"));

        cache.addDecision("a","hi", "hello", true);
        assertTrue(cache.isAuthorized("a","hi", "hello"));

        cache.addDecision("b","hi", "hello", true);
        cache.addDecision("c","hi", "hello", true);
        cache.addDecision("d","hi", "hello", true);
        cache.addDecision("e","hi", "hello", true);
        cache.addDecision("f","hi", "hello", true);
        cache.addDecision("g","hi", "hello", true);
        
        assertNull(cache.isAuthorized("a","hi", "hello"));
        
        assertTrue(cache.isAuthorized("f","hi", "hello"));
        

    }


    public void testClear(){
      cache.addDecision("x","hi", "hello", true);
      assertTrue(cache.isAuthorized("x","hi", "hello"));

      cache.addDecision("x","hi","man", false);
      cache.clear();

      assertNull(cache.isAuthorized("x", "hi", "hello"));
    }
    
    public void testMaxElementsToCache() throws Exception{
        cache.setMaxElementsToCache(2);
        assertEquals(2, cache.getMaxElementsToCache());
    }
}
