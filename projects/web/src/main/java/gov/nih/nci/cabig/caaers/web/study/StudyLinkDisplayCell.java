package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.Study;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.AbstractCell;
import org.extremecomponents.table.core.TableModel;

public class StudyLinkDisplayCell extends AbstractCell {

    @Override
    protected String getCellValue(TableModel model, Column column) {
    	System.out.println("jj " + model.getCurrentRowBean().getClass().getName());
        Study study = (Study) model.getCurrentRowBean();
        String cellValue = column.getValueAsString();
        String link = model.getContext().getContextPath() + "/pages/study/edit?studyId=";

        if (study != null) {
            cellValue = "<a href=\"" + link + study.getId().toString() + "\">"
                + cellValue + "</a>";
        }

        return cellValue;
    }
}
