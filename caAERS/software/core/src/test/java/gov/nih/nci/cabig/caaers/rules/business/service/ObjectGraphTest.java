package gov.nih.nci.cabig.caaers.rules.business.service;

import junit.framework.TestCase;

import com.semanticbits.rules.objectgraph.NavigationPath;
import com.semanticbits.rules.objectgraph.ObjectGraphFactory;
/**
 * Refactored the testcase, to only test objectGraphFactory
 * @author Biju Joseph
 * @author Monish Dombla (original author)
 *
 */
public class ObjectGraphTest extends TestCase {

    private ObjectGraphFactory objectGraphFactory;

    protected void setUp() throws Exception {
        objectGraphFactory = ObjectGraphFactory.getInstance();
    }
    
    public void testSingetone() throws Exception{
    	assertSame(objectGraphFactory, ObjectGraphFactory.getInstance());
    }
    
    /**
     * Source : gov.nih.nci.cabig.caaers.domain.Study
     * Target : gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug
     */
    public void testNavigationPath_Study_IND(){
    	String sourceObjectType = "gov.nih.nci.cabig.caaers.domain.Study";
    	String targetObjectType = "gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug";
    	NavigationPath np = objectGraphFactory.findNavigationPath(sourceObjectType, targetObjectType);
    	assertNotNull(np);
    }
    
    
    /**
     * Source : gov.nih.nci.cabig.caaers.domain.LocalStudy
     * Target : gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug
     */
    public void testNavigationPath_LocalStudy_IND(){
    	String sourceObjectType = "gov.nih.nci.cabig.caaers.domain.LocalStudy";
    	String targetObjectType = "gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug";
    	NavigationPath np = objectGraphFactory.findNavigationPath(sourceObjectType, targetObjectType);
    	assertNotNull(np);
    }
    
    
    /**
     * Source : gov.nih.nci.cabig.caaers.domain.RemoteStudy
     * Target : gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug
     */
    public void testNavigationPath_RemoteStudy_IND(){
    	String sourceObjectType = "gov.nih.nci.cabig.caaers.domain.RemoteStudy";
    	String targetObjectType = "gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug";
    	NavigationPath np = objectGraphFactory.findNavigationPath(sourceObjectType, targetObjectType);
    	assertNotNull(np);
    }
    
    
}
