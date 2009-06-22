package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.domain.Organization;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.AbstractCell;
import org.extremecomponents.table.core.TableModel;

public class OrganizationLinkDisplayCell extends AbstractCell {

    @Override
    protected String getCellValue(final TableModel model, final Column column) {

        Organization organization = (Organization) model.getCurrentRowBean();
        String cellValue = column.getValueAsString();
        String link = model.getContext().getContextPath()
                        + "/pages/admin/editOrganization?organizationId=";
        
        String image = "";
        String imagePath = model.getContext().getContextPath() + "/images/chrome/nci_icon_22.png";
        if (organization.getExternalId() != null) {
        	image = "<img src=\"" +imagePath+"\" alt=\"NCI data\" width=\"17\" height=\"16\" border=\"0\" align=\"middle\">&nbsp;";
        }

        if (organization != null) {
            cellValue = image + "<a href=\"" + link + organization.getId().toString() + "\">" + cellValue
                            + "</a>";
        }
        return cellValue;
    }
}
