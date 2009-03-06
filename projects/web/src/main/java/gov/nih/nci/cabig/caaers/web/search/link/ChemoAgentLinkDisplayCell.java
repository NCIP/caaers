package gov.nih.nci.cabig.caaers.web.search.link;

import gov.nih.nci.cabig.caaers.domain.ChemoAgent;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.AbstractCell;
import org.extremecomponents.table.core.TableModel;

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
