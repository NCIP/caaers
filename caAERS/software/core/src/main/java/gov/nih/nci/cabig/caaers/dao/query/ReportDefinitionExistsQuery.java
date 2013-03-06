/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao.query;

import gov.nih.nci.cabig.caaers.domain.Organization;

/**
 * 
 * @author Biju Joseph
 *
 */
public class ReportDefinitionExistsQuery extends AbstractQuery {
	private static String queryString = "select count(rd) from ReportDefinition rd ";
	
	public ReportDefinitionExistsQuery() {
		super(queryString);
	}
	
	public void filterByDifferentId(Integer id){
    	if(id == null) return;
    	andWhere(" rd.id != :rdid");
    	setParameter("rdid", id);
    }
	
	public void filterByName(String name){
		if(name == null) return;
		andWhere("rd.name like :rdname");
		setParameter("rdname", name);
	}
	
	public void filterByOrganization(Organization organization){
		if(organization == null || organization.getNciInstituteCode() == null) return;
		andWhere("rd.organization.nciInstituteCode like :nciInstituteCode");
		setParameter("nciInstituteCode",organization.getNciInstituteCode());
	}
		
	
}
