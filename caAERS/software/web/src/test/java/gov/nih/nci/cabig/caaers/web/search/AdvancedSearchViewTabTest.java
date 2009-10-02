package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.domain.LocalStudy;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.cabig.caaers.web.search.ui.AdvancedSearchUi;
import gov.nih.nci.cabig.caaers.web.search.ui.DependentObject;
import gov.nih.nci.cabig.caaers.web.search.ui.ViewColumn;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

/**
 * This class tests the AdvancedSearchViewTab
 * @author Sameer Sawant
 */

public class AdvancedSearchViewTabTest extends WebTestCase{
	AdvancedSearchViewTab tab;
	AdvancedSearchCommand command;
	Errors errors;
	AdvancedSearchUi advancedSearchUi;
	
	protected void setUp() throws Exception {
		super.setUp();
		tab = new AdvancedSearchViewTab();
		
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
		setupCommand();
		errors = new BindException(command, "command");
	}
	
	public void testProcessMultipleObjectsListForNestedView() throws Exception{
		List<Object[]> objectList = createTestObjectList();
		tab.processMultipleObjectsListForNestedView(objectList, command);
		assertNotNull("rowList cannot be null", command.getAdvancedSearchRowList());
	}
	
	private void setupCommand(){
		command.setSearchTargetObject(AdvancedSearchUiUtil.getSearchTargetObjectByName(advancedSearchUi, "gov.nih.nci.cabig.caaers.domain.Participant"));
		// Setup view for Participant Dependent Object
		DependentObject participantDependentObject = AdvancedSearchUiUtil.getDependentObjectByName(command.getSearchTargetObject(), 
				"gov.nih.nci.cabig.caaers.domain.Participant");
		participantDependentObject.setInView(true);
		for(ViewColumn v: participantDependentObject.getViewColumn()){
			if(v.getColumnAttribute().equals("firstName") || v.getColumnAttribute().equals("lastName"))
				v.setSelected(true);
			else
				v.setSelected(false);
		}
		
		// Setup view for Study Dependent Object
		DependentObject studyDependentObject = AdvancedSearchUiUtil.getDependentObjectByName(command.getSearchTargetObject(), 
				"gov.nih.nci.cabig.caaers.domain.Study");
		studyDependentObject.setInView(true);
		for(ViewColumn v: studyDependentObject.getViewColumn()){
			if(v.getColumnAttribute().equals("shortTitle") || v.getColumnAttribute().equals("longTitle"))
				v.setSelected(true);
			else
				v.setSelected(false);
		}
		
		// Setup view for AdverseEvent Dependent Object.
		/*DependentObject aeDependentObject = AdvancedSearchUiUtil.getDependentObjectByName(command.getSearchTargetObject(),
				"gov.nih.nci.cabig.caaers.domain.AdverseEvent");
		aeDependentObject.setInView(true);
		for(ViewColumn v: aeDependentObject.getViewColumn()){
			if(v.getColumnAttribute().equals("grade") || v.getColumnAttribute().equals("expected"))
				v.setSelected(true);
			else
				v.setSelected(false);
		}*/
	}
	
	private List<Object[]> createTestObjectList(){
		List<Object[]> objectList= new ArrayList<Object[]>();
		for(int i = 0; i < 5; i++){;
			Object[] objectArr = new Object[2];
			Participant p = new Participant();
			p.setFirstName("firstName" + i);
			p.setLastName("lastName" + i);
			Study s = new LocalStudy();
			s.setShortTitle("shortTitle" + i);
			s.setLongTitle("longTitle" + i);
			//AdverseEvent ae = new AdverseEvent();
			//ae.setGrade(Grade.MODERATE);
			//ae.setExpected(true);
			objectArr[0] = p;
			objectArr[1] = s;
			//objectArr[2] = ae;
			objectList.add(objectArr);
		}
		objectList.set(1, objectList.get(0));
		return objectList;
	}
}