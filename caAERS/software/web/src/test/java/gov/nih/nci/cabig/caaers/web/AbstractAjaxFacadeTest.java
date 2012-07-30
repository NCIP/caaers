package gov.nih.nci.cabig.caaers.web;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

public class AbstractAjaxFacadeTest extends TestCase {
	
	private AbstractAjaxFacade abstractAjaxFacade;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		abstractAjaxFacade = new AbstractAjaxFacade() {
			
			@Override
			public Class<?>[] controllers() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}

	public void testCreateQueryString() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("x", "y");
		map.put("p", "q");
		assertEquals("p=q&x=y", abstractAjaxFacade.createQueryString(map));
	}

}
