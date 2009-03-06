package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.domain.CtcTerm;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.AbstractCell;
import org.extremecomponents.table.core.TableModel;

/**
 * @author Biju Joseph
 */
public class CtcTermLinkDisplayCell extends AbstractCell {

    @Override
    protected String getCellValue(final TableModel model, final Column column) {

        CtcTerm ctcTerm = (CtcTerm) model.getCurrentRowBean();
        String cellValue = column.getValueAsString();
        String tableId = model.getTableHandler().getTable().getTableId();

        if (ctcTerm != null) {
            cellValue = "<a  href=\"javascript:fillCtcTerm('" + ctcTerm.getId() + "','" + tableId
                            + "')\">" + ctcTerm.getFullName() + "</a>";
        }
        return cellValue;
    }

}
