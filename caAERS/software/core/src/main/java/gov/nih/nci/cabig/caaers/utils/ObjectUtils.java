package gov.nih.nci.cabig.caaers.utils;

import gov.nih.nci.cabig.caaers.domain.AbstractAdverseEventTerm;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;

/**
 * @author Biju Joseph
 * @since 1.5
 */
public class ObjectUtils {

    public static boolean equals(Short i, Short j){
        if(i == null && j == null) return false;
        return org.apache.commons.lang.ObjectUtils.equals(i, j);
    }

    public static boolean equals(Integer i, Integer j){
        if(i == null && j == null) return false;
        return org.apache.commons.lang.ObjectUtils.equals(i, j);
    }

    public static boolean equals(Long i, Long j){
        if(i == null && j == null) return false;
        return org.apache.commons.lang.ObjectUtils.equals(i, j);
    }

    public static boolean equals(Double i, Double j){
        if(i == null && j == null) return false;
        return org.apache.commons.lang.ObjectUtils.equals(i, j);
    }

    public static boolean equals(Boolean i, Boolean j){
        if(i == null && j == null) return false;
        return org.apache.commons.lang.ObjectUtils.equals(i, j);
    }

    public static boolean equals(String i, String j){
        if(i == null && j == null) return false;
        return org.apache.commons.lang.ObjectUtils.equals(i, j);
    }

}
