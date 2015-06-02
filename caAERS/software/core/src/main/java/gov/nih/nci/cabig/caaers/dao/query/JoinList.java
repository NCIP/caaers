package gov.nih.nci.cabig.caaers.dao.query;

import java.util.LinkedHashMap;

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
		for(JoinType join : joins.values()) {
			str.append(join.getClause()).append(" ");
		}
		
		return str.toString();
	}
}
