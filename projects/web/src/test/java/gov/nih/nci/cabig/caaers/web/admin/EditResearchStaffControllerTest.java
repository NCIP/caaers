package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.LocalResearchStaff;
import gov.nih.nci.cabig.caaers.domain.RemoteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.repository.ResearchStaffRepository;
import gov.nih.nci.cabig.caaers.web.WebTestCase;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class EditResearchStaffControllerTest extends WebTestCase {
	
	private EditResearchStaffController controller;
	
	@Override
    protected void setUp() throws Exception {
        super.setUp();
        controller = new EditResearchStaffController();
        controller.setResearchStaffRepository(new ResearchStaffRepository(){
        	public void save(final ResearchStaff researchStaff, String changeURL) {
        		
        	}
        	
        	public void convertToRemote(ResearchStaff localResearchStaff, ResearchStaff remoteResearchStaff){
        		
        	}
        	
        	public void evict(ResearchStaff researchStaff){
        		
        	}
        	
        	public ResearchStaff getById(final int id) {
                return new LocalResearchStaff();
            }
        	
        	public List<ResearchStaff> getRemoteResearchStaff(final ResearchStaff researchStaff){
        		List<ResearchStaff> rsList = new ArrayList<ResearchStaff>();
        		ResearchStaff r1 = new RemoteResearchStaff();
        		ResearchStaff r2 = new RemoteResearchStaff();
        		rsList.add(r1);
        		rsList.add(r2);
        		return rsList;
        	}
        	
        });
    }
	
	/**
	 * 
	 * @throws Exception
	 */
	public void testProcessFinish() throws Exception {
        request.setMethod("POST");
        request.setParameter("_action", "saveRemoteRs");
        request.setParameter("_selected", "0");
        request.setParameter("researchStaffId", "109");
        ResearchStaff command = new LocalResearchStaff();
        ResearchStaff l1 = new LocalResearchStaff();
        command.getExternalResearchStaff().add(l1);
        BindException errors = new BindException(command, "command");
        ModelAndView mv = controller.processFinish(request, response, command, errors);
        assertEquals("admin/research_staff_review", mv.getViewName());
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	public void testOnBindAndValidateWithResults() throws Exception {
        request.setMethod("POST");
        request.setParameter("_action", "syncResearchStaff");
        ResearchStaff command = new LocalResearchStaff();
        BindException errors = new BindException(command, "command");
        controller.onBindAndValidate(request, command, errors, 1);
        assertNotNull("List should not be Null", command.getExternalResearchStaff());
        assertEquals(2, command.getExternalResearchStaff().size());
        assertEquals(1, errors.getErrorCount());
	}
	

	public void testOnBindAndValidateWithOutResults() throws Exception {
		controller.setResearchStaffRepository(new ResearchStaffRepository(){
        	public List<ResearchStaff> getRemoteResearchStaff(final ResearchStaff researchStaff){
        		List<ResearchStaff> rsList = new ArrayList<ResearchStaff>();
        		return rsList;
        	}
        	
        });
		
		request.setMethod("POST");
        request.setParameter("_action", "syncResearchStaff");
        ResearchStaff command = new LocalResearchStaff();
        BindException errors = new BindException(command, "command");
        controller.onBindAndValidate(request, command, errors, 1);
        assertEquals(0, command.getExternalResearchStaff().size());
	}
}
