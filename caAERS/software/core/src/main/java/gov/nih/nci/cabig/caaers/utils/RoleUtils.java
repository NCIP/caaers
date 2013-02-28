/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.utils;

/**
 * 
 * @author Biju Joseph
 *
 */
public class RoleUtils {

    public static final String[] sponsorAndCoordinatingCenterSpecificRoles = {
            "ae_expedited_report_reviewer",
            "ae_study_data_reviewer"
    };

	//known site specific roles.
	public  static final  String[] studySiteSpecificRoles = {"ae_reporter",
			  							 "SI",/*Site Investigator*/
			  							 "SPI" /*Site Principal Investigator*/};
	//known report specific roles. 
	 public  static final String[] reportSpecificRoles = {"REP",/*Reporter*/
			  						  "SUB" /*Submitter*/, "PHY" /*Physician*/};

}
