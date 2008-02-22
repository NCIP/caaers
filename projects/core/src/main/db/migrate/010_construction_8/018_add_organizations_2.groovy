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
      insert('organizations', [ id: 100998, nci_institute_code: "47002", name: "Cancer Screening and Treatment Center", city: "Singapore", country: "Singapore"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1013,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.47002",GROUP_DESC:"47002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1013,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.47002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.47002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1013,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.47002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2013 ,protection_group_id: -1013, protection_element_id:-1013], primaryKey: false);
      insert('organizations', [ id: 100999, nci_institute_code: "47003", name: "Singapore General Hospital", city: "Singapore", country: "Singapore"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1014,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.47003",GROUP_DESC:"47003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1014,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.47003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.47003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1014,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.47003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2014 ,protection_group_id: -1014, protection_element_id:-1014], primaryKey: false);
      insert('organizations', [ id: 101000, nci_institute_code: "47004", name: "National University Hospital", city: "Singapore", country: "Singapore"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1015,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.47004",GROUP_DESC:"47004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1015,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.47004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.47004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1015,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.47004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2015 ,protection_group_id: -1015, protection_element_id:-1015], primaryKey: false);
      insert('organizations', [ id: 101001, nci_institute_code: "47005", name: "National Cancer Centre", city: "Singapore", country: "Singapore"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1016,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.47005",GROUP_DESC:"47005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1016,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.47005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.47005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1016,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.47005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2016 ,protection_group_id: -1016, protection_element_id:-1016], primaryKey: false);
      insert('organizations', [ id: 101002, nci_institute_code: "47006", name: "Johns Hopkins Singapore International Medical Center", city: "Singapore", country: "Singapore"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1017,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.47006",GROUP_DESC:"47006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1017,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.47006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.47006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1017,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.47006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2017 ,protection_group_id: -1017, protection_element_id:-1017], primaryKey: false);
      insert('organizations', [ id: 101003, nci_institute_code: "47007", name: "National University of Singapore", city: "Singapore", country: "Singapore"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1018,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.47007",GROUP_DESC:"47007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1018,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.47007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.47007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1018,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.47007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2018 ,protection_group_id: -1018, protection_element_id:-1018], primaryKey: false);
      insert('organizations', [ id: 101004, nci_institute_code: "47009", name: "Institute of Molecular and Cell Biology Singapore", city: "Singapore", country: "Singapore"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1019,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.47009",GROUP_DESC:"47009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1019,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.47009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.47009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1019,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.47009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2019 ,protection_group_id: -1019, protection_element_id:-1019], primaryKey: false);
      insert('organizations', [ id: 101005, nci_institute_code: "47010", name: "Nanyang Technological University", city: "Singapore", country: "Singapore"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1020,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.47010",GROUP_DESC:"47010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1020,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.47010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.47010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1020,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.47010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2020 ,protection_group_id: -1020, protection_element_id:-1020], primaryKey: false);
      insert('organizations', [ id: 101006, nci_institute_code: "47011", name: "Singapore Health Services Pte Ltd", city: "Singapore", country: "Singapore"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1021,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.47011",GROUP_DESC:"47011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1021,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.47011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.47011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1021,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.47011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2021 ,protection_group_id: -1021, protection_element_id:-1021], primaryKey: false);
      insert('organizations', [ id: 101007, nci_institute_code: "48001", name: "Hospital Infantil De Mexico", city: "Mexico 7 D. F.", country: "Mexico"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1022,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.48001",GROUP_DESC:"48001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1022,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.48001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.48001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1022,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.48001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2022 ,protection_group_id: -1022, protection_element_id:-1022], primaryKey: false);
      insert('organizations', [ id: 101008, nci_institute_code: "48002", name: "Hospital General De Mexico", city: "Mexico City, 7df", country: "Mexico"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1023,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.48002",GROUP_DESC:"48002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1023,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.48002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.48002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1023,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.48002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2023 ,protection_group_id: -1023, protection_element_id:-1023], primaryKey: false);
      insert('organizations', [ id: 101009, nci_institute_code: "48003", name: "Apartado Postal 105-34", city: "Mexico 5, D.F.", country: "Mexico"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1024,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.48003",GROUP_DESC:"48003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1024,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.48003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.48003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1024,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.48003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2024 ,protection_group_id: -1024, protection_element_id:-1024], primaryKey: false);
      insert('organizations', [ id: 101010, nci_institute_code: "48004", name: "University Nacional Autonoma De Mexico", city: "Mexico 20 D.F.", country: "Mexico"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1025,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.48004",GROUP_DESC:"48004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1025,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.48004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.48004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1025,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.48004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2025 ,protection_group_id: -1025, protection_element_id:-1025], primaryKey: false);
      insert('organizations', [ id: 101011, nci_institute_code: "48005", name: "Hospital Central Militar", city: "Mexico D.F.", country: "Mexico"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1026,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.48005",GROUP_DESC:"48005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1026,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.48005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.48005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1026,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.48005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2026 ,protection_group_id: -1026, protection_element_id:-1026], primaryKey: false);
      insert('organizations', [ id: 101012, nci_institute_code: "48006", name: "Centro De Hem/Med Int.", city: "Puebla, Pue", country: "Mexico"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1027,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.48006",GROUP_DESC:"48006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1027,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.48006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.48006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1027,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.48006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2027 ,protection_group_id: -1027, protection_element_id:-1027], primaryKey: false);
      insert('organizations', [ id: 101013, nci_institute_code: "48007", name: "Instituto Nacional De Cancerologia de Mexico", city: "Tlalpan", country: "Mexico"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1028,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.48007",GROUP_DESC:"48007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1028,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.48007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.48007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1028,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.48007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2028 ,protection_group_id: -1028, protection_element_id:-1028], primaryKey: false);
      insert('organizations', [ id: 101014, nci_institute_code: "49001", name: "National Cancer Institute of Egypt", city: "Cairo", country: "Egypt"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1029,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.49001",GROUP_DESC:"49001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1029,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.49001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.49001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1029,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.49001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2029 ,protection_group_id: -1029, protection_element_id:-1029], primaryKey: false);
      insert('organizations', [ id: 101015, nci_institute_code: "49003", name: "Cairo University", city: "Cairo", country: "Egypt"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1030,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.49003",GROUP_DESC:"49003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1030,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.49003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.49003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1030,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.49003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2030 ,protection_group_id: -1030, protection_element_id:-1030], primaryKey: false);
      insert('organizations', [ id: 101016, nci_institute_code: "49004", name: "Jordan University Hospital", city: "Amman, Jordon", country: "Egypt"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1031,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.49004",GROUP_DESC:"49004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1031,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.49004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.49004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1031,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.49004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2031 ,protection_group_id: -1031, protection_element_id:-1031], primaryKey: false);
      insert('organizations', [ id: 101017, nci_institute_code: "49005", name: "Misr International Hospital", city: "Cairo", country: "Egypt"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1032,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.49005",GROUP_DESC:"49005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1032,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.49005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.49005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1032,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.49005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2032 ,protection_group_id: -1032, protection_element_id:-1032], primaryKey: false);
      insert('organizations', [ id: 101018, nci_institute_code: "49006", name: "Cairo Oncology Center", city: "Cairo", country: "Egypt"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1033,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.49006",GROUP_DESC:"49006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1033,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.49006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.49006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1033,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.49006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2033 ,protection_group_id: -1033, protection_element_id:-1033], primaryKey: false);
      insert('organizations', [ id: 101019, nci_institute_code: "51001", name: "University Hospital of Leyden", city: "Leyden", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1034,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51001",GROUP_DESC:"51001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1034,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1034,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2034 ,protection_group_id: -1034, protection_element_id:-1034], primaryKey: false);
      insert('organizations', [ id: 101020, nci_institute_code: "51002", name: "Univ. Hosp Kijkzigt", city: "Rotterdam, Holland", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1035,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51002",GROUP_DESC:"51002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1035,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1035,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2035 ,protection_group_id: -1035, protection_element_id:-1035], primaryKey: false);
      insert('organizations', [ id: 101021, nci_institute_code: "51003", name: "Free Univ. Hosp.", city: "Amsterdam", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1036,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51003",GROUP_DESC:"51003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1036,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1036,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2036 ,protection_group_id: -1036, protection_element_id:-1036], primaryKey: false);
      insert('organizations', [ id: 101022, nci_institute_code: "51004", name: "Municipal Hosp.", city: "3400 - Dordrecht", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1037,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51004",GROUP_DESC:"51004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1037,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1037,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2037 ,protection_group_id: -1037, protection_element_id:-1037], primaryKey: false);
    }

    void m1() {
        // all1 (25 terms)
      insert('organizations', [ id: 101023, nci_institute_code: "51005", name: "Academ Zienkenhuis Bij De University", city: "Amsterdam", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1038,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51005",GROUP_DESC:"51005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1038,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1038,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2038 ,protection_group_id: -1038, protection_element_id:-1038], primaryKey: false);
      insert('organizations', [ id: 101024, nci_institute_code: "51006", name: "University Hospital", city: "2333 Aa Leiden", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1039,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51006",GROUP_DESC:"51006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1039,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1039,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2039 ,protection_group_id: -1039, protection_element_id:-1039], primaryKey: false);
      insert('organizations', [ id: 101025, nci_institute_code: "51007", name: "Univ Hosp Rotterdam-Dijkzigt", city: "Rotterdam", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1040,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51007",GROUP_DESC:"51007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1040,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1040,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2040 ,protection_group_id: -1040, protection_element_id:-1040], primaryKey: false);
      insert('organizations', [ id: 101026, nci_institute_code: "51008", name: "Municipal Hosp. Leyenburg", city: "The Hague", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1041,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51008",GROUP_DESC:"51008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1041,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1041,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2041 ,protection_group_id: -1041, protection_element_id:-1041], primaryKey: false);
      insert('organizations', [ id: 101027, nci_institute_code: "51009", name: "Canisius-Wilhelmina Hosp.", city: "6532 Sz Nijmegen", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1042,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51009",GROUP_DESC:"51009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1042,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1042,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2042 ,protection_group_id: -1042, protection_element_id:-1042], primaryKey: false);
      insert('organizations', [ id: 101028, nci_institute_code: "51010", name: "Nijmegen University Cancer Center", city: "Nymegen", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1043,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51010",GROUP_DESC:"51010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1043,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1043,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2043 ,protection_group_id: -1043, protection_element_id:-1043], primaryKey: false);
      insert('organizations', [ id: 101029, nci_institute_code: "51011", name: "University Medical Center Groningen", city: "Groningen", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1044,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51011",GROUP_DESC:"51011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1044,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1044,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2044 ,protection_group_id: -1044, protection_element_id:-1044], primaryKey: false);
      insert('organizations', [ id: 101030, nci_institute_code: "51012", name: "St. Raddood Hospital", city: "Nymegen", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1045,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51012",GROUP_DESC:"51012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1045,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1045,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2045 ,protection_group_id: -1045, protection_element_id:-1045], primaryKey: false);
      insert('organizations', [ id: 101031, nci_institute_code: "51013", name: "Primatencentrum Tno", city: "Rijswijk", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1046,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51013",GROUP_DESC:"51013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1046,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1046,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2046 ,protection_group_id: -1046, protection_element_id:-1046], primaryKey: false);
      insert('organizations', [ id: 101032, nci_institute_code: "51014", name: "Academisch Ziekenhvis-Leiden", city: "2333aa Leiden", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1047,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51014",GROUP_DESC:"51014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1047,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1047,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2047 ,protection_group_id: -1047, protection_element_id:-1047], primaryKey: false);
      insert('organizations', [ id: 101033, nci_institute_code: "51015", name: "Academisch Ziekenhuis-Groningen", city: "9700 Rb Groningen", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1048,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51015",GROUP_DESC:"51015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1048,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1048,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2048 ,protection_group_id: -1048, protection_element_id:-1048], primaryKey: false);
      insert('organizations', [ id: 101034, nci_institute_code: "51016", name: "Sophia Childrens Hospital", city: "Rotterdam 3000 Ll", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1049,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51016",GROUP_DESC:"51016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1049,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1049,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2049 ,protection_group_id: -1049, protection_element_id:-1049], primaryKey: false);
      insert('organizations', [ id: 101035, nci_institute_code: "51017", name: "The Netherlands Cancer Institute", city: "Amsterdam, Holland", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1050,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51017",GROUP_DESC:"51017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1050,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1050,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2050 ,protection_group_id: -1050, protection_element_id:-1050], primaryKey: false);
      insert('organizations', [ id: 101036, nci_institute_code: "51018", name: "Emma Children's Hospital", city: "Amsterdam", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1051,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51018",GROUP_DESC:"51018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1051,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1051,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2051 ,protection_group_id: -1051, protection_element_id:-1051], primaryKey: false);
      insert('organizations', [ id: 101037, nci_institute_code: "51019", name: "National Cancer Institute - Netherlands", city: "Nymegen", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1052,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51019",GROUP_DESC:"51019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1052,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1052,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2052 ,protection_group_id: -1052, protection_element_id:-1052], primaryKey: false);
      insert('organizations', [ id: 101038, nci_institute_code: "51020", name: "Ignatius Hospital", city: "Breda", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1053,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51020",GROUP_DESC:"51020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1053,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1053,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2053 ,protection_group_id: -1053, protection_element_id:-1053], primaryKey: false);
      insert('organizations', [ id: 101039, nci_institute_code: "51021", name: "Univ. Hosp. Utrecht", city: "Utrecht", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1054,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51021",GROUP_DESC:"51021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1054,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1054,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2054 ,protection_group_id: -1054, protection_element_id:-1054], primaryKey: false);
      insert('organizations', [ id: 101040, nci_institute_code: "51022", name: "University Hospital Roterdam - Daniel Den Hoed Cancer Center", city: "Rotterdam", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1055,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51022",GROUP_DESC:"51022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1055,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1055,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2055 ,protection_group_id: -1055, protection_element_id:-1055], primaryKey: false);
      insert('organizations', [ id: 101041, nci_institute_code: "51023", name: "Drechtsteden Ziekenhuis", city: "Dordrecht 1", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1056,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51023",GROUP_DESC:"51023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1056,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1056,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2056 ,protection_group_id: -1056, protection_element_id:-1056], primaryKey: false);
      insert('organizations', [ id: 101042, nci_institute_code: "51024", name: "Leiden University Hospital", city: "2333", state: "ZA", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1057,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51024",GROUP_DESC:"51024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1057,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1057,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2057 ,protection_group_id: -1057, protection_element_id:-1057], primaryKey: false);
      insert('organizations', [ id: 101043, nci_institute_code: "51025", name: "Academisch Ziekenhuis Maastricht", city: "Maastricht", state: "HX", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1058,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51025",GROUP_DESC:"51025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1058,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1058,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2058 ,protection_group_id: -1058, protection_element_id:-1058], primaryKey: false);
      insert('organizations', [ id: 101044, nci_institute_code: "51026", name: "Academisch Ziekenhuis Groningen", city: "Groningen", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1059,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51026",GROUP_DESC:"51026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1059,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1059,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2059 ,protection_group_id: -1059, protection_element_id:-1059], primaryKey: false);
      insert('organizations', [ id: 101045, nci_institute_code: "51027", name: "Albert Schweitzer Ziekenhuis", city: "Dordrecht", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1060,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51027",GROUP_DESC:"51027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1060,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1060,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2060 ,protection_group_id: -1060, protection_element_id:-1060], primaryKey: false);
      insert('organizations', [ id: 101046, nci_institute_code: "51028", name: "Antonius Ziekenhuis", city: "Sneek", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1061,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51028",GROUP_DESC:"51028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1061,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1061,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2061 ,protection_group_id: -1061, protection_element_id:-1061], primaryKey: false);
      insert('organizations', [ id: 101047, nci_institute_code: "51029", name: "Beatrix Ziekenhuis", city: "Gorinchem", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1062,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51029",GROUP_DESC:"51029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1062,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1062,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2062 ,protection_group_id: -1062, protection_element_id:-1062], primaryKey: false);
    }

    void m2() {
        // all2 (25 terms)
      insert('organizations', [ id: 101048, nci_institute_code: "51030", name: "Bosch Medicentrum", city: "'S-Hertogenbosch", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1063,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51030",GROUP_DESC:"51030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1063,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1063,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2063 ,protection_group_id: -1063, protection_element_id:-1063], primaryKey: false);
      insert('organizations', [ id: 101049, nci_institute_code: "51031", name: "Cauolus-Liduina Ziekenhuis", city: "'S-Hertogenbosch", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1064,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51031",GROUP_DESC:"51031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1064,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1064,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2064 ,protection_group_id: -1064, protection_element_id:-1064], primaryKey: false);
      insert('organizations', [ id: 101050, nci_institute_code: "51032", name: "Catharina Ziekenhuis", city: "Eindhoven", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1065,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51032",GROUP_DESC:"51032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1065,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1065,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2065 ,protection_group_id: -1065, protection_element_id:-1065], primaryKey: false);
      insert('organizations', [ id: 101051, nci_institute_code: "51033", name: "Delfzicht Ziekenhuis", city: "Delfzijl", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1066,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51033",GROUP_DESC:"51033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1066,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1066,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2066 ,protection_group_id: -1066, protection_element_id:-1066], primaryKey: false);
      insert('organizations', [ id: 101052, nci_institute_code: "51034", name: "Diaconessenhuis", city: "Leiden", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1067,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51034",GROUP_DESC:"51034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1067,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1067,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2067 ,protection_group_id: -1067, protection_element_id:-1067], primaryKey: false);
      insert('organizations', [ id: 101053, nci_institute_code: "51035", name: "Diakonessenhuis, Utrecht", city: "Utrecht", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1068,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51035",GROUP_DESC:"51035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1068,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1068,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2068 ,protection_group_id: -1068, protection_element_id:-1068], primaryKey: false);
      insert('organizations', [ id: 101054, nci_institute_code: "51036", name: "Dijkzigt Ziekenhuis (AZR)", city: "Rotterdam", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1069,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51036",GROUP_DESC:"51036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1069,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1069,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2069 ,protection_group_id: -1069, protection_element_id:-1069], primaryKey: false);
      insert('organizations', [ id: 101055, nci_institute_code: "51037", name: "Holy Ziekenhuis", city: "Vlaardingen", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1070,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51037",GROUP_DESC:"51037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1070,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1070,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2070 ,protection_group_id: -1070, protection_element_id:-1070], primaryKey: false);
      insert('organizations', [ id: 101056, nci_institute_code: "51038", name: "Ijsselland Ziekenhuis", city: "Ijssel", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1071,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51038",GROUP_DESC:"51038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1071,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1071,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2071 ,protection_group_id: -1071, protection_element_id:-1071], primaryKey: false);
      insert('organizations', [ id: 101057, nci_institute_code: "51039", name: "Ikazia Zienkenhuis", city: "Rotterdam", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1072,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51039",GROUP_DESC:"51039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1072,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1072,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2072 ,protection_group_id: -1072, protection_element_id:-1072], primaryKey: false);
      insert('organizations', [ id: 101058, nci_institute_code: "51040", name: "Isala Klinieken", city: "Zwolle", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1073,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51040",GROUP_DESC:"51040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1073,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1073,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2073 ,protection_group_id: -1073, protection_element_id:-1073], primaryKey: false);
      insert('organizations', [ id: 101059, nci_institute_code: "51041", name: "Martini Ziekenhuis", city: "Groningen", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1074,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51041",GROUP_DESC:"51041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1074,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1074,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2074 ,protection_group_id: -1074, protection_element_id:-1074], primaryKey: false);
      insert('organizations', [ id: 101060, nci_institute_code: "51042", name: "Medisch Centrum Leeuwarden", city: "Leeuwarden", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1075,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51042",GROUP_DESC:"51042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1075,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1075,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2075 ,protection_group_id: -1075, protection_element_id:-1075], primaryKey: false);
      insert('organizations', [ id: 101061, nci_institute_code: "51043", name: "Onze Lieve Vrouwe Gasthuis", city: "Amsterdam", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1076,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51043",GROUP_DESC:"51043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1076,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1076,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2076 ,protection_group_id: -1076, protection_element_id:-1076], primaryKey: false);
      insert('organizations', [ id: 101062, nci_institute_code: "51046", name: "Schieland Ziekenhuis", city: "Schiedam", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1077,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51046",GROUP_DESC:"51046 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1077,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51046",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51046",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1077,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51046", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2077 ,protection_group_id: -1077, protection_element_id:-1077], primaryKey: false);
      insert('organizations', [ id: 101063, nci_institute_code: "51047", name: "Sint Clara Ziekenhuis", city: "Rotterdam", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1078,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51047",GROUP_DESC:"51047 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1078,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51047",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51047",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1078,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51047", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2078 ,protection_group_id: -1078, protection_element_id:-1078], primaryKey: false);
      insert('organizations', [ id: 101064, nci_institute_code: "51048", name: "Slingeland Ziekenhuis", city: "Doetichem", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1079,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51048",GROUP_DESC:"51048 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1079,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51048",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51048",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1079,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51048", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2079 ,protection_group_id: -1079, protection_element_id:-1079], primaryKey: false);
      insert('organizations', [ id: 101065, nci_institute_code: "51049", name: "St Elisabeth Ziekenhuis", city: "Tilburg", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1080,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51049",GROUP_DESC:"51049 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1080,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51049",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51049",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1080,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51049", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2080 ,protection_group_id: -1080, protection_element_id:-1080], primaryKey: false);
      insert('organizations', [ id: 101066, nci_institute_code: "51050", name: "St Joseph Ziekenhuis", city: "Veldhoven", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1081,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51050",GROUP_DESC:"51050 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1081,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51050",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51050",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1081,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51050", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2081 ,protection_group_id: -1081, protection_element_id:-1081], primaryKey: false);
      insert('organizations', [ id: 101067, nci_institute_code: "51051", name: "St Lucas Ziekenhuis", city: "Winschoten", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1082,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51051",GROUP_DESC:"51051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1082,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1082,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2082 ,protection_group_id: -1082, protection_element_id:-1082], primaryKey: false);
      insert('organizations', [ id: 101068, nci_institute_code: "51052", name: "Tweesteden Ziekenhuis", city: "Tilburg", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1083,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51052",GROUP_DESC:"51052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1083,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1083,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2083 ,protection_group_id: -1083, protection_element_id:-1083], primaryKey: false);
      insert('organizations', [ id: 101069, nci_institute_code: "51053", name: "Ziekenhuis Amstelveen", city: "Amstelveen", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1084,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51053",GROUP_DESC:"51053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1084,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1084,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2084 ,protection_group_id: -1084, protection_element_id:-1084], primaryKey: false);
      insert('organizations', [ id: 101070, nci_institute_code: "51054", name: "Ziekenhuis de Baronie", city: "Breda", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1085,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51054",GROUP_DESC:"51054 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1085,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51054",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51054",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1085,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51054", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2085 ,protection_group_id: -1085, protection_element_id:-1085], primaryKey: false);
      insert('organizations', [ id: 101071, nci_institute_code: "51055", name: "Ziekenhuis Gelderse Vallei", city: "Bennekom", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1086,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51055",GROUP_DESC:"51055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1086,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1086,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2086 ,protection_group_id: -1086, protection_element_id:-1086], primaryKey: false);
      insert('organizations', [ id: 101072, nci_institute_code: "51056", name: "Ziekenhuis Gooi-Noord", city: "Blaricum", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1087,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51056",GROUP_DESC:"51056 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1087,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51056",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51056",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1087,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51056", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2087 ,protection_group_id: -1087, protection_element_id:-1087], primaryKey: false);
    }

    void m3() {
        // all3 (25 terms)
      insert('organizations', [ id: 101073, nci_institute_code: "51057", name: "Ziekenhuis Sint Franciscus", city: "Roosendaal", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1088,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51057",GROUP_DESC:"51057 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1088,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51057",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51057",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1088,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51057", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2088 ,protection_group_id: -1088, protection_element_id:-1088], primaryKey: false);
      insert('organizations', [ id: 101074, nci_institute_code: "51058", name: "Ziekenhuis St. Jansdal", city: "Hardewijk", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1089,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51058",GROUP_DESC:"51058 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1089,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51058",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51058",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1089,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51058", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2089 ,protection_group_id: -1089, protection_element_id:-1089], primaryKey: false);
      insert('organizations', [ id: 101075, nci_institute_code: "51059", name: "Ziekenhuis Walcheren", city: "Vlissingen", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1090,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51059",GROUP_DESC:"51059 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1090,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51059",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51059",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1090,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51059", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2090 ,protection_group_id: -1090, protection_element_id:-1090], primaryKey: false);
      insert('organizations', [ id: 101076, nci_institute_code: "51060", name: "Ziekenhuis Zeeuws Vlaanderen", city: "Oostburg", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1091,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51060",GROUP_DESC:"51060 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1091,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51060",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51060",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1091,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51060", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2091 ,protection_group_id: -1091, protection_element_id:-1091], primaryKey: false);
      insert('organizations', [ id: 101077, nci_institute_code: "51061", name: "Ziekenhuis Centrum Apeldoorn", city: "Apeldoorn", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1092,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51061",GROUP_DESC:"51061 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1092,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51061",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51061",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1092,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51061", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2092 ,protection_group_id: -1092, protection_element_id:-1092], primaryKey: false);
      insert('organizations', [ id: 101078, nci_institute_code: "51062", name: "Zuiderziekenhuis", city: "Rotterdam", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1093,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51062",GROUP_DESC:"51062 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1093,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51062",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51062",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1093,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51062", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2093 ,protection_group_id: -1093, protection_element_id:-1093], primaryKey: false);
      insert('organizations', [ id: 101079, nci_institute_code: "51063", name: "Academisch Ziekenhuis Nijmegen", city: "Nijmegen", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1094,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51063",GROUP_DESC:"51063 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1094,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51063",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51063",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1094,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51063", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2094 ,protection_group_id: -1094, protection_element_id:-1094], primaryKey: false);
      insert('organizations', [ id: 101080, nci_institute_code: "51064", name: "Spaarne Ziekenhuis (locatie Haarlem)", city: "Haarlem", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1095,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51064",GROUP_DESC:"51064 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1095,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51064",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51064",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1095,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51064", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2095 ,protection_group_id: -1095, protection_element_id:-1095], primaryKey: false);
      insert('organizations', [ id: 101081, nci_institute_code: "51065", name: "Streekziekenhuis Coervorden-Hardenberg", city: "Hardenberg", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1096,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51065",GROUP_DESC:"51065 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1096,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51065",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51065",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1096,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51065", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2096 ,protection_group_id: -1096, protection_element_id:-1096], primaryKey: false);
      insert('organizations', [ id: 101082, nci_institute_code: "51066", name: "Eemland Ziekenhuis (locatie de Lichtenberg)", city: "Amersfoort", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1097,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51066",GROUP_DESC:"51066 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1097,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51066",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51066",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1097,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51066", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2097 ,protection_group_id: -1097, protection_element_id:-1097], primaryKey: false);
      insert('organizations', [ id: 101083, nci_institute_code: "51067", name: "Leids Universitair Medisch Centrum (LUMC)", city: "Leiden", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1098,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51067",GROUP_DESC:"51067 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1098,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51067",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51067",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1098,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51067", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2098 ,protection_group_id: -1098, protection_element_id:-1098], primaryKey: false);
      insert('organizations', [ id: 101084, nci_institute_code: "51068", name: "Medisch Spectrum Twente (locatie Ariensplein)", city: "Enschede", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1099,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51068",GROUP_DESC:"51068 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1099,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51068",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51068",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1099,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51068", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2099 ,protection_group_id: -1099, protection_element_id:-1099], primaryKey: false);
      insert('organizations', [ id: 101085, nci_institute_code: "51069", name: "Rijnland Ziekenhuis", city: "Leiderdorp", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1100,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51069",GROUP_DESC:"51069 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1100,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51069",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51069",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1100,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51069", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2100 ,protection_group_id: -1100, protection_element_id:-1100], primaryKey: false);
      insert('organizations', [ id: 101086, nci_institute_code: "51070", name: "Stichting Het van Weel-Bethesda Ziekenhuis", city: "Dirksland", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1101,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51070",GROUP_DESC:"51070 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1101,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51070",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51070",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1101,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51070", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2101 ,protection_group_id: -1101, protection_element_id:-1101], primaryKey: false);
      insert('organizations', [ id: 101087, nci_institute_code: "51071", name: "Rijnstate Ziekenhuis", city: "Arnhem", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1102,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51071",GROUP_DESC:"51071 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1102,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51071",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51071",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1102,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51071", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2102 ,protection_group_id: -1102, protection_element_id:-1102], primaryKey: false);
      insert('organizations', [ id: 101088, nci_institute_code: "51072", name: "Diaconessenhuis, Utrecht", city: "Utrecht", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1103,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51072",GROUP_DESC:"51072 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1103,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51072",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51072",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1103,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51072", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2103 ,protection_group_id: -1103, protection_element_id:-1103], primaryKey: false);
      insert('organizations', [ id: 101089, nci_institute_code: "51073", name: "Sofia Childrens's Hospital Rottedam", city: "Rotterdam", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1104,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51073",GROUP_DESC:"51073 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1104,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51073",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51073",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1104,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51073", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2104 ,protection_group_id: -1104, protection_element_id:-1104], primaryKey: false);
      insert('organizations', [ id: 101090, nci_institute_code: "51074", name: "Radboud University Nijmegen Medical Centre", city: "Nijmegen", state: "GA", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1105,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51074",GROUP_DESC:"51074 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1105,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51074",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51074",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1105,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51074", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2105 ,protection_group_id: -1105, protection_element_id:-1105], primaryKey: false);
      insert('organizations', [ id: 101091, nci_institute_code: "51075", name: "Dutch Childhood Oncology Group", city: "Den Haag", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1106,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51075",GROUP_DESC:"51075 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1106,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51075",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51075",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1106,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51075", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2106 ,protection_group_id: -1106, protection_element_id:-1106], primaryKey: false);
      insert('organizations', [ id: 101092, nci_institute_code: "51076", name: "Erasmus University Medical Center", city: "CE Rotterdam", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1107,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51076",GROUP_DESC:"51076 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1107,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51076",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51076",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1107,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51076", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2107 ,protection_group_id: -1107, protection_element_id:-1107], primaryKey: false);
      insert('organizations', [ id: 101093, nci_institute_code: "52001", name: "Auckland Hospital", city: "Auckland", country: "New Zealand"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1108,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.52001",GROUP_DESC:"52001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1108,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.52001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.52001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1108,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.52001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2108 ,protection_group_id: -1108, protection_element_id:-1108], primaryKey: false);
      insert('organizations', [ id: 101094, nci_institute_code: "52002", name: "Princess Margaret Hospital", city: "Christchurch", country: "New Zealand"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1109,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.52002",GROUP_DESC:"52002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1109,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.52002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.52002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1109,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.52002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2109 ,protection_group_id: -1109, protection_element_id:-1109], primaryKey: false);
      insert('organizations', [ id: 101095, nci_institute_code: "52003", name: "University of Auckland School of Medicine", city: "Auckland", country: "New Zealand"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1110,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.52003",GROUP_DESC:"52003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1110,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.52003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.52003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1110,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.52003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2110 ,protection_group_id: -1110, protection_element_id:-1110], primaryKey: false);
      insert('organizations', [ id: 101096, nci_institute_code: "52004", name: "Hutt Hospital", city: "Wellington", country: "New Zealand"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1111,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.52004",GROUP_DESC:"52004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1111,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.52004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.52004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1111,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.52004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2111 ,protection_group_id: -1111, protection_element_id:-1111], primaryKey: false);
      insert('organizations', [ id: 101097, nci_institute_code: "52005", name: "Wellington Hospital", city: "Wellington", country: "New Zealand"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1112,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.52005",GROUP_DESC:"52005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1112,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.52005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.52005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1112,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.52005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2112 ,protection_group_id: -1112, protection_element_id:-1112], primaryKey: false);
    }

    void m4() {
        // all4 (25 terms)
      insert('organizations', [ id: 101098, nci_institute_code: "52006", name: "Christchurch Hospital", city: "Christchurch", country: "New Zealand"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1113,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.52006",GROUP_DESC:"52006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1113,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.52006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.52006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1113,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.52006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2113 ,protection_group_id: -1113, protection_element_id:-1113], primaryKey: false);
      insert('organizations', [ id: 101099, nci_institute_code: "52007", name: "Waikato Hospital", city: "Hamilton", country: "New Zealand"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1114,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.52007",GROUP_DESC:"52007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1114,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.52007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.52007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1114,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.52007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2114 ,protection_group_id: -1114, protection_element_id:-1114], primaryKey: false);
      insert('organizations', [ id: 101100, nci_institute_code: "52008", name: "Starship Children's Hospital", city: "Auckland", country: "New Zealand"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1115,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.52008",GROUP_DESC:"52008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1115,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.52008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.52008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1115,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.52008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2115 ,protection_group_id: -1115, protection_element_id:-1115], primaryKey: false);
      insert('organizations', [ id: 101101, nci_institute_code: "52009", name: "Dunedin Hospital", city: "Dunedin", country: "New Zealand"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1116,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.52009",GROUP_DESC:"52009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1116,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.52009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.52009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1116,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.52009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2116 ,protection_group_id: -1116, protection_element_id:-1116], primaryKey: false);
      insert('organizations', [ id: 101102, nci_institute_code: "52010", name: "Palmerston North Hospital", city: "Plamerston", country: "New Zealand"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1117,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.52010",GROUP_DESC:"52010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1117,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.52010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.52010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1117,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.52010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2117 ,protection_group_id: -1117, protection_element_id:-1117], primaryKey: false);
      insert('organizations', [ id: 101103, nci_institute_code: "52011", name: "Waitemata District Health Board", city: "Takapuna", country: "New Zealand"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1118,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.52011",GROUP_DESC:"52011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1118,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.52011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.52011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1118,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.52011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2118 ,protection_group_id: -1118, protection_element_id:-1118], primaryKey: false);
      insert('organizations', [ id: 101104, nci_institute_code: "52012", name: "Wellington Children's Hospital", city: "Wellington", country: "New Zealand"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1119,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.52012",GROUP_DESC:"52012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1119,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.52012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.52012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1119,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.52012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2119 ,protection_group_id: -1119, protection_element_id:-1119], primaryKey: false);
      insert('organizations', [ id: 101105, nci_institute_code: "54002", name: "Rikshospitalet", city: "Oslo", country: "Norway"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1120,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.54002",GROUP_DESC:"54002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1120,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.54002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.54002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1120,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.54002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2120 ,protection_group_id: -1120, protection_element_id:-1120], primaryKey: false);
      insert('organizations', [ id: 101106, nci_institute_code: "54003", name: "National Hospital of Norway", city: "Oslo", country: "Norway"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1121,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.54003",GROUP_DESC:"54003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1121,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.54003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.54003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1121,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.54003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2121 ,protection_group_id: -1121, protection_element_id:-1121], primaryKey: false);
      insert('organizations', [ id: 101107, nci_institute_code: "54004", name: "University of Oslo", city: "Oslo", country: "Norway"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1122,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.54004",GROUP_DESC:"54004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1122,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.54004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.54004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1122,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.54004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2122 ,protection_group_id: -1122, protection_element_id:-1122], primaryKey: false);
      insert('organizations', [ id: 101108, nci_institute_code: "54005", name: "Aust-Agder Central Hosp.", city: "4801 Arendal", country: "Norway"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1123,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.54005",GROUP_DESC:"54005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1123,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.54005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.54005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1123,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.54005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2123 ,protection_group_id: -1123, protection_element_id:-1123], primaryKey: false);
      insert('organizations', [ id: 101109, nci_institute_code: "54006", name: "Nordic Society for Gynecology Oncology", city: "Oslo", country: "Norway"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1124,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.54006",GROUP_DESC:"54006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1124,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.54006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.54006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1124,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.54006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2124 ,protection_group_id: -1124, protection_element_id:-1124], primaryKey: false);
      insert('organizations', [ id: 101110, nci_institute_code: "54007", name: "Aker University Hospital", city: "Oslo 5", country: "Norway"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1125,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.54007",GROUP_DESC:"54007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1125,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.54007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.54007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1125,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.54007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2125 ,protection_group_id: -1125, protection_element_id:-1125], primaryKey: false);
      insert('organizations', [ id: 101111, nci_institute_code: "54008", name: "Univ. Hosp.", city: "Trondheim", country: "Norway"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1126,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.54008",GROUP_DESC:"54008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1126,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.54008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.54008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1126,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.54008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2126 ,protection_group_id: -1126, protection_element_id:-1126], primaryKey: false);
      insert('organizations', [ id: 101112, nci_institute_code: "54009", name: "Huekeland Hospital-University of Berlin", city: "Bergen", country: "Norway"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1127,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.54009",GROUP_DESC:"54009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1127,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.54009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.54009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1127,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.54009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2127 ,protection_group_id: -1127, protection_element_id:-1127], primaryKey: false);
      insert('organizations', [ id: 101113, nci_institute_code: "54010", name: "Arne Hospital", city: "Tromso", country: "Norway"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1128,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.54010",GROUP_DESC:"54010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1128,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.54010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.54010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1128,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.54010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2128 ,protection_group_id: -1128, protection_element_id:-1128], primaryKey: false);
      insert('organizations', [ id: 101114, nci_institute_code: "54011", name: "Norwegian Radium Hospital", city: "Oslo", country: "Norway"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1129,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.54011",GROUP_DESC:"54011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1129,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.54011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.54011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1129,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.54011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2129 ,protection_group_id: -1129, protection_element_id:-1129], primaryKey: false);
      insert('organizations', [ id: 101115, nci_institute_code: "55001", name: "Hospital Central Del Empleado", city: "Lima", country: "Peru"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1130,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.55001",GROUP_DESC:"55001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1130,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.55001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.55001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1130,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.55001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2130 ,protection_group_id: -1130, protection_element_id:-1130], primaryKey: false);
      insert('organizations', [ id: 101116, nci_institute_code: "55002", name: "Horacio Urteaga 849-F", city: "Lima", country: "Peru"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1131,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.55002",GROUP_DESC:"55002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1131,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.55002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.55002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1131,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.55002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2131 ,protection_group_id: -1131, protection_element_id:-1131], primaryKey: false);
      insert('organizations', [ id: 101117, nci_institute_code: "55003", name: "Clinica Etella Moris", city: "Lima", country: "Peru"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1132,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.55003",GROUP_DESC:"55003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1132,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.55003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.55003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1132,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.55003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2132 ,protection_group_id: -1132, protection_element_id:-1132], primaryKey: false);
      insert('organizations', [ id: 101118, nci_institute_code: "55004", name: "Instituto Nacional de Enfermedades Neoplasicas", city: "Lima", country: "Peru"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1133,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.55004",GROUP_DESC:"55004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1133,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.55004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.55004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1133,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.55004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2133 ,protection_group_id: -1133, protection_element_id:-1133], primaryKey: false);
      insert('organizations', [ id: 101119, nci_institute_code: "55005", name: "Oncosalud S.A.C.", city: "Lima", country: "Peru"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1134,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.55005",GROUP_DESC:"55005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1134,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.55005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.55005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1134,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.55005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2134 ,protection_group_id: -1134, protection_element_id:-1134], primaryKey: false);
      insert('organizations', [ id: 101120, nci_institute_code: "55006", name: "Hospital Nacional Guillermo Almenara Irigoyen", city: "Lima", country: "Peru"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1135,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.55006",GROUP_DESC:"55006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1135,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.55006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.55006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1135,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.55006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2135 ,protection_group_id: -1135, protection_element_id:-1135], primaryKey: false);
      insert('organizations', [ id: 101121, nci_institute_code: "55007", name: "Hospital Nacional Edgardo Rebagliati Martins", city: "Jesus Maria", state: "Lima", country: "Peru"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1136,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.55007",GROUP_DESC:"55007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1136,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.55007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.55007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1136,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.55007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2136 ,protection_group_id: -1136, protection_element_id:-1136], primaryKey: false);
      insert('organizations', [ id: 101122, nci_institute_code: "56002", name: "Chong Hua Hosp.", city: "Cebu City", country: "Philippines"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1137,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.56002",GROUP_DESC:"56002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1137,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.56002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.56002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1137,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.56002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2137 ,protection_group_id: -1137, protection_element_id:-1137], primaryKey: false);
    }

    void m5() {
        // all5 (25 terms)
      insert('organizations', [ id: 101123, nci_institute_code: "56003", name: "Veterans Administration Hospital Manilla", city: "Quezon", country: "Philippines"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1138,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.56003",GROUP_DESC:"56003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1138,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.56003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.56003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1138,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.56003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2138 ,protection_group_id: -1138, protection_element_id:-1138], primaryKey: false);
      insert('organizations', [ id: 101124, nci_institute_code: "56004", name: "Cebu Doctors Hosp.", city: "Cebu City", country: "Philippines"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1139,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.56004",GROUP_DESC:"56004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1139,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.56004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.56004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1139,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.56004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2139 ,protection_group_id: -1139, protection_element_id:-1139], primaryKey: false);
      insert('organizations', [ id: 101125, nci_institute_code: "56005", name: "St. Luke's Medical Center", city: "Quezon City", country: "Philippines"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1140,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.56005",GROUP_DESC:"56005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1140,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.56005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.56005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1140,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.56005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2140 ,protection_group_id: -1140, protection_element_id:-1140], primaryKey: false);
      insert('organizations', [ id: 101126, nci_institute_code: "57001", name: "Warsaw University School of Medicine", city: "Warsaw", country: "Poland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1141,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.57001",GROUP_DESC:"57001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1141,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.57001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.57001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1141,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.57001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2141 ,protection_group_id: -1141, protection_element_id:-1141], primaryKey: false);
      insert('organizations', [ id: 101127, nci_institute_code: "57002", name: "Postgraduate Medical School", city: "Pl-01-813 Warsaw", country: "Poland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1142,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.57002",GROUP_DESC:"57002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1142,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.57002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.57002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1142,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.57002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2142 ,protection_group_id: -1142, protection_element_id:-1142], primaryKey: false);
      insert('organizations', [ id: 101128, nci_institute_code: "57003", name: "Copernicus University School of Medicine", city: "Krakow", country: "Poland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1143,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.57003",GROUP_DESC:"57003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1143,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.57003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.57003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1143,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.57003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2143 ,protection_group_id: -1143, protection_element_id:-1143], primaryKey: false);
      insert('organizations', [ id: 101129, nci_institute_code: "57004", name: "Copernicus Hosp.", city: "Polska", country: "Poland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1144,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.57004",GROUP_DESC:"57004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1144,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.57004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.57004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1144,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.57004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2144 ,protection_group_id: -1144, protection_element_id:-1144], primaryKey: false);
      insert('organizations', [ id: 101130, nci_institute_code: "57005", name: "Oncology Centre Institute", city: "Warsaw", country: "Poland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1145,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.57005",GROUP_DESC:"57005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1145,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.57005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.57005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1145,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.57005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2145 ,protection_group_id: -1145, protection_element_id:-1145], primaryKey: false);
      insert('organizations', [ id: 101131, nci_institute_code: "57006", name: "The Maria Sklodowska C.M.C.C.I.O Krakow", city: "Krakow", country: "Poland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1146,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.57006",GROUP_DESC:"57006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1146,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.57006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.57006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1146,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.57006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2146 ,protection_group_id: -1146, protection_element_id:-1146], primaryKey: false);
      insert('organizations', [ id: 101132, nci_institute_code: "60001", name: "Peoples Hospital/Peking Medical College", city: "Peking", country: "China"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1147,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.60001",GROUP_DESC:"60001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1147,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.60001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.60001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1147,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.60001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2147 ,protection_group_id: -1147, protection_element_id:-1147], primaryKey: false);
      insert('organizations', [ id: 101133, nci_institute_code: "60002", name: "Shanghai Cancer Hospital, Shanghai Medical University", city: "Shanghai", country: "China"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1148,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.60002",GROUP_DESC:"60002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1148,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.60002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.60002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1148,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.60002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2148 ,protection_group_id: -1148, protection_element_id:-1148], primaryKey: false);
      insert('organizations', [ id: 101134, nci_institute_code: "60003", name: "Chinese Academy of Medical Sciences", city: "Beijing", country: "China"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1149,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.60003",GROUP_DESC:"60003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1149,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.60003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.60003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1149,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.60003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2149 ,protection_group_id: -1149, protection_element_id:-1149], primaryKey: false);
      insert('organizations', [ id: 101135, nci_institute_code: "60004", name: "Hong Kong University of Science And Technology", city: "Hong Kong", state: "Kowloon", country: "China"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1150,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.60004",GROUP_DESC:"60004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1150,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.60004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.60004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1150,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.60004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2150 ,protection_group_id: -1150, protection_element_id:-1150], primaryKey: false);
      insert('organizations', [ id: 101136, nci_institute_code: "62001", name: "Splaiul Independentei 99-101", city: "Bucharest 35", country: "Romania"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1151,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.62001",GROUP_DESC:"62001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1151,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.62001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.62001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1151,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.62001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2151 ,protection_group_id: -1151, protection_element_id:-1151], primaryKey: false);
      insert('organizations', [ id: 101137, nci_institute_code: "62002", name: "Victor Babes Institute", city: "Bucharest", country: "Romania"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1152,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.62002",GROUP_DESC:"62002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1152,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.62002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.62002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1152,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.62002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2152 ,protection_group_id: -1152, protection_element_id:-1152], primaryKey: false);
      insert('organizations', [ id: 101138, nci_institute_code: "63001", name: "Groote Schuur Hosp.", city: "Cape Town 7925", country: "South Africa"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1153,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.63001",GROUP_DESC:"63001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1153,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.63001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.63001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1153,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.63001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2153 ,protection_group_id: -1153, protection_element_id:-1153], primaryKey: false);
      insert('organizations', [ id: 101139, nci_institute_code: "63002", name: "Johannesburg General Hospital", city: "Johannesburg", country: "South Africa"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1154,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.63002",GROUP_DESC:"63002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1154,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.63002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.63002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1154,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.63002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2154 ,protection_group_id: -1154, protection_element_id:-1154], primaryKey: false);
      insert('organizations', [ id: 101140, nci_institute_code: "63003", name: "University  of Witwatersrand", city: "Wits 2050", country: "South Africa"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1155,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.63003",GROUP_DESC:"63003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1155,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.63003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.63003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1155,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.63003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2155 ,protection_group_id: -1155, protection_element_id:-1155], primaryKey: false);
      insert('organizations', [ id: 101141, nci_institute_code: "63004", name: "University of Cape Town Leukemia Center", city: "Cape, 7925", state: "S. AFRICA", country: "South Africa"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1156,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.63004",GROUP_DESC:"63004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1156,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.63004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.63004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1156,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.63004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2156 ,protection_group_id: -1156, protection_element_id:-1156], primaryKey: false);
      insert('organizations', [ id: 101142, nci_institute_code: "63005", name: "Johannesburg Hosp.", city: "Johannesburg 2000", country: "South Africa"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1157,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.63005",GROUP_DESC:"63005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1157,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.63005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.63005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1157,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.63005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2157 ,protection_group_id: -1157, protection_element_id:-1157], primaryKey: false);
      insert('organizations', [ id: 101143, nci_institute_code: "63006", name: "University of Stellenbosch", city: "Matieland 7602", country: "South Africa"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1158,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.63006",GROUP_DESC:"63006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1158,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.63006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.63006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1158,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.63006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2158 ,protection_group_id: -1158, protection_element_id:-1158], primaryKey: false);
      insert('organizations', [ id: 101144, nci_institute_code: "63007", name: "Karl Bremer Hospital", city: "Bellville 7531", country: "South Africa"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1159,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.63007",GROUP_DESC:"63007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1159,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.63007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.63007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1159,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.63007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2159 ,protection_group_id: -1159, protection_element_id:-1159], primaryKey: false);
      insert('organizations', [ id: 101145, nci_institute_code: "63008", name: "Tygerberg Hospital", city: "Cape Town", country: "South Africa"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1160,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.63008",GROUP_DESC:"63008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1160,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.63008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.63008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1160,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.63008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2160 ,protection_group_id: -1160, protection_element_id:-1160], primaryKey: false);
      insert('organizations', [ id: 101146, nci_institute_code: "63009", name: "H. F. Verwoerd Hospital", city: "Pretoria", country: "South Africa"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1161,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.63009",GROUP_DESC:"63009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1161,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.63009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.63009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1161,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.63009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2161 ,protection_group_id: -1161, protection_element_id:-1161], primaryKey: false);
      insert('organizations', [ id: 101147, nci_institute_code: "63010", name: "University Of Pretoria", city: "Pretoria", country: "South Africa"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1162,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.63010",GROUP_DESC:"63010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1162,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.63010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.63010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1162,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.63010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2162 ,protection_group_id: -1162, protection_element_id:-1162], primaryKey: false);
    }

    void m6() {
        // all6 (25 terms)
      insert('organizations', [ id: 101148, nci_institute_code: "63011", name: "University of Orange Free State", city: "Bloemfontein 9300", country: "South Africa"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1163,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.63011",GROUP_DESC:"63011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1163,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.63011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.63011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1163,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.63011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2163 ,protection_group_id: -1163, protection_element_id:-1163], primaryKey: false);
      insert('organizations', [ id: 101149, nci_institute_code: "63013", name: "Wynberg Hospital", city: "Wynberg 7800", country: "South Africa"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1164,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.63013",GROUP_DESC:"63013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1164,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.63013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.63013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1164,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.63013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2164 ,protection_group_id: -1164, protection_element_id:-1164], primaryKey: false);
      insert('organizations', [ id: 101150, nci_institute_code: "63014", name: "The Constantiaberg Medical Clinic", city: "Plumstead", state: "Cape Town", country: "South Africa"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1165,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.63014",GROUP_DESC:"63014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1165,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.63014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.63014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1165,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.63014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2165 ,protection_group_id: -1165, protection_element_id:-1165], primaryKey: false);
      insert('organizations', [ id: 101151, nci_institute_code: "63015", name: "Medical Oncology Centre of Rosebank", city: "Johannesburg", country: "South Africa"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1166,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.63015",GROUP_DESC:"63015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1166,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.63015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.63015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1166,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.63015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2166 ,protection_group_id: -1166, protection_element_id:-1166], primaryKey: false);
      insert('organizations', [ id: 101152, nci_institute_code: "63016", name: "Lem Hospital", city: "Pretoria", country: "South Africa"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1167,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.63016",GROUP_DESC:"63016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1167,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.63016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.63016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1167,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.63016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2167 ,protection_group_id: -1167, protection_element_id:-1167], primaryKey: false);
      insert('organizations', [ id: 101153, nci_institute_code: "63017", name: "Sandton Oncology Centre", city: "Sandton", country: "South Africa"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1168,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.63017",GROUP_DESC:"63017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1168,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.63017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.63017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1168,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.63017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2168 ,protection_group_id: -1168, protection_element_id:-1168], primaryKey: false);
      insert('organizations', [ id: 101154, nci_institute_code: "63018", name: "Mary Potter Oncology Centre", city: "Brooklyn Square", country: "South Africa"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1169,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.63018",GROUP_DESC:"63018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1169,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.63018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.63018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1169,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.63018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2169 ,protection_group_id: -1169, protection_element_id:-1169], primaryKey: false);
      insert('organizations', [ id: 101155, nci_institute_code: "64001", name: "Royal Hospital/Sick Children", city: "Edinburgh", state: "Midlothian", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1170,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.64001",GROUP_DESC:"64001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1170,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.64001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.64001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1170,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.64001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2170 ,protection_group_id: -1170, protection_element_id:-1170], primaryKey: false);
      insert('organizations', [ id: 101156, nci_institute_code: "64002", name: "Eastern Gen. Hosp.", city: "Edinburgh", state: "Midlothian", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1171,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.64002",GROUP_DESC:"64002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1171,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.64002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.64002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1171,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.64002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2171 ,protection_group_id: -1171, protection_element_id:-1171], primaryKey: false);
      insert('organizations', [ id: 101157, nci_institute_code: "64003", name: "University of Aberdeen", city: "Aberdeen", state: "Aberdeenshire", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1172,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.64003",GROUP_DESC:"64003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1172,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.64003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.64003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1172,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.64003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2172 ,protection_group_id: -1172, protection_element_id:-1172], primaryKey: false);
      insert('organizations', [ id: 101158, nci_institute_code: "64004", name: "University of Glasgow", city: "Scotland", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1173,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.64004",GROUP_DESC:"64004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1173,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.64004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.64004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1173,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.64004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2173 ,protection_group_id: -1173, protection_element_id:-1173], primaryKey: false);
      insert('organizations', [ id: 101159, nci_institute_code: "64005", name: "City Hosp.", city: "Edinburgh", state: "Scotland", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1174,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.64005",GROUP_DESC:"64005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1174,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.64005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.64005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1174,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.64005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2174 ,protection_group_id: -1174, protection_element_id:-1174], primaryKey: false);
      insert('organizations', [ id: 101160, nci_institute_code: "64006", name: "Western General Hospital", city: "Edinburgh", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1175,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.64006",GROUP_DESC:"64006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1175,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.64006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.64006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1175,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.64006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2175 ,protection_group_id: -1175, protection_element_id:-1175], primaryKey: false);
      insert('organizations', [ id: 101161, nci_institute_code: "64007", name: "Southern General Hospital", city: "Larkhall", state: "Lanarkshire", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1176,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.64007",GROUP_DESC:"64007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1176,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.64007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.64007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1176,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.64007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2176 ,protection_group_id: -1176, protection_element_id:-1176], primaryKey: false);
      insert('organizations', [ id: 101162, nci_institute_code: "64009", name: "Aberdeen Royal Infirmary", city: "Aberdeen", state: "Aberdeenshire", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1177,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.64009",GROUP_DESC:"64009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1177,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.64009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.64009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1177,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.64009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2177 ,protection_group_id: -1177, protection_element_id:-1177], primaryKey: false);
      insert('organizations', [ id: 101163, nci_institute_code: "64010", name: "Bupa Murrayfield Hospital", city: "Edinburgh", state: "Midlothian", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1178,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.64010",GROUP_DESC:"64010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1178,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.64010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.64010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1178,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.64010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2178 ,protection_group_id: -1178, protection_element_id:-1178], primaryKey: false);
      insert('organizations', [ id: 101164, nci_institute_code: "64011", name: "Glasgow Royal Infirmary", city: "Glasgow", state: "Scotland", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1179,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.64011",GROUP_DESC:"64011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1179,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.64011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.64011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1179,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.64011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2179 ,protection_group_id: -1179, protection_element_id:-1179], primaryKey: false);
      insert('organizations', [ id: 101165, nci_institute_code: "64012", name: "Victoria Hosp.", city: "Edinburgh", state: "Scotland", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1180,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.64012",GROUP_DESC:"64012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1180,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.64012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.64012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1180,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.64012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2180 ,protection_group_id: -1180, protection_element_id:-1180], primaryKey: false);
      insert('organizations', [ id: 101166, nci_institute_code: "64013", name: "Stophill General Hospital", city: "Glasgow", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1181,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.64013",GROUP_DESC:"64013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1181,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.64013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.64013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1181,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.64013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2181 ,protection_group_id: -1181, protection_element_id:-1181], primaryKey: false);
      insert('organizations', [ id: 101167, nci_institute_code: "64014", name: "Monklands District Gen. Hosp.", city: "Airdrie", state: "Lanarkshire", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1182,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.64014",GROUP_DESC:"64014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1182,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.64014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.64014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1182,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.64014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2182 ,protection_group_id: -1182, protection_element_id:-1182], primaryKey: false);
      insert('organizations', [ id: 101168, nci_institute_code: "64016", name: "Ninewells Hosp & Medical Sch", city: "Dundee", state: "Angus", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1183,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.64016",GROUP_DESC:"64016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1183,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.64016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.64016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1183,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.64016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2183 ,protection_group_id: -1183, protection_element_id:-1183], primaryKey: false);
      insert('organizations', [ id: 101169, nci_institute_code: "64017", name: "Beatson Oncology Center", city: "Glasgow", state: "SCOTLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1184,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.64017",GROUP_DESC:"64017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1184,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.64017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.64017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1184,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.64017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2184 ,protection_group_id: -1184, protection_element_id:-1184], primaryKey: false);
      insert('organizations', [ id: 101170, nci_institute_code: "64018", name: "University of Strathclyde", city: "Glasgow", state: "Scotland", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1185,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.64018",GROUP_DESC:"64018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1185,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.64018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.64018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1185,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.64018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2185 ,protection_group_id: -1185, protection_element_id:-1185], primaryKey: false);
      insert('organizations', [ id: 101171, nci_institute_code: "64019", name: "Crosshouse Hospital", city: "Kilmarnock", state: "AYRSHIRE", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1186,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.64019",GROUP_DESC:"64019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1186,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.64019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.64019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1186,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.64019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2186 ,protection_group_id: -1186, protection_element_id:-1186], primaryKey: false);
      insert('organizations', [ id: 101172, nci_institute_code: "64020", name: "Western Infirmary", city: "Glasgow", state: "SCOTLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1187,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.64020",GROUP_DESC:"64020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1187,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.64020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.64020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1187,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.64020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2187 ,protection_group_id: -1187, protection_element_id:-1187], primaryKey: false);
    }

    void m7() {
        // all7 (25 terms)
      insert('organizations', [ id: 101173, nci_institute_code: "64021", name: "The Victoria Infirmary", city: "Glasgow", state: "Scotland", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1188,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.64021",GROUP_DESC:"64021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1188,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.64021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.64021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1188,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.64021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2188 ,protection_group_id: -1188, protection_element_id:-1188], primaryKey: false);
      insert('organizations', [ id: 101174, nci_institute_code: "64022", name: "Leighton Hospital", city: "Crewe, Cheshire", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1189,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.64022",GROUP_DESC:"64022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1189,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.64022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.64022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1189,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.64022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2189 ,protection_group_id: -1189, protection_element_id:-1189], primaryKey: false);
      insert('organizations', [ id: 101175, nci_institute_code: "65001", name: "Ciudad Universitaria", city: "Madrid", state: "SPAIN", country: "Spain"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1190,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65001",GROUP_DESC:"65001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1190,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.65001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.65001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1190,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2190 ,protection_group_id: -1190, protection_element_id:-1190], primaryKey: false);
      insert('organizations', [ id: 101176, nci_institute_code: "65002", name: "Hosp. 1 De Octobre", city: "Madrid-26", country: "Spain"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1191,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65002",GROUP_DESC:"65002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1191,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.65002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.65002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1191,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2191 ,protection_group_id: -1191, protection_element_id:-1191], primaryKey: false);
      insert('organizations', [ id: 101177, nci_institute_code: "65003", name: "Hospital Clinico De Barcelona", city: "Barcelona - 36", country: "Spain"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1192,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65003",GROUP_DESC:"65003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1192,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.65003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.65003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1192,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2192 ,protection_group_id: -1192, protection_element_id:-1192], primaryKey: false);
      insert('organizations', [ id: 101178, nci_institute_code: "65004", name: "Hospital Gen De Asturias", city: "Oviedo", country: "Spain"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1193,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65004",GROUP_DESC:"65004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1193,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.65004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.65004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1193,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2193 ,protection_group_id: -1193, protection_element_id:-1193], primaryKey: false);
      insert('organizations', [ id: 101179, nci_institute_code: "65005", name: "Hospital Cruz Roja", city: "Barcelona 13", country: "Spain"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1194,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65005",GROUP_DESC:"65005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1194,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.65005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.65005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1194,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2194 ,protection_group_id: -1194, protection_element_id:-1194], primaryKey: false);
      insert('organizations', [ id: 101180, nci_institute_code: "65006", name: "Hospital Infantil", city: "Bilbao", country: "Spain"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1195,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65006",GROUP_DESC:"65006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1195,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.65006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.65006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1195,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2195 ,protection_group_id: -1195, protection_element_id:-1195], primaryKey: false);
      insert('organizations', [ id: 101181, nci_institute_code: "65007", name: "Hospital De La Crdz Roja", city: "Barcelona", country: "Spain"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1196,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65007",GROUP_DESC:"65007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1196,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.65007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.65007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1196,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2196 ,protection_group_id: -1196, protection_element_id:-1196], primaryKey: false);
      insert('organizations', [ id: 101182, nci_institute_code: "65008", name: "University of Salamanca", city: "Salamanca", state: "Madrid", country: "Spain"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1197,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65008",GROUP_DESC:"65008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1197,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.65008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.65008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1197,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2197 ,protection_group_id: -1197, protection_element_id:-1197], primaryKey: false);
      insert('organizations', [ id: 101183, nci_institute_code: "65009", name: "Red Cross Hospital", city: "Barcelona", country: "Spain"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1198,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65009",GROUP_DESC:"65009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1198,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.65009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.65009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1198,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2198 ,protection_group_id: -1198, protection_element_id:-1198], primaryKey: false);
      insert('organizations', [ id: 101184, nci_institute_code: "65010", name: "Hospital De Navarra", city: "Pamplona", country: "Spain"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1199,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65010",GROUP_DESC:"65010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1199,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.65010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.65010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1199,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2199 ,protection_group_id: -1199, protection_element_id:-1199], primaryKey: false);
      insert('organizations', [ id: 101185, nci_institute_code: "65011", name: "Hospital Baldecilla", city: "Santander", country: "Spain"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1200,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65011",GROUP_DESC:"65011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1200,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.65011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.65011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1200,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2200 ,protection_group_id: -1200, protection_element_id:-1200], primaryKey: false);
      insert('organizations', [ id: 101186, nci_institute_code: "65012", name: "Clinica Puerta De Hierro", city: "Madrid 28035", country: "Spain"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1201,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65012",GROUP_DESC:"65012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1201,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.65012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.65012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1201,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2201 ,protection_group_id: -1201, protection_element_id:-1201], primaryKey: false);
      insert('organizations', [ id: 101187, nci_institute_code: "65013", name: "Hospital Ntra Sra De Aranzazu", city: "Dnostia Sansebastia", country: "Spain"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1202,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65013",GROUP_DESC:"65013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1202,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.65013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.65013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1202,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2202 ,protection_group_id: -1202, protection_element_id:-1202], primaryKey: false);
      insert('organizations', [ id: 101188, nci_institute_code: "65014", name: "University of La Laguna", city: "Tenerife", country: "Spain"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1203,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65014",GROUP_DESC:"65014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1203,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.65014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.65014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1203,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2203 ,protection_group_id: -1203, protection_element_id:-1203], primaryKey: false);
      insert('organizations', [ id: 101189, nci_institute_code: "65015", name: "Hospital Rio Hortega", city: "Valladolid", country: "Spain"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1204,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65015",GROUP_DESC:"65015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1204,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.65015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.65015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1204,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2204 ,protection_group_id: -1204, protection_element_id:-1204], primaryKey: false);
      insert('organizations', [ id: 101190, nci_institute_code: "65016", name: "Instituto Valencia De Oncologia", city: "Valencia", country: "Spain"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1205,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65016",GROUP_DESC:"65016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1205,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.65016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.65016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1205,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2205 ,protection_group_id: -1205, protection_element_id:-1205], primaryKey: false);
      insert('organizations', [ id: 101191, nci_institute_code: "65017", name: "Hospital De La Santa Creu I Sant Pau", city: "Barcelona", country: "Spain"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1206,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65017",GROUP_DESC:"65017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1206,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.65017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.65017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1206,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2206 ,protection_group_id: -1206, protection_element_id:-1206], primaryKey: false);
      insert('organizations', [ id: 101192, nci_institute_code: "65018", name: "Hospital Insualr De Gran Canara", city: "Las Palmas G.C.", country: "Spain"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1207,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65018",GROUP_DESC:"65018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1207,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.65018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.65018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1207,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2207 ,protection_group_id: -1207, protection_element_id:-1207], primaryKey: false);
      insert('organizations', [ id: 101193, nci_institute_code: "65019", name: "Hospital Univ Virgen de la Arrixaca", city: "El Palmar Mucia", country: "Spain"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1208,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65019",GROUP_DESC:"65019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1208,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.65019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.65019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1208,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2208 ,protection_group_id: -1208, protection_element_id:-1208], primaryKey: false);
      insert('organizations', [ id: 101194, nci_institute_code: "65020", name: "Centro Nacional de Investigaciones Oncologicas", city: "Madrid", country: "Spain"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1209,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65020",GROUP_DESC:"65020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1209,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.65020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.65020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1209,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2209 ,protection_group_id: -1209, protection_element_id:-1209], primaryKey: false);
      insert('organizations', [ id: 101195, nci_institute_code: "65021", name: "Complejo Hospitalario Universitario de Albacete", city: "Albacete", country: "Spain"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1210,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65021",GROUP_DESC:"65021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1210,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.65021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.65021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1210,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2210 ,protection_group_id: -1210, protection_element_id:-1210], primaryKey: false);
      insert('organizations', [ id: 101196, nci_institute_code: "65022", name: "Complexo Hospitalario de Orense", city: "Ourense", country: "Spain"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1211,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65022",GROUP_DESC:"65022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1211,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.65022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.65022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1211,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2211 ,protection_group_id: -1211, protection_element_id:-1211], primaryKey: false);
      insert('organizations', [ id: 101197, nci_institute_code: "65023", name: "Institut de Recerca Biomedica", city: "Barcelona", country: "Spain"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1212,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65023",GROUP_DESC:"65023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1212,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.65023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.65023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1212,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2212 ,protection_group_id: -1212, protection_element_id:-1212], primaryKey: false);
    }

    void m8() {
        // all8 (25 terms)
      insert('organizations', [ id: 101198, nci_institute_code: "65024", name: "M D Anderson International Spain", city: "Madrid", state: "Madrid", country: "Spain"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1213,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65024",GROUP_DESC:"65024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1213,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.65024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.65024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1213,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2213 ,protection_group_id: -1213, protection_element_id:-1213], primaryKey: false);
      insert('organizations', [ id: 101199, nci_institute_code: "65025", name: "Institut Universitari d'Investigacio en Ciencies de la Salut", city: "Illes Balears", country: "Spain"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1214,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65025",GROUP_DESC:"65025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1214,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.65025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.65025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1214,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.65025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2214 ,protection_group_id: -1214, protection_element_id:-1214], primaryKey: false);
      insert('organizations', [ id: 101200, nci_institute_code: "66001", name: "Sahlgrenska University Hospital", city: "Goteborg", country: "Sweden"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1215,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.66001",GROUP_DESC:"66001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1215,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.66001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.66001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1215,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.66001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2215 ,protection_group_id: -1215, protection_element_id:-1215], primaryKey: false);
      insert('organizations', [ id: 101201, nci_institute_code: "66002", name: "Karolinska Institutet", city: "Stockholm", country: "Sweden"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1216,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.66002",GROUP_DESC:"66002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1216,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.66002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.66002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1216,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.66002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2216 ,protection_group_id: -1216, protection_element_id:-1216], primaryKey: false);
      insert('organizations', [ id: 101202, nci_institute_code: "66003", name: "Universtiy Hospital", city: "S-750 14 Uppsala", country: "Sweden"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1217,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.66003",GROUP_DESC:"66003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1217,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.66003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.66003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1217,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.66003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2217 ,protection_group_id: -1217, protection_element_id:-1217], primaryKey: false);
      insert('organizations', [ id: 101203, nci_institute_code: "66004", name: "Univ. Hosp.", city: "Umea", country: "Sweden"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1218,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.66004",GROUP_DESC:"66004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1218,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.66004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.66004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1218,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.66004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2218 ,protection_group_id: -1218, protection_element_id:-1218], primaryKey: false);
      insert('organizations', [ id: 101204, nci_institute_code: "66005", name: "University of Lund", city: "Lund", country: "Sweden"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1219,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.66005",GROUP_DESC:"66005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1219,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.66005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.66005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1219,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.66005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2219 ,protection_group_id: -1219, protection_element_id:-1219], primaryKey: false);
      insert('organizations', [ id: 101205, nci_institute_code: "66006", name: "Orebro Regional Hospital", city: "Orebro Pa 70185", country: "Sweden"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1220,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.66006",GROUP_DESC:"66006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1220,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.66006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.66006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1220,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.66006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2220 ,protection_group_id: -1220, protection_element_id:-1220], primaryKey: false);
      insert('organizations', [ id: 101206, nci_institute_code: "66007", name: "Uppsala University Hospital", city: "Uppsala", country: "Sweden"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1221,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.66007",GROUP_DESC:"66007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1221,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.66007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.66007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1221,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.66007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2221 ,protection_group_id: -1221, protection_element_id:-1221], primaryKey: false);
      insert('organizations', [ id: 101207, nci_institute_code: "66008", name: "Ostra Hospital", city: "Goteberg, S-41685", country: "Sweden"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1222,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.66008",GROUP_DESC:"66008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1222,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.66008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.66008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1222,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.66008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2222 ,protection_group_id: -1222, protection_element_id:-1222], primaryKey: false);
      insert('organizations', [ id: 101208, nci_institute_code: "66009", name: "Central Hospital", city: "S-651 85 Karlstad", country: "Sweden"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1223,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.66009",GROUP_DESC:"66009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1223,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.66009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.66009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1223,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.66009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2223 ,protection_group_id: -1223, protection_element_id:-1223], primaryKey: false);
      insert('organizations', [ id: 101209, nci_institute_code: "66010", name: "Falu Hospital", city: "Falun", country: "Sweden"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1224,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.66010",GROUP_DESC:"66010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1224,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.66010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.66010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1224,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.66010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2224 ,protection_group_id: -1224, protection_element_id:-1224], primaryKey: false);
      insert('organizations', [ id: 101210, nci_institute_code: "66011", name: "Linkoping University Hospital", city: "Linkoping", country: "Sweden"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1225,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.66011",GROUP_DESC:"66011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1225,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.66011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.66011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1225,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.66011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2225 ,protection_group_id: -1225, protection_element_id:-1225], primaryKey: false);
      insert('organizations', [ id: 101211, nci_institute_code: "66012", name: "Orebro Medical Center Hospital", city: "Orebro", country: "Sweden"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1226,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.66012",GROUP_DESC:"66012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1226,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.66012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.66012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1226,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.66012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2226 ,protection_group_id: -1226, protection_element_id:-1226], primaryKey: false);
      insert('organizations', [ id: 101212, nci_institute_code: "66013", name: "Akademiska Sjuhuset", city: "Uppsala", country: "Sweden"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1227,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.66013",GROUP_DESC:"66013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1227,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.66013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.66013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1227,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.66013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2227 ,protection_group_id: -1227, protection_element_id:-1227], primaryKey: false);
      insert('organizations', [ id: 101213, nci_institute_code: "66014", name: "Umea Universitet", city: "Umea", country: "Sweden"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1228,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.66014",GROUP_DESC:"66014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1228,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.66014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.66014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1228,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.66014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2228 ,protection_group_id: -1228, protection_element_id:-1228], primaryKey: false);
      insert('organizations', [ id: 101214, nci_institute_code: "66015", name: "Karolinska University Hospital", city: "Stockholm", country: "Sweden"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1229,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.66015",GROUP_DESC:"66015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1229,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.66015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.66015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1229,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.66015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2229 ,protection_group_id: -1229, protection_element_id:-1229], primaryKey: false);
      insert('organizations', [ id: 101215, nci_institute_code: "67001", name: "Inselspital", city: "Bern", country: "Switzerland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1230,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67001",GROUP_DESC:"67001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1230,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.67001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.67001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1230,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2230 ,protection_group_id: -1230, protection_element_id:-1230], primaryKey: false);
      insert('organizations', [ id: 101216, nci_institute_code: "67002", name: "Tiefenauspital", city: "Bern", country: "Switzerland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1231,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67002",GROUP_DESC:"67002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1231,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.67002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.67002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1231,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2231 ,protection_group_id: -1231, protection_element_id:-1231], primaryKey: false);
      insert('organizations', [ id: 101217, nci_institute_code: "67003", name: "Hospital Nestle", city: "1 11 Lausanne", country: "Switzerland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1232,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67003",GROUP_DESC:"67003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1232,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.67003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.67003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1232,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2232 ,protection_group_id: -1232, protection_element_id:-1232], primaryKey: false);
      insert('organizations', [ id: 101218, nci_institute_code: "67004", name: "Kinderspital Saint. Gallen", city: "9006 St. Gallen", country: "Switzerland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1233,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67004",GROUP_DESC:"67004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1233,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.67004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.67004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1233,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2233 ,protection_group_id: -1233, protection_element_id:-1233], primaryKey: false);
      insert('organizations', [ id: 101219, nci_institute_code: "67005", name: "University Clinic, Inselspital", city: "Ch-3010 Bern", country: "Switzerland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1234,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67005",GROUP_DESC:"67005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1234,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.67005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.67005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1234,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2234 ,protection_group_id: -1234, protection_element_id:-1234], primaryKey: false);
      insert('organizations', [ id: 101220, nci_institute_code: "67006", name: "Swiss Pediatric Oncology Group - Geneva", city: "1211,Geneva,4", country: "Switzerland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1235,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67006",GROUP_DESC:"67006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1235,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.67006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.67006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1235,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2235 ,protection_group_id: -1235, protection_element_id:-1235], primaryKey: false);
      insert('organizations', [ id: 101221, nci_institute_code: "67007", name: "Univ. Childrens Hospital", city: "8032 Zurich", country: "Switzerland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1236,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67007",GROUP_DESC:"67007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1236,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.67007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.67007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1236,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2236 ,protection_group_id: -1236, protection_element_id:-1236], primaryKey: false);
      insert('organizations', [ id: 101222, nci_institute_code: "67008", name: "Children's Hospital University of Bern", city: "Bern", country: "Switzerland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1237,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67008",GROUP_DESC:"67008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1237,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.67008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.67008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1237,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2237 ,protection_group_id: -1237, protection_element_id:-1237], primaryKey: false);
    }

    void m9() {
        // all9 (25 terms)
      insert('organizations', [ id: 101223, nci_institute_code: "67009", name: "Swiss Pediatric Oncology Group - Bern", city: "Bern", country: "Switzerland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1238,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67009",GROUP_DESC:"67009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1238,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.67009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.67009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1238,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2238 ,protection_group_id: -1238, protection_element_id:-1238], primaryKey: false);
      insert('organizations', [ id: 101224, nci_institute_code: "67010", name: "Hospital Cantonal Univ.", city: "Geneve 4", country: "Switzerland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1239,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67010",GROUP_DESC:"67010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1239,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.67010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.67010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1239,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2239 ,protection_group_id: -1239, protection_element_id:-1239], primaryKey: false);
      insert('organizations', [ id: 101225, nci_institute_code: "67011", name: "Burgerspital", city: "Solothurn", country: "Switzerland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1240,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67011",GROUP_DESC:"67011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1240,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.67011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.67011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1240,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2240 ,protection_group_id: -1240, protection_element_id:-1240], primaryKey: false);
      insert('organizations', [ id: 101226, nci_institute_code: "67012", name: "Univ. Hosp.", city: "Geneva Ch-1211", country: "Switzerland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1241,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67012",GROUP_DESC:"67012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1241,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.67012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.67012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1241,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2241 ,protection_group_id: -1241, protection_element_id:-1241], primaryKey: false);
      insert('organizations', [ id: 101227, nci_institute_code: "67013", name: "Policlinique Medicale Univ", city: "Lausanne", country: "Switzerland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1242,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67013",GROUP_DESC:"67013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1242,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.67013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.67013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1242,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2242 ,protection_group_id: -1242, protection_element_id:-1242], primaryKey: false);
      insert('organizations', [ id: 101228, nci_institute_code: "67014", name: "Medecine Interne Fmh", city: "1009 Pully/Louisanne", country: "Switzerland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1243,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67014",GROUP_DESC:"67014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1243,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.67014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.67014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1243,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2243 ,protection_group_id: -1243, protection_element_id:-1243], primaryKey: false);
      insert('organizations', [ id: 101229, nci_institute_code: "67015", name: "Univ. Hosp.", city: "Ch-8091 Zurich", country: "Switzerland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1244,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67015",GROUP_DESC:"67015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1244,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.67015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.67015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1244,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2244 ,protection_group_id: -1244, protection_element_id:-1244], primaryKey: false);
      insert('organizations', [ id: 101230, nci_institute_code: "67016", name: "Kantonsspital University Hospital", city: "Ch-4031 Basel", country: "Switzerland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1245,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67016",GROUP_DESC:"67016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1245,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.67016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.67016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1245,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2245 ,protection_group_id: -1245, protection_element_id:-1245], primaryKey: false);
      insert('organizations', [ id: 101231, nci_institute_code: "67017", name: "Swiss Pediatric Oncology Group - Lausanne", city: "Lausanne", country: "Switzerland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1246,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67017",GROUP_DESC:"67017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1246,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.67017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.67017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1246,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2246 ,protection_group_id: -1246, protection_element_id:-1246], primaryKey: false);
      insert('organizations', [ id: 101232, nci_institute_code: "67018", name: "Kantonsspital", city: "Ch-9007 St. Gallen", country: "Switzerland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1247,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67018",GROUP_DESC:"67018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1247,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.67018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.67018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1247,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2247 ,protection_group_id: -1247, protection_element_id:-1247], primaryKey: false);
      insert('organizations', [ id: 101233, nci_institute_code: "67019", name: "Innere Medizin Fmh", city: "4127 Birsfelden", country: "Switzerland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1248,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67019",GROUP_DESC:"67019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1248,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.67019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.67019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1248,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2248 ,protection_group_id: -1248, protection_element_id:-1248], primaryKey: false);
      insert('organizations', [ id: 101234, nci_institute_code: "67020", name: "Swiss Group for Clinical Cancer Research", city: "3008 Bern", country: "Switzerland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1249,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67020",GROUP_DESC:"67020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1249,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.67020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.67020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1249,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2249 ,protection_group_id: -1249, protection_element_id:-1249], primaryKey: false);
      insert('organizations', [ id: 101235, nci_institute_code: "67021", name: "Division of Oncology", city: "6500 Bellinzona", country: "Switzerland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1250,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67021",GROUP_DESC:"67021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1250,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.67021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.67021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1250,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2250 ,protection_group_id: -1250, protection_element_id:-1250], primaryKey: false);
      insert('organizations', [ id: 101236, nci_institute_code: "67022", name: "Zurich University Hospital", city: "Zurich", country: "Switzerland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1251,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67022",GROUP_DESC:"67022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1251,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.67022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.67022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1251,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2251 ,protection_group_id: -1251, protection_element_id:-1251], primaryKey: false);
      insert('organizations', [ id: 101237, nci_institute_code: "67023", name: "Hopitaux Universitaires de Geneve", city: "Geneva", country: "Switzerland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1252,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67023",GROUP_DESC:"67023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1252,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.67023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.67023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1252,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2252 ,protection_group_id: -1252, protection_element_id:-1252], primaryKey: false);
      insert('organizations', [ id: 101238, nci_institute_code: "67024", name: "University Children's Hospital-Geneva", city: "Geneva", country: "Switzerland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1253,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67024",GROUP_DESC:"67024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1253,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.67024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.67024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1253,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2253 ,protection_group_id: -1253, protection_element_id:-1253], primaryKey: false);
      insert('organizations', [ id: 101239, nci_institute_code: "67025", name: "University Hospital of Zurich", city: "8091 Zurich", country: "Switzerland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1254,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67025",GROUP_DESC:"67025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1254,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.67025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.67025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1254,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2254 ,protection_group_id: -1254, protection_element_id:-1254], primaryKey: false);
      insert('organizations', [ id: 101240, nci_institute_code: "67026", name: "University of Zurich", city: "Z\374rich", country: "Switzerland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1255,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67026",GROUP_DESC:"67026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1255,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.67026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.67026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1255,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2255 ,protection_group_id: -1255, protection_element_id:-1255], primaryKey: false);
      insert('organizations', [ id: 101241, nci_institute_code: "67027", name: "University Hospital of Lausanne", city: "Lausanne", country: "Switzerland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1256,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67027",GROUP_DESC:"67027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1256,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.67027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.67027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1256,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2256 ,protection_group_id: -1256, protection_element_id:-1256], primaryKey: false);
      insert('organizations', [ id: 101242, nci_institute_code: "67028", name: "Hopital des Enfants", city: "Geneva 14", country: "Switzerland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1257,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67028",GROUP_DESC:"67028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1257,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.67028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.67028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1257,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2257 ,protection_group_id: -1257, protection_element_id:-1257], primaryKey: false);
      insert('organizations', [ id: 101243, nci_institute_code: "67029", name: "Kantonsspital Aurau", city: "Aarau", country: "Switzerland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1258,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67029",GROUP_DESC:"67029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1258,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.67029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.67029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1258,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2258 ,protection_group_id: -1258, protection_element_id:-1258], primaryKey: false);
      insert('organizations', [ id: 101244, nci_institute_code: "67030", name: "Oncology Institute of Southern Switzerland", city: "Bellinzona", country: "Switzerland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1259,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67030",GROUP_DESC:"67030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1259,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.67030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.67030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1259,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2259 ,protection_group_id: -1259, protection_element_id:-1259], primaryKey: false);
      insert('organizations', [ id: 101245, nci_institute_code: "67031", name: "Swiss Federal Institute of Technology Zurich", city: "Zurich", country: "Switzerland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1260,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67031",GROUP_DESC:"67031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1260,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.67031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.67031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1260,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2260 ,protection_group_id: -1260, protection_element_id:-1260], primaryKey: false);
      insert('organizations', [ id: 101246, nci_institute_code: "67032", name: "Istituto Oncologico della Svizzera Italiana", city: "Lugano", country: "Switzerland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1261,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67032",GROUP_DESC:"67032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1261,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.67032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.67032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1261,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2261 ,protection_group_id: -1261, protection_element_id:-1261], primaryKey: false);
      insert('organizations', [ id: 101247, nci_institute_code: "67033", name: "Centre Pluridisciplinaire d'Oncologie", city: "Lausanne", country: "Switzerland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1262,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67033",GROUP_DESC:"67033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1262,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.67033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.67033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1262,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2262 ,protection_group_id: -1262, protection_element_id:-1262], primaryKey: false);
    }

    void m10() {
        // all10 (25 terms)
      insert('organizations', [ id: 101248, nci_institute_code: "67034", name: "Spital Simmental-Thun-Saanenland AG", city: "Thun", country: "Switzerland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1263,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67034",GROUP_DESC:"67034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1263,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.67034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.67034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1263,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2263 ,protection_group_id: -1263, protection_element_id:-1263], primaryKey: false);
      insert('organizations', [ id: 101249, nci_institute_code: "68001", name: "Ramathibodi Hospital", city: "Banghkok, J4", country: "Thailand"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1264,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.68001",GROUP_DESC:"68001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1264,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.68001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.68001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1264,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.68001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2264 ,protection_group_id: -1264, protection_element_id:-1264], primaryKey: false);
      insert('organizations', [ id: 101250, nci_institute_code: "68002", name: "Mccormick Hospital", city: "Chiang Mai", country: "Thailand"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1265,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.68002",GROUP_DESC:"68002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1265,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.68002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.68002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1265,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.68002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2265 ,protection_group_id: -1265, protection_element_id:-1265], primaryKey: false);
      insert('organizations', [ id: 101251, nci_institute_code: "68003", name: "Bumrungrad Hospital", city: "Bangkok", country: "Thailand"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1266,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.68003",GROUP_DESC:"68003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1266,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.68003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.68003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1266,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.68003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2266 ,protection_group_id: -1266, protection_element_id:-1266], primaryKey: false);
      insert('organizations', [ id: 101252, nci_institute_code: "69001", name: "Hacettepe Tip Fakultesi", city: "Ankara", country: "Turkey"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1267,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.69001",GROUP_DESC:"69001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1267,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.69001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.69001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1267,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.69001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2267 ,protection_group_id: -1267, protection_element_id:-1267], primaryKey: false);
      insert('organizations', [ id: 101253, nci_institute_code: "69002", name: "Medical School/Ankara Univ", city: "Cebeci, Ankara", country: "Turkey"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1268,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.69002",GROUP_DESC:"69002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1268,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.69002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.69002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1268,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.69002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2268 ,protection_group_id: -1268, protection_element_id:-1268], primaryKey: false);
      insert('organizations', [ id: 101254, nci_institute_code: "69003", name: "Istanbul University", city: "Istanbul", country: "Turkey"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1269,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.69003",GROUP_DESC:"69003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1269,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.69003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.69003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1269,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.69003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2269 ,protection_group_id: -1269, protection_element_id:-1269], primaryKey: false);
      insert('organizations', [ id: 101255, nci_institute_code: "69005", name: "Ankara Univ/Medical School", city: "Ankara", country: "Turkey"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1270,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.69005",GROUP_DESC:"69005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1270,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.69005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.69005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1270,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.69005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2270 ,protection_group_id: -1270, protection_element_id:-1270], primaryKey: false);
      insert('organizations', [ id: 101256, nci_institute_code: "71001", name: "Academy of Science of The Union Soviet Socialism Republic", city: "Moscow", state: "RUSSIA", country: "Russian Federation"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1271,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.71001",GROUP_DESC:"71001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1271,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.71001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.71001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1271,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.71001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2271 ,protection_group_id: -1271, protection_element_id:-1271], primaryKey: false);
      insert('organizations', [ id: 101257, nci_institute_code: "71002", name: "USSR Academy of Med Sciences", city: "Moscow, M-478", state: "RUSSIA", country: "Russian Federation"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1272,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.71002",GROUP_DESC:"71002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1272,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.71002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.71002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1272,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.71002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2272 ,protection_group_id: -1272, protection_element_id:-1272], primaryKey: false);
      insert('organizations', [ id: 101258, nci_institute_code: "71003", name: "National Research Center for Hematology", city: "Moscow", state: "RUSSIA", country: "Russian Federation"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1273,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.71003",GROUP_DESC:"71003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1273,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.71003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.71003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1273,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.71003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2273 ,protection_group_id: -1273, protection_element_id:-1273], primaryKey: false);
      insert('organizations', [ id: 101259, nci_institute_code: "71004", name: "Inst Experiment & Clinical Onc", city: "Moscow, M-478", state: "RUSSIA", country: "Russian Federation"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1274,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.71004",GROUP_DESC:"71004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1274,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.71004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.71004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1274,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.71004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2274 ,protection_group_id: -1274, protection_element_id:-1274], primaryKey: false);
      insert('organizations', [ id: 101260, nci_institute_code: "71006", name: "Kiev Scientific Res. Institute", city: "Ukraine", state: "RUSSIA", country: "Russian Federation"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1275,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.71006",GROUP_DESC:"71006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1275,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.71006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.71006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1275,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.71006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2275 ,protection_group_id: -1275, protection_element_id:-1275], primaryKey: false);
      insert('organizations', [ id: 101261, nci_institute_code: "71007", name: "USSR Academy Medical Sci-All-Union Cancer Research Center", city: "Moscow", state: "RUSSIA", country: "Russian Federation"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1276,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.71007",GROUP_DESC:"71007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1276,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.71007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.71007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1276,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.71007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2276 ,protection_group_id: -1276, protection_element_id:-1276], primaryKey: false);
      insert('organizations', [ id: 101262, nci_institute_code: "71008", name: "Zhordania Institution of Human Repro", city: "Toilissi, Georgia", state: "RUSSIA", country: "Russian Federation"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1277,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.71008",GROUP_DESC:"71008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1277,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.71008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.71008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1277,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.71008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2277 ,protection_group_id: -1277, protection_element_id:-1277], primaryKey: false);
      insert('organizations', [ id: 101263, nci_institute_code: "72001", name: "Hosp. De Clinicas Planta Baja", city: "Montevideo", country: "Uruguay"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1278,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.72001",GROUP_DESC:"72001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1278,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.72001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.72001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1278,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.72001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2278 ,protection_group_id: -1278, protection_element_id:-1278], primaryKey: false);
      insert('organizations', [ id: 101264, nci_institute_code: "72002", name: "C-O Oficiana Sanitaria Panamericana", city: "Montevideo", country: "Uruguay"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1279,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.72002",GROUP_DESC:"72002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1279,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.72002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.72002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1279,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.72002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2279 ,protection_group_id: -1279, protection_element_id:-1279], primaryKey: false);
      insert('organizations', [ id: 101265, nci_institute_code: "72004", name: "Hospital Delas Fuerzas Armadas", city: "Montevideo", country: "Uruguay"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1280,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.72004",GROUP_DESC:"72004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1280,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.72004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.72004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1280,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.72004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2280 ,protection_group_id: -1280, protection_element_id:-1280], primaryKey: false);
      insert('organizations', [ id: 101266, nci_institute_code: "73001", name: "Hospital Universitario", city: "Caracas", country: "Venezuela"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1281,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.73001",GROUP_DESC:"73001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1281,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.73001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.73001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1281,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.73001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2281 ,protection_group_id: -1281, protection_element_id:-1281], primaryKey: false);
      insert('organizations', [ id: 101267, nci_institute_code: "73002", name: "Unidad Hematologia Oncologia Medica", city: "Caracas 1080-A", country: "Venezuela"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1282,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.73002",GROUP_DESC:"73002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1282,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.73002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.73002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1282,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.73002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2282 ,protection_group_id: -1282, protection_element_id:-1282], primaryKey: false);
      insert('organizations', [ id: 101268, nci_institute_code: "73003", name: "Hosp. Central De Las Fuerzad", city: "Caracas", country: "Venezuela"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1283,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.73003",GROUP_DESC:"73003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1283,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.73003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.73003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1283,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.73003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2283 ,protection_group_id: -1283, protection_element_id:-1283], primaryKey: false);
      insert('organizations', [ id: 101269, nci_institute_code: "73004", name: "Clinica El Avila, 6ta, Transversal", city: "Caracas", country: "Venezuela"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1284,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.73004",GROUP_DESC:"73004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1284,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.73004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.73004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1284,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.73004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2284 ,protection_group_id: -1284, protection_element_id:-1284], primaryKey: false);
      insert('organizations', [ id: 101270, nci_institute_code: "73005", name: "Secretaria Consejo Nacional", city: "Caracas", country: "Venezuela"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1285,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.73005",GROUP_DESC:"73005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1285,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.73005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.73005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1285,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.73005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2285 ,protection_group_id: -1285, protection_element_id:-1285], primaryKey: false);
      insert('organizations', [ id: 101271, nci_institute_code: "73006", name: "Centro Medico De Caracas", city: "Caracas", country: "Venezuela"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1286,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.73006",GROUP_DESC:"73006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1286,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.73006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.73006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1286,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.73006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2286 ,protection_group_id: -1286, protection_element_id:-1286], primaryKey: false);
      insert('organizations', [ id: 101272, nci_institute_code: "73007", name: "Banco Municipal De Sangre Del D.F.", city: "Caracas", country: "Venezuela"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1287,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.73007",GROUP_DESC:"73007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1287,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.73007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.73007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1287,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.73007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2287 ,protection_group_id: -1287, protection_element_id:-1287], primaryKey: false);
    }

    void m11() {
        // all11 (25 terms)
      insert('organizations', [ id: 101273, nci_institute_code: "73008", name: "Comercial Las Vegas Local #6", city: "Valencia", country: "Venezuela"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1288,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.73008",GROUP_DESC:"73008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1288,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.73008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.73008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1288,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.73008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2288 ,protection_group_id: -1288, protection_element_id:-1288], primaryKey: false);
      insert('organizations', [ id: 101274, nci_institute_code: "73009", name: "Edificio Esaval Urb San Blas", city: "Valencia", country: "Venezuela"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1289,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.73009",GROUP_DESC:"73009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1289,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.73009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.73009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1289,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.73009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2289 ,protection_group_id: -1289, protection_element_id:-1289], primaryKey: false);
      insert('organizations', [ id: 101275, nci_institute_code: "76001", name: "Universitat Munchen", city: "8000 Munich 2", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1290,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76001",GROUP_DESC:"76001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1290,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1290,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2290 ,protection_group_id: -1290, protection_element_id:-1290], primaryKey: false);
      insert('organizations', [ id: 101276, nci_institute_code: "76003", name: "Medizinische Univ Klinik", city: "7400 Tubingen 1", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1291,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76003",GROUP_DESC:"76003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1291,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1291,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2291 ,protection_group_id: -1291, protection_element_id:-1291], primaryKey: false);
      insert('organizations', [ id: 101277, nci_institute_code: "76004", name: "Medizinische Klinik/Poliklinik", city: "Tubingen D74", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1292,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76004",GROUP_DESC:"76004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1292,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1292,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2292 ,protection_group_id: -1292, protection_element_id:-1292], primaryKey: false);
      insert('organizations', [ id: 101278, nci_institute_code: "76005", name: "University Kinderklinik", city: "Tubingen, 74", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1293,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76005",GROUP_DESC:"76005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1293,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1293,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2293 ,protection_group_id: -1293, protection_element_id:-1293], primaryKey: false);
      insert('organizations', [ id: 101279, nci_institute_code: "76006", name: "Mannheim Univ Heidelberg", city: "Mannheim", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1294,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76006",GROUP_DESC:"76006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1294,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1294,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2294 ,protection_group_id: -1294, protection_element_id:-1294], primaryKey: false);
      insert('organizations', [ id: 101280, nci_institute_code: "76007", name: "Innere Klinik (Tumorforschung)", city: "D-43, Essen 1", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1295,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76007",GROUP_DESC:"76007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1295,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1295,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2295 ,protection_group_id: -1295, protection_element_id:-1295], primaryKey: false);
      insert('organizations', [ id: 101281, nci_institute_code: "76008", name: "Univ. Hamburg/Kinderkliniks", city: "2000 Hamburg 20", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1296,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76008",GROUP_DESC:"76008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1296,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1296,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2296 ,protection_group_id: -1296, protection_element_id:-1296], primaryKey: false);
      insert('organizations', [ id: 101282, nci_institute_code: "76009", name: "Universitat Tubingen", city: "Tubingen, D-74", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1297,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76009",GROUP_DESC:"76009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1297,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1297,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2297 ,protection_group_id: -1297, protection_element_id:-1297], primaryKey: false);
      insert('organizations', [ id: 101283, nci_institute_code: "76010", name: "Univ-Krankenhaus Ependorf", city: "2 Hamburg 20", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1298,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76010",GROUP_DESC:"76010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1298,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1298,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2298 ,protection_group_id: -1298, protection_element_id:-1298], primaryKey: false);
      insert('organizations', [ id: 101284, nci_institute_code: "76011", name: "Der Universitat Marburg", city: "Marburg", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1299,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76011",GROUP_DESC:"76011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1299,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1299,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2299 ,protection_group_id: -1299, protection_element_id:-1299], primaryKey: false);
      insert('organizations', [ id: 101285, nci_institute_code: "76012", name: "Chirurgische Klinik Der Universitat", city: "8 Munich 15", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1300,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76012",GROUP_DESC:"76012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1300,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1300,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2300 ,protection_group_id: -1300, protection_element_id:-1300], primaryKey: false);
      insert('organizations', [ id: 101286, nci_institute_code: "76013", name: "Medizinische Univ Klinik Koln", city: "5 Koln 41", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1301,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76013",GROUP_DESC:"76013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1301,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1301,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2301 ,protection_group_id: -1301, protection_element_id:-1301], primaryKey: false);
      insert('organizations', [ id: 101287, nci_institute_code: "76014", name: "Universitats Kinderklinik", city: "Mainz", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1302,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76014",GROUP_DESC:"76014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1302,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1302,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2302 ,protection_group_id: -1302, protection_element_id:-1302], primaryKey: false);
      insert('organizations', [ id: 101288, nci_institute_code: "76015", name: "Der Johannes Gutenberg-Universitat", city: "Mainz", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1303,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76015",GROUP_DESC:"76015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1303,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1303,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2303 ,protection_group_id: -1303, protection_element_id:-1303], primaryKey: false);
      insert('organizations', [ id: 101289, nci_institute_code: "76017", name: "Medizinische Universitat", city: "Heidelberg", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1304,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76017",GROUP_DESC:"76017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1304,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1304,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2304 ,protection_group_id: -1304, protection_element_id:-1304], primaryKey: false);
      insert('organizations', [ id: 101290, nci_institute_code: "76018", name: "Gutenburg Universitat", city: "Mainz", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1305,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76018",GROUP_DESC:"76018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1305,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1305,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2305 ,protection_group_id: -1305, protection_element_id:-1305], primaryKey: false);
      insert('organizations', [ id: 101291, nci_institute_code: "76020", name: "University of Munster", city: "Munster", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1306,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76020",GROUP_DESC:"76020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1306,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1306,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2306 ,protection_group_id: -1306, protection_element_id:-1306], primaryKey: false);
      insert('organizations', [ id: 101292, nci_institute_code: "76021", name: "Medizinische Univ Klinik", city: "Gottingen", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1307,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76021",GROUP_DESC:"76021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1307,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1307,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2307 ,protection_group_id: -1307, protection_element_id:-1307], primaryKey: false);
      insert('organizations', [ id: 101293, nci_institute_code: "76022", name: "Saint Elisabeth", city: "Neuwied", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1308,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76022",GROUP_DESC:"76022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1308,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1308,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2308 ,protection_group_id: -1308, protection_element_id:-1308], primaryKey: false);
      insert('organizations', [ id: 101294, nci_institute_code: "76023", name: "Chirugische Universitatsklinik", city: "8520 Erlangen", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1309,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76023",GROUP_DESC:"76023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1309,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1309,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2309 ,protection_group_id: -1309, protection_element_id:-1309], primaryKey: false);
      insert('organizations', [ id: 101295, nci_institute_code: "76024", name: "Augusta-Kranken-Anstalt", city: "D-4630 Bochum", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1310,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76024",GROUP_DESC:"76024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1310,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1310,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2310 ,protection_group_id: -1310, protection_element_id:-1310], primaryKey: false);
      insert('organizations', [ id: 101296, nci_institute_code: "76025", name: "St.-Willehad-Hospital", city: "2940 Wilhelmshaven", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1311,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76025",GROUP_DESC:"76025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1311,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1311,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2311 ,protection_group_id: -1311, protection_element_id:-1311], primaryKey: false);
      insert('organizations', [ id: 101297, nci_institute_code: "76026", name: "Wilheim Anton Hospital", city: "Goche", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1312,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76026",GROUP_DESC:"76026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1312,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1312,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2312 ,protection_group_id: -1312, protection_element_id:-1312], primaryKey: false);
    }

    void m12() {
        // all12 (25 terms)
      insert('organizations', [ id: 101298, nci_institute_code: "76027", name: "Saint Johannes Hospital", city: "4100 Duisburg 11", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1313,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76027",GROUP_DESC:"76027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1313,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1313,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2313 ,protection_group_id: -1313, protection_element_id:-1313], primaryKey: false);
      insert('organizations', [ id: 101299, nci_institute_code: "76028", name: "Universitat Hamburg", city: "2000 Hamburg 20", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1314,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76028",GROUP_DESC:"76028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1314,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1314,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2314 ,protection_group_id: -1314, protection_element_id:-1314], primaryKey: false);
      insert('organizations', [ id: 101300, nci_institute_code: "76029", name: "Medizinische Univ Klinik", city: "5300 Bonn 1", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1315,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76029",GROUP_DESC:"76029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1315,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1315,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2315 ,protection_group_id: -1315, protection_element_id:-1315], primaryKey: false);
      insert('organizations', [ id: 101301, nci_institute_code: "76030", name: "University of Dusseldorf", city: "4000 Dusseldorf 30", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1316,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76030",GROUP_DESC:"76030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1316,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1316,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2316 ,protection_group_id: -1316, protection_element_id:-1316], primaryKey: false);
      insert('organizations', [ id: 101302, nci_institute_code: "76031", name: "University of Dusseldorf", city: "4000 Dusseldorf 1", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1317,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76031",GROUP_DESC:"76031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1317,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1317,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2317 ,protection_group_id: -1317, protection_element_id:-1317], primaryKey: false);
      insert('organizations', [ id: 101303, nci_institute_code: "76032", name: "Saint Joseph Hospital.", city: "Bremerhaven", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1318,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76032",GROUP_DESC:"76032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1318,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1318,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2318 ,protection_group_id: -1318, protection_element_id:-1318], primaryKey: false);
      insert('organizations', [ id: 101304, nci_institute_code: "76033", name: "Freie Univ Berlin/Univ Steglitz", city: "D 1000 Berlin 45", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1319,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76033",GROUP_DESC:"76033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1319,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1319,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2319 ,protection_group_id: -1319, protection_element_id:-1319], primaryKey: false);
      insert('organizations', [ id: 101305, nci_institute_code: "76034", name: "University of Ulm", city: "D-7900 Ulm A.D.Donau", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1320,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76034",GROUP_DESC:"76034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1320,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1320,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2320 ,protection_group_id: -1320, protection_element_id:-1320], primaryKey: false);
      insert('organizations', [ id: 101306, nci_institute_code: "76035", name: "University Hospital", city: "Cologne", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1321,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76035",GROUP_DESC:"76035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1321,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1321,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2321 ,protection_group_id: -1321, protection_element_id:-1321], primaryKey: false);
      insert('organizations', [ id: 101307, nci_institute_code: "76036", name: "Med. Hochschule Hannover", city: "D-3000 Hannover 61", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1322,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76036",GROUP_DESC:"76036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1322,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1322,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2322 ,protection_group_id: -1322, protection_element_id:-1322], primaryKey: false);
      insert('organizations', [ id: 101308, nci_institute_code: "76038", name: "Univtskrankenhaus Eppendorfv", city: "Hamburg 2000", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1323,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76038",GROUP_DESC:"76038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1323,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1323,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2323 ,protection_group_id: -1323, protection_element_id:-1323], primaryKey: false);
      insert('organizations', [ id: 101309, nci_institute_code: "76039", name: "Military Hospital In Ulm", city: "Ulm 7900", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1324,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76039",GROUP_DESC:"76039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1324,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1324,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2324 ,protection_group_id: -1324, protection_element_id:-1324], primaryKey: false);
      insert('organizations', [ id: 101310, nci_institute_code: "76040", name: "Ludwig-Maximilians Universitat", city: "Munchen", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1325,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76040",GROUP_DESC:"76040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1325,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1325,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2325 ,protection_group_id: -1325, protection_element_id:-1325], primaryKey: false);
      insert('organizations', [ id: 101311, nci_institute_code: "76041", name: "Children's Hospital of Giessen", city: "Giessen", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1326,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76041",GROUP_DESC:"76041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1326,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1326,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2326 ,protection_group_id: -1326, protection_element_id:-1326], primaryKey: false);
      insert('organizations', [ id: 101312, nci_institute_code: "76042", name: "Universitat Heidelberg", city: "Heidelberg", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1327,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76042",GROUP_DESC:"76042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1327,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1327,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2327 ,protection_group_id: -1327, protection_element_id:-1327], primaryKey: false);
      insert('organizations', [ id: 101313, nci_institute_code: "76043", name: "Der Justus-Leibig-Universitat", city: "Giessen", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1328,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76043",GROUP_DESC:"76043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1328,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1328,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2328 ,protection_group_id: -1328, protection_element_id:-1328], primaryKey: false);
      insert('organizations', [ id: 101314, nci_institute_code: "76044", name: "Univ. Klinik Ulm", city: "Ulm", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1329,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76044",GROUP_DESC:"76044 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1329,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76044",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76044",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1329,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76044", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2329 ,protection_group_id: -1329, protection_element_id:-1329], primaryKey: false);
      insert('organizations', [ id: 101315, nci_institute_code: "76045", name: "Innenstadt Klinic", city: "Munich", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1330,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76045",GROUP_DESC:"76045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1330,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1330,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2330 ,protection_group_id: -1330, protection_element_id:-1330], primaryKey: false);
      insert('organizations', [ id: 101316, nci_institute_code: "76046", name: "Klinikum Jw Goethe Univ", city: "Frankfurt Am Main 70", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1331,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76046",GROUP_DESC:"76046 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1331,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76046",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76046",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1331,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76046", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2331 ,protection_group_id: -1331, protection_element_id:-1331], primaryKey: false);
      insert('organizations', [ id: 101317, nci_institute_code: "76048", name: "Rwth Klinikum Aachen", city: "Aachen", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1332,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76048",GROUP_DESC:"76048 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1332,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76048",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76048",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1332,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76048", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2332 ,protection_group_id: -1332, protection_element_id:-1332], primaryKey: false);
      insert('organizations', [ id: 101318, nci_institute_code: "76049", name: "Landstuhl Army Regional Medical Center", city: "Landstuhl", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1333,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76049",GROUP_DESC:"76049 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1333,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76049",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76049",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1333,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76049", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2333 ,protection_group_id: -1333, protection_element_id:-1333], primaryKey: false);
      insert('organizations', [ id: 101319, nci_institute_code: "76050", name: "Klinikum Grosshadern Der University", city: "Munchen", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1334,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76050",GROUP_DESC:"76050 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1334,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76050",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76050",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1334,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76050", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2334 ,protection_group_id: -1334, protection_element_id:-1334], primaryKey: false);
      insert('organizations', [ id: 101320, nci_institute_code: "76051", name: "Universitats-Und Polikliniken", city: "6650 Homburg/Saar", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1335,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76051",GROUP_DESC:"76051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1335,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1335,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2335 ,protection_group_id: -1335, protection_element_id:-1335], primaryKey: false);
      insert('organizations', [ id: 101321, nci_institute_code: "76052", name: "University of Erlangen", city: "Erlangen", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1336,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76052",GROUP_DESC:"76052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1336,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1336,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2336 ,protection_group_id: -1336, protection_element_id:-1336], primaryKey: false);
      insert('organizations', [ id: 101322, nci_institute_code: "76053", name: "Georg August Univ/Gottingen", city: "W-3400 Gottingen", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1337,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76053",GROUP_DESC:"76053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1337,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1337,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2337 ,protection_group_id: -1337, protection_element_id:-1337], primaryKey: false);
    }

    void m13() {
        // all13 (25 terms)
      insert('organizations', [ id: 101323, nci_institute_code: "76054", name: "Vincentius Hospital", city: "7500 Karlsruhe-1", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1338,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76054",GROUP_DESC:"76054 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1338,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76054",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76054",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1338,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76054", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2338 ,protection_group_id: -1338, protection_element_id:-1338], primaryKey: false);
      insert('organizations', [ id: 101324, nci_institute_code: "76055", name: "Medical University of Kiel", city: "24116 Kiel", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1339,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76055",GROUP_DESC:"76055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1339,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1339,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2339 ,protection_group_id: -1339, protection_element_id:-1339], primaryKey: false);
      insert('organizations', [ id: 101325, nci_institute_code: "76056", name: "Charite Humboldt - University Hospital", city: "Berlin", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1340,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76056",GROUP_DESC:"76056 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1340,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76056",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76056",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1340,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76056", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2340 ,protection_group_id: -1340, protection_element_id:-1340], primaryKey: false);
      insert('organizations', [ id: 101326, nci_institute_code: "76057", name: "University of Essen", city: "D-45147 Essen", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1341,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76057",GROUP_DESC:"76057 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1341,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76057",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76057",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1341,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76057", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2341 ,protection_group_id: -1341, protection_element_id:-1341], primaryKey: false);
      insert('organizations', [ id: 101327, nci_institute_code: "76058", name: "Strahlen Abteilung", city: "Landshut", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1342,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76058",GROUP_DESC:"76058 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1342,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76058",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76058",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1342,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76058", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2342 ,protection_group_id: -1342, protection_element_id:-1342], primaryKey: false);
      insert('organizations', [ id: 101328, nci_institute_code: "76059", name: "Robert-Bosch-Krankehaus", city: "7000 Stuttgart 50", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1343,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76059",GROUP_DESC:"76059 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1343,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76059",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76059",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1343,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76059", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2343 ,protection_group_id: -1343, protection_element_id:-1343], primaryKey: false);
      insert('organizations', [ id: 101329, nci_institute_code: "76060", name: "Klinik Der Univ Med Poliklinik", city: "Krefelg", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1344,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76060",GROUP_DESC:"76060 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1344,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76060",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76060",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1344,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76060", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2344 ,protection_group_id: -1344, protection_element_id:-1344], primaryKey: false);
      insert('organizations', [ id: 101330, nci_institute_code: "76061", name: "Medizinische Klinik Iii", city: "Munchen", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1345,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76061",GROUP_DESC:"76061 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1345,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76061",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76061",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1345,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76061", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2345 ,protection_group_id: -1345, protection_element_id:-1345], primaryKey: false);
      insert('organizations', [ id: 101331, nci_institute_code: "76062", name: "Apotheke St. Johannes-Hospital", city: "47166 Duisburg", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1346,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76062",GROUP_DESC:"76062 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1346,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76062",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76062",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1346,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76062", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2346 ,protection_group_id: -1346, protection_element_id:-1346], primaryKey: false);
      insert('organizations', [ id: 101332, nci_institute_code: "76063", name: "Saint Antonius Kliniken", city: "Wuppertal", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1347,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76063",GROUP_DESC:"76063 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1347,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76063",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76063",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1347,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76063", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2347 ,protection_group_id: -1347, protection_element_id:-1347], primaryKey: false);
      insert('organizations', [ id: 101333, nci_institute_code: "76064", name: "L. Medizinische Klinik", city: "Wuppertal", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1348,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76064",GROUP_DESC:"76064 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1348,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76064",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76064",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1348,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76064", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2348 ,protection_group_id: -1348, protection_element_id:-1348], primaryKey: false);
      insert('organizations', [ id: 101334, nci_institute_code: "76065", name: "University of Bonn", city: "Bonn", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1349,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76065",GROUP_DESC:"76065 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1349,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76065",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76065",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1349,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76065", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2349 ,protection_group_id: -1349, protection_element_id:-1349], primaryKey: false);
      insert('organizations', [ id: 101335, nci_institute_code: "76066", name: "University of Halle", city: "Halle-Saale", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1350,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76066",GROUP_DESC:"76066 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1350,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76066",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76066",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1350,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76066", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2350 ,protection_group_id: -1350, protection_element_id:-1350], primaryKey: false);
      insert('organizations', [ id: 101336, nci_institute_code: "76069", name: "University of Munster", city: "D-48129 Munster", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1351,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76069",GROUP_DESC:"76069 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1351,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76069",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76069",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1351,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76069", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2351 ,protection_group_id: -1351, protection_element_id:-1351], primaryKey: false);
      insert('organizations', [ id: 101337, nci_institute_code: "76071", name: "University of Freiburg", city: "D-79106 Freiburg", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1352,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76071",GROUP_DESC:"76071 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1352,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76071",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76071",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1352,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76071", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2352 ,protection_group_id: -1352, protection_element_id:-1352], primaryKey: false);
      insert('organizations', [ id: 101338, nci_institute_code: "76072", name: "Ruprecht Karls Universitaet Heidelberg Vaudois", city: "D-69115 Heidelberg", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1353,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76072",GROUP_DESC:"76072 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1353,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76072",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76072",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1353,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76072", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2353 ,protection_group_id: -1353, protection_element_id:-1353], primaryKey: false);
      insert('organizations', [ id: 101339, nci_institute_code: "76073", name: "Klinikun Grosshadern Ludwig-Maximillians Univ. Muenchen", city: "Muenchen", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1354,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76073",GROUP_DESC:"76073 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1354,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76073",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76073",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1354,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76073", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2354 ,protection_group_id: -1354, protection_element_id:-1354], primaryKey: false);
      insert('organizations', [ id: 101340, nci_institute_code: "76074", name: "Robert-Roessle-Kink, Humboldt Universitaet Berlin", city: "Berlin", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1355,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76074",GROUP_DESC:"76074 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1355,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76074",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76074",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1355,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76074", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2355 ,protection_group_id: -1355, protection_element_id:-1355], primaryKey: false);
      insert('organizations', [ id: 101341, nci_institute_code: "76075", name: "Roche Diagnostic GmbH", city: "Penzberg", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1356,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76075",GROUP_DESC:"76075 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1356,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76075",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76075",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1356,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76075", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2356 ,protection_group_id: -1356, protection_element_id:-1356], primaryKey: false);
      insert('organizations', [ id: 101342, nci_institute_code: "76076", name: "Klinik fur Knochenmarktransplantation und Hamatologie/Onkologie", city: "Idar-Oberstein", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1357,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76076",GROUP_DESC:"76076 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1357,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76076",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76076",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1357,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76076", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2357 ,protection_group_id: -1357, protection_element_id:-1357], primaryKey: false);
      insert('organizations', [ id: 101343, nci_institute_code: "76077", name: "Universitatsklinikum", city: "Frankfurt", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1358,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76077",GROUP_DESC:"76077 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1358,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76077",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76077",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1358,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76077", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2358 ,protection_group_id: -1358, protection_element_id:-1358], primaryKey: false);
      insert('organizations', [ id: 101344, nci_institute_code: "76078", name: "Klinikum Stuttgart-Olgahospital", city: "Stuttgart", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1359,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76078",GROUP_DESC:"76078 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1359,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76078",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76078",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1359,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76078", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2359 ,protection_group_id: -1359, protection_element_id:-1359], primaryKey: false);
      insert('organizations', [ id: 101345, nci_institute_code: "76079", name: "Asklepios Klinik Saint Georg", city: "Hamburg", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1360,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76079",GROUP_DESC:"76079 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1360,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76079",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76079",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1360,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76079", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2360 ,protection_group_id: -1360, protection_element_id:-1360], primaryKey: false);
      insert('organizations', [ id: 101346, nci_institute_code: "76080", name: "Ruhr-Universitat Bochum", city: "Deutschland", state: "Bochum", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1361,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76080",GROUP_DESC:"76080 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1361,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76080",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76080",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1361,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76080", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2361 ,protection_group_id: -1361, protection_element_id:-1361], primaryKey: false);
      insert('organizations', [ id: 101347, nci_institute_code: "76081", name: "Heinrich Heine-Universitaetssr Dusseldorf", city: "Dussseldorf", state: "Deutschland", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1362,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76081",GROUP_DESC:"76081 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1362,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76081",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76081",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1362,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76081", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2362 ,protection_group_id: -1362, protection_element_id:-1362], primaryKey: false);
    }

    void m14() {
        // all14 (25 terms)
      insert('organizations', [ id: 101348, nci_institute_code: "76082", name: "Marienhospital-Herne Klinik II", city: "Herne", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1363,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76082",GROUP_DESC:"76082 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1363,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76082",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76082",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1363,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76082", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2363 ,protection_group_id: -1363, protection_element_id:-1363], primaryKey: false);
      insert('organizations', [ id: 101349, nci_institute_code: "76083", name: "University of Tuebingen-Germany", city: "Tuebingen", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1364,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76083",GROUP_DESC:"76083 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1364,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76083",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76083",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1364,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76083", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2364 ,protection_group_id: -1364, protection_element_id:-1364], primaryKey: false);
      insert('organizations', [ id: 101350, nci_institute_code: "76084", name: "Philipps University Marburg", city: "Marburg", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1365,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76084",GROUP_DESC:"76084 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1365,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76084",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76084",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1365,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76084", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2365 ,protection_group_id: -1365, protection_element_id:-1365], primaryKey: false);
      insert('organizations', [ id: 101351, nci_institute_code: "76085", name: "Max Planck Institut Fur Biophysikalische Chemie", city: "Gottingen", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1366,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76085",GROUP_DESC:"76085 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1366,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76085",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76085",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1366,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76085", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2366 ,protection_group_id: -1366, protection_element_id:-1366], primaryKey: false);
      insert('organizations', [ id: 101352, nci_institute_code: "76086", name: "Max Delbruck Center for Molecular Medicine Berlin", city: "Berlin", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1367,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76086",GROUP_DESC:"76086 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1367,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76086",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76086",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1367,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76086", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2367 ,protection_group_id: -1367, protection_element_id:-1367], primaryKey: false);
      insert('organizations', [ id: 101353, nci_institute_code: "76087", name: "Witten/Herdecke University", city: "Witten", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1368,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76087",GROUP_DESC:"76087 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1368,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76087",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76087",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1368,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76087", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2368 ,protection_group_id: -1368, protection_element_id:-1368], primaryKey: false);
      insert('organizations', [ id: 101354, nci_institute_code: "76088", name: "German Cancer Research Center", city: "Heidelberg", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1369,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76088",GROUP_DESC:"76088 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1369,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76088",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76088",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1369,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76088", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2369 ,protection_group_id: -1369, protection_element_id:-1369], primaryKey: false);
      insert('organizations', [ id: 101355, nci_institute_code: "76089", name: "University of Berlin Charite Campus Virchow Clinic", city: "Berlin", country: "Germany"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1370,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76089",GROUP_DESC:"76089 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1370,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.76089",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.76089",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1370,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.76089", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2370 ,protection_group_id: -1370, protection_element_id:-1370], primaryKey: false);
      insert('organizations', [ id: 101356, nci_institute_code: "77001", name: "Combined Military Hosp.", city: "Rawalpindi", country: "Pakistan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1371,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.77001",GROUP_DESC:"77001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1371,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.77001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.77001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1371,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.77001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2371 ,protection_group_id: -1371, protection_element_id:-1371], primaryKey: false);
      insert('organizations', [ id: 101357, nci_institute_code: "77002", name: "Aga Khan University Medical Center", city: "Karachi", country: "Pakistan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1372,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.77002",GROUP_DESC:"77002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1372,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.77002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.77002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1372,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.77002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2372 ,protection_group_id: -1372, protection_element_id:-1372], primaryKey: false);
      insert('organizations', [ id: 101358, nci_institute_code: "77003", name: "National Cancer Institute - Pakistan", city: "Karachi", country: "Pakistan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1373,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.77003",GROUP_DESC:"77003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1373,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.77003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.77003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1373,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.77003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2373 ,protection_group_id: -1373, protection_element_id:-1373], primaryKey: false);
      insert('organizations', [ id: 101359, nci_institute_code: "77004", name: "Shaukat Khanum Memorial Hospital", city: "Lahore", country: "Pakistan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1374,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.77004",GROUP_DESC:"77004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1374,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.77004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.77004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1374,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.77004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2374 ,protection_group_id: -1374, protection_element_id:-1374], primaryKey: false);
      insert('organizations', [ id: 101360, nci_institute_code: "78001", name: "Velindre Hospital", city: "Cardiff", state: "England", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1375,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.78001",GROUP_DESC:"78001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1375,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.78001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.78001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1375,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.78001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2375 ,protection_group_id: -1375, protection_element_id:-1375], primaryKey: false);
      insert('organizations', [ id: 101361, nci_institute_code: "78003", name: "Welsh National Sch. of Medicine", city: "Health Park, Cardiff, Wales", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1376,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.78003",GROUP_DESC:"78003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1376,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.78003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.78003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1376,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.78003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2376 ,protection_group_id: -1376, protection_element_id:-1376], primaryKey: false);
      insert('organizations', [ id: 101362, nci_institute_code: "78004", name: "Royal Gwent Hospital", city: "Newport, Gwent", state: "Wales", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1377,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.78004",GROUP_DESC:"78004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1377,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.78004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.78004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1377,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.78004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2377 ,protection_group_id: -1377, protection_element_id:-1377], primaryKey: false);
      insert('organizations', [ id: 101363, nci_institute_code: "79001", name: "West Indies", city: "Kingston 6", state: "WEST INDIES", country: "Jamaica"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1378,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.79001",GROUP_DESC:"79001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1378,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.79001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.79001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1378,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.79001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2378 ,protection_group_id: -1378, protection_element_id:-1378], primaryKey: false);
      insert('organizations', [ id: 101364, nci_institute_code: "79002", name: "University Hospital, Mona", city: "Kingston 7", state: "JAMAICA WI", country: "Jamaica"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1379,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.79002",GROUP_DESC:"79002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1379,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.79002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.79002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1379,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.79002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2379 ,protection_group_id: -1379, protection_element_id:-1379], primaryKey: false);
      insert('organizations', [ id: 101365, nci_institute_code: "80001", name: "Institute of Oncology", city: "11000 Belgrade", country: "Yugoslavia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1380,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.80001",GROUP_DESC:"80001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1380,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.80001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.80001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1380,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.80001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2380 ,protection_group_id: -1380, protection_element_id:-1380], primaryKey: false);
      insert('organizations', [ id: 101366, nci_institute_code: "80002", name: "Sredisnji Inst Za Tumore I S1 Bolest", city: "Zagreb 41000", country: "Yugoslavia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1381,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.80002",GROUP_DESC:"80002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1381,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.80002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.80002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1381,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.80002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2381 ,protection_group_id: -1381, protection_element_id:-1381], primaryKey: false);
      insert('organizations', [ id: 101367, nci_institute_code: "80003", name: "Medical Faculty University of Zagreb", city: "Zagreb 41000", country: "Yugoslavia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1382,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.80003",GROUP_DESC:"80003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1382,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.80003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.80003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1382,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.80003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2382 ,protection_group_id: -1382, protection_element_id:-1382], primaryKey: false);
      insert('organizations', [ id: 101368, nci_institute_code: "80004", name: "Univ. Clinical Ctrs", city: "11000 Belgrade", country: "Yugoslavia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1383,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.80004",GROUP_DESC:"80004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1383,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.80004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.80004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1383,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.80004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2383 ,protection_group_id: -1383, protection_element_id:-1383], primaryKey: false);
      insert('organizations', [ id: 101369, nci_institute_code: "81001", name: "Univ Teach Hosp/Univ 0f Zambia", city: "Lusaka", country: "Zambia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1384,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.81001",GROUP_DESC:"81001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1384,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.81001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.81001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1384,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.81001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2384 ,protection_group_id: -1384, protection_element_id:-1384], primaryKey: false);
      insert('organizations', [ id: 101370, nci_institute_code: "82001", name: "Queen Elizabeth Central Hospital", city: "Blantyre", country: "Malawi"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1385,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.82001",GROUP_DESC:"82001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1385,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.82001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.82001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1385,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.82001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2385 ,protection_group_id: -1385, protection_element_id:-1385], primaryKey: false);
      insert('organizations', [ id: 101371, nci_institute_code: "87001", name: "National Taiwan University Hosp.", city: "Taipei", country: "Taiwan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1386,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.87001",GROUP_DESC:"87001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1386,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.87001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.87001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1386,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.87001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2386 ,protection_group_id: -1386, protection_element_id:-1386], primaryKey: false);
      insert('organizations', [ id: 101372, nci_institute_code: "87002", name: "Tri-Service General Hospital", city: "Taipei", country: "Taiwan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1387,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.87002",GROUP_DESC:"87002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1387,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.87002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.87002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1387,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.87002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2387 ,protection_group_id: -1387, protection_element_id:-1387], primaryKey: false);
    }

    void m15() {
        // all15 (25 terms)
      insert('organizations', [ id: 101373, nci_institute_code: "87004", name: "Veterans General Hospital", city: "Taipai", country: "Taiwan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1388,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.87004",GROUP_DESC:"87004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1388,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.87004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.87004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1388,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.87004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2388 ,protection_group_id: -1388, protection_element_id:-1388], primaryKey: false);
      insert('organizations', [ id: 101374, nci_institute_code: "87005", name: "Koo Foundation Sun Yat-Sen Cancer Center", city: "Taipei", country: "Taiwan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1389,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.87005",GROUP_DESC:"87005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1389,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.87005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.87005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1389,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.87005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2389 ,protection_group_id: -1389, protection_element_id:-1389], primaryKey: false);
      insert('organizations', [ id: 101375, nci_institute_code: "89001", name: "New Mulago Hospital", city: "Kampala", country: "Uganda"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1390,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.89001",GROUP_DESC:"89001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1390,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.89001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.89001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1390,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.89001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2390 ,protection_group_id: -1390, protection_element_id:-1390], primaryKey: false);
      insert('organizations', [ id: 101376, nci_institute_code: "89002", name: "Uganda Cancer Institute", city: "Kampala", country: "Uganda"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1391,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.89002",GROUP_DESC:"89002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1391,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.89002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.89002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1391,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.89002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2391 ,protection_group_id: -1391, protection_element_id:-1391], primaryKey: false);
      insert('organizations', [ id: 101377, nci_institute_code: "91001", name: "St. Lukes Hospital.", city: "G'Mangia", country: "Malta"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1392,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.91001",GROUP_DESC:"91001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1392,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.91001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.91001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1392,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.91001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2392 ,protection_group_id: -1392, protection_element_id:-1392], primaryKey: false);
      insert('organizations', [ id: 101378, nci_institute_code: "95001", name: "Hospital Corporation of America", city: "Riyada Saudi", state: "ARABIA", country: "Saudi Arabia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1393,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.95001",GROUP_DESC:"95001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1393,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.95001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.95001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1393,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.95001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2393 ,protection_group_id: -1393, protection_element_id:-1393], primaryKey: false);
      insert('organizations', [ id: 101379, nci_institute_code: "95002", name: "King Faisal Spec Hosp & Res Ctr", city: "Riyadh, Saudi", state: "ARABIA", country: "Saudi Arabia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1394,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.95002",GROUP_DESC:"95002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1394,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.95002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.95002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1394,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.95002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2394 ,protection_group_id: -1394, protection_element_id:-1394], primaryKey: false);
      insert('organizations', [ id: 101380, nci_institute_code: "95003", name: "Saudi Arabia King Khaled Eye Specialist Hospital", city: "Saudi", state: "ARABIA", country: "Saudi Arabia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1395,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.95003",GROUP_DESC:"95003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1395,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.95003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.95003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1395,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.95003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2395 ,protection_group_id: -1395, protection_element_id:-1395], primaryKey: false);
      insert('organizations', [ id: 101381, nci_institute_code: "95004", name: "King Abdulaziz Medical City", city: "Riyadh", country: "Saudi Arabia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1396,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.95004",GROUP_DESC:"95004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1396,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.95004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.95004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1396,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.95004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2396 ,protection_group_id: -1396, protection_element_id:-1396], primaryKey: false);
      insert('organizations', [ id: 101382, nci_institute_code: "98001", name: "Arrowe Park Hospital", city: "Upton Wirral", state: "UK", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1397,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.98001",GROUP_DESC:"98001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1397,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.98001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.98001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1397,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.98001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2397 ,protection_group_id: -1397, protection_element_id:-1397], primaryKey: false);
      insert('organizations', [ id: 101383, nci_institute_code: "98002", name: "Bibra International", city: "Carshalton Surrey", state: "UK", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1398,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.98002",GROUP_DESC:"98002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1398,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.98002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.98002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1398,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.98002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2398 ,protection_group_id: -1398, protection_element_id:-1398], primaryKey: false);
      insert('organizations', [ id: 101384, nci_institute_code: "98003", name: "York District Hospital", city: "York", state: "UK", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1399,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.98003",GROUP_DESC:"98003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1399,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.98003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.98003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1399,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.98003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2399 ,protection_group_id: -1399, protection_element_id:-1399], primaryKey: false);
      insert('organizations', [ id: 101385, nci_institute_code: "98004", name: "Imperial College London", city: "London", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1400,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.98004",GROUP_DESC:"98004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1400,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.98004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.98004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1400,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.98004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2400 ,protection_group_id: -1400, protection_element_id:-1400], primaryKey: false);
      insert('organizations', [ id: 101386, nci_institute_code: "98005", name: "Beatson Institute for Cancer Research", city: "Bearsden", state: "Glasgow", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1401,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.98005",GROUP_DESC:"98005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1401,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.98005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.98005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1401,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.98005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2401 ,protection_group_id: -1401, protection_element_id:-1401], primaryKey: false);
      insert('organizations', [ id: 101387, nci_institute_code: "98006", name: "CRC Center for Cancer Therapeutics", city: "Sutton", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1402,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.98006",GROUP_DESC:"98006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1402,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.98006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.98006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1402,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.98006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2402 ,protection_group_id: -1402, protection_element_id:-1402], primaryKey: false);
      insert('organizations', [ id: 101388, nci_institute_code: "98007", name: "MRC Technology", city: "Mill Hill", state: "London", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1403,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.98007",GROUP_DESC:"98007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1403,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.98007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.98007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1403,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.98007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2403 ,protection_group_id: -1403, protection_element_id:-1403], primaryKey: false);
      insert('organizations', [ id: 101389, nci_institute_code: "98008", name: "University of Nottingham - UK", city: "Nottingham", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1404,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.98008",GROUP_DESC:"98008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1404,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.98008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.98008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1404,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.98008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2404 ,protection_group_id: -1404, protection_element_id:-1404], primaryKey: false);
      insert('organizations', [ id: 101390, nci_institute_code: "98009", name: "European Drug Discovery Network", city: "London", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1405,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.98009",GROUP_DESC:"98009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1405,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.98009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.98009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1405,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.98009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2405 ,protection_group_id: -1405, protection_element_id:-1405], primaryKey: false);
      insert('organizations', [ id: 101391, nci_institute_code: "98010", name: "James Paget Healthcare NHS Trust", city: "Great Yarmouth", state: "Norfolk", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1406,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.98010",GROUP_DESC:"98010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1406,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.98010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.98010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1406,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.98010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2406 ,protection_group_id: -1406, protection_element_id:-1406], primaryKey: false);
      insert('organizations', [ id: 101392, nci_institute_code: "98011", name: "University of Sussex", city: "Brighton", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1407,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.98011",GROUP_DESC:"98011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1407,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.98011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.98011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1407,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.98011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2407 ,protection_group_id: -1407, protection_element_id:-1407], primaryKey: false);
      insert('organizations', [ id: 101393, nci_institute_code: "98012", name: "Cardiff University", city: "Cardiff", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1408,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.98012",GROUP_DESC:"98012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1408,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.98012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.98012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1408,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.98012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2408 ,protection_group_id: -1408, protection_element_id:-1408], primaryKey: false);
      insert('organizations', [ id: 101394, nci_institute_code: "98013", name: "University College London", city: "London", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1409,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.98013",GROUP_DESC:"98013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1409,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.98013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.98013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1409,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.98013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2409 ,protection_group_id: -1409, protection_element_id:-1409], primaryKey: false);
      insert('organizations', [ id: 101395, nci_institute_code: "98014", name: "Birmingham Children's Hospital", city: "Birmingham", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1410,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.98014",GROUP_DESC:"98014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1410,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.98014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.98014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1410,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.98014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2410 ,protection_group_id: -1410, protection_element_id:-1410], primaryKey: false);
      insert('organizations', [ id: 101396, nci_institute_code: "98015", name: "Bristol Haematology and Oncology Centre", city: "Bristol", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1411,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.98015",GROUP_DESC:"98015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1411,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.98015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.98015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1411,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.98015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2411 ,protection_group_id: -1411, protection_element_id:-1411], primaryKey: false);
      insert('organizations', [ id: 101397, nci_institute_code: "99001", name: "Jordan Univ. Hosp.", state: "AMMAN", country: "Jordan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1412,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.99001",GROUP_DESC:"99001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1412,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.99001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.99001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1412,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.99001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2412 ,protection_group_id: -1412, protection_element_id:-1412], primaryKey: false);
    }

    void m16() {
        // all16 (25 terms)
      insert('organizations', [ id: 101398, nci_institute_code: "ACTG", name: "Aids Clinical Trials Group", city: "Silver Spring", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1413,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ACTG",GROUP_DESC:"ACTG group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1413,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ACTG",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ACTG",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1413,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ACTG", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2413 ,protection_group_id: -1413, protection_element_id:-1413], primaryKey: false);
      insert('organizations', [ id: 101399, nci_institute_code: "ADIR", name: "Adirondack Surgical Group", city: "Tupper Lake", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1414,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ADIR",GROUP_DESC:"ADIR group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1414,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ADIR",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ADIR",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1414,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ADIR", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2414 ,protection_group_id: -1414, protection_element_id:-1414], primaryKey: false);
      insert('organizations', [ id: 101400, nci_institute_code: "AK001", name: "Anchorage Medical and Surgical Clinic", city: "Anchorage", state: "AK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1415,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AK001",GROUP_DESC:"AK001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1415,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AK001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AK001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1415,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AK001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2415 ,protection_group_id: -1415, protection_element_id:-1415], primaryKey: false);
      insert('organizations', [ id: 101401, nci_institute_code: "AK002", name: "Providence Alaska Medical Center", city: "Anchorage", state: "AK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1416,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AK002",GROUP_DESC:"AK002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1416,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AK002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AK002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1416,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AK002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2416 ,protection_group_id: -1416, protection_element_id:-1416], primaryKey: false);
      insert('organizations', [ id: 101402, nci_institute_code: "AK003", name: "Anchorage Radiation Therapy Center", city: "Anchorage", state: "AK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1417,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AK003",GROUP_DESC:"AK003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1417,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AK003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AK003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1417,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AK003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2417 ,protection_group_id: -1417, protection_element_id:-1417], primaryKey: false);
      insert('organizations', [ id: 101403, nci_institute_code: "AK004", name: "Fairbanks Medical and Surgical Clinic", city: "Fairbanks", state: "AK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1418,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AK004",GROUP_DESC:"AK004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1418,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AK004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AK004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1418,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AK004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2418 ,protection_group_id: -1418, protection_element_id:-1418], primaryKey: false);
      insert('organizations', [ id: 101404, nci_institute_code: "AK005", name: "Tanana Valley Med/Surg Group", city: "Fairbanks", state: "AK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1419,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AK005",GROUP_DESC:"AK005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1419,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AK005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AK005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1419,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AK005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2419 ,protection_group_id: -1419, protection_element_id:-1419], primaryKey: false);
      insert('organizations', [ id: 101405, nci_institute_code: "AK007", name: "Alaska Regional Hospital", city: "Anchorage", state: "AK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1420,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AK007",GROUP_DESC:"AK007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1420,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AK007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AK007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1420,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AK007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2420 ,protection_group_id: -1420, protection_element_id:-1420], primaryKey: false);
      insert('organizations', [ id: 101406, nci_institute_code: "AK008", name: "Bartlett Regional Hospital", city: "Juneau", state: "AK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1421,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AK008",GROUP_DESC:"AK008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1421,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AK008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AK008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1421,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AK008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2421 ,protection_group_id: -1421, protection_element_id:-1421], primaryKey: false);
      insert('organizations', [ id: 101407, nci_institute_code: "AK010", name: "Alaska Native Medical Center", city: "Anchorage", state: "AK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1422,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AK010",GROUP_DESC:"AK010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1422,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AK010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AK010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1422,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AK010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2422 ,protection_group_id: -1422, protection_element_id:-1422], primaryKey: false);
      insert('organizations', [ id: 101408, nci_institute_code: "AK012", name: "Quest Diagnostics", city: "Fairbanks", state: "AK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1423,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AK012",GROUP_DESC:"AK012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1423,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AK012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AK012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1423,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AK012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2423 ,protection_group_id: -1423, protection_element_id:-1423], primaryKey: false);
      insert('organizations', [ id: 101409, nci_institute_code: "AK013", name: "Fairbanks Memorial Hospital", city: "Fairbanks", state: "AK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1424,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AK013",GROUP_DESC:"AK013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1424,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AK013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AK013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1424,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AK013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2424 ,protection_group_id: -1424, protection_element_id:-1424], primaryKey: false);
      insert('organizations', [ id: 101410, nci_institute_code: "AK014", name: "Anchorage Oncology Center", city: "Anchorage", state: "AK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1425,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AK014",GROUP_DESC:"AK014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1425,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AK014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AK014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1425,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AK014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2425 ,protection_group_id: -1425, protection_element_id:-1425], primaryKey: false);
      insert('organizations', [ id: 101411, nci_institute_code: "AK015", name: "Katmai Oncology Group", city: "Anchorage", state: "AK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1426,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AK015",GROUP_DESC:"AK015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1426,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AK015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AK015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1426,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AK015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2426 ,protection_group_id: -1426, protection_element_id:-1426], primaryKey: false);
      insert('organizations', [ id: 101412, nci_institute_code: "AK016", name: "Oncology Alaska, LLC", city: "Anchorage", state: "AK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1427,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AK016",GROUP_DESC:"AK016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1427,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AK016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AK016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1427,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AK016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2427 ,protection_group_id: -1427, protection_element_id:-1427], primaryKey: false);
      insert('organizations', [ id: 101413, nci_institute_code: "AK017", name: "Alaska Cancer Research and Education Center", city: "Anchorage", state: "AK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1428,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AK017",GROUP_DESC:"AK017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1428,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AK017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AK017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1428,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AK017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2428 ,protection_group_id: -1428, protection_element_id:-1428], primaryKey: false);
      insert('organizations', [ id: 101414, nci_institute_code: "AK018", name: "Cancer Treatment Center", city: "Fairbanks", state: "AK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1429,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AK018",GROUP_DESC:"AK018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1429,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AK018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AK018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1429,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AK018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2429 ,protection_group_id: -1429, protection_element_id:-1429], primaryKey: false);
      insert('organizations', [ id: 101415, nci_institute_code: "AK019", name: "Alaska Oncology and Hematology LLC", city: "Anchorage", state: "AK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1430,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AK019",GROUP_DESC:"AK019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1430,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AK019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AK019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1430,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AK019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2430 ,protection_group_id: -1430, protection_element_id:-1430], primaryKey: false);
      insert('organizations', [ id: 101416, nci_institute_code: "AKSW", name: "Allgemeines Krankenhaus Der Stadt Wien", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1431,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AKSW",GROUP_DESC:"AKSW group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1431,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AKSW",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AKSW",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1431,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AKSW", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2431 ,protection_group_id: -1431, protection_element_id:-1431], primaryKey: false);
      insert('organizations', [ id: 101417, nci_institute_code: "AL001", name: "Lloyd Nolan Hospital and Clinic", city: "Fairfield", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1432,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL001",GROUP_DESC:"AL001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1432,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1432,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2432 ,protection_group_id: -1432, protection_element_id:-1432], primaryKey: false);
      insert('organizations', [ id: 101418, nci_institute_code: "AL002", name: "University of Alabama at Birmingham", city: "Birmingham", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1433,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL002",GROUP_DESC:"AL002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1433,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1433,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2433 ,protection_group_id: -1433, protection_element_id:-1433], primaryKey: false);
      insert('organizations', [ id: 101419, nci_institute_code: "AL003", name: "Saint Vincent's Hospital", city: "Birmingham", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1434,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL003",GROUP_DESC:"AL003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1434,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1434,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2434 ,protection_group_id: -1434, protection_element_id:-1434], primaryKey: false);
      insert('organizations', [ id: 101420, nci_institute_code: "AL005", name: "Brookwood Medical Center", city: "Birmingham", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1435,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL005",GROUP_DESC:"AL005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1435,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1435,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2435 ,protection_group_id: -1435, protection_element_id:-1435], primaryKey: false);
      insert('organizations', [ id: 101421, nci_institute_code: "AL006", name: "Gulf Coast MBCCOP", city: "Mobile", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1436,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL006",GROUP_DESC:"AL006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1436,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1436,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2436 ,protection_group_id: -1436, protection_element_id:-1436], primaryKey: false);
      insert('organizations', [ id: 101422, nci_institute_code: "AL007", name: "Simon Williamson Clinic", city: "Birmingham", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1437,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL007",GROUP_DESC:"AL007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1437,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1437,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2437 ,protection_group_id: -1437, protection_element_id:-1437], primaryKey: false);
    }

    void m17() {
        // all17 (25 terms)
      insert('organizations', [ id: 101423, nci_institute_code: "AL008", name: "Medical Center East", city: "Brimingham", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1438,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL008",GROUP_DESC:"AL008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1438,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1438,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2438 ,protection_group_id: -1438, protection_element_id:-1438], primaryKey: false);
      insert('organizations', [ id: 101424, nci_institute_code: "AL009", name: "Baptist Medical Center -Montclair Campus", city: "Birmingham", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1439,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL009",GROUP_DESC:"AL009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1439,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1439,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2439 ,protection_group_id: -1439, protection_element_id:-1439], primaryKey: false);
      insert('organizations', [ id: 101425, nci_institute_code: "AL010", name: "Jefferson Clinic, P. C.", city: "Birmingham", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1440,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL010",GROUP_DESC:"AL010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1440,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1440,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2440 ,protection_group_id: -1440, protection_element_id:-1440], primaryKey: false);
      insert('organizations', [ id: 101426, nci_institute_code: "AL011", name: "Veterans Administration Medical Center - Birmingham", city: "Birmingham", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1441,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL011",GROUP_DESC:"AL011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1441,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1441,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2441 ,protection_group_id: -1441, protection_element_id:-1441], primaryKey: false);
      insert('organizations', [ id: 101427, nci_institute_code: "AL012", name: "Uab Russell Ambulatory Pharm.", city: "Birmingham", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1442,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL012",GROUP_DESC:"AL012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1442,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1442,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2442 ,protection_group_id: -1442, protection_element_id:-1442], primaryKey: false);
      insert('organizations', [ id: 101428, nci_institute_code: "AL013", name: "Children's Hospital of Alabama", city: "Birmingham", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1443,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL013",GROUP_DESC:"AL013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1443,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1443,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2443 ,protection_group_id: -1443, protection_element_id:-1443], primaryKey: false);
      insert('organizations', [ id: 101429, nci_institute_code: "AL014", name: "Wallace Tumor Institute", city: "Birmingham", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1444,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL014",GROUP_DESC:"AL014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1444,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1444,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2444 ,protection_group_id: -1444, protection_element_id:-1444], primaryKey: false);
      insert('organizations', [ id: 101430, nci_institute_code: "AL015", name: "Norwood Clinic, Inc. P. C.", city: "Birmingham", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1445,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL015",GROUP_DESC:"AL015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1445,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1445,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2445 ,protection_group_id: -1445, protection_element_id:-1445], primaryKey: false);
      insert('organizations', [ id: 101431, nci_institute_code: "AL016", name: "Huntsville Hospital", city: "Huntsville", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1446,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL016",GROUP_DESC:"AL016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1446,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1446,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2446 ,protection_group_id: -1446, protection_element_id:-1446], primaryKey: false);
      insert('organizations', [ id: 101432, nci_institute_code: "AL017", name: "University of Alabama - Huntsville", city: "Huntsville", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1447,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL017",GROUP_DESC:"AL017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1447,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1447,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2447 ,protection_group_id: -1447, protection_element_id:-1447], primaryKey: false);
      insert('organizations', [ id: 101433, nci_institute_code: "AL019", name: "Jackson Hospital and Clinic", city: "Montgomery", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1448,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL019",GROUP_DESC:"AL019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1448,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1448,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2448 ,protection_group_id: -1448, protection_element_id:-1448], primaryKey: false);
      insert('organizations', [ id: 101434, nci_institute_code: "AL020", name: "Baptist Medical Center - East", city: "Montgomery", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1449,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL020",GROUP_DESC:"AL020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1449,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1449,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2449 ,protection_group_id: -1449, protection_element_id:-1449], primaryKey: false);
      insert('organizations', [ id: 101435, nci_institute_code: "AL021", name: "Noble Army Hospital", city: "Ft. Mcclellan", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1450,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL021",GROUP_DESC:"AL021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1450,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1450,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2450 ,protection_group_id: -1450, protection_element_id:-1450], primaryKey: false);
      insert('organizations', [ id: 101436, nci_institute_code: "AL022", name: "University of South Alabama", city: "Mobile", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1451,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL022",GROUP_DESC:"AL022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1451,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1451,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2451 ,protection_group_id: -1451, protection_element_id:-1451], primaryKey: false);
      insert('organizations', [ id: 101437, nci_institute_code: "AL023", name: "Carraway Methodist Medical Center", city: "Birmingham", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1452,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL023",GROUP_DESC:"AL023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1452,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1452,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2452 ,protection_group_id: -1452, protection_element_id:-1452], primaryKey: false);
      insert('organizations', [ id: 101438, nci_institute_code: "AL024", name: "Uab Comprehensive Cancer Ctr.", city: "Birmingham", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1453,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL024",GROUP_DESC:"AL024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1453,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1453,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2453 ,protection_group_id: -1453, protection_element_id:-1453], primaryKey: false);
      insert('organizations', [ id: 101439, nci_institute_code: "AL025", name: "Providence Hospital", city: "Mobile", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1454,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL025",GROUP_DESC:"AL025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1454,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1454,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2454 ,protection_group_id: -1454, protection_element_id:-1454], primaryKey: false);
      insert('organizations', [ id: 101440, nci_institute_code: "AL026", name: "Springhill Memorial Hospital", city: "Mobile", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1455,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL026",GROUP_DESC:"AL026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1455,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1455,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2455 ,protection_group_id: -1455, protection_element_id:-1455], primaryKey: false);
      insert('organizations', [ id: 101441, nci_institute_code: "AL027", name: "Tennessee Valley Blood Cancer Center", city: "Florence", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1456,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL027",GROUP_DESC:"AL027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1456,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1456,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2456 ,protection_group_id: -1456, protection_element_id:-1456], primaryKey: false);
      insert('organizations', [ id: 101442, nci_institute_code: "AL028", name: "Baptist Medical Center - Princeton Campus", city: "Birmingham", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1457,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL028",GROUP_DESC:"AL028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1457,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1457,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2457 ,protection_group_id: -1457, protection_element_id:-1457], primaryKey: false);
      insert('organizations', [ id: 101443, nci_institute_code: "AL029", name: "Oncology Association West Alabama", city: "Tuscaloosa", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1458,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL029",GROUP_DESC:"AL029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1458,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1458,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2458 ,protection_group_id: -1458, protection_element_id:-1458], primaryKey: false);
      insert('organizations', [ id: 101444, nci_institute_code: "AL030", name: "Mobile Infirmary Medical Center", city: "Mobile", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1459,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL030",GROUP_DESC:"AL030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1459,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1459,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2459 ,protection_group_id: -1459, protection_element_id:-1459], primaryKey: false);
      insert('organizations', [ id: 101445, nci_institute_code: "AL031", name: "Tri-State Cancer Center- Flowers Hospital", city: "Dothan", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1460,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL031",GROUP_DESC:"AL031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1460,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1460,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2460 ,protection_group_id: -1460, protection_element_id:-1460], primaryKey: false);
      insert('organizations', [ id: 101446, nci_institute_code: "AL032", name: "Northeast Alabama Regional Medical Center", city: "Anniston", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1461,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL032",GROUP_DESC:"AL032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1461,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1461,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2461 ,protection_group_id: -1461, protection_element_id:-1461], primaryKey: false);
      insert('organizations', [ id: 101447, nci_institute_code: "AL033", name: "East Alabama Cancer Center", city: "Opelika", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1462,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL033",GROUP_DESC:"AL033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1462,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1462,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2462 ,protection_group_id: -1462, protection_element_id:-1462], primaryKey: false);
    }

    void m18() {
        // all18 (25 terms)
      insert('organizations', [ id: 101448, nci_institute_code: "AL034", name: "Clearview Cancer Institute", city: "Huntsville", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1463,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL034",GROUP_DESC:"AL034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1463,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1463,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2463 ,protection_group_id: -1463, protection_element_id:-1463], primaryKey: false);
      insert('organizations', [ id: 101449, nci_institute_code: "AL035", name: "Montgomery Cancer Center", city: "Montgomery", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1464,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL035",GROUP_DESC:"AL035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1464,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1464,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2464 ,protection_group_id: -1464, protection_element_id:-1464], primaryKey: false);
      insert('organizations', [ id: 101450, nci_institute_code: "AL036", name: "Bessemer Carraway Medical Center", city: "Bessemer", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1465,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL036",GROUP_DESC:"AL036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1465,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1465,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2465 ,protection_group_id: -1465, protection_element_id:-1465], primaryKey: false);
      insert('organizations', [ id: 101451, nci_institute_code: "AL037", name: "The Cooper Green", city: "Birmingham", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1466,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL037",GROUP_DESC:"AL037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1466,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1466,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2466 ,protection_group_id: -1466, protection_element_id:-1466], primaryKey: false);
      insert('organizations', [ id: 101452, nci_institute_code: "AL038", name: "Alabama Oncology", city: "Montgomery", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1467,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL038",GROUP_DESC:"AL038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1467,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1467,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2467 ,protection_group_id: -1467, protection_element_id:-1467], primaryKey: false);
      insert('organizations', [ id: 101453, nci_institute_code: "AL039", name: "Internal Medicine and Oncology Services PA", city: "Mobile", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1468,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL039",GROUP_DESC:"AL039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1468,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1468,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2468 ,protection_group_id: -1468, protection_element_id:-1468], primaryKey: false);
      insert('organizations', [ id: 101454, nci_institute_code: "AL040", name: "Birmingham Hem/Onc Assoc.", city: "Birmingham", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1469,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL040",GROUP_DESC:"AL040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1469,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1469,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2469 ,protection_group_id: -1469, protection_element_id:-1469], primaryKey: false);
      insert('organizations', [ id: 101455, nci_institute_code: "AL041", name: "Cancer Treatment Center", city: "Birmingham", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1470,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL041",GROUP_DESC:"AL041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1470,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1470,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2470 ,protection_group_id: -1470, protection_element_id:-1470], primaryKey: false);
      insert('organizations', [ id: 101456, nci_institute_code: "AL045", name: "Shelby Baptist Medical Ctr", city: "Alabaster", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1471,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL045",GROUP_DESC:"AL045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1471,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1471,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2471 ,protection_group_id: -1471, protection_element_id:-1471], primaryKey: false);
      insert('organizations', [ id: 101457, nci_institute_code: "AL046", name: "Baptist Health System - Cullman", city: "Cullman", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1472,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL046",GROUP_DESC:"AL046 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1472,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL046",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL046",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1472,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL046", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2472 ,protection_group_id: -1472, protection_element_id:-1472], primaryKey: false);
      insert('organizations', [ id: 101458, nci_institute_code: "AL047", name: "Sorra Research Center", city: "Birmingham", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1473,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL047",GROUP_DESC:"AL047 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1473,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL047",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL047",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1473,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL047", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2473 ,protection_group_id: -1473, protection_element_id:-1473], primaryKey: false);
      insert('organizations', [ id: 101459, nci_institute_code: "AL048", name: "The Surgeon's Group", city: "Birmingham", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1474,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL048",GROUP_DESC:"AL048 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1474,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL048",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL048",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1474,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL048", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2474 ,protection_group_id: -1474, protection_element_id:-1474], primaryKey: false);
      insert('organizations', [ id: 101460, nci_institute_code: "AL049", name: "Baptist Medical Center -South", city: "Montgomery", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1475,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL049",GROUP_DESC:"AL049 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1475,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL049",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL049",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1475,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL049", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2475 ,protection_group_id: -1475, protection_element_id:-1475], primaryKey: false);
      insert('organizations', [ id: 101461, nci_institute_code: "AL050", name: "Southern Hematology Oncology", city: "Birmingham", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1476,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL050",GROUP_DESC:"AL050 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1476,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL050",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL050",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1476,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL050", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2476 ,protection_group_id: -1476, protection_element_id:-1476], primaryKey: false);
      insert('organizations', [ id: 101462, nci_institute_code: "AL051", name: "Thomas Hospital", city: "Fairhope", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1477,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL051",GROUP_DESC:"AL051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1477,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1477,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2477 ,protection_group_id: -1477, protection_element_id:-1477], primaryKey: false);
      insert('organizations', [ id: 101463, nci_institute_code: "AL052", name: "Clinical Research Consultants, Inc.", city: "Hoover", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1478,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL052",GROUP_DESC:"AL052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1478,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1478,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2478 ,protection_group_id: -1478, protection_element_id:-1478], primaryKey: false);
      insert('organizations', [ id: 101464, nci_institute_code: "AL053", name: "Veteran Administration Medical Center, Tuscaloosa", city: "Tuscaloosa", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1479,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL053",GROUP_DESC:"AL053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1479,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1479,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2479 ,protection_group_id: -1479, protection_element_id:-1479], primaryKey: false);
      insert('organizations', [ id: 101465, nci_institute_code: "AL054", name: "The Kirklin Clinic at Acton Road", city: "Birmingham", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1480,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL054",GROUP_DESC:"AL054 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1480,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL054",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL054",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1480,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL054", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2480 ,protection_group_id: -1480, protection_element_id:-1480], primaryKey: false);
      insert('organizations', [ id: 101466, nci_institute_code: "AL055", name: "University of South Alabama Knollwood Park Hospital", city: "Mobile", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1481,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL055",GROUP_DESC:"AL055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1481,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1481,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2481 ,protection_group_id: -1481, protection_element_id:-1481], primaryKey: false);
      insert('organizations', [ id: 101467, nci_institute_code: "AL056", name: "Radiation Therapy Oncology, P.C.", city: "Mogile", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1482,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL056",GROUP_DESC:"AL056 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1482,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL056",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL056",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1482,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL056", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2482 ,protection_group_id: -1482, protection_element_id:-1482], primaryKey: false);
      insert('organizations', [ id: 101468, nci_institute_code: "AL057", name: "University of South Alabama Medical Center", city: "Mobile", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1483,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL057",GROUP_DESC:"AL057 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1483,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL057",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL057",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1483,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL057", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2483 ,protection_group_id: -1483, protection_element_id:-1483], primaryKey: false);
      insert('organizations', [ id: 101469, nci_institute_code: "AL058", name: "Oncology Specialists PC", city: "Huntsville", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1484,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL058",GROUP_DESC:"AL058 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1484,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL058",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL058",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1484,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL058", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2484 ,protection_group_id: -1484, protection_element_id:-1484], primaryKey: false);
      insert('organizations', [ id: 101470, nci_institute_code: "AL059", name: "The Cancer Center of Huntsville, PC", city: "Huntsville", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1485,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL059",GROUP_DESC:"AL059 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1485,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL059",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL059",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1485,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL059", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2485 ,protection_group_id: -1485, protection_element_id:-1485], primaryKey: false);
      insert('organizations', [ id: 101471, nci_institute_code: "AL060", name: "Cancer Surgery of Mobile, P.C.", city: "Mobile", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1486,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL060",GROUP_DESC:"AL060 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1486,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL060",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL060",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1486,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL060", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2486 ,protection_group_id: -1486, protection_element_id:-1486], primaryKey: false);
      insert('organizations', [ id: 101472, nci_institute_code: "AL061", name: "Alabama Breast Center", city: "Montgomery", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1487,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL061",GROUP_DESC:"AL061 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1487,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL061",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL061",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1487,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL061", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2487 ,protection_group_id: -1487, protection_element_id:-1487], primaryKey: false);
    }

    void m19() {
        // all19 (25 terms)
      insert('organizations', [ id: 101473, nci_institute_code: "AL062", name: "Capital Cariothoracic P.C.", city: "Montgomery", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1488,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL062",GROUP_DESC:"AL062 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1488,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL062",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL062",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1488,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL062", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2488 ,protection_group_id: -1488, protection_element_id:-1488], primaryKey: false);
      insert('organizations', [ id: 101474, nci_institute_code: "AL064", name: "Cardio-Thoracic & Vascular Surgical Associates, P.C.", city: "Mobile", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1489,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL064",GROUP_DESC:"AL064 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1489,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL064",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL064",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1489,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL064", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2489 ,protection_group_id: -1489, protection_element_id:-1489], primaryKey: false);
      insert('organizations', [ id: 101475, nci_institute_code: "AL065", name: "Anniston Oncology", city: "Anniston", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1490,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL065",GROUP_DESC:"AL065 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1490,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL065",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL065",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1490,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL065", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2490 ,protection_group_id: -1490, protection_element_id:-1490], primaryKey: false);
      insert('organizations', [ id: 101476, nci_institute_code: "AL066", name: "Providence Cancer Center", city: "Mobile", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1491,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL066",GROUP_DESC:"AL066 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1491,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL066",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL066",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1491,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL066", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2491 ,protection_group_id: -1491, protection_element_id:-1491], primaryKey: false);
      insert('organizations', [ id: 101477, nci_institute_code: "AL067", name: "Birmingham Hematology & Oncology Associates - Bessemer", city: "Bessemer", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1492,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL067",GROUP_DESC:"AL067 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1492,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL067",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL067",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1492,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL067", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2492 ,protection_group_id: -1492, protection_element_id:-1492], primaryKey: false);
      insert('organizations', [ id: 101478, nci_institute_code: "AL068", name: "University of South Alabama Mitchell Cancer Institute", city: "Mobile", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1493,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL068",GROUP_DESC:"AL068 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1493,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL068",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL068",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1493,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL068", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2493 ,protection_group_id: -1493, protection_element_id:-1493], primaryKey: false);
      insert('organizations', [ id: 101479, nci_institute_code: "AL069", name: "Surgical Oncology and General Surgery PC", city: "Mobile", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1494,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL069",GROUP_DESC:"AL069 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1494,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL069",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL069",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1494,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL069", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2494 ,protection_group_id: -1494, protection_element_id:-1494], primaryKey: false);
      insert('organizations', [ id: 101480, nci_institute_code: "AL070", name: "Southern Cancer Center PC", city: "Mobile", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1495,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL070",GROUP_DESC:"AL070 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1495,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL070",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL070",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1495,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL070", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2495 ,protection_group_id: -1495, protection_element_id:-1495], primaryKey: false);
      insert('organizations', [ id: 101481, nci_institute_code: "AL071", name: "Princeton Surgical Specialists PC", city: "Birmingham", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1496,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL071",GROUP_DESC:"AL071 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1496,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL071",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL071",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1496,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL071", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2496 ,protection_group_id: -1496, protection_element_id:-1496], primaryKey: false);
      insert('organizations', [ id: 101482, nci_institute_code: "AL072", name: "Clearview Cancer Institute-Satellite Office", city: "Decatur", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1497,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL072",GROUP_DESC:"AL072 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1497,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL072",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL072",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1497,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL072", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2497 ,protection_group_id: -1497, protection_element_id:-1497], primaryKey: false);
      insert('organizations', [ id: 101483, nci_institute_code: "AL073", name: "DCH Regional Medical Center Cancer Treatment Center", city: "Tuscaloosa", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1498,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL073",GROUP_DESC:"AL073 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1498,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL073",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL073",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1498,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL073", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2498 ,protection_group_id: -1498, protection_element_id:-1498], primaryKey: false);
      insert('organizations', [ id: 101484, nci_institute_code: "AMU", name: "Address Module Unit", city: "Rockville", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1499,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AMU",GROUP_DESC:"AMU group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1499,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AMU",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AMU",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1499,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AMU", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2499 ,protection_group_id: -1499, protection_element_id:-1499], primaryKey: false);
      insert('organizations', [ id: 101485, nci_institute_code: "AR001", name: "Memorial Hospital", city: "North Little Rock", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1500,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR001",GROUP_DESC:"AR001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1500,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1500,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2500 ,protection_group_id: -1500, protection_element_id:-1500], primaryKey: false);
      insert('organizations', [ id: 101486, nci_institute_code: "AR003", name: "Arkansas Childrens Hospital", city: "Little Rock", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1501,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR003",GROUP_DESC:"AR003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1501,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1501,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2501 ,protection_group_id: -1501, protection_element_id:-1501], primaryKey: false);
      insert('organizations', [ id: 101487, nci_institute_code: "AR004", name: "Columbia Doctors Hospital", city: "Little Rock", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1502,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR004",GROUP_DESC:"AR004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1502,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1502,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2502 ,protection_group_id: -1502, protection_element_id:-1502], primaryKey: false);
      insert('organizations', [ id: 101488, nci_institute_code: "AR005", name: "Saint Vincent Infirmary", city: "Little Rock", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1503,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR005",GROUP_DESC:"AR005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1503,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1503,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2503 ,protection_group_id: -1503, protection_element_id:-1503], primaryKey: false);
      insert('organizations', [ id: 101489, nci_institute_code: "AR006", name: "University of Arkansas for Medical Sciences", city: "Little Rock", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1504,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR006",GROUP_DESC:"AR006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1504,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1504,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2504 ,protection_group_id: -1504, protection_element_id:-1504], primaryKey: false);
      insert('organizations', [ id: 101490, nci_institute_code: "AR007", name: "Veteran's Administration Medical Center", city: "Little Rock", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1505,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR007",GROUP_DESC:"AR007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1505,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1505,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2505 ,protection_group_id: -1505, protection_element_id:-1505], primaryKey: false);
      insert('organizations', [ id: 101491, nci_institute_code: "AR008", name: "Washington Regional Medical Center - Fayetteville", city: "Fayetteville", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1506,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR008",GROUP_DESC:"AR008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1506,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1506,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2506 ,protection_group_id: -1506, protection_element_id:-1506], primaryKey: false);
      insert('organizations', [ id: 101492, nci_institute_code: "AR009", name: "Washington Regional Medical Center - Bentonville", city: "Bentonville", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1507,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR009",GROUP_DESC:"AR009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1507,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1507,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2507 ,protection_group_id: -1507, protection_element_id:-1507], primaryKey: false);
      insert('organizations', [ id: 101493, nci_institute_code: "AR011", name: "Sparks Regional Medical Center", city: "Fort Smith", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1508,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR011",GROUP_DESC:"AR011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1508,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1508,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2508 ,protection_group_id: -1508, protection_element_id:-1508], primaryKey: false);
      insert('organizations', [ id: 101494, nci_institute_code: "AR012", name: "Saint Edward Mercy Medical Center", city: "Ft. Smith", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1509,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR012",GROUP_DESC:"AR012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1509,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1509,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2509 ,protection_group_id: -1509, protection_element_id:-1509], primaryKey: false);
      insert('organizations', [ id: 101495, nci_institute_code: "AR013", name: "Veterans Administration Medical Center", city: "Fayetteville", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1510,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR013",GROUP_DESC:"AR013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1510,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1510,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2510 ,protection_group_id: -1510, protection_element_id:-1510], primaryKey: false);
      insert('organizations', [ id: 101496, nci_institute_code: "AR014", name: "Holt Krock Clinic", city: "Fort Smith", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1511,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR014",GROUP_DESC:"AR014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1511,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1511,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2511 ,protection_group_id: -1511, protection_element_id:-1511], primaryKey: false);
      insert('organizations', [ id: 101497, nci_institute_code: "AR015", name: "Hembree Regional Cancer Center", city: "Fortsmith", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1512,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR015",GROUP_DESC:"AR015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1512,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1512,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2512 ,protection_group_id: -1512, protection_element_id:-1512], primaryKey: false);
    }

    void m20() {
        // all20 (25 terms)
      insert('organizations', [ id: 101498, nci_institute_code: "AR016", name: "Saint Bernards Regional Medical Center", city: "Jonesboro", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1513,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR016",GROUP_DESC:"AR016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1513,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1513,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2513 ,protection_group_id: -1513, protection_element_id:-1513], primaryKey: false);
      insert('organizations', [ id: 101499, nci_institute_code: "AR017", name: "Saint Joseph's Regional Health", city: "Hot Springs", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1514,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR017",GROUP_DESC:"AR017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1514,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1514,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2514 ,protection_group_id: -1514, protection_element_id:-1514], primaryKey: false);
      insert('organizations', [ id: 101500, nci_institute_code: "AR018", name: "Saint Mary-Rogers Memorial Hospital", city: "Rogers", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1515,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR018",GROUP_DESC:"AR018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1515,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1515,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2515 ,protection_group_id: -1515, protection_element_id:-1515], primaryKey: false);
      insert('organizations', [ id: 101501, nci_institute_code: "AR019", name: "Northeast Arkansas Clinic", city: "Jonesboro", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1516,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR019",GROUP_DESC:"AR019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1516,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1516,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2516 ,protection_group_id: -1516, protection_element_id:-1516], primaryKey: false);
      insert('organizations', [ id: 101502, nci_institute_code: "AR020", name: "Little Rock Hematology-Oncology Association, Pa", city: "Little Rock", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1517,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR020",GROUP_DESC:"AR020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1517,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1517,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2517 ,protection_group_id: -1517, protection_element_id:-1517], primaryKey: false);
      insert('organizations', [ id: 101503, nci_institute_code: "AR021", name: "Arkansas Oncology Clinic", city: "Little Rock", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1518,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR021",GROUP_DESC:"AR021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1518,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1518,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2518 ,protection_group_id: -1518, protection_element_id:-1518], primaryKey: false);
      insert('organizations', [ id: 101504, nci_institute_code: "AR022", name: "Oncology Associates PA", city: "Little Rock", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1519,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR022",GROUP_DESC:"AR022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1519,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1519,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2519 ,protection_group_id: -1519, protection_element_id:-1519], primaryKey: false);
      insert('organizations', [ id: 101505, nci_institute_code: "AR023", name: "Highlands Oncology Group, P.A. - Fayetteville", city: "Fayetteville", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1520,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR023",GROUP_DESC:"AR023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1520,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1520,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2520 ,protection_group_id: -1520, protection_element_id:-1520], primaryKey: false);
      insert('organizations', [ id: 101506, nci_institute_code: "AR024", name: "Genesis Cancer Center", city: "Hot Springs", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1521,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR024",GROUP_DESC:"AR024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1521,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1521,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2521 ,protection_group_id: -1521, protection_element_id:-1521], primaryKey: false);
      insert('organizations', [ id: 101507, nci_institute_code: "AR026", name: "Northwest Medical Center", city: "Springdale", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1522,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR026",GROUP_DESC:"AR026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1522,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1522,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2522 ,protection_group_id: -1522, protection_element_id:-1522], primaryKey: false);
      insert('organizations', [ id: 101508, nci_institute_code: "AR027", name: "Little Rock Cancer Clinic", city: "Little Rock", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1523,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR027",GROUP_DESC:"AR027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1523,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1523,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2523 ,protection_group_id: -1523, protection_element_id:-1523], primaryKey: false);
      insert('organizations', [ id: 101509, nci_institute_code: "AR028", name: "Little Rock Neurology Center", city: "Little Rock", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1524,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR028",GROUP_DESC:"AR028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1524,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1524,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2524 ,protection_group_id: -1524, protection_element_id:-1524], primaryKey: false);
      insert('organizations', [ id: 101510, nci_institute_code: "AR029", name: "Highlands Oncology Group - Springdale", city: "Springdale", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1525,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR029",GROUP_DESC:"AR029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1525,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1525,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2525 ,protection_group_id: -1525, protection_element_id:-1525], primaryKey: false);
      insert('organizations', [ id: 101511, nci_institute_code: "AR030", name: "Northeast Arkansas Surgical Clinic", city: "Jonesboro", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1526,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR030",GROUP_DESC:"AR030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1526,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1526,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2526 ,protection_group_id: -1526, protection_element_id:-1526], primaryKey: false);
      insert('organizations', [ id: 101512, nci_institute_code: "AR031", name: "North Arkansas Regional Medical Center", city: "Harrison", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1527,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR031",GROUP_DESC:"AR031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1527,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1527,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2527 ,protection_group_id: -1527, protection_element_id:-1527], primaryKey: false);
      insert('organizations', [ id: 101513, nci_institute_code: "AR032", name: "Breast Treatment Associates", city: "Fayetteville", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1528,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR032",GROUP_DESC:"AR032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1528,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1528,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2528 ,protection_group_id: -1528, protection_element_id:-1528], primaryKey: false);
      insert('organizations', [ id: 101514, nci_institute_code: "AR033", name: "Central AR Veterans Healthcare Sys", city: "Little Rock", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1529,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR033",GROUP_DESC:"AR033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1529,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1529,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2529 ,protection_group_id: -1529, protection_element_id:-1529], primaryKey: false);
      insert('organizations', [ id: 101515, nci_institute_code: "AR034", name: "Surgical Associates", city: "Fort Smith", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1530,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR034",GROUP_DESC:"AR034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1530,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1530,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2530 ,protection_group_id: -1530, protection_element_id:-1530], primaryKey: false);
      insert('organizations', [ id: 101516, nci_institute_code: "AR035", name: "Saint Vincent Doctors Hospital", city: "Little Rock", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1531,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR035",GROUP_DESC:"AR035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1531,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1531,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2531 ,protection_group_id: -1531, protection_element_id:-1531], primaryKey: false);
      insert('organizations', [ id: 101517, nci_institute_code: "AR036", name: "Jefferson Regional Medical Center", city: "Pine Bluff", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1532,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR036",GROUP_DESC:"AR036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1532,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1532,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2532 ,protection_group_id: -1532, protection_element_id:-1532], primaryKey: false);
      insert('organizations', [ id: 101518, nci_institute_code: "AR037", name: "Ozark Cancer Clinic", city: "Harrison", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1533,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR037",GROUP_DESC:"AR037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1533,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1533,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2533 ,protection_group_id: -1533, protection_element_id:-1533], primaryKey: false);
      insert('organizations', [ id: 101519, nci_institute_code: "AR038", name: "Baptist Health Medical Center", city: "Little Rock", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1534,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR038",GROUP_DESC:"AR038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1534,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1534,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2534 ,protection_group_id: -1534, protection_element_id:-1534], primaryKey: false);
      insert('organizations', [ id: 101520, nci_institute_code: "AR039", name: "Arkansas Cancer Institute", city: "Pine Bluff", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1535,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR039",GROUP_DESC:"AR039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1535,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1535,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2535 ,protection_group_id: -1535, protection_element_id:-1535], primaryKey: false);
      insert('organizations', [ id: 101521, nci_institute_code: "AR040", name: "Arkansas Urology", city: "Little Rock", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1536,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR040",GROUP_DESC:"AR040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1536,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1536,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2536 ,protection_group_id: -1536, protection_element_id:-1536], primaryKey: false);
      insert('organizations', [ id: 101522, nci_institute_code: "AR041", name: "Northwest Arkansas Clinic for Women", city: "Fayetteville", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1537,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR041",GROUP_DESC:"AR041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1537,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1537,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2537 ,protection_group_id: -1537, protection_element_id:-1537], primaryKey: false);
    }

    void m21() {
        // all21 (25 terms)
      insert('organizations', [ id: 101523, nci_institute_code: "AR042", name: "Arkansas Institute for Research and Education", city: "Fayetteville", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1538,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR042",GROUP_DESC:"AR042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1538,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1538,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2538 ,protection_group_id: -1538, protection_element_id:-1538], primaryKey: false);
      insert('organizations', [ id: 101524, nci_institute_code: "AR043", name: "Saint Joseph's Mercy Cancer Center", city: "Hot Springs", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1539,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR043",GROUP_DESC:"AR043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1539,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1539,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2539 ,protection_group_id: -1539, protection_element_id:-1539], primaryKey: false);
      insert('organizations', [ id: 101525, nci_institute_code: "AR044", name: "Mercy Health Center", city: "Bentonville", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1540,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR044",GROUP_DESC:"AR044 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1540,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR044",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR044",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1540,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR044", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2540 ,protection_group_id: -1540, protection_element_id:-1540], primaryKey: false);
      insert('organizations', [ id: 101526, nci_institute_code: "AR045", name: "Northwest Arkansas Urology Associates", city: "Fayetteville", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1541,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR045",GROUP_DESC:"AR045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1541,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1541,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2541 ,protection_group_id: -1541, protection_element_id:-1541], primaryKey: false);
      insert('organizations', [ id: 101527, nci_institute_code: "AR046", name: "Helping Oncology Patients Excel, Inc.", city: "Springdale", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1542,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR046",GROUP_DESC:"AR046 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1542,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR046",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR046",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1542,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR046", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2542 ,protection_group_id: -1542, protection_element_id:-1542], primaryKey: false);
      insert('organizations', [ id: 101528, nci_institute_code: "AR047", name: "NEA Medical Center", city: "Jonesboro", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1543,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR047",GROUP_DESC:"AR047 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1543,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR047",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR047",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1543,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR047", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2543 ,protection_group_id: -1543, protection_element_id:-1543], primaryKey: false);
      insert('organizations', [ id: 101529, nci_institute_code: "AR048", name: "Ozark Surgical Group", city: "Mountain Home", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1544,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR048",GROUP_DESC:"AR048 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1544,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR048",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR048",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1544,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR048", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2544 ,protection_group_id: -1544, protection_element_id:-1544], primaryKey: false);
      insert('organizations', [ id: 101530, nci_institute_code: "AR050", name: "Northwest Arkansas Radiation Therapy Institute - Springdale", city: "Springdale", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1545,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR050",GROUP_DESC:"AR050 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1545,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR050",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR050",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1545,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR050", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2545 ,protection_group_id: -1545, protection_element_id:-1545], primaryKey: false);
      insert('organizations', [ id: 101531, nci_institute_code: "AR051", name: "Arkansas Oklahoma Cancer Treatment Center", city: "Fort Smith", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1546,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR051",GROUP_DESC:"AR051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1546,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1546,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2546 ,protection_group_id: -1546, protection_element_id:-1546], primaryKey: false);
      insert('organizations', [ id: 101532, nci_institute_code: "AR052", name: "Washington Regional Specialty Clinic", city: "Fayetteville", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1547,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR052",GROUP_DESC:"AR052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1547,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1547,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2547 ,protection_group_id: -1547, protection_element_id:-1547], primaryKey: false);
      insert('organizations', [ id: 101533, nci_institute_code: "AR053", name: "Hematology Oncology Service of Arkansas", city: "Little Rock", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1548,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR053",GROUP_DESC:"AR053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1548,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1548,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2548 ,protection_group_id: -1548, protection_element_id:-1548], primaryKey: false);
      insert('organizations', [ id: 101534, nci_institute_code: "AUSTLA", name: "Australian Leukemia Study Group", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1549,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AUSTLA",GROUP_DESC:"AUSTLA group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1549,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AUSTLA",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AUSTLA",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1549,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AUSTLA", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2549 ,protection_group_id: -1549, protection_element_id:-1549], primaryKey: false);
      insert('organizations', [ id: 101535, nci_institute_code: "AZ001", name: "Memorial Hospital", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1550,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ001",GROUP_DESC:"AZ001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1550,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1550,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2550 ,protection_group_id: -1550, protection_element_id:-1550], primaryKey: false);
      insert('organizations', [ id: 101536, nci_institute_code: "AZ002", name: "Banner Good Samaritan Medical Center", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1551,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ002",GROUP_DESC:"AZ002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1551,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1551,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2551 ,protection_group_id: -1551, protection_element_id:-1551], primaryKey: false);
      insert('organizations', [ id: 101537, nci_institute_code: "AZ003", name: "St. Lukes Hospital Medical Center", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1552,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ003",GROUP_DESC:"AZ003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1552,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1552,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2552 ,protection_group_id: -1552, protection_element_id:-1552], primaryKey: false);
      insert('organizations', [ id: 101538, nci_institute_code: "AZ004", name: "Phoenix Childrens Hospital", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1553,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ004",GROUP_DESC:"AZ004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1553,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1553,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2553 ,protection_group_id: -1553, protection_element_id:-1553], primaryKey: false);
      insert('organizations', [ id: 101539, nci_institute_code: "AZ005", name: "Maricopa Medical Center", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1554,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ005",GROUP_DESC:"AZ005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1554,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1554,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2554 ,protection_group_id: -1554, protection_element_id:-1554], primaryKey: false);
      insert('organizations', [ id: 101540, nci_institute_code: "AZ006", name: "Phoenix Indian Medical Center", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1555,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ006",GROUP_DESC:"AZ006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1555,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1555,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2555 ,protection_group_id: -1555, protection_element_id:-1555], primaryKey: false);
      insert('organizations', [ id: 101541, nci_institute_code: "AZ007", name: "Veterans Administration Medical Center", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1556,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ007",GROUP_DESC:"AZ007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1556,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1556,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2556 ,protection_group_id: -1556, protection_element_id:-1556], primaryKey: false);
      insert('organizations', [ id: 101542, nci_institute_code: "AZ008", name: "Banner Baywood Medical Center", city: "Mesa", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1557,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ008",GROUP_DESC:"AZ008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1557,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1557,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2557 ,protection_group_id: -1557, protection_element_id:-1557], primaryKey: false);
      insert('organizations', [ id: 101543, nci_institute_code: "AZ009", name: "Saint Joseph's Hospital", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1558,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ009",GROUP_DESC:"AZ009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1558,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1558,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2558 ,protection_group_id: -1558, protection_element_id:-1558], primaryKey: false);
      insert('organizations', [ id: 101544, nci_institute_code: "AZ010", name: "Maryvale Samaritan Medical Center", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1559,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ010",GROUP_DESC:"AZ010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1559,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1559,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2559 ,protection_group_id: -1559, protection_element_id:-1559], primaryKey: false);
      insert('organizations', [ id: 101545, nci_institute_code: "AZ011", name: "Phoenix General Hospital", city: "Phoeniz", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1560,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ011",GROUP_DESC:"AZ011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1560,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1560,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2560 ,protection_group_id: -1560, protection_element_id:-1560], primaryKey: false);
      insert('organizations', [ id: 101546, nci_institute_code: "AZ012", name: "Mesa Lutheran Hospital", city: "Mesa", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1561,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ012",GROUP_DESC:"AZ012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1561,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1561,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2561 ,protection_group_id: -1561, protection_element_id:-1561], primaryKey: false);
      insert('organizations', [ id: 101547, nci_institute_code: "AZ013", name: "S.W. Biomedical Research Inst.", city: "Tempe", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1562,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ013",GROUP_DESC:"AZ013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1562,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1562,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2562 ,protection_group_id: -1562, protection_element_id:-1562], primaryKey: false);
    }

    void m22() {
        // all22 (25 terms)
      insert('organizations', [ id: 101548, nci_institute_code: "AZ014", name: "Terra Southern Medical Center", city: "Tempe", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1563,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ014",GROUP_DESC:"AZ014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1563,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1563,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2563 ,protection_group_id: -1563, protection_element_id:-1563], primaryKey: false);
      insert('organizations', [ id: 101549, nci_institute_code: "AZ015", name: "Thomas Davis Clinic", city: "Tucson", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1564,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ015",GROUP_DESC:"AZ015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1564,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1564,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2564 ,protection_group_id: -1564, protection_element_id:-1564], primaryKey: false);
      insert('organizations', [ id: 101550, nci_institute_code: "AZ016", name: "Southern Arizona Veterans Affairs Health Center", city: "Tucson", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1565,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ016",GROUP_DESC:"AZ016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1565,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1565,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2565 ,protection_group_id: -1565, protection_element_id:-1565], primaryKey: false);
      insert('organizations', [ id: 101551, nci_institute_code: "AZ017", name: "University of Arizona Health Sciences Center", city: "Tucson", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1566,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ017",GROUP_DESC:"AZ017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1566,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1566,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2566 ,protection_group_id: -1566, protection_element_id:-1566], primaryKey: false);
      insert('organizations', [ id: 101552, nci_institute_code: "AZ018", name: "Tucson Medical Center", city: "Tucson", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1567,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ018",GROUP_DESC:"AZ018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1567,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1567,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2567 ,protection_group_id: -1567, protection_element_id:-1567], primaryKey: false);
      insert('organizations', [ id: 101553, nci_institute_code: "AZ019", name: "W O Boswell Memorial Hospital", city: "Sun City", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1568,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ019",GROUP_DESC:"AZ019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1568,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1568,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2568 ,protection_group_id: -1568, protection_element_id:-1568], primaryKey: false);
      insert('organizations', [ id: 101554, nci_institute_code: "AZ020", name: "Mayo Clinic Scottsdale-Phoenix", city: "Scottsdale", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1569,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ020",GROUP_DESC:"AZ020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1569,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1569,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2569 ,protection_group_id: -1569, protection_element_id:-1569], primaryKey: false);
      insert('organizations', [ id: 101555, nci_institute_code: "AZ021", name: "Urology Associates, Ltd.", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1570,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ021",GROUP_DESC:"AZ021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1570,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1570,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2570 ,protection_group_id: -1570, protection_element_id:-1570], primaryKey: false);
      insert('organizations', [ id: 101556, nci_institute_code: "AZ022", name: "Community Hosp Medical Center", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1571,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ022",GROUP_DESC:"AZ022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1571,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1571,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2571 ,protection_group_id: -1571, protection_element_id:-1571], primaryKey: false);
      insert('organizations', [ id: 101557, nci_institute_code: "AZ023", name: "Carondelet Saint Mary'S", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1572,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ023",GROUP_DESC:"AZ023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1572,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1572,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2572 ,protection_group_id: -1572, protection_element_id:-1572], primaryKey: false);
      insert('organizations', [ id: 101558, nci_institute_code: "AZ024", name: "Casa Blanca Clinic", city: "Gilbert", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1573,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ024",GROUP_DESC:"AZ024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1573,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1573,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2573 ,protection_group_id: -1573, protection_element_id:-1573], primaryKey: false);
      insert('organizations', [ id: 101559, nci_institute_code: "AZ025", name: "MJ Lawrence Memorial Hospital", city: "Cottonwood", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1574,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ025",GROUP_DESC:"AZ025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1574,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1574,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2574 ,protection_group_id: -1574, protection_element_id:-1574], primaryKey: false);
      insert('organizations', [ id: 101560, nci_institute_code: "AZ026", name: "Scottsdale Healthcare", city: "Scottsdale", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1575,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ026",GROUP_DESC:"AZ026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1575,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1575,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2575 ,protection_group_id: -1575, protection_element_id:-1575], primaryKey: false);
      insert('organizations', [ id: 101561, nci_institute_code: "AZ027", name: "Arizona Cancer Center", city: "Tucson", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1576,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ027",GROUP_DESC:"AZ027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1576,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1576,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2576 ,protection_group_id: -1576, protection_element_id:-1576], primaryKey: false);
      insert('organizations', [ id: 101562, nci_institute_code: "AZ028", name: "Flagstaff Medical Center", city: "Flagstaff", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1577,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ028",GROUP_DESC:"AZ028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1577,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1577,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2577 ,protection_group_id: -1577, protection_element_id:-1577], primaryKey: false);
      insert('organizations', [ id: 101563, nci_institute_code: "AZ029", name: "Banner Desert Medical Center", city: "Mesa", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1578,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ029",GROUP_DESC:"AZ029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1578,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1578,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2578 ,protection_group_id: -1578, protection_element_id:-1578], primaryKey: false);
      insert('organizations', [ id: 101564, nci_institute_code: "AZ030", name: "Cancer Care Center/S. Arizona", city: "Tucson", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1579,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ030",GROUP_DESC:"AZ030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1579,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1579,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2579 ,protection_group_id: -1579, protection_element_id:-1579], primaryKey: false);
      insert('organizations', [ id: 101565, nci_institute_code: "AZ031", name: "Western Regional CCOP", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1580,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ031",GROUP_DESC:"AZ031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1580,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1580,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2580 ,protection_group_id: -1580, protection_element_id:-1580], primaryKey: false);
      insert('organizations', [ id: 101566, nci_institute_code: "AZ032", name: "Paradise Valley Hospital", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1581,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ032",GROUP_DESC:"AZ032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1581,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1581,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2581 ,protection_group_id: -1581, protection_element_id:-1581], primaryKey: false);
      insert('organizations', [ id: 101567, nci_institute_code: "AZ033", name: "Southwest Radiation", city: "Tucson", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1582,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ033",GROUP_DESC:"AZ033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1582,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1582,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2582 ,protection_group_id: -1582, protection_element_id:-1582], primaryKey: false);
      insert('organizations', [ id: 101568, nci_institute_code: "AZ034", name: "Banner Thunderbird Medical Center", city: "Glandale", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1583,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ034",GROUP_DESC:"AZ034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1583,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1583,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2583 ,protection_group_id: -1583, protection_element_id:-1583], primaryKey: false);
      insert('organizations', [ id: 101569, nci_institute_code: "AZ038", name: "Cigna Health Plan", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1584,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ038",GROUP_DESC:"AZ038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1584,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1584,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2584 ,protection_group_id: -1584, protection_element_id:-1584], primaryKey: false);
      insert('organizations', [ id: 101570, nci_institute_code: "AZ039", name: "Valley Radiology Oncology  Limited", city: "Glendale", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1585,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ039",GROUP_DESC:"AZ039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1585,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1585,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2585 ,protection_group_id: -1585, protection_element_id:-1585], primaryKey: false);
      insert('organizations', [ id: 101571, nci_institute_code: "AZ040", name: "Hematology Oncology Association", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1586,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ040",GROUP_DESC:"AZ040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1586,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1586,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2586 ,protection_group_id: -1586, protection_element_id:-1586], primaryKey: false);
      insert('organizations', [ id: 101572, nci_institute_code: "AZ041", name: "Valley Medical Specialists", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1587,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ041",GROUP_DESC:"AZ041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1587,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1587,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2587 ,protection_group_id: -1587, protection_element_id:-1587], primaryKey: false);
    }

    void m23() {
        // all23 (25 terms)
      insert('organizations', [ id: 101573, nci_institute_code: "AZ042", name: "Desert Oncology PC", city: "Mesa", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1588,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ042",GROUP_DESC:"AZ042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1588,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1588,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2588 ,protection_group_id: -1588, protection_element_id:-1588], primaryKey: false);
      insert('organizations', [ id: 101574, nci_institute_code: "AZ044", name: "Arizona Oncology Service, Incorporated", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1589,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ044",GROUP_DESC:"AZ044 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1589,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ044",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ044",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1589,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ044", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2589 ,protection_group_id: -1589, protection_element_id:-1589], primaryKey: false);
      insert('organizations', [ id: 101575, nci_institute_code: "AZ045", name: "Northwest Hospital", city: "Tucson", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1590,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ045",GROUP_DESC:"AZ045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1590,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1590,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2590 ,protection_group_id: -1590, protection_element_id:-1590], primaryKey: false);
      insert('organizations', [ id: 101576, nci_institute_code: "AZ046", name: "Southwest Oncology Centers, Limited", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1591,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ046",GROUP_DESC:"AZ046 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1591,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ046",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ046",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1591,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ046", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2591 ,protection_group_id: -1591, protection_element_id:-1591], primaryKey: false);
      insert('organizations', [ id: 101577, nci_institute_code: "AZ047", name: "Internists Oncologist Limited", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1592,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ047",GROUP_DESC:"AZ047 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1592,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ047",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ047",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1592,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ047", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2592 ,protection_group_id: -1592, protection_element_id:-1592], primaryKey: false);
      insert('organizations', [ id: 101578, nci_institute_code: "AZ048", name: "Northwest Cancer Center", city: "Tucson", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1593,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ048",GROUP_DESC:"AZ048 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1593,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ048",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ048",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1593,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ048", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2593 ,protection_group_id: -1593, protection_element_id:-1593], primaryKey: false);
      insert('organizations', [ id: 101579, nci_institute_code: "AZ049", name: "Southwest Medical Oncologist, P.C.", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1594,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ049",GROUP_DESC:"AZ049 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1594,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ049",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ049",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1594,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ049", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2594 ,protection_group_id: -1594, protection_element_id:-1594], primaryKey: false);
      insert('organizations', [ id: 101580, nci_institute_code: "AZ050", name: "Arrowhead Cancer Center", city: "Glendale", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1595,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ050",GROUP_DESC:"AZ050 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1595,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ050",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ050",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1595,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ050", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2595 ,protection_group_id: -1595, protection_element_id:-1595], primaryKey: false);
      insert('organizations', [ id: 101581, nci_institute_code: "AZ051", name: "Palo Verde Hematology/Oncology", city: "Glendale", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1596,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ051",GROUP_DESC:"AZ051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1596,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1596,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2596 ,protection_group_id: -1596, protection_element_id:-1596], primaryKey: false);
      insert('organizations', [ id: 101582, nci_institute_code: "AZ052", name: "Arizona Oncology Associates", city: "Tucson", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1597,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ052",GROUP_DESC:"AZ052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1597,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1597,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2597 ,protection_group_id: -1597, protection_element_id:-1597], primaryKey: false);
      insert('organizations', [ id: 101583, nci_institute_code: "AZ053", name: "Northern Arizona Hematology and Oncology Associates., P.C.", city: "Flagstaff", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1598,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ053",GROUP_DESC:"AZ053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1598,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1598,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2598 ,protection_group_id: -1598, protection_element_id:-1598], primaryKey: false);
      insert('organizations', [ id: 101584, nci_institute_code: "AZ054", name: "Chandler Regional Hospital", city: "Chandler", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1599,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ054",GROUP_DESC:"AZ054 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1599,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ054",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ054",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1599,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ054", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2599 ,protection_group_id: -1599, protection_element_id:-1599], primaryKey: false);
      insert('organizations', [ id: 101585, nci_institute_code: "AZ055", name: "Tucson Breast Center", city: "Tucson", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1600,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ055",GROUP_DESC:"AZ055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1600,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1600,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2600 ,protection_group_id: -1600, protection_element_id:-1600], primaryKey: false);
      insert('organizations', [ id: 101586, nci_institute_code: "AZ056", name: "Arizona Gynecology Oncology", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1601,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ056",GROUP_DESC:"AZ056 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1601,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ056",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ056",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1601,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ056", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2601 ,protection_group_id: -1601, protection_element_id:-1601], primaryKey: false);
      insert('organizations', [ id: 101587, nci_institute_code: "AZ057", name: "Arizona Oncology Services, East Valley", city: "Mesa", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1602,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ057",GROUP_DESC:"AZ057 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1602,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ057",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ057",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1602,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ057", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2602 ,protection_group_id: -1602, protection_element_id:-1602], primaryKey: false);
      insert('organizations', [ id: 101588, nci_institute_code: "AZ058", name: "Urology Specialists, Limited", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1603,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ058",GROUP_DESC:"AZ058 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1603,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ058",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ058",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1603,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ058", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2603 ,protection_group_id: -1603, protection_element_id:-1603], primaryKey: false);
      insert('organizations', [ id: 101589, nci_institute_code: "AZ059", name: "Arizona Clinical Research Center", city: "Tucson", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1604,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ059",GROUP_DESC:"AZ059 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1604,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ059",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ059",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1604,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ059", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2604 ,protection_group_id: -1604, protection_element_id:-1604], primaryKey: false);
      insert('organizations', [ id: 101590, nci_institute_code: "AZ060", name: "Baptist Oncology Center", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1605,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ060",GROUP_DESC:"AZ060 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1605,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ060",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ060",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1605,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ060", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2605 ,protection_group_id: -1605, protection_element_id:-1605], primaryKey: false);
      insert('organizations', [ id: 101591, nci_institute_code: "AZ061", name: "Verde Valley Medical Center", city: "Cottonwood", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1606,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ061",GROUP_DESC:"AZ061 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1606,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ061",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ061",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1606,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ061", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2606 ,protection_group_id: -1606, protection_element_id:-1606], primaryKey: false);
      insert('organizations', [ id: 101592, nci_institute_code: "AZ062", name: "Yavapai Regional Medical Ctr", city: "Prescott", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1607,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ062",GROUP_DESC:"AZ062 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1607,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ062",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ062",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1607,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ062", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2607 ,protection_group_id: -1607, protection_element_id:-1607], primaryKey: false);
      insert('organizations', [ id: 101593, nci_institute_code: "AZ063", name: "Payson Regional Medical Ctr", city: "Payson", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1608,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ063",GROUP_DESC:"AZ063 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1608,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ063",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ063",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1608,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ063", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2608 ,protection_group_id: -1608, protection_element_id:-1608], primaryKey: false);
      insert('organizations', [ id: 101594, nci_institute_code: "AZ064", name: "John C. Lincoln Hospital", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1609,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ064",GROUP_DESC:"AZ064 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1609,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ064",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ064",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1609,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ064", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2609 ,protection_group_id: -1609, protection_element_id:-1609], primaryKey: false);
      insert('organizations', [ id: 101595, nci_institute_code: "AZ065", name: "Sedona Medical Center", city: "Sedona", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1610,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ065",GROUP_DESC:"AZ065 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1610,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ065",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ065",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1610,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ065", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2610 ,protection_group_id: -1610, protection_element_id:-1610], primaryKey: false);
      insert('organizations', [ id: 101596, nci_institute_code: "AZ066", name: "Scottsdale Health Care-Osborn", city: "Scottsdale", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1611,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ066",GROUP_DESC:"AZ066 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1611,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ066",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ066",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1611,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ066", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2611 ,protection_group_id: -1611, protection_element_id:-1611], primaryKey: false);
      insert('organizations', [ id: 101597, nci_institute_code: "AZ067", name: "Scottsdale Medical Group", city: "Scottsdale", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1612,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ067",GROUP_DESC:"AZ067 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1612,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ067",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ067",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1612,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ067", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2612 ,protection_group_id: -1612, protection_element_id:-1612], primaryKey: false);
    }

    void m24() {
        // all24 (25 terms)
      insert('organizations', [ id: 101598, nci_institute_code: "AZ068", name: "Arizona Oncology Services Foundation", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1613,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ068",GROUP_DESC:"AZ068 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1613,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ068",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ068",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1613,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ068", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2613 ,protection_group_id: -1613, protection_element_id:-1613], primaryKey: false);
      insert('organizations', [ id: 101599, nci_institute_code: "AZ069", name: "Arizona Oncology Associates", city: "Yuma", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1614,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ069",GROUP_DESC:"AZ069 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1614,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ069",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ069",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1614,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ069", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2614 ,protection_group_id: -1614, protection_element_id:-1614], primaryKey: false);
      insert('organizations', [ id: 101600, nci_institute_code: "AZ071", name: "Arizona Health Sciences Center", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1615,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ071",GROUP_DESC:"AZ071 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1615,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ071",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ071",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1615,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ071", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2615 ,protection_group_id: -1615, protection_element_id:-1615], primaryKey: false);
      insert('organizations', [ id: 101601, nci_institute_code: "AZ073", name: "Mayo Clinic Hospital", city: "Phoeniz", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1616,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ073",GROUP_DESC:"AZ073 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1616,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ073",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ073",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1616,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ073", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2616 ,protection_group_id: -1616, protection_element_id:-1616], primaryKey: false);
      insert('organizations', [ id: 101602, nci_institute_code: "AZ074", name: "Southwest Oncology & Hematology", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1617,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ074",GROUP_DESC:"AZ074 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1617,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ074",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ074",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1617,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ074", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2617 ,protection_group_id: -1617, protection_element_id:-1617], primaryKey: false);
      insert('organizations', [ id: 101603, nci_institute_code: "AZ075", name: "Phoenix Memorial Hospital", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1618,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ075",GROUP_DESC:"AZ075 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1618,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ075",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ075",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1618,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ075", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2618 ,protection_group_id: -1618, protection_element_id:-1618], primaryKey: false);
      insert('organizations', [ id: 101604, nci_institute_code: "AZ076", name: "Kingman Regional Medical Center", city: "Kingman", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1619,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ076",GROUP_DESC:"AZ076 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1619,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ076",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ076",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1619,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ076", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2619 ,protection_group_id: -1619, protection_element_id:-1619], primaryKey: false);
      insert('organizations', [ id: 101605, nci_institute_code: "AZ077", name: "Scottsdale Healthcare-Shea", city: "Scottsdale", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1620,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ077",GROUP_DESC:"AZ077 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1620,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ077",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ077",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1620,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ077", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2620 ,protection_group_id: -1620, protection_element_id:-1620], primaryKey: false);
      insert('organizations', [ id: 101606, nci_institute_code: "AZ078", name: "Affiliated Oncologists Ltd", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1621,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ078",GROUP_DESC:"AZ078 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1621,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ078",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ078",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1621,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ078", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2621 ,protection_group_id: -1621, protection_element_id:-1621], primaryKey: false);
      insert('organizations', [ id: 101607, nci_institute_code: "AZ079", name: "Cancer Resource Network", city: "Mesa", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1622,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ079",GROUP_DESC:"AZ079 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1622,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ079",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ079",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1622,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ079", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2622 ,protection_group_id: -1622, protection_element_id:-1622], primaryKey: false);
      insert('organizations', [ id: 101608, nci_institute_code: "AZ080", name: "Arizona Medical Clinic", city: "Peoria", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1623,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ080",GROUP_DESC:"AZ080 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1623,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ080",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ080",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1623,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ080", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2623 ,protection_group_id: -1623, protection_element_id:-1623], primaryKey: false);
      insert('organizations', [ id: 101609, nci_institute_code: "AZ081", name: "Yuma Cancer Center", city: "Yuma", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1624,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ081",GROUP_DESC:"AZ081 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1624,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ081",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ081",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1624,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ081", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2624 ,protection_group_id: -1624, protection_element_id:-1624], primaryKey: false);
      insert('organizations', [ id: 101610, nci_institute_code: "AZ082", name: "Arizona Cancer Center", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1625,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ082",GROUP_DESC:"AZ082 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1625,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ082",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ082",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1625,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ082", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2625 ,protection_group_id: -1625, protection_element_id:-1625], primaryKey: false);
      insert('organizations', [ id: 101611, nci_institute_code: "AZ083", name: "Valley Oncology", city: "Chandler", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1626,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ083",GROUP_DESC:"AZ083 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1626,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ083",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ083",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1626,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ083", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2626 ,protection_group_id: -1626, protection_element_id:-1626], primaryKey: false);
      insert('organizations', [ id: 101612, nci_institute_code: "AZ084", name: "Scottsdale Medical Imaging, Ltd.", city: "Scottsdale", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1627,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ084",GROUP_DESC:"AZ084 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1627,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ084",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ084",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1627,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ084", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2627 ,protection_group_id: -1627, protection_element_id:-1627], primaryKey: false);
      insert('organizations', [ id: 101613, nci_institute_code: "AZ085", name: "Sun Health Research Institute", city: "Sun City", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1628,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ085",GROUP_DESC:"AZ085 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1628,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ085",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ085",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1628,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ085", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2628 ,protection_group_id: -1628, protection_element_id:-1628], primaryKey: false);
      insert('organizations', [ id: 101614, nci_institute_code: "AZ086", name: "Barrow Neurology Clinics", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1629,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ086",GROUP_DESC:"AZ086 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1629,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ086",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ086",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1629,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ086", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2629 ,protection_group_id: -1629, protection_element_id:-1629], primaryKey: false);
      insert('organizations', [ id: 101615, nci_institute_code: "AZ087", name: "Associates in Radiation Oncology", city: "Sun City", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1630,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ087",GROUP_DESC:"AZ087 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1630,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ087",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ087",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1630,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ087", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2630 ,protection_group_id: -1630, protection_element_id:-1630], primaryKey: false);
      insert('organizations', [ id: 101616, nci_institute_code: "AZ088", name: "Central Arizona Urologists", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1631,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ088",GROUP_DESC:"AZ088 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1631,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ088",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ088",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1631,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ088", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2631 ,protection_group_id: -1631, protection_element_id:-1631], primaryKey: false);
      insert('organizations', [ id: 101617, nci_institute_code: "AZ089", name: "Gynecologic Oncology Group of Arizona", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1632,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ089",GROUP_DESC:"AZ089 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1632,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ089",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ089",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1632,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ089", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2632 ,protection_group_id: -1632, protection_element_id:-1632], primaryKey: false);
      insert('organizations', [ id: 101618, nci_institute_code: "AZ090", name: "Southwest Oncology & Hematology P.C.", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1633,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ090",GROUP_DESC:"AZ090 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1633,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ090",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ090",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1633,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ090", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2633 ,protection_group_id: -1633, protection_element_id:-1633], primaryKey: false);
      insert('organizations', [ id: 101619, nci_institute_code: "AZ092", name: "Virginia G. Piper Cancer Center", city: "Scottsdale", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1634,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ092",GROUP_DESC:"AZ092 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1634,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ092",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ092",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1634,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ092", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2634 ,protection_group_id: -1634, protection_element_id:-1634], primaryKey: false);
      insert('organizations', [ id: 101620, nci_institute_code: "AZ094", name: "Oncogyne PC", city: "Scottsdale", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1635,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ094",GROUP_DESC:"AZ094 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1635,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ094",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ094",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1635,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ094", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2635 ,protection_group_id: -1635, protection_element_id:-1635], primaryKey: false);
      insert('organizations', [ id: 101621, nci_institute_code: "AZ095", name: "Arizona Cancer Care-Scottsdale", city: "Scottsdale", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1636,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ095",GROUP_DESC:"AZ095 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1636,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ095",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ095",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1636,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ095", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2636 ,protection_group_id: -1636, protection_element_id:-1636], primaryKey: false);
      insert('organizations', [ id: 101622, nci_institute_code: "AZ096", name: "Tricity ColoRectal Surgery", city: "Mesa", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1637,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ096",GROUP_DESC:"AZ096 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1637,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ096",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ096",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1637,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ096", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2637 ,protection_group_id: -1637, protection_element_id:-1637], primaryKey: false);
    }

    void m25() {
        // all25 (25 terms)
      insert('organizations', [ id: 101623, nci_institute_code: "AZ097", name: "Arizona State University", city: "Tempe", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1638,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ097",GROUP_DESC:"AZ097 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1638,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ097",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ097",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1638,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ097", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2638 ,protection_group_id: -1638, protection_element_id:-1638], primaryKey: false);
      insert('organizations', [ id: 101624, nci_institute_code: "AZ098", name: "Southwest Hematology Oncology", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1639,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ098",GROUP_DESC:"AZ098 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1639,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ098",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ098",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1639,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ098", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2639 ,protection_group_id: -1639, protection_element_id:-1639], primaryKey: false);
      insert('organizations', [ id: 101625, nci_institute_code: "AZ099", name: "Arizona Pediatric Hematology/Oncology", city: "Mesa", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1640,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ099",GROUP_DESC:"AZ099 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1640,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ099",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ099",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1640,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ099", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2640 ,protection_group_id: -1640, protection_element_id:-1640], primaryKey: false);
      insert('organizations', [ id: 101626, nci_institute_code: "AZ100", name: "Translational Genomics Research Institute", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1641,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ100",GROUP_DESC:"AZ100 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1641,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ100",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ100",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1641,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ100", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2641 ,protection_group_id: -1641, protection_element_id:-1641], primaryKey: false);
      insert('organizations', [ id: 101627, nci_institute_code: "AZ101", name: "Ironwood Cancer and Research Center - Chandler", city: "Chandler", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1642,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ101",GROUP_DESC:"AZ101 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1642,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ101",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ101",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1642,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ101", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2642 ,protection_group_id: -1642, protection_element_id:-1642], primaryKey: false);
      insert('organizations', [ id: 101628, nci_institute_code: "AZ102", name: "Western Radiation Oncology Ltd", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1643,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ102",GROUP_DESC:"AZ102 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1643,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ102",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ102",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1643,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ102", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2643 ,protection_group_id: -1643, protection_element_id:-1643], primaryKey: false);
      insert('organizations', [ id: 101629, nci_institute_code: "AZ103", name: "Premiere Oncology of Arizona", city: "Scottsdale", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1644,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ103",GROUP_DESC:"AZ103 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1644,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ103",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ103",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1644,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ103", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2644 ,protection_group_id: -1644, protection_element_id:-1644], primaryKey: false);
      insert('organizations', [ id: 101630, nci_institute_code: "AZ104", name: "Banner Health Research Institute", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1645,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ104",GROUP_DESC:"AZ104 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1645,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ104",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ104",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1645,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ104", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2645 ,protection_group_id: -1645, protection_element_id:-1645], primaryKey: false);
      insert('organizations', [ id: 101631, nci_institute_code: "AZ105", name: "Banner's Children's Hospital at Banner Desert Medical Center", city: "Mesa", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1646,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ105",GROUP_DESC:"AZ105 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1646,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ105",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ105",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1646,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ105", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2646 ,protection_group_id: -1646, protection_element_id:-1646], primaryKey: false);
      insert('organizations', [ id: 101632, nci_institute_code: "AZ106", name: "Desert Oncology PC", city: "Mesa", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1647,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ106",GROUP_DESC:"AZ106 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1647,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ106",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ106",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1647,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ106", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2647 ,protection_group_id: -1647, protection_element_id:-1647], primaryKey: false);
      insert('organizations', [ id: 101633, nci_institute_code: "AZ107", name: "Pediatric Urology Associates", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1648,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ107",GROUP_DESC:"AZ107 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1648,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ107",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ107",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1648,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ107", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2648 ,protection_group_id: -1648, protection_element_id:-1648], primaryKey: false);
      insert('organizations', [ id: 101634, nci_institute_code: "AZ108", name: "Arizona Center for Hematology and Oncology PLC", city: "Glendale", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1649,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ108",GROUP_DESC:"AZ108 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1649,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ108",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ108",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1649,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ108", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2649 ,protection_group_id: -1649, protection_element_id:-1649], primaryKey: false);
      insert('organizations', [ id: 101635, nci_institute_code: "AZ109", name: "Desert Oasis Cancer Center", city: "Casa Grande", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1650,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ109",GROUP_DESC:"AZ109 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1650,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ109",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ109",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1650,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ109", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2650 ,protection_group_id: -1650, protection_element_id:-1650], primaryKey: false);
      insert('organizations', [ id: 101636, nci_institute_code: "AZ110", name: "Arizona Cancer Center at University Medical Center North", city: "Tucson", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1651,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ110",GROUP_DESC:"AZ110 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1651,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ110",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ110",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1651,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ110", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2651 ,protection_group_id: -1651, protection_element_id:-1651], primaryKey: false);
      insert('organizations', [ id: 101637, nci_institute_code: "AZ111", name: "Arizona Oncology Associates-West Orange Grove", city: "Tucson", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1652,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ111",GROUP_DESC:"AZ111 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1652,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ111",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ111",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1652,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ111", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2652 ,protection_group_id: -1652, protection_element_id:-1652], primaryKey: false);
      insert('organizations', [ id: 101638, nci_institute_code: "CA001", name: "John Wesley County Hospital", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1653,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA001",GROUP_DESC:"CA001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1653,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1653,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2653 ,protection_group_id: -1653, protection_element_id:-1653], primaryKey: false);
      insert('organizations', [ id: 101639, nci_institute_code: "CA002", name: "California Hospital Medical Center", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1654,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA002",GROUP_DESC:"CA002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1654,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1654,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2654 ,protection_group_id: -1654, protection_element_id:-1654], primaryKey: false);
      insert('organizations', [ id: 101640, nci_institute_code: "CA003", name: "Hospital Good Samaritan", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1655,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA003",GROUP_DESC:"CA003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1655,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1655,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2655 ,protection_group_id: -1655, protection_element_id:-1655], primaryKey: false);
      insert('organizations', [ id: 101641, nci_institute_code: "CA004", name: "Santa Fe Hospital", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1656,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA004",GROUP_DESC:"CA004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1656,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1656,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2656 ,protection_group_id: -1656, protection_element_id:-1656], primaryKey: false);
      insert('organizations', [ id: 101642, nci_institute_code: "CA005", name: "John Wayne Clinic", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1657,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA005",GROUP_DESC:"CA005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1657,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1657,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2657 ,protection_group_id: -1657, protection_element_id:-1657], primaryKey: false);
      insert('organizations', [ id: 101643, nci_institute_code: "CA006", name: "University of California at Los Angeles (UCLA )", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1658,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA006",GROUP_DESC:"CA006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1658,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1658,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2658 ,protection_group_id: -1658, protection_element_id:-1658], primaryKey: false);
      insert('organizations', [ id: 101644, nci_institute_code: "CA007", name: "Queen of Angels Hospital", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1659,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA007",GROUP_DESC:"CA007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1659,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1659,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2659 ,protection_group_id: -1659, protection_element_id:-1659], primaryKey: false);
      insert('organizations', [ id: 101645, nci_institute_code: "CA008", name: "Kaiser Foundation Hospital", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1660,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA008",GROUP_DESC:"CA008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1660,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1660,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2660 ,protection_group_id: -1660, protection_element_id:-1660], primaryKey: false);
      insert('organizations', [ id: 101646, nci_institute_code: "CA009", name: "Children's Hospital of Los Angeles", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1661,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA009",GROUP_DESC:"CA009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1661,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1661,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2661 ,protection_group_id: -1661, protection_element_id:-1661], primaryKey: false);
      insert('organizations', [ id: 101647, nci_institute_code: "CA010", name: "Southern California Permanente Medical Group", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1662,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA010",GROUP_DESC:"CA010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1662,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1662,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2662 ,protection_group_id: -1662, protection_element_id:-1662], primaryKey: false);
    }

    void m26() {
        // all26 (25 terms)
      insert('organizations', [ id: 101648, nci_institute_code: "CA011", name: "University of Southern California", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1663,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA011",GROUP_DESC:"CA011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1663,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1663,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2663 ,protection_group_id: -1663, protection_element_id:-1663], primaryKey: false);
      insert('organizations', [ id: 101649, nci_institute_code: "CA012", name: "Los Angeles General Hospital", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1664,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA012",GROUP_DESC:"CA012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1664,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1664,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2664 ,protection_group_id: -1664, protection_element_id:-1664], primaryKey: false);
      insert('organizations', [ id: 101650, nci_institute_code: "CA015", name: "White Memorial Medical Center", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1665,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA015",GROUP_DESC:"CA015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1665,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1665,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2665 ,protection_group_id: -1665, protection_element_id:-1665], primaryKey: false);
      insert('organizations', [ id: 101651, nci_institute_code: "CA016", name: "Cedars-Sinai Medical Center", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1666,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA016",GROUP_DESC:"CA016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1666,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1666,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2666 ,protection_group_id: -1666, protection_element_id:-1666], primaryKey: false);
      insert('organizations', [ id: 101652, nci_institute_code: "CA017", name: "Los Angeles Oncology Institute", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1667,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA017",GROUP_DESC:"CA017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1667,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1667,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2667 ,protection_group_id: -1667, protection_element_id:-1667], primaryKey: false);
      insert('organizations', [ id: 101653, nci_institute_code: "CA018", name: "Martin L King, Jr. General Hospital", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1668,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA018",GROUP_DESC:"CA018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1668,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1668,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2668 ,protection_group_id: -1668, protection_element_id:-1668], primaryKey: false);
      insert('organizations', [ id: 101654, nci_institute_code: "CA019", name: "Wadsworth Veterans Administration Hospital", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1669,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA019",GROUP_DESC:"CA019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1669,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1669,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2669 ,protection_group_id: -1669, protection_element_id:-1669], primaryKey: false);
      insert('organizations', [ id: 101655, nci_institute_code: "CA020", name: "Brotman Medical Center", city: "Culver City", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1670,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA020",GROUP_DESC:"CA020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1670,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1670,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2670 ,protection_group_id: -1670, protection_element_id:-1670], primaryKey: false);
      insert('organizations', [ id: 101656, nci_institute_code: "CA021", name: "Memorial Cancer Research Foundation", city: "Culver City", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1671,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA021",GROUP_DESC:"CA021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1671,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1671,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2671 ,protection_group_id: -1671, protection_element_id:-1671], primaryKey: false);
      insert('organizations', [ id: 101657, nci_institute_code: "CA022", name: "Southern California Permanente Medical Group", city: "Downey", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1672,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA022",GROUP_DESC:"CA022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1672,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1672,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2672 ,protection_group_id: -1672, protection_element_id:-1672], primaryKey: false);
      insert('organizations', [ id: 101658, nci_institute_code: "CA024", name: "Daniel Freeman Hospital", city: "Inglewood", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1673,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA024",GROUP_DESC:"CA024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1673,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1673,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2673 ,protection_group_id: -1673, protection_element_id:-1673], primaryKey: false);
      insert('organizations', [ id: 101659, nci_institute_code: "CA025", name: "Kaiser Hospital", city: "Inglewood", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1674,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA025",GROUP_DESC:"CA025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1674,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1674,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2674 ,protection_group_id: -1674, protection_element_id:-1674], primaryKey: false);
      insert('organizations', [ id: 101660, nci_institute_code: "CA026", name: "Santa Monica Hospital Medical Center", city: "Santa Monica", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1675,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA026",GROUP_DESC:"CA026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1675,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1675,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2675 ,protection_group_id: -1675, protection_element_id:-1675], primaryKey: false);
      insert('organizations', [ id: 101661, nci_institute_code: "CA027", name: "Saint Johns Hospital", city: "Santa Monica", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1676,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA027",GROUP_DESC:"CA027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1676,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1676,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2676 ,protection_group_id: -1676, protection_element_id:-1676], primaryKey: false);
      insert('organizations', [ id: 101662, nci_institute_code: "CA028", name: "Harbor-University of California at Los Angeles Medical Center", city: "Torrance", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1677,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA028",GROUP_DESC:"CA028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1677,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1677,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2677 ,protection_group_id: -1677, protection_element_id:-1677], primaryKey: false);
      insert('organizations', [ id: 101663, nci_institute_code: "CA029", name: "Torrance Memorial Medical Center", city: "Torrance", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1678,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA029",GROUP_DESC:"CA029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1678,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1678,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2678 ,protection_group_id: -1678, protection_element_id:-1678], primaryKey: false);
      insert('organizations', [ id: 101664, nci_institute_code: "CA030", name: "Presbyterian Hospital", city: "Whittier", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1679,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA030",GROUP_DESC:"CA030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1679,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1679,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2679 ,protection_group_id: -1679, protection_element_id:-1679], primaryKey: false);
      insert('organizations', [ id: 101665, nci_institute_code: "CA031", name: "Kaiser Foundation Hospital", city: "Bellflower", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1680,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA031",GROUP_DESC:"CA031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1680,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1680,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2680 ,protection_group_id: -1680, protection_element_id:-1680], primaryKey: false);
      insert('organizations', [ id: 101666, nci_institute_code: "CA033", name: "Los Alamitos Medical Center", city: "Los Alamitos", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1681,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA033",GROUP_DESC:"CA033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1681,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1681,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2681 ,protection_group_id: -1681, protection_element_id:-1681], primaryKey: false);
      insert('organizations', [ id: 101667, nci_institute_code: "CA034", name: "Long Beach Memorial Medical Center-Todd Cancer Institute", city: "Long Beach", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1682,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA034",GROUP_DESC:"CA034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1682,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1682,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2682 ,protection_group_id: -1682, protection_element_id:-1682], primaryKey: false);
      insert('organizations', [ id: 101668, nci_institute_code: "CA035", name: "Naval Hospital", city: "Long Beach", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1683,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA035",GROUP_DESC:"CA035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1683,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1683,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2683 ,protection_group_id: -1683, protection_element_id:-1683], primaryKey: false);
      insert('organizations', [ id: 101669, nci_institute_code: "CA036", name: "Long Beach Community Hospital", city: "Long Beach", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1684,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA036",GROUP_DESC:"CA036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1684,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1684,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2684 ,protection_group_id: -1684, protection_element_id:-1684], primaryKey: false);
      insert('organizations', [ id: 101670, nci_institute_code: "CA039", name: "Kaiser Permanente - Harbor City", city: "Harbor City", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1685,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA039",GROUP_DESC:"CA039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1685,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1685,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2685 ,protection_group_id: -1685, protection_element_id:-1685], primaryKey: false);
      insert('organizations', [ id: 101671, nci_institute_code: "CA040", name: "Long Beach Tumor Institute", city: "Long Beach", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1686,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA040",GROUP_DESC:"CA040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1686,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1686,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2686 ,protection_group_id: -1686, protection_element_id:-1686], primaryKey: false);
      insert('organizations', [ id: 101672, nci_institute_code: "CA041", name: "Veterans Administration Medical Center", city: "Long Beach", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1687,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA041",GROUP_DESC:"CA041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1687,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1687,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2687 ,protection_group_id: -1687, protection_element_id:-1687], primaryKey: false);
    }

    void m27() {
        // all27 (25 terms)
      insert('organizations', [ id: 101673, nci_institute_code: "CA042", name: "Arcadia Methodist Hospital of Southern California", city: "Arcadia", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1688,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA042",GROUP_DESC:"CA042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1688,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1688,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2688 ,protection_group_id: -1688, protection_element_id:-1688], primaryKey: false);
      insert('organizations', [ id: 101674, nci_institute_code: "CA043", name: "City of Hope National Medical Center", city: "Duarte", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1689,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA043",GROUP_DESC:"CA043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1689,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1689,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2689 ,protection_group_id: -1689, protection_element_id:-1689], primaryKey: false);
      insert('organizations', [ id: 101675, nci_institute_code: "CA044", name: "Santa Anita Small Animal Hospital", city: "Monrovia", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1690,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA044",GROUP_DESC:"CA044 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1690,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA044",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA044",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1690,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA044", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2690 ,protection_group_id: -1690, protection_element_id:-1690], primaryKey: false);
      insert('organizations', [ id: 101676, nci_institute_code: "CA045", name: "Huntington Memorial Hospital", city: "Pasadena", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1691,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA045",GROUP_DESC:"CA045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1691,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1691,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2691 ,protection_group_id: -1691, protection_element_id:-1691], primaryKey: false);
      insert('organizations', [ id: 101677, nci_institute_code: "CA048", name: "Northridge Hospital Medical Center", city: "Northridge", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1692,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA048",GROUP_DESC:"CA048 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1692,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA048",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA048",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1692,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA048", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2692 ,protection_group_id: -1692, protection_element_id:-1692], primaryKey: false);
      insert('organizations', [ id: 101678, nci_institute_code: "CA049", name: "Veterans Administration Medical Center", city: "Sepulveda", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1693,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA049",GROUP_DESC:"CA049 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1693,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA049",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA049",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1693,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA049", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2693 ,protection_group_id: -1693, protection_element_id:-1693], primaryKey: false);
      insert('organizations', [ id: 101679, nci_institute_code: "CA051", name: "Los Robles Hospital-Medical Center", city: "Thousand Oaks", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1694,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA051",GROUP_DESC:"CA051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1694,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1694,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2694 ,protection_group_id: -1694, protection_element_id:-1694], primaryKey: false);
      insert('organizations', [ id: 101680, nci_institute_code: "CA053", name: "Valley Hospital Medical Center", city: "Van Nuys", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1695,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA053",GROUP_DESC:"CA053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1695,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1695,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2695 ,protection_group_id: -1695, protection_element_id:-1695], primaryKey: false);
      insert('organizations', [ id: 101681, nci_institute_code: "CA054", name: "Valley Presbyterian Hospital", city: "Van Nuys", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1696,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA054",GROUP_DESC:"CA054 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1696,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA054",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA054",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1696,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA054", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2696 ,protection_group_id: -1696, protection_element_id:-1696], primaryKey: false);
      insert('organizations', [ id: 101682, nci_institute_code: "CA055", name: "Olive View Medical Center", city: "Sylmar", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1697,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA055",GROUP_DESC:"CA055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1697,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1697,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2697 ,protection_group_id: -1697, protection_element_id:-1697], primaryKey: false);
      insert('organizations', [ id: 101683, nci_institute_code: "CA056", name: "Encino Hospital", city: "Encino", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1698,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA056",GROUP_DESC:"CA056 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1698,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA056",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA056",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1698,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA056", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2698 ,protection_group_id: -1698, protection_element_id:-1698], primaryKey: false);
      insert('organizations', [ id: 101684, nci_institute_code: "CA058", name: "San Dimas Community", city: "San Dimas", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1699,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA058",GROUP_DESC:"CA058 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1699,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA058",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA058",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1699,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA058", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2699 ,protection_group_id: -1699, protection_element_id:-1699], primaryKey: false);
      insert('organizations', [ id: 101685, nci_institute_code: "CA061", name: "Scripps Memorial Hospitals", city: "La Jolla", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1700,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA061",GROUP_DESC:"CA061 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1700,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA061",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA061",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1700,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA061", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2700 ,protection_group_id: -1700, protection_element_id:-1700], primaryKey: false);
      insert('organizations', [ id: 101686, nci_institute_code: "CA062", name: "Wilshire Oncology Medical Group", city: "West Covina", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1701,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA062",GROUP_DESC:"CA062 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1701,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA062",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA062",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1701,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA062", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2701 ,protection_group_id: -1701, protection_element_id:-1701], primaryKey: false);
      insert('organizations', [ id: 101687, nci_institute_code: "CA063", name: "Scripps Clinic - La Jolla", city: "La Jolla", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1702,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA063",GROUP_DESC:"CA063 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1702,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA063",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA063",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1702,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA063", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2702 ,protection_group_id: -1702, protection_element_id:-1702], primaryKey: false);
      insert('organizations', [ id: 101688, nci_institute_code: "CA064", name: "Sharp Grossmont", city: "La Mesa", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1703,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA064",GROUP_DESC:"CA064 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1703,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA064",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA064",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1703,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA064", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2703 ,protection_group_id: -1703, protection_element_id:-1703], primaryKey: false);
      insert('organizations', [ id: 101689, nci_institute_code: "CA065", name: "Tri City Hospital", city: "Oceanside", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1704,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA065",GROUP_DESC:"CA065 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1704,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA065",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA065",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1704,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA065", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2704 ,protection_group_id: -1704, protection_element_id:-1704], primaryKey: false);
      insert('organizations', [ id: 101690, nci_institute_code: "CA066", name: "Naval Hospital", city: "Camp Pendleton", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1705,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA066",GROUP_DESC:"CA066 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1705,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA066",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA066",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1705,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA066", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2705 ,protection_group_id: -1705, protection_element_id:-1705], primaryKey: false);
      insert('organizations', [ id: 101691, nci_institute_code: "CA067", name: "University of California At San Diego", city: "San Diego", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1706,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA067",GROUP_DESC:"CA067 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1706,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA067",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA067",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1706,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA067", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2706 ,protection_group_id: -1706, protection_element_id:-1706], primaryKey: false);
      insert('organizations', [ id: 101692, nci_institute_code: "CA068", name: "Wilshire Oncology Medical Group, Inc.", city: "Glendora", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1707,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA068",GROUP_DESC:"CA068 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1707,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA068",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA068",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1707,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA068", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2707 ,protection_group_id: -1707, protection_element_id:-1707], primaryKey: false);
      insert('organizations', [ id: 101693, nci_institute_code: "CA070", name: "University of California San Diego - Veterans Administration", city: "San Diego", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1708,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA070",GROUP_DESC:"CA070 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1708,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA070",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA070",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1708,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA070", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2708 ,protection_group_id: -1708, protection_element_id:-1708], primaryKey: false);
      insert('organizations', [ id: 101694, nci_institute_code: "CA071", name: "Sharp Memorial Hospital", city: "San Diego", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1709,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA071",GROUP_DESC:"CA071 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1709,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA071",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA071",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1709,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA071", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2709 ,protection_group_id: -1709, protection_element_id:-1709], primaryKey: false);
      insert('organizations', [ id: 101695, nci_institute_code: "CA072", name: "Scripps Clinic - San Diego", city: "San Diego", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1710,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA072",GROUP_DESC:"CA072 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1710,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA072",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA072",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1710,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA072", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2710 ,protection_group_id: -1710, protection_element_id:-1710], primaryKey: false);
      insert('organizations', [ id: 101696, nci_institute_code: "CA073", name: "Rady Children's Hospital - San Diego", city: "San Diego", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1711,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA073",GROUP_DESC:"CA073 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1711,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA073",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA073",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1711,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA073", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2711 ,protection_group_id: -1711, protection_element_id:-1711], primaryKey: false);
      insert('organizations', [ id: 101697, nci_institute_code: "CA074", name: "Naval Medical Center -San Diego", city: "San Diego", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1712,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA074",GROUP_DESC:"CA074 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1712,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA074",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA074",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1712,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA074", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2712 ,protection_group_id: -1712, protection_element_id:-1712], primaryKey: false);
    }

    void m28() {
        // all28 (25 terms)
      insert('organizations', [ id: 101698, nci_institute_code: "CA075", name: "Desert Regional Medical Center", city: "Palm Springs", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1713,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA075",GROUP_DESC:"CA075 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1713,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA075",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA075",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1713,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA075", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2713 ,protection_group_id: -1713, protection_element_id:-1713], primaryKey: false);
      insert('organizations', [ id: 101699, nci_institute_code: "CA076", name: "Eisenhower Medical Center", city: "Rancho Mirage", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1714,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA076",GROUP_DESC:"CA076 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1714,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA076",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA076",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1714,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA076", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2714 ,protection_group_id: -1714, protection_element_id:-1714], primaryKey: false);
      insert('organizations', [ id: 101700, nci_institute_code: "CA077", name: "Kaiser Permanente Hospital", city: "Fontana", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1715,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA077",GROUP_DESC:"CA077 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1715,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA077",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA077",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1715,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA077", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2715 ,protection_group_id: -1715, protection_element_id:-1715], primaryKey: false);
      insert('organizations', [ id: 101701, nci_institute_code: "CA078", name: "Loma Linda University Medical Center", city: "Loma Linda", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1716,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA078",GROUP_DESC:"CA078 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1716,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA078",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA078",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1716,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA078", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2716 ,protection_group_id: -1716, protection_element_id:-1716], primaryKey: false);
      insert('organizations', [ id: 101702, nci_institute_code: "CA079", name: "Veterans Affairs Loma Linda Healthcare System", city: "Loma Linda", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1717,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA079",GROUP_DESC:"CA079 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1717,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA079",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA079",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1717,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA079", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2717 ,protection_group_id: -1717, protection_element_id:-1717], primaryKey: false);
      insert('organizations', [ id: 101703, nci_institute_code: "CA080", name: "Saint Bernardine Medical Center", city: "San Bernardino", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1718,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA080",GROUP_DESC:"CA080 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1718,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA080",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA080",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1718,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA080", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2718 ,protection_group_id: -1718, protection_element_id:-1718], primaryKey: false);
      insert('organizations', [ id: 101704, nci_institute_code: "CA081", name: "San Bernardino County", city: "San Bernardino", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1719,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA081",GROUP_DESC:"CA081 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1719,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA081",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA081",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1719,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA081", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2719 ,protection_group_id: -1719, protection_element_id:-1719], primaryKey: false);
      insert('organizations', [ id: 101705, nci_institute_code: "CA082", name: "Parkview Community Hospital", city: "Riverside", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1720,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA082",GROUP_DESC:"CA082 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1720,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA082",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA082",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1720,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA082", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2720 ,protection_group_id: -1720, protection_element_id:-1720], primaryKey: false);
      insert('organizations', [ id: 101706, nci_institute_code: "CA083", name: "Childrens Hospital of Orange County", city: "Orange", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1721,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA083",GROUP_DESC:"CA083 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1721,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA083",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA083",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1721,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA083", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2721 ,protection_group_id: -1721, protection_element_id:-1721], primaryKey: false);
      insert('organizations', [ id: 101707, nci_institute_code: "CA084", name: "Saint Jude Medical Center", city: "Fullerton", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1722,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA084",GROUP_DESC:"CA084 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1722,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA084",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA084",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1722,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA084", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2722 ,protection_group_id: -1722, protection_element_id:-1722], primaryKey: false);
      insert('organizations', [ id: 101708, nci_institute_code: "CA086", name: "Hoag Memorial Hospital", city: "Newport Beach", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1723,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA086",GROUP_DESC:"CA086 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1723,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA086",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA086",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1723,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA086", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2723 ,protection_group_id: -1723, protection_element_id:-1723], primaryKey: false);
      insert('organizations', [ id: 101709, nci_institute_code: "CA087", name: "Saint Joseph Hospital", city: "Orange", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1724,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA087",GROUP_DESC:"CA087 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1724,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA087",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA087",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1724,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA087", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2724 ,protection_group_id: -1724, protection_element_id:-1724], primaryKey: false);
      insert('organizations', [ id: 101710, nci_institute_code: "CA088", name: "University of California Medical Center At Irvine", city: "Orange", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1725,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA088",GROUP_DESC:"CA088 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1725,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA088",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA088",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1725,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA088", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2725 ,protection_group_id: -1725, protection_element_id:-1725], primaryKey: false);
      insert('organizations', [ id: 101711, nci_institute_code: "CA089", name: "Orange County General Hospital", city: "Orange", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1726,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA089",GROUP_DESC:"CA089 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1726,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA089",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA089",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1726,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA089", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2726 ,protection_group_id: -1726, protection_element_id:-1726], primaryKey: false);
      insert('organizations', [ id: 101712, nci_institute_code: "CA090", name: "Miller Children's Hospital", city: "Long Beach", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1727,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA090",GROUP_DESC:"CA090 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1727,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA090",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA090",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1727,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA090", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2727 ,protection_group_id: -1727, protection_element_id:-1727], primaryKey: false);
      insert('organizations', [ id: 101713, nci_institute_code: "CA091", name: "Bristol Medical Center", city: "Santa Ana", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1728,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA091",GROUP_DESC:"CA091 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1728,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA091",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA091",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1728,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA091", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2728 ,protection_group_id: -1728, protection_element_id:-1728], primaryKey: false);
      insert('organizations', [ id: 101714, nci_institute_code: "CA092", name: "Western Medical Center", city: "Santa Ana", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1729,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA092",GROUP_DESC:"CA092 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1729,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA092",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA092",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1729,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA092", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2729 ,protection_group_id: -1729, protection_element_id:-1729], primaryKey: false);
      insert('organizations', [ id: 101715, nci_institute_code: "CA093", name: "Anaheim Memorial Hospital", city: "Anaheim", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1730,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA093",GROUP_DESC:"CA093 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1730,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA093",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA093",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1730,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA093", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2730 ,protection_group_id: -1730, protection_element_id:-1730], primaryKey: false);
      insert('organizations', [ id: 101716, nci_institute_code: "CA094", name: "Lutheran Hospital Society", city: "Anaheim", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1731,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA094",GROUP_DESC:"CA094 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1731,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA094",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA094",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1731,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA094", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2731 ,protection_group_id: -1731, protection_element_id:-1731], primaryKey: false);
      insert('organizations', [ id: 101717, nci_institute_code: "CA095", name: "Kaiser Anaheim Medical Center", city: "Anaheim", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1732,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA095",GROUP_DESC:"CA095 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1732,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA095",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA095",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1732,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA095", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2732 ,protection_group_id: -1732, protection_element_id:-1732], primaryKey: false);
      insert('organizations', [ id: 101718, nci_institute_code: "CA096", name: "Ventura County General Hospital", city: "Ventura", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1733,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA096",GROUP_DESC:"CA096 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1733,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA096",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA096",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1733,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA096", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2733 ,protection_group_id: -1733, protection_element_id:-1733], primaryKey: false);
      insert('organizations', [ id: 101719, nci_institute_code: "CA097", name: "St. Johns Hospital, Inc.", city: "Oxnard", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1734,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA097",GROUP_DESC:"CA097 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1734,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA097",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA097",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1734,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA097", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2734 ,protection_group_id: -1734, protection_element_id:-1734], primaryKey: false);
      insert('organizations', [ id: 101720, nci_institute_code: "CA098", name: "Sansum Medical Clinic Inc.", city: "Santa Barbara", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1735,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA098",GROUP_DESC:"CA098 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1735,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA098",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA098",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1735,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA098", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2735 ,protection_group_id: -1735, protection_element_id:-1735], primaryKey: false);
      insert('organizations', [ id: 101721, nci_institute_code: "CA099", name: "Santa Barbara Med Fdn Clinic", city: "Santa Barbara", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1736,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA099",GROUP_DESC:"CA099 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1736,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA099",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA099",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1736,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA099", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2736 ,protection_group_id: -1736, protection_element_id:-1736], primaryKey: false);
      insert('organizations', [ id: 101722, nci_institute_code: "CA101", name: "Santa Barbara Cottage Hospital", city: "Santa Barbara", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1737,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA101",GROUP_DESC:"CA101 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1737,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA101",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA101",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1737,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA101", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2737 ,protection_group_id: -1737, protection_element_id:-1737], primaryKey: false);
    }

    void m29() {
        // all29 (25 terms)
      insert('organizations', [ id: 101723, nci_institute_code: "CA103", name: "Santa Barbara General Hospital", city: "Santa Barbara", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1738,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA103",GROUP_DESC:"CA103 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1738,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA103",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA103",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1738,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA103", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2738 ,protection_group_id: -1738, protection_element_id:-1738], primaryKey: false);
      insert('organizations', [ id: 101724, nci_institute_code: "CA105", name: "Bakersfield Memorial Hospital", city: "Bakersfield", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1739,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA105",GROUP_DESC:"CA105 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1739,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA105",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA105",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1739,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA105", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2739 ,protection_group_id: -1739, protection_element_id:-1739], primaryKey: false);
      insert('organizations', [ id: 101725, nci_institute_code: "CA106", name: "Kern Medical Center", city: "Bakersfield", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1740,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA106",GROUP_DESC:"CA106 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1740,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA106",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA106",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1740,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA106", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2740 ,protection_group_id: -1740, protection_element_id:-1740], primaryKey: false);
      insert('organizations', [ id: 101726, nci_institute_code: "CA107", name: "Sierra Vista Regional Medical Center", city: "San Luis Obispo", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1741,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA107",GROUP_DESC:"CA107 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1741,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA107",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA107",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1741,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA107", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2741 ,protection_group_id: -1741, protection_element_id:-1741], primaryKey: false);
      insert('organizations', [ id: 101727, nci_institute_code: "CA110", name: "Veterans Administration Medical Center", city: "Fresno", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1742,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA110",GROUP_DESC:"CA110 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1742,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA110",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA110",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1742,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA110", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2742 ,protection_group_id: -1742, protection_element_id:-1742], primaryKey: false);
      insert('organizations', [ id: 101728, nci_institute_code: "CA111", name: "University of California/Fresno", city: "Fresno", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1743,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA111",GROUP_DESC:"CA111 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1743,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA111",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA111",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1743,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA111", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2743 ,protection_group_id: -1743, protection_element_id:-1743], primaryKey: false);
      insert('organizations', [ id: 101729, nci_institute_code: "CA112", name: "Saint Agnes Hospital, Fresno", city: "Fresno", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1744,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA112",GROUP_DESC:"CA112 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1744,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA112",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA112",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1744,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA112", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2744 ,protection_group_id: -1744, protection_element_id:-1744], primaryKey: false);
      insert('organizations', [ id: 101730, nci_institute_code: "CA113", name: "Fresno Community Hospital and Medical Center", city: "Fresno", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1745,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA113",GROUP_DESC:"CA113 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1745,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA113",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA113",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1745,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA113", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2745 ,protection_group_id: -1745, protection_element_id:-1745], primaryKey: false);
      insert('organizations', [ id: 101731, nci_institute_code: "CA115", name: "Community Hospital of Monterey Peninsula", city: "Monterey", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1746,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA115",GROUP_DESC:"CA115 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1746,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA115",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA115",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1746,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA115", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2746 ,protection_group_id: -1746, protection_element_id:-1746], primaryKey: false);
      insert('organizations', [ id: 101732, nci_institute_code: "CA116", name: "Silas B Hayes Army Community Hospital", city: "Fort Ord", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1747,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA116",GROUP_DESC:"CA116 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1747,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA116",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA116",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1747,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA116", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2747 ,protection_group_id: -1747, protection_element_id:-1747], primaryKey: false);
      insert('organizations', [ id: 101733, nci_institute_code: "CA117", name: "Mills - Peninsula Hospitals", city: "Burlingame", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1748,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA117",GROUP_DESC:"CA117 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1748,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA117",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA117",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1748,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA117", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2748 ,protection_group_id: -1748, protection_element_id:-1748], primaryKey: false);
      insert('organizations', [ id: 101734, nci_institute_code: "CA119", name: "El Camino Hospital", city: "Mountain View", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1749,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA119",GROUP_DESC:"CA119 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1749,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA119",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA119",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1749,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA119", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2749 ,protection_group_id: -1749, protection_element_id:-1749], primaryKey: false);
      insert('organizations', [ id: 101735, nci_institute_code: "CA120", name: "Sequoia Hospital", city: "Redwood City", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1750,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA120",GROUP_DESC:"CA120 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1750,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA120",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA120",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1750,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA120", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2750 ,protection_group_id: -1750, protection_element_id:-1750], primaryKey: false);
      insert('organizations', [ id: 101736, nci_institute_code: "CA121", name: "Redwood Medical Center", city: "Redwood City", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1751,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA121",GROUP_DESC:"CA121 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1751,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA121",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA121",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1751,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA121", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2751 ,protection_group_id: -1751, protection_element_id:-1751], primaryKey: false);
      insert('organizations', [ id: 101737, nci_institute_code: "CA122", name: "Kaiser Permanente, South San Francisco", city: "South San Francisco", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1752,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA122",GROUP_DESC:"CA122 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1752,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA122",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA122",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1752,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA122", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2752 ,protection_group_id: -1752, protection_element_id:-1752], primaryKey: false);
      insert('organizations', [ id: 101738, nci_institute_code: "CA123", name: "Seton Medical Center", city: "Daly City", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1753,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA123",GROUP_DESC:"CA123 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1753,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA123",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA123",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1753,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA123", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2753 ,protection_group_id: -1753, protection_element_id:-1753], primaryKey: false);
      insert('organizations', [ id: 101739, nci_institute_code: "CA124", name: "Saint Francis Memorial Hospital", city: "San Francisco", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1754,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA124",GROUP_DESC:"CA124 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1754,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA124",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA124",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1754,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA124", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2754 ,protection_group_id: -1754, protection_element_id:-1754], primaryKey: false);
      insert('organizations', [ id: 101740, nci_institute_code: "CA125", name: "San Francisco General", city: "San Francisco", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1755,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA125",GROUP_DESC:"CA125 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1755,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA125",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA125",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1755,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA125", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2755 ,protection_group_id: -1755, protection_element_id:-1755], primaryKey: false);
      insert('organizations', [ id: 101741, nci_institute_code: "CA126", name: "Ralph K. Davies Med Center", city: "San Francisco", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1756,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA126",GROUP_DESC:"CA126 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1756,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA126",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA126",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1756,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA126", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2756 ,protection_group_id: -1756, protection_element_id:-1756], primaryKey: false);
      insert('organizations', [ id: 101742, nci_institute_code: "CA127", name: "Presbyterian Hospital of Pacific Medical Center", city: "San Francisco", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1757,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA127",GROUP_DESC:"CA127 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1757,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA127",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA127",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1757,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA127", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2757 ,protection_group_id: -1757, protection_element_id:-1757], primaryKey: false);
      insert('organizations', [ id: 101743, nci_institute_code: "CA128", name: "USPHS Hospital", city: "San Francisco", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1758,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA128",GROUP_DESC:"CA128 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1758,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA128",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA128",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1758,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA128", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2758 ,protection_group_id: -1758, protection_element_id:-1758], primaryKey: false);
      insert('organizations', [ id: 101744, nci_institute_code: "CA129", name: "Institute of Cancer Research", city: "San Francisco", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1759,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA129",GROUP_DESC:"CA129 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1759,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA129",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA129",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1759,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA129", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2759 ,protection_group_id: -1759, protection_element_id:-1759], primaryKey: false);
      insert('organizations', [ id: 101745, nci_institute_code: "CA130", name: "Saint Mary's Medical Center", city: "San Francisco", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1760,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA130",GROUP_DESC:"CA130 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1760,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA130",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA130",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1760,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA130", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2760 ,protection_group_id: -1760, protection_element_id:-1760], primaryKey: false);
      insert('organizations', [ id: 101746, nci_institute_code: "CA131", name: "Harkness Hospital", city: "San Francisco", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1761,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA131",GROUP_DESC:"CA131 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1761,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA131",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA131",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1761,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA131", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2761 ,protection_group_id: -1761, protection_element_id:-1761], primaryKey: false);
      insert('organizations', [ id: 101747, nci_institute_code: "CA134", name: "Veterans Affairs Medical Center - San Francisco", city: "San Francisco", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1762,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA134",GROUP_DESC:"CA134 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1762,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA134",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA134",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1762,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA134", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2762 ,protection_group_id: -1762, protection_element_id:-1762], primaryKey: false);
    }

    void m30() {
        // all30 (25 terms)
      insert('organizations', [ id: 101748, nci_institute_code: "CA135", name: "Letterman US Army Hospital", city: "San Francisco", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1763,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA135",GROUP_DESC:"CA135 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1763,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA135",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA135",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1763,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA135", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2763 ,protection_group_id: -1763, protection_element_id:-1763], primaryKey: false);
      insert('organizations', [ id: 101749, nci_institute_code: "CA136", name: "University of California at San Francisco", city: "San Francisco", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1764,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA136",GROUP_DESC:"CA136 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1764,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA136",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA136",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1764,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA136", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2764 ,protection_group_id: -1764, protection_element_id:-1764], primaryKey: false);
      insert('organizations', [ id: 101750, nci_institute_code: "CA137", name: "University of California Moffit Hospital", city: "San Francisco", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1765,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA137",GROUP_DESC:"CA137 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1765,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA137",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA137",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1765,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA137", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2765 ,protection_group_id: -1765, protection_element_id:-1765], primaryKey: false);
      insert('organizations', [ id: 101751, nci_institute_code: "CA138", name: "Palo Alto Medical Foundation / Heath Care", city: "Palo Alto", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1766,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA138",GROUP_DESC:"CA138 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1766,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA138",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA138",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1766,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA138", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2766 ,protection_group_id: -1766, protection_element_id:-1766], primaryKey: false);
      insert('organizations', [ id: 101752, nci_institute_code: "CA139", name: "Packard Children's Hospital Stanford University", city: "Palo Alto", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1767,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA139",GROUP_DESC:"CA139 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1767,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA139",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA139",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1767,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA139", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2767 ,protection_group_id: -1767, protection_element_id:-1767], primaryKey: false);
      insert('organizations', [ id: 101753, nci_institute_code: "CA140", name: "VA Palo Alto Health Care System", city: "Palo Alto", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1768,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA140",GROUP_DESC:"CA140 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1768,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA140",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA140",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1768,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA140", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2768 ,protection_group_id: -1768, protection_element_id:-1768], primaryKey: false);
      insert('organizations', [ id: 101754, nci_institute_code: "CA141", name: "Stanford University", city: "Stanford", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1769,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA141",GROUP_DESC:"CA141 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1769,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA141",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA141",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1769,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA141", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2769 ,protection_group_id: -1769, protection_element_id:-1769], primaryKey: false);
      insert('organizations', [ id: 101755, nci_institute_code: "CA142", name: "Westlake Medical Center", city: "Westlake Village", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1770,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA142",GROUP_DESC:"CA142 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1770,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA142",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA142",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1770,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA142", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2770 ,protection_group_id: -1770, protection_element_id:-1770], primaryKey: false);
      insert('organizations', [ id: 101756, nci_institute_code: "CA143", name: "NCCC/NCOG Headquarters", city: "Palo Alto", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1771,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA143",GROUP_DESC:"CA143 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1771,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA143",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA143",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1771,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA143", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2771 ,protection_group_id: -1771, protection_element_id:-1771], primaryKey: false);
      insert('organizations', [ id: 101757, nci_institute_code: "CA144", name: "Sievers and Candela Medical Corporation", city: "San Mateo", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1772,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA144",GROUP_DESC:"CA144 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1772,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA144",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA144",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1772,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA144", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2772 ,protection_group_id: -1772, protection_element_id:-1772], primaryKey: false);
      insert('organizations', [ id: 101758, nci_institute_code: "CA145", name: "John Muir Medical Center-Concord Campus", city: "Concord", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1773,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA145",GROUP_DESC:"CA145 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1773,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA145",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA145",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1773,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA145", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2773 ,protection_group_id: -1773, protection_element_id:-1773], primaryKey: false);
      insert('organizations', [ id: 101759, nci_institute_code: "CA146", name: "David Grant United States Air Force Medical Center", city: "Travis AFB", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1774,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA146",GROUP_DESC:"CA146 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1774,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA146",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA146",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1774,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA146", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2774 ,protection_group_id: -1774, protection_element_id:-1774], primaryKey: false);
      insert('organizations', [ id: 101760, nci_institute_code: "CA147", name: "Washington Hospital", city: "Fremont", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1775,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA147",GROUP_DESC:"CA147 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1775,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA147",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA147",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1775,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA147", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2775 ,protection_group_id: -1775, protection_element_id:-1775], primaryKey: false);
      insert('organizations', [ id: 101761, nci_institute_code: "CA148", name: "Kaiser Permanente, Fremont", city: "Fremont", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1776,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA148",GROUP_DESC:"CA148 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1776,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA148",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA148",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1776,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA148", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2776 ,protection_group_id: -1776, protection_element_id:-1776], primaryKey: false);
      insert('organizations', [ id: 101762, nci_institute_code: "CA150", name: "Kaiser Permanente, Hayward", city: "Hayward", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1777,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA150",GROUP_DESC:"CA150 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1777,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA150",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA150",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1777,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA150", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2777 ,protection_group_id: -1777, protection_element_id:-1777], primaryKey: false);
      insert('organizations', [ id: 101763, nci_institute_code: "CA151", name: "Veterans Administration Hospital - Martinez, Ca", city: "Martinez", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1778,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA151",GROUP_DESC:"CA151 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1778,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA151",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA151",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1778,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA151", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2778 ,protection_group_id: -1778, protection_element_id:-1778], primaryKey: false);
      insert('organizations', [ id: 101764, nci_institute_code: "CA152", name: "Queen of The Valley", city: "Napa", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1779,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA152",GROUP_DESC:"CA152 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1779,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA152",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA152",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1779,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA152", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2779 ,protection_group_id: -1779, protection_element_id:-1779], primaryKey: false);
      insert('organizations', [ id: 101765, nci_institute_code: "CA154", name: "Kaiser Permanente-Vallejo", city: "Vallejo", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1780,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA154",GROUP_DESC:"CA154 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1780,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA154",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA154",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1780,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA154", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2780 ,protection_group_id: -1780, protection_element_id:-1780], primaryKey: false);
      insert('organizations', [ id: 101766, nci_institute_code: "CA156", name: "Kaiser Permanente-Walnut Creek", city: "Walnut Creek", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1781,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA156",GROUP_DESC:"CA156 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1781,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA156",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA156",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1781,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA156", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2781 ,protection_group_id: -1781, protection_element_id:-1781], primaryKey: false);
      insert('organizations', [ id: 101767, nci_institute_code: "CA157", name: "John Muir Medical Center", city: "Walnut Creek", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1782,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA157",GROUP_DESC:"CA157 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1782,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA157",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA157",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1782,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA157", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2782 ,protection_group_id: -1782, protection_element_id:-1782], primaryKey: false);
      insert('organizations', [ id: 101768, nci_institute_code: "CA158", name: "Highland General Hospital", city: "Oakland", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1783,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA158",GROUP_DESC:"CA158 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1783,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA158",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA158",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1783,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA158", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2783 ,protection_group_id: -1783, protection_element_id:-1783], primaryKey: false);
      insert('organizations', [ id: 101769, nci_institute_code: "CA159", name: "Samuel Merritt Hospital", city: "Oakland", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1784,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA159",GROUP_DESC:"CA159 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1784,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA159",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA159",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1784,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA159", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2784 ,protection_group_id: -1784, protection_element_id:-1784], primaryKey: false);
      insert('organizations', [ id: 101770, nci_institute_code: "CA160", name: "Bay Area Tumor Institution CCOP", city: "Oakland", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1785,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA160",GROUP_DESC:"CA160 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1785,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA160",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA160",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1785,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA160", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2785 ,protection_group_id: -1785, protection_element_id:-1785], primaryKey: false);
      insert('organizations', [ id: 101771, nci_institute_code: "CA161", name: "Children's Hospital and Research Center at Oakland", city: "Oakland", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1786,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA161",GROUP_DESC:"CA161 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1786,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA161",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA161",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1786,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA161", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2786 ,protection_group_id: -1786, protection_element_id:-1786], primaryKey: false);
      insert('organizations', [ id: 101772, nci_institute_code: "CA162", name: "Kaiser Permanente-Oakland", city: "Oakland", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1787,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA162",GROUP_DESC:"CA162 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1787,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA162",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA162",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1787,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA162", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2787 ,protection_group_id: -1787, protection_element_id:-1787], primaryKey: false);
    }

    void m31() {
        // all31 (25 terms)
      insert('organizations', [ id: 101773, nci_institute_code: "CA163", name: "Naval Hospital", city: "Oakland", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1788,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA163",GROUP_DESC:"CA163 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1788,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA163",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA163",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1788,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA163", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2788 ,protection_group_id: -1788, protection_element_id:-1788], primaryKey: false);
      insert('organizations', [ id: 101774, nci_institute_code: "CA164", name: "Alta Bates Medical Center", city: "Berkeley", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1789,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA164",GROUP_DESC:"CA164 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1789,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA164",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA164",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1789,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA164", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2789 ,protection_group_id: -1789, protection_element_id:-1789], primaryKey: false);
      insert('organizations', [ id: 101775, nci_institute_code: "CA165", name: "University of California", city: "Berkeley", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1790,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA165",GROUP_DESC:"CA165 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1790,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA165",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA165",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1790,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA165", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2790 ,protection_group_id: -1790, protection_element_id:-1790], primaryKey: false);
      insert('organizations', [ id: 101776, nci_institute_code: "CA168", name: "Kaiser Permanente-San Rafael", city: "San Rafael", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1791,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA168",GROUP_DESC:"CA168 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1791,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA168",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA168",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1791,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA168", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2791 ,protection_group_id: -1791, protection_element_id:-1791], primaryKey: false);
      insert('organizations', [ id: 101777, nci_institute_code: "CA169", name: "Marin General Hospital", city: "Greenbrae", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1792,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA169",GROUP_DESC:"CA169 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1792,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA169",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA169",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1792,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA169", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2792 ,protection_group_id: -1792, protection_element_id:-1792], primaryKey: false);
      insert('organizations', [ id: 101778, nci_institute_code: "CA172", name: "Kaiser Permanente Medical Center - Santa Clara", city: "Santa Clara", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1793,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA172",GROUP_DESC:"CA172 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1793,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA172",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA172",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1793,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA172", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2793 ,protection_group_id: -1793, protection_element_id:-1793], primaryKey: false);
      insert('organizations', [ id: 101779, nci_institute_code: "CA173", name: "Dominican Santa Cruz Hospital", city: "Santa Cruz", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1794,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA173",GROUP_DESC:"CA173 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1794,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA173",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA173",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1794,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA173", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2794 ,protection_group_id: -1794, protection_element_id:-1794], primaryKey: false);
      insert('organizations', [ id: 101780, nci_institute_code: "CA174", name: "San Jose Medical Center", city: "San Jose", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1795,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA174",GROUP_DESC:"CA174 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1795,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA174",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA174",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1795,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA174", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2795 ,protection_group_id: -1795, protection_element_id:-1795], primaryKey: false);
      insert('organizations', [ id: 101781, nci_institute_code: "CA175", name: "Kaiser Permanente, Santa Teresa (San Jose)", city: "San Jose", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1796,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA175",GROUP_DESC:"CA175 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1796,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA175",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA175",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1796,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA175", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2796 ,protection_group_id: -1796, protection_element_id:-1796], primaryKey: false);
      insert('organizations', [ id: 101782, nci_institute_code: "CA177", name: "O'Connor Hospital", city: "San Jose", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1797,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA177",GROUP_DESC:"CA177 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1797,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA177",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA177",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1797,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA177", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2797 ,protection_group_id: -1797, protection_element_id:-1797], primaryKey: false);
      insert('organizations', [ id: 101783, nci_institute_code: "CA178", name: "Santa Clara Valley Medical Center", city: "San Jose", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1798,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA178",GROUP_DESC:"CA178 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1798,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA178",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA178",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1798,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA178", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2798 ,protection_group_id: -1798, protection_element_id:-1798], primaryKey: false);
      insert('organizations', [ id: 101784, nci_institute_code: "CA179", name: "Saint Joseph's Medical Center", city: "Stockton", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1799,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA179",GROUP_DESC:"CA179 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1799,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA179",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA179",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1799,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA179", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2799 ,protection_group_id: -1799, protection_element_id:-1799], primaryKey: false);
      insert('organizations', [ id: 101785, nci_institute_code: "CA180", name: "Lodi Memorial Hospital", city: "Lodi", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1800,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA180",GROUP_DESC:"CA180 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1800,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA180",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA180",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1800,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA180", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2800 ,protection_group_id: -1800, protection_element_id:-1800], primaryKey: false);
      insert('organizations', [ id: 101786, nci_institute_code: "CA182", name: "Memorial Medical Center", city: "Modesto", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1801,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA182",GROUP_DESC:"CA182 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1801,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA182",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA182",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1801,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA182", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2801 ,protection_group_id: -1801, protection_element_id:-1801], primaryKey: false);
      insert('organizations', [ id: 101787, nci_institute_code: "CA184", name: "Saint Joseph's Hospital", city: "Eureka", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1802,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA184",GROUP_DESC:"CA184 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1802,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA184",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA184",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1802,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA184", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2802 ,protection_group_id: -1802, protection_element_id:-1802], primaryKey: false);
      insert('organizations', [ id: 101788, nci_institute_code: "CA185", name: "Mercy San Juan Medical Center", city: "Carmichael", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1803,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA185",GROUP_DESC:"CA185 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1803,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA185",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA185",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1803,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA185", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2803 ,protection_group_id: -1803, protection_element_id:-1803], primaryKey: false);
      insert('organizations', [ id: 101789, nci_institute_code: "CA186", name: "Modesto Radiation Oncology Center", city: "Modesto", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1804,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA186",GROUP_DESC:"CA186 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1804,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA186",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA186",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1804,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA186", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2804 ,protection_group_id: -1804, protection_element_id:-1804], primaryKey: false);
      insert('organizations', [ id: 101790, nci_institute_code: "CA188", name: "Woodland Clinic Medical Group", city: "Woodland", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1805,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA188",GROUP_DESC:"CA188 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1805,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA188",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA188",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1805,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA188", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2805 ,protection_group_id: -1805, protection_element_id:-1805], primaryKey: false);
      insert('organizations', [ id: 101791, nci_institute_code: "CA189", name: "University of California At Davis", city: "Davis", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1806,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA189",GROUP_DESC:"CA189 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1806,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA189",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA189",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1806,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA189", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2806 ,protection_group_id: -1806, protection_element_id:-1806], primaryKey: false);
      insert('organizations', [ id: 101792, nci_institute_code: "CA190", name: "Mercy General Hospital", city: "Sacramento", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1807,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA190",GROUP_DESC:"CA190 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1807,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA190",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA190",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1807,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA190", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2807 ,protection_group_id: -1807, protection_element_id:-1807], primaryKey: false);
      insert('organizations', [ id: 101793, nci_institute_code: "CA191", name: "Kaiser Permanente-South Sacramento", city: "Sacramento", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1808,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA191",GROUP_DESC:"CA191 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1808,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA191",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA191",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1808,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA191", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2808 ,protection_group_id: -1808, protection_element_id:-1808], primaryKey: false);
      insert('organizations', [ id: 101794, nci_institute_code: "CA192", name: "Kaiser Permanente - Sacramento", city: "Sacramento", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1809,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA192",GROUP_DESC:"CA192 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1809,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA192",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA192",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1809,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA192", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2809 ,protection_group_id: -1809, protection_element_id:-1809], primaryKey: false);
      insert('organizations', [ id: 101795, nci_institute_code: "CA193", name: "Tripler Army Medical Center", city: "San Francisco", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1810,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA193",GROUP_DESC:"CA193 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1810,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA193",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA193",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1810,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA193", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2810 ,protection_group_id: -1810, protection_element_id:-1810], primaryKey: false);
      insert('organizations', [ id: 101796, nci_institute_code: "CA194", name: "Enloe Medical Center", city: "Chico", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1811,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA194",GROUP_DESC:"CA194 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1811,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA194",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA194",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1811,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA194", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2811 ,protection_group_id: -1811, protection_element_id:-1811], primaryKey: false);
      insert('organizations', [ id: 101797, nci_institute_code: "CA195", name: "Panorama Community Hospital", city: "Panorama City", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1812,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA195",GROUP_DESC:"CA195 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1812,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA195",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA195",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1812,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA195", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2812 ,protection_group_id: -1812, protection_element_id:-1812], primaryKey: false);
    }

    void m32() {
        // all32 (25 terms)
      insert('organizations', [ id: 101798, nci_institute_code: "CA196", name: "Fountain Valley Hospital", city: "Fountain Valley", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1813,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA196",GROUP_DESC:"CA196 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1813,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA196",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA196",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1813,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA196", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2813 ,protection_group_id: -1813, protection_element_id:-1813], primaryKey: false);
      insert('organizations', [ id: 101799, nci_institute_code: "CA197", name: "Kaiser Permanente, San Francisco", city: "San Francisco", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1814,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA197",GROUP_DESC:"CA197 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1814,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA197",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA197",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1814,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA197", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2814 ,protection_group_id: -1814, protection_element_id:-1814], primaryKey: false);
      insert('organizations', [ id: 101800, nci_institute_code: "CA198", name: "Saint Joseph Medical Center", city: "Burbank", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1815,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA198",GROUP_DESC:"CA198 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1815,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA198",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA198",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1815,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA198", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2815 ,protection_group_id: -1815, protection_element_id:-1815], primaryKey: false);
      insert('organizations', [ id: 101801, nci_institute_code: "CA199", name: "Peralta Hospital", city: "Oakland", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1816,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA199",GROUP_DESC:"CA199 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1816,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA199",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA199",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1816,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA199", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2816 ,protection_group_id: -1816, protection_element_id:-1816], primaryKey: false);
      insert('organizations', [ id: 101802, nci_institute_code: "CA200", name: "Eden Hospital Medical Center", city: "Castro Valley", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1817,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA200",GROUP_DESC:"CA200 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1817,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA200",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA200",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1817,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA200", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2817 ,protection_group_id: -1817, protection_element_id:-1817], primaryKey: false);
      insert('organizations', [ id: 101803, nci_institute_code: "CA201", name: "Antelope Valley Cancer Center", city: "Lancaster", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1818,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA201",GROUP_DESC:"CA201 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1818,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA201",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA201",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1818,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA201", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2818 ,protection_group_id: -1818, protection_element_id:-1818], primaryKey: false);
      insert('organizations', [ id: 101804, nci_institute_code: "CA202", name: "Santa Rosa Memorial Hospital", city: "Sana Rosa", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1819,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA202",GROUP_DESC:"CA202 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1819,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA202",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA202",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1819,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA202", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2819 ,protection_group_id: -1819, protection_element_id:-1819], primaryKey: false);
      insert('organizations', [ id: 101805, nci_institute_code: "CA203", name: "Pacific Shores Medical Group", city: "Long Beach", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1820,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA203",GROUP_DESC:"CA203 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1820,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA203",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA203",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1820,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA203", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2820 ,protection_group_id: -1820, protection_element_id:-1820], primaryKey: false);
      insert('organizations', [ id: 101806, nci_institute_code: "CA204", name: "San Luis Medical Clinic", city: "San Luis Obispo", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1821,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA204",GROUP_DESC:"CA204 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1821,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA204",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA204",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1821,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA204", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2821 ,protection_group_id: -1821, protection_element_id:-1821], primaryKey: false);
      insert('organizations', [ id: 101807, nci_institute_code: "CA205", name: "Sharp Health Care - Grossmont", city: "San Diego", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1822,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA205",GROUP_DESC:"CA205 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1822,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA205",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA205",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1822,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA205", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2822 ,protection_group_id: -1822, protection_element_id:-1822], primaryKey: false);
      insert('organizations', [ id: 101808, nci_institute_code: "CA207", name: "Downey Community Hospital", city: "Downey", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1823,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA207",GROUP_DESC:"CA207 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1823,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA207",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA207",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1823,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA207", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2823 ,protection_group_id: -1823, protection_element_id:-1823], primaryKey: false);
      insert('organizations', [ id: 101809, nci_institute_code: "CA208", name: "Sharp Rees - Stealy Clinic", city: "San Diego", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1824,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA208",GROUP_DESC:"CA208 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1824,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA208",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA208",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1824,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA208", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2824 ,protection_group_id: -1824, protection_element_id:-1824], primaryKey: false);
      insert('organizations', [ id: 101810, nci_institute_code: "CA209", name: "Mills Hospital", city: "San Mateo", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1825,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA209",GROUP_DESC:"CA209 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1825,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA209",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA209",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1825,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA209", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2825 ,protection_group_id: -1825, protection_element_id:-1825], primaryKey: false);
      insert('organizations', [ id: 101811, nci_institute_code: "CA210", name: "Mercy Regional Cancer Center", city: "Redding", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1826,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA210",GROUP_DESC:"CA210 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1826,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA210",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA210",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1826,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA210", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2826 ,protection_group_id: -1826, protection_element_id:-1826], primaryKey: false);
      insert('organizations', [ id: 101812, nci_institute_code: "CA211", name: "Foothill Presbyterian", city: "Glendora", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1827,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA211",GROUP_DESC:"CA211 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1827,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA211",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA211",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1827,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA211", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2827 ,protection_group_id: -1827, protection_element_id:-1827], primaryKey: false);
      insert('organizations', [ id: 101813, nci_institute_code: "CA212", name: "Childrens Hospital Central California", city: "Avenue", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1828,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA212",GROUP_DESC:"CA212 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1828,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA212",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA212",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1828,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA212", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2828 ,protection_group_id: -1828, protection_element_id:-1828], primaryKey: false);
      insert('organizations', [ id: 101814, nci_institute_code: "CA213", name: "Northern California Cancer Center", city: "Belmont", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1829,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA213",GROUP_DESC:"CA213 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1829,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA213",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA213",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1829,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA213", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2829 ,protection_group_id: -1829, protection_element_id:-1829], primaryKey: false);
      insert('organizations', [ id: 101815, nci_institute_code: "CA214", name: "University of California San Diego", city: "La Jolla", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1830,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA214",GROUP_DESC:"CA214 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1830,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA214",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA214",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1830,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA214", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2830 ,protection_group_id: -1830, protection_element_id:-1830], primaryKey: false);
      insert('organizations', [ id: 101816, nci_institute_code: "CA215", name: "Women's Cancer Center", city: "Palo Alto", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1831,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA215",GROUP_DESC:"CA215 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1831,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA215",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA215",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1831,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA215", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2831 ,protection_group_id: -1831, protection_element_id:-1831], primaryKey: false);
      insert('organizations', [ id: 101817, nci_institute_code: "CA216", name: "Saint Mary Medical Center", city: "Long Beach", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1832,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA216",GROUP_DESC:"CA216 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1832,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA216",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA216",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1832,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA216", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2832 ,protection_group_id: -1832, protection_element_id:-1832], primaryKey: false);
      insert('organizations', [ id: 101818, nci_institute_code: "CA218", name: "Saint Francis Medical Center", city: "Lynwood", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1833,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA218",GROUP_DESC:"CA218 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1833,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA218",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA218",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1833,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA218", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2833 ,protection_group_id: -1833, protection_element_id:-1833], primaryKey: false);
      insert('organizations', [ id: 101819, nci_institute_code: "CA219", name: "Riverside Medical Clinic", city: "Riverside", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1834,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA219",GROUP_DESC:"CA219 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1834,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA219",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA219",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1834,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA219", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2834 ,protection_group_id: -1834, protection_element_id:-1834], primaryKey: false);
      insert('organizations', [ id: 101820, nci_institute_code: "CA220", name: "Kaiser Permanente Los Angeles Medical Center", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1835,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA220",GROUP_DESC:"CA220 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1835,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA220",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA220",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1835,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA220", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2835 ,protection_group_id: -1835, protection_element_id:-1835], primaryKey: false);
      insert('organizations', [ id: 101821, nci_institute_code: "CA221", name: "Veterans Administration Medical Center Southern California", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1836,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA221",GROUP_DESC:"CA221 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1836,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA221",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA221",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1836,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA221", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2836 ,protection_group_id: -1836, protection_element_id:-1836], primaryKey: false);
      insert('organizations', [ id: 101822, nci_institute_code: "CA222", name: "California Pacific Medical Center", city: "San Francisco", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1837,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA222",GROUP_DESC:"CA222 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1837,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA222",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA222",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1837,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA222", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2837 ,protection_group_id: -1837, protection_element_id:-1837], primaryKey: false);
    }

    void m33() {
        // all33 (25 terms)
      insert('organizations', [ id: 101823, nci_institute_code: "CA223", name: "Kaiser Permanente, Roseville", city: "Roseville", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1838,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA223",GROUP_DESC:"CA223 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1838,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA223",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA223",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1838,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA223", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2838 ,protection_group_id: -1838, protection_element_id:-1838], primaryKey: false);
      insert('organizations', [ id: 101824, nci_institute_code: "CA224", name: "Orion Medical Sciences Institute", city: "Arcadia", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1839,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA224",GROUP_DESC:"CA224 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1839,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA224",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA224",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1839,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA224", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2839 ,protection_group_id: -1839, protection_element_id:-1839], primaryKey: false);
      insert('organizations', [ id: 101825, nci_institute_code: "CA225", name: "Mercy Hospital", city: "Bakersfield", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1840,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA225",GROUP_DESC:"CA225 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1840,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA225",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA225",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1840,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA225", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2840 ,protection_group_id: -1840, protection_element_id:-1840], primaryKey: false);
      insert('organizations', [ id: 101826, nci_institute_code: "CA226", name: "Kaweah Delta District Hospital", city: "Visalia", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1841,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA226",GROUP_DESC:"CA226 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1841,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA226",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA226",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1841,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA226", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2841 ,protection_group_id: -1841, protection_element_id:-1841], primaryKey: false);
      insert('organizations', [ id: 101827, nci_institute_code: "CA227", name: "Kaiser Permanente, Redwood City", city: "Redwood City", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1842,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA227",GROUP_DESC:"CA227 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1842,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA227",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA227",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1842,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA227", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2842 ,protection_group_id: -1842, protection_element_id:-1842], primaryKey: false);
      insert('organizations', [ id: 101828, nci_institute_code: "CA228", name: "Sherman Oaks Community Hospital", city: "Sherman Oaks", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1843,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA228",GROUP_DESC:"CA228 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1843,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA228",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA228",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1843,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA228", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2843 ,protection_group_id: -1843, protection_element_id:-1843], primaryKey: false);
      insert('organizations', [ id: 101829, nci_institute_code: "CA229", name: "Salinas Valley Memorial", city: "Salinas", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1844,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA229",GROUP_DESC:"CA229 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1844,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA229",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA229",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1844,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA229", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2844 ,protection_group_id: -1844, protection_element_id:-1844], primaryKey: false);
      insert('organizations', [ id: 101830, nci_institute_code: "CA230", name: "Scripps Clinic - Encinitas South", city: "Encinitas", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1845,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA230",GROUP_DESC:"CA230 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1845,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA230",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA230",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1845,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA230", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2845 ,protection_group_id: -1845, protection_element_id:-1845], primaryKey: false);
      insert('organizations', [ id: 101831, nci_institute_code: "CA231", name: "Veterans Administration N. Ca Sys of Clinics", city: "Benicia", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1846,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA231",GROUP_DESC:"CA231 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1846,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA231",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA231",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1846,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA231", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2846 ,protection_group_id: -1846, protection_element_id:-1846], primaryKey: false);
      insert('organizations', [ id: 101832, nci_institute_code: "CA232", name: "Stanford University Medical Center", city: "Stanford", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1847,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA232",GROUP_DESC:"CA232 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1847,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA232",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA232",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1847,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA232", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2847 ,protection_group_id: -1847, protection_element_id:-1847], primaryKey: false);
      insert('organizations', [ id: 101833, nci_institute_code: "CA234", name: "University of California School of Medicine", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1848,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA234",GROUP_DESC:"CA234 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1848,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA234",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA234",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1848,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA234", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2848 ,protection_group_id: -1848, protection_element_id:-1848], primaryKey: false);
      insert('organizations', [ id: 101834, nci_institute_code: "CA235", name: "Pacificia of The Valley Hospital", city: "Sun Valley", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1849,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA235",GROUP_DESC:"CA235 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1849,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA235",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA235",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1849,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA235", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2849 ,protection_group_id: -1849, protection_element_id:-1849], primaryKey: false);
      insert('organizations', [ id: 101835, nci_institute_code: "CA236", name: "Cancer Center of Santa Barbara", city: "Snata Barbara", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1850,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA236",GROUP_DESC:"CA236 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1850,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA236",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA236",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1850,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA236", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2850 ,protection_group_id: -1850, protection_element_id:-1850], primaryKey: false);
      insert('organizations', [ id: 101836, nci_institute_code: "CA237", name: "Southern California Permanente Medical Group", city: "Panorama City", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1851,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA237",GROUP_DESC:"CA237 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1851,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA237",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA237",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1851,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA237", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2851 ,protection_group_id: -1851, protection_element_id:-1851], primaryKey: false);
      insert('organizations', [ id: 101837, nci_institute_code: "CA238", name: "Kaiser Permanente-Richmond", city: "Richmond", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1852,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA238",GROUP_DESC:"CA238 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1852,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA238",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA238",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1852,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA238", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2852 ,protection_group_id: -1852, protection_element_id:-1852], primaryKey: false);
      insert('organizations', [ id: 101838, nci_institute_code: "CA239", name: "Good Samaritan Hospital", city: "San Jose", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1853,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA239",GROUP_DESC:"CA239 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1853,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA239",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA239",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1853,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA239", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2853 ,protection_group_id: -1853, protection_element_id:-1853], primaryKey: false);
      insert('organizations', [ id: 101839, nci_institute_code: "CA240", name: "Southwest Cancer Care", city: "Poway", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1854,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA240",GROUP_DESC:"CA240 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1854,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA240",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA240",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1854,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA240", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2854 ,protection_group_id: -1854, protection_element_id:-1854], primaryKey: false);
      insert('organizations', [ id: 101840, nci_institute_code: "CA241", name: "Prigge Radiation Oncology Center of Modesto", city: "Modesto", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1855,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA241",GROUP_DESC:"CA241 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1855,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA241",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA241",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1855,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA241", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2855 ,protection_group_id: -1855, protection_element_id:-1855], primaryKey: false);
      insert('organizations', [ id: 101841, nci_institute_code: "CA242", name: "Kaiser Permanente", city: "Fresno", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1856,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA242",GROUP_DESC:"CA242 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1856,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA242",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA242",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1856,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA242", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2856 ,protection_group_id: -1856, protection_element_id:-1856], primaryKey: false);
      insert('organizations', [ id: 101842, nci_institute_code: "CA243", name: "Sutter General Hospital", city: "Sacramento", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1857,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA243",GROUP_DESC:"CA243 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1857,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA243",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA243",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1857,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA243", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2857 ,protection_group_id: -1857, protection_element_id:-1857], primaryKey: false);
      insert('organizations', [ id: 101843, nci_institute_code: "CA244", name: "Kaiser Permanente, Martinez", city: "Martinez", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1858,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA244",GROUP_DESC:"CA244 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1858,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA244",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA244",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1858,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA244", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2858 ,protection_group_id: -1858, protection_element_id:-1858], primaryKey: false);
      insert('organizations', [ id: 101844, nci_institute_code: "CA245", name: "Palm Drive Hospital", city: "Sebastapol", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1859,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA245",GROUP_DESC:"CA245 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1859,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA245",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA245",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1859,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA245", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2859 ,protection_group_id: -1859, protection_element_id:-1859], primaryKey: false);
      insert('organizations', [ id: 101845, nci_institute_code: "CA246", name: "Consulate General-Rep of Hungary", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1860,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA246",GROUP_DESC:"CA246 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1860,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA246",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA246",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1860,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA246", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2860 ,protection_group_id: -1860, protection_element_id:-1860], primaryKey: false);
      insert('organizations', [ id: 101846, nci_institute_code: "CA247", name: "Sepulveda Veterans Administration Hospital", city: "Sepulveda", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1861,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA247",GROUP_DESC:"CA247 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1861,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA247",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA247",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1861,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA247", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2861 ,protection_group_id: -1861, protection_element_id:-1861], primaryKey: false);
      insert('organizations', [ id: 101847, nci_institute_code: "CA248", name: "Saint Helena Hospital", city: "Deer Park", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1862,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA248",GROUP_DESC:"CA248 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1862,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA248",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA248",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1862,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA248", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2862 ,protection_group_id: -1862, protection_element_id:-1862], primaryKey: false);
    }

    void m34() {
        // all34 (25 terms)
      insert('organizations', [ id: 101848, nci_institute_code: "CA249", name: "Moores University of California San Diego Cancer Center", city: "LA Jolla", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1863,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA249",GROUP_DESC:"CA249 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1863,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA249",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA249",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1863,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA249", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2863 ,protection_group_id: -1863, protection_element_id:-1863], primaryKey: false);
      insert('organizations', [ id: 101849, nci_institute_code: "CA250", name: "Little Company of Mary Hospital", city: "Torrance", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1864,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA250",GROUP_DESC:"CA250 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1864,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA250",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA250",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1864,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA250", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2864 ,protection_group_id: -1864, protection_element_id:-1864], primaryKey: false);
      insert('organizations', [ id: 101850, nci_institute_code: "CA251", name: "National Childhood Cancer Foundation", city: "Arcadia", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1865,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA251",GROUP_DESC:"CA251 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1865,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA251",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA251",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1865,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA251", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2865 ,protection_group_id: -1865, protection_element_id:-1865], primaryKey: false);
      insert('organizations', [ id: 101851, nci_institute_code: "CA252", name: "Kaiser Permanente, Santa Rosa", city: "Santa Rosa", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1866,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA252",GROUP_DESC:"CA252 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1866,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA252",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA252",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1866,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA252", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2866 ,protection_group_id: -1866, protection_element_id:-1866], primaryKey: false);
      insert('organizations', [ id: 101852, nci_institute_code: "CA253", name: "Sierra Nevada Memorial", city: "Grass Valley", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1867,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA253",GROUP_DESC:"CA253 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1867,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA253",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA253",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1867,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA253", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2867 ,protection_group_id: -1867, protection_element_id:-1867], primaryKey: false);
      insert('organizations', [ id: 101853, nci_institute_code: "CA254", name: "Mercy Hospital and Medical Center", city: "San Diego", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1868,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA254",GROUP_DESC:"CA254 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1868,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA254",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA254",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1868,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA254", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2868 ,protection_group_id: -1868, protection_element_id:-1868], primaryKey: false);
      insert('organizations', [ id: 101854, nci_institute_code: "CA255", name: "Fhp Hospital", city: "Fountain Valley", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1869,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA255",GROUP_DESC:"CA255 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1869,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA255",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA255",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1869,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA255", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2869 ,protection_group_id: -1869, protection_element_id:-1869], primaryKey: false);
      insert('organizations', [ id: 101855, nci_institute_code: "CA256", name: "Arroyo Grande Community", city: "Arroyo Grande", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1870,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA256",GROUP_DESC:"CA256 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1870,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA256",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA256",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1870,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA256", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2870 ,protection_group_id: -1870, protection_element_id:-1870], primaryKey: false);
      insert('organizations', [ id: 101856, nci_institute_code: "CA257", name: "Cigna Medical Center", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1871,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA257",GROUP_DESC:"CA257 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1871,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA257",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA257",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1871,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA257", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2871 ,protection_group_id: -1871, protection_element_id:-1871], primaryKey: false);
      insert('organizations', [ id: 101857, nci_institute_code: "CA258", name: "Westlake Comp Breast Center", city: "Westlake Village", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1872,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA258",GROUP_DESC:"CA258 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1872,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA258",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA258",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1872,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA258", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2872 ,protection_group_id: -1872, protection_element_id:-1872], primaryKey: false);
      insert('organizations', [ id: 101858, nci_institute_code: "CA259", name: "Saddleback Memorial Medical Center", city: "Laguna Hills", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1873,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA259",GROUP_DESC:"CA259 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1873,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA259",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA259",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1873,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA259", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2873 ,protection_group_id: -1873, protection_element_id:-1873], primaryKey: false);
      insert('organizations', [ id: 101859, nci_institute_code: "CA260", name: "Redwood Regional Medical Group - Main Office", city: "Santa Rosa", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1874,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA260",GROUP_DESC:"CA260 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1874,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA260",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA260",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1874,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA260", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2874 ,protection_group_id: -1874, protection_element_id:-1874], primaryKey: false);
      insert('organizations', [ id: 101860, nci_institute_code: "CA262", name: "Stockton Hematology Oncology Medical Group", city: "Stockton", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1875,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA262",GROUP_DESC:"CA262 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1875,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA262",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA262",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1875,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA262", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2875 ,protection_group_id: -1875, protection_element_id:-1875], primaryKey: false);
      insert('organizations', [ id: 101861, nci_institute_code: "CA263", name: "Southern California Permanente Medical Group", city: "Anaheim", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1876,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA263",GROUP_DESC:"CA263 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1876,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA263",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA263",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1876,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA263", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2876 ,protection_group_id: -1876, protection_element_id:-1876], primaryKey: false);
      insert('organizations', [ id: 101862, nci_institute_code: "CA264", name: "Shasta Regional Medical Center", city: "Redding", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1877,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA264",GROUP_DESC:"CA264 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1877,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA264",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA264",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1877,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA264", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2877 ,protection_group_id: -1877, protection_element_id:-1877], primaryKey: false);
      insert('organizations', [ id: 101863, nci_institute_code: "CA265", name: "Beaver Medical Clinic, Incorporated", city: "Highland", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1878,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA265",GROUP_DESC:"CA265 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1878,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA265",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA265",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1878,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA265", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2878 ,protection_group_id: -1878, protection_element_id:-1878], primaryKey: false);
      insert('organizations', [ id: 101864, nci_institute_code: "CA266", name: "San Diego Blood Bank", city: "San Diego", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1879,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA266",GROUP_DESC:"CA266 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1879,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA266",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA266",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1879,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA266", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2879 ,protection_group_id: -1879, protection_element_id:-1879], primaryKey: false);
      insert('organizations', [ id: 101865, nci_institute_code: "CA267", name: "Care More Medical Group", city: "Pico Rivera", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1880,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA267",GROUP_DESC:"CA267 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1880,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA267",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA267",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1880,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA267", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2880 ,protection_group_id: -1880, protection_element_id:-1880], primaryKey: false);
      insert('organizations', [ id: 101866, nci_institute_code: "CA268", name: "Sharp Medical Group of North County", city: "Vista", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1881,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA268",GROUP_DESC:"CA268 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1881,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA268",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA268",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1881,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA268", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2881 ,protection_group_id: -1881, protection_element_id:-1881], primaryKey: false);
      insert('organizations', [ id: 101867, nci_institute_code: "CA269", name: "Radiological Associates of Sacramento", city: "Sacramento", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1882,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA269",GROUP_DESC:"CA269 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1882,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA269",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA269",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1882,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA269", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2882 ,protection_group_id: -1882, protection_element_id:-1882], primaryKey: false);
      insert('organizations', [ id: 101868, nci_institute_code: "CA270", name: "South Orange County Hematology Oncology Associates", city: "Laguna Hills", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1883,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA270",GROUP_DESC:"CA270 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1883,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA270",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA270",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1883,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA270", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2883 ,protection_group_id: -1883, protection_element_id:-1883], primaryKey: false);
      insert('organizations', [ id: 101869, nci_institute_code: "CA271", name: "Saint Louise Hospital", city: "Morgan Hill", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1884,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA271",GROUP_DESC:"CA271 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1884,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA271",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA271",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1884,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA271", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2884 ,protection_group_id: -1884, protection_element_id:-1884], primaryKey: false);
      insert('organizations', [ id: 101870, nci_institute_code: "CA272", name: "Palomar Medical Center", city: "Escondido", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1885,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA272",GROUP_DESC:"CA272 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1885,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA272",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA272",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1885,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA272", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2885 ,protection_group_id: -1885, protection_element_id:-1885], primaryKey: false);
      insert('organizations', [ id: 101871, nci_institute_code: "CA273", name: "Northbay Cancer Center", city: "Fairfield", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1886,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA273",GROUP_DESC:"CA273 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1886,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA273",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA273",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1886,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA273", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2886 ,protection_group_id: -1886, protection_element_id:-1886], primaryKey: false);
      insert('organizations', [ id: 101872, nci_institute_code: "CA274", name: "Santa Barbara Hematology Oncology Group, Inc.- Santa Barbara", city: "Santa Barbara", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1887,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA274",GROUP_DESC:"CA274 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1887,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA274",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA274",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1887,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA274", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2887 ,protection_group_id: -1887, protection_element_id:-1887], primaryKey: false);
    }

    void m35() {
        // all35 (25 terms)
      insert('organizations', [ id: 101873, nci_institute_code: "CA275", name: "Brea Community Hospital", city: "Brea", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1888,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA275",GROUP_DESC:"CA275 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1888,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA275",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA275",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1888,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA275", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2888 ,protection_group_id: -1888, protection_element_id:-1888], primaryKey: false);
      insert('organizations', [ id: 101874, nci_institute_code: "CA276", name: "Sutter Health Western Division Cancer Research Group", city: "Greenbrae", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1889,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA276",GROUP_DESC:"CA276 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1889,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA276",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA276",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1889,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA276", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2889 ,protection_group_id: -1889, protection_element_id:-1889], primaryKey: false);
      insert('organizations', [ id: 101875, nci_institute_code: "CA277", name: "Central Los Angeles CCOP", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1890,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA277",GROUP_DESC:"CA277 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1890,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA277",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA277",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1890,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA277", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2890 ,protection_group_id: -1890, protection_element_id:-1890], primaryKey: false);
      insert('organizations', [ id: 101876, nci_institute_code: "CA278", name: "Gould Medical Foundation", city: "Modesto", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1891,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA278",GROUP_DESC:"CA278 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1891,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA278",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA278",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1891,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA278", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2891 ,protection_group_id: -1891, protection_element_id:-1891], primaryKey: false);
      insert('organizations', [ id: 101877, nci_institute_code: "CA279", name: "Hemet Valley Medical Center", city: "Hemet", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1892,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA279",GROUP_DESC:"CA279 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1892,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA279",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA279",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1892,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA279", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2892 ,protection_group_id: -1892, protection_element_id:-1892], primaryKey: false);
      insert('organizations', [ id: 101878, nci_institute_code: "CA281", name: "Sacramento Regional Oncology", city: "Sacramento", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1893,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA281",GROUP_DESC:"CA281 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1893,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA281",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA281",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1893,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA281", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2893 ,protection_group_id: -1893, protection_element_id:-1893], primaryKey: false);
      insert('organizations', [ id: 101879, nci_institute_code: "CA282", name: "San Francisco UCOP", city: "San Francisco", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1894,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA282",GROUP_DESC:"CA282 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1894,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA282",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA282",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1894,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA282", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2894 ,protection_group_id: -1894, protection_element_id:-1894], primaryKey: false);
      insert('organizations', [ id: 101880, nci_institute_code: "CA283", name: "California Cancer Center - North Fresno", city: "Fresno", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1895,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA283",GROUP_DESC:"CA283 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1895,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA283",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA283",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1895,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA283", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2895 ,protection_group_id: -1895, protection_element_id:-1895], primaryKey: false);
      insert('organizations', [ id: 101881, nci_institute_code: "CA284", name: "Saint Luke Medical Center", city: "Pasadena", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1896,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA284",GROUP_DESC:"CA284 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1896,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA284",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA284",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1896,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA284", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2896 ,protection_group_id: -1896, protection_element_id:-1896], primaryKey: false);
      insert('organizations', [ id: 101882, nci_institute_code: "CA285", name: "Medical Group Incorporated", city: "Oxnard", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1897,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA285",GROUP_DESC:"CA285 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1897,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA285",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA285",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1897,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA285", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2897 ,protection_group_id: -1897, protection_element_id:-1897], primaryKey: false);
      insert('organizations', [ id: 101883, nci_institute_code: "CA287", name: "Radiation Medical Group", city: "San Diego", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1898,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA287",GROUP_DESC:"CA287 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1898,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA287",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA287",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1898,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA287", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2898 ,protection_group_id: -1898, protection_element_id:-1898], primaryKey: false);
      insert('organizations', [ id: 101884, nci_institute_code: "CA288", name: "Santa Cruz Radiation Oncology Medical Group", city: "Santa Cruz", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1899,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA288",GROUP_DESC:"CA288 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1899,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA288",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA288",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1899,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA288", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2899 ,protection_group_id: -1899, protection_element_id:-1899], primaryKey: false);
      insert('organizations', [ id: 101885, nci_institute_code: "CA289", name: "Santa Rosa Radiation Oncology Center", city: "Santa Rosa", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1900,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA289",GROUP_DESC:"CA289 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1900,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA289",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA289",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1900,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA289", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2900 ,protection_group_id: -1900, protection_element_id:-1900], primaryKey: false);
      insert('organizations', [ id: 101886, nci_institute_code: "CA292", name: "John Wayne Cancer Institute", city: "Santa Monica", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1901,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA292",GROUP_DESC:"CA292 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1901,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA292",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA292",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1901,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA292", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2901 ,protection_group_id: -1901, protection_element_id:-1901], primaryKey: false);
      insert('organizations', [ id: 101887, nci_institute_code: "CA294", name: "San Leandro Hospital", city: "San Leandro", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1902,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA294",GROUP_DESC:"CA294 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1902,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA294",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA294",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1902,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA294", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2902 ,protection_group_id: -1902, protection_element_id:-1902], primaryKey: false);
      insert('organizations', [ id: 101888, nci_institute_code: "CA295", name: "Wilshire Oncology Medical", city: "Rancho Cucamonga", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1903,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA295",GROUP_DESC:"CA295 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1903,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA295",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA295",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1903,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA295", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2903 ,protection_group_id: -1903, protection_element_id:-1903], primaryKey: false);
      insert('organizations', [ id: 101889, nci_institute_code: "CA296", name: "Women's and Children's Hospital", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1904,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA296",GROUP_DESC:"CA296 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1904,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA296",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA296",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1904,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA296", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2904 ,protection_group_id: -1904, protection_element_id:-1904], primaryKey: false);
      insert('organizations', [ id: 101890, nci_institute_code: "CA297", name: "Drew-Martin Luther King", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1905,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA297",GROUP_DESC:"CA297 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1905,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA297",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA297",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1905,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA297", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2905 ,protection_group_id: -1905, protection_element_id:-1905], primaryKey: false);
      insert('organizations', [ id: 101891, nci_institute_code: "CA299", name: "Hematology Oncology Consultants", city: "Van Nuys", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1906,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA299",GROUP_DESC:"CA299 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1906,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA299",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA299",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1906,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA299", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2906 ,protection_group_id: -1906, protection_element_id:-1906], primaryKey: false);
      insert('organizations', [ id: 101892, nci_institute_code: "CA304", name: "Western Hematology Oncology", city: "Glendale", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1907,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA304",GROUP_DESC:"CA304 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1907,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA304",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA304",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1907,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA304", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2907 ,protection_group_id: -1907, protection_element_id:-1907], primaryKey: false);
      insert('organizations', [ id: 101893, nci_institute_code: "CA305", name: "Veterans Administration Outpatient Clinic", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1908,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA305",GROUP_DESC:"CA305 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1908,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA305",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA305",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1908,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA305", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2908 ,protection_group_id: -1908, protection_element_id:-1908], primaryKey: false);
      insert('organizations', [ id: 101894, nci_institute_code: "CA306", name: "Kaiser Permanente", city: "San Diego", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1909,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA306",GROUP_DESC:"CA306 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1909,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA306",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA306",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1909,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA306", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2909 ,protection_group_id: -1909, protection_element_id:-1909], primaryKey: false);
      insert('organizations', [ id: 101895, nci_institute_code: "CA307", name: "Agouron Pharmaceuticals, Incorporated", city: "San Diego", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1910,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA307",GROUP_DESC:"CA307 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1910,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA307",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA307",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1910,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA307", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2910 ,protection_group_id: -1910, protection_element_id:-1910], primaryKey: false);
      insert('organizations', [ id: 101896, nci_institute_code: "CA308", name: "South Coast Urological Group", city: "Laguna Hills", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1911,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA308",GROUP_DESC:"CA308 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1911,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA308",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA308",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1911,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA308", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2911 ,protection_group_id: -1911, protection_element_id:-1911], primaryKey: false);
      insert('organizations', [ id: 101897, nci_institute_code: "CA309", name: "Hematology Oncology Association", city: "Torrance", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1912,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA309",GROUP_DESC:"CA309 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1912,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA309",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA309",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1912,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA309", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2912 ,protection_group_id: -1912, protection_element_id:-1912], primaryKey: false);
    }

    void m36() {
        // all36 (25 terms)
      insert('organizations', [ id: 101898, nci_institute_code: "CA310", name: "Coast Hematology Oncology Medical Group Incorporated.,", city: "Long Beach", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1913,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA310",GROUP_DESC:"CA310 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1913,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA310",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA310",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1913,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA310", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2913 ,protection_group_id: -1913, protection_element_id:-1913], primaryKey: false);
      insert('organizations', [ id: 101899, nci_institute_code: "CA311", name: "Sierra Oncology Medical Associates", city: "Cameron Park", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1914,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA311",GROUP_DESC:"CA311 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1914,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA311",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA311",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1914,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA311", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2914 ,protection_group_id: -1914, protection_element_id:-1914], primaryKey: false);
      insert('organizations', [ id: 101900, nci_institute_code: "CA312", name: "Inland Hematology Oncology Medical Group", city: "San Bernardino", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1915,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA312",GROUP_DESC:"CA312 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1915,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA312",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA312",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1915,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA312", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2915 ,protection_group_id: -1915, protection_element_id:-1915], primaryKey: false);
      insert('organizations', [ id: 101901, nci_institute_code: "CA313", name: "Lombard Cancer Center", city: "Thousand Oaks", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1916,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA313",GROUP_DESC:"CA313 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1916,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA313",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA313",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1916,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA313", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2916 ,protection_group_id: -1916, protection_element_id:-1916], primaryKey: false);
      insert('organizations', [ id: 101902, nci_institute_code: "CA314", name: "Island View Hematology Oncology", city: "Oxnard", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1917,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA314",GROUP_DESC:"CA314 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1917,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA314",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA314",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1917,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA314", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2917 ,protection_group_id: -1917, protection_element_id:-1917], primaryKey: false);
      insert('organizations', [ id: 101903, nci_institute_code: "CA315", name: "Santa Clarita/University California Los Angeles Cancer Center", city: "Valencia", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1918,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA315",GROUP_DESC:"CA315 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1918,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA315",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA315",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1918,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA315", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2918 ,protection_group_id: -1918, protection_element_id:-1918], primaryKey: false);
      insert('organizations', [ id: 101904, nci_institute_code: "CA316", name: "Riverside Oncology/Medical Group, Incorporated.,", city: "Riverside", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1919,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA316",GROUP_DESC:"CA316 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1919,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA316",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA316",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1919,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA316", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2919 ,protection_group_id: -1919, protection_element_id:-1919], primaryKey: false);
      insert('organizations', [ id: 101905, nci_institute_code: "CA317", name: "Medical Corporation", city: "San Gabriel", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1920,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA317",GROUP_DESC:"CA317 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1920,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA317",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA317",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1920,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA317", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2920 ,protection_group_id: -1920, protection_element_id:-1920], primaryKey: false);
      insert('organizations', [ id: 101906, nci_institute_code: "CA318", name: "Alta Bates Summit Medical Center - Summit Campus", city: "Oakland", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1921,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA318",GROUP_DESC:"CA318 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1921,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA318",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA318",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1921,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA318", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2921 ,protection_group_id: -1921, protection_element_id:-1921], primaryKey: false);
      insert('organizations', [ id: 101907, nci_institute_code: "CA319", name: "Permanente Medical Group, Incorporated.,", city: "San Francisco", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1922,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA319",GROUP_DESC:"CA319 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1922,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA319",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA319",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1922,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA319", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2922 ,protection_group_id: -1922, protection_element_id:-1922], primaryKey: false);
      insert('organizations', [ id: 101908, nci_institute_code: "CA320", name: "West Anaheim Medical Center", city: "Anaheim", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1923,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA320",GROUP_DESC:"CA320 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1923,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA320",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA320",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1923,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA320", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2923 ,protection_group_id: -1923, protection_element_id:-1923], primaryKey: false);
      insert('organizations', [ id: 101909, nci_institute_code: "CA321", name: "East Valley Hematology Oncology Medical Group", city: "Burbank", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1924,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA321",GROUP_DESC:"CA321 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1924,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA321",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA321",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1924,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA321", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2924 ,protection_group_id: -1924, protection_element_id:-1924], primaryKey: false);
      insert('organizations', [ id: 101910, nci_institute_code: "CA322", name: "Metropolitian Oncology Medical Group Inc", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1925,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA322",GROUP_DESC:"CA322 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1925,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA322",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA322",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1925,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA322", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2925 ,protection_group_id: -1925, protection_element_id:-1925], primaryKey: false);
      insert('organizations', [ id: 101911, nci_institute_code: "CA323", name: "Hematology Oncology Medical Group/Orange County", city: "Orange", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1926,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA323",GROUP_DESC:"CA323 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1926,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA323",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA323",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1926,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA323", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2926 ,protection_group_id: -1926, protection_element_id:-1926], primaryKey: false);
      insert('organizations', [ id: 101912, nci_institute_code: "CA324", name: "East County Hematology Oncology Associates", city: "San Diego", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1927,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA324",GROUP_DESC:"CA324 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1927,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA324",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA324",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1927,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA324", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2927 ,protection_group_id: -1927, protection_element_id:-1927], primaryKey: false);
      insert('organizations', [ id: 101913, nci_institute_code: "CA325", name: "Los Angeles Hematology Oncology Medical Group", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1928,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA325",GROUP_DESC:"CA325 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1928,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA325",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA325",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1928,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA325", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2928 ,protection_group_id: -1928, protection_element_id:-1928], primaryKey: false);
      insert('organizations', [ id: 101914, nci_institute_code: "CA326", name: "Marin Oncology Associates", city: "Greenbrae", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1929,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA326",GROUP_DESC:"CA326 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1929,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA326",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA326",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1929,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA326", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2929 ,protection_group_id: -1929, protection_element_id:-1929], primaryKey: false);
      insert('organizations', [ id: 101915, nci_institute_code: "CA327", name: "Southbay Oncology/Hematology Partners", city: "Campbell", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1930,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA327",GROUP_DESC:"CA327 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1930,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA327",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA327",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1930,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA327", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2930 ,protection_group_id: -1930, protection_element_id:-1930], primaryKey: false);
      insert('organizations', [ id: 101916, nci_institute_code: "CA328", name: "Sharp Healthcare", city: "San Diego", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1931,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA328",GROUP_DESC:"CA328 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1931,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA328",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA328",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1931,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA328", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2931 ,protection_group_id: -1931, protection_element_id:-1931], primaryKey: false);
      insert('organizations', [ id: 101917, nci_institute_code: "CA329", name: "Coagulation Hematology Oncology., Inc.", city: "Orange", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1932,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA329",GROUP_DESC:"CA329 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1932,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA329",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA329",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1932,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA329", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2932 ,protection_group_id: -1932, protection_element_id:-1932], primaryKey: false);
      insert('organizations', [ id: 101918, nci_institute_code: "CA330", name: "Shapiro and Stafford Medical Office", city: "Arcadia", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1933,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA330",GROUP_DESC:"CA330 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1933,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA330",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA330",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1933,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA330", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2933 ,protection_group_id: -1933, protection_element_id:-1933], primaryKey: false);
      insert('organizations', [ id: 101919, nci_institute_code: "CA331", name: "Hematology/Medical Oncology", city: "Chico", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1934,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA331",GROUP_DESC:"CA331 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1934,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA331",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA331",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1934,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA331", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2934 ,protection_group_id: -1934, protection_element_id:-1934], primaryKey: false);
      insert('organizations', [ id: 101920, nci_institute_code: "CA332", name: "Kaiser Permanente at San Diego", city: "San Diego", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1935,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA332",GROUP_DESC:"CA332 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1935,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA332",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA332",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1935,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA332", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2935 ,protection_group_id: -1935, protection_element_id:-1935], primaryKey: false);
      insert('organizations', [ id: 101921, nci_institute_code: "CA333", name: "Redwood Regional Medical Group - Napa", city: "Napa", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1936,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA333",GROUP_DESC:"CA333 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1936,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA333",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA333",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1936,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA333", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2936 ,protection_group_id: -1936, protection_element_id:-1936], primaryKey: false);
      insert('organizations', [ id: 101922, nci_institute_code: "CA334", name: "Cancer Care Consultants", city: "Redding", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1937,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA334",GROUP_DESC:"CA334 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1937,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA334",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA334",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1937,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA334", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2937 ,protection_group_id: -1937, protection_element_id:-1937], primaryKey: false);
    }

    void m37() {
        // all37 (25 terms)
      insert('organizations', [ id: 101923, nci_institute_code: "CA335", name: "Watsonville Community Hospital", city: "Watsonville", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1938,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA335",GROUP_DESC:"CA335 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1938,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA335",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA335",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1938,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA335", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2938 ,protection_group_id: -1938, protection_element_id:-1938], primaryKey: false);
      insert('organizations', [ id: 101924, nci_institute_code: "CA336", name: "Hematology Oncology Medical Group", city: "Encino", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1939,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA336",GROUP_DESC:"CA336 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1939,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA336",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA336",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1939,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA336", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2939 ,protection_group_id: -1939, protection_element_id:-1939], primaryKey: false);
      insert('organizations', [ id: 101925, nci_institute_code: "CA338", name: "Pacific Hematology Oncology Associates", city: "San Francisco", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1940,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA338",GROUP_DESC:"CA338 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1940,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA338",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA338",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1940,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA338", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2940 ,protection_group_id: -1940, protection_element_id:-1940], primaryKey: false);
      insert('organizations', [ id: 101926, nci_institute_code: "CA340", name: "Cancer Care Associates", city: "Torrance", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1941,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA340",GROUP_DESC:"CA340 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1941,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA340",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA340",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1941,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA340", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2941 ,protection_group_id: -1941, protection_element_id:-1941], primaryKey: false);
      insert('organizations', [ id: 101927, nci_institute_code: "CA341", name: "San Gabriel Valley", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1942,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA341",GROUP_DESC:"CA341 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1942,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA341",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA341",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1942,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA341", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2942 ,protection_group_id: -1942, protection_element_id:-1942], primaryKey: false);
      insert('organizations', [ id: 101928, nci_institute_code: "CA342", name: "Diablo Clinical Research Incorporated.,", city: "Walnut Creek", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1943,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA342",GROUP_DESC:"CA342 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1943,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA342",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA342",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1943,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA342", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2943 ,protection_group_id: -1943, protection_element_id:-1943], primaryKey: false);
      insert('organizations', [ id: 101929, nci_institute_code: "CA343", name: "Cancer Care Consultants Medical Group", city: "Santa Monica", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1944,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA343",GROUP_DESC:"CA343 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1944,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA343",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA343",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1944,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA343", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2944 ,protection_group_id: -1944, protection_element_id:-1944], primaryKey: false);
      insert('organizations', [ id: 101930, nci_institute_code: "CA344", name: "California Cancer Care Group", city: "Modesto", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1945,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA344",GROUP_DESC:"CA344 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1945,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA344",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA344",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1945,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA344", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2945 ,protection_group_id: -1945, protection_element_id:-1945], primaryKey: false);
      insert('organizations', [ id: 101931, nci_institute_code: "CA345", name: "Henry Mayo Newhall Memorial Hospital", city: "Valencia", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1946,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA345",GROUP_DESC:"CA345 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1946,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA345",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA345",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1946,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA345", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2946 ,protection_group_id: -1946, protection_element_id:-1946], primaryKey: false);
      insert('organizations', [ id: 101932, nci_institute_code: "CA347", name: "Sonora Cancer Center", city: "Sonora", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1947,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA347",GROUP_DESC:"CA347 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1947,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA347",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA347",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1947,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA347", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2947 ,protection_group_id: -1947, protection_element_id:-1947], primaryKey: false);
      insert('organizations', [ id: 101933, nci_institute_code: "CA348", name: "Western Tumor Medical Group, Incorporated.,", city: "Sherman Oaks", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1948,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA348",GROUP_DESC:"CA348 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1948,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA348",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA348",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1948,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA348", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2948 ,protection_group_id: -1948, protection_element_id:-1948], primaryKey: false);
      insert('organizations', [ id: 101934, nci_institute_code: "CA349", name: "Consultants Medical Group", city: "Roseville", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1949,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA349",GROUP_DESC:"CA349 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1949,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA349",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA349",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1949,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA349", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2949 ,protection_group_id: -1949, protection_element_id:-1949], primaryKey: false);
      insert('organizations', [ id: 101935, nci_institute_code: "CA350", name: "Charter Community Hospital", city: "Hawaiian Garden", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1950,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA350",GROUP_DESC:"CA350 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1950,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA350",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA350",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1950,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA350", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2950 ,protection_group_id: -1950, protection_element_id:-1950], primaryKey: false);
      insert('organizations', [ id: 101936, nci_institute_code: "CA351", name: "Compassionate Oncology/Hematology Medical Group", city: "Tarzana", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1951,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA351",GROUP_DESC:"CA351 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1951,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA351",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA351",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1951,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA351", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2951 ,protection_group_id: -1951, protection_element_id:-1951], primaryKey: false);
      insert('organizations', [ id: 101937, nci_institute_code: "CA352", name: "Palo Alto Medical Foundation-Camino Division", city: "Sunnyvale", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1952,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA352",GROUP_DESC:"CA352 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1952,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA352",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA352",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1952,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA352", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2952 ,protection_group_id: -1952, protection_element_id:-1952], primaryKey: false);
      insert('organizations', [ id: 101938, nci_institute_code: "CA353", name: "Visalia Medical Clinic, Incorportated.,", city: "Visalia", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1953,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA353",GROUP_DESC:"CA353 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1953,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA353",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA353",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1953,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA353", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2953 ,protection_group_id: -1953, protection_element_id:-1953], primaryKey: false);
      insert('organizations', [ id: 101939, nci_institute_code: "CA354", name: "Medical Oncology and Hematology", city: "Bakersfield", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1954,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA354",GROUP_DESC:"CA354 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1954,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA354",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA354",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1954,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA354", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2954 ,protection_group_id: -1954, protection_element_id:-1954], primaryKey: false);
      insert('organizations', [ id: 101940, nci_institute_code: "CA356", name: "Code Available", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1955,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA356",GROUP_DESC:"CA356 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1955,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA356",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA356",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1955,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA356", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2955 ,protection_group_id: -1955, protection_element_id:-1955], primaryKey: false);
      insert('organizations', [ id: 101941, nci_institute_code: "CA357", name: "Pacific Coast Hematology/Oncology, Suite 911", city: "Irvine", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1956,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA357",GROUP_DESC:"CA357 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1956,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA357",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA357",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1956,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA357", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2956 ,protection_group_id: -1956, protection_element_id:-1956], primaryKey: false);
      insert('organizations', [ id: 101942, nci_institute_code: "CA358", name: "Breast Center Medical Group, Incorpoated", city: "Redding", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1957,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA358",GROUP_DESC:"CA358 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1957,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA358",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA358",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1957,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA358", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2957 ,protection_group_id: -1957, protection_element_id:-1957], primaryKey: false);
      insert('organizations', [ id: 101943, nci_institute_code: "CA360", name: "Idec Pharmaceuticals Corporation", city: "San Diego", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1958,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA360",GROUP_DESC:"CA360 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1958,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA360",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA360",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1958,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA360", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2958 ,protection_group_id: -1958, protection_element_id:-1958], primaryKey: false);
      insert('organizations', [ id: 101944, nci_institute_code: "CA361", name: "Suite #3106", city: "Oakland", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1959,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA361",GROUP_DESC:"CA361 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1959,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA361",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA361",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1959,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA361", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2959 ,protection_group_id: -1959, protection_element_id:-1959], primaryKey: false);
      insert('organizations', [ id: 101945, nci_institute_code: "CA362", name: "Chanduri, Swarna S, MD (Office)", city: "Pomona", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1960,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA362",GROUP_DESC:"CA362 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1960,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA362",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA362",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1960,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA362", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2960 ,protection_group_id: -1960, protection_element_id:-1960], primaryKey: false);
      insert('organizations', [ id: 101946, nci_institute_code: "CA363", name: "Suite 429", city: "San Diego", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1961,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA363",GROUP_DESC:"CA363 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1961,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA363",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA363",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1961,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA363", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2961 ,protection_group_id: -1961, protection_element_id:-1961], primaryKey: false);
      insert('organizations', [ id: 101947, nci_institute_code: "CA365", name: "Marshall Hospital", city: "Placerville", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1962,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA365",GROUP_DESC:"CA365 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1962,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA365",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA365",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1962,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA365", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2962 ,protection_group_id: -1962, protection_element_id:-1962], primaryKey: false);
    }

    void m38() {
        // all38 (25 terms)
      insert('organizations', [ id: 101948, nci_institute_code: "CA366", name: "Woodland Memorial Hospital", city: "Woodland", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1963,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA366",GROUP_DESC:"CA366 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1963,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA366",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA366",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1963,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA366", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2963 ,protection_group_id: -1963, protection_element_id:-1963], primaryKey: false);
      insert('organizations', [ id: 101949, nci_institute_code: "CA367", name: "Pomona Valley Hospital Medical Center", city: "Pomona", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1964,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA367",GROUP_DESC:"CA367 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1964,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA367",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA367",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1964,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA367", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2964 ,protection_group_id: -1964, protection_element_id:-1964], primaryKey: false);
      insert('organizations', [ id: 101950, nci_institute_code: "CA368", name: "The Permanente Medical Group-Roseville Radiation Oncology", city: "Roseville", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1965,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA368",GROUP_DESC:"CA368 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1965,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA368",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA368",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1965,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA368", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2965 ,protection_group_id: -1965, protection_element_id:-1965], primaryKey: false);
      insert('organizations', [ id: 101951, nci_institute_code: "CA369", name: "Stanislaus Medical Center", city: "Modesto", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1966,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA369",GROUP_DESC:"CA369 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1966,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA369",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA369",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1966,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA369", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2966 ,protection_group_id: -1966, protection_element_id:-1966], primaryKey: false);
      insert('organizations', [ id: 101952, nci_institute_code: "CA370", name: "Kaiser Permanente", city: "Woodland Hills", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1967,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA370",GROUP_DESC:"CA370 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1967,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA370",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA370",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1967,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA370", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2967 ,protection_group_id: -1967, protection_element_id:-1967], primaryKey: false);
      insert('organizations', [ id: 101953, nci_institute_code: "CA371", name: "Columbia South Valley Hospital", city: "Gilroy", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1968,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA371",GROUP_DESC:"CA371 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1968,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA371",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA371",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1968,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA371", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2968 ,protection_group_id: -1968, protection_element_id:-1968], primaryKey: false);
      insert('organizations', [ id: 101954, nci_institute_code: "CA372", name: "Comprehensive Blood and Cancer Center", city: "Bakersfield", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1969,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA372",GROUP_DESC:"CA372 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1969,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA372",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA372",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1969,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA372", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2969 ,protection_group_id: -1969, protection_element_id:-1969], primaryKey: false);
      insert('organizations', [ id: 101955, nci_institute_code: "CA373", name: "Gyn Onc, Surgery & Endoscopy", city: "Portola Valley", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1970,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA373",GROUP_DESC:"CA373 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1970,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA373",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA373",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1970,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA373", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2970 ,protection_group_id: -1970, protection_element_id:-1970], primaryKey: false);
      insert('organizations', [ id: 101956, nci_institute_code: "CA374", name: "Ventura County Hematology Oncology Specialists", city: "Oxnard", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1971,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA374",GROUP_DESC:"CA374 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1971,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA374",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA374",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1971,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA374", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2971 ,protection_group_id: -1971, protection_element_id:-1971], primaryKey: false);
      insert('organizations', [ id: 101957, nci_institute_code: "CA375", name: "Sharp Chula Visainta", city: "Chula Vista", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1972,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA375",GROUP_DESC:"CA375 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1972,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA375",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA375",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1972,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA375", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2972 ,protection_group_id: -1972, protection_element_id:-1972], primaryKey: false);
      insert('organizations', [ id: 101958, nci_institute_code: "CA376", name: "Sharp Cabrillo", city: "San Diego", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1973,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA376",GROUP_DESC:"CA376 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1973,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA376",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA376",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1973,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA376", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2973 ,protection_group_id: -1973, protection_element_id:-1973], primaryKey: false);
      insert('organizations', [ id: 101959, nci_institute_code: "CA377", name: "Pacific Shores Medical Group", city: "Los Almaitos", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1974,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA377",GROUP_DESC:"CA377 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1974,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA377",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA377",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1974,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA377", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2974 ,protection_group_id: -1974, protection_element_id:-1974], primaryKey: false);
      insert('organizations', [ id: 101960, nci_institute_code: "CA378", name: "Pacific Shores Medical Group", city: "Glendale", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1975,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA378",GROUP_DESC:"CA378 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1975,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA378",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA378",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1975,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA378", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2975 ,protection_group_id: -1975, protection_element_id:-1975], primaryKey: false);
      insert('organizations', [ id: 101961, nci_institute_code: "CA379", name: "Mission Hospital Regional Medical", city: "Mission Viejo", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1976,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA379",GROUP_DESC:"CA379 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1976,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA379",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA379",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1976,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA379", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2976 ,protection_group_id: -1976, protection_element_id:-1976], primaryKey: false);
      insert('organizations', [ id: 101962, nci_institute_code: "CA381", name: "San Clemente Tumor Medical Center", city: "San Clemente", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1977,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA381",GROUP_DESC:"CA381 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1977,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA381",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA381",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1977,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA381", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2977 ,protection_group_id: -1977, protection_element_id:-1977], primaryKey: false);
      insert('organizations', [ id: 101963, nci_institute_code: "CA382", name: "Shc Speciality Hospital", city: "Westlake Village", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1978,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA382",GROUP_DESC:"CA382 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1978,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA382",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA382",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1978,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA382", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2978 ,protection_group_id: -1978, protection_element_id:-1978], primaryKey: false);
      insert('organizations', [ id: 101964, nci_institute_code: "CA383", name: "Redwood Regional Oncology Group", city: "Fairfield", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1979,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA383",GROUP_DESC:"CA383 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1979,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA383",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA383",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1979,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA383", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2979 ,protection_group_id: -1979, protection_element_id:-1979], primaryKey: false);
      insert('organizations', [ id: 101965, nci_institute_code: "CA384", name: "Palchak David MD (Office)", city: "Pismo Beach", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1980,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA384",GROUP_DESC:"CA384 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1980,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA384",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA384",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1980,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA384", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2980 ,protection_group_id: -1980, protection_element_id:-1980], primaryKey: false);
      insert('organizations', [ id: 101966, nci_institute_code: "CA385", name: "University of California San Francisco Medical Center", city: "San Francisco", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1981,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA385",GROUP_DESC:"CA385 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1981,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA385",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA385",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1981,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA385", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2981 ,protection_group_id: -1981, protection_element_id:-1981], primaryKey: false);
      insert('organizations', [ id: 101967, nci_institute_code: "CA386", name: "Veterans Administration Palo Alto, Health and Science Center", city: "Livermore", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1982,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA386",GROUP_DESC:"CA386 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1982,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA386",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA386",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1982,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA386", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2982 ,protection_group_id: -1982, protection_element_id:-1982], primaryKey: false);
      insert('organizations', [ id: 101968, nci_institute_code: "CA387", name: "Doctors Medical Center", city: "Modesto", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1983,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA387",GROUP_DESC:"CA387 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1983,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA387",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA387",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1983,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA387", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2983 ,protection_group_id: -1983, protection_element_id:-1983], primaryKey: false);
      insert('organizations', [ id: 101969, nci_institute_code: "CA388", name: "Kaiser Permanente Medical Center", city: "Riverside", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1984,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA388",GROUP_DESC:"CA388 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1984,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA388",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA388",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1984,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA388", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2984 ,protection_group_id: -1984, protection_element_id:-1984], primaryKey: false);
      insert('organizations', [ id: 101970, nci_institute_code: "CA390", name: "High Desert Medical Oncology Center", city: "Apple Valley", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1985,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA390",GROUP_DESC:"CA390 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1985,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA390",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA390",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1985,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA390", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2985 ,protection_group_id: -1985, protection_element_id:-1985], primaryKey: false);
      insert('organizations', [ id: 101971, nci_institute_code: "CA391", name: "Cancer Detection Center", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1986,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA391",GROUP_DESC:"CA391 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1986,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA391",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA391",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1986,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA391", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2986 ,protection_group_id: -1986, protection_element_id:-1986], primaryKey: false);
      insert('organizations', [ id: 101972, nci_institute_code: "CA392", name: "Los Robles Regional Medical Center", city: "Westlake Village", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1987,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA392",GROUP_DESC:"CA392 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1987,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA392",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA392",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1987,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA392", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2987 ,protection_group_id: -1987, protection_element_id:-1987], primaryKey: false);
    }

    void m39() {
        // all39 (25 terms)
      insert('organizations', [ id: 101973, nci_institute_code: "CA393", name: "Community Hospital of Los Gatos", city: "Los Gatos", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1988,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA393",GROUP_DESC:"CA393 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1988,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA393",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA393",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1988,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA393", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2988 ,protection_group_id: -1988, protection_element_id:-1988], primaryKey: false);
      insert('organizations', [ id: 101974, nci_institute_code: "CA394", name: "Friendly Hills Health Care Center", city: "North Hollywood", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1989,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA394",GROUP_DESC:"CA394 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1989,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA394",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA394",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1989,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA394", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2989 ,protection_group_id: -1989, protection_element_id:-1989], primaryKey: false);
      insert('organizations', [ id: 101975, nci_institute_code: "CA395", name: "West Hills Regional Medical Center", city: "West Hills", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1990,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA395",GROUP_DESC:"CA395 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1990,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA395",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA395",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1990,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA395", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2990 ,protection_group_id: -1990, protection_element_id:-1990], primaryKey: false);
      insert('organizations', [ id: 101976, nci_institute_code: "CA396", name: "Providence Holy Cross Medical Center", city: "Mission Hills", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1991,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA396",GROUP_DESC:"CA396 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1991,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA396",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA396",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1991,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA396", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2991 ,protection_group_id: -1991, protection_element_id:-1991], primaryKey: false);
      insert('organizations', [ id: 101977, nci_institute_code: "CA397", name: "Riverside General Hospital/University Medical Center", city: "Riverside", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1992,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA397",GROUP_DESC:"CA397 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1992,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA397",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA397",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1992,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA397", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2992 ,protection_group_id: -1992, protection_element_id:-1992], primaryKey: false);
      insert('organizations', [ id: 101978, nci_institute_code: "CA398", name: "Lakewood Regional Medical Center", city: "Lakewood", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1993,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA398",GROUP_DESC:"CA398 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1993,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA398",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA398",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1993,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA398", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2993 ,protection_group_id: -1993, protection_element_id:-1993], primaryKey: false);
      insert('organizations', [ id: 101979, nci_institute_code: "CA399", name: "Century City Hospital", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1994,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA399",GROUP_DESC:"CA399 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1994,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA399",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA399",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1994,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA399", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2994 ,protection_group_id: -1994, protection_element_id:-1994], primaryKey: false);
      insert('organizations', [ id: 101980, nci_institute_code: "CA400", name: "Santa Maria Cgop", city: "Santa Maria", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1995,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA400",GROUP_DESC:"CA400 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1995,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA400",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA400",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1995,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA400", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2995 ,protection_group_id: -1995, protection_element_id:-1995], primaryKey: false);
      insert('organizations', [ id: 101981, nci_institute_code: "CA401", name: "Santa Barbara Hematology Oncology Group Inc. - Santa Maria", city: "Santa Maria", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1996,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA401",GROUP_DESC:"CA401 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1996,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA401",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA401",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1996,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA401", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2996 ,protection_group_id: -1996, protection_element_id:-1996], primaryKey: false);
      insert('organizations', [ id: 101982, nci_institute_code: "CA402", name: "Women's Cancer Center of Southern California", city: "Sherman Oaks", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1997,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA402",GROUP_DESC:"CA402 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1997,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA402",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA402",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1997,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA402", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2997 ,protection_group_id: -1997, protection_element_id:-1997], primaryKey: false);
      insert('organizations', [ id: 101983, nci_institute_code: "CA403", name: "Santa Maria Radiation Oncology Center", city: "Santa Maria", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1998,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA403",GROUP_DESC:"CA403 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1998,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA403",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA403",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1998,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA403", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2998 ,protection_group_id: -1998, protection_element_id:-1998], primaryKey: false);
      insert('organizations', [ id: 101984, nci_institute_code: "CA404", name: "The Breast Center", city: "Van Nuys", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1999,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA404",GROUP_DESC:"CA404 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1999,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA404",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA404",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1999,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA404", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2999 ,protection_group_id: -1999, protection_element_id:-1999], primaryKey: false);
      insert('organizations', [ id: 101985, nci_institute_code: "CA405", name: "Sharp North County Oncology", city: "Oceanside", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2000,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA405",GROUP_DESC:"CA405 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2000,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA405",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA405",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2000,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA405", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3000 ,protection_group_id: -2000, protection_element_id:-2000], primaryKey: false);
      insert('organizations', [ id: 101986, nci_institute_code: "CA406", name: "Monterey Park Hospital", city: "Monterey Park", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2001,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA406",GROUP_DESC:"CA406 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2001,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA406",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA406",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2001,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA406", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3001 ,protection_group_id: -2001, protection_element_id:-2001], primaryKey: false);
      insert('organizations', [ id: 101987, nci_institute_code: "CA407", name: "Intercommunity Medical Center", city: "Covina", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2002,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA407",GROUP_DESC:"CA407 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2002,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA407",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA407",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2002,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA407", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3002 ,protection_group_id: -2002, protection_element_id:-2002], primaryKey: false);
      insert('organizations', [ id: 101988, nci_institute_code: "CA408", name: "Hematology and Oncology Consultants", city: "Glendale", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2003,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA408",GROUP_DESC:"CA408 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2003,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA408",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA408",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2003,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA408", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3003 ,protection_group_id: -2003, protection_element_id:-2003], primaryKey: false);
      insert('organizations', [ id: 101989, nci_institute_code: "CA409", name: "Veteran's Administration Medical Center", city: "Las Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2004,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA409",GROUP_DESC:"CA409 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2004,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA409",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA409",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2004,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA409", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3004 ,protection_group_id: -2004, protection_element_id:-2004], primaryKey: false);
      insert('organizations', [ id: 101990, nci_institute_code: "CA410", name: "Beverly Hospital", city: "Montebello", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2005,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA410",GROUP_DESC:"CA410 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2005,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA410",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA410",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2005,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA410", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3005 ,protection_group_id: -2005, protection_element_id:-2005], primaryKey: false);
      insert('organizations', [ id: 101991, nci_institute_code: "CA411", name: "San Jose Medical Group", city: "San Jose", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2006,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA411",GROUP_DESC:"CA411 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2006,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA411",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA411",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2006,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA411", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3006 ,protection_group_id: -2006, protection_element_id:-2006], primaryKey: false);
      insert('organizations', [ id: 101992, nci_institute_code: "CA413", name: "Glendale Memorial Hospital and Health Center", city: "Glendale", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2007,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA413",GROUP_DESC:"CA413 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2007,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA413",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA413",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2007,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA413", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3007 ,protection_group_id: -2007, protection_element_id:-2007], primaryKey: false);
      insert('organizations', [ id: 101993, nci_institute_code: "CA414", name: "Sutter Davis Hospital", city: "Davis", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2008,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA414",GROUP_DESC:"CA414 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2008,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA414",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA414",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2008,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA414", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3008 ,protection_group_id: -2008, protection_element_id:-2008], primaryKey: false);
      insert('organizations', [ id: 101994, nci_institute_code: "CA415", name: "Redwood Regional Medical Group - Lakeport", city: "Lakeport", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2009,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA415",GROUP_DESC:"CA415 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2009,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA415",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA415",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2009,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA415", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3009 ,protection_group_id: -2009, protection_element_id:-2009], primaryKey: false);
      insert('organizations', [ id: 101995, nci_institute_code: "CA416", name: "Valley Cancer Medical Group", city: "Modesto", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2010,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA416",GROUP_DESC:"CA416 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2010,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA416",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA416",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2010,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA416", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3010 ,protection_group_id: -2010, protection_element_id:-2010], primaryKey: false);
      insert('organizations', [ id: 101996, nci_institute_code: "CA418", name: "UCLA Medical Center", city: "Lancaster", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2011,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA418",GROUP_DESC:"CA418 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2011,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA418",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA418",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2011,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA418", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3011 ,protection_group_id: -2011, protection_element_id:-2011], primaryKey: false);
      insert('organizations', [ id: 101997, nci_institute_code: "CA419", name: "Redwood Regional Medical Group - Sebastopol", city: "Sebastopol", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -2012,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA419",GROUP_DESC:"CA419 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -2012,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA419",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA419",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -2012,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA419", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:3012 ,protection_group_id: -2012, protection_element_id:-2012], primaryKey: false);
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
