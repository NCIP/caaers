class AddOrganizationProtectionElement extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {

	  insert('csm_protection_element',
	  		 [protection_element_id: -7942, protection_element_name: 'gov.nih.nci.cabig.caaers.domain.Organization', object_id: 'gov.nih.nci.cabig.caaers.domain.Organization', application_id: -1],
	  		 primaryKey: false);
	  insert('csm_pg_pe',
	  		 [pg_pe_id: 9012, protection_group_id: -5, protection_element_id: -7942],
	  		 primaryKey: false);
    }

    void down(){
        execute("delete from csm_protection_element where protection_element_id = -7942");
        execute("delete from csm_pg_pe where pg_pe_id = 9012");
    }
}