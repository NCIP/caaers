package gov.nih.nci.cabig.caaers.web.search;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import gov.nih.nci.cabig.caaers.web.search.ui.AdvancedSearchUi;
import gov.nih.nci.cabig.caaers.web.search.ui.DependentObject;
import gov.nih.nci.cabig.caaers.web.search.ui.SearchTargetObject;
import gov.nih.nci.cabig.caaers.web.search.ui.UiAttribute;
import gov.nih.nci.cagrid.cqlquery.Association;
import gov.nih.nci.cagrid.cqlquery.Attribute;
import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlquery.Group;
import gov.nih.nci.cagrid.cqlquery.LogicalOperator;
import gov.nih.nci.cagrid.cqlquery.Predicate;

/**
 * This is the main processor class that transforms the command object of the 
 * advanced search page to the CQL object. This CQL will be then be used to genrate
 * the corresponding HQL
 *
 * @author Sameer Sawant
 */
public class UICQL2CQL{
	
	private static AdvancedSearchUi advancedSearchUi;
	
	static{
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("advancedSearch-ui.xml");

		Unmarshaller unmarshaller;
		try {
			unmarshaller = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.web.search.ui").createUnmarshaller();
			advancedSearchUi = (AdvancedSearchUi) unmarshaller.unmarshal(inputStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static CQLQuery transform(List<AdvancedSearchCriteriaParameter> criteriaParameters, SearchTargetObject targetObject){
		CQLQuery cqlQuery = new CQLQuery();
		Map<String, List<Object>> searchObjectToGroupListMap = new HashMap<String, List<Object>>();
		
		//Sort the criteria
		Collections.sort(criteriaParameters);
		if(criteriaParameters != null && criteriaParameters.size() >= 0){
			int startIndex = 0;
			int currentIndex = 0;
			while(startIndex < criteriaParameters.size()){
				if(currentIndex < criteriaParameters.size()){
					String startCriteriaObjectName = criteriaParameters.get(startIndex).getObjectName();
					String currentCriteriaObjectName = criteriaParameters.get(currentIndex).getObjectName();
					if(!startCriteriaObjectName.equals(currentCriteriaObjectName)){
						// we are dealing with criteria from different objects
						processRelatedParameters(startIndex, currentIndex-1, criteriaParameters, searchObjectToGroupListMap, targetObject);
						startIndex = currentIndex;
						continue;
					}else{
						String startCriteriaAttributeName = criteriaParameters.get(startIndex).getAttributeName();
						String currentCriteriaAttributeName = criteriaParameters.get(currentIndex).getAttributeName();
						if(!startCriteriaAttributeName.equals(currentCriteriaAttributeName)){
							// we are dealing with criteria on different attributes of same object.
							boolean startCriteriaAssociationReqd = associationRequiredForSearchAttribute(criteriaParameters.get(startIndex), targetObject);
							boolean currentCriteriaAssociationReqd = associationRequiredForSearchAttribute(criteriaParameters.get(currentIndex), targetObject);
							if(startCriteriaAssociationReqd || currentCriteriaAssociationReqd){
								processRelatedParameters(startIndex, currentIndex-1, criteriaParameters, searchObjectToGroupListMap, targetObject);
								startIndex = currentIndex;
								continue;
							}else{
								currentIndex++;
							}
						}else{
							// we are dealing with the same attribute on same search object so they can be in the same logical group
							currentIndex++;
						}
					}
				}else{
					processRelatedParameters(startIndex, currentIndex-1, criteriaParameters, searchObjectToGroupListMap, targetObject);
					startIndex = currentIndex;
					continue;
				}
			}
		}
		//Code to group the criteriaParameters into LogicalGroup and calling processRelatedParameters
		
		//The searchObjectToGroupListMap will now be populated.
		//Code to create cqlQuery using searchObjectToGroupListMap.
		return buildCqlQuery(targetObject, searchObjectToGroupListMap);
		//If there are multiple keys in the map, then we need a group to include them
		//If theres just one element in the list add it as a group/association/attribute
		//If there are more than one element then 
		//     - club all the associations into one Array
		//     - club all the Attributes into one group
		//     - club all the groups into one bigger group.
		// IDEA - traverse through the list, count the number of associations, create and array of associations and add them to this
		// array at the same time remove it from the list.
		// then traverse through the list, count the number of attributes and the attributes within the groups if any.
		// create and array of all these attributes (direct attributes and attributes within a group) and create a group with these attribute
		// array. Add the group and association to the wrapper group.
		
		//return cqlQuery;
	}
	
	public static void processRelatedParameters(int startIndex, int endIndex, List<AdvancedSearchCriteriaParameter> criteriaParameters, 
			Map<String, List<Object>> searchObjectToGroupListMap, SearchTargetObject targetObject){

		String objectName = criteriaParameters.get(startIndex).getObjectName();
		// If startIndex == endIndex ==> There is only one criteria parameter in the logical group so in that
		// case an Attribute object needs to be created.
		if(startIndex == endIndex){
			
			if(!associationRequiredForSearchAttribute(criteriaParameters.get(startIndex), targetObject)){
				//Create just one attribute
				Attribute attr = createAttributeElement(startIndex, criteriaParameters);
				addToSearchObjectToGroupListMap(objectName, attr, searchObjectToGroupListMap);
			}
			else{
				//Create an association with just one attribute
				Association association = createAttributeAssociationElement(startIndex, criteriaParameters, targetObject);
				Attribute attr = createAttributeElement(startIndex, criteriaParameters);
				// Iterate through the association chain to add the attribute to the innermost association.
				Association assoc = association;
				while(assoc.getAssociation() != null)
					assoc = assoc.getAssociation();
				assoc.setAttribute(attr);
				addToSearchObjectToGroupListMap(objectName, association, searchObjectToGroupListMap);
			}
		}else{
			// The else section is executed when there are multiple criteria parameters in the logical group. So in this 
			// case either a group of attributes will be created or an association containing group of attributes is created.
			int numberOfAttributes = endIndex - startIndex + 1;
			Attribute[] attributeArr = new Attribute[numberOfAttributes];
			int arrIndex = 0;
			for(int i = startIndex; i <= endIndex; i++){
				Attribute attr = createAttributeElement(i, criteriaParameters);
				attributeArr[arrIndex++] = attr;
			}
			Group group = new Group();
			//TODO - set the correct logicalRelation operator
			group.setLogicRelation(LogicalOperator.AND);
			group.setAttribute(attributeArr);
			
			if(associationRequiredForSearchAttribute(criteriaParameters.get(startIndex), targetObject)){
				//Create an association that includes the group created from the attributes and add it to the map.
				Association association = createAttributeAssociationElement(startIndex, criteriaParameters, targetObject);
				association.setGroup(group);
				addToSearchObjectToGroupListMap(objectName, association, searchObjectToGroupListMap);
			}else{
				//Simply add the group created to the map.
				addToSearchObjectToGroupListMap(objectName, group, searchObjectToGroupListMap);
			}
		}
	}
	
	public static boolean associationRequiredForSearchAttribute(AdvancedSearchCriteriaParameter parameter, SearchTargetObject targetObject){
		DependentObject dObject = AdvancedSearchUiUtil.getDependentObjectByName(targetObject, parameter.getObjectName());
		UiAttribute attribute = AdvancedSearchUiUtil.getUiAttributeByName(dObject, parameter.getAttributeName());
		return !(attribute.getUiAssociation() == null || attribute.getUiAssociation().size() == 0);
	}
	
	public static boolean associationRequiredForSearchObject(SearchTargetObject targetObject, String objectName){
		DependentObject dObject = AdvancedSearchUiUtil.getDependentObjectByName(targetObject, objectName);
		return !(dObject.getUiAssociation() == null || dObject.getUiAssociation().size() == 0);
	}
	
	public static void addToSearchObjectToGroupListMap(String objectName, Object object, Map<String, List<Object>> searchObjectToGroupListMap){
		if(searchObjectToGroupListMap.containsKey(objectName))
			searchObjectToGroupListMap.get(objectName).add(object);
		else{
			List<Object> groupList = new ArrayList<Object>();
			groupList.add(object);
			searchObjectToGroupListMap.put(objectName, groupList);
		}
	}
	
	public static Attribute createAttributeElement(int index, List<AdvancedSearchCriteriaParameter> criteriaParameters){
		AdvancedSearchCriteriaParameter parameter = criteriaParameters.get(index);
		Attribute attribute = new Attribute(parameter.getAttributeName(), fetchPredicate(parameter.getPredicate()), parameter.getValue());
		return attribute;
	}
	
	public static Association createAttributeAssociationElement(int index, List<AdvancedSearchCriteriaParameter> criteriaParameters, SearchTargetObject targetObject){
		Association association = new Association();
		DependentObject dObject = AdvancedSearchUiUtil.getDependentObjectByName(targetObject, criteriaParameters.get(index).getObjectName());
		UiAttribute attribute = AdvancedSearchUiUtil.getUiAttributeByName(dObject, criteriaParameters.get(index).getAttributeName());
		
		for(int i = attribute.getUiAssociation().size() - 1; i >= 0; i--){
			Association assoc = new Association(attribute.getUiAssociation().get(i).getRoleName());
			assoc.setName(attribute.getUiAssociation().get(i).getName());
			assoc.setAssociation(association.getAssociation());
			association.setAssociation(assoc);
		}
		
		return association.getAssociation();
	}
	
	public static Association createObjectAssociationElement(SearchTargetObject targetObject, String objectName){
		DependentObject dObject = AdvancedSearchUiUtil.getDependentObjectByName(targetObject, objectName);
		Association association = new Association();
		
		for(int i = dObject.getUiAssociation().size() - 1; i >= 0; i--){
			Association assoc = new Association(dObject.getUiAssociation().get(i).getRoleName());
			assoc.setName(dObject.getUiAssociation().get(i).getName());
			assoc.setAssociation(association.getAssociation());
			association.setAssociation(assoc);
		}
		
		return association.getAssociation();
	}
	
	public static Predicate fetchPredicate(String predicateString){
		// Here you will have to take care of IN and NOT IN correctly.
		// If predicateString equals (IN or NOT IN) then you will have to create a new predicate object
		// using new Predicate("IN") otherwise you can use the Predicates supported by CqlQuery.
		return Predicate.EQUAL_TO;
	}
	
	public static CQLQuery buildCqlQuery(SearchTargetObject targetObject, Map<String, List<Object>> searchObjectToGroupListMap){
		CQLQuery cqlQuery = new CQLQuery();
		gov.nih.nci.cagrid.cqlquery.Object target = new gov.nih.nci.cagrid.cqlquery.Object();
		target.setName(targetObject.getClassName());
		cqlQuery.setTarget(target);
		
		///////boolean multipleSearchTargetsInvolved = searchObjectToGroupListMap.size() > 1;
		
		// id multipleSearchTargetsInvolved == true then a group is created and added to the cqlQuery to enclose the CQL for 
		// each searchTargetObject
		///////if(multipleSearchTargetsInvolved){
		//////	Group group = new Group();
			// TODO: Get the conditional operator correctly
		//////	group.setLogicRelation(LogicalOperator.OR);
		//////	cqlQuery.getTarget().setGroup(group);
		///////}
		
		// Create a new map with the same keys as in searchObjectToGroupListMap.
		// Process the List and the association of the searchObject (if Any) to create the final Group/Association/Attribute object
		// and this will be the value in the new Map.

		List<Group> groupArrList = new ArrayList<Group>();
		List<Association> associationArrList = new ArrayList<Association>();
		List<Attribute> attributeArrList = new ArrayList<Attribute>();
		Group[] groupArr;
		Association[] associationArr;
		Attribute[] attributeArr;
		
		
		Map<String, Object> searchObjectToCQLMap = new HashMap<String,Object>();
		for(String key: searchObjectToGroupListMap.keySet()){
			List<Object> groupList = searchObjectToGroupListMap.get(key);
			Association searchObjectAssociation = null;
			Group searchObjectGroup = null;
			boolean searchObjectRequiresAssociation = associationRequiredForSearchObject(targetObject, key);
			if(searchObjectRequiresAssociation){
				// Create the association Object for this searchObject. This will be the wrapper for the group objects in the list
				searchObjectAssociation = createObjectAssociationElement(targetObject, key);
			}
			
			boolean searchObjectRequiresGroup = searchObjectToGroupListMap.get(key).size() > 1;
			if(searchObjectRequiresGroup){
				// Create the group Object for this searchObject. This will be the wrapper for the group objects in the list.
				searchObjectGroup = new Group();
				//TODO: ADD the logical operator correctly
				searchObjectGroup.setLogicRelation(LogicalOperator.OR);
				
				
				
				for(Object obj: groupList){
					if(obj instanceof Attribute)
						attributeArrList.add((Attribute) obj);
					else if(obj instanceof Association)
						associationArrList.add((Association) obj);
					else if(obj instanceof Group)
						groupArrList.add((Group) obj);
				}
				if(groupArrList.size() > 0){
					groupArr = new Group[groupArrList.size()];
					searchObjectGroup.setGroup(groupArrList.toArray(groupArr));
				}
				if(associationArrList.size() > 0){
					associationArr = new Association[associationArrList.size()];
					searchObjectGroup.setAssociation(associationArrList.toArray(associationArr));
				}
				if(attributeArrList.size() > 0){
					attributeArr = new Attribute[attributeArrList.size()];
					searchObjectGroup.setAttribute(attributeArrList.toArray(attributeArr));
				}
				
				if(searchObjectRequiresAssociation){
					searchObjectAssociation.setGroup(searchObjectGroup);
					searchObjectToCQLMap.put(key, searchObjectAssociation);
				}else{
					searchObjectToCQLMap.put(key, searchObjectGroup);
				}
				
			}else{
				// There is just one element in the list so this needs to be added to the map searchObjectToCQLMap directly or 
				// add it to the association incase searchObjectRequiresAssociation
				
				if(searchObjectRequiresAssociation){
					Object obj = groupList.get(0);
					Association leafAssociation = searchObjectAssociation;
					while(leafAssociation.getAssociation() != null){
						leafAssociation = leafAssociation.getAssociation();
					}
					
					if(obj instanceof Group){
						leafAssociation.setGroup((Group)obj);
					}else if(obj instanceof Association){
						leafAssociation.setAssociation((Association)obj);
					}else if(obj instanceof Attribute){
						leafAssociation.setAttribute((Attribute) obj);
					}
					searchObjectToCQLMap.put(key, searchObjectAssociation);
				}else{
					searchObjectToCQLMap.put(key, groupList.get(0));
				}
			}
			
			
		}
		groupArrList.clear();
		attributeArrList.clear();
		associationArrList.clear();
		groupArr = null;
		associationArr = null;
		attributeArr = null;
		
		Collection<Object> CQLElements = searchObjectToCQLMap.values();
		if(CQLElements.size() > 1){
			Group group = new Group();
			//TODO: set the groups logical operator correctly
			group.setLogicRelation(LogicalOperator.OR);
			cqlQuery.getTarget().setGroup(group);
			Iterator iterator = CQLElements.iterator();
			while(iterator.hasNext()){
				Object element = iterator.next();
				if(element instanceof Attribute)
					attributeArrList.add((Attribute) element);
				else if(element instanceof Association)
					associationArrList.add((Association) element);
				else if(element instanceof Group)
					groupArrList.add((Group) element);
			}
			
			if(associationArrList.size() > 0){
				associationArr = new Association[associationArrList.size()];
				cqlQuery.getTarget().getGroup().setAssociation(associationArrList.toArray(associationArr));
			}
			
			if(groupArrList.size() > 0){
				groupArr = new Group[groupArrList.size()];
				cqlQuery.getTarget().getGroup().setGroup(groupArrList.toArray(groupArr));
			}
			
			if(attributeArrList.size() > 0){
				attributeArr = new Attribute[attributeArrList.size()];
				cqlQuery.getTarget().getGroup().setAttribute(attributeArrList.toArray(attributeArr));
			}
		}else{
			Object element = CQLElements.iterator().next();
			if(element instanceof Attribute)
				cqlQuery.getTarget().setAttribute((Attribute) element);
			else if(element instanceof Group)
				cqlQuery.getTarget().setGroup((Group) element);
			else if(element instanceof Association)
				cqlQuery.getTarget().setAssociation((Association) element);
		}
		
		return cqlQuery;
	}
}