package gov.nih.nci.cabig.caaers.domain;

/**
 * @author Rhett Sutphin
 */
class EnumHelper {
   public static <T extends Enum<T>> String titleCasedName(T instance) {
        StringBuilder name = new StringBuilder(instance.name().toLowerCase());
        name.replace(0, 1, name.substring(0, 1).toUpperCase());
        return name.toString();
    }
}
