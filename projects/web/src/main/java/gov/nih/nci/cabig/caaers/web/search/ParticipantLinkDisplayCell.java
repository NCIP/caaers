package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.domain.Participant;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.AbstractCell;
import org.extremecomponents.table.core.TableModel;

public class ParticipantLinkDisplayCell extends AbstractCell {

    //private static final String LINK = "edit?studyId=";
    //private  String LINK = model.getContext().getContextPath() "view?participantId=";

    @Override
    protected String getCellValue(TableModel model, Column column) {
        Participant participant = (Participant) model.getCurrentRowBean();
        String cellValue = column.getValueAsString();
        String link = model.getContext().getContextPath() + "/pages/participant/view?participantId=";
        

        if (participant != null) {
            cellValue = "<a href=\"" + link + participant.getId().toString() + "&type=confirm\">"
                + cellValue + "</a>";
        }

        return cellValue;
    }
}
