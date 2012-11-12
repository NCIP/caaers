package gov.nih.nci.cabig.caaers.dao.query;

import org.hibernate.Hibernate;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author: Biju Joseph
 */
public class NativeSQLQuery extends AbstractQuery{
    private Map<String, org.hibernate.type.Type> scalarMap = new HashMap<String, org.hibernate.type.Type>();

    public NativeSQLQuery(String sql){
        super(sql);
    }

    public void setScalar(String key, org.hibernate.type.Type type){
        scalarMap.put(key, type);
    }

    public Set<String> getScalarNames(){
        return scalarMap.keySet();
    }

    public org.hibernate.type.Type getScalarType(String key){
        return scalarMap.get(key);
    }

    public Map<String, org.hibernate.type.Type> getScalarMap() {
        return scalarMap;
    }
}
