package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import static gov.nih.nci.cabig.ctms.domain.EnumHelper.sentenceCasedName;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

public enum Stage implements CodedEnum<Integer> {
	
	 REQUEST_RECEIVED(1, "Message Received"),
     ROUTED_TO_ADEERS_SINK(10, "Message Routed to AdEERS Sink Channel"),
     ROUTED_TO_ADEERS_WS_INVOCATION_CHANNEL(20, "Routed to AdEERS Webservice Invocation route"),
     ADEERS_WS_IN_TRANSFORMATION(30, "AdEERS Webservice request transformation"),
     ADEERS_WS_INVOCATION(35, "AdEERS Webservice invocation"),
     ADEERS_WS_OUT_TRANSFORMATION(40, "AdEERS Webservice response transformation") ,
     ROUTED_TO_CAAERS_SINK(50, "Message Routed to caAERS Sink Channel"),
     CAAERS_WS_IN_TRANSFORMATION(60, "caAERS Webservice request transformation"),
     CAAERS_WS_INVOCATION(65, "caAERS Webservice invocation"),
     CAAERS_WS_OUT_TRANSFORMATION(70, "caAERS Webservice response transformation") ,
     REQUST_PROCESSING_ERROR(900, "Error while processing request"),
     REQUEST_COMPLETION(999, "Message processing complete");

    private Integer code;
    
    private String stageName;

    private Stage(int code, String stageName) {
        this.code = code;
        this.stageName=stageName;
        register(this);
    }

    public String getStageName() {
		return stageName;
	}

	public Integer getCode() {
        return code;
    }

    public String getDisplayName() {
        return sentenceCasedName(this);
    }

    public String getName() {
        return name();
    }

    public static Stage getByCode(Integer code) {
        return getByClassAndCode(Stage.class, code);
    }
}