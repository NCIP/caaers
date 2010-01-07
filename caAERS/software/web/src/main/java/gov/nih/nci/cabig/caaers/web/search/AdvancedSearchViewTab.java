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
			command.setAdvancedSearchRowList(processSingleObjectList(singleObjectList, command.getSearchTargetObject().getDependentObject().get(0)));
			for(DependentObject dObject: command.getSearchTargetObject().getDependentObject())
				if(dObject.isInView()){
					for(ViewColumn vColumn: dObject.getViewColumn())
						if(vColumn.isSelected())
							resultsViewColumnList.add(vColumn);
				}
		}
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
					BeanWrapper wrapper = new BeanWrapperImpl(obj);
					for(ViewColumn viewColumn: dObject.getViewColumn()){
						if(viewColumn.isSelected()){
							column = new AdvancedSearchColumn();
							column.setColumnHeader(viewColumn.getColumnTitle());
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
							row.getColumnList().add(column);
						}
					}
				}
			}
			rowList.add(row);
		}
		command.setAdvancedSearchRowList(rowList);
	}
	
	
	/**
	 * This method processes the result object list when there are multiple objects in the view and the user demands nested view.
	 */
	public void processMultipleObjectsListForNestedView(List<Object[]> objectList, AdvancedSearchCommand command) {
		// Create the list of dependentObjects in the view which will be used to create inner tables.
		List<DependentObject> dependentObjectsList = new ArrayList<DependentObject>();
		for(DependentObject dObject: command.getSearchTargetObject().getDependentObject())
			if(dObject.isInView())
				dependentObjectsList.add(dObject);
		
		int start = 0;
		int curr = 0;
		int end = objectList.size() - 1;
		int nestingLevel = 0;
		//x(0, end, 0, rowList, dependentObjectsList, objectList);
		List<AdvancedSearchRow> advancedSearchRowList = new ArrayList<AdvancedSearchRow>();
		buildRowList(0, end, 0, advancedSearchRowList, dependentObjectsList, objectList);
		command.setAdvancedSearchRowList(advancedSearchRowList);
	}
	
	public void buildRowList(int start, int end, int nestingLevel, List<AdvancedSearchRow> rowList, List<DependentObject> dependentObjectsList, List<Object[]> objectList){
		if(nestingLevel == dependentObjectsList.size() - 1){
			for(int i = start; i <= end; i++){
				AdvancedSearchRow row = new AdvancedSearchRow();
				row.setRowList(null);
				List<AdvancedSearchColumn> colList = new ArrayList<AdvancedSearchColumn>();
				row.setColumnList(colList);
				Object[] valueArr = objectList.get(i);
				BeanWrapper wrapper = new BeanWrapperImpl(valueArr[nestingLevel]);
				for(ViewColumn v: dependentObjectsList.get(nestingLevel).getViewColumn()){
					if(v.isSelected()){
						AdvancedSearchColumn col = new AdvancedSearchColumn();
						col.setColumnHeader(v.getColumnTitle());
						if(wrapper.getPropertyValue(v.getColumnAttribute())  != null)
							col.setValue(wrapper.getPropertyValue(v.getColumnAttribute()).toString());
						else
							col.setValue("");
						row.getColumnList().add(col);
					}
				}
				rowList.add(row);
			}
		}// if nestingLevel == dependentObjectsList.size - 1
		else if(nestingLevel < (dependentObjectsList.size() - 1)){
			int curr = start;
			while (start <= end){
				Object[] startValueArr = null;
				Object[] currValueArr = null;
				if(curr <= end){
					startValueArr = objectList.get(start);
					currValueArr = objectList.get(curr);
				}
				if(curr <= end && startValueArr[nestingLevel].equals(currValueArr[nestingLevel])){
					curr++;
				}else{
					AdvancedSearchRow row = new AdvancedSearchRow();
					row.setRowList(new ArrayList<AdvancedSearchRow>());
					List<AdvancedSearchColumn> colList = new ArrayList<AdvancedSearchColumn>();
					row.setColumnList(colList);
					Object[] valueArr = objectList.get(start);
					BeanWrapper wrapper = new BeanWrapperImpl(valueArr[nestingLevel]);
					for(ViewColumn v: dependentObjectsList.get(nestingLevel).getViewColumn()){
						if(v.isSelected()){
							AdvancedSearchColumn col = new AdvancedSearchColumn();
							col.setColumnHeader(v.getColumnTitle());
							if(wrapper.getPropertyValue(v.getColumnAttribute()) != null)
								col.setValue(wrapper.getPropertyValue(v.getColumnAttribute()).toString());
							else
								col.setValue("");
							row.getColumnList().add(col);
						}
					}
					rowList.add(row);
					buildRowList(start, curr - 1, nestingLevel + 1, row.getRowList(), dependentObjectsList, objectList);
					start = curr;
				}	
			}
			
		}
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