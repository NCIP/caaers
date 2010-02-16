class InsertDummyOrg extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		if (databaseMatches('postgresql')){
			execute("insert into organizations (id,name,version,nci_institute_code) values (-1111,'Dummy Organization',0,'DUMMY')")
			execute("insert into csm_group (group_id,group_name,group_desc,application_id) values ((select nextval('csm_group_group_id_seq')),'gov.nih.nci.cabig.caaers.domain.Organization.DUMMY','DUMMY.group',-1)")
			execute("insert into csm_protection_element (protection_element_id,protection_element_name,object_id,application_id) values ((select nextval('csm_protectio_protection_e_seq')),'gov.nih.nci.cabig.caaers.domain.Organization.DUMMY','gov.nih.nci.cabig.caaers.domain.Organization.DUMMY',-1)")
			execute("insert into csm_protection_group (protection_group_id,protection_group_name,application_id,parent_protection_group_id,large_element_count_flag) values ((select nextval('csm_protectio_protection_g_seq')),'gov.nih.nci.cabig.caaers.domain.Organization.DUMMY',-1,-5,0)")
			execute("insert into csm_pg_pe (pg_pe_id,protection_group_id,protection_element_id) values ((select nextval('csm_pg_pe_id_seq')),(select currval('csm_protectio_protection_g_seq')),(select currval('csm_protectio_protection_e_seq')))")
		}else if(databaseMatches('oracle')){
			execute("insert into organizations (id,name,version,nci_institute_code) values (-1111,'Dummy Organization',0,'DUMMY')")
			execute("insert into csm_group (group_id,group_name,group_desc,application_id) values (CSM_GROUP_GROUP_ID_SEQ.nextval,'gov.nih.nci.cabig.caaers.domain.Organization.DUMMY','DUMMY.group',-1)")
			execute("insert into csm_protection_element (protection_element_id,protection_element_name,object_id,application_id) values (CSM_PROTECTIO_PROTECTION_E_SEQ.nextval,'gov.nih.nci.cabig.caaers.domain.Organization.DUMMY','gov.nih.nci.cabig.caaers.domain.Organization.DUMMY',-1)")
			execute("insert into csm_protection_group (protection_group_id,protection_group_name,application_id,parent_protection_group_id,large_element_count_flag) values (CSM_PROTECTIO_PROTECTION_G_SEQ.nextval,'gov.nih.nci.cabig.caaers.domain.Organization.DUMMY',-1,-5,0)")
			execute("insert into csm_pg_pe (pg_pe_id,protection_group_id,protection_element_id) values (CSM_PG_PE_PG_PE_ID_SEQ.nextval,CSM_PROTECTIO_PROTECTION_G_SEQ.currval,CSM_PROTECTIO_PROTECTION_E_SEQ.currval)")
		}
	}

	void down() {
		//nothing here
	}
}