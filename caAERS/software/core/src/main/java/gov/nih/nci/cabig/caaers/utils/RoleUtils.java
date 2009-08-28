package gov.nih.nci.cabig.caaers.utils;

/**
 * 
 * @author Biju Joseph
 *
 */
public class RoleUtils {

	//known site specific roles.
	public transient static final  String[] studySiteSpecificRoles = {"caaers_participant_cd",
			  							 "caaers_ae_cd",
			  							 "caaers_site_cd",
			  							 "SI",/*Site Investigator*/
			  							 "SPI" /*Site Principal Investigator*/};
	//known report specific roles. 
	 public transient static final String[] reportSpecificRoles = {"REP",/*Reporter*/
			  						  "SUB" /*Submitter*/};

}
