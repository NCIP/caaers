package gov.nih.nci.cabig.caaers.web.search;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.cabig.caaers.web.search.ui.AdvancedSearchUi;
import gov.nih.nci.cabig.caaers.web.search.ui.DependentObject;
import gov.nih.nci.cabig.caaers.web.search.ui.SearchTargetObject;
import gov.nih.nci.cabig.caaers.web.search.ui.ViewColumn;

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
public class CommandToSQLTest extends AbstractTestCase {
	AdvancedSearchCommand command;
	List<AdvancedSearchCriteriaParameter> criteriaParameters;
	SearchTargetObject targetObject;
	AdvancedSearchUi advancedSearchUi;
	
	protected void setUp() throws Exception {
        super.setUp();
        setupCommand();
        criteriaParameters = new ArrayList<AdvancedSearchCriteriaParameter>();
    }

	public void testGetProjectionStringForQuery1() throws Exception{
		createDataForQuery1();
		CommandToSQL.initializeTableToAliasMap(targetObject, criteriaParameters);
		String projectionString = CommandToSQL.getProjectionString(targetObject);
		String expectedProjectionString = "select AdverseEvent_xx, Study_xx";
		assertEquals("Projection String is created incorrectly", expectedProjectionString, projectionString);
		assert(true);
	}

	public void testGetProjectionStringForQuery2() throws Exception{
		createDataForQuery2();
		CommandToSQL.initializeTableToAliasMap(targetObject, criteriaParameters);
		String projectionString = CommandToSQL.getProjectionString(targetObject);
		String expectedProjectionString = "select AdverseEvent_xx";
		assertEquals("Projection String is created incorrectly", expectedProjectionString, projectionString);
		assert(true);
	}

	public void testGetProjectionStringForQuery3() throws Exception{
		createDataForQuery3();
		CommandToSQL.initializeTableToAliasMap(targetObject, criteriaParameters);
		String projectionString = CommandToSQL.getProjectionString(targetObject);
		String expectedProjectionString = "select Participant_xx, Study_xx";
		assertEquals("Projection String is created incorrectly", expectedProjectionString, projectionString);
		assert(true);
	}

	public void testGetProjectionStringForQuery4() throws Exception{
		createDataForQuery4();
		CommandToSQL.initializeTableToAliasMap(targetObject, criteriaParameters);
		String projectionString = CommandToSQL.getProjectionString(targetObject);
		String expectedProjectionString = "select Study_xx, Organization_xx";
		assertEquals("Projection String is created incorrectly", expectedProjectionString, projectionString);
		assert(true);
	}

	public void testGetFromTablesStringForQuery1() throws Exception{
		createDataForQuery1();
		CommandToSQL.initializeTableToAliasMap(targetObject, criteriaParameters);
		String fromTablesString = CommandToSQL.getFromTablesString(targetObject, criteriaParameters);
		String expectedFromTablesString = "from gov.nih.nci.cabig.caaers.domain.AdverseEvent AdverseEvent_xx, " +
			"gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod AdverseEventReportingPeriod_xx, " +
			"gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment StudyParticipantAssignment_xx, " +
			"gov.nih.nci.cabig.caaers.domain.StudyOrganization StudyOrganization_xx, gov.nih.nci.cabig.caaers.domain.Study Study_xx";
		assertEquals("From tables string is created incorrectly", expectedFromTablesString, fromTablesString);
		assert(true);
	}

	public void testGetFromTablesStringForQuery2() throws Exception{
		createDataForQuery2();
		CommandToSQL.initializeTableToAliasMap(targetObject, criteriaParameters);
		String fromTablesString = CommandToSQL.getFromTablesString(targetObject, criteriaParameters);
		String expectedFromTablesString = "from gov.nih.nci.cabig.caaers.domain.AdverseEvent AdverseEvent_xx";
		assertEquals("From tables string is created incorrectly", expectedFromTablesString, fromTablesString);
		assert(true);
	}

	public void testGetFromTablesStringForQuery3() throws Exception{
		createDataForQuery3();
		CommandToSQL.initializeTableToAliasMap(targetObject, criteriaParameters);
		String fromTablesString = CommandToSQL.getFromTablesString(targetObject, criteriaParameters);
		String expectedFromTablesString = "from gov.nih.nci.cabig.caaers.domain.Participant Participant_xx, " +
			"gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment StudyParticipantAssignment_xx, " +
			"gov.nih.nci.cabig.caaers.domain.StudyOrganization StudyOrganization_xx, gov.nih.nci.cabig.caaers.domain.Study Study_xx";
		assertEquals("From tables string is created incorrectly", expectedFromTablesString, fromTablesString);
		assert(true);
	}

	public void testGetFromTablesStringForQuery4() throws Exception{
		createDataForQuery4();
		CommandToSQL.initializeTableToAliasMap(targetObject, criteriaParameters);
		String fromTablesString = CommandToSQL.getFromTablesString(targetObject, criteriaParameters);
		String expectedFromTablesString = "from gov.nih.nci.cabig.caaers.domain.Study Study_xx, " +
			"gov.nih.nci.cabig.caaers.domain.StudyOrganization StudyOrganization_xx, " + "" +
			"gov.nih.nci.cabig.caaers.domain.Organization Organization_xx";
		assertEquals("From tables string is created incorrectly", expectedFromTablesString, fromTablesString);
		assert(true);
	}

	public void testGetJoiningConditionStringForQuery1() throws Exception{
		createDataForQuery1();
		CommandToSQL.initializeTableToAliasMap(targetObject, criteriaParameters);
		String joiningString = CommandToSQL.getJoiningConditionString(targetObject, criteriaParameters);
		String expectedJoiningString = "AdverseEvent_xx.reportingPeriod = AdverseEventReportingPeriod_xx and " +
			"AdverseEventReportingPeriod_xx.assignment = StudyParticipantAssignment_xx and " +
			"StudyParticipantAssignment_xx.studySite = StudyOrganization_xx and StudyOrganization_xx.study = Study_xx";
		assertEquals("Joining string is created incorrectly", expectedJoiningString, joiningString);
		assert(true);
	}

	public void testGetJoiningConditionStringForQuery3() throws Exception{
		createDataForQuery3();
		CommandToSQL.initializeTableToAliasMap(targetObject, criteriaParameters);
		String joiningString = CommandToSQL.getJoiningConditionString(targetObject, criteriaParameters);
		String expectedJoiningString = "Participant_xx = StudyParticipantAssignment_xx.participant and " +
			"StudyParticipantAssignment_xx.studySite = StudyOrganization_xx and StudyOrganization_xx.study = Study_xx"; 
		assertEquals("Joining string is created incorrectly", expectedJoiningString, joiningString);
		assert(true);
	}

	public void testGetJoiningConditionStringForQuery4() throws Exception{
		createDataForQuery4();
		CommandToSQL.initializeTableToAliasMap(targetObject, criteriaParameters);
		String joiningString = CommandToSQL.getJoiningConditionString(targetObject, criteriaParameters);
		String expectedJoiningString = "Study_xx = StudyOrganization_xx.study and " + 
			"StudyOrganization_xx.organization = Organization_xx";
		assertEquals("Joining string is created incorrectly", expectedJoiningString, joiningString);
		assert(true);
	}
	
	public void testGetCriteriaConditionStringForQuery1() throws Exception{
		createDataForQuery1();
		CommandToSQL.initializeTableToAliasMap(targetObject, criteriaParameters);
		String criteriaConditionString = CommandToSQL.getCriteriaConditionString(targetObject, criteriaParameters, true);
		String expectedCriteriaConditionString = "Study_xx.multiInstitutionIndicator = true and AdverseEvent_xx.grade >= '2'";
		assertEquals("CriteriaConditionString is created incorrectly", expectedCriteriaConditionString, criteriaConditionString);
		assert(true);
	}

	public void testGetCriteriaConditionStringForQuery2() throws Exception{
		createDataForQuery2();
		CommandToSQL.initializeTableToAliasMap(targetObject, criteriaParameters);
		String criteriaConditionString = CommandToSQL.getCriteriaConditionString(targetObject, criteriaParameters, true);
		String expectedCriteriaConditionString = "AdverseEvent_xx.requiresReporting = true";
		assertEquals("CriteriaConditionString is created incorrectly", expectedCriteriaConditionString, criteriaConditionString);
		assert(true);
	}

	public void testGetCriteriaConditionStringForQuery3() throws Exception{
		createDataForQuery3();
		CommandToSQL.initializeTableToAliasMap(targetObject, criteriaParameters);
		String criteriaConditionString = CommandToSQL.getCriteriaConditionString(targetObject, criteriaParameters, true);
		String expectedCriteriaConditionString = "Study_xx.id = '5'";
		assertEquals("CriteriaConditionString is created incorrectly", expectedCriteriaConditionString, criteriaConditionString);
		assert(true);
	}

	public void testGetCriteriaConditionStringForQuery4() throws Exception{
		createDataForQuery4();
		CommandToSQL.initializeTableToAliasMap(targetObject, criteriaParameters);
		String criteriaConditionString = CommandToSQL.getCriteriaConditionString(targetObject, criteriaParameters, true);
		String expectedCriteriaConditionString = "Organization_xx.id = '2'";
		assertEquals("CriteriaConditionString is created incorrectly", expectedCriteriaConditionString, criteriaConditionString);
		assert(true);
	}

	public void setupCommand(){
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("advancedSearch-ui.xml");
        Unmarshaller unmarshaller;
		try {
			unmarshaller = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.web.search.ui").createUnmarshaller();
			advancedSearchUi = (AdvancedSearchUi) unmarshaller.unmarshal(inputStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		command = new AdvancedSearchCommand(advancedSearchUi);
	}
	
	/**
	 * This method sets up data for Query 1.
	 * Query1 - Show all adverse events with grade >= 2 which occured on all multi-institutional studies.
	 */
	public void createDataForQuery1(){
		// Setup searchTargetObject
		targetObject = AdvancedSearchUiUtil.getSearchTargetObjectByName(command.getAdvancedSearchUi(), AdverseEvent.class.getName());
		
		// Setup view
		for(DependentObject dObject: targetObject.getDependentObject()){
			if(dObject.getClassName().equals(AdverseEvent.class.getName()) || dObject.getClassName().equals(Study.class.getName())){
				dObject.setInView(true);
				for(ViewColumn vColumn: dObject.getViewColumn()){
					vColumn.setSelected(true);
				}
			}else
				dObject.setInView(false);
		}
		
		// Setup parameters
		criteriaParameters.clear();
		AdvancedSearchCriteriaParameter parameter = createStudyCriteria("multiInstitutionIndicator", "=", "true");
		criteriaParameters.add(parameter);
		parameter = createAdverseEventCriteria("grade.code", ">=", "2");
		criteriaParameters.add(parameter);
	}
	
	/**
	 * This method sets up data for Query 2.
	 * Query2 - Show all adverse events requiring reporting and the report(s) they were included in
	 */
	public void createDataForQuery2(){
		// Setup searchTargetObject
		targetObject = AdvancedSearchUiUtil.getSearchTargetObjectByName(command.getAdvancedSearchUi(), AdverseEvent.class.getName());
		
		// Setup view
		for(DependentObject dObject: targetObject.getDependentObject()){
			if(dObject.getClassName().equals(AdverseEvent.class.getName()) || dObject.getClassName().equals(Report.class.getName())){
				dObject.setInView(true);
				for(ViewColumn vColumn: dObject.getViewColumn()){
					vColumn.setSelected(true);
				}
			}else
				dObject.setInView(false);
		}
		
		// Setup parameters
		criteriaParameters.clear();
		AdvancedSearchCriteriaParameter parameter = createAdverseEventCriteria("requiresReporting", "=", "true");
		criteriaParameters.add(parameter);
	}
	
	/**
	 * This method sets up data for Query 3.
	 * Query3 - Show all the participants registered on a study
	 */
	public void createDataForQuery3(){
		// Setup searchTargetObject
		targetObject = AdvancedSearchUiUtil.getSearchTargetObjectByName(command.getAdvancedSearchUi(), Participant.class.getName());
		
		// Setup view
		for(DependentObject dObject: targetObject.getDependentObject()){
			if(dObject.getClassName().equals(Participant.class.getName()) || dObject.getClassName().equals(Study.class.getName())){
				dObject.setInView(true);
				for(ViewColumn vColumn: dObject.getViewColumn()){
					vColumn.setSelected(true);
				}
			}else
				dObject.setInView(false);
		}
		
		// Setup parameters
		criteriaParameters.clear();
		AdvancedSearchCriteriaParameter parameter = createStudyCriteria("id", "=", "5");
		criteriaParameters.add(parameter);
	}
	
	/**
	 * This method sets up data for Query 4.
	 * Query4 - Show all studies that have sponsor X and/or coordinating center Y and or site Z
	 */
	public void createDataForQuery4(){
		// Setup searchTargetObject
		targetObject = AdvancedSearchUiUtil.getSearchTargetObjectByName(command.getAdvancedSearchUi(), Study.class.getName());
		
		// Setup view
		for(DependentObject dObject: targetObject.getDependentObject()){
			if(dObject.getClassName().equals(Study.class.getName()) || dObject.getClassName().equals(Organization.class.getName())){
				dObject.setInView(true);
				for(ViewColumn vColumn: dObject.getViewColumn()){
					vColumn.setSelected(true);
				}
			}else
				dObject.setInView(false);
		}
		
		// Setup parameters
		criteriaParameters.clear();
		AdvancedSearchCriteriaParameter parameter = new AdvancedSearchCriteriaParameter();
		parameter.setAttributeName("id");
		parameter.setPredicate("=");
		parameter.setValue("2");
		parameter.setObjectName("gov.nih.nci.cabig.caaers.domain.Organization");
		criteriaParameters.add(parameter);
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
	
	private AdvancedSearchCriteriaParameter createAdverseEventCriteria(String attributeName, String predicate, String value){
		AdvancedSearchCriteriaParameter parameter = new AdvancedSearchCriteriaParameter();
		parameter.setAttributeName(attributeName);
		parameter.setPredicate(predicate);
		parameter.setValue(value);
		parameter.setObjectName("gov.nih.nci.cabig.caaers.domain.AdverseEvent");
		return parameter;
	}
	
}