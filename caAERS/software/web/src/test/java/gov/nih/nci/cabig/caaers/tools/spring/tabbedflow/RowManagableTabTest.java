package gov.nih.nci.cabig.caaers.tools.spring.tabbedflow;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.AbstractMutableRetireableDomainObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

public class RowManagableTabTest extends AbstractTestCase {
	
	private RowManagableTab rowManagableTab;
	private MockHttpServletRequest request;
	
	@Override
	protected void setUp() throws Exception {
		rowManagableTab = new RowManagableTab() {
		};request = new MockHttpServletRequest();
	}

	public void testGetSoftDeleteParamName() {
		assertEquals("softDelete", rowManagableTab.getSoftDeleteParamName());
	}

	public void testGetDeleteIndexParamName() {
		assertEquals("deleteIndex", rowManagableTab.getDeleteIndexParamName());
	}

	public void testGetDeleteHashCodeParamName() {
		assertEquals("deleteHashCode", rowManagableTab.getDeleteHashCodeParamName());
	}

	public void testGetCollectionParamName() {
		assertEquals("collection", rowManagableTab.getCollectionParamName());
	}
	
	public void testShouldDelete() {
		assertTrue(rowManagableTab.shouldDelete(request, new Object(), registerMockFor(Errors.class)));
		request.addParameter("softDelete", "true");
		assertFalse(rowManagableTab.shouldDelete(request, new Object(), registerMockFor(Errors.class)));
	}

	public void testDeleteRowIDBased() throws Exception {
		request.addParameter("collection", "objects");
		request.addParameter("deleteHashCode", "ID#1");
		TestDomainObject domainObject1 = new TestDomainObject(1);
		TestDomainObject domainObject2 = new TestDomainObject(2);
		TestDomainObject domainObject3 = new TestDomainObject(3);
		TestCommandObject command = new TestCommandObject(
				Arrays.asList(new TestDomainObject[]{domainObject2, domainObject1, domainObject3})
				);
		ModelAndView mv = rowManagableTab.deleteRow(request, command, registerMockFor(Errors.class));
		assertNotNull(mv.getModel().get("free_text"));
		assertEquals(2, command.getObjects().size());
		
		request.addParameter("softDelete", "true");
		domainObject1 = new TestDomainObject(1);
		domainObject2 = new TestDomainObject(2);
		domainObject3 = new TestDomainObject(3);
		command = new TestCommandObject(
				Arrays.asList(new TestDomainObject[]{domainObject2, domainObject1, domainObject3})
				);
		mv = rowManagableTab.deleteRow(request, command, registerMockFor(Errors.class));
		assertEquals(3, command.getObjects().size());
		assertTrue(domainObject1.isRetired());
	}
	
	public void testDeleteRowHashBased() throws Exception {
		request.addParameter("collection", "objects");
		TestDomainObject domainObject1 = new TestDomainObject(1);
		TestDomainObject domainObject2 = new TestDomainObject(2);
		TestDomainObject domainObject3 = new TestDomainObject(3);
		request.addParameter("deleteHashCode", "HC#"+domainObject1.hashCode());
		TestCommandObject command = new TestCommandObject(
				Arrays.asList(new TestDomainObject[]{domainObject2, domainObject1, domainObject3})
				);
		ModelAndView mv = rowManagableTab.deleteRow(request, command, registerMockFor(Errors.class));
		assertNotNull(mv.getModel().get("free_text"));
		assertEquals(2, command.getObjects().size());
		
		request.addParameter("softDelete", "true");
		command = new TestCommandObject(
				Arrays.asList(new TestDomainObject[]{domainObject2, domainObject1, domainObject3})
				);
		mv = rowManagableTab.deleteRow(request, command, registerMockFor(Errors.class));
		assertEquals(3, command.getObjects().size());
		assertTrue(domainObject1.isRetired());
	}
	
	public class TestCommandObject{
		private List<TestDomainObject> objects = new ArrayList<TestDomainObject>();

		public TestCommandObject(List<TestDomainObject> objects) {
			this.objects.addAll(objects);
		}

		public List<TestDomainObject> getObjects() {
			return objects;
		}

		public void setObjects(List<TestDomainObject> objects) {
			this.objects = objects;
		}
	}
	
	public class TestDomainObject extends AbstractMutableRetireableDomainObject{

		public TestDomainObject(Integer id) {
			super();
			this.setId(id);
		}
		
	}

}
