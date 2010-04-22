package gov.nih.nci.cabig.caaers.dao.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Saurabh Agrawal
 */
public class AbstractQuery {

    private final String queryString;

    private StringBuffer queryBuffer;

    private final List<String> andConditions = new LinkedList<String>();

    private final List<String> orConditions = new LinkedList<String>();

    private final List<String> joins = new ArrayList<String>();

    private final Map<String, Object> queryParameterMap;

    public final static String ID = "OBJID";

    public final static String WHERE = "WHERE";

    public final static String AND = "AND";

    public final static String OR = "OR";

    public AbstractQuery(final String queryString) {
        this.queryString = queryString;
        queryParameterMap = new HashMap<String, Object>(0);
    }

    public String getQueryString() {
        String orderByString = "";
        if (queryString.lastIndexOf("order by") > 0) {
            orderByString = queryString.substring(queryString.lastIndexOf("order by"),
                    queryString.length()).trim();
            queryBuffer = new StringBuffer(queryString.substring(0,
                    queryString.lastIndexOf("order by")).trim());
        } else {
            queryBuffer = new StringBuffer(queryString.trim());
        }

        for (String join : joins) {
            queryBuffer.append(join);
        }

        for (String conditon : andConditions) {

            if (queryBuffer.toString().toUpperCase().lastIndexOf(WHERE) < 0 || queryBuffer.toString().toUpperCase().lastIndexOf(WHERE)
                    < queryBuffer.toString().toUpperCase().lastIndexOf("FROM")) {
                queryBuffer.append(" " + WHERE + " " + conditon);
            } else {
                queryBuffer.append(" " + AND + " " + conditon);
            }

        }

        if (!orConditions.isEmpty()) {
            boolean groupOR = andConditions.size() > 0
                    || queryBuffer.toString().toUpperCase().indexOf(WHERE) > 0;

            if (groupOR) {
                queryBuffer.append(" " + AND + " (");
            }

            int orIndx = 0;
            for (String conditon : orConditions) {
                if (orIndx == 0 && groupOR) {
                    queryBuffer.append(" " + conditon);
                } else {
                    queryBuffer.append(" " + OR + " " + conditon);
                }
                orIndx++;
            }

            if (groupOR) {
                queryBuffer.append(" )");
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
     * @param key   the key of the parameter
     * @param value the value of the parameter
     */
    protected void setParameter(final String key, final Object value) {
        queryParameterMap.put(key, value);
    }

    /**
     * add the 'Where' condition to the existing Query String.
     * <p>
     * For example if for the queryString is "Select * from Article a order by a.id";
     * andWhere("a.name=:name") will append queryString to "Select * from Article a WHERE
     * a.name=:name order by a.id"
     * </p>
     *
     * @param condition the condition
     */
    protected void andWhere(final String condition) {
        andConditions.add(condition);
    }
    
    /**
     * add the 'Where' condition to the existing Query String.
     * <p>
     * For example if for the queryString is "Select * from Article a order by a.id";
     * andWhere("a.name=:name") will append queryString to "Select * from Article a WHERE
     * a.name=:name order by a.id"
     * </p>
     *
     * @param condition the condition
     */
    protected void orWhere(final String condition) {
        orConditions.add(condition);
    }

    public Map<String, Object> getParameterMap() {
        return queryParameterMap;
    }

    /**
     * Joins an object to the query select * from Study s join s.identifiers as id where
     * s.shortTitle='study'
     *
     * @param join
     */
    protected void join(String objectQuery) {
        addToJoinsList(" join " + objectQuery);

    }

    /**
     * Joins an object to the query select * from Study s left join s.identifiers as id where
     * s.shortTitle='study'
     *
     * @param join
     */
    protected void leftJoin(String objectQuery) {
        addToJoinsList(" left join " + objectQuery);
    }

    protected void leftJoinFetch(String objectQuery) {
        addToJoinsList(" left join fetch " + objectQuery);
    }

    private void addToJoinsList(String object) {
        if (!joins.contains(object)) joins.add(object);
    }
    
    public void setParameterList(String name , List values){
		setParameter(name, values);
    }
    
    public void filterByAnyAnd(String clause) {
    	andWhere(clause);
    }
    
    public void filterINQuery(String inClause , List ids){
		andWhere(inClause);
		setParameter("ids", ids);
    }
    
    
}
