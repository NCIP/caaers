package gov.nih.nci.cabig.report2caaers.exchange;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;

public class CorrelationIdBean {
	
	public String getId() {
        return String.valueOf(System.currentTimeMillis()) + RandomStringUtils.randomAlphanumeric(5);
    }

}