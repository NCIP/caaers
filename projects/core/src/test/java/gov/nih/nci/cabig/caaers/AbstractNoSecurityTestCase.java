package gov.nih.nci.cabig.caaers;


/**
 * Similar to CaaersTestCase, but will stub-out the AspectJSecurityAspect
 * @author Biju Joseph
 *
 */
public class AbstractNoSecurityTestCase extends CaaersTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		aspect.setSecurityInterceptor(stubInterceptor);
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
}
