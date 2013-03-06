/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.web.search.ui.AdvancedSearchUi;
import gov.nih.nci.cabig.caaers.web.search.ui.DependentObject;
import gov.nih.nci.cabig.caaers.web.search.ui.SearchTargetObject;
import gov.nih.nci.cabig.caaers.web.search.ui.ViewColumn;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

/**
 * This class tests Command2SQL convertor.
 * 
 * This class tests Command2SQl convertor for 5 different queries listed below
 * Query1 - Show all adverse events with grade >= 2 which occured on all multi-institutional studies.
 * Query2 - Show all adverse events requiring reporting and the report(s) they were included in
 * Query3 - Show all the participants registered on a study
 * Query4 - Show all studies that have sponsor X and/or coordinating center Y and or site Z
 * @author Sameer Sawant
 */
public class CommandToSQLTest extends TestCase {
	List<AdvancedSearchCriteriaParameter> criteriaParameters;
	SearchTargetObject targetObject;
	AdvancedSearchUi advancedSearchUi;
	
	
	protected void setUp() throws Exception {
        super.setUp();
        criteriaParameters = new ArrayList<AdvancedSearchCriteriaParameter>();    
        advancedSearchUi = AdvancedSearchUiUtil.loadAdvancedSearchUi();
    }

	
	public void testTest() throws Exception {
		createStudySearchXML();
		AbstractQuery aq = CommandToSQL.transform(targetObject, criteriaParameters);
		String expectedHql = "select  distinct s from Study s WHERE lower(s.shortTitle) like :shortTitleText AND terminology.term = :term";
		assertEquals(expectedHql,aq.getQueryString());
		System.out.println(aq.getQueryString());
	}
	
	public void createStudySearchXML(){
		// Setup searchTargetObject
		targetObject = AdvancedSearchUiUtil.getSearchTargetObjectByName(advancedSearchUi, Study.class.getName());
		
		
		// Setup view
		for(DependentObject dObject: targetObject.getDependentObject()){
			if(dObject.getClassName().equals(Study.class.getName())){
				dObject.setInView(true);				
			}

			for(ViewColumn vColumn: dObject.getViewColumn()){
				if (vColumn.getColumnAttribute().equals("shortTitle")) {
					vColumn.setSelected(true);
				}
			}
		}
		
		// Setup parameters
		criteriaParameters.clear();
		AdvancedSearchCriteriaParameter parameter = createStudyCriteria("shortTitle", "like", "test" , "String", "filterByStudyShortTitle");
		criteriaParameters.add(parameter);
		parameter = createStudyCriteria("term.code", "=", "1" , "Integer", "filterByTerminology");
		criteriaParameters.add(parameter);
		//criteriaParameters.add(parameter1);
		
	}

	
	private AdvancedSearchCriteriaParameter createParticipantCriteria(String attributeName, String predicate, String value){
		AdvancedSearchCriteriaParameter parameter = new AdvancedSearchCriteriaParameter();
		parameter.setAttributeName(attributeName);
		parameter.setPredicate(predicate);
		parameter.setValue(value);
		parameter.setObjectName("gov.nih.nci.cabig.caaers.domain.Participant");
		return parameter;
	}
	
	private AdvancedSearchCriteriaParameter createStudyCriteria(String attributeName, String predicate, String value){
		AdvancedSearchCriteriaParameter parameter = new AdvancedSearchCriteriaParameter();
		parameter.setAttributeName(attributeName);
		parameter.setPredicate(predicate);
		parameter.setValue(value);
		parameter.setObjectName("gov.nih.nci.cabig.caaers.domain.Study");

		return parameter;
	}
	private AdvancedSearchCriteriaParameter createStudyCriteria(String attributeName, String predicate, String value, String dataType , String filterByMethodInQueryClass){
		AdvancedSearchCriteriaParameter parameter = new AdvancedSearchCriteriaParameter();
		parameter.setAttributeName(attributeName);
		parameter.setPredicate(predicate);
		parameter.setValue(value);
		parameter.setObjectName("gov.nih.nci.cabig.caaers.domain.Study");
		parameter.setFilterByMethodInQueryClass(filterByMethodInQueryClass);
		parameter.setDataType(dataType);
		return parameter;
	}	
	private AdvancedSearchCriteriaParameter createAdverseEventCriteria(String attributeName, String predicate, String value){
		AdvancedSearchCriteriaParameter parameter = new AdvancedSearchCriteriaParameter();
		parameter.setAttributeName(attributeName);
		parameter.setPredicate(predicate);
		parameter.setValue(value);
		parameter.setObjectName("gov.nih.nci.cabig.caaers.domain.AdverseEvent");
		return parameter;
	}
	
}
