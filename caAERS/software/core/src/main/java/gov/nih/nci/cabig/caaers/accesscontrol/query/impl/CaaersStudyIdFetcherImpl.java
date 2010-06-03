package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import gov.nih.nci.cabig.caaers.dao.query.HQLQuery;

import java.util.List;

import com.semanticbits.security.contentfilter.IdFetcher;


/**
 * Will return the ID of the studies that a particular user has access to.
 * The rules are
 *
 *Research Staff:
 *  AE Coordinator	Study assignment
 *  Subject Coordinator	Study assignment
 *  Data Coordinator	Study assignment
 *  Central Office Report Reviewer	Study assignment
 *
 *  Study Coordinator	Organization association
 *  Site Coordinator	Organization association
 *
 *
 *Investigator  Study assignment
 *
 * @author Srini Akkala
 * @author Biju Joseph
 *
 */
public class CaaersStudyIdFetcherImpl extends AbstractIdFetcher implements IdFetcher {

	@Override
	public List fetch(String loginId) {
		 StringBuilder hql = new StringBuilder("select s.id from Study s");
		 HQLQuery query = new HQLQuery(hql.toString());
		 List<Integer> resultList = (List<Integer>) search(query);
		 return resultList;
	}
}
