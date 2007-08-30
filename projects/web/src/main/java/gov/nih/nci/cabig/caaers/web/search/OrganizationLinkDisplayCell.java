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
		String link = model.getContext().getContextPath() + "/pages/admin/editOrganization?organizationId=";

		if (organization != null) {
			cellValue = "<a href=\"" + link + organization.getId().toString() + "\">" + cellValue + "</a>";
		}
		return cellValue;
	}
}
