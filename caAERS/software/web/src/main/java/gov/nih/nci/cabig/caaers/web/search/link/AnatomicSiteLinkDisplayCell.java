package gov.nih.nci.cabig.caaers.web.search.link;

import gov.nih.nci.cabig.caaers.domain.AnatomicSite;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.AbstractCell;
import org.extremecomponents.table.core.TableModel;

/**
 * @author Biju Joseph
 */
public class AnatomicSiteLinkDisplayCell extends AbstractCell {

    @Override
    protected String getCellValue(final TableModel model, final Column column) {

        AnatomicSite anatomicSite = (AnatomicSite) model.getCurrentRowBean();
        String cellValue = column.getValueAsString();
        String tableId = model.getTableHandler().getTable().getTableId();

        if (anatomicSite != null) {
            cellValue = "<a  href=\"javascript:fillDiseaseSiteAutoCompletor('" + anatomicSite.getId() + "', '" + tableId + "','" +  anatomicSite.getName() + "')\">"
                            + anatomicSite.getName() + 
                        "</a>";
        }
        return cellValue;
    }

}
