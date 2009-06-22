package gov.nih.nci.cabig.caaers.rules.common;

public class AdverseEventEvaluationResult {

    private String message;

    public AdverseEventEvaluationResult() {
        this.message = null;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Message :" + message;
    }
}
