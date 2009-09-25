package gov.nih.nci.cabig.caaers.rules.business.service;

import com.semanticbits.rules.objectgraph.FactResolver;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import junit.framework.TestCase;
/**
 * Test cover the following requirement. 
 * CAAERS-2861 - "If attribution is NULL in capture AE, run rules as if it were "possible"
 * 
 * @author Biju Joseph
 *
 */
public class FactResolverTest extends TestCase {
	FactResolver resolver;
	AdverseEvent ae;
	
	protected void setUp() throws Exception {
		super.setUp();
		ae = new AdverseEvent();
		ae.setAttributionSummary(Attribution.DEFINITE);
		resolver = new FactResolver();
	}
	
	
	public void testAssertFact() throws Exception{
		boolean fact = resolver.assertFact(ae,"gov.nih.nci.cabig.caaers.domain.Attribution","name","DEFINITE","==");
		assertTrue(fact);
	}
	

	public void testAssertFact_Negative() throws Exception{
		boolean fact = resolver.assertFact(ae,"gov.nih.nci.cabig.caaers.domain.Attribution","name","UNLIKELY","!=");
		assertTrue(fact);
	}
	
	public void testAssertFact_TargetObjectIsNull() throws Exception{
		ae.setAttributionSummary(null);
		boolean fact = resolver.assertFact(ae,"gov.nih.nci.cabig.caaers.domain.Attribution","name","DEFINITE","==");
		assertFalse(fact);
	}
	
	public void testAssertFact_Negative_TargetObjectIsNull() throws Exception{
		ae.setAttributionSummary(null);
		boolean fact = resolver.assertFact(ae,"gov.nih.nci.cabig.caaers.domain.Attribution","name","UNLIKELY","!=");
		assertTrue(fact);
	}
	
	
}
