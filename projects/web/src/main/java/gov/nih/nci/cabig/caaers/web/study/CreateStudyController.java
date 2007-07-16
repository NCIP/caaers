package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

/**
 * Study Controller for 'Create' Workflow
 * @author Priyatam
 */
public class CreateStudyController extends StudyController <Study>{
     
	/**
	 * Layout Tabs
	 * @param request - flow the Flow object
	 */
	
	@Override
	protected void layoutTabs(Flow<Study> flow) {
        flow.addTab(new DetailsTab());
        flow.addTab(new IdentifiersTab());
        flow.addTab(new SitesTab());
        flow.addTab(new InvestigatorsTab());
        flow.addTab(new PersonnelTab());
        flow.addTab(new AgentsTab());
        flow.addTab(new DiseaseTab());
        flow.addTab(new EmptyStudyTab("Overview", "Overview", "study/study_reviewsummary"));
	}
   
    
    /**
     * Creates an Study(empty study), with a empty StudySite and Identifier.
     */
    @Override
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
    	Study study = new Study();
    	
    	StudySite studySite = new StudySite();
    	study.addStudySite(studySite);
    	List<Identifier> studyIdentifiers = new ArrayList<Identifier>();
    	Identifier studyIdentifier = new Identifier();
    	studyIdentifiers.add(studyIdentifier);
    	study.setIdentifiers(studyIdentifiers);
    	
    	return study;
    }

    @Override
    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response,
            Object command, BindException errors) throws Exception {
        Study study = (Study) command;
        //save the study by calling merge, as the study might be assocated 
        //to different copy of same object (eg: Organization, with same id)
        //in different screens (hibernate session)
        studyDao.merge(study);

        return new ModelAndView("forward:view?type=confirm", errors.getModel());
    }

    
}