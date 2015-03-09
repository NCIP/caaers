package gov.nih.nci.cabig.report2caaers.exchange;

import org.apache.commons.lang.math.RandomUtils;

public class CorrelationIdBean {
	
	public String getId() {
		return String.valueOf(System.currentTimeMillis()) + randomAlphaNumberic(3));
	}
	
	private String randomAlphaNumberic(int length) {
		String str ="";
		for (int i =0; i < length; i++) {
			str += String.copyValueOf(Character.toChars(RandomUtils.nextInt(25) + 65));
		}
		return str;
	}
}