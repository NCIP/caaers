package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.Organization;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.AbstractCell;
import org.extremecomponents.table.core.TableModel;

public class AgentLinkDisplayCell extends AbstractCell {

    @Override
    protected String getCellValue(final TableModel model, final Column column) {

        Agent agent = (Agent) model.getCurrentRowBean();
        String cellValue = column.getValueAsString();
        String link = model.getContext().getContextPath() + "/pages/admin/asaelEdit?agentID=";

        if (agent != null) {
            cellValue = "<a href=\"" + link + agent.getId().toString() + "\">" + cellValue + "</a>";
        }
        
        return cellValue;
    }
}