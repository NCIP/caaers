package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;
import gov.nih.nci.cabig.caaers.web.search.ui.AdvancedSearchUi;
import gov.nih.nci.cabig.caaers.web.search.ui.SearchTargetObject;
import gov.nih.nci.cabig.caaers.web.search.ui.ViewColumn;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class AdvancedSearchCommandTest extends TestCase{
	
	protected AdvancedSearchCommand cmd;
	protected AdvancedSearchUi advancedSearchUi;
	protected List<AdvancedSearchCriteriaParameter> criteriaParameters;
	protected SearchTargetObject searchTargetObject;
	protected String searchName;
	protected String searchDescription;
	protected List<AbstractQuery> hql;
	protected List<ViewColumn> resultsViewColumnList;
	protected Integer numberOfResults;
	protected List<AdvancedSearchRow> advancedSearchRowList;
	protected List<String> aliasList = new ArrayList<String>();
	
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		cmd = new AdvancedSearchCommand();
		hql =  new ArrayList<AbstractQuery>();
		resultsViewColumnList = new ArrayList<ViewColumn>();
		aliasList = new ArrayList<String>();
		
		cmd.setAdvancedSearchRowList(advancedSearchRowList);
		cmd.setAdvancedSearchUi(advancedSearchUi);
		cmd.setAliasList(aliasList);
		cmd.setCriteriaParameters(criteriaParameters);
		cmd.setHql(hql);
		cmd.setNumberOfResults(numberOfResults);
		cmd.setSearchDescription(searchDescription);
		cmd.setSearchName(searchName);
		cmd.setSearchTargetObject(searchTargetObject);
	}
	
	public void testGetCriteriaParameters(){
		assertNotNull(cmd.getCriteriaParameters());
	}
	
	public void testGetAdvancedSearchRowList(){
		assertNotNull(cmd.getAdvancedSearchRowList());
	}
	
	public void testHql(){
		assertNotNull(cmd.getHql());
	}
	
	public void testGetAliasList(){
		assertNotNull(cmd.getAliasList());
	}
	
	public void testNumberOfResults(){
		assertNull(cmd.getNumberOfResults());
	}
	
	public void testGetSearchDescription(){
		assertNull(cmd.getSearchDescription());
	}
	
	public void testSearchName(){
		assertNull(cmd.getSearchName());
	}
	
	public void testGetSearchTargetObject(){
		assertNull(cmd.getSearchTargetObject());
	}
	
	public void testAdvanceSearchUI(){
		assertNull(cmd.getAdvancedSearchUi());
	}
	
	public void testGetResultsViewColumnList(){
		assertNull(cmd.getResultsViewColumnList());
	}

}
