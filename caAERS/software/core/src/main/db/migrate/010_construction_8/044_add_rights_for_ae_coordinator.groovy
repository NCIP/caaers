
        class UpdateCsmPrivileges extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){



// Gives caaers_ae_coordinator the
		// gov.nih.nci.cabig.caaers.domain.Study.READ role on
		// gov.nih.nci.cabig.caaers.domain.Study protection group
		insert(
			'csm_user_group_role_pg',
			[
				user_group_role_pg_id: -56,
				group_id: -13, role_id: -3, protection_group_id: -1
			],
			primaryKey: false
		)


	}
	void down(){
		execute("DELETE FROM csm_user_group_role_pg WHERE user_group_role_pg_id=-56");
		}
}

