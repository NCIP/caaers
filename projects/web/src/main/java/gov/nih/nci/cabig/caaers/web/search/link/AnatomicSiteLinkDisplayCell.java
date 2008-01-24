package gov.nih.nci.cabig.caaers.web.search.link;

import org.extremecomponents.table.cell.AbstractCell;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.bean.Column;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.AnatomicSite;

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
            cellValue = "<a  href=\"javascript:fillDiseaseSiteAutoCompletor('" + anatomicSite.getId() + "','" + tableId + "')\">" + anatomicSite.getName() + "</a>";
        }
        return cellValue;
    }


}

