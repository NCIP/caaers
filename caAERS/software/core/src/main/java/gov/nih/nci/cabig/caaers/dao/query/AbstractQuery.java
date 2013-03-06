/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao.query;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This class has the common functions used by all Queries in caAERS. 
 * @author Saurabh Agrawal
 * @author Biju Joseph
 */
public abstract class AbstractQuery {

    private  StringBuffer queryString;

    private final List<String> andConditions = new LinkedList<String>();

    private final List<String> orConditions = new LinkedList<String>();

    private final List<String> joins = new ArrayList<String>();

    private final Map<String, Object> queryParameterMap;

    public final static String ID = "OBJID";

    public final static String WHERE = "WHERE";

    public final static String AND = "AND";

    public final static String OR = "OR";

    private String orderByClause;

    private boolean filtered;
    
	public static final String STUDY_ALIAS = "s";
	
	public static final String PARTICIPANT_ALIAS = "p";
	

    public AbstractQuery(final String queryString) {
        this.queryString = new StringBuffer(queryString);
        queryParameterMap = new HashMap<String, Object>(0);
    }

    public String getQueryString() {
        
        StringBuffer queryBuffer = new StringBuffer(queryString.toString().trim());
        String upperCaseQuery = queryBuffer.toString().toUpperCase();

        int indexOfWhere = upperCaseQuery.indexOf(WHERE);
        int indexOfFrom = upperCaseQuery.indexOf("FROM");

        //is where condition present?
        boolean hasWhere = indexOfWhere > 0;


        for (String join : joins) {
            queryBuffer.append(join);
        }

        //add the Where conditions
        if( !(andConditions.isEmpty() && orConditions.isEmpty())){
          if(!hasWhere) {
              queryBuffer.append(" ").append(WHERE).append(" ");
              hasWhere = true;
          }

        }

        int n = andConditions.size();
        for(int i = 0; i< n; i++){
            if(i > 0) queryBuffer.append(AND).append(" ");
            queryBuffer.append(andConditions.get(i)).append(" ");
        }

        n = orConditions.size();
        if(n > 0){
            boolean groupOR = !andConditions.isEmpty();
            if(groupOR){
                queryBuffer.append("AND ( ");
            }
            for(int i =0; i < n; i++){
               if(i > 0) queryBuffer.append(OR).append(" ");
                queryBuffer.append(orConditions.get(i)).append(" ");
            }

            if(groupOR){
                queryBuffer.append(")");
            }

        }

        if (StringUtils.isNotEmpty(orderByClause)) {
            // finally add order by
            queryBuffer.append(" order by " + orderByClause);
        }

        return queryBuffer.toString().trim();
    }

    /**
     * Bind an argument to a named parameter.
     *
     * @param key   the key of the parameter
     * @param value the value of the parameter
     */
    public void setParameter(final String key, final Object value) {
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
    public void andWhere(final String condition) {
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
    public void orWhere(final String condition) {
        orConditions.add(condition);
    }

    public Map<String, Object> getParameterMap() {
        return queryParameterMap;
    }

    /**
     * Joins an object to the query select * from Study s join s.identifiers as id where
     * s.shortTitle='study'
     *
     * @param objectQuery
     */
     public void join(String objectQuery) {
        join(objectQuery, -1);

    }


    /**
     * Joins an object to the query select * from Study s join s.identifiers as id where
     * s.shortTitle='study'
     *
     * @param objectQuery
     * @param pos the position in join list to insert the join query. 
     */
     public void join(String objectQuery, int pos) {
        addToJoinsList(" join " + objectQuery, pos);

    }

    /**
     * Joins an object to the query select * from Study s left join s.identifiers as id where
     * s.shortTitle='study'
     *
     * @param objectQuery
     */
    public void leftJoin(String objectQuery) {
        addToJoinsList(" left join " + objectQuery);
    }

    public void leftOuterJoin(String objectQuery) {
        addToJoinsList(" left outer join " + objectQuery);
    }
    
    public void leftJoinFetch(String objectQuery) {
        addToJoinsList(" left join fetch " + objectQuery);
    }

    public void addToJoinsList(String object) {
        addToJoinsList(object, -1);
    }


    public void addToJoinsList(String object, int pos) {
        if (!joins.contains(object)) {
            if(pos != -1) joins.add(pos, object);
            else joins.add(object);
        }
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

    /**
     * Will append to the query the order by clause. 
     * @param orderBy
     */
    public void orderBy(String orderBy){
        this.orderByClause = orderBy;  
    }

    /**
     * Will modify the base query with the newly provided query.
     * @param newQuery - An hql
     */
    public void  modifyQueryString(String newQuery){
        queryString.setLength(0);
        queryString.append(newQuery);
    }

    /**
     * The query string this query represents will be returned. 
     * @return
     */
    public String getBaseQueryString(){
        return queryString.toString();
    }

    public boolean isFiltered(){
        return filtered;
    }

    public void setFiltered(boolean b){
        filtered = b;
    }
    
    
    public String getLikeValue(String value) {
    	if (value.indexOf("%") != -1) {
			return value;
		} else {
			return "%"+value+"%";
		}
    }
    // Advanced Search query methods 
    // id
    public void filterByStudyId(final Integer id,String operator) {
        andWhere("s.id "+operator+" :ID");
        setParameter("ID", id);
    } 
    
    // shortTitle
    public void filterByStudyShortTitle(final String shortTitleText , String operator) {
    	andWhere("lower(s.shortTitle) "+operator+" :" + "shortTitleText");
        
    	if (operator.equals("like")) {
    		setParameter("shortTitleText", getLikeValue(shortTitleText.toLowerCase()));
    	} else {
    		setParameter("shortTitleText", shortTitleText.toLowerCase());
    	}
    }

    // longTitle
    public void filterByStudyLongTitle(final String longTitleText ,String operator) {
    	andWhere("lower(s.longTitle) "+operator+" :" + "longTitleText");
        
    	if (operator.equals("like")) {
    		setParameter("longTitleText", getLikeValue(longTitleText.toLowerCase()));
    	} else {
    		setParameter("longTitleText", longTitleText.toLowerCase());
    	}
    }
    
    
    // participant-id
    public void filterByParticipantId(final Integer id,String operator) {
        andWhere("p.id "+operator+" :id");
        setParameter("id", id);
    }  
    
    
    
    // participant-FirstName
    public void filterByParticipantFirstName(final String fName,String operator) {
        andWhere("lower(p.firstName) "+operator+" :pfName");
        if (operator.equals("like")) {
        	setParameter("pfName", getLikeValue(fName.toLowerCase()));
        } else {
        	setParameter("pfName", fName.toLowerCase());
        }
    }

    // participant - LastName
    public void filterByParticipantLastName(final String lName,String operator) {
        andWhere("lower(p.lastName) "+operator+" :plName");
        if (operator.equals("like")) {
        	setParameter("plName", getLikeValue(lName.toLowerCase()) );
        } else {
        	setParameter("plName", lName.toLowerCase() );
        }
    }

    // participant - Ethnicity
    public void filterByParticipantEthnicity(String ethenicity,String operator) {
        andWhere("lower(p.ethnicity) "+operator+" :pEthenicity");
        setParameter("pEthenicity", ethenicity.toLowerCase() );
    }

    // participant - Race
    public void filterByParticipantRace(String race,String operator) {
        andWhere("lower(p.race) "+operator+" :pRace");
        setParameter("pRace", race.toLowerCase() );
    }
    
    // p.gender
    public void filterByParticipantGender(final String gender,String operator) {
        andWhere("lower(p.gender) "+operator+" :pGender");
        setParameter("pGender", gender.toLowerCase());
    }
    
    public void filterByParticipantDOB(String dateString , String operator) throws Exception {
    	andWhere(createDateQuery("p.dateOfBirth", dateString, operator));
    }
    
    
	public  String createDateQuery(String fullAttributeName, String dateString, String predicate) throws Exception {
		Date dateValue = null;
		try {
			//dateValue = java.text.DateFormat.getDateTimeInstance().parse(dateString);
			DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	        dateValue = (Date)formatter.parse(dateString);
	    
		} catch (Exception ex) {
			throw new Exception("Error parsing date " + dateString + ": " + ex.getMessage(), ex);
		}
		String yearPredicate = null;
		String monthPredicate = null;
		
		if (predicate.equals(">") || predicate.equals(">=")) {
			yearPredicate = ">";
			monthPredicate = ">";
		} else if (predicate.equals("<") || predicate.equals("<=")) {
			yearPredicate = "<=";
			monthPredicate = "<";
		} 

		StringBuilder dateQuery = new StringBuilder();
		// parse the date value into a Java Date object
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateValue);
		dateQuery.append("(");
		
		int month = cal.get(Calendar.MONTH) + 1;
		if(!predicate.equals("=")){
			String yearQuery = "year(" + fullAttributeName + ") " + yearPredicate + " '" + cal.get(Calendar.YEAR) + "'";
			String monthQuery = "year(" + fullAttributeName + ") = '" + cal.get(Calendar.YEAR) + "' AND " +
					"month(" + fullAttributeName + ") " + monthPredicate + " '" + month + "'";
			String dayQuery = "year(" + fullAttributeName + ") = '" + cal.get(Calendar.YEAR) + "' AND " +
				"month(" + fullAttributeName + ") = '" + month + "' AND " +
				"day(" + fullAttributeName + ") " + predicate + " '" + cal.get(Calendar.DAY_OF_MONTH) + "'";
			dateQuery.append(yearQuery).append(" OR (").append(monthQuery).append(") OR (").append(dayQuery).append(")");
		}else{
			dateQuery.append("year(").append(fullAttributeName).append(") = '").append(cal.get(Calendar.YEAR)).append("' AND ")
					.append("month(").append(fullAttributeName).append(") = '").append(month).append("' AND ")
					.append("day(").append(fullAttributeName).append(") = '").append(cal.get(Calendar.DAY_OF_MONTH)).append("'");
		}
		dateQuery.append(")");
		return dateQuery.toString();
	}

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
    
}
