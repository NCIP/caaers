class CreateRolePrivilegeTable extends edu.northwestern.bioinformatics.bering.Migration {
	
    void up() {
 		createTable("role_privilege") { t ->
            t.addVersionColumn()
            t.addColumn("role_name", "string", nullable:false)
            t.addColumn("object_id", "string", nullable:false)
            t.addColumn("privilege", "string", nullable:false)
        }
        
        insert("role_privilege", [ version: '0', role_name : 	 'system_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.Admin',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'system_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.Task',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'system_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'system_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'system_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'system_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'system_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Organization',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'system_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'system_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'system_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'system_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'system_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'system_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'system_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'system_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'system_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'CREATE']);
		
		insert("role_privilege", [ version: '0', role_name : 	 'business_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.Admin',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'business_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.Task',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'business_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'business_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'business_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'business_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'business_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Organization',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'business_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'business_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'business_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'business_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'business_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'business_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'business_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'business_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'business_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'CREATE']);
		
		insert("role_privilege", [ version: '0', role_name : 	 'person_and_organization_information_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.Admin',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'person_and_organization_information_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.Task',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'person_and_organization_information_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'person_and_organization_information_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'person_and_organization_information_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'person_and_organization_information_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'person_and_organization_information_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Organization',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'person_and_organization_information_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'person_and_organization_information_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'person_and_organization_information_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'person_and_organization_information_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'person_and_organization_information_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'person_and_organization_information_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'person_and_organization_information_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'person_and_organization_information_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'person_and_organization_information_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'CREATE']);
		
		
		insert("role_privilege", [ version: '0', role_name : 	 'data_importer',	object_id : 	 'gov.nih.nci.cabig.caaers.Admin',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_importer',	object_id : 	 'gov.nih.nci.cabig.caaers.Task',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_importer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_importer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_importer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_importer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_importer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Organization',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_importer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_importer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_importer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_importer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_importer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_importer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_importer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_importer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_importer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'CREATE']);
		
		
		insert("role_privilege", [ version: '0', role_name : 	 'user_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.Admin',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'user_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.Task',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'user_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'user_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'user_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'user_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'user_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Organization',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'user_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'user_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'user_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'user_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'user_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'user_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'user_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'user_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'user_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'CREATE']);
		
		
		insert("role_privilege", [ version: '0', role_name : 	 'study_qa_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.Admin',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_qa_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.Task',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_qa_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_qa_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_qa_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_qa_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_qa_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Organization',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_qa_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_qa_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_qa_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_qa_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_qa_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_qa_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_qa_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_qa_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_qa_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'CREATE']);
		
		
		insert("role_privilege", [ version: '0', role_name : 	 'study_creator',	object_id : 	 'gov.nih.nci.cabig.caaers.Admin',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_creator',	object_id : 	 'gov.nih.nci.cabig.caaers.Task',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_creator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_creator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_creator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_creator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_creator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Organization',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_creator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_creator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_creator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_creator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_creator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_creator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_creator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_creator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_creator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'CREATE']);
		
		
		insert("role_privilege", [ version: '0', role_name : 	 'supplemental_study_information_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.Admin',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'supplemental_study_information_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.Task',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'supplemental_study_information_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'supplemental_study_information_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'supplemental_study_information_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'supplemental_study_information_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'supplemental_study_information_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Organization',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'supplemental_study_information_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'supplemental_study_information_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'supplemental_study_information_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'supplemental_study_information_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'supplemental_study_information_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'supplemental_study_information_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'supplemental_study_information_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'supplemental_study_information_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'supplemental_study_information_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'CREATE']);
		
		
		insert("role_privilege", [ version: '0', role_name : 	 'study_team_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.Admin',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_team_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.Task',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_team_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_team_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_team_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_team_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_team_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Organization',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_team_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_team_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_team_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_team_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_team_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_team_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_team_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_team_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_team_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'CREATE']);
		
		
		insert("role_privilege", [ version: '0', role_name : 	 'study_site_participation_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.Admin',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_site_participation_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.Task',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_site_participation_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_site_participation_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_site_participation_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_site_participation_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_site_participation_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Organization',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_site_participation_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_site_participation_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_site_participation_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_site_participation_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_site_participation_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_site_participation_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_site_participation_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_site_participation_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_site_participation_administrator',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'CREATE']);
		
		
		insert("role_privilege", [ version: '0', role_name : 	 'ae_rule_and_report_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.Admin',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_rule_and_report_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.Task',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_rule_and_report_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_rule_and_report_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_rule_and_report_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_rule_and_report_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_rule_and_report_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Organization',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_rule_and_report_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_rule_and_report_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_rule_and_report_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_rule_and_report_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_rule_and_report_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_rule_and_report_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_rule_and_report_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_rule_and_report_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_rule_and_report_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'CREATE']);
		
		
		insert("role_privilege", [ version: '0', role_name : 	 'study_calendar_template_builder',	object_id : 	 'gov.nih.nci.cabig.caaers.Admin',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_calendar_template_builder',	object_id : 	 'gov.nih.nci.cabig.caaers.Task',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_calendar_template_builder',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_calendar_template_builder',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_calendar_template_builder',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_calendar_template_builder',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_calendar_template_builder',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Organization',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_calendar_template_builder',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_calendar_template_builder',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_calendar_template_builder',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_calendar_template_builder',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_calendar_template_builder',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_calendar_template_builder',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_calendar_template_builder',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_calendar_template_builder',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_calendar_template_builder',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'CREATE']);
		
		
		insert("role_privilege", [ version: '0', role_name : 	 'registration_qa_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.Admin',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'registration_qa_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.Task',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'registration_qa_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'registration_qa_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'registration_qa_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'registration_qa_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'registration_qa_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Organization',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'registration_qa_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'registration_qa_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'registration_qa_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'registration_qa_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'registration_qa_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'registration_qa_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'registration_qa_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'registration_qa_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'registration_qa_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'CREATE']);
		
		
		insert("role_privilege", [ version: '0', role_name : 	 'subject_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.Admin',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'subject_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.Task',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'subject_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'subject_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'subject_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'subject_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'subject_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Organization',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'subject_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'subject_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'subject_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'subject_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'subject_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'subject_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'subject_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'subject_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'subject_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'CREATE']);
		
		insert("role_privilege", [ version: '0', role_name : 	 'study_subject_calendar_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.Admin',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_subject_calendar_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.Task',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_subject_calendar_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_subject_calendar_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_subject_calendar_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_subject_calendar_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_subject_calendar_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Organization',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_subject_calendar_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_subject_calendar_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_subject_calendar_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_subject_calendar_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_subject_calendar_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_subject_calendar_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_subject_calendar_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_subject_calendar_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'study_subject_calendar_manager',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'CREATE']);
		
		insert("role_privilege", [ version: '0', role_name : 	 'registrar',	object_id : 	 'gov.nih.nci.cabig.caaers.Admin',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'registrar',	object_id : 	 'gov.nih.nci.cabig.caaers.Task',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'registrar',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'registrar',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'registrar',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'registrar',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'registrar',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Organization',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'registrar',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'registrar',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'registrar',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'registrar',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'registrar',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'registrar',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'registrar',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'registrar',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'registrar',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'CREATE']);
		
		insert("role_privilege", [ version: '0', role_name : 	 'ae_reporter',	object_id : 	 'gov.nih.nci.cabig.caaers.Admin',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_reporter',	object_id : 	 'gov.nih.nci.cabig.caaers.Task',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_reporter',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_reporter',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_reporter',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_reporter',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_reporter',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Organization',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_reporter',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_reporter',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_reporter',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_reporter',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_reporter',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_reporter',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_reporter',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_reporter',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_reporter',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'CREATE']);
		
		insert("role_privilege", [ version: '0', role_name : 	 'ae_expedited_report_reviewer',	object_id : 	 'gov.nih.nci.cabig.caaers.Admin',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_expedited_report_reviewer',	object_id : 	 'gov.nih.nci.cabig.caaers.Task',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_expedited_report_reviewer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_expedited_report_reviewer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_expedited_report_reviewer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_expedited_report_reviewer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_expedited_report_reviewer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Organization',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_expedited_report_reviewer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_expedited_report_reviewer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_expedited_report_reviewer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_expedited_report_reviewer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_expedited_report_reviewer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_expedited_report_reviewer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_expedited_report_reviewer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_expedited_report_reviewer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_expedited_report_reviewer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'CREATE']);
		
		insert("role_privilege", [ version: '0', role_name : 	 'ae_study_data_reviewer',	object_id : 	 'gov.nih.nci.cabig.caaers.Admin',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_study_data_reviewer',	object_id : 	 'gov.nih.nci.cabig.caaers.Task',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_study_data_reviewer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_study_data_reviewer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_study_data_reviewer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_study_data_reviewer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_study_data_reviewer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Organization',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_study_data_reviewer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_study_data_reviewer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_study_data_reviewer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_study_data_reviewer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_study_data_reviewer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_study_data_reviewer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_study_data_reviewer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_study_data_reviewer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'ae_study_data_reviewer',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'CREATE']);
		
		
		insert("role_privilege", [ version: '0', role_name : 	 'lab_impact_calendar_notifier',	object_id : 	 'gov.nih.nci.cabig.caaers.Admin',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'lab_impact_calendar_notifier',	object_id : 	 'gov.nih.nci.cabig.caaers.Task',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'lab_impact_calendar_notifier',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'lab_impact_calendar_notifier',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'lab_impact_calendar_notifier',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'lab_impact_calendar_notifier',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'lab_impact_calendar_notifier',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Organization',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'lab_impact_calendar_notifier',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'lab_impact_calendar_notifier',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'lab_impact_calendar_notifier',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'lab_impact_calendar_notifier',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'lab_impact_calendar_notifier',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'lab_impact_calendar_notifier',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'lab_impact_calendar_notifier',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'lab_impact_calendar_notifier',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'lab_impact_calendar_notifier',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'CREATE']);
		
		
		insert("role_privilege", [ version: '0', role_name : 	 'lab_data_user',	object_id : 	 'gov.nih.nci.cabig.caaers.Admin',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'lab_data_user',	object_id : 	 'gov.nih.nci.cabig.caaers.Task',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'lab_data_user',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'lab_data_user',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'lab_data_user',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'lab_data_user',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'lab_data_user',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Organization',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'lab_data_user',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'lab_data_user',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'lab_data_user',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'lab_data_user',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'lab_data_user',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'lab_data_user',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'lab_data_user',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'lab_data_user',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'lab_data_user',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'CREATE']);
		
		
		insert("role_privilege", [ version: '0', role_name : 	 'data_reader',	object_id : 	 'gov.nih.nci.cabig.caaers.Admin',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_reader',	object_id : 	 'gov.nih.nci.cabig.caaers.Task',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_reader',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_reader',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_reader',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_reader',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_reader',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Organization',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_reader',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_reader',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_reader',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_reader',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_reader',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_reader',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_reader',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_reader',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_reader',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'CREATE']);
		
		
		insert("role_privilege", [ version: '0', role_name : 	 'data_analyst',	object_id : 	 'gov.nih.nci.cabig.caaers.Admin',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_analyst',	object_id : 	 'gov.nih.nci.cabig.caaers.Task',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_analyst',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_analyst',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_analyst',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_analyst',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_analyst',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Organization',	 privilege :	 'ACCESS']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_analyst',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_analyst',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_analyst',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Participant',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_analyst',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_analyst',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'CREATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_analyst',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Rule',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_analyst',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'READ']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_analyst',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'UPDATE']);
		insert("role_privilege", [ version: '0', role_name : 	 'data_analyst',	object_id : 	 'gov.nih.nci.cabig.caaers.domain.Study',	 privilege :	 'CREATE']);
        
    }

    void down() {
    }
}