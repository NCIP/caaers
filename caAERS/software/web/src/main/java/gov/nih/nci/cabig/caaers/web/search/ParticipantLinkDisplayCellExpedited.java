package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Participant;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.AbstractCell;
import org.extremecomponents.table.core.TableModel;

public class ParticipantLinkDisplayCellExpedited extends AbstractCell {

    // private static final String LINK = "edit?studyId=";
    // private String LINK = model.getContext().getContextPath() "view?participantId=";

    @Override
    protected String getCellValue(TableModel model, Column column) {
        ExpeditedAdverseEventReport expeditedReport = (ExpeditedAdverseEventReport) model
                        .getCurrentRowBean();
        Participant participant = expeditedReport.getParticipant();
        String cellValue = participant.getPrimaryIdentifier() != null ? participant
                        .getPrimaryIdentifier().getValue() : "n/a";
        String link = model.getContext().getContextPath()
                        + "/pages/participant/view?participantId=";

        if (participant != null) {
            cellValue = "<a href=\"" + link + participant.getId().toString() + "&type=confirm\">"
                            + cellValue + "</a>";
        }

        return cellValue;
    }
}
