package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.dao.query.ajax.ParticipantAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.domain.ajax.ParticipantAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySiteAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.repository.ajax.ParticipantAjaxableDomainObjectRepository;
import gov.nih.nci.cabig.caaers.web.WebTestCase;

import java.util.List;

public class SearchStudyAjaxFacadeTest extends WebTestCase {
	 private SearchStudyAjaxFacade searchStudyAjaxFacade;
	 private ParticipantAjaxableDomainObjectRepository participantAjaxableDomainObjectRepository;
	 
    protected void setUp() throws Exception {
    	super.setUp();
        searchStudyAjaxFacade = new SearchStudyAjaxFacade();
        participantAjaxableDomainObjectRepository = registerMockFor(ParticipantAjaxableDomainObjectRepository.class);
        
        ParticipantAjaxableDomainObject participantAjaxableDomainObject = new ParticipantAjaxableDomainObject();
        participantAjaxableDomainObject.setFirstName("David");
        participantAjaxableDomainObject.setLastName("Miller");
        
        StudySiteAjaxableDomainObject studySiteAjaxableDomainObject = new StudySiteAjaxableDomainObject();
        studySiteAjaxableDomainObject.setNciInstituteCode("NCI");
        
      //  participantAjaxableDomainObject.addStudySite(studySiteAjaxableDomainObject);
        
        //searchStudyAjaxFacade.setParticipantAjaxableDomainObjectRepository(participantAjaxableDomainObjectRepository);
    }

    public void testConstructExecuteParticipantQuery() throws Exception {
    	ParticipantAjaxableDomainObjectQuery query = new ParticipantAjaxableDomainObjectQuery();
    	System.out.println(query.getQueryString());
    	List<ParticipantAjaxableDomainObject> participants = participantAjaxableDomainObjectRepository.findParticipants(query);
    	
    	for (ParticipantAjaxableDomainObject participant:participants) {
    		System.out.println(participant.getFirstName());    	}
    }
}
