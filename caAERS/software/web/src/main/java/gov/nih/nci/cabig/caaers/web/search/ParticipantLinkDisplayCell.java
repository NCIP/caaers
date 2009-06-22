package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.domain.ajax.ParticipantAjaxableDomainObject;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.AbstractCell;
import org.extremecomponents.table.core.TableModel;

public class ParticipantLinkDisplayCell extends AbstractCell {

    @Override
    protected String getCellValue(final TableModel model, final Column column) {

    	ParticipantAjaxableDomainObject participant = (ParticipantAjaxableDomainObject) model.getCurrentRowBean();

        String cellValue = column.getValueAsString();
        String link = model.getContext().getContextPath()
                        + "/pages/participant/edit?participantId=";

        if (participant != null) {
            cellValue = "<a href=\"" + link + participant.getId().toString() + "\">" + cellValue
                            + "</a>";
        }
        return cellValue;

    }
}
