package gov.nih.nci.cabig.caaers.dao.query;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Saurabh Agrawal
 * 
 */
public class AbstractQuery {

	private final String queryString;

	private StringBuffer queryBuffer;

	private final List<String> conditions = new LinkedList<String>();

	private final Map<String, Object> queryParameterMap;

	private static String WHERE = "WHERE";

	private static String AND = "AND";

	public AbstractQuery(final String queryString) {
		this.queryString = queryString;
		queryParameterMap = new HashMap<String, Object>(0);
	}

	public String getQueryString() {
		String orderByString = "";
		if (queryString.lastIndexOf("order by") > 0) {
			orderByString = queryString.substring(queryString.lastIndexOf("order by"), queryString.length()).trim();
			queryBuffer = new StringBuffer(queryString.substring(0, queryString.lastIndexOf("order by")).trim());
		}
		else {
			queryBuffer = new StringBuffer(queryString.trim());
		}

		for (String conditon : conditions) {
			if (queryBuffer.toString().toUpperCase().indexOf(WHERE) < 0) {
				queryBuffer.append(" " + WHERE + " " + conditon);
			}
			else {
				queryBuffer.append(" " + AND + " " + conditon);
			}

		}
		if (!orderByString.equalsIgnoreCase("")) {
			// finally add order by
			queryBuffer.append(" " + orderByString);
		}

		return queryBuffer.toString();
	}

	/**
	 * Bind an argument to a named parameter.
	 * 
	 * @param key the key of the parameter
	 * @param value the value of the parameter
	 */
	protected void setParameter(final String key, final Object value) {
		queryParameterMap.put(key, value);
	}

	/**
	 * add the 'Where' condition to the existing Query String.
	 * <p>
	 * For example if for the queryString is "Select * from Article a order by a.id"; andWhere("a.name=:name") will append queryString to
	 * "Select * from Article a WHERE a.name=:name order by a.id"
	 * </p>
	 * @param condition the condition
	 */
	protected void andWhere(final String condition) {
		conditions.add(condition);
	}

	public Map<String, Object> getParameterMap() {
		return queryParameterMap;
	}

}
