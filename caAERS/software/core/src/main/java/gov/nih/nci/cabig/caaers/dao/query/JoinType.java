package gov.nih.nci.cabig.caaers.dao.query;

public class JoinType {

	enum JOIN {
		INNER_JOIN("join"),
		LEFT_JOIN("left join"),
		RIGHT_JOIN("right join"),
		FULL_JOIN("full join");
		
		public final String hqlName;
		
		private JOIN(String str) {
			hqlName = str;
		}
	}
	
	private JOIN join;
	private final String table;
	private boolean fetch = false;
	
	public JoinType(String tableName) {
		this(tableName, JOIN.INNER_JOIN);
	}
	
	public JoinType(String tableName, JOIN join) {
		this(tableName, join, false);
	}
	
	public JoinType(String tableName, JOIN join, boolean fetch) {
		this.join = join;
		this.fetch = fetch;
		this.table = tableName;
	}

	public JOIN getJoin() {
		return join;
	}

	public void setJoin(JOIN join) {
		this.join = join;
	}

	public boolean isFetch() {
		return fetch;
	}

	public void setFetch(boolean fetch) {
		this.fetch = fetch;
	}
	
	public String getTableName() {
		return table;
	}
	
	public String getClause() {
		StringBuilder str = new StringBuilder();
		str.append(join.hqlName);
		if(fetch) {
			str.append(" fetch");
		}
		str.append(" ").append(table);
		
		return str.toString();
	}
	
	public void mergeJoin(JoinType other) {
		if(other.fetch) {
			fetch = true;
		}
		if (join != other.join) {
			switch(join) {
			case FULL_JOIN: //do nothing, already at highest level
				break;
			case INNER_JOIN: join = other.join; //if inner stay the same otherwise match the other
				break;
			case LEFT_JOIN: if(other.join == JOIN.FULL_JOIN || other.join == JOIN.RIGHT_JOIN) {
					join = JOIN.FULL_JOIN;
				} //otherwise inner join or mathcih join type so no change.
				break;
			case RIGHT_JOIN: if(other.join == JOIN.FULL_JOIN || other.join == JOIN.LEFT_JOIN) {
					join = JOIN.FULL_JOIN;
				} //otherwise inner join or mathcih join type so no change.
				break;			
			}
		}
	}
	
}
