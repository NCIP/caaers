/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.search.link;

import gov.nih.nci.cabig.caaers.domain.LabTerm;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.AbstractCell;
import org.extremecomponents.table.core.TableModel;

/**
 * @author Biju Joseph
 */
public class LabTermLinkDisplayCell extends AbstractCell {

    @Override
    protected String getCellValue(final TableModel model, final Column column) {

        LabTerm labTerm = (LabTerm) model.getCurrentRowBean();
        String cellValue = column.getValueAsString();
        String tableId = model.getTableHandler().getTable().getTableId();

        if (labTerm != null) {
            cellValue = "<a  href=\"javascript:fillLabsAutoCompletor('" + labTerm.getId() + "','"
                            + tableId + "')\">" + labTerm.getTerm() + "</a>";
        }
        return cellValue;
    }

}
