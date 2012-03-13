package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.rules.business.service.CaaersRulesEngineService;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

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

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        studyDao = registerDaoMockFor(StudyDao.class);
        caaersRulesEngineService = registerMockFor(CaaersRulesEngineService.class);
        reportDefinitionDao = registerDaoMockFor(ReportDefinitionDao.class);
        organizationDao = registerDaoMockFor(OrganizationDao.class);

        command = new CreateRuleCommand(caaersRulesEngineService, reportDefinitionDao, organizationDao, studyDao);
        commandWrapper = new BeanWrapperImpl(command);
        tab = new SelectRuleTypeTab();
        errors = new BindException(command, "command");
        
    }

    //no validation error
    public void testValidate() throws Exception {
       tab.validate(command, commandWrapper, null,  errors);
       assertFalse(errors.hasErrors());
    }

    //checks when the rule type is field level rules
    public void testValidate_FieldLevelRules(){
      tab.validate(command, commandWrapper, null,  errors);
       assertTrue(errors.hasErrors());
       assertEquals(1, errors.getErrorCount());
       assertNotNull(errors.getFieldError("ruleSetName"));
    }


}
