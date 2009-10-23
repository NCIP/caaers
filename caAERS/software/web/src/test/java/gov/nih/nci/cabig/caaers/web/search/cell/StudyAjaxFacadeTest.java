package gov.nih.nci.cabig.caaers.web.search.cell;

import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import gov.nih.nci.cabig.caaers.dao.query.ajax.StudySearchableAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySearchableAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySiteAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.repository.ajax.StudySearchableAjaxableDomainObjectRepository;
import gov.nih.nci.cabig.caaers.web.DwrFacadeTestCase;
import gov.nih.nci.cabig.caaers.web.participant.AssignParticipantController;
import gov.nih.nci.cabig.caaers.web.participant.AssignParticipantStudyCommand;
import gov.nih.nci.cabig.caaers.web.study.SearchStudyAjaxFacade;

import java.util.Arrays;

/**
 * @author Saurabh Agrawal
 * @crated Oct 2, 2008
 */
public class StudyAjaxFacadeTest extends DwrFacadeTestCase {

    private SearchStudyAjaxFacade searchStudyAjaxFacade;
    private StudySearchableAjaxableDomainObject study;
    private StudySiteAjaxableDomainObject nciStudySite, ctepStudySite;
    private StudySearchableAjaxableDomainObjectRepository studySearchableAjaxableDomainObjectRepository;


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        searchStudyAjaxFacade = new SearchStudyAjaxFacade();
        studySearchableAjaxableDomainObjectRepository = registerMockFor(StudySearchableAjaxableDomainObjectRepository.class);
        study = new StudySearchableAjaxableDomainObject();
        study.setShortTitle("short title");
        study.setId(1);

        nciStudySite = new StudySiteAjaxableDomainObject();
        nciStudySite.setId(1);
        nciStudySite.setName("National Cancer Inst");
        ctepStudySite = new StudySiteAjaxableDomainObject();
        ctepStudySite.setId(2);
        ctepStudySite.setName("Ctep Org");

        study.addStudySite(nciStudySite);
        study.addStudySite(ctepStudySite);
        searchStudyAjaxFacade.setStudySearchableAjaxableDomainObjectRepository(studySearchableAjaxableDomainObjectRepository);


    }

    public void testGetTable() {
    	AssignParticipantStudyCommand command = new AssignParticipantStudyCommand();
    	session.setAttribute( AssignParticipantController.class.getName() + ".FORM.command", command);
    	expect(webContext.getSession()).andReturn(session).anyTimes();
        expect(studySearchableAjaxableDomainObjectRepository.findStudies(isA(StudySearchableAjaxableDomainObjectQuery.class), eq(""), eq(""))).andReturn(Arrays.asList(study));
        replayMocks();
        String table = searchStudyAjaxFacade.getTableForAssignParticipant(null, "", "", request);
        System.out.println(table);
        verifyMocks();
       // assertEquals("", table);
    }
}
