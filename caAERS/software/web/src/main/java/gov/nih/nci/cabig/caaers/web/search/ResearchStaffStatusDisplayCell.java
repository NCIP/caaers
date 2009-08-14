package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaff;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.AbstractCell;
import org.extremecomponents.table.core.TableModel;

public class ResearchStaffStatusDisplayCell extends AbstractCell {

    @Override
    protected String getCellValue(final TableModel model, final Column column) {

        ResearchStaff researchStaff = ((SiteResearchStaff)model.getCurrentRowBean()).getResearchStaff();
        String cellValue = column.getValueAsString();

        if (cellValue.equals("true")) return "Active"; else return "Inactive";
    }
}
