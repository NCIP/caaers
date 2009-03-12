package gov.nih.nci.cabig.caaers.domain.comparator;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.utils.CodedEnumUtils;

import java.util.Comparator;

import org.apache.commons.lang.BooleanUtils;

public class AdverseEventComprator implements Comparator<AdverseEvent>{
	
	public static final AdverseEventComprator DEFAULT_ADVERSE_EVENT_COMPARATOR = new AdverseEventComprator(); 
	
	/**
	 * Sort the AE's according to the following
           a. Descending by Reporting Required (Yes, then No)
           b. Descending by grade (highest grade 1st)
           c. Descending by Attribution (definite, probable, possible, unlikely, unrelated)
           d. Descending by Hospitalization (Yes, then No)
           e. Ascending by Expected (No, then Yes) 
           
	 * Returns 1 if ae2 is greater ae1, 0 if they are equal , -1 if ae1 is greater than ae2.
	 */
	public int compare(AdverseEvent ae1, AdverseEvent ae2) {
		if(ae1 == null && ae2 == null) return 0;
		if(ae1 == null) return 1;
		if(ae2 == null) return -1;
		
		//if they are equal return 0
		if(ae1 == ae2) return 0;
		
		if(BooleanUtils.isTrue(ae1.getRequiresReporting()) && BooleanUtils.isNotTrue(ae2.getRequiresReporting())) return -1;
		if(BooleanUtils.isTrue(ae2.getRequiresReporting()) && BooleanUtils.isNotTrue(ae1.getRequiresReporting())) return 1;
		
		int gradeComparisonResult = CodedEnumUtils.compare(ae2.getGrade(), ae1.getGrade());
		if(gradeComparisonResult != 0) return -1 * gradeComparisonResult;
		
		int attributionComparisonResult = CodedEnumUtils.compare(ae2.getAttributionSummary(), ae1.getAttributionSummary());
		if(attributionComparisonResult != 0) return -1 * attributionComparisonResult;
		
		int hospitalizationComparisonResult = CodedEnumUtils.compare(ae2.getHospitalization(), ae1.getHospitalization());
		if(hospitalizationComparisonResult != 0) return hospitalizationComparisonResult;
		
		if(BooleanUtils.isTrue(ae1.getExpected()) && BooleanUtils.isNotTrue(ae2.getExpected())) return -1;
		if(BooleanUtils.isTrue(ae2.getExpected()) && BooleanUtils.isNotTrue(ae1.getExpected())) return 1;
		
		return 0;
	};

}
