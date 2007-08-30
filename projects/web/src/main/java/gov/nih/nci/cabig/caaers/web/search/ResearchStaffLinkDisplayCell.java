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
		String link = model.getContext().getContextPath() + "/pages/admin/editResearchStaff?researchStaffId=";

		if (researchStaff != null) {
			cellValue = "<a href=\"" + link + researchStaff.getId().toString() + "\">" + cellValue + "</a>";
		}
		return cellValue;
	}
}
