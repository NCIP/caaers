package gov.nih.nci.cabig.caaers.accesscontrol.query;

import java.util.List;
import java.util.Map;

public interface IdFetcher {
	
	/**
	 * 
	 * @param loginId
	 * @param roleCode
	 * @return Ids for each roleCode 
	 * key - roleCode , value - List of Ids
	 */
	public Map<Integer,List<Integer>> fetch(String loginId , List<Integer> roleCode) ;
	
}
