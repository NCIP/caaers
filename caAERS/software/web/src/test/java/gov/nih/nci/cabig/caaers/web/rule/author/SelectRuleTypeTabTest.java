package gov.nih.nci.cabig.caaers.web.rule.author;

import com.semanticbits.rules.api.RepositoryService;
import com.semanticbits.rules.api.RuleAuthoringService;
import com.semanticbits.rules.api.RuleDeploymentService;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.NotificationDao;
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
    
    RuleAuthoringService ruleAuthoringService;
    StudyDao studyDao;
    NotificationDao notificationDao;
    CaaersRulesEngineService caaersRulesEngineService;
    ReportDefinitionDao reportDefinitionDao;
    OrganizationDao organizationDao;
    CtcDao ctcDao;
    RuleDeploymentService ruleDeploymentService;
    RepositoryService repositoryService;
    Errors errors;
    BeanWrapper commandWrapper;
    CreateRuleCommand command;
    SelectRuleTypeTab tab;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        ruleAuthoringService = registerMockFor(RuleAuthoringService.class);
        studyDao = registerDaoMockFor(StudyDao.class);
        notificationDao = registerDaoMockFor(NotificationDao.class);
        caaersRulesEngineService = registerMockFor(CaaersRulesEngineService.class);
        reportDefinitionDao = registerDaoMockFor(ReportDefinitionDao.class);
        organizationDao = registerDaoMockFor(OrganizationDao.class);
        ctcDao = registerDaoMockFor(CtcDao.class);
        ruleDeploymentService = registerMockFor(RuleDeploymentService.class);
        repositoryService = registerMockFor(RepositoryService.class);

        command = new CreateRuleCommand(ruleAuthoringService, studyDao, notificationDao, caaersRulesEngineService, reportDefinitionDao, organizationDao,
                ctcDao, ruleDeploymentService, repositoryService);
        commandWrapper = new BeanWrapperImpl(command);
        tab = new SelectRuleTypeTab();
        errors = new BindException(command, "command");
        
    }

    //no validation error
    public void testValidate() throws Exception {
       command.setRuleSetName("Field Rules");
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
