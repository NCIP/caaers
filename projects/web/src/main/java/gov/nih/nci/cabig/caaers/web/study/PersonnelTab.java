package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyPersonnel;
import gov.nih.nci.cabig.caaers.domain.StudySite;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
*/
class PersonnelTab extends StudyTab {
    public PersonnelTab() {
        super("Study Personnel", "Personnel", "study/study_personnel");
    }

    @Override
    public Map<String, Object> referenceData() {
        Map<String, Object> refdata = super.referenceData();
        addConfigMapToRefdata(refdata, "studyPersonnelRoleRefData");
        addConfigMapToRefdata(refdata, "studyPersonnelStatusRefData");
        return refdata;
    }

    public void postProcess(HttpServletRequest request, Study command, Errors errors) {
    	if("siteChange".equals(request.getParameter("_action")))
		{
			request.getSession().setAttribute("selectedSite", request.getParameter("_selectedSite"));
			
			StudySite studySite = ((Study)command).getStudySites().get(Integer.parseInt(request.getParameter("_selectedSite")));
			if(studySite.getStudyPersonnels().size() == 0 )
			{						
				StudyPersonnel studyPersonnel = new StudyPersonnel();
				studyPersonnel.setStudySite(studySite);								
				studySite.addStudyPersonnel(studyPersonnel);
			}										
		}
		else {
			handleStudyPersonnelAction((Study)command, request);
		}		
    }

    private void handleStudyPersonnelAction(Study study, HttpServletRequest request)
	{			
		String action =request.getParameter("_action");
		String selectedSite = request.getParameter("_selectedSite"); 
		String selectedPersonnel = request.getParameter("_selectedPersonnel");
		
		if ("addStudyPersonnel".equals(action))
		{	
			StudyPersonnel studyPersonnel = new StudyPersonnel();
			studyPersonnel.setResearchStaff(new ResearchStaff());
			StudySite studySite = study.getStudySites().get(Integer.parseInt(selectedSite));
			studyPersonnel.setStudySite(studySite);		
			studySite.addStudyPersonnel(studyPersonnel);														
		}
		else if ("removeStudyPersonnel".equals(action))
		{	
			study.getStudySites().get(Integer.parseInt(selectedSite)).getStudyPersonnels()
				.remove(Integer.parseInt(selectedPersonnel));
		}										
	}	
    
 }
