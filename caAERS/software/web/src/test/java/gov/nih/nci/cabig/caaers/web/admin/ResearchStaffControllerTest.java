package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.LocalResearchStaff;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.repository.ResearchStaffRepository;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import org.hibernate.StaleObjectStateException;
import org.springframework.orm.hibernate3.HibernateOptimisticLockingFailureException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;

import static org.easymock.EasyMock.expectLastCall;

/**
 * @author Ion C. Olaru
 */
public class ResearchStaffControllerTest extends WebTestCase {
    ResearchStaffController controller;
    protected ResearchStaffRepository researchStaffRepository;
    protected ResearchStaffCommand command;
    BindException errors;

    public void setUp() throws Exception {
        super.setUp();
        // controller = registerMockFor(ResearchStaffController.class);
        controller = new CreateResearchStaffController();
        command = new ResearchStaffCommand();
        command.setResearchStaff(new LocalResearchStaff());
        command.setSiteResearchStaffCommandHelper(new ArrayList());
        // controller.setC
        researchStaffRepository = registerMockFor(ResearchStaffRepository.class);
        controller.setResearchStaffRepository(researchStaffRepository);
        errors = new BindException(command, "command");
    }
    
    public void testTheOptimisticException() throws Exception {
        HibernateOptimisticLockingFailureException exception = new HibernateOptimisticLockingFailureException(new StaleObjectStateException(null, null));
        errors.reject("");
        controller.processFinish(request, response, command, errors);
        expectLastCall().andThrow(exception);
    }
}
