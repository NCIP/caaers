class OrganizationCodes extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	

      //"American College of Surgeons Oncology Trials Group",nci_institute_code:"ACOSOG"      
      insert('csm_group',[GROUP_ID: -7932,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ACOSOG",GROUP_DESC:"ACOSOG group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7932,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ACOSOG",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ACOSOG",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7932,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ACOSOG", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8932 ,protection_group_id: -7932, protection_element_id:-7932], primaryKey: false);
      

	//Cancer and Leukemia Group B,nci_institute_code:"CALGB"      
      insert('csm_group',[GROUP_ID: -7933,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CALGB",GROUP_DESC:"CALGB group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7933,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CALGB",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CALGB",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7933,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CALGB", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8933 ,protection_group_id: -7933, protection_element_id:-7933], primaryKey: false);


	//"Eastern Cooperative Oncology Group",nci_institute_code:"ECOG", 
      insert('csm_group',[GROUP_ID: -7934,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ECOG",GROUP_DESC:"ECOG group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7934,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ECOG",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ECOG",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7934,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ECOG", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8934 ,protection_group_id: -7934, protection_element_id:-7934], primaryKey: false);


//"Gynecologic Oncology Group",nci_institute_code:"GOG"       
      insert('csm_group',[GROUP_ID: -7935,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GOG",GROUP_DESC:"GOG group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7935,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GOG",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GOG",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7935,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GOG", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8935 ,protection_group_id: -7935, protection_element_id:-7935], primaryKey: false);


//"North Central Cancer Treatment Group",nci_institute_code:"NCCTG"  
      insert('csm_group',[GROUP_ID: -7936,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCCTG",GROUP_DESC:"NCCTG group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7936,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCCTG",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCCTG",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7936,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCCTG", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8936 ,protection_group_id: -7936, protection_element_id:-7936], primaryKey: false);


      
	//"National Cancer Institute of Canada Clinical Trials Group",nci_institute_code:"NCIC"
      insert('csm_group',[GROUP_ID: -7937,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIC",GROUP_DESC:"NCIC group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7937,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCIC",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCIC",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7937,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIC", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8937 ,protection_group_id: -7937, protection_element_id:-7937], primaryKey: false);


	//"National Surgical Adjuvant Breast and Bowel Project",nci_institute_code:"NSABP",
      insert('csm_group',[GROUP_ID: -7938,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NSABP",GROUP_DESC:"NSABP group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7938,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NSABP",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NSABP",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7938,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NSABP", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8938 ,protection_group_id: -7938, protection_element_id:-7938], primaryKey: false);



	//"Southwest Oncology Group",nci_institute_code:"SWOG",    
      insert('csm_group',[GROUP_ID: -7939,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SWOG",GROUP_DESC:"SWOG group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7939,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SWOG",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SWOG",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7939,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SWOG", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8939 ,protection_group_id: -7939, protection_element_id:-7939], primaryKey: false);


	//"Children's Oncology Group",nci_institute_code:"COG",
      insert('csm_group',[GROUP_ID: -7940,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.COG",GROUP_DESC:"COG group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7940,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.COG",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.COG",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7940,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.COG", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8940 ,protection_group_id: -7940, protection_element_id:-7940], primaryKey: false);



	//"Radiation Therapy Oncology Group",nci_institute_code:"RTOG",     
      insert('csm_group',[GROUP_ID: -7941,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RTOG",GROUP_DESC:"RTOG group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7941,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.RTOG",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.RTOG",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7941,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RTOG", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8941 ,protection_group_id: -7941, protection_element_id:-7941], primaryKey: false);

      
    }

    void down() {
        execute("delete from csm_pg_pe where pg_pe_id < -8931 and  pg_pe_id <= -8941");
        execute("delete from CSM_PROTECTION_GROUP where protection_group_id <= -7932 and protection_group_id >= -7941");
        execute("delete from csm_protection_element where protection_element_id <= -7932 and protection_element_id >= -7941");
        execute("delete from csm_group where group_id <= -7932 and group_id >= -7941");
        
    }
}
