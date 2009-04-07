package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.domain.ResearchStaff;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.AbstractCell;
import org.extremecomponents.table.core.TableModel;

public class ResearchStaffLinkDisplayCell extends AbstractCell {

    @Override
    protected String getCellValue(final TableModel model, final Column column) {

        ResearchStaff researchStaff = (ResearchStaff) model.getCurrentRowBean();
        String cellValue = column.getValueAsString();
        String link = model.getContext().getContextPath()
                        + "/pages/admin/editResearchStaff?researchStaffId=";
        
        String image = "";
        String imagePath = model.getContext().getContextPath() + "/images/chrome/nci_icon_22.png";
        if (researchStaff.getExternalId() != null) {
        	image = "<img src=\"" +imagePath+"\" alt=\"NCI data\" width=\"17\" height=\"16\" border=\"0\" align=\"middle\">&nbsp;";
        }

        if (researchStaff != null) {
            cellValue = image + "<a href=\"" + link + researchStaff.getId().toString() + "\">" +  cellValue
                            + "</a>";
        }
        //System.out.println(cellValue);
        return cellValue;
    }
}
