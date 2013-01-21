package gov.nih.nci.cabig.caaers;

/**
 * @author Rhett Sutphin
 */
public class CaaersSystemException extends RuntimeException {
    private String errorCode;

    public CaaersSystemException(String code, String message) {
        this(message);
        this.errorCode = code;
    }

    public CaaersSystemException(String code, String message, Throwable cause) {
        this(message, cause);
        this.errorCode = code;
    }

    public CaaersSystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public CaaersSystemException(Throwable cause) {
        super(cause);
    }

    public CaaersSystemException(String message) {
        super(message);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String toString(){
        String s = "";
        if(errorCode != null) s = errorCode;
        if(!s.isEmpty()) s = s + " : ";
        if(getMessage() != null) s = s + getMessage();
        return s;
    }
}
