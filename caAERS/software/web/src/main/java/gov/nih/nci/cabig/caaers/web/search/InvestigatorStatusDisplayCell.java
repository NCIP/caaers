package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.domain.Investigator;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.AbstractCell;
import org.extremecomponents.table.core.TableModel;

public class InvestigatorStatusDisplayCell extends AbstractCell {

    @Override
    protected String getCellValue(final TableModel model, final Column column) {

        Investigator investigator = (Investigator) model.getCurrentRowBean();
        String cellValue = column.getValueAsString();

        if (investigator.getActive()) return "Active"; else return "Inactive";
    }
}