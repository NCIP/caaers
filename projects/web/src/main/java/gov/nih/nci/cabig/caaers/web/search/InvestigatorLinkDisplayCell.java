package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.domain.Investigator;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.AbstractCell;
import org.extremecomponents.table.core.TableModel;

public class InvestigatorLinkDisplayCell extends AbstractCell {

    @Override
    protected String getCellValue(final TableModel model, final Column column) {

        Investigator investigator = (Investigator) model.getCurrentRowBean();
        String cellValue = column.getValueAsString();
        String link = model.getContext().getContextPath()
                        + "/pages/admin/editInvestigator?investigatorId=";

        if (investigator != null) {
            cellValue = "<a href=\"" + link + investigator.getId().toString() + "\">" + cellValue
                            + "</a>";
        }
        return cellValue;
    }
}
