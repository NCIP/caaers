/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.CaaersContextLoader;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudySiteDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacadeImpl;
import gov.nih.nci.cabig.caaers.security.SecurityTestUtils;
import gov.nih.nci.cabig.caaers.web.utils.ConfigPropertyHelper;
import org.easymock.EasyMock;
import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * @author Biju Joseph
 * @author Ion C. Olaru
 */
public class EditParticipantTabTest extends AbstractTabTestCase<EditParticipantTab, ParticipantInputCommand> {

    private EditParticipantTab tab;
    private ParticipantInputCommand command;
    private StudySiteDao studySiteDao;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        ConfigPropertyHelper.putParticipantIdentifiersType(configProperty);
        command.setStudy(new LocalStudy());
        studySiteDao = registerMockFor(StudySiteDao.class);
        tab = createTab();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }


    @Override
    protected EditParticipantTab createTab() {
        tab = new EditParticipantTab();
        tab.setListValues(listValues);
        tab.setConfigurationProperty(configProperty);
        tab.setStudySiteDao(studySiteDao);
        return tab;
    }

    @Override
    protected ParticipantInputCommand createCommand() {
        command = new ParticipantInputCommand();
        command.setParticipant(new Participant());
        command.setOrganization(new LocalOrganization());
        command.getOrganization().setId(-1);
        command.setAssignment(new StudyParticipantAssignment());
        return command;
    }

    public void testParticipantFields() throws Exception {
        assertFieldProperties("participant",
                "participant.firstName",
                "participant.lastName",
                "participant.maidenName",
                "participant.middleName",
                "participant.dateOfBirth",
                "participant.gender",
                "participant.ethnicity",
                "participant.race"
        );
    }

    @Override
    protected Map<String, Object> createReferenceData() {
        Map<String, Object> referenceData = getTab().referenceData(getCommand());
        return referenceData;
    }

    public void testPostProcess() {
        StudySite ss = new StudySite() ;
        ss.setStudy(command.getStudy());
        command.getStudy().setDiseaseTerminology(Fixtures.createDiseaseTerminology(command.getStudy()));
        ss.setId(-1000);
        command.getAssignment().setStudySite(ss);
        EasyMock.expect(studySiteDao.getById(-1000)).andReturn(ss).times(1);
        replayMocks();
        tab.postProcess(request, command, errors);
        verifyMocks();
        assertEquals(0, errors.getErrorCount());
    }


}
