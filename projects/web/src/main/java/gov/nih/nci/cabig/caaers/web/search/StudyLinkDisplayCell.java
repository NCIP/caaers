package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Study;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.AbstractCell;
import org.extremecomponents.table.core.TableModel;

public class StudyLinkDisplayCell extends AbstractCell {

    @Override
    protected String getCellValue(TableModel model, Column column) {
        AdverseEvent ae = (AdverseEvent) model.getCurrentRowBean();
        Study study = null;
        if (ae.getReport() != null) {
            study = ae.getReport().getAssignment().getStudySite().getStudy();
        }
        if (ae.getRoutineReport() != null) {
            study = ae.getRoutineReport().getAssignment().getStudySite().getStudy();
        }
        String cellValue = study.getPrimaryIdentifier().getValue();
        String link = model.getContext().getContextPath() + "/pages/study/edit?studyId=";

        if (study != null) {
            cellValue = "<a href=\"" + link + study.getId().toString() + "\">" + cellValue + "</a>";
        }

        return cellValue;
    }
}
