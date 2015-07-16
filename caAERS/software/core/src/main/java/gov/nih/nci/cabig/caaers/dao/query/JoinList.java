package gov.nih.nci.cabig.caaers.dao.query;

import java.util.LinkedHashMap;
import java.util.Map;

public class JoinList {

	private LinkedHashMap<String, JoinType> joins = new LinkedHashMap<>();
	
	public void addJoin(JoinType join) {
		JoinType ext = joins.get(join.getTableName());
		if (ext == null) {
			joins.put(join.getTableName(), join);
		} else {
			ext.mergeJoin(join);
		}
	}
	
	public void clear() {
		joins.clear();
	}
	
	public String getClause() {
		StringBuilder str = new StringBuilder(24*joins.size());
		str.append(" ");
		//go through entryset for ordered list.
		for(Map.Entry<String, JoinType> join : joins.entrySet()) {
			str.append(join.getValue().getClause()).append(" ");
		}
		
		return str.toString();
	}
}
