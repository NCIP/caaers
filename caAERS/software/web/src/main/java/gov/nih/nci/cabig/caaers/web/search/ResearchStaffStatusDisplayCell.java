package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.domain.ResearchStaff;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.AbstractCell;
import org.extremecomponents.table.core.TableModel;

public class ResearchStaffStatusDisplayCell extends AbstractCell {

    @Override
    protected String getCellValue(final TableModel model, final Column column) {

    	ResearchStaff researchStaff = (ResearchStaff)model.getCurrentRowBean();
        if (researchStaff.isActive()) return "Active"; else return "Inactive";
    }
}
