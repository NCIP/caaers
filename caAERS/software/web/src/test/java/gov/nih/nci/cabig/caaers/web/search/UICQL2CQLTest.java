package gov.nih.nci.cabig.caaers.web.search;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import gov.nih.nci.cabig.caaers.web.ListValues;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.cabig.caaers.web.search.ui.AdvancedSearchUi;
import gov.nih.nci.cabig.caaers.web.search.ui.SearchTargetObject;

/**
 * This class tests UICQL2CQL class
 *
 * @author Sameer Sawant
 */
public class UICQL2CQLTest extends WebTestCase {
	AdvancedSearchCommand command;
	AdvancedSearchUi advancedSearchUi;
	
	protected void setUp() throws Exception {
        super.setUp();
        setupCommand();
    }
	
	
	/**
	 * This method tests the following criteria parameters - 
	 * Return object - Participant 
	 *        Object           Attribute           Predicate           Value
	 * ==============================================================================       
	 *      Participant       firstName             Equal To          testFirstName 
	 * 
	 * 
	 * @throws Exception
	 */
	public void testTransform1() throws Exception{
		AdvancedSearchCriteriaParameter parameter = createParticipantCriteria("firstName", "Equal To", "testFirstName");
		command.getCriteriaParameters().add(parameter);
		SearchTargetObject stObject = AdvancedSearchUiUtil.getSearchTargetObjectByName(command.getAdvancedSearchUi(), parameter.getObjectName());
		command.setSearchTargetObject(stObject);
		UICQL2CQL.transform(command.getCriteriaParameters(), command.getSearchTargetObject());
	}
	
	/**
	 * This method tests the following criteria parameters - 
	 * Return object - Participant 
	 *        Object           Attribute           Predicate           Value
	 * ==============================================================================       
	 *      Participant       firstName             Equal To          testFirstName 
	 *      Participant       lastName              Equal To          testLastName
	 * 
	 * @throws Exception
	 */
	public void testTransform2() throws Exception{
		AdvancedSearchCriteriaParameter parameter = createParticipantCriteria("firstName", "Equal To", "testFirstName");
		command.getCriteriaParameters().add(parameter);
		parameter = createParticipantCriteria("lastName", "Equal To", "testLastName");
		command.getCriteriaParameters().add(parameter);
		SearchTargetObject stObject = AdvancedSearchUiUtil.getSearchTargetObjectByName(command.getAdvancedSearchUi(), parameter.getObjectName());
		command.setSearchTargetObject(stObject);
		UICQL2CQL.transform(command.getCriteriaParameters(), command.getSearchTargetObject());
	}
	
	/**
	 * This method tests the following criteria parameters - 
	 * Return object - Participant 
	 *        Object           Attribute           Predicate           Value
	 * ==============================================================================       
	 *         Study           shortTitle          Equal To          testShortTitle 
	 * 
	 * 
	 * @throws Exception
	 */
	public void testTransform3() throws Exception{
		AdvancedSearchCriteriaParameter parameter = createStudyCriteria("shortTitle", "Equal To", "testShortTitle");
		command.getCriteriaParameters().add(parameter);
		SearchTargetObject stObject = AdvancedSearchUiUtil.getSearchTargetObjectByName(command.getAdvancedSearchUi(), parameter.getObjectName());
		command.setSearchTargetObject(stObject);
		UICQL2CQL.transform(command.getCriteriaParameters(), command.getSearchTargetObject());
	}
	
	
	/**
	 * This method tests the following criteria parameters - 
	 * Return object - Participant 
	 *        Object           Attribute           Predicate           Value
	 * ==============================================================================       
	 *         Study           shortTitle          Equal To          testShortTitle 
	 *        Participant      firstName           Equal To          testFirstName
	 * 
	 * @throws Exception
	 */
	public void testTransform4() throws Exception{
		AdvancedSearchCriteriaParameter parameter = createParticipantCriteria("firstName", "Equal To", "testFirstName");
		command.getCriteriaParameters().add(parameter);
		SearchTargetObject stObject = AdvancedSearchUiUtil.getSearchTargetObjectByName(command.getAdvancedSearchUi(), parameter.getObjectName());
		command.setSearchTargetObject(stObject);
		parameter = createStudyCriteria("shortTitle", "Equal To", "testShortTitle");
		command.getCriteriaParameters().add(parameter);
		
		UICQL2CQL.transform(command.getCriteriaParameters(), command.getSearchTargetObject());
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