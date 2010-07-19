package gov.nih.nci.cabig.caaers.utils;


/**
 * @author: Biju Joseph
 */
public class MethodParamsHolder {
    private static final ThreadLocal<Object[]> holder = new ThreadLocal<Object[]>();

    private MethodParamsHolder(){
        //nothing to prevent sub-classing and instantiating. 
    }

    public static Object[] getParams(){
        return holder.get();
    }

    public static void setParams(Object[] params){
        holder.set(params);
    }
}
