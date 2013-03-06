/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.AbstractCell;
import org.extremecomponents.table.core.TableModel;

public class InvestigatorSiteDisplayCell extends AbstractCell{

	@Override
	protected String getCellValue(TableModel model, Column arg1) {
		Investigator investigator = (Investigator)model.getCurrentRowBean();
		String cellValue = "";
		
		for(SiteInvestigator sInv : investigator.getSiteInvestigators()){
			cellValue += sInv.getOrganization().getName()+"<br>";
		}
		return cellValue;
	}

}
