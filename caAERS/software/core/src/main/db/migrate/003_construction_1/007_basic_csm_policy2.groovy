class BasicCsmPolicy2 extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		insert('csm_group', [ group_id: -3, group_name: 'caaers_super_user',  application_id: -1 ], primaryKey: false)
		insert('csm_group', [ group_id: -4, group_name: 'caaers_study_cd',  application_id: -1 ], primaryKey: false)
		insert('csm_group', [ group_id: -5, group_name: 'caaers_participant_cd',  application_id: -1 ], primaryKey: false)
	}
	void down(){
	
	}
}