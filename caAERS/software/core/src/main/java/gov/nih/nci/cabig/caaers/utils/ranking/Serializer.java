package gov.nih.nci.cabig.caaers.utils.ranking;

/**
 * @author: Biju Joseph
 */
public interface Serializer<T> {
    /**
     * Will serailize to string the object passed-in. 
     * @param object
     * @return
     */
    public String serialize(T object);
}
