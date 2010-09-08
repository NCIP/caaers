package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;
import gov.nih.nci.cabig.caaers.dao.query.ParticipantQuery;
import gov.nih.nci.cabig.caaers.dao.query.StudyQuery;
import gov.nih.nci.cabig.caaers.web.search.ui.DependentObject;
import gov.nih.nci.cabig.caaers.web.search.ui.SearchTargetObject;
import gov.nih.nci.cabig.caaers.web.search.ui.UiAssociation;
import gov.nih.nci.cabig.caaers.web.search.ui.ViewColumn;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Sameer Sawant
 */
public class CommandToSQL{
	private  Map<String, String> classToAliasMap;
	private  List<String> classList;
	
	public  String transform(SearchTargetObject targetObject, 
			List<AdvancedSearchCriteriaParameter> criteriaParameters, boolean caseInsensitive) throws Exception{
		
		// Initialize the tableToAliasMap
		initializeTableToAliasMap(targetObject, criteriaParameters);
		
		// build Abstract Query
		
		
		StringBuffer sqlString = new StringBuffer();
		sqlString.append(getProjectionString(targetObject));
		sqlString.append(" ");
		sqlString.append(getFromTablesString(targetObject, criteriaParameters));
		sqlString.append(" where ");
		String joiningConditionString = getJoiningConditionString(targetObject, criteriaParameters);
		if(!joiningConditionString.equals("")){
			sqlString.append(joiningConditionString);
			sqlString.append(" and ");
		}
		sqlString.append(getCriteriaConditionString(targetObject, criteriaParameters, caseInsensitive));
		sqlString.append(" order by ");
		sqlString.append(getOrderByString(targetObject));
		
		return sqlString.toString();
	}
	
	public  AbstractQuery transform(SearchTargetObject targetObject, 
			List<AdvancedSearchCriteriaParameter> criteriaParameters) throws Exception{
		initializeTableToAliasMap(targetObject, criteriaParameters);
		return this.buildAbstractQuery(targetObject, criteriaParameters);
	}
	private String invokeField(Object query , String fieldName) throws Exception{
		Field field = query.getClass().getField(fieldName);
		Object aliasValue = field.get(query);
		return aliasValue.toString();
	}
	
	private void invokeMethod(Object query , String joinMethodName , Class[] par , Object[] obj) throws Exception {
		Method mthd = query.getClass().getMethod(joinMethodName,par);
		mthd.invoke(query,obj);
	}
	private AbstractQuery buildAbstractQuery(SearchTargetObject targetObject , List<AdvancedSearchCriteriaParameter> criteriaParameters) throws Exception {
		AbstractQuery query = null;
		if (targetObject.getClassName().equals("gov.nih.nci.cabig.caaers.domain.Study")) {
			query = new StudyQuery();
		}
		if (targetObject.getClassName().equals("gov.nih.nci.cabig.caaers.domain.Participant")) {
			query = new ParticipantQuery();
		}		 
		List<String> objectsToJoin = new ArrayList<String>();
		List<String> objectsInView = new ArrayList<String>();
		
		String[] hqlElements = StringUtils.split(query.getBaseQueryString());

        int etIndex = ArrayUtils.indexOf(hqlElements, invokeField(query,targetObject.getTableAlias()));
        hqlElements[etIndex] = invokeField(query,targetObject.getTableAlias());
        String associtedObjectsInQueryString = "";
			// check the objects selected in results , and add those objects to query .. 
			for(DependentObject dObject: targetObject.getDependentObject()) {
				List<ViewColumn> viewColumns = dObject.getViewColumn();
				for (ViewColumn viewColumn:viewColumns) {
					if (viewColumn.isSelected()) {
						if (!dObject.getClassName().equals(targetObject.getClassName())) {
							if (!objectsInView.contains(dObject.getClassName())) {
								objectsInView.add(dObject.getClassName());
								associtedObjectsInQueryString = associtedObjectsInQueryString + " , " + invokeField(query,dObject.getTableAlias());
							}
						}
					}
				}
			}
			
			if (!associtedObjectsInQueryString.equals("")) {
				associtedObjectsInQueryString = associtedObjectsInQueryString + " from ";
				hqlElements[etIndex + 1] = associtedObjectsInQueryString;
				String newQuery = StringUtils.join(hqlElements, " ");
		        query.modifyQueryString(newQuery);
			}
		
			// now we need to check which objects are in search criteria  , if objects are in search criteria they will be joined 
			for(AdvancedSearchCriteriaParameter parameter: criteriaParameters){
				if (parameter.getAttributeName() != null) {
					DependentObject dobj = AdvancedSearchUiUtil.getDependentObjectByName(targetObject, parameter.getObjectName());
					if (!dobj.getClassName().equals(targetObject.getClassName())) {
						if (!objectsToJoin.contains(dobj.getClassName())) {
							String joinMethodName = dobj.getJoinByMethod();
							invokeMethod(query,joinMethodName,new Class[0],new Object[0]);
							objectsToJoin.add(dobj.getClassName());
						}
					}
				}
				
			}
			
			
			//if objectsInView  are part of objectsToJoin we are fine ..
			// if objectsInView are not part of objectsToJoin , means - we need to outer join on these objects .. 
			for (String viewObj:objectsInView) {
				if (!objectsToJoin.contains(viewObj)) {
					DependentObject dobj = AdvancedSearchUiUtil.getDependentObjectByName(targetObject, viewObj);
					String outerJoinMethodName = "outer"+dobj.getJoinByMethod();
					invokeMethod(query,outerJoinMethodName,new Class[0],new Object[0]);
				}
			}
			

		
		// add filters 
		for(AdvancedSearchCriteriaParameter parameter: criteriaParameters){
			if (parameter.getAttributeName() != null) {
				String filterMethodName = parameter.getFilterByMethodInQueryClass();

				Class[] par=new Class[2];
				Object[] obj = new Object[2];
				if (parameter.getDataType().equals("String")) {
					par[0]=String.class;
					obj[0] = parameter.getValue();
				} else if (parameter.getDataType().equals("Integer")) {
					par[0]=Integer.class;
					obj[0] = Integer.parseInt(parameter.getValue());					
				} else {
					continue;
				}
				par[1] = String.class;
				obj[1] = parameter.getPredicate();
				//Method mthd = query.getClass().getMethod(filterMethodName,par);
			    //mthd.invoke(query,obj);
			    invokeMethod(query,filterMethodName,par,obj);
			}
		}
		
		return query;
	}
	
	public boolean isMultipleViewQuery(SearchTargetObject targetObject){
		int numberOfDependentObjectsInView = 0;
		for(DependentObject dObject: targetObject.getDependentObject())
			if(dObject.isInView())
				numberOfDependentObjectsInView++;
		return numberOfDependentObjectsInView > 1;
	}
	
	/**
	 * This method initializes the tableToAliasMap with the tables to be included in the HQL.
	 * First all the tables to be included in the join need to be identified and aliased need to be assigned. The Dependent-object list
	 * in the targetObject is then traversed to check if it needs to be included in the view by checking the boolean "inView" attribute
	 * of the dependentObject. To identify the list of tables in the query the following order is used - 
	 * 					a. the target object
	 * 					b. the dependent objects involved in the criteria
	 *					c. the dependent objects involved in the view and not in the criteria 
	 * 
	 * @param targetObject
	 * @param criteriaParameters
	 */
	public  void initializeTableToAliasMap(SearchTargetObject targetObject,
			List<AdvancedSearchCriteriaParameter> criteriaParameters){
		
		classToAliasMap = new HashMap<String, String>();
		// First create a list of all the tables to be included in the query
		classList = new ArrayList<String>();
		
		// First add the table for the targetObject.
		classList.add(targetObject.getClassName());
		classToAliasMap.put(targetObject.getClassName(), targetObject.getTableAlias());
		
		// Add the tables for the dependent objects in criteria
		for(AdvancedSearchCriteriaParameter parameter: criteriaParameters){
			DependentObject dObject = AdvancedSearchUiUtil.getDependentObjectByName(targetObject, parameter.getObjectName());
			for(UiAssociation assoc: dObject.getUiAssociation()){
				if(!classToAliasMap.containsKey(assoc.getName())){
					classToAliasMap.put(assoc.getName(), assoc.getTableAlias());
					classList.add(assoc.getName());
				}
			}
		}
		
		// Add the tables for the dependent objects in view
		for(DependentObject dObject: targetObject.getDependentObject()){
			if(dObject.isInView()){
				for(UiAssociation assoc: dObject.getUiAssociation()){
					if(!classToAliasMap.containsKey(assoc.getName())){
						classToAliasMap.put(assoc.getName(), assoc.getTableAlias());
						classList.add(assoc.getName());
					}
				}
			}
		}
	}

	/**
	 * This method returns the projection string for the SQL. For this it traverses through the dependent objects of the target object
	 * it receives as a parameter and checks if its in the view. If yes it appends the alias of the table from tableToAliasMap to the 
	 * projection string.
	 * @param targetObject
	 * @param criteriaParameters
	 * @return String - projection string 
	 */
	public String getProjectionString(SearchTargetObject targetObject){ 
		StringBuffer projectionStringBuffer = new StringBuffer();
		
		projectionStringBuffer.append("select ");
		for(DependentObject dObject: targetObject.getDependentObject()){
			if(dObject.isInView()){
				projectionStringBuffer.append(classToAliasMap.get(dObject.getClassName()));
				projectionStringBuffer.append(", ");
			}
		}
		// Remove the last "," added to the end of the buffer.
		int indexOfLastComma = projectionStringBuffer.lastIndexOf(",");
		return projectionStringBuffer.substring(0, indexOfLastComma);
	}
	
	/**
	 * This method returns the order by string for the HQL. For this it traverses through the dependent objects of the target object
	 * it receives as a parameter and checks if its in the view. If yes it addeds a clause to the order by string to sort the results
	 * based on its id.
	 * @param targetObject
	 * @return String - orderby String.
	 */
	public String getOrderByString(SearchTargetObject targetObject){
		StringBuffer orderByStringBuffer = new StringBuffer();
		for(DependentObject dObject: targetObject.getDependentObject()){
			if(dObject.isInView()){
				orderByStringBuffer.append(classToAliasMap.get(dObject.getClassName()));
				orderByStringBuffer.append(".id, ");
			}
		}
		
		// Remove the last "," added to the end of the buffer.
		int indexOfLastComma = orderByStringBuffer.lastIndexOf(",");
		return orderByStringBuffer.substring(0, indexOfLastComma);
	}
	
	/**
	 * This method creates the from tables string by simple using the keys in the tableToAliasMap
	 * @param targetObject
	 * @param criteriaParameters
	 * @return String fromTables string.
	 */
	public String getFromTablesString(SearchTargetObject targetObject,
			List<AdvancedSearchCriteriaParameter> criteriaParameters){
		StringBuffer fromTablesStringBuffer = new StringBuffer();
		fromTablesStringBuffer.append("from ");
		for(String className: classList){
			if(classToAliasMap.containsKey(className)){
				fromTablesStringBuffer.append(className);
				fromTablesStringBuffer.append(" " + classToAliasMap.get(className));
				fromTablesStringBuffer.append(", ");
			}
		}
		// Remove the last "," added to the end of the buffer.
		int indexOfLastComma = fromTablesStringBuffer.lastIndexOf(",");
		return fromTablesStringBuffer.substring(0, indexOfLastComma);
	}
	
	/**
	 * This method creates the joining condition string. We need to check if a particular joining condition is already added to the
	 * joining condition string so we add the joining conditions to a joiningConditionAddedMap to check if its already added.
	 * @param targetObject
	 * @param criteriaParameters
	 * @return
	 */
	public String getJoiningConditionString(SearchTargetObject targetObject,
			List<AdvancedSearchCriteriaParameter> criteriaParameters){
		// Create the joiningConditionAddedMap
		Map<String, Boolean> joiningConditionAddedMap = new HashMap<String, Boolean>();
		StringBuffer joiningConditionStringBuffer = new StringBuffer();
		
		// First we will traverse through all the criteria parameters and add the joining conditions for the dependent objects
		// involved in the criteria
		for(AdvancedSearchCriteriaParameter parameter: criteriaParameters){
			DependentObject dObject = AdvancedSearchUiUtil.getDependentObjectByName(targetObject, parameter.getObjectName());
			if(dObject.getUiAssociation() != null && dObject.getUiAssociation().size() > 0){
				// if the dependentObject needs association
				for(UiAssociation assoc: dObject.getUiAssociation()){
					if(!joiningConditionAddedMap.containsKey(assoc.getJoiningCondition())){
						joiningConditionAddedMap.put(assoc.getJoiningCondition(), Boolean.TRUE);
						joiningConditionStringBuffer.append(assoc.getJoiningCondition());
						joiningConditionStringBuffer.append(" and ");
					}
				}
			}
		}
		// We are done with adding all the joining conditions for the dependent objects involved in the criteria
		// Now we will traverse through dependentObjects of the targetObject to check if the dependentObjects are added to the view
		// And add the joining conditions for such dependentObjects
		for(DependentObject dObject: targetObject.getDependentObject()){
			if(dObject.isInView()){
				if(dObject.getUiAssociation() != null && dObject.getUiAssociation().size() > 0){
					// if the dependentObject needs association
					for(UiAssociation assoc: dObject.getUiAssociation()){
						if(!joiningConditionAddedMap.containsKey(assoc.getJoiningCondition())){
							joiningConditionAddedMap.put(assoc.getJoiningCondition(), Boolean.TRUE);
							joiningConditionStringBuffer.append(assoc.getJoiningCondition());
							joiningConditionStringBuffer.append(" and ");
						}
					}
				}
			}
		}
		
		int indexOfLastAnd = joiningConditionStringBuffer.lastIndexOf(" and ");
		if(indexOfLastAnd != -1)
			return joiningConditionStringBuffer.substring(0, indexOfLastAnd);
		else
			return joiningConditionStringBuffer.toString();
	}
	
	public String getCriteriaConditionString(SearchTargetObject targetObject,
			List<AdvancedSearchCriteriaParameter> criteriaParameters, boolean caseInsensitive) throws Exception{
		
		// get the field for the attribute
		StringBuffer criteriaConditionStringBuffer = new StringBuffer();
		for(AdvancedSearchCriteriaParameter parameter: criteriaParameters){
			Field attribField = null;
			try {
				Class objClass = Class.forName(parameter.getObjectName());
				attribField = ClassAccessUtilities.getNamedField(objClass, parameter.getAttributeName());
			} catch (ClassNotFoundException ex) {
				throw new Exception("Object class " + parameter.getObjectName() + "was not found", ex);
			}
			
			// data type flags
			int typeFlag = AttributeTypeDetector.UNKNOWN_TYPE;
			if(attribField != null)
				typeFlag = AttributeTypeDetector.determineType(attribField);
			
			if (typeFlag == AttributeTypeDetector.UNKNOWN_TYPE) {
				// Check if the attribute is an embedded type
				if(parameter.getAttributeName().indexOf(".") != -1){
					String embeddedAttribName = parameter.getAttributeName().split("\\.")[0];
					String childAttribName = parameter.getAttributeName().split("\\.")[1];
					Field embeddedField = null;
					Field childField = null;
					try{
						Class objClass = Class.forName(parameter.getObjectName());
						embeddedField = ClassAccessUtilities.getNamedField(objClass, embeddedAttribName);
						childField = ClassAccessUtilities.getNamedField(embeddedField.getType(), childAttribName);
						
						int childTypeFlag = AttributeTypeDetector.determineType(childField);
						if(childTypeFlag == AttributeTypeDetector.UNKNOWN_TYPE)
							throw new Exception("Unable to determine type of attribute " + parameter.getAttributeName() + " of class " + parameter.getObjectName());
						else{
							typeFlag = childTypeFlag;
							//parameter.setAttributeName(embeddedAttribName);
						}
					} catch (ClassNotFoundException ex) {
						throw new Exception("Object class " + parameter.getObjectName() + "was not found", ex);
					}
				}else{
					throw new Exception("Unable to determine type of attribute " + parameter.getAttributeName() + " of class " + parameter.getObjectName());
				}
			}
			boolean useLowercase = typeFlag == AttributeTypeDetector.STRING_TYPE || typeFlag == AttributeTypeDetector.CHARACTER_TYPE;
			
			// Create the fullAttributeName taking into consideration the ObjectAlias.
			DependentObject dObject = AdvancedSearchUiUtil.getDependentObjectByName(targetObject, parameter.getObjectName());
			
			String fullAttribName = "";
			if(parameter.getAttributeName().indexOf(".") != -1)
				fullAttribName = dObject.getTableAlias() + "." + parameter.getAttributeName().split("\\.")[0];
			else
				fullAttribName = dObject.getTableAlias() + "." + parameter.getAttributeName();
			
			boolean inClauseCheck = parameter.getPredicate().equals("in") || parameter.getPredicate().equals("not in");
			caseInsensitive = caseInsensitive && !inClauseCheck;
			useLowercase = useLowercase && !inClauseCheck;
			
			if (typeFlag == AttributeTypeDetector.DATE_TYPE){
				String dateQueryString = createDateQuery(fullAttribName, parameter.getValue(), parameter.getPredicate());
				criteriaConditionStringBuffer.append(dateQueryString);
			}else{
				if (useLowercase) {
					criteriaConditionStringBuffer.append("lower(");
				}
				criteriaConditionStringBuffer.append(fullAttribName);
				if (useLowercase) {
					criteriaConditionStringBuffer.append(")");
				}
				
				criteriaConditionStringBuffer.append(" ");
				criteriaConditionStringBuffer.append(parameter.getPredicate());
				criteriaConditionStringBuffer.append(" ");
				
				// binary predicates
				if (caseInsensitive && (typeFlag == AttributeTypeDetector.STRING_TYPE || typeFlag == AttributeTypeDetector.CHARACTER_TYPE)) {
					criteriaConditionStringBuffer.append("lower(");
				}
				if (typeFlag != AttributeTypeDetector.BOOLEAN_TYPE && !inClauseCheck) {
					criteriaConditionStringBuffer.append("'");
				}
				if (typeFlag != AttributeTypeDetector.BOOLEAN_TYPE && inClauseCheck){
					criteriaConditionStringBuffer.append("(");
				}
				
				if (parameter.getPredicate().equals("like")) {
					if (parameter.getValue().indexOf("%") != -1) {
						criteriaConditionStringBuffer.append(parameter.getValue());
					} else {
						criteriaConditionStringBuffer.append("%"+parameter.getValue()+"%");
					}					
				} else {
					criteriaConditionStringBuffer.append(parameter.getValue());
				}
				
				if (typeFlag != AttributeTypeDetector.BOOLEAN_TYPE && inClauseCheck){
					criteriaConditionStringBuffer.append(")");
				}
				if (typeFlag != AttributeTypeDetector.BOOLEAN_TYPE && !inClauseCheck) {
					criteriaConditionStringBuffer.append("'");
				}
				if (caseInsensitive && (typeFlag == AttributeTypeDetector.STRING_TYPE || typeFlag == AttributeTypeDetector.CHARACTER_TYPE)) {
					criteriaConditionStringBuffer.append(")");
				}
			}
			criteriaConditionStringBuffer.append(" and ");
		}// For loop
		
		int indexOfLastAnd = criteriaConditionStringBuffer.lastIndexOf(" and ");
		return criteriaConditionStringBuffer.substring(0, indexOfLastAnd);
	}
	
	
	public  String createDateQuery(String fullAttributeName, String dateString, String predicate) throws Exception {
		Date dateValue = null;
		try {
			//dateValue = java.text.DateFormat.getDateTimeInstance().parse(dateString);
			DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	        dateValue = (Date)formatter.parse(dateString);
	    
		} catch (Exception ex) {
			throw new Exception("Error parsing date " + dateString + ": " + ex.getMessage(), ex);
		}
		String yearPredicate = null;
		String monthPredicate = null;
		
		if (predicate.equals(">") || predicate.equals(">=")) {
			yearPredicate = ">";
			monthPredicate = ">";
		} else if (predicate.equals("<") || predicate.equals("<=")) {
			yearPredicate = "<=";
			monthPredicate = "<";
		} 

		StringBuilder dateQuery = new StringBuilder();
		// parse the date value into a Java Date object
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateValue);
		dateQuery.append("(");
		
		int month = cal.get(Calendar.MONTH) + 1;
		if(!predicate.equals("=")){
			String yearQuery = "year(" + fullAttributeName + ") " + yearPredicate + " '" + cal.get(Calendar.YEAR) + "'";
			String monthQuery = "year(" + fullAttributeName + ") = '" + cal.get(Calendar.YEAR) + "' AND " +
					"month(" + fullAttributeName + ") " + monthPredicate + " '" + month + "'";
			String dayQuery = "year(" + fullAttributeName + ") = '" + cal.get(Calendar.YEAR) + "' AND " +
				"month(" + fullAttributeName + ") = '" + month + "' AND " +
				"day(" + fullAttributeName + ") " + predicate + " '" + cal.get(Calendar.DAY_OF_MONTH) + "'";
			dateQuery.append(yearQuery).append(" OR (").append(monthQuery).append(") OR (").append(dayQuery).append(")");
		}else{
			dateQuery.append("year(").append(fullAttributeName).append(") = '").append(cal.get(Calendar.YEAR)).append("' AND ")
					.append("month(").append(fullAttributeName).append(") = '").append(month).append("' AND ")
					.append("day(").append(fullAttributeName).append(") = '").append(cal.get(Calendar.DAY_OF_MONTH)).append("'");
		}
		dateQuery.append(")");
		return dateQuery.toString();
	}
}