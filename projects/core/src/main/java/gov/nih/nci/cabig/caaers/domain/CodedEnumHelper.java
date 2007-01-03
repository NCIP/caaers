package gov.nih.nci.cabig.caaers.domain;

import java.util.Map;
import java.util.HashMap;

/**
 * Common implementations for CodedEnum instances.  Not for public use; this is a workaround
 * for the fact that you can't have an abstract base class for enums.
 *
 * @author Rhett Sutphin
 */
class CodedEnumHelper {
    private static Map<Class<? extends CodedEnum>, Map<Integer, Object>> byClassAndCode
        = new HashMap<Class<? extends CodedEnum>, Map<Integer, Object>>();

    static <T extends CodedEnum> void register(T instance) {
        Class<? extends CodedEnum> classKey = instance.getClass();
        if (!byClassAndCode.containsKey(classKey)) {
            byClassAndCode.put(classKey, new HashMap<Integer, Object>());
        }
        byClassAndCode.get(classKey).put(instance.getCode(), instance);
    }

    @SuppressWarnings("unchecked")
    static <T extends CodedEnum> T getByClassAndCode(Class<T> clazz, int code) {
        Map<Integer, Object> byCode = byClassAndCode.get(clazz);
        return byCode == null ? null : (T) byCode.get(code);
    }

    static <T extends Enum<T> & CodedEnum> String toStringHelper(T instance) {
        return new StringBuilder()
            .append(instance.getCode()).append(": ")
            .append(instance.getDisplayName()).toString();
    }

    static <T extends Enum<T> & CodedEnum> String titleCasedName(T instance) {
        StringBuilder name = new StringBuilder(instance.name().toLowerCase());
        name.replace(0, 1, name.substring(0, 1).toUpperCase());
        return name.toString();
    }
}
