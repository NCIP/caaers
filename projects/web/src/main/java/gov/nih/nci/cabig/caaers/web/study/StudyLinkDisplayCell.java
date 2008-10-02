package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.ajax.AjaxableDomainObject;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.AbstractCell;
import org.extremecomponents.table.core.TableModel;

public class StudyLinkDisplayCell extends AbstractCell {

    @Override
    protected String getCellValue(TableModel model, Column column) {
        String id = null;
        if (model.getCurrentRowBean() != null && model.getCurrentRowBean() instanceof Study) {
            Study study = (Study) model.getCurrentRowBean();
            id = study.getId().toString();
        }
        if (model.getCurrentRowBean() != null && model.getCurrentRowBean() instanceof AjaxableDomainObject) {
            AjaxableDomainObject ajaxableDomainObject = (AjaxableDomainObject) model.getCurrentRowBean();
            id = ajaxableDomainObject.getId().toString();
        }

        String cellValue = column.getValueAsString();
        String link = model.getContext().getContextPath() + "/pages/study/edit?studyId=";

        if (id != null) {
            cellValue = "<a href=\"" + link + id.toString() + "\">" + cellValue + "</a>";
        }

        return cellValue;
    }
}
