/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.dao.SearchDao;
import gov.nih.nci.cabig.caaers.domain.Search;
import gov.nih.nci.cabig.caaers.web.WebTestCase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.easymock.classextension.EasyMock;
import org.springframework.core.io.ClassPathResource;
import org.springframework.validation.BindException;

/**
 *
 * @author Ramakrishna
 */
public class AdvancedSearchControllerTest extends WebTestCase {
	
	protected AdvancedSearchController controller;
	protected BindException errors;
	protected AdvancedSearchCommand command;
	protected SearchDao searchDao;
	
	protected void setUp() throws Exception {
		super.setUp();
	
		controller = new AdvancedSearchController();
		searchDao = registerDaoMockFor(SearchDao.class);
		controller.setSearchDao(searchDao);
	}
	public void testFormBackingObject() throws Exception {
		request.setParameter("searchName", "searchName");
		List<Search> searches = new ArrayList<Search>(); 
		Search search = new Search();
		search.setCreatedDate(NOW);
		
		/*File testFile = new ClassPathResource("/gov/nih/nci/cabig/caaers/advancedsearch/saved_advanced_search1.xml").getFile();
        BufferedReader ds = new BufferedReader(new FileReader(testFile));
        String line = null;
        StringBuffer xml = new StringBuffer();
        while ((line = ds.readLine()) != null) {
            xml.append(line.trim());
        }
		
		search.setCriteriaXml(xml.toString());*/
		searches.add(search);
		
		EasyMock.expect(searchDao.getByLoginAndName("SYSTEM", "searchName")).andReturn(searches);
		replayMocks();
		Object object = controller.formBackingObject(request);
		verifyMocks();
		assertTrue(object instanceof AdvancedSearchCommand);
	}
	
	public void testSupressValiation() throws Exception{
		
		request.setAttribute("gov.nih.nci.cabig.caaers.web.search.AdvancedSearchController.PAGE.command", new Integer(1));
		request.setParameter("_target2", "_target2");
		assertFalse(controller.suppressValidation(request));
				
		request.removeParameter("_target2");
		request.setParameter("_target0", "_target0");
		assertTrue(controller.suppressValidation(request));
		
		request.setParameter("subview", "ajax_view");
		assertTrue(controller.suppressValidation(request));
		
	}
	
	
	public void testIsFormSubmission() throws Exception{
		
		assertTrue(controller.isFormSubmission(request));
		
		request.setParameter("runSavedQuery", "runSavedQuery");
		assertTrue(controller.isFormSubmission(request));
		
	}
	
	
	
}
