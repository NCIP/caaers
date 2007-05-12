package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyInvestigator;
import gov.nih.nci.cabig.caaers.domain.StudySite;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
*/
class InvestigatorsTab extends StudyTab {
    public InvestigatorsTab() {
        super("Study Investigators", "Investigators", "study/study_investigators");
    }

    @Override
    public Map<String, Object> referenceData() {
        Map<String, Object> refdata = super.referenceData();
        addConfigMapToRefdata(refdata, "invRoleCodeRefData");
        addConfigMapToRefdata(refdata, "invStatusCodeRefData");
        return refdata;
    }

    public void postProcess(HttpServletRequest request, Study command, Errors errors) {
    	if("siteChange".equals(request.getParameter("_action")))
		{
			request.getSession().setAttribute("selectedSite", request.getParameter("_selectedSite"));
			
			StudySite studySite = ((Study)command).getStudySites().get(Integer.parseInt(request.getParameter("_selectedSite")));
			if(studySite.getStudyInvestigators().size() == 0 )
			{						
				StudyInvestigator studyInvestigator = new StudyInvestigator();	
				studyInvestigator.setSiteInvestigator(new SiteInvestigator());
				studySite.addStudyInvestigators(studyInvestigator);
			}
		}
		else {
			handleStudyInvestigatorAction((Study)command, request);
		}
    }

    private void handleStudyInvestigatorAction(Study study, HttpServletRequest request)
	{				
		String action =request.getParameter("_action");
		String selectedSite = request.getParameter("_selectedSite"); 
		String selectedInvestigator = request.getParameter("_selectedInvestigator");
		
		if ("addInv".equals(action))
		{				
			StudyInvestigator studyInvestigator = new StudyInvestigator();
			StudySite studySite = study.getStudySites().get(Integer.parseInt(selectedSite));			
			studySite.addStudyInvestigators(studyInvestigator);														
		}
		else if ("removeInv".equals(action))
		{	
			study.getStudySites().get(Integer.parseInt(selectedSite)).getStudyInvestigators()
				.remove(Integer.parseInt(selectedInvestigator));
		}										
	}	
}
