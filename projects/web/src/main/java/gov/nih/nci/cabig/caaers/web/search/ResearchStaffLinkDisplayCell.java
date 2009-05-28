package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.domain.ResearchStaff;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.AbstractCell;
import org.extremecomponents.table.core.TableModel;

public class ResearchStaffLinkDisplayCell extends AbstractCell {
	
	public static final String VIEW_RESEARCHSTAFF_URL= "editResearchStaff?researchStaffId=";
	
    @Override
    protected String getCellValue(final TableModel model, final Column column) {

        ResearchStaff researchStaff = (ResearchStaff) model.getCurrentRowBean();
        String cellValue = column.getValueAsString();
        
        String image = "";
        String imagePath = model.getContext().getContextPath() + "/images/chrome/nci_icon_22.png";
        if (researchStaff.getExternalId() != null) {
        	image = "<img src=\"" +imagePath+"\" alt=\"NCI data\" width=\"17\" height=\"16\" border=\"0\" align=\"middle\">&nbsp;";
        }

        if (researchStaff != null) {
            cellValue = image +  cellValue;
        }
        String url = "document.location='" + VIEW_RESEARCHSTAFF_URL + researchStaff.getId().toString() + "'";
        model.getRowHandler().getRow().setOnclick(url);
        model.getRowHandler().getRow().setStyle("cursor:pointer");
        return cellValue;
    }
}
