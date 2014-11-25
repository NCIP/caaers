/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.utils;

/**
 * 
 * @author Ramakrishna Gundala
 *
 */
import org.apache.commons.lang.StringUtils;

public class CaaersUtils {
	
	/**
     * This method will return String
     * @param inputString
     * @return
     */
	
	/*
	 * This method is used by the ReportVersion and the Reporter objects, which store email as a string
	 */
	public static String getEmailStringWithoutSemiColonsAndSpaces(String inputString){
		if(StringUtils.isBlank(inputString)){
			return null;
		}
		
		return inputString.replace(";", ",").replace(" ", "");
	}

}
