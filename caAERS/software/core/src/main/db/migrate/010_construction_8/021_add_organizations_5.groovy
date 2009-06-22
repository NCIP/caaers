class OrganizationCodes extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        if (!databaseMatches("hsql")) {
	// Have to break up the inserts so as not to exceed the java max method length
        m0()
        m1()
        m2()
        m3()
        m4()
        m5()
        m6()
        m7()
        m8()
        m9()
        m10()
        m11()
        m12()
        m13()
        m14()
        m15()
        m16()
        m17()
        m18()
        m19()
        m20()
        m21()
        m22()
        m23()
        m24()
        m25()
        m26()
        m27()
        m28()
        m29()
        m30()
        m31()
        m32()
        m33()
        m34()
        m35()
        m36()
        m37()
        m38()
        m39()
        m40()
	}
    }

    void m0() {
        // all0 (25 terms)
      insert('organizations', [ id: 103998, nci_institute_code: "MA089", name: "Brockton - West Roxbury", city: "West Roxbury", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4013,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA089",GROUP_DESC:"MA089 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4013,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA089",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA089",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4013,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA089", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5013 ,protection_group_id: -4013, protection_element_id:-4013], primaryKey: false);
      insert('organizations', [ id: 103999, nci_institute_code: "MA090", name: "Tuff University-New England Medical Center", city: "Winchester", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4014,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA090",GROUP_DESC:"MA090 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4014,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA090",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA090",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4014,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA090", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5014 ,protection_group_id: -4014, protection_element_id:-4014], primaryKey: false);
      insert('organizations', [ id: 104000, nci_institute_code: "MA091", name: "Cooley-Dickenson", city: "Northampton", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4015,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA091",GROUP_DESC:"MA091 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4015,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA091",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA091",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4015,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA091", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5015 ,protection_group_id: -4015, protection_element_id:-4015], primaryKey: false);
      insert('organizations', [ id: 104001, nci_institute_code: "MA092", name: "Milford Regional Medical Center", city: "Milford", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4016,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA092",GROUP_DESC:"MA092 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4016,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA092",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA092",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4016,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA092", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5016 ,protection_group_id: -4016, protection_element_id:-4016], primaryKey: false);
      insert('organizations', [ id: 104002, nci_institute_code: "MA093", name: "North Shore Medical Center Cancer Center", city: "Peabody", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4017,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA093",GROUP_DESC:"MA093 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4017,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA093",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA093",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4017,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA093", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5017 ,protection_group_id: -4017, protection_element_id:-4017], primaryKey: false);
      insert('organizations', [ id: 104003, nci_institute_code: "MA094", name: "Metro-West Medical Center", city: "Framingham", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4018,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA094",GROUP_DESC:"MA094 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4018,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA094",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA094",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4018,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA094", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5018 ,protection_group_id: -4018, protection_element_id:-4018], primaryKey: false);
      insert('organizations', [ id: 104004, nci_institute_code: "MA095", name: "Franklin Medical Center", city: "Greenfield", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4019,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA095",GROUP_DESC:"MA095 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4019,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA095",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA095",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4019,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA095", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5019 ,protection_group_id: -4019, protection_element_id:-4019], primaryKey: false);
      insert('organizations', [ id: 104005, nci_institute_code: "MA096", name: "Winchester Hospital", city: "Winchester", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4020,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA096",GROUP_DESC:"MA096 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4020,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA096",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA096",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4020,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA096", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5020 ,protection_group_id: -4020, protection_element_id:-4020], primaryKey: false);
      insert('organizations', [ id: 104006, nci_institute_code: "MA098", name: "Saint Vincent Hospital - Fallon Clinic", city: "Worcesster", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4021,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA098",GROUP_DESC:"MA098 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4021,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA098",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA098",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4021,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA098", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5021 ,protection_group_id: -4021, protection_element_id:-4021], primaryKey: false);
      insert('organizations', [ id: 104007, nci_institute_code: "MA099", name: "North Adams Regional Hospital", city: "North Adams", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4022,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA099",GROUP_DESC:"MA099 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4022,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA099",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA099",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4022,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA099", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5022 ,protection_group_id: -4022, protection_element_id:-4022], primaryKey: false);
      insert('organizations', [ id: 104008, nci_institute_code: "MA100", name: "Emerson Hospital", city: "Concord", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4023,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA100",GROUP_DESC:"MA100 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4023,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA100",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA100",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4023,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA100", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5023 ,protection_group_id: -4023, protection_element_id:-4023], primaryKey: false);
      insert('organizations', [ id: 104009, nci_institute_code: "MA101", name: "Oncology  & Hematology Assoc., Inc", city: "Methuen", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4024,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA101",GROUP_DESC:"MA101 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4024,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA101",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA101",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4024,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA101", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5024 ,protection_group_id: -4024, protection_element_id:-4024], primaryKey: false);
      insert('organizations', [ id: 104010, nci_institute_code: "MA102", name: "Springfield Medical Associates Incorporated", city: "Springfield", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4025,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA102",GROUP_DESC:"MA102 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4025,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA102",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA102",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4025,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA102", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5025 ,protection_group_id: -4025, protection_element_id:-4025], primaryKey: false);
      insert('organizations', [ id: 104011, nci_institute_code: "MA103", name: "Northampton Internal Associates PC", city: "Northampton", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4026,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA103",GROUP_DESC:"MA103 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4026,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA103",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA103",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4026,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA103", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5026 ,protection_group_id: -4026, protection_element_id:-4026], primaryKey: false);
      insert('organizations', [ id: 104012, nci_institute_code: "MA105", name: "New England Hematology/Oncology Associates", city: "Wellesley", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4027,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA105",GROUP_DESC:"MA105 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4027,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA105",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA105",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4027,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA105", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5027 ,protection_group_id: -4027, protection_element_id:-4027], primaryKey: false);
      insert('organizations', [ id: 104013, nci_institute_code: "MA106", name: "Baystate Urologist", city: "Brighton", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4028,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA106",GROUP_DESC:"MA106 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4028,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA106",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA106",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4028,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA106", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5028 ,protection_group_id: -4028, protection_element_id:-4028], primaryKey: false);
      insert('organizations', [ id: 104014, nci_institute_code: "MA107", name: "Joint Center for Radiation Therapy", city: "Boston", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4029,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA107",GROUP_DESC:"MA107 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4029,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA107",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA107",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4029,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA107", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5029 ,protection_group_id: -4029, protection_element_id:-4029], primaryKey: false);
      insert('organizations', [ id: 104015, nci_institute_code: "MA108", name: "Commonwealth Hematology Oncology PC-Quincy", city: "Quincy", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4030,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA108",GROUP_DESC:"MA108 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4030,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA108",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA108",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4030,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA108", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5030 ,protection_group_id: -4030, protection_element_id:-4030], primaryKey: false);
      insert('organizations', [ id: 104016, nci_institute_code: "MA109", name: "The Cancer Center of Boston At Plymouth", city: "Plymouth", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4031,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA109",GROUP_DESC:"MA109 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4031,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA109",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA109",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4031,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA109", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5031 ,protection_group_id: -4031, protection_element_id:-4031], primaryKey: false);
      insert('organizations', [ id: 104017, nci_institute_code: "MA110", name: "Holy Family Hospital", city: "Methuen", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4032,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA110",GROUP_DESC:"MA110 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4032,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA110",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA110",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4032,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA110", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5032 ,protection_group_id: -4032, protection_element_id:-4032], primaryKey: false);
      insert('organizations', [ id: 104018, nci_institute_code: "MA111", name: "Metrowest Medical Center, Incorporated", city: "Natick", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4033,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA111",GROUP_DESC:"MA111 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4033,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA111",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA111",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4033,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA111", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5033 ,protection_group_id: -4033, protection_element_id:-4033], primaryKey: false);
      insert('organizations', [ id: 104019, nci_institute_code: "MA112", name: "Joint Center for Radiology /Oncology", city: "North Dartmouth", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4034,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA112",GROUP_DESC:"MA112 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4034,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA112",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA112",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4034,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA112", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5034 ,protection_group_id: -4034, protection_element_id:-4034], primaryKey: false);
      insert('organizations', [ id: 104020, nci_institute_code: "MA113", name: "Medical West Oncology", city: "Chicopee", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4035,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA113",GROUP_DESC:"MA113 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4035,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA113",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA113",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4035,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA113", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5035 ,protection_group_id: -4035, protection_element_id:-4035], primaryKey: false);
      insert('organizations', [ id: 104021, nci_institute_code: "MA114", name: "Noble Hospital", city: "Westfield", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4036,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA114",GROUP_DESC:"MA114 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4036,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA114",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA114",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4036,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA114", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5036 ,protection_group_id: -4036, protection_element_id:-4036], primaryKey: false);
      insert('organizations', [ id: 104022, nci_institute_code: "MA115", name: "South Suburban Oncology Center", city: "Quincy", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4037,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA115",GROUP_DESC:"MA115 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4037,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA115",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA115",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4037,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA115", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5037 ,protection_group_id: -4037, protection_element_id:-4037], primaryKey: false);
    }

    void m1() {
        // all1 (25 terms)
      insert('organizations', [ id: 104023, nci_institute_code: "MA116", name: "Columbia Metrowest Medical Center", city: "Framingham", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4038,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA116",GROUP_DESC:"MA116 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4038,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA116",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA116",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4038,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA116", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5038 ,protection_group_id: -4038, protection_element_id:-4038], primaryKey: false);
      insert('organizations', [ id: 104024, nci_institute_code: "MA117", name: "CHEM Center for Radiation Oncology", city: "Stoneham", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4039,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA117",GROUP_DESC:"MA117 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4039,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA117",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA117",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4039,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA117", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5039 ,protection_group_id: -4039, protection_element_id:-4039], primaryKey: false);
      insert('organizations', [ id: 104025, nci_institute_code: "MA118", name: "Memorial Health Care", city: "Worchester", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4040,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA118",GROUP_DESC:"MA118 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4040,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA118",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA118",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4040,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA118", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5040 ,protection_group_id: -4040, protection_element_id:-4040], primaryKey: false);
      insert('organizations', [ id: 104026, nci_institute_code: "MA119", name: "Nashoba Valley Medical Center", city: "Ayer", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4041,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA119",GROUP_DESC:"MA119 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4041,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA119",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA119",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4041,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA119", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5041 ,protection_group_id: -4041, protection_element_id:-4041], primaryKey: false);
      insert('organizations', [ id: 104027, nci_institute_code: "MA121", name: "Commonwealth Hematology Oncology PC-Weymouth", city: "Weymouth", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4042,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA121",GROUP_DESC:"MA121 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4042,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA121",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA121",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4042,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA121", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5042 ,protection_group_id: -4042, protection_element_id:-4042], primaryKey: false);
      insert('organizations', [ id: 104028, nci_institute_code: "MA122", name: "Goddard Memorial Hospital", city: "Stoughton", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4043,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA122",GROUP_DESC:"MA122 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4043,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA122",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA122",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4043,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA122", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5043 ,protection_group_id: -4043, protection_element_id:-4043], primaryKey: false);
      insert('organizations', [ id: 104029, nci_institute_code: "MA123", name: "Caritas Norwood Hospital", city: "Norwood", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4044,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA123",GROUP_DESC:"MA123 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4044,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA123",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA123",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4044,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA123", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5044 ,protection_group_id: -4044, protection_element_id:-4044], primaryKey: false);
      insert('organizations', [ id: 104030, nci_institute_code: "MA124", name: "Lahey Clinic North Shore", city: "Peabody", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4045,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA124",GROUP_DESC:"MA124 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4045,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA124",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA124",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4045,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA124", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5045 ,protection_group_id: -4045, protection_element_id:-4045], primaryKey: false);
      insert('organizations', [ id: 104031, nci_institute_code: "MA125", name: "Berkshire Hematology Oncology, PC", city: "Pittsfield", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4046,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA125",GROUP_DESC:"MA125 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4046,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA125",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA125",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4046,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA125", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5046 ,protection_group_id: -4046, protection_element_id:-4046], primaryKey: false);
      insert('organizations', [ id: 104032, nci_institute_code: "MA126", name: "Chicopee Medical Center", city: "Chicopee", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4047,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA126",GROUP_DESC:"MA126 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4047,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA126",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA126",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4047,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA126", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5047 ,protection_group_id: -4047, protection_element_id:-4047], primaryKey: false);
      insert('organizations', [ id: 104033, nci_institute_code: "MA127", name: "River Bend Medical Group", city: "Chicopee", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4048,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA127",GROUP_DESC:"MA127 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4048,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA127",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA127",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4048,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA127", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5048 ,protection_group_id: -4048, protection_element_id:-4048], primaryKey: false);
      insert('organizations', [ id: 104034, nci_institute_code: "MA128", name: "Eileen Barrett Cancer Center-Berkshire Hematology Oncology PC", city: "North Adams", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4049,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA128",GROUP_DESC:"MA128 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4049,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA128",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA128",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4049,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA128", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5049 ,protection_group_id: -4049, protection_element_id:-4049], primaryKey: false);
      insert('organizations', [ id: 104035, nci_institute_code: "MA129", name: "Commonwealth Hematology Oncology PC-Worcester", city: "Worcester", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4050,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA129",GROUP_DESC:"MA129 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4050,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA129",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA129",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4050,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA129", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5050 ,protection_group_id: -4050, protection_element_id:-4050], primaryKey: false);
      insert('organizations', [ id: 104036, nci_institute_code: "MA130", name: "Falmouth Hospital", city: "Falmouth", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4051,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA130",GROUP_DESC:"MA130 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4051,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA130",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA130",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4051,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA130", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5051 ,protection_group_id: -4051, protection_element_id:-4051], primaryKey: false);
      insert('organizations', [ id: 104037, nci_institute_code: "MA131", name: "Falmouth Hospital Oncology Center", city: "Mashpee", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4052,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA131",GROUP_DESC:"MA131 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4052,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA131",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA131",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4052,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA131", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5052 ,protection_group_id: -4052, protection_element_id:-4052], primaryKey: false);
      insert('organizations', [ id: 104038, nci_institute_code: "MA132", name: "Simonds-Sinon Regional Cancer Center", city: "Fitchburg", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4053,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA132",GROUP_DESC:"MA132 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4053,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA132",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA132",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4053,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA132", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5053 ,protection_group_id: -4053, protection_element_id:-4053], primaryKey: false);
      insert('organizations', [ id: 104039, nci_institute_code: "MA134", name: "Lowell General Hospital", city: "Lowell", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4054,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA134",GROUP_DESC:"MA134 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4054,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA134",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA134",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4054,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA134", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5054 ,protection_group_id: -4054, protection_element_id:-4054], primaryKey: false);
      insert('organizations', [ id: 104040, nci_institute_code: "MA135", name: "East Boston Neighborhood Health Center", city: "East Boston", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4055,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA135",GROUP_DESC:"MA135 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4055,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA135",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA135",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4055,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA135", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5055 ,protection_group_id: -4055, protection_element_id:-4055], primaryKey: false);
      insert('organizations', [ id: 104041, nci_institute_code: "MA136", name: "Boston University School of Medicine", city: "Boston", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4056,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA136",GROUP_DESC:"MA136 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4056,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA136",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA136",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4056,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA136", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5056 ,protection_group_id: -4056, protection_element_id:-4056], primaryKey: false);
      insert('organizations', [ id: 104042, nci_institute_code: "MA137", name: "Fallon Clinic", city: "Worcester", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4057,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA137",GROUP_DESC:"MA137 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4057,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA137",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA137",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4057,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA137", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5057 ,protection_group_id: -4057, protection_element_id:-4057], primaryKey: false);
      insert('organizations', [ id: 104043, nci_institute_code: "MA138", name: "Quest Diagnostics", city: "Florence", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4058,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA138",GROUP_DESC:"MA138 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4058,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA138",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA138",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4058,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA138", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5058 ,protection_group_id: -4058, protection_element_id:-4058], primaryKey: false);
      insert('organizations', [ id: 104044, nci_institute_code: "MA139", name: "VA Boston Healthcare System", city: "Lowell", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4059,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA139",GROUP_DESC:"MA139 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4059,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA139",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA139",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4059,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA139", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5059 ,protection_group_id: -4059, protection_element_id:-4059], primaryKey: false);
      insert('organizations', [ id: 104045, nci_institute_code: "MA140", name: "Plymouth Ears Nose & Throat", city: "Plymouth", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4060,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA140",GROUP_DESC:"MA140 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4060,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA140",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA140",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4060,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA140", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5060 ,protection_group_id: -4060, protection_element_id:-4060], primaryKey: false);
      insert('organizations', [ id: 104046, nci_institute_code: "MA141", name: "Harbor Medical Associates", city: "South Weymouth", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4061,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA141",GROUP_DESC:"MA141 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4061,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA141",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA141",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4061,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA141", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5061 ,protection_group_id: -4061, protection_element_id:-4061], primaryKey: false);
      insert('organizations', [ id: 104047, nci_institute_code: "MA143", name: "Commonwealth Hematology Oncology PC-Stoneham", city: "Stoneham", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4062,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA143",GROUP_DESC:"MA143 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4062,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA143",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA143",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4062,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA143", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5062 ,protection_group_id: -4062, protection_element_id:-4062], primaryKey: false);
    }

    void m2() {
        // all2 (25 terms)
      insert('organizations', [ id: 104048, nci_institute_code: "MA144", name: "Center for Molecular Imaging Research", city: "Charlestown", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4063,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA144",GROUP_DESC:"MA144 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4063,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA144",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA144",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4063,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA144", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5063 ,protection_group_id: -4063, protection_element_id:-4063], primaryKey: false);
      insert('organizations', [ id: 104049, nci_institute_code: "MA145", name: "D'Amour Center for Cancer Care", city: "Springfield", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4064,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA145",GROUP_DESC:"MA145 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4064,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA145",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA145",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4064,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA145", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5064 ,protection_group_id: -4064, protection_element_id:-4064], primaryKey: false);
      insert('organizations', [ id: 104050, nci_institute_code: "MA146", name: "The Cancer Center of Boston", city: "Chestnut Hill", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4065,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA146",GROUP_DESC:"MA146 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4065,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA146",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA146",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4065,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA146", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5065 ,protection_group_id: -4065, protection_element_id:-4065], primaryKey: false);
      insert('organizations', [ id: 104051, nci_institute_code: "MA147", name: "Tufts University", city: "Medford", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4066,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA147",GROUP_DESC:"MA147 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4066,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA147",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA147",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4066,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA147", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5066 ,protection_group_id: -4066, protection_element_id:-4066], primaryKey: false);
      insert('organizations', [ id: 104052, nci_institute_code: "MA148", name: "Massachusetts General Hospital", city: "Charlestown", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4067,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA148",GROUP_DESC:"MA148 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4067,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA148",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA148",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4067,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA148", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5067 ,protection_group_id: -4067, protection_element_id:-4067], primaryKey: false);
      insert('organizations', [ id: 104053, nci_institute_code: "MA149", name: "Massachusetts Eye and Ear Infirmary", city: "Boston", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4068,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA149",GROUP_DESC:"MA149 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4068,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA149",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA149",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4068,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA149", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5068 ,protection_group_id: -4068, protection_element_id:-4068], primaryKey: false);
      insert('organizations', [ id: 104054, nci_institute_code: "MA150", name: "Hampden County Physicians Associates", city: "Springfield", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4069,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA150",GROUP_DESC:"MA150 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4069,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA150",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA150",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4069,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA150", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5069 ,protection_group_id: -4069, protection_element_id:-4069], primaryKey: false);
      insert('organizations', [ id: 104055, nci_institute_code: "MA151", name: "Hampden County Physicians Office", city: "Springfield", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4070,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA151",GROUP_DESC:"MA151 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4070,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA151",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA151",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4070,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA151", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5070 ,protection_group_id: -4070, protection_element_id:-4070], primaryKey: false);
      insert('organizations', [ id: 104056, nci_institute_code: "MA152", name: "Newton Wellesley Urology", city: "Newton", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4071,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA152",GROUP_DESC:"MA152 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4071,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA152",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA152",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4071,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA152", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5071 ,protection_group_id: -4071, protection_element_id:-4071], primaryKey: false);
      insert('organizations', [ id: 104057, nci_institute_code: "MA153", name: "Commonwealth Hematology Oncology PC-Lawrence", city: "Lawrence", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4072,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA153",GROUP_DESC:"MA153 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4072,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA153",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA153",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4072,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA153", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5072 ,protection_group_id: -4072, protection_element_id:-4072], primaryKey: false);
      insert('organizations', [ id: 104058, nci_institute_code: "MA154", name: "Caritas Good Samaritan Medical Center", city: "Brockton", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4073,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA154",GROUP_DESC:"MA154 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4073,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA154",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA154",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4073,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA154", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5073 ,protection_group_id: -4073, protection_element_id:-4073], primaryKey: false);
      insert('organizations', [ id: 104059, nci_institute_code: "MA155", name: "Mercy Medical Center", city: "Springfield", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4074,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA155",GROUP_DESC:"MA155 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4074,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA155",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA155",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4074,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA155", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5074 ,protection_group_id: -4074, protection_element_id:-4074], primaryKey: false);
      insert('organizations', [ id: 104060, nci_institute_code: "MA156", name: "Clinquest Inc", city: "Hudson", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4075,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA156",GROUP_DESC:"MA156 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4075,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA156",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA156",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4075,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA156", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5075 ,protection_group_id: -4075, protection_element_id:-4075], primaryKey: false);
      insert('organizations', [ id: 104061, nci_institute_code: "MA157", name: "Medical Oncology and Hematology PC", city: "Brockton", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4076,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA157",GROUP_DESC:"MA157 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4076,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA157",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA157",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4076,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA157", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5076 ,protection_group_id: -4076, protection_element_id:-4076], primaryKey: false);
      insert('organizations', [ id: 104062, nci_institute_code: "MA158", name: "Harvard Medical International", city: "Boston", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4077,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA158",GROUP_DESC:"MA158 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4077,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA158",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA158",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4077,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA158", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5077 ,protection_group_id: -4077, protection_element_id:-4077], primaryKey: false);
      insert('organizations', [ id: 104063, nci_institute_code: "MA159", name: "Medical Specialists of Taunton PC", city: "Taunton", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4078,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA159",GROUP_DESC:"MA159 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4078,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA159",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA159",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4078,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA159", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5078 ,protection_group_id: -4078, protection_element_id:-4078], primaryKey: false);
      insert('organizations', [ id: 104064, nci_institute_code: "MA160", name: "Mount Auburn Hematology Oncology Associates", city: "Cambridge", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4079,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA160",GROUP_DESC:"MA160 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4079,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA160",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA160",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4079,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA160", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5079 ,protection_group_id: -4079, protection_element_id:-4079], primaryKey: false);
      insert('organizations', [ id: 104065, nci_institute_code: "MA161", name: "Science and Technology Policy Institute IDA", city: "Cambridge", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4080,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA161",GROUP_DESC:"MA161 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4080,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA161",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA161",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4080,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA161", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5080 ,protection_group_id: -4080, protection_element_id:-4080], primaryKey: false);
      insert('organizations', [ id: 104066, nci_institute_code: "MA162", name: "Lahey Arlington", city: "Arlington", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4081,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA162",GROUP_DESC:"MA162 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4081,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA162",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA162",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4081,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA162", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5081 ,protection_group_id: -4081, protection_element_id:-4081], primaryKey: false);
      insert('organizations', [ id: 104067, nci_institute_code: "MA163", name: "Murray and Glynn PC", city: "Springfield", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4082,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA163",GROUP_DESC:"MA163 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4082,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA163",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA163",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4082,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA163", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5082 ,protection_group_id: -4082, protection_element_id:-4082], primaryKey: false);
      insert('organizations', [ id: 104068, nci_institute_code: "MA164", name: "Mcbrine and Vasconcellos MDs PC", city: "Mansfield", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4083,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA164",GROUP_DESC:"MA164 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4083,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA164",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA164",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4083,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA164", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5083 ,protection_group_id: -4083, protection_element_id:-4083], primaryKey: false);
      insert('organizations', [ id: 104069, nci_institute_code: "MA165", name: "Commonwealth Hematology Oncology PC-Dorchester", city: "Dorchester", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4084,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA165",GROUP_DESC:"MA165 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4084,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA165",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA165",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4084,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA165", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5084 ,protection_group_id: -4084, protection_element_id:-4084], primaryKey: false);
      insert('organizations', [ id: 104070, nci_institute_code: "MA166", name: "Commonwealth Hematology Oncology PC-Haverhill", city: "Haverhill", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4085,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA166",GROUP_DESC:"MA166 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4085,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA166",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA166",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4085,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA166", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5085 ,protection_group_id: -4085, protection_element_id:-4085], primaryKey: false);
      insert('organizations', [ id: 104071, nci_institute_code: "MA167", name: "Commonwealth Hematology Oncology PC-Leominster", city: "Leominster", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4086,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA167",GROUP_DESC:"MA167 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4086,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA167",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA167",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4086,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA167", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5086 ,protection_group_id: -4086, protection_element_id:-4086], primaryKey: false);
      insert('organizations', [ id: 104072, nci_institute_code: "MA168", name: "Commonwealth Hematology Oncology PC-Milton", city: "Milton", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4087,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA168",GROUP_DESC:"MA168 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4087,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA168",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA168",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4087,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA168", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5087 ,protection_group_id: -4087, protection_element_id:-4087], primaryKey: false);
    }

    void m3() {
        // all3 (25 terms)
      insert('organizations', [ id: 104073, nci_institute_code: "MA169", name: "Commonwealth Hematology Oncology PC-Concord", city: "Concord", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4088,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA169",GROUP_DESC:"MA169 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4088,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA169",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA169",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4088,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA169", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5088 ,protection_group_id: -4088, protection_element_id:-4088], primaryKey: false);
      insert('organizations', [ id: 104074, nci_institute_code: "MA170", name: "Whidden Memorial Hospital", city: "Everett", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4089,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA170",GROUP_DESC:"MA170 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4089,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA170",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA170",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4089,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA170", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5089 ,protection_group_id: -4089, protection_element_id:-4089], primaryKey: false);
      insert('organizations', [ id: 104075, nci_institute_code: "MA171", name: "Harvard School of Public Health", city: "Boston", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4090,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA171",GROUP_DESC:"MA171 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4090,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA171",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA171",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4090,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA171", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5090 ,protection_group_id: -4090, protection_element_id:-4090], primaryKey: false);
      insert('organizations', [ id: 104076, nci_institute_code: "MA172", name: "Winchester Hospital Radiation Oncology Center", city: "Winchester", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4091,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA172",GROUP_DESC:"MA172 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4091,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA172",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA172",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4091,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA172", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5091 ,protection_group_id: -4091, protection_element_id:-4091], primaryKey: false);
      insert('organizations', [ id: 104077, nci_institute_code: "MA173", name: "Shields Radiation Oncology Center- Mansfield", city: "Mansfield", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4092,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA173",GROUP_DESC:"MA173 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4092,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA173",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA173",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4092,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA173", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5092 ,protection_group_id: -4092, protection_element_id:-4092], primaryKey: false);
      insert('organizations', [ id: 104078, nci_institute_code: "MAOP", name: "Mid-Atlantic Oncology Program", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4093,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MAOP",GROUP_DESC:"MAOP group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4093,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MAOP",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MAOP",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4093,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MAOP", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5093 ,protection_group_id: -4093, protection_element_id:-4093], primaryKey: false);
      insert('organizations', [ id: 104079, nci_institute_code: "MARCOG", name: "Mid Atlantic Regional Cooperative Oncology Group", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4094,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MARCOG",GROUP_DESC:"MARCOG group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4094,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MARCOG",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MARCOG",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4094,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MARCOG", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5094 ,protection_group_id: -4094, protection_element_id:-4094], primaryKey: false);
      insert('organizations', [ id: 104080, nci_institute_code: "MD001", name: "National Naval Medical Center", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4095,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD001",GROUP_DESC:"MD001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4095,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4095,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5095 ,protection_group_id: -4095, protection_element_id:-4095], primaryKey: false);
      insert('organizations', [ id: 104081, nci_institute_code: "MD002", name: "BioReliance Corporation", city: "Rockville", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4096,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD002",GROUP_DESC:"MD002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4096,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4096,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5096 ,protection_group_id: -4096, protection_element_id:-4096], primaryKey: false);
      insert('organizations', [ id: 104082, nci_institute_code: "MD003", name: "Malcolm Grow, USAF Medical Center", city: "Camp Springs", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4097,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD003",GROUP_DESC:"MD003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4097,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4097,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5097 ,protection_group_id: -4097, protection_element_id:-4097], primaryKey: false);
      insert('organizations', [ id: 104083, nci_institute_code: "MD004", name: "National Institutes of Health", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4098,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD004",GROUP_DESC:"MD004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4098,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4098,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5098 ,protection_group_id: -4098, protection_element_id:-4098], primaryKey: false);
      insert('organizations', [ id: 104084, nci_institute_code: "MD005", name: "Southern Maryland Hospital Center", city: "Clinton", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4099,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD005",GROUP_DESC:"MD005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4099,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4099,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5099 ,protection_group_id: -4099, protection_element_id:-4099], primaryKey: false);
      insert('organizations', [ id: 104085, nci_institute_code: "MD007", name: "Suburban Hospital", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4100,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD007",GROUP_DESC:"MD007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4100,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4100,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5100 ,protection_group_id: -4100, protection_element_id:-4100], primaryKey: false);
      insert('organizations', [ id: 104086, nci_institute_code: "MD008", name: "Montgomery General Hospital., Olney", city: "Olney", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4101,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD008",GROUP_DESC:"MD008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4101,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4101,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5101 ,protection_group_id: -4101, protection_element_id:-4101], primaryKey: false);
      insert('organizations', [ id: 104087, nci_institute_code: "MD009", name: "Walter Reed Army Medical Center, Olney", city: "Olney", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4102,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD009",GROUP_DESC:"MD009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4102,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4102,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5102 ,protection_group_id: -4102, protection_element_id:-4102], primaryKey: false);
      insert('organizations', [ id: 104088, nci_institute_code: "MD010", name: "Food and Drug Administration", city: "Rockville", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4103,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD010",GROUP_DESC:"MD010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4103,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4103,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5103 ,protection_group_id: -4103, protection_element_id:-4103], primaryKey: false);
      insert('organizations', [ id: 104089, nci_institute_code: "MD011", name: "Holy Cross Hospital", city: "Silver Spring", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4104,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD011",GROUP_DESC:"MD011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4104,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4104,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5104 ,protection_group_id: -4104, protection_element_id:-4104], primaryKey: false);
      insert('organizations', [ id: 104090, nci_institute_code: "MD012", name: "Washington Adventist Hospital", city: "Takoma Park", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4105,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD012",GROUP_DESC:"MD012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4105,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4105,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5105 ,protection_group_id: -4105, protection_element_id:-4105], primaryKey: false);
      insert('organizations', [ id: 104091, nci_institute_code: "MD013", name: "Mercy Hospital, Lutherville", city: "Lutherville", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4106,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD013",GROUP_DESC:"MD013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4106,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4106,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5106 ,protection_group_id: -4106, protection_element_id:-4106], primaryKey: false);
      insert('organizations', [ id: 104092, nci_institute_code: "MD015", name: "University of Maryland Greenebaum Cancer Center", city: "Baltimore", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4107,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD015",GROUP_DESC:"MD015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4107,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4107,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5107 ,protection_group_id: -4107, protection_element_id:-4107], primaryKey: false);
      insert('organizations', [ id: 104093, nci_institute_code: "MD016", name: "Mercy Medical Center", city: "Baltimore", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4108,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD016",GROUP_DESC:"MD016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4108,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4108,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5108 ,protection_group_id: -4108, protection_element_id:-4108], primaryKey: false);
      insert('organizations', [ id: 104094, nci_institute_code: "MD017", name: "Johns Hopkins University", city: "Baltimore", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4109,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD017",GROUP_DESC:"MD017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4109,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4109,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5109 ,protection_group_id: -4109, protection_element_id:-4109], primaryKey: false);
      insert('organizations', [ id: 104095, nci_institute_code: "MD018", name: "Greater Baltimore Medical Center", city: "Baltimore", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4110,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD018",GROUP_DESC:"MD018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4110,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4110,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5110 ,protection_group_id: -4110, protection_element_id:-4110], primaryKey: false);
      insert('organizations', [ id: 104096, nci_institute_code: "MD019", name: "Saint Joseph Medical Center", city: "Towson", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4111,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD019",GROUP_DESC:"MD019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4111,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4111,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5111 ,protection_group_id: -4111, protection_element_id:-4111], primaryKey: false);
      insert('organizations', [ id: 104097, nci_institute_code: "MD020", name: "Sinai Hospital of Baltimore", city: "Baltimore", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4112,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD020",GROUP_DESC:"MD020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4112,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4112,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5112 ,protection_group_id: -4112, protection_element_id:-4112], primaryKey: false);
    }

    void m4() {
        // all4 (25 terms)
      insert('organizations', [ id: 104098, nci_institute_code: "MD021", name: "Lutheran Hospital of Maryland", city: "Blatimore", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4113,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD021",GROUP_DESC:"MD021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4113,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4113,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5113 ,protection_group_id: -4113, protection_element_id:-4113], primaryKey: false);
      insert('organizations', [ id: 104099, nci_institute_code: "MD022", name: "North Charles General Hospital", city: "Baltimore", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4114,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD022",GROUP_DESC:"MD022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4114,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4114,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5114 ,protection_group_id: -4114, protection_element_id:-4114], primaryKey: false);
      insert('organizations', [ id: 104100, nci_institute_code: "MD023", name: "The Union Memorial Hospital", city: "Baltimore", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4115,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD023",GROUP_DESC:"MD023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4115,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4115,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5115 ,protection_group_id: -4115, protection_element_id:-4115], primaryKey: false);
      insert('organizations', [ id: 104101, nci_institute_code: "MD024", name: "Veterans Administration Medical Center", city: "Baltimore", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4116,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD024",GROUP_DESC:"MD024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4116,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4116,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5116 ,protection_group_id: -4116, protection_element_id:-4116], primaryKey: false);
      insert('organizations', [ id: 104102, nci_institute_code: "MD025", name: "Baltimore City Hospital", city: "Baltimore", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4117,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD025",GROUP_DESC:"MD025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4117,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4117,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5117 ,protection_group_id: -4117, protection_element_id:-4117], primaryKey: false);
      insert('organizations', [ id: 104103, nci_institute_code: "MD026", name: "Francis Scott Key", city: "Baltimore", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4118,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD026",GROUP_DESC:"MD026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4118,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4118,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5118 ,protection_group_id: -4118, protection_element_id:-4118], primaryKey: false);
      insert('organizations', [ id: 104104, nci_institute_code: "MD027", name: "Saint Agnes Hospital", city: "Baltimore", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4119,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD027",GROUP_DESC:"MD027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4119,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4119,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5119 ,protection_group_id: -4119, protection_element_id:-4119], primaryKey: false);
      insert('organizations', [ id: 104105, nci_institute_code: "MD028", name: "South Baltimore General Hospital", city: "Baltimore", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4120,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD028",GROUP_DESC:"MD028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4120,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4120,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5120 ,protection_group_id: -4120, protection_element_id:-4120], primaryKey: false);
      insert('organizations', [ id: 104106, nci_institute_code: "MD029", name: "Franklin Square Hospital Center", city: "Baltimore", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4121,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD029",GROUP_DESC:"MD029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4121,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4121,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5121 ,protection_group_id: -4121, protection_element_id:-4121], primaryKey: false);
      insert('organizations', [ id: 104107, nci_institute_code: "MD030", name: "Good Samaritan Hospital of Maryland", city: "Baltimore", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4122,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD030",GROUP_DESC:"MD030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4122,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4122,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5122 ,protection_group_id: -4122, protection_element_id:-4122], primaryKey: false);
      insert('organizations', [ id: 104108, nci_institute_code: "MD031", name: "Western Maryland Health System - Braddock Campus", city: "Cumberland", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4123,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD031",GROUP_DESC:"MD031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4123,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4123,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5123 ,protection_group_id: -4123, protection_element_id:-4123], primaryKey: false);
      insert('organizations', [ id: 104109, nci_institute_code: "MD032", name: "Western Maryland Health System - Memorial Campus", city: "Cumberland", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4124,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD032",GROUP_DESC:"MD032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4124,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4124,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5124 ,protection_group_id: -4124, protection_element_id:-4124], primaryKey: false);
      insert('organizations', [ id: 104110, nci_institute_code: "MD033", name: "The Memorial Hospital at Easton", city: "Easton", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4125,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD033",GROUP_DESC:"MD033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4125,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4125,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5125 ,protection_group_id: -4125, protection_element_id:-4125], primaryKey: false);
      insert('organizations', [ id: 104111, nci_institute_code: "MD034", name: "Frederick Cancer Research/Development Center", city: "Frederick", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4126,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD034",GROUP_DESC:"MD034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4126,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4126,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5126 ,protection_group_id: -4126, protection_element_id:-4126], primaryKey: false);
      insert('organizations', [ id: 104112, nci_institute_code: "MD035", name: "Peninsula Regional Medical Center", city: "Salisbury", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4127,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD035",GROUP_DESC:"MD035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4127,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4127,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5127 ,protection_group_id: -4127, protection_element_id:-4127], primaryKey: false);
      insert('organizations', [ id: 104113, nci_institute_code: "MD036", name: "Frederick Memorial Hospital", city: "Frederick", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4128,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD036",GROUP_DESC:"MD036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4128,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4128,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5128 ,protection_group_id: -4128, protection_element_id:-4128], primaryKey: false);
      insert('organizations', [ id: 104114, nci_institute_code: "MD037", name: "Uniformed Services University of The Health Sciences", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4129,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD037",GROUP_DESC:"MD037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4129,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4129,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5129 ,protection_group_id: -4129, protection_element_id:-4129], primaryKey: false);
      insert('organizations', [ id: 104115, nci_institute_code: "MD038", name: "Kaiser Permanente - Kensington Medical Center", city: "Kensington", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4130,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD038",GROUP_DESC:"MD038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4130,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4130,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5130 ,protection_group_id: -4130, protection_element_id:-4130], primaryKey: false);
      insert('organizations', [ id: 104116, nci_institute_code: "MD039", name: "Harbor Hospital Center", city: "Baltimore", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4131,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD039",GROUP_DESC:"MD039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4131,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4131,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5131 ,protection_group_id: -4131, protection_element_id:-4131], primaryKey: false);
      insert('organizations', [ id: 104117, nci_institute_code: "MD042", name: "Anne Arundel Medical Center", city: "Annapolis", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4132,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD042",GROUP_DESC:"MD042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4132,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4132,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5132 ,protection_group_id: -4132, protection_element_id:-4132], primaryKey: false);
      insert('organizations', [ id: 104118, nci_institute_code: "MD043", name: "Hopkins Bayview Medical Center", city: "Baltimore", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4133,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD043",GROUP_DESC:"MD043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4133,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4133,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5133 ,protection_group_id: -4133, protection_element_id:-4133], primaryKey: false);
      insert('organizations', [ id: 104119, nci_institute_code: "MD044", name: "Central Maryland Oncology at Howard County Hospital", city: "Columbia", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4134,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD044",GROUP_DESC:"MD044 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4134,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD044",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD044",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4134,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD044", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5134 ,protection_group_id: -4134, protection_element_id:-4134], primaryKey: false);
      insert('organizations', [ id: 104120, nci_institute_code: "MD045", name: "Church Hospital", city: "Baltimore", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4135,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD045",GROUP_DESC:"MD045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4135,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4135,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5135 ,protection_group_id: -4135, protection_element_id:-4135], primaryKey: false);
      insert('organizations', [ id: 104121, nci_institute_code: "MD046", name: "Howard County General Hospital", city: "Columbia", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4136,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD046",GROUP_DESC:"MD046 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4136,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD046",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD046",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4136,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD046", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5136 ,protection_group_id: -4136, protection_element_id:-4136], primaryKey: false);
      insert('organizations', [ id: 104122, nci_institute_code: "MD047", name: "Union Hospital of Cecil County", city: "Elkton MD", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4137,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD047",GROUP_DESC:"MD047 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4137,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD047",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD047",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4137,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD047", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5137 ,protection_group_id: -4137, protection_element_id:-4137], primaryKey: false);
    }

    void m5() {
        // all5 (25 terms)
      insert('organizations', [ id: 104123, nci_institute_code: "MD048", name: "Washington County Hospital", city: "Hagerstown", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4138,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD048",GROUP_DESC:"MD048 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4138,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD048",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD048",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4138,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD048", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5138 ,protection_group_id: -4138, protection_element_id:-4138], primaryKey: false);
      insert('organizations', [ id: 104124, nci_institute_code: "MD049", name: "Annapolis Oncology Center", city: "Annapolis", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4139,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD049",GROUP_DESC:"MD049 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4139,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD049",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD049",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4139,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD049", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5139 ,protection_group_id: -4139, protection_element_id:-4139], primaryKey: false);
      insert('organizations', [ id: 104125, nci_institute_code: "MD050", name: "Oncology Hematology Association", city: "Clinton", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4140,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD050",GROUP_DESC:"MD050 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4140,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD050",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD050",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4140,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD050", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5140 ,protection_group_id: -4140, protection_element_id:-4140], primaryKey: false);
      insert('organizations', [ id: 104126, nci_institute_code: "MD051", name: "Dorcehester General Hospital", city: "Cambridge", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4141,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD051",GROUP_DESC:"MD051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4141,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4141,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5141 ,protection_group_id: -4141, protection_element_id:-4141], primaryKey: false);
      insert('organizations', [ id: 104127, nci_institute_code: "MD052", name: "Hematololgy/Oncology Consultants", city: "Greenbelt", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4142,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD052",GROUP_DESC:"MD052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4142,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4142,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5142 ,protection_group_id: -4142, protection_element_id:-4142], primaryKey: false);
      insert('organizations', [ id: 104128, nci_institute_code: "MD053", name: "Associates In Oncology Hematology PC-Rockville", city: "Rockville", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4143,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD053",GROUP_DESC:"MD053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4143,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4143,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5143 ,protection_group_id: -4143, protection_element_id:-4143], primaryKey: false);
      insert('organizations', [ id: 104129, nci_institute_code: "MD054", name: "Laboratory of Biological Chemistry", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4144,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD054",GROUP_DESC:"MD054 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4144,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD054",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD054",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4144,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD054", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5144 ,protection_group_id: -4144, protection_element_id:-4144], primaryKey: false);
      insert('organizations', [ id: 104130, nci_institute_code: "MD055", name: "Pediatric Care Center", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4145,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD055",GROUP_DESC:"MD055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4145,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4145,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5145 ,protection_group_id: -4145, protection_element_id:-4145], primaryKey: false);
      insert('organizations', [ id: 104131, nci_institute_code: "MD057", name: "Anmed Biosafe Incorporated.,", city: "Rockville", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4146,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD057",GROUP_DESC:"MD057 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4146,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD057",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD057",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4146,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD057", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5146 ,protection_group_id: -4146, protection_element_id:-4146], primaryKey: false);
      insert('organizations', [ id: 104132, nci_institute_code: "MD058", name: "Dyncorp/Ats", city: "Rockville", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4147,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD058",GROUP_DESC:"MD058 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4147,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD058",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD058",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4147,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD058", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5147 ,protection_group_id: -4147, protection_element_id:-4147], primaryKey: false);
      insert('organizations', [ id: 104133, nci_institute_code: "MD059", name: "National Institute on Aging", city: "Baltimore", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4148,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD059",GROUP_DESC:"MD059 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4148,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD059",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD059",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4148,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD059", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5148 ,protection_group_id: -4148, protection_element_id:-4148], primaryKey: false);
      insert('organizations', [ id: 104134, nci_institute_code: "MD061", name: "Mckesson Bioservices, Inc.", city: "Rockville", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4149,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD061",GROUP_DESC:"MD061 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4149,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD061",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD061",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4149,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD061", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5149 ,protection_group_id: -4149, protection_element_id:-4149], primaryKey: false);
      insert('organizations', [ id: 104135, nci_institute_code: "MD062", name: "MDPA Medical Hematology Oncology Associates of Maryland", city: "Owings Mills", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4150,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD062",GROUP_DESC:"MD062 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4150,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD062",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD062",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4150,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD062", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5150 ,protection_group_id: -4150, protection_element_id:-4150], primaryKey: false);
      insert('organizations', [ id: 104136, nci_institute_code: "MD063", name: "Potomac Physician Association P.C.", city: "Kensington", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4151,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD063",GROUP_DESC:"MD063 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4151,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD063",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD063",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4151,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD063", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5151 ,protection_group_id: -4151, protection_element_id:-4151], primaryKey: false);
      insert('organizations', [ id: 104137, nci_institute_code: "MD066", name: "NCI-Frederick", city: "Frederick", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4152,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD066",GROUP_DESC:"MD066 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4152,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD066",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD066",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4152,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD066", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5152 ,protection_group_id: -4152, protection_element_id:-4152], primaryKey: false);
      insert('organizations', [ id: 104138, nci_institute_code: "MD069", name: "Genzyme/Washington Labs", city: "Rockville", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4153,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD069",GROUP_DESC:"MD069 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4153,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD069",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD069",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4153,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD069", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5153 ,protection_group_id: -4153, protection_element_id:-4153], primaryKey: false);
      insert('organizations', [ id: 104139, nci_institute_code: "MD070", name: "Chesapeak Regional Cancer Center", city: "Charlotte Hall", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4154,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD070",GROUP_DESC:"MD070 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4154,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD070",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD070",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4154,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD070", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5154 ,protection_group_id: -4154, protection_element_id:-4154], primaryKey: false);
      insert('organizations', [ id: 104140, nci_institute_code: "MD071", name: "Women's Health Specialists", city: "Rockville", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4155,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD071",GROUP_DESC:"MD071 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4155,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD071",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD071",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4155,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD071", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5155 ,protection_group_id: -4155, protection_element_id:-4155], primaryKey: false);
      insert('organizations', [ id: 104141, nci_institute_code: "MD072", name: "Calvert Memorial Hospital", city: "Prince Frederick", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4156,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD072",GROUP_DESC:"MD072 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4156,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD072",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD072",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4156,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD072", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5156 ,protection_group_id: -4156, protection_element_id:-4156], primaryKey: false);
      insert('organizations', [ id: 104142, nci_institute_code: "MD073", name: "Saint Mary's Hospital", city: "Leonardtown", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4157,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD073",GROUP_DESC:"MD073 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4157,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD073",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD073",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4157,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD073", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5157 ,protection_group_id: -4157, protection_element_id:-4157], primaryKey: false);
      insert('organizations', [ id: 104143, nci_institute_code: "MD074", name: "Anand and Associates", city: "Greenbelt", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4158,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD074",GROUP_DESC:"MD074 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4158,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD074",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD074",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4158,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD074", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5158 ,protection_group_id: -4158, protection_element_id:-4158], primaryKey: false);
      insert('organizations', [ id: 104144, nci_institute_code: "MD075", name: "Greater Chesapeake Medical Group", city: "Annapolis", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4159,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD075",GROUP_DESC:"MD075 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4159,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD075",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD075",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4159,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD075", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5159 ,protection_group_id: -4159, protection_element_id:-4159], primaryKey: false);
      insert('organizations', [ id: 104145, nci_institute_code: "MD076", name: "Department of Veteran's Affairs", city: "Baltimore", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4160,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD076",GROUP_DESC:"MD076 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4160,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD076",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD076",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4160,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD076", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5160 ,protection_group_id: -4160, protection_element_id:-4160], primaryKey: false);
      insert('organizations', [ id: 104146, nci_institute_code: "MD078", name: "Kaiser Permanente - Largo Medical Center", city: "Largo", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4161,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD078",GROUP_DESC:"MD078 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4161,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD078",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD078",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4161,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD078", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5161 ,protection_group_id: -4161, protection_element_id:-4161], primaryKey: false);
      insert('organizations', [ id: 104147, nci_institute_code: "MD079", name: "Gynecologic Oncology Group", city: "Silver Spring", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4162,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD079",GROUP_DESC:"MD079 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4162,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD079",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD079",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4162,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD079", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5162 ,protection_group_id: -4162, protection_element_id:-4162], primaryKey: false);
    }

    void m6() {
        // all6 (25 terms)
      insert('organizations', [ id: 104148, nci_institute_code: "MD080", name: "Chesapeake Bay Oncology", city: "Annapolis", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4163,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD080",GROUP_DESC:"MD080 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4163,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD080",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD080",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4163,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD080", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5163 ,protection_group_id: -4163, protection_element_id:-4163], primaryKey: false);
      insert('organizations', [ id: 104149, nci_institute_code: "MD082", name: "North Arundel Radiation Oncology Center", city: "Glen Burnie", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4164,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD082",GROUP_DESC:"MD082 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4164,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD082",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD082",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4164,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD082", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5164 ,protection_group_id: -4164, protection_element_id:-4164], primaryKey: false);
      insert('organizations', [ id: 104150, nci_institute_code: "MD083", name: "North Anne Arundel Health System", city: "Glen Burnie", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4165,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD083",GROUP_DESC:"MD083 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4165,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD083",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD083",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4165,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD083", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5165 ,protection_group_id: -4165, protection_element_id:-4165], primaryKey: false);
      insert('organizations', [ id: 104151, nci_institute_code: "MD087", name: "Medical Hematology\\Oncology", city: "Easton", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4166,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD087",GROUP_DESC:"MD087 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4166,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD087",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD087",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4166,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD087", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5166 ,protection_group_id: -4166, protection_element_id:-4166], primaryKey: false);
      insert('organizations', [ id: 104152, nci_institute_code: "MD088", name: "Mckesson HBOC Pharmaceuticals Partners Group", city: "Rockville", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4167,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD088",GROUP_DESC:"MD088 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4167,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD088",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD088",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4167,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD088", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5167 ,protection_group_id: -4167, protection_element_id:-4167], primaryKey: false);
      insert('organizations', [ id: 104153, nci_institute_code: "MD090", name: "Saint Joseph Radiation Oncology", city: "Towson", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4168,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD090",GROUP_DESC:"MD090 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4168,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD090",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD090",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4168,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD090", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5168 ,protection_group_id: -4168, protection_element_id:-4168], primaryKey: false);
      insert('organizations', [ id: 104154, nci_institute_code: "MD091", name: "Central Maryland Onc/Howard Cty Hosp", city: "Columbia", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4169,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD091",GROUP_DESC:"MD091 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4169,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD091",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD091",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4169,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD091", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5169 ,protection_group_id: -4169, protection_element_id:-4169], primaryKey: false);
      insert('organizations', [ id: 104155, nci_institute_code: "MD092", name: "Univ-Maryland Paca-Redwood Facility", city: "Baltimore", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4170,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD092",GROUP_DESC:"MD092 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4170,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD092",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD092",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4170,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD092", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5170 ,protection_group_id: -4170, protection_element_id:-4170], primaryKey: false);
      insert('organizations', [ id: 104156, nci_institute_code: "MD093", name: "Franklin Square Medical Arts Building", city: "Baltimore", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4171,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD093",GROUP_DESC:"MD093 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4171,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD093",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD093",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4171,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD093", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5171 ,protection_group_id: -4171, protection_element_id:-4171], primaryKey: false);
      insert('organizations', [ id: 104157, nci_institute_code: "MD094", name: "Maryland Hematology/Oncology Associates", city: "Baltimore", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4172,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD094",GROUP_DESC:"MD094 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4172,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD094",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD094",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4172,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD094", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5172 ,protection_group_id: -4172, protection_element_id:-4172], primaryKey: false);
      insert('organizations', [ id: 104158, nci_institute_code: "MD095", name: "Helen Denit Cancer Center", city: "Olney", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4173,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD095",GROUP_DESC:"MD095 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4173,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD095",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD095",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4173,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD095", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5173 ,protection_group_id: -4173, protection_element_id:-4173], primaryKey: false);
      insert('organizations', [ id: 104159, nci_institute_code: "MD096", name: "Christiana Care Health Services", city: "Elkton", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4174,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD096",GROUP_DESC:"MD096 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4174,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD096",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD096",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4174,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD096", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5174 ,protection_group_id: -4174, protection_element_id:-4174], primaryKey: false);
      insert('organizations', [ id: 104160, nci_institute_code: "MD097", name: "Carroll County Cancer Center", city: "Westminster", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4175,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD097",GROUP_DESC:"MD097 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4175,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD097",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD097",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4175,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD097", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5175 ,protection_group_id: -4175, protection_element_id:-4175], primaryKey: false);
      insert('organizations', [ id: 104161, nci_institute_code: "MD098", name: "The Oncology Center of Central Baltimore", city: "Baltimore", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4176,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD098",GROUP_DESC:"MD098 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4176,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD098",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD098",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4176,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD098", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5176 ,protection_group_id: -4176, protection_element_id:-4176], primaryKey: false);
      insert('organizations', [ id: 104162, nci_institute_code: "MD099", name: "Memorial Hospital at Easton - Shore Regional Cancer Center", city: "Easton", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4177,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD099",GROUP_DESC:"MD099 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4177,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD099",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD099",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4177,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD099", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5177 ,protection_group_id: -4177, protection_element_id:-4177], primaryKey: false);
      insert('organizations', [ id: 104163, nci_institute_code: "MD100", name: "Oncology Care Associates", city: "Rockville", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4178,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD100",GROUP_DESC:"MD100 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4178,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD100",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD100",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4178,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD100", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5178 ,protection_group_id: -4178, protection_element_id:-4178], primaryKey: false);
      insert('organizations', [ id: 104164, nci_institute_code: "MD101", name: "Chesapeake Hematology-Oncology Associates", city: "Glen Burnie", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4179,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD101",GROUP_DESC:"MD101 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4179,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD101",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD101",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4179,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD101", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5179 ,protection_group_id: -4179, protection_element_id:-4179], primaryKey: false);
      insert('organizations', [ id: 104165, nci_institute_code: "MD102", name: "Frederick Oncology Hematology Associates", city: "Frederick", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4180,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD102",GROUP_DESC:"MD102 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4180,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD102",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD102",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4180,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD102", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5180 ,protection_group_id: -4180, protection_element_id:-4180], primaryKey: false);
      insert('organizations', [ id: 104166, nci_institute_code: "MD103", name: "Johns Hopkins Radiation Oncology", city: "Lutherville", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4181,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD103",GROUP_DESC:"MD103 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4181,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD103",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD103",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4181,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD103", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5181 ,protection_group_id: -4181, protection_element_id:-4181], primaryKey: false);
      insert('organizations', [ id: 104167, nci_institute_code: "MD104", name: "Osler Medical Center", city: "Towson", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4182,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD104",GROUP_DESC:"MD104 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4182,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD104",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD104",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4182,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD104", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5182 ,protection_group_id: -4182, protection_element_id:-4182], primaryKey: false);
      insert('organizations', [ id: 104168, nci_institute_code: "MD105", name: "Kaiser Permanente - Towson Medical Center", city: "Lutherville", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4183,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD105",GROUP_DESC:"MD105 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4183,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD105",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD105",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4183,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD105", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5183 ,protection_group_id: -4183, protection_element_id:-4183], primaryKey: false);
      insert('organizations', [ id: 104169, nci_institute_code: "MD106", name: "The Center for Cancer and Blood Disorders", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4184,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD106",GROUP_DESC:"MD106 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4184,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD106",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD106",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4184,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD106", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5184 ,protection_group_id: -4184, protection_element_id:-4184], primaryKey: false);
      insert('organizations', [ id: 104170, nci_institute_code: "MD107", name: "Kaiser Permanente - Shady Grove Medical Center", city: "Rockville", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4185,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD107",GROUP_DESC:"MD107 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4185,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD107",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD107",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4185,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD107", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5185 ,protection_group_id: -4185, protection_element_id:-4185], primaryKey: false);
      insert('organizations', [ id: 104171, nci_institute_code: "MD108", name: "Center For Prostate Disease Resarch", city: "Rockville", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4186,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD108",GROUP_DESC:"MD108 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4186,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD108",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD108",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4186,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD108", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5186 ,protection_group_id: -4186, protection_element_id:-4186], primaryKey: false);
      insert('organizations', [ id: 104172, nci_institute_code: "MD109", name: "Peninsula Oncology and Hematology PA", city: "Salisbury", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4187,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD109",GROUP_DESC:"MD109 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4187,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD109",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD109",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4187,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD109", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5187 ,protection_group_id: -4187, protection_element_id:-4187], primaryKey: false);
    }

    void m7() {
        // all7 (25 terms)
      insert('organizations', [ id: 104173, nci_institute_code: "MD110", name: "Anne Arundel Urology, P.A.", city: "Annapolis", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4188,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD110",GROUP_DESC:"MD110 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4188,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD110",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD110",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4188,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD110", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5188 ,protection_group_id: -4188, protection_element_id:-4188], primaryKey: false);
      insert('organizations', [ id: 104174, nci_institute_code: "MD111", name: "National Childhood Cancer Foundation", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4189,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD111",GROUP_DESC:"MD111 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4189,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD111",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD111",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4189,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD111", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5189 ,protection_group_id: -4189, protection_element_id:-4189], primaryKey: false);
      insert('organizations', [ id: 104175, nci_institute_code: "MD112", name: "Henry M. Jackson Foundation", city: "Rockville", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4190,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD112",GROUP_DESC:"MD112 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4190,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD112",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD112",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4190,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD112", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5190 ,protection_group_id: -4190, protection_element_id:-4190], primaryKey: false);
      insert('organizations', [ id: 104176, nci_institute_code: "MD114", name: "Sidney Kimmel Comprehensive Cancer Center at Johns Hopkins Hospital", city: "Baltimore", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4191,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD114",GROUP_DESC:"MD114 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4191,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD114",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD114",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4191,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD114", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5191 ,protection_group_id: -4191, protection_element_id:-4191], primaryKey: false);
      insert('organizations', [ id: 104177, nci_institute_code: "MD115", name: "Mark O Hatfield-Warren Grant Magnusun Clinical Center", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4192,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD115",GROUP_DESC:"MD115 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4192,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD115",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD115",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4192,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD115", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5192 ,protection_group_id: -4192, protection_element_id:-4192], primaryKey: false);
      insert('organizations', [ id: 104178, nci_institute_code: "MD116", name: "Walter Reed Army Institute of Research", city: "Silver Spring", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4193,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD116",GROUP_DESC:"MD116 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4193,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD116",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD116",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4193,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD116", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5193 ,protection_group_id: -4193, protection_element_id:-4193], primaryKey: false);
      insert('organizations', [ id: 104179, nci_institute_code: "MD117", name: "Greenbelt Radiation Oncology Center", city: "Greenbelt", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4194,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD117",GROUP_DESC:"MD117 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4194,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD117",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD117",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4194,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD117", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5194 ,protection_group_id: -4194, protection_element_id:-4194], primaryKey: false);
      insert('organizations', [ id: 104180, nci_institute_code: "MD118", name: "Carolyn B Hendricks MD PA", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4195,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD118",GROUP_DESC:"MD118 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4195,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD118",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD118",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4195,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD118", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5195 ,protection_group_id: -4195, protection_element_id:-4195], primaryKey: false);
      insert('organizations', [ id: 104181, nci_institute_code: "MD119", name: "Community Hematology Oncology Practitioners", city: "Olney", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4196,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD119",GROUP_DESC:"MD119 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4196,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD119",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD119",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4196,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD119", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5196 ,protection_group_id: -4196, protection_element_id:-4196], primaryKey: false);
      insert('organizations', [ id: 104182, nci_institute_code: "MD120", name: "Johns Hopkins at Green Spring Station", city: "Lutherville", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4197,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD120",GROUP_DESC:"MD120 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4197,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD120",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD120",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4197,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD120", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5197 ,protection_group_id: -4197, protection_element_id:-4197], primaryKey: false);
      insert('organizations', [ id: 104183, nci_institute_code: "MD121", name: "Humanitas, Inc", city: "Silver Spring", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4198,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD121",GROUP_DESC:"MD121 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4198,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD121",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD121",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4198,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD121", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5198 ,protection_group_id: -4198, protection_element_id:-4198], primaryKey: false);
      insert('organizations', [ id: 104184, nci_institute_code: "MD122", name: "Pathology Associates International", city: "Frederick", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4199,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD122",GROUP_DESC:"MD122 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4199,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD122",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD122",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4199,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD122", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5199 ,protection_group_id: -4199, protection_element_id:-4199], primaryKey: false);
      insert('organizations', [ id: 104185, nci_institute_code: "MD123", name: "Allegany Surgical Associates PA", city: "Cumberland", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4200,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD123",GROUP_DESC:"MD123 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4200,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD123",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD123",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4200,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD123", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5200 ,protection_group_id: -4200, protection_element_id:-4200], primaryKey: false);
      insert('organizations', [ id: 104186, nci_institute_code: "MD124", name: "Suburban Hospital Cancer Program", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4201,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD124",GROUP_DESC:"MD124 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4201,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD124",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD124",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4201,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD124", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5201 ,protection_group_id: -4201, protection_element_id:-4201], primaryKey: false);
      insert('organizations', [ id: 104187, nci_institute_code: "MD125", name: "University of Maryland Biotechnology Institute", city: "Baltimore", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4202,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD125",GROUP_DESC:"MD125 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4202,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD125",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD125",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4202,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD125", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5202 ,protection_group_id: -4202, protection_element_id:-4202], primaryKey: false);
      insert('organizations', [ id: 104188, nci_institute_code: "MD126", name: "Pediatric Hematology/Oncology", city: "Baltimore", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4203,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD126",GROUP_DESC:"MD126 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4203,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD126",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD126",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4203,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD126", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5203 ,protection_group_id: -4203, protection_element_id:-4203], primaryKey: false);
      insert('organizations', [ id: 104189, nci_institute_code: "MD127", name: "Franklin Square Radiation Oncology Center", city: "Baltimore", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4204,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD127",GROUP_DESC:"MD127 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4204,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD127",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD127",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4204,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD127", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5204 ,protection_group_id: -4204, protection_element_id:-4204], primaryKey: false);
      insert('organizations', [ id: 104190, nci_institute_code: "MD129", name: "Howard Hughes Medical Institute", city: "Chevy Chase", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4205,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD129",GROUP_DESC:"MD129 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4205,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD129",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD129",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4205,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD129", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5205 ,protection_group_id: -4205, protection_element_id:-4205], primaryKey: false);
      insert('organizations', [ id: 104191, nci_institute_code: "MD130", name: "exten CARE Inc", city: "Elkridge", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4206,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD130",GROUP_DESC:"MD130 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4206,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD130",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD130",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4206,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD130", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5206 ,protection_group_id: -4206, protection_element_id:-4206], primaryKey: false);
      insert('organizations', [ id: 104192, nci_institute_code: "MD131", name: "Forris Surgical Group LLP", city: "Frederick", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4207,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD131",GROUP_DESC:"MD131 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4207,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD131",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD131",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4207,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD131", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5207 ,protection_group_id: -4207, protection_element_id:-4207], primaryKey: false);
      insert('organizations', [ id: 104193, nci_institute_code: "MD132", name: "Urologic Surgery Associates PA", city: "Towson", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4208,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD132",GROUP_DESC:"MD132 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4208,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD132",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD132",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4208,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD132", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5208 ,protection_group_id: -4208, protection_element_id:-4208], primaryKey: false);
      insert('organizations', [ id: 104194, nci_institute_code: "MD133", name: "Advanced Technology Center", city: "Gaithersburg", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4209,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD133",GROUP_DESC:"MD133 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4209,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD133",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD133",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4209,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD133", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5209 ,protection_group_id: -4209, protection_element_id:-4209], primaryKey: false);
      insert('organizations', [ id: 104195, nci_institute_code: "MD134", name: "Maryland Regional Cancer Care", city: "Silver Spring", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4210,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD134",GROUP_DESC:"MD134 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4210,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD134",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD134",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4210,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD134", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5210 ,protection_group_id: -4210, protection_element_id:-4210], primaryKey: false);
      insert('organizations', [ id: 104196, nci_institute_code: "MD135", name: "Maryland Regional Cancer Care", city: "Bowie", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4211,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD135",GROUP_DESC:"MD135 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4211,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD135",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD135",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4211,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD135", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5211 ,protection_group_id: -4211, protection_element_id:-4211], primaryKey: false);
      insert('organizations', [ id: 104197, nci_institute_code: "MD136", name: "Tate Cancer Center", city: "Glen Burnie", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4212,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD136",GROUP_DESC:"MD136 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4212,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD136",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD136",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4212,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD136", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5212 ,protection_group_id: -4212, protection_element_id:-4212], primaryKey: false);
    }

    void m8() {
        // all8 (25 terms)
      insert('organizations', [ id: 104198, nci_institute_code: "MD137", name: "National Institute of Diabetes and Digestive and Kidney Diseases", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4213,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD137",GROUP_DESC:"MD137 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4213,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD137",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD137",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4213,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD137", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5213 ,protection_group_id: -4213, protection_element_id:-4213], primaryKey: false);
      insert('organizations', [ id: 104199, nci_institute_code: "MD138", name: "Kaiser Permanente/Woodlawn Medical Center", city: "Baltimore", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4214,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD138",GROUP_DESC:"MD138 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4214,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD138",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD138",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4214,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD138", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5214 ,protection_group_id: -4214, protection_element_id:-4214], primaryKey: false);
      insert('organizations', [ id: 104200, nci_institute_code: "MD139", name: "Baltimore Surgical Associates", city: "Towson", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4215,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD139",GROUP_DESC:"MD139 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4215,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD139",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD139",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4215,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD139", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5215 ,protection_group_id: -4215, protection_element_id:-4215], primaryKey: false);
      insert('organizations', [ id: 104201, nci_institute_code: "MD140", name: "Eastern Shore Urology Associates PA", city: "Easton", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4216,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD140",GROUP_DESC:"MD140 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4216,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD140",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD140",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4216,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD140", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5216 ,protection_group_id: -4216, protection_element_id:-4216], primaryKey: false);
      insert('organizations', [ id: 104202, nci_institute_code: "MD141", name: "National Institute of Drug Abuse", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4217,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD141",GROUP_DESC:"MD141 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4217,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD141",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD141",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4217,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD141", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5217 ,protection_group_id: -4217, protection_element_id:-4217], primaryKey: false);
      insert('organizations', [ id: 104203, nci_institute_code: "MD142", name: "Shady Grove Adventist Hospital", city: "Rockville", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4218,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD142",GROUP_DESC:"MD142 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4218,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD142",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD142",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4218,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD142", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5218 ,protection_group_id: -4218, protection_element_id:-4218], primaryKey: false);
      insert('organizations', [ id: 104204, nci_institute_code: "MD143", name: "Auerbach Hematology Oncology Associates", city: "Baltimore", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4219,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD143",GROUP_DESC:"MD143 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4219,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD143",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD143",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4219,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD143", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5219 ,protection_group_id: -4219, protection_element_id:-4219], primaryKey: false);
      insert('organizations', [ id: 104205, nci_institute_code: "MD144", name: "Oncology Care Associates PA", city: "Wheaton", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4220,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD144",GROUP_DESC:"MD144 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4220,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD144",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD144",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4220,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD144", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5220 ,protection_group_id: -4220, protection_element_id:-4220], primaryKey: false);
      insert('organizations', [ id: 104206, nci_institute_code: "MD145", name: "Robert D. Hammond Associates LLC", city: "North Potomac", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4221,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD145",GROUP_DESC:"MD145 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4221,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD145",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD145",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4221,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD145", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5221 ,protection_group_id: -4221, protection_element_id:-4221], primaryKey: false);
      insert('organizations', [ id: 104207, nci_institute_code: "MD146", name: "Calvert Oncology Associates PA", city: "Prince Frederick", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4222,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD146",GROUP_DESC:"MD146 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4222,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD146",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD146",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4222,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD146", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5222 ,protection_group_id: -4222, protection_element_id:-4222], primaryKey: false);
      insert('organizations', [ id: 104208, nci_institute_code: "MD147", name: "Doctors Community Hospital", city: "Lanham", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4223,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD147",GROUP_DESC:"MD147 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4223,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD147",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD147",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4223,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD147", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5223 ,protection_group_id: -4223, protection_element_id:-4223], primaryKey: false);
      insert('organizations', [ id: 104209, nci_institute_code: "MD148", name: "Surgical Associates Chartered", city: "Greenbelt", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4224,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD148",GROUP_DESC:"MD148 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4224,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD148",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD148",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4224,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD148", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5224 ,protection_group_id: -4224, protection_element_id:-4224], primaryKey: false);
      insert('organizations', [ id: 104210, nci_institute_code: "MD149", name: "University of Maryland Baltimore", city: "Baltimore", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4225,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD149",GROUP_DESC:"MD149 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4225,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD149",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD149",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4225,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD149", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5225 ,protection_group_id: -4225, protection_element_id:-4225], primaryKey: false);
      insert('organizations', [ id: 104211, nci_institute_code: "MD150", name: "Harry and Jeanette Weinberg Cancer Institute", city: "Baltimore", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4226,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD150",GROUP_DESC:"MD150 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4226,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD150",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD150",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4226,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD150", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5226 ,protection_group_id: -4226, protection_element_id:-4226], primaryKey: false);
      insert('organizations', [ id: 104212, nci_institute_code: "MD151", name: "Sarcoma Foundation of America", city: "Damascus", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4227,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD151",GROUP_DESC:"MD151 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4227,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD151",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD151",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4227,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD151", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5227 ,protection_group_id: -4227, protection_element_id:-4227], primaryKey: false);
      insert('organizations', [ id: 104213, nci_institute_code: "MD152", name: "The Harry and Jeanette Weinberg Center", city: "Baltimore", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4228,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD152",GROUP_DESC:"MD152 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4228,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD152",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD152",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4228,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD152", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5228 ,protection_group_id: -4228, protection_element_id:-4228], primaryKey: false);
      insert('organizations', [ id: 104214, nci_institute_code: "MD153", name: "Medicine Invention Design Inc", city: "Gaithersburg", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4229,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD153",GROUP_DESC:"MD153 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4229,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD153",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD153",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4229,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD153", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5229 ,protection_group_id: -4229, protection_element_id:-4229], primaryKey: false);
      insert('organizations', [ id: 104215, nci_institute_code: "MD154", name: "Maryland Hematology Oncology Associates PA", city: "Bel Air", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4230,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD154",GROUP_DESC:"MD154 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4230,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD154",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD154",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4230,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD154", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5230 ,protection_group_id: -4230, protection_element_id:-4230], primaryKey: false);
      insert('organizations', [ id: 104216, nci_institute_code: "MD155", name: "Science Applications International Corporation", city: "Fredrick", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4231,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD155",GROUP_DESC:"MD155 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4231,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD155",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD155",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4231,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD155", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5231 ,protection_group_id: -4231, protection_element_id:-4231], primaryKey: false);
      insert('organizations', [ id: 104217, nci_institute_code: "MD156", name: "Maryland Oncology PA", city: "Towson", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4232,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD156",GROUP_DESC:"MD156 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4232,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD156",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD156",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4232,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD156", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5232 ,protection_group_id: -4232, protection_element_id:-4232], primaryKey: false);
      insert('organizations', [ id: 104218, nci_institute_code: "MD157", name: "Upper Chesapeake Medical Center", city: "Bel Air", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4233,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD157",GROUP_DESC:"MD157 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4233,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD157",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD157",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4233,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD157", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5233 ,protection_group_id: -4233, protection_element_id:-4233], primaryKey: false);
      insert('organizations', [ id: 104219, nci_institute_code: "MD158", name: "Chesapeake Urology Associates", city: "Towson", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4234,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD158",GROUP_DESC:"MD158 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4234,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD158",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD158",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4234,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD158", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5234 ,protection_group_id: -4234, protection_element_id:-4234], primaryKey: false);
      insert('organizations', [ id: 104220, nci_institute_code: "MD159", name: "Annapolis Surgical Oncology Associates", city: "Annapolis", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4235,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD159",GROUP_DESC:"MD159 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4235,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD159",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD159",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4235,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD159", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5235 ,protection_group_id: -4235, protection_element_id:-4235], primaryKey: false);
      insert('organizations', [ id: 104221, nci_institute_code: "MD160", name: "Frederick P Smith MD PC", city: "Chevy Chase", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4236,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD160",GROUP_DESC:"MD160 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4236,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD160",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD160",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4236,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD160", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5236 ,protection_group_id: -4236, protection_element_id:-4236], primaryKey: false);
      insert('organizations', [ id: 104222, nci_institute_code: "MD161", name: "Antietam Oncology and Hematology Group PC", city: "Hagerstown", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4237,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD161",GROUP_DESC:"MD161 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4237,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD161",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD161",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4237,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD161", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5237 ,protection_group_id: -4237, protection_element_id:-4237], primaryKey: false);
    }

    void m9() {
        // all9 (25 terms)
      insert('organizations', [ id: 104223, nci_institute_code: "MD162", name: "Colette M Magnant MD PC", city: "Chevy Chase", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4238,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD162",GROUP_DESC:"MD162 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4238,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD162",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD162",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4238,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD162", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5238 ,protection_group_id: -4238, protection_element_id:-4238], primaryKey: false);
      insert('organizations', [ id: 104224, nci_institute_code: "MD163", name: "Brecher and Ballard MD PA", city: "Silver Spring", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4239,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD163",GROUP_DESC:"MD163 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4239,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD163",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD163",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4239,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD163", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5239 ,protection_group_id: -4239, protection_element_id:-4239], primaryKey: false);
      insert('organizations', [ id: 104225, nci_institute_code: "MD164", name: "Robinwood Surgical Associates", city: "Hagerstown", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4240,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD164",GROUP_DESC:"MD164 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4240,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD164",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD164",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4240,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD164", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5240 ,protection_group_id: -4240, protection_element_id:-4240], primaryKey: false);
      insert('organizations', [ id: 104226, nci_institute_code: "MD165", name: "Choice Metro Surgery", city: "Greenbelt", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4241,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD165",GROUP_DESC:"MD165 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4241,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD165",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD165",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4241,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD165", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5241 ,protection_group_id: -4241, protection_element_id:-4241], primaryKey: false);
      insert('organizations', [ id: 104227, nci_institute_code: "MD166", name: "Chevy Chase Health Care", city: "Chevy Chase", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4242,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD166",GROUP_DESC:"MD166 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4242,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD166",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD166",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4242,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD166", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5242 ,protection_group_id: -4242, protection_element_id:-4242], primaryKey: false);
      insert('organizations', [ id: 104228, nci_institute_code: "MD167", name: "Center for Diagnostic Services", city: "Cumberland", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4243,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD167",GROUP_DESC:"MD167 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4243,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD167",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD167",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4243,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD167", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5243 ,protection_group_id: -4243, protection_element_id:-4243], primaryKey: false);
      insert('organizations', [ id: 104229, nci_institute_code: "MD168", name: "Baltimore Oncology and Hematology Associates LLC", city: "Baltimore", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4244,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD168",GROUP_DESC:"MD168 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4244,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD168",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD168",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4244,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD168", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5244 ,protection_group_id: -4244, protection_element_id:-4244], primaryKey: false);
      insert('organizations', [ id: 104230, nci_institute_code: "MD169", name: "Bay Hematology and Oncology PA", city: "Easton", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4245,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD169",GROUP_DESC:"MD169 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4245,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD169",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD169",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4245,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD169", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5245 ,protection_group_id: -4245, protection_element_id:-4245], primaryKey: false);
      insert('organizations', [ id: 104231, nci_institute_code: "MD170", name: "Capital Oncology and Hematology Associates", city: "Silver Spring", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4246,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD170",GROUP_DESC:"MD170 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4246,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD170",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD170",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4246,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD170", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5246 ,protection_group_id: -4246, protection_element_id:-4246], primaryKey: false);
      insert('organizations', [ id: 104232, nci_institute_code: "MD171", name: "21st Century Oncology", city: "Berlin", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4247,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD171",GROUP_DESC:"MD171 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4247,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD171",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD171",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4247,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD171", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5247 ,protection_group_id: -4247, protection_element_id:-4247], primaryKey: false);
      insert('organizations', [ id: 104233, nci_institute_code: "MD172", name: "Associates in Oncology Hematology PC -Kensington", city: "Kensington", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4248,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD172",GROUP_DESC:"MD172 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4248,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD172",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD172",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4248,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD172", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5248 ,protection_group_id: -4248, protection_element_id:-4248], primaryKey: false);
      insert('organizations', [ id: 104234, nci_institute_code: "MDA", name: "M D Anderson Cancer Center", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4249,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MDA",GROUP_DESC:"MDA group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4249,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MDA",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MDA",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4249,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MDA", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5249 ,protection_group_id: -4249, protection_element_id:-4249], primaryKey: false);
      insert('organizations', [ id: 104235, nci_institute_code: "ME001", name: "Department of Veteran's Affairs - Togus", city: "Togus", state: "ME", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4250,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME001",GROUP_DESC:"ME001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4250,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ME001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ME001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4250,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5250 ,protection_group_id: -4250, protection_element_id:-4250], primaryKey: false);
      insert('organizations', [ id: 104236, nci_institute_code: "ME002", name: "The Barbara Bush Children's Hospital", city: "Portland", state: "ME", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4251,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME002",GROUP_DESC:"ME002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4251,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ME002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ME002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4251,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5251 ,protection_group_id: -4251, protection_element_id:-4251], primaryKey: false);
      insert('organizations', [ id: 104237, nci_institute_code: "ME004", name: "Maine General Medical Center", city: "Augusta", state: "ME", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4252,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME004",GROUP_DESC:"ME004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4252,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ME004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ME004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4252,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5252 ,protection_group_id: -4252, protection_element_id:-4252], primaryKey: false);
      insert('organizations', [ id: 104238, nci_institute_code: "ME006", name: "Eastern Maine Medical Center", city: "Bangor", state: "ME", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4253,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME006",GROUP_DESC:"ME006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4253,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ME006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ME006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4253,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5253 ,protection_group_id: -4253, protection_element_id:-4253], primaryKey: false);
      insert('organizations', [ id: 104239, nci_institute_code: "ME008", name: "Maine General Medical Center", city: "Waterville", state: "ME", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4254,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME008",GROUP_DESC:"ME008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4254,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ME008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ME008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4254,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5254 ,protection_group_id: -4254, protection_element_id:-4254], primaryKey: false);
      insert('organizations', [ id: 104240, nci_institute_code: "ME009", name: "Theyer Hospital", city: "Waterville", state: "ME", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4255,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME009",GROUP_DESC:"ME009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4255,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ME009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ME009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4255,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5255 ,protection_group_id: -4255, protection_element_id:-4255], primaryKey: false);
      insert('organizations', [ id: 104241, nci_institute_code: "ME010", name: "Maine Medical Center", city: "Portland", state: "ME", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4256,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME010",GROUP_DESC:"ME010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4256,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ME010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ME010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4256,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5256 ,protection_group_id: -4256, protection_element_id:-4256], primaryKey: false);
      insert('organizations', [ id: 104242, nci_institute_code: "ME011", name: "Central Maine Medical Center", city: "Lewiston", state: "ME", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4257,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME011",GROUP_DESC:"ME011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4257,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ME011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ME011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4257,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5257 ,protection_group_id: -4257, protection_element_id:-4257], primaryKey: false);
      insert('organizations', [ id: 104243, nci_institute_code: "ME012", name: "Aroostook Medical Center", city: "Presque Isle", state: "ME", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4258,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME012",GROUP_DESC:"ME012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4258,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ME012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ME012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4258,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5258 ,protection_group_id: -4258, protection_element_id:-4258], primaryKey: false);
      insert('organizations', [ id: 104244, nci_institute_code: "ME013", name: "Critical Care Systems", city: "South Portland", state: "ME", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4259,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME013",GROUP_DESC:"ME013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4259,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ME013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ME013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4259,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5259 ,protection_group_id: -4259, protection_element_id:-4259], primaryKey: false);
      insert('organizations', [ id: 104245, nci_institute_code: "ME015", name: "Mercy Hospital", city: "Portland", state: "ME", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4260,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME015",GROUP_DESC:"ME015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4260,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ME015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ME015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4260,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5260 ,protection_group_id: -4260, protection_element_id:-4260], primaryKey: false);
      insert('organizations', [ id: 104246, nci_institute_code: "ME016", name: "Southern Maine Medical Center", city: "Biddeford", state: "ME", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4261,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME016",GROUP_DESC:"ME016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4261,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ME016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ME016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4261,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5261 ,protection_group_id: -4261, protection_element_id:-4261], primaryKey: false);
      insert('organizations', [ id: 104247, nci_institute_code: "ME017", name: "York Hospital", city: "York", state: "ME", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4262,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME017",GROUP_DESC:"ME017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4262,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ME017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ME017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4262,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5262 ,protection_group_id: -4262, protection_element_id:-4262], primaryKey: false);
    }

    void m10() {
        // all10 (25 terms)
      insert('organizations', [ id: 104248, nci_institute_code: "ME018", name: "Maine Medical Center", city: "Scarborough", state: "ME", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4263,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME018",GROUP_DESC:"ME018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4263,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ME018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ME018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4263,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5263 ,protection_group_id: -4263, protection_element_id:-4263], primaryKey: false);
      insert('organizations', [ id: 104249, nci_institute_code: "ME019", name: "Saint Mary's Regional Medical Center", city: "Lewiston", state: "ME", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4264,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME019",GROUP_DESC:"ME019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4264,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ME019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ME019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4264,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5264 ,protection_group_id: -4264, protection_element_id:-4264], primaryKey: false);
      insert('organizations', [ id: 104250, nci_institute_code: "ME020", name: "Maine Centers for Health Care", city: "Portland", state: "ME", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4265,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME020",GROUP_DESC:"ME020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4265,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ME020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ME020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4265,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5265 ,protection_group_id: -4265, protection_element_id:-4265], primaryKey: false);
      insert('organizations', [ id: 104251, nci_institute_code: "ME021", name: "Maine Center for Cancer Medicine/Blood Disorders", city: "Brunswick", state: "ME", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4266,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME021",GROUP_DESC:"ME021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4266,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ME021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ME021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4266,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5266 ,protection_group_id: -4266, protection_element_id:-4266], primaryKey: false);
      insert('organizations', [ id: 104252, nci_institute_code: "ME022", name: "Penobscot Bay Medical Center", city: "Rockport", state: "ME", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4267,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME022",GROUP_DESC:"ME022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4267,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ME022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ME022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4267,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5267 ,protection_group_id: -4267, protection_element_id:-4267], primaryKey: false);
      insert('organizations', [ id: 104253, nci_institute_code: "ME023", name: "Maine Center for Cancer Medicine & Blood Disorders", city: "Scarborough", state: "ME", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4268,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME023",GROUP_DESC:"ME023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4268,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ME023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ME023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4268,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5268 ,protection_group_id: -4268, protection_element_id:-4268], primaryKey: false);
      insert('organizations', [ id: 104254, nci_institute_code: "ME024", name: "Cancer Care of Maine", city: "Bangor", state: "ME", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4269,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME024",GROUP_DESC:"ME024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4269,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ME024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ME024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4269,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5269 ,protection_group_id: -4269, protection_element_id:-4269], primaryKey: false);
      insert('organizations', [ id: 104255, nci_institute_code: "ME025", name: "Hematology/Oncology Associates", city: "Lewiston", state: "ME", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4270,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME025",GROUP_DESC:"ME025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4270,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ME025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ME025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4270,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5270 ,protection_group_id: -4270, protection_element_id:-4270], primaryKey: false);
      insert('organizations', [ id: 104256, nci_institute_code: "ME026", name: "Henrietta D. Goodall Hospital", city: "Sanford", state: "ME", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4271,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME026",GROUP_DESC:"ME026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4271,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ME026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ME026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4271,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5271 ,protection_group_id: -4271, protection_element_id:-4271], primaryKey: false);
      insert('organizations', [ id: 104257, nci_institute_code: "ME027", name: "Portland Gastroenterology Associates", city: "Portland", state: "ME", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4272,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME027",GROUP_DESC:"ME027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4272,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ME027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ME027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4272,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5272 ,protection_group_id: -4272, protection_element_id:-4272], primaryKey: false);
      insert('organizations', [ id: 104258, nci_institute_code: "ME028", name: "Maine Surgical Care Group", city: "Portland", state: "ME", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4273,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME028",GROUP_DESC:"ME028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4273,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ME028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ME028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4273,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5273 ,protection_group_id: -4273, protection_element_id:-4273], primaryKey: false);
      insert('organizations', [ id: 104259, nci_institute_code: "ME029", name: "Casco Bay Surgery", city: "Portland", state: "ME", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4274,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME029",GROUP_DESC:"ME029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4274,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ME029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ME029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4274,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5274 ,protection_group_id: -4274, protection_element_id:-4274], primaryKey: false);
      insert('organizations', [ id: 104260, nci_institute_code: "ME030", name: "Women to Women", city: "Yarmouth", state: "ME", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4275,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME030",GROUP_DESC:"ME030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4275,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ME030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ME030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4275,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5275 ,protection_group_id: -4275, protection_element_id:-4275], primaryKey: false);
      insert('organizations', [ id: 104261, nci_institute_code: "ME031", name: "Maine Center for Cancer Medicine", city: "Biddeford", state: "ME", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4276,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME031",GROUP_DESC:"ME031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4276,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ME031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ME031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4276,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5276 ,protection_group_id: -4276, protection_element_id:-4276], primaryKey: false);
      insert('organizations', [ id: 104262, nci_institute_code: "ME032", name: "Center for Cancer and Blood Disorders", city: "Lewistown", state: "ME", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4277,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME032",GROUP_DESC:"ME032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4277,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ME032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ME032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4277,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5277 ,protection_group_id: -4277, protection_element_id:-4277], primaryKey: false);
      insert('organizations', [ id: 104263, nci_institute_code: "ME033", name: "Maine Women's Surgery and Cancer Center", city: "Scarborough", state: "ME", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4278,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME033",GROUP_DESC:"ME033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4278,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ME033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ME033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4278,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5278 ,protection_group_id: -4278, protection_element_id:-4278], primaryKey: false);
      insert('organizations', [ id: 104264, nci_institute_code: "ME034", name: "York Hospital Oncology Treatment Center", city: "York", state: "ME", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4279,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME034",GROUP_DESC:"ME034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4279,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ME034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ME034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4279,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5279 ,protection_group_id: -4279, protection_element_id:-4279], primaryKey: false);
      insert('organizations', [ id: 104265, nci_institute_code: "ME035", name: "Maine Center for Cancer Medicine and Blood Disorders", city: "Sanford", state: "ME", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4280,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME035",GROUP_DESC:"ME035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4280,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ME035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ME035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4280,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5280 ,protection_group_id: -4280, protection_element_id:-4280], primaryKey: false);
      insert('organizations', [ id: 104266, nci_institute_code: "ME036", name: "Bridgton Hospital", city: "Bridgton", state: "ME", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4281,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME036",GROUP_DESC:"ME036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4281,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ME036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ME036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4281,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5281 ,protection_group_id: -4281, protection_element_id:-4281], primaryKey: false);
      insert('organizations', [ id: 104267, nci_institute_code: "MEDREX", name: "Medarex, Incorporated", city: "Princeton", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4282,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MEDREX",GROUP_DESC:"MEDREX group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4282,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MEDREX",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MEDREX",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4282,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MEDREX", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5282 ,protection_group_id: -4282, protection_element_id:-4282], primaryKey: false);
      insert('organizations', [ id: 104268, nci_institute_code: "MFCG", name: "Mycosis Fungoides Cooperative Group", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4283,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MFCG",GROUP_DESC:"MFCG group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4283,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MFCG",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MFCG",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4283,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MFCG", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5283 ,protection_group_id: -4283, protection_element_id:-4283], primaryKey: false);
      insert('organizations', [ id: 104269, nci_institute_code: "MI001", name: "Hematology Oncology Associates East PC", city: "Harper Woods", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4284,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI001",GROUP_DESC:"MI001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4284,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4284,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5284 ,protection_group_id: -4284, protection_element_id:-4284], primaryKey: false);
      insert('organizations', [ id: 104270, nci_institute_code: "MI002", name: "Saint Joseph Mercy Oakland", city: "Pontiac", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4285,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI002",GROUP_DESC:"MI002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4285,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4285,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5285 ,protection_group_id: -4285, protection_element_id:-4285], primaryKey: false);
      insert('organizations', [ id: 104271, nci_institute_code: "MI003", name: "Pontiac General Hospital", city: "Pontiac", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4286,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI003",GROUP_DESC:"MI003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4286,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4286,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5286 ,protection_group_id: -4286, protection_element_id:-4286], primaryKey: false);
      insert('organizations', [ id: 104272, nci_institute_code: "MI004", name: "Oakland General Hospital", city: "Madison Heights", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4287,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI004",GROUP_DESC:"MI004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4287,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4287,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5287 ,protection_group_id: -4287, protection_element_id:-4287], primaryKey: false);
    }

    void m11() {
        // all11 (25 terms)
      insert('organizations', [ id: 104273, nci_institute_code: "MI005", name: "William Beaumont Hospital", city: "Royal Oak", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4288,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI005",GROUP_DESC:"MI005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4288,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4288,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5288 ,protection_group_id: -4288, protection_element_id:-4288], primaryKey: false);
      insert('organizations', [ id: 104274, nci_institute_code: "MI006", name: "Providence Hospital", city: "Southfield", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4289,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI006",GROUP_DESC:"MI006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4289,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4289,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5289 ,protection_group_id: -4289, protection_element_id:-4289], primaryKey: false);
      insert('organizations', [ id: 104275, nci_institute_code: "MI007", name: "Memorial Medical Center", city: "Sterling Heights", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4290,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI007",GROUP_DESC:"MI007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4290,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4290,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5290 ,protection_group_id: -4290, protection_element_id:-4290], primaryKey: false);
      insert('organizations', [ id: 104276, nci_institute_code: "MI008", name: "Genesys Regional Medical Center", city: "Grand Blanc", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4291,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI008",GROUP_DESC:"MI008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4291,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4291,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5291 ,protection_group_id: -4291, protection_element_id:-4291], primaryKey: false);
      insert('organizations', [ id: 104277, nci_institute_code: "MI011", name: "Veteran's Administration Medical Center - Ann Arbor", city: "Ann Arbor", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4292,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI011",GROUP_DESC:"MI011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4292,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4292,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5292 ,protection_group_id: -4292, protection_element_id:-4292], primaryKey: false);
      insert('organizations', [ id: 104278, nci_institute_code: "MI012", name: "Parker-Davis & Company", city: "Ann Arbor", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4293,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI012",GROUP_DESC:"MI012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4293,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4293,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5293 ,protection_group_id: -4293, protection_element_id:-4293], primaryKey: false);
      insert('organizations', [ id: 104279, nci_institute_code: "MI013", name: "Saint Joseph Mercy Hospital", city: "Ann Arbor", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4294,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI013",GROUP_DESC:"MI013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4294,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4294,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5294 ,protection_group_id: -4294, protection_element_id:-4294], primaryKey: false);
      insert('organizations', [ id: 104280, nci_institute_code: "MI014", name: "University of Michigan University Hospital", city: "Ann Arbor", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4295,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI014",GROUP_DESC:"MI014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4295,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4295,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5295 ,protection_group_id: -4295, protection_element_id:-4295], primaryKey: false);
      insert('organizations', [ id: 104281, nci_institute_code: "MI015", name: "Oakwood Hospital", city: "Dearborn", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4296,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI015",GROUP_DESC:"MI015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4296,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4296,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5296 ,protection_group_id: -4296, protection_element_id:-4296], primaryKey: false);
      insert('organizations', [ id: 104282, nci_institute_code: "MI016", name: "Garden City Hospital", city: "Garden City", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4297,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI016",GROUP_DESC:"MI016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4297,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4297,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5297 ,protection_group_id: -4297, protection_element_id:-4297], primaryKey: false);
      insert('organizations', [ id: 104283, nci_institute_code: "MI017", name: "Saint Mary Mercy Hospital", city: "Livonia", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4298,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI017",GROUP_DESC:"MI017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4298,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4298,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5298 ,protection_group_id: -4298, protection_element_id:-4298], primaryKey: false);
      insert('organizations', [ id: 104284, nci_institute_code: "MI018", name: "Wayne County General Hosp", city: "Westland", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4299,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI018",GROUP_DESC:"MI018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4299,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4299,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5299 ,protection_group_id: -4299, protection_element_id:-4299], primaryKey: false);
      insert('organizations', [ id: 104285, nci_institute_code: "MI020", name: "Wayne State University", city: "Detroit", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4300,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI020",GROUP_DESC:"MI020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4300,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4300,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5300 ,protection_group_id: -4300, protection_element_id:-4300], primaryKey: false);
      insert('organizations', [ id: 104286, nci_institute_code: "MI021", name: "Grand Rapids Clinical Oncology Program", city: "Grand Rapids", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4301,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI021",GROUP_DESC:"MI021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4301,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4301,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5301 ,protection_group_id: -4301, protection_element_id:-4301], primaryKey: false);
      insert('organizations', [ id: 104287, nci_institute_code: "MI023", name: "Hutzel Hospital", city: "Detroit", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4302,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI023",GROUP_DESC:"MI023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4302,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4302,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5302 ,protection_group_id: -4302, protection_element_id:-4302], primaryKey: false);
      insert('organizations', [ id: 104288, nci_institute_code: "MI024", name: "Sinai-Grace Hospital", city: "Detroit", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4303,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI024",GROUP_DESC:"MI024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4303,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4303,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5303 ,protection_group_id: -4303, protection_element_id:-4303], primaryKey: false);
      insert('organizations', [ id: 104289, nci_institute_code: "MI025", name: "Harper University Hospital -", city: "Detroit", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4304,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI025",GROUP_DESC:"MI025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4304,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4304,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5304 ,protection_group_id: -4304, protection_element_id:-4304], primaryKey: false);
      insert('organizations', [ id: 104290, nci_institute_code: "MI026", name: "Henry Ford Hospital", city: "Detroit", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4305,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI026",GROUP_DESC:"MI026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4305,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4305,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5305 ,protection_group_id: -4305, protection_element_id:-4305], primaryKey: false);
      insert('organizations', [ id: 104291, nci_institute_code: "MI027", name: "Detroit Osteopathic Hospital", city: "Detroit", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4306,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI027",GROUP_DESC:"MI027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4306,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4306,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5306 ,protection_group_id: -4306, protection_element_id:-4306], primaryKey: false);
      insert('organizations', [ id: 104292, nci_institute_code: "MI028", name: "Metropolitan Hospital", city: "Detroit", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4307,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI028",GROUP_DESC:"MI028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4307,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4307,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5307 ,protection_group_id: -4307, protection_element_id:-4307], primaryKey: false);
      insert('organizations', [ id: 104293, nci_institute_code: "MI029", name: "Detroit General Hospital", city: "Detroit", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4308,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI029",GROUP_DESC:"MI029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4308,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4308,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5308 ,protection_group_id: -4308, protection_element_id:-4308], primaryKey: false);
      insert('organizations', [ id: 104294, nci_institute_code: "MI030", name: "Kalamazoo Center for Medical Studies", city: "Kalamazoo", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4309,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI030",GROUP_DESC:"MI030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4309,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4309,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5309 ,protection_group_id: -4309, protection_element_id:-4309], primaryKey: false);
      insert('organizations', [ id: 104295, nci_institute_code: "MI031", name: "Sinai Hospital", city: "Detroit", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4310,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI031",GROUP_DESC:"MI031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4310,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4310,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5310 ,protection_group_id: -4310, protection_element_id:-4310], primaryKey: false);
      insert('organizations', [ id: 104296, nci_institute_code: "MI032", name: "Saint John Hospital & Medical Center", city: "Detroit", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4311,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI032",GROUP_DESC:"MI032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4311,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4311,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5311 ,protection_group_id: -4311, protection_element_id:-4311], primaryKey: false);
      insert('organizations', [ id: 104297, nci_institute_code: "MI033", name: "Saint Joseph Hospital", city: "Flint", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4312,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI033",GROUP_DESC:"MI033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4312,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4312,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5312 ,protection_group_id: -4312, protection_element_id:-4312], primaryKey: false);
    }

    void m12() {
        // all12 (25 terms)
      insert('organizations', [ id: 104298, nci_institute_code: "MI035", name: "Saginaw General Hospital", city: "Saginaw", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4313,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI035",GROUP_DESC:"MI035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4313,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4313,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5313 ,protection_group_id: -4313, protection_element_id:-4313], primaryKey: false);
      insert('organizations', [ id: 104299, nci_institute_code: "MI036", name: "Midland Hospital Center", city: "Midland", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4314,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI036",GROUP_DESC:"MI036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4314,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4314,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5314 ,protection_group_id: -4314, protection_element_id:-4314], primaryKey: false);
      insert('organizations', [ id: 104300, nci_institute_code: "MI037", name: "Bay Regional Medical Center", city: "Bay City", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4315,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI037",GROUP_DESC:"MI037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4315,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4315,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5315 ,protection_group_id: -4315, protection_element_id:-4315], primaryKey: false);
      insert('organizations', [ id: 104301, nci_institute_code: "MI038", name: "Michigan State University - Breslin Cancer Center", city: "East Lansing", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4316,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI038",GROUP_DESC:"MI038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4316,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4316,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5316 ,protection_group_id: -4316, protection_element_id:-4316], primaryKey: false);
      insert('organizations', [ id: 104302, nci_institute_code: "MI039", name: "Sparrow Hospital", city: "Lansing", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4317,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI039",GROUP_DESC:"MI039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4317,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4317,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5317 ,protection_group_id: -4317, protection_element_id:-4317], primaryKey: false);
      insert('organizations', [ id: 104303, nci_institute_code: "MI041", name: "Borgess Medical Center", city: "Kalamazoo", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4318,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI041",GROUP_DESC:"MI041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4318,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4318,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5318 ,protection_group_id: -4318, protection_element_id:-4318], primaryKey: false);
      insert('organizations', [ id: 104304, nci_institute_code: "MI042", name: "Upjohn", city: "Kalamazoo", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4319,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI042",GROUP_DESC:"MI042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4319,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4319,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5319 ,protection_group_id: -4319, protection_element_id:-4319], primaryKey: false);
      insert('organizations', [ id: 104305, nci_institute_code: "MI043", name: "Midwest Oncology Center", city: "Kalamazoo", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4320,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI043",GROUP_DESC:"MI043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4320,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4320,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5320 ,protection_group_id: -4320, protection_element_id:-4320], primaryKey: false);
      insert('organizations', [ id: 104306, nci_institute_code: "MI044", name: "Bronson Methodist Hospital", city: "Kalamazoo", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4321,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI044",GROUP_DESC:"MI044 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4321,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI044",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI044",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4321,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI044", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5321 ,protection_group_id: -4321, protection_element_id:-4321], primaryKey: false);
      insert('organizations', [ id: 104307, nci_institute_code: "MI045", name: "Battle Creek Health System", city: "Battle Creek", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4322,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI045",GROUP_DESC:"MI045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4322,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4322,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5322 ,protection_group_id: -4322, protection_element_id:-4322], primaryKey: false);
      insert('organizations', [ id: 104308, nci_institute_code: "MI047", name: "Saint Mary's Health Care", city: "Grand Rapids", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4323,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI047",GROUP_DESC:"MI047 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4323,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI047",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI047",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4323,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI047", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5323 ,protection_group_id: -4323, protection_element_id:-4323], primaryKey: false);
      insert('organizations', [ id: 104309, nci_institute_code: "MI048", name: "Ferguson Clinic", city: "Grand Rapids", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4324,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI048",GROUP_DESC:"MI048 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4324,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI048",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI048",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4324,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI048", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5324 ,protection_group_id: -4324, protection_element_id:-4324], primaryKey: false);
      insert('organizations', [ id: 104310, nci_institute_code: "MI049", name: "Blodgett Memorial Medical Center", city: "Grand Rapids", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4325,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI049",GROUP_DESC:"MI049 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4325,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI049",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI049",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4325,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI049", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5325 ,protection_group_id: -4325, protection_element_id:-4325], primaryKey: false);
      insert('organizations', [ id: 104311, nci_institute_code: "MI050", name: "Marquette General Hospital", city: "Marquette", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4326,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI050",GROUP_DESC:"MI050 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4326,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI050",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI050",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4326,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI050", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5326 ,protection_group_id: -4326, protection_element_id:-4326], primaryKey: false);
      insert('organizations', [ id: 104312, nci_institute_code: "MI051", name: "Detroit Receiving Hospital", city: "Detroit", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4327,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI051",GROUP_DESC:"MI051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4327,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4327,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5327 ,protection_group_id: -4327, protection_element_id:-4327], primaryKey: false);
      insert('organizations', [ id: 104313, nci_institute_code: "MI052", name: "Hurley Medical Center", city: "Flint", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4328,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI052",GROUP_DESC:"MI052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4328,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4328,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5328 ,protection_group_id: -4328, protection_element_id:-4328], primaryKey: false);
      insert('organizations', [ id: 104314, nci_institute_code: "MI053", name: "Detroit Medical Center., HCC", city: "Detroit", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4329,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI053",GROUP_DESC:"MI053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4329,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4329,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5329 ,protection_group_id: -4329, protection_element_id:-4329], primaryKey: false);
      insert('organizations', [ id: 104315, nci_institute_code: "MI054", name: "Botsford General Hospital", city: "Farmington", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4330,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI054",GROUP_DESC:"MI054 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4330,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI054",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI054",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4330,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI054", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5330 ,protection_group_id: -4330, protection_element_id:-4330], primaryKey: false);
      insert('organizations', [ id: 104316, nci_institute_code: "MI055", name: "Bi-County Community Hospital", city: "Warren", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4331,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI055",GROUP_DESC:"MI055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4331,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4331,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5331 ,protection_group_id: -4331, protection_element_id:-4331], primaryKey: false);
      insert('organizations', [ id: 104317, nci_institute_code: "MI056", name: "Mercy Memorial Medical Center", city: "Monroe", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4332,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI056",GROUP_DESC:"MI056 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4332,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI056",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI056",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4332,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI056", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5332 ,protection_group_id: -4332, protection_element_id:-4332], primaryKey: false);
      insert('organizations', [ id: 104318, nci_institute_code: "MI057", name: "Mercy Hospital", city: "Port Huron", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4333,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI057",GROUP_DESC:"MI057 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4333,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI057",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI057",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4333,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI057", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5333 ,protection_group_id: -4333, protection_element_id:-4333], primaryKey: false);
      insert('organizations', [ id: 104319, nci_institute_code: "MI058", name: "Detroit Riverview Hospital", city: "Detroit", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4334,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI058",GROUP_DESC:"MI058 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4334,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI058",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI058",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4334,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI058", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5334 ,protection_group_id: -4334, protection_element_id:-4334], primaryKey: false);
      insert('organizations', [ id: 104320, nci_institute_code: "MI059", name: "Munson Medical Center", city: "Traverse City", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4335,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI059",GROUP_DESC:"MI059 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4335,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI059",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI059",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4335,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI059", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5335 ,protection_group_id: -4335, protection_element_id:-4335], primaryKey: false);
      insert('organizations', [ id: 104321, nci_institute_code: "MI060", name: "McLaren Regional Medical Center", city: "Flint", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4336,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI060",GROUP_DESC:"MI060 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4336,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI060",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI060",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4336,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI060", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5336 ,protection_group_id: -4336, protection_element_id:-4336], primaryKey: false);
      insert('organizations', [ id: 104322, nci_institute_code: "MI061", name: "Mercy Memorial Hospital", city: "Monroe", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4337,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI061",GROUP_DESC:"MI061 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4337,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI061",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI061",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4337,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI061", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5337 ,protection_group_id: -4337, protection_element_id:-4337], primaryKey: false);
    }

    void m13() {
        // all13 (25 terms)
      insert('organizations', [ id: 104323, nci_institute_code: "MI062", name: "Burns Clinic Medical Center", city: "Petoskey", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4338,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI062",GROUP_DESC:"MI062 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4338,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI062",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI062",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4338,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI062", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5338 ,protection_group_id: -4338, protection_element_id:-4338], primaryKey: false);
      insert('organizations', [ id: 104324, nci_institute_code: "MI063", name: "Rose Cancer Center", city: "Royal Oak", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4339,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI063",GROUP_DESC:"MI063 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4339,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI063",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI063",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4339,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI063", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5339 ,protection_group_id: -4339, protection_element_id:-4339], primaryKey: false);
      insert('organizations', [ id: 104325, nci_institute_code: "MI065", name: "Southfield Oncology Institute", city: "Southfield", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4340,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI065",GROUP_DESC:"MI065 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4340,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI065",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI065",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4340,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI065", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5340 ,protection_group_id: -4340, protection_element_id:-4340], primaryKey: false);
      insert('organizations', [ id: 104326, nci_institute_code: "MI066", name: "Mount Clemens Regional Medical Center", city: "Mount Clemens", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4341,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI066",GROUP_DESC:"MI066 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4341,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI066",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI066",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4341,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI066", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5341 ,protection_group_id: -4341, protection_element_id:-4341], primaryKey: false);
      insert('organizations', [ id: 104327, nci_institute_code: "MI067", name: "Meridian Medical Center", city: "Okemos", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4342,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI067",GROUP_DESC:"MI067 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4342,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI067",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI067",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4342,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI067", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5342 ,protection_group_id: -4342, protection_element_id:-4342], primaryKey: false);
      insert('organizations', [ id: 104328, nci_institute_code: "MI068", name: "Cancer Care Associates PC", city: "Royal Oak", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4343,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI068",GROUP_DESC:"MI068 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4343,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI068",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI068",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4343,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI068", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5343 ,protection_group_id: -4343, protection_element_id:-4343], primaryKey: false);
      insert('organizations', [ id: 104329, nci_institute_code: "MI069", name: "Macomb Hospital Center", city: "Warren", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4344,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI069",GROUP_DESC:"MI069 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4344,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI069",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI069",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4344,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI069", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5344 ,protection_group_id: -4344, protection_element_id:-4344], primaryKey: false);
      insert('organizations', [ id: 104330, nci_institute_code: "MI070", name: "Bixby Medical Center", city: "Adrian", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4345,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI070",GROUP_DESC:"MI070 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4345,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI070",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI070",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4345,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI070", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5345 ,protection_group_id: -4345, protection_element_id:-4345], primaryKey: false);
      insert('organizations', [ id: 104331, nci_institute_code: "MI071", name: "Community Health Center", city: "Coldwater", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4346,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI071",GROUP_DESC:"MI071 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4346,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI071",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI071",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4346,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI071", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5346 ,protection_group_id: -4346, protection_element_id:-4346], primaryKey: false);
      insert('organizations', [ id: 104332, nci_institute_code: "MI072", name: "Saint Lawrence Hospital", city: "Lansing", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4347,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI072",GROUP_DESC:"MI072 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4347,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI072",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI072",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4347,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI072", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5347 ,protection_group_id: -4347, protection_element_id:-4347], primaryKey: false);
      insert('organizations', [ id: 104333, nci_institute_code: "MI074", name: "The Breslin Cancer Center", city: "Lansing", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4348,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI074",GROUP_DESC:"MI074 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4348,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI074",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI074",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4348,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI074", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5348 ,protection_group_id: -4348, protection_element_id:-4348], primaryKey: false);
      insert('organizations', [ id: 104334, nci_institute_code: "MI075", name: "Hackley Hospital", city: "Muskegon", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4349,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI075",GROUP_DESC:"MI075 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4349,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI075",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI075",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4349,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI075", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5349 ,protection_group_id: -4349, protection_element_id:-4349], primaryKey: false);
      insert('organizations', [ id: 104335, nci_institute_code: "MI076", name: "Holland Community Hospital", city: "Holland", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4350,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI076",GROUP_DESC:"MI076 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4350,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI076",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI076",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4350,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI076", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5350 ,protection_group_id: -4350, protection_element_id:-4350], primaryKey: false);
      insert('organizations', [ id: 104336, nci_institute_code: "MI077", name: "Bon Secours Hospital", city: "Grosse Pointe", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4351,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI077",GROUP_DESC:"MI077 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4351,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI077",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI077",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4351,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI077", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5351 ,protection_group_id: -4351, protection_element_id:-4351], primaryKey: false);
      insert('organizations', [ id: 104337, nci_institute_code: "MI078", name: "Lapeer Regional Hospital", city: "Lapeer", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4352,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI078",GROUP_DESC:"MI078 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4352,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI078",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI078",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4352,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI078", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5352 ,protection_group_id: -4352, protection_element_id:-4352], primaryKey: false);
      insert('organizations', [ id: 104338, nci_institute_code: "MI079", name: "W A Foote Memorial Hospital", city: "Jackson", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4353,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI079",GROUP_DESC:"MI079 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4353,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI079",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI079",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4353,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI079", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5353 ,protection_group_id: -4353, protection_element_id:-4353], primaryKey: false);
      insert('organizations', [ id: 104339, nci_institute_code: "MI080", name: "West Michigan Cancer Center", city: "Kalamazoo", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4354,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI080",GROUP_DESC:"MI080 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4354,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI080",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI080",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4354,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI080", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5354 ,protection_group_id: -4354, protection_element_id:-4354], primaryKey: false);
      insert('organizations', [ id: 104340, nci_institute_code: "MI081", name: "Northern Michigan Hospital", city: "Petoskey", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4355,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI081",GROUP_DESC:"MI081 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4355,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI081",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI081",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4355,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI081", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5355 ,protection_group_id: -4355, protection_element_id:-4355], primaryKey: false);
      insert('organizations', [ id: 104341, nci_institute_code: "MI082", name: "Port Huron Hospital", city: "Port Huron", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4356,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI082",GROUP_DESC:"MI082 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4356,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI082",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI082",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4356,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI082", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5356 ,protection_group_id: -4356, protection_element_id:-4356], primaryKey: false);
      insert('organizations', [ id: 104342, nci_institute_code: "MI083", name: "Riverside Osteopathic", city: "Trenton", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4357,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI083",GROUP_DESC:"MI083 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4357,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI083",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI083",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4357,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI083", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5357 ,protection_group_id: -4357, protection_element_id:-4357], primaryKey: false);
      insert('organizations', [ id: 104343, nci_institute_code: "MI084", name: "Saint Luke's Hospital", city: "Saginaw", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4358,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI084",GROUP_DESC:"MI084 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4358,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI084",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI084",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4358,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI084", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5358 ,protection_group_id: -4358, protection_element_id:-4358], primaryKey: false);
      insert('organizations', [ id: 104344, nci_institute_code: "MI085", name: "Metropolitan Detroit Minority Based CCOP", city: "Southfield", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4359,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI085",GROUP_DESC:"MI085 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4359,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI085",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI085",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4359,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI085", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5359 ,protection_group_id: -4359, protection_element_id:-4359], primaryKey: false);
      insert('organizations', [ id: 104345, nci_institute_code: "MI086", name: "Michigan Cancer Research Consortium Community Clinical Oncology Program", city: "Ann Arbor", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4360,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI086",GROUP_DESC:"MI086 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4360,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI086",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI086",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4360,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI086", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5360 ,protection_group_id: -4360, protection_element_id:-4360], primaryKey: false);
      insert('organizations', [ id: 104346, nci_institute_code: "MI087", name: "C.S. Mott Children's Hospital", city: "Ann Arbor", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4361,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI087",GROUP_DESC:"MI087 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4361,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI087",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI087",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4361,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI087", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5361 ,protection_group_id: -4361, protection_element_id:-4361], primaryKey: false);
      insert('organizations', [ id: 104347, nci_institute_code: "MI088", name: "Genesys Regional Medical Center", city: "Flint", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4362,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI088",GROUP_DESC:"MI088 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4362,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI088",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI088",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4362,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI088", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5362 ,protection_group_id: -4362, protection_element_id:-4362], primaryKey: false);
    }

    void m14() {
        // all14 (25 terms)
      insert('organizations', [ id: 104348, nci_institute_code: "MI090", name: "Comprehensive Hematology/Oncology Physicians, P.C.", city: "Dearborn", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4363,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI090",GROUP_DESC:"MI090 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4363,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI090",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI090",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4363,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI090", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5363 ,protection_group_id: -4363, protection_element_id:-4363], primaryKey: false);
      insert('organizations', [ id: 104349, nci_institute_code: "MI091", name: "Sinai Oakland Internists and Association", city: "Southfield", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4364,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI091",GROUP_DESC:"MI091 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4364,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI091",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI091",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4364,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI091", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5364 ,protection_group_id: -4364, protection_element_id:-4364], primaryKey: false);
      insert('organizations', [ id: 104350, nci_institute_code: "MI092", name: "Mid-Michigan Physicians PC", city: "Lansing", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4365,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI092",GROUP_DESC:"MI092 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4365,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI092",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI092",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4365,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI092", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5365 ,protection_group_id: -4365, protection_element_id:-4365], primaryKey: false);
      insert('organizations', [ id: 104351, nci_institute_code: "MI093", name: "Sinai Women's Center", city: "Farmington", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4366,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI093",GROUP_DESC:"MI093 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4366,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI093",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI093",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4366,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI093", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5366 ,protection_group_id: -4366, protection_element_id:-4366], primaryKey: false);
      insert('organizations', [ id: 104352, nci_institute_code: "MI094", name: "Court One Medical Center", city: "Lansing", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4367,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI094",GROUP_DESC:"MI094 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4367,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI094",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI094",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4367,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI094", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5367 ,protection_group_id: -4367, protection_element_id:-4367], primaryKey: false);
      insert('organizations', [ id: 104353, nci_institute_code: "MI095", name: "Code Available", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4368,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI095",GROUP_DESC:"MI095 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4368,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI095",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI095",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4368,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI095", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5368 ,protection_group_id: -4368, protection_element_id:-4368], primaryKey: false);
      insert('organizations', [ id: 104354, nci_institute_code: "MI096", name: "Center for Hematology- Oncology of Southern Michigan P.L.C.", city: "Jackson", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4369,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI096",GROUP_DESC:"MI096 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4369,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI096",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI096",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4369,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI096", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5369 ,protection_group_id: -4369, protection_element_id:-4369], primaryKey: false);
      insert('organizations', [ id: 104355, nci_institute_code: "MI097", name: "Brighton Family Care Specialists", city: "Brighton", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4370,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI097",GROUP_DESC:"MI097 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4370,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI097",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI097",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4370,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI097", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5370 ,protection_group_id: -4370, protection_element_id:-4370], primaryKey: false);
      insert('organizations', [ id: 104356, nci_institute_code: "MI098", name: "Flint Osteopathic Hospital", city: "Flint", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4371,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI098",GROUP_DESC:"MI098 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4371,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI098",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI098",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4371,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI098", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5371 ,protection_group_id: -4371, protection_element_id:-4371], primaryKey: false);
      insert('organizations', [ id: 104357, nci_institute_code: "MI099", name: "Ann Arbor Hematology -Oncology Associates", city: "Ypsilanti", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4372,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI099",GROUP_DESC:"MI099 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4372,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI099",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI099",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4372,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI099", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5372 ,protection_group_id: -4372, protection_element_id:-4372], primaryKey: false);
      insert('organizations', [ id: 104358, nci_institute_code: "MI101", name: "X-Ray Treatment Center", city: "East Pointe", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4373,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI101",GROUP_DESC:"MI101 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4373,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI101",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI101",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4373,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI101", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5373 ,protection_group_id: -4373, protection_element_id:-4373], primaryKey: false);
      insert('organizations', [ id: 104359, nci_institute_code: "MI102", name: "General Vascular Surg. Assoc., P.C.", city: "Ann Arbor", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4374,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI102",GROUP_DESC:"MI102 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4374,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI102",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI102",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4374,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI102", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5374 ,protection_group_id: -4374, protection_element_id:-4374], primaryKey: false);
      insert('organizations', [ id: 104360, nci_institute_code: "MI106", name: "Cadillac Mercy Hospital", city: "Cadillac", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4375,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI106",GROUP_DESC:"MI106 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4375,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI106",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI106",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4375,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI106", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5375 ,protection_group_id: -4375, protection_element_id:-4375], primaryKey: false);
      insert('organizations', [ id: 104361, nci_institute_code: "MI107", name: "Huron Medical Center, P.C.", city: "Port Huron", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4376,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI107",GROUP_DESC:"MI107 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4376,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI107",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI107",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4376,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI107", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5376 ,protection_group_id: -4376, protection_element_id:-4376], primaryKey: false);
      insert('organizations', [ id: 104362, nci_institute_code: "MI108", name: "Lakeland Hospital", city: "St. Joseph", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4377,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI108",GROUP_DESC:"MI108 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4377,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI108",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI108",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4377,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI108", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5377 ,protection_group_id: -4377, protection_element_id:-4377], primaryKey: false);
      insert('organizations', [ id: 104363, nci_institute_code: "MI109", name: "Cancer & Hematology Ctrs of Western Michigan", city: "Grand Rapids", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4378,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI109",GROUP_DESC:"MI109 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4378,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI109",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI109",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4378,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI109", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5378 ,protection_group_id: -4378, protection_element_id:-4378], primaryKey: false);
      insert('organizations', [ id: 104364, nci_institute_code: "MI110", name: "North Oakland Medical Center", city: "Pontiac", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4379,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI110",GROUP_DESC:"MI110 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4379,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI110",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI110",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4379,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI110", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5379 ,protection_group_id: -4379, protection_element_id:-4379], primaryKey: false);
      insert('organizations', [ id: 104365, nci_institute_code: "MI111", name: "Hematology/Oncology", city: "Clinton Township", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4380,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI111",GROUP_DESC:"MI111 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4380,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI111",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI111",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4380,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI111", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5380 ,protection_group_id: -4380, protection_element_id:-4380], primaryKey: false);
      insert('organizations', [ id: 104366, nci_institute_code: "MI112", name: "Michigan Cancer Institute", city: "Pontiac", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4381,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI112",GROUP_DESC:"MI112 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4381,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI112",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI112",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4381,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI112", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5381 ,protection_group_id: -4381, protection_element_id:-4381], primaryKey: false);
      insert('organizations', [ id: 104367, nci_institute_code: "MI113", name: "Comprehensive Cancer Cenrter Metropolitan Detroit", city: "Detroit", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4382,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI113",GROUP_DESC:"MI113 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4382,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI113",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI113",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4382,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI113", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5382 ,protection_group_id: -4382, protection_element_id:-4382], primaryKey: false);
      insert('organizations', [ id: 104368, nci_institute_code: "MI115", name: "Mount. Zion Medical Center", city: "Ann Arbor", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4383,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI115",GROUP_DESC:"MI115 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4383,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI115",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI115",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4383,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI115", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5383 ,protection_group_id: -4383, protection_element_id:-4383], primaryKey: false);
      insert('organizations', [ id: 104369, nci_institute_code: "MI116", name: "Mecosta County Medical Center", city: "Big Rapids", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4384,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI116",GROUP_DESC:"MI116 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4384,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI116",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI116",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4384,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI116", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5384 ,protection_group_id: -4384, protection_element_id:-4384], primaryKey: false);
      insert('organizations', [ id: 104370, nci_institute_code: "MI117", name: "Metro Health Hospital", city: "Grand Rapids", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4385,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI117",GROUP_DESC:"MI117 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4385,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI117",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI117",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4385,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI117", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5385 ,protection_group_id: -4385, protection_element_id:-4385], primaryKey: false);
      insert('organizations', [ id: 104371, nci_institute_code: "MI118", name: "Community Cancer Care Specialists", city: "Mount Clemens", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4386,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI118",GROUP_DESC:"MI118 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4386,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI118",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI118",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4386,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI118", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5386 ,protection_group_id: -4386, protection_element_id:-4386], primaryKey: false);
      insert('organizations', [ id: 104372, nci_institute_code: "MI119", name: "Mid-Michigan Medical Center - Midland", city: "Midland", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4387,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI119",GROUP_DESC:"MI119 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4387,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI119",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI119",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4387,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI119", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5387 ,protection_group_id: -4387, protection_element_id:-4387], primaryKey: false);
    }

    void m15() {
        // all15 (25 terms)
      insert('organizations', [ id: 104373, nci_institute_code: "MI120", name: "Covenant Health Systems", city: "Saginaw", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4388,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI120",GROUP_DESC:"MI120 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4388,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI120",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI120",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4388,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI120", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5388 ,protection_group_id: -4388, protection_element_id:-4388], primaryKey: false);
      insert('organizations', [ id: 104374, nci_institute_code: "MI122", name: "Memorial Healthcare Center", city: "Owosso", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4389,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI122",GROUP_DESC:"MI122 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4389,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI122",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI122",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4389,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI122", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5389 ,protection_group_id: -4389, protection_element_id:-4389], primaryKey: false);
      insert('organizations', [ id: 104375, nci_institute_code: "MI124", name: "Alpena General Hospital", city: "Alpena", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4390,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI124",GROUP_DESC:"MI124 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4390,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI124",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI124",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4390,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI124", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5390 ,protection_group_id: -4390, protection_element_id:-4390], primaryKey: false);
      insert('organizations', [ id: 104376, nci_institute_code: "MI125", name: "Cottage Hospital", city: "Grosse Pointe Farms", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4391,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI125",GROUP_DESC:"MI125 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4391,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI125",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI125",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4391,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI125", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5391 ,protection_group_id: -4391, protection_element_id:-4391], primaryKey: false);
      insert('organizations', [ id: 104377, nci_institute_code: "MI127", name: "Huron Valley/Sinai Hospital", city: "Commerce", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4392,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI127",GROUP_DESC:"MI127 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4392,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI127",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI127",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4392,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI127", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5392 ,protection_group_id: -4392, protection_element_id:-4392], primaryKey: false);
      insert('organizations', [ id: 104378, nci_institute_code: "MI128", name: "William Beaumont Hospital - Troy", city: "Troy", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4393,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI128",GROUP_DESC:"MI128 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4393,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI128",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI128",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4393,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI128", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5393 ,protection_group_id: -4393, protection_element_id:-4393], primaryKey: false);
      insert('organizations', [ id: 104379, nci_institute_code: "MI129", name: "Blue Water Oncology", city: "Port Huron", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4394,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI129",GROUP_DESC:"MI129 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4394,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI129",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI129",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4394,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI129", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5394 ,protection_group_id: -4394, protection_element_id:-4394], primaryKey: false);
      insert('organizations', [ id: 104380, nci_institute_code: "MI130", name: "Crittenton Hospital", city: "Rochester", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4395,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI130",GROUP_DESC:"MI130 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4395,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI130",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI130",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4395,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI130", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5395 ,protection_group_id: -4395, protection_element_id:-4395], primaryKey: false);
      insert('organizations', [ id: 104381, nci_institute_code: "MI131", name: "Downriver Center for Oncology", city: "Trenton", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4396,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI131",GROUP_DESC:"MI131 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4396,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI131",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI131",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4396,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI131", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5396 ,protection_group_id: -4396, protection_element_id:-4396], primaryKey: false);
      insert('organizations', [ id: 104382, nci_institute_code: "MI132", name: "Spectrum Health-Butterworth Campus", city: "Grand Rapids", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4397,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI132",GROUP_DESC:"MI132 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4397,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI132",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI132",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4397,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI132", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5397 ,protection_group_id: -4397, protection_element_id:-4397], primaryKey: false);
      insert('organizations', [ id: 104383, nci_institute_code: "MI133", name: "Spectrum Health-Blodgett Campus", city: "Grand Rapids", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4398,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI133",GROUP_DESC:"MI133 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4398,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI133",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI133",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4398,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI133", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5398 ,protection_group_id: -4398, protection_element_id:-4398], primaryKey: false);
      insert('organizations', [ id: 104384, nci_institute_code: "MI134", name: "Cancer and Hematology Clinic, Ostego Memorial Hospital", city: "Gaylord", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4399,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI134",GROUP_DESC:"MI134 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4399,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI134",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI134",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4399,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI134", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5399 ,protection_group_id: -4399, protection_element_id:-4399], primaryKey: false);
      insert('organizations', [ id: 104385, nci_institute_code: "MI135", name: "Henry Ford Medical Center - West Bloomfield", city: "West Bloomfield", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4400,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI135",GROUP_DESC:"MI135 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4400,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI135",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI135",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4400,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI135", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5400 ,protection_group_id: -4400, protection_element_id:-4400], primaryKey: false);
      insert('organizations', [ id: 104386, nci_institute_code: "MI136", name: "Children's Hospital of Michigan", city: "Detroit", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4401,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI136",GROUP_DESC:"MI136 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4401,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI136",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI136",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4401,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI136", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5401 ,protection_group_id: -4401, protection_element_id:-4401], primaryKey: false);
      insert('organizations', [ id: 104387, nci_institute_code: "MI137", name: "Saint Joseph Mercy of Macomb", city: "Clinton Township", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4402,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI137",GROUP_DESC:"MI137 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4402,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI137",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI137",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4402,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI137", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5402 ,protection_group_id: -4402, protection_element_id:-4402], primaryKey: false);
      insert('organizations', [ id: 104388, nci_institute_code: "MI139", name: "Saint John Macomb Hospital", city: "Warren", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4403,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI139",GROUP_DESC:"MI139 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4403,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI139",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI139",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4403,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI139", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5403 ,protection_group_id: -4403, protection_element_id:-4403], primaryKey: false);
      insert('organizations', [ id: 104389, nci_institute_code: "MI140", name: "Ingham Regional Medical Center", city: "Lansing", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4404,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI140",GROUP_DESC:"MI140 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4404,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI140",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI140",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4404,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI140", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5404 ,protection_group_id: -4404, protection_element_id:-4404], primaryKey: false);
      insert('organizations', [ id: 104390, nci_institute_code: "MI142", name: "Saint Mary's of Michigan", city: "Saginaw", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4405,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI142",GROUP_DESC:"MI142 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4405,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI142",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI142",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4405,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI142", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5405 ,protection_group_id: -4405, protection_element_id:-4405], primaryKey: false);
      insert('organizations', [ id: 104391, nci_institute_code: "MI144", name: "Southwest Michigan Surgery", city: "Kalamazoo", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4406,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI144",GROUP_DESC:"MI144 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4406,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI144",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI144",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4406,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI144", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5406 ,protection_group_id: -4406, protection_element_id:-4406], primaryKey: false);
      insert('organizations', [ id: 104392, nci_institute_code: "MI145", name: "Michigan Comprehensive Cancer Ins't", city: "East Point", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4407,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI145",GROUP_DESC:"MI145 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4407,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI145",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI145",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4407,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI145", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5407 ,protection_group_id: -4407, protection_element_id:-4407], primaryKey: false);
      insert('organizations', [ id: 104393, nci_institute_code: "MI146", name: "Oncology and Hematology P.C.", city: "Kalamazoo", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4408,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI146",GROUP_DESC:"MI146 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4408,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI146",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI146",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4408,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI146", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5408 ,protection_group_id: -4408, protection_element_id:-4408], primaryKey: false);
      insert('organizations', [ id: 104394, nci_institute_code: "MI147", name: "Kalamazoo CCOP", city: "Kalamazoo", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4409,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI147",GROUP_DESC:"MI147 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4409,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI147",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI147",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4409,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI147", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5409 ,protection_group_id: -4409, protection_element_id:-4409], primaryKey: false);
      insert('organizations', [ id: 104395, nci_institute_code: "MI148", name: "Bay Regional Oncology Center", city: "Bay City", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4410,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI148",GROUP_DESC:"MI148 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4410,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI148",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI148",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4410,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI148", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5410 ,protection_group_id: -4410, protection_element_id:-4410], primaryKey: false);
      insert('organizations', [ id: 104396, nci_institute_code: "MI149", name: "Genesys Hurley Cancer Institute", city: "Flint", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4411,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI149",GROUP_DESC:"MI149 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4411,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI149",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI149",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4411,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI149", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5411 ,protection_group_id: -4411, protection_element_id:-4411], primaryKey: false);
      insert('organizations', [ id: 104397, nci_institute_code: "MI150", name: "John D Dingell Veterans Administration Medical Center", city: "Detroit", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4412,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI150",GROUP_DESC:"MI150 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4412,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI150",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI150",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4412,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI150", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5412 ,protection_group_id: -4412, protection_element_id:-4412], primaryKey: false);
    }

    void m16() {
        // all16 (25 terms)
      insert('organizations', [ id: 104398, nci_institute_code: "MI151", name: "Kalamazoo Hematology & Oncology", city: "Kalamazoo", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4413,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI151",GROUP_DESC:"MI151 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4413,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI151",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI151",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4413,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI151", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5413 ,protection_group_id: -4413, protection_element_id:-4413], primaryKey: false);
      insert('organizations', [ id: 104399, nci_institute_code: "MI152", name: "Van Elslander Cancer Center", city: "Grosse Pointe Woods", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4414,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI152",GROUP_DESC:"MI152 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4414,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI152",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI152",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4414,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI152", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5414 ,protection_group_id: -4414, protection_element_id:-4414], primaryKey: false);
      insert('organizations', [ id: 104400, nci_institute_code: "MI153", name: "Monroe Clinic", city: "Monroe", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4415,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI153",GROUP_DESC:"MI153 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4415,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI153",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI153",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4415,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI153", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5415 ,protection_group_id: -4415, protection_element_id:-4415], primaryKey: false);
      insert('organizations', [ id: 104401, nci_institute_code: "MI154", name: "Michigan Cancer Specialists", city: "Roseville", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4416,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI154",GROUP_DESC:"MI154 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4416,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI154",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI154",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4416,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI154", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5416 ,protection_group_id: -4416, protection_element_id:-4416], primaryKey: false);
      insert('organizations', [ id: 104402, nci_institute_code: "MI155", name: "Hematology Consultants of West Michigan, P.C.", city: "Grand Rapids", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4417,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI155",GROUP_DESC:"MI155 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4417,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI155",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI155",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4417,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI155", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5417 ,protection_group_id: -4417, protection_element_id:-4417], primaryKey: false);
      insert('organizations', [ id: 104403, nci_institute_code: "MI156", name: "Family Urology Associates", city: "Grand Rapids", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4418,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI156",GROUP_DESC:"MI156 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4418,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI156",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI156",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4418,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI156", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5418 ,protection_group_id: -4418, protection_element_id:-4418], primaryKey: false);
      insert('organizations', [ id: 104404, nci_institute_code: "MI157", name: "Marquette Medical Center", city: "Escanaba", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4419,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI157",GROUP_DESC:"MI157 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4419,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI157",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI157",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4419,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI157", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5419 ,protection_group_id: -4419, protection_element_id:-4419], primaryKey: false);
      insert('organizations', [ id: 104405, nci_institute_code: "MI158", name: "Wyoming Family Practice", city: "Wyoming", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4420,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI158",GROUP_DESC:"MI158 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4420,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI158",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI158",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4420,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI158", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5420 ,protection_group_id: -4420, protection_element_id:-4420], primaryKey: false);
      insert('organizations', [ id: 104406, nci_institute_code: "MI159", name: "University of Detroit Mercy", city: "Detroit", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4421,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI159",GROUP_DESC:"MI159 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4421,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI159",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI159",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4421,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI159", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5421 ,protection_group_id: -4421, protection_element_id:-4421], primaryKey: false);
      insert('organizations', [ id: 104407, nci_institute_code: "MI160", name: "Osteopathic Medical Oncology and Hematology", city: "Riverview", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4422,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI160",GROUP_DESC:"MI160 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4422,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI160",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI160",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4422,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI160", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5422 ,protection_group_id: -4422, protection_element_id:-4422], primaryKey: false);
      insert('organizations', [ id: 104408, nci_institute_code: "MI161", name: "Grand View Clinic", city: "Ironwood", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4423,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI161",GROUP_DESC:"MI161 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4423,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI161",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI161",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4423,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI161", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5423 ,protection_group_id: -4423, protection_element_id:-4423], primaryKey: false);
      insert('organizations', [ id: 104409, nci_institute_code: "MI162", name: "Oncology Care Associates PLLC", city: "Saint Joseph", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4424,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI162",GROUP_DESC:"MI162 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4424,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI162",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI162",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4424,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI162", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5424 ,protection_group_id: -4424, protection_element_id:-4424], primaryKey: false);
      insert('organizations', [ id: 104410, nci_institute_code: "MI164", name: "Michigan Comprehensive Cancer Institute", city: "Macomb", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4425,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI164",GROUP_DESC:"MI164 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4425,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI164",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI164",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4425,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI164", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5425 ,protection_group_id: -4425, protection_element_id:-4425], primaryKey: false);
      insert('organizations', [ id: 104411, nci_institute_code: "MI165", name: "Urologic Consultants, PC", city: "Grand Rapids", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4426,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI165",GROUP_DESC:"MI165 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4426,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI165",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI165",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4426,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI165", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5426 ,protection_group_id: -4426, protection_element_id:-4426], primaryKey: false);
      insert('organizations', [ id: 104412, nci_institute_code: "MI166", name: "Gynecologic Oncology of West Michigan PLLC", city: "Grand Rapids", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4427,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI166",GROUP_DESC:"MI166 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4427,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI166",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI166",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4427,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI166", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5427 ,protection_group_id: -4427, protection_element_id:-4427], primaryKey: false);
      insert('organizations', [ id: 104413, nci_institute_code: "MI167", name: "Michigan Medical PC", city: "Grand Rapids", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4428,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI167",GROUP_DESC:"MI167 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4428,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI167",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI167",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4428,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI167", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5428 ,protection_group_id: -4428, protection_element_id:-4428], primaryKey: false);
      insert('organizations', [ id: 104414, nci_institute_code: "MI168", name: "Towers Surgeon, PC", city: "Grand Rapids", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4429,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI168",GROUP_DESC:"MI168 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4429,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI168",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI168",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4429,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI168", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5429 ,protection_group_id: -4429, protection_element_id:-4429], primaryKey: false);
      insert('organizations', [ id: 104415, nci_institute_code: "MI169", name: "McLaren Regional Cancer Center", city: "Flint", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4430,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI169",GROUP_DESC:"MI169 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4430,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI169",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI169",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4430,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI169", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5430 ,protection_group_id: -4430, protection_element_id:-4430], primaryKey: false);
      insert('organizations', [ id: 104416, nci_institute_code: "MI170", name: "U.P. Hematology/Oncology Associates", city: "Hancock", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4431,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI170",GROUP_DESC:"MI170 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4431,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI170",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI170",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4431,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI170", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5431 ,protection_group_id: -4431, protection_element_id:-4431], primaryKey: false);
      insert('organizations', [ id: 104417, nci_institute_code: "MI171", name: "Grand River Surgical Specialists PC", city: "Wyoming", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4432,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI171",GROUP_DESC:"MI171 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4432,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI171",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI171",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4432,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI171", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5432 ,protection_group_id: -4432, protection_element_id:-4432], primaryKey: false);
      insert('organizations', [ id: 104418, nci_institute_code: "MI172", name: "Barbara Ann Karmanos Cancer Institute", city: "Detroit", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4433,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI172",GROUP_DESC:"MI172 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4433,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI172",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI172",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4433,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI172", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5433 ,protection_group_id: -4433, protection_element_id:-4433], primaryKey: false);
      insert('organizations', [ id: 104419, nci_institute_code: "MI173", name: "Michigan State University Department of Surgery", city: "Lansing", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4434,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI173",GROUP_DESC:"MI173 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4434,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI173",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI173",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4434,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI173", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5434 ,protection_group_id: -4434, protection_element_id:-4434], primaryKey: false);
      insert('organizations', [ id: 104420, nci_institute_code: "MI174", name: "Green Bay Oncology - Iron Mountain", city: "Iron Mountain", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4435,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI174",GROUP_DESC:"MI174 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4435,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI174",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI174",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4435,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI174", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5435 ,protection_group_id: -4435, protection_element_id:-4435], primaryKey: false);
      insert('organizations', [ id: 104421, nci_institute_code: "MI175", name: "Green Bay Oncology - Escanaba", city: "Escanaba", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4436,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI175",GROUP_DESC:"MI175 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4436,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI175",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI175",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4436,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI175", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5436 ,protection_group_id: -4436, protection_element_id:-4436], primaryKey: false);
      insert('organizations', [ id: 104422, nci_institute_code: "MI176", name: "Osteopathic Medical Oncology and Hematology", city: "Clinton Township", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4437,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI176",GROUP_DESC:"MI176 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4437,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI176",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI176",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4437,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI176", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5437 ,protection_group_id: -4437, protection_element_id:-4437], primaryKey: false);
    }

    void m17() {
        // all17 (25 terms)
      insert('organizations', [ id: 104423, nci_institute_code: "MI177", name: "Parvez Khan Oncology Clinic", city: "Dearborn", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4438,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI177",GROUP_DESC:"MI177 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4438,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI177",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI177",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4438,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI177", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5438 ,protection_group_id: -4438, protection_element_id:-4438], primaryKey: false);
      insert('organizations', [ id: 104424, nci_institute_code: "MI178", name: "Lakewood Family Medicine", city: "Holland", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4439,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI178",GROUP_DESC:"MI178 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4439,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI178",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI178",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4439,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI178", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5439 ,protection_group_id: -4439, protection_element_id:-4439], primaryKey: false);
      insert('organizations', [ id: 104425, nci_institute_code: "MI179", name: "Healthquest Surgical Associates", city: "Grand Rapids", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4440,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI179",GROUP_DESC:"MI179 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4440,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI179",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI179",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4440,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI179", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5440 ,protection_group_id: -4440, protection_element_id:-4440], primaryKey: false);
      insert('organizations', [ id: 104426, nci_institute_code: "MI180", name: "Bay Area Urology Associates", city: "Traverse City", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4441,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI180",GROUP_DESC:"MI180 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4441,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI180",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI180",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4441,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI180", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5441 ,protection_group_id: -4441, protection_element_id:-4441], primaryKey: false);
      insert('organizations', [ id: 104427, nci_institute_code: "MI181", name: "Hematology Oncology Associates of Ohio and Michigan, PC", city: "Lambertville", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4442,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI181",GROUP_DESC:"MI181 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4442,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI181",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI181",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4442,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI181", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5442 ,protection_group_id: -4442, protection_element_id:-4442], primaryKey: false);
      insert('organizations', [ id: 104428, nci_institute_code: "MI182", name: "Oakland Colon & Rectal Association", city: "Royal Oak", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4443,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI182",GROUP_DESC:"MI182 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4443,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI182",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI182",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4443,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI182", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5443 ,protection_group_id: -4443, protection_element_id:-4443], primaryKey: false);
      insert('organizations', [ id: 104429, nci_institute_code: "MI183", name: "Michiana Hematology-Oncology of Michigan, P.C.", city: "St. Joseph", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4444,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI183",GROUP_DESC:"MI183 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4444,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI183",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI183",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4444,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI183", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5444 ,protection_group_id: -4444, protection_element_id:-4444], primaryKey: false);
      insert('organizations', [ id: 104430, nci_institute_code: "MI184", name: "Preferred Urology Consultants, P.C.", city: "Southfield", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4445,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI184",GROUP_DESC:"MI184 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4445,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI184",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI184",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4445,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI184", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5445 ,protection_group_id: -4445, protection_element_id:-4445], primaryKey: false);
      insert('organizations', [ id: 104431, nci_institute_code: "MI185", name: "Gynecologic Oncology Specialists of Michigan", city: "Lansing", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4446,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI185",GROUP_DESC:"MI185 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4446,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI185",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI185",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4446,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI185", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5446 ,protection_group_id: -4446, protection_element_id:-4446], primaryKey: false);
      insert('organizations', [ id: 104432, nci_institute_code: "MI186", name: "Koshy & Koshy M.D., P.C.", city: "St. Joseph", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4447,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI186",GROUP_DESC:"MI186 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4447,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI186",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI186",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4447,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI186", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5447 ,protection_group_id: -4447, protection_element_id:-4447], primaryKey: false);
      insert('organizations', [ id: 104433, nci_institute_code: "MI187", name: "Hickman Cancer Center", city: "Adrian", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4448,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI187",GROUP_DESC:"MI187 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4448,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI187",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI187",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4448,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI187", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5448 ,protection_group_id: -4448, protection_element_id:-4448], primaryKey: false);
      insert('organizations', [ id: 104434, nci_institute_code: "MI188", name: "Community Cancer Center of Monroe", city: "Monroe", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4449,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI188",GROUP_DESC:"MI188 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4449,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI188",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI188",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4449,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI188", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5449 ,protection_group_id: -4449, protection_element_id:-4449], primaryKey: false);
      insert('organizations', [ id: 104435, nci_institute_code: "MI189", name: "Cancer and Transplant Consultants, PLC", city: "Rochester", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4450,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI189",GROUP_DESC:"MI189 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4450,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI189",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI189",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4450,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI189", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5450 ,protection_group_id: -4450, protection_element_id:-4450], primaryKey: false);
      insert('organizations', [ id: 104436, nci_institute_code: "MI190", name: "Dickinson County Healthcare System", city: "Iron Mountain", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4451,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI190",GROUP_DESC:"MI190 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4451,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI190",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI190",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4451,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI190", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5451 ,protection_group_id: -4451, protection_element_id:-4451], primaryKey: false);
      insert('organizations', [ id: 104437, nci_institute_code: "MI192", name: "Providence Cancer Institute", city: "Southfield", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4452,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI192",GROUP_DESC:"MI192 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4452,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI192",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI192",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4452,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI192", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5452 ,protection_group_id: -4452, protection_element_id:-4452], primaryKey: false);
      insert('organizations', [ id: 104438, nci_institute_code: "MI193", name: "Rochester Colon & Rectal Surgery, PC", city: "Rochester Hills", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4453,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI193",GROUP_DESC:"MI193 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4453,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI193",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI193",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4453,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI193", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5453 ,protection_group_id: -4453, protection_element_id:-4453], primaryKey: false);
      insert('organizations', [ id: 104439, nci_institute_code: "MI194", name: "Newland Medical Associates", city: "Southfield", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4454,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI194",GROUP_DESC:"MI194 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4454,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI194",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI194",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4454,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI194", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5454 ,protection_group_id: -4454, protection_element_id:-4454], primaryKey: false);
      insert('organizations', [ id: 104440, nci_institute_code: "MI195", name: "Premier Surgical Specialists, PC", city: "Rochester Hills", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4455,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI195",GROUP_DESC:"MI195 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4455,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI195",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI195",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4455,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI195", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5455 ,protection_group_id: -4455, protection_element_id:-4455], primaryKey: false);
      insert('organizations', [ id: 104441, nci_institute_code: "MI196", name: "West Michigan Surgical", city: "Grand Rapids", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4456,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI196",GROUP_DESC:"MI196 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4456,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI196",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI196",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4456,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI196", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5456 ,protection_group_id: -4456, protection_element_id:-4456], primaryKey: false);
      insert('organizations', [ id: 104442, nci_institute_code: "MI197", name: "Genesee Hematology Oncology, PC", city: "Flint", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4457,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI197",GROUP_DESC:"MI197 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4457,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI197",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI197",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4457,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI197", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5457 ,protection_group_id: -4457, protection_element_id:-4457], primaryKey: false);
      insert('organizations', [ id: 104443, nci_institute_code: "MI198", name: "Northern Michigan Hematology/Oncology", city: "Petoskey", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4458,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI198",GROUP_DESC:"MI198 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4458,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI198",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI198",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4458,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI198", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5458 ,protection_group_id: -4458, protection_element_id:-4458], primaryKey: false);
      insert('organizations', [ id: 104444, nci_institute_code: "MI199", name: "Martin I Schock MD PC", city: "Warren", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4459,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI199",GROUP_DESC:"MI199 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4459,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI199",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI199",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4459,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI199", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5459 ,protection_group_id: -4459, protection_element_id:-4459], primaryKey: false);
      insert('organizations', [ id: 104445, nci_institute_code: "MI200", name: "Van Andel Research Institute", city: "Grand Rapids ", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4460,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI200",GROUP_DESC:"MI200 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4460,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI200",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI200",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4460,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI200", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5460 ,protection_group_id: -4460, protection_element_id:-4460], primaryKey: false);
      insert('organizations', [ id: 104446, nci_institute_code: "MI201", name: "Advanced General and Oncological Surgery Asscociates", city: "Flint", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4461,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI201",GROUP_DESC:"MI201 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4461,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI201",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI201",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4461,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI201", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5461 ,protection_group_id: -4461, protection_element_id:-4461], primaryKey: false);
      insert('organizations', [ id: 104447, nci_institute_code: "MI202", name: "Great Lakes Cancer Management Specialists", city: "Grosse Point Woods", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4462,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI202",GROUP_DESC:"MI202 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4462,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI202",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI202",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4462,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI202", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5462 ,protection_group_id: -4462, protection_element_id:-4462], primaryKey: false);
    }

    void m18() {
        // all18 (25 terms)
      insert('organizations', [ id: 104448, nci_institute_code: "MI203", name: "Huron Valley Urology Associates", city: "Ypsilanti", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4463,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI203",GROUP_DESC:"MI203 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4463,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI203",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI203",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4463,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI203", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5463 ,protection_group_id: -4463, protection_element_id:-4463], primaryKey: false);
      insert('organizations', [ id: 104449, nci_institute_code: "MI204", name: "East Central Oncology Associates PLC", city: "Midland", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4464,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI204",GROUP_DESC:"MI204 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4464,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI204",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI204",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4464,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI204", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5464 ,protection_group_id: -4464, protection_element_id:-4464], primaryKey: false);
      insert('organizations', [ id: 104450, nci_institute_code: "MI205", name: "East Central Michigan Radiation Oncology Associates PC", city: "Midland", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4465,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI205",GROUP_DESC:"MI205 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4465,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI205",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI205",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4465,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI205", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5465 ,protection_group_id: -4465, protection_element_id:-4465], primaryKey: false);
      insert('organizations', [ id: 104451, nci_institute_code: "MI206", name: "Downriver Comprehensive Breast Center", city: "Southgate", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4466,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI206",GROUP_DESC:"MI206 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4466,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI206",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI206",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4466,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI206", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5466 ,protection_group_id: -4466, protection_element_id:-4466], primaryKey: false);
      insert('organizations', [ id: 104452, nci_institute_code: "MI207", name: "Mid-Michigan Gratiot Cancer Center", city: "Alma", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4467,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI207",GROUP_DESC:"MI207 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4467,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI207",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI207",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4467,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI207", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5467 ,protection_group_id: -4467, protection_element_id:-4467], primaryKey: false);
      insert('organizations', [ id: 104453, nci_institute_code: "MI208", name: "Dearborn Oncology Associates", city: "Dearborn", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4468,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI208",GROUP_DESC:"MI208 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4468,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI208",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI208",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4468,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI208", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5468 ,protection_group_id: -4468, protection_element_id:-4468], primaryKey: false);
      insert('organizations', [ id: 104454, nci_institute_code: "MI209", name: "South Macomb Internist PC", city: "Warren", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4469,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI209",GROUP_DESC:"MI209 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4469,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI209",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI209",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4469,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI209", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5469 ,protection_group_id: -4469, protection_element_id:-4469], primaryKey: false);
      insert('organizations', [ id: 104455, nci_institute_code: "MI210", name: "Surgical Specialists of Michigan PC", city: "Saint Clair Shores", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4470,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI210",GROUP_DESC:"MI210 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4470,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI210",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI210",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4470,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI210", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5470 ,protection_group_id: -4470, protection_element_id:-4470], primaryKey: false);
      insert('organizations', [ id: 104456, nci_institute_code: "MI211", name: "North County Medical Specialists", city: "Iron Mountain", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4471,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI211",GROUP_DESC:"MI211 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4471,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI211",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI211",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4471,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI211", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5471 ,protection_group_id: -4471, protection_element_id:-4471], primaryKey: false);
      insert('organizations', [ id: 104457, nci_institute_code: "MI212", name: "Urology Specialists of Michigan PC", city: "Royal Oak", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4472,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI212",GROUP_DESC:"MI212 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4472,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI212",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI212",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4472,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI212", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5472 ,protection_group_id: -4472, protection_element_id:-4472], primaryKey: false);
      insert('organizations', [ id: 104458, nci_institute_code: "MI213", name: "University of Michigan Comprehensive Cancer Center", city: "Ann Arbor", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4473,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI213",GROUP_DESC:"MI213 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4473,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI213",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI213",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4473,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI213", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5473 ,protection_group_id: -4473, protection_element_id:-4473], primaryKey: false);
      insert('organizations', [ id: 104459, nci_institute_code: "MI214", name: "Central Michigan Community Hospital", city: "Mount Pleasant", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4474,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI214",GROUP_DESC:"MI214 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4474,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI214",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI214",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4474,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI214", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5474 ,protection_group_id: -4474, protection_element_id:-4474], primaryKey: false);
      insert('organizations', [ id: 104460, nci_institute_code: "MI215", name: "University of Michigan", city: "Ann Arbor", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4475,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI215",GROUP_DESC:"MI215 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4475,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI215",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI215",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4475,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI215", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5475 ,protection_group_id: -4475, protection_element_id:-4475], primaryKey: false);
      insert('organizations', [ id: 104461, nci_institute_code: "MI216", name: "Academic Surgical Associates", city: "Grand Rapids", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4476,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI216",GROUP_DESC:"MI216 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4476,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI216",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI216",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4476,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI216", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5476 ,protection_group_id: -4476, protection_element_id:-4476], primaryKey: false);
      insert('organizations', [ id: 104462, nci_institute_code: "MI217", name: "West Side Medical Center", city: "Kalamazoo", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4477,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI217",GROUP_DESC:"MI217 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4477,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI217",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI217",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4477,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI217", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5477 ,protection_group_id: -4477, protection_element_id:-4477], primaryKey: false);
      insert('organizations', [ id: 104463, nci_institute_code: "MI218", name: "Assarian Cancer Center", city: "Novi", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4478,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI218",GROUP_DESC:"MI218 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4478,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI218",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI218",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4478,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI218", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5478 ,protection_group_id: -4478, protection_element_id:-4478], primaryKey: false);
      insert('organizations', [ id: 104464, nci_institute_code: "MI219", name: "General & Vascular Surgery PC", city: "Kalamazoo", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4479,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI219",GROUP_DESC:"MI219 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4479,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI219",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI219",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4479,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI219", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5479 ,protection_group_id: -4479, protection_element_id:-4479], primaryKey: false);
      insert('organizations', [ id: 104465, nci_institute_code: "MI220", name: "Weisberg Cancer Treatment Center", city: "Farmington Hills", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4480,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI220",GROUP_DESC:"MI220 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4480,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI220",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI220",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4480,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI220", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5480 ,protection_group_id: -4480, protection_element_id:-4480], primaryKey: false);
      insert('organizations', [ id: 104466, nci_institute_code: "MI221", name: "Saint Joseph Mercy Woodland Cancer Center", city: "Brighton", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4481,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI221",GROUP_DESC:"MI221 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4481,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI221",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI221",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4481,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI221", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5481 ,protection_group_id: -4481, protection_element_id:-4481], primaryKey: false);
      insert('organizations', [ id: 104467, nci_institute_code: "MI222", name: "Chelsea Surgical Associates", city: "Chelsea", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4482,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI222",GROUP_DESC:"MI222 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4482,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI222",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI222",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4482,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI222", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5482 ,protection_group_id: -4482, protection_element_id:-4482], primaryKey: false);
      insert('organizations', [ id: 104468, nci_institute_code: "MI223", name: "Mid-Michigan Healthcare Associates", city: "Lansing", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4483,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI223",GROUP_DESC:"MI223 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4483,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI223",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI223",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4483,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI223", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5483 ,protection_group_id: -4483, protection_element_id:-4483], primaryKey: false);
      insert('organizations', [ id: 104469, nci_institute_code: "MI224", name: "Radiation Oncology Specialists", city: "Grosse Pointe Woods", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4484,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI224",GROUP_DESC:"MI224 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4484,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI224",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI224",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4484,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI224", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5484 ,protection_group_id: -4484, protection_element_id:-4484], primaryKey: false);
      insert('organizations', [ id: 104470, nci_institute_code: "MI225", name: "Osteopathic Medical Oncology and Hematology", city: "Garden City", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4485,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI225",GROUP_DESC:"MI225 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4485,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI225",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI225",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4485,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI225", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5485 ,protection_group_id: -4485, protection_element_id:-4485], primaryKey: false);
      insert('organizations', [ id: 104471, nci_institute_code: "MI226", name: "Upper Peninsula Hematology Oncology Associates", city: "Marquette", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4486,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI226",GROUP_DESC:"MI226 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4486,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI226",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI226",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4486,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI226", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5486 ,protection_group_id: -4486, protection_element_id:-4486], primaryKey: false);
      insert('organizations', [ id: 104472, nci_institute_code: "MI227", name: "Hematology Oncology Consultants PC", city: "Royal Oak", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4487,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI227",GROUP_DESC:"MI227 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4487,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI227",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI227",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4487,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI227", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5487 ,protection_group_id: -4487, protection_element_id:-4487], primaryKey: false);
    }

    void m19() {
        // all19 (25 terms)
      insert('organizations', [ id: 104473, nci_institute_code: "MI228", name: "Sai Bikkina MD PC", city: "Lapeer", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4488,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI228",GROUP_DESC:"MI228 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4488,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI228",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI228",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4488,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI228", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5488 ,protection_group_id: -4488, protection_element_id:-4488], primaryKey: false);
      insert('organizations', [ id: 104474, nci_institute_code: "MI229", name: "Seraphim Pallas MD PLLC", city: "Bloomfield Hills", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4489,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI229",GROUP_DESC:"MI229 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4489,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI229",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI229",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4489,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI229", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5489 ,protection_group_id: -4489, protection_element_id:-4489], primaryKey: false);
      insert('organizations', [ id: 104475, nci_institute_code: "MI230", name: "Associates in General Vascular Surgery", city: "Ypsilanti", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4490,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI230",GROUP_DESC:"MI230 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4490,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI230",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI230",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4490,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI230", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5490 ,protection_group_id: -4490, protection_element_id:-4490], primaryKey: false);
      insert('organizations', [ id: 104476, nci_institute_code: "MI231", name: "Eastside Surgical Practice", city: "Saint Clair Shores", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4491,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI231",GROUP_DESC:"MI231 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4491,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI231",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI231",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4491,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI231", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5491 ,protection_group_id: -4491, protection_element_id:-4491], primaryKey: false);
      insert('organizations', [ id: 104477, nci_institute_code: "MI232", name: "Mitchell Folbe MD PC", city: "Troy", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4492,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI232",GROUP_DESC:"MI232 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4492,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI232",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI232",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4492,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI232", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5492 ,protection_group_id: -4492, protection_element_id:-4492], primaryKey: false);
      insert('organizations', [ id: 104478, nci_institute_code: "MI233", name: "Michigan Medical PC", city: "Grand Rapids", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4493,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI233",GROUP_DESC:"MI233 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4493,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI233",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI233",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4493,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI233", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5493 ,protection_group_id: -4493, protection_element_id:-4493], primaryKey: false);
      insert('organizations', [ id: 104479, nci_institute_code: "MI234", name: "Robert Schwert DO PC", city: "Traverse City", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4494,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI234",GROUP_DESC:"MI234 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4494,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI234",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI234",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4494,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI234", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5494 ,protection_group_id: -4494, protection_element_id:-4494], primaryKey: false);
      insert('organizations', [ id: 104480, nci_institute_code: "MI235", name: "Medical Specialty Associates PC", city: "Grand Rapids", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4495,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI235",GROUP_DESC:"MI235 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4495,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI235",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI235",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4495,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI235", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5495 ,protection_group_id: -4495, protection_element_id:-4495], primaryKey: false);
      insert('organizations', [ id: 104481, nci_institute_code: "MI236", name: "Grand Traverse Internists", city: "Traverse City", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4496,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI236",GROUP_DESC:"MI236 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4496,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI236",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI236",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4496,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI236", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5496 ,protection_group_id: -4496, protection_element_id:-4496], primaryKey: false);
      insert('organizations', [ id: 104482, nci_institute_code: "MI237", name: "Midland Surgical Group PC", city: "Midland", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4497,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI237",GROUP_DESC:"MI237 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4497,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI237",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI237",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4497,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI237", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5497 ,protection_group_id: -4497, protection_element_id:-4497], primaryKey: false);
      insert('organizations', [ id: 104483, nci_institute_code: "MI238", name: "Michigan Institute of Urology PC", city: "Saint Clair Shores", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4498,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI238",GROUP_DESC:"MI238 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4498,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI238",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI238",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4498,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI238", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5498 ,protection_group_id: -4498, protection_element_id:-4498], primaryKey: false);
      insert('organizations', [ id: 104484, nci_institute_code: "MI239", name: "Vidal D Borromeo MD PC", city: "Bloomfield Hills", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4499,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI239",GROUP_DESC:"MI239 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4499,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI239",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI239",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4499,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI239", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5499 ,protection_group_id: -4499, protection_element_id:-4499], primaryKey: false);
      insert('organizations', [ id: 104485, nci_institute_code: "MI240", name: "Medical Oncology Associates PLLC", city: "Lansing", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4500,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI240",GROUP_DESC:"MI240 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4500,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI240",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI240",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4500,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI240", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5500 ,protection_group_id: -4500, protection_element_id:-4500], primaryKey: false);
      insert('organizations', [ id: 104486, nci_institute_code: "MI241", name: "Osteopathic Medical Oncology and Hematology", city: "Woodhaven", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4501,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI241",GROUP_DESC:"MI241 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4501,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI241",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI241",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4501,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI241", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5501 ,protection_group_id: -4501, protection_element_id:-4501], primaryKey: false);
      insert('organizations', [ id: 104487, nci_institute_code: "MI242", name: "Claudia BR Herke MD PC", city: "Troy", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4502,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI242",GROUP_DESC:"MI242 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4502,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI242",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI242",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4502,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI242", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5502 ,protection_group_id: -4502, protection_element_id:-4502], primaryKey: false);
      insert('organizations', [ id: 104488, nci_institute_code: "MI243", name: "Cancer and Transplant Consultants PLC", city: "Troy", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4503,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI243",GROUP_DESC:"MI243 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4503,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI243",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI243",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4503,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI243", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5503 ,protection_group_id: -4503, protection_element_id:-4503], primaryKey: false);
      insert('organizations', [ id: 104489, nci_institute_code: "MI244", name: "Michigan Institute of Hematology and Oncology", city: "Southgate", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4504,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI244",GROUP_DESC:"MI244 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4504,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI244",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI244",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4504,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI244", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5504 ,protection_group_id: -4504, protection_element_id:-4504], primaryKey: false);
      insert('organizations', [ id: 104490, nci_institute_code: "MI245", name: "Rochester Oncology", city: "Lake Orion", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4505,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI245",GROUP_DESC:"MI245 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4505,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI245",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI245",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4505,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI245", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5505 ,protection_group_id: -4505, protection_element_id:-4505], primaryKey: false);
      insert('organizations', [ id: 104491, nci_institute_code: "MI246", name: "Lakeside Cancer Specialists PLLC", city: "Saint Joseph", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4506,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI246",GROUP_DESC:"MI246 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4506,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI246",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI246",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4506,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI246", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5506 ,protection_group_id: -4506, protection_element_id:-4506], primaryKey: false);
      insert('organizations', [ id: 104492, nci_institute_code: "MI247", name: "Grand Valley Gynecology PC", city: "Grand Rapids", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4507,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI247",GROUP_DESC:"MI247 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4507,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI247",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI247",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4507,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI247", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5507 ,protection_group_id: -4507, protection_element_id:-4507], primaryKey: false);
      insert('organizations', [ id: 104493, nci_institute_code: "MI249", name: "Comprehensive Medical Center PLLC", city: "Royal Oak", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4508,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI249",GROUP_DESC:"MI249 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4508,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI249",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI249",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4508,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI249", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5508 ,protection_group_id: -4508, protection_element_id:-4508], primaryKey: false);
      insert('organizations', [ id: 104494, nci_institute_code: "MI250", name: "Macomb Hematology Oncology PC", city: "Warren", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4509,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI250",GROUP_DESC:"MI250 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4509,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI250",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI250",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4509,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI250", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5509 ,protection_group_id: -4509, protection_element_id:-4509], primaryKey: false);
      insert('organizations', [ id: 104495, nci_institute_code: "MI251", name: "Royal Oak Surgical Associates PC", city: "Royal Oak", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4510,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI251",GROUP_DESC:"MI251 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4510,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI251",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI251",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4510,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI251", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5510 ,protection_group_id: -4510, protection_element_id:-4510], primaryKey: false);
      insert('organizations', [ id: 104496, nci_institute_code: "MI252", name: "Great Lakes Heart Lung and Vascular Institute", city: "Lansing", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4511,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI252",GROUP_DESC:"MI252 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4511,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI252",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI252",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4511,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI252", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5511 ,protection_group_id: -4511, protection_element_id:-4511], primaryKey: false);
      insert('organizations', [ id: 104497, nci_institute_code: "MI253", name: "Thoracic Cardiovascular Healthcare Foundation", city: "Lansing", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4512,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI253",GROUP_DESC:"MI253 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4512,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI253",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI253",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4512,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI253", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5512 ,protection_group_id: -4512, protection_element_id:-4512], primaryKey: false);
    }

    void m20() {
        // all20 (25 terms)
      insert('organizations', [ id: 104498, nci_institute_code: "MI254", name: "Michigan Institute of Radiation Oncology", city: "Pontiac", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4513,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI254",GROUP_DESC:"MI254 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4513,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI254",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI254",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4513,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI254", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5513 ,protection_group_id: -4513, protection_element_id:-4513], primaryKey: false);
      insert('organizations', [ id: 104499, nci_institute_code: "MN001", name: "Regions Hospital", city: "Saint Paul", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4514,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN001",GROUP_DESC:"MN001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4514,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4514,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5514 ,protection_group_id: -4514, protection_element_id:-4514], primaryKey: false);
      insert('organizations', [ id: 104500, nci_institute_code: "MN002", name: "United Hospital", city: "St. Paul", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4515,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN002",GROUP_DESC:"MN002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4515,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4515,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5515 ,protection_group_id: -4515, protection_element_id:-4515], primaryKey: false);
      insert('organizations', [ id: 104501, nci_institute_code: "MN003", name: "Miller Hospital", city: "St. Paul", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4516,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN003",GROUP_DESC:"MN003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4516,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4516,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5516 ,protection_group_id: -4516, protection_element_id:-4516], primaryKey: false);
      insert('organizations', [ id: 104502, nci_institute_code: "MN004", name: "Saint Joseph's Hospital - Healtheast", city: "St Paul", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4517,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN004",GROUP_DESC:"MN004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4517,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4517,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5517 ,protection_group_id: -4517, protection_element_id:-4517], primaryKey: false);
      insert('organizations', [ id: 104503, nci_institute_code: "MN005", name: "Midway Hospital - Healtheast", city: "St Paul", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4518,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN005",GROUP_DESC:"MN005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4518,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4518,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5518 ,protection_group_id: -4518, protection_element_id:-4518], primaryKey: false);
      insert('organizations', [ id: 104504, nci_institute_code: "MN007", name: "Mounds Park Hospital", city: "St. Paul", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4519,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN007",GROUP_DESC:"MN007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4519,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4519,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5519 ,protection_group_id: -4519, protection_element_id:-4519], primaryKey: false);
      insert('organizations', [ id: 104505, nci_institute_code: "MN008", name: "Abbott-Northwestern Hospital", city: "Minneapolis", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4520,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN008",GROUP_DESC:"MN008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4520,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4520,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5520 ,protection_group_id: -4520, protection_element_id:-4520], primaryKey: false);
      insert('organizations', [ id: 104506, nci_institute_code: "MN009", name: "Metropolitan-Mount Sinai Medical Center", city: "Minneapolis", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4521,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN009",GROUP_DESC:"MN009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4521,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4521,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5521 ,protection_group_id: -4521, protection_element_id:-4521], primaryKey: false);
      insert('organizations', [ id: 104507, nci_institute_code: "MN010", name: "Children's Hospitals & Clinics of Minnesota - Minneapolis", city: "Minneapolis", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4522,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN010",GROUP_DESC:"MN010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4522,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4522,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5522 ,protection_group_id: -4522, protection_element_id:-4522], primaryKey: false);
      insert('organizations', [ id: 104508, nci_institute_code: "MN011", name: "Metropolitan Medcial Center", city: "Minneapolis", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4523,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN011",GROUP_DESC:"MN011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4523,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4523,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5523 ,protection_group_id: -4523, protection_element_id:-4523], primaryKey: false);
      insert('organizations', [ id: 104509, nci_institute_code: "MN012", name: "Saint Joseph's Medical Center", city: "Brainerd", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4524,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN012",GROUP_DESC:"MN012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4524,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4524,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5524 ,protection_group_id: -4524, protection_element_id:-4524], primaryKey: false);
      insert('organizations', [ id: 104510, nci_institute_code: "MN013", name: "Hennepin County Medical Center", city: "Minneapolis", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4525,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN013",GROUP_DESC:"MN013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4525,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4525,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5525 ,protection_group_id: -4525, protection_element_id:-4525], primaryKey: false);
      insert('organizations', [ id: 104511, nci_institute_code: "MN014", name: "Park Nicollet Clinic - St. Louis Park", city: "St. Louis Park", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4526,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN014",GROUP_DESC:"MN014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4526,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4526,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5526 ,protection_group_id: -4526, protection_element_id:-4526], primaryKey: false);
      insert('organizations', [ id: 104512, nci_institute_code: "MN015", name: "Minneapolis Veterans Medical Center", city: "Minneapolis", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4527,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN015",GROUP_DESC:"MN015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4527,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4527,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5527 ,protection_group_id: -4527, protection_element_id:-4527], primaryKey: false);
      insert('organizations', [ id: 104513, nci_institute_code: "MN016", name: "Charles T. Miller Hospital", city: "St. Paul", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4528,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN016",GROUP_DESC:"MN016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4528,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4528,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5528 ,protection_group_id: -4528, protection_element_id:-4528], primaryKey: false);
      insert('organizations', [ id: 104514, nci_institute_code: "MN017", name: "Methodist Hospital", city: "St. Louis Park", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4529,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN017",GROUP_DESC:"MN017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4529,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4529,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5529 ,protection_group_id: -4529, protection_element_id:-4529], primaryKey: false);
      insert('organizations', [ id: 104515, nci_institute_code: "MN018", name: "Unity Hospital", city: "Fridley", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4530,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN018",GROUP_DESC:"MN018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4530,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4530,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5530 ,protection_group_id: -4530, protection_element_id:-4530], primaryKey: false);
      insert('organizations', [ id: 104516, nci_institute_code: "MN019", name: "Mercy Hospital", city: "Coon Rapids", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4531,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN019",GROUP_DESC:"MN019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4531,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4531,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5531 ,protection_group_id: -4531, protection_element_id:-4531], primaryKey: false);
      insert('organizations', [ id: 104517, nci_institute_code: "MN020", name: "Saint Mary's Hospital, Minneapolis", city: "Minneapolis", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4532,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN020",GROUP_DESC:"MN020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4532,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4532,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5532 ,protection_group_id: -4532, protection_element_id:-4532], primaryKey: false);
      insert('organizations', [ id: 104518, nci_institute_code: "MN021", name: "Fairview Riverside Medical Center", city: "Minneapolis", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4533,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN021",GROUP_DESC:"MN021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4533,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4533,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5533 ,protection_group_id: -4533, protection_element_id:-4533], primaryKey: false);
      insert('organizations', [ id: 104519, nci_institute_code: "MN022", name: "University of Minnesota Medical School", city: "Minneapolis", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4534,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN022",GROUP_DESC:"MN022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4534,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4534,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5534 ,protection_group_id: -4534, protection_element_id:-4534], primaryKey: false);
      insert('organizations', [ id: 104520, nci_institute_code: "MN023", name: "Saint Mary's Medical Center", city: "Duluth", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4535,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN023",GROUP_DESC:"MN023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4535,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4535,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5535 ,protection_group_id: -4535, protection_element_id:-4535], primaryKey: false);
      insert('organizations', [ id: 104521, nci_institute_code: "MN024", name: "Duluth Clinic CCOP", city: "Duluth", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4536,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN024",GROUP_DESC:"MN024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4536,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4536,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5536 ,protection_group_id: -4536, protection_element_id:-4536], primaryKey: false);
      insert('organizations', [ id: 104522, nci_institute_code: "MN026", name: "Mayo Clinic Rochester", city: "Rochester", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4537,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN026",GROUP_DESC:"MN026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4537,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4537,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5537 ,protection_group_id: -4537, protection_element_id:-4537], primaryKey: false);
    }

    void m21() {
        // all21 (25 terms)
      insert('organizations', [ id: 104523, nci_institute_code: "MN027", name: "Willmar Medical Center", city: "Willmar", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4538,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN027",GROUP_DESC:"MN027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4538,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4538,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5538 ,protection_group_id: -4538, protection_element_id:-4538], primaryKey: false);
      insert('organizations', [ id: 104524, nci_institute_code: "MN028", name: "Rice Memorial Hospital", city: "Willmar", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4539,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN028",GROUP_DESC:"MN028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4539,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4539,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5539 ,protection_group_id: -4539, protection_element_id:-4539], primaryKey: false);
      insert('organizations', [ id: 104525, nci_institute_code: "MN029", name: "Saint Cloud Hospital", city: "Saint Cloud", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4540,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN029",GROUP_DESC:"MN029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4540,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4540,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5540 ,protection_group_id: -4540, protection_element_id:-4540], primaryKey: false);
      insert('organizations', [ id: 104526, nci_institute_code: "MN030", name: "Miller-Dwan Hospital", city: "Duluth", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4541,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN030",GROUP_DESC:"MN030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4541,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4541,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5541 ,protection_group_id: -4541, protection_element_id:-4541], primaryKey: false);
      insert('organizations', [ id: 104527, nci_institute_code: "MN031", name: "Fairview-Southdale Hospital", city: "Edina", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4542,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN031",GROUP_DESC:"MN031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4542,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4542,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5542 ,protection_group_id: -4542, protection_element_id:-4542], primaryKey: false);
      insert('organizations', [ id: 104528, nci_institute_code: "MN032", name: "Minnesota Oncology Hematology PA-Edina", city: "Edina", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4543,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN032",GROUP_DESC:"MN032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4543,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4543,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5543 ,protection_group_id: -4543, protection_element_id:-4543], primaryKey: false);
      insert('organizations', [ id: 104529, nci_institute_code: "MN033", name: "Saint Luke's Hospital of Duluth", city: "Duluth", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4544,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN033",GROUP_DESC:"MN033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4544,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4544,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5544 ,protection_group_id: -4544, protection_element_id:-4544], primaryKey: false);
      insert('organizations', [ id: 104530, nci_institute_code: "MN035", name: "Health Partners Inc", city: "Minneapolis", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4545,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN035",GROUP_DESC:"MN035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4545,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4545,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5545 ,protection_group_id: -4545, protection_element_id:-4545], primaryKey: false);
      insert('organizations', [ id: 104531, nci_institute_code: "MN036", name: "Children's Hospital and Clinic-Saint Paul", city: "St. Paul", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4546,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN036",GROUP_DESC:"MN036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4546,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4546,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5546 ,protection_group_id: -4546, protection_element_id:-4546], primaryKey: false);
      insert('organizations', [ id: 104532, nci_institute_code: "MN037", name: "Brainerd Medical Center, Professional Association", city: "Brainerd", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4547,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN037",GROUP_DESC:"MN037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4547,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4547,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5547 ,protection_group_id: -4547, protection_element_id:-4547], primaryKey: false);
      insert('organizations', [ id: 104533, nci_institute_code: "MN038", name: "Adams Clinic", city: "Hibbing", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4548,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN038",GROUP_DESC:"MN038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4548,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4548,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5548 ,protection_group_id: -4548, protection_element_id:-4548], primaryKey: false);
      insert('organizations', [ id: 104534, nci_institute_code: "MN039", name: "Mesabi Regional Medical Center", city: "Hibbing", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4549,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN039",GROUP_DESC:"MN039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4549,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4549,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5549 ,protection_group_id: -4549, protection_element_id:-4549], primaryKey: false);
      insert('organizations', [ id: 104535, nci_institute_code: "MN040", name: "Centracare Clinic", city: "St. Cloud", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4550,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN040",GROUP_DESC:"MN040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4550,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4550,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5550 ,protection_group_id: -4550, protection_element_id:-4550], primaryKey: false);
      insert('organizations', [ id: 104536, nci_institute_code: "MN041", name: "Saint John's Hospital - Healtheast", city: "Maplewood", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4551,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN041",GROUP_DESC:"MN041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4551,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4551,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5551 ,protection_group_id: -4551, protection_element_id:-4551], primaryKey: false);
      insert('organizations', [ id: 104537, nci_institute_code: "MN042", name: "Virginia Piper Cancer Institute", city: "Minneapolis", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4552,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN042",GROUP_DESC:"MN042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4552,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4552,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5552 ,protection_group_id: -4552, protection_element_id:-4552], primaryKey: false);
      insert('organizations', [ id: 104538, nci_institute_code: "MN043", name: "Metro-Minnesota CCOP", city: "Saint Louis Park", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4553,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN043",GROUP_DESC:"MN043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4553,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4553,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5553 ,protection_group_id: -4553, protection_element_id:-4553], primaryKey: false);
      insert('organizations', [ id: 104539, nci_institute_code: "MN045", name: "Divine Redeemer Hospital", city: "St. Paul", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4554,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN045",GROUP_DESC:"MN045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4554,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4554,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5554 ,protection_group_id: -4554, protection_element_id:-4554], primaryKey: false);
      insert('organizations', [ id: 104540, nci_institute_code: "MN048", name: "Saint John's Regional Health", city: "Red Wing", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4555,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN048",GROUP_DESC:"MN048 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4555,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN048",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN048",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4555,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN048", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5555 ,protection_group_id: -4555, protection_element_id:-4555], primaryKey: false);
      insert('organizations', [ id: 104541, nci_institute_code: "MN049", name: "Minnesota Cooperative Group Outreach Program", city: "Minneapolis", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4556,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN049",GROUP_DESC:"MN049 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4556,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN049",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN049",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4556,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN049", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5556 ,protection_group_id: -4556, protection_element_id:-4556], primaryKey: false);
      insert('organizations', [ id: 104542, nci_institute_code: "MN051", name: "Minnesota Medical Spcialists Limited", city: "Robbinsdale", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4557,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN051",GROUP_DESC:"MN051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4557,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4557,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5557 ,protection_group_id: -4557, protection_element_id:-4557], primaryKey: false);
      insert('organizations', [ id: 104543, nci_institute_code: "MN052", name: "Digestive Healthcare", city: "Minneapolis", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4558,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN052",GROUP_DESC:"MN052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4558,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4558,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5558 ,protection_group_id: -4558, protection_element_id:-4558], primaryKey: false);
      insert('organizations', [ id: 104544, nci_institute_code: "MN053", name: "Fairview Redwing Clinic", city: "Redwing", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4559,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN053",GROUP_DESC:"MN053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4559,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4559,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5559 ,protection_group_id: -4559, protection_element_id:-4559], primaryKey: false);
      insert('organizations', [ id: 104545, nci_institute_code: "MN054", name: "North Memorial Medical Health Center", city: "Robbinsdale", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4560,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN054",GROUP_DESC:"MN054 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4560,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN054",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN054",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4560,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN054", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5560 ,protection_group_id: -4560, protection_element_id:-4560], primaryKey: false);
      insert('organizations', [ id: 104546, nci_institute_code: "MN058", name: "Fergus Falls Medical Group", city: "Fergus Falls", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4561,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN058",GROUP_DESC:"MN058 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4561,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN058",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN058",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4561,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN058", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5561 ,protection_group_id: -4561, protection_element_id:-4561], primaryKey: false);
      insert('organizations', [ id: 104547, nci_institute_code: "MN059", name: "Ridgeview Medical Center", city: "Waconia", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4562,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN059",GROUP_DESC:"MN059 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4562,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN059",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN059",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4562,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN059", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5562 ,protection_group_id: -4562, protection_element_id:-4562], primaryKey: false);
    }

    void m22() {
        // all22 (25 terms)
      insert('organizations', [ id: 104548, nci_institute_code: "MN060", name: "Hubert H. Humphrey Cancer Center", city: "Robbinsdale", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4563,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN060",GROUP_DESC:"MN060 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4563,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN060",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN060",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4563,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN060", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5563 ,protection_group_id: -4563, protection_element_id:-4563], primaryKey: false);
      insert('organizations', [ id: 104549, nci_institute_code: "MN061", name: "Fairview-University Medical Center", city: "Minneapolis", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4564,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN061",GROUP_DESC:"MN061 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4564,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN061",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN061",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4564,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN061", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5564 ,protection_group_id: -4564, protection_element_id:-4564], primaryKey: false);
      insert('organizations', [ id: 104550, nci_institute_code: "MN062", name: "Mankato Clinic", city: "Makato", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4565,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN062",GROUP_DESC:"MN062 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4565,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN062",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN062",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4565,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN062", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5565 ,protection_group_id: -4565, protection_element_id:-4565], primaryKey: false);
      insert('organizations', [ id: 104551, nci_institute_code: "MN063", name: "Alexandria Clinic", city: "Alexandria", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4566,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN063",GROUP_DESC:"MN063 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4566,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN063",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN063",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4566,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN063", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5566 ,protection_group_id: -4566, protection_element_id:-4566], primaryKey: false);
      insert('organizations', [ id: 104552, nci_institute_code: "MN064", name: "Saint Francis Regional Medical Center", city: "Shakopee", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4567,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN064",GROUP_DESC:"MN064 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4567,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN064",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN064",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4567,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN064", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5567 ,protection_group_id: -4567, protection_element_id:-4567], primaryKey: false);
      insert('organizations', [ id: 104553, nci_institute_code: "MN066", name: "Northland Ent Association, PA", city: "Duluth", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4568,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN066",GROUP_DESC:"MN066 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4568,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN066",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN066",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4568,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN066", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5568 ,protection_group_id: -4568, protection_element_id:-4568], primaryKey: false);
      insert('organizations', [ id: 104554, nci_institute_code: "MN067", name: "Meritcare Clinic Perham", city: "Perham", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4569,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN067",GROUP_DESC:"MN067 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4569,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN067",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN067",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4569,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN067", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5569 ,protection_group_id: -4569, protection_element_id:-4569], primaryKey: false);
      insert('organizations', [ id: 104555, nci_institute_code: "MN068", name: "Meeker County Memorial Hospital", city: "Litchfield", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4570,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN068",GROUP_DESC:"MN068 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4570,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN068",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN068",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4570,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN068", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5570 ,protection_group_id: -4570, protection_element_id:-4570], primaryKey: false);
      insert('organizations', [ id: 104556, nci_institute_code: "MN069", name: "Aspen Medical Group", city: "Hopkins", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4571,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN069",GROUP_DESC:"MN069 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4571,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN069",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN069",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4571,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN069", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5571 ,protection_group_id: -4571, protection_element_id:-4571], primaryKey: false);
      insert('organizations', [ id: 104557, nci_institute_code: "MN070", name: "Fairview Ridges Hospital", city: "Burnsville", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4572,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN070",GROUP_DESC:"MN070 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4572,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN070",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN070",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4572,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN070", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5572 ,protection_group_id: -4572, protection_element_id:-4572], primaryKey: false);
      insert('organizations', [ id: 104558, nci_institute_code: "MN071", name: "University of Minnesota Hospitals", city: "Minneapolis", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4573,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN071",GROUP_DESC:"MN071 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4573,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN071",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN071",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4573,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN071", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5573 ,protection_group_id: -4573, protection_element_id:-4573], primaryKey: false);
      insert('organizations', [ id: 104559, nci_institute_code: "MN072", name: "Radiation Therapy Center", city: "Hibbing", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4574,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN072",GROUP_DESC:"MN072 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4574,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN072",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN072",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4574,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN072", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5574 ,protection_group_id: -4574, protection_element_id:-4574], primaryKey: false);
      insert('organizations', [ id: 104560, nci_institute_code: "MN074", name: "Hutchinson Area Health Care", city: "Hutchinson", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4575,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN074",GROUP_DESC:"MN074 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4575,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN074",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN074",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4575,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN074", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5575 ,protection_group_id: -4575, protection_element_id:-4575], primaryKey: false);
      insert('organizations', [ id: 104561, nci_institute_code: "MN075", name: "Minnesota Oncology Hematology PA-Maplewood", city: "Maplewood", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4576,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN075",GROUP_DESC:"MN075 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4576,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN075",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN075",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4576,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN075", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5576 ,protection_group_id: -4576, protection_element_id:-4576], primaryKey: false);
      insert('organizations', [ id: 104562, nci_institute_code: "MN076", name: "Douglas County Hospital", city: "Alexandria", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4577,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN076",GROUP_DESC:"MN076 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4577,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN076",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN076",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4577,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN076", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5577 ,protection_group_id: -4577, protection_element_id:-4577], primaryKey: false);
      insert('organizations', [ id: 104563, nci_institute_code: "MN079", name: "Immanuel-St.Joseph Hosp-Mayo Hlt'h Syst", city: "Mankato", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4578,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN079",GROUP_DESC:"MN079 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4578,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN079",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN079",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4578,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN079", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5578 ,protection_group_id: -4578, protection_element_id:-4578], primaryKey: false);
      insert('organizations', [ id: 104564, nci_institute_code: "MN080", name: "Community Memorial Hospital", city: "Winona", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4579,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN080",GROUP_DESC:"MN080 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4579,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN080",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN080",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4579,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN080", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5579 ,protection_group_id: -4579, protection_element_id:-4579], primaryKey: false);
      insert('organizations', [ id: 104565, nci_institute_code: "MN081", name: "West Bank Radiation Therapy Center", city: "Minneapolis", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4580,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN081",GROUP_DESC:"MN081 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4580,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN081",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN081",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4580,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN081", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5580 ,protection_group_id: -4580, protection_element_id:-4580], primaryKey: false);
      insert('organizations', [ id: 104566, nci_institute_code: "MN083", name: "Minnesota Oncology Hematology PA-Minneapolis", city: "Minneapolis", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4581,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN083",GROUP_DESC:"MN083 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4581,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN083",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN083",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4581,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN083", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5581 ,protection_group_id: -4581, protection_element_id:-4581], primaryKey: false);
      insert('organizations', [ id: 104567, nci_institute_code: "MN085", name: "Parker Hughes Cancer Center", city: "Roseville", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4582,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN085",GROUP_DESC:"MN085 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4582,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN085",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN085",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4582,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN085", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5582 ,protection_group_id: -4582, protection_element_id:-4582], primaryKey: false);
      insert('organizations', [ id: 104568, nci_institute_code: "MN086", name: "Albert Lea Medical Center-Mayo Health System", city: "Albert Lea", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4583,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN086",GROUP_DESC:"MN086 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4583,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN086",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN086",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4583,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN086", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5583 ,protection_group_id: -4583, protection_element_id:-4583], primaryKey: false);
      insert('organizations', [ id: 104569, nci_institute_code: "MN088", name: "Minnesota Oncology Hematology PA-Waconia", city: "Waconia", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4584,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN088",GROUP_DESC:"MN088 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4584,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN088",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN088",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4584,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN088", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5584 ,protection_group_id: -4584, protection_element_id:-4584], primaryKey: false);
      insert('organizations', [ id: 104570, nci_institute_code: "MN089", name: "Merit Care Clinic Bemidji", city: "Bemidji", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4585,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN089",GROUP_DESC:"MN089 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4585,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN089",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN089",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4585,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN089", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5585 ,protection_group_id: -4585, protection_element_id:-4585], primaryKey: false);
      insert('organizations', [ id: 104571, nci_institute_code: "MN091", name: "Duluth Clinic", city: "Duluth", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4586,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN091",GROUP_DESC:"MN091 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4586,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN091",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN091",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4586,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN091", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5586 ,protection_group_id: -4586, protection_element_id:-4586], primaryKey: false);
      insert('organizations', [ id: 104572, nci_institute_code: "MN092", name: "Minnesota Cancer Survallience System", city: "Minneapolis", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4587,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN092",GROUP_DESC:"MN092 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4587,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN092",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN092",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4587,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN092", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5587 ,protection_group_id: -4587, protection_element_id:-4587], primaryKey: false);
    }

    void m23() {
        // all23 (25 terms)
      insert('organizations', [ id: 104573, nci_institute_code: "MN093", name: "Fairview Mesaba Clinic- Hibbing", city: "Hibbing", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4588,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN093",GROUP_DESC:"MN093 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4588,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN093",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN093",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4588,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN093", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5588 ,protection_group_id: -4588, protection_element_id:-4588], primaryKey: false);
      insert('organizations', [ id: 104574, nci_institute_code: "MN094", name: "Chippewa County - Montevideo Hospital", city: "Montevideo", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4589,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN094",GROUP_DESC:"MN094 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4589,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN094",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN094",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4589,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN094", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5589 ,protection_group_id: -4589, protection_element_id:-4589], primaryKey: false);
      insert('organizations', [ id: 104575, nci_institute_code: "MN095", name: "Adult and Pediatric Urology PLLP", city: "Sartell", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4590,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN095",GROUP_DESC:"MN095 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4590,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN095",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN095",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4590,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN095", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5590 ,protection_group_id: -4590, protection_element_id:-4590], primaryKey: false);
      insert('organizations', [ id: 104576, nci_institute_code: "MN096", name: "Saint Luke's Cancer Care Center", city: "Duluth", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4591,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN096",GROUP_DESC:"MN096 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4591,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN096",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN096",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4591,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN096", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5591 ,protection_group_id: -4591, protection_element_id:-4591], primaryKey: false);
      insert('organizations', [ id: 104577, nci_institute_code: "MN097", name: "United Radiation Therapy", city: "Saint Paul", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4592,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN097",GROUP_DESC:"MN097 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4592,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN097",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN097",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4592,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN097", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5592 ,protection_group_id: -4592, protection_element_id:-4592], primaryKey: false);
      insert('organizations', [ id: 104578, nci_institute_code: "MN098", name: "Minnesota Oncology and Hematology PA-Woodbury", city: "Woodbury", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4593,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN098",GROUP_DESC:"MN098 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4593,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN098",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN098",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4593,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN098", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5593 ,protection_group_id: -4593, protection_element_id:-4593], primaryKey: false);
      insert('organizations', [ id: 104579, nci_institute_code: "MN099", name: "Woodwinds Health Campus", city: "Woodbury", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4594,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN099",GROUP_DESC:"MN099 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4594,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN099",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN099",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4594,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN099", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5594 ,protection_group_id: -4594, protection_element_id:-4594], primaryKey: false);
      insert('organizations', [ id: 104580, nci_institute_code: "MN100", name: "Deer River Healthcare Center", city: "Deer River", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4595,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN100",GROUP_DESC:"MN100 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4595,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN100",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN100",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4595,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN100", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5595 ,protection_group_id: -4595, protection_element_id:-4595], primaryKey: false);
      insert('organizations', [ id: 104581, nci_institute_code: "MN101", name: "Deer River Clinic", city: "Deer River", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4596,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN101",GROUP_DESC:"MN101 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4596,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN101",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN101",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4596,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN101", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5596 ,protection_group_id: -4596, protection_element_id:-4596], primaryKey: false);
      insert('organizations', [ id: 104582, nci_institute_code: "MN102", name: "Minnesota Oncology Hematology PA-Saint Paul", city: "Saint Paul", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4597,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN102",GROUP_DESC:"MN102 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4597,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN102",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN102",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4597,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN102", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5597 ,protection_group_id: -4597, protection_element_id:-4597], primaryKey: false);
      insert('organizations', [ id: 104583, nci_institute_code: "MN103", name: "Etzell, Paul S MD (UIA Investigator)", city: "Fergus Falls", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4598,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN103",GROUP_DESC:"MN103 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4598,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN103",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN103",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4598,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN103", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5598 ,protection_group_id: -4598, protection_element_id:-4598], primaryKey: false);
      insert('organizations', [ id: 104584, nci_institute_code: "MN104", name: "Harris, John Gilbert MD (UIA Investigator)", city: "Alexandria", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4599,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN104",GROUP_DESC:"MN104 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4599,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN104",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN104",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4599,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN104", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5599 ,protection_group_id: -4599, protection_element_id:-4599], primaryKey: false);
      insert('organizations', [ id: 104585, nci_institute_code: "MN105", name: "Medini, Eitan MD (UIA Investigator)", city: "Alexandria", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4600,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN105",GROUP_DESC:"MN105 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4600,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN105",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN105",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4600,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN105", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5600 ,protection_group_id: -4600, protection_element_id:-4600], primaryKey: false);
      insert('organizations', [ id: 104586, nci_institute_code: "MN106", name: "Affiliated Community Medcial Center PA", city: "Willmar", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4601,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN106",GROUP_DESC:"MN106 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4601,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN106",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN106",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4601,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN106", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5601 ,protection_group_id: -4601, protection_element_id:-4601], primaryKey: false);
      insert('organizations', [ id: 104587, nci_institute_code: "MN107", name: "Colon and Rectal Surgery Associates", city: "Saint Paul", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4602,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN107",GROUP_DESC:"MN107 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4602,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN107",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN107",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4602,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN107", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5602 ,protection_group_id: -4602, protection_element_id:-4602], primaryKey: false);
      insert('organizations', [ id: 104588, nci_institute_code: "MN108", name: "Swenson, Wade II, MD (UIA Investigator)", city: "Fergus Falls", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4603,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN108",GROUP_DESC:"MN108 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4603,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN108",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN108",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4603,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN108", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5603 ,protection_group_id: -4603, protection_element_id:-4603], primaryKey: false);
      insert('organizations', [ id: 104589, nci_institute_code: "MN109", name: "University of Minnesota Physicians Therapeutic Radiology Clinic", city: "Wyoming", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4604,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN109",GROUP_DESC:"MN109 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4604,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN109",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN109",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4604,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN109", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5604 ,protection_group_id: -4604, protection_element_id:-4604], primaryKey: false);
      insert('organizations', [ id: 104590, nci_institute_code: "MN110", name: "Minnesota Oncology and Hematology PA", city: "Minneapolis", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4605,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN110",GROUP_DESC:"MN110 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4605,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN110",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN110",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4605,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN110", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5605 ,protection_group_id: -4605, protection_element_id:-4605], primaryKey: false);
      insert('organizations', [ id: 104591, nci_institute_code: "MO001", name: "Western Missouri Medical Center", city: "Warrensburg", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4606,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO001",GROUP_DESC:"MO001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4606,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4606,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5606 ,protection_group_id: -4606, protection_element_id:-4606], primaryKey: false);
      insert('organizations', [ id: 104592, nci_institute_code: "MO002", name: "Saint Luke's Hospital", city: "Chesterfield", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4607,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO002",GROUP_DESC:"MO002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4607,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4607,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5607 ,protection_group_id: -4607, protection_element_id:-4607], primaryKey: false);
      insert('organizations', [ id: 104593, nci_institute_code: "MO003", name: "DePaul Health Center", city: "Bridgeton", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4608,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO003",GROUP_DESC:"MO003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4608,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4608,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5608 ,protection_group_id: -4608, protection_element_id:-4608], primaryKey: false);
      insert('organizations', [ id: 104594, nci_institute_code: "MO005", name: "Saint Louis University Hospital", city: "St Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4609,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO005",GROUP_DESC:"MO005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4609,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4609,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5609 ,protection_group_id: -4609, protection_element_id:-4609], primaryKey: false);
      insert('organizations', [ id: 104595, nci_institute_code: "MO006", name: "Incarnate Word Hospital", city: "Saint Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4610,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO006",GROUP_DESC:"MO006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4610,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4610,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5610 ,protection_group_id: -4610, protection_element_id:-4610], primaryKey: false);
      insert('organizations', [ id: 104596, nci_institute_code: "MO007", name: "Cardinal Glennon Children's Medical Center", city: "St. Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4611,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO007",GROUP_DESC:"MO007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4611,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4611,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5611 ,protection_group_id: -4611, protection_element_id:-4611], primaryKey: false);
      insert('organizations', [ id: 104597, nci_institute_code: "MO008", name: "Saint Mary's Health Center", city: "St. Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4612,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO008",GROUP_DESC:"MO008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4612,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4612,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5612 ,protection_group_id: -4612, protection_element_id:-4612], primaryKey: false);
    }

    void m24() {
        // all24 (25 terms)
      insert('organizations', [ id: 104598, nci_institute_code: "MO009", name: "Saint Francis Medical Center", city: "Cape Girardeau", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4613,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO009",GROUP_DESC:"MO009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4613,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4613,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5613 ,protection_group_id: -4613, protection_element_id:-4613], primaryKey: false);
      insert('organizations', [ id: 104599, nci_institute_code: "MO010", name: "Veteran's Affairs Medical Center - St. Louis", city: "St. Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4614,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO010",GROUP_DESC:"MO010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4614,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4614,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5614 ,protection_group_id: -4614, protection_element_id:-4614], primaryKey: false);
      insert('organizations', [ id: 104600, nci_institute_code: "MO011", name: "Washington University School of Medicine", city: "St Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4615,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO011",GROUP_DESC:"MO011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4615,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4615,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5615 ,protection_group_id: -4615, protection_element_id:-4615], primaryKey: false);
      insert('organizations', [ id: 104601, nci_institute_code: "MO012", name: "Saint Louis Children's Hospital", city: "Saint Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4616,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO012",GROUP_DESC:"MO012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4616,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4616,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5616 ,protection_group_id: -4616, protection_element_id:-4616], primaryKey: false);
      insert('organizations', [ id: 104602, nci_institute_code: "MO013", name: "Barnes-Jewish Hospital", city: "Saint Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4617,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO013",GROUP_DESC:"MO013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4617,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4617,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5617 ,protection_group_id: -4617, protection_element_id:-4617], primaryKey: false);
      insert('organizations', [ id: 104603, nci_institute_code: "MO014", name: "Mallinckrodt Institute of Radiology", city: "St. Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4618,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO014",GROUP_DESC:"MO014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4618,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4618,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5618 ,protection_group_id: -4618, protection_element_id:-4618], primaryKey: false);
      insert('organizations', [ id: 104604, nci_institute_code: "MO016", name: "Overland Medical Center", city: "St. Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4619,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO016",GROUP_DESC:"MO016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4619,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4619,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5619 ,protection_group_id: -4619, protection_element_id:-4619], primaryKey: false);
      insert('organizations', [ id: 104605, nci_institute_code: "MO019", name: "Washington University - Jewish", city: "St Loius", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4620,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO019",GROUP_DESC:"MO019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4620,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4620,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5620 ,protection_group_id: -4620, protection_element_id:-4620], primaryKey: false);
      insert('organizations', [ id: 104606, nci_institute_code: "MO020", name: "Christian Hospital Northeast - Northwest", city: "St. Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4621,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO020",GROUP_DESC:"MO020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4621,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4621,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5621 ,protection_group_id: -4621, protection_element_id:-4621], primaryKey: false);
      insert('organizations', [ id: 104607, nci_institute_code: "MO021", name: "Saint John's Mercy Medical Center", city: "St. Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4622,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO021",GROUP_DESC:"MO021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4622,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4622,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5622 ,protection_group_id: -4622, protection_element_id:-4622], primaryKey: false);
      insert('organizations', [ id: 104608, nci_institute_code: "MO022", name: "Saint Joseph's Health Center", city: "St. Charles", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4623,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO022",GROUP_DESC:"MO022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4623,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4623,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5623 ,protection_group_id: -4623, protection_element_id:-4623], primaryKey: false);
      insert('organizations', [ id: 104609, nci_institute_code: "MO023", name: "Southeast Missouri Hospital", city: "Gape Girardeau", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4624,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO023",GROUP_DESC:"MO023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4624,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4624,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5624 ,protection_group_id: -4624, protection_element_id:-4624], primaryKey: false);
      insert('organizations', [ id: 104610, nci_institute_code: "MO024", name: "The Childrens Mercy Hospital", city: "Kansas City", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4625,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO024",GROUP_DESC:"MO024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4625,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4625,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5625 ,protection_group_id: -4625, protection_element_id:-4625], primaryKey: false);
      insert('organizations', [ id: 104611, nci_institute_code: "MO025", name: "University of Missouri at Kansas", city: "Kansas City", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4626,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO025",GROUP_DESC:"MO025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4626,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4626,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5626 ,protection_group_id: -4626, protection_element_id:-4626], primaryKey: false);
      insert('organizations', [ id: 104612, nci_institute_code: "MO027", name: "Statland Clinic Ltd.", city: "Kansas City", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4627,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO027",GROUP_DESC:"MO027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4627,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4627,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5627 ,protection_group_id: -4627, protection_element_id:-4627], primaryKey: false);
      insert('organizations', [ id: 104613, nci_institute_code: "MO028", name: "Saint Luke's Hospital of Kansas City", city: "Kansas City", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4628,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO028",GROUP_DESC:"MO028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4628,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4628,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5628 ,protection_group_id: -4628, protection_element_id:-4628], primaryKey: false);
      insert('organizations', [ id: 104614, nci_institute_code: "MO029", name: "Kansas City Veterans Affairs Medical Center", city: "Kansas City", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4629,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO029",GROUP_DESC:"MO029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4629,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4629,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5629 ,protection_group_id: -4629, protection_element_id:-4629], primaryKey: false);
      insert('organizations', [ id: 104615, nci_institute_code: "MO030", name: "Baptist-Lutheran Medical Center", city: "Kansas City", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4630,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO030",GROUP_DESC:"MO030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4630,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4630,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5630 ,protection_group_id: -4630, protection_element_id:-4630], primaryKey: false);
      insert('organizations', [ id: 104616, nci_institute_code: "MO031", name: "Research Medical Center", city: "Kansas City", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4631,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO031",GROUP_DESC:"MO031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4631,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4631,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5631 ,protection_group_id: -4631, protection_element_id:-4631], primaryKey: false);
      insert('organizations', [ id: 104617, nci_institute_code: "MO032", name: "Saint Mary's Hospital", city: "Kansas City", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4632,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO032",GROUP_DESC:"MO032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4632,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4632,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5632 ,protection_group_id: -4632, protection_element_id:-4632], primaryKey: false);
      insert('organizations', [ id: 104618, nci_institute_code: "MO034", name: "Saint John's Regional Medical Center", city: "Joplin", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4633,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO034",GROUP_DESC:"MO034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4633,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4633,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5633 ,protection_group_id: -4633, protection_element_id:-4633], primaryKey: false);
      insert('organizations', [ id: 104619, nci_institute_code: "MO035", name: "Boone Hospital Center", city: "Columbia", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4634,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO035",GROUP_DESC:"MO035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4634,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4634,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5634 ,protection_group_id: -4634, protection_element_id:-4634], primaryKey: false);
      insert('organizations', [ id: 104620, nci_institute_code: "MO036", name: "University of Missouri - Ellis Fischel", city: "Columbia", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4635,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO036",GROUP_DESC:"MO036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4635,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4635,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5635 ,protection_group_id: -4635, protection_element_id:-4635], primaryKey: false);
      insert('organizations', [ id: 104621, nci_institute_code: "MO037", name: "University of Missouri-Columbia", city: "Columbia", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4636,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO037",GROUP_DESC:"MO037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4636,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4636,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5636 ,protection_group_id: -4636, protection_element_id:-4636], primaryKey: false);
      insert('organizations', [ id: 104622, nci_institute_code: "MO038", name: "Columbia Regional", city: "Columbia", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4637,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO038",GROUP_DESC:"MO038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4637,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4637,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5637 ,protection_group_id: -4637, protection_element_id:-4637], primaryKey: false);
    }

    void m25() {
        // all25 (25 terms)
      insert('organizations', [ id: 104623, nci_institute_code: "MO039", name: "Lakeside Hospital", city: "Kansas City", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4638,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO039",GROUP_DESC:"MO039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4638,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4638,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5638 ,protection_group_id: -4638, protection_element_id:-4638], primaryKey: false);
      insert('organizations', [ id: 104624, nci_institute_code: "MO040", name: "Memorial Community Hospital", city: "Jefferson City", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4639,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO040",GROUP_DESC:"MO040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4639,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4639,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5639 ,protection_group_id: -4639, protection_element_id:-4639], primaryKey: false);
      insert('organizations', [ id: 104625, nci_institute_code: "MO042", name: "Cox Medical Center", city: "Springfield", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4640,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO042",GROUP_DESC:"MO042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4640,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4640,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5640 ,protection_group_id: -4640, protection_element_id:-4640], primaryKey: false);
      insert('organizations', [ id: 104626, nci_institute_code: "MO043", name: "Saint John's Hospital", city: "Springfield", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4641,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO043",GROUP_DESC:"MO043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4641,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4641,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5641 ,protection_group_id: -4641, protection_element_id:-4641], primaryKey: false);
      insert('organizations', [ id: 104627, nci_institute_code: "MO045", name: "Liberty Radiation Oncology Clinic", city: "Kansas City", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4642,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO045",GROUP_DESC:"MO045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4642,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4642,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5642 ,protection_group_id: -4642, protection_element_id:-4642], primaryKey: false);
      insert('organizations', [ id: 104628, nci_institute_code: "MO046", name: "Missouri Baptist Medical Center", city: "St. Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4643,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO046",GROUP_DESC:"MO046 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4643,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO046",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO046",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4643,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO046", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5643 ,protection_group_id: -4643, protection_element_id:-4643], primaryKey: false);
      insert('organizations', [ id: 104629, nci_institute_code: "MO047", name: "Barnard Cancer Center", city: "St. Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4644,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO047",GROUP_DESC:"MO047 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4644,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO047",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO047",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4644,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO047", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5644 ,protection_group_id: -4644, protection_element_id:-4644], primaryKey: false);
      insert('organizations', [ id: 104630, nci_institute_code: "MO048", name: "Saint Joseph Hospital", city: "St. Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4645,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO048",GROUP_DESC:"MO048 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4645,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO048",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO048",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4645,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO048", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5645 ,protection_group_id: -4645, protection_element_id:-4645], primaryKey: false);
      insert('organizations', [ id: 104631, nci_institute_code: "MO049", name: "Freeman Health System", city: "Joplin", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4646,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO049",GROUP_DESC:"MO049 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4646,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO049",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO049",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4646,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO049", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5646 ,protection_group_id: -4646, protection_element_id:-4646], primaryKey: false);
      insert('organizations', [ id: 104632, nci_institute_code: "MO050", name: "Truman Medical Center", city: "Kansas City", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4647,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO050",GROUP_DESC:"MO050 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4647,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO050",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO050",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4647,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO050", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5647 ,protection_group_id: -4647, protection_element_id:-4647], primaryKey: false);
      insert('organizations', [ id: 104633, nci_institute_code: "MO051", name: "Saint Louis Cancer and Breast Institute", city: "St. Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4648,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO051",GROUP_DESC:"MO051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4648,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4648,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5648 ,protection_group_id: -4648, protection_element_id:-4648], primaryKey: false);
      insert('organizations', [ id: 104634, nci_institute_code: "MO052", name: "Phelps County Regional Medical Center", city: "Rolla", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4649,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO052",GROUP_DESC:"MO052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4649,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4649,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5649 ,protection_group_id: -4649, protection_element_id:-4649], primaryKey: false);
      insert('organizations', [ id: 104635, nci_institute_code: "MO053", name: "Barnes West County Hospital", city: "St. Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4650,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO053",GROUP_DESC:"MO053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4650,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4650,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5650 ,protection_group_id: -4650, protection_element_id:-4650], primaryKey: false);
      insert('organizations', [ id: 104636, nci_institute_code: "MO054", name: "Deaconess Medical Center", city: "St. Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4651,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO054",GROUP_DESC:"MO054 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4651,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO054",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO054",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4651,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO054", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5651 ,protection_group_id: -4651, protection_element_id:-4651], primaryKey: false);
      insert('organizations', [ id: 104637, nci_institute_code: "MO055", name: "Excelsior Springs Medical Center", city: "Excelsior Springs", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4652,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO055",GROUP_DESC:"MO055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4652,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4652,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5652 ,protection_group_id: -4652, protection_element_id:-4652], primaryKey: false);
      insert('organizations', [ id: 104638, nci_institute_code: "MO056", name: "Heartland Regional Medical Center", city: "St Joseph", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4653,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO056",GROUP_DESC:"MO056 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4653,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO056",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO056",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4653,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO056", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5653 ,protection_group_id: -4653, protection_element_id:-4653], primaryKey: false);
      insert('organizations', [ id: 104639, nci_institute_code: "MO057", name: "Independence Regional Health", city: "Kansas City", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4654,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO057",GROUP_DESC:"MO057 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4654,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO057",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO057",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4654,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO057", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5654 ,protection_group_id: -4654, protection_element_id:-4654], primaryKey: false);
      insert('organizations', [ id: 104640, nci_institute_code: "MO058", name: "Kansas City CCOP", city: "Kansas City", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4655,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO058",GROUP_DESC:"MO058 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4655,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO058",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO058",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4655,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO058", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5655 ,protection_group_id: -4655, protection_element_id:-4655], primaryKey: false);
      insert('organizations', [ id: 104641, nci_institute_code: "MO059", name: "University of Kansas", city: "Kansas City", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4656,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO059",GROUP_DESC:"MO059 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4656,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO059",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO059",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4656,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO059", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5656 ,protection_group_id: -4656, protection_element_id:-4656], primaryKey: false);
      insert('organizations', [ id: 104642, nci_institute_code: "MO060", name: "Midwest CGOP", city: "Kansas City", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4657,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO060",GROUP_DESC:"MO060 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4657,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO060",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO060",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4657,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO060", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5657 ,protection_group_id: -4657, protection_element_id:-4657], primaryKey: false);
      insert('organizations', [ id: 104643, nci_institute_code: "MO061", name: "Cancer Research for The Ozarks Springfield", city: "Springfield", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4658,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO061",GROUP_DESC:"MO061 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4658,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO061",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO061",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4658,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO061", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5658 ,protection_group_id: -4658, protection_element_id:-4658], primaryKey: false);
      insert('organizations', [ id: 104644, nci_institute_code: "MO062", name: "Spelman Memorial Hospital", city: "Smithville", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4659,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO062",GROUP_DESC:"MO062 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4659,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO062",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO062",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4659,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO062", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5659 ,protection_group_id: -4659, protection_element_id:-4659], primaryKey: false);
      insert('organizations', [ id: 104645, nci_institute_code: "MO063", name: "Spelman - Saint Luke's", city: "Smithville", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4660,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO063",GROUP_DESC:"MO063 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4660,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO063",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO063",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4660,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO063", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5660 ,protection_group_id: -4660, protection_element_id:-4660], primaryKey: false);
      insert('organizations', [ id: 104646, nci_institute_code: "MO064", name: "Saint Louis CCOP", city: "St. Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4661,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO064",GROUP_DESC:"MO064 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4661,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO064",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO064",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4661,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO064", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5661 ,protection_group_id: -4661, protection_element_id:-4661], primaryKey: false);
      insert('organizations', [ id: 104647, nci_institute_code: "MO066", name: "Sale Hospital", city: "Neosha", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4662,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO066",GROUP_DESC:"MO066 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4662,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO066",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO066",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4662,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO066", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5662 ,protection_group_id: -4662, protection_element_id:-4662], primaryKey: false);
    }

    void m26() {
        // all26 (25 terms)
      insert('organizations', [ id: 104648, nci_institute_code: "MO067", name: "Midwest Oncology Consortium", city: "Kansas City", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4663,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO067",GROUP_DESC:"MO067 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4663,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO067",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO067",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4663,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO067", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5663 ,protection_group_id: -4663, protection_element_id:-4663], primaryKey: false);
      insert('organizations', [ id: 104649, nci_institute_code: "MO068", name: "United States Air Force Medical Center", city: "Scott Air Force Base", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4664,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO068",GROUP_DESC:"MO068 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4664,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO068",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO068",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4664,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO068", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5664 ,protection_group_id: -4664, protection_element_id:-4664], primaryKey: false);
      insert('organizations', [ id: 104650, nci_institute_code: "MO069", name: "Veterans Administration", city: "Columbia", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4665,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO069",GROUP_DESC:"MO069 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4665,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO069",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO069",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4665,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO069", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5665 ,protection_group_id: -4665, protection_element_id:-4665], primaryKey: false);
      insert('organizations', [ id: 104651, nci_institute_code: "MO070", name: "Chapman Cancer Center", city: "Joplin", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4666,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO070",GROUP_DESC:"MO070 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4666,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO070",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO070",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4666,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO070", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5666 ,protection_group_id: -4666, protection_element_id:-4666], primaryKey: false);
      insert('organizations', [ id: 104652, nci_institute_code: "MO071", name: "Boone Clinic", city: "Columbia", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4667,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO071",GROUP_DESC:"MO071 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4667,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO071",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO071",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4667,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO071", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5667 ,protection_group_id: -4667, protection_element_id:-4667], primaryKey: false);
      insert('organizations', [ id: 104653, nci_institute_code: "MO072", name: "Missouri Delta Medical Center", city: "Sikeston", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4668,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO072",GROUP_DESC:"MO072 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4668,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO072",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO072",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4668,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO072", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5668 ,protection_group_id: -4668, protection_element_id:-4668], primaryKey: false);
      insert('organizations', [ id: 104654, nci_institute_code: "MO073", name: "Cliffview Medical Group", city: "Independence", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4669,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO073",GROUP_DESC:"MO073 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4669,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO073",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO073",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4669,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO073", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5669 ,protection_group_id: -4669, protection_element_id:-4669], primaryKey: false);
      insert('organizations', [ id: 104655, nci_institute_code: "MO074", name: "Grandel Medical Group", city: "St. Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4670,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO074",GROUP_DESC:"MO074 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4670,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO074",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO074",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4670,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO074", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5670 ,protection_group_id: -4670, protection_element_id:-4670], primaryKey: false);
      insert('organizations', [ id: 104656, nci_institute_code: "MO075", name: "Missouri Cancer Associates", city: "Columbia", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4671,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO075",GROUP_DESC:"MO075 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4671,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO075",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO075",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4671,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO075", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5671 ,protection_group_id: -4671, protection_element_id:-4671], primaryKey: false);
      insert('organizations', [ id: 104657, nci_institute_code: "MO076", name: "Cancer and Hematology Center", city: "Springfield", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4672,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO076",GROUP_DESC:"MO076 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4672,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO076",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO076",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4672,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO076", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5672 ,protection_group_id: -4672, protection_element_id:-4672], primaryKey: false);
      insert('organizations', [ id: 104658, nci_institute_code: "MO077", name: "Hematology/Oncology Associates", city: "Columbia", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4673,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO077",GROUP_DESC:"MO077 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4673,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO077",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO077",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4673,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO077", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5673 ,protection_group_id: -4673, protection_element_id:-4673], primaryKey: false);
      insert('organizations', [ id: 104659, nci_institute_code: "MO078", name: "Mo Cardio Vascular/Thor Surg", city: "Columbia", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4674,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO078",GROUP_DESC:"MO078 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4674,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO078",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO078",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4674,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO078", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5674 ,protection_group_id: -4674, protection_element_id:-4674], primaryKey: false);
      insert('organizations', [ id: 104660, nci_institute_code: "MO079", name: "Metro Hematology Oncology ., Incorporated.", city: "St. Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4675,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO079",GROUP_DESC:"MO079 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4675,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO079",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO079",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4675,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO079", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5675 ,protection_group_id: -4675, protection_element_id:-4675], primaryKey: false);
      insert('organizations', [ id: 104661, nci_institute_code: "MO080", name: "Saint Joseph Health Center", city: "Kansas City", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4676,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO080",GROUP_DESC:"MO080 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4676,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO080",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO080",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4676,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO080", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5676 ,protection_group_id: -4676, protection_element_id:-4676], primaryKey: false);
      insert('organizations', [ id: 104662, nci_institute_code: "MO081", name: "Ozarks Medical Center", city: "West Plains", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4677,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO081",GROUP_DESC:"MO081 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4677,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO081",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO081",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4677,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO081", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5677 ,protection_group_id: -4677, protection_element_id:-4677], primaryKey: false);
      insert('organizations', [ id: 104663, nci_institute_code: "MO082", name: "Kirksville Osteopathic Medical Center", city: "Kirksville", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4678,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO082",GROUP_DESC:"MO082 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4678,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO082",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO082",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4678,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO082", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5678 ,protection_group_id: -4678, protection_element_id:-4678], primaryKey: false);
      insert('organizations', [ id: 104664, nci_institute_code: "MO083", name: "Urological Surgical Associates. P.C.", city: "Springfield", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4679,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO083",GROUP_DESC:"MO083 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4679,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO083",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO083",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4679,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO083", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5679 ,protection_group_id: -4679, protection_element_id:-4679], primaryKey: false);
      insert('organizations', [ id: 104665, nci_institute_code: "MO084", name: "Saint Mary's Health Center", city: "Jefferson City", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4680,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO084",GROUP_DESC:"MO084 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4680,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO084",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO084",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4680,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO084", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5680 ,protection_group_id: -4680, protection_element_id:-4680], primaryKey: false);
      insert('organizations', [ id: 104666, nci_institute_code: "MO085", name: "Oncology Hematology Associates of Kansas City", city: "Kansas City", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4681,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO085",GROUP_DESC:"MO085 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4681,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO085",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO085",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4681,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO085", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5681 ,protection_group_id: -4681, protection_element_id:-4681], primaryKey: false);
      insert('organizations', [ id: 104667, nci_institute_code: "MO086", name: "Mid Missouri Medical Foundation", city: "Jefferson City", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4682,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO086",GROUP_DESC:"MO086 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4682,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO086",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO086",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4682,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO086", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5682 ,protection_group_id: -4682, protection_element_id:-4682], primaryKey: false);
      insert('organizations', [ id: 104668, nci_institute_code: "MO087", name: "Capital Regional Medical Center", city: "Jefferson City", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4683,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO087",GROUP_DESC:"MO087 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4683,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO087",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO087",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4683,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO087", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5683 ,protection_group_id: -4683, protection_element_id:-4683], primaryKey: false);
      insert('organizations', [ id: 104669, nci_institute_code: "MO088", name: "Kaiser Permanente", city: "Kansas City", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4684,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO088",GROUP_DESC:"MO088 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4684,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO088",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO088",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4684,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO088", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5684 ,protection_group_id: -4684, protection_element_id:-4684], primaryKey: false);
      insert('organizations', [ id: 104670, nci_institute_code: "MO089", name: "North Kansas City Hospital", city: "Kansas City", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4685,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO089",GROUP_DESC:"MO089 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4685,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO089",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO089",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4685,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO089", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5685 ,protection_group_id: -4685, protection_element_id:-4685], primaryKey: false);
      insert('organizations', [ id: 104671, nci_institute_code: "MO090", name: "Missouri Cancer Care, P.C.", city: "Wentzville", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4686,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO090",GROUP_DESC:"MO090 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4686,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO090",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO090",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4686,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO090", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5686 ,protection_group_id: -4686, protection_element_id:-4686], primaryKey: false);
      insert('organizations', [ id: 104672, nci_institute_code: "MO092", name: "Independence Regional Health Center", city: "Independence", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4687,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO092",GROUP_DESC:"MO092 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4687,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO092",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO092",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4687,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO092", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5687 ,protection_group_id: -4687, protection_element_id:-4687], primaryKey: false);
    }

    void m27() {
        // all27 (25 terms)
      insert('organizations', [ id: 104673, nci_institute_code: "MO093", name: "Radiation Oncology Practice Corporation - North", city: "Kansas City", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4688,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO093",GROUP_DESC:"MO093 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4688,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO093",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO093",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4688,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO093", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5688 ,protection_group_id: -4688, protection_element_id:-4688], primaryKey: false);
      insert('organizations', [ id: 104674, nci_institute_code: "MO094", name: "Radiation Oncology Associates of Kansas City", city: "Kansas City", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4689,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO094",GROUP_DESC:"MO094 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4689,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO094",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO094",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4689,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO094", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5689 ,protection_group_id: -4689, protection_element_id:-4689], primaryKey: false);
      insert('organizations', [ id: 104675, nci_institute_code: "MO095", name: "Lincoln County Memorial Hospital", city: "Troy", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4690,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO095",GROUP_DESC:"MO095 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4690,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO095",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO095",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4690,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO095", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5690 ,protection_group_id: -4690, protection_element_id:-4690], primaryKey: false);
      insert('organizations', [ id: 104676, nci_institute_code: "MO096", name: "Heartland Hematology and Oncology Associates, Incorporated.,", city: "Kansas City", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4691,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO096",GROUP_DESC:"MO096 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4691,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO096",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO096",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4691,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO096", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5691 ,protection_group_id: -4691, protection_element_id:-4691], primaryKey: false);
      insert('organizations', [ id: 104677, nci_institute_code: "MO097", name: "Saint Anthony's Medical Center", city: "St. Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4692,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO097",GROUP_DESC:"MO097 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4692,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO097",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO097",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4692,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO097", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5692 ,protection_group_id: -4692, protection_element_id:-4692], primaryKey: false);
      insert('organizations', [ id: 104678, nci_institute_code: "MO098", name: "The Radiarium Foundation", city: "Independence", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4693,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO098",GROUP_DESC:"MO098 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4693,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO098",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO098",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4693,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO098", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5693 ,protection_group_id: -4693, protection_element_id:-4693], primaryKey: false);
      insert('organizations', [ id: 104679, nci_institute_code: "MO099", name: "Radiation Oncology Practice Corporation South", city: "Kansas City", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4694,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO099",GROUP_DESC:"MO099 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4694,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO099",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO099",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4694,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO099", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5694 ,protection_group_id: -4694, protection_element_id:-4694], primaryKey: false);
      insert('organizations', [ id: 104680, nci_institute_code: "MO101", name: "Bonds Clinic, Incorporated", city: "Rolla", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4695,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO101",GROUP_DESC:"MO101 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4695,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO101",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO101",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4695,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO101", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5695 ,protection_group_id: -4695, protection_element_id:-4695], primaryKey: false);
      insert('organizations', [ id: 104681, nci_institute_code: "MO102", name: "Missouri Hematology & Oncology Care", city: "St. Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4696,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO102",GROUP_DESC:"MO102 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4696,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO102",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO102",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4696,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO102", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5696 ,protection_group_id: -4696, protection_element_id:-4696], primaryKey: false);
      insert('organizations', [ id: 104682, nci_institute_code: "MO103", name: "Springfiled Neurological Ins't, LLC", city: "Springfield", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4697,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO103",GROUP_DESC:"MO103 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4697,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO103",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO103",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4697,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO103", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5697 ,protection_group_id: -4697, protection_element_id:-4697], primaryKey: false);
      insert('organizations', [ id: 104683, nci_institute_code: "MO104", name: "Barnes-Jewish St. Peters Hospital", city: "St.Peters", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4698,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO104",GROUP_DESC:"MO104 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4698,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO104",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO104",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4698,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO104", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5698 ,protection_group_id: -4698, protection_element_id:-4698], primaryKey: false);
      insert('organizations', [ id: 104684, nci_institute_code: "MO107", name: "West County GYN Oncology\\GYN Inc.", city: "St. Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4699,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO107",GROUP_DESC:"MO107 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4699,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO107",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO107",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4699,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO107", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5699 ,protection_group_id: -4699, protection_element_id:-4699], primaryKey: false);
      insert('organizations', [ id: 104685, nci_institute_code: "MO108", name: "Center for Cancer Care and Research", city: "Saint Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4700,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO108",GROUP_DESC:"MO108 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4700,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO108",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO108",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4700,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO108", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5700 ,protection_group_id: -4700, protection_element_id:-4700], primaryKey: false);
      insert('organizations', [ id: 104686, nci_institute_code: "MO109", name: "Saint Louis Oncology Associates, Inc.", city: "St. Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4701,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO109",GROUP_DESC:"MO109 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4701,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO109",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO109",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4701,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO109", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5701 ,protection_group_id: -4701, protection_element_id:-4701], primaryKey: false);
      insert('organizations', [ id: 104687, nci_institute_code: "MO110", name: "Ozarks Regional CCOP", city: "Springfield", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4702,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO110",GROUP_DESC:"MO110 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4702,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO110",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO110",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4702,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO110", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5702 ,protection_group_id: -4702, protection_element_id:-4702], primaryKey: false);
      insert('organizations', [ id: 104688, nci_institute_code: "MO111", name: "Siteman Cancer Center", city: "Saint Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4703,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO111",GROUP_DESC:"MO111 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4703,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO111",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO111",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4703,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO111", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5703 ,protection_group_id: -4703, protection_element_id:-4703], primaryKey: false);
      insert('organizations', [ id: 104689, nci_institute_code: "MO112", name: "Ferrell-Duncan Clinic", city: "Springfield", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4704,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO112",GROUP_DESC:"MO112 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4704,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO112",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO112",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4704,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO112", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5704 ,protection_group_id: -4704, protection_element_id:-4704], primaryKey: false);
      insert('organizations', [ id: 104690, nci_institute_code: "MO113", name: "Ballas Cancer Center, LLC", city: "Saint Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4705,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO113",GROUP_DESC:"MO113 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4705,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO113",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO113",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4705,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO113", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5705 ,protection_group_id: -4705, protection_element_id:-4705], primaryKey: false);
      insert('organizations', [ id: 104691, nci_institute_code: "MO114", name: "Oncology Hema/Assoc of Springfield, M.D. P.C.", city: "Springfield", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4706,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO114",GROUP_DESC:"MO114 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4706,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO114",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO114",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4706,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO114", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5706 ,protection_group_id: -4706, protection_element_id:-4706], primaryKey: false);
      insert('organizations', [ id: 104692, nci_institute_code: "MO115", name: "Saint Luke's Breast Care Center", city: "Chesterfield", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4707,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO115",GROUP_DESC:"MO115 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4707,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO115",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO115",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4707,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO115", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5707 ,protection_group_id: -4707, protection_element_id:-4707], primaryKey: false);
      insert('organizations', [ id: 104693, nci_institute_code: "MO116", name: "University Hematology Oncology, Incorporated", city: "Saint Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4708,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO116",GROUP_DESC:"MO116 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4708,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO116",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO116",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4708,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO116", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5708 ,protection_group_id: -4708, protection_element_id:-4708], primaryKey: false);
      insert('organizations', [ id: 104694, nci_institute_code: "MO117", name: "Audrain Medical Center", city: "Mexico", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4709,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO117",GROUP_DESC:"MO117 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4709,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO117",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO117",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4709,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO117", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5709 ,protection_group_id: -4709, protection_element_id:-4709], primaryKey: false);
      insert('organizations', [ id: 104695, nci_institute_code: "MO118", name: "Suburban Surgical Associates", city: "St. Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4710,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO118",GROUP_DESC:"MO118 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4710,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO118",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO118",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4710,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO118", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5710 ,protection_group_id: -4710, protection_element_id:-4710], primaryKey: false);
      insert('organizations', [ id: 104696, nci_institute_code: "MO119", name: "Associated Urologists, PC", city: "Columbia", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4711,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO119",GROUP_DESC:"MO119 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4711,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO119",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO119",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4711,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO119", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5711 ,protection_group_id: -4711, protection_element_id:-4711], primaryKey: false);
      insert('organizations', [ id: 104697, nci_institute_code: "MO122", name: "Saint Louis Gynecology & Oncology LLC", city: "St. Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4712,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO122",GROUP_DESC:"MO122 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4712,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO122",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO122",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4712,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO122", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5712 ,protection_group_id: -4712, protection_element_id:-4712], primaryKey: false);
    }

    void m28() {
        // all28 (25 terms)
      insert('organizations', [ id: 104698, nci_institute_code: "MO124", name: "Missouri Cancer Care PC - Saint Charles", city: "Saint Charles", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4713,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO124",GROUP_DESC:"MO124 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4713,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO124",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO124",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4713,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO124", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5713 ,protection_group_id: -4713, protection_element_id:-4713], primaryKey: false);
      insert('organizations', [ id: 104699, nci_institute_code: "MO126", name: "Ozark Medical Surgical Associates", city: "Springfield", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4714,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO126",GROUP_DESC:"MO126 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4714,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO126",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO126",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4714,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO126", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5714 ,protection_group_id: -4714, protection_element_id:-4714], primaryKey: false);
      insert('organizations', [ id: 104700, nci_institute_code: "MO127", name: "Hulston Cancer Center", city: "Springfield", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4715,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO127",GROUP_DESC:"MO127 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4715,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO127",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO127",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4715,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO127", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5715 ,protection_group_id: -4715, protection_element_id:-4715], primaryKey: false);
      insert('organizations', [ id: 104701, nci_institute_code: "MO128", name: "Urology Group", city: "Springfield", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4716,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO128",GROUP_DESC:"MO128 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4716,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO128",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO128",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4716,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO128", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5716 ,protection_group_id: -4716, protection_element_id:-4716], primaryKey: false);
      insert('organizations', [ id: 104702, nci_institute_code: "MO129", name: "Columbia Comprehensive Cancer Care Clinic", city: "Columbia", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4717,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO129",GROUP_DESC:"MO129 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4717,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO129",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO129",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4717,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO129", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5717 ,protection_group_id: -4717, protection_element_id:-4717], primaryKey: false);
      insert('organizations', [ id: 104703, nci_institute_code: "MO130", name: "St. John's Clinic - OB/GYN - Smith-Glynn-Callaway", city: "Springfield", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4718,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO130",GROUP_DESC:"MO130 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4718,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO130",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO130",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4718,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO130", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5718 ,protection_group_id: -4718, protection_element_id:-4718], primaryKey: false);
      insert('organizations', [ id: 104704, nci_institute_code: "MO131", name: "St. Louis Cancer & Breast Institute", city: "St. Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4719,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO131",GROUP_DESC:"MO131 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4719,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO131",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO131",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4719,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO131", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5719 ,protection_group_id: -4719, protection_element_id:-4719], primaryKey: false);
      insert('organizations', [ id: 104705, nci_institute_code: "MO132", name: "Kansas City Cancer Center-Lee's Summit", city: "Lee's Summit", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4720,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO132",GROUP_DESC:"MO132 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4720,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO132",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO132",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4720,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO132", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5720 ,protection_group_id: -4720, protection_element_id:-4720], primaryKey: false);
      insert('organizations', [ id: 104706, nci_institute_code: "MO133", name: "Cancer Institute of Cape Girardeau", city: "Cape Girardeau", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4721,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO133",GROUP_DESC:"MO133 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4721,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO133",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO133",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4721,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO133", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5721 ,protection_group_id: -4721, protection_element_id:-4721], primaryKey: false);
      insert('organizations', [ id: 104707, nci_institute_code: "MO134", name: "Missouri Cancer Care, P.C.", city: "St. Charles", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4722,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO134",GROUP_DESC:"MO134 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4722,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO134",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO134",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4722,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO134", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5722 ,protection_group_id: -4722, protection_element_id:-4722], primaryKey: false);
      insert('organizations', [ id: 104708, nci_institute_code: "MO135", name: "Bothwell Regional Health Center", city: "Sedalia", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4723,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO135",GROUP_DESC:"MO135 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4723,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO135",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO135",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4723,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO135", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5723 ,protection_group_id: -4723, protection_element_id:-4723], primaryKey: false);
      insert('organizations', [ id: 104709, nci_institute_code: "MO136", name: "Kansas City Cancer Centers - Central", city: "Kansas City", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4724,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO136",GROUP_DESC:"MO136 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4724,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO136",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO136",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4724,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO136", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5724 ,protection_group_id: -4724, protection_element_id:-4724], primaryKey: false);
      insert('organizations', [ id: 104710, nci_institute_code: "MO137", name: "Kansas City Cancer Centers - North", city: "Kansas City", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4725,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO137",GROUP_DESC:"MO137 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4725,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO137",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO137",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4725,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO137", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5725 ,protection_group_id: -4725, protection_element_id:-4725], primaryKey: false);
      insert('organizations', [ id: 104711, nci_institute_code: "MO138", name: "Resource Center for Gynecologic Oncology", city: "Kansas City", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4726,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO138",GROUP_DESC:"MO138 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4726,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO138",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO138",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4726,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO138", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5726 ,protection_group_id: -4726, protection_element_id:-4726], primaryKey: false);
      insert('organizations', [ id: 104712, nci_institute_code: "MO139", name: "Heartland Surgical Associates", city: "Kansas City", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4727,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO139",GROUP_DESC:"MO139 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4727,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO139",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO139",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4727,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO139", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5727 ,protection_group_id: -4727, protection_element_id:-4727], primaryKey: false);
      insert('organizations', [ id: 104713, nci_institute_code: "MO140", name: "Saint Luke's Cancer Institute", city: "Kansas City", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4728,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO140",GROUP_DESC:"MO140 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4728,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO140",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO140",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4728,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO140", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5728 ,protection_group_id: -4728, protection_element_id:-4728], primaryKey: false);
      insert('organizations', [ id: 104714, nci_institute_code: "MO141", name: "Kansas City Surgical Group", city: "Kansas City", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4729,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO141",GROUP_DESC:"MO141 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4729,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO141",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO141",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4729,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO141", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5729 ,protection_group_id: -4729, protection_element_id:-4729], primaryKey: false);
      insert('organizations', [ id: 104715, nci_institute_code: "MO142", name: "Capital Regional Medical Center - Cancer Center", city: "Jefferson City", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4730,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO142",GROUP_DESC:"MO142 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4730,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO142",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO142",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4730,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO142", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5730 ,protection_group_id: -4730, protection_element_id:-4730], primaryKey: false);
      insert('organizations', [ id: 104716, nci_institute_code: "MO144", name: "The Cancer Institute- Peet Building", city: "Kansas City", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4731,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO144",GROUP_DESC:"MO144 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4731,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO144",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO144",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4731,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO144", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5731 ,protection_group_id: -4731, protection_element_id:-4731], primaryKey: false);
      insert('organizations', [ id: 104717, nci_institute_code: "MO145", name: "Hematology Oncology Associates, LLC", city: "Cape Girardeau", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4732,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO145",GROUP_DESC:"MO145 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4732,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO145",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO145",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4732,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO145", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5732 ,protection_group_id: -4732, protection_element_id:-4732], primaryKey: false);
      insert('organizations', [ id: 104718, nci_institute_code: "MO146", name: "Cape Girardeau Physician Associates", city: "Cape Girardeau", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4733,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO146",GROUP_DESC:"MO146 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4733,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO146",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO146",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4733,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO146", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5733 ,protection_group_id: -4733, protection_element_id:-4733], primaryKey: false);
      insert('organizations', [ id: 104719, nci_institute_code: "MO147", name: "Saint Louis University Cancer Center", city: "St. Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4734,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO147",GROUP_DESC:"MO147 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4734,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO147",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO147",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4734,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO147", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5734 ,protection_group_id: -4734, protection_element_id:-4734], primaryKey: false);
      insert('organizations', [ id: 104720, nci_institute_code: "MO148", name: "Cardiothoracic Surgery North LLC", city: "Saint Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4735,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO148",GROUP_DESC:"MO148 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4735,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO148",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO148",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4735,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO148", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5735 ,protection_group_id: -4735, protection_element_id:-4735], primaryKey: false);
      insert('organizations', [ id: 104721, nci_institute_code: "MO149", name: "Specialists in Oncology Hematology PC", city: "Saint Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4736,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO149",GROUP_DESC:"MO149 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4736,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO149",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO149",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4736,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO149", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5736 ,protection_group_id: -4736, protection_element_id:-4736], primaryKey: false);
      insert('organizations', [ id: 104722, nci_institute_code: "MO150", name: "Heartland Cancer Research CCOP", city: "St. Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4737,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO150",GROUP_DESC:"MO150 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4737,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO150",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO150",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4737,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO150", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5737 ,protection_group_id: -4737, protection_element_id:-4737], primaryKey: false);
    }

    void m29() {
        // all29 (25 terms)
      insert('organizations', [ id: 104723, nci_institute_code: "MO151", name: "Midwest Hematology Oncology Consultants LTD", city: "St. Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4738,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO151",GROUP_DESC:"MO151 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4738,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO151",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO151",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4738,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO151", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5738 ,protection_group_id: -4738, protection_element_id:-4738], primaryKey: false);
      insert('organizations', [ id: 104724, nci_institute_code: "MO152", name: "Siteman Cancer Center - Saint Peters", city: "Saint Peters", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4739,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO152",GROUP_DESC:"MO152 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4739,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO152",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO152",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4739,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO152", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5739 ,protection_group_id: -4739, protection_element_id:-4739], primaryKey: false);
      insert('organizations', [ id: 104725, nci_institute_code: "MO153", name: "Freeman Cancer Institute - Lamar", city: "Lamar", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4740,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO153",GROUP_DESC:"MO153 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4740,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO153",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO153",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4740,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO153", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5740 ,protection_group_id: -4740, protection_element_id:-4740], primaryKey: false);
      insert('organizations', [ id: 104726, nci_institute_code: "MO154", name: "Saint John's Cancer Center", city: "Springfield", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4741,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO154",GROUP_DESC:"MO154 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4741,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO154",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO154",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4741,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO154", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5741 ,protection_group_id: -4741, protection_element_id:-4741], primaryKey: false);
      insert('organizations', [ id: 104727, nci_institute_code: "MO155", name: "Cape Gynecologic Oncology", city: "Cape Girardeau", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4742,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO155",GROUP_DESC:"MO155 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4742,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO155",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO155",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4742,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO155", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5742 ,protection_group_id: -4742, protection_element_id:-4742], primaryKey: false);
      insert('organizations', [ id: 104728, nci_institute_code: "MO156", name: "Saint Joseph Oncology Inc", city: "Saint Joseph", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4743,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO156",GROUP_DESC:"MO156 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4743,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO156",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO156",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4743,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO156", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5743 ,protection_group_id: -4743, protection_element_id:-4743], primaryKey: false);
      insert('organizations', [ id: 104729, nci_institute_code: "MO157", name: "John Cochran Veteran's Affairs Medical Center", city: "St Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4744,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO157",GROUP_DESC:"MO157 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4744,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO157",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO157",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4744,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO157", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5744 ,protection_group_id: -4744, protection_element_id:-4744], primaryKey: false);
      insert('organizations', [ id: 104730, nci_institute_code: "MO158", name: "Women's Oncology Care", city: "Saint Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4745,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO158",GROUP_DESC:"MO158 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4745,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO158",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO158",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4745,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO158", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5745 ,protection_group_id: -4745, protection_element_id:-4745], primaryKey: false);
      insert('organizations', [ id: 104731, nci_institute_code: "MO159", name: "Saint John's Clinic - Ear Nose and Throat", city: "Springfield", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4746,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO159",GROUP_DESC:"MO159 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4746,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO159",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO159",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4746,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO159", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5746 ,protection_group_id: -4746, protection_element_id:-4746], primaryKey: false);
      insert('organizations', [ id: 104732, nci_institute_code: "MO160", name: "Hematology and Oncology PC", city: "Joplin", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4747,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO160",GROUP_DESC:"MO160 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4747,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO160",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO160",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4747,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO160", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5747 ,protection_group_id: -4747, protection_element_id:-4747], primaryKey: false);
      insert('organizations', [ id: 104733, nci_institute_code: "MO161", name: "SSM Saint Charles Clinic Medical Group", city: "Saint Charles", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4748,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO161",GROUP_DESC:"MO161 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4748,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO161",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO161",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4748,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO161", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5748 ,protection_group_id: -4748, protection_element_id:-4748], primaryKey: false);
      insert('organizations', [ id: 104734, nci_institute_code: "MO162", name: "Saint Louis Cancer Care LLP", city: "Chesterfield", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4749,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO162",GROUP_DESC:"MO162 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4749,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO162",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO162",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4749,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO162", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5749 ,protection_group_id: -4749, protection_element_id:-4749], primaryKey: false);
      insert('organizations', [ id: 104735, nci_institute_code: "MO163", name: "Kansas City Cancer Center - South", city: "Kansas City", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4750,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO163",GROUP_DESC:"MO163 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4750,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO163",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO163",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4750,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO163", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5750 ,protection_group_id: -4750, protection_element_id:-4750], primaryKey: false);
      insert('organizations', [ id: 104736, nci_institute_code: "MO164", name: "Saint John's Mercy Cardiovascular and Thoracic Surgery Associates LLC", city: "St. Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4751,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO164",GROUP_DESC:"MO164 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4751,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO164",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO164",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4751,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO164", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5751 ,protection_group_id: -4751, protection_element_id:-4751], primaryKey: false);
      insert('organizations', [ id: 104737, nci_institute_code: "MO165", name: "Saint Luke's Surgical Specialists", city: "Kansas City", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4752,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO165",GROUP_DESC:"MO165 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4752,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO165",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO165",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4752,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO165", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5752 ,protection_group_id: -4752, protection_element_id:-4752], primaryKey: false);
      insert('organizations', [ id: 104738, nci_institute_code: "MS001", name: "Northwest Mississippi Regional. Medical Center", city: "Clarksdale", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4753,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS001",GROUP_DESC:"MS001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4753,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4753,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5753 ,protection_group_id: -4753, protection_element_id:-4753], primaryKey: false);
      insert('organizations', [ id: 104739, nci_institute_code: "MS002", name: "Delta Medical Center", city: "Greenville", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4754,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS002",GROUP_DESC:"MS002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4754,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4754,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5754 ,protection_group_id: -4754, protection_element_id:-4754], primaryKey: false);
      insert('organizations', [ id: 104740, nci_institute_code: "MS003", name: "North Mississippi Hematology and Oncology Associates, Ltd", city: "Tupelo", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4755,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS003",GROUP_DESC:"MS003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4755,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4755,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5755 ,protection_group_id: -4755, protection_element_id:-4755], primaryKey: false);
      insert('organizations', [ id: 104741, nci_institute_code: "MS004", name: "Mississippi Baptist Medical Center", city: "Jackson", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4756,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS004",GROUP_DESC:"MS004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4756,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4756,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5756 ,protection_group_id: -4756, protection_element_id:-4756], primaryKey: false);
      insert('organizations', [ id: 104742, nci_institute_code: "MS005", name: "University of Mississippi Medical Center", city: "Jackson", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4757,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS005",GROUP_DESC:"MS005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4757,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4757,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5757 ,protection_group_id: -4757, protection_element_id:-4757], primaryKey: false);
      insert('organizations', [ id: 104743, nci_institute_code: "MS006", name: "Veterans Affairs Medical Center", city: "Jackson", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4758,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS006",GROUP_DESC:"MS006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4758,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4758,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5758 ,protection_group_id: -4758, protection_element_id:-4758], primaryKey: false);
      insert('organizations', [ id: 104744, nci_institute_code: "MS007", name: "Jeff Anderson Regional Medical Center", city: "Meridian", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4759,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS007",GROUP_DESC:"MS007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4759,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4759,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5759 ,protection_group_id: -4759, protection_element_id:-4759], primaryKey: false);
      insert('organizations', [ id: 104745, nci_institute_code: "MS008", name: "Forest General Hospital", city: "Hattiesburg", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4760,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS008",GROUP_DESC:"MS008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4760,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4760,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5760 ,protection_group_id: -4760, protection_element_id:-4760], primaryKey: false);
      insert('organizations', [ id: 104746, nci_institute_code: "MS009", name: "Hattiesburg Clinic", city: "Hattiesburg", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4761,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS009",GROUP_DESC:"MS009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4761,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4761,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5761 ,protection_group_id: -4761, protection_element_id:-4761], primaryKey: false);
      insert('organizations', [ id: 104747, nci_institute_code: "MS011", name: "Keesler Medical Center", city: "Kessler AFB", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4762,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS011",GROUP_DESC:"MS011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4762,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4762,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5762 ,protection_group_id: -4762, protection_element_id:-4762], primaryKey: false);
    }

    void m30() {
        // all30 (25 terms)
      insert('organizations', [ id: 104748, nci_institute_code: "MS012", name: "Singing River Hospital System", city: "Pascagoula", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4763,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS012",GROUP_DESC:"MS012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4763,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4763,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5763 ,protection_group_id: -4763, protection_element_id:-4763], primaryKey: false);
      insert('organizations', [ id: 104749, nci_institute_code: "MS013", name: "Methodist Medical Center", city: "Jackson", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4764,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS013",GROUP_DESC:"MS013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4764,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4764,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5764 ,protection_group_id: -4764, protection_element_id:-4764], primaryKey: false);
      insert('organizations', [ id: 104750, nci_institute_code: "MS014", name: "Jackson Oncology Associates PLLC", city: "Jackson", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4765,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS014",GROUP_DESC:"MS014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4765,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4765,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5765 ,protection_group_id: -4765, protection_element_id:-4765], primaryKey: false);
      insert('organizations', [ id: 104751, nci_institute_code: "MS015", name: "Keesler Air Force Base", city: "Biloxi", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4766,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS015",GROUP_DESC:"MS015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4766,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4766,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5766 ,protection_group_id: -4766, protection_element_id:-4766], primaryKey: false);
      insert('organizations', [ id: 104752, nci_institute_code: "MS016", name: "Hematology and Oncology Clinic", city: "Hattiesburg", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4767,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS016",GROUP_DESC:"MS016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4767,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4767,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5767 ,protection_group_id: -4767, protection_element_id:-4767], primaryKey: false);
      insert('organizations', [ id: 104753, nci_institute_code: "MS017", name: "Gulfport Memorial Hospital", city: "Gulfport", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4768,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS017",GROUP_DESC:"MS017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4768,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4768,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5768 ,protection_group_id: -4768, protection_element_id:-4768], primaryKey: false);
      insert('organizations', [ id: 104754, nci_institute_code: "MS018", name: "Methodist Hospital", city: "Hattiesburg", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4769,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS018",GROUP_DESC:"MS018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4769,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4769,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5769 ,protection_group_id: -4769, protection_element_id:-4769], primaryKey: false);
      insert('organizations', [ id: 104755, nci_institute_code: "MS019", name: "Gulf Coast Medical Center", city: "Biloxi", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4770,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS019",GROUP_DESC:"MS019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4770,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4770,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5770 ,protection_group_id: -4770, protection_element_id:-4770], primaryKey: false);
      insert('organizations', [ id: 104756, nci_institute_code: "MS020", name: "Biloxi Regional Medical Center", city: "Biloxi", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4771,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS020",GROUP_DESC:"MS020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4771,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4771,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5771 ,protection_group_id: -4771, protection_element_id:-4771], primaryKey: false);
      insert('organizations', [ id: 104757, nci_institute_code: "MS021", name: "Ocean Springs Hospital", city: "Ocean Springs", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4772,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS021",GROUP_DESC:"MS021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4772,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4772,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5772 ,protection_group_id: -4772, protection_element_id:-4772], primaryKey: false);
      insert('organizations', [ id: 104758, nci_institute_code: "MS022", name: "Parkview Regional Medical Center", city: "Vicksburg", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4773,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS022",GROUP_DESC:"MS022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4773,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4773,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5773 ,protection_group_id: -4773, protection_element_id:-4773], primaryKey: false);
      insert('organizations', [ id: 104759, nci_institute_code: "MS023", name: "Meridian Radiation Oncology Center", city: "Meridian", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4774,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS023",GROUP_DESC:"MS023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4774,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4774,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5774 ,protection_group_id: -4774, protection_element_id:-4774], primaryKey: false);
      insert('organizations', [ id: 104760, nci_institute_code: "MS025", name: "St. Dominic-Jackson Memorial Hospital", city: "Jackson", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4775,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS025",GROUP_DESC:"MS025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4775,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4775,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5775 ,protection_group_id: -4775, protection_element_id:-4775], primaryKey: false);
      insert('organizations', [ id: 104761, nci_institute_code: "MS026", name: "Baptist Memorial Hospital", city: "Oxford", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4776,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS026",GROUP_DESC:"MS026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4776,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4776,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5776 ,protection_group_id: -4776, protection_element_id:-4776], primaryKey: false);
      insert('organizations', [ id: 104762, nci_institute_code: "MS028", name: "The Medical Oncology Group, P.A.", city: "Gulfport", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4777,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS028",GROUP_DESC:"MS028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4777,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4777,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5777 ,protection_group_id: -4777, protection_element_id:-4777], primaryKey: false);
      insert('organizations', [ id: 104763, nci_institute_code: "MS029", name: "Veterans Affairs Medical Center", city: "Biloxi", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4778,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS029",GROUP_DESC:"MS029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4778,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4778,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5778 ,protection_group_id: -4778, protection_element_id:-4778], primaryKey: false);
      insert('organizations', [ id: 104764, nci_institute_code: "MS030", name: "Coast Oncology/Hematology, PLLC", city: "Biloxi", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4779,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS030",GROUP_DESC:"MS030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4779,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4779,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5779 ,protection_group_id: -4779, protection_element_id:-4779], primaryKey: false);
      insert('organizations', [ id: 104765, nci_institute_code: "MS032", name: "CCOP, Baptist Cancer Institute", city: "Columbus", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4780,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS032",GROUP_DESC:"MS032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4780,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4780,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5780 ,protection_group_id: -4780, protection_element_id:-4780], primaryKey: false);
      insert('organizations', [ id: 104766, nci_institute_code: "MS033", name: "Mississippi Cancer Institute", city: "Mccomb", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4781,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS033",GROUP_DESC:"MS033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4781,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4781,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5781 ,protection_group_id: -4781, protection_element_id:-4781], primaryKey: false);
      insert('organizations', [ id: 104767, nci_institute_code: "MS034", name: "Natchez Oncology Clinic", city: "Natchez", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4782,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS034",GROUP_DESC:"MS034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4782,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4782,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5782 ,protection_group_id: -4782, protection_element_id:-4782], primaryKey: false);
      insert('organizations', [ id: 104768, nci_institute_code: "MS035", name: "Meridian Oncology Associates", city: "Meridian", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4783,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS035",GROUP_DESC:"MS035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4783,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4783,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5783 ,protection_group_id: -4783, protection_element_id:-4783], primaryKey: false);
      insert('organizations', [ id: 104769, nci_institute_code: "MS036", name: "Baptist Memorial Hosp-Gld'n Triangle", city: "Columbus", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4784,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS036",GROUP_DESC:"MS036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4784,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4784,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5784 ,protection_group_id: -4784, protection_element_id:-4784], primaryKey: false);
      insert('organizations', [ id: 104770, nci_institute_code: "MS037", name: "University of Mississippi Medical Center", city: "Jackson", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4785,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS037",GROUP_DESC:"MS037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4785,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4785,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5785 ,protection_group_id: -4785, protection_element_id:-4785], primaryKey: false);
      insert('organizations', [ id: 104771, nci_institute_code: "MS038", name: "Orange Grove Medical Specialties", city: "Gulfport", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4786,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS038",GROUP_DESC:"MS038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4786,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4786,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5786 ,protection_group_id: -4786, protection_element_id:-4786], primaryKey: false);
      insert('organizations', [ id: 104772, nci_institute_code: "MS039", name: "Delta Health Center", city: "Mound Bayou", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4787,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS039",GROUP_DESC:"MS039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4787,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4787,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5787 ,protection_group_id: -4787, protection_element_id:-4787], primaryKey: false);
    }

    void m31() {
        // all31 (25 terms)
      insert('organizations', [ id: 104773, nci_institute_code: "MS040", name: "Mid Delta Family Practice", city: "Cleveland", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4788,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS040",GROUP_DESC:"MS040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4788,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4788,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5788 ,protection_group_id: -4788, protection_element_id:-4788], primaryKey: false);
      insert('organizations', [ id: 104774, nci_institute_code: "MS041", name: "South Mississippi Surgeons", city: "Pascagoula", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4789,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS041",GROUP_DESC:"MS041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4789,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4789,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5789 ,protection_group_id: -4789, protection_element_id:-4789], primaryKey: false);
      insert('organizations', [ id: 104775, nci_institute_code: "MS042", name: "Center for Digestive Health", city: "Tupelo", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4790,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS042",GROUP_DESC:"MS042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4790,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4790,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5790 ,protection_group_id: -4790, protection_element_id:-4790], primaryKey: false);
      insert('organizations', [ id: 104776, nci_institute_code: "MS043", name: "Central Mississippi Medical Center", city: "Jackson", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4791,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS043",GROUP_DESC:"MS043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4791,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4791,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5791 ,protection_group_id: -4791, protection_element_id:-4791], primaryKey: false);
      insert('organizations', [ id: 104777, nci_institute_code: "MS044", name: "Memorial Cancer Center", city: "Gulfport", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4792,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS044",GROUP_DESC:"MS044 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4792,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS044",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS044",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4792,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS044", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5792 ,protection_group_id: -4792, protection_element_id:-4792], primaryKey: false);
      insert('organizations', [ id: 104778, nci_institute_code: "MS045", name: "North Mississippi Medical Center", city: "Tupelo", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4793,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS045",GROUP_DESC:"MS045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4793,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4793,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5793 ,protection_group_id: -4793, protection_element_id:-4793], primaryKey: false);
      insert('organizations', [ id: 104779, nci_institute_code: "MS046", name: "Saint Dominic Neurosurgery Associates", city: "Jackson", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4794,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS046",GROUP_DESC:"MS046 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4794,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS046",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS046",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4794,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS046", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5794 ,protection_group_id: -4794, protection_element_id:-4794], primaryKey: false);
      insert('organizations', [ id: 104780, nci_institute_code: "MS047", name: "Jackson Gynecologic Oncology", city: "Jackson", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4795,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS047",GROUP_DESC:"MS047 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4795,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS047",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS047",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4795,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS047", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5795 ,protection_group_id: -4795, protection_element_id:-4795], primaryKey: false);
      insert('organizations', [ id: 104781, nci_institute_code: "MS048", name: "Family Cancer Center PLLC", city: "Southaven", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4796,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS048",GROUP_DESC:"MS048 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4796,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS048",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS048",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4796,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS048", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5796 ,protection_group_id: -4796, protection_element_id:-4796], primaryKey: false);
      insert('organizations', [ id: 104782, nci_institute_code: "MS049", name: "Surgical Clinic Associates PA", city: "Jackson", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4797,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS049",GROUP_DESC:"MS049 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4797,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS049",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS049",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4797,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS049", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5797 ,protection_group_id: -4797, protection_element_id:-4797], primaryKey: false);
      insert('organizations', [ id: 104783, nci_institute_code: "MS050", name: "The West Clinic - Southaven", city: "Southaven", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4798,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS050",GROUP_DESC:"MS050 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4798,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS050",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS050",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4798,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS050", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5798 ,protection_group_id: -4798, protection_element_id:-4798], primaryKey: false);
      insert('organizations', [ id: 104784, nci_institute_code: "MT001", name: "Saint Vincent Hospital", city: "Billings", state: "MT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4799,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT001",GROUP_DESC:"MT001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4799,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MT001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MT001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4799,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5799 ,protection_group_id: -4799, protection_element_id:-4799], primaryKey: false);
      insert('organizations', [ id: 104785, nci_institute_code: "MT002", name: "Billings Clinic", city: "Billings", state: "MT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4800,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT002",GROUP_DESC:"MT002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4800,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MT002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MT002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4800,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5800 ,protection_group_id: -4800, protection_element_id:-4800], primaryKey: false);
      insert('organizations', [ id: 104786, nci_institute_code: "MT003", name: "Columbus Hospital", city: "Great Falls", state: "MT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4801,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT003",GROUP_DESC:"MT003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4801,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MT003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MT003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4801,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5801 ,protection_group_id: -4801, protection_element_id:-4801], primaryKey: false);
      insert('organizations', [ id: 104787, nci_institute_code: "MT004", name: "Mount Deaconess Medical Center", city: "Great Falls", state: "MT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4802,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT004",GROUP_DESC:"MT004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4802,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MT004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MT004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4802,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5802 ,protection_group_id: -4802, protection_element_id:-4802], primaryKey: false);
      insert('organizations', [ id: 104788, nci_institute_code: "MT005", name: "Saint Peter's Community Hospital", city: "Helena", state: "MT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4803,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT005",GROUP_DESC:"MT005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4803,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MT005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MT005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4803,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5803 ,protection_group_id: -4803, protection_element_id:-4803], primaryKey: false);
      insert('organizations', [ id: 104789, nci_institute_code: "MT006", name: "Saint James Community Hospital and Cancer Treatment Center", city: "Butte", state: "MT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4804,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT006",GROUP_DESC:"MT006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4804,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MT006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MT006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4804,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5804 ,protection_group_id: -4804, protection_element_id:-4804], primaryKey: false);
      insert('organizations', [ id: 104790, nci_institute_code: "MT007", name: "Community Medical Hospital", city: "Missoula", state: "MT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4805,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT007",GROUP_DESC:"MT007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4805,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MT007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MT007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4805,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5805 ,protection_group_id: -4805, protection_element_id:-4805], primaryKey: false);
      insert('organizations', [ id: 104791, nci_institute_code: "MT008", name: "University of Montana", city: "Missoula", state: "MT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4806,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT008",GROUP_DESC:"MT008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4806,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MT008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MT008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4806,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5806 ,protection_group_id: -4806, protection_element_id:-4806], primaryKey: false);
      insert('organizations', [ id: 104792, nci_institute_code: "MT009", name: "Saint Patrick Hospital - Community Hospital", city: "Missoula", state: "MT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4807,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT009",GROUP_DESC:"MT009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4807,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MT009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MT009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4807,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5807 ,protection_group_id: -4807, protection_element_id:-4807], primaryKey: false);
      insert('organizations', [ id: 104793, nci_institute_code: "MT010", name: "Kalispell Regional Medical Center", city: "Kalispell", state: "MT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4808,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT010",GROUP_DESC:"MT010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4808,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MT010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MT010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4808,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5808 ,protection_group_id: -4808, protection_element_id:-4808], primaryKey: false);
      insert('organizations', [ id: 104794, nci_institute_code: "MT011", name: "Northern Rockies Radiation Oncology Center", city: "Billings", state: "MT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4809,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT011",GROUP_DESC:"MT011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4809,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MT011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MT011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4809,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5809 ,protection_group_id: -4809, protection_element_id:-4809], primaryKey: false);
      insert('organizations', [ id: 104795, nci_institute_code: "MT012", name: "Missoula Medical Oncology", city: "Missoula", state: "MT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4810,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT012",GROUP_DESC:"MT012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4810,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MT012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MT012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4810,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5810 ,protection_group_id: -4810, protection_element_id:-4810], primaryKey: false);
      insert('organizations', [ id: 104796, nci_institute_code: "MT013", name: "Eastern Montana Cancer Center", city: "Miles City", state: "MT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4811,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT013",GROUP_DESC:"MT013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4811,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MT013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MT013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4811,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5811 ,protection_group_id: -4811, protection_element_id:-4811], primaryKey: false);
      insert('organizations', [ id: 104797, nci_institute_code: "MT014", name: "Deaconess Medical Center", city: "Billings", state: "MT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4812,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT014",GROUP_DESC:"MT014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4812,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MT014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MT014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4812,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5812 ,protection_group_id: -4812, protection_element_id:-4812], primaryKey: false);
    }

    void m32() {
        // all32 (25 terms)
      insert('organizations', [ id: 104798, nci_institute_code: "MT015", name: "North Montana Hospital", city: "Havre", state: "MT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4813,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT015",GROUP_DESC:"MT015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4813,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MT015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MT015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4813,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5813 ,protection_group_id: -4813, protection_element_id:-4813], primaryKey: false);
      insert('organizations', [ id: 104799, nci_institute_code: "MT016", name: "Montana Cancer Consortium CCOP", city: "Billings", state: "MT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4814,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT016",GROUP_DESC:"MT016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4814,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MT016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MT016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4814,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5814 ,protection_group_id: -4814, protection_element_id:-4814], primaryKey: false);
      insert('organizations', [ id: 104800, nci_institute_code: "MT017", name: "Havre Clinic", city: "Havre", state: "MT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4815,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT017",GROUP_DESC:"MT017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4815,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MT017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MT017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4815,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5815 ,protection_group_id: -4815, protection_element_id:-4815], primaryKey: false);
      insert('organizations', [ id: 104801, nci_institute_code: "MT018", name: "Missoula Oncology & Infectious", city: "Missoula", state: "MT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4816,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT018",GROUP_DESC:"MT018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4816,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MT018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MT018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4816,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5816 ,protection_group_id: -4816, protection_element_id:-4816], primaryKey: false);
      insert('organizations', [ id: 104802, nci_institute_code: "MT019", name: "Bozeman Deaconess Hospital", city: "Bozeman", state: "MT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4817,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT019",GROUP_DESC:"MT019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4817,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MT019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MT019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4817,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5817 ,protection_group_id: -4817, protection_element_id:-4817], primaryKey: false);
      insert('organizations', [ id: 104803, nci_institute_code: "MT020", name: "Benefis Healthcare- Sletten Cancer Institute", city: "Great Falls", state: "MT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4818,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT020",GROUP_DESC:"MT020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4818,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MT020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MT020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4818,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5818 ,protection_group_id: -4818, protection_element_id:-4818], primaryKey: false);
      insert('organizations', [ id: 104804, nci_institute_code: "MT021", name: "Great Falls Clinic", city: "Great Falls", state: "MT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4819,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT021",GROUP_DESC:"MT021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4819,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MT021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MT021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4819,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5819 ,protection_group_id: -4819, protection_element_id:-4819], primaryKey: false);
      insert('organizations', [ id: 104805, nci_institute_code: "MT022", name: "Bozeman Deaconess Cancer Center", city: "Bozeman", state: "MT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4820,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT022",GROUP_DESC:"MT022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4820,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MT022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MT022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4820,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5820 ,protection_group_id: -4820, protection_element_id:-4820], primaryKey: false);
      insert('organizations', [ id: 104806, nci_institute_code: "MT023", name: "Billings Oncology Associates", city: "Billings", state: "MT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4821,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT023",GROUP_DESC:"MT023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4821,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MT023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MT023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4821,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5821 ,protection_group_id: -4821, protection_element_id:-4821], primaryKey: false);
      insert('organizations', [ id: 104807, nci_institute_code: "MT024", name: "Billings Surgical Group", city: "Billings", state: "MT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4822,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT024",GROUP_DESC:"MT024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4822,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MT024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MT024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4822,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5822 ,protection_group_id: -4822, protection_element_id:-4822], primaryKey: false);
      insert('organizations', [ id: 104808, nci_institute_code: "MT025", name: "Glacier Oncology, PLLC", city: "Kalispell", state: "MT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4823,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT025",GROUP_DESC:"MT025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4823,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MT025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MT025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4823,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5823 ,protection_group_id: -4823, protection_element_id:-4823], primaryKey: false);
      insert('organizations', [ id: 104809, nci_institute_code: "MT026", name: "Big Sky Oncology", city: "Great Falls", state: "MT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4824,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT026",GROUP_DESC:"MT026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4824,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MT026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MT026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4824,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5824 ,protection_group_id: -4824, protection_element_id:-4824], primaryKey: false);
      insert('organizations', [ id: 104810, nci_institute_code: "MT027", name: "Hematology-Oncology Centers of the Northern Rockies, P.C.", city: "Billings", state: "MT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4825,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT027",GROUP_DESC:"MT027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4825,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MT027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MT027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4825,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5825 ,protection_group_id: -4825, protection_element_id:-4825], primaryKey: false);
      insert('organizations', [ id: 104811, nci_institute_code: "MT028", name: "Internal Medicine of Bozeman", city: "Bozeman", state: "MT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4826,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT028",GROUP_DESC:"MT028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4826,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MT028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MT028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4826,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5826 ,protection_group_id: -4826, protection_element_id:-4826], primaryKey: false);
      insert('organizations', [ id: 104812, nci_institute_code: "MT029", name: "Kalispell Medical Oncology", city: "Kalispell", state: "MT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4827,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT029",GROUP_DESC:"MT029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4827,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MT029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MT029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4827,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5827 ,protection_group_id: -4827, protection_element_id:-4827], primaryKey: false);
      insert('organizations', [ id: 104813, nci_institute_code: "MT030", name: "Montana Cancer Specialists", city: "Missoula", state: "MT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4828,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT030",GROUP_DESC:"MT030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4828,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MT030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MT030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4828,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5828 ,protection_group_id: -4828, protection_element_id:-4828], primaryKey: false);
      insert('organizations', [ id: 104814, nci_institute_code: "MT031", name: "Terry, John, M.D. (UIA Investigator)", city: "Billings", state: "MT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4829,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT031",GROUP_DESC:"MT031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4829,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MT031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MT031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4829,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5829 ,protection_group_id: -4829, protection_element_id:-4829], primaryKey: false);
      insert('organizations', [ id: 104815, nci_institute_code: "MT032", name: "Pfeffer, Robert MD (UIA Investigator)", city: "Great Falls", state: "MT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4830,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT032",GROUP_DESC:"MT032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4830,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MT032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MT032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4830,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5830 ,protection_group_id: -4830, protection_element_id:-4830], primaryKey: false);
      insert('organizations', [ id: 104816, nci_institute_code: "MT033", name: "Berdeaux, Donald MD (UIA Investigator)", city: "Great Falls", state: "MT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4831,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT033",GROUP_DESC:"MT033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4831,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MT033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MT033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4831,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5831 ,protection_group_id: -4831, protection_element_id:-4831], primaryKey: false);
      insert('organizations', [ id: 104817, nci_institute_code: "MT034", name: "Guardian Oncology and Center for Wellness", city: "Missoula", state: "MT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4832,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT034",GROUP_DESC:"MT034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4832,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MT034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MT034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4832,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5832 ,protection_group_id: -4832, protection_element_id:-4832], primaryKey: false);
      insert('organizations', [ id: 104818, nci_institute_code: "MT035", name: "Bozeman Urological Associates", city: "Bozeman", state: "MT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4833,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT035",GROUP_DESC:"MT035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4833,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MT035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MT035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4833,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5833 ,protection_group_id: -4833, protection_element_id:-4833], primaryKey: false);
      insert('organizations', [ id: 104819, nci_institute_code: "MT037", name: "Three Rivers Oncology", city: "Missoula", state: "MT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4834,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT037",GROUP_DESC:"MT037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4834,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MT037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MT037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4834,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5834 ,protection_group_id: -4834, protection_element_id:-4834], primaryKey: false);
      insert('organizations', [ id: 104820, nci_institute_code: "MT038", name: "Great Falls Clinic Specialty Center", city: "Great Falls", state: "MT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4835,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT038",GROUP_DESC:"MT038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4835,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MT038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MT038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4835,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5835 ,protection_group_id: -4835, protection_element_id:-4835], primaryKey: false);
      insert('organizations', [ id: 104821, nci_institute_code: "NAVMOC", name: "NCI Center for Cancer Research Medical Oncology Clinical Research Unit - Navy Oncology", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4836,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NAVMOC",GROUP_DESC:"NAVMOC group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4836,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NAVMOC",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NAVMOC",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4836,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NAVMOC", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5836 ,protection_group_id: -4836, protection_element_id:-4836], primaryKey: false);
      insert('organizations', [ id: 104822, nci_institute_code: "NBCG", name: "National Bladder Cancer Group", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4837,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NBCG",GROUP_DESC:"NBCG group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4837,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NBCG",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NBCG",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4837,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NBCG", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5837 ,protection_group_id: -4837, protection_element_id:-4837], primaryKey: false);
    }

    void m33() {
        // all33 (25 terms)
      insert('organizations', [ id: 104823, nci_institute_code: "NC001", name: "North Carolina Baptist Hospital", city: "Winston Salem", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4838,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC001",GROUP_DESC:"NC001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4838,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4838,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5838 ,protection_group_id: -4838, protection_element_id:-4838], primaryKey: false);
      insert('organizations', [ id: 104824, nci_institute_code: "NC002", name: "Wake Forest University Health Sciences", city: "Winston-Salem", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4839,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC002",GROUP_DESC:"NC002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4839,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4839,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5839 ,protection_group_id: -4839, protection_element_id:-4839], primaryKey: false);
      insert('organizations', [ id: 104825, nci_institute_code: "NC003", name: "Alamance Regional Medical Center", city: "Burlington", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4840,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC003",GROUP_DESC:"NC003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4840,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4840,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5840 ,protection_group_id: -4840, protection_element_id:-4840], primaryKey: false);
      insert('organizations', [ id: 104826, nci_institute_code: "NC004", name: "Moses H. Cone Memorial", city: "Greensboro", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4841,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC004",GROUP_DESC:"NC004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4841,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4841,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5841 ,protection_group_id: -4841, protection_element_id:-4841], primaryKey: false);
      insert('organizations', [ id: 104827, nci_institute_code: "NC006", name: "University of North Carolina Health Care", city: "Chapel Hill", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4842,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC006",GROUP_DESC:"NC006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4842,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4842,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5842 ,protection_group_id: -4842, protection_element_id:-4842], primaryKey: false);
      insert('organizations', [ id: 104828, nci_institute_code: "NC007", name: "University of North Carolina", city: "Chapel Hill", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4843,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC007",GROUP_DESC:"NC007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4843,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4843,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5843 ,protection_group_id: -4843, protection_element_id:-4843], primaryKey: false);
      insert('organizations', [ id: 104829, nci_institute_code: "NC008", name: "Wake Medical Center", city: "Winston-Salem", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4844,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC008",GROUP_DESC:"NC008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4844,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4844,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5844 ,protection_group_id: -4844, protection_element_id:-4844], primaryKey: false);
      insert('organizations', [ id: 104830, nci_institute_code: "NC009", name: "Durham Veterans Affairs Medical Center", city: "Durham", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4845,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC009",GROUP_DESC:"NC009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4845,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4845,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5845 ,protection_group_id: -4845, protection_element_id:-4845], primaryKey: false);
      insert('organizations', [ id: 104831, nci_institute_code: "NC010", name: "Duke University Medical Center", city: "Durham", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4846,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC010",GROUP_DESC:"NC010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4846,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4846,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5846 ,protection_group_id: -4846, protection_element_id:-4846], primaryKey: false);
      insert('organizations', [ id: 104832, nci_institute_code: "NC011", name: "Boice-Willis Clinic", city: "Rocky Mount", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4847,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC011",GROUP_DESC:"NC011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4847,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4847,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5847 ,protection_group_id: -4847, protection_element_id:-4847], primaryKey: false);
      insert('organizations', [ id: 104833, nci_institute_code: "NC012", name: "East Carolina University School of Medicine", city: "Greenville", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4848,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC012",GROUP_DESC:"NC012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4848,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4848,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5848 ,protection_group_id: -4848, protection_element_id:-4848], primaryKey: false);
      insert('organizations', [ id: 104834, nci_institute_code: "NC013", name: "Pitt County Memorial Hosp Inc.", city: "Greenville", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4849,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC013",GROUP_DESC:"NC013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4849,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4849,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5849 ,protection_group_id: -4849, protection_element_id:-4849], primaryKey: false);
      insert('organizations', [ id: 104835, nci_institute_code: "NC016", name: "Wilson Medical Center", city: "Wilson", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4850,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC016",GROUP_DESC:"NC016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4850,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4850,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5850 ,protection_group_id: -4850, protection_element_id:-4850], primaryKey: false);
      insert('organizations', [ id: 104836, nci_institute_code: "NC017", name: "Rowan Regional Medical Center", city: "Salisbury", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4851,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC017",GROUP_DESC:"NC017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4851,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4851,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5851 ,protection_group_id: -4851, protection_element_id:-4851], primaryKey: false);
      insert('organizations', [ id: 104837, nci_institute_code: "NC019", name: "Presbyterian Hospital", city: "Charlotte", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4852,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC019",GROUP_DESC:"NC019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4852,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4852,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5852 ,protection_group_id: -4852, protection_element_id:-4852], primaryKey: false);
      insert('organizations', [ id: 104838, nci_institute_code: "NC020", name: "Charlotte Memorial Hospital and Medical Center", city: "Charlotte", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4853,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC020",GROUP_DESC:"NC020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4853,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4853,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5853 ,protection_group_id: -4853, protection_element_id:-4853], primaryKey: false);
      insert('organizations', [ id: 104839, nci_institute_code: "NC021", name: "Highsmith-Rainey Memorial Hospital", city: "Fayetteville", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4854,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC021",GROUP_DESC:"NC021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4854,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4854,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5854 ,protection_group_id: -4854, protection_element_id:-4854], primaryKey: false);
      insert('organizations', [ id: 104840, nci_institute_code: "NC022", name: "Pinehurst Medical Clinic", city: "Pinehurst", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4855,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC022",GROUP_DESC:"NC022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4855,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4855,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5855 ,protection_group_id: -4855, protection_element_id:-4855], primaryKey: false);
      insert('organizations', [ id: 104841, nci_institute_code: "NC024", name: "Wilmington Health Associates", city: "Wilmington", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4856,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC024",GROUP_DESC:"NC024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4856,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4856,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5856 ,protection_group_id: -4856, protection_element_id:-4856], primaryKey: false);
      insert('organizations', [ id: 104842, nci_institute_code: "NC025", name: "Naval Regional Medical Center", city: "Camp Lejeune", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4857,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC025",GROUP_DESC:"NC025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4857,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4857,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5857 ,protection_group_id: -4857, protection_element_id:-4857], primaryKey: false);
      insert('organizations', [ id: 104843, nci_institute_code: "NC026", name: "Craven County Hospital", city: "New Bern", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4858,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC026",GROUP_DESC:"NC026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4858,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4858,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5858 ,protection_group_id: -4858, protection_element_id:-4858], primaryKey: false);
      insert('organizations', [ id: 104844, nci_institute_code: "NC027", name: "Catawba Memorial Hospital", city: "Hickory", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4859,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC027",GROUP_DESC:"NC027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4859,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4859,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5859 ,protection_group_id: -4859, protection_element_id:-4859], primaryKey: false);
      insert('organizations', [ id: 104845, nci_institute_code: "NC028", name: "Broughton Hospital", city: "Morganton", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4860,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC028",GROUP_DESC:"NC028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4860,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4860,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5860 ,protection_group_id: -4860, protection_element_id:-4860], primaryKey: false);
      insert('organizations', [ id: 104846, nci_institute_code: "NC029", name: "Iredell Memorial Hospital", city: "Statesville", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4861,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC029",GROUP_DESC:"NC029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4861,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4861,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5861 ,protection_group_id: -4861, protection_element_id:-4861], primaryKey: false);
      insert('organizations', [ id: 104847, nci_institute_code: "NC031", name: "Mission Hospitals, Inc", city: "Asheville", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4862,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC031",GROUP_DESC:"NC031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4862,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4862,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5862 ,protection_group_id: -4862, protection_element_id:-4862], primaryKey: false);
    }

    void m34() {
        // all34 (25 terms)
      insert('organizations', [ id: 104848, nci_institute_code: "NC032", name: "Asheville VA Medical Center", city: "Asheville", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4863,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC032",GROUP_DESC:"NC032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4863,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4863,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5863 ,protection_group_id: -4863, protection_element_id:-4863], primaryKey: false);
      insert('organizations', [ id: 104849, nci_institute_code: "NC034", name: "Womack Army Medical Center", city: "Fort Bragg", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4864,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC034",GROUP_DESC:"NC034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4864,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4864,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5864 ,protection_group_id: -4864, protection_element_id:-4864], primaryKey: false);
      insert('organizations', [ id: 104850, nci_institute_code: "NC035", name: "Valdese General Hospital", city: "Valdese", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4865,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC035",GROUP_DESC:"NC035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4865,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4865,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5865 ,protection_group_id: -4865, protection_element_id:-4865], primaryKey: false);
      insert('organizations', [ id: 104851, nci_institute_code: "NC036", name: "Wayne Memorial Hospital", city: "Goldsboro", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4866,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC036",GROUP_DESC:"NC036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4866,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4866,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5866 ,protection_group_id: -4866, protection_element_id:-4866], primaryKey: false);
      insert('organizations', [ id: 104852, nci_institute_code: "NC038", name: "Cancer Centers of North Carolina", city: "Raleigh", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4867,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC038",GROUP_DESC:"NC038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4867,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4867,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5867 ,protection_group_id: -4867, protection_element_id:-4867], primaryKey: false);
      insert('organizations', [ id: 104853, nci_institute_code: "NC039", name: "Rex Hospital", city: "Raleigh", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4868,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC039",GROUP_DESC:"NC039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4868,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4868,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5868 ,protection_group_id: -4868, protection_element_id:-4868], primaryKey: false);
      insert('organizations', [ id: 104854, nci_institute_code: "NC040", name: "Saint Joseph's Hospital", city: "Asheville", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4869,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC040",GROUP_DESC:"NC040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4869,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4869,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5869 ,protection_group_id: -4869, protection_element_id:-4869], primaryKey: false);
      insert('organizations', [ id: 104855, nci_institute_code: "NC041", name: "Mecklenburg Hematology Oncology", city: "Charlotte", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4870,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC041",GROUP_DESC:"NC041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4870,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4870,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5870 ,protection_group_id: -4870, protection_element_id:-4870], primaryKey: false);
      insert('organizations', [ id: 104856, nci_institute_code: "NC042", name: "Carolinas Medical Center", city: "Charlotte", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4871,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC042",GROUP_DESC:"NC042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4871,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4871,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5871 ,protection_group_id: -4871, protection_element_id:-4871], primaryKey: false);
      insert('organizations', [ id: 104857, nci_institute_code: "NC043", name: "New Hanover Regional Medical Center", city: "Wilmington", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4872,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC043",GROUP_DESC:"NC043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4872,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4872,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5872 ,protection_group_id: -4872, protection_element_id:-4872], primaryKey: false);
      insert('organizations', [ id: 104858, nci_institute_code: "NC044", name: "Aml Sub of North Carolina", city: "Sanford", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4873,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC044",GROUP_DESC:"NC044 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4873,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC044",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC044",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4873,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC044", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5873 ,protection_group_id: -4873, protection_element_id:-4873], primaryKey: false);
      insert('organizations', [ id: 104859, nci_institute_code: "NC045", name: "Wendover Mdcl Ctr", city: "Charlotte", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4874,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC045",GROUP_DESC:"NC045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4874,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4874,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5874 ,protection_group_id: -4874, protection_element_id:-4874], primaryKey: false);
      insert('organizations', [ id: 104860, nci_institute_code: "NC047", name: "Forsyth Memorial Hospital", city: "Winston-Salem", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4875,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC047",GROUP_DESC:"NC047 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4875,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC047",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC047",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4875,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC047", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5875 ,protection_group_id: -4875, protection_element_id:-4875], primaryKey: false);
      insert('organizations', [ id: 104861, nci_institute_code: "NC048", name: "Piedmont Hematology Oncology Associates PA", city: "Winston-Salem", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4876,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC048",GROUP_DESC:"NC048 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4876,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC048",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC048",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4876,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC048", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5876 ,protection_group_id: -4876, protection_element_id:-4876], primaryKey: false);
      insert('organizations', [ id: 104862, nci_institute_code: "NC049", name: "Southeast Cancer Control Consortium, CCOP", city: "Winston-Salem", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4877,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC049",GROUP_DESC:"NC049 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4877,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC049",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC049",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4877,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC049", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5877 ,protection_group_id: -4877, protection_element_id:-4877], primaryKey: false);
      insert('organizations', [ id: 104863, nci_institute_code: "NC050", name: "Lenoir Memorial Hospital", city: "Kinston", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4878,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC050",GROUP_DESC:"NC050 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4878,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC050",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC050",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4878,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC050", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5878 ,protection_group_id: -4878, protection_element_id:-4878], primaryKey: false);
      insert('organizations', [ id: 104864, nci_institute_code: "NC052", name: "Cato Research", city: "Durham", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4879,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC052",GROUP_DESC:"NC052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4879,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4879,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5879 ,protection_group_id: -4879, protection_element_id:-4879], primaryKey: false);
      insert('organizations', [ id: 104865, nci_institute_code: "NC053", name: "Cancer Care of Western North Carolina", city: "Asheville", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4880,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC053",GROUP_DESC:"NC053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4880,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4880,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5880 ,protection_group_id: -4880, protection_element_id:-4880], primaryKey: false);
      insert('organizations', [ id: 104866, nci_institute_code: "NC054", name: "Mercy Hospital", city: "Charlotte", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4881,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC054",GROUP_DESC:"NC054 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4881,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC054",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC054",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4881,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC054", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5881 ,protection_group_id: -4881, protection_element_id:-4881], primaryKey: false);
      insert('organizations', [ id: 104867, nci_institute_code: "NC055", name: "Harris Regional Hospital", city: "Sylva", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4882,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC055",GROUP_DESC:"NC055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4882,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4882,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5882 ,protection_group_id: -4882, protection_element_id:-4882], primaryKey: false);
      insert('organizations', [ id: 104868, nci_institute_code: "NC056", name: "Cape Fear Valley Health System", city: "Fayetteville", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4883,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC056",GROUP_DESC:"NC056 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4883,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC056",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC056",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4883,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC056", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5883 ,protection_group_id: -4883, protection_element_id:-4883], primaryKey: false);
      insert('organizations', [ id: 104869, nci_institute_code: "NC057", name: "Frye Regional Medical Center", city: "Hickory", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4884,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC057",GROUP_DESC:"NC057 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4884,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC057",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC057",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4884,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC057", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5884 ,protection_group_id: -4884, protection_element_id:-4884], primaryKey: false);
      insert('organizations', [ id: 104870, nci_institute_code: "NC058", name: "Cancer Centers of North Carolina - Asheville", city: "Asheville", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4885,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC058",GROUP_DESC:"NC058 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4885,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC058",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC058",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4885,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC058", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5885 ,protection_group_id: -4885, protection_element_id:-4885], primaryKey: false);
      insert('organizations', [ id: 104871, nci_institute_code: "NC059", name: "Mecklenburg Medical Group", city: "Charlotte", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4886,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC059",GROUP_DESC:"NC059 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4886,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC059",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC059",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4886,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC059", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5886 ,protection_group_id: -4886, protection_element_id:-4886], primaryKey: false);
      insert('organizations', [ id: 104872, nci_institute_code: "NC060", name: "Southeast Radiation/Oncology Group, P.A.", city: "Charlotte", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4887,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC060",GROUP_DESC:"NC060 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4887,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC060",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC060",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4887,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC060", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5887 ,protection_group_id: -4887, protection_element_id:-4887], primaryKey: false);
    }

    void m35() {
        // all35 (25 terms)
      insert('organizations', [ id: 104873, nci_institute_code: "NC062", name: "Nalle Clinic", city: "Charlotte", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4888,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC062",GROUP_DESC:"NC062 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4888,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC062",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC062",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4888,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC062", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5888 ,protection_group_id: -4888, protection_element_id:-4888], primaryKey: false);
      insert('organizations', [ id: 104874, nci_institute_code: "NC063", name: "Piedmont Cancer Institute", city: "Greensboro", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4889,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC063",GROUP_DESC:"NC063 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4889,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC063",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC063",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4889,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC063", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5889 ,protection_group_id: -4889, protection_element_id:-4889], primaryKey: false);
      insert('organizations', [ id: 104875, nci_institute_code: "NC064", name: "Durham Clinic., P.A.", city: "Durham", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4890,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC064",GROUP_DESC:"NC064 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4890,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC064",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC064",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4890,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC064", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5890 ,protection_group_id: -4890, protection_element_id:-4890], primaryKey: false);
      insert('organizations', [ id: 104876, nci_institute_code: "NC065", name: "Southeastern Medical Oncology Center", city: "Goldsboro", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4891,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC065",GROUP_DESC:"NC065 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4891,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC065",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC065",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4891,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC065", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5891 ,protection_group_id: -4891, protection_element_id:-4891], primaryKey: false);
      insert('organizations', [ id: 104877, nci_institute_code: "NC066", name: "Lumberton Medical Clinic", city: "Lumberton", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4892,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC066",GROUP_DESC:"NC066 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4892,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC066",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC066",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4892,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC066", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5892 ,protection_group_id: -4892, protection_element_id:-4892], primaryKey: false);
      insert('organizations', [ id: 104878, nci_institute_code: "NC067", name: "Southeast Medical Center", city: "Lumberton", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4893,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC067",GROUP_DESC:"NC067 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4893,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC067",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC067",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4893,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC067", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5893 ,protection_group_id: -4893, protection_element_id:-4893], primaryKey: false);
      insert('organizations', [ id: 104879, nci_institute_code: "NC068", name: "Piedmont Oncology Specialists", city: "Charlotte", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4894,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC068",GROUP_DESC:"NC068 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4894,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC068",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC068",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4894,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC068", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5894 ,protection_group_id: -4894, protection_element_id:-4894], primaryKey: false);
      insert('organizations', [ id: 104880, nci_institute_code: "NC069", name: "Gaston Memorial Hospital", city: "Gastonia", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4895,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC069",GROUP_DESC:"NC069 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4895,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC069",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC069",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4895,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC069", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5895 ,protection_group_id: -4895, protection_element_id:-4895], primaryKey: false);
      insert('organizations', [ id: 104881, nci_institute_code: "NC072", name: "Brookview Research, Incorporated", city: "Winston-Salem", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4896,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC072",GROUP_DESC:"NC072 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4896,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC072",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC072",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4896,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC072", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5896 ,protection_group_id: -4896, protection_element_id:-4896], primaryKey: false);
      insert('organizations', [ id: 104882, nci_institute_code: "NC073", name: "Scotland Memorial Hospital/Laurinburg Cancer Center", city: "Laurinburg", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4897,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC073",GROUP_DESC:"NC073 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4897,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC073",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC073",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4897,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC073", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5897 ,protection_group_id: -4897, protection_element_id:-4897], primaryKey: false);
      insert('organizations', [ id: 104883, nci_institute_code: "NC074", name: "Lexington Memorial Hospital", city: "Lexington", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4898,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC074",GROUP_DESC:"NC074 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4898,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC074",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC074",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4898,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC074", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5898 ,protection_group_id: -4898, protection_element_id:-4898], primaryKey: false);
      insert('organizations', [ id: 104884, nci_institute_code: "NC075", name: "Sanford Surgical Clinic", city: "Sanford", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4899,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC075",GROUP_DESC:"NC075 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4899,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC075",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC075",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4899,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC075", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5899 ,protection_group_id: -4899, protection_element_id:-4899], primaryKey: false);
      insert('organizations', [ id: 104885, nci_institute_code: "NC076", name: "Medical Center, Winston", city: "Winston-Salem", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4900,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC076",GROUP_DESC:"NC076 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4900,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC076",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC076",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4900,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC076", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5900 ,protection_group_id: -4900, protection_element_id:-4900], primaryKey: false);
      insert('organizations', [ id: 104886, nci_institute_code: "NC077", name: "Hanover Medical Specialists, P.A.", city: "Wilmington", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4901,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC077",GROUP_DESC:"NC077 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4901,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC077",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC077",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4901,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC077", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5901 ,protection_group_id: -4901, protection_element_id:-4901], primaryKey: false);
      insert('organizations', [ id: 104887, nci_institute_code: "NC080", name: "Southern Pines Women's Health Center, Professional Corporation", city: "Southern Pines", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4902,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC080",GROUP_DESC:"NC080 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4902,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC080",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC080",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4902,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC080", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5902 ,protection_group_id: -4902, protection_element_id:-4902], primaryKey: false);
      insert('organizations', [ id: 104888, nci_institute_code: "NC081", name: "FirstHealth of the Carolinas, Moore Regional Hosiptal", city: "Pinehurst", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4903,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC081",GROUP_DESC:"NC081 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4903,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC081",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC081",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4903,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC081", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5903 ,protection_group_id: -4903, protection_element_id:-4903], primaryKey: false);
      insert('organizations', [ id: 104889, nci_institute_code: "NC083", name: "Carolinas Medical Center - Northeast", city: "Concord", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4904,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC083",GROUP_DESC:"NC083 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4904,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC083",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC083",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4904,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC083", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5904 ,protection_group_id: -4904, protection_element_id:-4904], primaryKey: false);
      insert('organizations', [ id: 104890, nci_institute_code: "NC084", name: "Niehs/Nih Lab of Signal Transd", city: "Research Triangle Pk", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4905,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC084",GROUP_DESC:"NC084 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4905,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC084",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC084",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4905,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC084", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5905 ,protection_group_id: -4905, protection_element_id:-4905], primaryKey: false);
      insert('organizations', [ id: 104891, nci_institute_code: "NC085", name: "Hematology/Oncology", city: "Charlotte", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4906,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC085",GROUP_DESC:"NC085 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4906,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC085",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC085",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4906,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC085", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5906 ,protection_group_id: -4906, protection_element_id:-4906], primaryKey: false);
      insert('organizations', [ id: 104892, nci_institute_code: "NC086", name: "Winston-Salem Health Care", city: "Winston-Salem", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4907,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC086",GROUP_DESC:"NC086 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4907,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC086",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC086",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4907,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC086", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5907 ,protection_group_id: -4907, protection_element_id:-4907], primaryKey: false);
      insert('organizations', [ id: 104893, nci_institute_code: "NC087", name: "Wayne Radiation Oncology", city: "Goldsboro", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4908,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC087",GROUP_DESC:"NC087 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4908,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC087",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC087",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4908,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC087", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5908 ,protection_group_id: -4908, protection_element_id:-4908], primaryKey: false);
      insert('organizations', [ id: 104894, nci_institute_code: "NC088", name: "Veterans Administration Medical Center.", city: "Salisbury", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4909,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC088",GROUP_DESC:"NC088 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4909,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC088",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC088",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4909,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC088", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5909 ,protection_group_id: -4909, protection_element_id:-4909], primaryKey: false);
      insert('organizations', [ id: 104895, nci_institute_code: "NC089", name: "Johnston Memorial Hospital", city: "Smithfield", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4910,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC089",GROUP_DESC:"NC089 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4910,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC089",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC089",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4910,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC089", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5910 ,protection_group_id: -4910, protection_element_id:-4910], primaryKey: false);
      insert('organizations', [ id: 104896, nci_institute_code: "NC090", name: "Margaret R. Pardee Memorial Hospital", city: "Hendersonville", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4911,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC090",GROUP_DESC:"NC090 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4911,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC090",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC090",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4911,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC090", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5911 ,protection_group_id: -4911, protection_element_id:-4911], primaryKey: false);
      insert('organizations', [ id: 104897, nci_institute_code: "NC092", name: "Emerywood Internal Medicine", city: "High Point", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4912,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC092",GROUP_DESC:"NC092 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4912,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC092",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC092",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4912,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC092", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5912 ,protection_group_id: -4912, protection_element_id:-4912], primaryKey: false);
    }

    void m36() {
        // all36 (25 terms)
      insert('organizations', [ id: 104898, nci_institute_code: "NC093", name: "Central Carolina Medicine and Oncology Assoc", city: "Greensboro", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4913,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC093",GROUP_DESC:"NC093 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4913,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC093",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC093",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4913,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC093", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5913 ,protection_group_id: -4913, protection_element_id:-4913], primaryKey: false);
      insert('organizations', [ id: 104899, nci_institute_code: "NC094", name: "Blood and Cancer Clinic P.A.", city: "Fayetteville", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4914,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC094",GROUP_DESC:"NC094 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4914,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC094",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC094",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4914,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC094", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5914 ,protection_group_id: -4914, protection_element_id:-4914], primaryKey: false);
      insert('organizations', [ id: 104900, nci_institute_code: "NC095", name: "Rutherford Hospital", city: "Rutherfordton", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4915,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC095",GROUP_DESC:"NC095 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4915,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC095",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC095",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4915,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC095", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5915 ,protection_group_id: -4915, protection_element_id:-4915], primaryKey: false);
      insert('organizations', [ id: 104901, nci_institute_code: "NC096", name: "Hope, A Women's Cancer Center", city: "Asheville", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4916,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC096",GROUP_DESC:"NC096 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4916,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC096",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC096",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4916,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC096", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5916 ,protection_group_id: -4916, protection_element_id:-4916], primaryKey: false);
      insert('organizations', [ id: 104902, nci_institute_code: "NC097", name: "Carolina Radiation and Cancer Treatment Center, Incorporated", city: "Greenville", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4917,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC097",GROUP_DESC:"NC097 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4917,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC097",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC097",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4917,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC097", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5917 ,protection_group_id: -4917, protection_element_id:-4917], primaryKey: false);
      insert('organizations', [ id: 104903, nci_institute_code: "NC099", name: "Waverly Hematology Oncology", city: "Cary", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4918,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC099",GROUP_DESC:"NC099 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4918,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC099",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC099",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4918,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC099", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5918 ,protection_group_id: -4918, protection_element_id:-4918], primaryKey: false);
      insert('organizations', [ id: 104904, nci_institute_code: "NC100", name: "Research Triangle Institute", city: "Research Triangle Park", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4919,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC100",GROUP_DESC:"NC100 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4919,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC100",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC100",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4919,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC100", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5919 ,protection_group_id: -4919, protection_element_id:-4919], primaryKey: false);
      insert('organizations', [ id: 104905, nci_institute_code: "NC101", name: "Cornerstone Surgery", city: "High Point", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4920,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC101",GROUP_DESC:"NC101 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4920,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC101",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC101",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4920,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC101", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5920 ,protection_group_id: -4920, protection_element_id:-4920], primaryKey: false);
      insert('organizations', [ id: 104906, nci_institute_code: "NC102", name: "Annie Penn Memorial Hospital", city: "Reidsville", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4921,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC102",GROUP_DESC:"NC102 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4921,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC102",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC102",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4921,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC102", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5921 ,protection_group_id: -4921, protection_element_id:-4921], primaryKey: false);
      insert('organizations', [ id: 104907, nci_institute_code: "NC103", name: "Kinston Medical Specialists PA - Main Office", city: "Kinston", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4922,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC103",GROUP_DESC:"NC103 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4922,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC103",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC103",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4922,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC103", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5922 ,protection_group_id: -4922, protection_element_id:-4922], primaryKey: false);
      insert('organizations', [ id: 104908, nci_institute_code: "NC104", name: "Hendersonville Hematology & Oncology", city: "Hendersonville", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4923,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC104",GROUP_DESC:"NC104 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4923,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC104",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC104",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4923,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC104", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5923 ,protection_group_id: -4923, protection_element_id:-4923], primaryKey: false);
      insert('organizations', [ id: 104909, nci_institute_code: "NC106", name: "Eastern North Carolina Medical Group", city: "Rocky Mount", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4924,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC106",GROUP_DESC:"NC106 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4924,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC106",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC106",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4924,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC106", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5924 ,protection_group_id: -4924, protection_element_id:-4924], primaryKey: false);
      insert('organizations', [ id: 104910, nci_institute_code: "NC107", name: "Blume Children's Cancer Clinic", city: "Charlotte", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4925,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC107",GROUP_DESC:"NC107 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4925,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC107",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC107",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4925,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC107", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5925 ,protection_group_id: -4925, protection_element_id:-4925], primaryKey: false);
      insert('organizations', [ id: 104911, nci_institute_code: "NC108", name: "Moorehead Memorial Hospital", city: "Eden", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4926,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC108",GROUP_DESC:"NC108 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4926,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC108",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC108",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4926,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC108", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5926 ,protection_group_id: -4926, protection_element_id:-4926], primaryKey: false);
      insert('organizations', [ id: 104912, nci_institute_code: "NC109", name: "Randolph Hospital", city: "Asheboro", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4927,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC109",GROUP_DESC:"NC109 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4927,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC109",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC109",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4927,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC109", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5927 ,protection_group_id: -4927, protection_element_id:-4927], primaryKey: false);
      insert('organizations', [ id: 104913, nci_institute_code: "NC110", name: "Person Memorial Hospital", city: "Roxboro", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4928,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC110",GROUP_DESC:"NC110 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4928,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC110",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC110",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4928,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC110", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5928 ,protection_group_id: -4928, protection_element_id:-4928], primaryKey: false);
      insert('organizations', [ id: 104914, nci_institute_code: "NC111", name: "Maria Parham Hospital", city: "Henderson", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4929,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC111",GROUP_DESC:"NC111 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4929,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC111",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC111",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4929,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC111", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5929 ,protection_group_id: -4929, protection_element_id:-4929], primaryKey: false);
      insert('organizations', [ id: 104915, nci_institute_code: "NC112", name: "Lake Norman Regional Medical Center", city: "Mooresville", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4930,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC112",GROUP_DESC:"NC112 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4930,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC112",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC112",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4930,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC112", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5930 ,protection_group_id: -4930, protection_element_id:-4930], primaryKey: false);
      insert('organizations', [ id: 104916, nci_institute_code: "NC113", name: "Oncology Specialists of Charlotte", city: "Charlotte", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4931,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC113",GROUP_DESC:"NC113 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4931,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC113",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC113",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4931,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC113", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5931 ,protection_group_id: -4931, protection_element_id:-4931], primaryKey: false);
      insert('organizations', [ id: 104917, nci_institute_code: "NC114", name: "Carolina Hematology and Oncology", city: "Hendersonville", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4932,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC114",GROUP_DESC:"NC114 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4932,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC114",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC114",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4932,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC114", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5932 ,protection_group_id: -4932, protection_element_id:-4932], primaryKey: false);
      insert('organizations', [ id: 104918, nci_institute_code: "NC115", name: "Cancer Centers of North Carolina", city: "Cary", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4933,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC115",GROUP_DESC:"NC115 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4933,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC115",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC115",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4933,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC115", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5933 ,protection_group_id: -4933, protection_element_id:-4933], primaryKey: false);
      insert('organizations', [ id: 104919, nci_institute_code: "NC116", name: "Carolinas Healthcare System - University", city: "Charlotte", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4934,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC116",GROUP_DESC:"NC116 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4934,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC116",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC116",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4934,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC116", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5934 ,protection_group_id: -4934, protection_element_id:-4934], primaryKey: false);
      insert('organizations', [ id: 104920, nci_institute_code: "NC117", name: "Durham Regional Hospital", city: "Durham", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4935,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC117",GROUP_DESC:"NC117 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4935,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC117",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC117",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4935,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC117", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5935 ,protection_group_id: -4935, protection_element_id:-4935], primaryKey: false);
      insert('organizations', [ id: 104921, nci_institute_code: "NC118", name: "High Point Regional Hospital", city: "High Point", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4936,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC118",GROUP_DESC:"NC118 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4936,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC118",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC118",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4936,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC118", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5936 ,protection_group_id: -4936, protection_element_id:-4936], primaryKey: false);
      insert('organizations', [ id: 104922, nci_institute_code: "NC119", name: "Carolina NeuroSurgery & Spine Associates", city: "Charlotte", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4937,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC119",GROUP_DESC:"NC119 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4937,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC119",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC119",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4937,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC119", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5937 ,protection_group_id: -4937, protection_element_id:-4937], primaryKey: false);
    }

    void m37() {
        // all37 (25 terms)
      insert('organizations', [ id: 104923, nci_institute_code: "NC120", name: "Granville Medical Center", city: "Oxford", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4938,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC120",GROUP_DESC:"NC120 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4938,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC120",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC120",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4938,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC120", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5938 ,protection_group_id: -4938, protection_element_id:-4938], primaryKey: false);
      insert('organizations', [ id: 104924, nci_institute_code: "NC121", name: "Mountain Radiation Oncology", city: "Asheville", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4939,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC121",GROUP_DESC:"NC121 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4939,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC121",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC121",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4939,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC121", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5939 ,protection_group_id: -4939, protection_element_id:-4939], primaryKey: false);
      insert('organizations', [ id: 104925, nci_institute_code: "NC122", name: "Greensboro Medical Associates", city: "Greensboro", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4940,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC122",GROUP_DESC:"NC122 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4940,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC122",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC122",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4940,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC122", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5940 ,protection_group_id: -4940, protection_element_id:-4940], primaryKey: false);
      insert('organizations', [ id: 104926, nci_institute_code: "NC123", name: "Cardiovascular/Thoracic Surgeons of Greensboro", city: "Greensboro", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4941,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC123",GROUP_DESC:"NC123 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4941,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC123",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC123",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4941,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC123", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5941 ,protection_group_id: -4941, protection_element_id:-4941], primaryKey: false);
      insert('organizations', [ id: 104927, nci_institute_code: "NC124", name: "Wake Radiology Oncology", city: "Cary", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4942,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC124",GROUP_DESC:"NC124 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4942,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC124",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC124",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4942,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC124", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5942 ,protection_group_id: -4942, protection_element_id:-4942], primaryKey: false);
      insert('organizations', [ id: 104928, nci_institute_code: "NC125", name: "Virginia Oncology Associates", city: "Elizabeth", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4943,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC125",GROUP_DESC:"NC125 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4943,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC125",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC125",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4943,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC125", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5943 ,protection_group_id: -4943, protection_element_id:-4943], primaryKey: false);
      insert('organizations', [ id: 104929, nci_institute_code: "NC126", name: "Haywood Regional Medical Center", city: "Clyde", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4944,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC126",GROUP_DESC:"NC126 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4944,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC126",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC126",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4944,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC126", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5944 ,protection_group_id: -4944, protection_element_id:-4944], primaryKey: false);
      insert('organizations', [ id: 104930, nci_institute_code: "NC127", name: "Smith-McMichael Cancer Center", city: "Eden", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4945,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC127",GROUP_DESC:"NC127 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4945,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC127",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC127",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4945,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC127", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5945 ,protection_group_id: -4945, protection_element_id:-4945], primaryKey: false);
      insert('organizations', [ id: 104931, nci_institute_code: "NC128", name: "Carolina Surgical Clinic", city: "Charlotte", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4946,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC128",GROUP_DESC:"NC128 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4946,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC128",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC128",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4946,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC128", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5946 ,protection_group_id: -4946, protection_element_id:-4946], primaryKey: false);
      insert('organizations', [ id: 104932, nci_institute_code: "NC129", name: "Urology Specialists of the Carolinas PLLC", city: "Charlotte", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4947,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC129",GROUP_DESC:"NC129 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4947,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC129",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC129",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4947,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC129", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5947 ,protection_group_id: -4947, protection_element_id:-4947], primaryKey: false);
      insert('organizations', [ id: 104933, nci_institute_code: "NC130", name: "Carolinas Hematology-Oncology Associates", city: "Charlotte", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4948,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC130",GROUP_DESC:"NC130 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4948,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC130",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC130",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4948,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC130", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5948 ,protection_group_id: -4948, protection_element_id:-4948], primaryKey: false);
      insert('organizations', [ id: 104934, nci_institute_code: "NC131", name: "Spartanburg Regional Medical Center", city: "Forest City", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4949,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC131",GROUP_DESC:"NC131 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4949,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC131",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC131",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4949,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC131", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5949 ,protection_group_id: -4949, protection_element_id:-4949], primaryKey: false);
      insert('organizations', [ id: 104935, nci_institute_code: "NC132", name: "Union Regional Medical Center", city: "Monroe", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4950,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC132",GROUP_DESC:"NC132 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4950,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC132",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC132",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4950,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC132", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5950 ,protection_group_id: -4950, protection_element_id:-4950], primaryKey: false);
      insert('organizations', [ id: 104936, nci_institute_code: "NC133", name: "Carolina Cancer Specialists", city: "Clyde", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4951,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC133",GROUP_DESC:"NC133 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4951,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC133",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC133",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4951,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC133", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5951 ,protection_group_id: -4951, protection_element_id:-4951], primaryKey: false);
      insert('organizations', [ id: 104937, nci_institute_code: "NC134", name: "Matthews Hematology and Oncology", city: "Matthews", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4952,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC134",GROUP_DESC:"NC134 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4952,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC134",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC134",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4952,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC134", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5952 ,protection_group_id: -4952, protection_element_id:-4952], primaryKey: false);
      insert('organizations', [ id: 104938, nci_institute_code: "NC135", name: "Carolina Oncology Associates, P.A.", city: "Salisbury", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4953,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC135",GROUP_DESC:"NC135 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4953,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC135",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC135",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4953,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC135", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5953 ,protection_group_id: -4953, protection_element_id:-4953], primaryKey: false);
      insert('organizations', [ id: 104939, nci_institute_code: "NC136", name: "Kayden Radiation Oncology", city: "Hendersonville", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4954,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC136",GROUP_DESC:"NC136 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4954,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC136",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC136",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4954,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC136", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5954 ,protection_group_id: -4954, protection_element_id:-4954], primaryKey: false);
      insert('organizations', [ id: 104940, nci_institute_code: "NC137", name: "Southeastern Medical Oncology Center", city: "Wilson", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4955,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC137",GROUP_DESC:"NC137 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4955,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC137",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC137",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4955,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC137", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5955 ,protection_group_id: -4955, protection_element_id:-4955], primaryKey: false);
      insert('organizations', [ id: 104941, nci_institute_code: "NC138", name: "Duke Hematology/Oncology at Raleigh Community Hospital", city: "Raleigh", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4956,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC138",GROUP_DESC:"NC138 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4956,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC138",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC138",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4956,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC138", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5956 ,protection_group_id: -4956, protection_element_id:-4956], primaryKey: false);
      insert('organizations', [ id: 104942, nci_institute_code: "NC139", name: "Thoracic & Vascular Associates of Kinston, P.A.", city: "Kinston", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4957,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC139",GROUP_DESC:"NC139 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4957,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC139",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC139",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4957,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC139", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5957 ,protection_group_id: -4957, protection_element_id:-4957], primaryKey: false);
      insert('organizations', [ id: 104943, nci_institute_code: "NC140", name: "Carolina Surgical Care", city: "Elizabeth City", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4958,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC140",GROUP_DESC:"NC140 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4958,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC140",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC140",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4958,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC140", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5958 ,protection_group_id: -4958, protection_element_id:-4958], primaryKey: false);
      insert('organizations', [ id: 104944, nci_institute_code: "NC141", name: "Northeastern Surgical Associates", city: "Ahoskie", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4959,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC141",GROUP_DESC:"NC141 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4959,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC141",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC141",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4959,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC141", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5959 ,protection_group_id: -4959, protection_element_id:-4959], primaryKey: false);
      insert('organizations', [ id: 104945, nci_institute_code: "NC142", name: "Charlotte Surgical Group, P.A.-Mid Town Medical Plaza", city: "Charlotte", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4960,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC142",GROUP_DESC:"NC142 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4960,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC142",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC142",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4960,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC142", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5960 ,protection_group_id: -4960, protection_element_id:-4960], primaryKey: false);
      insert('organizations', [ id: 104946, nci_institute_code: "NC143", name: "Central Carolina Surgery", city: "Greensboro", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4961,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC143",GROUP_DESC:"NC143 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4961,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC143",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC143",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4961,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC143", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5961 ,protection_group_id: -4961, protection_element_id:-4961], primaryKey: false);
      insert('organizations', [ id: 104947, nci_institute_code: "NC144", name: "Piedmont HealthCare", city: "Statesville", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4962,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC144",GROUP_DESC:"NC144 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4962,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC144",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC144",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4962,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC144", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5962 ,protection_group_id: -4962, protection_element_id:-4962], primaryKey: false);
    }

    void m38() {
        // all38 (25 terms)
      insert('organizations', [ id: 104948, nci_institute_code: "NC145", name: "High Point Regional Cancer Center", city: "High Point", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4963,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC145",GROUP_DESC:"NC145 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4963,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC145",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC145",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4963,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC145", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5963 ,protection_group_id: -4963, protection_element_id:-4963], primaryKey: false);
      insert('organizations', [ id: 104949, nci_institute_code: "NC146", name: "Comprehensive Cancer Care", city: "Washington", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4964,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC146",GROUP_DESC:"NC146 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4964,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC146",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC146",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4964,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC146", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5964 ,protection_group_id: -4964, protection_element_id:-4964], primaryKey: false);
      insert('organizations', [ id: 104950, nci_institute_code: "NC147", name: "Beaufort County Hospital", city: "Washington", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4965,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC147",GROUP_DESC:"NC147 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4965,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC147",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC147",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4965,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC147", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5965 ,protection_group_id: -4965, protection_element_id:-4965], primaryKey: false);
      insert('organizations', [ id: 104951, nci_institute_code: "NC148", name: "Carolina Medical Associates", city: "Charlotte", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4966,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC148",GROUP_DESC:"NC148 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4966,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC148",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC148",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4966,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC148", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5966 ,protection_group_id: -4966, protection_element_id:-4966], primaryKey: false);
      insert('organizations', [ id: 104952, nci_institute_code: "NC149", name: "Leo Jenkins Cancer Center", city: "Greenville", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4967,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC149",GROUP_DESC:"NC149 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4967,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC149",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC149",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4967,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC149", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5967 ,protection_group_id: -4967, protection_element_id:-4967], primaryKey: false);
      insert('organizations', [ id: 104953, nci_institute_code: "NC150", name: "Regional Hematology-Oncology Associates", city: "Durham", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4968,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC150",GROUP_DESC:"NC150 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4968,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC150",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC150",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4968,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC150", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5968 ,protection_group_id: -4968, protection_element_id:-4968], primaryKey: false);
      insert('organizations', [ id: 104954, nci_institute_code: "NC151", name: "Hugh Chatham Memorial Hospital", city: "Elkin", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4969,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC151",GROUP_DESC:"NC151 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4969,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC151",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC151",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4969,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC151", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5969 ,protection_group_id: -4969, protection_element_id:-4969], primaryKey: false);
      insert('organizations', [ id: 104955, nci_institute_code: "NC152", name: "Brody School of Medicine at East Carolina University", city: "Greenville", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4970,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC152",GROUP_DESC:"NC152 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4970,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC152",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC152",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4970,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC152", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5970 ,protection_group_id: -4970, protection_element_id:-4970], primaryKey: false);
      insert('organizations', [ id: 104956, nci_institute_code: "NC153", name: "Kinston Medical Specialists PA", city: "Kinston", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4971,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC153",GROUP_DESC:"NC153 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4971,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC153",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC153",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4971,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC153", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5971 ,protection_group_id: -4971, protection_element_id:-4971], primaryKey: false);
      insert('organizations', [ id: 104957, nci_institute_code: "NC154", name: "Carolinas Hematology and Oncology Associates", city: "Charlotte", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4972,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC154",GROUP_DESC:"NC154 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4972,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC154",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC154",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4972,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC154", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5972 ,protection_group_id: -4972, protection_element_id:-4972], primaryKey: false);
      insert('organizations', [ id: 104958, nci_institute_code: "NC155", name: "Outer Banks Surgery", city: "Nags Head", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4973,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC155",GROUP_DESC:"NC155 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4973,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC155",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC155",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4973,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC155", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5973 ,protection_group_id: -4973, protection_element_id:-4973], primaryKey: false);
      insert('organizations', [ id: 104959, nci_institute_code: "NC156", name: "Physicians East, PA", city: "Greenville", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4974,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC156",GROUP_DESC:"NC156 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4974,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC156",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC156",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4974,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC156", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5974 ,protection_group_id: -4974, protection_element_id:-4974], primaryKey: false);
      insert('organizations', [ id: 104960, nci_institute_code: "NC157", name: "Emerywood Hematology and Oncology", city: "High Point", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4975,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC157",GROUP_DESC:"NC157 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4975,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC157",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC157",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4975,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC157", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5975 ,protection_group_id: -4975, protection_element_id:-4975], primaryKey: false);
      insert('organizations', [ id: 104961, nci_institute_code: "NC158", name: "Carolinas Cancer Care-Charlotte", city: "Charlotte", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4976,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC158",GROUP_DESC:"NC158 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4976,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC158",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC158",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4976,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC158", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5976 ,protection_group_id: -4976, protection_element_id:-4976], primaryKey: false);
      insert('organizations', [ id: 104962, nci_institute_code: "NC159", name: "Gynecologic Oncology Network", city: "Greenville", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4977,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC159",GROUP_DESC:"NC159 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4977,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC159",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC159",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4977,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC159", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5977 ,protection_group_id: -4977, protection_element_id:-4977], primaryKey: false);
      insert('organizations', [ id: 104963, nci_institute_code: "NC160", name: "Matthews Radiation Oncology Center", city: "Matthews", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4978,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC160",GROUP_DESC:"NC160 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4978,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC160",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC160",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4978,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC160", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5978 ,protection_group_id: -4978, protection_element_id:-4978], primaryKey: false);
      insert('organizations', [ id: 104964, nci_institute_code: "NC161", name: "Lake Norman Hematology and Oncology", city: "Mooresville", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4979,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC161",GROUP_DESC:"NC161 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4979,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC161",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC161",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4979,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC161", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5979 ,protection_group_id: -4979, protection_element_id:-4979], primaryKey: false);
      insert('organizations', [ id: 104965, nci_institute_code: "NC162", name: "Mission Children's Clinic", city: "Asheville", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4980,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC162",GROUP_DESC:"NC162 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4980,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC162",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC162",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4980,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC162", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5980 ,protection_group_id: -4980, protection_element_id:-4980], primaryKey: false);
      insert('organizations', [ id: 104966, nci_institute_code: "NC163", name: "National Institute of Environmental Health", city: "Research Triangle Park", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4981,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC163",GROUP_DESC:"NC163 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4981,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC163",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC163",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4981,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC163", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5981 ,protection_group_id: -4981, protection_element_id:-4981], primaryKey: false);
      insert('organizations', [ id: 104967, nci_institute_code: "NC164", name: "New Hanover Radiation Oncology Center", city: "Wilmington", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4982,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC164",GROUP_DESC:"NC164 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4982,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC164",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC164",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4982,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC164", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5982 ,protection_group_id: -4982, protection_element_id:-4982], primaryKey: false);
      insert('organizations', [ id: 104968, nci_institute_code: "NC165", name: "Northeast Oncology Associates", city: "Concord", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4983,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC165",GROUP_DESC:"NC165 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4983,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC165",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC165",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4983,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC165", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5983 ,protection_group_id: -4983, protection_element_id:-4983], primaryKey: false);
      insert('organizations', [ id: 104969, nci_institute_code: "NC166", name: "Duke University Health Center", city: "Durham", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4984,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC166",GROUP_DESC:"NC166 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4984,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC166",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC166",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4984,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC166", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5984 ,protection_group_id: -4984, protection_element_id:-4984], primaryKey: false);
      insert('organizations', [ id: 104970, nci_institute_code: "NC167", name: "Cancer Centers of North Carolina", city: "Raleigh", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4985,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC167",GROUP_DESC:"NC167 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4985,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC167",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC167",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4985,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC167", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5985 ,protection_group_id: -4985, protection_element_id:-4985], primaryKey: false);
      insert('organizations', [ id: 104971, nci_institute_code: "NC169", name: "Wilmed Radiation Oncology Services", city: "Wilson", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4986,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC169",GROUP_DESC:"NC169 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4986,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC169",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC169",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4986,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC169", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5986 ,protection_group_id: -4986, protection_element_id:-4986], primaryKey: false);
      insert('organizations', [ id: 104972, nci_institute_code: "NC170", name: "Private Diagnostic Clinic", city: "Durham", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4987,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC170",GROUP_DESC:"NC170 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4987,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC170",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC170",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4987,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC170", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5987 ,protection_group_id: -4987, protection_element_id:-4987], primaryKey: false);
    }

    void m39() {
        // all39 (25 terms)
      insert('organizations', [ id: 104973, nci_institute_code: "NC171", name: "Pinehurst Surgical", city: "Pinehurst", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4988,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC171",GROUP_DESC:"NC171 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4988,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC171",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC171",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4988,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC171", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5988 ,protection_group_id: -4988, protection_element_id:-4988], primaryKey: false);
      insert('organizations', [ id: 104974, nci_institute_code: "NC172", name: "Smith's Drugs Vital Care", city: "Forest City", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4989,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC172",GROUP_DESC:"NC172 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4989,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC172",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC172",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4989,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC172", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5989 ,protection_group_id: -4989, protection_element_id:-4989], primaryKey: false);
      insert('organizations', [ id: 104975, nci_institute_code: "NC173", name: "North Carolina Radiation Oncology Affiliates", city: "Burlington", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4990,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC173",GROUP_DESC:"NC173 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4990,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC173",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC173",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4990,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC173", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5990 ,protection_group_id: -4990, protection_element_id:-4990], primaryKey: false);
      insert('organizations', [ id: 104976, nci_institute_code: "NC175", name: "Alamance Oncology/Hematology Associates", city: "Burlington", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4991,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC175",GROUP_DESC:"NC175 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4991,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC175",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC175",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4991,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC175", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5991 ,protection_group_id: -4991, protection_element_id:-4991], primaryKey: false);
      insert('organizations', [ id: 104977, nci_institute_code: "NC176", name: "Carolinas Hematology/Oncology Associates, Ballantyne", city: "Charlotte", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4992,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC176",GROUP_DESC:"NC176 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4992,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC176",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC176",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4992,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC176", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5992 ,protection_group_id: -4992, protection_element_id:-4992], primaryKey: false);
      insert('organizations', [ id: 104978, nci_institute_code: "NC177", name: "Cleveland Regional Medical Center", city: "Shelby", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4993,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC177",GROUP_DESC:"NC177 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4993,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC177",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC177",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4993,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC177", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5993 ,protection_group_id: -4993, protection_element_id:-4993], primaryKey: false);
      insert('organizations', [ id: 104979, nci_institute_code: "NC178", name: "Rex Cancer Center", city: "Raleigh", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4994,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC178",GROUP_DESC:"NC178 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4994,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC178",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC178",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4994,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC178", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5994 ,protection_group_id: -4994, protection_element_id:-4994], primaryKey: false);
      insert('organizations', [ id: 104980, nci_institute_code: "NC179", name: "Mecklenburg Medical Group Morehead", city: "Charlotte", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4995,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC179",GROUP_DESC:"NC179 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4995,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC179",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC179",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4995,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC179", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5995 ,protection_group_id: -4995, protection_element_id:-4995], primaryKey: false);
      insert('organizations', [ id: 104981, nci_institute_code: "NC180", name: "Nash General Hospital", city: "Rocky Mount", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4996,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC180",GROUP_DESC:"NC180 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4996,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC180",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC180",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4996,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC180", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5996 ,protection_group_id: -4996, protection_element_id:-4996], primaryKey: false);
      insert('organizations', [ id: 104982, nci_institute_code: "NC181", name: "Zimmer Cancer Center", city: "Wilmington", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4997,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC181",GROUP_DESC:"NC181 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4997,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC181",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC181",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4997,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC181", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5997 ,protection_group_id: -4997, protection_element_id:-4997], primaryKey: false);
      insert('organizations', [ id: 104983, nci_institute_code: "NC182", name: "Piedmont Research Center", city: "Morrisville", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4998,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC182",GROUP_DESC:"NC182 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4998,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC182",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC182",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4998,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC182", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5998 ,protection_group_id: -4998, protection_element_id:-4998], primaryKey: false);
      insert('organizations', [ id: 104984, nci_institute_code: "NC183", name: "Carolina BioOncology Institute", city: "Huntersville", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -4999,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC183",GROUP_DESC:"NC183 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -4999,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC183",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC183",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -4999,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC183", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:5999 ,protection_group_id: -4999, protection_element_id:-4999], primaryKey: false);
      insert('organizations', [ id: 104985, nci_institute_code: "NC185", name: "21st Century Oncology", city: "Asheville", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5000,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC185",GROUP_DESC:"NC185 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5000,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC185",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC185",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5000,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC185", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6000 ,protection_group_id: -5000, protection_element_id:-5000], primaryKey: false);
      insert('organizations', [ id: 104986, nci_institute_code: "NC186", name: "Blumenthal Cancer Center", city: "Charlotte", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5001,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC186",GROUP_DESC:"NC186 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5001,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC186",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC186",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5001,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC186", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6001 ,protection_group_id: -5001, protection_element_id:-5001], primaryKey: false);
      insert('organizations', [ id: 104987, nci_institute_code: "NC187", name: "Sampson Radiation Oncology", city: "Clinton", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5002,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC187",GROUP_DESC:"NC187 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5002,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC187",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC187",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5002,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC187", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6002 ,protection_group_id: -5002, protection_element_id:-5002], primaryKey: false);
      insert('organizations', [ id: 104988, nci_institute_code: "NC188", name: "Duke University Health Center - Pratt Street", city: "Durham", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5003,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC188",GROUP_DESC:"NC188 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5003,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC188",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC188",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5003,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC188", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6003 ,protection_group_id: -5003, protection_element_id:-5003], primaryKey: false);
      insert('organizations', [ id: 104989, nci_institute_code: "NC189", name: "Gaston Hematology & Oncology Associates", city: "Gastonia", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5004,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC189",GROUP_DESC:"NC189 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5004,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC189",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC189",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5004,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC189", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6004 ,protection_group_id: -5004, protection_element_id:-5004], primaryKey: false);
      insert('organizations', [ id: 104990, nci_institute_code: "NC190", name: "Marion L Shepard Cancer Center", city: "Washington", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5005,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC190",GROUP_DESC:"NC190 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5005,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC190",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC190",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5005,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC190", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6005 ,protection_group_id: -5005, protection_element_id:-5005], primaryKey: false);
      insert('organizations', [ id: 104991, nci_institute_code: "NC191", name: "Park Ridge Hospital Breast Health Center", city: "Hendersonville", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5006,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC191",GROUP_DESC:"NC191 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5006,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC191",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC191",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5006,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC191", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6006 ,protection_group_id: -5006, protection_element_id:-5006], primaryKey: false);
      insert('organizations', [ id: 104992, nci_institute_code: "NC192", name: "Regional Cancer Center at Stoney Creek", city: "Whitsett", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5007,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC192",GROUP_DESC:"NC192 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5007,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC192",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC192",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5007,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC192", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6007 ,protection_group_id: -5007, protection_element_id:-5007], primaryKey: false);
      insert('organizations', [ id: 104993, nci_institute_code: "NC193", name: "Mecklenburg Medical Group - Pineville", city: "Charlotte", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5008,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC193",GROUP_DESC:"NC193 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5008,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC193",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC193",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5008,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC193", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6008 ,protection_group_id: -5008, protection_element_id:-5008], primaryKey: false);
      insert('organizations', [ id: 104994, nci_institute_code: "NC194", name: "Alan F Jacks MD PA", city: "Rutherford College", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5009,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC194",GROUP_DESC:"NC194 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5009,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC194",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC194",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5009,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC194", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6009 ,protection_group_id: -5009, protection_element_id:-5009], primaryKey: false);
      insert('organizations', [ id: 104995, nci_institute_code: "NC195", name: "Campbell University School of Pharmacy", city: "Buies Creek", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5010,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC195",GROUP_DESC:"NC195 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5010,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC195",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC195",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5010,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC195", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6010 ,protection_group_id: -5010, protection_element_id:-5010], primaryKey: false);
      insert('organizations', [ id: 104996, nci_institute_code: "NC196", name: "Salem Research Group Inc", city: "Winston-Salem", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5011,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC196",GROUP_DESC:"NC196 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5011,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC196",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC196",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5011,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC196", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6011 ,protection_group_id: -5011, protection_element_id:-5011], primaryKey: false);
      insert('organizations', [ id: 104997, nci_institute_code: "NC197", name: "Outpatient Cancer Center of FirstHealth", city: "Pinehurst", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -5012,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC197",GROUP_DESC:"NC197 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -5012,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC197",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC197",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -5012,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC197", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:6012 ,protection_group_id: -5012, protection_element_id:-5012], primaryKey: false);
    }

    void m40() {
        // all40 (0 terms)
    }

    void down() {
        execute("delete from csm_pg_pe where pg_pe_id >= 1015 and  pg_pe_id <= 8999 ");
        execute("delete from CSM_PROTECTION_GROUP where protection_group_id  <= -15 ");
        execute("delete from csm_protection_element where protection_element_id <= -15 ");
        execute("delete from csm_group where group_id <= -15 ");
        execute("DELETE from organizations where id >= 100000 and id < 110000")
    }
}
