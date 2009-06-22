package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.web.search.ui.AdvancedSearchUi;
import gov.nih.nci.cabig.caaers.web.search.ui.DependentObject;
import gov.nih.nci.cabig.caaers.web.search.ui.SearchTargetObject;
import gov.nih.nci.cabig.caaers.web.search.ui.UiAttribute;

/**
 * This is a utility class for AdvancedSearchUi.
 *
 * @author Sameer Sawant
 */
public class AdvancedSearchUiUtil{
	
	public static SearchTargetObject getSearchTargetObjectByName(AdvancedSearchUi advancedSearchUi, String targetObjectName){
		for(SearchTargetObject stObject: advancedSearchUi.getSearchTargetObject())
			if(stObject.getClassName().equals(targetObjectName))
				return stObject;
		return null;
	}
	
	public static UiAttribute getUiAttributeByName(DependentObject dObject, String attributeName){
		for(UiAttribute attr: dObject.getUiAttribute())
			if(attr.getName().equals(attributeName))
				return attr;
		return null;
	}
	
	public static DependentObject getDependentObjectByName(SearchTargetObject stObject, String dependentObjectName){
		for(DependentObject dObject: stObject.getDependentObject())
			if(dObject.getClassName().equals(dependentObjectName))
				return dObject;
		return null;
	}
	
	public static DependentObject getDependentObjectByDisplayName(SearchTargetObject stObject, String dependentObjectDisplayName){
		for(DependentObject dObject: stObject.getDependentObject())
			if(dObject.getDisplayName().equals(dependentObjectDisplayName))
				return dObject;
		return null;
	}
}