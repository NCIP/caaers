package gov.nih.nci.cabig.caaers.dao.query.ajax;

import java.util.List;

public class AjaxableDomainObjectQuery extends AbstractAjaxableDomainObjectQuery {

	public AjaxableDomainObjectQuery(String queryString) {
		super(queryString);
	}
	
    public void setParameterList(String name , List values){
		setParameter(name, values);
    }
    
    public void filterByAnyAnd(String clause) {
    	andWhere(clause);
    }
    
    

}
