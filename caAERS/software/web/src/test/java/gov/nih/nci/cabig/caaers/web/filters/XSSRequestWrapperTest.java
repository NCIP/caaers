/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.filters;


import edu.emory.mathcs.backport.java.util.Arrays;
import gov.nih.nci.cabig.caaers.AbstractTestCase;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.springframework.mock.web.MockHttpServletRequest;

public class XSSRequestWrapperTest extends AbstractTestCase {
	
	private MockHttpServletRequest request;
	private XSSRequestWrapper xssRequestWrapper;
	private Map<String, String> xssTestStrings;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		xssTestStrings = new HashMap<String, String>(){{
			put("test1\0test2","test1test2");
			put("test1<script>alert('1234');</script>test2","test1test2");
			put("test1<iframe src='www.google.com'></iframe>test2","test1</iframe>test2");
			put("test1<iframe>abcd</iframe>test2","test1test2");
			put("test1<img src='www.google.com'></img>test2","test1</img>test2");
			put("test1src='www.google.com'test2","test1test2");
			put("test1</script>test2","test1test2");
			put("test1<script lang='javascript'>test2","test1test2");
			put("test1eval('12+24');test2","test1;test2");
			put("test1expression('12+24');test2","test1;test2");
			put("test1javascript:alert('12+24');test2","test1alert('12+24');test2");
			put("test1javascript:alert('12+24');test2","test1alert('12+24');test2");
			put("test1vbscript:alert('12+24');test2","test1alert('12+24');test2");
			put("test1onload('abc')=alert('12+24');test2","test1alert('12+24');test2");
			}};
		
	}

	public void testGetParameter() {
		for(String key: xssTestStrings.keySet()){
			request = new MockHttpServletRequest();
			xssRequestWrapper = new XSSRequestWrapper(request);
			request.setParameter("param", key);
			assertEquals(xssTestStrings.get(key), xssRequestWrapper.getParameter("param"));
		}
	}
	
	public void testGetParameterValues() {
		request = new MockHttpServletRequest();
		xssRequestWrapper = new XSSRequestWrapper(request);
		String[] keys = new String[xssTestStrings.size()];
		Iterator<String> it = xssTestStrings.keySet().iterator();
		int i=0;
		while (it.hasNext()) {
			keys[i++] = it.next();
			
		}
		request.setParameter("param", keys);
		assertEquals(new HashSet<String>(xssTestStrings.values()), new HashSet(Arrays.asList(xssRequestWrapper.getParameterValues("param"))));
	}
	
	public void testGetHeaderString() {
		for(String key: xssTestStrings.keySet()){
			request = new MockHttpServletRequest();
			xssRequestWrapper = new XSSRequestWrapper(request);
			request.addHeader("header", key);
			assertEquals(xssTestStrings.get(key), xssRequestWrapper.getHeader("header"));
		}
	}

}
