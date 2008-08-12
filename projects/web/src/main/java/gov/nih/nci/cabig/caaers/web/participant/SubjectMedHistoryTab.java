package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.domain.ConcomitantMedication;
import gov.nih.nci.cabig.caaers.tools.spring.tabbedflow.InPlaceEditableTab;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

public class SubjectMedHistoryTab extends InPlaceEditableTab<AssignParticipantStudyCommand> {
    private static final Log log = LogFactory.getLog(AssignStudyTab.class);

	
	public SubjectMedHistoryTab() {
        super("Subject Medical History", "Subject Medical History", "par/par_subject_med_history");
    }

    @Override
    public Map<String, Object> referenceData(AssignParticipantStudyCommand command) {
        Map<String, Object> refdata = super.referenceData(command);
        return refdata;
    }

/*
    public ModelAndView addMedication(HttpServletRequest request, Object commandObj, Errors error) {
    	AssignParticipantStudyCommand assignParticipantStudyCommand = (AssignParticipantStudyCommand)commandObj;
        List<ConcomitantMedication> listOfConMeds = assignParticipantStudyCommand.getListOfConcomitantMedications();
        listOfConMeds.add( new ConcomitantMedication() );
        return new ModelAndView(getAjaxViewName(request), new java.util.HashMap());
    }
*/

}

