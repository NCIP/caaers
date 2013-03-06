/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.search;

import static org.easymock.EasyMock.expect;
import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.SearchDao;
import gov.nih.nci.cabig.caaers.domain.LocalStudy;
import gov.nih.nci.cabig.caaers.domain.repository.ajax.ParticipantAjaxableDomainObjectRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ajax.StudySearchableAjaxableDomainObjectRepository;
import gov.nih.nci.cabig.caaers.security.SecurityTestUtils;
import gov.nih.nci.cabig.caaers.web.DwrFacadeTestCase;
import gov.nih.nci.cabig.caaers.web.dwr.AjaxOutput;
import gov.nih.nci.cabig.caaers.web.search.ui.DependentObject;
import gov.nih.nci.cabig.caaers.web.search.ui.SearchTargetObject;
import gov.nih.nci.cabig.caaers.web.search.ui.UiAttribute;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;

import org.easymock.EasyMock;

/**
 *
 * @author Ramakrishna Gundala
 */
public class AdvancedSearchAjaxFacadeTest extends DwrFacadeTestCase {
	
	protected AdvancedSearchAjaxFacade searchFacade;
	protected StudySearchableAjaxableDomainObjectRepository studySearchableAjaxableDomainObjectRepository;
	protected ParticipantAjaxableDomainObjectRepository participantAjaxableDomainObjectRepository;
	protected SearchDao searchDao;
	protected CtcTermDao ctcTermDao;
	protected AgentDao agentDao;
	protected AdvancedSearchCommand command;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		SecurityTestUtils.switchToSuperuser();
		searchFacade =  new AdvancedSearchAjaxFacade();
		command = new AdvancedSearchCommand();
		studySearchableAjaxableDomainObjectRepository = 
				registerMockFor(StudySearchableAjaxableDomainObjectRepository.class);
		participantAjaxableDomainObjectRepository = 
				registerMockFor(ParticipantAjaxableDomainObjectRepository.class);
		searchDao = registerDaoMockFor(SearchDao.class);
		ctcTermDao = registerDaoMockFor(CtcTermDao.class);
		agentDao = registerDaoMockFor(AgentDao.class);
		
		searchFacade.setAgentDao(agentDao);
		searchFacade.setCtcTermDao(ctcTermDao);
		searchFacade.setParticipantAjaxableDomainObjectRepository(participantAjaxableDomainObjectRepository);
		searchFacade.setSearchDao(searchDao);
		searchFacade.setStudySearchableAjaxableDomainObjectRepository(studySearchableAjaxableDomainObjectRepository);
		
		expect(webContext.getSession()).andReturn(session).anyTimes();
		session.setAttribute("gov.nih.nci.cabig.caaers.web.search.AdvancedSearchController.FORM.command",command);
		
	}
	
	public void testupdateSearchTargetObject(){
		replayMocks();
		AjaxOutput test = searchFacade.updateSearchTargetObject("");
		assertNotNull(test);
	}
	
	
	public void testupdateAttribute(){
		List<AdvancedSearchCriteriaParameter> criteriaParams = new ArrayList<AdvancedSearchCriteriaParameter>();
		AdvancedSearchCriteriaParameter param1 = new AdvancedSearchCriteriaParameter();
		param1.setAttributeName("type");
		param1.setObjectName("Identifier");
		param1.setPredicate("=");
		param1.setValue("val1");
		param1.setDependentObjectName("gov.nih.nci.cabig.caaers.domain.LocalStudy");
		criteriaParams.add(param1);
		SearchTargetObject searchTObject = new SearchTargetObject();
		DependentObject depObject1 = new DependentObject();
		depObject1.setClassName(LocalStudy.class.getName());
		searchTObject.setDependentObject(Arrays.asList(new DependentObject[]{depObject1}));
		command.setSearchTargetObject(searchTObject);
		command.setCriteriaParameters(criteriaParams);
		expect(webContext.getCurrentPage()).andReturn("0");
		DependentObject dpObject = new DependentObject();
		
		List<UiAttribute> uiAttributes = new ArrayList<UiAttribute>();
		UiAttribute uiAttribute = new UiAttribute();
		uiAttribute.setName("title");
		uiAttribute.setDataType("String");
		uiAttribute.setAjaxMethod("addAttribute");
		uiAttributes.add(uiAttribute);
		
		SearchTargetObject sto = new SearchTargetObject();
		DependentObject dpO = new DependentObject();
		dpO.setClassName("Study");
		List<DependentObject> depObjs = new ArrayList<DependentObject>();
		depObjs.add(dpO);
		sto.setDependentObject(depObjs);
		String dependeontObjectName = "Study";
	
		
		dpObject.setUiAttribute(uiAttributes);
		AdvancedSearchUiUtil.getDependentObjectByName(sto,dependeontObjectName);
		try {
			expect(webContext.forwardToString("0?ajax_action=updateAttribute&index=0&attributeName=attributeName&subview=updateValueContent")).andReturn("<p></p>");
		} catch (ServletException e) {
			e.printStackTrace();
			fail();
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		replayMocks();
		AjaxOutput test = searchFacade.updateAttribute("attributeName", "attributeLabel", 0);
		assertNotNull(test);
	}
	
}
