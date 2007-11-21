package gov.nih.nci.cabig.caaers.security.passwordpolicy;

import gov.nih.nci.cabig.caaers.domain.report.TimeScaleUnit;

public class Duration {

	private TimeScaleUnit unitOfMeasure;
	private int value;

	public Duration(){

	}

	public void finalize() throws Throwable {

	}

	public TimeScaleUnit getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(TimeScaleUnit unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
