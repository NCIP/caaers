package gov.nih.nci.cabig.caaers.dao.query;

import java.util.List;

/**
 * @author: Biju Joseph
 */
public class UserQuery extends AbstractQuery{

    public UserQuery(){
        this("select distinct u from _User u");
    }
    public UserQuery(String queryString) {
        super(queryString);
    }

    public void filterByLoginNames(List<String> loginNames){
        andWhere("u.loginName in( :loginNames )");
        setParameterList("loginNames", loginNames);
    }
}
