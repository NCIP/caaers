package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.ajax.AjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySearchableAjaxableDomainObject;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.AbstractCell;
import org.extremecomponents.table.core.TableModel;

public class StudyLinkDisplayCell extends AbstractCell {

    @Override
    protected String getCellValue(TableModel model, Column column) {
        String id = null;
        String externalId = null;
        
        if (model.getCurrentRowBean() != null && model.getCurrentRowBean() instanceof Study) {
            Study study = (Study) model.getCurrentRowBean();
            id = study.getId().toString();
            externalId = study.getExternalId();
        }
        if (model.getCurrentRowBean() != null && model.getCurrentRowBean() instanceof StudySearchableAjaxableDomainObject) {
        	StudySearchableAjaxableDomainObject studySearchableAjaxableDomainObject = (StudySearchableAjaxableDomainObject) model.getCurrentRowBean();
            id = studySearchableAjaxableDomainObject.getId().toString();
            externalId = studySearchableAjaxableDomainObject.getExternalId();
        }

        String cellValue = column.getValueAsString();
        String link = model.getContext().getContextPath() + "/pages/study/edit?studyId=";
        
        String image = "";
        String imagePath = model.getContext().getContextPath() + "/images/chrome/nci_icon_22.png";
        if (externalId != null) {
        	image = "<img src=\"" +imagePath+"\" alt=\"NCI data\" width=\"17\" height=\"16\" border=\"0\" align=\"middle\">&nbsp;";
        }
        if (id != null) {
            cellValue = image +  "<a href=\"" + link + id.toString() + "\">" + cellValue + "</a>";
        }
        return cellValue;
    }
}
