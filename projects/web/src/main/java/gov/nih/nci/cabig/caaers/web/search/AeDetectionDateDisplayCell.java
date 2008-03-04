package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.web.ControllerTools;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.AbstractCell;
import org.extremecomponents.table.core.TableModel;

public class AeDetectionDateDisplayCell extends AbstractCell {

    @Override
    protected String getCellValue(TableModel model, Column column) {

        AdverseEvent ae = (AdverseEvent) model.getCurrentRowBean();
        String date = ae.getReport() != null ? ControllerTools.formatDate(ae.getReport()
                        .getAdverseEvents().get(0).getStartDate())
                        : ae.getRoutineReport() != null ? ControllerTools.formatDate(ae
                                        .getRoutineReport().getStartDate())
                                        + " - "
                                        + ControllerTools.formatDate(ae.getRoutineReport()
                                                        .getEndDate()) : "N/A";

        String cellValue = column.getValueAsString();
        cellValue = date;
        return cellValue;
    }
}
