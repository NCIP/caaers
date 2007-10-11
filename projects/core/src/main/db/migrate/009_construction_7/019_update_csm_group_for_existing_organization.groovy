class UpdateCsmGroupForExisintOrganization extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {
        insert('csm_group',[GROUP_ID: -6,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DEFAULT",GROUP_DESC: "Default organization Group",application_id: -1], primaryKey: false);
        insert('csm_protection_element',[protection_element_id: -6,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DEFAULT",
            object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DEFAULT",application_id: -1], primaryKey: false);
        insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DEFAULT", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
        insert('csm_pg_pe',[pg_pe_id: 5,protection_group_id: -6, protection_element_id:-6], primaryKey: false);

        insert('csm_group',[GROUP_ID: -7,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCI",GROUP_DESC: "NCI Group",application_id: -1], primaryKey: false);
        insert('csm_protection_element',[protection_element_id: -7,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCI", object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCI",application_id: -1], primaryKey: false);
        insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCI", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
        insert('csm_pg_pe',[pg_pe_id: 6,protection_group_id: -7, protection_element_id:-7], primaryKey: false);

        
        insert('csm_group',[GROUP_ID: -8,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WAKE ",GROUP_DESC: "Wake university group",application_id: -1], primaryKey: false);
        insert('csm_protection_element',[protection_element_id: -8,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WAKE", object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WAKE",application_id: -1], primaryKey: false);
        insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WAKE", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
        insert('csm_pg_pe',[pg_pe_id: 7,protection_group_id: -8, protection_element_id:-8], primaryKey: false);

        insert('csm_group',[GROUP_ID: -9,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DUKE",GROUP_DESC: "Duke university group",application_id: -1], primaryKey: false);
        insert('csm_protection_element',[protection_element_id: -9,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DUKE", object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DUKE",application_id: -1], primaryKey: false);
        insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -9,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DUKE", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
        insert('csm_pg_pe',[pg_pe_id: 8,protection_group_id: -9, protection_element_id:-9], primaryKey: false);


        insert('csm_group',[GROUP_ID: -10,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DCP",GROUP_DESC: "DCP Group",application_id: -1], primaryKey: false);
        insert('csm_protection_element',[protection_element_id: -10,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DCP", object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DCP",application_id: -1], primaryKey: false);
        insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -10,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DCP", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
        insert('csm_pg_pe',[pg_pe_id: 9,protection_group_id: -10, protection_element_id:-10], primaryKey: false);

        insert('csm_group',[GROUP_ID: -11,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CTEP",GROUP_DESC: "CTEP Group",application_id: -1], primaryKey: false);
        insert('csm_protection_element',[protection_element_id: -11,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CTEP", object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CTEP",application_id: -1], primaryKey: false);
        insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -11,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CTEP", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
        insert('csm_pg_pe',[pg_pe_id: 10,protection_group_id: -11, protection_element_id:-11], primaryKey: false);

        insert('csm_group',[GROUP_ID: -12,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization",GROUP_DESC: "Organization Group",application_id: -1], primaryKey: false);

        insert('csm_user_group_role_pg',[USER_GROUP_ROLE_PG_ID: -25,GROUP_ID: -12, PROTECTION_GROUP_ID: -5, ROLE_ID: -14], primaryKey: false);
    }

    void down(){
        execute("delete from csm_pg_pe where pg_pe_id IN (6,7,8,9,10,11)");
        execute("delete from CSM_PROTECTION_GROUP where protection_group_id IN (-6,-7,-8,-9,-10,-11)");
        execute("delete from csm_protection_element where protection_element_id IN (-6,-7,-8,-9,-10,-11,-12)");
        execute("delete from csm_group where group_id IN (-6,-7,-8,-9,-10,-11,-12)");
        execute("delete from csm_user_group_role_pg where USER_GROUP_ROLE_PG_ID IN (-25)");
    }
}