package gov.nih.nci.cabig.caaers.web.security;

import gov.nih.nci.cabig.caaers.dao.index.*;
import gov.nih.nci.cabig.caaers.domain.repository.*;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import junit.framework.TestCase;
import org.easymock.EasyMock;

import javax.servlet.FilterChain;
import java.util.Collection;
import java.util.Map;

/**
 * @author: Biju Joseph
 */
public class FabricatedAuthenticationFilterTest extends WebTestCase {
    FabricatedAuthenticationFilter filter;
    CaaersSecurityFacade securityFacade;
    ResearchStaffRepository researchStaffRepository;
    InvestigatorRepository investigatorRepository;
    OrganizationRepository organizationRepository;
    StudyRepository studyRepository;
    OrganizationIndexDao organizationIndexDao;
    StudyIndexDao studyIndexDao;
    ExpeditedAdverseEventReportIndexDao expeditedAdverseEventReportIndexDao;
    ResearchStaffIndexDao researchStaffIndexDao;
    InvestigatorIndexDao investigatorIndexDao;
    ParticipantIndexDao participantIndexDao;

    public void setUp() throws Exception {
        super.setUp();
        filter = new FabricatedAuthenticationFilter();
        securityFacade = registerMockFor(CaaersSecurityFacade.class);
        researchStaffRepository = new ResearchStaffRepository();
        investigatorRepository = new InvestigatorRepositoryImpl();
        organizationRepository = new OrganizationRepositoryImpl();
        studyRepository = new StudyRepository();
        organizationIndexDao = new OrganizationIndexDao();
        studyIndexDao = new StudyIndexDao();
        expeditedAdverseEventReportIndexDao = new ExpeditedAdverseEventReportIndexDao();
        researchStaffIndexDao = new ResearchStaffIndexDao();
        investigatorIndexDao = new InvestigatorIndexDao();
        participantIndexDao = new ParticipantIndexDao();
    }

    public void testMutators(){
         filter.setSecurityFacade(securityFacade);
         assertSame(securityFacade, filter.getSecurityFacade());
         filter.setResearchStaffIndexDao(researchStaffIndexDao);
        assertSame(researchStaffIndexDao, filter.getResearchStaffIndexDao());
        filter.setResearchStaffRepository(researchStaffRepository);
        assertSame(researchStaffRepository, filter.getResearchStaffRepository()) ;
        filter.setInvestigatorRepository(investigatorRepository);
        assertSame(investigatorRepository, filter.getInvestigatorRepository());
        filter.setOrganizationRepository(organizationRepository);
        assertSame(organizationRepository, filter.getOrganizationRepository());
        filter.setStudyRepository(studyRepository);
        assertSame(studyRepository, filter.getStudyRepository());
        filter.setOrganizationIndexDao(organizationIndexDao);
        assertSame(organizationIndexDao, filter.getOrganizationIndexDao());
        filter.setStudyIndexDao(studyIndexDao);
        assertSame(studyIndexDao, filter.getStudyIndexDao());
        filter.setExpeditedAdverseEventReportIndexDao(expeditedAdverseEventReportIndexDao);
        assertSame(expeditedAdverseEventReportIndexDao, filter.getExpeditedAdverseEventReportIndexDao());
        filter.setResearchStaffIndexDao(researchStaffIndexDao);
        assertSame(researchStaffIndexDao, filter.getResearchStaffIndexDao());
        filter.setInvestigatorIndexDao(investigatorIndexDao);
        assertSame(investigatorIndexDao, filter.getInvestigatorIndexDao());
        filter.setParticipantIndexDao(participantIndexDao);
        assertSame(participantIndexDao, filter.getParticipantIndexDao());
    }

    public void testDestroy() throws Exception {
       try{
           filter.destroy();
       } catch (Exception fail){
           fail.printStackTrace();
           fail("exception thrown");
       }
    }

    public void testDoFilterAlreadyFiltered() throws Exception {

        FilterChain chain = registerMockFor(FilterChain.class);
        chain.doFilter(request,  response);
        replayMocks();
        request.setAttribute("gov.nih.nci.cabig.caaers.web.security.FabricatedAuthenticationFilter.FILTER_APPLIED", true);

        filter.doFilter(request, response, chain);
        assertContains((Collection) request.getAttribute("cl"), "Caaers Super User") ;
        assertTrue( ( (Map<String, String>)request.getAttribute("roles")).containsKey("caaers_super_user") )  ;
        assertTrue( ( (Map<String, String>)request.getAttribute("originalRoles")).containsKey("caaers_super_user") )  ;
        verifyMocks();
        
    }

    public void testInit() throws Exception {
        try{
            filter.init(null);
        } catch (Exception fail){
            fail.printStackTrace();
            fail("exception thrown");
        }
    }
}
