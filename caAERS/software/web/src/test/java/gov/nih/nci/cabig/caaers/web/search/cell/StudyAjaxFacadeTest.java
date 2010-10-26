package gov.nih.nci.cabig.caaers.web.search.cell;

import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;

import gov.nih.nci.cabig.caaers.dao.query.StudySitesQuery;
import gov.nih.nci.cabig.caaers.dao.query.ajax.StudySearchableAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySearchableAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySiteAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ajax.StudySearchableAjaxableDomainObjectRepository;
import gov.nih.nci.cabig.caaers.web.DwrFacadeTestCase;
import gov.nih.nci.cabig.caaers.web.participant.AssignParticipantController;
import gov.nih.nci.cabig.caaers.web.participant.AssignParticipantStudyCommand;
import gov.nih.nci.cabig.caaers.web.study.SearchStudyAjaxFacade;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author Saurabh Agrawal
 * @crated Oct 2, 2008
 */
public class StudyAjaxFacadeTest extends DwrFacadeTestCase {

    private SearchStudyAjaxFacade searchStudyAjaxFacade;
    private LocalStudy study;
    private StudySite studySite;
    private StudyFundingSponsor ctepStudySponsor;
    private StudySite nciStudySite, ctepStudySite;
    private StudySearchableAjaxableDomainObjectRepository studySearchableAjaxableDomainObjectRepository;
    private StudyRepository studyRepository;


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        searchStudyAjaxFacade = new SearchStudyAjaxFacade();
        studySearchableAjaxableDomainObjectRepository = registerMockFor(StudySearchableAjaxableDomainObjectRepository.class);
        studyRepository = registerMockFor(StudyRepository.class);
        study = new LocalStudy();
        study.setShortTitle("short title");
        study.setId(1);
        studySite = new StudySite();
        studySite.setStudy(study);

        nciStudySite = new StudySite();
        nciStudySite.setId(1);
        nciStudySite.setOrganization(new LocalOrganization()); nciStudySite.getOrganization().setName("National Cancer Inst");

        ctepStudySite = new StudySite();
        ctepStudySite.setId(2);
        ctepStudySite.setOrganization(new LocalOrganization()); nciStudySite.getOrganization().setName("Ctep Org");

        ctepStudySponsor = new StudyFundingSponsor();
        ctepStudySponsor.setId(3);
        ctepStudySponsor.setPrimary(true);
        ctepStudySponsor.setOrganization(new LocalOrganization()); ctepStudySponsor.getOrganization().setName("Ctep Org");

        study.addStudyFundingSponsor(ctepStudySponsor);
        study.addStudySite(nciStudySite);
        study.addStudySite(ctepStudySite);
        searchStudyAjaxFacade.setStudySearchableAjaxableDomainObjectRepository(studySearchableAjaxableDomainObjectRepository);
        searchStudyAjaxFacade.setStudyRepository(studyRepository);
    }

    public void testGetTable() {
    	AssignParticipantStudyCommand command = new AssignParticipantStudyCommand();
    	session.setAttribute( AssignParticipantController.class.getName() + ".FORM.command", command);
    	expect(webContext.getSession()).andReturn(session).anyTimes();
        // expect(studySearchableAjaxableDomainObjectRepository.findStudies(isA(StudySearchableAjaxableDomainObjectQuery.class), eq(""), eq(""))).andReturn(Arrays.asList(study));
        expect(studyRepository.search(isA(StudySitesQuery.class), eq(""), eq(""), eq(false))).andReturn(Arrays.asList(studySite));
        replayMocks();
        List table = searchStudyAjaxFacade.getTableForAssignParticipant(new HashMap(), "", "", request);
        verifyMocks();
    }
}
