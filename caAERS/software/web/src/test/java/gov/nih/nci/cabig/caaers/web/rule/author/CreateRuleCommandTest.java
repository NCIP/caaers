/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.query.ReportDefinitionQuery;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.report.Mandatory;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.rules.business.service.CaaersRulesEngineService;
import org.easymock.IArgumentMatcher;
import org.easymock.classextension.EasyMock;

import java.util.List;

/**
 * @author: Biju Joseph
 */
public class CreateRuleCommandTest extends AbstractTestCase {

    CreateRuleCommand command;
    CaaersRulesEngineService caaersRulesEngineService;
    ReportDefinitionDao reportDefinitionDao;
    OrganizationDao organizationDao;
    StudyDao studyDao;

    public  void setUp(){

        reportDefinitionDao = registerDaoMockFor(ReportDefinitionDao.class);

        command = new CreateRuleCommand(caaersRulesEngineService,
                reportDefinitionDao, organizationDao, studyDao);
    }

    //checks the mandatory options
    public void testGetMandatoryOptions() throws Exception {
        Mandatory[] options = command.getMandatoryOptions();
        assertSame(Mandatory.OPTIONAL, options[0]);
        assertSame(Mandatory.MANDATORY, options[1]);
        assertSame(Mandatory.NA, options[2]);
    }

    //tests a query with organization
    public void testFetchReportDefinitions(){

        final String expectedQuery = "select new ReportDefinition(rd.id, rd.name, rd.label) from ReportDefinition rd WHERE  rd.organization.id = :orgId";

        IArgumentMatcher queryMatcher = new IArgumentMatcher(){

            public void appendTo(StringBuffer buffer) {
                buffer.append("eqQuery(").append(expectedQuery).append(")");
            }

            public boolean matches(Object argument) {
                ReportDefinitionQuery query = (ReportDefinitionQuery) argument;
                if(query.getQueryString().equals(expectedQuery)) return true;
                return true;
            }
        };

        EasyMock.expect(reportDefinitionDao.search(matcher(queryMatcher))).andReturn(null);

        replayMocks();
        Organization org = Fixtures.createOrganization("test");
        org.setId(4);
        command.fetchReportDefinitions(org);

        verifyMocks();

    }

    //tests a query without organization
    public void testFetchReportDefinitionsWhenOrganziationIsNull(){

        final String expectedQuery = "select new ReportDefinition(rd.id, rd.name, rd.label) from ReportDefinition rd";

        IArgumentMatcher queryMatcher = new IArgumentMatcher(){

            public void appendTo(StringBuffer buffer) {
                buffer.append("eqQuery(").append(expectedQuery).append(")");
            }

            public boolean matches(Object argument) {
                ReportDefinitionQuery query = (ReportDefinitionQuery) argument;
                if(query.getQueryString().equals(expectedQuery)) return true;
                return false;
            }
        };

        EasyMock.expect(reportDefinitionDao.search(matcher(queryMatcher))).andReturn(null);

        replayMocks();
        Organization org = null;
        
        List<ReportDefinition> result = command.fetchReportDefinitions(org);
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verifyMocks();

    }


    public static ReportDefinitionQuery matcher(IArgumentMatcher matcher){
        EasyMock.reportMatcher(matcher);
        return null;
    }
    
}
