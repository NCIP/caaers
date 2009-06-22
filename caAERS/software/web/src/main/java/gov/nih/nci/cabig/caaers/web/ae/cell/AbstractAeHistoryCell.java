package gov.nih.nci.cabig.caaers.web.ae.cell;

import gov.nih.nci.cabig.ctms.audit.domain.DataAuditEvent;
import gov.nih.nci.cabig.ctms.audit.domain.DataAuditEventValue;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.AbstractCell;
import org.extremecomponents.table.core.TableModel;

/**
 * @author Biju Joseph
 */
public abstract class AbstractAeHistoryCell extends AbstractCell {


    @Override
    protected String getCellValue(TableModel model, Column column) {


        DataAuditEvent dataAuditEvent = (DataAuditEvent) model.getCurrentRowBean();

        String cellValue = "";

        for (DataAuditEventValue dataAuditEventValue : dataAuditEvent.getValues()) {
            if (dataAuditEventValue.getAttributeName().equals(getAttributeName())) {
                cellValue = dataAuditEventValue.getCurrentValue();
            }
        }

        return cellValue;
    }

    public abstract String getAttributeName();
}
