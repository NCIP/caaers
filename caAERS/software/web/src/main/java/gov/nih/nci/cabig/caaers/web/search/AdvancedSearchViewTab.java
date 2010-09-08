package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.dao.AdvancedSearchDao;
import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;
import gov.nih.nci.cabig.caaers.tools.spring.tabbedflow.WorkFlowTab;
import gov.nih.nci.cabig.caaers.web.search.ui.DependentObject;
import gov.nih.nci.cabig.caaers.web.search.ui.ViewColumn;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

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
	
	public AdvancedSearchViewTab() {
        super("Select view", "Select view", "search/advancedSearchView");
    }
	
	public void onBind(HttpServletRequest request, T command, Errors errors) {
        super.onBind(request, command, errors);
    }
	@Override
	public void postProcess(HttpServletRequest request, T command, Errors errors){
		super.postProcess(request, command, errors);
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
		//String query = "";
		AbstractQuery queryObj = null;
		CommandToSQL commandToSQL =  new CommandToSQL();
		try{
			queryObj = commandToSQL.transform(command.getSearchTargetObject(), parameters);
			
			//query = commandToSQL.transform(command.getSearchTargetObject(), parameters, true);
		}catch(Exception e){
			e.printStackTrace();
			errors.reject("EXP", "There was an exception while generating the HQL :" + e.getMessage());
		}
		
		List<Object> singleObjectList = new ArrayList<Object>();
		List<Object[]> multipleObjectList = new ArrayList<Object[]>();
		
		if(commandToSQL.isMultipleViewQuery(command.getSearchTargetObject())){
			multipleObjectList = (List<Object[]>) advancedSearchDao.search(queryObj);
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
			singleObjectList = (List<Object>) advancedSearchDao.search(queryObj);
			//command.setNumberOfResults(singleObjectList.size());
			command.setAdvancedSearchRowList(processSingleObjectList(singleObjectList, command.getSearchTargetObject().getDependentObject().get(0)));
			for(DependentObject dObject: command.getSearchTargetObject().getDependentObject())
				if(dObject.isInView()){
					for(ViewColumn vColumn: dObject.getViewColumn())
						if(vColumn.isSelected())
							resultsViewColumnList.add(vColumn);
				}
		}
		command.setHql(queryObj.getQueryString());
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
					if (obj != null) {
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
							} else {
								column.setValue(" ");
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


	public void setAdvancedSearchDao(AdvancedSearchDao advancedSearchDao) {
		this.advancedSearchDao = advancedSearchDao;
	}

}