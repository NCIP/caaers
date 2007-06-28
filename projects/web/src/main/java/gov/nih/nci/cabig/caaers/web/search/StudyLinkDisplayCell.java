package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.AbstractCell;
import org.extremecomponents.table.core.TableModel;

public class StudyLinkDisplayCell extends AbstractCell {

    private static final String LINK = "edit?studyId=";

    @Override
    protected String getCellValue(TableModel model, Column column) {
        AdverseEvent ae = (AdverseEvent) model.getCurrentRowBean();
        Study study = ae.getReport().getAssignment().getStudySite().getStudy();
        String cellValue = study.getPrimaryIdentifier().getValue();

        if (study != null) {
            cellValue = "<a href=\"" + LINK + study.getId().toString() + "\">"
                + cellValue + "</a>";
        }

        return cellValue;
    }
}
