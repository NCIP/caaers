package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.domain.ajax.ParticipantAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySearchableAjaxableDomainObject;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.AbstractCell;
import org.extremecomponents.table.core.TableModel;

public class ParticipantStudyLinkDisplayCell extends AbstractCell {

    // private static final String LINK = "edit?studyId=";
    // private String LINK = model.getContext().getContextPath() "view?participantId=";

    @Override
    protected String getCellValue(TableModel model, Column column) {
    	ParticipantAjaxableDomainObject participant = (ParticipantAjaxableDomainObject) model.getCurrentRowBean();

        String cellValue = column.getValueAsString();
        String link = model.getContext().getContextPath() + "/pages/study/edit?studyId=";        

        for (StudySearchableAjaxableDomainObject study : participant.getStudies()) {
            if (study.getPrimaryIdentifierValue() != null)  {
            	String identifier = study.getPrimaryIdentifierValue();
            	cellValue = cellValue+ "<a href=\"" + link + study.getId().toString() + "\">" + identifier + "</a><br>";
            }

        }

        return cellValue;
    }
}
