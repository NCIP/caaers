package gov.nih.nci.cabig.caaers.dao.query;

import org.hibernate.Hibernate;
import org.hibernate.type.NullableType;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author: Biju Joseph
 */
public class NativeSQLQuery extends AbstractQuery{
    private Map<String, NullableType> scalarMap = new HashMap<String, NullableType>();

    public NativeSQLQuery(String sql){
        super(sql);
    }

    public void setScalar(String key, NullableType type){
        scalarMap.put(key, type);
    }

    public Set<String> getScalarNames(){
        return scalarMap.keySet();
    }

    public NullableType getScalarType(String key){
        return scalarMap.get(key);
    }

    public Map<String, NullableType> getScalarMap() {
        return scalarMap;
    }
}
