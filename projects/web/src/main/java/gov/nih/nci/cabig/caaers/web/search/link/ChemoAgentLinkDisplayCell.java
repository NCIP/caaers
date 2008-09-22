package gov.nih.nci.cabig.caaers.web.search.link;

import org.extremecomponents.table.cell.AbstractCell;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.bean.Column;
import gov.nih.nci.cabig.caaers.domain.LabTerm;
import gov.nih.nci.cabig.caaers.domain.ChemoAgent;

/**
 * @author Biju Joseph
 */
public class ChemoAgentLinkDisplayCell extends AbstractCell {

    @Override
    protected String getCellValue(final TableModel model, final Column column) {

        ChemoAgent chemoAgent = (ChemoAgent) model.getCurrentRowBean();
        String cellValue = column.getValueAsString();
        String tableId = model.getTableHandler().getTable().getTableId();

        if (chemoAgent != null) {
            cellValue = "<a  href=\"javascript:fillChemoAgentAutoCompletor('" + chemoAgent.getId()
                            + "','" + tableId + "','" + chemoAgent.getName() + "')\">" + chemoAgent.getName() + "</a>";
        }
        return cellValue;
    }

}
