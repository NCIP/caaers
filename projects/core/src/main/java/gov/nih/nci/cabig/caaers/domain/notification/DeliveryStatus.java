package gov.nih.nci.cabig.caaers.domain.notification;

import gov.nih.nci.cabig.caaers.domain.CodedEnum;
import static gov.nih.nci.cabig.caaers.domain.CodedEnumHelper.*;

/**
 * This enumeration represents the status of an EmailNotificaiton.
 * @author Biju Joseph
 *
 */
public enum DeliveryStatus implements CodedEnum{
	CREATED(1),
	SCHEDULED(2),
	DELIVERED(3),
	ERROR(4),
	RETRY(5),
	RECALLED(6);
	
	
	private int code;
	
	private DeliveryStatus(int code){
		this.code = code;
		register(this);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.CodedEnum#getCode()
	 */
	public int getCode() {
		return code;
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.CodedEnum#getDisplayName()
	 */
	public String getDisplayName() {
		return name();
	}
	public String getName(){
		return name();
	}
	
	public static DeliveryStatus getByCode(int code){
		return getByClassAndCode(DeliveryStatus.class, code);
	}
}
