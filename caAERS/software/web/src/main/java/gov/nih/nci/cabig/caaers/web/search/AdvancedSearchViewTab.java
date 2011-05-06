package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.dao.AdvancedSearchDao;
import gov.nih.nci.cabig.caaers.dao.SearchDao;
import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;
import gov.nih.nci.cabig.caaers.domain.Search;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import gov.nih.nci.cabig.caaers.tools.spring.tabbedflow.WorkFlowTab;
import gov.nih.nci.cabig.caaers.web.search.ui.AdvancedSearchUi;
import gov.nih.nci.cabig.caaers.web.search.ui.CriteriaParameter;
import gov.nih.nci.cabig.caaers.web.search.ui.DependentObject;
import gov.nih.nci.cabig.caaers.web.search.ui.SaveSearch;
import gov.nih.nci.cabig.caaers.web.search.ui.SearchTargetObject;
import gov.nih.nci.cabig.caaers.web.search.ui.SelectedColumn;
import gov.nih.nci.cabig.caaers.web.search.ui.ViewColumn;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.validation.Errors;

/**
 * This is the advanced search criteria tab.
 * @author Sameer Sawant
 */
public class AdvancedSearchViewTab<T extends AdvancedSearchCommand> extends WorkFlowTab<T> {
	
	public static final String AJAX_SUBVIEW_PARAMETER = "subview";
	private AdvancedSearchDao advancedSearchDao;
	private SearchDao searchDao;
	
	public AdvancedSearchViewTab() {
        super("Select view", "Select view", "search/advancedSearchView");
    }
	
	public void onBind(HttpServletRequest request, T command, Errors errors) {
        super.onBind(request, command, errors);
    }

	@Override
	public void postProcess(HttpServletRequest request, T command, Errors errors){
		super.postProcess(request, command, errors);
		if (findInRequest(request, "searchName") != null && findInRequest(request,"runSavedQuery") !=null ) {
			String searchName = request.getParameter("searchName");
			getSavedSearch(searchName,command);
		}
			//return; //ignore if this is an ajax request
		String action = (String) findInRequest(request, "_action");
		List<ViewColumn> resultsViewColumnList = new ArrayList<ViewColumn>();
		boolean isInterventionInView = false;
		// Setup up the isInView attribute of the dependentObject if any of the attribute is selected.
		for(DependentObject dObject: command.getSearchTargetObject().getDependentObject()){
			dObject.setInView(false);
			for(ViewColumn viewColumn: dObject.getViewColumn()){
				if(viewColumn.getColumnAttribute().equals("id") && !viewColumn.isSelected()) {
					viewColumn.setSelected(true);
				}
				if(viewColumn.isSelected()) {
					dObject.setInView(true);
					if (viewColumn.getColumnAttribute().equals(CommandToSQL.STUDY_THERAPY_VIEW_FIELD_NAME)) {
						isInterventionInView = true;
					}
				}
			}
		}
		List<AdvancedSearchCriteriaParameter> parameters = new ArrayList<AdvancedSearchCriteriaParameter>();
		for(AdvancedSearchCriteriaParameter p: command.getCriteriaParameters()){
			if(p.getAttributeName()!= null && !p.getAttributeName().equals("") && !p.getAttributeName().equals("none") && !p.isDeleted())
					parameters.add(p);
		}
		//String query = "";
		
		List<AbstractQuery> multipleQueries = new ArrayList<AbstractQuery>();
		try{
			AbstractQuery queryObj = null;
			List<String> aliasList = new ArrayList<String>();
			
			// hack for interventions to fire 3 quries and merge the results ...
			for(AdvancedSearchCriteriaParameter parameter: parameters){
				if ( (parameter.getAttributeName() != null && parameter.getAttributeName().equals(CommandToSQL.STUDY_THERAPY_CODE_FIELD_NAME)) || isInterventionInView) {
					aliasList = CommandToSQL.getAliasList();
				}
			}
			
			if (aliasList.size()>0) {
				command.setAliasList(aliasList);
				for (String str:aliasList) {
					queryObj = CommandToSQL.transform(command.getSearchTargetObject(), parameters , str);
					System.out.println(queryObj.getQueryString());
					multipleQueries.add(queryObj);
				}
			} else {
				queryObj = CommandToSQL.transform(command.getSearchTargetObject(), parameters , null);
				System.out.println(queryObj.getQueryString());
				multipleQueries.add(queryObj);
			}
			
			//query = commandToSQL.transform(command.getSearchTargetObject(), parameters, true);
		}catch(Exception e){
			e.printStackTrace();
			errors.reject("EXP", "There was an exception while generating the HQL :" + e.getMessage());
		}
		
		List<Object> singleObjectList = new ArrayList<Object>();
		List<Object[]> multipleObjectList = new ArrayList<Object[]>();
		
		if(CommandToSQL.isMultipleViewQuery(command.getSearchTargetObject())){
			for (AbstractQuery qry:multipleQueries){
				List<Object[]> result = (List<Object[]>) advancedSearchDao.search(qry);
				multipleObjectList.addAll(result);
			}
			
			processMultipleObjectsList(multipleObjectList, command);
			//command.setNumberOfResults(singleObjectList.size());
			for(DependentObject dObject: command.getSearchTargetObject().getDependentObject())
				if(dObject.isInView()){
					for(ViewColumn vColumn: dObject.getViewColumn())
						if(vColumn.isSelected())
							resultsViewColumnList.add(vColumn);
				}
		}
		else{
			
			for (AbstractQuery qry:multipleQueries){
				List<Object[]> result = (List<Object[]>) advancedSearchDao.search(qry);
				singleObjectList.addAll(result);
			}
			
			//command.setNumberOfResults(singleObjectList.size());
			command.setAdvancedSearchRowList(processSingleObjectList(singleObjectList, command.getSearchTargetObject().getDependentObject().get(0)));
			for(DependentObject dObject: command.getSearchTargetObject().getDependentObject())
				if(dObject.isInView()){
					for(ViewColumn vColumn: dObject.getViewColumn())
						if(vColumn.isSelected())
							resultsViewColumnList.add(vColumn);
				}
		}
		command.setHql(multipleQueries);
		command.setResultsViewColumnList(resultsViewColumnList);
		command.setNumberOfResults(command.getAdvancedSearchRowList().size());
		
		
		// Set the results and the viewColumn in the session.
		request.getSession().setAttribute("resultsViewColumnList", command.getResultsViewColumnList());
		request.getSession().setAttribute("searchResultsRowList", command.getAdvancedSearchRowList());
	}
	
	/**
	 * This method processes the result object list when there are multiple objects in the view
	 */
	public void processMultipleObjectsList(List<Object[]> objectList, AdvancedSearchCommand command){
		command.setNumberOfResults(objectList.size());
		List<AdvancedSearchRow> rowList = new ArrayList<AdvancedSearchRow>();
		for(Object[] objectArr: objectList){
			AdvancedSearchRow row = new AdvancedSearchRow();
			AdvancedSearchColumn column = null;
			int i = 0;
			for(DependentObject dObject: command.getSearchTargetObject().getDependentObject()){
				if(dObject.isInView()){
					Object obj = objectArr[i++];
					BeanWrapper wrapper = null;
					boolean isString = false;
					if (obj != null && obj instanceof String) {
						isString = true;
					} else if (obj != null) {
						wrapper = new BeanWrapperImpl(obj);
					}
					for(ViewColumn viewColumn: dObject.getViewColumn()){
						if(viewColumn.isSelected()){
							column = new AdvancedSearchColumn();
							column.setColumnHeader(viewColumn.getColumnTitle());
							if (wrapper != null) {
								if(!viewColumn.isLengthy()){
									if(wrapper.getPropertyValue(viewColumn.getColumnAttribute()) != null)
										column.setValue(wrapper.getPropertyValue(viewColumn.getColumnAttribute()).toString());
									else
										column.setValue(wrapper.getPropertyValue(viewColumn.getColumnAttribute()));
								}else{
									if(wrapper.getPropertyValue(viewColumn.getColumnAttribute()) != null){
										String lengthyValue = wrapper.getPropertyValue(viewColumn.getColumnAttribute()).toString();
										column.setLengthyValue(lengthyValue);
										if(lengthyValue.length() < 20)
											column.setValue(lengthyValue);
										else
											column.setValue(lengthyValue.substring(0, 19) + " ...");
									}else{
										column.setValue(" ");
										column.setLengthyValue(" ");
									}
								}
							} else if (isString){
								if(obj != null){
									String lengthyValue = obj.toString();
									column.setLengthyValue(obj);
									if(lengthyValue.length() < 20)
										column.setValue(lengthyValue);
									else
										column.setValue(lengthyValue.substring(0, 19) + " ...");
								} else {
									column.setValue(" ");
									column.setLengthyValue(" ");									
								}
							} else {
								column.setValue(" ");
							}
							row.getColumnList().add(column);
						}
					}
				}
			}
			if (!rowList.contains(row)) {
				rowList.add(row);
			}
		}
		command.setAdvancedSearchRowList(rowList);
	}

	public List<AdvancedSearchRow> processSingleObjectList(List<Object> objectList, DependentObject dObject){
		List<AdvancedSearchRow> rowList = new ArrayList<AdvancedSearchRow>();
		for(Object object: objectList){
			AdvancedSearchRow row = new AdvancedSearchRow();
			AdvancedSearchColumn column = null;
			BeanWrapper wrapper = new BeanWrapperImpl(object);
			
			for(ViewColumn viewColumn: dObject.getViewColumn()){
				if(viewColumn.isSelected()){
					column = new AdvancedSearchColumn();
					column.setColumnHeader(viewColumn.getColumnTitle());
					if(!viewColumn.isLengthy()){
						if(wrapper.getPropertyValue(viewColumn.getColumnAttribute()) != null)
							column.setValue(wrapper.getPropertyValue(viewColumn.getColumnAttribute()).toString());
						else
							column.setValue("");
							
					}else{
						if(wrapper.getPropertyValue(viewColumn.getColumnAttribute()) != null){
							String lengthyValue = wrapper.getPropertyValue(viewColumn.getColumnAttribute()).toString();
							column.setLengthyValue(lengthyValue);
							if(lengthyValue.length() < 20)
								column.setValue(lengthyValue);
							else
								column.setValue(lengthyValue.substring(0, 19) + " ...");
						}else{
							column.setValue("");
							column.setLengthyValue("");
						}
					}
					row.getColumnList().add(column);
				}
			}
			rowList.add(row);
		}
		return rowList;
	}

	private void getSavedSearch(String searchName,T command) {
		if(searchName != null){
			// This is when the user clicks on one of the saved searches.
			AdvancedSearchUi advancedSearchUi = AdvancedSearchUiUtil.loadAdvancedSearchUi();
			String loginId = SecurityUtils.getUserLoginName();
			List<Search> searchList = searchDao.getByLoginAndName(loginId, searchName);
			Unmarshaller unmarshaller;
			SaveSearch savedSearch = null;
			try {
				unmarshaller = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.web.search.ui").createUnmarshaller();
				StringReader reader = new StringReader(searchList.get(0).getCriteriaXml()); 
				savedSearch = (SaveSearch) unmarshaller.unmarshal(reader);
				for(SearchTargetObject targetObject: advancedSearchUi.getSearchTargetObject()){
					if(targetObject.getClassName().equals(savedSearch.getTargetClassName()))
						command.setSearchTargetObject(targetObject);
				}
				command.getCriteriaParameters().clear();
				for(CriteriaParameter criteriaParameter: savedSearch.getCriteriaParameter()){
					AdvancedSearchCriteriaParameter parameter = new AdvancedSearchCriteriaParameter();
					parameter.setObjectName(criteriaParameter.getObjectName());
					parameter.setDependentObjectName(criteriaParameter.getDependentObjectName());
					parameter.setAttributeName(criteriaParameter.getAttributeName());
					parameter.setPredicate(criteriaParameter.getPredicate());
					parameter.setValue(criteriaParameter.getValue());
					parameter.setDisplayValue(criteriaParameter.getDisplayValue());
					parameter.setDataType(criteriaParameter.getDataType());
					parameter.setFilterByMethodInQueryClass(criteriaParameter.getFilterMethod());
					command.getCriteriaParameters().add(parameter);
				}
				// Setup the view selected.
				for(SelectedColumn selectedColumn: savedSearch.getSelectedColumn()){
					DependentObject dObject = AdvancedSearchUiUtil.getDependentObjectByName(command.getSearchTargetObject(), 
							selectedColumn.getDependentObjectClassName());
					dObject.setInView(true);
					for(ViewColumn vCol: dObject.getViewColumn()){
						if(selectedColumn.getColumnAttribute().equals(vCol.getColumnAttribute()))
							vCol.setSelected(true);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
     * Returns the value associated with the <code>attributeName</code>, if present in
     * HttpRequest parameter, if not available, will check in HttpRequest attribute map.
     */
    protected Object findInRequest(final ServletRequest request, final String attributName) {

        Object attr = request.getParameter(attributName);
        if (attr == null) {
            attr = request.getAttribute(attributName);
        }
        return attr;
    }


	public void setAdvancedSearchDao(AdvancedSearchDao advancedSearchDao) {
		this.advancedSearchDao = advancedSearchDao;
	}

	public void setSearchDao(SearchDao searchDao) {
		this.searchDao = searchDao;
	}

}