package gov.nih.nci.cabig.caaers.validation;

import java.util.Arrays;

public class ValidationError {
    private String code;
    private String message;
    private String[] fieldNames;
    private Object[] replacementVariables;

    public ValidationError() {
        this("0", "", "");
    }

    public ValidationError(String code, String msg, Object... replacementVariables) {
        this.code = code;
        this.message = msg;
        this.replacementVariables = replacementVariables;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object[] getReplacementVariables() {
        return replacementVariables;
    }

    @Override
    public String toString() {
        return "code: " + code + ", fieldNames: " + fieldNames + ", replacements: " + Arrays.asList(replacementVariables).toString() + ", message: " + message;
    }

    public void addFieldNames(String... fieldNames) {
        this.fieldNames = fieldNames;
    }

    public String[] getFieldNames() {
        return this.fieldNames;
    }

    public void setFieldNames(String[] fieldNames) {
        this.fieldNames = fieldNames;
    }
}
