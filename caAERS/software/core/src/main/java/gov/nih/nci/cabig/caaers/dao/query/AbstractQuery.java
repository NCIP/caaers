/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao.query;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import gov.nih.nci.cabig.caaers.dao.query.JoinType.JOIN;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

    private final JoinList joins = new JoinList();

    private final Map<String, Object> queryParameterMap;

    public final static String ID = "OBJID";

    public final static String WHERE = "WHERE";

    public final static String AND = "AND";

    public final static String OR = "OR";

    private String orderByClause;

    private boolean filtered;
    
	public static final String STUDY_ALIAS = "s";
	
	public static final String PARTICIPANT_ALIAS = "p";
	
	public static final String PARAMETER_BASE = "param";
	private int paramCounter = 0;
	

    public AbstractQuery(final String queryString) {
        this.queryString = new StringBuffer(queryString);
        queryParameterMap = new HashMap<String, Object>(0);
    }
    
    public String generateParam() {
    	String param = PARAMETER_BASE + paramCounter++;
    	return param;
    }
    
    public String generateParam(Object value) {
    	String param = generateParam();
    	this.setParameter(param, value);
    	return param;
    }

    public String getQueryString() {
        
        StringBuffer queryBuffer = new StringBuffer(queryString.toString().trim());
        String upperCaseQuery = queryBuffer.toString().toUpperCase();

        int indexOfWhere = upperCaseQuery.indexOf(WHERE);

        //is where condition present?
        boolean hasWhere = indexOfWhere > 0;

        //add all the joins
        queryBuffer.append(joins.getClause());

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
    	 addToJoinsList(new JoinType(objectQuery, JOIN.INNER_JOIN));

    }
     
     /**
      * Joins an object to the query join conditions at the beginning of the list
      *
      * @param objectQuery
      */
      public void joinAtTheBeginningOfList(String objectQuery) {
     	 addToJoinsListAtTheBeginning(new JoinType(objectQuery, JOIN.INNER_JOIN));

     }

    /**
     * Joins an object to the query select * from Study s left join s.identifiers as id where
     * s.shortTitle='study'
     *
     * @param objectQuery
     */
    public void leftJoin(String objectQuery) {
        addToJoinsList(new JoinType(objectQuery, JOIN.LEFT_JOIN));
    }
    
    @Deprecated
    public void leftOuterJoin(String str) {
    	leftJoin(str);
    }
    
    @Deprecated
    public void leftOuterJoinFetch(String str) {
    	leftJoinFetch(str);
    }
    
    public void leftJoinFetch(String objectQuery) {
    	addToJoinsList(new JoinType(objectQuery, JOIN.LEFT_JOIN, true));
    }
    
    public void addToJoinsList(JoinType join) {
        joins.addJoin(join);
    }
    
    public void addToJoinsListAtTheBeginning(JoinType join) {
        joins.addJoinAtTheBeginning(join);
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
		}
		return "%"+value+"%";
    }
    // Advanced Search query methods 
    // id
    public void filterByStudyId(final Integer id,String operator) {
        andWhere("s.id " + parseOperator(operator) + " :" + generateParam(id));
    }

    public void filterByStudyId(Integer id){
        filterByStudyId(id, "=");
    }
    
    // shortTitle
    public void filterByStudyShortTitle(final String shortTitleText , String operator) {
    	final String titleText = generateParam();
    	andWhere("lower(s.shortTitle) " + parseOperator(operator) + " :" + titleText);
        
    	if (operator.equals("like")) {
    		setParameter(titleText, getLikeValue(shortTitleText.toLowerCase()));
    	} else {
    		setParameter(titleText, shortTitleText.toLowerCase());
    	}
    }

    // longTitle
    public void filterByStudyLongTitle(final String longTitleText ,String operator) {
    	final String titleText = generateParam();
    	andWhere("lower(s.longTitle) " + parseOperator(operator) + " :" + titleText);
    	if (operator.equals("like")) {
    		setParameter(titleText, getLikeValue(longTitleText.toLowerCase()));
    	} else {
    		setParameter(titleText, longTitleText.toLowerCase());
    	}
    }
    
    
    // participant-id
    public void filterByParticipantId(final Integer id,String operator) {
        andWhere("p.id " + parseOperator(operator) + " :" + generateParam(id));
    }  
    
    
    
    // participant-FirstName
    public void filterByParticipantFirstName(final String fName,String operator) {
    	final String pfName = generateParam();
        andWhere("lower(p.firstName) " + parseOperator(operator) + " :" + pfName);
        if (operator.equals("like")) {
        	setParameter(pfName, getLikeValue(fName.toLowerCase()));
        } else {
        	setParameter(pfName, fName.toLowerCase());
        }
    }

    // participant - LastName
    public void filterByParticipantLastName(final String lName,String operator) {
    	final String plName = generateParam();
        andWhere("lower(p.lastName) " + parseOperator(operator) + " :" + plName);
        if (operator.equals("like")) {
        	setParameter(plName, getLikeValue(lName.toLowerCase()) );
        } else {
        	setParameter(plName, lName.toLowerCase() );
        }
    }

    // participant - Ethnicity
    public void filterByParticipantEthnicity(String ethenicity,String operator) {
        andWhere("lower(p.ethnicity) " + parseOperator(operator) + " :" + generateParam(ethenicity.toLowerCase()));
    }

    // participant - Race
    public void filterByParticipantRace(String race,String operator) {
        andWhere("lower(p.race) " + parseOperator(operator) + " :" + generateParam(race.toLowerCase()));
    }
    
    // p.gender
    public void filterByParticipantGender(final String gender,String operator) {
        andWhere("lower(p.gender) " + parseOperator(operator) + " :" + generateParam(gender.toLowerCase()));
    }
    
    public void filterByParticipantDOB(String dateString , String operator) throws Exception {
    	andWhere(createDateQuery("p.dateOfBirth", dateString, operator));
    }

    public void filterByRetiredStatus(String alias, Boolean status) {
        andWhere(alias + ".retiredIndicator = :" + generateParam(status.booleanValue()));
    }

	public  String createDateQuery(String fullAttributeName, String dateString, String predicate) throws Exception {
		Date dateValue = null;
		predicate = parseOperator(predicate);
		try {
			//dateValue = java.text.DateFormat.getDateTimeInstance().parse(dateString);
			DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	        dateValue = formatter.parse(dateString);
	    
		} catch (Exception ex) {
			throw new Exception("Error parsing date " + dateString + ": " + ex.getMessage(), ex);
		}
		String yearPredicate = null;
		String monthPredicate = null;
		
		if (predicate.equals(">") || predicate.equals(">=")) {
			yearPredicate = ">";
			monthPredicate = ">";
		} else if (predicate.equals("<") || predicate.equals("<=")) {
			yearPredicate = "<";
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
    
    protected String parseOperator(String operator) {
    	if(operator.equals("=")) {
    		return "=";
    	} else if(operator.equals("!=")) {
    		return "!=";
    	} else if(operator.equals("gt")) {
    		return ">=";
    	} else if(operator.equals("g")) {
    		return ">";
    	} else if(operator.equals("l")) {
    		return "<";
    	} else if(operator.equals("lt")) {
    		return "<=";
    	} else if(operator.equals("like")) {
    		return "like";
		}
		throw new IllegalArgumentException("Operator: '" + operator + "' is not recognized as a valid operator.");
	}
    
}
