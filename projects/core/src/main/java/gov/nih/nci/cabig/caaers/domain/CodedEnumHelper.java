package gov.nih.nci.cabig.caaers.domain;

import java.util.Map;
import java.util.HashMap;

/**
 * Common implementations for CodedEnum instances.  This is a workaround
 * for the fact that you can't have an abstract base class for enums.
 *
 * @author Rhett Sutphin
 */
@SuppressWarnings("RawUseOfParameterizedType") // can't be avoided in this class
public class CodedEnumHelper extends EnumHelper {
    private static Map<Class<? extends CodedEnum>, Map<Object, Object>> byClassAndCode
        = new HashMap<Class<? extends CodedEnum>, Map<Object, Object>>();

    public static <C, T extends CodedEnum<C>> void register(T instance) {
        Class<? extends CodedEnum> classKey = instance.getClass();
        if (!byClassAndCode.containsKey(classKey)) {
            byClassAndCode.put(classKey, new HashMap<Object, Object>());
        }
        byClassAndCode.get(classKey).put(instance.getCode(), instance);
    }

    @SuppressWarnings("unchecked")
    public static <C, T extends CodedEnum<C>> T getByClassAndCode(Class<T> clazz, C code) {
        Map<Object, Object> byCode = byClassAndCode.get(clazz);
        return byCode == null ? null : (T) byCode.get(code);
    }

    public static <T extends Enum<T> & CodedEnum> String toStringHelper(T instance) {
        return new StringBuilder()
            .append(instance.getCode()).append(": ")
            .append(instance.getDisplayName()).toString();
    }
}
