package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Study;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.AbstractCell;
import org.extremecomponents.table.core.TableModel;

public class StudyLinkDisplayCellExpedited extends AbstractCell {

    @Override
    protected String getCellValue(TableModel model, Column column) {
        ExpeditedAdverseEventReport expeditedAe = (ExpeditedAdverseEventReport) model
                        .getCurrentRowBean();
        Study study = expeditedAe.getStudy();
        String cellValue = study.getPrimaryIdentifier().getValue();
        String link = model.getContext().getContextPath() + "/pages/study/edit?studyId=";

        if (study != null) {
            cellValue = "<a href=\"" + link + study.getId().toString() + "\">" + cellValue + "</a>";
        }

        return cellValue;
    }
}
