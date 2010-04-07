class AddOrganizationProtectionElement extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {

	  if (databaseMatches('postgresql')){
			execute("insert into csm_protection_element (protection_element_id,protection_element_name,object_id,application_id) values (-7942,'gov.nih.nci.cabig.caaers.domain.Organization','gov.nih.nci.cabig.caaers.domain.Organization',-1)")
			execute("insert into csm_pg_pe (pg_pe_id,protection_group_id,protection_element_id) values ((select nextval('csm_pg_pe_id_seq')),-5,-7942)")
		}else if(databaseMatches('oracle')){
			execute("insert into csm_protection_element (protection_element_id,protection_element_name,object_id,application_id) values (-7942,'gov.nih.nci.cabig.caaers.domain.Organization','gov.nih.nci.cabig.caaers.domain.Organization',-1)")
			execute("insert into csm_pg_pe (pg_pe_id,protection_group_id,protection_element_id) values (CSM_PG_PE_PG_PE_ID_SEQ.nextval,-5,-7942)")
		}		 
    }

    void down(){
        execute("delete from csm_protection_element where protection_element_id = -7942");
    }
}