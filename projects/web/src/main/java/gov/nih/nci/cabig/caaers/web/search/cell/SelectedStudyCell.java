package gov.nih.nci.cabig.caaers.web.search.cell;

import gov.nih.nci.cabig.caaers.domain.ajax.StudySearchableAjaxableDomainObject;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.Cell;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.view.html.ColumnBuilder;


/**
 * @author Biju Joseph
 * @crated Oct 2, 2008
 */
public class SelectedStudyCell implements Cell {

    public String getExportDisplay(TableModel model, Column column) {
        return column.getValueAsString();
    }

    public String getHtmlDisplay(TableModel model, Column column) {
        ColumnBuilder inputBuilder = new ColumnBuilder(column);
        inputBuilder.tdStart();

        try {
            StudySearchableAjaxableDomainObject bean = (StudySearchableAjaxableDomainObject) model.getCurrentRowBean();
            Integer id = bean.getId();
            inputBuilder.getHtmlBuilder().input("radio")
                    .name("study" + id.intValue())
                    .id("study" + id.intValue())
                    .value(id.toString())
                    .onclick("selectStudy(this.value)")
                    ;
            inputBuilder.getHtmlBuilder().xclose();
            inputBuilder.tdBody(bean.getPrimaryIdentifierValue());

        } catch (Exception e) {
        }
        inputBuilder.tdEnd();

        return inputBuilder.toString().trim();
    }

}