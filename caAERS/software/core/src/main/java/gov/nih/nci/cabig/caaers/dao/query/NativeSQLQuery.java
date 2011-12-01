package gov.nih.nci.cabig.caaers.dao.query;

import org.hibernate.Hibernate;
import org.hibernate.type.IntegerType;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author: Biju Joseph
 */
public class NativeSQLQuery extends AbstractQuery{
    private Map<String, IntegerType> scalarMap = new HashMap<String, IntegerType>();

    public NativeSQLQuery(String sql){
        super(sql);
    }

    public void setScalar(String key, IntegerType type){
        scalarMap.put(key, type);
    }

    public Set<String> getScalarNames(){
        return scalarMap.keySet();
    }

    public IntegerType getScalarType(String key){
        return scalarMap.get(key);
    }

    public Map<String, IntegerType> getScalarMap() {
        return scalarMap;
    }
}
