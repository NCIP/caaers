/**
 * 
 */
package gov.nih.nci.cabig.caaers.web.security;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.web.SectionInterceptor;
import gov.nih.nci.cabig.caaers.web.chrome.Section;
import gov.nih.nci.cabig.caaers.web.chrome.Task;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 * 
 */
public class TaskAuthorizationTest extends CaaersTestCase {

	ApplicationContext ctx = null;

	public TaskAuthorizationTest() {
		super();
		ApplicationContext parent = getDeployedApplicationContext();

		String[] locations = new String[] { "WEB-INF/pages-servlet.xml",
				"WEB-INF/applicationContext-acegi-security.xml" };
		MockServletContext servletContext = new MockServletContext(
				"src/main/webapp", new FileSystemResourceLoader());
		XmlWebApplicationContext context = new XmlWebApplicationContext();
		context.setParent(parent);
		context.setServletContext(servletContext);
		context.setConfigLocations(locations);
		context.refresh();
		this.ctx = context;
	}

	public void testAllTasksCovered() {
		TaskPrivilegeAndObjectIdGenerator taskGen = (TaskPrivilegeAndObjectIdGenerator) this.ctx
				.getBean("taskPrivilegeAndObjectIdGenerator");
		SectionInterceptor si = (SectionInterceptor) this.ctx
				.getBean("sectionInterceptor");
		assertNotNull(si);
		List<Section> sections = si.getSections();
		for (Section section : sections) {

			List<Task> tasks = section.getTasks();
			for (Task task : tasks) {

				assertNotNull("No privilege for task " + task.getUrl(), taskGen
						.generatePrivilege(task));
				assertNotNull("No objectId for task " + task.getUrl(), taskGen
						.generateId(task));

			}

		}
	}

	public void testAllSectionsCovered() {
		SectionPrivilegeAndObjectIdGenerator sectionGen = (SectionPrivilegeAndObjectIdGenerator) this.ctx
				.getBean("sectionPrivilegeAndObjectIdGenerator");
		SectionInterceptor si = (SectionInterceptor) this.ctx
				.getBean("sectionInterceptor");
		assertNotNull(si);
		List<Section> sections = si.getSections();
		for (Section section : sections) {

			assertNotNull("No privilege for section " + section.getMainUrl(),
					sectionGen.generatePrivilege(section));
			assertNotNull("No objectId for section " + section.getMainUrl(),
					sectionGen.generateId(section));

		}
	}

}
