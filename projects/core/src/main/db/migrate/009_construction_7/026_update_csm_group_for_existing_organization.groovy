class UpdateCsmPrivileges extends edu.northwestern.bioinformatics.bering.Migration {

void up() {


        execute("update  csm_group set GROUP_NAME='gov.nih.nci.cabig.caaers.domain.Organization.DEFAULT' where GROUP_ID =-6");
        execute("update  csm_group set GROUP_NAME='gov.nih.nci.cabig.caaers.domain.Organization.NCI' where GROUP_ID =-7");
        execute("update  csm_group set GROUP_NAME='gov.nih.nci.cabig.caaers.domain.Organization.WAKE' where GROUP_ID =-8");
        execute("update  csm_group set GROUP_NAME='gov.nih.nci.cabig.caaers.domain.Organization.DUKE' where GROUP_ID =-9");
        execute("update  csm_group set GROUP_NAME='gov.nih.nci.cabig.caaers.domain.Organization.DCP' where GROUP_ID =-10");
        execute("update  csm_group set GROUP_NAME='gov.nih.nci.cabig.caaers.domain.Organization.CTEP' where GROUP_ID =-11");

        execute("update  csm_protection_element set protection_element_name='gov.nih.nci.cabig.caaers.domain.Organization.DEFAULT' where protection_element_id =-6");
        execute("update  csm_protection_element set protection_element_name='gov.nih.nci.cabig.caaers.domain.Organization.NCI' where protection_element_id =-7");
        execute("update  csm_protection_element set protection_element_name='gov.nih.nci.cabig.caaers.domain.Organization.WAKE' where protection_element_id =-8");
        execute("update  csm_protection_element set protection_element_name='gov.nih.nci.cabig.caaers.domain.Organization.DUKE' where protection_element_id =-9");
        execute("update  csm_protection_element set protection_element_name='gov.nih.nci.cabig.caaers.domain.Organization.DCP' where protection_element_id =-10");
        execute("update  csm_protection_element set protection_element_name='gov.nih.nci.cabig.caaers.domain.Organization.CTEP' where protection_element_id =-11");

        execute("update  CSM_PROTECTION_GROUP set PROTECTION_GROUP_NAME='gov.nih.nci.cabig.caaers.domain.Organization.DEFAULT' where PROTECTION_GROUP_ID =-6");
        execute("update  CSM_PROTECTION_GROUP set PROTECTION_GROUP_NAME='gov.nih.nci.cabig.caaers.domain.Organization.NCI' where PROTECTION_GROUP_ID =-7");
        execute("update  CSM_PROTECTION_GROUP set PROTECTION_GROUP_NAME='gov.nih.nci.cabig.caaers.domain.Organization.WAKE' where PROTECTION_GROUP_ID =-8");
        execute("update  CSM_PROTECTION_GROUP set PROTECTION_GROUP_NAME='gov.nih.nci.cabig.caaers.domain.Organization.DUKE' where PROTECTION_GROUP_ID =-9");
        execute("update  CSM_PROTECTION_GROUP set PROTECTION_GROUP_NAME='gov.nih.nci.cabig.caaers.domain.Organization.DCP' where PROTECTION_GROUP_ID =-10");
        execute("update  CSM_PROTECTION_GROUP set PROTECTION_GROUP_NAME='gov.nih.nci.cabig.caaers.domain.Organization.CTEP' where PROTECTION_GROUP_ID =-11");




  	}


	void down(){
	   
	}


}