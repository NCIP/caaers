/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.AbstractCell;
import org.extremecomponents.table.core.TableModel;

public class AeTypeDisplayCell extends AbstractCell {

    @Override
    protected String getCellValue(TableModel model, Column column) {
        AdverseEvent ae = (AdverseEvent) model.getCurrentRowBean();
        String output = ae.getReport() != null ? "SAE" : "AE";

        String cellValue = column.getValueAsString();
        cellValue = output;
        return cellValue;
    }
}