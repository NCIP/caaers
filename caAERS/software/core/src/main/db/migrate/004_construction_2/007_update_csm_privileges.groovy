class UpdateCsmPrivileges extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){

		execute("DELETE FROM csm_user_group_role_pg");
		execute("DELETE FROM csm_pg_pe");
		execute("DELETE FROM csm_role_privilege");
		execute("DELETE FROM csm_role");
		execute("DELETE FROM csm_protection_element");
		execute("DELETE FROM csm_protection_group");
		
		
		///////////////////////
		// PROTECTION GROUPS //
		///////////////////////
		
		insert(
			'csm_protection_group',
			[
				protection_group_id: -1,
				protection_group_name: 'gov.nih.nci.cabig.caaers.domain.Study',
				protection_group_description: 'Study Extent Protection Group',
				application_id: -1, large_element_count_flag: 0
			],
			primaryKey: false
		)
		insert(
			'csm_protection_group',
			[
				protection_group_id: -2,
				protection_group_name: 'gov.nih.nci.cabig.caaers.domain.Participant',
				protection_group_description: 'Participant Extent Protection Group',
				application_id: -1, large_element_count_flag: 0
			],
			primaryKey: false
		)
		insert(
			'csm_protection_group',
			[
				protection_group_id: -3,
				protection_group_name: 'gov.nih.nci.cabig.caaers.domain.AdverseEventReport',
				protection_group_description: 'AdverseEventReport Extent Protection Group',
				application_id: -1, large_element_count_flag: 0
			],
			primaryKey: false
		)
		insert(
			'csm_protection_group',
			[
				protection_group_id: -4,
				protection_group_name: 'gov.nih.nci.cabig.caaers.domain.Rule',
				protection_group_description: 'Rule Extent Protection Group',
				application_id: -1, large_element_count_flag: 0
			],
			primaryKey: false
		)
		
		/////////////////////////
		// PROTECTION ELEMENTS //
		/////////////////////////
		insert(
			'csm_protection_element',
			[
				protection_element_id: -1,
				protection_element_name: 'caaers',
				protection_element_description: 'caAERS Application',
				object_id: 'caaers', attribute: '',
				application_id: -1
			],
			primaryKey: false
		)
		insert(
			'csm_protection_element',
			[
				protection_element_id: -2,
				protection_element_name: 'gov.nih.nci.cabig.caaers.domain.Study',
				protection_element_description: 'Study Extent Protection Element',
				object_id: 'gov.nih.nci.cabig.caaers.domain.Study', 
				attribute: '',
				application_id: -1
			],
			primaryKey: false
		)
		insert(
			'csm_protection_element',
			[
				protection_element_id: -3,
				protection_element_name: 'gov.nih.nci.cabig.caaers.domain.Participant',
				protection_element_description: 'Participant Extent Protection Element',
				object_id: 'gov.nih.nci.cabig.caaers.domain.Participant', 
				attribute: '',
				application_id: -1
			],
			primaryKey: false
		)
		insert(
			'csm_protection_element',
			[
				protection_element_id: -4,
				protection_element_name: 'gov.nih.nci.cabig.caaers.domain.AdverseEventReport',
				protection_element_description: 'AdverseEventReport Extent Protection Element',
				object_id: 'gov.nih.nci.cabig.caaers.domain.AdverseEventReport', 
				attribute: '',
				application_id: -1
			],
			primaryKey: false
		)
		insert(
			'csm_protection_element',
			[
				protection_element_id: -5,
				protection_element_name: 'gov.nih.nci.cabig.caaers.domain.Rule',
				protection_element_description: 'Rule Extent Protection Element',
				object_id: 'gov.nih.nci.cabig.caaers.domain.Rule', 
				attribute: '',
				application_id: -1
			],
			primaryKey: false
		)
		
		/////////////////////////////////////////////////////
		// PROTECTION_GROUP TO PROTECTION_ELEMENT MAPPINGS //
		/////////////////////////////////////////////////////
		
		// Maps gov.nih.nci.cabig.caaers.domain.Study Extent Protection Group to 
		// gov.nih.nci.cabig.caaers.domain.Study Extent Protection Element
		insert(
			'csm_pg_pe',
			[
				protection_group_id: -1,
				protection_element_id: -2,
			],
			primaryKey: false
		)
		
		// Maps gov.nih.nci.cabig.caaers.domain.Participant Extent Protection Group to 
		// gov.nih.nci.cabig.caaers.domain.Participant Extent Protection Element
		insert(
			'csm_pg_pe',
			[
				protection_group_id: -2,
				protection_element_id: -3,
			],
			primaryKey: false
		)
		
		// Maps gov.nih.nci.cabig.caaers.domain.AdverseEventReport Extent Protection Group to 
		// gov.nih.nci.cabig.caaers.domain.AdverseEventReport Extent Protection Element
		insert(
			'csm_pg_pe',
			[
				protection_group_id: -3,
				protection_element_id: -4,
			],
			primaryKey: false
		)
		
		// Maps gov.nih.nci.cabig.caaers.domain.Rule Extent Protection Group to 
		// gov.nih.nci.cabig.caaers.domain.Rule Extent Protection Element
		insert(
			'csm_pg_pe',
			[
				protection_group_id: -4,
				protection_element_id: -5,
			],
			primaryKey: false
		)
		
		///////////
		// ROLES //
		///////////
		insert(
			'csm_role',
			[
				role_id: -1, role_name: 'gov.nih.nci.cabig.caaers.domain.Study.CREATE',
				role_description: '', application_id: -1, active_flag: 1
			],
			primaryKey: false
		)
		insert(
			'csm_role',
			[
				role_id: -2, role_name: 'gov.nih.nci.cabig.caaers.domain.Study.UPDATE',
				role_description: '', application_id: -1, active_flag: 1
			],
			primaryKey: false
		)
		insert(
			'csm_role',
			[
				role_id: -3, role_name: 'gov.nih.nci.cabig.caaers.domain.Study.READ',
				role_description: '', application_id: -1, active_flag: 1
			],
			primaryKey: false
		)
		insert(
			'csm_role',
			[
				role_id: -4, role_name: 'gov.nih.nci.cabig.caaers.domain.Participant.CREATE',
				role_description: '', application_id: -1, active_flag: 1
			],
			primaryKey: false
		)
		insert(
			'csm_role',
			[
				role_id: -5, role_name: 'gov.nih.nci.cabig.caaers.domain.Participant.UPDATE',
				role_description: '', application_id: -1, active_flag: 1
			],
			primaryKey: false
		)
		insert(
			'csm_role',
			[
				role_id: -6, role_name: 'gov.nih.nci.cabig.caaers.domain.Participant.READ',
				role_description: '', application_id: -1, active_flag: 1
			],
			primaryKey: false
		)
		
		insert(
			'csm_role',
			[
				role_id: -7, role_name: 'gov.nih.nci.cabig.caaers.domain.AdverseEventReport.CREATE',
				role_description: '', application_id: -1, active_flag: 1
			],
			primaryKey: false
		)
		insert(
			'csm_role',
			[
				role_id: -8, role_name: 'gov.nih.nci.cabig.caaers.domain.AdverseEventReport.UPDATE',
				role_description: '', application_id: -1, active_flag: 1
			],
			primaryKey: false
		)
		insert(
			'csm_role',
			[
				role_id: -9, role_name: 'gov.nih.nci.cabig.caaers.domain.AdverseEventReport.READ',
				role_description: '', application_id: -1, active_flag: 1
			],
			primaryKey: false
		)
		insert(
			'csm_role',
			[
				role_id: -10, role_name: 'gov.nih.nci.cabig.caaers.domain.Rule.CREATE',
				role_description: '', application_id: -1, active_flag: 1
			],
			primaryKey: false
		)
		insert(
			'csm_role',
			[
				role_id: -11, role_name: 'gov.nih.nci.cabig.caaers.domain.Rule.UPDATE',
				role_description: '', application_id: -1, active_flag: 1
			],
			primaryKey: false
		)
		insert(
			'csm_role',
			[
				role_id: -12, role_name: 'gov.nih.nci.cabig.caaers.domain.Rule.READ',
				role_description: '', application_id: -1, active_flag: 1
			],
			primaryKey: false
		)
		
		////////////////////////////////
		// ROLE TO PRIVILEGE MAPPINGS //
		////////////////////////////////
		
		// Maps gov.nih.nci.cabig.caaers.domain.Study.CREATE to CREATE
		insert(
			'csm_role_privilege',
			[
				role_id: -1, privilege_id: -1
			],
			primaryKey: false
		)
		
		// Maps gov.nih.nci.cabig.caaers.domain.Study.UPDATE to UPDATE
		insert(
			'csm_role_privilege',
			[
				role_id: -2, privilege_id: -5
			],
			primaryKey: false
		)
		
		// Maps gov.nih.nci.cabig.caaers.domain.Study.READ to READ
		insert(
			'csm_role_privilege',
			[
				role_id: -3, privilege_id: -3
			],
			primaryKey: false
		)
		
		
		// Maps gov.nih.nci.cabig.caaers.domain.Participant.CREATE to CREATE
		insert(
			'csm_role_privilege',
			[
				role_id: -4, privilege_id: -1
			],
			primaryKey: false
		)
		
		// Maps gov.nih.nci.cabig.caaers.domain.Participant.UPDATE to UPDATE
		insert(
			'csm_role_privilege',
			[
				role_id: -5, privilege_id: -5
			],
			primaryKey: false
		)
		
		// Maps gov.nih.nci.cabig.caaers.domain.Participant.READ to READ
		insert(
			'csm_role_privilege',
			[
				role_id: -6, privilege_id: -3
			],
			primaryKey: false
		)
		
		
		// Maps gov.nih.nci.cabig.caaers.domain.AdverseEventReport.CREATE to CREATE
		insert(
			'csm_role_privilege',
			[
				role_id: -7, privilege_id: -1
			],
			primaryKey: false
		)
		
		// Maps gov.nih.nci.cabig.caaers.domain.AdverseEventReport.UPDATE to UPDATE
		insert(
			'csm_role_privilege',
			[
				role_id: -8, privilege_id: -5
			],
			primaryKey: false
		)
		
		// Maps gov.nih.nci.cabig.caaers.domain.AdverseEventReport.READ to READ
		insert(
			'csm_role_privilege',
			[
				role_id: -9, privilege_id: -3
			],
			primaryKey: false
		)
		
		// Maps gov.nih.nci.cabig.caaers.domain.Rule.CREATE to CREATE
		insert(
			'csm_role_privilege',
			[
				role_id: -10, privilege_id: -1
			],
			primaryKey: false
		)
		
		// Maps gov.nih.nci.cabig.caaers.domain.Rule.UPDATE to UPDATE
		insert(
			'csm_role_privilege',
			[
				role_id: -11, privilege_id: -5
			],
			primaryKey: false
		)
		
		// Maps gov.nih.nci.cabig.caaers.domain.Rule.READ to READ
		insert(
			'csm_role_privilege',
			[
				role_id: -12, privilege_id: -3
			],
			primaryKey: false
		)
		
		////////////////////////////////
		// GRANT PRIVILEGES TO GROUPS //
		////////////////////////////////
		
		//== gov.nih.nci.cabig.caaers.domain.Study ==//
		
		// Gives caaers_study_cd group the 
		// gov.nih.nci.cabig.caaers.domain.Study.CREATE role on 
		// gov.nih.nci.cabig.caaers.domain.Study protection group
		insert(
			'csm_user_group_role_pg',
			[
				user_group_role_pg_id: -1,
				group_id: -4, role_id: -1, protection_group_id: -1
			],
			primaryKey: false
		)
		// Gives caaers_study_cd group the 
		// gov.nih.nci.cabig.caaers.domain.Study.UPDATE role on 
		// gov.nih.nci.cabig.caaers.domain.Study protection group
		insert(
			'csm_user_group_role_pg',
			[
				user_group_role_pg_id: -2,
				group_id: -4, role_id: -2, protection_group_id: -1
			],
			primaryKey: false
		)
		// Gives caaers_study_cd group the 
		// gov.nih.nci.cabig.caaers.domain.Study.READ role on 
		// gov.nih.nci.cabig.caaers.domain.Study protection group
		insert(
			'csm_user_group_role_pg',
			[
				user_group_role_pg_id: -3,
				group_id: -4, role_id: -3, protection_group_id: -1
			],
			primaryKey: false
		)

		
		// Gives caaers_super_user group the 
		// gov.nih.nci.cabig.caaers.domain.Study.CREATE role on 
		// gov.nih.nci.cabig.caaers.domain.Study protection group
		insert(
			'csm_user_group_role_pg',
			[
				user_group_role_pg_id: -4,
				group_id: -3, role_id: -1, protection_group_id: -1
			],
			primaryKey: false
		)
		// Gives caaers_super_user group the 
		// gov.nih.nci.cabig.caaers.domain.Study.UPDATE role on 
		// gov.nih.nci.cabig.caaers.domain.Study protection group
		insert(
			'csm_user_group_role_pg',
			[
				user_group_role_pg_id: -5,
				group_id: -3, role_id: -2, protection_group_id: -1
			],
			primaryKey: false
		)
		// Gives caaers_super_user group the 
		// gov.nih.nci.cabig.caaers.domain.Study.READ role on 
		// gov.nih.nci.cabig.caaers.domain.Study protection group
		insert(
			'csm_user_group_role_pg',
			[
				user_group_role_pg_id: -6,
				group_id: -3, role_id: -3, protection_group_id: -1
			],
			primaryKey: false
		)
		

		// Gives caaers_user group the 
		// gov.nih.nci.cabig.caaers.domain.Study.READ role on 
		// gov.nih.nci.cabig.caaers.domain.Study protection group
		insert(
			'csm_user_group_role_pg',
			[
				user_group_role_pg_id: -7,
				group_id: -2, role_id: -3, protection_group_id: -1
			],
			primaryKey: false
		)
		
		
		//== gov.nih.nci.cabig.caaers.domain.Participant ==//
		
		// Gives caaers_participant_cd group the 
		// gov.nih.nci.cabig.caaers.domain.Participant.CREATE role on 
		// gov.nih.nci.cabig.caaers.domain.Participant protection group
		insert(
			'csm_user_group_role_pg',
			[
				user_group_role_pg_id: -8,
				group_id: -5, role_id: -4, protection_group_id: -2
			],
			primaryKey: false
		)
		// Gives caaers_participant_cd group the 
		// gov.nih.nci.cabig.caaers.domain.Participant.UPDATE role on 
		// gov.nih.nci.cabig.caaers.domain.Participant protection group
		insert(
			'csm_user_group_role_pg',
			[
				user_group_role_pg_id: -9,
				group_id: -5, role_id: -5, protection_group_id: -2
			],
			primaryKey: false
		)
		// Gives caaers_participant_cd group the 
		// gov.nih.nci.cabig.caaers.domain.Participant.READ role on 
		// gov.nih.nci.cabig.caaers.domain.Participant protection group
		insert(
			'csm_user_group_role_pg',
			[
				user_group_role_pg_id: -10,
				group_id: -5, role_id: -6, protection_group_id: -2
			],
			primaryKey: false
		)
		
		// Gives caaers_super_user group the 
		// gov.nih.nci.cabig.caaers.domain.Participant.CREATE role on 
		// gov.nih.nci.cabig.caaers.domain.Participant protection group
		insert(
			'csm_user_group_role_pg',
			[
				user_group_role_pg_id: -11,
				group_id: -3, role_id: -4, protection_group_id: -2
			],
			primaryKey: false
		)
		// Gives caaers_super_user group the 
		// gov.nih.nci.cabig.caaers.domain.Participant.UPDATE role on 
		// gov.nih.nci.cabig.caaers.domain.Participant protection group
		insert(
			'csm_user_group_role_pg',
			[
				user_group_role_pg_id: -12,
				group_id: -3, role_id: -5, protection_group_id: -2
			],
			primaryKey: false
		)
		// Gives caaers_super_user group the 
		// gov.nih.nci.cabig.caaers.domain.Participant.READ role on 
		// gov.nih.nci.cabig.caaers.domain.Participant protection group
		insert(
			'csm_user_group_role_pg',
			[
				user_group_role_pg_id: -13,
				group_id: -3, role_id: -6, protection_group_id: -2
			],
			primaryKey: false
		)
		
		// Gives caaers_user group the 
		// gov.nih.nci.cabig.caaers.domain.Participant.READ role on 
		// gov.nih.nci.cabig.caaers.domain.Participant protection group
		insert(
			'csm_user_group_role_pg',
			[
				user_group_role_pg_id: -14,
				group_id: -2, role_id: -6, protection_group_id: -2
			],
			primaryKey: false
		)
		
		//== gov.nih.nci.cabig.caaers.domain.AdverseEvent ==//
		
		// Gives caaers_participant_cd group the 
		// gov.nih.nci.cabig.caaers.domain.AdverseEventReport.CREATE role on 
		// gov.nih.nci.cabig.caaers.domain.AdverseEventReport protection group
		insert(
			'csm_user_group_role_pg',
			[
				user_group_role_pg_id: -15,
				group_id: -5, role_id: -7, protection_group_id: -3
			],
			primaryKey: false
		)
		// Gives caaers_participant_cd group the 
		// gov.nih.nci.cabig.caaers.domain.AdverseEventReport.UPDATE role on 
		// gov.nih.nci.cabig.caaers.domain.AdverseEventReport protection group
		insert(
			'csm_user_group_role_pg',
			[
				user_group_role_pg_id: -16,
				group_id: -5, role_id: -8, protection_group_id: -3
			],
			primaryKey: false
		)
		// Gives caaers_participant_cd group the 
		// gov.nih.nci.cabig.caaers.domain.AdverseEventReport.READ role on 
		// gov.nih.nci.cabig.caaers.domain.AdverseEventReport protection group
		insert(
			'csm_user_group_role_pg',
			[
				user_group_role_pg_id: -17,
				group_id: -5, role_id: -9, protection_group_id: -3
			],
			primaryKey: false
		)
		
		// Gives caaers_super_user group the 
		// gov.nih.nci.cabig.caaers.domain.AdverseEventReport.CREATE role on 
		// gov.nih.nci.cabig.caaers.domain.AdverseEventReport protection group
		insert(
			'csm_user_group_role_pg',
			[
				user_group_role_pg_id: -18,
				group_id: -3, role_id: -7, protection_group_id: -3
			],
			primaryKey: false
		)
		// Gives caaers_super_user group the 
		// gov.nih.nci.cabig.caaers.domain.AdverseEventReport.UPDATE role on 
		// gov.nih.nci.cabig.caaers.domain.AdverseEventReport protection group
		insert(
			'csm_user_group_role_pg',
			[
				user_group_role_pg_id: -19,
				group_id: -3, role_id: -8, protection_group_id: -3
			],
			primaryKey: false
		)
		// Gives caaers_super_user group the 
		// gov.nih.nci.cabig.caaers.domain.AdverseEventReport.READ role on 
		// gov.nih.nci.cabig.caaers.domain.AdverseEventReport protection group
		insert(
			'csm_user_group_role_pg',
			[
				user_group_role_pg_id: -20,
				group_id: -3, role_id: -9, protection_group_id: -3
			],
			primaryKey: false
		)
		
		// Gives caaers_user group the 
		// gov.nih.nci.cabig.caaers.domain.AdverseEventReport.READ role on 
		// gov.nih.nci.cabig.caaers.domain.AdverseEventReport protection group
		insert(
			'csm_user_group_role_pg',
			[
				user_group_role_pg_id: -21,
				group_id: -2, role_id: -9, protection_group_id: -3
			],
			primaryKey: false
		)
		
		
		//== gov.nih.nci.cabig.caaers.domain.Rule ==//
		
		// Gives caaers_user group the 
		// gov.nih.nci.cabig.caaers.domain.Rule.CREATE role on 
		// gov.nih.nci.cabig.caaers.domain.Rule protection group
		insert(
			'csm_user_group_role_pg',
			[
				user_group_role_pg_id: -22,
				group_id: -3, role_id: -10, protection_group_id: -4
			],
			primaryKey: false
		)
		// Gives caaers_user group the 
		// gov.nih.nci.cabig.caaers.domain.Rule.UPDATE role on 
		// gov.nih.nci.cabig.caaers.domain.Rule protection group
		insert(
			'csm_user_group_role_pg',
			[
				user_group_role_pg_id: -23,
				group_id: -3, role_id: -11, protection_group_id: -4
			],
			primaryKey: false
		)
		// Gives caaers_user group the 
		// gov.nih.nci.cabig.caaers.domain.Rule.READ role on 
		// gov.nih.nci.cabig.caaers.domain.Rule protection group
		insert(
			'csm_user_group_role_pg',
			[
				user_group_role_pg_id: -24,
				group_id: -3, role_id: -12, protection_group_id: -4
			],
			primaryKey: false
		)
		
	}
	void down(){
		execute("DELETE FROM csm_user_group_role_pg");
		execute("DELETE FROM csm_pg_pe");
		execute("DELETE FROM csm_role_privilege");
		execute("DELETE FROM csm_role");
		execute("DELETE FROM csm_protection_element");
		execute("DELETE FROM csm_protection_group");
	}
}