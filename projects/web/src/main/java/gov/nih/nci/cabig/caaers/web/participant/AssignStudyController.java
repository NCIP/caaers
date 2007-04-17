package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.ctms.web.tabs.Flow;

import java.util.Map;
/**
 * @author Krikor Krumlian
 */
public class AssignStudyController extends AssignParticipantStudyController{
	
	public AssignStudyController() {
        setCommandClass(AssignParticipantStudyCommand.class);
        setFlow(new Flow<AssignParticipantStudyCommand>("Assign Participant to Study"));
        getFlow().addTab(new Tab("Search for Studies", "Search Study", "par/reg_protocol_search") {
            public Map<String, Object> referenceData() {
                Map<String, Object> refdata = super.referenceData();
                refdata.put("studySearchType", listValues.getStudySearchType());
                refdata.put("assignType", "study");
                return refdata;
            }
        });
        getFlow().addTab(new Tab("Choose Study", "Search Participant", "par/reg_participant_search") {
            public Map<String, Object> referenceData() {
                Map<String, Object> refdata = super.referenceData();
                refdata.put("participantSearchType", listValues.getParticipantSearchType());
                refdata.put("assignType", "study");
                return refdata;
            }
        });
        getFlow().addTab(new Tab("Review and Submit", "Review and Submit", "par/reg_review_submit") {
            public Map<String, Object> referenceData() {
                Map<String, Object> refdata = super.referenceData();
                return refdata;
            }
        });
    }

}
