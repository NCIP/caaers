package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;
import gov.nih.nci.cabig.caaers.web.search.ui.DependentObject;
import gov.nih.nci.cabig.caaers.web.search.ui.SearchTargetObject;
import gov.nih.nci.cabig.caaers.web.search.ui.ViewColumn;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Sameer Sawant
 * @author Srini Akkala
 */
public final class CommandToSQL {
	
	private CommandToSQL() {	
	}

	public static String OUTER_JOIN_PREFIX = "outer";
	
	public static AbstractQuery transform(SearchTargetObject targetObject, 
			List<AdvancedSearchCriteriaParameter> criteriaParameters , String tableAliasForInterventions) throws Exception{

		return buildAbstractQuery(targetObject, criteriaParameters, tableAliasForInterventions);
	}

	private static AbstractQuery buildAbstractQuery(SearchTargetObject targetObject , List<AdvancedSearchCriteriaParameter> criteriaParameters, String tableAliasForInterventions) throws Exception {
		String queryClassName = targetObject.getQueryClassName();
		AbstractQuery query = (AbstractQuery)Class.forName(queryClassName).newInstance();
	 	 
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
								if (tableAliasForInterventions != null && dObject.getClassName().equals(INTERVENTION_CLASS_NAME)) {
									associtedObjectsInQueryString = associtedObjectsInQueryString + " , " + invokeField(query,tableAliasForInterventions);
								} else {
									associtedObjectsInQueryString = associtedObjectsInQueryString + " , " + invokeField(query,dObject.getTableAlias());
								}
								
							}
						}
					}
				}
			}
			// build base query 
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
							String joinMethodName = "";
							if (dobj.getJoinByMethod().equals(INTERVENTION_JOIN_INXML) && tableAliasForInterventions != null && dobj.getClassName().equals(INTERVENTION_CLASS_NAME)) {
								joinMethodName = interventionJoinMethodMap().get(tableAliasForInterventions);
							} else {
								joinMethodName = dobj.getJoinByMethod();
							}
							invokeMethod(query,joinMethodName,new Class[0],new Object[0]);
							objectsToJoin.add(dobj.getClassName());
						}
					}
				}
				
			}
			
			
			//if objectsInView  are part of objectsToJoin we are fine ..
			// if objectsInView are not part of objectsToJoin , means - we need to outerjoin/join on these objects based on <outer-join-required>..
			// method with "outer" prfix shud exist
			for (String viewObj:objectsInView) {
				if (!objectsToJoin.contains(viewObj)) {
					DependentObject dobj = AdvancedSearchUiUtil.getDependentObjectByName(targetObject, viewObj);
					if (dobj.isOuterJoinRequired()) {
						invokeMethod(query,OUTER_JOIN_PREFIX+dobj.getJoinByMethod(),new Class[0],new Object[0]);
					} else {
						String joinMethodName = "";
						if (dobj.getJoinByMethod().equals(INTERVENTION_JOIN_INXML) && tableAliasForInterventions != null && dobj.getClassName().equals(INTERVENTION_CLASS_NAME)) {
							joinMethodName = interventionJoinMethodMap().get(tableAliasForInterventions);
						} else {
							joinMethodName = dobj.getJoinByMethod();
						}
						
						invokeMethod(query,joinMethodName,new Class[0],new Object[0]);
					}
				}
				// sometimes view attributes needs some default filtering also , like study identifiers ..
				DependentObject dobj1 = AdvancedSearchUiUtil.getDependentObjectByName(targetObject, viewObj);
				List<ViewColumn> vcs= dobj1.getViewColumn();
				for (ViewColumn vc:vcs) {
					if (vc.isSelected() && vc.getFilterMethod() !=null) {
						invokeMethod(query,vc.getFilterMethod(),new Class[0],new Object[0]);
					}
				}
			}
			

			

		
		// add filters 
		for(AdvancedSearchCriteriaParameter parameter: criteriaParameters){
			if (parameter.getAttributeName() != null) {
				String filterMethodName = parameter.getFilterByMethodInQueryClass();
					if (parameter.getAttributeName().equals(STUDY_THERAPY_CODE_FIELD_NAME)) {
						filterMethodName = interventionFilterMethodMap().get(tableAliasForInterventions);
					}

				

				Class[] par=new Class[2];
				Object[] obj = new Object[2];
				if (parameter.getDataType().equals("String")) {
					par[0]=String.class;
					obj[0] = parameter.getValue();
				} else if (parameter.getDataType().equals("Integer")) {
					par[0]=Integer.class;
					obj[0] = Integer.parseInt(parameter.getValue());					
				} else if (parameter.getDataType().equals("boolean")) {
					par[0]=Boolean.TYPE;
					obj[0] = Boolean.parseBoolean(parameter.getValue());					
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
	
	public static boolean isMultipleViewQuery(SearchTargetObject targetObject){
		int numberOfDependentObjectsInView = 0;
		for(DependentObject dObject: targetObject.getDependentObject())
			if(dObject.isInView())
				numberOfDependentObjectsInView++;
		return numberOfDependentObjectsInView > 1;
	}
	
	public static String invokeField(Object query , String fieldName) throws Exception{
		Field field = query.getClass().getField(fieldName);
		Object aliasValue = field.get(query);
		return aliasValue.toString();
	}
	
	public static void invokeMethod(Object query , String joinMethodName , Class[] par , Object[] obj) throws Exception {
		Method mthd = query.getClass().getMethod(joinMethodName,par);
		mthd.invoke(query,obj);
	}
	
	private static Map<String,String> interventionJoinMethodMap() {
		Map<String,String> map = new HashMap<String,String>() ;
		map.put("OTHER_INT_ALIAS", "joinOtherIntervention");
		map.put("DEVICE_INT_ALIAS", "joinDeviceIntervention");
		map.put("AGENT_INT_ALIAS", "joinAgentIntervention");
		return map; 
	}

	private static Map<String,String> interventionFilterMethodMap() {
		Map<String,String> map = new HashMap<String,String>() ;
		map.put("OTHER_INT_ALIAS", "filterByOtherIntervention");
		map.put("DEVICE_INT_ALIAS", "filterByDeviceIntervention");
		map.put("AGENT_INT_ALIAS", "filterByAgentIntervention");
		return map; 
	}
	
	public static List<String> getAliasList() {
		List<String> aliasList = new ArrayList<String>();
		aliasList.add("OTHER_INT_ALIAS");
		aliasList.add("DEVICE_INT_ALIAS");
		aliasList.add("AGENT_INT_ALIAS");
		return aliasList;
	}
	private static String INTERVENTION_JOIN_INXML = "joinStudyIntervention";
	public static String STUDY_THERAPY_CODE_FIELD_NAME = "studyTherapyType.code";
	public static String STUDY_THERAPY_VIEW_FIELD_NAME = "interventionName";
	private static String INTERVENTION_CLASS_NAME = "gov.nih.nci.cabig.caaers.domain.StudyIntervention";

}