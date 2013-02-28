/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.RuleSetDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.query.AbstractQuery;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.RuleSet;
import gov.nih.nci.cabig.caaers.rules.business.service.CaaersRulesEngineService;
import gov.nih.nci.cabig.caaers.rules.common.RuleType;
import org.easymock.classextension.EasyMock;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

import java.util.Arrays;
import java.util.List;

/**
 * @author: Biju Joseph
 */
public class SelectRuleTypeTabTest extends AbstractTestCase {
    
    CaaersRulesEngineService caaersRulesEngineService;
    ReportDefinitionDao reportDefinitionDao;
    OrganizationDao organizationDao;
    Errors errors;
    BeanWrapper commandWrapper;
    CreateRuleCommand command;
    SelectRuleTypeTab tab;
    StudyDao studyDao;
    RuleSetDao ruleSetDao;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        studyDao = registerDaoMockFor(StudyDao.class);
        caaersRulesEngineService = registerMockFor(CaaersRulesEngineService.class);
        reportDefinitionDao = registerDaoMockFor(ReportDefinitionDao.class);
        organizationDao = registerDaoMockFor(OrganizationDao.class);
        ruleSetDao = registerDaoMockFor(RuleSetDao.class);

        command = new CreateRuleCommand(caaersRulesEngineService, reportDefinitionDao, organizationDao, studyDao);
        command.setCaaersRuleSet(Fixtures.createRuleSet());
        commandWrapper = new BeanWrapperImpl(command);
        tab = new SelectRuleTypeTab();
        errors = new BindException(command, "command");
        tab.setRuleSetDao(ruleSetDao);
        
    }

    //no validation error
    public void testValidate() throws Exception {
        List ls = Arrays.asList();
        EasyMock.expect(ruleSetDao.search((AbstractQuery) EasyMock.anyObject()))
                .andReturn(ls);
        replayMocks();
       tab.validate(command, commandWrapper, null,  errors);
        System.out.println(errors);
       assertFalse(errors.hasErrors());
        verifyMocks();
    }

    //checks when the rule type is field level rules
    public void testValidation_havingError(){

        List ls = Arrays.asList(Fixtures.createRuleSet());
        
        EasyMock.expect(ruleSetDao.search((AbstractQuery) EasyMock.anyObject()))
                .andReturn(ls);
        replayMocks();
      tab.validate(command, commandWrapper, null,  errors);
        System.out.println(errors);
       assertTrue(errors.hasErrors());
       assertEquals(1, errors.getErrorCount());
       assertTrue(errors.getGlobalErrors().get(0).getCode().equals("RUL_026"));
        verifyMocks();
    }


}
