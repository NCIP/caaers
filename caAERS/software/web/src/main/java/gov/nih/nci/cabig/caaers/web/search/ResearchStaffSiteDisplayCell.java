package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaff;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.AbstractCell;
import org.extremecomponents.table.core.TableModel;

public class ResearchStaffSiteDisplayCell extends AbstractCell{

	@Override
	protected String getCellValue(TableModel model, Column arg1) {
		ResearchStaff researchStaff = (ResearchStaff)model.getCurrentRowBean();
		String cellValue = "";
		
		for(SiteResearchStaff srs : researchStaff.getSiteResearchStaffs()){
			cellValue += srs.getOrganization().getName()+"<br>";
		}
		return cellValue;
	}

}
