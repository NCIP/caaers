package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.domain.RoutineAdverseEventReport;
import gov.nih.nci.cabig.caaers.web.ControllerTools;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.AbstractCell;
import org.extremecomponents.table.core.TableModel;

public class ObservationDateDisplayCell extends AbstractCell {

    @Override
    protected String getCellValue(TableModel model, Column column) {

        RoutineAdverseEventReport routineReport = (RoutineAdverseEventReport) model
                        .getCurrentRowBean();
        String startDate = routineReport.getStartDate() != null ? ControllerTools
                        .formatDate(routineReport.getStartDate())
                        + " - " : " - ";
        String endDate = routineReport.getEndDate() != null ? ControllerTools
                        .formatDate(routineReport.getEndDate()) : "";
        String cellValue = column.getValueAsString();
        cellValue = startDate + endDate;
        return cellValue;
    }
}
