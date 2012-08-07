package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.dao.AdvancedSearchDao;
import gov.nih.nci.cabig.caaers.dao.SearchDao;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.cabig.caaers.web.search.ui.AdvancedSearchUi;
import gov.nih.nci.cabig.caaers.web.search.ui.ViewColumn;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindException;

/**
 *
 * @author Ramakrishna Gundala
 */
public class ExportSearchResultsControllerTest extends WebTestCase {
	
	protected ExportSearchResultsController controller;
	
	protected SearchDao searchDao;
	protected AdvancedSearchUi advancedSearchUi;
	protected AdvancedSearchDao advancedSearchDao;
	protected AdvancedSearchCommand cmd;
	protected BindException bindException;
	
	protected void setUp() throws Exception {
		super.setUp();
		controller = new ExportSearchResultsController();
		searchDao = registerDaoMockFor(SearchDao.class);
		advancedSearchUi = controller.getAdvancedSearchUi();
		advancedSearchDao = registerMockFor(AdvancedSearchDao.class);
		cmd = registerMockFor(AdvancedSearchCommand.class);
		bindException = registerMockFor(BindException.class);
		List<ViewColumn> viewColumnsList = new ArrayList<ViewColumn>();
		ViewColumn col1 = new ViewColumn();
		col1.setColumnTitle("Short Title");
		col1.setTableTitle("Studies");
		col1.setColumnAttribute("shortTitleText");
		viewColumnsList.add(col1);
		session.setAttribute("resultsViewColumnList", viewColumnsList);
		
		List<AdvancedSearchRow> searchRowList = new ArrayList<AdvancedSearchRow>();
		AdvancedSearchRow row1 = new AdvancedSearchRow();
		List<AdvancedSearchColumn> searchColmnList = new ArrayList<AdvancedSearchColumn>();
		row1.setColumnList(searchColmnList);
		searchRowList.add(row1);
		session.setAttribute("searchResultsRowList", searchRowList);
		
	}
	
	public void testConstructor(){
		assertNotNull(advancedSearchUi);
	}
	
	public void testHandle() throws Exception{
			controller.handle(request, response, cmd, bindException);
			String fileName = System.getProperty("java.io.tmpdir") + File.separator  + ExportSearchResultsController.getXlsSearchResultsFilename();
			File file = new File(fileName);
			assertTrue(file.exists());
			
	}
	
}