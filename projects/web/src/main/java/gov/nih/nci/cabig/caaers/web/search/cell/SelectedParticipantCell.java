package gov.nih.nci.cabig.caaers.web.search.cell;

import gov.nih.nci.cabig.caaers.domain.ajax.ParticipantAjaxableDomainObject;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.Cell;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.view.html.ColumnBuilder;

/**
 * @author Biju Joseph
 * @crated Oct 2, 2008
 */
public class SelectedParticipantCell implements Cell {

    public String getExportDisplay(TableModel model, Column column) {
        return column.getValueAsString();
    }

    public String getHtmlDisplay(TableModel model, Column column) {
        ColumnBuilder inputBuilder = new ColumnBuilder(column);
        inputBuilder.tdStart();

        try {
        	ParticipantAjaxableDomainObject bean = (ParticipantAjaxableDomainObject) model.getCurrentRowBean();
            Integer id = bean.getId();
            inputBuilder.getHtmlBuilder().input("radio")
                    .name("participant" + id.intValue())
                    .id("participant" + id.intValue())
                    .value(id.toString())
                    .onclick("selectParticipant(this.value)")
                    ;
            inputBuilder.getHtmlBuilder().xclose();
            inputBuilder.tdBody(bean.getPrimaryIdentifierValue());

        } catch (Exception e) {
        }
        inputBuilder.tdEnd();

        return inputBuilder.toString().trim();
    }

}


