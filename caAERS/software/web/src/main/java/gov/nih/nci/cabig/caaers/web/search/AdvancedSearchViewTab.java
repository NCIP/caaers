package gov.nih.nci.cabig.caaers.web.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.validation.Errors;

import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.query.HQLQuery;
import gov.nih.nci.cabig.caaers.tools.spring.tabbedflow.WorkFlowTab;
import gov.nih.nci.cabig.caaers.web.ae.CaptureAdverseEventController;
import gov.nih.nci.cabig.caaers.web.search.ui.DependentObject;
import gov.nih.nci.cabig.caaers.web.search.ui.ViewColumn;

/**
 * This is the advanced search criteria tab.
 * @author Sameer Sawant
 */
public class AdvancedSearchViewTab<T extends AdvancedSearchCommand> extends WorkFlowTab<T> {
	
	public static final String AJAX_SUBVIEW_PARAMETER = "subview";
	private ParticipantDao participantDao;
	
	public AdvancedSearchViewTab() {
        super("Select view", "Select view", "search/advancedSearchView");
    }
	
	public void onBind(HttpServletRequest request, T command, Errors errors) {
        super.onBind(request, command, errors);
    }
	
	@Override
	public void postProcess(HttpServletRequest request, AdvancedSearchCommand command, Errors errors){
		if (findInRequest(request, AJAX_SUBVIEW_PARAMETER) != null || errors.hasErrors())
			return; //ignore if this is an ajax request
		
		String action = (String) findInRequest(request, "_action");
		List<ViewColumn> resultsViewColumnList = new ArrayList<ViewColumn>();
		// Setup up the isInView attribute of the dependentObject if any of the attribute is selected.
		for(DependentObject dObject: command.getSearchTargetObject().getDependentObject()){
			dObject.setInView(false);
			for(ViewColumn viewColumn: dObject.getViewColumn()){
				if(viewColumn.isSelected())
					dObject.setInView(true);
			}
		}
		List<AdvancedSearchCriteriaParameter> parameters = new ArrayList<AdvancedSearchCriteriaParameter>();
		for(AdvancedSearchCriteriaParameter p: command.getCriteriaParameters()){
			if(p.getAttributeName()!= null && !p.getAttributeName().equals("") && !p.getAttributeName().equals("none") && !p.isDeleted())
					parameters.add(p);
		}
		String query = "";
		try{
			query = CommandToSQL.transform(command.getSearchTargetObject(), parameters, true);
		}catch(Exception e){
			errors.reject("EXP", "There was an exception while generating the HQL :" + e.getMessage());
		}
		command.setHql(query);
		System.out.println("query = " + query);
		List<Object> singleObjectList = new ArrayList<Object>();
		List<Object[]> multipleObjectList = new ArrayList<Object[]>();
		if(CommandToSQL.isMultipleViewQuery(command.getSearchTargetObject())){
			multipleObjectList = (List<Object[]>) participantDao.search(new HQLQuery(query));
			if(action != null && action.equals("nestedView")){
				processMultipleObjectsListForNestedView(multipleObjectList, command);
				for(DependentObject dObject: command.getSearchTargetObject().getDependentObject())
					if(dObject.isInView() && dObject.getClassName().equals(command.getSearchTargetObject().getClassName())){
						for(ViewColumn vColumn: dObject.getViewColumn())
							if(vColumn.isSelected())
								resultsViewColumnList.add(vColumn);
					}
			}
			else{
				processMultipleObjectsList(multipleObjectList, command);
				//command.setNumberOfResults(singleObjectList.size());
				for(DependentObject dObject: command.getSearchTargetObject().getDependentObject())
					if(dObject.isInView()){
						for(ViewColumn vColumn: dObject.getViewColumn())
							if(vColumn.isSelected())
								resultsViewColumnList.add(vColumn);
					}
			}
		}
		else{
			singleObjectList = (List<Object>) participantDao.search(new HQLQuery(query));
			//command.setNumberOfResults(singleObjectList.size());
			command.setRowList(processSingleObjectList(singleObjectList, command.getSearchTargetObject().getDependentObject().get(0)));
			for(DependentObject dObject: command.getSearchTargetObject().getDependentObject())
				if(dObject.isInView()){
					for(ViewColumn vColumn: dObject.getViewColumn())
						if(vColumn.isSelected())
							resultsViewColumnList.add(vColumn);
				}
		}
		command.setResultsViewColumnList(resultsViewColumnList);
		command.setNumberOfResults(command.getRowList().getRowListDTO().size());
	}
	
	/**
	 * This method processes the result object list when there are multiple objects in the view
	 */
	private void processMultipleObjectsList(List<Object[]> objectList, AdvancedSearchCommand command){
		command.setNumberOfResults(objectList.size());
		SearchResultRowListDTO rowList = new SearchResultRowListDTO();
		for(Object[] objectArr: objectList){
			SearchResultRowDTO row = new SearchResultRowDTO();
			SearchResultColumnDTO column = null;
			int i = 0;
			for(DependentObject dObject: command.getSearchTargetObject().getDependentObject()){
				if(dObject.isInView()){
					Object obj = objectArr[i++];
					BeanWrapper wrapper = new BeanWrapperImpl(obj);
					for(ViewColumn viewColumn: dObject.getViewColumn()){
						if(viewColumn.isSelected()){
							column = new SearchResultColumnDTO();
							column.setColumnHeader(viewColumn.getColumnTitle());
							if(wrapper.getPropertyValue(viewColumn.getColumnAttribute()) != null)
								column.setValue(wrapper.getPropertyValue(viewColumn.getColumnAttribute()).toString());
							else
								column.setValue(wrapper.getPropertyValue(viewColumn.getColumnAttribute()));
							row.getColumnListDTO().getColumnDTOList().add(column);
						}
					}
				}
			}
			rowList.getRowListDTO().add(row);
		}
		
		command.setRowList(rowList);
	}
	
	/**
	 * This method processes the result object list when there are multiple objects in the view and the user demands nested view.
	 */
	private void processMultipleObjectsListForNestedView(List<Object[]> objectList, AdvancedSearchCommand command){
		SearchResultRowListDTO rowList = new SearchResultRowListDTO();
		DependentObject groupingDependentObject = command.getSearchTargetObject().getDependentObject().get(0);
		// Create the list of dependentObjects in the view which will be used to create inner tables.
		List<DependentObject> dependentObjectsList = new ArrayList<DependentObject>();
		for(DependentObject dObject: command.getSearchTargetObject().getDependentObject())
			if(dObject.isInView())
				dependentObjectsList.add(dObject);
		
		int start = 0;
		int curr = 0;
		while(start < objectList.size()){
			Object[] startObjectArr = null;
			Object[] currObjectArr = null;
			if(curr < objectList.size()){
				startObjectArr = objectList.get(start);
				currObjectArr = objectList.get(curr);
			}
			if(curr < objectList.size() && startObjectArr[0].equals(currObjectArr[0])){
				// we are dealing with the same grouping object.
				curr++;
			}else{
				// the grouping object is different than the earlier grouping object.
				// Add a row for the grouping object
				Object[] objectArr = objectList.get(start);
				BeanWrapper wrapper = new BeanWrapperImpl(objectArr[0]);
				SearchResultRowDTO row = new SearchResultRowDTO();
				SearchResultColumnDTO column = null;
				for(ViewColumn viewColumn: groupingDependentObject.getViewColumn()){
					if(viewColumn.isSelected()){
						column = new SearchResultColumnDTO();
						column.setColumnHeader(viewColumn.getColumnTitle());
						if(wrapper.getPropertyValue(viewColumn.getColumnAttribute()) != null)
							column.setValue(wrapper.getPropertyValue(viewColumn.getColumnAttribute()).toString());
						else
							column.setValue(wrapper.getPropertyValue(viewColumn.getColumnAttribute()));
						row.getColumnListDTO().getColumnDTOList().add(column);
					}
				}
				
				// Create innerTables here.
				Map<Object, Boolean> addedObjectsMap = new HashMap<Object, Boolean>();
				for(int j = 1; j < dependentObjectsList.size(); j++){
					addedObjectsMap.clear();
					List<Object> resultObjectList = new ArrayList<Object>();
					for(int k = start; k < curr; k++){
						Object[] depObjectArr = objectList.get(k);
						if(!addedObjectsMap.containsKey(depObjectArr[j])){
							addedObjectsMap.put(depObjectArr[j], true);
							resultObjectList.add(depObjectArr[j]);
						}
					}
					row.getListOfRowList().add(processSingleObjectList(resultObjectList, dependentObjectsList.get(j)));
				}
				
				rowList.getRowListDTO().add(row);
				start = curr;
			}
		}
		
		command.setRowList(rowList);
	}
	
	private SearchResultRowListDTO processSingleObjectList(List<Object> objectList, DependentObject dObject){
		SearchResultRowListDTO rowList = new SearchResultRowListDTO();
		for(Object object: objectList){
			SearchResultRowDTO row = new SearchResultRowDTO();
			SearchResultColumnDTO column = null;
			BeanWrapper wrapper = new BeanWrapperImpl(object);
			for(ViewColumn viewColumn: dObject.getViewColumn()){
				if(viewColumn.isSelected()){
					column = new SearchResultColumnDTO();
					column.setColumnHeader(viewColumn.getColumnTitle());
					if(wrapper.getPropertyValue(viewColumn.getColumnAttribute()) != null)
						column.setValue(wrapper.getPropertyValue(viewColumn.getColumnAttribute()).toString());
					else
						column.setValue(wrapper.getPropertyValue(viewColumn.getColumnAttribute()));
					row.getColumnListDTO().getColumnDTOList().add(column);
				}
			}
			rowList.getRowListDTO().add(row);
		}
		return rowList;
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

	/**
	 * @param participantDao the participantDao to set
	 */
	public void setParticipantDao(ParticipantDao participantDao) {
		this.participantDao = participantDao;
	}
}