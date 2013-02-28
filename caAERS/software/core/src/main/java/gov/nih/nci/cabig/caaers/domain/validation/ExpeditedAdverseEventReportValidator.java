/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.validation;

import org.springframework.validation.Errors;
/**
 * This interface to validate an ExpeditedAdverseEventReport object.
 * 
 * Following validations are done by this validator
 * Study Device: either device or one among (commonName,brandName) should be specified .
 * Treatment Information: course numbers should not be greater than total number of courses.
 * Disease History: 'Diagnosis date' cannot be a future date
 * Adverse event :'First learned date' cannot be a future date; cannot be before the 'Start date'
 * Lab: 'Baseline value date', 'Worst value date' and 'Recovery date' cannot be future dates.
 * Lab: 'Baseline value date', cannot be after 'Worst value date' and 'Worst value date' cannot be after 'Recovery value date'.
 * 
 * @author Ramakrishna Gundala
 */

public interface ExpeditedAdverseEventReportValidator{
	public void validate(Object obj, Errors errors);
}
