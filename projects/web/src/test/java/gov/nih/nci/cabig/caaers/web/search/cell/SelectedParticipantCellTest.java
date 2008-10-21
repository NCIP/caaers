package gov.nih.nci.cabig.caaers.web.search.cell;

import org.extremecomponents.table.bean.Row;
import org.extremecomponents.table.bean.Column;
import gov.nih.nci.cabig.caaers.web.participant.AssignParticipantStudyCommand;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier;

import java.util.Arrays;

/**
 * @author Saurabh Agrawal
 * @crated Oct 2, 2008
 */
public class SelectedParticipantCellTest extends AbstractCellTestCase {

    private SelectedParticipantCell selectedParticipantCell;
    private Participant participant;
    private SystemAssignedIdentifier newIdentifier;

    @Override
    protected void setUp() throws Exception {

        super.setUp();
        selectedParticipantCell = new SelectedParticipantCell();
        participant = new Participant();
        participant.setId(1);
        newIdentifier = new SystemAssignedIdentifier();
        newIdentifier.setPrimaryIndicator(Boolean.TRUE);
        newIdentifier.setValue("value");
        participant.addIdentifier(newIdentifier);


    }

    public void testDisplay() throws Exception {
        assembleTable(Arrays.asList(participant));

        String cellValue = selectedParticipantCell.getHtmlDisplay(model, column);
//        assertEquals("<td><input type=\"radio\"  name=\"participant\"   id=\"participant1\"  value=\"1\"  onclick=\"selectParticipant(this.value)\" />value</td>", cellValue);

    }
}
