package gov.nih.nci.cabig.caaers.security.passwordpolicy;

import gov.nih.nci.cabig.caaers.domain.report.TimeScaleUnit;

public class Duration {
    
    private TimeScaleUnit _unitOfMeasure;
    private int _value;
    
    public TimeScaleUnit getUnitOfMeasure() {
	return _unitOfMeasure;
    }
    
    public void setUnitOfMeasure(TimeScaleUnit unitOfMeasure) {
	_unitOfMeasure = unitOfMeasure;
    }
    
    public int getValue() {
	return _value;
    }
    
    public void setValue(int value) {
	_value = value;
    }
}
