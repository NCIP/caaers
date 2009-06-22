package gov.nih.nci.cabig.caaers.tools.editors;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang.StringUtils;

/**
 * @author Rhett Sutphin
 */
public class EnumByNameEditor<E extends Enum<E>> extends PropertyEditorSupport {
    Class<E> enumClass;

    public EnumByNameEditor(Class<E> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text == null || StringUtils.isBlank(text)) {
            setValue(null);
        } else {
            setValue(Enum.valueOf(enumClass, text));
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public String getAsText() {
        Enum<E> v = (Enum<E>) getValue();
        if (v == null) {
            return null;
        } else {
            return v.name();
        }
    }
}
