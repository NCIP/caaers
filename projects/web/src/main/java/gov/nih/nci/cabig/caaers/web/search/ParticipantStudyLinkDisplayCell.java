package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.AbstractCell;
import org.extremecomponents.table.core.TableModel;

public class ParticipantStudyLinkDisplayCell extends AbstractCell {

    //private static final String LINK = "edit?studyId=";
    //private  String LINK = model.getContext().getContextPath() "view?participantId=";

    @Override
    protected String getCellValue(TableModel model, Column column) {
    	System.out.println("jj " + model.getCurrentRowBean().getClass().getName());
        Participant participant = (Participant) model.getCurrentRowBean();
        
        String cellValue = column.getValueAsString();
        String link = model.getContext().getContextPath() + "/pages/study/view?studyId=";
        
        for (StudyParticipantAssignment assignment : participant.getAssignments()){
        	Study study = assignment.getStudySite().getStudy();
        	cellValue=  cellValue + " <a href=\"" + link + study.getId().toString() + "&type=confirm\">" 
        	+ study.getPrimaryIdentifier().getValue() + "</a><br>";
        	
        }

        return cellValue;
    }
}
