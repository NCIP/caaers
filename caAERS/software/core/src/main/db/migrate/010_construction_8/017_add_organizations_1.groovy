class OrganizationCodes extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
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
    }

    void m0() {
        // all0 (25 terms)
      insert('organizations', [ id: 100000, nci_institute_code: "00000", name: "Unknown", city: ".", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -15,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.00000",GROUP_DESC:"00000 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -15,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.00000",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.00000",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -15,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.00000", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1015 ,protection_group_id: -15, protection_element_id:-15], primaryKey: false);
      insert('organizations', [ id: 100001, nci_institute_code: "02001", name: "Catedra De Oncologia Univ-Salvador", city: "Buenos Aires", country: "Argentina"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -16,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02001",GROUP_DESC:"02001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -16,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.02001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.02001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -16,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1016 ,protection_group_id: -16, protection_element_id:-16], primaryKey: false);
      insert('organizations', [ id: 100002, nci_institute_code: "02002", name: "Hospital Militar Central", city: "Buenos Aires", country: "Argentina"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -17,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02002",GROUP_DESC:"02002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -17,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.02002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.02002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -17,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1017 ,protection_group_id: -17, protection_element_id:-17], primaryKey: false);
      insert('organizations', [ id: 100003, nci_institute_code: "02003", name: "Hospitales Privado Guemes", city: "Buenos Aires", country: "Argentina"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -18,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02003",GROUP_DESC:"02003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -18,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.02003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.02003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -18,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1018 ,protection_group_id: -18, protection_element_id:-18], primaryKey: false);
      insert('organizations', [ id: 100004, nci_institute_code: "02004", name: "Hosp. Rawson", city: "Buenos Aires", country: "Argentina"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -19,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02004",GROUP_DESC:"02004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -19,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.02004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.02004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -19,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1019 ,protection_group_id: -19, protection_element_id:-19], primaryKey: false);
      insert('organizations', [ id: 100005, nci_institute_code: "02005", name: "Hosp. Teodoro Alvarez", city: "Buenos Aires", country: "Argentina"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -20,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02005",GROUP_DESC:"02005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -20,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.02005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.02005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -20,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1020 ,protection_group_id: -20, protection_element_id:-20], primaryKey: false);
      insert('organizations', [ id: 100006, nci_institute_code: "02006", name: "Hosp. Ramos Mejia, Sala 18", city: "Buenos Aires", country: "Argentina"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -21,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02006",GROUP_DESC:"02006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -21,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.02006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.02006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -21,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1021 ,protection_group_id: -21, protection_element_id:-21], primaryKey: false);
      insert('organizations', [ id: 100007, nci_institute_code: "02007", name: "Deutsches Hosp.", city: "Buenos Aires", country: "Argentina"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -22,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02007",GROUP_DESC:"02007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -22,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.02007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.02007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -22,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1022 ,protection_group_id: -22, protection_element_id:-22], primaryKey: false);
      insert('organizations', [ id: 100008, nci_institute_code: "02008", name: "Universidad Nacional Del Sur", city: "Bahia Blanca", country: "Argentina"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -23,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02008",GROUP_DESC:"02008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -23,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.02008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.02008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -23,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1023 ,protection_group_id: -23, protection_element_id:-23], primaryKey: false);
      insert('organizations', [ id: 100009, nci_institute_code: "02009", name: "Hosp. Frances", city: "Buenos Aires", country: "Argentina"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -24,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02009",GROUP_DESC:"02009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -24,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.02009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.02009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -24,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1024 ,protection_group_id: -24, protection_element_id:-24], primaryKey: false);
      insert('organizations', [ id: 100010, nci_institute_code: "02010", name: "Hosp. Policial", city: "Buenos Aires", country: "Argentina"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -25,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02010",GROUP_DESC:"02010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -25,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.02010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.02010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -25,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1025 ,protection_group_id: -25, protection_element_id:-25], primaryKey: false);
      insert('organizations', [ id: 100011, nci_institute_code: "02011", name: "Hosp. Israelita", city: "Buenos Aires", country: "Argentina"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -26,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02011",GROUP_DESC:"02011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -26,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.02011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.02011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -26,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1026 ,protection_group_id: -26, protection_element_id:-26], primaryKey: false);
      insert('organizations', [ id: 100012, nci_institute_code: "02012", name: "Hosp. Municipal F. Santojanni", city: "Buenos Aires", country: "Argentina"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -27,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02012",GROUP_DESC:"02012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -27,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.02012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.02012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -27,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1027 ,protection_group_id: -27, protection_element_id:-27], primaryKey: false);
      insert('organizations', [ id: 100013, nci_institute_code: "02013", name: "Instituto De Oncologia", city: "Buenos Aires", country: "Argentina"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -28,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02013",GROUP_DESC:"02013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -28,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.02013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.02013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -28,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1028 ,protection_group_id: -28, protection_element_id:-28], primaryKey: false);
      insert('organizations', [ id: 100014, nci_institute_code: "02014", name: "Hosp. Interzonal Gen. San Martin", city: "Buenos Aires", country: "Argentina"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -29,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02014",GROUP_DESC:"02014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -29,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.02014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.02014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -29,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1029 ,protection_group_id: -29, protection_element_id:-29], primaryKey: false);
      insert('organizations', [ id: 100015, nci_institute_code: "02015", name: "Hospital Italiano of Buenos Aires", city: "Buenos Aires", country: "Argentina"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -30,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02015",GROUP_DESC:"02015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -30,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.02015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.02015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -30,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1030 ,protection_group_id: -30, protection_element_id:-30], primaryKey: false);
      insert('organizations', [ id: 100016, nci_institute_code: "02016", name: "San Martin De Tours 2980", city: "Buenos Aires", country: "Argentina"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -31,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02016",GROUP_DESC:"02016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -31,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.02016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.02016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -31,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1031 ,protection_group_id: -31, protection_element_id:-31], primaryKey: false);
      insert('organizations', [ id: 100017, nci_institute_code: "02017", name: "Hematologia", city: "Rosario", country: "Argentina"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -32,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02017",GROUP_DESC:"02017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -32,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.02017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.02017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -32,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1032 ,protection_group_id: -32, protection_element_id:-32], primaryKey: false);
      insert('organizations', [ id: 100018, nci_institute_code: "02018", name: "Academia Nacional De Medicina", city: "Buenos Aires", country: "Argentina"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -33,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02018",GROUP_DESC:"02018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -33,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.02018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.02018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -33,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1033 ,protection_group_id: -33, protection_element_id:-33], primaryKey: false);
      insert('organizations', [ id: 100019, nci_institute_code: "02019", name: "Lab/Reproduction and Lactation", city: "Mendoza", country: "Argentina"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -34,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02019",GROUP_DESC:"02019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -34,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.02019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.02019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -34,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1034 ,protection_group_id: -34, protection_element_id:-34], primaryKey: false);
      insert('organizations', [ id: 100020, nci_institute_code: "02020", name: "Alexander Fleming Cancer Center", city: "Buenos Aires", country: "Argentina"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -35,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02020",GROUP_DESC:"02020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -35,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.02020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.02020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -35,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1035 ,protection_group_id: -35, protection_element_id:-35], primaryKey: false);
      insert('organizations', [ id: 100021, nci_institute_code: "02021", name: "Hospital Universitario Austral", city: "Derqui, Buenos Aires", country: "Argentina"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -36,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02021",GROUP_DESC:"02021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -36,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.02021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.02021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -36,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1036 ,protection_group_id: -36, protection_element_id:-36], primaryKey: false);
      insert('organizations', [ id: 100022, nci_institute_code: "02022", name: "CERIM", city: "Buenos Aires", country: "Argentina"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -37,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02022",GROUP_DESC:"02022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -37,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.02022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.02022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -37,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1037 ,protection_group_id: -37, protection_element_id:-37], primaryKey: false);
      insert('organizations', [ id: 100023, nci_institute_code: "03001", name: "WP Holman Clinic - Launceston General Hospital", city: "Launceston", state: "TAS", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -38,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03001",GROUP_DESC:"03001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -38,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -38,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1038 ,protection_group_id: -38, protection_element_id:-38], primaryKey: false);
      insert('organizations', [ id: 100024, nci_institute_code: "03002", name: "Concord Repatriation General Hospital", city: "Concord", state: "NSW", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -39,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03002",GROUP_DESC:"03002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -39,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -39,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1039 ,protection_group_id: -39, protection_element_id:-39], primaryKey: false);
    }

    void m1() {
        // all1 (25 terms)
      insert('organizations', [ id: 100025, nci_institute_code: "03003", name: "Peter MacCallum Cancer Centre", city: "East Melbourne", state: "VIC", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -40,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03003",GROUP_DESC:"03003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -40,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -40,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1040 ,protection_group_id: -40, protection_element_id:-40], primaryKey: false);
      insert('organizations', [ id: 100026, nci_institute_code: "03004", name: "Flinders Medical Center", city: "Bedford Park, SA", state: "5042", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -41,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03004",GROUP_DESC:"03004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -41,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -41,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1041 ,protection_group_id: -41, protection_element_id:-41], primaryKey: false);
      insert('organizations', [ id: 100027, nci_institute_code: "03005", name: "Royal Prince Alfred Hospital", city: "Camperdown", state: "NSW", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -42,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03005",GROUP_DESC:"03005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -42,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -42,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1042 ,protection_group_id: -42, protection_element_id:-42], primaryKey: false);
      insert('organizations', [ id: 100028, nci_institute_code: "03006", name: "University of Melbourne", city: "Heidleberg, Victoria", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -43,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03006",GROUP_DESC:"03006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -43,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -43,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1043 ,protection_group_id: -43, protection_element_id:-43], primaryKey: false);
      insert('organizations', [ id: 100029, nci_institute_code: "03007", name: "Mount Hospital", city: "Perth, Wa, 6000", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -44,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03007",GROUP_DESC:"03007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -44,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -44,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1044 ,protection_group_id: -44, protection_element_id:-44], primaryKey: false);
      insert('organizations', [ id: 100030, nci_institute_code: "03008", name: "Sacred Heart Hospice", city: "Darlinghurst", state: "NSW", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -45,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03008",GROUP_DESC:"03008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -45,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -45,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1045 ,protection_group_id: -45, protection_element_id:-45], primaryKey: false);
      insert('organizations', [ id: 100031, nci_institute_code: "03009", name: "Woden Valley Hospital", city: "Woden, A.C.T. 2606", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -46,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03009",GROUP_DESC:"03009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -46,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -46,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1046 ,protection_group_id: -46, protection_element_id:-46], primaryKey: false);
      insert('organizations', [ id: 100032, nci_institute_code: "03010", name: "Sir Charles Gairdner Hospital", city: "Nedlands", state: "Western Australia", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -47,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03010",GROUP_DESC:"03010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -47,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -47,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1047 ,protection_group_id: -47, protection_element_id:-47], primaryKey: false);
      insert('organizations', [ id: 100033, nci_institute_code: "03011", name: "Princess Alexandra Hospital", city: "Brisbane", state: "QLD", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -48,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03011",GROUP_DESC:"03011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -48,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -48,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1048 ,protection_group_id: -48, protection_element_id:-48], primaryKey: false);
      insert('organizations', [ id: 100034, nci_institute_code: "03012", name: "Woden Vallery Hospital", city: "Garran", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -49,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03012",GROUP_DESC:"03012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -49,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -49,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1049 ,protection_group_id: -49, protection_element_id:-49], primaryKey: false);
      insert('organizations', [ id: 100035, nci_institute_code: "03013", name: "Royal Children's Hospital", city: "Parkville", state: "VIC", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -50,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03013",GROUP_DESC:"03013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -50,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -50,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1050 ,protection_group_id: -50, protection_element_id:-50], primaryKey: false);
      insert('organizations', [ id: 100036, nci_institute_code: "03014", name: "Royal Perth Hospital", city: "Perth, Wa, 6001", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -51,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03014",GROUP_DESC:"03014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -51,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -51,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1051 ,protection_group_id: -51, protection_element_id:-51], primaryKey: false);
      insert('organizations', [ id: 100037, nci_institute_code: "03015", name: "Alfred Hospital Melbourne", city: "Melbourne", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -52,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03015",GROUP_DESC:"03015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -52,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -52,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1052 ,protection_group_id: -52, protection_element_id:-52], primaryKey: false);
      insert('organizations', [ id: 100038, nci_institute_code: "03016", name: "Saint. Vincent Hospital, Sydney", city: "Sydney", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -53,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03016",GROUP_DESC:"03016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -53,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -53,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1053 ,protection_group_id: -53, protection_element_id:-53], primaryKey: false);
      insert('organizations', [ id: 100039, nci_institute_code: "03017", name: "Royal North Shore Hospital", city: "St Leonards", state: "NSW", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -54,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03017",GROUP_DESC:"03017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -54,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -54,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1054 ,protection_group_id: -54, protection_element_id:-54], primaryKey: false);
      insert('organizations', [ id: 100040, nci_institute_code: "03018", name: "Royal Alexandra Hospital/Children", city: "Camperdown, Nsw", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -55,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03018",GROUP_DESC:"03018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -55,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -55,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1055 ,protection_group_id: -55, protection_element_id:-55], primaryKey: false);
      insert('organizations', [ id: 100041, nci_institute_code: "03019", name: "University of Tasmania", city: "Hobart, Tasmania", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -56,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03019",GROUP_DESC:"03019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -56,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -56,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1056 ,protection_group_id: -56, protection_element_id:-56], primaryKey: false);
      insert('organizations', [ id: 100042, nci_institute_code: "03020", name: "Adelaide Childrens Hospital", city: "North Adelaide", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -57,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03020",GROUP_DESC:"03020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -57,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -57,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1057 ,protection_group_id: -57, protection_element_id:-57], primaryKey: false);
      insert('organizations', [ id: 100043, nci_institute_code: "03021", name: "University of Sydney", city: "Sydney", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -58,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03021",GROUP_DESC:"03021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -58,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -58,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1058 ,protection_group_id: -58, protection_element_id:-58], primaryKey: false);
      insert('organizations', [ id: 100044, nci_institute_code: "03022", name: "Prince of Wales Hospital", city: "Randwick, Nsw", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -59,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03022",GROUP_DESC:"03022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -59,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -59,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1059 ,protection_group_id: -59, protection_element_id:-59], primaryKey: false);
      insert('organizations', [ id: 100045, nci_institute_code: "03023", name: "Royal Newcastle Hospital", city: "New Castle", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -60,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03023",GROUP_DESC:"03023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -60,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -60,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1060 ,protection_group_id: -60, protection_element_id:-60], primaryKey: false);
      insert('organizations', [ id: 100046, nci_institute_code: "03024", name: "University of New South Wales", city: "Kogarah", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -61,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03024",GROUP_DESC:"03024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -61,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -61,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1061 ,protection_group_id: -61, protection_element_id:-61], primaryKey: false);
      insert('organizations', [ id: 100047, nci_institute_code: "03025", name: "Sydney Hospital", city: "Sydney", state: "NSW", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -62,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03025",GROUP_DESC:"03025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -62,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -62,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1062 ,protection_group_id: -62, protection_element_id:-62], primaryKey: false);
      insert('organizations', [ id: 100048, nci_institute_code: "03026", name: "Royal Adelaide Hospital", city: "Adelaide, S.A., 5000", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -63,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03026",GROUP_DESC:"03026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -63,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -63,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1063 ,protection_group_id: -63, protection_element_id:-63], primaryKey: false);
      insert('organizations', [ id: 100049, nci_institute_code: "03027", name: "Royal North Shore Hospital of Sydney", city: "Sydney  2065", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -64,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03027",GROUP_DESC:"03027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -64,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -64,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1064 ,protection_group_id: -64, protection_element_id:-64], primaryKey: false);
    }

    void m2() {
        // all2 (25 terms)
      insert('organizations', [ id: 100050, nci_institute_code: "03028", name: "Royal Childrens Hospital", city: "Parkville", state: "Victoria", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -65,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03028",GROUP_DESC:"03028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -65,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -65,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1065 ,protection_group_id: -65, protection_element_id:-65], primaryKey: false);
      insert('organizations', [ id: 100051, nci_institute_code: "03029", name: "Sydney West Area Health Service-Westmead Hospital", city: "Westmead", state: "NSW", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -66,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03029",GROUP_DESC:"03029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -66,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -66,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1066 ,protection_group_id: -66, protection_element_id:-66], primaryKey: false);
      insert('organizations', [ id: 100052, nci_institute_code: "03030", name: "Prince Henrys Hospital", city: "Clayton", state: "VIC", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -67,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03030",GROUP_DESC:"03030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -67,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -67,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1067 ,protection_group_id: -67, protection_element_id:-67], primaryKey: false);
      insert('organizations', [ id: 100053, nci_institute_code: "03031", name: "Saint Vincent's Hospital", city: "Melbourne, Victoria", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -68,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03031",GROUP_DESC:"03031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -68,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -68,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1068 ,protection_group_id: -68, protection_element_id:-68], primaryKey: false);
      insert('organizations', [ id: 100054, nci_institute_code: "03032", name: "Queen Elizabeth II Medical Center", state: "WA", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -69,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03032",GROUP_DESC:"03032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -69,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -69,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1069 ,protection_group_id: -69, protection_element_id:-69], primaryKey: false);
      insert('organizations', [ id: 100055, nci_institute_code: "03033", name: "Royal Hobart Hospital", city: "St. Hobart", state: "TAS", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -70,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03033",GROUP_DESC:"03033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -70,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -70,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1070 ,protection_group_id: -70, protection_element_id:-70], primaryKey: false);
      insert('organizations', [ id: 100056, nci_institute_code: "03035", name: "Royal Melbourne Hospital", city: "Parkville", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -71,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03035",GROUP_DESC:"03035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -71,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -71,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1071 ,protection_group_id: -71, protection_element_id:-71], primaryKey: false);
      insert('organizations', [ id: 100057, nci_institute_code: "03036", name: "Saint George Hospital", city: "Kogarah, Nsw", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -72,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03036",GROUP_DESC:"03036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -72,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -72,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1072 ,protection_group_id: -72, protection_element_id:-72], primaryKey: false);
      insert('organizations', [ id: 100058, nci_institute_code: "03037", name: "The Alfred Hospital", city: "Prahran, Victoria", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -73,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03037",GROUP_DESC:"03037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -73,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -73,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1073 ,protection_group_id: -73, protection_element_id:-73], primaryKey: false);
      insert('organizations', [ id: 100059, nci_institute_code: "03038", name: "The Prince Charles Hospital", city: "Brisbane", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -74,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03038",GROUP_DESC:"03038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -74,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -74,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1074 ,protection_group_id: -74, protection_element_id:-74], primaryKey: false);
      insert('organizations', [ id: 100060, nci_institute_code: "03039", name: "Department of Health", city: "Canberra, A.C.T.", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -75,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03039",GROUP_DESC:"03039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -75,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -75,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1075 ,protection_group_id: -75, protection_element_id:-75], primaryKey: false);
      insert('organizations', [ id: 100061, nci_institute_code: "03040", name: "Saint Vincent's Hospital", city: "Darlinghurst, N.S.W.", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -76,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03040",GROUP_DESC:"03040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -76,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -76,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1076 ,protection_group_id: -76, protection_element_id:-76], primaryKey: false);
      insert('organizations', [ id: 100062, nci_institute_code: "03041", name: "Ludwig Institute for Cancer Research", city: "Parkville", state: "VIC", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -77,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03041",GROUP_DESC:"03041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -77,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -77,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1077 ,protection_group_id: -77, protection_element_id:-77], primaryKey: false);
      insert('organizations', [ id: 100063, nci_institute_code: "03044", name: "Austin Health", city: "Heidelberg", state: "VIC", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -78,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03044",GROUP_DESC:"03044 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -78,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03044",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03044",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -78,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03044", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1078 ,protection_group_id: -78, protection_element_id:-78], primaryKey: false);
      insert('organizations', [ id: 100064, nci_institute_code: "03045", name: "Royal Melbourne Hospital", city: "Parkville", state: "VIC", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -79,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03045",GROUP_DESC:"03045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -79,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -79,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1079 ,protection_group_id: -79, protection_element_id:-79], primaryKey: false);
      insert('organizations', [ id: 100065, nci_institute_code: "03046", name: "Institute of Medical and Vet Science", city: "Adelaide", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -80,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03046",GROUP_DESC:"03046 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -80,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03046",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03046",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -80,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03046", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1080 ,protection_group_id: -80, protection_element_id:-80], primaryKey: false);
      insert('organizations', [ id: 100066, nci_institute_code: "03048", name: "Calvary Mater Newcastle Hospital", city: "Waratah", state: "NSW", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -81,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03048",GROUP_DESC:"03048 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -81,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03048",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03048",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -81,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03048", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1081 ,protection_group_id: -81, protection_element_id:-81], primaryKey: false);
      insert('organizations', [ id: 100067, nci_institute_code: "03049", name: "Austin and Repatriation Medical Center", city: "Heidelberg West 3081", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -82,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03049",GROUP_DESC:"03049 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -82,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03049",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03049",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -82,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03049", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1082 ,protection_group_id: -82, protection_element_id:-82], primaryKey: false);
      insert('organizations', [ id: 100068, nci_institute_code: "03050", name: "The Geelong Hospital", city: "Geelong, Victoria", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -83,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03050",GROUP_DESC:"03050 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -83,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03050",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03050",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -83,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03050", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1083 ,protection_group_id: -83, protection_element_id:-83], primaryKey: false);
      insert('organizations', [ id: 100069, nci_institute_code: "03051", name: "Royal Brisbane Hospital", city: "Brisbane", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -84,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03051",GROUP_DESC:"03051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -84,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -84,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1084 ,protection_group_id: -84, protection_element_id:-84], primaryKey: false);
      insert('organizations', [ id: 100070, nci_institute_code: "03052", name: "Mercy Hospital for Women", city: "Heidelberg", state: "VIC", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -85,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03052",GROUP_DESC:"03052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -85,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -85,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1085 ,protection_group_id: -85, protection_element_id:-85], primaryKey: false);
      insert('organizations', [ id: 100071, nci_institute_code: "03053", name: "Queen Elizabeth Hospital", city: "Woodville, Sa., 5011", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -86,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03053",GROUP_DESC:"03053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -86,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -86,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1086 ,protection_group_id: -86, protection_element_id:-86], primaryKey: false);
      insert('organizations', [ id: 100072, nci_institute_code: "03055", name: "Princess Margaret Hospital for Children", city: "West Leederville", state: "WA", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -87,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03055",GROUP_DESC:"03055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -87,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -87,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1087 ,protection_group_id: -87, protection_element_id:-87], primaryKey: false);
      insert('organizations', [ id: 100073, nci_institute_code: "03056", name: "Wesley Clinic for Haematology Oncology", city: "Auchenflower", state: "QLD", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -88,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03056",GROUP_DESC:"03056 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -88,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03056",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03056",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -88,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03056", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1088 ,protection_group_id: -88, protection_element_id:-88], primaryKey: false);
      insert('organizations', [ id: 100074, nci_institute_code: "03057", name: "The Canberra Hospital", city: "Woden Act", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -89,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03057",GROUP_DESC:"03057 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -89,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03057",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03057",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -89,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03057", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1089 ,protection_group_id: -89, protection_element_id:-89], primaryKey: false);
    }

    void m3() {
        // all3 (25 terms)
      insert('organizations', [ id: 100075, nci_institute_code: "03058", name: "Liverpool Cancer Therapy Center-Liverpool Hospital", city: "Liverpool, Nsw", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -90,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03058",GROUP_DESC:"03058 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -90,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03058",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03058",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -90,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03058", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1090 ,protection_group_id: -90, protection_element_id:-90], primaryKey: false);
      insert('organizations', [ id: 100076, nci_institute_code: "03059", name: "Repatriation General Hosp.", city: "Daw Park", state: "S AUSTRALIA", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -91,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03059",GROUP_DESC:"03059 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -91,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03059",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03059",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -91,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03059", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1091 ,protection_group_id: -91, protection_element_id:-91], primaryKey: false);
      insert('organizations', [ id: 100077, nci_institute_code: "03060", name: "Latrobe Regional Hospital", city: "Traralgon, Victoria", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -92,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03060",GROUP_DESC:"03060 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -92,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03060",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03060",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -92,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03060", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1092 ,protection_group_id: -92, protection_element_id:-92], primaryKey: false);
      insert('organizations', [ id: 100078, nci_institute_code: "03061", name: "Princess Alexandra Hospital", city: "Woolloongabba", state: "QLD", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -93,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03061",GROUP_DESC:"03061 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -93,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03061",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03061",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -93,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03061", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1093 ,protection_group_id: -93, protection_element_id:-93], primaryKey: false);
      insert('organizations', [ id: 100079, nci_institute_code: "03062", name: "Western Hospital", city: "Footscray Victoria", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -94,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03062",GROUP_DESC:"03062 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -94,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03062",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03062",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -94,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03062", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1094 ,protection_group_id: -94, protection_element_id:-94], primaryKey: false);
      insert('organizations', [ id: 100080, nci_institute_code: "03064", name: "Mater Private Hospital - Brisbane", city: "South Brisbane", state: "QLD", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -95,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03064",GROUP_DESC:"03064 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -95,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03064",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03064",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -95,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03064", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1095 ,protection_group_id: -95, protection_element_id:-95], primaryKey: false);
      insert('organizations', [ id: 100081, nci_institute_code: "03065", name: "Institute for Child Health Research", city: "West Perta", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -96,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03065",GROUP_DESC:"03065 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -96,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03065",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03065",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -96,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03065", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1096 ,protection_group_id: -96, protection_element_id:-96], primaryKey: false);
      insert('organizations', [ id: 100082, nci_institute_code: "03066", name: "Bendigo Hospital", city: "Bendigo Vic", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -97,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03066",GROUP_DESC:"03066 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -97,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03066",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03066",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -97,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03066", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1097 ,protection_group_id: -97, protection_element_id:-97], primaryKey: false);
      insert('organizations', [ id: 100083, nci_institute_code: "03067", name: "Royal Brisbane and Women's Hospital", city: "Herston", state: "QLD", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -98,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03067",GROUP_DESC:"03067 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -98,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03067",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03067",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -98,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03067", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1098 ,protection_group_id: -98, protection_element_id:-98], primaryKey: false);
      insert('organizations', [ id: 100084, nci_institute_code: "03068", name: "Townsville General Hospital", city: "Townsville, Qld", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -99,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03068",GROUP_DESC:"03068 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -99,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03068",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03068",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -99,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03068", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1099 ,protection_group_id: -99, protection_element_id:-99], primaryKey: false);
      insert('organizations', [ id: 100085, nci_institute_code: "03069", name: "Cancer Council of Western Australia", city: "Perth", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -100,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03069",GROUP_DESC:"03069 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -100,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03069",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03069",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -100,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03069", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1100 ,protection_group_id: -100, protection_element_id:-100], primaryKey: false);
      insert('organizations', [ id: 100086, nci_institute_code: "03070", name: "Gosford Hospital", city: "Gosford, Nsw", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -101,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03070",GROUP_DESC:"03070 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -101,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03070",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03070",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -101,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03070", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1101 ,protection_group_id: -101, protection_element_id:-101], primaryKey: false);
      insert('organizations', [ id: 100087, nci_institute_code: "03071", name: "Sydney Breast Cancer Institute", city: "Sydney", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -102,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03071",GROUP_DESC:"03071 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -102,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03071",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03071",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -102,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03071", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1102 ,protection_group_id: -102, protection_element_id:-102], primaryKey: false);
      insert('organizations', [ id: 100088, nci_institute_code: "03072", name: "Orange Base Hospital", city: "Orange, Nsw", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -103,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03072",GROUP_DESC:"03072 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -103,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03072",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03072",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -103,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03072", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1103 ,protection_group_id: -103, protection_element_id:-103], primaryKey: false);
      insert('organizations', [ id: 100089, nci_institute_code: "03073", name: "Fremantle Hospital", city: "Fremantle Wa", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -104,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03073",GROUP_DESC:"03073 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -104,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03073",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03073",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -104,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03073", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1104 ,protection_group_id: -104, protection_element_id:-104], primaryKey: false);
      insert('organizations', [ id: 100090, nci_institute_code: "03074", name: "Monash Medical Center, Clayton Campus", city: "Clayton, Vic", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -105,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03074",GROUP_DESC:"03074 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -105,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03074",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03074",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -105,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03074", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1105 ,protection_group_id: -105, protection_element_id:-105], primaryKey: false);
      insert('organizations', [ id: 100091, nci_institute_code: "03075", name: "Lidcombe-Bankstown Hospital", city: "Bankstown, Nsw", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -106,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03075",GROUP_DESC:"03075 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -106,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03075",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03075",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -106,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03075", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1106 ,protection_group_id: -106, protection_element_id:-106], primaryKey: false);
      insert('organizations', [ id: 100092, nci_institute_code: "03076", name: "Australasian Gastro-Intestinal Trials Group Coordinating Center", city: "Camperdown", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -107,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03076",GROUP_DESC:"03076 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -107,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03076",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03076",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -107,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03076", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1107 ,protection_group_id: -107, protection_element_id:-107], primaryKey: false);
      insert('organizations', [ id: 100093, nci_institute_code: "03077", name: "The Canberra Hospital", city: "Garran", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -108,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03077",GROUP_DESC:"03077 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -108,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03077",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03077",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -108,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03077", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1108 ,protection_group_id: -108, protection_element_id:-108], primaryKey: false);
      insert('organizations', [ id: 100094, nci_institute_code: "03078", name: "Box Hill Hospital", city: "Box Hill", state: "VIC", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -109,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03078",GROUP_DESC:"03078 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -109,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03078",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03078",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -109,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03078", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1109 ,protection_group_id: -109, protection_element_id:-109], primaryKey: false);
      insert('organizations', [ id: 100095, nci_institute_code: "03079", name: "Frankston Hospital", city: "Frankston, Vic", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -110,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03079",GROUP_DESC:"03079 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -110,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03079",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03079",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -110,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03079", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1110 ,protection_group_id: -110, protection_element_id:-110], primaryKey: false);
      insert('organizations', [ id: 100096, nci_institute_code: "03080", name: "Saint Andrew's Medical Centre", city: "Adealide", state: "South Ausralia", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -111,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03080",GROUP_DESC:"03080 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -111,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03080",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03080",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -111,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03080", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1111 ,protection_group_id: -111, protection_element_id:-111], primaryKey: false);
      insert('organizations', [ id: 100097, nci_institute_code: "03081", name: "John Hunter Children's Hospital", city: "Nsw", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -112,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03081",GROUP_DESC:"03081 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -112,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03081",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03081",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -112,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03081", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1112 ,protection_group_id: -112, protection_element_id:-112], primaryKey: false);
      insert('organizations', [ id: 100098, nci_institute_code: "03082", name: "Lingard Private Hospital", city: "Merryweather", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -113,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03082",GROUP_DESC:"03082 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -113,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03082",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03082",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -113,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03082", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1113 ,protection_group_id: -113, protection_element_id:-113], primaryKey: false);
      insert('organizations', [ id: 100099, nci_institute_code: "03083", name: "Port Macquarie Base Hospital", city: "Port Macquarie,Nsw", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -114,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03083",GROUP_DESC:"03083 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -114,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03083",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03083",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -114,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03083", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1114 ,protection_group_id: -114, protection_element_id:-114], primaryKey: false);
    }

    void m4() {
        // all4 (25 terms)
      insert('organizations', [ id: 100100, nci_institute_code: "03084", name: "Murray Valley Private Hospital", city: "Wadonga", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -115,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03084",GROUP_DESC:"03084 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -115,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03084",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03084",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -115,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03084", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1115 ,protection_group_id: -115, protection_element_id:-115], primaryKey: false);
      insert('organizations', [ id: 100101, nci_institute_code: "03085", name: "Saint Andrew's Hospital", city: "Adelaide", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -116,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03085",GROUP_DESC:"03085 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -116,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03085",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03085",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -116,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03085", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1116 ,protection_group_id: -116, protection_element_id:-116], primaryKey: false);
      insert('organizations', [ id: 100102, nci_institute_code: "03086", name: "Ashford Hospital", city: "Ashford", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -117,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03086",GROUP_DESC:"03086 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -117,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03086",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03086",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -117,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03086", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1117 ,protection_group_id: -117, protection_element_id:-117], primaryKey: false);
      insert('organizations', [ id: 100103, nci_institute_code: "03087", name: "St. John of God Hospital-Murdoch", city: "Murdoch", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -118,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03087",GROUP_DESC:"03087 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -118,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03087",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03087",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -118,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03087", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1118 ,protection_group_id: -118, protection_element_id:-118], primaryKey: false);
      insert('organizations', [ id: 100104, nci_institute_code: "03088", name: "John James Medical Centre", city: "Deakin", state: "ACT", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -119,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03088",GROUP_DESC:"03088 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -119,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03088",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03088",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -119,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03088", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1119 ,protection_group_id: -119, protection_element_id:-119], primaryKey: false);
      insert('organizations', [ id: 100105, nci_institute_code: "03089", name: "Nepean Hospital", city: "Penrith", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -120,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03089",GROUP_DESC:"03089 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -120,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03089",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03089",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -120,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03089", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1120 ,protection_group_id: -120, protection_element_id:-120], primaryKey: false);
      insert('organizations', [ id: 100106, nci_institute_code: "03092", name: "Wesley Medical Centre", city: "Auchenflower", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -121,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03092",GROUP_DESC:"03092 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -121,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03092",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03092",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -121,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03092", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1121 ,protection_group_id: -121, protection_element_id:-121], primaryKey: false);
      insert('organizations', [ id: 100107, nci_institute_code: "03094", name: "Barkers Pharmacy", city: "Albury", state: "NSW", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -122,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03094",GROUP_DESC:"03094 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -122,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03094",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03094",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -122,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03094", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1122 ,protection_group_id: -122, protection_element_id:-122], primaryKey: false);
      insert('organizations', [ id: 100108, nci_institute_code: "03095", name: "St. Andrew's Toowoomba Hospital", city: "Toowoomba, Qld", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -123,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03095",GROUP_DESC:"03095 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -123,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03095",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03095",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -123,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03095", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1123 ,protection_group_id: -123, protection_element_id:-123], primaryKey: false);
      insert('organizations', [ id: 100109, nci_institute_code: "03096", name: "Women's and Children's Hospital, Adelaide", city: "North Adelaide", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -124,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03096",GROUP_DESC:"03096 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -124,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03096",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03096",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -124,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03096", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1124 ,protection_group_id: -124, protection_element_id:-124], primaryKey: false);
      insert('organizations', [ id: 100110, nci_institute_code: "03097", name: "Cabrini Hospital", city: "Malvern, Victoria", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -125,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03097",GROUP_DESC:"03097 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -125,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03097",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03097",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -125,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03097", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1125 ,protection_group_id: -125, protection_element_id:-125], primaryKey: false);
      insert('organizations', [ id: 100111, nci_institute_code: "03098", name: "Royal Women's Hospital", city: "Carlton", state: "Victoria", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -126,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03098",GROUP_DESC:"03098 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -126,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03098",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03098",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -126,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03098", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1126 ,protection_group_id: -126, protection_element_id:-126], primaryKey: false);
      insert('organizations', [ id: 100112, nci_institute_code: "03099", name: "Australia New Zealand Gynaecological Oncology", city: "Camperdown", state: "NSW", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -127,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03099",GROUP_DESC:"03099 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -127,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03099",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03099",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -127,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03099", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1127 ,protection_group_id: -127, protection_element_id:-127], primaryKey: false);
      insert('organizations', [ id: 100113, nci_institute_code: "03100", name: "John James Memorial Hospital", city: "Deakin", state: "ACT", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -128,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03100",GROUP_DESC:"03100 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -128,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03100",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03100",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -128,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03100", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1128 ,protection_group_id: -128, protection_element_id:-128], primaryKey: false);
      insert('organizations', [ id: 100114, nci_institute_code: "03101", name: "Sydney Cancer Centre", city: "Camperdown", state: "Sydney", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -129,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03101",GROUP_DESC:"03101 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -129,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03101",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03101",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -129,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03101", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1129 ,protection_group_id: -129, protection_element_id:-129], primaryKey: false);
      insert('organizations', [ id: 100115, nci_institute_code: "03102", name: "Liverpool Hospital", city: "Liverpool, Bc", state: "Nsw", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -130,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03102",GROUP_DESC:"03102 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -130,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03102",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03102",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -130,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03102", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1130 ,protection_group_id: -130, protection_element_id:-130], primaryKey: false);
      insert('organizations', [ id: 100116, nci_institute_code: "03103", name: "Ashford Cancer Centre", city: "Ashford", state: "SA", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -131,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03103",GROUP_DESC:"03103 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -131,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03103",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03103",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -131,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03103", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1131 ,protection_group_id: -131, protection_element_id:-131], primaryKey: false);
      insert('organizations', [ id: 100117, nci_institute_code: "03104", name: "Mater Hospital - North Sydney", city: "Crows Nest", state: "NSW", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -132,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03104",GROUP_DESC:"03104 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -132,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03104",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03104",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -132,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03104", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1132 ,protection_group_id: -132, protection_element_id:-132], primaryKey: false);
      insert('organizations', [ id: 100118, nci_institute_code: "03105", name: "Newcastle Melanoma unit, Mater Hospital", city: "Newcastle", state: "New South Wales", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -133,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03105",GROUP_DESC:"03105 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -133,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03105",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03105",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -133,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03105", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1133 ,protection_group_id: -133, protection_element_id:-133], primaryKey: false);
      insert('organizations', [ id: 100119, nci_institute_code: "03106", name: "Peter MacCallum Cancer Centre Monash Medical Centre Moorabbin Campus", city: "East Bentleigh", state: "VIC", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -134,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03106",GROUP_DESC:"03106 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -134,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03106",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03106",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -134,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03106", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1134 ,protection_group_id: -134, protection_element_id:-134], primaryKey: false);
      insert('organizations', [ id: 100120, nci_institute_code: "03107", name: "Campbelltown Hospital - Macarthur Cancer Therapy Centre", city: "Campbelltown", state: "NSW", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -135,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03107",GROUP_DESC:"03107 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -135,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03107",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03107",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -135,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03107", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1135 ,protection_group_id: -135, protection_element_id:-135], primaryKey: false);
      insert('organizations', [ id: 100121, nci_institute_code: "03108", name: "The Children's Hospital at Westmead", city: "Westmead", state: "NSW", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -136,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03108",GROUP_DESC:"03108 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -136,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03108",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03108",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -136,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03108", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1136 ,protection_group_id: -136, protection_element_id:-136], primaryKey: false);
      insert('organizations', [ id: 100122, nci_institute_code: "03109", name: "Nepean Cancer Care Center", city: "Sydney", state: "NSW", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -137,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03109",GROUP_DESC:"03109 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -137,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03109",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03109",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -137,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03109", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1137 ,protection_group_id: -137, protection_element_id:-137], primaryKey: false);
      insert('organizations', [ id: 100123, nci_institute_code: "03110", name: "Oncology and Immunology Unit", city: "Newcastle", state: "NSW", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -138,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03110",GROUP_DESC:"03110 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -138,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03110",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03110",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -138,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03110", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1138 ,protection_group_id: -138, protection_element_id:-138], primaryKey: false);
      insert('organizations', [ id: 100124, nci_institute_code: "03111", name: "Giast Clinic", city: "South Brisbane", state: "QLD", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -139,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03111",GROUP_DESC:"03111 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -139,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03111",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03111",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -139,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03111", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1139 ,protection_group_id: -139, protection_element_id:-139], primaryKey: false);
    }

    void m5() {
        // all5 (25 terms)
      insert('organizations', [ id: 100125, nci_institute_code: "03112", name: "Saint Vincent Institute of Medical Research", city: "Fitzroy", state: "Victoria", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -140,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03112",GROUP_DESC:"03112 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -140,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03112",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03112",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -140,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03112", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1140 ,protection_group_id: -140, protection_element_id:-140], primaryKey: false);
      insert('organizations', [ id: 100126, nci_institute_code: "03113", name: "Royal Hospital for Women", city: "Randwick", state: "NSW", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -141,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03113",GROUP_DESC:"03113 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -141,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03113",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03113",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -141,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03113", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1141 ,protection_group_id: -141, protection_element_id:-141], primaryKey: false);
      insert('organizations', [ id: 100127, nci_institute_code: "03114", name: "Children's Cancer Institute of Australia", city: "Randwick", state: "NSW", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -142,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03114",GROUP_DESC:"03114 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -142,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03114",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03114",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -142,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03114", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1142 ,protection_group_id: -142, protection_element_id:-142], primaryKey: false);
      insert('organizations', [ id: 100128, nci_institute_code: "03115", name: "Sydney Children's Hospital", city: "Randwick", state: "NSW", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -143,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03115",GROUP_DESC:"03115 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -143,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03115",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03115",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -143,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03115", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1143 ,protection_group_id: -143, protection_element_id:-143], primaryKey: false);
      insert('organizations', [ id: 100129, nci_institute_code: "03116", name: "Ballarat Oncology and Haematology Services", city: "Wendouree", state: "VIC", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -144,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03116",GROUP_DESC:"03116 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -144,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03116",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03116",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -144,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03116", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1144 ,protection_group_id: -144, protection_element_id:-144], primaryKey: false);
      insert('organizations', [ id: 100130, nci_institute_code: "03117", name: "Maroondah Hospital", city: "Ringwood East", state: "VIC", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -145,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03117",GROUP_DESC:"03117 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -145,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03117",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03117",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -145,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03117", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1145 ,protection_group_id: -145, protection_element_id:-145], primaryKey: false);
      insert('organizations', [ id: 100131, nci_institute_code: "03118", name: "St John of God Hospital - Subiaco", city: "Subiaco", state: "WA", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -146,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03118",GROUP_DESC:"03118 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -146,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03118",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03118",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -146,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03118", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1146 ,protection_group_id: -146, protection_element_id:-146], primaryKey: false);
      insert('organizations', [ id: 100132, nci_institute_code: "03119", name: "Mater Private Centre for Haematology and Oncology", city: "South Brisbane", state: "QLD", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -147,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03119",GROUP_DESC:"03119 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -147,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03119",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03119",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -147,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03119", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1147 ,protection_group_id: -147, protection_element_id:-147], primaryKey: false);
      insert('organizations', [ id: 100133, nci_institute_code: "03120", name: "Andrew Love Cancer Center", city: "Geelong", state: "VIC", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -148,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03120",GROUP_DESC:"03120 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -148,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03120",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03120",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -148,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03120", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1148 ,protection_group_id: -148, protection_element_id:-148], primaryKey: false);
      insert('organizations', [ id: 100134, nci_institute_code: "03121", name: "Saint Johyn of Gog Hospital - Bunbury", city: "Bunbury", state: "WA", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -149,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03121",GROUP_DESC:"03121 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -149,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03121",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03121",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -149,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03121", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1149 ,protection_group_id: -149, protection_element_id:-149], primaryKey: false);
      insert('organizations', [ id: 100135, nci_institute_code: "03122", name: "MacFarlane Burnet Institute for Medical Research and Public Health", city: "Melbourne", state: "VIC", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -150,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03122",GROUP_DESC:"03122 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -150,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03122",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03122",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -150,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03122", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1150 ,protection_group_id: -150, protection_element_id:-150], primaryKey: false);
      insert('organizations', [ id: 100136, nci_institute_code: "03123", name: "Riverina Cancer Care Centre", city: "Wagga Wagga", state: "NSW", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -151,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03123",GROUP_DESC:"03123 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -151,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03123",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03123",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -151,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03123", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1151 ,protection_group_id: -151, protection_element_id:-151], primaryKey: false);
      insert('organizations', [ id: 100137, nci_institute_code: "04001", name: "University-Kinderklinik", city: "Graz, A-8036", country: "Austria"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -152,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.04001",GROUP_DESC:"04001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -152,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.04001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.04001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -152,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.04001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1152 ,protection_group_id: -152, protection_element_id:-152], primaryKey: false);
      insert('organizations', [ id: 100138, nci_institute_code: "04002", name: "Der Universitat Wien", city: "Ix Borshkegasse 8a", country: "Austria"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -153,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.04002",GROUP_DESC:"04002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -153,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.04002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.04002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -153,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.04002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1153 ,protection_group_id: -153, protection_element_id:-153], primaryKey: false);
      insert('organizations', [ id: 100139, nci_institute_code: "04003", name: "I. Chirurgische Univ-Klinik", city: "Wien Ix", country: "Austria"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -154,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.04003",GROUP_DESC:"04003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -154,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.04003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.04003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -154,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.04003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1154 ,protection_group_id: -154, protection_element_id:-154], primaryKey: false);
      insert('organizations', [ id: 100140, nci_institute_code: "04004", name: "Orthodadische Univ.-Klinic", city: "1097 Wien", country: "Austria"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -155,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.04004",GROUP_DESC:"04004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -155,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.04004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.04004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -155,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.04004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1155 ,protection_group_id: -155, protection_element_id:-155], primaryKey: false);
      insert('organizations', [ id: 100141, nci_institute_code: "04005", name: "University Hospital Innsbruck", city: "Innsbruck", country: "Austria"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -156,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.04005",GROUP_DESC:"04005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -156,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.04005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.04005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -156,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.04005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1156 ,protection_group_id: -156, protection_element_id:-156], primaryKey: false);
      insert('organizations', [ id: 100142, nci_institute_code: "04006", name: "I. Medizinische Univ-Klinik", city: "Wein 1090", country: "Austria"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -157,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.04006",GROUP_DESC:"04006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -157,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.04006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.04006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -157,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.04006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1157 ,protection_group_id: -157, protection_element_id:-157], primaryKey: false);
      insert('organizations', [ id: 100143, nci_institute_code: "04007", name: "Univ Fur Innere Mediizin", city: "Innsbruck", country: "Austria"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -158,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.04007",GROUP_DESC:"04007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -158,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.04007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.04007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -158,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.04007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1158 ,protection_group_id: -158, protection_element_id:-158], primaryKey: false);
      insert('organizations', [ id: 100144, nci_institute_code: "04008", name: "University of Vienna Allgemeines", city: "Vienna", country: "Austria"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -159,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.04008",GROUP_DESC:"04008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -159,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.04008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.04008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -159,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.04008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1159 ,protection_group_id: -159, protection_element_id:-159], primaryKey: false);
      insert('organizations', [ id: 100145, nci_institute_code: "04009", name: "Medizinische Universitatklinik", city: "A-8036 Graz", country: "Austria"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -160,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.04009",GROUP_DESC:"04009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -160,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.04009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.04009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -160,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.04009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1160 ,protection_group_id: -160, protection_element_id:-160], primaryKey: false);
      insert('organizations', [ id: 100146, nci_institute_code: "05001", name: "Hosp. Del Aire", city: "Madrid", state: "BAHAMAS", country: "Bahamas"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -161,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.05001",GROUP_DESC:"05001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -161,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.05001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.05001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -161,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.05001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1161 ,protection_group_id: -161, protection_element_id:-161], primaryKey: false);
      insert('organizations', [ id: 100147, nci_institute_code: "06001", name: "Clinique Generale Saint-Jean", city: "Brussels", country: "Belgium"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -162,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06001",GROUP_DESC:"06001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -162,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.06001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.06001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -162,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1162 ,protection_group_id: -162, protection_element_id:-162], primaryKey: false);
      insert('organizations', [ id: 100148, nci_institute_code: "06002", name: "Voies Respiratoires", city: "1040 Bruxelles", country: "Belgium"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -163,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06002",GROUP_DESC:"06002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -163,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.06002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.06002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -163,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1163 ,protection_group_id: -163, protection_element_id:-163], primaryKey: false);
      insert('organizations', [ id: 100149, nci_institute_code: "06004", name: "Universite Libre De Bruxelles", city: "Brussels", country: "Belgium"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -164,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06004",GROUP_DESC:"06004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -164,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.06004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.06004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -164,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1164 ,protection_group_id: -164, protection_element_id:-164], primaryKey: false);
    }

    void m6() {
        // all6 (25 terms)
      insert('organizations', [ id: 100150, nci_institute_code: "06005", name: "University of Louvain", city: "Louvain", country: "Belgium"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -165,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06005",GROUP_DESC:"06005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -165,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.06005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.06005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -165,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1165 ,protection_group_id: -165, protection_element_id:-165], primaryKey: false);
      insert('organizations', [ id: 100151, nci_institute_code: "06006", name: "Akademisch Ziekenhuis", city: "Gent", country: "Belgium"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -166,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06006",GROUP_DESC:"06006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -166,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.06006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.06006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -166,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1166 ,protection_group_id: -166, protection_element_id:-166], primaryKey: false);
      insert('organizations', [ id: 100152, nci_institute_code: "06007", name: "Rega Instituut", city: "Leuven, 3000", country: "Belgium"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -167,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06007",GROUP_DESC:"06007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -167,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.06007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.06007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -167,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1167 ,protection_group_id: -167, protection_element_id:-167], primaryKey: false);
      insert('organizations', [ id: 100153, nci_institute_code: "06008", name: "Hopital Univ. St. Pierre", city: "Bruxelles", country: "Belgium"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -168,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06008",GROUP_DESC:"06008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -168,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.06008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.06008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -168,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1168 ,protection_group_id: -168, protection_element_id:-168], primaryKey: false);
      insert('organizations', [ id: 100154, nci_institute_code: "06009", name: "St. Johns Hosp.", city: "Brussels", country: "Belgium"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -169,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06009",GROUP_DESC:"06009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -169,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.06009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.06009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -169,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1169 ,protection_group_id: -169, protection_element_id:-169], primaryKey: false);
      insert('organizations', [ id: 100155, nci_institute_code: "06010", name: "Hopital De Jolimont", city: "St. Paul", country: "Belgium"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -170,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06010",GROUP_DESC:"06010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -170,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.06010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.06010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -170,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1170 ,protection_group_id: -170, protection_element_id:-170], primaryKey: false);
      insert('organizations', [ id: 100156, nci_institute_code: "06011", name: "Vrije Universiteit Brussel", city: "1090 Brussel", country: "Belgium"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -171,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06011",GROUP_DESC:"06011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -171,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.06011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.06011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -171,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1171 ,protection_group_id: -171, protection_element_id:-171], primaryKey: false);
      insert('organizations', [ id: 100157, nci_institute_code: "06013", name: "Hopital De Baviere", city: "B-4020 Liege", country: "Belgium"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -172,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06013",GROUP_DESC:"06013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -172,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.06013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.06013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -172,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1172 ,protection_group_id: -172, protection_element_id:-172], primaryKey: false);
      insert('organizations', [ id: 100158, nci_institute_code: "06014", name: "Institut Jules Bordet", city: "B-1000 Bruxelles", country: "Belgium"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -173,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06014",GROUP_DESC:"06014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -173,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.06014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.06014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -173,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1173 ,protection_group_id: -173, protection_element_id:-173], primaryKey: false);
      insert('organizations', [ id: 100159, nci_institute_code: "06016", name: "University Hospital Antwerp", city: "B2520 Edegem Antwerp", country: "Belgium"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -174,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06016",GROUP_DESC:"06016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -174,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.06016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.06016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -174,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1174 ,protection_group_id: -174, protection_element_id:-174], primaryKey: false);
      insert('organizations', [ id: 100160, nci_institute_code: "06017", name: "University Hospital Saint Luc", city: "B-1200 Bruxelles", country: "Belgium"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -175,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06017",GROUP_DESC:"06017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -175,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.06017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.06017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -175,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1175 ,protection_group_id: -175, protection_element_id:-175], primaryKey: false);
      insert('organizations', [ id: 100161, nci_institute_code: "06018", name: "University Hospital Gasthuisberg", city: "3000 Leuven", country: "Belgium"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -176,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06018",GROUP_DESC:"06018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -176,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.06018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.06018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -176,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1176 ,protection_group_id: -176, protection_element_id:-176], primaryKey: false);
      insert('organizations', [ id: 100162, nci_institute_code: "06019", name: "University Hospital", city: "B-3000 Leuven", country: "Belgium"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -177,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06019",GROUP_DESC:"06019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -177,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.06019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.06019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -177,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1177 ,protection_group_id: -177, protection_element_id:-177], primaryKey: false);
      insert('organizations', [ id: 100163, nci_institute_code: "06020", name: "Hopital Civil Marchienne", city: "6030 Charleroi", country: "Belgium"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -178,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06020",GROUP_DESC:"06020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -178,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.06020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.06020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -178,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1178 ,protection_group_id: -178, protection_element_id:-178], primaryKey: false);
      insert('organizations', [ id: 100164, nci_institute_code: "06021", name: "H. Hart Ziekenhuis", city: "Roeselare", country: "Belgium"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -179,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06021",GROUP_DESC:"06021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -179,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.06021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.06021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -179,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1179 ,protection_group_id: -179, protection_element_id:-179], primaryKey: false);
      insert('organizations', [ id: 100165, nci_institute_code: "06022", name: "Centre hospitalier Unversitaire du Sart-Tilman", city: "Li\350ge", country: "Belgium"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -180,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06022",GROUP_DESC:"06022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -180,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.06022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.06022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -180,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1180 ,protection_group_id: -180, protection_element_id:-180], primaryKey: false);
      insert('organizations', [ id: 100166, nci_institute_code: "06023", name: "Hospital Univ. Erasme", city: "Brussels", country: "Belgium"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -181,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06023",GROUP_DESC:"06023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -181,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.06023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.06023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -181,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1181 ,protection_group_id: -181, protection_element_id:-181], primaryKey: false);
      insert('organizations', [ id: 100167, nci_institute_code: "06024", name: "Univ. Hosps.", city: "Leuven", country: "Belgium"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -182,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06024",GROUP_DESC:"06024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -182,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.06024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.06024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -182,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1182 ,protection_group_id: -182, protection_element_id:-182], primaryKey: false);
      insert('organizations', [ id: 100168, nci_institute_code: "06025", name: "Alegemeen Ziekenhuis Middelheim", city: "Antwerpen", state: "Belgium", country: "Belgium"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -183,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06025",GROUP_DESC:"06025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -183,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.06025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.06025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -183,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1183 ,protection_group_id: -183, protection_element_id:-183], primaryKey: false);
      insert('organizations', [ id: 100169, nci_institute_code: "06026", name: "Universitair Ziekenhuis Antwerpen", city: "Edegem", state: "Belgium", country: "Belgium"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -184,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06026",GROUP_DESC:"06026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -184,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.06026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.06026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -184,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1184 ,protection_group_id: -184, protection_element_id:-184], primaryKey: false);
      insert('organizations', [ id: 100170, nci_institute_code: "06027", name: "Katholieke Universiteit Leuven", city: "Leuven", country: "Belgium"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -185,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06027",GROUP_DESC:"06027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -185,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.06027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.06027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -185,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1185 ,protection_group_id: -185, protection_element_id:-185], primaryKey: false);
      insert('organizations', [ id: 100171, nci_institute_code: "06028", name: "Centre Hospitalier Regional de Huy", city: "Huy", country: "Belgium"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -186,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06028",GROUP_DESC:"06028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -186,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.06028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.06028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -186,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1186 ,protection_group_id: -186, protection_element_id:-186], primaryKey: false);
      insert('organizations', [ id: 100172, nci_institute_code: "06029", name: "Centre Hospitalier Regional de la Citadel", city: "Liege", country: "Belgium"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -187,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06029",GROUP_DESC:"06029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -187,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.06029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.06029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -187,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1187 ,protection_group_id: -187, protection_element_id:-187], primaryKey: false);
      insert('organizations', [ id: 100173, nci_institute_code: "06030", name: "Centre Hospitalier Peltzer La Tourelle", city: "Verviers", country: "Belgium"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -188,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06030",GROUP_DESC:"06030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -188,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.06030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.06030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -188,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1188 ,protection_group_id: -188, protection_element_id:-188], primaryKey: false);
      insert('organizations', [ id: 100174, nci_institute_code: "06031", name: "Algemeen Ziekenhuis Sint Lucas", city: "Ghent", country: "Belgium"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -189,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06031",GROUP_DESC:"06031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -189,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.06031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.06031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -189,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1189 ,protection_group_id: -189, protection_element_id:-189], primaryKey: false);
    }

    void m7() {
        // all7 (25 terms)
      insert('organizations', [ id: 100175, nci_institute_code: "06032", name: "Algemeen Ziekenhuis Damiaan", city: "Oostende", country: "Belgium"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -190,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06032",GROUP_DESC:"06032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -190,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.06032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.06032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -190,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1190 ,protection_group_id: -190, protection_element_id:-190], primaryKey: false);
      insert('organizations', [ id: 100176, nci_institute_code: "07001", name: "Nordwest Krankenhaus", city: "Frankfurt", country: "Bangladesh"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -191,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.07001",GROUP_DESC:"07001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -191,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.07001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.07001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -191,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.07001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1191 ,protection_group_id: -191, protection_element_id:-191], primaryKey: false);
      insert('organizations', [ id: 100177, nci_institute_code: "08001", name: "Hosp. Celso Ramos Fpolis", city: "Santa Catarina", country: "Brazil"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -192,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08001",GROUP_DESC:"08001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -192,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.08001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.08001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -192,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1192 ,protection_group_id: -192, protection_element_id:-192], primaryKey: false);
      insert('organizations', [ id: 100178, nci_institute_code: "08002", name: "Hospital Albert Einftein", city: "Sao Paulo", country: "Brazil"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -193,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08002",GROUP_DESC:"08002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -193,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.08002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.08002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -193,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1193 ,protection_group_id: -193, protection_element_id:-193], primaryKey: false);
      insert('organizations', [ id: 100179, nci_institute_code: "08003", name: "University of Parana School of Medicine", city: "Curitiba,Parana 8000", country: "Brazil"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -194,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08003",GROUP_DESC:"08003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -194,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.08003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.08003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -194,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1194 ,protection_group_id: -194, protection_element_id:-194], primaryKey: false);
      insert('organizations', [ id: 100180, nci_institute_code: "08004", name: "Brazilian National War College", city: "Rio De Janeiro", country: "Brazil"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -195,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08004",GROUP_DESC:"08004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -195,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.08004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.08004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -195,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1195 ,protection_group_id: -195, protection_element_id:-195], primaryKey: false);
      insert('organizations', [ id: 100181, nci_institute_code: "08005", name: "Hospital Sao Paulo", city: "Sao Paulo", country: "Brazil"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -196,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08005",GROUP_DESC:"08005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -196,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.08005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.08005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -196,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1196 ,protection_group_id: -196, protection_element_id:-196], primaryKey: false);
      insert('organizations', [ id: 100182, nci_institute_code: "08006", name: "Fundacao Hosp Distrito Fed.", city: "Brasilia", country: "Brazil"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -197,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08006",GROUP_DESC:"08006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -197,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.08006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.08006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -197,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1197 ,protection_group_id: -197, protection_element_id:-197], primaryKey: false);
      insert('organizations', [ id: 100183, nci_institute_code: "08007", name: "Hosp. Osvaldo Cruz", city: "Sao Paulo", country: "Brazil"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -198,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08007",GROUP_DESC:"08007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -198,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.08007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.08007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -198,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1198 ,protection_group_id: -198, protection_element_id:-198], primaryKey: false);
      insert('organizations', [ id: 100184, nci_institute_code: "08008", name: "Armed Forces Hospital", city: "Brazilia", country: "Brazil"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -199,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08008",GROUP_DESC:"08008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -199,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.08008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.08008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -199,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1199 ,protection_group_id: -199, protection_element_id:-199], primaryKey: false);
      insert('organizations', [ id: 100185, nci_institute_code: "08009", name: "Hosp. Santa Rita", city: "Porte Alegre", country: "Brazil"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -200,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08009",GROUP_DESC:"08009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -200,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.08009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.08009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -200,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1200 ,protection_group_id: -200, protection_element_id:-200], primaryKey: false);
      insert('organizations', [ id: 100186, nci_institute_code: "08010", name: "South American Office for Anticancer Drug Development", city: "Porto Alegre", country: "Brazil"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -201,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08010",GROUP_DESC:"08010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -201,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.08010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.08010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -201,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1201 ,protection_group_id: -201, protection_element_id:-201], primaryKey: false);
      insert('organizations', [ id: 100187, nci_institute_code: "08011", name: "Hosp. Belo Horizonte", city: "Belo Horizonte", country: "Brazil"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -202,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08011",GROUP_DESC:"08011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -202,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.08011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.08011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -202,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1202 ,protection_group_id: -202, protection_element_id:-202], primaryKey: false);
      insert('organizations', [ id: 100188, nci_institute_code: "08012", name: "Cidade Univ-Barao Geraldo", city: "Campinas, Sao Paulo", country: "Brazil"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -203,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08012",GROUP_DESC:"08012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -203,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.08012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.08012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -203,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1203 ,protection_group_id: -203, protection_element_id:-203], primaryKey: false);
      insert('organizations', [ id: 100189, nci_institute_code: "08013", name: "Rua Sete De Septembeo 508", city: "Recife P.E.", country: "Brazil"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -204,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08013",GROUP_DESC:"08013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -204,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.08013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.08013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -204,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1204 ,protection_group_id: -204, protection_element_id:-204], primaryKey: false);
      insert('organizations', [ id: 100190, nci_institute_code: "08014", name: "Hosp Evan Do Espirito Santo", city: "Vila Valha, S-N", country: "Brazil"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -205,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08014",GROUP_DESC:"08014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -205,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.08014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.08014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -205,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1205 ,protection_group_id: -205, protection_element_id:-205], primaryKey: false);
      insert('organizations', [ id: 100191, nci_institute_code: "08015", name: "Hosp. Felicio Rocho", city: "Belo Horizonte, 3000", country: "Brazil"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -206,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08015",GROUP_DESC:"08015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -206,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.08015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.08015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -206,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1206 ,protection_group_id: -206, protection_element_id:-206], primaryKey: false);
      insert('organizations', [ id: 100192, nci_institute_code: "08016", name: "Hosp. Universitario Ae Da Puc", city: "Porto Alegre- Rs", country: "Brazil"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -207,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08016",GROUP_DESC:"08016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -207,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.08016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.08016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -207,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1207 ,protection_group_id: -207, protection_element_id:-207], primaryKey: false);
      insert('organizations', [ id: 100193, nci_institute_code: "08017", name: "A. C. Camargo Hospital", city: "Sao Paulo", country: "Brazil"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -208,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08017",GROUP_DESC:"08017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -208,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.08017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.08017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -208,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1208 ,protection_group_id: -208, protection_element_id:-208], primaryKey: false);
      insert('organizations', [ id: 100194, nci_institute_code: "08018", name: "Visconde De Piraja", city: "Rio De Janeiro", country: "Brazil"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -209,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08018",GROUP_DESC:"08018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -209,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.08018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.08018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -209,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1209 ,protection_group_id: -209, protection_element_id:-209], primaryKey: false);
      insert('organizations', [ id: 100195, nci_institute_code: "08019", name: "Hospital De Base De Brasilia", city: "Brasilia Df", country: "Brazil"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -210,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08019",GROUP_DESC:"08019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -210,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.08019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.08019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -210,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1210 ,protection_group_id: -210, protection_element_id:-210], primaryKey: false);
      insert('organizations', [ id: 100196, nci_institute_code: "08020", name: "Instituto Nacional De Cancer", city: "Rio De Janerio", country: "Brazil"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -211,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08020",GROUP_DESC:"08020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -211,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.08020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.08020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -211,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1211 ,protection_group_id: -211, protection_element_id:-211], primaryKey: false);
      insert('organizations', [ id: 100197, nci_institute_code: "08021", name: "State University of Campinas", city: "Campinas, Sao Paulo", country: "Brazil"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -212,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08021",GROUP_DESC:"08021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -212,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.08021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.08021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -212,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1212 ,protection_group_id: -212, protection_element_id:-212], primaryKey: false);
      insert('organizations', [ id: 100198, nci_institute_code: "08022", name: "ASVP Hospital Sao Vicente De Paulo", city: "Rio Janerio", country: "Brazil"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -213,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08022",GROUP_DESC:"08022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -213,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.08022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.08022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -213,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1213 ,protection_group_id: -213, protection_element_id:-213], primaryKey: false);
      insert('organizations', [ id: 100199, nci_institute_code: "08023", name: "Instituto Do Cancer Infantil", city: "Porto Alegre Rs", country: "Brazil"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -214,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08023",GROUP_DESC:"08023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -214,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.08023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.08023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -214,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1214 ,protection_group_id: -214, protection_element_id:-214], primaryKey: false);
    }

    void m8() {
        // all8 (25 terms)
      insert('organizations', [ id: 100200, nci_institute_code: "08024", name: "Boldrini Children's Cancer Ctr.", city: "Sao Paulo", country: "Brazil"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -215,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08024",GROUP_DESC:"08024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -215,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.08024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.08024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -215,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1215 ,protection_group_id: -215, protection_element_id:-215], primaryKey: false);
      insert('organizations', [ id: 100201, nci_institute_code: "08025", name: "Faculdade De Medicina Do Abc", city: "Sao Paulo", country: "Brazil"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -216,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08025",GROUP_DESC:"08025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -216,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.08025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.08025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -216,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1216 ,protection_group_id: -216, protection_element_id:-216], primaryKey: false);
      insert('organizations', [ id: 100202, nci_institute_code: "08026", name: "Hospital Sirio e Libanes", city: "Sao Paulo", country: "Brazil"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -217,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08026",GROUP_DESC:"08026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -217,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.08026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.08026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -217,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1217 ,protection_group_id: -217, protection_element_id:-217], primaryKey: false);
      insert('organizations', [ id: 100203, nci_institute_code: "08027", name: "Hematologica", city: "Belo Horizonte", state: "Minas Gerais", country: "Brazil"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -218,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08027",GROUP_DESC:"08027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -218,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.08027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.08027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -218,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1218 ,protection_group_id: -218, protection_element_id:-218], primaryKey: false);
      insert('organizations', [ id: 100204, nci_institute_code: "09001", name: "An. 36, Em. Ii", city: "Sofia 18", country: "Bulgaria"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -219,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.09001",GROUP_DESC:"09001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -219,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.09001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.09001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -219,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.09001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1219 ,protection_group_id: -219, protection_element_id:-219], primaryKey: false);
      insert('organizations', [ id: 100205, nci_institute_code: "09002", name: "Department of Bulgarian Medical Academy", city: "Sofia", country: "Bulgaria"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -220,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.09002",GROUP_DESC:"09002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -220,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.09002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.09002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -220,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.09002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1220 ,protection_group_id: -220, protection_element_id:-220], primaryKey: false);
      insert('organizations', [ id: 100206, nci_institute_code: "09003", name: "District Cancer Hosp.", city: "Plovdiv", country: "Bulgaria"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -221,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.09003",GROUP_DESC:"09003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -221,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.09003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.09003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -221,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.09003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1221 ,protection_group_id: -221, protection_element_id:-221], primaryKey: false);
      insert('organizations', [ id: 100207, nci_institute_code: "09004", name: "Institute of Rontgenologie", city: "Vidin", country: "Bulgaria"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -222,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.09004",GROUP_DESC:"09004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -222,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.09004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.09004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -222,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.09004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1222 ,protection_group_id: -222, protection_element_id:-222], primaryKey: false);
      insert('organizations', [ id: 100208, nci_institute_code: "09005", name: "Oncological Research Institute", city: "Sofia-Darvenitza", country: "Bulgaria"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -223,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.09005",GROUP_DESC:"09005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -223,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.09005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.09005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -223,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.09005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1223 ,protection_group_id: -223, protection_element_id:-223], primaryKey: false);
      insert('organizations', [ id: 100209, nci_institute_code: "100001", name: "Kenyatta National Hospital", city: "Nairobi", country: "Kenya"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -224,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.100001",GROUP_DESC:"100001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -224,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.100001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.100001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -224,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.100001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1224 ,protection_group_id: -224, protection_element_id:-224], primaryKey: false);
      insert('organizations', [ id: 100210, nci_institute_code: "101001", name: "University of Jos", city: "Jos", country: "Nigeria"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -225,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.101001",GROUP_DESC:"101001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -225,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.101001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.101001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -225,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.101001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1225 ,protection_group_id: -225, protection_element_id:-225], primaryKey: false);
      insert('organizations', [ id: 100211, nci_institute_code: "101002", name: "University College Hospital", city: "Ibadan", state: "OYO", country: "Nigeria"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -226,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.101002",GROUP_DESC:"101002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -226,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.101002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.101002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -226,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.101002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1226 ,protection_group_id: -226, protection_element_id:-226], primaryKey: false);
      insert('organizations', [ id: 100212, nci_institute_code: "102001", name: "Paul Stradins Clinical University Hospital", city: "Riga", country: "Latvia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -227,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.102001",GROUP_DESC:"102001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -227,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.102001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.102001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -227,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.102001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1227 ,protection_group_id: -227, protection_element_id:-227], primaryKey: false);
      insert('organizations', [ id: 100213, nci_institute_code: "11001", name: "Montreal Childrens Hospital", city: "Montreal", state: "QC", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -228,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11001",GROUP_DESC:"11001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -228,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -228,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1228 ,protection_group_id: -228, protection_element_id:-228], primaryKey: false);
      insert('organizations', [ id: 100214, nci_institute_code: "11002", name: "Royal Victoria  Hospital", city: "Montreal", state: "Quebec", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -229,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11002",GROUP_DESC:"11002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -229,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -229,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1229 ,protection_group_id: -229, protection_element_id:-229], primaryKey: false);
      insert('organizations', [ id: 100215, nci_institute_code: "11003", name: "BCCA-Vancouver Island Cancer Centre", city: "Victoria", state: "BC", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -230,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11003",GROUP_DESC:"11003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -230,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -230,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1230 ,protection_group_id: -230, protection_element_id:-230], primaryKey: false);
      insert('organizations', [ id: 100216, nci_institute_code: "11004", name: "CHA Hopital Enfant-Jesus", city: "Quebec City", state: "Quebec", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -231,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11004",GROUP_DESC:"11004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -231,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -231,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1231 ,protection_group_id: -231, protection_element_id:-231], primaryKey: false);
      insert('organizations', [ id: 100217, nci_institute_code: "11005", name: "Nanaimo Regional General Hospital", city: "Nanaimo", state: "British Columbia", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -232,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11005",GROUP_DESC:"11005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -232,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -232,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1232 ,protection_group_id: -232, protection_element_id:-232], primaryKey: false);
      insert('organizations', [ id: 100218, nci_institute_code: "11006", name: "Camp Hill Hospital", city: "Halifax", state: "Nova Scotia", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -233,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11006",GROUP_DESC:"11006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -233,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -233,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1233 ,protection_group_id: -233, protection_element_id:-233], primaryKey: false);
      insert('organizations', [ id: 100219, nci_institute_code: "11008", name: "QE II Health Sciences Centre - Nova Scotia Cancer Centre", city: "Halifax", state: "Nova Scotia", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -234,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11008",GROUP_DESC:"11008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -234,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -234,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1234 ,protection_group_id: -234, protection_element_id:-234], primaryKey: false);
      insert('organizations', [ id: 100220, nci_institute_code: "11009", name: "IWK Health Centre", city: "Halifax", state: "Nova Scotia", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -235,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11009",GROUP_DESC:"11009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -235,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -235,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1235 ,protection_group_id: -235, protection_element_id:-235], primaryKey: false);
      insert('organizations', [ id: 100221, nci_institute_code: "11010", name: "Hospital for Children", city: "Halifax", state: "Nova Scotia", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -236,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11010",GROUP_DESC:"11010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -236,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -236,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1236 ,protection_group_id: -236, protection_element_id:-236], primaryKey: false);
      insert('organizations', [ id: 100222, nci_institute_code: "11011", name: "British Columbia Children's Hospital", city: "Vancouver", state: "British Columbia", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -237,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11011",GROUP_DESC:"11011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -237,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -237,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1237 ,protection_group_id: -237, protection_element_id:-237], primaryKey: false);
      insert('organizations', [ id: 100223, nci_institute_code: "11012", name: "Ontario Cancer Treatment and Research Foundation", city: "Windsor", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -238,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11012",GROUP_DESC:"11012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -238,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -238,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1238 ,protection_group_id: -238, protection_element_id:-238], primaryKey: false);
      insert('organizations', [ id: 100224, nci_institute_code: "11013", name: "Windsor Regional Cancer Centre", city: "Windsor", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -239,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11013",GROUP_DESC:"11013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -239,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -239,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1239 ,protection_group_id: -239, protection_element_id:-239], primaryKey: false);
    }

    void m9() {
        // all9 (25 terms)
      insert('organizations', [ id: 100225, nci_institute_code: "11014", name: "Montreal General Hospital", city: "Montreal", state: "Quebec", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -240,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11014",GROUP_DESC:"11014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -240,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -240,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1240 ,protection_group_id: -240, protection_element_id:-240], primaryKey: false);
      insert('organizations', [ id: 100226, nci_institute_code: "11015", name: "Iwk Childrens Hospital", city: "Halifax", state: "Nova Scotia", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -241,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11015",GROUP_DESC:"11015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -241,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -241,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1241 ,protection_group_id: -241, protection_element_id:-241], primaryKey: false);
      insert('organizations', [ id: 100227, nci_institute_code: "11016", name: "Niagara Health System", city: "St. Catharines", state: "ON", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -242,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11016",GROUP_DESC:"11016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -242,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -242,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1242 ,protection_group_id: -242, protection_element_id:-242], primaryKey: false);
      insert('organizations', [ id: 100228, nci_institute_code: "11017", name: "Hopital Du Sacre-Coeur de Montreal", city: "Montreal", state: "Quebec", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -243,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11017",GROUP_DESC:"11017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -243,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -243,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1243 ,protection_group_id: -243, protection_element_id:-243], primaryKey: false);
      insert('organizations', [ id: 100229, nci_institute_code: "11018", name: "Health Science Centre", city: "St Johns", state: "New Foundland", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -244,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11018",GROUP_DESC:"11018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -244,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -244,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1244 ,protection_group_id: -244, protection_element_id:-244], primaryKey: false);
      insert('organizations', [ id: 100230, nci_institute_code: "11019", name: "The Royal Victoria Hospital", city: "Barrie", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -245,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11019",GROUP_DESC:"11019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -245,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -245,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1245 ,protection_group_id: -245, protection_element_id:-245], primaryKey: false);
      insert('organizations', [ id: 100231, nci_institute_code: "11020", name: "Queens University", city: "Kingston", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -246,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11020",GROUP_DESC:"11020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -246,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -246,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1246 ,protection_group_id: -246, protection_element_id:-246], primaryKey: false);
      insert('organizations', [ id: 100232, nci_institute_code: "11021", name: "Cancer Centre of Southeastern Ontario at Kingston General Hospital", city: "Kingston", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -247,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11021",GROUP_DESC:"11021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -247,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -247,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1247 ,protection_group_id: -247, protection_element_id:-247], primaryKey: false);
      insert('organizations', [ id: 100233, nci_institute_code: "11022", name: "Health Sciences Centre", city: "Winnipeg", state: "Manitoba", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -248,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11022",GROUP_DESC:"11022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -248,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -248,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1248 ,protection_group_id: -248, protection_element_id:-248], primaryKey: false);
      insert('organizations', [ id: 100234, nci_institute_code: "11023", name: "University of Manitoba", city: "Winnipeg", state: "Manitaba", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -249,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11023",GROUP_DESC:"11023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -249,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -249,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1249 ,protection_group_id: -249, protection_element_id:-249], primaryKey: false);
      insert('organizations', [ id: 100235, nci_institute_code: "11024", name: "St Boniface General Hospital", city: "Winnipeg", state: "Manitoba", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -250,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11024",GROUP_DESC:"11024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -250,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -250,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1250 ,protection_group_id: -250, protection_element_id:-250], primaryKey: false);
      insert('organizations', [ id: 100236, nci_institute_code: "11025", name: "Ottawa Health Research Institute-General Division", city: "Ottawa", state: "ON", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -251,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11025",GROUP_DESC:"11025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -251,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -251,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1251 ,protection_group_id: -251, protection_element_id:-251], primaryKey: false);
      insert('organizations', [ id: 100237, nci_institute_code: "11026", name: "University of Toronto", city: "Toronto", state: "ON", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -252,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11026",GROUP_DESC:"11026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -252,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -252,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1252 ,protection_group_id: -252, protection_element_id:-252], primaryKey: false);
      insert('organizations', [ id: 100238, nci_institute_code: "11027", name: "Saint Michael's Hospital", city: "Toronto", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -253,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11027",GROUP_DESC:"11027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -253,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -253,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1253 ,protection_group_id: -253, protection_element_id:-253], primaryKey: false);
      insert('organizations', [ id: 100239, nci_institute_code: "11028", name: "Mount Sinai Hospital", city: "Toronto", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -254,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11028",GROUP_DESC:"11028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -254,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -254,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1254 ,protection_group_id: -254, protection_element_id:-254], primaryKey: false);
      insert('organizations', [ id: 100240, nci_institute_code: "11030", name: "University Health Network-Princess Margaret Hospital", city: "Toronto", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -255,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11030",GROUP_DESC:"11030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -255,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -255,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1255 ,protection_group_id: -255, protection_element_id:-255], primaryKey: false);
      insert('organizations', [ id: 100241, nci_institute_code: "11031", name: "Wellesley Hospital", city: "Toronto", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -256,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11031",GROUP_DESC:"11031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -256,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -256,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1256 ,protection_group_id: -256, protection_element_id:-256], primaryKey: false);
      insert('organizations', [ id: 100242, nci_institute_code: "11032", name: "Verdun Granil Hospital", city: "Montreal", state: "Quebec", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -257,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11032",GROUP_DESC:"11032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -257,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -257,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1257 ,protection_group_id: -257, protection_element_id:-257], primaryKey: false);
      insert('organizations', [ id: 100243, nci_institute_code: "11033", name: "Ontario Veterinary College", city: "Guelph", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -258,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11033",GROUP_DESC:"11033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -258,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -258,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1258 ,protection_group_id: -258, protection_element_id:-258], primaryKey: false);
      insert('organizations', [ id: 100244, nci_institute_code: "11034", name: "Calgary Cancer Clinic", city: "Calgary", state: "Alberta", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -259,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11034",GROUP_DESC:"11034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -259,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -259,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1259 ,protection_group_id: -259, protection_element_id:-259], primaryKey: false);
      insert('organizations', [ id: 100245, nci_institute_code: "11035", name: "Atlantic Health Sciences Corporation-Saint John Regional Hospital", city: "Saint John", state: "New Brunswick", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -260,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11035",GROUP_DESC:"11035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -260,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -260,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1260 ,protection_group_id: -260, protection_element_id:-260], primaryKey: false);
      insert('organizations', [ id: 100246, nci_institute_code: "11036", name: "Hopital St-Francois D'Assise", city: "Quebec", state: "Quebec", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -261,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11036",GROUP_DESC:"11036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -261,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -261,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1261 ,protection_group_id: -261, protection_element_id:-261], primaryKey: false);
      insert('organizations', [ id: 100247, nci_institute_code: "11037", name: "Dalhousie U/Victoria Gen Hosp", city: "Halifax", state: "Nova Scotia", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -262,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11037",GROUP_DESC:"11037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -262,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -262,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1262 ,protection_group_id: -262, protection_element_id:-262], primaryKey: false);
      insert('organizations', [ id: 100248, nci_institute_code: "11038", name: "Centre Hosp Reg De L'Outaouais (Chro)", city: "New Brunswick", state: "Quebec", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -263,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11038",GROUP_DESC:"11038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -263,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -263,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1263 ,protection_group_id: -263, protection_element_id:-263], primaryKey: false);
      insert('organizations', [ id: 100249, nci_institute_code: "11039", name: "A. Maxwell Evans Clinic", city: "Vancouve", state: "British Columbia", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -264,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11039",GROUP_DESC:"11039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -264,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -264,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1264 ,protection_group_id: -264, protection_element_id:-264], primaryKey: false);
    }

    void m10() {
        // all10 (25 terms)
      insert('organizations', [ id: 100250, nci_institute_code: "11040", name: "Children's Hospital of Eastern Ontario", city: "Ottawa, Ont", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -265,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11040",GROUP_DESC:"11040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -265,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -265,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1265 ,protection_group_id: -265, protection_element_id:-265], primaryKey: false);
      insert('organizations', [ id: 100251, nci_institute_code: "11042", name: "Kitcher Waterloo Hospital", city: "Kitchener", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -266,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11042",GROUP_DESC:"11042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -266,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -266,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1266 ,protection_group_id: -266, protection_element_id:-266], primaryKey: false);
      insert('organizations', [ id: 100252, nci_institute_code: "11043", name: "The Moncton Hospital", city: "Moncton", state: "New Brunswick", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -267,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11043",GROUP_DESC:"11043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -267,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -267,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1267 ,protection_group_id: -267, protection_element_id:-267], primaryKey: false);
      insert('organizations', [ id: 100253, nci_institute_code: "11044", name: "Markham Stouffville Hospital", city: "Markham", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -268,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11044",GROUP_DESC:"11044 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -268,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11044",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11044",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -268,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11044", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1268 ,protection_group_id: -268, protection_element_id:-268], primaryKey: false);
      insert('organizations', [ id: 100254, nci_institute_code: "11045", name: "Scarborough Centenary Hospital", city: "West Hill", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -269,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11045",GROUP_DESC:"11045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -269,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -269,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1269 ,protection_group_id: -269, protection_element_id:-269], primaryKey: false);
      insert('organizations', [ id: 100255, nci_institute_code: "11046", name: "Saint Paul's Hospital", city: "Vancouver", state: "British Columbia", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -270,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11046",GROUP_DESC:"11046 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -270,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11046",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11046",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -270,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11046", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1270 ,protection_group_id: -270, protection_element_id:-270], primaryKey: false);
      insert('organizations', [ id: 100256, nci_institute_code: "11047", name: "Hopital Du St.-Sacrement, Universite Laval, Quebec, QC", city: "Quebec City", state: "Quebec", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -271,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11047",GROUP_DESC:"11047 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -271,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11047",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11047",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -271,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11047", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1271 ,protection_group_id: -271, protection_element_id:-271], primaryKey: false);
      insert('organizations', [ id: 100257, nci_institute_code: "11049", name: "Scarborough Centre Hospital", city: "E. Scarborough", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -272,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11049",GROUP_DESC:"11049 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -272,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11049",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11049",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -272,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11049", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1272 ,protection_group_id: -272, protection_element_id:-272], primaryKey: false);
      insert('organizations', [ id: 100258, nci_institute_code: "11051", name: "Saint. Josephs Hospital.", city: "Vancouver", state: "British Columbia", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -273,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11051",GROUP_DESC:"11051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -273,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -273,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1273 ,protection_group_id: -273, protection_element_id:-273], primaryKey: false);
      insert('organizations', [ id: 100259, nci_institute_code: "11052", name: "UHN-Toronto General Hospital", city: "Toronto", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -274,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11052",GROUP_DESC:"11052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -274,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -274,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1274 ,protection_group_id: -274, protection_element_id:-274], primaryKey: false);
      insert('organizations', [ id: 100260, nci_institute_code: "11054", name: "Vancouver General Hospital", city: "Vancouver", state: "BC", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -275,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11054",GROUP_DESC:"11054 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -275,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11054",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11054",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -275,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11054", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1275 ,protection_group_id: -275, protection_element_id:-275], primaryKey: false);
      insert('organizations', [ id: 100261, nci_institute_code: "11055", name: "Trillium Health Centre (Queensway and Mississauga)", city: "Mississauga", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -276,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11055",GROUP_DESC:"11055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -276,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -276,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1276 ,protection_group_id: -276, protection_element_id:-276], primaryKey: false);
      insert('organizations', [ id: 100262, nci_institute_code: "11057", name: "Algoma District Cancer Program Sault Area Hospital", city: "Sault Ste Marie", state: "ON", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -277,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11057",GROUP_DESC:"11057 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -277,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11057",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11057",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -277,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11057", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1277 ,protection_group_id: -277, protection_element_id:-277], primaryKey: false);
      insert('organizations', [ id: 100263, nci_institute_code: "11058", name: "Penticton Regional Hospital", city: "Penticton", state: "British Columbia", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -278,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11058",GROUP_DESC:"11058 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -278,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11058",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11058",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -278,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11058", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1278 ,protection_group_id: -278, protection_element_id:-278], primaryKey: false);
      insert('organizations', [ id: 100264, nci_institute_code: "11059", name: "BCCA-Fraser Valley Cancer Centre", city: "Surrey", state: "British Columbia", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -279,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11059",GROUP_DESC:"11059 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -279,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11059",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11059",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -279,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11059", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1279 ,protection_group_id: -279, protection_element_id:-279], primaryKey: false);
      insert('organizations', [ id: 100265, nci_institute_code: "11060", name: "Hopital Maisonneuve-Rosemont", city: "Montreal", state: "Quebec", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -280,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11060",GROUP_DESC:"11060 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -280,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11060",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11060",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -280,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11060", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1280 ,protection_group_id: -280, protection_element_id:-280], primaryKey: false);
      insert('organizations', [ id: 100266, nci_institute_code: "11061", name: "Central Alberta Cancer Centre", city: "Red Deer", state: "Alberta", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -281,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11061",GROUP_DESC:"11061 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -281,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11061",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11061",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -281,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11061", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1281 ,protection_group_id: -281, protection_element_id:-281], primaryKey: false);
      insert('organizations', [ id: 100267, nci_institute_code: "11062", name: "CHUM-Saint Luc Hospital", city: "Montreal", state: "Quebec", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -282,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11062",GROUP_DESC:"11062 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -282,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11062",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11062",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -282,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11062", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1282 ,protection_group_id: -282, protection_element_id:-282], primaryKey: false);
      insert('organizations', [ id: 100268, nci_institute_code: "11064", name: "Santa Cabrini Hospital", city: "Montreal", state: "Quebec", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -283,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11064",GROUP_DESC:"11064 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -283,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11064",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11064",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -283,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11064", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1283 ,protection_group_id: -283, protection_element_id:-283], primaryKey: false);
      insert('organizations', [ id: 100269, nci_institute_code: "11065", name: "Centre Hospitalier Universitaire de Sherbrooke", city: "Fleurimont", state: "QC", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -284,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11065",GROUP_DESC:"11065 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -284,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11065",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11065",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -284,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11065", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1284 ,protection_group_id: -284, protection_element_id:-284], primaryKey: false);
      insert('organizations', [ id: 100270, nci_institute_code: "11067", name: "Chedoke-Mcmaster Hosp.", city: "Hamilton", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -285,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11067",GROUP_DESC:"11067 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -285,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11067",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11067",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -285,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11067", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1285 ,protection_group_id: -285, protection_element_id:-285], primaryKey: false);
      insert('organizations', [ id: 100271, nci_institute_code: "11068", name: "Centre Hospital Pierre Boucher", city: "Longueuil", state: "Quebec", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -286,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11068",GROUP_DESC:"11068 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -286,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11068",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11068",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -286,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11068", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1286 ,protection_group_id: -286, protection_element_id:-286], primaryKey: false);
      insert('organizations', [ id: 100272, nci_institute_code: "11069", name: "Hopital Charles LeMoyne", city: "Greenfield Park", state: "QC", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -287,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11069",GROUP_DESC:"11069 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -287,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11069",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11069",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -287,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11069", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1287 ,protection_group_id: -287, protection_element_id:-287], primaryKey: false);
      insert('organizations', [ id: 100273, nci_institute_code: "11070", name: "North York General Hospital", city: "Toronto", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -288,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11070",GROUP_DESC:"11070 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -288,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11070",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11070",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -288,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11070", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1288 ,protection_group_id: -288, protection_element_id:-288], primaryKey: false);
      insert('organizations', [ id: 100274, nci_institute_code: "11071", name: "Victoria General Hospital", city: "Halifax", state: "Nova Scotia", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -289,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11071",GROUP_DESC:"11071 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -289,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11071",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11071",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -289,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11071", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1289 ,protection_group_id: -289, protection_element_id:-289], primaryKey: false);
    }

    void m11() {
        // all11 (25 terms)
      insert('organizations', [ id: 100275, nci_institute_code: "11072", name: "Doctor H. Bliss Murphy Cancer Centre", city: "St. John's", state: "Newfoundland", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -290,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11072",GROUP_DESC:"11072 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -290,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11072",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11072",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -290,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11072", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1290 ,protection_group_id: -290, protection_element_id:-290], primaryKey: false);
      insert('organizations', [ id: 100276, nci_institute_code: "11073", name: "Chuq - Pavilion Hotel-Dieu De Quebec", city: "Quebec City", state: "Quebec", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -291,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11073",GROUP_DESC:"11073 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -291,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11073",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11073",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -291,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11073", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1291 ,protection_group_id: -291, protection_element_id:-291], primaryKey: false);
      insert('organizations', [ id: 100277, nci_institute_code: "11074", name: "Centre Hospitalier Universitaire de Quebec", city: "Ste-Foy", state: "Quebec", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -292,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11074",GROUP_DESC:"11074 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -292,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11074",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11074",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -292,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11074", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1292 ,protection_group_id: -292, protection_element_id:-292], primaryKey: false);
      insert('organizations', [ id: 100278, nci_institute_code: "11076", name: "Allan Blair Cancer Centre", city: "Regina", state: "Saskatchewan", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -293,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11076",GROUP_DESC:"11076 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -293,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11076",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11076",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -293,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11076", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1293 ,protection_group_id: -293, protection_element_id:-293], primaryKey: false);
      insert('organizations', [ id: 100279, nci_institute_code: "11077", name: "Trail Regional Hospital", city: "Trail", state: "British Columbia", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -294,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11077",GROUP_DESC:"11077 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -294,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11077",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11077",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -294,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11077", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1294 ,protection_group_id: -294, protection_element_id:-294], primaryKey: false);
      insert('organizations', [ id: 100280, nci_institute_code: "11078", name: "The Ottawa Hospital Regional Cancer Centre (Ottawa Health Research Institute) Civic Campus", city: "Ottawa", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -295,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11078",GROUP_DESC:"11078 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -295,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11078",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11078",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -295,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11078", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1295 ,protection_group_id: -295, protection_element_id:-295], primaryKey: false);
      insert('organizations', [ id: 100281, nci_institute_code: "11079", name: "Womens Clinic", city: "Halifax", state: "Nova Scotia", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -296,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11079",GROUP_DESC:"11079 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -296,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11079",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11079",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -296,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11079", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1296 ,protection_group_id: -296, protection_element_id:-296], primaryKey: false);
      insert('organizations', [ id: 100282, nci_institute_code: "11080", name: "Royal Columbia Hospital", city: "New Westminster", state: "British Columbia", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -297,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11080",GROUP_DESC:"11080 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -297,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11080",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11080",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -297,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11080", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1297 ,protection_group_id: -297, protection_element_id:-297], primaryKey: false);
      insert('organizations', [ id: 100283, nci_institute_code: "11081", name: "BCCA Vancouver Cancer Centre", city: "Vancouver", state: "British Columbia", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -298,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11081",GROUP_DESC:"11081 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -298,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11081",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11081",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -298,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11081", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1298 ,protection_group_id: -298, protection_element_id:-298], primaryKey: false);
      insert('organizations', [ id: 100284, nci_institute_code: "11083", name: "Saint Joseph's Hospital", city: "Hamilton", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -299,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11083",GROUP_DESC:"11083 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -299,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11083",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11083",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -299,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11083", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1299 ,protection_group_id: -299, protection_element_id:-299], primaryKey: false);
      insert('organizations', [ id: 100285, nci_institute_code: "11084", name: "Janeway Child Health Centre", city: "St. John's", state: "Newfoundland", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -300,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11084",GROUP_DESC:"11084 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -300,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11084",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11084",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -300,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11084", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1300 ,protection_group_id: -300, protection_element_id:-300], primaryKey: false);
      insert('organizations', [ id: 100286, nci_institute_code: "11086", name: "University of Ottawa Heart Institute", city: "Ottawa", state: "ON", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -301,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11086",GROUP_DESC:"11086 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -301,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11086",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11086",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -301,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11086", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1301 ,protection_group_id: -301, protection_element_id:-301], primaryKey: false);
      insert('organizations', [ id: 100287, nci_institute_code: "11087", name: "Oshawa Clinic", city: "Oshawa", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -302,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11087",GROUP_DESC:"11087 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -302,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11087",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11087",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -302,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11087", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1302 ,protection_group_id: -302, protection_element_id:-302], primaryKey: false);
      insert('organizations', [ id: 100288, nci_institute_code: "11088", name: "Women's College Hospital", city: "Toronto", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -303,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11088",GROUP_DESC:"11088 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -303,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11088",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11088",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -303,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11088", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1303 ,protection_group_id: -303, protection_element_id:-303], primaryKey: false);
      insert('organizations', [ id: 100289, nci_institute_code: "11089", name: "Ontario Cancer Foundation", city: "Hamilton", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -304,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11089",GROUP_DESC:"11089 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -304,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11089",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11089",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -304,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11089", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1304 ,protection_group_id: -304, protection_element_id:-304], primaryKey: false);
      insert('organizations', [ id: 100290, nci_institute_code: "11090", name: "The Male Health Centre/Climx Research Network", city: "Oakville", state: "ON", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -305,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11090",GROUP_DESC:"11090 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -305,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11090",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11090",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -305,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11090", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1305 ,protection_group_id: -305, protection_element_id:-305], primaryKey: false);
      insert('organizations', [ id: 100291, nci_institute_code: "11091", name: "University of Laval", city: "Quebec", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -306,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11091",GROUP_DESC:"11091 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -306,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11091",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11091",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -306,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11091", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1306 ,protection_group_id: -306, protection_element_id:-306], primaryKey: false);
      insert('organizations', [ id: 100292, nci_institute_code: "11092", name: "School of Psychology", city: "Quebec", state: "Quebec", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -307,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11092",GROUP_DESC:"11092 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -307,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11092",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11092",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -307,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11092", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1307 ,protection_group_id: -307, protection_element_id:-307], primaryKey: false);
      insert('organizations', [ id: 100293, nci_institute_code: "11093", name: "Ontario Cancer Foundation", city: "Ottawa, Ont", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -308,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11093",GROUP_DESC:"11093 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -308,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11093",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11093",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -308,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11093", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1308 ,protection_group_id: -308, protection_element_id:-308], primaryKey: false);
      insert('organizations', [ id: 100294, nci_institute_code: "11094", name: "Victoria Hospital", city: "London", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -309,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11094",GROUP_DESC:"11094 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -309,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11094",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11094",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -309,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11094", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1309 ,protection_group_id: -309, protection_element_id:-309], primaryKey: false);
      insert('organizations', [ id: 100295, nci_institute_code: "11095", name: "War Memorial Childrens Hospital", city: "London, Ont", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -310,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11095",GROUP_DESC:"11095 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -310,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11095",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11095",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -310,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11095", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1310 ,protection_group_id: -310, protection_element_id:-310], primaryKey: false);
      insert('organizations', [ id: 100296, nci_institute_code: "11097", name: "Henderson General Hospital", city: "Hamilton", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -311,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11097",GROUP_DESC:"11097 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -311,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11097",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11097",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -311,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11097", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1311 ,protection_group_id: -311, protection_element_id:-311], primaryKey: false);
      insert('organizations', [ id: 100297, nci_institute_code: "11098", name: "Hospital for Sick Children", city: "Toronto", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -312,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11098",GROUP_DESC:"11098 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -312,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11098",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11098",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -312,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11098", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1312 ,protection_group_id: -312, protection_element_id:-312], primaryKey: false);
      insert('organizations', [ id: 100298, nci_institute_code: "11099", name: "St. Joseph Hospital", city: "Trois-Rivieres", state: "Quebec", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -313,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11099",GROUP_DESC:"11099 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -313,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11099",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11099",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -313,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11099", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1313 ,protection_group_id: -313, protection_element_id:-313], primaryKey: false);
      insert('organizations', [ id: 100299, nci_institute_code: "11100", name: "CHUM-Hotel Dieu du Montreal", city: "Montreal", state: "Quebec", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -314,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11100",GROUP_DESC:"11100 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -314,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11100",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11100",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -314,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11100", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1314 ,protection_group_id: -314, protection_element_id:-314], primaryKey: false);
    }

    void m12() {
        // all12 (25 terms)
      insert('organizations', [ id: 100300, nci_institute_code: "11101", name: "Children's Hospital of Western Ontario", city: "London", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -315,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11101",GROUP_DESC:"11101 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -315,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11101",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11101",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -315,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11101", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1315 ,protection_group_id: -315, protection_element_id:-315], primaryKey: false);
      insert('organizations', [ id: 100301, nci_institute_code: "11102", name: "Queen Elizabeth Hospital", city: "Charlottetown", state: "Prince Edward Island", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -316,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11102",GROUP_DESC:"11102 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -316,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11102",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11102",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -316,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11102", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1316 ,protection_group_id: -316, protection_element_id:-316], primaryKey: false);
      insert('organizations', [ id: 100302, nci_institute_code: "11103", name: "National Defense Medical Centre", city: "Ottawt", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -317,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11103",GROUP_DESC:"11103 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -317,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11103",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11103",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -317,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11103", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1317 ,protection_group_id: -317, protection_element_id:-317], primaryKey: false);
      insert('organizations', [ id: 100303, nci_institute_code: "11104", name: "University of Alberta Hospital", city: "Edmonton", state: "Alberta", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -318,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11104",GROUP_DESC:"11104 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -318,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11104",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11104",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -318,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11104", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1318 ,protection_group_id: -318, protection_element_id:-318], primaryKey: false);
      insert('organizations', [ id: 100304, nci_institute_code: "11105", name: "Clinical Research Unit at Vancouver Coastal Health Authority", city: "Vancouver", state: "BC", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -319,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11105",GROUP_DESC:"11105 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -319,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11105",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11105",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -319,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11105", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1319 ,protection_group_id: -319, protection_element_id:-319], primaryKey: false);
      insert('organizations', [ id: 100305, nci_institute_code: "11107", name: "Prince County Hospital", city: "Summerside", state: "Prince Edward Island", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -320,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11107",GROUP_DESC:"11107 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -320,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11107",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11107",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -320,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11107", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1320 ,protection_group_id: -320, protection_element_id:-320], primaryKey: false);
      insert('organizations', [ id: 100306, nci_institute_code: "11108", name: "PEI Cancer Treatment Centre-Queen Elizabeth Hospital", city: "Charlottetown", state: "PE", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -321,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11108",GROUP_DESC:"11108 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -321,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11108",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11108",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -321,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11108", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1321 ,protection_group_id: -321, protection_element_id:-321], primaryKey: false);
      insert('organizations', [ id: 100307, nci_institute_code: "11109", name: "Foothills Hospital", city: "Calgary", state: "Alberta", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -322,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11109",GROUP_DESC:"11109 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -322,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11109",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11109",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -322,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11109", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1322 ,protection_group_id: -322, protection_element_id:-322], primaryKey: false);
      insert('organizations', [ id: 100308, nci_institute_code: "11110", name: "Centre Hospitalier Affilie Universitaire de Quebec, Hospital du St-Sacrement", city: "Quebec City", state: "Quebec", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -323,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11110",GROUP_DESC:"11110 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -323,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11110",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11110",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -323,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11110", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1323 ,protection_group_id: -323, protection_element_id:-323], primaryKey: false);
      insert('organizations', [ id: 100309, nci_institute_code: "11111", name: "Dr. Leon Richard Oncology Centre", city: "Moncton", state: "New Brunswick", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -324,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11111",GROUP_DESC:"11111 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -324,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11111",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11111",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -324,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11111", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1324 ,protection_group_id: -324, protection_element_id:-324], primaryKey: false);
      insert('organizations', [ id: 100310, nci_institute_code: "11112", name: "Iwk-Grace Health Centre", city: "Halifax", state: "Nova Scotia", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -325,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11112",GROUP_DESC:"11112 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -325,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11112",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11112",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -325,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11112", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1325 ,protection_group_id: -325, protection_element_id:-325], primaryKey: false);
      insert('organizations', [ id: 100311, nci_institute_code: "11115", name: "Code Available-Merger See 11198", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -326,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11115",GROUP_DESC:"11115 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -326,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11115",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11115",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -326,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11115", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1326 ,protection_group_id: -326, protection_element_id:-326], primaryKey: false);
      insert('organizations', [ id: 100312, nci_institute_code: "11116", name: "Jewish General Hospital", city: "Montreal", state: "QC", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -327,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11116",GROUP_DESC:"11116 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -327,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11116",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11116",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -327,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11116", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1327 ,protection_group_id: -327, protection_element_id:-327], primaryKey: false);
      insert('organizations', [ id: 100313, nci_institute_code: "11117", name: "McMaster University", city: "Hamilton", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -328,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11117",GROUP_DESC:"11117 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -328,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11117",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11117",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -328,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11117", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1328 ,protection_group_id: -328, protection_element_id:-328], primaryKey: false);
      insert('organizations', [ id: 100314, nci_institute_code: "11118", name: "Odette Cancer Centre- Sunnybrook Health Sciences Centre", city: "Toronto", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -329,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11118",GROUP_DESC:"11118 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -329,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11118",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11118",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -329,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11118", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1329 ,protection_group_id: -329, protection_element_id:-329], primaryKey: false);
      insert('organizations', [ id: 100315, nci_institute_code: "11120", name: "Saskatoon Cancer Centre", city: "Saskatoon", state: "SK", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -330,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11120",GROUP_DESC:"11120 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -330,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11120",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11120",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -330,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11120", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1330 ,protection_group_id: -330, protection_element_id:-330], primaryKey: false);
      insert('organizations', [ id: 100316, nci_institute_code: "11123", name: "Hopital Laval", city: "Quebec", state: "QC", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -331,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11123",GROUP_DESC:"11123 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -331,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11123",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11123",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -331,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11123", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1331 ,protection_group_id: -331, protection_element_id:-331], primaryKey: false);
      insert('organizations', [ id: 100317, nci_institute_code: "11124", name: "Royal Jubilee Hospital", city: "Victoria", state: "British Columbia", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -332,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11124",GROUP_DESC:"11124 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -332,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11124",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11124",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -332,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11124", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1332 ,protection_group_id: -332, protection_element_id:-332], primaryKey: false);
      insert('organizations', [ id: 100318, nci_institute_code: "11125", name: "Centre Hosp. U. De Sherbrooke", city: "Sherbrooke", state: "Quebec", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -333,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11125",GROUP_DESC:"11125 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -333,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11125",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11125",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -333,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11125", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1333 ,protection_group_id: -333, protection_element_id:-333], primaryKey: false);
      insert('organizations', [ id: 100319, nci_institute_code: "11126", name: "Hotel-Dieu De Levis", city: "Levi", state: "Quebec", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -334,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11126",GROUP_DESC:"11126 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -334,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11126",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11126",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -334,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11126", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1334 ,protection_group_id: -334, protection_element_id:-334], primaryKey: false);
      insert('organizations', [ id: 100320, nci_institute_code: "11127", name: "Hopital Notre-Dame", city: "Montreal", state: "Quebec", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -335,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11127",GROUP_DESC:"11127 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -335,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11127",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11127",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -335,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11127", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1335 ,protection_group_id: -335, protection_element_id:-335], primaryKey: false);
      insert('organizations', [ id: 100321, nci_institute_code: "11128", name: "University Hospital.", city: "London", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -336,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11128",GROUP_DESC:"11128 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -336,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11128",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11128",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -336,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11128", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1336 ,protection_group_id: -336, protection_element_id:-336], primaryKey: false);
      insert('organizations', [ id: 100322, nci_institute_code: "11131", name: "Saint Mary's Hospital", city: "Montreal", state: "Quebec", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -337,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11131",GROUP_DESC:"11131 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -337,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11131",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11131",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -337,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11131", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1337 ,protection_group_id: -337, protection_element_id:-337], primaryKey: false);
      insert('organizations', [ id: 100323, nci_institute_code: "11132", name: "Cross Cancer Institute", city: "Edmonton", state: "Alberta", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -338,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11132",GROUP_DESC:"11132 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -338,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11132",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11132",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -338,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11132", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1338 ,protection_group_id: -338, protection_element_id:-338], primaryKey: false);
      insert('organizations', [ id: 100324, nci_institute_code: "11134", name: "Tom Baker Cancer Centre", city: "Calgary", state: "Alberta", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -339,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11134",GROUP_DESC:"11134 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -339,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11134",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11134",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -339,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11134", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1339 ,protection_group_id: -339, protection_element_id:-339], primaryKey: false);
    }

    void m13() {
        // all13 (25 terms)
      insert('organizations', [ id: 100325, nci_institute_code: "11137", name: "London Regional Cancer Program", city: "London", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -340,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11137",GROUP_DESC:"11137 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -340,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11137",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11137",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -340,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11137", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1340 ,protection_group_id: -340, protection_element_id:-340], primaryKey: false);
      insert('organizations', [ id: 100326, nci_institute_code: "11138", name: "CancerCare Manitoba", city: "Winnipeg", state: "Manitoba", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -341,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11138",GROUP_DESC:"11138 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -341,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11138",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11138",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -341,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11138", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1341 ,protection_group_id: -341, protection_element_id:-341], primaryKey: false);
      insert('organizations', [ id: 100327, nci_institute_code: "11142", name: "Thunder Bay Regional Health Science Centre", city: "Thunder Bay", state: "ON", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -342,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11142",GROUP_DESC:"11142 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -342,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11142",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11142",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -342,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11142", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1342 ,protection_group_id: -342, protection_element_id:-342], primaryKey: false);
      insert('organizations', [ id: 100328, nci_institute_code: "11143", name: "General Hospital of Port Arthur", city: "Thunder Bay, Ont", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -343,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11143",GROUP_DESC:"11143 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -343,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11143",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11143",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -343,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11143", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1343 ,protection_group_id: -343, protection_element_id:-343], primaryKey: false);
      insert('organizations', [ id: 100329, nci_institute_code: "11144", name: "Pasqua Hospital", city: "Regina", state: "Saskatchewan", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -344,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11144",GROUP_DESC:"11144 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -344,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11144",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11144",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -344,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11144", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1344 ,protection_group_id: -344, protection_element_id:-344], primaryKey: false);
      insert('organizations', [ id: 100330, nci_institute_code: "11145", name: "University of Calgary", city: "Calgary", state: "AB", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -345,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11145",GROUP_DESC:"11145 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -345,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11145",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11145",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -345,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11145", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1345 ,protection_group_id: -345, protection_element_id:-345], primaryKey: false);
      insert('organizations', [ id: 100331, nci_institute_code: "11147", name: "Hospital Sainte-Justine", city: "Montreal", state: "Quebec", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -346,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11147",GROUP_DESC:"11147 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -346,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11147",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11147",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -346,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11147", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1346 ,protection_group_id: -346, protection_element_id:-346], primaryKey: false);
      insert('organizations', [ id: 100332, nci_institute_code: "11148", name: "Halifax Infirmary Hospital", city: "Halifax", state: "Nova Scotia", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -347,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11148",GROUP_DESC:"11148 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -347,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11148",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11148",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -347,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11148", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1347 ,protection_group_id: -347, protection_element_id:-347], primaryKey: false);
      insert('organizations', [ id: 100333, nci_institute_code: "11149", name: "Scarborough-Grace Hospital", city: "Scarborough", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -348,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11149",GROUP_DESC:"11149 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -348,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11149",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11149",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -348,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11149", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1348 ,protection_group_id: -348, protection_element_id:-348], primaryKey: false);
      insert('organizations', [ id: 100334, nci_institute_code: "11151", name: "Victoria General Hospital", city: "Winnipeg", state: "Manitoba", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -349,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11151",GROUP_DESC:"11151 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -349,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11151",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11151",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -349,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11151", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1349 ,protection_group_id: -349, protection_element_id:-349], primaryKey: false);
      insert('organizations', [ id: 100335, nci_institute_code: "11152", name: "McGill University Department of Oncology", city: "Montreal", state: "PQ", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -350,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11152",GROUP_DESC:"11152 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -350,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11152",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11152",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -350,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11152", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1350 ,protection_group_id: -350, protection_element_id:-350], primaryKey: false);
      insert('organizations', [ id: 100336, nci_institute_code: "11153", name: "Regional Cancer Program of The Hopital Regional de Sudbury Regional Hospital", city: "Sudbury", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -351,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11153",GROUP_DESC:"11153 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -351,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11153",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11153",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -351,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11153", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1351 ,protection_group_id: -351, protection_element_id:-351], primaryKey: false);
      insert('organizations', [ id: 100337, nci_institute_code: "11154", name: "Alberta Children's Hospital", city: "Calgary", state: "AB", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -352,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11154",GROUP_DESC:"11154 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -352,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11154",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11154",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -352,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11154", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1352 ,protection_group_id: -352, protection_element_id:-352], primaryKey: false);
      insert('organizations', [ id: 100338, nci_institute_code: "11156", name: "Joseph Brant Memorial Hospital", city: "Burlington", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -353,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11156",GROUP_DESC:"11156 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -353,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11156",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11156",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -353,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11156", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1353 ,protection_group_id: -353, protection_element_id:-353], primaryKey: false);
      insert('organizations', [ id: 100339, nci_institute_code: "11157", name: "Humber River Regional Hospital", city: "Weston", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -354,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11157",GROUP_DESC:"11157 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -354,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11157",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11157",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -354,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11157", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1354 ,protection_group_id: -354, protection_element_id:-354], primaryKey: false);
      insert('organizations', [ id: 100340, nci_institute_code: "11158", name: "Victoria General Hospital", city: "Victoria", state: "British Columbia", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -355,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11158",GROUP_DESC:"11158 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -355,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11158",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11158",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -355,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11158", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1355 ,protection_group_id: -355, protection_element_id:-355], primaryKey: false);
      insert('organizations', [ id: 100341, nci_institute_code: "11159", name: "Centre Hospitalier De Verdun", city: "Verdun", state: "Qubec", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -356,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11159",GROUP_DESC:"11159 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -356,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11159",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11159",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -356,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11159", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1356 ,protection_group_id: -356, protection_element_id:-356], primaryKey: false);
      insert('organizations', [ id: 100342, nci_institute_code: "11160", name: "Calgary General Hospital", city: "Calgary", state: "Alberta", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -357,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11160",GROUP_DESC:"11160 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -357,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11160",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11160",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -357,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11160", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1357 ,protection_group_id: -357, protection_element_id:-357], primaryKey: false);
      insert('organizations', [ id: 100343, nci_institute_code: "11162", name: "William Osler Health Centre, Brampton Memorial Hospital Campus", city: "Brampton", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -358,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11162",GROUP_DESC:"11162 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -358,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11162",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11162",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -358,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11162", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1358 ,protection_group_id: -358, protection_element_id:-358], primaryKey: false);
      insert('organizations', [ id: 100344, nci_institute_code: "11163", name: "Everett Chalmers Hospital", city: "Fredericton", state: "New Brunswick", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -359,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11163",GROUP_DESC:"11163 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -359,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11163",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11163",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -359,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11163", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1359 ,protection_group_id: -359, protection_element_id:-359], primaryKey: false);
      insert('organizations', [ id: 100345, nci_institute_code: "11164", name: "Baie Comeau Hospital Centre", city: "Baie Comeau", state: "Quebec", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -360,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11164",GROUP_DESC:"11164 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -360,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11164",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11164",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -360,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11164", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1360 ,protection_group_id: -360, protection_element_id:-360], primaryKey: false);
      insert('organizations', [ id: 100346, nci_institute_code: "11165", name: "Centre Hosp Reg De Lanaudiere", city: "Joliette", state: "Quebec", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -361,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11165",GROUP_DESC:"11165 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -361,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11165",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11165",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -361,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11165", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1361 ,protection_group_id: -361, protection_element_id:-361], primaryKey: false);
      insert('organizations', [ id: 100347, nci_institute_code: "11167", name: "Credit Valley Hospital", city: "Mississauga", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -362,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11167",GROUP_DESC:"11167 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -362,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11167",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11167",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -362,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11167", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1362 ,protection_group_id: -362, protection_element_id:-362], primaryKey: false);
      insert('organizations', [ id: 100348, nci_institute_code: "11168", name: "Royal University Hospital", city: "Saskatoon", state: "Saskatchewan", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -363,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11168",GROUP_DESC:"11168 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -363,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11168",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11168",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -363,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11168", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1363 ,protection_group_id: -363, protection_element_id:-363], primaryKey: false);
      insert('organizations', [ id: 100349, nci_institute_code: "11169", name: "Richmond General Hospital", city: "Richmond", state: "British Columbia", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -364,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11169",GROUP_DESC:"11169 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -364,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11169",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11169",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -364,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11169", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1364 ,protection_group_id: -364, protection_element_id:-364], primaryKey: false);
    }

    void m14() {
        // all14 (25 terms)
      insert('organizations', [ id: 100350, nci_institute_code: "11170", name: "Centre Regional de Sante et de Services Sociaux Rimouski", city: "Rimouski", state: "Quebec", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -365,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11170",GROUP_DESC:"11170 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -365,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11170",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11170",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -365,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11170", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1365 ,protection_group_id: -365, protection_element_id:-365], primaryKey: false);
      insert('organizations', [ id: 100351, nci_institute_code: "11171", name: "Childrens Hospital of Winnipeg", city: "Winnipeg", state: "Manitoba", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -366,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11171",GROUP_DESC:"11171 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -366,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11171",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11171",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -366,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11171", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1366 ,protection_group_id: -366, protection_element_id:-366], primaryKey: false);
      insert('organizations', [ id: 100352, nci_institute_code: "11173", name: "Misericordia General Hospital", city: "Winnipeg", state: "Manitoba", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -367,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11173",GROUP_DESC:"11173 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -367,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11173",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11173",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -367,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11173", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1367 ,protection_group_id: -367, protection_element_id:-367], primaryKey: false);
      insert('organizations', [ id: 100353, nci_institute_code: "11174", name: "Hopital Haut-Richelieu", city: "Saint Jean sur Richelieu", state: "Quebec", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -368,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11174",GROUP_DESC:"11174 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -368,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11174",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11174",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -368,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11174", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1368 ,protection_group_id: -368, protection_element_id:-368], primaryKey: false);
      insert('organizations', [ id: 100354, nci_institute_code: "11178", name: "Saint Joseph's Health Centre", city: "Toronto", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -369,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11178",GROUP_DESC:"11178 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -369,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11178",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11178",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -369,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11178", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1369 ,protection_group_id: -369, protection_element_id:-369], primaryKey: false);
      insert('organizations', [ id: 100355, nci_institute_code: "11179", name: "Ste-Marie Hopital", city: "Trois Rivs", state: "Quebec", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -370,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11179",GROUP_DESC:"11179 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -370,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11179",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11179",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -370,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11179", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1370 ,protection_group_id: -370, protection_element_id:-370], primaryKey: false);
      insert('organizations', [ id: 100356, nci_institute_code: "11180", name: "Medicine Hat Regional Hospital", city: "Medicine Hat", state: "Alberta", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -371,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11180",GROUP_DESC:"11180 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -371,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11180",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11180",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -371,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11180", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1371 ,protection_group_id: -371, protection_element_id:-371], primaryKey: false);
      insert('organizations', [ id: 100357, nci_institute_code: "11182", name: "The Seymour Medical Clinic", city: "Vancouver", state: "British Columbia", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -372,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11182",GROUP_DESC:"11182 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -372,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11182",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11182",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -372,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11182", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1372 ,protection_group_id: -372, protection_element_id:-372], primaryKey: false);
      insert('organizations', [ id: 100358, nci_institute_code: "11183", name: "Juravinski Cancer Centre at Hamilton Health Sciences", city: "Hamilton", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -373,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11183",GROUP_DESC:"11183 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -373,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11183",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11183",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -373,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11183", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1373 ,protection_group_id: -373, protection_element_id:-373], primaryKey: false);
      insert('organizations', [ id: 100359, nci_institute_code: "11185", name: "Centre Hospitalier De Gatineau", city: "Gatineau", state: "QC", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -374,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11185",GROUP_DESC:"11185 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -374,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11185",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11185",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -374,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11185", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1374 ,protection_group_id: -374, protection_element_id:-374], primaryKey: false);
      insert('organizations', [ id: 100360, nci_institute_code: "11186", name: "Plummer Memorial Public Hosp", city: "Sault Ste Marie", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -375,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11186",GROUP_DESC:"11186 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -375,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11186",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11186",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -375,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11186", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1375 ,protection_group_id: -375, protection_element_id:-375], primaryKey: false);
      insert('organizations', [ id: 100361, nci_institute_code: "11187", name: "Children's Hospital", city: "Winnipeg", state: "Manitoba", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -376,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11187",GROUP_DESC:"11187 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -376,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11187",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11187",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -376,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11187", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1376 ,protection_group_id: -376, protection_element_id:-376], primaryKey: false);
      insert('organizations', [ id: 100362, nci_institute_code: "11189", name: "Burnaby Hospital", city: "Burnaby", state: "British Columbia", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -377,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11189",GROUP_DESC:"11189 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -377,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11189",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11189",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -377,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11189", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1377 ,protection_group_id: -377, protection_element_id:-377], primaryKey: false);
      insert('organizations', [ id: 100363, nci_institute_code: "11190", name: "Southlake Regional Health Centre", city: "Newmarket", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -378,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11190",GROUP_DESC:"11190 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -378,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11190",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11190",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -378,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11190", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1378 ,protection_group_id: -378, protection_element_id:-378], primaryKey: false);
      insert('organizations', [ id: 100364, nci_institute_code: "11193", name: "Chum-Pavillion Notre-Dame", city: "Montreal", state: "Quebec", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -379,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11193",GROUP_DESC:"11193 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -379,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11193",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11193",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -379,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11193", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1379 ,protection_group_id: -379, protection_element_id:-379], primaryKey: false);
      insert('organizations', [ id: 100365, nci_institute_code: "11194", name: "The Scarborough Hospital -General Campus and Grace Campus", city: "Scarborough", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -380,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11194",GROUP_DESC:"11194 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -380,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11194",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11194",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -380,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11194", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1380 ,protection_group_id: -380, protection_element_id:-380], primaryKey: false);
      insert('organizations', [ id: 100366, nci_institute_code: "11195", name: "Lethbridge Cancer Clinic", city: "Lethbridge", state: "Alberta", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -381,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11195",GROUP_DESC:"11195 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -381,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11195",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11195",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -381,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11195", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1381 ,protection_group_id: -381, protection_element_id:-381], primaryKey: false);
      insert('organizations', [ id: 100367, nci_institute_code: "11196", name: "Western Memorial Regional Hospital", city: "Corner Brook", state: "New Foundland", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -382,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11196",GROUP_DESC:"11196 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -382,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11196",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11196",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -382,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11196", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1382 ,protection_group_id: -382, protection_element_id:-382], primaryKey: false);
      insert('organizations', [ id: 100368, nci_institute_code: "11197", name: "Kelowna General Hospital", city: "Kelowna", state: "British Columbia", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -383,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11197",GROUP_DESC:"11197 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -383,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11197",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11197",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -383,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11197", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1383 ,protection_group_id: -383, protection_element_id:-383], primaryKey: false);
      insert('organizations', [ id: 100369, nci_institute_code: "11199", name: "Cancer Centre for The Southern Interior", city: "Kelowna", state: "British Columbia", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -384,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11199",GROUP_DESC:"11199 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -384,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11199",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11199",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -384,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11199", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1384 ,protection_group_id: -384, protection_element_id:-384], primaryKey: false);
      insert('organizations', [ id: 100370, nci_institute_code: "11201", name: "Lakeridge Health Oshawa", city: "Oshawa", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -385,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11201",GROUP_DESC:"11201 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -385,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11201",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11201",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -385,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11201", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1385 ,protection_group_id: -385, protection_element_id:-385], primaryKey: false);
      insert('organizations', [ id: 100371, nci_institute_code: "11203", name: "Toronto East General Hospital", city: "Toronto", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -386,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11203",GROUP_DESC:"11203 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -386,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11203",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11203",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -386,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11203", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1386 ,protection_group_id: -386, protection_element_id:-386], primaryKey: false);
      insert('organizations', [ id: 100372, nci_institute_code: "11204", name: "Hamilton and District Urology Association", city: "Hamilton", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -387,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11204",GROUP_DESC:"11204 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -387,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11204",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11204",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -387,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11204", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1387 ,protection_group_id: -387, protection_element_id:-387], primaryKey: false);
      insert('organizations', [ id: 100373, nci_institute_code: "11205", name: "Capitol Health Region(Endeavour Clinical Research)", city: "Victoria", state: "BC", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -388,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11205",GROUP_DESC:"11205 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -388,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11205",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11205",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -388,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11205", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1388 ,protection_group_id: -388, protection_element_id:-388], primaryKey: false);
      insert('organizations', [ id: 100374, nci_institute_code: "11206", name: "Ville Marie Breast Centre", city: "Montreal", state: "Qubec", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -389,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11206",GROUP_DESC:"11206 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -389,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11206",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11206",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -389,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11206", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1389 ,protection_group_id: -389, protection_element_id:-389], primaryKey: false);
    }

    void m15() {
        // all15 (25 terms)
      insert('organizations', [ id: 100375, nci_institute_code: "11207", name: "University Health Network", city: "Toronto", state: "ON", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -390,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11207",GROUP_DESC:"11207 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -390,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11207",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11207",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -390,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11207", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1390 ,protection_group_id: -390, protection_element_id:-390], primaryKey: false);
      insert('organizations', [ id: 100376, nci_institute_code: "11208", name: "London Health Sciences Centre Urology", city: "London", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -391,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11208",GROUP_DESC:"11208 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -391,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11208",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11208",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -391,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11208", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1391 ,protection_group_id: -391, protection_element_id:-391], primaryKey: false);
      insert('organizations', [ id: 100377, nci_institute_code: "11209", name: "Peterborough Regional Health Centre", city: "Peterborough", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -392,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11209",GROUP_DESC:"11209 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -392,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11209",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11209",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -392,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11209", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1392 ,protection_group_id: -392, protection_element_id:-392], primaryKey: false);
      insert('organizations', [ id: 100378, nci_institute_code: "11211", name: "Toronto Urology Study Group", city: "Toronto", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -393,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11211",GROUP_DESC:"11211 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -393,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11211",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11211",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -393,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11211", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1393 ,protection_group_id: -393, protection_element_id:-393], primaryKey: false);
      insert('organizations', [ id: 100379, nci_institute_code: "11212", name: "Centre De Sante Et De Services Sociaux De Chicoutimi", city: "Chicoutimi", state: "Quebec", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -394,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11212",GROUP_DESC:"11212 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -394,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11212",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11212",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -394,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11212", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1394 ,protection_group_id: -394, protection_element_id:-394], primaryKey: false);
      insert('organizations', [ id: 100380, nci_institute_code: "11213", name: "Quinte Healthcare Corporation Belleville General", city: "Belleville", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -395,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11213",GROUP_DESC:"11213 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -395,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11213",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11213",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -395,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11213", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1395 ,protection_group_id: -395, protection_element_id:-395], primaryKey: false);
      insert('organizations', [ id: 100381, nci_institute_code: "11214", name: "Dr Georges L Dumont Hospital", city: "Moncton, Nb", state: "CA", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -396,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11214",GROUP_DESC:"11214 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -396,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11214",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11214",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -396,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11214", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1396 ,protection_group_id: -396, protection_element_id:-396], primaryKey: false);
      insert('organizations', [ id: 100382, nci_institute_code: "11215", name: "McGill University Health Centre at Royal Victoria Hospital", city: "Montreal", state: "QC", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -397,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11215",GROUP_DESC:"11215 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -397,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11215",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11215",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -397,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11215", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1397 ,protection_group_id: -397, protection_element_id:-397], primaryKey: false);
      insert('organizations', [ id: 100383, nci_institute_code: "11218", name: "Cape Breton Cancer Centre", city: "Sydney, Nova Scotia", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -398,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11218",GROUP_DESC:"11218 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -398,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11218",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11218",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -398,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11218", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1398 ,protection_group_id: -398, protection_element_id:-398], primaryKey: false);
      insert('organizations', [ id: 100384, nci_institute_code: "11219", name: "Lions Gate Hospital", city: "North  Vancouver", state: "British Columbia", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -399,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11219",GROUP_DESC:"11219 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -399,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11219",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11219",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -399,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11219", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1399 ,protection_group_id: -399, protection_element_id:-399], primaryKey: false);
      insert('organizations', [ id: 100385, nci_institute_code: "11220", name: "Regina General Hospital", city: "Regina", state: "Saskatatchewan", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -400,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11220",GROUP_DESC:"11220 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -400,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11220",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11220",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -400,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11220", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1400 ,protection_group_id: -400, protection_element_id:-400], primaryKey: false);
      insert('organizations', [ id: 100386, nci_institute_code: "11221", name: "Internal Medicine and Hematology", city: "Sudury", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -401,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11221",GROUP_DESC:"11221 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -401,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11221",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11221",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -401,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11221", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1401 ,protection_group_id: -401, protection_element_id:-401], primaryKey: false);
      insert('organizations', [ id: 100387, nci_institute_code: "11222", name: "Sault Area Hospital", city: "Sault Ste Marie", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -402,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11222",GROUP_DESC:"11222 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -402,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11222",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11222",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -402,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11222", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1402 ,protection_group_id: -402, protection_element_id:-402], primaryKey: false);
      insert('organizations', [ id: 100388, nci_institute_code: "11223", name: "Cancer Care Manitoba (CCMB)", city: "Winnipeg", state: "Manitoba", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -403,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11223",GROUP_DESC:"11223 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -403,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11223",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11223",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -403,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11223", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1403 ,protection_group_id: -403, protection_element_id:-403], primaryKey: false);
      insert('organizations', [ id: 100389, nci_institute_code: "11224", name: "Capital Region Prostate Centre", city: "Victoria", state: "BC", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -404,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11224",GROUP_DESC:"11224 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -404,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11224",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11224",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -404,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11224", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1404 ,protection_group_id: -404, protection_element_id:-404], primaryKey: false);
      insert('organizations', [ id: 100390, nci_institute_code: "11225", name: "Grace General Hospital", city: "Winnipeg", state: "MB", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -405,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11225",GROUP_DESC:"11225 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -405,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11225",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11225",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -405,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11225", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1405 ,protection_group_id: -405, protection_element_id:-405], primaryKey: false);
      insert('organizations', [ id: 100391, nci_institute_code: "11226", name: "Royal Alexandria Hospital", city: "Edmonton", state: "Alberta", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -406,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11226",GROUP_DESC:"11226 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -406,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11226",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11226",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -406,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11226", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1406 ,protection_group_id: -406, protection_element_id:-406], primaryKey: false);
      insert('organizations', [ id: 100392, nci_institute_code: "11227", name: "London Health Sciences Centre", city: "London", state: "ON", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -407,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11227",GROUP_DESC:"11227 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -407,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11227",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11227",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -407,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11227", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1407 ,protection_group_id: -407, protection_element_id:-407], primaryKey: false);
      insert('organizations', [ id: 100393, nci_institute_code: "11228", name: "Cancer Care Ontario", city: "Toronto", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -408,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11228",GROUP_DESC:"11228 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -408,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11228",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11228",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -408,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11228", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1408 ,protection_group_id: -408, protection_element_id:-408], primaryKey: false);
      insert('organizations', [ id: 100394, nci_institute_code: "11229", name: "Centre de Recherche, Clinique en Urologie", city: "Quebec City", state: "PQ", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -409,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11229",GROUP_DESC:"11229 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -409,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11229",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11229",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -409,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11229", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1409 ,protection_group_id: -409, protection_element_id:-409], primaryKey: false);
      insert('organizations', [ id: 100395, nci_institute_code: "11230", name: "Ottawa General Hospital", city: "Ottawa", state: "ON", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -410,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11230",GROUP_DESC:"11230 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -410,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11230",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11230",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -410,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11230", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1410 ,protection_group_id: -410, protection_element_id:-410], primaryKey: false);
      insert('organizations', [ id: 100396, nci_institute_code: "11231", name: "Prostate Cancer Institute", city: "Calgary", state: "AB", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -411,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11231",GROUP_DESC:"11231 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -411,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11231",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11231",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -411,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11231", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1411 ,protection_group_id: -411, protection_element_id:-411], primaryKey: false);
      insert('organizations', [ id: 100397, nci_institute_code: "11232", name: "Alberta Cancer Board", city: "Edmonton", state: "AB", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -412,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11232",GROUP_DESC:"11232 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -412,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11232",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11232",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -412,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11232", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1412 ,protection_group_id: -412, protection_element_id:-412], primaryKey: false);
      insert('organizations', [ id: 100398, nci_institute_code: "11234", name: "DMJ Research and Development", city: "Bramalea", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -413,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11234",GROUP_DESC:"11234 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -413,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11234",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11234",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -413,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11234", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1413 ,protection_group_id: -413, protection_element_id:-413], primaryKey: false);
      insert('organizations', [ id: 100399, nci_institute_code: "11235", name: "Sudbury Regional Hospital Memorial Site", city: "Sudbury", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -414,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11235",GROUP_DESC:"11235 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -414,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11235",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11235",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -414,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11235", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1414 ,protection_group_id: -414, protection_element_id:-414], primaryKey: false);
    }

    void m16() {
        // all16 (25 terms)
      insert('organizations', [ id: 100400, nci_institute_code: "11237", name: "Kells Medical Research Group Inc.", city: "Pointe Claire", state: "PQ", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -415,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11237",GROUP_DESC:"11237 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -415,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11237",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11237",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -415,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11237", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1415 ,protection_group_id: -415, protection_element_id:-415], primaryKey: false);
      insert('organizations', [ id: 100401, nci_institute_code: "11238", name: "Memorial Univ. at NFLD-Health Sciences Centre", city: "Saint John's", state: "NF", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -416,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11238",GROUP_DESC:"11238 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -416,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11238",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11238",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -416,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11238", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1416 ,protection_group_id: -416, protection_element_id:-416], primaryKey: false);
      insert('organizations', [ id: 100402, nci_institute_code: "11239", name: "Queen II Elizabeth Health and Sciences Centre", city: "Halifax", state: "NS", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -417,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11239",GROUP_DESC:"11239 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -417,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11239",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11239",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -417,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11239", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1417 ,protection_group_id: -417, protection_element_id:-417], primaryKey: false);
      insert('organizations', [ id: 100403, nci_institute_code: "11241", name: "Burlington Urology", city: "Burlington", state: "ON", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -418,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11241",GROUP_DESC:"11241 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -418,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11241",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11241",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -418,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11241", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1418 ,protection_group_id: -418, protection_element_id:-418], primaryKey: false);
      insert('organizations', [ id: 100404, nci_institute_code: "11242", name: "Hamilton Osteoporosis Diagnostic Centre", city: "Hamilton", state: "ON", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -419,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11242",GROUP_DESC:"11242 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -419,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11242",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11242",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -419,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11242", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1419 ,protection_group_id: -419, protection_element_id:-419], primaryKey: false);
      insert('organizations', [ id: 100405, nci_institute_code: "11243", name: "Hamilton Health Sciences Corporation-McMaster Site", city: "Hamilton", state: "ON", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -420,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11243",GROUP_DESC:"11243 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -420,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11243",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11243",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -420,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11243", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1420 ,protection_group_id: -420, protection_element_id:-420], primaryKey: false);
      insert('organizations', [ id: 100406, nci_institute_code: "11244", name: "Cambridge Memorial Hospital", city: "Cambridge", state: "ON", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -421,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11244",GROUP_DESC:"11244 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -421,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11244",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11244",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -421,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11244", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1421 ,protection_group_id: -421, protection_element_id:-421], primaryKey: false);
      insert('organizations', [ id: 100407, nci_institute_code: "11245", name: "Centre Hospitalier des Valles de L'Outaouais", city: "Gatineau", state: "Quebec", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -422,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11245",GROUP_DESC:"11245 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -422,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11245",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11245",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -422,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11245", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1422 ,protection_group_id: -422, protection_element_id:-422], primaryKey: false);
      insert('organizations', [ id: 100408, nci_institute_code: "11246", name: "Ottawa General Hospital, Civic Campus", city: "Ottawa", state: "ON", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -423,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11246",GROUP_DESC:"11246 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -423,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11246",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11246",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -423,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11246", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1423 ,protection_group_id: -423, protection_element_id:-423], primaryKey: false);
      insert('organizations', [ id: 100409, nci_institute_code: "11247", name: "Grand River Regional Cancer Centre at Grand River Hospital", city: "Kitchener", state: "ON", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -424,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11247",GROUP_DESC:"11247 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -424,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11247",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11247",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -424,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11247", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1424 ,protection_group_id: -424, protection_element_id:-424], primaryKey: false);
      insert('organizations', [ id: 100410, nci_institute_code: "11248", name: "Manitoba Clinic", city: "Winnipeg", state: "MB", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -425,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11248",GROUP_DESC:"11248 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -425,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11248",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11248",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -425,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11248", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1425 ,protection_group_id: -425, protection_element_id:-425], primaryKey: false);
      insert('organizations', [ id: 100411, nci_institute_code: "11249", name: "Saskatoon Osteoporosis Centre", city: "Saskatoon", state: "SK", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -426,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11249",GROUP_DESC:"11249 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -426,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11249",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11249",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -426,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11249", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1426 ,protection_group_id: -426, protection_element_id:-426], primaryKey: false);
      insert('organizations', [ id: 100412, nci_institute_code: "11250", name: "Saint Vincent's Hospital", city: "Vancouver", state: "BC", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -427,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11250",GROUP_DESC:"11250 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -427,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11250",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11250",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -427,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11250", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1427 ,protection_group_id: -427, protection_element_id:-427], primaryKey: false);
      insert('organizations', [ id: 100413, nci_institute_code: "11251", name: "Ottawa Women's Breast Health Centre - Ottawa Hospital", city: "Ottawa", state: "ON", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -428,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11251",GROUP_DESC:"11251 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -428,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11251",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11251",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -428,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11251", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1428 ,protection_group_id: -428, protection_element_id:-428], primaryKey: false);
      insert('organizations', [ id: 100414, nci_institute_code: "11253", name: "Saint Joseph's Health Centre", city: "London, Ont", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -429,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11253",GROUP_DESC:"11253 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -429,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11253",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11253",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -429,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11253", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1429 ,protection_group_id: -429, protection_element_id:-429], primaryKey: false);
      insert('organizations', [ id: 100415, nci_institute_code: "11254", name: "Health Sciences Centre-University of Calgary", city: "Calgary", state: "AB", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -430,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11254",GROUP_DESC:"11254 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -430,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11254",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11254",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -430,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11254", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1430 ,protection_group_id: -430, protection_element_id:-430], primaryKey: false);
      insert('organizations', [ id: 100416, nci_institute_code: "11255", name: "The Breast Centre", city: "Edmonton", state: "Alberta", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -431,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11255",GROUP_DESC:"11255 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -431,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11255",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11255",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -431,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11255", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1431 ,protection_group_id: -431, protection_element_id:-431], primaryKey: false);
      insert('organizations', [ id: 100417, nci_institute_code: "11256", name: "Familial Oncology Program", city: "Kingston, Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -432,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11256",GROUP_DESC:"11256 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -432,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11256",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11256",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -432,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11256", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1432 ,protection_group_id: -432, protection_element_id:-432], primaryKey: false);
      insert('organizations', [ id: 100418, nci_institute_code: "11257", name: "British Columbia Research Institute for Children's & Women's Health Centre", city: "Vancouver", state: "BC", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -433,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11257",GROUP_DESC:"11257 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -433,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11257",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11257",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -433,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11257", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1433 ,protection_group_id: -433, protection_element_id:-433], primaryKey: false);
      insert('organizations', [ id: 100419, nci_institute_code: "11258", name: "Medical Oncology & Internal Medicine", city: "Brampton", state: "ON", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -434,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11258",GROUP_DESC:"11258 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -434,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11258",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11258",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -434,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11258", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1434 ,protection_group_id: -434, protection_element_id:-434], primaryKey: false);
      insert('organizations', [ id: 100420, nci_institute_code: "11259", name: "The Urology Clinic", city: "St. Catharines", state: "ON", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -435,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11259",GROUP_DESC:"11259 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -435,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11259",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11259",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -435,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11259", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1435 ,protection_group_id: -435, protection_element_id:-435], primaryKey: false);
      insert('organizations', [ id: 100421, nci_institute_code: "11260", name: "Urology Resource Centre", city: "Burlington", state: "ON", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -436,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11260",GROUP_DESC:"11260 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -436,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11260",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11260",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -436,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11260", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1436 ,protection_group_id: -436, protection_element_id:-436], primaryKey: false);
      insert('organizations', [ id: 100422, nci_institute_code: "11261", name: "Medical Advisory Secretariat", city: "Toronto", state: "ON", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -437,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11261",GROUP_DESC:"11261 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -437,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11261",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11261",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -437,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11261", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1437 ,protection_group_id: -437, protection_element_id:-437], primaryKey: false);
      insert('organizations', [ id: 100423, nci_institute_code: "11262", name: "Peter Lougheed Centre", city: "Calgary", state: "AB", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -438,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11262",GROUP_DESC:"11262 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -438,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11262",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11262",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -438,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11262", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1438 ,protection_group_id: -438, protection_element_id:-438], primaryKey: false);
      insert('organizations', [ id: 100424, nci_institute_code: "11263", name: "Vancouver General Hospital-Heather Pavilion", city: "Vancouver", state: "BC", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -439,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11263",GROUP_DESC:"11263 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -439,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11263",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11263",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -439,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11263", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1439 ,protection_group_id: -439, protection_element_id:-439], primaryKey: false);
    }

    void m17() {
        // all17 (25 terms)
      insert('organizations', [ id: 100425, nci_institute_code: "11265", name: "Kriger Research Centre", city: "Toronto", state: "ON", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -440,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11265",GROUP_DESC:"11265 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -440,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11265",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11265",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -440,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11265", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1440 ,protection_group_id: -440, protection_element_id:-440], primaryKey: false);
      insert('organizations', [ id: 100426, nci_institute_code: "11266", name: "QEII Health Sciences Centre - Centre for Clinical Research", city: "Halifax", state: "NS", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -441,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11266",GROUP_DESC:"11266 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -441,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11266",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11266",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -441,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11266", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1441 ,protection_group_id: -441, protection_element_id:-441], primaryKey: false);
      insert('organizations', [ id: 100427, nci_institute_code: "11267", name: "Toronto Western Hospital", city: "Toronto", state: "ON", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -442,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11267",GROUP_DESC:"11267 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -442,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11267",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11267",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -442,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11267", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1442 ,protection_group_id: -442, protection_element_id:-442], primaryKey: false);
      insert('organizations', [ id: 100428, nci_institute_code: "11268", name: "Grey Bruce Health Services", city: "Owen Sound", state: "ON", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -443,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11268",GROUP_DESC:"11268 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -443,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11268",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11268",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -443,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11268", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1443 ,protection_group_id: -443, protection_element_id:-443], primaryKey: false);
      insert('organizations', [ id: 100429, nci_institute_code: "11270", name: "University of Western Ontario", city: "London", state: "ON", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -444,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11270",GROUP_DESC:"11270 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -444,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11270",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11270",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -444,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11270", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1444 ,protection_group_id: -444, protection_element_id:-444], primaryKey: false);
      insert('organizations', [ id: 100430, nci_institute_code: "11271", name: "Robarts Research Institute", city: "London", state: "Ontario", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -445,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11271",GROUP_DESC:"11271 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -445,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11271",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11271",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -445,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11271", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1445 ,protection_group_id: -445, protection_element_id:-445], primaryKey: false);
      insert('organizations', [ id: 100431, nci_institute_code: "11272", name: "Hamilton Health Sciences - Henderson Site", city: "Hamilton", state: "ON", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -446,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11272",GROUP_DESC:"11272 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -446,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11272",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11272",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -446,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11272", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1446 ,protection_group_id: -446, protection_element_id:-446], primaryKey: false);
      insert('organizations', [ id: 100432, nci_institute_code: "11273", name: "Assiniboine Clinic", city: "Winnipeg", state: "MB", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -447,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11273",GROUP_DESC:"11273 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -447,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11273",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11273",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -447,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11273", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1447 ,protection_group_id: -447, protection_element_id:-447], primaryKey: false);
      insert('organizations', [ id: 100433, nci_institute_code: "11275", name: "University of Ottawa", city: "Ottawa", state: "ON", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -448,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11275",GROUP_DESC:"11275 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -448,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11275",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11275",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -448,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11275", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1448 ,protection_group_id: -448, protection_element_id:-448], primaryKey: false);
      insert('organizations', [ id: 100434, nci_institute_code: "11276", name: "Stollery Children's Hospital", city: "Edmonton", state: "AB", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -449,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11276",GROUP_DESC:"11276 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -449,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11276",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11276",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -449,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11276", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1449 ,protection_group_id: -449, protection_element_id:-449], primaryKey: false);
      insert('organizations', [ id: 100435, nci_institute_code: "11277", name: "Niagara Health System - Saint Catharines", city: "Saint Catharines", state: "ON", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -450,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11277",GROUP_DESC:"11277 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -450,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11277",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11277",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -450,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11277", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1450 ,protection_group_id: -450, protection_element_id:-450], primaryKey: false);
      insert('organizations', [ id: 100436, nci_institute_code: "11278", name: "Thunder Bay Medical Centre", city: "Thunder Bay", state: "ON", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -451,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11278",GROUP_DESC:"11278 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -451,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11278",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11278",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -451,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11278", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1451 ,protection_group_id: -451, protection_element_id:-451], primaryKey: false);
      insert('organizations', [ id: 100437, nci_institute_code: "11279", name: "York University", city: "Toronto", state: "ON", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -452,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11279",GROUP_DESC:"11279 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -452,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11279",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11279",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -452,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11279", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1452 ,protection_group_id: -452, protection_element_id:-452], primaryKey: false);
      insert('organizations', [ id: 100438, nci_institute_code: "11280", name: "BC Cancer Research Centre", city: "Vancouver", state: "BC", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -453,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11280",GROUP_DESC:"11280 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -453,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11280",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11280",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -453,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11280", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1453 ,protection_group_id: -453, protection_element_id:-453], primaryKey: false);
      insert('organizations', [ id: 100439, nci_institute_code: "11281", name: "Hotel Dieu Hospital Kingston", city: "Kingston", state: "ON", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -454,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11281",GROUP_DESC:"11281 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -454,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11281",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11281",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -454,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11281", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1454 ,protection_group_id: -454, protection_element_id:-454], primaryKey: false);
      insert('organizations', [ id: 100440, nci_institute_code: "11283", name: "Thoracic Surgery", city: "Sudbury", state: "ON", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -455,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11283",GROUP_DESC:"11283 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -455,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11283",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11283",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -455,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11283", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1455 ,protection_group_id: -455, protection_element_id:-455], primaryKey: false);
      insert('organizations', [ id: 100441, nci_institute_code: "11284", name: "Centre de Recherche Clinique et Evaluative en Oncologie", city: "Quebec", state: "QC", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -456,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11284",GROUP_DESC:"11284 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -456,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11284",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11284",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -456,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11284", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1456 ,protection_group_id: -456, protection_element_id:-456], primaryKey: false);
      insert('organizations', [ id: 100442, nci_institute_code: "11285", name: "University of British Columbia Hospital", city: "Vancouver", state: "BC", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -457,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11285",GROUP_DESC:"11285 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -457,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11285",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11285",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -457,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11285", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1457 ,protection_group_id: -457, protection_element_id:-457], primaryKey: false);
      insert('organizations', [ id: 100443, nci_institute_code: "11286", name: "Vancouver Island Health Authority", city: "Victoria", state: "BC", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -458,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11286",GROUP_DESC:"11286 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -458,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11286",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11286",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -458,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11286", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1458 ,protection_group_id: -458, protection_element_id:-458], primaryKey: false);
      insert('organizations', [ id: 100444, nci_institute_code: "11287", name: "Bastian Brydon and Thial PC", city: "Regina", state: "SK", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -459,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11287",GROUP_DESC:"11287 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -459,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11287",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11287",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -459,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11287", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1459 ,protection_group_id: -459, protection_element_id:-459], primaryKey: false);
      insert('organizations', [ id: 100445, nci_institute_code: "11288", name: "Saint Jerome Medical Research Inc", city: "Saint Jerome", state: "QC", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -460,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11288",GROUP_DESC:"11288 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -460,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11288",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11288",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -460,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11288", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1460 ,protection_group_id: -460, protection_element_id:-460], primaryKey: false);
      insert('organizations', [ id: 100446, nci_institute_code: "11289", name: "Down Nancy King MD (Office)", city: "North York", state: "ON", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -461,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11289",GROUP_DESC:"11289 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -461,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11289",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11289",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -461,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11289", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1461 ,protection_group_id: -461, protection_element_id:-461], primaryKey: false);
      insert('organizations', [ id: 100447, nci_institute_code: "11290", name: "Medicine Hat Cancer Centre", city: "Medicine Hat", state: "AB", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -462,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11290",GROUP_DESC:"11290 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -462,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11290",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11290",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -462,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11290", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1462 ,protection_group_id: -462, protection_element_id:-462], primaryKey: false);
      insert('organizations', [ id: 100448, nci_institute_code: "11291", name: "Gordon and Leslie Diamond Health Care Centre", city: "Vancouver", state: "BC", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -463,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11291",GROUP_DESC:"11291 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -463,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11291",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11291",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -463,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11291", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1463 ,protection_group_id: -463, protection_element_id:-463], primaryKey: false);
      insert('organizations', [ id: 100449, nci_institute_code: "11292", name: "CancerCare Manitoba - Saint Boniface Unit", city: "Winnipeg", state: "MB", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -464,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11292",GROUP_DESC:"11292 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -464,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11292",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11292",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -464,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11292", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1464 ,protection_group_id: -464, protection_element_id:-464], primaryKey: false);
    }

    void m18() {
        // all18 (25 terms)
      insert('organizations', [ id: 100450, nci_institute_code: "11293", name: "Montreal Neurological Institute and Hospital", city: "Montreal", state: "QC", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -465,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11293",GROUP_DESC:"11293 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -465,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11293",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11293",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -465,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11293", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1465 ,protection_group_id: -465, protection_element_id:-465], primaryKey: false);
      insert('organizations', [ id: 100451, nci_institute_code: "11294", name: "Meadowlands Family Health Centre", city: "Ottawa", state: "ON", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -466,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11294",GROUP_DESC:"11294 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -466,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11294",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11294",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -466,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11294", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1466 ,protection_group_id: -466, protection_element_id:-466], primaryKey: false);
      insert('organizations', [ id: 100452, nci_institute_code: "13001", name: "Maharagana Cancer Institute", city: "Golombo", country: "Sri Lanka"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -467,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.13001",GROUP_DESC:"13001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -467,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.13001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.13001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -467,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.13001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1467 ,protection_group_id: -467, protection_element_id:-467], primaryKey: false);
      insert('organizations', [ id: 100453, nci_institute_code: "14001", name: "Universidad Catholica De Chile", city: "Santiago", country: "Chile"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -468,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.14001",GROUP_DESC:"14001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -468,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.14001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.14001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -468,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.14001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1468 ,protection_group_id: -468, protection_element_id:-468], primaryKey: false);
      insert('organizations', [ id: 100454, nci_institute_code: "14002", name: "Louis Calvo Mackenna Hospital", city: "Santiago", country: "Chile"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -469,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.14002",GROUP_DESC:"14002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -469,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.14002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.14002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -469,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.14002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1469 ,protection_group_id: -469, protection_element_id:-469], primaryKey: false);
      insert('organizations', [ id: 100455, nci_institute_code: "14003", name: "C Mackenna Hospital University of Chile", city: "Santiago, 9", country: "Chile"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -470,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.14003",GROUP_DESC:"14003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -470,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.14003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.14003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -470,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.14003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1470 ,protection_group_id: -470, protection_element_id:-470], primaryKey: false);
      insert('organizations', [ id: 100456, nci_institute_code: "14004", name: "Clinica Alemana", city: "Santiago", country: "Chile"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -471,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.14004",GROUP_DESC:"14004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -471,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.14004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.14004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -471,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.14004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1471 ,protection_group_id: -471, protection_element_id:-471], primaryKey: false);
      insert('organizations', [ id: 100457, nci_institute_code: "14005", name: "Fundacion Arturo Lopez Perez", city: "Santiago", country: "Chile"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -472,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.14005",GROUP_DESC:"14005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -472,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.14005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.14005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -472,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.14005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1472 ,protection_group_id: -472, protection_element_id:-472], primaryKey: false);
      insert('organizations', [ id: 100458, nci_institute_code: "15001", name: "Jackson Memorial Hospital", city: "Bogota", state: "COLUMBIA", country: "Colombia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -473,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.15001",GROUP_DESC:"15001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -473,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.15001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.15001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -473,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.15001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1473 ,protection_group_id: -473, protection_element_id:-473], primaryKey: false);
      insert('organizations', [ id: 100459, nci_institute_code: "15002", name: "Inst Nacional De Cancerologia", city: "Bogota, D.E.", state: "COLUMBIA", country: "Colombia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -474,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.15002",GROUP_DESC:"15002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -474,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.15002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.15002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -474,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.15002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1474 ,protection_group_id: -474, protection_element_id:-474], primaryKey: false);
      insert('organizations', [ id: 100460, nci_institute_code: "15003", name: "Central Military Hosp.", city: "Bogota", state: "COLUMBIA", country: "Colombia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -475,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.15003",GROUP_DESC:"15003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -475,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.15003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.15003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -475,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.15003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1475 ,protection_group_id: -475, protection_element_id:-475], primaryKey: false);
      insert('organizations', [ id: 100461, nci_institute_code: "15004", name: "Universidad De Antioquia", city: "Medellin", state: "COLUMBIA", country: "Colombia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -476,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.15004",GROUP_DESC:"15004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -476,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.15004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.15004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -476,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.15004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1476 ,protection_group_id: -476, protection_element_id:-476], primaryKey: false);
      insert('organizations', [ id: 100462, nci_institute_code: "15005", name: "Ministerio De Salud Publica", city: "Bogota, D. E.", state: "COLUMBIA", country: "Colombia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -477,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.15005",GROUP_DESC:"15005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -477,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.15005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.15005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -477,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.15005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1477 ,protection_group_id: -477, protection_element_id:-477], primaryKey: false);
      insert('organizations', [ id: 100463, nci_institute_code: "15006", name: "Central Medico De Los Andes", city: "Bogota", state: "COLUMBIA", country: "Colombia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -478,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.15006",GROUP_DESC:"15006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -478,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.15006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.15006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -478,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.15006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1478 ,protection_group_id: -478, protection_element_id:-478], primaryKey: false);
      insert('organizations', [ id: 100464, nci_institute_code: "15007", name: "Hospital San Juan De Dios", city: "Bogota", state: "COLUMBIA", country: "Colombia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -479,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.15007",GROUP_DESC:"15007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -479,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.15007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.15007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -479,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.15007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1479 ,protection_group_id: -479, protection_element_id:-479], primaryKey: false);
      insert('organizations', [ id: 100465, nci_institute_code: "16001", name: "Hospital Mexico", city: "San Jose, La Uruca", country: "Costa Rica"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -480,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.16001",GROUP_DESC:"16001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -480,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.16001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.16001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -480,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.16001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1480 ,protection_group_id: -480, protection_element_id:-480], primaryKey: false);
      insert('organizations', [ id: 100466, nci_institute_code: "16002", name: "Hospital Nacional De Ninos", city: "San Jose", country: "Costa Rica"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -481,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.16002",GROUP_DESC:"16002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -481,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.16002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.16002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -481,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.16002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1481 ,protection_group_id: -481, protection_element_id:-481], primaryKey: false);
      insert('organizations', [ id: 100467, nci_institute_code: "16003", name: "Ofc Sanitaria Pan Americana", city: "San Jose", country: "Costa Rica"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -482,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.16003",GROUP_DESC:"16003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -482,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.16003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.16003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -482,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.16003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1482 ,protection_group_id: -482, protection_element_id:-482], primaryKey: false);
      insert('organizations', [ id: 100468, nci_institute_code: "17001", name: "Pan American Union", city: "Havana", country: "Cuba"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -483,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.17001",GROUP_DESC:"17001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -483,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.17001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.17001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -483,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.17001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1483 ,protection_group_id: -483, protection_element_id:-483], primaryKey: false);
      insert('organizations', [ id: 100469, nci_institute_code: "18001", name: "Motol Hospital", city: "Prague 5", state: "CZECH.", country: "Czech Republic"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -484,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.18001",GROUP_DESC:"18001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -484,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.18001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.18001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -484,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.18001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1484 ,protection_group_id: -484, protection_element_id:-484], primaryKey: false);
      insert('organizations', [ id: 100470, nci_institute_code: "18002", name: "Charles University Hospital", city: "U Nemocnice", state: "CZECH.", country: "Czech Republic"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -485,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.18002",GROUP_DESC:"18002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -485,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.18002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.18002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -485,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.18002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1485 ,protection_group_id: -485, protection_element_id:-485], primaryKey: false);
      insert('organizations', [ id: 100471, nci_institute_code: "18003", name: "Comenius Univ.", city: "Bratislava", state: "CZECH.", country: "Czech Republic"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -486,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.18003",GROUP_DESC:"18003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -486,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.18003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.18003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -486,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.18003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1486 ,protection_group_id: -486, protection_element_id:-486], primaryKey: false);
      insert('organizations', [ id: 100472, nci_institute_code: "18004", name: "National Cancer Institute - Slovakia Republic ", city: "Bratislava", country: "Slovakia (Slovak Rep)"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -487,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.18004",GROUP_DESC:"18004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -487,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.18004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.18004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -487,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.18004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1487 ,protection_group_id: -487, protection_element_id:-487], primaryKey: false);
      insert('organizations', [ id: 100473, nci_institute_code: "19001", name: "Herlev Hosp.", city: "Herlev", country: "Denmark"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -488,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.19001",GROUP_DESC:"19001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -488,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.19001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.19001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -488,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.19001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1488 ,protection_group_id: -488, protection_element_id:-488], primaryKey: false);
      insert('organizations', [ id: 100474, nci_institute_code: "19002", name: "Finsen Institute", city: "Dk-2100  Copenhagen", country: "Denmark"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -489,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.19002",GROUP_DESC:"19002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -489,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.19002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.19002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -489,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.19002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1489 ,protection_group_id: -489, protection_element_id:-489], primaryKey: false);
    }

    void m19() {
        // all19 (25 terms)
      insert('organizations', [ id: 100475, nci_institute_code: "19003", name: "Aarhus University Hospital", city: "DK 8200 Arhus N", country: "Denmark"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -490,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.19003",GROUP_DESC:"19003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -490,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.19003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.19003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -490,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.19003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1490 ,protection_group_id: -490, protection_element_id:-490], primaryKey: false);
      insert('organizations', [ id: 100476, nci_institute_code: "19004", name: "Copenhagen University Hospital", city: "Copenhagen 2100", country: "Denmark"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -491,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.19004",GROUP_DESC:"19004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -491,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.19004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.19004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -491,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.19004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1491 ,protection_group_id: -491, protection_element_id:-491], primaryKey: false);
      insert('organizations', [ id: 100477, nci_institute_code: "19005", name: "Odense Hospital", city: "Odense 5000", country: "Denmark"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -492,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.19005",GROUP_DESC:"19005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -492,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.19005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.19005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -492,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.19005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1492 ,protection_group_id: -492, protection_element_id:-492], primaryKey: false);
      insert('organizations', [ id: 100478, nci_institute_code: "19006", name: "Rigshospitalet University Hospital", city: "Copenhagen", country: "Denmark"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -493,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.19006",GROUP_DESC:"19006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -493,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.19006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.19006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -493,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.19006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1493 ,protection_group_id: -493, protection_element_id:-493], primaryKey: false);
      insert('organizations', [ id: 100479, nci_institute_code: "19007", name: "Bispebjerg Hospital", city: "Copenhagen", country: "Denmark"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -494,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.19007",GROUP_DESC:"19007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -494,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.19007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.19007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -494,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.19007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1494 ,protection_group_id: -494, protection_element_id:-494], primaryKey: false);
      insert('organizations', [ id: 100480, nci_institute_code: "19008", name: "Aalborg University Hospital", city: "Aalborg", country: "Denmark"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -495,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.19008",GROUP_DESC:"19008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -495,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.19008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.19008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -495,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.19008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1495 ,protection_group_id: -495, protection_element_id:-495], primaryKey: false);
      insert('organizations', [ id: 100481, nci_institute_code: "19009", name: "Vejle Hospital", city: "7100 Vejle", country: "Denmark"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -496,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.19009",GROUP_DESC:"19009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -496,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.19009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.19009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -496,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.19009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1496 ,protection_group_id: -496, protection_element_id:-496], primaryKey: false);
      insert('organizations', [ id: 100482, nci_institute_code: "21002", name: "East German Code, See German Code 76059", country: "Ethiopia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -497,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.21002",GROUP_DESC:"21002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -497,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.21002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.21002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -497,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.21002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1497 ,protection_group_id: -497, protection_element_id:-497], primaryKey: false);
      insert('organizations', [ id: 100483, nci_institute_code: "22001", name: "Univ Federale Du Cameroon", state: "CAMEROON", country: "Cameroon"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -498,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.22001",GROUP_DESC:"22001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -498,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.22001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.22001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -498,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.22001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1498 ,protection_group_id: -498, protection_element_id:-498], primaryKey: false);
      insert('organizations', [ id: 100484, nci_institute_code: "23001", name: "Hospital Regional Iess", city: "Guayaquil", country: "Ecuador"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -499,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.23001",GROUP_DESC:"23001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -499,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.23001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.23001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -499,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.23001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1499 ,protection_group_id: -499, protection_element_id:-499], primaryKey: false);
      insert('organizations', [ id: 100485, nci_institute_code: "24001", name: "Hospital La Policlinica", city: "Comayaguela Mdc", country: "Honduras"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -500,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.24001",GROUP_DESC:"24001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -500,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.24001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.24001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -500,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.24001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1500 ,protection_group_id: -500, protection_element_id:-500], primaryKey: false);
      insert('organizations', [ id: 100486, nci_institute_code: "25001", name: "Westminster Hospital", city: "London, S.W.1", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -501,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25001",GROUP_DESC:"25001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -501,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -501,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1501 ,protection_group_id: -501, protection_element_id:-501], primaryKey: false);
      insert('organizations', [ id: 100487, nci_institute_code: "25002", name: "Royal Berkshire Hospital", city: "Reading", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -502,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25002",GROUP_DESC:"25002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -502,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -502,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1502 ,protection_group_id: -502, protection_element_id:-502], primaryKey: false);
      insert('organizations', [ id: 100488, nci_institute_code: "25003", name: "University of Surrey", city: "Guildford, Surrey", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -503,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25003",GROUP_DESC:"25003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -503,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -503,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1503 ,protection_group_id: -503, protection_element_id:-503], primaryKey: false);
      insert('organizations', [ id: 100489, nci_institute_code: "25004", name: "Royal Brompton Hospital", city: "London", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -504,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25004",GROUP_DESC:"25004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -504,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -504,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1504 ,protection_group_id: -504, protection_element_id:-504], primaryKey: false);
      insert('organizations', [ id: 100490, nci_institute_code: "25005", name: "Guys Hospital Medical School", city: "London, S.E.I.", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -505,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25005",GROUP_DESC:"25005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -505,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -505,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1505 ,protection_group_id: -505, protection_element_id:-505], primaryKey: false);
      insert('organizations', [ id: 100491, nci_institute_code: "25006", name: "Royal Infirmary", city: "Manchester M13 9wl", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -506,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25006",GROUP_DESC:"25006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -506,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -506,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1506 ,protection_group_id: -506, protection_element_id:-506], primaryKey: false);
      insert('organizations', [ id: 100492, nci_institute_code: "25007", name: "Royal Manchester Children Hospital", city: "Manchester M27.1ha", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -507,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25007",GROUP_DESC:"25007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -507,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -507,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1507 ,protection_group_id: -507, protection_element_id:-507], primaryKey: false);
      insert('organizations', [ id: 100493, nci_institute_code: "25008", name: "Norfolk and Norwich Hospital", city: "Norwich, Norfolk", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -508,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25008",GROUP_DESC:"25008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -508,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -508,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1508 ,protection_group_id: -508, protection_element_id:-508], primaryKey: false);
      insert('organizations', [ id: 100494, nci_institute_code: "25009", name: "King's College Hospital", city: "London", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -509,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25009",GROUP_DESC:"25009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -509,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -509,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1509 ,protection_group_id: -509, protection_element_id:-509], primaryKey: false);
      insert('organizations', [ id: 100495, nci_institute_code: "25010", name: "University of Leeds", city: "Leeds", state: "England", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -510,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25010",GROUP_DESC:"25010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -510,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -510,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1510 ,protection_group_id: -510, protection_element_id:-510], primaryKey: false);
      insert('organizations', [ id: 100496, nci_institute_code: "25011", name: "Saint James University Hospital", city: "West Yorkshire", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -511,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25011",GROUP_DESC:"25011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -511,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -511,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1511 ,protection_group_id: -511, protection_element_id:-511], primaryKey: false);
      insert('organizations', [ id: 100497, nci_institute_code: "25012", name: "Clatterbridge Hospital West", city: "Cheshire", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -512,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25012",GROUP_DESC:"25012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -512,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -512,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1512 ,protection_group_id: -512, protection_element_id:-512], primaryKey: false);
      insert('organizations', [ id: 100498, nci_institute_code: "25013", name: "Selley Oak Hospital", city: "Birmingham", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -513,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25013",GROUP_DESC:"25013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -513,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -513,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1513 ,protection_group_id: -513, protection_element_id:-513], primaryKey: false);
      insert('organizations', [ id: 100499, nci_institute_code: "25015", name: "Hospital for Sick Children", city: "London Wc1n, 3jh", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -514,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25015",GROUP_DESC:"25015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -514,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -514,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1514 ,protection_group_id: -514, protection_element_id:-514], primaryKey: false);
    }

    void m20() {
        // all20 (25 terms)
      insert('organizations', [ id: 100500, nci_institute_code: "25017", name: "Royal Postgraduate Medical School", city: "London W12 Ohs", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -515,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25017",GROUP_DESC:"25017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -515,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -515,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1515 ,protection_group_id: -515, protection_element_id:-515], primaryKey: false);
      insert('organizations', [ id: 100501, nci_institute_code: "25018", name: "Addenbrookes Hospital/ Medical School", city: "Cambridge", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -516,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25018",GROUP_DESC:"25018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -516,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -516,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1516 ,protection_group_id: -516, protection_element_id:-516], primaryKey: false);
      insert('organizations', [ id: 100502, nci_institute_code: "25019", name: "Middlesex Hospital Medical School", city: "London, Win 8aa", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -517,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25019",GROUP_DESC:"25019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -517,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -517,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1517 ,protection_group_id: -517, protection_element_id:-517], primaryKey: false);
      insert('organizations', [ id: 100503, nci_institute_code: "25020", name: "Churchill Hospital", city: "Oxford", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -518,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25020",GROUP_DESC:"25020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -518,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -518,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1518 ,protection_group_id: -518, protection_element_id:-518], primaryKey: false);
      insert('organizations', [ id: 100504, nci_institute_code: "25022", name: "National Hospital", city: "London, Wc1n 3bg", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -519,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25022",GROUP_DESC:"25022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -519,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -519,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1519 ,protection_group_id: -519, protection_element_id:-519], primaryKey: false);
      insert('organizations', [ id: 100505, nci_institute_code: "25023", name: "Royal Free Hospital", city: "London Sw3 6jb", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -520,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25023",GROUP_DESC:"25023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -520,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -520,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1520 ,protection_group_id: -520, protection_element_id:-520], primaryKey: false);
      insert('organizations', [ id: 100506, nci_institute_code: "25025", name: "Saint Lukes Hospital", city: "Guildford, Surrey", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -521,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25025",GROUP_DESC:"25025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -521,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -521,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1521 ,protection_group_id: -521, protection_element_id:-521], primaryKey: false);
      insert('organizations', [ id: 100507, nci_institute_code: "25026", name: "Torbay Hospital", city: "Torquay Devon Tq27aa", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -522,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25026",GROUP_DESC:"25026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -522,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -522,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1522 ,protection_group_id: -522, protection_element_id:-522], primaryKey: false);
      insert('organizations', [ id: 100508, nci_institute_code: "25027", name: "Northwick Park Hospital", city: "Harrow, Ha1 3uj", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -523,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25027",GROUP_DESC:"25027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -523,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -523,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1523 ,protection_group_id: -523, protection_element_id:-523], primaryKey: false);
      insert('organizations', [ id: 100509, nci_institute_code: "25029", name: "Queen Elizabeth Hospital.", city: "Birmingham, B15 2th", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -524,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25029",GROUP_DESC:"25029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -524,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -524,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1524 ,protection_group_id: -524, protection_element_id:-524], primaryKey: false);
      insert('organizations', [ id: 100510, nci_institute_code: "25030", name: "University of Leeds Saint James Hospital", city: "Leeds", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -525,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25030",GROUP_DESC:"25030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -525,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -525,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1525 ,protection_group_id: -525, protection_element_id:-525], primaryKey: false);
      insert('organizations', [ id: 100511, nci_institute_code: "25031", name: "Royal Sussex County Hospital", city: "Brighton, Bn2 5be", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -526,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25031",GROUP_DESC:"25031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -526,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -526,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1526 ,protection_group_id: -526, protection_element_id:-526], primaryKey: false);
      insert('organizations', [ id: 100512, nci_institute_code: "25032", name: "Nevill Hall Hospital", city: "Abergavenny", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -527,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25032",GROUP_DESC:"25032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -527,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -527,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1527 ,protection_group_id: -527, protection_element_id:-527], primaryKey: false);
      insert('organizations', [ id: 100513, nci_institute_code: "25033", name: "Royal Devon and Exeter Hospital", city: "Exter, Ex2 5dw", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -528,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25033",GROUP_DESC:"25033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -528,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -528,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1528 ,protection_group_id: -528, protection_element_id:-528], primaryKey: false);
      insert('organizations', [ id: 100514, nci_institute_code: "25034", name: "Saint Richards Hospital", city: "Chichester", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -529,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25034",GROUP_DESC:"25034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -529,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -529,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1529 ,protection_group_id: -529, protection_element_id:-529], primaryKey: false);
      insert('organizations', [ id: 100515, nci_institute_code: "25036", name: "The Royal Marsden NHS Foundation Trust", city: "London", state: "England", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -530,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25036",GROUP_DESC:"25036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -530,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -530,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1530 ,protection_group_id: -530, protection_element_id:-530], primaryKey: false);
      insert('organizations', [ id: 100516, nci_institute_code: "25037", name: "Liverpool Royal Infirmary", city: "Liverpool L3 5pu", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -531,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25037",GROUP_DESC:"25037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -531,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -531,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1531 ,protection_group_id: -531, protection_element_id:-531], primaryKey: false);
      insert('organizations', [ id: 100517, nci_institute_code: "25038", name: "Peterborough District Hospital", city: "Peterborough", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -532,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25038",GROUP_DESC:"25038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -532,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -532,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1532 ,protection_group_id: -532, protection_element_id:-532], primaryKey: false);
      insert('organizations', [ id: 100518, nci_institute_code: "25039", name: "North Ormesby Hospital", city: "North Ormesby", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -533,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25039",GROUP_DESC:"25039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -533,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -533,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1533 ,protection_group_id: -533, protection_element_id:-533], primaryKey: false);
      insert('organizations', [ id: 100519, nci_institute_code: "25041", name: "Saint Marys Hospital", city: "London", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -534,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25041",GROUP_DESC:"25041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -534,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -534,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1534 ,protection_group_id: -534, protection_element_id:-534], primaryKey: false);
      insert('organizations', [ id: 100520, nci_institute_code: "25042", name: "W. Norfolk Kings Lyenn General Hospital", city: "Norfolk", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -535,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25042",GROUP_DESC:"25042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -535,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -535,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1535 ,protection_group_id: -535, protection_element_id:-535], primaryKey: false);
      insert('organizations', [ id: 100521, nci_institute_code: "25043", name: "University Hospital of Wales", city: "Wales", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -536,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25043",GROUP_DESC:"25043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -536,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -536,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1536 ,protection_group_id: -536, protection_element_id:-536], primaryKey: false);
      insert('organizations', [ id: 100522, nci_institute_code: "25044", name: "Bristol Royal Hospital", city: "Bristol", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -537,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25044",GROUP_DESC:"25044 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -537,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25044",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25044",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -537,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25044", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1537 ,protection_group_id: -537, protection_element_id:-537], primaryKey: false);
      insert('organizations', [ id: 100523, nci_institute_code: "25045", name: "Singleton Hospital", city: "South Wales", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -538,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25045",GROUP_DESC:"25045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -538,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -538,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1538 ,protection_group_id: -538, protection_element_id:-538], primaryKey: false);
      insert('organizations', [ id: 100524, nci_institute_code: "25046", name: "Leighton Hospital", city: "Cheshire", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -539,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25046",GROUP_DESC:"25046 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -539,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25046",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25046",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -539,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25046", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1539 ,protection_group_id: -539, protection_element_id:-539], primaryKey: false);
    }

    void m21() {
        // all21 (25 terms)
      insert('organizations', [ id: 100525, nci_institute_code: "25047", name: "N Staffordshire Royal Infirmary", city: "Stoke On Trent", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -540,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25047",GROUP_DESC:"25047 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -540,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25047",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25047",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -540,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25047", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1540 ,protection_group_id: -540, protection_element_id:-540], primaryKey: false);
      insert('organizations', [ id: 100526, nci_institute_code: "25048", name: "Weston Park Hospital", city: "Sheffield", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -541,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25048",GROUP_DESC:"25048 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -541,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25048",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25048",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -541,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25048", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1541 ,protection_group_id: -541, protection_element_id:-541], primaryKey: false);
      insert('organizations', [ id: 100527, nci_institute_code: "25049", name: "University College Hospital", city: "London", state: "England", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -542,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25049",GROUP_DESC:"25049 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -542,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25049",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25049",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -542,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25049", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1542 ,protection_group_id: -542, protection_element_id:-542], primaryKey: false);
      insert('organizations', [ id: 100528, nci_institute_code: "25050", name: "Saint Bartholomew Hospital", city: "London", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -543,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25050",GROUP_DESC:"25050 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -543,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25050",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25050",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -543,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25050", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1543 ,protection_group_id: -543, protection_element_id:-543], primaryKey: false);
      insert('organizations', [ id: 100529, nci_institute_code: "25051", name: "Hackney Hospital", city: "Hackney London E9", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -544,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25051",GROUP_DESC:"25051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -544,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -544,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1544 ,protection_group_id: -544, protection_element_id:-544], primaryKey: false);
      insert('organizations', [ id: 100530, nci_institute_code: "25052", name: "Derbyshire Royal Infirmary", city: "Derby", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -545,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25052",GROUP_DESC:"25052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -545,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -545,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1545 ,protection_group_id: -545, protection_element_id:-545], primaryKey: false);
      insert('organizations', [ id: 100531, nci_institute_code: "25053", name: "North Middlesex Hospital", city: "London", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -546,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25053",GROUP_DESC:"25053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -546,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -546,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1546 ,protection_group_id: -546, protection_element_id:-546], primaryKey: false);
      insert('organizations', [ id: 100532, nci_institute_code: "25054", name: "Saint Thomas Hospital.", city: "London", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -547,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25054",GROUP_DESC:"25054 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -547,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25054",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25054",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -547,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25054", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1547 ,protection_group_id: -547, protection_element_id:-547], primaryKey: false);
      insert('organizations', [ id: 100533, nci_institute_code: "25055", name: "University of Birmingham Crc Institute for Cancer Studies", city: "Birmingham", state: "England", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -548,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25055",GROUP_DESC:"25055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -548,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -548,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1548 ,protection_group_id: -548, protection_element_id:-548], primaryKey: false);
      insert('organizations', [ id: 100534, nci_institute_code: "25056", name: "Cambridge University School of Veterinary Medicine", city: "Cambridge", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -549,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25056",GROUP_DESC:"25056 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -549,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25056",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25056",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -549,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25056", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1549 ,protection_group_id: -549, protection_element_id:-549], primaryKey: false);
      insert('organizations', [ id: 100535, nci_institute_code: "25057", name: "Radcliff Infirmary", city: "Oxford", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -550,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25057",GROUP_DESC:"25057 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -550,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25057",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25057",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -550,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25057", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1550 ,protection_group_id: -550, protection_element_id:-550], primaryKey: false);
      insert('organizations', [ id: 100536, nci_institute_code: "25059", name: "University of Bristol", city: "Bristol", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -551,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25059",GROUP_DESC:"25059 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -551,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25059",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25059",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -551,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25059", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1551 ,protection_group_id: -551, protection_element_id:-551], primaryKey: false);
      insert('organizations', [ id: 100537, nci_institute_code: "25061", name: "University of Oxford", city: "Oxford", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -552,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25061",GROUP_DESC:"25061 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -552,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25061",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25061",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -552,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25061", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1552 ,protection_group_id: -552, protection_element_id:-552], primaryKey: false);
      insert('organizations', [ id: 100538, nci_institute_code: "25066", name: "Derby City Hospital", city: "Derby", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -553,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25066",GROUP_DESC:"25066 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -553,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25066",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25066",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -553,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25066", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1553 ,protection_group_id: -553, protection_element_id:-553], primaryKey: false);
      insert('organizations', [ id: 100539, nci_institute_code: "25067", name: "Hammersmith Hospital", city: "London W.12 Ohs.", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -554,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25067",GROUP_DESC:"25067 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -554,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25067",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25067",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -554,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25067", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1554 ,protection_group_id: -554, protection_element_id:-554], primaryKey: false);
      insert('organizations', [ id: 100540, nci_institute_code: "25069", name: "Royal Victoria Infirmary", city: "Newcastle", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -555,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25069",GROUP_DESC:"25069 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -555,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25069",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25069",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -555,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25069", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1555 ,protection_group_id: -555, protection_element_id:-555], primaryKey: false);
      insert('organizations', [ id: 100541, nci_institute_code: "25070", name: "Greenwich District Hospital", city: "London", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -556,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25070",GROUP_DESC:"25070 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -556,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25070",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25070",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -556,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25070", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1556 ,protection_group_id: -556, protection_element_id:-556], primaryKey: false);
      insert('organizations', [ id: 100542, nci_institute_code: "25071", name: "General Hospital", city: "Nottingham Ngi Gha", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -557,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25071",GROUP_DESC:"25071 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -557,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25071",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25071",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -557,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25071", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1557 ,protection_group_id: -557, protection_element_id:-557], primaryKey: false);
      insert('organizations', [ id: 100543, nci_institute_code: "25073", name: "University of London", city: "London", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -558,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25073",GROUP_DESC:"25073 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -558,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25073",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25073",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -558,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25073", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1558 ,protection_group_id: -558, protection_element_id:-558], primaryKey: false);
      insert('organizations', [ id: 100544, nci_institute_code: "25074", name: "Cookridge Hospital", city: "Leeds Ls16", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -559,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25074",GROUP_DESC:"25074 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -559,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25074",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25074",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -559,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25074", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1559 ,protection_group_id: -559, protection_element_id:-559], primaryKey: false);
      insert('organizations', [ id: 100545, nci_institute_code: "25075", name: "Walsgrave Hospital", city: "Walsgrave, Coventry", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -560,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25075",GROUP_DESC:"25075 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -560,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25075",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25075",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -560,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25075", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1560 ,protection_group_id: -560, protection_element_id:-560], primaryKey: false);
      insert('organizations', [ id: 100546, nci_institute_code: "25076", name: "Royal United Hospital", city: "Avon", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -561,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25076",GROUP_DESC:"25076 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -561,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25076",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25076",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -561,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25076", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1561 ,protection_group_id: -561, protection_element_id:-561], primaryKey: false);
      insert('organizations', [ id: 100547, nci_institute_code: "25077", name: "East Birmingham Hospital", city: "Birmingham B9 5st", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -562,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25077",GROUP_DESC:"25077 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -562,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25077",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25077",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -562,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25077", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1562 ,protection_group_id: -562, protection_element_id:-562], primaryKey: false);
      insert('organizations', [ id: 100548, nci_institute_code: "25078", name: "Newcastle General Hospital", city: "Newcastle upon Tyne", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -563,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25078",GROUP_DESC:"25078 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -563,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25078",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25078",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -563,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25078", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1563 ,protection_group_id: -563, protection_element_id:-563], primaryKey: false);
      insert('organizations', [ id: 100549, nci_institute_code: "25079", name: "Whittington Hospital", city: "London N19 5nf", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -564,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25079",GROUP_DESC:"25079 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -564,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25079",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25079",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -564,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25079", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1564 ,protection_group_id: -564, protection_element_id:-564], primaryKey: false);
    }

    void m22() {
        // all22 (25 terms)
      insert('organizations', [ id: 100550, nci_institute_code: "25082", name: "Institute of Cancer Research", city: "Sutton", state: "England", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -565,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25082",GROUP_DESC:"25082 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -565,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25082",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25082",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -565,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25082", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1565 ,protection_group_id: -565, protection_element_id:-565], primaryKey: false);
      insert('organizations', [ id: 100551, nci_institute_code: "25083", name: "Guys Hospital", city: "London", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -566,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25083",GROUP_DESC:"25083 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -566,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25083",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25083",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -566,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25083", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1566 ,protection_group_id: -566, protection_element_id:-566], primaryKey: false);
      insert('organizations', [ id: 100552, nci_institute_code: "25084", name: "Leicester Royal Infirmary", city: "Leicester", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -567,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25084",GROUP_DESC:"25084 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -567,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25084",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25084",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -567,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25084", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1567 ,protection_group_id: -567, protection_element_id:-567], primaryKey: false);
      insert('organizations', [ id: 100553, nci_institute_code: "25085", name: "General Hospital.", city: "Birmingham B4 6nh", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -568,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25085",GROUP_DESC:"25085 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -568,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25085",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25085",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -568,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25085", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1568 ,protection_group_id: -568, protection_element_id:-568], primaryKey: false);
      insert('organizations', [ id: 100554, nci_institute_code: "25086", name: "Northwick Park Hospital.", city: "Middlesex, Ha1 3uj", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -569,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25086",GROUP_DESC:"25086 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -569,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25086",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25086",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -569,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25086", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1569 ,protection_group_id: -569, protection_element_id:-569], primaryKey: false);
      insert('organizations', [ id: 100555, nci_institute_code: "25087", name: "Cambridge University", city: "Cambridge", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -570,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25087",GROUP_DESC:"25087 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -570,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25087",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25087",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -570,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25087", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1570 ,protection_group_id: -570, protection_element_id:-570], primaryKey: false);
      insert('organizations', [ id: 100556, nci_institute_code: "25089", name: "London Hospital", city: "London", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -571,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25089",GROUP_DESC:"25089 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -571,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25089",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25089",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -571,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25089", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1571 ,protection_group_id: -571, protection_element_id:-571], primaryKey: false);
      insert('organizations', [ id: 100557, nci_institute_code: "25090", name: "Southampton General Hospital", city: "Southampton", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -572,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25090",GROUP_DESC:"25090 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -572,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25090",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25090",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -572,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25090", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1572 ,protection_group_id: -572, protection_element_id:-572], primaryKey: false);
      insert('organizations', [ id: 100558, nci_institute_code: "25091", name: "Block E", city: "Sutton Surry", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -573,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25091",GROUP_DESC:"25091 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -573,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25091",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25091",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -573,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25091", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1573 ,protection_group_id: -573, protection_element_id:-573], primaryKey: false);
      insert('organizations', [ id: 100559, nci_institute_code: "25093", name: "Kent and Canterbury Hospital", city: "Canterbury, Kent", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -574,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25093",GROUP_DESC:"25093 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -574,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25093",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25093",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -574,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25093", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1574 ,protection_group_id: -574, protection_element_id:-574], primaryKey: false);
      insert('organizations', [ id: 100560, nci_institute_code: "25094", name: "Royal Marsden Institute of Cancer Research", city: "London", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -575,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25094",GROUP_DESC:"25094 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -575,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25094",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25094",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -575,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25094", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1575 ,protection_group_id: -575, protection_element_id:-575], primaryKey: false);
      insert('organizations', [ id: 100561, nci_institute_code: "25095", name: "Freeman Hospital.", city: "Newcastle Upon Tyne", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -576,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25095",GROUP_DESC:"25095 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -576,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25095",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25095",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -576,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25095", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1576 ,protection_group_id: -576, protection_element_id:-576], primaryKey: false);
      insert('organizations', [ id: 100562, nci_institute_code: "25096", name: "John Radcliffe Hospital", city: "Oxford", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -577,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25096",GROUP_DESC:"25096 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -577,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25096",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25096",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -577,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25096", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1577 ,protection_group_id: -577, protection_element_id:-577], primaryKey: false);
      insert('organizations', [ id: 100563, nci_institute_code: "25098", name: "Walton Hospital", city: "Liverpool, L91ae", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -578,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25098",GROUP_DESC:"25098 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -578,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25098",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25098",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -578,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25098", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1578 ,protection_group_id: -578, protection_element_id:-578], primaryKey: false);
      insert('organizations', [ id: 100564, nci_institute_code: "25099", name: "Dudley Road Hospital", city: "Birmingham B18 7qh", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -579,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25099",GROUP_DESC:"25099 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -579,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25099",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25099",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -579,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25099", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1579 ,protection_group_id: -579, protection_element_id:-579], primaryKey: false);
      insert('organizations', [ id: 100565, nci_institute_code: "25100", name: "Charing Cross Hospital", city: "London W6 8rf", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -580,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25100",GROUP_DESC:"25100 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -580,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25100",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25100",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -580,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25100", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1580 ,protection_group_id: -580, protection_element_id:-580], primaryKey: false);
      insert('organizations', [ id: 100566, nci_institute_code: "25101", name: "Maidstone Hospital", city: "Maidstone Kent", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -581,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25101",GROUP_DESC:"25101 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -581,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25101",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25101",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -581,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25101", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1581 ,protection_group_id: -581, protection_element_id:-581], primaryKey: false);
      insert('organizations', [ id: 100567, nci_institute_code: "25102", name: "Musgrove Park Hospital", city: "Tauton Sumerset", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -582,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25102",GROUP_DESC:"25102 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -582,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25102",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25102",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -582,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25102", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1582 ,protection_group_id: -582, protection_element_id:-582], primaryKey: false);
      insert('organizations', [ id: 100568, nci_institute_code: "25103", name: "Mount Vernon Hospital.", city: "Northwood, Middlesex", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -583,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25103",GROUP_DESC:"25103 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -583,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25103",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25103",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -583,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25103", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1583 ,protection_group_id: -583, protection_element_id:-583], primaryKey: false);
      insert('organizations', [ id: 100569, nci_institute_code: "25107", name: "Royal South Hants Hospital", city: "Southampton", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -584,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25107",GROUP_DESC:"25107 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -584,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25107",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25107",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -584,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25107", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1584 ,protection_group_id: -584, protection_element_id:-584], primaryKey: false);
      insert('organizations', [ id: 100570, nci_institute_code: "25108", name: "Good Hope Hospital.", city: "Sutton Coldfield", state: "England", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -585,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25108",GROUP_DESC:"25108 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -585,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25108",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25108",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -585,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25108", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1585 ,protection_group_id: -585, protection_element_id:-585], primaryKey: false);
      insert('organizations', [ id: 100571, nci_institute_code: "25109", name: "Pinderfield General Hospital", city: "West Yorkshire", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -586,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25109",GROUP_DESC:"25109 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -586,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25109",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25109",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -586,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25109", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1586 ,protection_group_id: -586, protection_element_id:-586], primaryKey: false);
      insert('organizations', [ id: 100572, nci_institute_code: "25110", name: "Old Church Hospital", city: "Ramford, Essex Obe", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -587,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25110",GROUP_DESC:"25110 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -587,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25110",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25110",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -587,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25110", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1587 ,protection_group_id: -587, protection_element_id:-587], primaryKey: false);
      insert('organizations', [ id: 100573, nci_institute_code: "25111", name: "All Saints Hospital", city: "Kent", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -588,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25111",GROUP_DESC:"25111 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -588,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25111",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25111",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -588,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25111", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1588 ,protection_group_id: -588, protection_element_id:-588], primaryKey: false);
      insert('organizations', [ id: 100574, nci_institute_code: "25112", name: "South Tyneside District Hospital", city: "South Shields Tyne", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -589,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25112",GROUP_DESC:"25112 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -589,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25112",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25112",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -589,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25112", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1589 ,protection_group_id: -589, protection_element_id:-589], primaryKey: false);
    }

    void m23() {
        // all23 (25 terms)
      insert('organizations', [ id: 100575, nci_institute_code: "25113", name: "Christie Hospital", city: "Manchester M20 9bx", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -590,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25113",GROUP_DESC:"25113 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -590,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25113",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25113",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -590,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25113", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1590 ,protection_group_id: -590, protection_element_id:-590], primaryKey: false);
      insert('organizations', [ id: 100576, nci_institute_code: "25114", name: "Bradford Royal Infirmary", city: "Bradford", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -591,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25114",GROUP_DESC:"25114 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -591,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25114",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25114",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -591,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25114", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1591 ,protection_group_id: -591, protection_element_id:-591], primaryKey: false);
      insert('organizations', [ id: 100577, nci_institute_code: "25115", name: "Wexham Park Hospital", city: "Berkshire Sl2 4ah", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -592,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25115",GROUP_DESC:"25115 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -592,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25115",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25115",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -592,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25115", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1592 ,protection_group_id: -592, protection_element_id:-592], primaryKey: false);
      insert('organizations', [ id: 100578, nci_institute_code: "25116", name: "Liverpool University Hospital", city: "Liverpool", state: "Merseyside", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -593,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25116",GROUP_DESC:"25116 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -593,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25116",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25116",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -593,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25116", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1593 ,protection_group_id: -593, protection_element_id:-593], primaryKey: false);
      insert('organizations', [ id: 100579, nci_institute_code: "25117", name: "Ninewells Hospital and Medical School", city: "Dundee Dd1 9sy", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -594,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25117",GROUP_DESC:"25117 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -594,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25117",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25117",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -594,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25117", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1594 ,protection_group_id: -594, protection_element_id:-594], primaryKey: false);
      insert('organizations', [ id: 100580, nci_institute_code: "25118", name: "Mount Vernon Hospital", city: "Northwood", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -595,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25118",GROUP_DESC:"25118 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -595,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25118",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25118",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -595,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25118", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1595 ,protection_group_id: -595, protection_element_id:-595], primaryKey: false);
      insert('organizations', [ id: 100581, nci_institute_code: "25119", name: "Lewisham Medical Trust", city: "London", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -596,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25119",GROUP_DESC:"25119 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -596,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25119",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25119",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -596,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25119", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1596 ,protection_group_id: -596, protection_element_id:-596], primaryKey: false);
      insert('organizations', [ id: 100582, nci_institute_code: "25120", name: "Castle Hill Hospital", city: "East Yorkshire", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -597,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25120",GROUP_DESC:"25120 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -597,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25120",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25120",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -597,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25120", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1597 ,protection_group_id: -597, protection_element_id:-597], primaryKey: false);
      insert('organizations', [ id: 100583, nci_institute_code: "25121", name: "Royal Preston Hospital", city: "Fulwood Preston", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -598,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25121",GROUP_DESC:"25121 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -598,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25121",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25121",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -598,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25121", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1598 ,protection_group_id: -598, protection_element_id:-598], primaryKey: false);
      insert('organizations', [ id: 100584, nci_institute_code: "25122", name: "Huddersfield Royal Infirmary", city: "Huddersfield", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -599,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25122",GROUP_DESC:"25122 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -599,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25122",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25122",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -599,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25122", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1599 ,protection_group_id: -599, protection_element_id:-599], primaryKey: false);
      insert('organizations', [ id: 100585, nci_institute_code: "25127", name: "Ysbyty Gwynedd", city: "Gwynedd", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -600,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25127",GROUP_DESC:"25127 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -600,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25127",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25127",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -600,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25127", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1600 ,protection_group_id: -600, protection_element_id:-600], primaryKey: false);
      insert('organizations', [ id: 100586, nci_institute_code: "25128", name: "North Staffs Royal Infirmary", state: "England", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -601,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25128",GROUP_DESC:"25128 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -601,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25128",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25128",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -601,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25128", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1601 ,protection_group_id: -601, protection_element_id:-601], primaryKey: false);
      insert('organizations', [ id: 100587, nci_institute_code: "25129", name: "Royal Lancaster Infirmary", city: "Lancaster", state: "England", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -602,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25129",GROUP_DESC:"25129 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -602,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25129",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25129",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -602,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25129", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1602 ,protection_group_id: -602, protection_element_id:-602], primaryKey: false);
      insert('organizations', [ id: 100588, nci_institute_code: "25130", name: "Royal Cornwall &Treliske Hosp", city: "Cornwall", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -603,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25130",GROUP_DESC:"25130 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -603,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25130",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25130",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -603,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25130", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1603 ,protection_group_id: -603, protection_element_id:-603], primaryKey: false);
      insert('organizations', [ id: 100589, nci_institute_code: "25131", name: "Northampton General Hosp", city: "Northampton", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -604,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25131",GROUP_DESC:"25131 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -604,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25131",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25131",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -604,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25131", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1604 ,protection_group_id: -604, protection_element_id:-604], primaryKey: false);
      insert('organizations', [ id: 100590, nci_institute_code: "25132", name: "Perth Royal Infirmary", city: ".", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -605,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25132",GROUP_DESC:"25132 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -605,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25132",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25132",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -605,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25132", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1605 ,protection_group_id: -605, protection_element_id:-605], primaryKey: false);
      insert('organizations', [ id: 100591, nci_institute_code: "25133", name: "Yeovill District Hospital", city: "Somerset", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -606,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25133",GROUP_DESC:"25133 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -606,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25133",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25133",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -606,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25133", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1606 ,protection_group_id: -606, protection_element_id:-606], primaryKey: false);
      insert('organizations', [ id: 100592, nci_institute_code: "25134", name: "Manor Hospital", city: "Midlands", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -607,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25134",GROUP_DESC:"25134 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -607,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25134",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25134",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -607,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25134", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1607 ,protection_group_id: -607, protection_element_id:-607], primaryKey: false);
      insert('organizations', [ id: 100593, nci_institute_code: "25135", name: "North Devon District Hosp", city: ".", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -608,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25135",GROUP_DESC:"25135 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -608,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25135",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25135",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -608,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25135", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1608 ,protection_group_id: -608, protection_element_id:-608], primaryKey: false);
      insert('organizations', [ id: 100594, nci_institute_code: "25136", name: "Derriford Hospital", city: "Plymouth, Devon", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -609,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25136",GROUP_DESC:"25136 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -609,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25136",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25136",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -609,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25136", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1609 ,protection_group_id: -609, protection_element_id:-609], primaryKey: false);
      insert('organizations', [ id: 100595, nci_institute_code: "25137", name: "East Surrey Hospital", city: "Redhill, Surrey", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -610,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25137",GROUP_DESC:"25137 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -610,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25137",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25137",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -610,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25137", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1610 ,protection_group_id: -610, protection_element_id:-610], primaryKey: false);
      insert('organizations', [ id: 100596, nci_institute_code: "25138", name: "Essex County Hospital", city: "Gloucester, Essex", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -611,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25138",GROUP_DESC:"25138 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -611,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25138",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25138",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -611,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25138", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1611 ,protection_group_id: -611, protection_element_id:-611], primaryKey: false);
      insert('organizations', [ id: 100597, nci_institute_code: "25139", name: "Cheltenham General Hospital", city: "Gloucester", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -612,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25139",GROUP_DESC:"25139 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -612,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25139",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25139",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -612,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25139", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1612 ,protection_group_id: -612, protection_element_id:-612], primaryKey: false);
      insert('organizations', [ id: 100598, nci_institute_code: "25140", name: "City Hospital", city: "Birmingham", state: "England", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -613,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25140",GROUP_DESC:"25140 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -613,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25140",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25140",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -613,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25140", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1613 ,protection_group_id: -613, protection_element_id:-613], primaryKey: false);
      insert('organizations', [ id: 100599, nci_institute_code: "25141", name: "Clatterbridge Centre for Oncolgy", city: "Bebington, Wirral", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -614,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25141",GROUP_DESC:"25141 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -614,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25141",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25141",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -614,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25141", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1614 ,protection_group_id: -614, protection_element_id:-614], primaryKey: false);
    }

    void m24() {
        // all24 (25 terms)
      insert('organizations', [ id: 100600, nci_institute_code: "25142", name: "Heartlands/Solihull Hosp.", city: "Birmingham", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -615,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25142",GROUP_DESC:"25142 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -615,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25142",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25142",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -615,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25142", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1615 ,protection_group_id: -615, protection_element_id:-615], primaryKey: false);
      insert('organizations', [ id: 100601, nci_institute_code: "25143", name: "Whiston Hospital", city: "Prescot,  Merseyside", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -616,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25143",GROUP_DESC:"25143 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -616,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25143",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25143",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -616,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25143", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1616 ,protection_group_id: -616, protection_element_id:-616], primaryKey: false);
      insert('organizations', [ id: 100602, nci_institute_code: "25144", name: "Worcester Royal Infirmary", city: "Worcester", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -617,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25144",GROUP_DESC:"25144 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -617,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25144",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25144",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -617,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25144", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1617 ,protection_group_id: -617, protection_element_id:-617], primaryKey: false);
      insert('organizations', [ id: 100603, nci_institute_code: "25145", name: "Stoke Mandeville Hospital", city: "Aylesbury, Bucks", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -618,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25145",GROUP_DESC:"25145 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -618,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25145",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25145",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -618,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25145", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1618 ,protection_group_id: -618, protection_element_id:-618], primaryKey: false);
      insert('organizations', [ id: 100604, nci_institute_code: "25146", name: "Ucl Hosp.-The Middlesex", city: ".", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -619,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25146",GROUP_DESC:"25146 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -619,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25146",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25146",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -619,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25146", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1619 ,protection_group_id: -619, protection_element_id:-619], primaryKey: false);
      insert('organizations', [ id: 100605, nci_institute_code: "25147", name: "Russell'S Hall Hospital", city: "Midlands", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -620,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25147",GROUP_DESC:"25147 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -620,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25147",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25147",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -620,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25147", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1620 ,protection_group_id: -620, protection_element_id:-620], primaryKey: false);
      insert('organizations', [ id: 100606, nci_institute_code: "25148", name: "Southend Hospital", city: "Essex", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -621,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25148",GROUP_DESC:"25148 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -621,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25148",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25148",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -621,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25148", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1621 ,protection_group_id: -621, protection_element_id:-621], primaryKey: false);
      insert('organizations', [ id: 100607, nci_institute_code: "25149", name: "St.Margaret'S Hospital", city: "Essex", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -622,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25149",GROUP_DESC:"25149 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -622,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25149",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25149",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -622,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25149", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1622 ,protection_group_id: -622, protection_element_id:-622], primaryKey: false);
      insert('organizations', [ id: 100608, nci_institute_code: "25150", name: "Taunton & Somerset Hosp.", city: "Taunton, Somerset", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -623,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25150",GROUP_DESC:"25150 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -623,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25150",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25150",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -623,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25150", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1623 ,protection_group_id: -623, protection_element_id:-623], primaryKey: false);
      insert('organizations', [ id: 100609, nci_institute_code: "25154", name: "Royal Orthopaedics Hospital", city: "Brimingham", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -624,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25154",GROUP_DESC:"25154 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -624,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25154",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25154",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -624,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25154", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1624 ,protection_group_id: -624, protection_element_id:-624], primaryKey: false);
      insert('organizations', [ id: 100610, nci_institute_code: "25155", name: "Nottingham City Hospital", city: "Nottingham", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -625,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25155",GROUP_DESC:"25155 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -625,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25155",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25155",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -625,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25155", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1625 ,protection_group_id: -625, protection_element_id:-625], primaryKey: false);
      insert('organizations', [ id: 100611, nci_institute_code: "25156", name: "Saint George's Hospital", city: "London", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -626,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25156",GROUP_DESC:"25156 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -626,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25156",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25156",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -626,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25156", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1626 ,protection_group_id: -626, protection_element_id:-626], primaryKey: false);
      insert('organizations', [ id: 100612, nci_institute_code: "25157", name: "Manor Hospital", city: "West Midlands", state: "England", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -627,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25157",GROUP_DESC:"25157 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -627,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25157",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25157",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -627,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25157", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1627 ,protection_group_id: -627, protection_element_id:-627], primaryKey: false);
      insert('organizations', [ id: 100613, nci_institute_code: "25158", name: "Airedale General Hospital", city: "Steeton", state: "West Yorkshire", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -628,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25158",GROUP_DESC:"25158 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -628,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25158",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25158",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -628,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25158", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1628 ,protection_group_id: -628, protection_element_id:-628], primaryKey: false);
      insert('organizations', [ id: 100614, nci_institute_code: "25159", name: "Yeovil District Hospital", city: "Yeovil", state: "Somerset", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -629,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25159",GROUP_DESC:"25159 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -629,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25159",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25159",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -629,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25159", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1629 ,protection_group_id: -629, protection_element_id:-629], primaryKey: false);
      insert('organizations', [ id: 100615, nci_institute_code: "25161", name: "Birmingham Heartlands Hospital", city: "Birmingham", state: "Engalnd", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -630,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25161",GROUP_DESC:"25161 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -630,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25161",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25161",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -630,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25161", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1630 ,protection_group_id: -630, protection_element_id:-630], primaryKey: false);
      insert('organizations', [ id: 100616, nci_institute_code: "25162", name: "Queen's Hospital", city: "Staffordshire", state: "England", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -631,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25162",GROUP_DESC:"25162 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -631,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25162",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25162",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -631,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25162", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1631 ,protection_group_id: -631, protection_element_id:-631], primaryKey: false);
      insert('organizations', [ id: 100617, nci_institute_code: "25163", name: "The Royal Marsden NHS Foundation Trust - Sutton", city: "Sutton", state: "Surrey", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -632,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25163",GROUP_DESC:"25163 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -632,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25163",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25163",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -632,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25163", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1632 ,protection_group_id: -632, protection_element_id:-632], primaryKey: false);
      insert('organizations', [ id: 100618, nci_institute_code: "25164", name: "City Hospital", city: "Birmingham", state: "BC", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -633,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25164",GROUP_DESC:"25164 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -633,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25164",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25164",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -633,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25164", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1633 ,protection_group_id: -633, protection_element_id:-633], primaryKey: false);
      insert('organizations', [ id: 100619, nci_institute_code: "25166", name: "Broomfield Hospital", city: "Chelmsford", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -634,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25166",GROUP_DESC:"25166 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -634,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25166",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25166",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -634,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25166", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1634 ,protection_group_id: -634, protection_element_id:-634], primaryKey: false);
      insert('organizations', [ id: 100620, nci_institute_code: "25167", name: "IDIS World Medicines", city: "Surbiton Surrey", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -635,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25167",GROUP_DESC:"25167 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -635,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25167",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25167",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -635,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25167", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1635 ,protection_group_id: -635, protection_element_id:-635], primaryKey: false);
      insert('organizations', [ id: 100621, nci_institute_code: "25168", name: "Hairmyres Hospital", city: "East Kilbride", state: "Scotland", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -636,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25168",GROUP_DESC:"25168 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -636,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25168",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25168",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -636,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25168", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1636 ,protection_group_id: -636, protection_element_id:-636], primaryKey: false);
      insert('organizations', [ id: 100622, nci_institute_code: "25169", name: "Llandough Hospital", city: "Wales", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -637,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25169",GROUP_DESC:"25169 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -637,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25169",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25169",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -637,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25169", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1637 ,protection_group_id: -637, protection_element_id:-637], primaryKey: false);
      insert('organizations', [ id: 100623, nci_institute_code: "25170", name: "North Satffordshire Hospital", city: "Stoke-On-Trent", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -638,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25170",GROUP_DESC:"25170 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -638,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25170",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25170",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -638,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25170", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1638 ,protection_group_id: -638, protection_element_id:-638], primaryKey: false);
      insert('organizations', [ id: 100624, nci_institute_code: "25171", name: "Velindre Hospital", city: "Cardiff", state: "South Glamorgan", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -639,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25171",GROUP_DESC:"25171 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -639,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25171",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25171",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -639,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25171", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1639 ,protection_group_id: -639, protection_element_id:-639], primaryKey: false);
    }

    void m25() {
        // all25 (25 terms)
      insert('organizations', [ id: 100625, nci_institute_code: "25172", name: "Bristol Royal Infirmary", city: "Bristol", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -640,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25172",GROUP_DESC:"25172 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -640,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25172",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25172",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -640,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25172", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1640 ,protection_group_id: -640, protection_element_id:-640], primaryKey: false);
      insert('organizations', [ id: 100626, nci_institute_code: "25173", name: "Blackpool Victoria Hospital", city: "Blackpool", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -641,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25173",GROUP_DESC:"25173 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -641,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25173",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25173",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -641,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25173", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1641 ,protection_group_id: -641, protection_element_id:-641], primaryKey: false);
      insert('organizations', [ id: 100627, nci_institute_code: "25174", name: "Glasgow Royal Infirmary", city: "Glascow", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -642,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25174",GROUP_DESC:"25174 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -642,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25174",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25174",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -642,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25174", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1642 ,protection_group_id: -642, protection_element_id:-642], primaryKey: false);
      insert('organizations', [ id: 100628, nci_institute_code: "25175", name: "New Cross Hospital", city: "Wolverhamptom,England", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -643,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25175",GROUP_DESC:"25175 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -643,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25175",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25175",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -643,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25175", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1643 ,protection_group_id: -643, protection_element_id:-643], primaryKey: false);
      insert('organizations', [ id: 100629, nci_institute_code: "25176", name: "Hope Hospital", city: "Salford", state: "England", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -644,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25176",GROUP_DESC:"25176 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -644,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25176",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25176",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -644,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25176", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1644 ,protection_group_id: -644, protection_element_id:-644], primaryKey: false);
      insert('organizations', [ id: 100630, nci_institute_code: "25177", name: "Birmingham Women's Hospital", city: "Birmingham", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -645,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25177",GROUP_DESC:"25177 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -645,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25177",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25177",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -645,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25177", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1645 ,protection_group_id: -645, protection_element_id:-645], primaryKey: false);
      insert('organizations', [ id: 100631, nci_institute_code: "25178", name: "Leeds Teaching Hospitals NHS Trust", city: "Leeds", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -646,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25178",GROUP_DESC:"25178 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -646,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25178",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25178",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -646,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25178", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1646 ,protection_group_id: -646, protection_element_id:-646], primaryKey: false);
      insert('organizations', [ id: 100632, nci_institute_code: "25179", name: "Cancer Research United Kingdom", city: "London", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -647,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25179",GROUP_DESC:"25179 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -647,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25179",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25179",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -647,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25179", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1647 ,protection_group_id: -647, protection_element_id:-647], primaryKey: false);
      insert('organizations', [ id: 100633, nci_institute_code: "25180", name: "Royal Free and University College Medical School", city: "London", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -648,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25180",GROUP_DESC:"25180 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -648,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25180",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25180",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -648,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25180", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1648 ,protection_group_id: -648, protection_element_id:-648], primaryKey: false);
      insert('organizations', [ id: 100634, nci_institute_code: "25181", name: "Withington Hospital", city: "Mancherster", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -649,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25181",GROUP_DESC:"25181 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -649,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25181",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25181",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -649,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25181", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1649 ,protection_group_id: -649, protection_element_id:-649], primaryKey: false);
      insert('organizations', [ id: 100635, nci_institute_code: "25182", name: "Clinical Trials and Research Unit, Leeds", city: "Leeds", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -650,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25182",GROUP_DESC:"25182 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -650,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25182",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25182",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -650,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25182", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1650 ,protection_group_id: -650, protection_element_id:-650], primaryKey: false);
      insert('organizations', [ id: 100636, nci_institute_code: "25183", name: "Barnet General Hospital", city: "Barnet, Hertfordshire", state: "ENGLAND", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -651,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25183",GROUP_DESC:"25183 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -651,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.25183",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.25183",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -651,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.25183", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1651 ,protection_group_id: -651, protection_element_id:-651], primaryKey: false);
      insert('organizations', [ id: 100637, nci_institute_code: "27001", name: "The University of Helsinki", city: "Helsinki", country: "Finland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -652,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.27001",GROUP_DESC:"27001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -652,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.27001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.27001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -652,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.27001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1652 ,protection_group_id: -652, protection_element_id:-652], primaryKey: false);
      insert('organizations', [ id: 100638, nci_institute_code: "27002", name: "University of Oulu", city: "Oulu", country: "Finland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -653,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.27002",GROUP_DESC:"27002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -653,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.27002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.27002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -653,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.27002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1653 ,protection_group_id: -653, protection_element_id:-653], primaryKey: false);
      insert('organizations', [ id: 100639, nci_institute_code: "27003", name: "Tampere University Hospital", city: "Tampere", country: "Finland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -654,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.27003",GROUP_DESC:"27003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -654,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.27003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.27003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -654,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.27003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1654 ,protection_group_id: -654, protection_element_id:-654], primaryKey: false);
      insert('organizations', [ id: 100640, nci_institute_code: "27004", name: "Central Hospital", city: "Abo", country: "Finland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -655,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.27004",GROUP_DESC:"27004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -655,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.27004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.27004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -655,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.27004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1655 ,protection_group_id: -655, protection_element_id:-655], primaryKey: false);
      insert('organizations', [ id: 100641, nci_institute_code: "28001", name: "Hopital Paul-Brousse", city: "94800 Villejuif", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -656,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28001",GROUP_DESC:"28001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -656,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -656,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1656 ,protection_group_id: -656, protection_element_id:-656], primaryKey: false);
      insert('organizations', [ id: 100642, nci_institute_code: "28002", name: "Hopital Salpetriere", city: "Paris Cedex 13", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -657,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28002",GROUP_DESC:"28002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -657,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -657,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1657 ,protection_group_id: -657, protection_element_id:-657], primaryKey: false);
      insert('organizations', [ id: 100643, nci_institute_code: "28003", name: "Hopital Saint Louis", city: "Paris", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -658,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28003",GROUP_DESC:"28003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -658,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -658,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1658 ,protection_group_id: -658, protection_element_id:-658], primaryKey: false);
      insert('organizations', [ id: 100644, nci_institute_code: "28004", name: "Hopital Saint Louis", city: "75475 Paris Cedex 10", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -659,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28004",GROUP_DESC:"28004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -659,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -659,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1659 ,protection_group_id: -659, protection_element_id:-659], primaryKey: false);
      insert('organizations', [ id: 100645, nci_institute_code: "28006", name: "Centre Leon Berard", city: "Lyon 8", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -660,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28006",GROUP_DESC:"28006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -660,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -660,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1660 ,protection_group_id: -660, protection_element_id:-660], primaryKey: false);
      insert('organizations', [ id: 100646, nci_institute_code: "28007", name: "Hopital Saint-Andre", city: "Bordeaux 33", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -661,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28007",GROUP_DESC:"28007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -661,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -661,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1661 ,protection_group_id: -661, protection_element_id:-661], primaryKey: false);
      insert('organizations', [ id: 100647, nci_institute_code: "28008", name: "Hopital Franco-Musulman", city: "93000 Bobigny", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -662,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28008",GROUP_DESC:"28008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -662,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -662,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1662 ,protection_group_id: -662, protection_element_id:-662], primaryKey: false);
      insert('organizations', [ id: 100648, nci_institute_code: "28009", name: "Hopital De La Timone", city: "Marseille 5e13", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -663,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28009",GROUP_DESC:"28009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -663,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -663,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1663 ,protection_group_id: -663, protection_element_id:-663], primaryKey: false);
      insert('organizations', [ id: 100649, nci_institute_code: "28010", name: "Centre Hopitalier Univ", city: "Paris 10", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -664,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28010",GROUP_DESC:"28010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -664,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -664,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1664 ,protection_group_id: -664, protection_element_id:-664], primaryKey: false);
    }

    void m26() {
        // all26 (25 terms)
      insert('organizations', [ id: 100650, nci_institute_code: "28011", name: "Institute Gustave Roussy", city: "Villejuif", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -665,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28011",GROUP_DESC:"28011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -665,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -665,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1665 ,protection_group_id: -665, protection_element_id:-665], primaryKey: false);
      insert('organizations', [ id: 100651, nci_institute_code: "28012", name: "Centre Hopitalier Regional", city: "Nancy 54037", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -666,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28012",GROUP_DESC:"28012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -666,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -666,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1666 ,protection_group_id: -666, protection_element_id:-666], primaryKey: false);
      insert('organizations', [ id: 100652, nci_institute_code: "28013", name: "Centre Neurologique", city: "Lyon 69003", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -667,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28013",GROUP_DESC:"28013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -667,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -667,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1667 ,protection_group_id: -667, protection_element_id:-667], primaryKey: false);
      insert('organizations', [ id: 100653, nci_institute_code: "28014", name: "Centre Alexis Vautrin", city: "Nancy", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -668,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28014",GROUP_DESC:"28014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -668,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -668,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1668 ,protection_group_id: -668, protection_element_id:-668], primaryKey: false);
      insert('organizations', [ id: 100654, nci_institute_code: "28015", name: "Contre Le Cancer De Nancy", city: "Nancy", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -669,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28015",GROUP_DESC:"28015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -669,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -669,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1669 ,protection_group_id: -669, protection_element_id:-669], primaryKey: false);
      insert('organizations', [ id: 100655, nci_institute_code: "28016", name: "Hopital Cochin", city: "Paris, 14", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -670,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28016",GROUP_DESC:"28016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -670,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -670,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1670 ,protection_group_id: -670, protection_element_id:-670], primaryKey: false);
      insert('organizations', [ id: 100656, nci_institute_code: "28017", name: "Hosp Edouard Herriot", city: "Lyon Cedex 03", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -671,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28017",GROUP_DESC:"28017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -671,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -671,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1671 ,protection_group_id: -671, protection_element_id:-671], primaryKey: false);
      insert('organizations', [ id: 100657, nci_institute_code: "28018", name: "Hopital Cochin", city: "75014 Paris", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -672,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28018",GROUP_DESC:"28018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -672,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -672,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1672 ,protection_group_id: -672, protection_element_id:-672], primaryKey: false);
      insert('organizations', [ id: 100658, nci_institute_code: "28019", name: "Hopital De La Conception", city: "13005 Marseille", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -673,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28019",GROUP_DESC:"28019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -673,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -673,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1673 ,protection_group_id: -673, protection_element_id:-673], primaryKey: false);
      insert('organizations', [ id: 100659, nci_institute_code: "28020", name: "Reboul Hopital Saint-Andre", city: "33000 Bordeaux", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -674,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28020",GROUP_DESC:"28020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -674,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -674,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1674 ,protection_group_id: -674, protection_element_id:-674], primaryKey: false);
      insert('organizations', [ id: 100660, nci_institute_code: "28021", name: "Universite Paris-Val-De-Marne", city: "Creteil, Paris", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -675,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28021",GROUP_DESC:"28021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -675,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -675,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1675 ,protection_group_id: -675, protection_element_id:-675], primaryKey: false);
      insert('organizations', [ id: 100661, nci_institute_code: "28022", name: "Hopital De Cimiez", city: "06100 Nice", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -676,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28022",GROUP_DESC:"28022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -676,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -676,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1676 ,protection_group_id: -676, protection_element_id:-676], primaryKey: false);
      insert('organizations', [ id: 100662, nci_institute_code: "28023", name: "Chirurgien Des Hopiteux", city: "Marseiue", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -677,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28023",GROUP_DESC:"28023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -677,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -677,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1677 ,protection_group_id: -677, protection_element_id:-677], primaryKey: false);
      insert('organizations', [ id: 100663, nci_institute_code: "28024", name: "Cite Hopitaliere Lille", city: "Lille", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -678,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28024",GROUP_DESC:"28024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -678,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -678,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1678 ,protection_group_id: -678, protection_element_id:-678], primaryKey: false);
      insert('organizations', [ id: 100664, nci_institute_code: "28025", name: "Hopital La Pitie", city: "Paris", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -679,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28025",GROUP_DESC:"28025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -679,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -679,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1679 ,protection_group_id: -679, protection_element_id:-679], primaryKey: false);
      insert('organizations', [ id: 100665, nci_institute_code: "28026", name: "Centre Oscar Lambert", city: "Lille", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -680,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28026",GROUP_DESC:"28026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -680,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -680,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1680 ,protection_group_id: -680, protection_element_id:-680], primaryKey: false);
      insert('organizations', [ id: 100666, nci_institute_code: "28027", name: "Centre Rene Huguenin", city: "Saint Cloud", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -681,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28027",GROUP_DESC:"28027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -681,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -681,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1681 ,protection_group_id: -681, protection_element_id:-681], primaryKey: false);
      insert('organizations', [ id: 100667, nci_institute_code: "28028", name: "Medecin Des Hopitaux", city: "Lille", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -682,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28028",GROUP_DESC:"28028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -682,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -682,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1682 ,protection_group_id: -682, protection_element_id:-682], primaryKey: false);
      insert('organizations', [ id: 100668, nci_institute_code: "28030", name: "Hospital Pellegrin", city: "Bord", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -683,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28030",GROUP_DESC:"28030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -683,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -683,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1683 ,protection_group_id: -683, protection_element_id:-683], primaryKey: false);
      insert('organizations', [ id: 100669, nci_institute_code: "28032", name: "Hotel Dieu", city: "Paris 75181 Cedes 04", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -684,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28032",GROUP_DESC:"28032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -684,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -684,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1684 ,protection_group_id: -684, protection_element_id:-684], primaryKey: false);
      insert('organizations', [ id: 100670, nci_institute_code: "28033", name: "Hopital Hotel-Dieu", city: "Paris", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -685,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28033",GROUP_DESC:"28033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -685,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -685,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1685 ,protection_group_id: -685, protection_element_id:-685], primaryKey: false);
      insert('organizations', [ id: 100671, nci_institute_code: "28036", name: "Centre Antoine Lacassagne", city: "Nice", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -686,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28036",GROUP_DESC:"28036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -686,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -686,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1686 ,protection_group_id: -686, protection_element_id:-686], primaryKey: false);
      insert('organizations', [ id: 100672, nci_institute_code: "28037", name: "Hospital Beaujon", city: "Clichy", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -687,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28037",GROUP_DESC:"28037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -687,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -687,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1687 ,protection_group_id: -687, protection_element_id:-687], primaryKey: false);
      insert('organizations', [ id: 100673, nci_institute_code: "28038", name: "Ctr De Recherche Merrell Intl", city: "Strasbourg", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -688,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28038",GROUP_DESC:"28038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -688,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -688,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1688 ,protection_group_id: -688, protection_element_id:-688], primaryKey: false);
      insert('organizations', [ id: 100674, nci_institute_code: "28039", name: "Hopital Du Bocage", city: "Dijon", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -689,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28039",GROUP_DESC:"28039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -689,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -689,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1689 ,protection_group_id: -689, protection_element_id:-689], primaryKey: false);
    }

    void m27() {
        // all27 (25 terms)
      insert('organizations', [ id: 100675, nci_institute_code: "28040", name: "Paul Brousse Hospital, Villejuif, Paris, France", city: "Paris", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -690,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28040",GROUP_DESC:"28040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -690,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -690,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1690 ,protection_group_id: -690, protection_element_id:-690], primaryKey: false);
      insert('organizations', [ id: 100676, nci_institute_code: "28041", name: "Centre Hopitalier", city: "Pau", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -691,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28041",GROUP_DESC:"28041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -691,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -691,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1691 ,protection_group_id: -691, protection_element_id:-691], primaryKey: false);
      insert('organizations', [ id: 100677, nci_institute_code: "28042", name: "Et Maladies Infectieuses", city: "Poitiers", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -692,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28042",GROUP_DESC:"28042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -692,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -692,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1692 ,protection_group_id: -692, protection_element_id:-692], primaryKey: false);
      insert('organizations', [ id: 100678, nci_institute_code: "28043", name: "Hospital Saint-Antoine", city: "Paris", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -693,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28043",GROUP_DESC:"28043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -693,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -693,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1693 ,protection_group_id: -693, protection_element_id:-693], primaryKey: false);
      insert('organizations', [ id: 100679, nci_institute_code: "28044", name: "Pitie-Salpetriere Hopital", city: "75013 Paris", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -694,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28044",GROUP_DESC:"28044 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -694,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28044",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28044",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -694,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28044", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1694 ,protection_group_id: -694, protection_element_id:-694], primaryKey: false);
      insert('organizations', [ id: 100680, nci_institute_code: "28045", name: "Nouvel Hopital", city: "Poitiers", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -695,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28045",GROUP_DESC:"28045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -695,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -695,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1695 ,protection_group_id: -695, protection_element_id:-695], primaryKey: false);
      insert('organizations', [ id: 100681, nci_institute_code: "28046", name: "Hospital Victor Jousselin", city: "Dreux", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -696,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28046",GROUP_DESC:"28046 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -696,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28046",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28046",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -696,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28046", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1696 ,protection_group_id: -696, protection_element_id:-696], primaryKey: false);
      insert('organizations', [ id: 100682, nci_institute_code: "28047", name: "Hopital Saint-Jacques", city: "Nantes Cedex", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -697,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28047",GROUP_DESC:"28047 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -697,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28047",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28047",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -697,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28047", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1697 ,protection_group_id: -697, protection_element_id:-697], primaryKey: false);
      insert('organizations', [ id: 100683, nci_institute_code: "28048", name: "Hopital Instruction Des Armees", city: "Toulon", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -698,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28048",GROUP_DESC:"28048 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -698,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28048",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28048",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -698,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28048", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1698 ,protection_group_id: -698, protection_element_id:-698], primaryKey: false);
      insert('organizations', [ id: 100684, nci_institute_code: "28049", name: "Henri Mondor Univ/Hosp Center", city: "Creteil 94010 Cedex", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -699,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28049",GROUP_DESC:"28049 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -699,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28049",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28049",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -699,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28049", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1699 ,protection_group_id: -699, protection_element_id:-699], primaryKey: false);
      insert('organizations', [ id: 100685, nci_institute_code: "28050", name: "Hopital De Boisgu Illaume", city: "Boisgullaume", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -700,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28050",GROUP_DESC:"28050 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -700,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28050",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28050",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -700,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28050", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1700 ,protection_group_id: -700, protection_element_id:-700], primaryKey: false);
      insert('organizations', [ id: 100686, nci_institute_code: "28051", name: "Hopital De La Timone", city: "13385 Marseille", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -701,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28051",GROUP_DESC:"28051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -701,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -701,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1701 ,protection_group_id: -701, protection_element_id:-701], primaryKey: false);
      insert('organizations', [ id: 100687, nci_institute_code: "28052", name: "Centre Hopitalier Regional", city: "Amiens Cedex", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -702,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28052",GROUP_DESC:"28052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -702,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -702,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1702 ,protection_group_id: -702, protection_element_id:-702], primaryKey: false);
      insert('organizations', [ id: 100688, nci_institute_code: "28053", name: "Hospital Nord", city: "13015 Marseille", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -703,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28053",GROUP_DESC:"28053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -703,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -703,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1703 ,protection_group_id: -703, protection_element_id:-703], primaryKey: false);
      insert('organizations', [ id: 100689, nci_institute_code: "28054", name: "Centre Hopitalier Et Univ", city: "25000 Besancon Cedex", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -704,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28054",GROUP_DESC:"28054 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -704,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28054",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28054",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -704,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28054", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1704 ,protection_group_id: -704, protection_element_id:-704], primaryKey: false);
      insert('organizations', [ id: 100690, nci_institute_code: "28055", name: "Contre Le Cancer De Rennes", city: "35011 Rennes Cedex", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -705,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28055",GROUP_DESC:"28055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -705,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -705,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1705 ,protection_group_id: -705, protection_element_id:-705], primaryKey: false);
      insert('organizations', [ id: 100691, nci_institute_code: "28056", name: "Hopital Robert Debre", city: "51092 Reims Cedex", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -706,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28056",GROUP_DESC:"28056 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -706,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28056",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28056",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -706,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28056", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1706 ,protection_group_id: -706, protection_element_id:-706], primaryKey: false);
      insert('organizations', [ id: 100692, nci_institute_code: "28057", name: "Policlinique Hop Ambroise-Pare", city: "F-92100 Boulogne", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -707,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28057",GROUP_DESC:"28057 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -707,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28057",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28057",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -707,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28057", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1707 ,protection_group_id: -707, protection_element_id:-707], primaryKey: false);
      insert('organizations', [ id: 100693, nci_institute_code: "28058", name: "Hospital Saint-Camille", city: "94360 Bry Sur Marne", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -708,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28058",GROUP_DESC:"28058 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -708,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28058",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28058",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -708,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28058", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1708 ,protection_group_id: -708, protection_element_id:-708], primaryKey: false);
      insert('organizations', [ id: 100694, nci_institute_code: "28059", name: "Centre Hop Intercommunal", city: "64500 St-Jean-De-Luz", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -709,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28059",GROUP_DESC:"28059 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -709,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28059",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28059",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -709,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28059", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1709 ,protection_group_id: -709, protection_element_id:-709], primaryKey: false);
      insert('organizations', [ id: 100695, nci_institute_code: "28060", name: "Centre Paul Papin", city: "49036 Angers Cedex", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -710,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28060",GROUP_DESC:"28060 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -710,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28060",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28060",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -710,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28060", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1710 ,protection_group_id: -710, protection_element_id:-710], primaryKey: false);
      insert('organizations', [ id: 100696, nci_institute_code: "28061", name: "Centre Hopitalier", city: "Saint-Jean-De-Luz", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -711,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28061",GROUP_DESC:"28061 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -711,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28061",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28061",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -711,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28061", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1711 ,protection_group_id: -711, protection_element_id:-711], primaryKey: false);
      insert('organizations', [ id: 100697, nci_institute_code: "28062", name: "Hopital General", city: "F21033 Dijon Cedex", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -712,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28062",GROUP_DESC:"28062 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -712,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28062",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28062",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -712,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28062", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1712 ,protection_group_id: -712, protection_element_id:-712], primaryKey: false);
      insert('organizations', [ id: 100698, nci_institute_code: "28063", name: "Hospital Du Cluzeau", city: "Limoges", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -713,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28063",GROUP_DESC:"28063 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -713,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28063",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28063",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -713,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28063", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1713 ,protection_group_id: -713, protection_element_id:-713], primaryKey: false);
      insert('organizations', [ id: 100699, nci_institute_code: "28064", name: "Clinique Du Pont De Chaume", city: "82000 Montauban", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -714,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28064",GROUP_DESC:"28064 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -714,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28064",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28064",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -714,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28064", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1714 ,protection_group_id: -714, protection_element_id:-714], primaryKey: false);
    }

    void m28() {
        // all28 (25 terms)
      insert('organizations', [ id: 100700, nci_institute_code: "28066", name: "Hospital Saint Jacques", city: "Cedex", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -715,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28066",GROUP_DESC:"28066 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -715,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28066",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28066",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -715,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28066", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1715 ,protection_group_id: -715, protection_element_id:-715], primaryKey: false);
      insert('organizations', [ id: 100701, nci_institute_code: "28067", name: "Hospital Des Enfants", city: "Bordeau Dedex 33077", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -716,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28067",GROUP_DESC:"28067 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -716,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28067",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28067",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -716,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28067", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1716 ,protection_group_id: -716, protection_element_id:-716], primaryKey: false);
      insert('organizations', [ id: 100702, nci_institute_code: "28068", name: "Hospital De Neuilly Sur Seine", city: "Neuilly-Sur-Seine", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -717,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28068",GROUP_DESC:"28068 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -717,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28068",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28068",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -717,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28068", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1717 ,protection_group_id: -717, protection_element_id:-717], primaryKey: false);
      insert('organizations', [ id: 100703, nci_institute_code: "28069", name: "Hospital Sud 16", city: "Rennes Cedex 35022", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -718,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28069",GROUP_DESC:"28069 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -718,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28069",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28069",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -718,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28069", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1718 ,protection_group_id: -718, protection_element_id:-718], primaryKey: false);
      insert('organizations', [ id: 100704, nci_institute_code: "28070", name: "Centre Francois Baclesse", city: "14021 Caen Cedex", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -719,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28070",GROUP_DESC:"28070 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -719,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28070",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28070",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -719,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28070", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1719 ,protection_group_id: -719, protection_element_id:-719], primaryKey: false);
      insert('organizations', [ id: 100705, nci_institute_code: "28071", name: "Hospital De La Source", city: "Orleans Cedex, 45046", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -720,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28071",GROUP_DESC:"28071 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -720,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28071",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28071",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -720,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28071", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1720 ,protection_group_id: -720, protection_element_id:-720], primaryKey: false);
      insert('organizations', [ id: 100706, nci_institute_code: "28072", name: "Hopital De Brabois", city: "Vandoeuvre-Les-Nancy", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -721,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28072",GROUP_DESC:"28072 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -721,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28072",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28072",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -721,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28072", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1721 ,protection_group_id: -721, protection_element_id:-721], primaryKey: false);
      insert('organizations', [ id: 100707, nci_institute_code: "28074", name: "Hospices Civils De Strasbourg", city: "Strasbourg", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -722,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28074",GROUP_DESC:"28074 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -722,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28074",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28074",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -722,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28074", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1722 ,protection_group_id: -722, protection_element_id:-722], primaryKey: false);
      insert('organizations', [ id: 100708, nci_institute_code: "28075", name: "Angers Hospital", city: "Angers Cedex", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -723,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28075",GROUP_DESC:"28075 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -723,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28075",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28075",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -723,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28075", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1723 ,protection_group_id: -723, protection_element_id:-723], primaryKey: false);
      insert('organizations', [ id: 100709, nci_institute_code: "28076", name: "Hospital Necker", city: "Paris Cedex 17", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -724,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28076",GROUP_DESC:"28076 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -724,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28076",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28076",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -724,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28076", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1724 ,protection_group_id: -724, protection_element_id:-724], primaryKey: false);
      insert('organizations', [ id: 100710, nci_institute_code: "28077", name: "Chu Purpan", city: "31059 Toulouse Cedex", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -725,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28077",GROUP_DESC:"28077 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -725,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28077",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28077",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -725,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28077", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1725 ,protection_group_id: -725, protection_element_id:-725], primaryKey: false);
      insert('organizations', [ id: 100711, nci_institute_code: "28078", name: "Hospital Bretonneau", city: "Tours Codex", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -726,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28078",GROUP_DESC:"28078 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -726,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28078",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28078",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -726,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28078", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1726 ,protection_group_id: -726, protection_element_id:-726], primaryKey: false);
      insert('organizations', [ id: 100712, nci_institute_code: "28079", name: "Hospital Claude Hurlez Chru", city: "Lille Cedex", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -727,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28079",GROUP_DESC:"28079 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -727,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28079",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28079",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -727,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28079", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1727 ,protection_group_id: -727, protection_element_id:-727], primaryKey: false);
      insert('organizations', [ id: 100713, nci_institute_code: "28080", name: "Institut Paoli Calmettes", city: "13273 Marseille 9", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -728,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28080",GROUP_DESC:"28080 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -728,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28080",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28080",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -728,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28080", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1728 ,protection_group_id: -728, protection_element_id:-728], primaryKey: false);
      insert('organizations', [ id: 100714, nci_institute_code: "28081", name: "Centre Hospitalier Universitaire", city: "Strasbourg", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -729,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28081",GROUP_DESC:"28081 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -729,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28081",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28081",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -729,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28081", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1729 ,protection_group_id: -729, protection_element_id:-729], primaryKey: false);
      insert('organizations', [ id: 100715, nci_institute_code: "28082", name: "Laennec Hopital", city: "Paris", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -730,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28082",GROUP_DESC:"28082 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -730,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28082",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28082",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -730,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28082", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1730 ,protection_group_id: -730, protection_element_id:-730], primaryKey: false);
      insert('organizations', [ id: 100716, nci_institute_code: "28083", name: "Ctr Hosp Regional De Bordeaux", city: "33604 Pessac", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -731,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28083",GROUP_DESC:"28083 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -731,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28083",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28083",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -731,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28083", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1731 ,protection_group_id: -731, protection_element_id:-731], primaryKey: false);
      insert('organizations', [ id: 100717, nci_institute_code: "28084", name: "C.H.U. Hopital D'Enfants", city: "21034 Dijon Cedex", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -732,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28084",GROUP_DESC:"28084 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -732,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28084",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28084",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -732,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28084", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1732 ,protection_group_id: -732, protection_element_id:-732], primaryKey: false);
      insert('organizations', [ id: 100718, nci_institute_code: "28085", name: "Hopital Chalucet", city: "Toulon", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -733,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28085",GROUP_DESC:"28085 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -733,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28085",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28085",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -733,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28085", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1733 ,protection_group_id: -733, protection_element_id:-733], primaryKey: false);
      insert('organizations', [ id: 100719, nci_institute_code: "28086", name: "Praticien Hospitalier", city: "80054 Amiens Cedex", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -734,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28086",GROUP_DESC:"28086 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -734,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28086",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28086",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -734,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28086", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1734 ,protection_group_id: -734, protection_element_id:-734], primaryKey: false);
      insert('organizations', [ id: 100720, nci_institute_code: "28091", name: "Hopital Pasteur", city: "68021 Colmar Cedex", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -735,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28091",GROUP_DESC:"28091 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -735,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28091",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28091",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -735,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28091", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1735 ,protection_group_id: -735, protection_element_id:-735], primaryKey: false);
      insert('organizations', [ id: 100721, nci_institute_code: "28092", name: "Centre Hospitalier", city: "22023 St Brieuc", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -736,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28092",GROUP_DESC:"28092 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -736,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28092",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28092",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -736,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28092", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1736 ,protection_group_id: -736, protection_element_id:-736], primaryKey: false);
      insert('organizations', [ id: 100722, nci_institute_code: "28093", name: "Hopital Jacques Monod", city: "76083 Le Havre Cedex", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -737,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28093",GROUP_DESC:"28093 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -737,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28093",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28093",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -737,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28093", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1737 ,protection_group_id: -737, protection_element_id:-737], primaryKey: false);
      insert('organizations', [ id: 100723, nci_institute_code: "28094", name: "Hopital De Rennes", city: "Pontchaillou 35", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -738,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28094",GROUP_DESC:"28094 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -738,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28094",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28094",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -738,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28094", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1738 ,protection_group_id: -738, protection_element_id:-738], primaryKey: false);
      insert('organizations', [ id: 100724, nci_institute_code: "28095", name: "University of Nantes", city: "44035 Nantes Cedex", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -739,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28095",GROUP_DESC:"28095 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -739,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28095",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28095",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -739,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28095", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1739 ,protection_group_id: -739, protection_element_id:-739], primaryKey: false);
    }

    void m29() {
        // all29 (25 terms)
      insert('organizations', [ id: 100725, nci_institute_code: "28096", name: "Hopital Du Haut-Leveque", city: "33604 Pessac", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -740,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28096",GROUP_DESC:"28096 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -740,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28096",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28096",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -740,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28096", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1740 ,protection_group_id: -740, protection_element_id:-740], primaryKey: false);
      insert('organizations', [ id: 100726, nci_institute_code: "28097", name: "Ctre Hospitalier Louise Michel", city: "Evry Cedex", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -741,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28097",GROUP_DESC:"28097 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -741,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28097",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28097",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -741,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28097", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1741 ,protection_group_id: -741, protection_element_id:-741], primaryKey: false);
      insert('organizations', [ id: 100727, nci_institute_code: "28098", name: "Centre Hospitalier Lyon-Sud", city: "69310 Pierre Benite", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -742,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28098",GROUP_DESC:"28098 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -742,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28098",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28098",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -742,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28098", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1742 ,protection_group_id: -742, protection_element_id:-742], primaryKey: false);
      insert('organizations', [ id: 100728, nci_institute_code: "28099", name: "Service D'Hematologie", city: "31059 Toulouse Cedex", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -743,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28099",GROUP_DESC:"28099 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -743,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28099",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28099",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -743,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28099", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1743 ,protection_group_id: -743, protection_element_id:-743], primaryKey: false);
      insert('organizations', [ id: 100729, nci_institute_code: "28100", name: "Svc Des Maladies Du Sang", city: "49033 Angers", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -744,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28100",GROUP_DESC:"28100 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -744,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28100",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28100",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -744,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28100", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1744 ,protection_group_id: -744, protection_element_id:-744], primaryKey: false);
      insert('organizations', [ id: 100730, nci_institute_code: "28101", name: "Hoechst Marion Roussel", city: "93235 Romainville", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -745,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28101",GROUP_DESC:"28101 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -745,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28101",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28101",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -745,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28101", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1745 ,protection_group_id: -745, protection_element_id:-745], primaryKey: false);
      insert('organizations', [ id: 100731, nci_institute_code: "28102", name: "Hopital D'Enfants Trousseau", city: "Paris", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -746,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28102",GROUP_DESC:"28102 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -746,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28102",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28102",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -746,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28102", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1746 ,protection_group_id: -746, protection_element_id:-746], primaryKey: false);
      insert('organizations', [ id: 100732, nci_institute_code: "28103", name: "Center Claudius Regaud", city: "Toulouse", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -747,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28103",GROUP_DESC:"28103 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -747,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28103",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28103",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -747,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28103", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1747 ,protection_group_id: -747, protection_element_id:-747], primaryKey: false);
      insert('organizations', [ id: 100733, nci_institute_code: "28104", name: "Institut De Cancerologie", city: "Villejuif", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -748,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28104",GROUP_DESC:"28104 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -748,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28104",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28104",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -748,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28104", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1748 ,protection_group_id: -748, protection_element_id:-748], primaryKey: false);
      insert('organizations', [ id: 100734, nci_institute_code: "28105", name: "Centre Georges-Francois-Leclerc", city: "Dijon", state: "Cedex", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -749,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28105",GROUP_DESC:"28105 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -749,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28105",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28105",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -749,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28105", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1749 ,protection_group_id: -749, protection_element_id:-749], primaryKey: false);
      insert('organizations', [ id: 100735, nci_institute_code: "28106", name: "Clinique Hartmann", city: "Neuilly-Sur-Seine", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -750,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28106",GROUP_DESC:"28106 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -750,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28106",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28106",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -750,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28106", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1750 ,protection_group_id: -750, protection_element_id:-750], primaryKey: false);
      insert('organizations', [ id: 100736, nci_institute_code: "28107", name: "Institut Curie Paris", city: "Paris", state: "Cedex", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -751,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28107",GROUP_DESC:"28107 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -751,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28107",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28107",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -751,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28107", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1751 ,protection_group_id: -751, protection_element_id:-751], primaryKey: false);
      insert('organizations', [ id: 100737, nci_institute_code: "28108", name: "Clnique De L'Orangerie", city: "Leperreux Sur Marne", state: "94170", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -752,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28108",GROUP_DESC:"28108 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -752,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28108",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28108",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -752,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28108", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1752 ,protection_group_id: -752, protection_element_id:-752], primaryKey: false);
      insert('organizations', [ id: 100738, nci_institute_code: "28109", name: "Institut Developpement Produits Sante", city: "33370 Artigues Pres Bordeaux", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -753,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28109",GROUP_DESC:"28109 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -753,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28109",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28109",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -753,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28109", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1753 ,protection_group_id: -753, protection_element_id:-753], primaryKey: false);
      insert('organizations', [ id: 100739, nci_institute_code: "28110", name: "Ecole Normale Superieure CNRS", city: "Paris", state: "Cedex 05", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -754,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28110",GROUP_DESC:"28110 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -754,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28110",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28110",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -754,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28110", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1754 ,protection_group_id: -754, protection_element_id:-754], primaryKey: false);
      insert('organizations', [ id: 100740, nci_institute_code: "28111", name: "Institut de Biologie Structurale Jean-Pierre Ebel", city: "Grenoble", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -755,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28111",GROUP_DESC:"28111 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -755,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28111",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28111",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -755,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28111", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1755 ,protection_group_id: -755, protection_element_id:-755], primaryKey: false);
      insert('organizations', [ id: 100741, nci_institute_code: "28112", name: "Universite Paul Sabatier", city: "Toulouse Cedex 4", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -756,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28112",GROUP_DESC:"28112 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -756,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28112",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28112",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -756,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28112", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1756 ,protection_group_id: -756, protection_element_id:-756], primaryKey: false);
      insert('organizations', [ id: 100742, nci_institute_code: "28113", name: "Institut National de la Sant\351 et de la Recherche M\351dicale", city: "Paris", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -757,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28113",GROUP_DESC:"28113 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -757,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28113",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28113",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -757,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28113", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1757 ,protection_group_id: -757, protection_element_id:-757], primaryKey: false);
      insert('organizations', [ id: 100743, nci_institute_code: "28114", name: "Commissariat a l'Energie Atomic", city: "Fontenay-aux-Roses", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -758,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28114",GROUP_DESC:"28114 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -758,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28114",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28114",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -758,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28114", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1758 ,protection_group_id: -758, protection_element_id:-758], primaryKey: false);
      insert('organizations', [ id: 100744, nci_institute_code: "28115", name: "Universite de Bourgogne", city: "Dijon", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -759,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28115",GROUP_DESC:"28115 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -759,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28115",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28115",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -759,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28115", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1759 ,protection_group_id: -759, protection_element_id:-759], primaryKey: false);
      insert('organizations', [ id: 100745, nci_institute_code: "28116", name: "Centre National de la Recherche Scientifque 8612", city: "Chatenay-Malabry", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -760,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28116",GROUP_DESC:"28116 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -760,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28116",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28116",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -760,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28116", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1760 ,protection_group_id: -760, protection_element_id:-760], primaryKey: false);
      insert('organizations', [ id: 100746, nci_institute_code: "29001", name: "University of Ghana Medical School", city: "Accra", country: "Ghana"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -761,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.29001",GROUP_DESC:"29001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -761,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.29001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.29001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -761,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.29001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1761 ,protection_group_id: -761, protection_element_id:-761], primaryKey: false);
      insert('organizations', [ id: 100747, nci_institute_code: "29002", name: "Korle Bu Hospital", city: "Accra", country: "Ghana"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -762,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.29002",GROUP_DESC:"29002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -762,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.29002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.29002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -762,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.29002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1762 ,protection_group_id: -762, protection_element_id:-762], primaryKey: false);
      insert('organizations', [ id: 100748, nci_institute_code: "30001", name: "Marika Eliadi Hospital", city: "Athens", country: "Greece"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -763,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.30001",GROUP_DESC:"30001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -763,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.30001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.30001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -763,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.30001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1763 ,protection_group_id: -763, protection_element_id:-763], primaryKey: false);
      insert('organizations', [ id: 100749, nci_institute_code: "30002", name: "Cancer Institute of Piraeus", city: "Piraeus  18537", country: "Greece"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -764,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.30002",GROUP_DESC:"30002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -764,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.30002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.30002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -764,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.30002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1764 ,protection_group_id: -764, protection_element_id:-764], primaryKey: false);
    }

    void m30() {
        // all30 (25 terms)
      insert('organizations', [ id: 100750, nci_institute_code: "30003", name: "Athens Naval and Veterans Hospital", city: "Athens", country: "Greece"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -765,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.30003",GROUP_DESC:"30003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -765,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.30003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.30003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -765,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.30003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1765 ,protection_group_id: -765, protection_element_id:-765], primaryKey: false);
      insert('organizations', [ id: 100751, nci_institute_code: "30004", name: "Alexandia Hospital", city: "Athens", country: "Greece"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -766,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.30004",GROUP_DESC:"30004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -766,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.30004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.30004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -766,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.30004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1766 ,protection_group_id: -766, protection_element_id:-766], primaryKey: false);
      insert('organizations', [ id: 100752, nci_institute_code: "30005", name: "Nimts Va Hospital", city: "Athens", country: "Greece"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -767,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.30005",GROUP_DESC:"30005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -767,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.30005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.30005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -767,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.30005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1767 ,protection_group_id: -767, protection_element_id:-767], primaryKey: false);
      insert('organizations', [ id: 100753, nci_institute_code: "30006", name: "Aretaion Hospital", city: "Athens", country: "Greece"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -768,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.30006",GROUP_DESC:"30006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -768,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.30006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.30006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -768,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.30006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1768 ,protection_group_id: -768, protection_element_id:-768], primaryKey: false);
      insert('organizations', [ id: 100754, nci_institute_code: "30007", name: "General Hospital of Piraeus", city: "Piraeus", country: "Greece"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -769,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.30007",GROUP_DESC:"30007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -769,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.30007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.30007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -769,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.30007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1769 ,protection_group_id: -769, protection_element_id:-769], primaryKey: false);
      insert('organizations', [ id: 100755, nci_institute_code: "30008", name: "Red Cross General Hospital", city: "Athens 607", country: "Greece"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -770,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.30008",GROUP_DESC:"30008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -770,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.30008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.30008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -770,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.30008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1770 ,protection_group_id: -770, protection_element_id:-770], primaryKey: false);
      insert('organizations', [ id: 100756, nci_institute_code: "30009", name: "Evangelismos Hospital", city: "Athens 139", country: "Greece"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -771,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.30009",GROUP_DESC:"30009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -771,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.30009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.30009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -771,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.30009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1771 ,protection_group_id: -771, protection_element_id:-771], primaryKey: false);
      insert('organizations', [ id: 100757, nci_institute_code: "30011", name: "University of Athens School of Medicine", city: "Athens 609", country: "Greece"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -772,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.30011",GROUP_DESC:"30011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -772,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.30011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.30011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -772,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.30011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1772 ,protection_group_id: -772, protection_element_id:-772], primaryKey: false);
      insert('organizations', [ id: 100758, nci_institute_code: "30012", name: "Saint Sophia Childrens Hospital", city: "Athens", country: "Greece"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -773,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.30012",GROUP_DESC:"30012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -773,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.30012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.30012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -773,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.30012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1773 ,protection_group_id: -773, protection_element_id:-773], primaryKey: false);
      insert('organizations', [ id: 100759, nci_institute_code: "30013", name: "St. Savas Hospital", city: "Athen", country: "Greece"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -774,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.30013",GROUP_DESC:"30013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -774,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.30013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.30013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -774,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.30013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1774 ,protection_group_id: -774, protection_element_id:-774], primaryKey: false);
      insert('organizations', [ id: 100760, nci_institute_code: "30014", name: "Childrens Hosp A. Kyriakou", city: "Gouthi Athens", country: "Greece"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -775,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.30014",GROUP_DESC:"30014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -775,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.30014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.30014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -775,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.30014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1775 ,protection_group_id: -775, protection_element_id:-775], primaryKey: false);
      insert('organizations', [ id: 100761, nci_institute_code: "30015", name: "Laikon General Hospital", city: "Athens, 115 27", country: "Greece"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -776,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.30015",GROUP_DESC:"30015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -776,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.30015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.30015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -776,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.30015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1776 ,protection_group_id: -776, protection_element_id:-776], primaryKey: false);
      insert('organizations', [ id: 100762, nci_institute_code: "30016", name: "Hygeia Hospital", city: "Marovsi Athens", country: "Greece"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -777,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.30016",GROUP_DESC:"30016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -777,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.30016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.30016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -777,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.30016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1777 ,protection_group_id: -777, protection_element_id:-777], primaryKey: false);
      insert('organizations', [ id: 100763, nci_institute_code: "30017", name: "University of Athens", city: "Athens", country: "Greece"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -778,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.30017",GROUP_DESC:"30017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -778,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.30017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.30017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -778,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.30017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1778 ,protection_group_id: -778, protection_element_id:-778], primaryKey: false);
      insert('organizations', [ id: 100764, nci_institute_code: "34001", name: "University of Hong Kong", city: "Hong Kong", country: "China"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -779,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.34001",GROUP_DESC:"34001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -779,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.34001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.34001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -779,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.34001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1779 ,protection_group_id: -779, protection_element_id:-779], primaryKey: false);
      insert('organizations', [ id: 100765, nci_institute_code: "34002", name: "Queen Mary Hospital", city: "Hong Kong", country: "China"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -780,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.34002",GROUP_DESC:"34002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -780,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.34002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.34002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -780,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.34002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1780 ,protection_group_id: -780, protection_element_id:-780], primaryKey: false);
      insert('organizations', [ id: 100766, nci_institute_code: "34003", name: "Prince of Wales Hospital", city: "Shatin", state: "Hong Kong", country: "China"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -781,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.34003",GROUP_DESC:"34003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -781,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.34003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.34003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -781,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.34003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1781 ,protection_group_id: -781, protection_element_id:-781], primaryKey: false);
      insert('organizations', [ id: 100767, nci_institute_code: "34004", name: "Hong Kong Sanatorium and Hospital", city: "Hong Kong", country: "China"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -782,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.34004",GROUP_DESC:"34004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -782,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.34004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.34004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -782,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.34004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1782 ,protection_group_id: -782, protection_element_id:-782], primaryKey: false);
      insert('organizations', [ id: 100768, nci_institute_code: "34005", name: "The Chinese University of Hong Kong", city: "Shantin", state: "New Territories", country: "Hong Kong"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -783,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.34005",GROUP_DESC:"34005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -783,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.34005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.34005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -783,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.34005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1783 ,protection_group_id: -783, protection_element_id:-783], primaryKey: false);
      insert('organizations', [ id: 100769, nci_institute_code: "35001", name: "Szeged, University Medical School", city: "Szeged, H-6701, Pb46", country: "Hungary"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -784,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.35001",GROUP_DESC:"35001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -784,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.35001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.35001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -784,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.35001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1784 ,protection_group_id: -784, protection_element_id:-784], primaryKey: false);
      insert('organizations', [ id: 100770, nci_institute_code: "35002", name: "University of Pecs", city: "Pecs", country: "Hungary"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -785,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.35002",GROUP_DESC:"35002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -785,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.35002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.35002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -785,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.35002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1785 ,protection_group_id: -785, protection_element_id:-785], primaryKey: false);
      insert('organizations', [ id: 100771, nci_institute_code: "35003", name: "Semmelweis Medical University", city: "Budapest 1083", country: "Hungary"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -786,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.35003",GROUP_DESC:"35003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -786,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.35003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.35003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -786,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.35003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1786 ,protection_group_id: -786, protection_element_id:-786], primaryKey: false);
      insert('organizations', [ id: 100772, nci_institute_code: "35004", name: "Uranus Children's Cancer Foundation", city: "Kokarda U.26", country: "Hungary"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -787,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.35004",GROUP_DESC:"35004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -787,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.35004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.35004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -787,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.35004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1787 ,protection_group_id: -787, protection_element_id:-787], primaryKey: false);
      insert('organizations', [ id: 100773, nci_institute_code: "35005", name: "National Institute of Oncology", city: "Budapest", country: "Hungary"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -788,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.35005",GROUP_DESC:"35005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -788,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.35005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.35005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -788,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.35005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1788 ,protection_group_id: -788, protection_element_id:-788], primaryKey: false);
      insert('organizations', [ id: 100774, nci_institute_code: "36001", name: "University Hospital Landspitalinn", city: "Reykjavik", country: "Iceland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -789,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.36001",GROUP_DESC:"36001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -789,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.36001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.36001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -789,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.36001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1789 ,protection_group_id: -789, protection_element_id:-789], primaryKey: false);
    }

    void m31() {
        // all31 (25 terms)
      insert('organizations', [ id: 100775, nci_institute_code: "37002", name: "Hospital Calcutta", city: "Calcutta", country: "India"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -790,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37002",GROUP_DESC:"37002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -790,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.37002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.37002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -790,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1790 ,protection_group_id: -790, protection_element_id:-790], primaryKey: false);
      insert('organizations', [ id: 100776, nci_institute_code: "37003", name: "Christian Med College and Hospital", city: "Ludhiana", country: "India"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -791,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37003",GROUP_DESC:"37003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -791,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.37003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.37003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -791,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1791 ,protection_group_id: -791, protection_element_id:-791], primaryKey: false);
      insert('organizations', [ id: 100777, nci_institute_code: "37005", name: "Mayo Hospital", city: "Pakistan", country: "India"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -792,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37005",GROUP_DESC:"37005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -792,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.37005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.37005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -792,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1792 ,protection_group_id: -792, protection_element_id:-792], primaryKey: false);
      insert('organizations', [ id: 100778, nci_institute_code: "37006", name: "King Edward Hospital", city: "Rasta Peth", country: "India"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -793,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37006",GROUP_DESC:"37006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -793,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.37006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.37006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -793,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1793 ,protection_group_id: -793, protection_element_id:-793], primaryKey: false);
      insert('organizations', [ id: 100779, nci_institute_code: "37007", name: "Karnataka Cancer Therapy and Research Institute", city: "Navanagar", state: "Hubli", country: "India"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -794,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37007",GROUP_DESC:"37007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -794,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.37007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.37007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -794,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1794 ,protection_group_id: -794, protection_element_id:-794], primaryKey: false);
      insert('organizations', [ id: 100780, nci_institute_code: "37008", name: "Tata Memorial Hospital", city: "Parel Mumbai", country: "India"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -795,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37008",GROUP_DESC:"37008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -795,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.37008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.37008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -795,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1795 ,protection_group_id: -795, protection_element_id:-795], primaryKey: false);
      insert('organizations', [ id: 100781, nci_institute_code: "37009", name: "Command Hospital", city: "Calcutta", country: "India"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -796,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37009",GROUP_DESC:"37009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -796,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.37009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.37009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -796,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1796 ,protection_group_id: -796, protection_element_id:-796], primaryKey: false);
      insert('organizations', [ id: 100782, nci_institute_code: "37010", name: "All India Institute of Medical Services, New Delhi, India", city: "New Delhi", country: "India"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -797,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37010",GROUP_DESC:"37010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -797,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.37010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.37010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -797,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1797 ,protection_group_id: -797, protection_element_id:-797], primaryKey: false);
      insert('organizations', [ id: 100783, nci_institute_code: "37011", name: "Madras Med Coll/ Govt Gen Hosp", city: "Madras", country: "India"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -798,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37011",GROUP_DESC:"37011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -798,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.37011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.37011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -798,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1798 ,protection_group_id: -798, protection_element_id:-798], primaryKey: false);
      insert('organizations', [ id: 100784, nci_institute_code: "37012", name: "Kidwai Memorial Institute Oncology", city: "Bangalore", country: "India"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -799,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37012",GROUP_DESC:"37012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -799,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.37012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.37012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -799,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1799 ,protection_group_id: -799, protection_element_id:-799], primaryKey: false);
      insert('organizations', [ id: 100785, nci_institute_code: "37013", name: "Calicut Medical College", city: "Kerala", country: "India"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -800,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37013",GROUP_DESC:"37013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -800,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.37013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.37013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -800,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1800 ,protection_group_id: -800, protection_element_id:-800], primaryKey: false);
      insert('organizations', [ id: 100786, nci_institute_code: "37014", name: "Nijams Institute", city: "Hyderabad", country: "India"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -801,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37014",GROUP_DESC:"37014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -801,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.37014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.37014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -801,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1801 ,protection_group_id: -801, protection_element_id:-801], primaryKey: false);
      insert('organizations', [ id: 100787, nci_institute_code: "37015", name: "Cancer Institute", city: "Madras", country: "India"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -802,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37015",GROUP_DESC:"37015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -802,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.37015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.37015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -802,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1802 ,protection_group_id: -802, protection_element_id:-802], primaryKey: false);
      insert('organizations', [ id: 100788, nci_institute_code: "37016", name: "Apollo Speciality Hospital", city: "Chennai", country: "India"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -803,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37016",GROUP_DESC:"37016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -803,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.37016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.37016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -803,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1803 ,protection_group_id: -803, protection_element_id:-803], primaryKey: false);
      insert('organizations', [ id: 100789, nci_institute_code: "37017", name: "ADI Professionals", city: "Chandigarh", country: "India"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -804,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37017",GROUP_DESC:"37017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -804,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.37017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.37017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -804,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1804 ,protection_group_id: -804, protection_element_id:-804], primaryKey: false);
      insert('organizations', [ id: 100790, nci_institute_code: "37018", name: "Centre for Cellular and Molecular Biology - India", city: "Hyderabad", country: "India"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -805,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37018",GROUP_DESC:"37018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -805,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.37018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.37018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -805,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1805 ,protection_group_id: -805, protection_element_id:-805], primaryKey: false);
      insert('organizations', [ id: 100791, nci_institute_code: "37019", name: "Sir Ganga Ram Hospital", city: "New Delhi", country: "India"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -806,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37019",GROUP_DESC:"37019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -806,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.37019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.37019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -806,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1806 ,protection_group_id: -806, protection_element_id:-806], primaryKey: false);
      insert('organizations', [ id: 100792, nci_institute_code: "38001", name: "Jam Hospital", city: "Tehran", country: "Iran"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -807,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.38001",GROUP_DESC:"38001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -807,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.38001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.38001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -807,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.38001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1807 ,protection_group_id: -807, protection_element_id:-807], primaryKey: false);
      insert('organizations', [ id: 100793, nci_institute_code: "38002", name: "Nioc - Central Hospital", city: "Tehran", country: "Iran"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -808,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.38002",GROUP_DESC:"38002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -808,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.38002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.38002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -808,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.38002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1808 ,protection_group_id: -808, protection_element_id:-808], primaryKey: false);
      insert('organizations', [ id: 100794, nci_institute_code: "39001", name: "Royal Victoria Hospital.", city: "Belfast", country: "Ireland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -809,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39001",GROUP_DESC:"39001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -809,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.39001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.39001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -809,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1809 ,protection_group_id: -809, protection_element_id:-809], primaryKey: false);
      insert('organizations', [ id: 100795, nci_institute_code: "39002", name: "Saint Vincents Hospital", city: "Dublin", country: "Ireland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -810,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39002",GROUP_DESC:"39002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -810,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.39002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.39002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -810,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1810 ,protection_group_id: -810, protection_element_id:-810], primaryKey: false);
      insert('organizations', [ id: 100796, nci_institute_code: "39003", name: "Saint Lukes Hospital.", city: "Dublin 6", country: "Ireland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -811,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39003",GROUP_DESC:"39003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -811,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.39003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.39003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -811,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1811 ,protection_group_id: -811, protection_element_id:-811], primaryKey: false);
      insert('organizations', [ id: 100797, nci_institute_code: "39004", name: "Adelaide & Meath Hospital / National Children's Hospital", city: "Dublin", country: "Ireland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -812,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39004",GROUP_DESC:"39004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -812,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.39004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.39004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -812,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1812 ,protection_group_id: -812, protection_element_id:-812], primaryKey: false);
      insert('organizations', [ id: 100798, nci_institute_code: "39005", name: "Saint Sinbars Hospital", city: "Cork", country: "Ireland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -813,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39005",GROUP_DESC:"39005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -813,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.39005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.39005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -813,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1813 ,protection_group_id: -813, protection_element_id:-813], primaryKey: false);
      insert('organizations', [ id: 100799, nci_institute_code: "39007", name: "Bon Secours Hospital", city: "Cork", country: "Ireland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -814,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39007",GROUP_DESC:"39007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -814,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.39007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.39007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -814,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1814 ,protection_group_id: -814, protection_element_id:-814], primaryKey: false);
    }

    void m32() {
        // all32 (25 terms)
      insert('organizations', [ id: 100800, nci_institute_code: "39008", name: "Natl Childrens Hospital", city: "Dublin", country: "Ireland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -815,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39008",GROUP_DESC:"39008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -815,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.39008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.39008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -815,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1815 ,protection_group_id: -815, protection_element_id:-815], primaryKey: false);
      insert('organizations', [ id: 100801, nci_institute_code: "39009", name: "Mercy Hospital", city: "Cork", country: "Ireland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -816,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39009",GROUP_DESC:"39009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -816,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.39009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.39009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -816,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1816 ,protection_group_id: -816, protection_element_id:-816], primaryKey: false);
      insert('organizations', [ id: 100802, nci_institute_code: "39010", name: "St. James Hospital", city: "Dublin", country: "Ireland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -817,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39010",GROUP_DESC:"39010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -817,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.39010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.39010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -817,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1817 ,protection_group_id: -817, protection_element_id:-817], primaryKey: false);
      insert('organizations', [ id: 100803, nci_institute_code: "39011", name: "Mercers Hospital", city: "Dublin 2", country: "Ireland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -818,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39011",GROUP_DESC:"39011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -818,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.39011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.39011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -818,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1818 ,protection_group_id: -818, protection_element_id:-818], primaryKey: false);
      insert('organizations', [ id: 100804, nci_institute_code: "39012", name: "Royal Belfast Hospital/Sick Children", city: "Belfast, Bt126ba", country: "Ireland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -819,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39012",GROUP_DESC:"39012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -819,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.39012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.39012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -819,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1819 ,protection_group_id: -819, protection_element_id:-819], primaryKey: false);
      insert('organizations', [ id: 100805, nci_institute_code: "39013", name: "Belfast City Hospital", city: "Belfast Bt9 7ad", country: "Ireland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -820,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39013",GROUP_DESC:"39013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -820,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.39013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.39013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -820,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1820 ,protection_group_id: -820, protection_element_id:-820], primaryKey: false);
      insert('organizations', [ id: 100806, nci_institute_code: "39015", name: "Mater Private Hospital", city: "Dublin", country: "Ireland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -821,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39015",GROUP_DESC:"39015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -821,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.39015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.39015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -821,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1821 ,protection_group_id: -821, protection_element_id:-821], primaryKey: false);
      insert('organizations', [ id: 100807, nci_institute_code: "39017", name: "Altnagelvin Hospital", city: "Londonderry", country: "Ireland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -822,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39017",GROUP_DESC:"39017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -822,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.39017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.39017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -822,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1822 ,protection_group_id: -822, protection_element_id:-822], primaryKey: false);
      insert('organizations', [ id: 100808, nci_institute_code: "39018", name: "Cork University Hospital", city: "Cork", country: "Ireland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -823,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39018",GROUP_DESC:"39018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -823,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.39018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.39018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -823,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1823 ,protection_group_id: -823, protection_element_id:-823], primaryKey: false);
      insert('organizations', [ id: 100809, nci_institute_code: "39019", name: "National University of Ireland Galway", city: "Galway", country: "Ireland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -824,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39019",GROUP_DESC:"39019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -824,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.39019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.39019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -824,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1824 ,protection_group_id: -824, protection_element_id:-824], primaryKey: false);
      insert('organizations', [ id: 100810, nci_institute_code: "39020", name: "Royal College of Surgeons In Ireland", city: "Stephens Green Dublin", country: "Ireland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -825,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39020",GROUP_DESC:"39020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -825,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.39020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.39020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -825,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1825 ,protection_group_id: -825, protection_element_id:-825], primaryKey: false);
      insert('organizations', [ id: 100811, nci_institute_code: "39021", name: "Beaumont Hospital", city: "Dublin 9", country: "Ireland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -826,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39021",GROUP_DESC:"39021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -826,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.39021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.39021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -826,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1826 ,protection_group_id: -826, protection_element_id:-826], primaryKey: false);
      insert('organizations', [ id: 100812, nci_institute_code: "39022", name: "Mid-Western Regional Hospital", city: "Limerick", country: "Ireland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -827,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39022",GROUP_DESC:"39022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -827,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.39022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.39022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -827,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1827 ,protection_group_id: -827, protection_element_id:-827], primaryKey: false);
      insert('organizations', [ id: 100813, nci_institute_code: "39023", name: "Waterford Regional Hospital", city: "Waterford", country: "Ireland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -828,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39023",GROUP_DESC:"39023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -828,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.39023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.39023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -828,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1828 ,protection_group_id: -828, protection_element_id:-828], primaryKey: false);
      insert('organizations', [ id: 100814, nci_institute_code: "39024", name: "Irish Clinical Oncology Research Group", city: "Dublin 2", country: "Ireland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -829,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39024",GROUP_DESC:"39024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -829,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.39024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.39024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -829,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1829 ,protection_group_id: -829, protection_element_id:-829], primaryKey: false);
      insert('organizations', [ id: 100815, nci_institute_code: "39025", name: "University College Hospital Galway", city: "Galway", country: "Ireland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -830,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39025",GROUP_DESC:"39025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -830,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.39025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.39025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -830,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1830 ,protection_group_id: -830, protection_element_id:-830], primaryKey: false);
      insert('organizations', [ id: 100816, nci_institute_code: "39026", name: "Midland Regional Hospital at Tullamore", city: "Tullamore", state: "Co Offaly", country: "Ireland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -831,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39026",GROUP_DESC:"39026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -831,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.39026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.39026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -831,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1831 ,protection_group_id: -831, protection_element_id:-831], primaryKey: false);
      insert('organizations', [ id: 100817, nci_institute_code: "39027", name: "South Infirmary Victoria Hospital", city: "Cork", country: "Ireland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -832,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39027",GROUP_DESC:"39027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -832,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.39027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.39027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -832,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1832 ,protection_group_id: -832, protection_element_id:-832], primaryKey: false);
      insert('organizations', [ id: 100818, nci_institute_code: "39028", name: "Letterkenny General Hospital", city: "Letterkenny", country: "Ireland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -833,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39028",GROUP_DESC:"39028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -833,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.39028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.39028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -833,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1833 ,protection_group_id: -833, protection_element_id:-833], primaryKey: false);
      insert('organizations', [ id: 100819, nci_institute_code: "39029", name: "Sligo General Hospital", city: "Sligo", country: "Ireland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -834,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39029",GROUP_DESC:"39029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -834,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.39029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.39029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -834,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1834 ,protection_group_id: -834, protection_element_id:-834], primaryKey: false);
      insert('organizations', [ id: 100820, nci_institute_code: "39030", name: "Mater Misericordiae University Hospital", city: "Dublin", country: "Ireland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -835,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39030",GROUP_DESC:"39030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -835,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.39030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.39030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -835,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1835 ,protection_group_id: -835, protection_element_id:-835], primaryKey: false);
      insert('organizations', [ id: 100821, nci_institute_code: "40001", name: "Northern Israel Oncology Center", city: "Haifa 35254", country: "Israel"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -836,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40001",GROUP_DESC:"40001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -836,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.40001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.40001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -836,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1836 ,protection_group_id: -836, protection_element_id:-836], primaryKey: false);
      insert('organizations', [ id: 100822, nci_institute_code: "40002", name: "Chaim Sheba Medical Center", city: "Tel Hashomer", country: "Israel"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -837,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40002",GROUP_DESC:"40002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -837,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.40002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.40002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -837,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1837 ,protection_group_id: -837, protection_element_id:-837], primaryKey: false);
      insert('organizations', [ id: 100823, nci_institute_code: "40003", name: "Government Hospital", city: "Tel-Hashomer", country: "Israel"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -838,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40003",GROUP_DESC:"40003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -838,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.40003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.40003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -838,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1838 ,protection_group_id: -838, protection_element_id:-838], primaryKey: false);
      insert('organizations', [ id: 100824, nci_institute_code: "40004", name: "Ichilov Hospital", city: "Tel Aviv Jaffe", country: "Israel"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -839,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40004",GROUP_DESC:"40004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -839,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.40004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.40004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -839,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1839 ,protection_group_id: -839, protection_element_id:-839], primaryKey: false);
    }

    void m33() {
        // all33 (25 terms)
      insert('organizations', [ id: 100825, nci_institute_code: "40005", name: "Tel-Hashomer Government Hospital", city: "Tel-Hashomer", country: "Israel"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -840,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40005",GROUP_DESC:"40005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -840,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.40005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.40005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -840,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1840 ,protection_group_id: -840, protection_element_id:-840], primaryKey: false);
      insert('organizations', [ id: 100826, nci_institute_code: "40006", name: "Meyer De Rothschild Hosp.", city: "Jerusalem", country: "Israel"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -841,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40006",GROUP_DESC:"40006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -841,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.40006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.40006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -841,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1841 ,protection_group_id: -841, protection_element_id:-841], primaryKey: false);
      insert('organizations', [ id: 100827, nci_institute_code: "40007", name: "Rambam Medical Center", city: "Haifa", country: "Israel"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -842,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40007",GROUP_DESC:"40007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -842,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.40007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.40007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -842,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1842 ,protection_group_id: -842, protection_element_id:-842], primaryKey: false);
      insert('organizations', [ id: 100828, nci_institute_code: "40008", name: "Tel-Aviv University", city: "Holton", country: "Israel"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -843,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40008",GROUP_DESC:"40008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -843,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.40008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.40008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -843,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1843 ,protection_group_id: -843, protection_element_id:-843], primaryKey: false);
      insert('organizations', [ id: 100829, nci_institute_code: "40010", name: "Medical Center/Rokach Hosp", city: "Tel Aviv", country: "Israel"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -844,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40010",GROUP_DESC:"40010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -844,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.40010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.40010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -844,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1844 ,protection_group_id: -844, protection_element_id:-844], primaryKey: false);
      insert('organizations', [ id: 100830, nci_institute_code: "40011", name: "Beilinson Hosp.", city: "Petach Tikva", country: "Israel"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -845,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40011",GROUP_DESC:"40011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -845,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.40011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.40011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -845,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1845 ,protection_group_id: -845, protection_element_id:-845], primaryKey: false);
      insert('organizations', [ id: 100831, nci_institute_code: "40012", name: "Hebrew University", city: "Jerusalem", country: "Israel"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -846,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40012",GROUP_DESC:"40012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -846,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.40012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.40012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -846,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1846 ,protection_group_id: -846, protection_element_id:-846], primaryKey: false);
      insert('organizations', [ id: 100832, nci_institute_code: "40013", name: "Hadassah University Hospital", city: "Jerusalem", country: "Israel"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -847,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40013",GROUP_DESC:"40013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -847,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.40013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.40013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -847,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1847 ,protection_group_id: -847, protection_element_id:-847], primaryKey: false);
      insert('organizations', [ id: 100833, nci_institute_code: "40014", name: "Tel Hashomer Hospital", city: "Ramat-Gan", country: "Israel"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -848,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40014",GROUP_DESC:"40014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -848,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.40014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.40014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -848,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1848 ,protection_group_id: -848, protection_element_id:-848], primaryKey: false);
      insert('organizations', [ id: 100834, nci_institute_code: "40015", name: "Kaplan Hospital", city: "Rehovot", country: "Israel"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -849,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40015",GROUP_DESC:"40015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -849,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.40015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.40015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -849,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1849 ,protection_group_id: -849, protection_element_id:-849], primaryKey: false);
      insert('organizations', [ id: 100835, nci_institute_code: "40016", name: "Shaare Zedek Medical Center", city: "Jerusalem", country: "Israel"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -850,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40016",GROUP_DESC:"40016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -850,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.40016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.40016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -850,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1850 ,protection_group_id: -850, protection_element_id:-850], primaryKey: false);
      insert('organizations', [ id: 100836, nci_institute_code: "40017", name: "Lady Davis Carmel Hospital", city: "Haifa  34362", country: "Israel"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -851,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40017",GROUP_DESC:"40017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -851,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.40017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.40017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -851,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1851 ,protection_group_id: -851, protection_element_id:-851], primaryKey: false);
      insert('organizations', [ id: 100837, nci_institute_code: "40018", name: "Assaf Harofeh Medical Center", city: "Zerifin", country: "Israel"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -852,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40018",GROUP_DESC:"40018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -852,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.40018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.40018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -852,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1852 ,protection_group_id: -852, protection_element_id:-852], primaryKey: false);
      insert('organizations', [ id: 100838, nci_institute_code: "40019", name: "Tel Aviv Sourasky Medical Center", city: "Tel Aviv", country: "Israel"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -853,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40019",GROUP_DESC:"40019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -853,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.40019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.40019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -853,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1853 ,protection_group_id: -853, protection_element_id:-853], primaryKey: false);
      insert('organizations', [ id: 100839, nci_institute_code: "40020", name: "Soroka University Medical Center", city: "Beer Sheva", country: "Israel"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -854,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40020",GROUP_DESC:"40020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -854,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.40020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.40020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -854,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1854 ,protection_group_id: -854, protection_element_id:-854], primaryKey: false);
      insert('organizations', [ id: 100840, nci_institute_code: "40021", name: "Rabin Medical Center", city: "Petach Tikva", country: "Israel"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -855,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40021",GROUP_DESC:"40021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -855,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.40021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.40021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -855,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1855 ,protection_group_id: -855, protection_element_id:-855], primaryKey: false);
      insert('organizations', [ id: 100841, nci_institute_code: "40022", name: "Schneider Children's Medical Center of Israel", city: "Petah Tikua", country: "Israel"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -856,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40022",GROUP_DESC:"40022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -856,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.40022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.40022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -856,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1856 ,protection_group_id: -856, protection_element_id:-856], primaryKey: false);
      insert('organizations', [ id: 100842, nci_institute_code: "40023", name: "Meir Hospital", city: "Kfar Saba", country: "Israel"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -857,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40023",GROUP_DESC:"40023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -857,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.40023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.40023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -857,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1857 ,protection_group_id: -857, protection_element_id:-857], primaryKey: false);
      insert('organizations', [ id: 100843, nci_institute_code: "40024", name: "Weizmann Institute", city: "Rehovot ", country: "Israel"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -858,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40024",GROUP_DESC:"40024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -858,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.40024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.40024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -858,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1858 ,protection_group_id: -858, protection_element_id:-858], primaryKey: false);
      insert('organizations', [ id: 100844, nci_institute_code: "40025", name: "Hadassah Medical Organization", city: "Jerusalem", country: "Israel"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -859,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40025",GROUP_DESC:"40025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -859,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.40025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.40025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -859,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1859 ,protection_group_id: -859, protection_element_id:-859], primaryKey: false);
      insert('organizations', [ id: 100845, nci_institute_code: "40026", name: "Rambam Medical Center", city: "Haifa", country: "Israel"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -860,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40026",GROUP_DESC:"40026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -860,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.40026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.40026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -860,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.40026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1860 ,protection_group_id: -860, protection_element_id:-860], primaryKey: false);
      insert('organizations', [ id: 100846, nci_institute_code: "41001", name: "Ospedale S. Giovanni Battista", city: "Torino 10123", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -861,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41001",GROUP_DESC:"41001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -861,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -861,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1861 ,protection_group_id: -861, protection_element_id:-861], primaryKey: false);
      insert('organizations', [ id: 100847, nci_institute_code: "41002", name: "San Filippo Hospital", city: "Rome", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -862,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41002",GROUP_DESC:"41002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -862,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -862,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1862 ,protection_group_id: -862, protection_element_id:-862], primaryKey: false);
      insert('organizations', [ id: 100848, nci_institute_code: "41003", name: "Universita Di Bologna", city: "Bologna", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -863,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41003",GROUP_DESC:"41003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -863,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -863,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1863 ,protection_group_id: -863, protection_element_id:-863], primaryKey: false);
      insert('organizations', [ id: 100849, nci_institute_code: "41004", name: "Istituto Nazionale Tumori", city: "Milano", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -864,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41004",GROUP_DESC:"41004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -864,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -864,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1864 ,protection_group_id: -864, protection_element_id:-864], primaryKey: false);
    }

    void m34() {
        // all34 (25 terms)
      insert('organizations', [ id: 100850, nci_institute_code: "41005", name: "Dell Universita Di Genova", city: "Genova", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -865,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41005",GROUP_DESC:"41005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -865,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -865,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1865 ,protection_group_id: -865, protection_element_id:-865], primaryKey: false);
      insert('organizations', [ id: 100851, nci_institute_code: "41006", name: "University of Pavia", city: "Pavia 27100", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -866,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41006",GROUP_DESC:"41006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -866,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -866,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1866 ,protection_group_id: -866, protection_element_id:-866], primaryKey: false);
      insert('organizations', [ id: 100852, nci_institute_code: "41007", name: "Instituto Di Clinica MedicaI", city: "Milano, 20122", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -867,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41007",GROUP_DESC:"41007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -867,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -867,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1867 ,protection_group_id: -867, protection_element_id:-867], primaryKey: false);
      insert('organizations', [ id: 100853, nci_institute_code: "41008", name: "University Degli Studi Di Milano", city: "Milano 20146", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -868,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41008",GROUP_DESC:"41008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -868,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -868,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1868 ,protection_group_id: -868, protection_element_id:-868], primaryKey: false);
      insert('organizations', [ id: 100854, nci_institute_code: "41009", name: "Universita Di Padova", city: "Padova", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -869,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41009",GROUP_DESC:"41009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -869,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -869,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1869 ,protection_group_id: -869, protection_element_id:-869], primaryKey: false);
      insert('organizations', [ id: 100855, nci_institute_code: "41010", name: "Dell Universita Policlinico", city: "Naples", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -870,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41010",GROUP_DESC:"41010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -870,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -870,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1870 ,protection_group_id: -870, protection_element_id:-870], primaryKey: false);
      insert('organizations', [ id: 100856, nci_institute_code: "41011", name: "Instituto Di Farmacologia", city: "Milano 20129", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -871,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41011",GROUP_DESC:"41011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -871,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -871,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1871 ,protection_group_id: -871, protection_element_id:-871], primaryKey: false);
      insert('organizations', [ id: 100857, nci_institute_code: "41012", name: "Hospital Cardarelli", city: "Napoli", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -872,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41012",GROUP_DESC:"41012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -872,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -872,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1872 ,protection_group_id: -872, protection_element_id:-872], primaryKey: false);
      insert('organizations', [ id: 100858, nci_institute_code: "41014", name: "Universita Di Genova", city: "Genova", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -873,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41014",GROUP_DESC:"41014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -873,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -873,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1873 ,protection_group_id: -873, protection_element_id:-873], primaryKey: false);
      insert('organizations', [ id: 100859, nci_institute_code: "41015", name: "Ospedali Riuniti", city: "Parma, 43100", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -874,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41015",GROUP_DESC:"41015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -874,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -874,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1874 ,protection_group_id: -874, protection_element_id:-874], primaryKey: false);
      insert('organizations', [ id: 100860, nci_institute_code: "41016", name: "Civil Hosp.", city: "Udine", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -875,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41016",GROUP_DESC:"41016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -875,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -875,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1875 ,protection_group_id: -875, protection_element_id:-875], primaryKey: false);
      insert('organizations', [ id: 100861, nci_institute_code: "41017", name: "Vicenza Regional Hospital", city: "Vicenza", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -876,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41017",GROUP_DESC:"41017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -876,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -876,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1876 ,protection_group_id: -876, protection_element_id:-876], primaryKey: false);
      insert('organizations', [ id: 100862, nci_institute_code: "41018", name: "Univista Di Napoli", city: "Napoli", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -877,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41018",GROUP_DESC:"41018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -877,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -877,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1877 ,protection_group_id: -877, protection_element_id:-877], primaryKey: false);
      insert('organizations', [ id: 100863, nci_institute_code: "41019", name: "Villa Verde Hospital", city: "Reggio Emilia", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -878,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41019",GROUP_DESC:"41019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -878,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -878,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1878 ,protection_group_id: -878, protection_element_id:-878], primaryKey: false);
      insert('organizations', [ id: 100864, nci_institute_code: "41020", name: "Univ Degli Studi Di Roma", city: "Rome", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -879,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41020",GROUP_DESC:"41020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -879,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -879,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1879 ,protection_group_id: -879, protection_element_id:-879], primaryKey: false);
      insert('organizations', [ id: 100865, nci_institute_code: "41021", name: "University of Rome", city: "Rome", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -880,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41021",GROUP_DESC:"41021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -880,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -880,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1880 ,protection_group_id: -880, protection_element_id:-880], primaryKey: false);
      insert('organizations', [ id: 100866, nci_institute_code: "41022", name: "University of Milan", city: "Milan", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -881,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41022",GROUP_DESC:"41022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -881,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -881,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1881 ,protection_group_id: -881, protection_element_id:-881], primaryKey: false);
      insert('organizations', [ id: 100867, nci_institute_code: "41023", name: "Ospedale Civile Di Pordenone", city: "33170 Pordenone", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -882,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41023",GROUP_DESC:"41023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -882,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -882,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1882 ,protection_group_id: -882, protection_element_id:-882], primaryKey: false);
      insert('organizations', [ id: 100868, nci_institute_code: "41024", name: "Ospedale Morgagni", city: "47100 Forli", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -883,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41024",GROUP_DESC:"41024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -883,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -883,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1883 ,protection_group_id: -883, protection_element_id:-883], primaryKey: false);
      insert('organizations', [ id: 100869, nci_institute_code: "41025", name: "Univ. Degli Studi Di Napoli", city: "Napoli", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -884,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41025",GROUP_DESC:"41025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -884,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -884,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1884 ,protection_group_id: -884, protection_element_id:-884], primaryKey: false);
      insert('organizations', [ id: 100870, nci_institute_code: "41026", name: "Rome University", city: "00100 Rome", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -885,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41026",GROUP_DESC:"41026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -885,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -885,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1885 ,protection_group_id: -885, protection_element_id:-885], primaryKey: false);
      insert('organizations', [ id: 100871, nci_institute_code: "41027", name: "Univ Cattolica Del Sacro Cuore", city: "Roma", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -886,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41027",GROUP_DESC:"41027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -886,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -886,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1886 ,protection_group_id: -886, protection_element_id:-886], primaryKey: false);
      insert('organizations', [ id: 100872, nci_institute_code: "41029", name: "Ospedale Cardarelli", city: "Napoli", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -887,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41029",GROUP_DESC:"41029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -887,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -887,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1887 ,protection_group_id: -887, protection_element_id:-887], primaryKey: false);
      insert('organizations', [ id: 100873, nci_institute_code: "41030", name: "Ii Clincia Medica", city: "00100 Roma", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -888,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41030",GROUP_DESC:"41030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -888,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -888,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1888 ,protection_group_id: -888, protection_element_id:-888], primaryKey: false);
      insert('organizations', [ id: 100874, nci_institute_code: "41032", name: "Institute of Hematology", city: "Rome", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -889,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41032",GROUP_DESC:"41032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -889,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -889,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1889 ,protection_group_id: -889, protection_element_id:-889], primaryKey: false);
    }

    void m35() {
        // all35 (25 terms)
      insert('organizations', [ id: 100875, nci_institute_code: "41033", name: "University of Florence", city: "Florence", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -890,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41033",GROUP_DESC:"41033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -890,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -890,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1890 ,protection_group_id: -890, protection_element_id:-890], primaryKey: false);
      insert('organizations', [ id: 100876, nci_institute_code: "41034", name: "First G Gaslini Childrens Hosp", city: "Genova", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -891,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41034",GROUP_DESC:"41034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -891,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -891,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1891 ,protection_group_id: -891, protection_element_id:-891], primaryKey: false);
      insert('organizations', [ id: 100877, nci_institute_code: "41035", name: "Ist. Fisiologia Generale", city: "Siena 53100", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -892,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41035",GROUP_DESC:"41035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -892,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -892,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1892 ,protection_group_id: -892, protection_element_id:-892], primaryKey: false);
      insert('organizations', [ id: 100878, nci_institute_code: "41036", name: "Mario Negia Institute", city: "Milano", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -893,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41036",GROUP_DESC:"41036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -893,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -893,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1893 ,protection_group_id: -893, protection_element_id:-893], primaryKey: false);
      insert('organizations', [ id: 100879, nci_institute_code: "41037", name: "Niguardo Ost", city: "Milano", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -894,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41037",GROUP_DESC:"41037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -894,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -894,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1894 ,protection_group_id: -894, protection_element_id:-894], primaryKey: false);
      insert('organizations', [ id: 100880, nci_institute_code: "41038", name: "San Martino Hospital", city: "Genova", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -895,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41038",GROUP_DESC:"41038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -895,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -895,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1895 ,protection_group_id: -895, protection_element_id:-895], primaryKey: false);
      insert('organizations', [ id: 100881, nci_institute_code: "41039", name: "Ospedali Riuniti", city: "Bergamo 24100", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -896,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41039",GROUP_DESC:"41039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -896,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -896,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1896 ,protection_group_id: -896, protection_element_id:-896], primaryKey: false);
      insert('organizations', [ id: 100882, nci_institute_code: "41040", name: "MSI of University Centro Marcora", city: "20122 Milan", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -897,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41040",GROUP_DESC:"41040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -897,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -897,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1897 ,protection_group_id: -897, protection_element_id:-897], primaryKey: false);
      insert('organizations', [ id: 100883, nci_institute_code: "41041", name: "Ospedale San Bortolo", city: "Vicenza", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -898,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41041",GROUP_DESC:"41041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -898,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -898,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1898 ,protection_group_id: -898, protection_element_id:-898], primaryKey: false);
      insert('organizations', [ id: 100884, nci_institute_code: "41043", name: "Hospedale Ferrarotto", city: "Catania Sicily", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -899,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41043",GROUP_DESC:"41043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -899,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -899,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1899 ,protection_group_id: -899, protection_element_id:-899], primaryKey: false);
      insert('organizations', [ id: 100885, nci_institute_code: "41044", name: "University of Modena", city: "41100 Modena", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -900,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41044",GROUP_DESC:"41044 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -900,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41044",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41044",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -900,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41044", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1900 ,protection_group_id: -900, protection_element_id:-900], primaryKey: false);
      insert('organizations', [ id: 100886, nci_institute_code: "41045", name: "University of Pisa", city: "Pisa", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -901,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41045",GROUP_DESC:"41045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -901,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -901,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1901 ,protection_group_id: -901, protection_element_id:-901], primaryKey: false);
      insert('organizations', [ id: 100887, nci_institute_code: "41046", name: "Cto Hospital", city: "Rome", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -902,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41046",GROUP_DESC:"41046 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -902,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41046",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41046",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -902,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41046", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1902 ,protection_group_id: -902, protection_element_id:-902], primaryKey: false);
      insert('organizations', [ id: 100888, nci_institute_code: "41047", name: "Casa Sollievo Della Sofferenza", city: "Rotondo", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -903,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41047",GROUP_DESC:"41047 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -903,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41047",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41047",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -903,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41047", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1903 ,protection_group_id: -903, protection_element_id:-903], primaryKey: false);
      insert('organizations', [ id: 100889, nci_institute_code: "41048", name: "Div Di Ematolgia", city: "Firenze", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -904,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41048",GROUP_DESC:"41048 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -904,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41048",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41048",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -904,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41048", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1904 ,protection_group_id: -904, protection_element_id:-904], primaryKey: false);
      insert('organizations', [ id: 100890, nci_institute_code: "41049", name: "Inst Nazionale Tumori Centroeuropeo", city: "Aviano Pordenone", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -905,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41049",GROUP_DESC:"41049 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -905,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41049",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41049",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -905,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41049", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1905 ,protection_group_id: -905, protection_element_id:-905], primaryKey: false);
      insert('organizations', [ id: 100891, nci_institute_code: "41050", name: "Istituto Nazionale Per La Ricerca Sul Cancro", city: "Genova", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -906,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41050",GROUP_DESC:"41050 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -906,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41050",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41050",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -906,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41050", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1906 ,protection_group_id: -906, protection_element_id:-906], primaryKey: false);
      insert('organizations', [ id: 100892, nci_institute_code: "41051", name: "S.Maria Delle Croci Hospital", city: "Ravenna", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -907,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41051",GROUP_DESC:"41051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -907,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -907,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1907 ,protection_group_id: -907, protection_element_id:-907], primaryKey: false);
      insert('organizations', [ id: 100893, nci_institute_code: "41052", name: "University of Perugia", city: "Perggia", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -908,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41052",GROUP_DESC:"41052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -908,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -908,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1908 ,protection_group_id: -908, protection_element_id:-908], primaryKey: false);
      insert('organizations', [ id: 100894, nci_institute_code: "41053", name: "L\222Ospedale Pediatrico Bambino Ges\371", city: "Rome", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -909,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41053",GROUP_DESC:"41053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -909,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -909,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1909 ,protection_group_id: -909, protection_element_id:-909], primaryKey: false);
      insert('organizations', [ id: 100895, nci_institute_code: "41054", name: "Instituto Clinico Humanitas", city: "Rozzano", state: "Milano", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -910,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41054",GROUP_DESC:"41054 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -910,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41054",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41054",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -910,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41054", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1910 ,protection_group_id: -910, protection_element_id:-910], primaryKey: false);
      insert('organizations', [ id: 100896, nci_institute_code: "41055", name: "Centro di Riferimento Oncologico", city: "Aviano", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -911,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41055",GROUP_DESC:"41055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -911,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -911,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1911 ,protection_group_id: -911, protection_element_id:-911], primaryKey: false);
      insert('organizations', [ id: 100897, nci_institute_code: "41056", name: "South European New Drug Office", city: "Milano", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -912,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41056",GROUP_DESC:"41056 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -912,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41056",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41056",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -912,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41056", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1912 ,protection_group_id: -912, protection_element_id:-912], primaryKey: false);
      insert('organizations', [ id: 100898, nci_institute_code: "41057", name: "Division of Medical Oncology, Facility of Medicine University of Siena", city: "Siena", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -913,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41057",GROUP_DESC:"41057 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -913,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41057",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41057",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -913,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41057", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1913 ,protection_group_id: -913, protection_element_id:-913], primaryKey: false);
      insert('organizations', [ id: 100899, nci_institute_code: "41058", name: "European Institute of Oncology", city: "Milano", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -914,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41058",GROUP_DESC:"41058 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -914,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41058",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41058",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -914,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41058", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1914 ,protection_group_id: -914, protection_element_id:-914], primaryKey: false);
    }

    void m36() {
        // all36 (25 terms)
      insert('organizations', [ id: 100900, nci_institute_code: "41060", name: "G Gaslini Children's Hospital", city: "Genoa", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -915,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41060",GROUP_DESC:"41060 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -915,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41060",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41060",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -915,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41060", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1915 ,protection_group_id: -915, protection_element_id:-915], primaryKey: false);
      insert('organizations', [ id: 100901, nci_institute_code: "41061", name: "Universita Di Roma La Sapienza", city: "Rome", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -916,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41061",GROUP_DESC:"41061 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -916,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41061",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41061",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -916,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41061", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1916 ,protection_group_id: -916, protection_element_id:-916], primaryKey: false);
      insert('organizations', [ id: 100902, nci_institute_code: "41062", name: "Galliera Hospital", city: "Genoa", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -917,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41062",GROUP_DESC:"41062 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -917,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41062",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41062",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -917,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41062", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1917 ,protection_group_id: -917, protection_element_id:-917], primaryKey: false);
      insert('organizations', [ id: 100903, nci_institute_code: "41063", name: "Universita di Bari", city: "Mari", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -918,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41063",GROUP_DESC:"41063 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -918,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41063",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41063",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -918,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41063", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1918 ,protection_group_id: -918, protection_element_id:-918], primaryKey: false);
      insert('organizations', [ id: 100904, nci_institute_code: "41064", name: "Fondazione Salvatore Maugeri", city: "Pavia", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -919,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41064",GROUP_DESC:"41064 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -919,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41064",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41064",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -919,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41064", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1919 ,protection_group_id: -919, protection_element_id:-919], primaryKey: false);
      insert('organizations', [ id: 100905, nci_institute_code: "41065", name: "Ospedale Degli Infermi", city: "Biella", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -920,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41065",GROUP_DESC:"41065 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -920,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41065",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41065",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -920,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41065", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1920 ,protection_group_id: -920, protection_element_id:-920], primaryKey: false);
      insert('organizations', [ id: 100906, nci_institute_code: "41066", name: "Spedali Civili", city: "Brescia", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -921,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41066",GROUP_DESC:"41066 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -921,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41066",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41066",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -921,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41066", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1921 ,protection_group_id: -921, protection_element_id:-921], primaryKey: false);
      insert('organizations', [ id: 100907, nci_institute_code: "41067", name: "Universit\340 di Cagliari", city: "Cagliari", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -922,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41067",GROUP_DESC:"41067 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -922,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41067",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41067",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -922,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41067", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1922 ,protection_group_id: -922, protection_element_id:-922], primaryKey: false);
      insert('organizations', [ id: 100908, nci_institute_code: "41068", name: "Ospedale Ramazzini", city: "Carpi", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -923,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41068",GROUP_DESC:"41068 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -923,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41068",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41068",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -923,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41068", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1923 ,protection_group_id: -923, protection_element_id:-923], primaryKey: false);
      insert('organizations', [ id: 100909, nci_institute_code: "41069", name: "Opedale Alessandro Manzoni", city: "Lecco", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -924,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41069",GROUP_DESC:"41069 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -924,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41069",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41069",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -924,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41069", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1924 ,protection_group_id: -924, protection_element_id:-924], primaryKey: false);
      insert('organizations', [ id: 100910, nci_institute_code: "41070", name: "Azienda Ospedaliera San Paolo", city: "Milano", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -925,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41070",GROUP_DESC:"41070 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -925,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41070",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41070",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -925,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41070", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1925 ,protection_group_id: -925, protection_element_id:-925], primaryKey: false);
      insert('organizations', [ id: 100911, nci_institute_code: "41071", name: "Hospital Sandro Pitigliani", city: "Prato", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -926,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41071",GROUP_DESC:"41071 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -926,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41071",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41071",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -926,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41071", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1926 ,protection_group_id: -926, protection_element_id:-926], primaryKey: false);
      insert('organizations', [ id: 100912, nci_institute_code: "41072", name: "Ospedale S. Eugenio", city: "Roma", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -927,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41072",GROUP_DESC:"41072 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -927,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41072",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41072",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -927,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41072", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1927 ,protection_group_id: -927, protection_element_id:-927], primaryKey: false);
      insert('organizations', [ id: 100913, nci_institute_code: "41073", name: "Ospedale di Circolo e Fondazione", city: "Varese", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -928,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41073",GROUP_DESC:"41073 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -928,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41073",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41073",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -928,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41073", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1928 ,protection_group_id: -928, protection_element_id:-928], primaryKey: false);
      insert('organizations', [ id: 100914, nci_institute_code: "41074", name: "University of L'aquila", city: "L'aquila", country: "Italy"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -929,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41074",GROUP_DESC:"41074 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -929,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.41074",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.41074",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -929,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.41074", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1929 ,protection_group_id: -929, protection_element_id:-929], primaryKey: false);
      insert('organizations', [ id: 100915, nci_institute_code: "42001", name: "Tohoku University School of Medicine", city: "Sendai", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -930,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42001",GROUP_DESC:"42001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -930,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -930,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1930 ,protection_group_id: -930, protection_element_id:-930], primaryKey: false);
      insert('organizations', [ id: 100916, nci_institute_code: "42002", name: "Ryukyo Univ Hosp/Col Health Scnc", city: "Naha-Shi, Owinawa", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -931,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42002",GROUP_DESC:"42002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -931,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -931,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1931 ,protection_group_id: -931, protection_element_id:-931], primaryKey: false);
      insert('organizations', [ id: 100917, nci_institute_code: "42003", name: "Gunma University School of Medicine", city: "Gunma, 371", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -932,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42003",GROUP_DESC:"42003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -932,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -932,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1932 ,protection_group_id: -932, protection_element_id:-932], primaryKey: false);
      insert('organizations', [ id: 100918, nci_institute_code: "42004", name: "Kyushu Univeristy", city: "Fukuoka", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -933,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42004",GROUP_DESC:"42004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -933,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -933,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1933 ,protection_group_id: -933, protection_element_id:-933], primaryKey: false);
      insert('organizations', [ id: 100919, nci_institute_code: "42005", name: "Juntendoh Lzu-Nagaoka Hospital", city: "Shizouka Pref.", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -934,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42005",GROUP_DESC:"42005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -934,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -934,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1934 ,protection_group_id: -934, protection_element_id:-934], primaryKey: false);
      insert('organizations', [ id: 100920, nci_institute_code: "42006", name: "Saint Marianna University School of Medicine", city: "Kawasaki", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -935,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42006",GROUP_DESC:"42006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -935,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -935,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1935 ,protection_group_id: -935, protection_element_id:-935], primaryKey: false);
      insert('organizations', [ id: 100921, nci_institute_code: "42008", name: "Hyogo College of Medicine", city: "Nishinomiya, Hyogo", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -936,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42008",GROUP_DESC:"42008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -936,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -936,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1936 ,protection_group_id: -936, protection_element_id:-936], primaryKey: false);
      insert('organizations', [ id: 100922, nci_institute_code: "42009", name: "School of Medicine University of Kanazawa", city: "Ishikanwa-Ken", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -937,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42009",GROUP_DESC:"42009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -937,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -937,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1937 ,protection_group_id: -937, protection_element_id:-937], primaryKey: false);
      insert('organizations', [ id: 100923, nci_institute_code: "42010", name: "Nagasaki University", city: "Nagasaki", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -938,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42010",GROUP_DESC:"42010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -938,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -938,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1938 ,protection_group_id: -938, protection_element_id:-938], primaryKey: false);
      insert('organizations', [ id: 100924, nci_institute_code: "42011", name: "Onomishi General Hopital", city: "Onomichi", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -939,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42011",GROUP_DESC:"42011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -939,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -939,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1939 ,protection_group_id: -939, protection_element_id:-939], primaryKey: false);
    }

    void m37() {
        // all37 (25 terms)
      insert('organizations', [ id: 100925, nci_institute_code: "42012", name: "Shinshu University Hospital", city: "Nagano-Pref", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -940,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42012",GROUP_DESC:"42012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -940,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -940,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1940 ,protection_group_id: -940, protection_element_id:-940], primaryKey: false);
      insert('organizations', [ id: 100926, nci_institute_code: "42013", name: "Osaka University Hospital", city: "Osaka", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -941,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42013",GROUP_DESC:"42013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -941,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -941,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1941 ,protection_group_id: -941, protection_element_id:-941], primaryKey: false);
      insert('organizations', [ id: 100927, nci_institute_code: "42014", name: "Sapporo Medical College", city: "Sapporo", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -942,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42014",GROUP_DESC:"42014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -942,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -942,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1942 ,protection_group_id: -942, protection_element_id:-942], primaryKey: false);
      insert('organizations', [ id: 100928, nci_institute_code: "42015", name: "Nihon University Hospital", city: "Tokyo", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -943,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42015",GROUP_DESC:"42015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -943,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -943,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1943 ,protection_group_id: -943, protection_element_id:-943], primaryKey: false);
      insert('organizations', [ id: 100929, nci_institute_code: "42016", name: "Tokyo Medical and Dental University", city: "Tokyo", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -944,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42016",GROUP_DESC:"42016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -944,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -944,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1944 ,protection_group_id: -944, protection_element_id:-944], primaryKey: false);
      insert('organizations', [ id: 100930, nci_institute_code: "42017", name: "National Cancer Center Hospital", city: "Tokyo", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -945,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42017",GROUP_DESC:"42017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -945,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -945,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1945 ,protection_group_id: -945, protection_element_id:-945], primaryKey: false);
      insert('organizations', [ id: 100931, nci_institute_code: "42018", name: "Japanese Found Cancer Research", city: "Toshima-Ku, Tokyo", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -946,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42018",GROUP_DESC:"42018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -946,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -946,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1946 ,protection_group_id: -946, protection_element_id:-946], primaryKey: false);
      insert('organizations', [ id: 100932, nci_institute_code: "42019", name: "Metropolitan Okubo Hospital", city: "Tokyo 160", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -947,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42019",GROUP_DESC:"42019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -947,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -947,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1947 ,protection_group_id: -947, protection_element_id:-947], primaryKey: false);
      insert('organizations', [ id: 100933, nci_institute_code: "42020", name: "University of Teikyo", city: "Tokyo 173", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -948,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42020",GROUP_DESC:"42020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -948,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -948,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1948 ,protection_group_id: -948, protection_element_id:-948], primaryKey: false);
      insert('organizations', [ id: 100934, nci_institute_code: "42021", name: "Dokkyo University School of Medicine", city: "Tochigi 321-02", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -949,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42021",GROUP_DESC:"42021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -949,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -949,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1949 ,protection_group_id: -949, protection_element_id:-949], primaryKey: false);
      insert('organizations', [ id: 100935, nci_institute_code: "42022", name: "Fujita Health University School Of Medicine", city: "Toyoake", state: "Aichi", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -950,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42022",GROUP_DESC:"42022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -950,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -950,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1950 ,protection_group_id: -950, protection_element_id:-950], primaryKey: false);
      insert('organizations', [ id: 100936, nci_institute_code: "42023", name: "Kumanoto University School of Medicine", city: "Kumanto 860", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -951,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42023",GROUP_DESC:"42023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -951,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -951,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1951 ,protection_group_id: -951, protection_element_id:-951], primaryKey: false);
      insert('organizations', [ id: 100937, nci_institute_code: "42024", name: "Toranomon Hospital", city: "Tokyo 105", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -952,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42024",GROUP_DESC:"42024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -952,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -952,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1952 ,protection_group_id: -952, protection_element_id:-952], primaryKey: false);
      insert('organizations', [ id: 100938, nci_institute_code: "42025", name: "Saiseikai Hospital", city: "Matsusaka City, 515", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -953,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42025",GROUP_DESC:"42025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -953,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -953,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1953 ,protection_group_id: -953, protection_element_id:-953], primaryKey: false);
      insert('organizations', [ id: 100939, nci_institute_code: "42026", name: "Nippon Medical School", city: "Tokyo", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -954,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42026",GROUP_DESC:"42026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -954,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -954,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1954 ,protection_group_id: -954, protection_element_id:-954], primaryKey: false);
      insert('organizations', [ id: 100940, nci_institute_code: "42027", name: "Chiba Univ.", city: "Chiba City", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -955,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42027",GROUP_DESC:"42027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -955,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -955,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1955 ,protection_group_id: -955, protection_element_id:-955], primaryKey: false);
      insert('organizations', [ id: 100941, nci_institute_code: "42028", name: "Nagoya University School of Medicine", city: "Showa-Ku Nagoya", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -956,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42028",GROUP_DESC:"42028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -956,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -956,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1956 ,protection_group_id: -956, protection_element_id:-956], primaryKey: false);
      insert('organizations', [ id: 100942, nci_institute_code: "42029", name: "Kawasaki Medical School", city: "Okayama 701-01", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -957,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42029",GROUP_DESC:"42029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -957,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -957,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1957 ,protection_group_id: -957, protection_element_id:-957], primaryKey: false);
      insert('organizations', [ id: 100943, nci_institute_code: "42030", name: "Tokyo Womens Med College Hosp", city: "Shinjukuku Tokyo", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -958,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42030",GROUP_DESC:"42030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -958,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -958,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1958 ,protection_group_id: -958, protection_element_id:-958], primaryKey: false);
      insert('organizations', [ id: 100944, nci_institute_code: "42031", name: "Rutgers Medical School", city: "Shinjuko-Ku Tokyo", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -959,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42031",GROUP_DESC:"42031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -959,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -959,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1959 ,protection_group_id: -959, protection_element_id:-959], primaryKey: false);
      insert('organizations', [ id: 100945, nci_institute_code: "42032", name: "Ogaki Municipal Hospital", city: "Ogaki City G1fu Ken", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -960,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42032",GROUP_DESC:"42032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -960,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -960,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1960 ,protection_group_id: -960, protection_element_id:-960], primaryKey: false);
      insert('organizations', [ id: 100946, nci_institute_code: "42033", name: "Kansai Medical University", city: "Osaka 570", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -961,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42033",GROUP_DESC:"42033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -961,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -961,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1961 ,protection_group_id: -961, protection_element_id:-961], primaryKey: false);
      insert('organizations', [ id: 100947, nci_institute_code: "42034", name: "Hamamatsu University School of Medicine", city: "Hamamatsu 431-31", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -962,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42034",GROUP_DESC:"42034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -962,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -962,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1962 ,protection_group_id: -962, protection_element_id:-962], primaryKey: false);
      insert('organizations', [ id: 100948, nci_institute_code: "42035", name: "Hiroshima University Hospital", city: "Hiroshima City", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -963,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42035",GROUP_DESC:"42035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -963,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -963,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1963 ,protection_group_id: -963, protection_element_id:-963], primaryKey: false);
      insert('organizations', [ id: 100949, nci_institute_code: "42036", name: "Kyorin University Hospital", city: "Tokyo 181", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -964,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42036",GROUP_DESC:"42036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -964,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -964,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1964 ,protection_group_id: -964, protection_element_id:-964], primaryKey: false);
    }

    void m38() {
        // all38 (25 terms)
      insert('organizations', [ id: 100950, nci_institute_code: "42037", name: "The Kanto Teishin Hospital", city: "Shinagawa-Ku  Tokyo", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -965,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42037",GROUP_DESC:"42037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -965,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -965,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1965 ,protection_group_id: -965, protection_element_id:-965], primaryKey: false);
      insert('organizations', [ id: 100951, nci_institute_code: "42038", name: "Japan Enery Corporation", city: "Saitama", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -966,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42038",GROUP_DESC:"42038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -966,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -966,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1966 ,protection_group_id: -966, protection_element_id:-966], primaryKey: false);
      insert('organizations', [ id: 100952, nci_institute_code: "42039", name: "Kyowa Hakko Kogya Company Limited", city: "Chiyoda-Ku", state: "Tokyo", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -967,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42039",GROUP_DESC:"42039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -967,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -967,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1967 ,protection_group_id: -967, protection_element_id:-967], primaryKey: false);
      insert('organizations', [ id: 100953, nci_institute_code: "42040", name: "Kirin Brewery Company Limited", city: "Takasaki", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -968,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42040",GROUP_DESC:"42040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -968,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -968,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1968 ,protection_group_id: -968, protection_element_id:-968], primaryKey: false);
      insert('organizations', [ id: 100954, nci_institute_code: "42041", name: "Jikei University School of Medicine", city: "Minato-ku, Tokyo", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -969,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42041",GROUP_DESC:"42041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -969,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -969,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1969 ,protection_group_id: -969, protection_element_id:-969], primaryKey: false);
      insert('organizations', [ id: 100955, nci_institute_code: "42042", name: "Kagoshima City Hospital", city: "Kagoshima City, Kagoshima", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -970,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42042",GROUP_DESC:"42042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -970,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -970,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1970 ,protection_group_id: -970, protection_element_id:-970], primaryKey: false);
      insert('organizations', [ id: 100956, nci_institute_code: "42043", name: "Keio University", city: "Shinjuku-ku", state: "Tokyo", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -971,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42043",GROUP_DESC:"42043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -971,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -971,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1971 ,protection_group_id: -971, protection_element_id:-971], primaryKey: false);
      insert('organizations', [ id: 100957, nci_institute_code: "42044", name: "Kinki University", city: "Osaka, Osaka", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -972,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42044",GROUP_DESC:"42044 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -972,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42044",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42044",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -972,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42044", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1972 ,protection_group_id: -972, protection_element_id:-972], primaryKey: false);
      insert('organizations', [ id: 100958, nci_institute_code: "42045", name: "Kurume University", city: "Kurume City", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -973,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42045",GROUP_DESC:"42045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -973,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -973,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1973 ,protection_group_id: -973, protection_element_id:-973], primaryKey: false);
      insert('organizations', [ id: 100959, nci_institute_code: "42046", name: "National Kyushu Cancer Center", city: "Minami-ku", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -974,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42046",GROUP_DESC:"42046 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -974,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42046",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42046",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -974,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42046", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1974 ,protection_group_id: -974, protection_element_id:-974], primaryKey: false);
      insert('organizations', [ id: 100960, nci_institute_code: "42047", name: "Tottori University", city: "Tottori", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -975,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42047",GROUP_DESC:"42047 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -975,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42047",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42047",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -975,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42047", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1975 ,protection_group_id: -975, protection_element_id:-975], primaryKey: false);
      insert('organizations', [ id: 100961, nci_institute_code: "42049", name: "National Hospital Organization, Kobe Medical Center", city: "Kobe", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -976,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42049",GROUP_DESC:"42049 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -976,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42049",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42049",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -976,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42049", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1976 ,protection_group_id: -976, protection_element_id:-976], primaryKey: false);
      insert('organizations', [ id: 100962, nci_institute_code: "42050", name: "The Kitasato Institute", city: "Tokyo", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -977,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42050",GROUP_DESC:"42050 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -977,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42050",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42050",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -977,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42050", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1977 ,protection_group_id: -977, protection_element_id:-977], primaryKey: false);
      insert('organizations', [ id: 100963, nci_institute_code: "42051", name: "Tottori University School of Medicine", city: "Yonago", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -978,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42051",GROUP_DESC:"42051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -978,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -978,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1978 ,protection_group_id: -978, protection_element_id:-978], primaryKey: false);
      insert('organizations', [ id: 100964, nci_institute_code: "42052", name: "Shikoku Cancer Center", city: "Matsuyama", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -979,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42052",GROUP_DESC:"42052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -979,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -979,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1979 ,protection_group_id: -979, protection_element_id:-979], primaryKey: false);
      insert('organizations', [ id: 100965, nci_institute_code: "42053", name: "Jikei University School of Medicine, Daisan Hospital", city: "Komae-shi, Tokyo", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -980,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42053",GROUP_DESC:"42053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -980,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -980,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1980 ,protection_group_id: -980, protection_element_id:-980], primaryKey: false);
      insert('organizations', [ id: 100966, nci_institute_code: "42054", name: "National Defense Medical College", city: "Saitama", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -981,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42054",GROUP_DESC:"42054 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -981,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42054",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42054",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -981,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42054", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1981 ,protection_group_id: -981, protection_element_id:-981], primaryKey: false);
      insert('organizations', [ id: 100967, nci_institute_code: "42055", name: "Hokkaido University Hospital", city: "Sapporo", state: "Hokkaido", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -982,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42055",GROUP_DESC:"42055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -982,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -982,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1982 ,protection_group_id: -982, protection_element_id:-982], primaryKey: false);
      insert('organizations', [ id: 100968, nci_institute_code: "42056", name: "Kure National Hospital", city: "Kure", state: "Hiroshima", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -983,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42056",GROUP_DESC:"42056 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -983,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42056",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42056",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -983,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42056", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1983 ,protection_group_id: -983, protection_element_id:-983], primaryKey: false);
      insert('organizations', [ id: 100969, nci_institute_code: "42057", name: "Iwate Medical University School of Medicine", city: "Morioka", state: "Iwate", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -984,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42057",GROUP_DESC:"42057 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -984,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42057",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42057",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -984,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42057", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1984 ,protection_group_id: -984, protection_element_id:-984], primaryKey: false);
      insert('organizations', [ id: 100970, nci_institute_code: "42058", name: "Saitama Medical University", city: "Saitama", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -985,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42058",GROUP_DESC:"42058 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -985,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42058",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42058",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -985,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42058", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1985 ,protection_group_id: -985, protection_element_id:-985], primaryKey: false);
      insert('organizations', [ id: 100971, nci_institute_code: "42059", name: "Yamaguchi University", city: "Yamaguchi", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -986,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42059",GROUP_DESC:"42059 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -986,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42059",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42059",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -986,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42059", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1986 ,protection_group_id: -986, protection_element_id:-986], primaryKey: false);
      insert('organizations', [ id: 100972, nci_institute_code: "42060", name: "Jikei University Aoto Hospital", city: "Katsushika-ku", state: "Tokyo", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -987,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42060",GROUP_DESC:"42060 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -987,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42060",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42060",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -987,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42060", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1987 ,protection_group_id: -987, protection_element_id:-987], primaryKey: false);
      insert('organizations', [ id: 100973, nci_institute_code: "42061", name: "Saitama Medical University International Medical Center", city: "Saitama", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -988,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42061",GROUP_DESC:"42061 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -988,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42061",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42061",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -988,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42061", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1988 ,protection_group_id: -988, protection_element_id:-988], primaryKey: false);
      insert('organizations', [ id: 100974, nci_institute_code: "43001", name: "Wonlo Chai Hosp.", city: "Seoul", state: "KOREA", country: "Korea (South)"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -989,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43001",GROUP_DESC:"43001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -989,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.43001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.43001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -989,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1989 ,protection_group_id: -989, protection_element_id:-989], primaryKey: false);
    }

    void m39() {
        // all39 (23 terms)
      insert('organizations', [ id: 100975, nci_institute_code: "43002", name: "Seoul National University Hospital", city: "Seoul", country: "Korea (South)"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -990,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43002",GROUP_DESC:"43002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -990,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.43002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.43002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -990,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1990 ,protection_group_id: -990, protection_element_id:-990], primaryKey: false);
      insert('organizations', [ id: 100976, nci_institute_code: "43003", name: "Catholic Univ Medical College", city: "Seoul", state: "KOREA", country: "Korea (South)"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -991,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43003",GROUP_DESC:"43003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -991,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.43003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.43003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -991,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1991 ,protection_group_id: -991, protection_element_id:-991], primaryKey: false);
      insert('organizations', [ id: 100977, nci_institute_code: "43004", name: "Samsung Medical Center", city: "Seoul", state: "KOREA", country: "Korea (South)"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -992,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43004",GROUP_DESC:"43004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -992,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.43004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.43004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -992,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1992 ,protection_group_id: -992, protection_element_id:-992], primaryKey: false);
      insert('organizations', [ id: 100978, nci_institute_code: "43005", name: "Korean Cancer Research Group", city: "Seoul", state: "KOREA", country: "Korea (South)"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -993,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43005",GROUP_DESC:"43005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -993,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.43005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.43005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -993,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1993 ,protection_group_id: -993, protection_element_id:-993], primaryKey: false);
      insert('organizations', [ id: 100979, nci_institute_code: "43006", name: "National Cancer Center, Korea", city: "Koyang", state: "Kyonggi", country: "Korea (South)"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -994,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43006",GROUP_DESC:"43006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -994,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.43006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.43006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -994,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1994 ,protection_group_id: -994, protection_element_id:-994], primaryKey: false);
      insert('organizations', [ id: 100980, nci_institute_code: "43007", name: "Yonsei University College of Medicine", city: "Seoul", country: "Korea (South)"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -995,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43007",GROUP_DESC:"43007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -995,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.43007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.43007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -995,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1995 ,protection_group_id: -995, protection_element_id:-995], primaryKey: false);
      insert('organizations', [ id: 100981, nci_institute_code: "43008", name: "Kyung Hee University - Korea", city: "Seoul", country: "Korea (South)"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -996,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43008",GROUP_DESC:"43008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -996,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.43008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.43008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -996,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1996 ,protection_group_id: -996, protection_element_id:-996], primaryKey: false);
      insert('organizations', [ id: 100982, nci_institute_code: "43010", name: "Ewha Woman's University", city: "Seoul", country: "Korea (South)"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -997,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43010",GROUP_DESC:"43010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -997,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.43010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.43010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -997,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1997 ,protection_group_id: -997, protection_element_id:-997], primaryKey: false);
      insert('organizations', [ id: 100983, nci_institute_code: "43011", name: "Kyungpook National University", city: "Taegu", country: "Korea (South)"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -998,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43011",GROUP_DESC:"43011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -998,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.43011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.43011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -998,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1998 ,protection_group_id: -998, protection_element_id:-998], primaryKey: false);
      insert('organizations', [ id: 100984, nci_institute_code: "43012", name: "Konkuk University Hospital", city: "Seoul", country: "Korea (South)"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -999,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43012",GROUP_DESC:"43012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -999,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.43012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.43012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -999,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:1999 ,protection_group_id: -999, protection_element_id:-999], primaryKey: false);
      insert('organizations', [ id: 100985, nci_institute_code: "43013", name: "University of Ulsan", city: "Ulsan", country: "Korea (South)"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1000,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43013",GROUP_DESC:"43013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1000,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.43013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.43013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1000,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2000 ,protection_group_id: -1000, protection_element_id:-1000], primaryKey: false);
      insert('organizations', [ id: 100986, nci_institute_code: "43014", name: "Inha University", city: "Nam-gu Incheon", country: "Korea (South)"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1001,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43014",GROUP_DESC:"43014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1001,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.43014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.43014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1001,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2001 ,protection_group_id: -1001, protection_element_id:-1001], primaryKey: false);
      insert('organizations', [ id: 100987, nci_institute_code: "43015", name: "Kangnam Sacred Heart Hospital", city: "Youngdungpo-gu", country: "Korea (South)"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1002,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43015",GROUP_DESC:"43015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1002,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.43015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.43015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1002,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2002 ,protection_group_id: -1002, protection_element_id:-1002], primaryKey: false);
      insert('organizations', [ id: 100988, nci_institute_code: "43016", name: "Hanyang University Hospital", city: "Seongdong-gu", state: "Seoul", country: "Korea (South)"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1003,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43016",GROUP_DESC:"43016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1003,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.43016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.43016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1003,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2003 ,protection_group_id: -1003, protection_element_id:-1003], primaryKey: false);
      insert('organizations', [ id: 100989, nci_institute_code: "43017", name: "Kangnam Saint Mary's Hospital", city: "Socho-gu", state: "Seoul", country: "Korea (South)"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1004,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43017",GROUP_DESC:"43017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1004,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.43017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.43017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1004,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2004 ,protection_group_id: -1004, protection_element_id:-1004], primaryKey: false);
      insert('organizations', [ id: 100990, nci_institute_code: "43018", name: "Holy Family Hospital", city: "Kyounggi-do", country: "Korea (South)"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1005,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43018",GROUP_DESC:"43018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1005,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.43018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.43018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1005,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2005 ,protection_group_id: -1005, protection_element_id:-1005], primaryKey: false);
      insert('organizations', [ id: 100991, nci_institute_code: "43019", name: "Saint Vincent's Hospital", city: "Suwon", country: "Korea (South)"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1006,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43019",GROUP_DESC:"43019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1006,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.43019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.43019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1006,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2006 ,protection_group_id: -1006, protection_element_id:-1006], primaryKey: false);
      insert('organizations', [ id: 100992, nci_institute_code: "43020", name: "Ajou University Hospital", city: "Suwon", country: "Korea (South)"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1007,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43020",GROUP_DESC:"43020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1007,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.43020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.43020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1007,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2007 ,protection_group_id: -1007, protection_element_id:-1007], primaryKey: false);
      insert('organizations', [ id: 100993, nci_institute_code: "43021", name: "Asan Medical Center", city: "Seoul", country: "Korea (South)"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1008,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43021",GROUP_DESC:"43021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1008,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.43021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.43021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1008,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2008 ,protection_group_id: -1008, protection_element_id:-1008], primaryKey: false);
      insert('organizations', [ id: 100994, nci_institute_code: "44001", name: "American University Medical Center", city: "Beirut", country: "Lebanon"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1009,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.44001",GROUP_DESC:"44001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1009,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.44001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.44001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1009,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.44001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2009 ,protection_group_id: -1009, protection_element_id:-1009], primaryKey: false);
      insert('organizations', [ id: 100995, nci_institute_code: "44002", name: "American University of Beirut", city: "Beirut", country: "Lebanon"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1010,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.44002",GROUP_DESC:"44002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1010,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.44002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.44002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1010,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.44002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2010 ,protection_group_id: -1010, protection_element_id:-1010], primaryKey: false);
      insert('organizations', [ id: 100996, nci_institute_code: "46001", name: "Hospital Befelatanana", city: "Tananarive  Rep", country: "Madagascar"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1011,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.46001",GROUP_DESC:"46001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1011,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.46001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.46001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1011,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.46001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2011 ,protection_group_id: -1011, protection_element_id:-1011], primaryKey: false);
      insert('organizations', [ id: 100997, nci_institute_code: "47001", name: "Penang Medical Centre", city: "Penang", country: "Malaysia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -1012,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.47001",GROUP_DESC:"47001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -1012,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.47001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.47001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -1012,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.47001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:2012 ,protection_group_id: -1012, protection_element_id:-1012], primaryKey: false);
    }

    void down() {
        execute("delete from csm_pg_pe where pg_pe_id >= 1015 and  pg_pe_id <= 8999 ");
        execute("delete from CSM_PROTECTION_GROUP where protection_group_id  <= -15 ");
        execute("delete from csm_protection_element where protection_element_id <= -15 ");
        execute("delete from csm_group where group_id <= -15 ");
        execute("DELETE from organizations where id >= 100000 and id < 110000")
    }
}
