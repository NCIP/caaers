package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;
import gov.nih.nci.cabig.caaers.web.search.ui.AdvancedSearchUi;
import gov.nih.nci.cabig.caaers.web.search.ui.SearchTargetObject;
import gov.nih.nci.cabig.caaers.web.search.ui.ViewColumn;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class AdvancedSearchCommand{
	
	private static final Log logger = LogFactory.getLog(AdvancedSearchCommand.class);
	private AdvancedSearchUi advancedSearchUi;
	private List<AdvancedSearchCriteriaParameter> criteriaParameters;
	private SearchTargetObject searchTargetObject;
	private String searchName;
	private String searchDescription;
	private List<AbstractQuery> hql;
	private List<ViewColumn> resultsViewColumnList;
	private Integer numberOfResults;
	private List<AdvancedSearchRow> advancedSearchRowList;
	private List<String> aliasList = new ArrayList<String>();
    
    public AdvancedSearchCommand(){
		
	}
	
	public AdvancedSearchCommand(AdvancedSearchUi advancedSearchUi){
		setAdvancedSearchUi(advancedSearchUi);
	}
	
	public AdvancedSearchUi getAdvancedSearchUi(){
		return advancedSearchUi;
	}
	
	public List<AdvancedSearchCriteriaParameter> getCriteriaParameters(){
		if(criteriaParameters == null)
			criteriaParameters = new ArrayList<AdvancedSearchCriteriaParameter>();
		return criteriaParameters;
	}
	
	public void setCriteriaParameters(List<AdvancedSearchCriteriaParameter> criteriaParameters){
		this.criteriaParameters = criteriaParameters;
	}
	
	public void setSearchTargetObject(SearchTargetObject searchTargetObject){
		this.searchTargetObject = searchTargetObject;
	}
	
	public SearchTargetObject getSearchTargetObject(){
		return searchTargetObject;
	}
	
	public void setAdvancedSearchUi(AdvancedSearchUi advancedSearchUi){
		this.advancedSearchUi = advancedSearchUi;
	}

	/**
	 * @return the searchName
	 */
	public String getSearchName() {
		return searchName;
	}

	/**
	 * @param searchName the searchName to set
	 */
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	/**
	 * @return the searchDescription
	 */
	public String getSearchDescription() {
		return searchDescription;
	}

	/**
	 * @param searchDescription the searchDescription to set
	 */
	public void setSearchDescription(String searchDescription) {
		this.searchDescription = searchDescription;
	}

	/**
	 * @return the hql
	 */
	public List<AbstractQuery> getHql() {
		return hql;
	}

	/**
	 * @param hql the hql to set
	 */
	public void setHql(List<AbstractQuery> hql) {
		this.hql = hql;
	}

	/**
	 * @return the resultsViewColumnList
	 */
	public List<ViewColumn> getResultsViewColumnList() {
		return resultsViewColumnList;
	}

	/**
	 * @param resultsViewColumnList the resultsViewColumnList to set
	 */
	public void setResultsViewColumnList(List<ViewColumn> resultsViewColumnList) {
		this.resultsViewColumnList = resultsViewColumnList;
	}

	/**
	 * @return the numberOfResults
	 */
	public Integer getNumberOfResults() {
		return numberOfResults;
	}

	/**
	 * @param numberOfResults the numberOfResults to set
	 */
	public void setNumberOfResults(Integer numberOfResults) {
		this.numberOfResults = numberOfResults;
	}

	/**
	 * @return the advancedSearchRowList
	 */
	public List<AdvancedSearchRow> getAdvancedSearchRowList() {
		if(advancedSearchRowList == null)
			advancedSearchRowList = new ArrayList<AdvancedSearchRow>();
		return advancedSearchRowList;
	}

	/**
	 * @param advancedSearchRowList the advancedSearchRowList to set
	 */
	public void setAdvancedSearchRowList(
			List<AdvancedSearchRow> advancedSearchRowList) {
		this.advancedSearchRowList = advancedSearchRowList;
	}

	public List<String> getAliasList() {
		return aliasList;
	}

	public void setAliasList(List<String> aliasList) {
		this.aliasList = aliasList;
	}
	
}