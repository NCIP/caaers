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
    }

    void m0() {
        // all0 (25 terms)
      insert('organizations', [ id: 107998, nci_institute_code: "02023", name: "Hospital de Pediatria Juan P Garrahan", city: "Buenos Aires", country: "Argentina"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8013,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02023",GROUP_DESC:"02023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8013,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.02023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.02023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8013,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.02023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9013 ,protection_group_id: -8013, protection_element_id:-8013], primaryKey: false);
      insert('organizations', [ id: 107999, nci_institute_code: "03125", name: "Hospital Pharmacy Services-Alexander Avenue", city: "Ashford", state: "SA", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8014,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03125",GROUP_DESC:"03125 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8014,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03125",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03125",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8014,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03125", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9014 ,protection_group_id: -8014, protection_element_id:-8014], primaryKey: false);
      insert('organizations', [ id: 108000, nci_institute_code: "03126", name: "Haematology and Oncology Clinics of Australasia", city: "Milton", state: "QLD", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8015,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03126",GROUP_DESC:"03126 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8015,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03126",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03126",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8015,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03126", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9015 ,protection_group_id: -8015, protection_element_id:-8015], primaryKey: false);
      insert('organizations', [ id: 108001, nci_institute_code: "03127", name: "River City Private Hospital", city: "Auchenflower", state: "QLD", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8016,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03127",GROUP_DESC:"03127 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8016,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03127",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03127",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8016,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03127", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9016 ,protection_group_id: -8016, protection_element_id:-8016], primaryKey: false);
      insert('organizations', [ id: 108002, nci_institute_code: "03128", name: "Tweed Hospital", city: "Tweed Heads", state: "NSW", country: "Australia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8017,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03128",GROUP_DESC:"03128 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8017,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.03128",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.03128",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8017,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.03128", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9017 ,protection_group_id: -8017, protection_element_id:-8017], primaryKey: false);
      insert('organizations', [ id: 108003, nci_institute_code: "04010", name: "Medical University Graz", city: "Graz", country: "Austria"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8018,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.04010",GROUP_DESC:"04010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8018,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.04010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.04010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8018,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.04010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9018 ,protection_group_id: -8018, protection_element_id:-8018], primaryKey: false);
      insert('organizations', [ id: 108004, nci_institute_code: "04011", name: "Krankenhaus Barmherzige Schwestern Linz", city: "Linz", country: "Austria"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8019,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.04011",GROUP_DESC:"04011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8019,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.04011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.04011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8019,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.04011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9019 ,protection_group_id: -8019, protection_element_id:-8019], primaryKey: false);
      insert('organizations', [ id: 108005, nci_institute_code: "04012", name: "Medical University Vienna", city: "Vienna", country: "Austria"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8020,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.04012",GROUP_DESC:"04012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8020,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.04012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.04012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8020,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.04012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9020 ,protection_group_id: -8020, protection_element_id:-8020], primaryKey: false);
      insert('organizations', [ id: 108006, nci_institute_code: "04013", name: "Krankenhaus Barmherzige Bruder Graz", city: "Graz", country: "Austria"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8021,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.04013",GROUP_DESC:"04013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8021,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.04013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.04013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8021,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.04013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9021 ,protection_group_id: -8021, protection_element_id:-8021], primaryKey: false);
      insert('organizations', [ id: 108007, nci_institute_code: "06033", name: "Cazk Groeninghe-Campus St-Niklaas", city: "Kortrijk", country: "Belgium"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8022,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06033",GROUP_DESC:"06033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8022,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.06033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.06033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8022,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9022 ,protection_group_id: -8022, protection_element_id:-8022], primaryKey: false);
      insert('organizations', [ id: 108008, nci_institute_code: "06034", name: "Ghent University Hospital", city: "Ghent", country: "Belgium"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8023,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06034",GROUP_DESC:"06034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8023,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.06034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.06034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8023,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.06034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9023 ,protection_group_id: -8023, protection_element_id:-8023], primaryKey: false);
      insert('organizations', [ id: 108009, nci_institute_code: "08028", name: "Centro de Referencia e Treinamento em DST/AIDS", city: "Vila Mariana", state: "S,o Paulo", country: "Brazil"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8024,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08028",GROUP_DESC:"08028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8024,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.08028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.08028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8024,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9024 ,protection_group_id: -8024, protection_element_id:-8024], primaryKey: false);
      insert('organizations', [ id: 108010, nci_institute_code: "08029", name: "Instituto de Infectologia Emilio Ribas", city: "Sao Paulo", state: "Sao Paulo", country: "Brazil"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8025,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08029",GROUP_DESC:"08029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8025,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.08029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.08029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8025,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9025 ,protection_group_id: -8025, protection_element_id:-8025], primaryKey: false);
      insert('organizations', [ id: 108011, nci_institute_code: "08030", name: "Pesquisa Clinica-Oncologua", city: "Santo Andre", country: "Brazil"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8026,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08030",GROUP_DESC:"08030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8026,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.08030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.08030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8026,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9026 ,protection_group_id: -8026, protection_element_id:-8026], primaryKey: false);
      insert('organizations', [ id: 108012, nci_institute_code: "08031", name: "Instituto De Oncologia Pediatrica", city: "Sao Paulo", country: "Brazil"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8027,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08031",GROUP_DESC:"08031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8027,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.08031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.08031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8027,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.08031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9027 ,protection_group_id: -8027, protection_element_id:-8027], primaryKey: false);
      insert('organizations', [ id: 108013, nci_institute_code: "102002", name: "Olegs Hublarovs Private Practice Urology", city: "Daugavpils", country: "Latvia"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8028,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.102002",GROUP_DESC:"102002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8028,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.102002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.102002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8028,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.102002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9028 ,protection_group_id: -8028, protection_element_id:-8028], primaryKey: false);
      insert('organizations', [ id: 108014, nci_institute_code: "11295", name: "Centre For Applied Urological Research", city: "Kingston", state: "ON", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8029,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11295",GROUP_DESC:"11295 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8029,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11295",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11295",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8029,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11295", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9029 ,protection_group_id: -8029, protection_element_id:-8029], primaryKey: false);
      insert('organizations', [ id: 108015, nci_institute_code: "11296", name: "Urology Consultants", city: "Halifax", state: "NS", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8030,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11296",GROUP_DESC:"11296 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8030,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11296",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11296",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8030,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11296", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9030 ,protection_group_id: -8030, protection_element_id:-8030], primaryKey: false);
      insert('organizations', [ id: 108016, nci_institute_code: "11297", name: "BC Cancer Agency-Abbotsford Centre", city: "Abbotsford", state: "BC", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8031,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11297",GROUP_DESC:"11297 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8031,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11297",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11297",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8031,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11297", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9031 ,protection_group_id: -8031, protection_element_id:-8031], primaryKey: false);
      insert('organizations', [ id: 108017, nci_institute_code: "11298", name: "Trillium Health Centre-West Toronto", city: "Toronto", state: "ON", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8032,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11298",GROUP_DESC:"11298 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8032,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11298",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11298",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8032,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11298", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9032 ,protection_group_id: -8032, protection_element_id:-8032], primaryKey: false);
      insert('organizations', [ id: 108018, nci_institute_code: "11299", name: "Tom Baker Cancer Centre-Holy Cross Site", city: "Calgary", state: "AB", country: "Canada"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8033,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11299",GROUP_DESC:"11299 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8033,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.11299",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.11299",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8033,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.11299", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9033 ,protection_group_id: -8033, protection_element_id:-8033], primaryKey: false);
      insert('organizations', [ id: 108019, nci_institute_code: "12001", name: "Instituto Oncologico Nacional", city: "Ancon", state: "Panama City", country: "Panama"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8034,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.12001",GROUP_DESC:"12001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8034,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.12001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.12001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8034,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.12001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9034 ,protection_group_id: -8034, protection_element_id:-8034], primaryKey: false);
      insert('organizations', [ id: 108020, nci_institute_code: "22002", name: "Solidarite Chimiotherapie", city: "Yaounde", country: "Cameroon"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8035,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.22002",GROUP_DESC:"22002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8035,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.22002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.22002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8035,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.22002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9035 ,protection_group_id: -8035, protection_element_id:-8035], primaryKey: false);
      insert('organizations', [ id: 108021, nci_institute_code: "28117", name: "The Centre Eugene Marquis", city: "BP 6279 Rennes", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8036,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28117",GROUP_DESC:"28117 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8036,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28117",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28117",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8036,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28117", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9036 ,protection_group_id: -8036, protection_element_id:-8036], primaryKey: false);
      insert('organizations', [ id: 108022, nci_institute_code: "28118", name: "Institut Bergonie Cancer Center", city: "Bordeaux", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8037,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28118",GROUP_DESC:"28118 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8037,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28118",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28118",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8037,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28118", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9037 ,protection_group_id: -8037, protection_element_id:-8037], primaryKey: false);
    }

    void m1() {
        // all1 (25 terms)
      insert('organizations', [ id: 108023, nci_institute_code: "28119", name: "Hospital European Georges Pompidou", city: "Paris", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8038,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28119",GROUP_DESC:"28119 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8038,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28119",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28119",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8038,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28119", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9038 ,protection_group_id: -8038, protection_element_id:-8038], primaryKey: false);
      insert('organizations', [ id: 108024, nci_institute_code: "28120", name: "Institut National du Cancer", city: "Boulogne-Billancourt Cedex", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8039,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28120",GROUP_DESC:"28120 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8039,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.28120",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.28120",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8039,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.28120", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9039 ,protection_group_id: -8039, protection_element_id:-8039], primaryKey: false);
      insert('organizations', [ id: 108025, nci_institute_code: "37020", name: "Advanced Clinical Research Limited", city: "Bangalore", country: "India"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8040,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37020",GROUP_DESC:"37020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8040,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.37020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.37020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8040,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9040 ,protection_group_id: -8040, protection_element_id:-8040], primaryKey: false);
      insert('organizations', [ id: 108026, nci_institute_code: "37021", name: "Saint John's Medical College and Hospital", city: "Bangalore", state: "Karnataka", country: "India"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8041,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37021",GROUP_DESC:"37021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8041,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.37021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.37021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8041,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9041 ,protection_group_id: -8041, protection_element_id:-8041], primaryKey: false);
      insert('organizations', [ id: 108027, nci_institute_code: "37022", name: "Bharath Hospital and Institute of Oncology", city: "Mysore", state: "Karnataka", country: "India"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8042,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37022",GROUP_DESC:"37022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8042,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.37022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.37022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8042,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9042 ,protection_group_id: -8042, protection_element_id:-8042], primaryKey: false);
      insert('organizations', [ id: 108028, nci_institute_code: "37023", name: "YR Gaitonde Centre for AIDS Research and Education", city: "Chennai", state: "Tamilnadu", country: "India"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8043,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37023",GROUP_DESC:"37023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8043,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.37023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.37023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8043,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9043 ,protection_group_id: -8043, protection_element_id:-8043], primaryKey: false);
      insert('organizations', [ id: 108029, nci_institute_code: "37024", name: "L V Prasad Eye Institute", city: "Hyderabad", country: "India"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8044,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37024",GROUP_DESC:"37024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8044,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.37024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.37024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8044,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9044 ,protection_group_id: -8044, protection_element_id:-8044], primaryKey: false);
      insert('organizations', [ id: 108030, nci_institute_code: "37025", name: "Vision and Medical Research Foundation", city: "Chennai", country: "India"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8045,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37025",GROUP_DESC:"37025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8045,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.37025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.37025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8045,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.37025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9045 ,protection_group_id: -8045, protection_element_id:-8045], primaryKey: false);
      insert('organizations', [ id: 108031, nci_institute_code: "39031", name: "Beacon Hospital", city: "Dublin", country: "Ireland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8046,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39031",GROUP_DESC:"39031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8046,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.39031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.39031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8046,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9046 ,protection_group_id: -8046, protection_element_id:-8046], primaryKey: false);
      insert('organizations', [ id: 108032, nci_institute_code: "39032", name: "UPMC Whitfield Cancer Centre", city: "Waterford", country: "Ireland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8047,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39032",GROUP_DESC:"39032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8047,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.39032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.39032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8047,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9047 ,protection_group_id: -8047, protection_element_id:-8047], primaryKey: false);
      insert('organizations', [ id: 108033, nci_institute_code: "39033", name: "Our Lady's Children's Hospital", city: "Dublin", country: "Ireland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8048,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39033",GROUP_DESC:"39033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8048,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.39033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.39033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8048,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9048 ,protection_group_id: -8048, protection_element_id:-8048], primaryKey: false);
      insert('organizations', [ id: 108034, nci_institute_code: "39034", name: "Saint Vincent's Private Hospital", city: "Merrion", country: "Ireland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8049,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39034",GROUP_DESC:"39034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8049,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.39034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.39034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8049,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.39034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9049 ,protection_group_id: -8049, protection_element_id:-8049], primaryKey: false);
      insert('organizations', [ id: 108035, nci_institute_code: "42062", name: "Okayama University Graduate School of Medicine Dentistry and Pharmaceutical Sciences", city: "Okayama", country: "Japan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8050,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42062",GROUP_DESC:"42062 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8050,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.42062",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.42062",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8050,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.42062", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9050 ,protection_group_id: -8050, protection_element_id:-8050], primaryKey: false);
      insert('organizations', [ id: 108036, nci_institute_code: "43022", name: "Yongdong Severence Hospital", city: "Seoul", country: "Korea (South)"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8051,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43022",GROUP_DESC:"43022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8051,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.43022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.43022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8051,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9051 ,protection_group_id: -8051, protection_element_id:-8051], primaryKey: false);
      insert('organizations', [ id: 108037, nci_institute_code: "43023", name: "Korea Cancer Center Hospital", city: "Seoul", country: "Korea (South)"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8052,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43023",GROUP_DESC:"43023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8052,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.43023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.43023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8052,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9052 ,protection_group_id: -8052, protection_element_id:-8052], primaryKey: false);
      insert('organizations', [ id: 108038, nci_institute_code: "43024", name: "Kwandong University College of Medicine", city: "Koyang-si", state: "Kyunggi-do", country: "Korea (South)"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8053,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43024",GROUP_DESC:"43024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8053,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.43024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.43024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8053,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9053 ,protection_group_id: -8053, protection_element_id:-8053], primaryKey: false);
      insert('organizations', [ id: 108039, nci_institute_code: "43025", name: "Ulsan University Hospital", city: "Ulsan", country: "Korea (South)"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8054,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43025",GROUP_DESC:"43025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8054,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.43025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.43025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8054,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9054 ,protection_group_id: -8054, protection_element_id:-8054], primaryKey: false);
      insert('organizations', [ id: 108040, nci_institute_code: "43026", name: "Inje University Busan-Paik Hospital", city: "Busanjin-gu", state: "Busan", country: "Korea (South)"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8055,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43026",GROUP_DESC:"43026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8055,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.43026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.43026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8055,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9055 ,protection_group_id: -8055, protection_element_id:-8055], primaryKey: false);
      insert('organizations', [ id: 108041, nci_institute_code: "43027", name: "Severance Hospital", city: "Seodaemun-gu", state: "Seoul", country: "Korea (South)"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8056,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43027",GROUP_DESC:"43027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8056,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.43027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.43027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8056,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9056 ,protection_group_id: -8056, protection_element_id:-8056], primaryKey: false);
      insert('organizations', [ id: 108042, nci_institute_code: "43028", name: "Keimyung University-Dongsan Medical Center", city: "Jung-Ku", state: "Daegu", country: "Korea (South)"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8057,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43028",GROUP_DESC:"43028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8057,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.43028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.43028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8057,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.43028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9057 ,protection_group_id: -8057, protection_element_id:-8057], primaryKey: false);
      insert('organizations', [ id: 108043, nci_institute_code: "47012", name: "Duke-NUS Graduate Medical School Singapore", city: "Singapore", country: "Singapore"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8058,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.47012",GROUP_DESC:"47012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8058,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.47012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.47012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8058,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.47012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9058 ,protection_group_id: -8058, protection_element_id:-8058], primaryKey: false);
      insert('organizations', [ id: 108044, nci_institute_code: "47013", name: "Gleneagles Hospital", city: "Singapore", country: "Singapore"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8059,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.47013",GROUP_DESC:"47013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8059,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.47013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.47013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8059,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.47013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9059 ,protection_group_id: -8059, protection_element_id:-8059], primaryKey: false);
      insert('organizations', [ id: 108045, nci_institute_code: "51077", name: "Dr Bernard Verbeeten Instituut", city: "Tilburg", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8060,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51077",GROUP_DESC:"51077 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8060,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51077",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51077",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8060,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51077", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9060 ,protection_group_id: -8060, protection_element_id:-8060], primaryKey: false);
      insert('organizations', [ id: 108046, nci_institute_code: "51078", name: "Medisch Centrum Haaglanden-Westeinde", city: "Den Haag", country: "Netherlands"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8061,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51078",GROUP_DESC:"51078 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8061,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.51078",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.51078",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8061,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.51078", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9061 ,protection_group_id: -8061, protection_element_id:-8061], primaryKey: false);
      insert('organizations', [ id: 108047, nci_institute_code: "60005", name: "Zhongshan Hospital Fudan University", city: "Guangdong Province", state: "Shanghai", country: "China"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8062,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.60005",GROUP_DESC:"60005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8062,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.60005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.60005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8062,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.60005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9062 ,protection_group_id: -8062, protection_element_id:-8062], primaryKey: false);
    }

    void m2() {
        // all2 (25 terms)
      insert('organizations', [ id: 108048, nci_institute_code: "60006", name: "Pamela Youde Nethersole Eastern Hospital", city: "Chai Wan", country: "China"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8063,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.60006",GROUP_DESC:"60006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8063,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.60006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.60006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8063,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.60006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9063 ,protection_group_id: -8063, protection_element_id:-8063], primaryKey: false);
      insert('organizations', [ id: 108049, nci_institute_code: "63012", name: "Red Cross War Memorial Children's Hospital", city: "Cape Town", country: "South Africa"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8064,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.63012",GROUP_DESC:"63012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8064,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.63012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.63012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8064,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.63012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9064 ,protection_group_id: -8064, protection_element_id:-8064], primaryKey: false);
      insert('organizations', [ id: 108050, nci_institute_code: "63019", name: "University of Zimbabwe College of Health Sciences", city: "Harare", state: "Zimbabwe", country: "South Africa"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8065,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.63019",GROUP_DESC:"63019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8065,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.63019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.63019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8065,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.63019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9065 ,protection_group_id: -8065, protection_element_id:-8065], primaryKey: false);
      insert('organizations', [ id: 108051, nci_institute_code: "66016", name: "Sundsvalls Hospital", city: "Sundsvalls", country: "Sweden"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8066,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.66016",GROUP_DESC:"66016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8066,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.66016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.66016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8066,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.66016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9066 ,protection_group_id: -8066, protection_element_id:-8066], primaryKey: false);
      insert('organizations', [ id: 108052, nci_institute_code: "66017", name: "Karolinska University Hospital Huddinge", city: "Huddinge", country: "Sweden"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8067,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.66017",GROUP_DESC:"66017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8067,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.66017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.66017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8067,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.66017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9067 ,protection_group_id: -8067, protection_element_id:-8067], primaryKey: false);
      insert('organizations', [ id: 108053, nci_institute_code: "67035", name: "University of Geneva", city: "Geneva", country: "Switzerland"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8068,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67035",GROUP_DESC:"67035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8068,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.67035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.67035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8068,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.67035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9068 ,protection_group_id: -8068, protection_element_id:-8068], primaryKey: false);
      insert('organizations', [ id: 108054, nci_institute_code: "87006", name: "Chang Gung Hospital-Lin Kou Medical Center", city: "Taoyuan", country: "Taiwan"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8069,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.87006",GROUP_DESC:"87006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8069,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.87006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.87006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8069,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.87006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9069 ,protection_group_id: -8069, protection_element_id:-8069], primaryKey: false);
      insert('organizations', [ id: 108055, nci_institute_code: "98016", name: "The University of Liverpool", city: "Liverpool", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8070,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.98016",GROUP_DESC:"98016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8070,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.98016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.98016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8070,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.98016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9070 ,protection_group_id: -8070, protection_element_id:-8070], primaryKey: false);
      insert('organizations', [ id: 108056, nci_institute_code: "98017", name: "University of Edinburgh Medical School", city: "Edinburgh", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8071,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.98017",GROUP_DESC:"98017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8071,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.98017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.98017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8071,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.98017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9071 ,protection_group_id: -8071, protection_element_id:-8071], primaryKey: false);
      insert('organizations', [ id: 108057, nci_institute_code: "98018", name: "The Royal Hallamshire Hospital", city: "Sheffield", state: "South Yorkshire", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8072,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.98018",GROUP_DESC:"98018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8072,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.98018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.98018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8072,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.98018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9072 ,protection_group_id: -8072, protection_element_id:-8072], primaryKey: false);
      insert('organizations', [ id: 108058, nci_institute_code: "98019", name: "Frontier Science (Scotland) Limited", city: "Inverness-shire", state: "Scotland", country: "United Kingdom"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8073,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.98019",GROUP_DESC:"98019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8073,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.98019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.98019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8073,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.98019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9073 ,protection_group_id: -8073, protection_element_id:-8073], primaryKey: false);
      insert('organizations', [ id: 108059, nci_institute_code: "AK021", name: "Alpine Urology-Anchorage Clinic", city: "Anchorage", state: "AK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8074,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AK021",GROUP_DESC:"AK021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8074,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AK021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AK021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8074,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AK021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9074 ,protection_group_id: -8074, protection_element_id:-8074], primaryKey: false);
      insert('organizations', [ id: 108060, nci_institute_code: "AL074", name: "Infirmary West Oncology and Infusion Services", city: "Mobile", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8075,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL074",GROUP_DESC:"AL074 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8075,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL074",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL074",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8075,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL074", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9075 ,protection_group_id: -8075, protection_element_id:-8075], primaryKey: false);
      insert('organizations', [ id: 108061, nci_institute_code: "AL075", name: "Southern Cancer Center PC", city: "Mobile", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8076,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL075",GROUP_DESC:"AL075 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8076,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL075",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL075",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8076,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL075", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9076 ,protection_group_id: -8076, protection_element_id:-8076], primaryKey: false);
      insert('organizations', [ id: 108062, nci_institute_code: "AL076", name: "Hematology and Oncology Associates of Alabama", city: "Anniston", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8077,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL076",GROUP_DESC:"AL076 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8077,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL076",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL076",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8077,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL076", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9077 ,protection_group_id: -8077, protection_element_id:-8077], primaryKey: false);
      insert('organizations', [ id: 108063, nci_institute_code: "AL077", name: "Children's and Women's Hospital-University of South Alabama", city: "Mobile", state: "AL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8078,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL077",GROUP_DESC:"AL077 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8078,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AL077",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AL077",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8078,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AL077", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9078 ,protection_group_id: -8078, protection_element_id:-8078], primaryKey: false);
      insert('organizations', [ id: 108064, nci_institute_code: "AR055", name: "Peachtree Hospice", city: "Fort Smith", state: "AR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8079,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR055",GROUP_DESC:"AR055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8079,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AR055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AR055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8079,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AR055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9079 ,protection_group_id: -8079, protection_element_id:-8079], primaryKey: false);
      insert('organizations', [ id: 108065, nci_institute_code: "AZ112", name: "Translational Genomics Research Institute Clinical Research Services at Scottsdale Health Care", city: "Scottsdale", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8080,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ112",GROUP_DESC:"AZ112 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8080,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ112",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ112",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8080,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ112", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9080 ,protection_group_id: -8080, protection_element_id:-8080], primaryKey: false);
      insert('organizations', [ id: 108066, nci_institute_code: "AZ113", name: "Ironwood Cancer and Research Centers", city: "Mesa", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8081,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ113",GROUP_DESC:"AZ113 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8081,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ113",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ113",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8081,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ113", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9081 ,protection_group_id: -8081, protection_element_id:-8081], primaryKey: false);
      insert('organizations', [ id: 108067, nci_institute_code: "AZ114", name: "Tri-City Colorectal Surgery", city: "Gilbert", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8082,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ114",GROUP_DESC:"AZ114 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8082,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ114",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ114",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8082,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ114", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9082 ,protection_group_id: -8082, protection_element_id:-8082], primaryKey: false);
      insert('organizations', [ id: 108068, nci_institute_code: "AZ115", name: "Translational Genomics Research Institute", city: "Scottsdale", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8083,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ115",GROUP_DESC:"AZ115 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8083,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ115",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ115",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8083,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ115", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9083 ,protection_group_id: -8083, protection_element_id:-8083], primaryKey: false);
      insert('organizations', [ id: 108069, nci_institute_code: "AZ116", name: "Arizona Pediatric Surgery Limited", city: "Tucson", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8084,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ116",GROUP_DESC:"AZ116 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8084,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ116",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ116",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8084,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ116", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9084 ,protection_group_id: -8084, protection_element_id:-8084], primaryKey: false);
      insert('organizations', [ id: 108070, nci_institute_code: "AZ117", name: "Pediatric Surgeons of Phoenix", city: "Phoenix", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8085,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ117",GROUP_DESC:"AZ117 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8085,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ117",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ117",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8085,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ117", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9085 ,protection_group_id: -8085, protection_element_id:-8085], primaryKey: false);
      insert('organizations', [ id: 108071, nci_institute_code: "AZ118", name: "Arizona Breast Cancer Specialists", city: "Scottsdale", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8086,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ118",GROUP_DESC:"AZ118 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8086,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ118",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ118",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8086,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ118", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9086 ,protection_group_id: -8086, protection_element_id:-8086], primaryKey: false);
      insert('organizations', [ id: 108072, nci_institute_code: "AZ119", name: "21st Century Oncology-Gilbert", city: "Gilbert", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8087,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ119",GROUP_DESC:"AZ119 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8087,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ119",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ119",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8087,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ119", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9087 ,protection_group_id: -8087, protection_element_id:-8087], primaryKey: false);
    }

    void m3() {
        // all3 (25 terms)
      insert('organizations', [ id: 108073, nci_institute_code: "AZ120", name: "Cancer and Blood Specialists-Bullhead", city: "Bullhead City", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8088,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ120",GROUP_DESC:"AZ120 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8088,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ120",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ120",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8088,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ120", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9088 ,protection_group_id: -8088, protection_element_id:-8088], primaryKey: false);
      insert('organizations', [ id: 108074, nci_institute_code: "AZ121", name: "Mohave Medical Oncology", city: "Kingman", state: "AZ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8089,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ121",GROUP_DESC:"AZ121 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8089,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.AZ121",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.AZ121",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8089,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.AZ121", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9089 ,protection_group_id: -8089, protection_element_id:-8089], primaryKey: false);
      insert('organizations', [ id: 108075, nci_institute_code: "CA359", name: "Pediatric and Adolescent Hematology Oncology Associates", city: "Long Beach", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8090,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA359",GROUP_DESC:"CA359 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8090,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA359",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA359",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8090,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA359", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9090 ,protection_group_id: -8090, protection_element_id:-8090], primaryKey: false);
      insert('organizations', [ id: 108076, nci_institute_code: "CA424", name: "Hematology/Oncology", city: "Beverly Hills", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8091,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA424",GROUP_DESC:"CA424 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8091,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA424",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA424",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8091,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA424", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9091 ,protection_group_id: -8091, protection_element_id:-8091], primaryKey: false);
      insert('organizations', [ id: 108077, nci_institute_code: "CA450", name: "HeamatologyOncology Association", city: "San Carlos", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8092,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA450",GROUP_DESC:"CA450 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8092,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA450",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA450",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8092,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA450", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9092 ,protection_group_id: -8092, protection_element_id:-8092], primaryKey: false);
      insert('organizations', [ id: 108078, nci_institute_code: "CA701", name: "Charles Wiseman MD FACP", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8093,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA701",GROUP_DESC:"CA701 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8093,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA701",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA701",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8093,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA701", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9093 ,protection_group_id: -8093, protection_element_id:-8093], primaryKey: false);
      insert('organizations', [ id: 108079, nci_institute_code: "CA702", name: "Loma Linda Oncology Medical Group", city: "Claremont", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8094,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA702",GROUP_DESC:"CA702 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8094,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA702",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA702",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8094,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA702", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9094 ,protection_group_id: -8094, protection_element_id:-8094], primaryKey: false);
      insert('organizations', [ id: 108080, nci_institute_code: "CA703", name: "Loma Linda Oncology Medical Group", city: "Redlands", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8095,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA703",GROUP_DESC:"CA703 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8095,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA703",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA703",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8095,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA703", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9095 ,protection_group_id: -8095, protection_element_id:-8095], primaryKey: false);
      insert('organizations', [ id: 108081, nci_institute_code: "CA704", name: "Jay Chen MD", city: "Modesto", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8096,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA704",GROUP_DESC:"CA704 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8096,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA704",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA704",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8096,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA704", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9096 ,protection_group_id: -8096, protection_element_id:-8096], primaryKey: false);
      insert('organizations', [ id: 108082, nci_institute_code: "CA705", name: "Promila Dhanuka MD Incorporated", city: "Redding", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8097,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA705",GROUP_DESC:"CA705 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8097,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA705",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA705",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8097,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA705", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9097 ,protection_group_id: -8097, protection_element_id:-8097], primaryKey: false);
      insert('organizations', [ id: 108083, nci_institute_code: "CA706", name: "San Francisco Surgical Medical Group", city: "San Francisco", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8098,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA706",GROUP_DESC:"CA706 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8098,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA706",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA706",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8098,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA706", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9098 ,protection_group_id: -8098, protection_element_id:-8098], primaryKey: false);
      insert('organizations', [ id: 108084, nci_institute_code: "CA707", name: "Oasis Surgical", city: "Turlock", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8099,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA707",GROUP_DESC:"CA707 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8099,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA707",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA707",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8099,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA707", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9099 ,protection_group_id: -8099, protection_element_id:-8099], primaryKey: false);
      insert('organizations', [ id: 108085, nci_institute_code: "CA708", name: "Community Oncology Group", city: "Upland", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8100,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA708",GROUP_DESC:"CA708 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8100,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA708",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA708",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8100,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA708", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9100 ,protection_group_id: -8100, protection_element_id:-8100], primaryKey: false);
      insert('organizations', [ id: 108086, nci_institute_code: "CA709", name: "Los Angeles Hematology Oncology Medical Group", city: "Glendale", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8101,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA709",GROUP_DESC:"CA709 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8101,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA709",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA709",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8101,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA709", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9101 ,protection_group_id: -8101, protection_element_id:-8101], primaryKey: false);
      insert('organizations', [ id: 108087, nci_institute_code: "CA710", name: "Associated Urologists of Orange County", city: "Santa Ana", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8102,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA710",GROUP_DESC:"CA710 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8102,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA710",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA710",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8102,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA710", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9102 ,protection_group_id: -8102, protection_element_id:-8102], primaryKey: false);
      insert('organizations', [ id: 108088, nci_institute_code: "CA711", name: "Affiliated Urologists of Orange County", city: "Orange", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8103,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA711",GROUP_DESC:"CA711 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8103,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA711",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA711",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8103,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA711", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9103 ,protection_group_id: -8103, protection_element_id:-8103], primaryKey: false);
      insert('organizations', [ id: 108089, nci_institute_code: "CA712", name: "Reconstructive Services Medical Group Inc", city: "Orange", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8104,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA712",GROUP_DESC:"CA712 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8104,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA712",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA712",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8104,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA712", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9104 ,protection_group_id: -8104, protection_element_id:-8104], primaryKey: false);
      insert('organizations', [ id: 108090, nci_institute_code: "CA713", name: "Vantage Oncology", city: "San Bernardino", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8105,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA713",GROUP_DESC:"CA713 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8105,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA713",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA713",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8105,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA713", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9105 ,protection_group_id: -8105, protection_element_id:-8105], primaryKey: false);
      insert('organizations', [ id: 108091, nci_institute_code: "CA714", name: "Stuart J Gourlay MD Corporation", city: "Pinole", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8106,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA714",GROUP_DESC:"CA714 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8106,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA714",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA714",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8106,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA714", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9106 ,protection_group_id: -8106, protection_element_id:-8106], primaryKey: false);
      insert('organizations', [ id: 108092, nci_institute_code: "CA715", name: "Wilshire Oncology Medical Group-West Covina", city: "West Covina", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8107,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA715",GROUP_DESC:"CA715 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8107,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA715",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA715",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8107,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA715", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9107 ,protection_group_id: -8107, protection_element_id:-8107], primaryKey: false);
      insert('organizations', [ id: 108093, nci_institute_code: "CA716", name: "Center for Breast Care Inc", city: "Glendale", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8108,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA716",GROUP_DESC:"CA716 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8108,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA716",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA716",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8108,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA716", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9108 ,protection_group_id: -8108, protection_element_id:-8108], primaryKey: false);
      insert('organizations', [ id: 108094, nci_institute_code: "CA717", name: "California Cancer Care Inc", city: "San Mateo", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8109,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA717",GROUP_DESC:"CA717 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8109,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA717",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA717",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8109,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA717", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9109 ,protection_group_id: -8109, protection_element_id:-8109], primaryKey: false);
      insert('organizations', [ id: 108095, nci_institute_code: "CA718", name: "Yosemite Pathology Medical Group Inc", city: "Modesto", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8110,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA718",GROUP_DESC:"CA718 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8110,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA718",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA718",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8110,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA718", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9110 ,protection_group_id: -8110, protection_element_id:-8110], primaryKey: false);
      insert('organizations', [ id: 108096, nci_institute_code: "CA719", name: "Emanuel Medical Center", city: "Turlock", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8111,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA719",GROUP_DESC:"CA719 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8111,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA719",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA719",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8111,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA719", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9111 ,protection_group_id: -8111, protection_element_id:-8111], primaryKey: false);
      insert('organizations', [ id: 108097, nci_institute_code: "CA720", name: "Stanford Emanuel Radiation Oncology Center", city: "Turlock", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8112,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA720",GROUP_DESC:"CA720 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8112,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA720",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA720",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8112,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA720", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9112 ,protection_group_id: -8112, protection_element_id:-8112], primaryKey: false);
    }

    void m4() {
        // all4 (25 terms)
      insert('organizations', [ id: 108098, nci_institute_code: "CA721", name: "Mercy UC Davis Cancer Center", city: "Merced", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8113,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA721",GROUP_DESC:"CA721 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8113,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA721",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA721",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8113,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA721", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9113 ,protection_group_id: -8113, protection_element_id:-8113], primaryKey: false);
      insert('organizations', [ id: 108099, nci_institute_code: "CA722", name: "Mercy Medical Center Merced-Dominican Campus", city: "Merced", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8114,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA722",GROUP_DESC:"CA722 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8114,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA722",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA722",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8114,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA722", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9114 ,protection_group_id: -8114, protection_element_id:-8114], primaryKey: false);
      insert('organizations', [ id: 108100, nci_institute_code: "CA723", name: "Sharon E Pereira MD", city: "Redding", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8115,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA723",GROUP_DESC:"CA723 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8115,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA723",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA723",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8115,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA723", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9115 ,protection_group_id: -8115, protection_element_id:-8115], primaryKey: false);
      insert('organizations', [ id: 108101, nci_institute_code: "CA724", name: "Feather River Hospital", city: "Paradise", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8116,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA724",GROUP_DESC:"CA724 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8116,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA724",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA724",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8116,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA724", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9116 ,protection_group_id: -8116, protection_element_id:-8116], primaryKey: false);
      insert('organizations', [ id: 108102, nci_institute_code: "CA725", name: "UCLA Center for Clinical AIDS Research and Education", city: "Los Angeles", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8117,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA725",GROUP_DESC:"CA725 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8117,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA725",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA725",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8117,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA725", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9117 ,protection_group_id: -8117, protection_element_id:-8117], primaryKey: false);
      insert('organizations', [ id: 108103, nci_institute_code: "CA726", name: "Kaiser Permanente-Ontario Vineyard Medical Offices and Ambulatory SurgiCenter", city: "Ontario", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8118,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA726",GROUP_DESC:"CA726 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8118,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA726",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA726",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8118,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA726", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9118 ,protection_group_id: -8118, protection_element_id:-8118], primaryKey: false);
      insert('organizations', [ id: 108104, nci_institute_code: "CA727", name: "Saint Agnes Cancer Center", city: "Fresno", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8119,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA727",GROUP_DESC:"CA727 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8119,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA727",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA727",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8119,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA727", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9119 ,protection_group_id: -8119, protection_element_id:-8119], primaryKey: false);
      insert('organizations', [ id: 108105, nci_institute_code: "CA728", name: "University of California Medical Center At Irvine-Irvine Campus", city: "Irvine", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8120,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA728",GROUP_DESC:"CA728 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8120,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA728",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA728",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8120,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA728", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9120 ,protection_group_id: -8120, protection_element_id:-8120], primaryKey: false);
      insert('organizations', [ id: 108106, nci_institute_code: "CA729", name: "Mercy Medical Group", city: "Sacramento", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8121,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA729",GROUP_DESC:"CA729 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8121,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA729",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA729",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8121,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA729", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9121 ,protection_group_id: -8121, protection_element_id:-8121], primaryKey: false);
      insert('organizations', [ id: 108107, nci_institute_code: "CA730", name: "Kaiser Permanente Medical Group", city: "South San Francisco", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8122,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA730",GROUP_DESC:"CA730 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8122,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA730",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA730",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8122,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA730", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9122 ,protection_group_id: -8122, protection_element_id:-8122], primaryKey: false);
      insert('organizations', [ id: 108108, nci_institute_code: "CA731", name: "Innovative Cancer Research Consortium", city: "Glendale", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8123,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA731",GROUP_DESC:"CA731 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8123,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA731",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA731",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8123,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA731", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9123 ,protection_group_id: -8123, protection_element_id:-8123], primaryKey: false);
      insert('organizations', [ id: 108109, nci_institute_code: "CA732", name: "Desert Cancer Care Inc", city: "Rancho Mirage", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8124,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA732",GROUP_DESC:"CA732 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8124,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA732",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA732",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8124,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA732", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9124 ,protection_group_id: -8124, protection_element_id:-8124], primaryKey: false);
      insert('organizations', [ id: 108110, nci_institute_code: "CA733", name: "Valley Radiotherapy Associates at Saint Joesph Hospital", city: "Orange", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8125,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA733",GROUP_DESC:"CA733 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8125,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA733",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA733",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8125,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA733", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9125 ,protection_group_id: -8125, protection_element_id:-8125], primaryKey: false);
      insert('organizations', [ id: 108111, nci_institute_code: "CA734", name: "South Orange County Surgical Medical Group Inc", city: "Laguna Hills", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8126,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA734",GROUP_DESC:"CA734 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8126,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA734",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA734",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8126,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA734", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9126 ,protection_group_id: -8126, protection_element_id:-8126], primaryKey: false);
      insert('organizations', [ id: 108112, nci_institute_code: "CA735", name: "Kaiser Permanente-Deer Valley Medical Center", city: "Antioch", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8127,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA735",GROUP_DESC:"CA735 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8127,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA735",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA735",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8127,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA735", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9127 ,protection_group_id: -8127, protection_element_id:-8127], primaryKey: false);
      insert('organizations', [ id: 108113, nci_institute_code: "CA736", name: "Napa Valley Cardiac and Thoracic Surgery Inc", city: "Napa", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8128,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA736",GROUP_DESC:"CA736 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8128,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA736",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA736",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8128,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA736", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9128 ,protection_group_id: -8128, protection_element_id:-8128], primaryKey: false);
      insert('organizations', [ id: 108114, nci_institute_code: "CA737", name: "Davood Vafai MD", city: "Rancho Mirage", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8129,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA737",GROUP_DESC:"CA737 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8129,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA737",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA737",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8129,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA737", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9129 ,protection_group_id: -8129, protection_element_id:-8129], primaryKey: false);
      insert('organizations', [ id: 108115, nci_institute_code: "CA738", name: "Coast Hematology-Oncology Associates", city: "Long Beach", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8130,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA738",GROUP_DESC:"CA738 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8130,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA738",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA738",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8130,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA738", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9130 ,protection_group_id: -8130, protection_element_id:-8130], primaryKey: false);
      insert('organizations', [ id: 108116, nci_institute_code: "CA739", name: "TCS Medical Group", city: "Long Beach", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8131,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA739",GROUP_DESC:"CA739 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8131,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA739",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA739",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8131,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA739", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9131 ,protection_group_id: -8131, protection_element_id:-8131], primaryKey: false);
      insert('organizations', [ id: 108117, nci_institute_code: "CA740", name: "Coluzzi and Tetef Medical Corporation", city: "Irvine", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8132,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA740",GROUP_DESC:"CA740 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8132,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA740",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA740",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8132,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA740", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9132 ,protection_group_id: -8132, protection_element_id:-8132], primaryKey: false);
      insert('organizations', [ id: 108118, nci_institute_code: "CA741", name: "Desert Breast and Osteoporosis Institute", city: "La Quinta", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8133,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA741",GROUP_DESC:"CA741 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8133,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA741",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA741",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8133,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA741", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9133 ,protection_group_id: -8133, protection_element_id:-8133], primaryKey: false);
      insert('organizations', [ id: 108119, nci_institute_code: "CA742", name: "Carey A Cullinane MD Inc", city: "Long Beach", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8134,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA742",GROUP_DESC:"CA742 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8134,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA742",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA742",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8134,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA742", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9134 ,protection_group_id: -8134, protection_element_id:-8134], primaryKey: false);
      insert('organizations', [ id: 108120, nci_institute_code: "CA743", name: "Florence R Wheeler Cancer Center", city: "Bakersfield", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8135,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA743",GROUP_DESC:"CA743 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8135,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA743",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA743",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8135,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA743", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9135 ,protection_group_id: -8135, protection_element_id:-8135], primaryKey: false);
      insert('organizations', [ id: 108121, nci_institute_code: "CA744", name: "Pacific Thoracic Surgery Inc", city: "Lauguna Hills", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8136,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA744",GROUP_DESC:"CA744 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8136,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA744",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA744",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8136,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA744", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9136 ,protection_group_id: -8136, protection_element_id:-8136], primaryKey: false);
      insert('organizations', [ id: 108122, nci_institute_code: "CA745", name: "Pasadena Colon and Rectal Medical Group Incorporated", city: "Pasadena", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8137,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA745",GROUP_DESC:"CA745 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8137,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA745",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA745",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8137,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA745", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9137 ,protection_group_id: -8137, protection_element_id:-8137], primaryKey: false);
    }

    void m5() {
        // all5 (25 terms)
      insert('organizations', [ id: 108123, nci_institute_code: "CA746", name: "South Orange County Hematology Oncology Associates-Mission Viejo", city: "Mission Viejo", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8138,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA746",GROUP_DESC:"CA746 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8138,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA746",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA746",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8138,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA746", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9138 ,protection_group_id: -8138, protection_element_id:-8138], primaryKey: false);
      insert('organizations', [ id: 108124, nci_institute_code: "CA747", name: "Ocean View Hematology Oncology", city: "San Clemente", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8139,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA747",GROUP_DESC:"CA747 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8139,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA747",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA747",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8139,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA747", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9139 ,protection_group_id: -8139, protection_element_id:-8139], primaryKey: false);
      insert('organizations', [ id: 108125, nci_institute_code: "CA748", name: "Solace Cancer Care", city: "Redding", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8140,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA748",GROUP_DESC:"CA748 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8140,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA748",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA748",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8140,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA748", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9140 ,protection_group_id: -8140, protection_element_id:-8140], primaryKey: false);
      insert('organizations', [ id: 108126, nci_institute_code: "CA749", name: "Pediatric Surgical Associates Inc", city: "Orange", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8141,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA749",GROUP_DESC:"CA749 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8141,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA749",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA749",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8141,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA749", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9141 ,protection_group_id: -8141, protection_element_id:-8141], primaryKey: false);
      insert('organizations', [ id: 108127, nci_institute_code: "CA750", name: "Genetics Center", city: "Orange", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8142,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA750",GROUP_DESC:"CA750 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8142,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA750",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA750",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8142,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA750", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9142 ,protection_group_id: -8142, protection_element_id:-8142], primaryKey: false);
      insert('organizations', [ id: 108128, nci_institute_code: "CA751", name: "Center for Breast Care Inc", city: "Burbank", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8143,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA751",GROUP_DESC:"CA751 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8143,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA751",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA751",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8143,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA751", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9143 ,protection_group_id: -8143, protection_element_id:-8143], primaryKey: false);
      insert('organizations', [ id: 108129, nci_institute_code: "CA752", name: "Kaiser Permanente Oakland-Broadway", city: "Oakland", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8144,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA752",GROUP_DESC:"CA752 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8144,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA752",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA752",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8144,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA752", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9144 ,protection_group_id: -8144, protection_element_id:-8144], primaryKey: false);
      insert('organizations', [ id: 108130, nci_institute_code: "CA753", name: "Kaiser Permanente Medical Center-Vacaville", city: "Vacaville", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8145,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA753",GROUP_DESC:"CA753 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8145,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA753",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA753",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8145,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA753", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9145 ,protection_group_id: -8145, protection_element_id:-8145], primaryKey: false);
      insert('organizations', [ id: 108131, nci_institute_code: "CA754", name: "City of Hope- South Pasadena Cancer Center", city: "South Pasadena", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8146,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA754",GROUP_DESC:"CA754 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8146,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA754",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA754",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8146,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA754", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9146 ,protection_group_id: -8146, protection_element_id:-8146], primaryKey: false);
      insert('organizations', [ id: 108132, nci_institute_code: "CA755", name: "Kar Urology", city: "Orange", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8147,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA755",GROUP_DESC:"CA755 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8147,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA755",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA755",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8147,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA755", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9147 ,protection_group_id: -8147, protection_element_id:-8147], primaryKey: false);
      insert('organizations', [ id: 108133, nci_institute_code: "CA756", name: "Women's Surgical Associates", city: "Long Beach", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8148,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA756",GROUP_DESC:"CA756 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8148,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA756",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA756",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8148,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA756", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9148 ,protection_group_id: -8148, protection_element_id:-8148], primaryKey: false);
      insert('organizations', [ id: 108134, nci_institute_code: "CA757", name: "Palo Alto Medical Foundation-Gynecologic Oncology", city: "Mountain View", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8149,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA757",GROUP_DESC:"CA757 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8149,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA757",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA757",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8149,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA757", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9149 ,protection_group_id: -8149, protection_element_id:-8149], primaryKey: false);
      insert('organizations', [ id: 108135, nci_institute_code: "CA758", name: "Bay Area Cancer Research Group LLC", city: "Pleasant Hill", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8150,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA758",GROUP_DESC:"CA758 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8150,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA758",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA758",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8150,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA758", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9150 ,protection_group_id: -8150, protection_element_id:-8150], primaryKey: false);
      insert('organizations', [ id: 108136, nci_institute_code: "CA759", name: "Colon Rectal Surgical Associates", city: "Los Alamitos", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8151,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA759",GROUP_DESC:"CA759 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8151,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA759",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA759",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8151,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA759", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9151 ,protection_group_id: -8151, protection_element_id:-8151], primaryKey: false);
      insert('organizations', [ id: 108137, nci_institute_code: "CA760", name: "Kaiser Permanente Health Care", city: "San Marcos", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8152,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA760",GROUP_DESC:"CA760 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8152,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA760",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA760",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8152,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA760", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9152 ,protection_group_id: -8152, protection_element_id:-8152], primaryKey: false);
      insert('organizations', [ id: 108138, nci_institute_code: "CA761", name: "Sierra Hematology and Oncology-Folsom", city: "Folsom", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8153,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA761",GROUP_DESC:"CA761 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8153,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA761",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA761",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8153,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA761", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9153 ,protection_group_id: -8153, protection_element_id:-8153], primaryKey: false);
      insert('organizations', [ id: 108139, nci_institute_code: "CA762", name: "UC Davis Medical Group-Rocklin", city: "Rocklin", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8154,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA762",GROUP_DESC:"CA762 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8154,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CA762",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CA762",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8154,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CA762", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9154 ,protection_group_id: -8154, protection_element_id:-8154], primaryKey: false);
      insert('organizations', [ id: 108140, nci_institute_code: "CALGA", name: "Cancer and Leukemia Group A", city: "Acardia", state: "CA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8155,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CALGA",GROUP_DESC:"CALGA group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8155,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CALGA",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CALGA",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8155,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CALGA", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9155 ,protection_group_id: -8155, protection_element_id:-8155], primaryKey: false);
      insert('organizations', [ id: 108141, nci_institute_code: "CO099", name: "Urology Consultants PC", city: "Pueblo", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8156,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO099",GROUP_DESC:"CO099 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8156,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO099",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO099",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8156,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO099", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9156 ,protection_group_id: -8156, protection_element_id:-8156], primaryKey: false);
      insert('organizations', [ id: 108142, nci_institute_code: "CO100", name: "Larry D Dillon MD PC", city: "Colorado Springs", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8157,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO100",GROUP_DESC:"CO100 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8157,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO100",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO100",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8157,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO100", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9157 ,protection_group_id: -8157, protection_element_id:-8157], primaryKey: false);
      insert('organizations', [ id: 108143, nci_institute_code: "CO101", name: "Rocky Mountain Hospital for Children-Presbyterian Saint Luke's Medical Center", city: "Denver", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8158,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO101",GROUP_DESC:"CO101 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8158,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO101",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO101",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8158,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO101", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9158 ,protection_group_id: -8158, protection_element_id:-8158], primaryKey: false);
      insert('organizations', [ id: 108144, nci_institute_code: "CO102", name: "Valley View Hospital Cancer Center", city: "Glenwood Springs", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8159,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO102",GROUP_DESC:"CO102 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8159,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO102",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO102",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8159,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO102", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9159 ,protection_group_id: -8159, protection_element_id:-8159], primaryKey: false);
      insert('organizations', [ id: 108145, nci_institute_code: "CO103", name: "Poudre Valley Hospital-Harmony Campus", city: "Fort Collins", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8160,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO103",GROUP_DESC:"CO103 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8160,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO103",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO103",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8160,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO103", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9160 ,protection_group_id: -8160, protection_element_id:-8160], primaryKey: false);
      insert('organizations', [ id: 108146, nci_institute_code: "CO104", name: "National Jewish Health", city: "Denver", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8161,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO104",GROUP_DESC:"CO104 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8161,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO104",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO104",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8161,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO104", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9161 ,protection_group_id: -8161, protection_element_id:-8161], primaryKey: false);
      insert('organizations', [ id: 108147, nci_institute_code: "CO105", name: "Diversified Radiology of Colorado PC", city: "Denver", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8162,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO105",GROUP_DESC:"CO105 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8162,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO105",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO105",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8162,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO105", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9162 ,protection_group_id: -8162, protection_element_id:-8162], primaryKey: false);
    }

    void m6() {
        // all6 (25 terms)
      insert('organizations', [ id: 108148, nci_institute_code: "CO106", name: "Rocky Mountain Pediatric Surgery", city: "Denver", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8163,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO106",GROUP_DESC:"CO106 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8163,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO106",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO106",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8163,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO106", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9163 ,protection_group_id: -8163, protection_element_id:-8163], primaryKey: false);
      insert('organizations', [ id: 108149, nci_institute_code: "CO107", name: "Rocky Mountain Pediatric Hematology Oncology LLC", city: "Denver", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8164,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO107",GROUP_DESC:"CO107 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8164,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO107",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO107",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8164,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO107", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9164 ,protection_group_id: -8164, protection_element_id:-8164], primaryKey: false);
      insert('organizations', [ id: 108150, nci_institute_code: "CO108", name: "Regis University", city: "Denver", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8165,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO108",GROUP_DESC:"CO108 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8165,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO108",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO108",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8165,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO108", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9165 ,protection_group_id: -8165, protection_element_id:-8165], primaryKey: false);
      insert('organizations', [ id: 108151, nci_institute_code: "CO109", name: "San Luis Valley Regional Medical Center", city: "Alamosa", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8166,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO109",GROUP_DESC:"CO109 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8166,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO109",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO109",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8166,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO109", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9166 ,protection_group_id: -8166, protection_element_id:-8166], primaryKey: false);
      insert('organizations', [ id: 108152, nci_institute_code: "CO110", name: "Rocky Mountain Cancer Centers-Colorado Springs", city: "Colorado Springs", state: "CO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8167,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO110",GROUP_DESC:"CO110 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8167,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CO110",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CO110",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8167,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CO110", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9167 ,protection_group_id: -8167, protection_element_id:-8167], primaryKey: false);
      insert('organizations', [ id: 108153, nci_institute_code: "CT110", name: "Medical Oncology and Hematology PC", city: "New Haven", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8168,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT110",GROUP_DESC:"CT110 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8168,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT110",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT110",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8168,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT110", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9168 ,protection_group_id: -8168, protection_element_id:-8168], primaryKey: false);
      insert('organizations', [ id: 108154, nci_institute_code: "CT111", name: "Conneticut Vascular and Thoracic Surgical Associates PC", city: "Fairfield", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8169,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT111",GROUP_DESC:"CT111 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8169,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT111",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT111",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8169,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT111", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9169 ,protection_group_id: -8169, protection_element_id:-8169], primaryKey: false);
      insert('organizations', [ id: 108155, nci_institute_code: "CT112", name: "Hematology and Oncology Associates of Greenwich LLP", city: "Greenwich", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8170,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT112",GROUP_DESC:"CT112 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8170,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT112",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT112",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8170,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT112", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9170 ,protection_group_id: -8170, protection_element_id:-8170], primaryKey: false);
      insert('organizations', [ id: 108156, nci_institute_code: "CT113", name: "Medical Oncology and Hematology PC- Spring Brook Common", city: "Orange", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8171,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT113",GROUP_DESC:"CT113 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8171,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT113",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT113",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8171,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT113", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9171 ,protection_group_id: -8171, protection_element_id:-8171], primaryKey: false);
      insert('organizations', [ id: 108157, nci_institute_code: "CT114", name: "Medical Hematlogy and Oncology PC-Griffin Hospital Ambulatory Care", city: "Derby", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8172,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT114",GROUP_DESC:"CT114 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8172,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT114",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT114",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8172,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT114", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9172 ,protection_group_id: -8172, protection_element_id:-8172], primaryKey: false);
      insert('organizations', [ id: 108158, nci_institute_code: "CT115", name: "Danbury Office of Physician Services General Surgery", city: "Danbury", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8173,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT115",GROUP_DESC:"CT115 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8173,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT115",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT115",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8173,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT115", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9173 ,protection_group_id: -8173, protection_element_id:-8173], primaryKey: false);
      insert('organizations', [ id: 108159, nci_institute_code: "CT116", name: "Medical Oncology and Hematology Group PC-Spring Brook Common", city: "Orange", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8174,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT116",GROUP_DESC:"CT116 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8174,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT116",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT116",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8174,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT116", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9174 ,protection_group_id: -8174, protection_element_id:-8174], primaryKey: false);
      insert('organizations', [ id: 108160, nci_institute_code: "CT117", name: "Saint Francis Medical Group Inc", city: "Hartford", state: "CT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8175,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT117",GROUP_DESC:"CT117 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8175,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CT117",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CT117",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8175,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CT117", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9175 ,protection_group_id: -8175, protection_element_id:-8175], primaryKey: false);
      insert('organizations', [ id: 108161, nci_institute_code: "CTMS", name: "Clinical Trials Monitoring Service", city: "Rockville", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8176,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CTMS",GROUP_DESC:"CTMS group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8176,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.CTMS",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.CTMS",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8176,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.CTMS", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9176 ,protection_group_id: -8176, protection_element_id:-8176], primaryKey: false);
      insert('organizations', [ id: 108162, nci_institute_code: "DC015", name: "Capitol Hill Hospital", city: "Washington", state: "DC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8177,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC015",GROUP_DESC:"DC015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8177,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DC015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DC015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8177,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DC015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9177 ,protection_group_id: -8177, protection_element_id:-8177], primaryKey: false);
      insert('organizations', [ id: 108163, nci_institute_code: "DE037", name: "Saint Francis Hospital-The Woman's Place", city: "Wilmington", state: "DE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8178,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE037",GROUP_DESC:"DE037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8178,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DE037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DE037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8178,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9178 ,protection_group_id: -8178, protection_element_id:-8178], primaryKey: false);
      insert('organizations', [ id: 108164, nci_institute_code: "DE038", name: "Medical Oncology Hematology Consultants PA", city: "Newark", state: "DE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8179,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE038",GROUP_DESC:"DE038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8179,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DE038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DE038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8179,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9179 ,protection_group_id: -8179, protection_element_id:-8179], primaryKey: false);
      insert('organizations', [ id: 108165, nci_institute_code: "DE039", name: "Wolf Creek Surgeons", city: "Dover", state: "DE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8180,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE039",GROUP_DESC:"DE039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8180,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DE039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DE039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8180,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9180 ,protection_group_id: -8180, protection_element_id:-8180], primaryKey: false);
      insert('organizations', [ id: 108166, nci_institute_code: "DE040", name: "Beebe Health Campus", city: "Rehoboth Beach", state: "DE", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8181,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE040",GROUP_DESC:"DE040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8181,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.DE040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.DE040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8181,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.DE040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9181 ,protection_group_id: -8181, protection_element_id:-8181], primaryKey: false);
      insert('organizations', [ id: 108167, nci_institute_code: "FL395", name: "Moffitt Cancer Center at Tampa General Hospital", city: "Tampa", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8182,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL395",GROUP_DESC:"FL395 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8182,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL395",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL395",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8182,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL395", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9182 ,protection_group_id: -8182, protection_element_id:-8182], primaryKey: false);
      insert('organizations', [ id: 108168, nci_institute_code: "FL396", name: "McKnight Brain Institute of the University of Florida", city: "Gainesville", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8183,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL396",GROUP_DESC:"FL396 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8183,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL396",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL396",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8183,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL396", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9183 ,protection_group_id: -8183, protection_element_id:-8183], primaryKey: false);
      insert('organizations', [ id: 108169, nci_institute_code: "FL397", name: "Stuart Oncology Associates PA", city: "Stuart", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8184,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL397",GROUP_DESC:"FL397 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8184,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL397",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL397",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8184,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL397", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9184 ,protection_group_id: -8184, protection_element_id:-8184], primaryKey: false);
      insert('organizations', [ id: 108170, nci_institute_code: "FL398", name: "West Palm Beach Veterans Administration Medical Center", city: "West Palm Beach", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8185,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL398",GROUP_DESC:"FL398 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8185,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL398",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL398",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8185,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL398", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9185 ,protection_group_id: -8185, protection_element_id:-8185], primaryKey: false);
      insert('organizations', [ id: 108171, nci_institute_code: "FL399", name: "Cancer Centers of Central Florida PA", city: "Leesburg", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8186,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL399",GROUP_DESC:"FL399 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8186,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL399",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL399",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8186,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL399", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9186 ,protection_group_id: -8186, protection_element_id:-8186], primaryKey: false);
      insert('organizations', [ id: 108172, nci_institute_code: "FL400", name: "East Orlando Hematology and Oncology Associates", city: "Orlando", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8187,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL400",GROUP_DESC:"FL400 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8187,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL400",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL400",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8187,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL400", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9187 ,protection_group_id: -8187, protection_element_id:-8187], primaryKey: false);
    }

    void m7() {
        // all7 (25 terms)
      insert('organizations', [ id: 108173, nci_institute_code: "FL401", name: "Florida Hospital East Orlando", city: "Orlando", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8188,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL401",GROUP_DESC:"FL401 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8188,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL401",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL401",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8188,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL401", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9188 ,protection_group_id: -8188, protection_element_id:-8188], primaryKey: false);
      insert('organizations', [ id: 108174, nci_institute_code: "FL402", name: "Cardiovascular and Thoracic Surgeon", city: "Vero Beach", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8189,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL402",GROUP_DESC:"FL402 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8189,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL402",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL402",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8189,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL402", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9189 ,protection_group_id: -8189, protection_element_id:-8189], primaryKey: false);
      insert('organizations', [ id: 108175, nci_institute_code: "FL403", name: "Longevity Medical  PLC", city: "Orlando", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8190,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL403",GROUP_DESC:"FL403 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8190,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL403",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL403",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8190,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL403", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9190 ,protection_group_id: -8190, protection_element_id:-8190], primaryKey: false);
      insert('organizations', [ id: 108176, nci_institute_code: "FL404", name: "Cancer Centers of Florida-Ocoee", city: "Ocoee", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8191,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL404",GROUP_DESC:"FL404 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8191,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL404",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL404",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8191,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL404", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9191 ,protection_group_id: -8191, protection_element_id:-8191], primaryKey: false);
      insert('organizations', [ id: 108177, nci_institute_code: "FL405", name: "Florida Cancer Specialists-Englewood", city: "Englewood", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8192,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL405",GROUP_DESC:"FL405 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8192,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL405",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL405",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8192,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL405", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9192 ,protection_group_id: -8192, protection_element_id:-8192], primaryKey: false);
      insert('organizations', [ id: 108178, nci_institute_code: "FL406", name: "North Florida Cancer Center - Lake City", city: "Lake City", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8193,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL406",GROUP_DESC:"FL406 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8193,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL406",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL406",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8193,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL406", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9193 ,protection_group_id: -8193, protection_element_id:-8193], primaryKey: false);
      insert('organizations', [ id: 108179, nci_institute_code: "FL407", name: "Lake City Medical Center", city: "Lake City", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8194,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL407",GROUP_DESC:"FL407 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8194,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL407",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL407",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8194,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL407", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9194 ,protection_group_id: -8194, protection_element_id:-8194], primaryKey: false);
      insert('organizations', [ id: 108180, nci_institute_code: "FL408", name: "Capital Cancer Center", city: "Tallahassee", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8195,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL408",GROUP_DESC:"FL408 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8195,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL408",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL408",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8195,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL408", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9195 ,protection_group_id: -8195, protection_element_id:-8195], primaryKey: false);
      insert('organizations', [ id: 108181, nci_institute_code: "FL409", name: "North Florida Hematology and Oncology-Riverside", city: "Jacksonville", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8196,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL409",GROUP_DESC:"FL409 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8196,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL409",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL409",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8196,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL409", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9196 ,protection_group_id: -8196, protection_element_id:-8196], primaryKey: false);
      insert('organizations', [ id: 108182, nci_institute_code: "FL410", name: "Central Florida Colon and Rectal Surgery", city: "Orlando", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8197,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL410",GROUP_DESC:"FL410 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8197,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL410",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL410",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8197,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL410", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9197 ,protection_group_id: -8197, protection_element_id:-8197], primaryKey: false);
      insert('organizations', [ id: 108183, nci_institute_code: "FL411", name: "Urologic Physicians and Surgeons PA", city: "Jupiter", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8198,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL411",GROUP_DESC:"FL411 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8198,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL411",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL411",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8198,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL411", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9198 ,protection_group_id: -8198, protection_element_id:-8198], primaryKey: false);
      insert('organizations', [ id: 108184, nci_institute_code: "FL412", name: "Surgical Associates of Palm Beach County", city: "Boca Raton", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8199,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL412",GROUP_DESC:"FL412 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8199,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL412",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL412",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8199,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL412", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9199 ,protection_group_id: -8199, protection_element_id:-8199], primaryKey: false);
      insert('organizations', [ id: 108185, nci_institute_code: "FL413", name: "Memorial Breast Cancer Center at Memorial Hospital West", city: "Pembroke Pines", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8200,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL413",GROUP_DESC:"FL413 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8200,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL413",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL413",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8200,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL413", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9200 ,protection_group_id: -8200, protection_element_id:-8200], primaryKey: false);
      insert('organizations', [ id: 108186, nci_institute_code: "FL414", name: "Broward Surgical Associates", city: "Fort Lauderdale", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8201,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL414",GROUP_DESC:"FL414 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8201,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL414",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL414",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8201,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL414", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9201 ,protection_group_id: -8201, protection_element_id:-8201], primaryKey: false);
      insert('organizations', [ id: 108187, nci_institute_code: "FL415", name: "Tallahassee Pulmonary Clinic PA", city: "Tallahassee", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8202,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL415",GROUP_DESC:"FL415 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8202,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL415",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL415",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8202,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL415", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9202 ,protection_group_id: -8202, protection_element_id:-8202], primaryKey: false);
      insert('organizations', [ id: 108188, nci_institute_code: "FL416", name: "Advanced Medical Specialties-North", city: "Miami", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8203,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL416",GROUP_DESC:"FL416 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8203,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL416",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL416",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8203,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL416", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9203 ,protection_group_id: -8203, protection_element_id:-8203], primaryKey: false);
      insert('organizations', [ id: 108189, nci_institute_code: "FL417", name: "University of Miami Sylvester Comprehensive Cancer Center at Deerfield Beach", city: "Deerfield Beach", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8204,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL417",GROUP_DESC:"FL417 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8204,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL417",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL417",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8204,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL417", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9204 ,protection_group_id: -8204, protection_element_id:-8204], primaryKey: false);
      insert('organizations', [ id: 108190, nci_institute_code: "FL418", name: "Florida Cancer Institute-New Hope-Hudson", city: "Hudson", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8205,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL418",GROUP_DESC:"FL418 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8205,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL418",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL418",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8205,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL418", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9205 ,protection_group_id: -8205, protection_element_id:-8205], primaryKey: false);
      insert('organizations', [ id: 108191, nci_institute_code: "FL419", name: "South Florida Thoracic Surgery", city: "Hollywood", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8206,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL419",GROUP_DESC:"FL419 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8206,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL419",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL419",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8206,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL419", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9206 ,protection_group_id: -8206, protection_element_id:-8206], primaryKey: false);
      insert('organizations', [ id: 108192, nci_institute_code: "FL420", name: "The Regional Cancer Center at Wellington", city: "Wellington", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8207,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL420",GROUP_DESC:"FL420 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8207,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL420",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL420",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8207,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL420", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9207 ,protection_group_id: -8207, protection_element_id:-8207], primaryKey: false);
      insert('organizations', [ id: 108193, nci_institute_code: "FL421", name: "Boynton Beach Radiation Oncology LLC", city: "Boynton Beach", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8208,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL421",GROUP_DESC:"FL421 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8208,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL421",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL421",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8208,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL421", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9208 ,protection_group_id: -8208, protection_element_id:-8208], primaryKey: false);
      insert('organizations', [ id: 108194, nci_institute_code: "FL422", name: "Tampa Bay Breast Care Specialists", city: "Tampa", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8209,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL422",GROUP_DESC:"FL422 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8209,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL422",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL422",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8209,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL422", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9209 ,protection_group_id: -8209, protection_element_id:-8209], primaryKey: false);
      insert('organizations', [ id: 108195, nci_institute_code: "FL423", name: "Regional Cancer Center-Lee Memorial Health System", city: "Fort Myers", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8210,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL423",GROUP_DESC:"FL423 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8210,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL423",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL423",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8210,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL423", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9210 ,protection_group_id: -8210, protection_element_id:-8210], primaryKey: false);
      insert('organizations', [ id: 108196, nci_institute_code: "FL424", name: "South Florida Radiation Oncology LLC", city: "West Palm Beach", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8211,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL424",GROUP_DESC:"FL424 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8211,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL424",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL424",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8211,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL424", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9211 ,protection_group_id: -8211, protection_element_id:-8211], primaryKey: false);
      insert('organizations', [ id: 108197, nci_institute_code: "FL425", name: "Cancer Center of South Florida Foundation Inc-Lake Worth", city: "Lake Worth", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8212,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL425",GROUP_DESC:"FL425 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8212,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL425",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL425",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8212,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL425", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9212 ,protection_group_id: -8212, protection_element_id:-8212], primaryKey: false);
    }

    void m8() {
        // all8 (25 terms)
      insert('organizations', [ id: 108198, nci_institute_code: "FL426", name: "Ocala Heart and Vascular Institute- Stuart", city: "Stuart", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8213,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL426",GROUP_DESC:"FL426 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8213,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL426",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL426",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8213,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL426", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9213 ,protection_group_id: -8213, protection_element_id:-8213], primaryKey: false);
      insert('organizations', [ id: 108199, nci_institute_code: "FL427", name: "Watson Clinic LLP-Women's Center", city: "Lakeland", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8214,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL427",GROUP_DESC:"FL427 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8214,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL427",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL427",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8214,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL427", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9214 ,protection_group_id: -8214, protection_element_id:-8214], primaryKey: false);
      insert('organizations', [ id: 108200, nci_institute_code: "FL428", name: "Hialeah Hospital", city: "Hialeah", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8215,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL428",GROUP_DESC:"FL428 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8215,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL428",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL428",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8215,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL428", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9215 ,protection_group_id: -8215, protection_element_id:-8215], primaryKey: false);
      insert('organizations', [ id: 108201, nci_institute_code: "FL429", name: "All Children's Specialty Care of Tampa", city: "Tampa", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8216,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL429",GROUP_DESC:"FL429 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8216,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL429",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL429",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8216,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL429", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9216 ,protection_group_id: -8216, protection_element_id:-8216], primaryKey: false);
      insert('organizations', [ id: 108202, nci_institute_code: "FL430", name: "Pediatric Orthopedics of Southwest Florida", city: "Fort Myers", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8217,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL430",GROUP_DESC:"FL430 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8217,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL430",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL430",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8217,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL430", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9217 ,protection_group_id: -8217, protection_element_id:-8217], primaryKey: false);
      insert('organizations', [ id: 108203, nci_institute_code: "FL431", name: "Children's Surgical Specialists", city: "Boca Raton", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8218,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL431",GROUP_DESC:"FL431 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8218,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL431",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL431",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8218,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL431", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9218 ,protection_group_id: -8218, protection_element_id:-8218], primaryKey: false);
      insert('organizations', [ id: 108204, nci_institute_code: "FL432", name: "Urology Specialists of West Florida Radiation Oncology Center", city: "Clearwater", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8219,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL432",GROUP_DESC:"FL432 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8219,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL432",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL432",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8219,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL432", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9219 ,protection_group_id: -8219, protection_element_id:-8219], primaryKey: false);
      insert('organizations', [ id: 108205, nci_institute_code: "FL433", name: "Southwest Florida Urologic Associates-Cape Coral", city: "Cape Coral", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8220,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL433",GROUP_DESC:"FL433 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8220,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL433",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL433",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8220,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL433", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9220 ,protection_group_id: -8220, protection_element_id:-8220], primaryKey: false);
      insert('organizations', [ id: 108206, nci_institute_code: "FL434", name: "Children's Surgical Associates", city: "Orlando", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8221,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL434",GROUP_DESC:"FL434 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8221,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL434",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL434",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8221,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL434", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9221 ,protection_group_id: -8221, protection_element_id:-8221], primaryKey: false);
      insert('organizations', [ id: 108207, nci_institute_code: "FL435", name: "Center For Radiation Oncology", city: "Brandon", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8222,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL435",GROUP_DESC:"FL435 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8222,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL435",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL435",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8222,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL435", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9222 ,protection_group_id: -8222, protection_element_id:-8222], primaryKey: false);
      insert('organizations', [ id: 108208, nci_institute_code: "FL436", name: "Pensacola Pathologists PA", city: "Pensacola", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8223,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL436",GROUP_DESC:"FL436 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8223,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL436",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL436",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8223,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL436", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9223 ,protection_group_id: -8223, protection_element_id:-8223], primaryKey: false);
      insert('organizations', [ id: 108209, nci_institute_code: "FL437", name: "Florida Cancer Specialists-Morton", city: "Clearwater", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8224,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL437",GROUP_DESC:"FL437 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8224,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL437",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL437",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8224,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL437", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9224 ,protection_group_id: -8224, protection_element_id:-8224], primaryKey: false);
      insert('organizations', [ id: 108210, nci_institute_code: "FL438", name: "Neurological Surgery Associates", city: "Tampa", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8225,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL438",GROUP_DESC:"FL438 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8225,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL438",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL438",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8225,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL438", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9225 ,protection_group_id: -8225, protection_element_id:-8225], primaryKey: false);
      insert('organizations', [ id: 108211, nci_institute_code: "FL439", name: "Advanced Pediatric Surgical Specialists", city: "Orlando", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8226,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL439",GROUP_DESC:"FL439 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8226,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL439",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL439",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8226,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL439", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9226 ,protection_group_id: -8226, protection_element_id:-8226], primaryKey: false);
      insert('organizations', [ id: 108212, nci_institute_code: "FL440", name: "Stern, Drake, Isbell and Associates PA", city: "Tampa", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8227,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL440",GROUP_DESC:"FL440 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8227,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL440",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL440",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8227,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL440", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9227 ,protection_group_id: -8227, protection_element_id:-8227], primaryKey: false);
      insert('organizations', [ id: 108213, nci_institute_code: "FL441", name: "Florida Institute of Research Medicine and Surgery-Cancer Center", city: "Orlando", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8228,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL441",GROUP_DESC:"FL441 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8228,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL441",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL441",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8228,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL441", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9228 ,protection_group_id: -8228, protection_element_id:-8228], primaryKey: false);
      insert('organizations', [ id: 108214, nci_institute_code: "FL442", name: "Bascom Palmer Eye Institute", city: "Miami", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8229,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL442",GROUP_DESC:"FL442 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8229,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL442",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL442",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8229,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL442", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9229 ,protection_group_id: -8229, protection_element_id:-8229], primaryKey: false);
      insert('organizations', [ id: 108215, nci_institute_code: "FL443", name: "Pediatric Surgery PA", city: "Orlando", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8230,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL443",GROUP_DESC:"FL443 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8230,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL443",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL443",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8230,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL443", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9230 ,protection_group_id: -8230, protection_element_id:-8230], primaryKey: false);
      insert('organizations', [ id: 108216, nci_institute_code: "FL444", name: "Gyn Oncology Associates", city: "Tallahassee", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8231,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL444",GROUP_DESC:"FL444 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8231,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL444",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL444",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8231,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL444", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9231 ,protection_group_id: -8231, protection_element_id:-8231], primaryKey: false);
      insert('organizations', [ id: 108217, nci_institute_code: "FL445", name: "21st Century Oncology-Tamarac", city: "Tamarac", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8232,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL445",GROUP_DESC:"FL445 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8232,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL445",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL445",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8232,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL445", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9232 ,protection_group_id: -8232, protection_element_id:-8232], primaryKey: false);
      insert('organizations', [ id: 108218, nci_institute_code: "FL446", name: "South Florida Radiation Oncology-Wellington", city: "Wellington", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8233,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL446",GROUP_DESC:"FL446 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8233,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL446",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL446",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8233,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL446", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9233 ,protection_group_id: -8233, protection_element_id:-8233], primaryKey: false);
      insert('organizations', [ id: 108219, nci_institute_code: "FL447", name: "Florida Institute of Research Medicine and Surgery-Orlando", city: "Orlando", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8234,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL447",GROUP_DESC:"FL447 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8234,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL447",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL447",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8234,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL447", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9234 ,protection_group_id: -8234, protection_element_id:-8234], primaryKey: false);
      insert('organizations', [ id: 108220, nci_institute_code: "FL448", name: "Lake Heart and Cancer Medical Center-Leesburg", city: "Leesburg", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8235,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL448",GROUP_DESC:"FL448 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8235,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL448",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL448",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8235,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL448", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9235 ,protection_group_id: -8235, protection_element_id:-8235], primaryKey: false);
      insert('organizations', [ id: 108221, nci_institute_code: "FL449", name: "Leesburg Regional Medical Center", city: "Leesburg", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8236,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL449",GROUP_DESC:"FL449 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8236,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL449",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL449",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8236,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL449", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9236 ,protection_group_id: -8236, protection_element_id:-8236], primaryKey: false);
      insert('organizations', [ id: 108222, nci_institute_code: "FL450", name: "Central Florida Hematology and Oncology PA", city: "Leesburg", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8237,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL450",GROUP_DESC:"FL450 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8237,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL450",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL450",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8237,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL450", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9237 ,protection_group_id: -8237, protection_element_id:-8237], primaryKey: false);
    }

    void m9() {
        // all9 (25 terms)
      insert('organizations', [ id: 108223, nci_institute_code: "FL451", name: "Florida Cancer Specialists - Naples West", city: "Naples", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8238,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL451",GROUP_DESC:"FL451 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8238,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL451",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL451",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8238,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL451", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9238 ,protection_group_id: -8238, protection_element_id:-8238], primaryKey: false);
      insert('organizations', [ id: 108224, nci_institute_code: "FL452", name: "Lake County Oncology and Hematology-Leesburg", city: "Leesburg", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8239,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL452",GROUP_DESC:"FL452 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8239,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL452",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL452",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8239,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL452", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9239 ,protection_group_id: -8239, protection_element_id:-8239], primaryKey: false);
      insert('organizations', [ id: 108225, nci_institute_code: "FL453", name: "Broward Health-Gynecologic Oncology", city: "Fort Lauderdale", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8240,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL453",GROUP_DESC:"FL453 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8240,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL453",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL453",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8240,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL453", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9240 ,protection_group_id: -8240, protection_element_id:-8240], primaryKey: false);
      insert('organizations', [ id: 108226, nci_institute_code: "FL454", name: "Florida Institute of Research Medicine and Surgery", city: "Orlando", state: "FL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8241,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL454",GROUP_DESC:"FL454 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8241,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.FL454",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.FL454",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8241,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.FL454", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9241 ,protection_group_id: -8241, protection_element_id:-8241], primaryKey: false);
      insert('organizations', [ id: 108227, nci_institute_code: "GA056", name: "Emory University Robert Woodruff Health Sciences Center", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8242,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA056",GROUP_DESC:"GA056 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8242,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA056",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA056",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8242,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA056", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9242 ,protection_group_id: -8242, protection_element_id:-8242], primaryKey: false);
      insert('organizations', [ id: 108228, nci_institute_code: "GA068", name: "South Georgia Oncology Hematology Center", city: "Waycross", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8243,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA068",GROUP_DESC:"GA068 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8243,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA068",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA068",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8243,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA068", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9243 ,protection_group_id: -8243, protection_element_id:-8243], primaryKey: false);
      insert('organizations', [ id: 108229, nci_institute_code: "GA072", name: "Georgia Cancer Specialists", city: "Tucker", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8244,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA072",GROUP_DESC:"GA072 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8244,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA072",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA072",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8244,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA072", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9244 ,protection_group_id: -8244, protection_element_id:-8244], primaryKey: false);
      insert('organizations', [ id: 108230, nci_institute_code: "GA210", name: "Summit Cancer Care at Memorial Health University Medical Center", city: "Savannah", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8245,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA210",GROUP_DESC:"GA210 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8245,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA210",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA210",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8245,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA210", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9245 ,protection_group_id: -8245, protection_element_id:-8245], primaryKey: false);
      insert('organizations', [ id: 108231, nci_institute_code: "GA211", name: "Atlanta Radiology Consultants", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8246,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA211",GROUP_DESC:"GA211 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8246,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA211",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA211",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8246,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA211", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9246 ,protection_group_id: -8246, protection_element_id:-8246], primaryKey: false);
      insert('organizations', [ id: 108232, nci_institute_code: "GA212", name: "Southeastern Gynecologic Oncology LLC-Gainesville", city: "Gainesville", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8247,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA212",GROUP_DESC:"GA212 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8247,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA212",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA212",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8247,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA212", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9247 ,protection_group_id: -8247, protection_element_id:-8247], primaryKey: false);
      insert('organizations', [ id: 108233, nci_institute_code: "GA213", name: "Michael A Quinones MD", city: "Decatur", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8248,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA213",GROUP_DESC:"GA213 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8248,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA213",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA213",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8248,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA213", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9248 ,protection_group_id: -8248, protection_element_id:-8248], primaryKey: false);
      insert('organizations', [ id: 108234, nci_institute_code: "GA214", name: "Georgia Radiation Oncology Consultants", city: "Decatur", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8249,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA214",GROUP_DESC:"GA214 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8249,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA214",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA214",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8249,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA214", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9249 ,protection_group_id: -8249, protection_element_id:-8249], primaryKey: false);
      insert('organizations', [ id: 108235, nci_institute_code: "GA215", name: "Radiotherapy Clinics of Georgia", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8250,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA215",GROUP_DESC:"GA215 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8250,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA215",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA215",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8250,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA215", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9250 ,protection_group_id: -8250, protection_element_id:-8250], primaryKey: false);
      insert('organizations', [ id: 108236, nci_institute_code: "GA216", name: "North Atlanta Surgical Associates PC", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8251,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA216",GROUP_DESC:"GA216 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8251,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA216",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA216",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8251,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA216", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9251 ,protection_group_id: -8251, protection_element_id:-8251], primaryKey: false);
      insert('organizations', [ id: 108237, nci_institute_code: "GA217", name: "Atlanta Cardiothoracic Associates", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8252,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA217",GROUP_DESC:"GA217 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8252,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA217",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA217",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8252,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA217", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9252 ,protection_group_id: -8252, protection_element_id:-8252], primaryKey: false);
      insert('organizations', [ id: 108238, nci_institute_code: "GA218", name: "Georgia Urology - Meridian Mark Plaza", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8253,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA218",GROUP_DESC:"GA218 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8253,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA218",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA218",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8253,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA218", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9253 ,protection_group_id: -8253, protection_element_id:-8253], primaryKey: false);
      insert('organizations', [ id: 108239, nci_institute_code: "GA219", name: "Georgia Cancer Treatment Center-Stockbridge", city: "Stockbridge", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8254,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA219",GROUP_DESC:"GA219 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8254,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA219",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA219",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8254,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA219", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9254 ,protection_group_id: -8254, protection_element_id:-8254], primaryKey: false);
      insert('organizations', [ id: 108240, nci_institute_code: "GA220", name: "North Georgia Radiation Oncology Center", city: "Cumming", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8255,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA220",GROUP_DESC:"GA220 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8255,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA220",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA220",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8255,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA220", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9255 ,protection_group_id: -8255, protection_element_id:-8255], primaryKey: false);
      insert('organizations', [ id: 108241, nci_institute_code: "GA221", name: "Atlanta Womens Cancer Care", city: "Atlanta", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8256,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA221",GROUP_DESC:"GA221 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8256,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA221",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA221",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8256,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA221", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9256 ,protection_group_id: -8256, protection_element_id:-8256], primaryKey: false);
      insert('organizations', [ id: 108242, nci_institute_code: "GA222", name: "Dublin Hematology Oncology Care PC", city: "Dublin", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8257,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA222",GROUP_DESC:"GA222 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8257,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA222",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA222",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8257,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA222", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9257 ,protection_group_id: -8257, protection_element_id:-8257], primaryKey: false);
      insert('organizations', [ id: 108243, nci_institute_code: "GA223", name: "Hutcheson Fuller Cancer Center", city: "Ringgold", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8258,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA223",GROUP_DESC:"GA223 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8258,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA223",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA223",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8258,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA223", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9258 ,protection_group_id: -8258, protection_element_id:-8258], primaryKey: false);
      insert('organizations', [ id: 108244, nci_institute_code: "GA224", name: "Savannah Pediatric Surgery Inc", city: "Savannah", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8259,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA224",GROUP_DESC:"GA224 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8259,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA224",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA224",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8259,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA224", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9259 ,protection_group_id: -8259, protection_element_id:-8259], primaryKey: false);
      insert('organizations', [ id: 108245, nci_institute_code: "GA225", name: "Cardiothoracic Surgery of Savannah PC", city: "Savannah", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8260,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA225",GROUP_DESC:"GA225 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8260,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA225",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA225",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8260,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA225", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9260 ,protection_group_id: -8260, protection_element_id:-8260], primaryKey: false);
      insert('organizations', [ id: 108246, nci_institute_code: "GA226", name: "SouthCoast Medical Group-Imaging Center", city: "Savannah", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8261,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA226",GROUP_DESC:"GA226 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8261,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA226",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA226",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8261,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA226", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9261 ,protection_group_id: -8261, protection_element_id:-8261], primaryKey: false);
      insert('organizations', [ id: 108247, nci_institute_code: "GA227", name: "Northeast Georgia Physicians Group-Medical Park 1", city: "Gainesville", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8262,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA227",GROUP_DESC:"GA227 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8262,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA227",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA227",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8262,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA227", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9262 ,protection_group_id: -8262, protection_element_id:-8262], primaryKey: false);
    }

    void m10() {
        // all10 (25 terms)
      insert('organizations', [ id: 108248, nci_institute_code: "GA228", name: "Central Georgia Radiation Oncology Centers-Macon", city: "Macon", state: "GA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8263,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA228",GROUP_DESC:"GA228 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8263,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GA228",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GA228",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8263,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GA228", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9263 ,protection_group_id: -8263, protection_element_id:-8263], primaryKey: false);
      insert('organizations', [ id: 108249, nci_institute_code: "GICON", name: "Gastrointestinal Consortium", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8264,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GICON",GROUP_DESC:"GICON group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8264,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.GICON",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.GICON",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8264,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.GICON", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9264 ,protection_group_id: -8264, protection_element_id:-8264], primaryKey: false);
      insert('organizations', [ id: 108250, nci_institute_code: "HI034", name: "Pacific Radiation Oncology Incorporated", city: "Honolulu", state: "HI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8265,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI034",GROUP_DESC:"HI034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8265,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HI034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HI034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8265,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9265 ,protection_group_id: -8265, protection_element_id:-8265], primaryKey: false);
      insert('organizations', [ id: 108251, nci_institute_code: "HI035", name: "Surgical Consultants of Hawaii", city: "Honolulu", state: "HI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8266,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI035",GROUP_DESC:"HI035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8266,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HI035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HI035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8266,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9266 ,protection_group_id: -8266, protection_element_id:-8266], primaryKey: false);
      insert('organizations', [ id: 108252, nci_institute_code: "HI036", name: "Castle Community Care-Castle Professional Center", city: "Kaneohe", state: "HI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8267,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI036",GROUP_DESC:"HI036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8267,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HI036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HI036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8267,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9267 ,protection_group_id: -8267, protection_element_id:-8267], primaryKey: false);
      insert('organizations', [ id: 108253, nci_institute_code: "HI037", name: "Pacific Radiation Oncology Inc", city: "Honolulu", state: "HI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8268,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI037",GROUP_DESC:"HI037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8268,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.HI037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.HI037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8268,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.HI037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9268 ,protection_group_id: -8268, protection_element_id:-8268], primaryKey: false);
      insert('organizations', [ id: 108254, nci_institute_code: "IA048", name: "Cogley Physicians Clinic", city: "Council Bluffs", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8269,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA048",GROUP_DESC:"IA048 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8269,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA048",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA048",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8269,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA048", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9269 ,protection_group_id: -8269, protection_element_id:-8269], primaryKey: false);
      insert('organizations', [ id: 108255, nci_institute_code: "IA087", name: "Hematology and Oncology Care", city: "Davenport", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8270,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA087",GROUP_DESC:"IA087 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8270,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA087",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA087",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8270,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA087", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9270 ,protection_group_id: -8270, protection_element_id:-8270], primaryKey: false);
      insert('organizations', [ id: 108256, nci_institute_code: "IA088", name: "The Iowa Clinic West Lakes Campus", city: "West Des Moines", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8271,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA088",GROUP_DESC:"IA088 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8271,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA088",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA088",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8271,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA088", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9271 ,protection_group_id: -8271, protection_element_id:-8271], primaryKey: false);
      insert('organizations', [ id: 108257, nci_institute_code: "IA089", name: "McCreery Cancer Center at Ottumwa Regional", city: "Ottumwa", state: "IA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8272,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA089",GROUP_DESC:"IA089 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8272,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IA089",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IA089",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8272,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IA089", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9272 ,protection_group_id: -8272, protection_element_id:-8272], primaryKey: false);
      insert('organizations', [ id: 108258, nci_institute_code: "ID025", name: "Idaho Urologic Institute PA", city: "Meridian", state: "ID", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8273,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID025",GROUP_DESC:"ID025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8273,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ID025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ID025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8273,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ID025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9273 ,protection_group_id: -8273, protection_element_id:-8273], primaryKey: false);
      insert('organizations', [ id: 108259, nci_institute_code: "IL348", name: "Ingalls Memorial Hospital-Tinley Campus", city: "Tinley Park", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8274,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL348",GROUP_DESC:"IL348 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8274,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL348",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL348",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8274,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL348", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9274 ,protection_group_id: -8274, protection_element_id:-8274], primaryKey: false);
      insert('organizations', [ id: 108260, nci_institute_code: "IL349", name: "OSF Urology", city: "Peoria", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8275,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL349",GROUP_DESC:"IL349 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8275,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL349",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL349",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8275,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL349", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9275 ,protection_group_id: -8275, protection_element_id:-8275], primaryKey: false);
      insert('organizations', [ id: 108261, nci_institute_code: "IL350", name: "Blue Cross and Blue Shield Association", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8276,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL350",GROUP_DESC:"IL350 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8276,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL350",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL350",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8276,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL350", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9276 ,protection_group_id: -8276, protection_element_id:-8276], primaryKey: false);
      insert('organizations', [ id: 108262, nci_institute_code: "IL351", name: "Illinois CancerCare-Monmouth", city: "Monmouth", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8277,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL351",GROUP_DESC:"IL351 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8277,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL351",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL351",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8277,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL351", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9277 ,protection_group_id: -8277, protection_element_id:-8277], primaryKey: false);
      insert('organizations', [ id: 108263, nci_institute_code: "IL352", name: "Illinois CancerCare-Bloomington", city: "Bloomington%", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8278,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL352",GROUP_DESC:"IL352 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8278,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL352",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL352",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8278,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL352", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9278 ,protection_group_id: -8278, protection_element_id:-8278], primaryKey: false);
      insert('organizations', [ id: 108264, nci_institute_code: "IL353", name: "Illinois CancerCare-Peru", city: "Peru", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8279,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL353",GROUP_DESC:"IL353 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8279,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL353",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL353",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8279,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL353", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9279 ,protection_group_id: -8279, protection_element_id:-8279], primaryKey: false);
      insert('organizations', [ id: 108265, nci_institute_code: "IL354", name: "Illinois CancerCare-Pekin", city: "Pekin", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8280,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL354",GROUP_DESC:"IL354 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8280,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL354",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL354",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8280,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL354", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9280 ,protection_group_id: -8280, protection_element_id:-8280], primaryKey: false);
      insert('organizations', [ id: 108266, nci_institute_code: "IL355", name: "Illinois CancerCare-Macomb", city: "Macomb", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8281,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL355",GROUP_DESC:"IL355 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8281,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL355",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL355",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8281,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL355", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9281 ,protection_group_id: -8281, protection_element_id:-8281], primaryKey: false);
      insert('organizations', [ id: 108267, nci_institute_code: "IL356", name: "Illinois CancerCare-Galesburg", city: "Galesburg", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8282,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL356",GROUP_DESC:"IL356 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8282,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL356",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL356",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8282,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL356", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9282 ,protection_group_id: -8282, protection_element_id:-8282], primaryKey: false);
      insert('organizations', [ id: 108268, nci_institute_code: "IL357", name: "Illinois CancerCare-Cottage", city: "Galesburg", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8283,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL357",GROUP_DESC:"IL357 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8283,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL357",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL357",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8283,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL357", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9283 ,protection_group_id: -8283, protection_element_id:-8283], primaryKey: false);
      insert('organizations', [ id: 108269, nci_institute_code: "IL358", name: "Illinois CancerCare-Princeton", city: "Princeton", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8284,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL358",GROUP_DESC:"IL358 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8284,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL358",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL358",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8284,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL358", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9284 ,protection_group_id: -8284, protection_element_id:-8284], primaryKey: false);
      insert('organizations', [ id: 108270, nci_institute_code: "IL359", name: "Illinois CancerCare-Spring Valley", city: "Spring Valley", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8285,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL359",GROUP_DESC:"IL359 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8285,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL359",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL359",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8285,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL359", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9285 ,protection_group_id: -8285, protection_element_id:-8285], primaryKey: false);
      insert('organizations', [ id: 108271, nci_institute_code: "IL360", name: "Illinois CancerCare-Community Cancer Center", city: "Normal", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8286,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL360",GROUP_DESC:"IL360 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8286,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL360",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL360",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8286,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL360", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9286 ,protection_group_id: -8286, protection_element_id:-8286], primaryKey: false);
      insert('organizations', [ id: 108272, nci_institute_code: "IL361", name: "Illinois CancerCare-Canton", city: "Canton", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8287,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL361",GROUP_DESC:"IL361 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8287,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL361",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL361",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8287,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL361", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9287 ,protection_group_id: -8287, protection_element_id:-8287], primaryKey: false);
    }

    void m11() {
        // all11 (25 terms)
      insert('organizations', [ id: 108273, nci_institute_code: "IL362", name: "Illinois CancerCare-Carthage", city: "Carthage", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8288,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL362",GROUP_DESC:"IL362 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8288,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL362",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL362",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8288,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL362", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9288 ,protection_group_id: -8288, protection_element_id:-8288], primaryKey: false);
      insert('organizations', [ id: 108274, nci_institute_code: "IL363", name: "Illinois CancerCare-Havana", city: "Havana", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8289,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL363",GROUP_DESC:"IL363 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8289,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL363",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL363",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8289,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL363", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9289 ,protection_group_id: -8289, protection_element_id:-8289], primaryKey: false);
      insert('organizations', [ id: 108275, nci_institute_code: "IL364", name: "DuPage Medical Group-Ogden", city: "Naperville", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8290,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL364",GROUP_DESC:"IL364 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8290,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL364",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL364",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8290,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL364", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9290 ,protection_group_id: -8290, protection_element_id:-8290], primaryKey: false);
      insert('organizations', [ id: 108276, nci_institute_code: "IL365", name: "Holy Family Medical Center", city: "Monmouth", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8291,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL365",GROUP_DESC:"IL365 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8291,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL365",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL365",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8291,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL365", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9291 ,protection_group_id: -8291, protection_element_id:-8291], primaryKey: false);
      insert('organizations', [ id: 108277, nci_institute_code: "IL366", name: "Illinois CancerCare-Eureka", city: "Eureka", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8292,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL366",GROUP_DESC:"IL366 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8292,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL366",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL366",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8292,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL366", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9292 ,protection_group_id: -8292, protection_element_id:-8292], primaryKey: false);
      insert('organizations', [ id: 108278, nci_institute_code: "IL367", name: "Oncology Specialists SC-Oak Mill Center", city: "Niles", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8293,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL367",GROUP_DESC:"IL367 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8293,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL367",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL367",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8293,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL367", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9293 ,protection_group_id: -8293, protection_element_id:-8293], primaryKey: false);
      insert('organizations', [ id: 108279, nci_institute_code: "IL368", name: "Kankakee Radiation Oncology", city: "Bourbonnais", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8294,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL368",GROUP_DESC:"IL368 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8294,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL368",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL368",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8294,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL368", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9294 ,protection_group_id: -8294, protection_element_id:-8294], primaryKey: false);
      insert('organizations', [ id: 108280, nci_institute_code: "IL369", name: "DuPage Medical Group", city: "Downers Grove", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8295,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL369",GROUP_DESC:"IL369 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8295,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL369",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL369",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8295,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL369", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9295 ,protection_group_id: -8295, protection_element_id:-8295], primaryKey: false);
      insert('organizations', [ id: 108281, nci_institute_code: "IL371", name: "Radiation Oncology of Northern Illinois", city: "Ottawa", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8296,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL371",GROUP_DESC:"IL371 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8296,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL371",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL371",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8296,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL371", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9296 ,protection_group_id: -8296, protection_element_id:-8296], primaryKey: false);
      insert('organizations', [ id: 108282, nci_institute_code: "IL372", name: "Hematology Oncology Associates-Berwyn", city: "Berwyn", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8297,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL372",GROUP_DESC:"IL372 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8297,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL372",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL372",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8297,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL372", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9297 ,protection_group_id: -8297, protection_element_id:-8297], primaryKey: false);
      insert('organizations', [ id: 108283, nci_institute_code: "IL373", name: "Southern Illinois Surgical Consultants SC", city: "Maryville", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8298,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL373",GROUP_DESC:"IL373 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8298,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL373",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL373",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8298,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL373", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9298 ,protection_group_id: -8298, protection_element_id:-8298], primaryKey: false);
      insert('organizations', [ id: 108284, nci_institute_code: "IL374", name: "Children's Memorial Outpatient Center in Lincoln Park", city: "Chicago", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8299,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL374",GROUP_DESC:"IL374 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8299,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL374",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL374",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8299,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL374", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9299 ,protection_group_id: -8299, protection_element_id:-8299], primaryKey: false);
      insert('organizations', [ id: 108285, nci_institute_code: "IL375", name: "Carle Clinic-Champaign on Curtis", city: "Champaign", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8300,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL375",GROUP_DESC:"IL375 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8300,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL375",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL375",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8300,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL375", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9300 ,protection_group_id: -8300, protection_element_id:-8300], primaryKey: false);
      insert('organizations', [ id: 108286, nci_institute_code: "IL376", name: "OSF Medical Group-Children's Medical-Pediatrics", city: "Peoria", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8301,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL376",GROUP_DESC:"IL376 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8301,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL376",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL376",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8301,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL376", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9301 ,protection_group_id: -8301, protection_element_id:-8301], primaryKey: false);
      insert('organizations', [ id: 108287, nci_institute_code: "IL377", name: "Heartland Home Infusion Services-Hinsdale", city: "Hinsdale", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8302,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL377",GROUP_DESC:"IL377 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8302,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL377",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL377",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8302,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL377", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9302 ,protection_group_id: -8302, protection_element_id:-8302], primaryKey: false);
      insert('organizations', [ id: 108288, nci_institute_code: "IL378", name: "Spector, David MD (UIA Investigator)", city: "Moline", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8303,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL378",GROUP_DESC:"IL378 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8303,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL378",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL378",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8303,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL378", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9303 ,protection_group_id: -8303, protection_element_id:-8303], primaryKey: false);
      insert('organizations', [ id: 108289, nci_institute_code: "IL379", name: "North Shore Oncology Hematology Associates-Crystal Lake", city: "Crystal Lake", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8304,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL379",GROUP_DESC:"IL379 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8304,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IL379",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IL379",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8304,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IL379", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9304 ,protection_group_id: -8304, protection_element_id:-8304], primaryKey: false);
      insert('organizations', [ id: 108290, nci_institute_code: "IN158", name: "Saint Margaret Mercy Healthcare Centers-Dyer Campus", city: "Dyer", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8305,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN158",GROUP_DESC:"IN158 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8305,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN158",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN158",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8305,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN158", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9305 ,protection_group_id: -8305, protection_element_id:-8305], primaryKey: false);
      insert('organizations', [ id: 108291, nci_institute_code: "IN159", name: "Indiana Oncology Hematology Consultants", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8306,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN159",GROUP_DESC:"IN159 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8306,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN159",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN159",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8306,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN159", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9306 ,protection_group_id: -8306, protection_element_id:-8306], primaryKey: false);
      insert('organizations', [ id: 108292, nci_institute_code: "IN160", name: "Spring Mill Medical Center", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8307,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN160",GROUP_DESC:"IN160 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8307,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN160",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN160",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8307,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN160", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9307 ,protection_group_id: -8307, protection_element_id:-8307], primaryKey: false);
      insert('organizations', [ id: 108293, nci_institute_code: "IN161", name: "Central Indiana Cancer Centers - Carmel", city: "Carmel", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8308,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN161",GROUP_DESC:"IN161 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8308,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN161",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN161",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8308,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN161", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9308 ,protection_group_id: -8308, protection_element_id:-8308], primaryKey: false);
      insert('organizations', [ id: 108294, nci_institute_code: "IN162", name: "Kendrick Regional Center-Mooresville", city: "Mooresville", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8309,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN162",GROUP_DESC:"IN162 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8309,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN162",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN162",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8309,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN162", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9309 ,protection_group_id: -8309, protection_element_id:-8309], primaryKey: false);
      insert('organizations', [ id: 108295, nci_institute_code: "IN163", name: "Northwest Oncology LLC", city: "Munster", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8310,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN163",GROUP_DESC:"IN163 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8310,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN163",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN163",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8310,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN163", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9310 ,protection_group_id: -8310, protection_element_id:-8310], primaryKey: false);
      insert('organizations', [ id: 108296, nci_institute_code: "IN164", name: "Memorial Hospital and Health Care Center", city: "Jasper", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8311,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN164",GROUP_DESC:"IN164 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8311,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN164",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN164",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8311,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN164", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9311 ,protection_group_id: -8311, protection_element_id:-8311], primaryKey: false);
      insert('organizations', [ id: 108297, nci_institute_code: "IN165", name: "Cancer Health Associates-Michigan City", city: "Michigan City", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8312,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN165",GROUP_DESC:"IN165 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8312,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN165",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN165",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8312,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN165", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9312 ,protection_group_id: -8312, protection_element_id:-8312], primaryKey: false);
    }

    void m12() {
        // all12 (25 terms)
      insert('organizations', [ id: 108298, nci_institute_code: "IN166", name: "Cancer Health Associates-Valparaiso Clinic", city: "Valparaiso", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8313,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN166",GROUP_DESC:"IN166 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8313,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN166",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN166",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8313,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN166", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9313 ,protection_group_id: -8313, protection_element_id:-8313], primaryKey: false);
      insert('organizations', [ id: 108299, nci_institute_code: "IN167", name: "Evansville Multi-Specialty Clinic PC", city: "Evansville", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8314,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN167",GROUP_DESC:"IN167 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8314,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN167",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN167",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8314,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN167", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9314 ,protection_group_id: -8314, protection_element_id:-8314], primaryKey: false);
      insert('organizations', [ id: 108300, nci_institute_code: "IN168", name: "AmeriPath Indiana", city: "Indianapolis", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8315,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN168",GROUP_DESC:"IN168 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8315,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN168",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN168",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8315,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN168", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9315 ,protection_group_id: -8315, protection_element_id:-8315], primaryKey: false);
      insert('organizations', [ id: 108301, nci_institute_code: "IN169", name: "Morgan Hospital and Medical Center", city: "Martinsville", state: "IN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8316,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN169",GROUP_DESC:"IN169 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8316,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.IN169",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.IN169",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8316,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.IN169", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9316 ,protection_group_id: -8316, protection_element_id:-8316], primaryKey: false);
      insert('organizations', [ id: 108302, nci_institute_code: "KS089", name: "Kansas City Cancer Center", city: "Overland Park", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8317,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS089",GROUP_DESC:"KS089 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8317,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS089",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS089",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8317,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS089", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9317 ,protection_group_id: -8317, protection_element_id:-8317], primaryKey: false);
      insert('organizations', [ id: 108303, nci_institute_code: "KS090", name: "Olathe Cancer Center", city: "Olathe", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8318,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS090",GROUP_DESC:"KS090 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8318,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS090",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS090",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8318,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS090", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9318 ,protection_group_id: -8318, protection_element_id:-8318], primaryKey: false);
      insert('organizations', [ id: 108304, nci_institute_code: "KS091", name: "Cancer Center of Kansas-Independence", city: "Independence", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8319,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS091",GROUP_DESC:"KS091 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8319,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS091",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS091",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8319,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS091", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9319 ,protection_group_id: -8319, protection_element_id:-8319], primaryKey: false);
      insert('organizations', [ id: 108305, nci_institute_code: "KS092", name: "Freeman Cancer Institute - Pittsburg", city: "Pittsburg", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8320,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS092",GROUP_DESC:"KS092 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8320,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS092",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS092",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8320,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS092", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9320 ,protection_group_id: -8320, protection_element_id:-8320], primaryKey: false);
      insert('organizations', [ id: 108306, nci_institute_code: "KS093", name: "Hospital District Sixth of Harper County", city: "Anthony", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8321,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS093",GROUP_DESC:"KS093 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8321,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS093",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS093",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8321,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS093", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9321 ,protection_group_id: -8321, protection_element_id:-8321], primaryKey: false);
      insert('organizations', [ id: 108307, nci_institute_code: "KS094", name: "Cancer Center of Kansas - Fort Scott", city: "Fort Scott", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8322,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS094",GROUP_DESC:"KS094 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8322,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS094",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS094",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8322,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS094", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9322 ,protection_group_id: -8322, protection_element_id:-8322], primaryKey: false);
      insert('organizations', [ id: 108308, nci_institute_code: "KS095", name: "Home Medical Services", city: "Wichita", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8323,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS095",GROUP_DESC:"KS095 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8323,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS095",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS095",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8323,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS095", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9323 ,protection_group_id: -8323, protection_element_id:-8323], primaryKey: false);
      insert('organizations', [ id: 108309, nci_institute_code: "KS096", name: "Saint Luke's South Hospital", city: "Overland Park", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8324,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS096",GROUP_DESC:"KS096 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8324,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS096",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS096",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8324,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS096", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9324 ,protection_group_id: -8324, protection_element_id:-8324], primaryKey: false);
      insert('organizations', [ id: 108310, nci_institute_code: "KS097", name: "Cancer Center of Kansas-Liberal", city: "Liberal", state: "KS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8325,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS097",GROUP_DESC:"KS097 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8325,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KS097",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KS097",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8325,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KS097", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9325 ,protection_group_id: -8325, protection_element_id:-8325], primaryKey: false);
      insert('organizations', [ id: 108311, nci_institute_code: "KY085", name: "Lexington Surgeons", city: "Lexington", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8326,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY085",GROUP_DESC:"KY085 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8326,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY085",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY085",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8326,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY085", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9326 ,protection_group_id: -8326, protection_element_id:-8326], primaryKey: false);
      insert('organizations', [ id: 108312, nci_institute_code: "KY086", name: "Kentucky Cancer Clinic", city: "Hazard", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8327,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY086",GROUP_DESC:"KY086 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8327,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY086",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY086",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8327,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY086", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9327 ,protection_group_id: -8327, protection_element_id:-8327], primaryKey: false);
      insert('organizations', [ id: 108313, nci_institute_code: "KY087", name: "Leonard Lawson Cancer Care Center", city: "Pikesville", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8328,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY087",GROUP_DESC:"KY087 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8328,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY087",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY087",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8328,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY087", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9328 ,protection_group_id: -8328, protection_element_id:-8328], primaryKey: false);
      insert('organizations', [ id: 108314, nci_institute_code: "KY088", name: "Louisville Oncology-Suburan", city: "Louisville", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8329,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY088",GROUP_DESC:"KY088 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8329,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY088",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY088",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8329,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY088", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9329 ,protection_group_id: -8329, protection_element_id:-8329], primaryKey: false);
      insert('organizations', [ id: 108315, nci_institute_code: "KY089", name: "Four Rivers Clinical Research", city: "Paducah", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8330,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY089",GROUP_DESC:"KY089 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8330,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY089",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY089",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8330,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY089", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9330 ,protection_group_id: -8330, protection_element_id:-8330], primaryKey: false);
      insert('organizations', [ id: 108316, nci_institute_code: "KY090", name: "Mary Chiles Hospital", city: "Mount Sterling", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8331,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY090",GROUP_DESC:"KY090 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8331,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY090",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY090",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8331,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY090", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9331 ,protection_group_id: -8331, protection_element_id:-8331], primaryKey: false);
      insert('organizations', [ id: 108317, nci_institute_code: "KY091", name: "Montgomery Cancer Center", city: "Mount Sterling", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8332,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY091",GROUP_DESC:"KY091 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8332,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY091",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY091",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8332,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY091", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9332 ,protection_group_id: -8332, protection_element_id:-8332], primaryKey: false);
      insert('organizations', [ id: 108318, nci_institute_code: "KY092", name: "Georgetown Cancer Treatment Center", city: "Georgetown", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8333,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY092",GROUP_DESC:"KY092 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8333,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY092",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY092",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8333,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY092", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9333 ,protection_group_id: -8333, protection_element_id:-8333], primaryKey: false);
      insert('organizations', [ id: 108319, nci_institute_code: "KY093", name: "Maysville Cancer Treatment Center", city: "Maysville", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8334,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY093",GROUP_DESC:"KY093 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8334,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY093",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY093",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8334,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY093", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9334 ,protection_group_id: -8334, protection_element_id:-8334], primaryKey: false);
      insert('organizations', [ id: 108320, nci_institute_code: "KY094", name: "Mount Sterling Cancer Treatment Center", city: "Mount Sterling", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8335,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY094",GROUP_DESC:"KY094 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8335,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY094",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY094",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8335,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY094", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9335 ,protection_group_id: -8335, protection_element_id:-8335], primaryKey: false);
      insert('organizations', [ id: 108321, nci_institute_code: "KY095", name: "Hazard Appalachian Regional Healthcare Regional Medical Center", city: "Hazard", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8336,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY095",GROUP_DESC:"KY095 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8336,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY095",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY095",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8336,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY095", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9336 ,protection_group_id: -8336, protection_element_id:-8336], primaryKey: false);
      insert('organizations', [ id: 108322, nci_institute_code: "KY096", name: "Hardin Memorial Hospital", city: "Elizabethtown", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8337,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY096",GROUP_DESC:"KY096 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8337,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY096",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY096",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8337,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY096", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9337 ,protection_group_id: -8337, protection_element_id:-8337], primaryKey: false);
    }

    void m13() {
        // all13 (25 terms)
      insert('organizations', [ id: 108323, nci_institute_code: "KY097", name: "Surgical Associates of Lexington PSC", city: "Lexington", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8338,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY097",GROUP_DESC:"KY097 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8338,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY097",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY097",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8338,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY097", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9338 ,protection_group_id: -8338, protection_element_id:-8338], primaryKey: false);
      insert('organizations', [ id: 108324, nci_institute_code: "KY098", name: "Norton Healthcare Office of Research Administration", city: "Louisville", state: "KY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8339,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY098",GROUP_DESC:"KY098 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8339,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.KY098",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.KY098",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8339,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.KY098", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9339 ,protection_group_id: -8339, protection_element_id:-8339], primaryKey: false);
      insert('organizations', [ id: 108325, nci_institute_code: "LA108", name: "River Region Cancer Screening and Early Detection Center", city: "Sorrento", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8340,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA108",GROUP_DESC:"LA108 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8340,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA108",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA108",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8340,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA108", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9340 ,protection_group_id: -8340, protection_element_id:-8340], primaryKey: false);
      insert('organizations', [ id: 108326, nci_institute_code: "LA109", name: "Cancer Care Specialists", city: "Thibodaux", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8341,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA109",GROUP_DESC:"LA109 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8341,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA109",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA109",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8341,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA109", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9341 ,protection_group_id: -8341, protection_element_id:-8341], primaryKey: false);
      insert('organizations', [ id: 108327, nci_institute_code: "LA110", name: "East Jefferson Radiation Oncology LLC", city: "Metairie", state: "LA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8342,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA110",GROUP_DESC:"LA110 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8342,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.LA110",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.LA110",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8342,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.LA110", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9342 ,protection_group_id: -8342, protection_element_id:-8342], primaryKey: false);
      insert('organizations', [ id: 108328, nci_institute_code: "MA174", name: "Comprehensive Breast Center", city: "Springfield", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8343,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA174",GROUP_DESC:"MA174 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8343,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA174",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA174",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8343,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA174", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9343 ,protection_group_id: -8343, protection_element_id:-8343], primaryKey: false);
      insert('organizations', [ id: 108329, nci_institute_code: "MA176", name: "Northeastern University", city: "Boston", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8344,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA176",GROUP_DESC:"MA176 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8344,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA176",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA176",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8344,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA176", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9344 ,protection_group_id: -8344, protection_element_id:-8344], primaryKey: false);
      insert('organizations', [ id: 108330, nci_institute_code: "MA177", name: "Baystate Mary Lane Hospital", city: "Ware", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8345,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA177",GROUP_DESC:"MA177 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8345,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA177",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA177",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8345,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA177", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9345 ,protection_group_id: -8345, protection_element_id:-8345], primaryKey: false);
      insert('organizations', [ id: 108331, nci_institute_code: "MA178", name: "Winchester Hospital Hematology Oncology Center", city: "Woburn", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8346,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA178",GROUP_DESC:"MA178 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8346,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA178",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA178",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8346,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA178", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9346 ,protection_group_id: -8346, protection_element_id:-8346], primaryKey: false);
      insert('organizations', [ id: 108332, nci_institute_code: "MA179", name: "Commonwealth Newburyport Cancer Center", city: "Newburyport", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8347,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA179",GROUP_DESC:"MA179 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8347,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA179",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA179",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8347,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA179", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9347 ,protection_group_id: -8347, protection_element_id:-8347], primaryKey: false);
      insert('organizations', [ id: 108333, nci_institute_code: "MA180", name: "Pediatric Surgical Services Inc", city: "Springfield", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8348,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA180",GROUP_DESC:"MA180 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8348,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA180",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA180",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8348,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA180", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9348 ,protection_group_id: -8348, protection_element_id:-8348], primaryKey: false);
      insert('organizations', [ id: 108334, nci_institute_code: "MA181", name: "Center for Molecular Imaging Research-Simches Research Center", city: "Boston", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8349,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA181",GROUP_DESC:"MA181 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8349,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA181",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA181",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8349,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA181", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9349 ,protection_group_id: -8349, protection_element_id:-8349], primaryKey: false);
      insert('organizations', [ id: 108335, nci_institute_code: "MA182", name: "Shriners Burns Hospital", city: "Boston", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8350,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA182",GROUP_DESC:"MA182 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8350,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA182",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA182",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8350,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA182", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9350 ,protection_group_id: -8350, protection_element_id:-8350], primaryKey: false);
      insert('organizations', [ id: 108336, nci_institute_code: "MA183", name: "Cape Cod Surgeons", city: "Falmouth", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8351,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA183",GROUP_DESC:"MA183 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8351,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA183",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA183",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8351,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA183", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9351 ,protection_group_id: -8351, protection_element_id:-8351], primaryKey: false);
      insert('organizations', [ id: 108337, nci_institute_code: "MA184", name: "New England Hematology Oncology Associates-Newton", city: "Newton", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8352,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA184",GROUP_DESC:"MA184 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8352,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MA184",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MA184",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8352,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MA184", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9352 ,protection_group_id: -8352, protection_element_id:-8352], primaryKey: false);
      insert('organizations', [ id: 108338, nci_institute_code: "MD041", name: "Washington Hospital Center", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8353,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD041",GROUP_DESC:"MD041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8353,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8353,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9353 ,protection_group_id: -8353, protection_element_id:-8353], primaryKey: false);
      insert('organizations', [ id: 108339, nci_institute_code: "MD173", name: "Peninsula Cancer Care Center", city: "Salisbury", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8354,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD173",GROUP_DESC:"MD173 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8354,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD173",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD173",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8354,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD173", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9354 ,protection_group_id: -8354, protection_element_id:-8354], primaryKey: false);
      insert('organizations', [ id: 108340, nci_institute_code: "MD174", name: "Colon Rectal Surgical Associates", city: "Owings Mills", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8355,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD174",GROUP_DESC:"MD174 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8355,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD174",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD174",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8355,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD174", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9355 ,protection_group_id: -8355, protection_element_id:-8355], primaryKey: false);
      insert('organizations', [ id: 108341, nci_institute_code: "MD175", name: "Susan T Forlifer MD LLC", city: "Easton", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8356,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD175",GROUP_DESC:"MD175 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8356,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD175",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD175",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8356,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD175", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9356 ,protection_group_id: -8356, protection_element_id:-8356], primaryKey: false);
      insert('organizations', [ id: 108342, nci_institute_code: "MD176", name: "The John R Marsh Cancer Center", city: "Hagerstown", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8357,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD176",GROUP_DESC:"MD176 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8357,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD176",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD176",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8357,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD176", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9357 ,protection_group_id: -8357, protection_element_id:-8357], primaryKey: false);
      insert('organizations', [ id: 108343, nci_institute_code: "MD177", name: "National Institute of Child Health and Human Development", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8358,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD177",GROUP_DESC:"MD177 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8358,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD177",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD177",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8358,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD177", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9358 ,protection_group_id: -8358, protection_element_id:-8358], primaryKey: false);
      insert('organizations', [ id: 108344, nci_institute_code: "MD178", name: "Upper Chesapeake Surgical Associates", city: "Bel Air", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8359,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD178",GROUP_DESC:"MD178 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8359,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD178",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD178",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8359,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD178", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9359 ,protection_group_id: -8359, protection_element_id:-8359], primaryKey: false);
      insert('organizations', [ id: 108345, nci_institute_code: "MD179", name: "Advanced Surgery PC", city: "Rockville", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8360,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD179",GROUP_DESC:"MD179 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8360,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD179",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD179",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8360,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD179", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9360 ,protection_group_id: -8360, protection_element_id:-8360], primaryKey: false);
      insert('organizations', [ id: 108346, nci_institute_code: "MD180", name: "Chesapeake Oncology Hematology Associates PA-Chester", city: "Chester", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8361,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD180",GROUP_DESC:"MD180 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8361,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD180",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD180",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8361,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD180", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9361 ,protection_group_id: -8361, protection_element_id:-8361], primaryKey: false);
      insert('organizations', [ id: 108347, nci_institute_code: "MD181", name: "Prince George's Hospital Center", city: "Cheverly", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8362,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD181",GROUP_DESC:"MD181 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8362,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD181",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD181",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8362,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD181", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9362 ,protection_group_id: -8362, protection_element_id:-8362], primaryKey: false);
    }

    void m14() {
        // all14 (25 terms)
      insert('organizations', [ id: 108348, nci_institute_code: "MD182", name: "Cheaspeake Oncology Hematology Associates PA-Annapolis Cancer Center", city: "Annapolis", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8363,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD182",GROUP_DESC:"MD182 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8363,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD182",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD182",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8363,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD182", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9363 ,protection_group_id: -8363, protection_element_id:-8363], primaryKey: false);
      insert('organizations', [ id: 108349, nci_institute_code: "MD183", name: "University of Maryland Oncology Associates", city: "Glen Burnie", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8364,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD183",GROUP_DESC:"MD183 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8364,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD183",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD183",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8364,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD183", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9364 ,protection_group_id: -8364, protection_element_id:-8364], primaryKey: false);
      insert('organizations', [ id: 108350, nci_institute_code: "MD184", name: "University of Maryland Baltimore County", city: "Baltimore", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8365,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD184",GROUP_DESC:"MD184 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8365,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD184",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD184",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8365,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD184", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9365 ,protection_group_id: -8365, protection_element_id:-8365], primaryKey: false);
      insert('organizations', [ id: 108351, nci_institute_code: "MD185", name: "Eric Oristian MD", city: "Wheaton", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8366,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD185",GROUP_DESC:"MD185 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8366,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD185",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD185",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8366,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD185", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9366 ,protection_group_id: -8366, protection_element_id:-8366], primaryKey: false);
      insert('organizations', [ id: 108352, nci_institute_code: "MD186", name: "The University of Maryland Dental School", city: "Baltimore", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8367,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD186",GROUP_DESC:"MD186 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8367,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD186",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD186",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8367,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD186", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9367 ,protection_group_id: -8367, protection_element_id:-8367], primaryKey: false);
      insert('organizations', [ id: 108353, nci_institute_code: "MD187", name: "Medstar Research Institute", city: "Hyattsville", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8368,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD187",GROUP_DESC:"MD187 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8368,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD187",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD187",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8368,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD187", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9368 ,protection_group_id: -8368, protection_element_id:-8368], primaryKey: false);
      insert('organizations', [ id: 108354, nci_institute_code: "MD188", name: "Shore Comprehensive Urology", city: "Easton", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8369,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD188",GROUP_DESC:"MD188 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8369,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD188",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD188",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8369,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD188", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9369 ,protection_group_id: -8369, protection_element_id:-8369], primaryKey: false);
      insert('organizations', [ id: 108355, nci_institute_code: "MD189", name: "Oncology Care Consultants", city: "Frederick", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8370,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD189",GROUP_DESC:"MD189 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8370,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MD189",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MD189",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8370,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MD189", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9370 ,protection_group_id: -8370, protection_element_id:-8370], primaryKey: false);
      insert('organizations', [ id: 108356, nci_institute_code: "ME037", name: "Harold Alfond Center for Cancer Care", city: "Augusta", state: "ME", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8371,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME037",GROUP_DESC:"ME037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8371,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ME037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ME037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8371,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9371 ,protection_group_id: -8371, protection_element_id:-8371], primaryKey: false);
      insert('organizations', [ id: 108357, nci_institute_code: "ME038", name: "Mercy Hospital at Fore River", city: "Portland", state: "ME", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8372,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME038",GROUP_DESC:"ME038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8372,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ME038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ME038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8372,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9372 ,protection_group_id: -8372, protection_element_id:-8372], primaryKey: false);
      insert('organizations', [ id: 108358, nci_institute_code: "ME039", name: "Cardiothoracic Surgery of Maine", city: "Bangor", state: "ME", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8373,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME039",GROUP_DESC:"ME039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8373,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ME039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ME039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8373,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9373 ,protection_group_id: -8373, protection_element_id:-8373], primaryKey: false);
      insert('organizations', [ id: 108359, nci_institute_code: "ME040", name: "Lafayette Family Cancer Center-EMMC", city: "Brewer", state: "ME", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8374,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME040",GROUP_DESC:"ME040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8374,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ME040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ME040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8374,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ME040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9374 ,protection_group_id: -8374, protection_element_id:-8374], primaryKey: false);
      insert('organizations', [ id: 108360, nci_institute_code: "MI104", name: "Rads, P.C. (Ostepatic)", city: "Madison Heights", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8375,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI104",GROUP_DESC:"MI104 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8375,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI104",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI104",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8375,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI104", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9375 ,protection_group_id: -8375, protection_element_id:-8375], primaryKey: false);
      insert('organizations', [ id: 108361, nci_institute_code: "MI255", name: "Oakland Medical Group", city: "Royal Oak", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8376,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI255",GROUP_DESC:"MI255 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8376,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI255",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI255",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8376,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI255", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9376 ,protection_group_id: -8376, protection_element_id:-8376], primaryKey: false);
      insert('organizations', [ id: 108362, nci_institute_code: "MI256", name: "Hutzel Women's Health Specialists", city: "Detriot", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8377,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI256",GROUP_DESC:"MI256 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8377,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI256",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI256",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8377,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI256", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9377 ,protection_group_id: -8377, protection_element_id:-8377], primaryKey: false);
      insert('organizations', [ id: 108363, nci_institute_code: "MI258", name: "Great Lakes Cancer Management Specialists", city: "Warren", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8378,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI258",GROUP_DESC:"MI258 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8378,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI258",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI258",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8378,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI258", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9378 ,protection_group_id: -8378, protection_element_id:-8378], primaryKey: false);
      insert('organizations', [ id: 108364, nci_institute_code: "MI259", name: "Midwest Thoracic Surgeons", city: "Royal Oak", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8379,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI259",GROUP_DESC:"MI259 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8379,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI259",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI259",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8379,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI259", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9379 ,protection_group_id: -8379, protection_element_id:-8379], primaryKey: false);
      insert('organizations', [ id: 108365, nci_institute_code: "MI260", name: "Michigan Heart and Vascular Institute", city: "Ypsilanti", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8380,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI260",GROUP_DESC:"MI260 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8380,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI260",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI260",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8380,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI260", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9380 ,protection_group_id: -8380, protection_element_id:-8380], primaryKey: false);
      insert('organizations', [ id: 108366, nci_institute_code: "MI261", name: "Thomas M Flake Jr MD PC", city: "Southfield", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8381,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI261",GROUP_DESC:"MI261 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8381,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI261",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI261",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8381,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI261", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9381 ,protection_group_id: -8381, protection_element_id:-8381], primaryKey: false);
      insert('organizations', [ id: 108367, nci_institute_code: "MI262", name: "Singh and Arora Hematology Oncology PC", city: "Flint", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8382,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI262",GROUP_DESC:"MI262 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8382,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI262",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI262",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8382,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI262", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9382 ,protection_group_id: -8382, protection_element_id:-8382], primaryKey: false);
      insert('organizations', [ id: 108368, nci_institute_code: "MI263", name: "Great Lakes Cancer Institute-Lapeer Campus", city: "Lapeer", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8383,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI263",GROUP_DESC:"MI263 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8383,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI263",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI263",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8383,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI263", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9383 ,protection_group_id: -8383, protection_element_id:-8383], primaryKey: false);
      insert('organizations', [ id: 108369, nci_institute_code: "MI264", name: "Oakman Medical Group PC", city: "Dearborn", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8384,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI264",GROUP_DESC:"MI264 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8384,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI264",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI264",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8384,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI264", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9384 ,protection_group_id: -8384, protection_element_id:-8384], primaryKey: false);
      insert('organizations', [ id: 108370, nci_institute_code: "MI265", name: "Hope Cancer Center", city: "Pontiac", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8385,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI265",GROUP_DESC:"MI265 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8385,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI265",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI265",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8385,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI265", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9385 ,protection_group_id: -8385, protection_element_id:-8385], primaryKey: false);
      insert('organizations', [ id: 108371, nci_institute_code: "MI266", name: "Cancer and Hematology Centers of Western Michigan-Holland", city: "Holland", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8386,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI266",GROUP_DESC:"MI266 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8386,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI266",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI266",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8386,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI266", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9386 ,protection_group_id: -8386, protection_element_id:-8386], primaryKey: false);
      insert('organizations', [ id: 108372, nci_institute_code: "MI267", name: "Alliance Health-Tejada Center Radiation Oncology", city: "Jackson", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8387,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI267",GROUP_DESC:"MI267 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8387,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI267",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI267",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8387,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI267", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9387 ,protection_group_id: -8387, protection_element_id:-8387], primaryKey: false);
    }

    void m15() {
        // all15 (25 terms)
      insert('organizations', [ id: 108373, nci_institute_code: "MI268", name: "Cancer and Hematology Centers of Western Michigan-Muskegon", city: "Muskegon", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8388,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI268",GROUP_DESC:"MI268 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8388,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI268",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI268",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8388,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI268", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9388 ,protection_group_id: -8388, protection_element_id:-8388], primaryKey: false);
      insert('organizations', [ id: 108374, nci_institute_code: "MI269", name: "Mercy Health Partners-Mercy Campus", city: "Muskegon", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8389,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI269",GROUP_DESC:"MI269 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8389,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI269",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI269",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8389,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI269", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9389 ,protection_group_id: -8389, protection_element_id:-8389], primaryKey: false);
      insert('organizations', [ id: 108375, nci_institute_code: "MI270", name: "Sarcoma Alliance for Research Through Collaboration", city: "Ann Arbor", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8390,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI270",GROUP_DESC:"MI270 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8390,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI270",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI270",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8390,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI270", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9390 ,protection_group_id: -8390, protection_element_id:-8390], primaryKey: false);
      insert('organizations', [ id: 108376, nci_institute_code: "MI271", name: "Great Lakes Breast Care", city: "Lansing", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8391,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI271",GROUP_DESC:"MI271 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8391,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI271",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI271",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8391,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI271", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9391 ,protection_group_id: -8391, protection_element_id:-8391], primaryKey: false);
      insert('organizations', [ id: 108377, nci_institute_code: "MI272", name: "Johnson Family Center for Cancer Care", city: "Muskegon", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8392,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI272",GROUP_DESC:"MI272 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8392,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI272",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI272",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8392,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI272", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9392 ,protection_group_id: -8392, protection_element_id:-8392], primaryKey: false);
      insert('organizations', [ id: 108378, nci_institute_code: "MI273", name: "Michigan Institute of Urology-Beaumont Medical Building", city: "West Bloomfield", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8393,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI273",GROUP_DESC:"MI273 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8393,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI273",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI273",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8393,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI273", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9393 ,protection_group_id: -8393, protection_element_id:-8393], primaryKey: false);
      insert('organizations', [ id: 108379, nci_institute_code: "MI274", name: "Spectrum Health-Crossroads Radiation Therapy Center", city: "Reed City", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8394,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI274",GROUP_DESC:"MI274 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8394,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI274",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI274",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8394,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI274", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9394 ,protection_group_id: -8394, protection_element_id:-8394], primaryKey: false);
      insert('organizations', [ id: 108380, nci_institute_code: "MI275", name: "Northern Institute of Urology PC", city: "Traverse City", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8395,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI275",GROUP_DESC:"MI275 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8395,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI275",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI275",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8395,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI275", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9395 ,protection_group_id: -8395, protection_element_id:-8395], primaryKey: false);
      insert('organizations', [ id: 108381, nci_institute_code: "MI276", name: "Helen DeVos Children's Hospital at Spectrum Health", city: "Grand Rapids", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8396,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI276",GROUP_DESC:"MI276 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8396,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI276",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI276",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8396,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI276", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9396 ,protection_group_id: -8396, protection_element_id:-8396], primaryKey: false);
      insert('organizations', [ id: 108382, nci_institute_code: "MI277", name: "Digestive Health Associates PLC-Farmington Hills", city: "Farmington Hills", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8397,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI277",GROUP_DESC:"MI277 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8397,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI277",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI277",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8397,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI277", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9397 ,protection_group_id: -8397, protection_element_id:-8397], primaryKey: false);
      insert('organizations', [ id: 108383, nci_institute_code: "MI278", name: "Female Pelvic Medicine and Urogynecology Institute of Michigan", city: "Grand Rapids", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8398,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI278",GROUP_DESC:"MI278 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8398,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI278",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI278",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8398,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI278", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9398 ,protection_group_id: -8398, protection_element_id:-8398], primaryKey: false);
      insert('organizations', [ id: 108384, nci_institute_code: "MI279", name: "Cancer Center at Metro Health Village", city: "Wyoming", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8399,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI279",GROUP_DESC:"MI279 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8399,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI279",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI279",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8399,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI279", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9399 ,protection_group_id: -8399, protection_element_id:-8399], primaryKey: false);
      insert('organizations', [ id: 108385, nci_institute_code: "MI280", name: "Beaumont Comprehensive Urology Center", city: "Royal Oak", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8400,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI280",GROUP_DESC:"MI280 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8400,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI280",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI280",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8400,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI280", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9400 ,protection_group_id: -8400, protection_element_id:-8400], primaryKey: false);
      insert('organizations', [ id: 108386, nci_institute_code: "MI281", name: "Michigan Breast Specialists", city: "Grosse Pointe Woods", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8401,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI281",GROUP_DESC:"MI281 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8401,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI281",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI281",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8401,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI281", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9401 ,protection_group_id: -8401, protection_element_id:-8401], primaryKey: false);
      insert('organizations', [ id: 108387, nci_institute_code: "MI282", name: "Chippewa Medical Associates", city: "Sault Saint Marie", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8402,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI282",GROUP_DESC:"MI282 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8402,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI282",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI282",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8402,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI282", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9402 ,protection_group_id: -8402, protection_element_id:-8402], primaryKey: false);
      insert('organizations', [ id: 108388, nci_institute_code: "MI283", name: "Bronson Advanced Radiology", city: "Kalamazoo", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8403,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI283",GROUP_DESC:"MI283 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8403,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI283",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI283",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8403,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI283", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9403 ,protection_group_id: -8403, protection_element_id:-8403], primaryKey: false);
      insert('organizations', [ id: 108389, nci_institute_code: "MI284", name: "Hurley West Flint Campus", city: "Flint", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8404,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI284",GROUP_DESC:"MI284 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8404,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI284",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI284",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8404,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI284", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9404 ,protection_group_id: -8404, protection_element_id:-8404], primaryKey: false);
      insert('organizations', [ id: 108390, nci_institute_code: "MI285", name: "Great Lakes Cancer Institute", city: "Clarkston", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8405,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI285",GROUP_DESC:"MI285 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8405,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI285",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI285",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8405,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI285", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9405 ,protection_group_id: -8405, protection_element_id:-8405], primaryKey: false);
      insert('organizations', [ id: 108391, nci_institute_code: "MI286", name: "Prem Khilanani MD PC", city: "Troy", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8406,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI286",GROUP_DESC:"MI286 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8406,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI286",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI286",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8406,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI286", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9406 ,protection_group_id: -8406, protection_element_id:-8406], primaryKey: false);
      insert('organizations', [ id: 108392, nci_institute_code: "MI287", name: "Surgical Centers of Michigan-Troy", city: "Troy", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8407,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI287",GROUP_DESC:"MI287 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8407,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI287",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI287",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8407,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI287", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9407 ,protection_group_id: -8407, protection_element_id:-8407], primaryKey: false);
      insert('organizations', [ id: 108393, nci_institute_code: "MI288", name: "Cancer Care Associates-Rochester Hills", city: "Rochester Hills", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8408,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI288",GROUP_DESC:"MI288 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8408,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI288",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI288",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8408,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI288", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9408 ,protection_group_id: -8408, protection_element_id:-8408], primaryKey: false);
      insert('organizations', [ id: 108394, nci_institute_code: "MI289", name: "Hematology Oncology Consultants PC-Troy", city: "Troy", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8409,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI289",GROUP_DESC:"MI289 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8409,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI289",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI289",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8409,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI289", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9409 ,protection_group_id: -8409, protection_element_id:-8409], primaryKey: false);
      insert('organizations', [ id: 108395, nci_institute_code: "MI290", name: "Michigan Hematology Oncology PC-Clarkston", city: "Clarkston", state: "MI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8410,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI290",GROUP_DESC:"MI290 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8410,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MI290",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MI290",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8410,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MI290", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9410 ,protection_group_id: -8410, protection_element_id:-8410], primaryKey: false);
      insert('organizations', [ id: 108396, nci_institute_code: "MN111", name: "University of Minnesota-Stem Cell Institute", city: "Minneapolis", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8411,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN111",GROUP_DESC:"MN111 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8411,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN111",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN111",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8411,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN111", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9411 ,protection_group_id: -8411, protection_element_id:-8411], primaryKey: false);
      insert('organizations', [ id: 108397, nci_institute_code: "MN112", name: "Saint Mary's Hospital", city: "Rochester", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8412,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN112",GROUP_DESC:"MN112 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8412,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN112",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN112",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8412,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN112", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9412 ,protection_group_id: -8412, protection_element_id:-8412], primaryKey: false);
    }

    void m16() {
        // all16 (25 terms)
      insert('organizations', [ id: 108398, nci_institute_code: "MN113", name: "Fairview Maple Grove Medical Center", city: "Maple Grove", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8413,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN113",GROUP_DESC:"MN113 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8413,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN113",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN113",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8413,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN113", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9413 ,protection_group_id: -8413, protection_element_id:-8413], primaryKey: false);
      insert('organizations', [ id: 108399, nci_institute_code: "MN114", name: "Riverside Hematology Oncology and Infusion Center", city: "Minneapolis", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8414,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN114",GROUP_DESC:"MN114 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8414,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN114",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN114",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8414,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN114", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9414 ,protection_group_id: -8414, protection_element_id:-8414], primaryKey: false);
      insert('organizations', [ id: 108400, nci_institute_code: "MN115", name: "Pediatric Surgical Associates Limited", city: "MInneapolis", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8415,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN115",GROUP_DESC:"MN115 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8415,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN115",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN115",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8415,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN115", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9415 ,protection_group_id: -8415, protection_element_id:-8415], primaryKey: false);
      insert('organizations', [ id: 108401, nci_institute_code: "MN116", name: "Minneapolis Medical Research Foundation", city: "Minneapolis", state: "MN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8416,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN116",GROUP_DESC:"MN116 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8416,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MN116",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MN116",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8416,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MN116", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9416 ,protection_group_id: -8416, protection_element_id:-8416], primaryKey: false);
      insert('organizations', [ id: 108402, nci_institute_code: "MO166", name: "Saint Louis Cancer Care", city: "Bridgeton", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8417,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO166",GROUP_DESC:"MO166 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8417,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO166",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO166",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8417,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO166", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9417 ,protection_group_id: -8417, protection_element_id:-8417], primaryKey: false);
      insert('organizations', [ id: 108403, nci_institute_code: "MO167", name: "Columbia Surgical Associates", city: "Columbia", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8418,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO167",GROUP_DESC:"MO167 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8418,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO167",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO167",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8418,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO167", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9418 ,protection_group_id: -8418, protection_element_id:-8418], primaryKey: false);
      insert('organizations', [ id: 108404, nci_institute_code: "MO168", name: "Cape Medical Oncology", city: "Cape Girardeau", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8419,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO168",GROUP_DESC:"MO168 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8419,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO168",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO168",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8419,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO168", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9419 ,protection_group_id: -8419, protection_element_id:-8419], primaryKey: false);
      insert('organizations', [ id: 108405, nci_institute_code: "MO169", name: "Oncology and Hematology Associates PC", city: "Joplin", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8420,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO169",GROUP_DESC:"MO169 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8420,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO169",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO169",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8420,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO169", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9420 ,protection_group_id: -8420, protection_element_id:-8420], primaryKey: false);
      insert('organizations', [ id: 108406, nci_institute_code: "MO170", name: "Liberty Hospital", city: "Liberty", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8421,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO170",GROUP_DESC:"MO170 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8421,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO170",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO170",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8421,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO170", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9421 ,protection_group_id: -8421, protection_element_id:-8421], primaryKey: false);
      insert('organizations', [ id: 108407, nci_institute_code: "MO171", name: "Crighton Olive Dunn Surgical Group", city: "Springfield", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8422,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO171",GROUP_DESC:"MO171 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8422,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO171",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO171",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8422,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO171", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9422 ,protection_group_id: -8422, protection_element_id:-8422], primaryKey: false);
      insert('organizations', [ id: 108408, nci_institute_code: "MO172", name: "The Women's Oncology Center", city: "Saint Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8423,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO172",GROUP_DESC:"MO172 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8423,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO172",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO172",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8423,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO172", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9423 ,protection_group_id: -8423, protection_element_id:-8423], primaryKey: false);
      insert('organizations', [ id: 108409, nci_institute_code: "MO173", name: "Saint Luke's East - Lee's Summit", city: "Lee's Summit", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8424,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO173",GROUP_DESC:"MO173 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8424,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO173",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO173",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8424,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO173", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9424 ,protection_group_id: -8424, protection_element_id:-8424], primaryKey: false);
      insert('organizations', [ id: 108410, nci_institute_code: "MO174", name: "Saint Louis Cancer and Breast Institute-Des Peres", city: "Des Peres", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8425,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO174",GROUP_DESC:"MO174 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8425,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO174",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO174",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8425,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO174", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9425 ,protection_group_id: -8425, protection_element_id:-8425], primaryKey: false);
      insert('organizations', [ id: 108411, nci_institute_code: "MO175", name: "Specialists in Oncology Hematology PC", city: "Chesterfield", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8426,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO175",GROUP_DESC:"MO175 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8426,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO175",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO175",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8426,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO175", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9426 ,protection_group_id: -8426, protection_element_id:-8426], primaryKey: false);
      insert('organizations', [ id: 108412, nci_institute_code: "MO176", name: "Missouri Cancer Care PC-Lake Saint Louis", city: "Lake Saint Louis", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8427,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO176",GROUP_DESC:"MO176 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8427,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO176",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO176",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8427,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO176", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9427 ,protection_group_id: -8427, protection_element_id:-8427], primaryKey: false);
      insert('organizations', [ id: 108413, nci_institute_code: "MO177", name: "Urology at National LLC", city: "Springfield", state: "MO", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8428,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO177",GROUP_DESC:"MO177 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8428,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MO177",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MO177",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8428,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MO177", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9428 ,protection_group_id: -8428, protection_element_id:-8428], primaryKey: false);
      insert('organizations', [ id: 108414, nci_institute_code: "MS051", name: "Jackson Oncology Associates PLLC-Jackson", city: "Jackson", state: "MS", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8429,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS051",GROUP_DESC:"MS051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8429,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MS051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MS051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8429,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MS051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9429 ,protection_group_id: -8429, protection_element_id:-8429], primaryKey: false);
      insert('organizations', [ id: 108415, nci_institute_code: "MT039", name: "Billings Clinic Cancer Center", city: "Billings", state: "MT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8430,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT039",GROUP_DESC:"MT039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8430,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.MT039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.MT039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8430,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.MT039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9430 ,protection_group_id: -8430, protection_element_id:-8430], primaryKey: false);
      insert('organizations', [ id: 108416, nci_institute_code: "NABMTG", name: "North American Bone Marrow Treatment Group", city: "Arlington Heights", state: "IL", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8431,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NABMTG",GROUP_DESC:"NABMTG group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8431,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NABMTG",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NABMTG",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8431,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NABMTG", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9431 ,protection_group_id: -8431, protection_element_id:-8431], primaryKey: false);
      insert('organizations', [ id: 108417, nci_institute_code: "NC198", name: "Sarah W Stedman Nutrition and Metabolism Center", city: "Durham", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8432,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC198",GROUP_DESC:"NC198 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8432,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC198",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC198",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8432,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC198", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9432 ,protection_group_id: -8432, protection_element_id:-8432], primaryKey: false);
      insert('organizations', [ id: 108418, nci_institute_code: "NC200", name: "Piedmont Oncology Specialists", city: "Huntersville", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8433,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC200",GROUP_DESC:"NC200 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8433,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC200",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC200",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8433,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC200", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9433 ,protection_group_id: -8433, protection_element_id:-8433], primaryKey: false);
      insert('organizations', [ id: 108419, nci_institute_code: "NC201", name: "Charlotte Medical Clinic", city: "Charlotte", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8434,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC201",GROUP_DESC:"NC201 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8434,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC201",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC201",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8434,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC201", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9434 ,protection_group_id: -8434, protection_element_id:-8434], primaryKey: false);
      insert('organizations', [ id: 108420, nci_institute_code: "NC202", name: "Rutherford Internal Medicine Associates PA", city: "Forest City", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8435,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC202",GROUP_DESC:"NC202 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8435,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC202",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC202",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8435,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC202", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9435 ,protection_group_id: -8435, protection_element_id:-8435], primaryKey: false);
      insert('organizations', [ id: 108421, nci_institute_code: "NC203", name: "Columbus Regional Healthcare System", city: "Whiteville", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8436,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC203",GROUP_DESC:"NC203 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8436,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC203",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC203",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8436,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC203", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9436 ,protection_group_id: -8436, protection_element_id:-8436], primaryKey: false);
      insert('organizations', [ id: 108422, nci_institute_code: "NC205", name: "Carolinas Cancer Care-Huntersville", city: "Huntersville", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8437,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC205",GROUP_DESC:"NC205 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8437,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC205",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC205",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8437,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC205", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9437 ,protection_group_id: -8437, protection_element_id:-8437], primaryKey: false);
    }

    void m17() {
        // all17 (25 terms)
      insert('organizations', [ id: 108423, nci_institute_code: "NC206", name: "Randolph Cancer Center", city: "Asheboro", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8438,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC206",GROUP_DESC:"NC206 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8438,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC206",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC206",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8438,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC206", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9438 ,protection_group_id: -8438, protection_element_id:-8438], primaryKey: false);
      insert('organizations', [ id: 108424, nci_institute_code: "NC207", name: "Carolina Breast and Oncologic Surgery", city: "Greenville", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8439,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC207",GROUP_DESC:"NC207 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8439,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC207",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC207",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8439,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC207", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9439 ,protection_group_id: -8439, protection_element_id:-8439], primaryKey: false);
      insert('organizations', [ id: 108425, nci_institute_code: "NC208", name: "Lake Norman Radiation Oncology Center", city: "Mooresville", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8440,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC208",GROUP_DESC:"NC208 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8440,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC208",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC208",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8440,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC208", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9440 ,protection_group_id: -8440, protection_element_id:-8440], primaryKey: false);
      insert('organizations', [ id: 108426, nci_institute_code: "NC209", name: "Pediatric Surgical Associates PA", city: "Charlotte", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8441,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC209",GROUP_DESC:"NC209 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8441,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC209",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC209",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8441,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC209", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9441 ,protection_group_id: -8441, protection_element_id:-8441], primaryKey: false);
      insert('organizations', [ id: 108427, nci_institute_code: "NC210", name: "Triad Cardiac and Thoracic Surgeons-Greensboro", city: "Greensboro", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8442,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC210",GROUP_DESC:"NC210 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8442,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC210",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC210",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8442,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC210", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9442 ,protection_group_id: -8442, protection_element_id:-8442], primaryKey: false);
      insert('organizations', [ id: 108428, nci_institute_code: "NC211", name: "Rex Cancer Center of Wakefield", city: "Raleigh", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8443,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC211",GROUP_DESC:"NC211 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8443,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC211",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC211",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8443,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC211", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9443 ,protection_group_id: -8443, protection_element_id:-8443], primaryKey: false);
      insert('organizations', [ id: 108429, nci_institute_code: "NC212", name: "Asheville Radiology Associates PA", city: "Asheville", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8444,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC212",GROUP_DESC:"NC212 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8444,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC212",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC212",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8444,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC212", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9444 ,protection_group_id: -8444, protection_element_id:-8444], primaryKey: false);
      insert('organizations', [ id: 108430, nci_institute_code: "NC213", name: "Duke Raleigh Hospital", city: "Raleigh", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8445,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC213",GROUP_DESC:"NC213 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8445,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC213",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC213",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8445,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC213", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9445 ,protection_group_id: -8445, protection_element_id:-8445], primaryKey: false);
      insert('organizations', [ id: 108431, nci_institute_code: "NC214", name: "New Bern Cancer Care", city: "New Bern", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8446,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC214",GROUP_DESC:"NC214 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8446,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC214",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC214",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8446,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC214", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9446 ,protection_group_id: -8446, protection_element_id:-8446], primaryKey: false);
      insert('organizations', [ id: 108432, nci_institute_code: "NC215", name: "Gibson Cancer Center", city: "Lumberton", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8447,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC215",GROUP_DESC:"NC215 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8447,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC215",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC215",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8447,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC215", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9447 ,protection_group_id: -8447, protection_element_id:-8447], primaryKey: false);
      insert('organizations', [ id: 108433, nci_institute_code: "NC216", name: "South Atlantic Radiation Oncology", city: "Supply", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8448,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC216",GROUP_DESC:"NC216 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8448,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC216",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC216",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8448,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC216", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9448 ,protection_group_id: -8448, protection_element_id:-8448], primaryKey: false);
      insert('organizations', [ id: 108434, nci_institute_code: "NC217", name: "Owen Drive Surgical Clinic of Fayetteville", city: "Fayetteville", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8449,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC217",GROUP_DESC:"NC217 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8449,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NC217",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NC217",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8449,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NC217", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9449 ,protection_group_id: -8449, protection_element_id:-8449], primaryKey: false);
      insert('organizations', [ id: 108435, nci_institute_code: "NCIMIP", name: "National Cancer Institute Molecular Imaging Program", city: "Bethesda", state: "MD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8450,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIMIP",GROUP_DESC:"NCIMIP group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8450,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NCIMIP",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NCIMIP",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8450,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NCIMIP", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9450 ,protection_group_id: -8450, protection_element_id:-8450], primaryKey: false);
      insert('organizations', [ id: 108436, nci_institute_code: "NH030", name: "Northern New England Surgical Associates", city: "Brighton", state: "MA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8451,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH030",GROUP_DESC:"NH030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8451,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NH030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NH030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8451,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9451 ,protection_group_id: -8451, protection_element_id:-8451], primaryKey: false);
      insert('organizations', [ id: 108437, nci_institute_code: "NH043", name: "The Dana-Farber Cancer Institute at Londonberry", city: "Londonderry", state: "NH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8452,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH043",GROUP_DESC:"NH043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8452,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NH043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NH043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8452,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NH043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9452 ,protection_group_id: -8452, protection_element_id:-8452], primaryKey: false);
      insert('organizations', [ id: 108438, nci_institute_code: "NJ226", name: "Premier Oncology LLC", city: "Galloway", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8453,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ226",GROUP_DESC:"NJ226 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8453,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ226",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ226",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8453,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ226", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9453 ,protection_group_id: -8453, protection_element_id:-8453], primaryKey: false);
      insert('organizations', [ id: 108439, nci_institute_code: "NJ227", name: "Cancer and Blood Disorders Care Center", city: "Galloway", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8454,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ227",GROUP_DESC:"NJ227 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8454,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ227",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ227",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8454,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ227", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9454 ,protection_group_id: -8454, protection_element_id:-8454], primaryKey: false);
      insert('organizations', [ id: 108440, nci_institute_code: "NJ228", name: "Monmouth Middlesex Hematology Oncology PC", city: "Freehold", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8455,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ228",GROUP_DESC:"NJ228 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8455,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ228",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ228",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8455,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ228", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9455 ,protection_group_id: -8455, protection_element_id:-8455], primaryKey: false);
      insert('organizations', [ id: 108441, nci_institute_code: "NJ229", name: "Hematology Oncology Associates of New Jersey PA", city: "Paramus", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8456,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ229",GROUP_DESC:"NJ229 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8456,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ229",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ229",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8456,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ229", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9456 ,protection_group_id: -8456, protection_element_id:-8456], primaryKey: false);
      insert('organizations', [ id: 108442, nci_institute_code: "NJ230", name: "Center for Cancer and Blood Disorders", city: "Edison", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8457,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ230",GROUP_DESC:"NJ230 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8457,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ230",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ230",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8457,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ230", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9457 ,protection_group_id: -8457, protection_element_id:-8457], primaryKey: false);
      insert('organizations', [ id: 108443, nci_institute_code: "NJ231", name: "Saint Joseph's Regional Medical Center-Clifton", city: "Clifton", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8458,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ231",GROUP_DESC:"NJ231 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8458,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ231",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ231",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8458,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ231", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9458 ,protection_group_id: -8458, protection_element_id:-8458], primaryKey: false);
      insert('organizations', [ id: 108444, nci_institute_code: "NJ232", name: "John Wiley and Sons Incorporated", city: "Hoboken", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8459,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ232",GROUP_DESC:"NJ232 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8459,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ232",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ232",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8459,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ232", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9459 ,protection_group_id: -8459, protection_element_id:-8459], primaryKey: false);
      insert('organizations', [ id: 108445, nci_institute_code: "NJ233", name: "Atlantic Health", city: "Morristown", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8460,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ233",GROUP_DESC:"NJ233 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8460,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ233",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ233",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8460,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ233", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9460 ,protection_group_id: -8460, protection_element_id:-8460], primaryKey: false);
      insert('organizations', [ id: 108446, nci_institute_code: "NJ234", name: "Oncology and Hematology Specialists", city: "Mountain Lakes", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8461,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ234",GROUP_DESC:"NJ234 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8461,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ234",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ234",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8461,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ234", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9461 ,protection_group_id: -8461, protection_element_id:-8461], primaryKey: false);
      insert('organizations', [ id: 108447, nci_institute_code: "NJ235", name: "Cancer Specialists of New Jersey", city: "Freehold", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8462,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ235",GROUP_DESC:"NJ235 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8462,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ235",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ235",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8462,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ235", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9462 ,protection_group_id: -8462, protection_element_id:-8462], primaryKey: false);
    }

    void m18() {
        // all18 (25 terms)
      insert('organizations', [ id: 108448, nci_institute_code: "NJ236", name: "Minimally Invasive Surgeons of South Jersey", city: "Galloway", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8463,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ236",GROUP_DESC:"NJ236 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8463,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ236",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ236",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8463,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ236", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9463 ,protection_group_id: -8463, protection_element_id:-8463], primaryKey: false);
      insert('organizations', [ id: 108449, nci_institute_code: "NJ237", name: "AtlantiCare Surgery Center", city: "Egg Harbor Township", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8464,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ237",GROUP_DESC:"NJ237 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8464,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ237",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ237",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8464,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ237", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9464 ,protection_group_id: -8464, protection_element_id:-8464], primaryKey: false);
      insert('organizations', [ id: 108450, nci_institute_code: "NJ238", name: "Medical Oncology and Hematology", city: "Somerset", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8465,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ238",GROUP_DESC:"NJ238 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8465,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ238",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ238",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8465,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ238", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9465 ,protection_group_id: -8465, protection_element_id:-8465], primaryKey: false);
      insert('organizations', [ id: 108451, nci_institute_code: "NJ239", name: "David P May MD FACS", city: "Egg Harbor Township", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8466,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ239",GROUP_DESC:"NJ239 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8466,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ239",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ239",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8466,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ239", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9466 ,protection_group_id: -8466, protection_element_id:-8466], primaryKey: false);
      insert('organizations', [ id: 108452, nci_institute_code: "NJ240", name: "AtlantiCare-RNS Regional Cancer Center", city: "Galloway", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8467,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ240",GROUP_DESC:"NJ240 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8467,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ240",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ240",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8467,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ240", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9467 ,protection_group_id: -8467, protection_element_id:-8467], primaryKey: false);
      insert('organizations', [ id: 108453, nci_institute_code: "NJ241", name: "Associated Colon and Rectal Surgeons PA", city: "Edison", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8468,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ241",GROUP_DESC:"NJ241 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8468,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ241",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ241",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8468,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ241", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9468 ,protection_group_id: -8468, protection_element_id:-8468], primaryKey: false);
      insert('organizations', [ id: 108454, nci_institute_code: "NJ242", name: "BreasT Specialty Care Group", city: "Voorhees", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8469,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ242",GROUP_DESC:"NJ242 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8469,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ242",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ242",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8469,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ242", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9469 ,protection_group_id: -8469, protection_element_id:-8469], primaryKey: false);
      insert('organizations', [ id: 108455, nci_institute_code: "NJ243", name: "Northern Valley Medical Associates", city: "Westwood", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8470,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ243",GROUP_DESC:"NJ243 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8470,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ243",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ243",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8470,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ243", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9470 ,protection_group_id: -8470, protection_element_id:-8470], primaryKey: false);
      insert('organizations', [ id: 108456, nci_institute_code: "NJ244", name: "Pilipshen Colon and Rectal Surgical Services PC", city: "Mooretown", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8471,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ244",GROUP_DESC:"NJ244 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8471,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ244",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ244",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8471,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ244", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9471 ,protection_group_id: -8471, protection_element_id:-8471], primaryKey: false);
      insert('organizations', [ id: 108457, nci_institute_code: "NJ245", name: "Surgical Associates of Sussex County", city: "Newton", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8472,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ245",GROUP_DESC:"NJ245 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8472,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ245",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ245",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8472,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ245", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9472 ,protection_group_id: -8472, protection_element_id:-8472], primaryKey: false);
      insert('organizations', [ id: 108458, nci_institute_code: "NJ246", name: "Newton Memorial Hospital", city: "Newton", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8473,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ246",GROUP_DESC:"NJ246 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8473,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ246",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ246",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8473,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ246", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9473 ,protection_group_id: -8473, protection_element_id:-8473], primaryKey: false);
      insert('organizations', [ id: 108459, nci_institute_code: "NJ247", name: "Gregory Shypula MD PA", city: "Avenel", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8474,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ247",GROUP_DESC:"NJ247 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8474,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ247",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ247",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8474,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ247", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9474 ,protection_group_id: -8474, protection_element_id:-8474], primaryKey: false);
      insert('organizations', [ id: 108460, nci_institute_code: "NJ248", name: "North Jersey Brain and Spine Center", city: "Oradell", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8475,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ248",GROUP_DESC:"NJ248 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8475,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ248",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ248",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8475,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ248", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9475 ,protection_group_id: -8475, protection_element_id:-8475], primaryKey: false);
      insert('organizations', [ id: 108461, nci_institute_code: "NJ249", name: "Loving Care Oncology", city: "Maplewood", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8476,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ249",GROUP_DESC:"NJ249 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8476,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ249",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ249",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8476,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ249", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9476 ,protection_group_id: -8476, protection_element_id:-8476], primaryKey: false);
      insert('organizations', [ id: 108462, nci_institute_code: "NJ250", name: "Jersey Urology Group PA", city: "Somers Point", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8477,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ250",GROUP_DESC:"NJ250 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8477,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ250",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ250",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8477,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ250", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9477 ,protection_group_id: -8477, protection_element_id:-8477], primaryKey: false);
      insert('organizations', [ id: 108463, nci_institute_code: "NJ251", name: "Pediatric Surgery Group LLC", city: "Maplewood", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8478,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ251",GROUP_DESC:"NJ251 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8478,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ251",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ251",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8478,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ251", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9478 ,protection_group_id: -8478, protection_element_id:-8478], primaryKey: false);
      insert('organizations', [ id: 108464, nci_institute_code: "NJ252", name: "The Thoracic Group", city: "Somerset", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8479,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ252",GROUP_DESC:"NJ252 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8479,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ252",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ252",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8479,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ252", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9479 ,protection_group_id: -8479, protection_element_id:-8479], primaryKey: false);
      insert('organizations', [ id: 108465, nci_institute_code: "NJ253", name: "HealthSouth Rehabilitation Hospital: Tinton Falls", city: "Tinton Falls", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8480,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ253",GROUP_DESC:"NJ253 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8480,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ253",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ253",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8480,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ253", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9480 ,protection_group_id: -8480, protection_element_id:-8480], primaryKey: false);
      insert('organizations', [ id: 108466, nci_institute_code: "NJ254", name: "Univeristy Medical Center at Princeton", city: "Eas Windsor", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8481,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ254",GROUP_DESC:"NJ254 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8481,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ254",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ254",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8481,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ254", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9481 ,protection_group_id: -8481, protection_element_id:-8481], primaryKey: false);
      insert('organizations', [ id: 108467, nci_institute_code: "NJ255", name: "Specialists in Hematology Oncology", city: "Howell", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8482,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ255",GROUP_DESC:"NJ255 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8482,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ255",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ255",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8482,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ255", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9482 ,protection_group_id: -8482, protection_element_id:-8482], primaryKey: false);
      insert('organizations', [ id: 108468, nci_institute_code: "NJ256", name: "Neurosurgical Associates of Central Jersey PA", city: "Bound Brook", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8483,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ256",GROUP_DESC:"NJ256 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8483,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ256",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ256",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8483,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ256", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9483 ,protection_group_id: -8483, protection_element_id:-8483], primaryKey: false);
      insert('organizations', [ id: 108469, nci_institute_code: "NJ257", name: "Raritan Valley Surgical Associates", city: "Hillsborough", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8484,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ257",GROUP_DESC:"NJ257 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8484,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NJ257",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NJ257",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8484,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NJ257", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9484 ,protection_group_id: -8484, protection_element_id:-8484], primaryKey: false);
      insert('organizations', [ id: 108470, nci_institute_code: "NM039", name: "University of New Mexico Cancer Center-South", city: "Las Cruces", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8485,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM039",GROUP_DESC:"NM039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8485,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8485,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9485 ,protection_group_id: -8485, protection_element_id:-8485], primaryKey: false);
      insert('organizations', [ id: 108471, nci_institute_code: "NM040", name: "Vincent Ortolano MD PC", city: "Albuqurque", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8486,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM040",GROUP_DESC:"NM040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8486,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8486,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9486 ,protection_group_id: -8486, protection_element_id:-8486], primaryKey: false);
      insert('organizations', [ id: 108472, nci_institute_code: "NM041", name: "University of New Mexico at Lovelace Medical Center", city: "Albuquerque", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8487,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM041",GROUP_DESC:"NM041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8487,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8487,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9487 ,protection_group_id: -8487, protection_element_id:-8487], primaryKey: false);
    }

    void m19() {
        // all19 (25 terms)
      insert('organizations', [ id: 108473, nci_institute_code: "NM042", name: "University of New Mexico Cancer Center", city: "Albuquerque", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8488,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM042",GROUP_DESC:"NM042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8488,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8488,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9488 ,protection_group_id: -8488, protection_element_id:-8488], primaryKey: false);
      insert('organizations', [ id: 108474, nci_institute_code: "NM043", name: "Lovelace Women's Hospital", city: "Albuquerque", state: "NM", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8489,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM043",GROUP_DESC:"NM043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8489,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NM043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NM043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8489,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NM043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9489 ,protection_group_id: -8489, protection_element_id:-8489], primaryKey: false);
      insert('organizations', [ id: 108475, nci_institute_code: "NV062", name: "Compassionate Cancer Care", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8490,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV062",GROUP_DESC:"NV062 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8490,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV062",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV062",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8490,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV062", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9490 ,protection_group_id: -8490, protection_element_id:-8490], primaryKey: false);
      insert('organizations', [ id: 108476, nci_institute_code: "NV063", name: "Urology Specialists of Nevada", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8491,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV063",GROUP_DESC:"NV063 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8491,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV063",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV063",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8491,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV063", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9491 ,protection_group_id: -8491, protection_element_id:-8491], primaryKey: false);
      insert('organizations', [ id: 108477, nci_institute_code: "NV064", name: "21st Century Oncology", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8492,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV064",GROUP_DESC:"NV064 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8492,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV064",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV064",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8492,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV064", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9492 ,protection_group_id: -8492, protection_element_id:-8492], primaryKey: false);
      insert('organizations', [ id: 108478, nci_institute_code: "NV065", name: "21st Century Oncology-Henderson", city: "Henderson", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8493,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV065",GROUP_DESC:"NV065 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8493,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV065",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV065",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8493,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV065", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9493 ,protection_group_id: -8493, protection_element_id:-8493], primaryKey: false);
      insert('organizations', [ id: 108479, nci_institute_code: "NV066", name: "Las Vegas Cancer Center-Henderson", city: "Henderson", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8494,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV066",GROUP_DESC:"NV066 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8494,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV066",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV066",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8494,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV066", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9494 ,protection_group_id: -8494, protection_element_id:-8494], primaryKey: false);
      insert('organizations', [ id: 108480, nci_institute_code: "NV067", name: "Nevada Surgery and Cancer Care", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8495,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV067",GROUP_DESC:"NV067 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8495,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV067",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV067",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8495,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV067", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9495 ,protection_group_id: -8495, protection_element_id:-8495], primaryKey: false);
      insert('organizations', [ id: 108481, nci_institute_code: "NV068", name: "General and Vascular Associates", city: "Reno", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8496,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV068",GROUP_DESC:"NV068 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8496,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV068",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV068",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8496,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV068", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9496 ,protection_group_id: -8496, protection_element_id:-8496], primaryKey: false);
      insert('organizations', [ id: 108482, nci_institute_code: "NV069", name: "Comprehensive Cancer Centers of Nevada-Southeast Henderson", city: "Henderson", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8497,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV069",GROUP_DESC:"NV069 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8497,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV069",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV069",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8497,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV069", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9497 ,protection_group_id: -8497, protection_element_id:-8497], primaryKey: false);
      insert('organizations', [ id: 108483, nci_institute_code: "NV070", name: "Capitol Oncology", city: "Carson City", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8498,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV070",GROUP_DESC:"NV070 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8498,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV070",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV070",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8498,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV070", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9498 ,protection_group_id: -8498, protection_element_id:-8498], primaryKey: false);
      insert('organizations', [ id: 108484, nci_institute_code: "NV071", name: "Comprehensive Cancer Centers-Central Valley Pediatrics", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8499,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV071",GROUP_DESC:"NV071 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8499,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV071",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV071",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8499,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV071", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9499 ,protection_group_id: -8499, protection_element_id:-8499], primaryKey: false);
      insert('organizations', [ id: 108485, nci_institute_code: "NV072", name: "Northern Nevada Radiation Oncology", city: "Reno", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8500,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV072",GROUP_DESC:"NV072 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8500,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV072",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV072",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8500,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV072", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9500 ,protection_group_id: -8500, protection_element_id:-8500], primaryKey: false);
      insert('organizations', [ id: 108486, nci_institute_code: "NV073", name: "Children's Surgery", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8501,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV073",GROUP_DESC:"NV073 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8501,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV073",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV073",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8501,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV073", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9501 ,protection_group_id: -8501, protection_element_id:-8501], primaryKey: false);
      insert('organizations', [ id: 108487, nci_institute_code: "NV074", name: "Radiation Oncology Centers of Las Vegas-Eastern", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8502,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV074",GROUP_DESC:"NV074 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8502,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV074",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV074",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8502,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV074", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9502 ,protection_group_id: -8502, protection_element_id:-8502], primaryKey: false);
      insert('organizations', [ id: 108488, nci_institute_code: "NV075", name: "Radiation Oncology Centers of Las Vegas-Tenaya", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8503,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV075",GROUP_DESC:"NV075 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8503,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV075",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV075",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8503,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV075", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9503 ,protection_group_id: -8503, protection_element_id:-8503], primaryKey: false);
      insert('organizations', [ id: 108489, nci_institute_code: "NV076", name: "21st Century Oncology-Fort Apache", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8504,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV076",GROUP_DESC:"NV076 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8504,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV076",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV076",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8504,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV076", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9504 ,protection_group_id: -8504, protection_element_id:-8504], primaryKey: false);
      insert('organizations', [ id: 108490, nci_institute_code: "NV077", name: "21st Century Oncology-Lake Mead", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8505,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV077",GROUP_DESC:"NV077 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8505,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV077",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV077",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8505,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV077", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9505 ,protection_group_id: -8505, protection_element_id:-8505], primaryKey: false);
      insert('organizations', [ id: 108491, nci_institute_code: "NV078", name: "21st Century Oncology-Vegas Tenaya", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8506,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV078",GROUP_DESC:"NV078 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8506,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV078",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV078",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8506,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV078", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9506 ,protection_group_id: -8506, protection_element_id:-8506], primaryKey: false);
      insert('organizations', [ id: 108492, nci_institute_code: "NV079", name: "Cancer and Blood Specialists-Shadow", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8507,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV079",GROUP_DESC:"NV079 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8507,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV079",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV079",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8507,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV079", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9507 ,protection_group_id: -8507, protection_element_id:-8507], primaryKey: false);
      insert('organizations', [ id: 108493, nci_institute_code: "NV080", name: "Cancer and Blood Specialists-Fort Apache", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8508,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV080",GROUP_DESC:"NV080 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8508,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV080",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV080",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8508,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV080", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9508 ,protection_group_id: -8508, protection_element_id:-8508], primaryKey: false);
      insert('organizations', [ id: 108494, nci_institute_code: "NV081", name: "Nevada Cancer Center-Southwest San Martin", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8509,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV081",GROUP_DESC:"NV081 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8509,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV081",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV081",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8509,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV081", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9509 ,protection_group_id: -8509, protection_element_id:-8509], primaryKey: false);
      insert('organizations', [ id: 108495, nci_institute_code: "NV082", name: "Nevada Cancer Center-Centennial Hills", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8510,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV082",GROUP_DESC:"NV082 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8510,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV082",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV082",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8510,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV082", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9510 ,protection_group_id: -8510, protection_element_id:-8510], primaryKey: false);
      insert('organizations', [ id: 108496, nci_institute_code: "NV083", name: "Saint Mary's Center for Cancer", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8511,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV083",GROUP_DESC:"NV083 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8511,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV083",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV083",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8511,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV083", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9511 ,protection_group_id: -8511, protection_element_id:-8511], primaryKey: false);
      insert('organizations', [ id: 108497, nci_institute_code: "NV084", name: "Carson Tahoe Cancer Center", city: "Carson City", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8512,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV084",GROUP_DESC:"NV084 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8512,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV084",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV084",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8512,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV084", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9512 ,protection_group_id: -8512, protection_element_id:-8512], primaryKey: false);
    }

    void m20() {
        // all20 (25 terms)
      insert('organizations', [ id: 108498, nci_institute_code: "NV085", name: "Comprehensive Cancer Centers of Nevada-Summerlin", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8513,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV085",GROUP_DESC:"NV085 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8513,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV085",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV085",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8513,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV085", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9513 ,protection_group_id: -8513, protection_element_id:-8513], primaryKey: false);
      insert('organizations', [ id: 108499, nci_institute_code: "NV086", name: "Comprehensive Cancer Centers of Nevada-Outreach Center", city: "Boulder City", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8514,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV086",GROUP_DESC:"NV086 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8514,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV086",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV086",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8514,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV086", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9514 ,protection_group_id: -8514, protection_element_id:-8514], primaryKey: false);
      insert('organizations', [ id: 108500, nci_institute_code: "NV087", name: "Las Vegas Cancer Center-Medical Center", city: "Las Vegas", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8515,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV087",GROUP_DESC:"NV087 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8515,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV087",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV087",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8515,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV087", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9515 ,protection_group_id: -8515, protection_element_id:-8515], primaryKey: false);
      insert('organizations', [ id: 108501, nci_institute_code: "NV088", name: "Las Vegas Cancer Center-Pahrump", city: "Pahrump", state: "NV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8516,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV088",GROUP_DESC:"NV088 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8516,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NV088",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NV088",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8516,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NV088", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9516 ,protection_group_id: -8516, protection_element_id:-8516], primaryKey: false);
      insert('organizations', [ id: 108502, nci_institute_code: "NY418", name: "Jamestown Area Medical Associates LLP-Riverwalk Medical Office", city: "Jamestown", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8517,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY418",GROUP_DESC:"NY418 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8517,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY418",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY418",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8517,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY418", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9517 ,protection_group_id: -8517, protection_element_id:-8517], primaryKey: false);
      insert('organizations', [ id: 108503, nci_institute_code: "NY419", name: "North Shore Surgical Oncology", city: "Great Neck", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8518,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY419",GROUP_DESC:"NY419 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8518,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY419",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY419",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8518,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY419", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9518 ,protection_group_id: -8518, protection_element_id:-8518], primaryKey: false);
      insert('organizations', [ id: 108504, nci_institute_code: "NY420", name: "The Jay Monahan Center for Gastrointestinal Health", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8519,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY420",GROUP_DESC:"NY420 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8519,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY420",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY420",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8519,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY420", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9519 ,protection_group_id: -8519, protection_element_id:-8519], primaryKey: false);
      insert('organizations', [ id: 108505, nci_institute_code: "NY421", name: "Associates for Women's Medicine", city: "Syracuse", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8520,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY421",GROUP_DESC:"NY421 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8520,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY421",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY421",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8520,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY421", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9520 ,protection_group_id: -8520, protection_element_id:-8520], primaryKey: false);
      insert('organizations', [ id: 108506, nci_institute_code: "NY422", name: "Saint Mary's Hospital", city: "Amsterdam", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8521,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY422",GROUP_DESC:"NY422 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8521,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY422",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY422",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8521,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY422", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9521 ,protection_group_id: -8521, protection_element_id:-8521], primaryKey: false);
      insert('organizations', [ id: 108507, nci_institute_code: "NY423", name: "New York Hospital Medical Center of Queens", city: "Fresh Meadows", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8522,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY423",GROUP_DESC:"NY423 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8522,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY423",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY423",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8522,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY423", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9522 ,protection_group_id: -8522, protection_element_id:-8522], primaryKey: false);
      insert('organizations', [ id: 108508, nci_institute_code: "NY424", name: "New York University Urology Associates", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8523,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY424",GROUP_DESC:"NY424 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8523,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY424",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY424",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8523,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY424", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9523 ,protection_group_id: -8523, protection_element_id:-8523], primaryKey: false);
      insert('organizations', [ id: 108509, nci_institute_code: "NY425", name: "Advanced Radiation Centers-Lake Success Radiation Oncology", city: "North Hills", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8524,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY425",GROUP_DESC:"NY425 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8524,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY425",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY425",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8524,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY425", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9524 ,protection_group_id: -8524, protection_element_id:-8524], primaryKey: false);
      insert('organizations', [ id: 108510, nci_institute_code: "NY426", name: "Nalitt Institute For Cancer and Blood Related Diseases", city: "Staten Island", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8525,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY426",GROUP_DESC:"NY426 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8525,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY426",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY426",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8525,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY426", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9525 ,protection_group_id: -8525, protection_element_id:-8525], primaryKey: false);
      insert('organizations', [ id: 108511, nci_institute_code: "NY427", name: "Ann and Jules Gottlieb Womens Comprehensive Health Center", city: "Manhasset", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8526,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY427",GROUP_DESC:"NY427 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8526,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY427",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY427",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8526,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY427", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9526 ,protection_group_id: -8526, protection_element_id:-8526], primaryKey: false);
      insert('organizations', [ id: 108512, nci_institute_code: "NY428", name: "Community Hospital at Dobbs Ferry", city: "Dobbs Ferry", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8527,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY428",GROUP_DESC:"NY428 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8527,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY428",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY428",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8527,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY428", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9527 ,protection_group_id: -8527, protection_element_id:-8527], primaryKey: false);
      insert('organizations', [ id: 108513, nci_institute_code: "NY429", name: "Health Quest Medical Practice PC-Cardio Thoracic", city: "Poughkeepsie", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8528,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY429",GROUP_DESC:"NY429 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8528,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY429",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY429",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8528,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY429", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9528 ,protection_group_id: -8528, protection_element_id:-8528], primaryKey: false);
      insert('organizations', [ id: 108514, nci_institute_code: "NY430", name: "Cancer and Blood Specialists of Li", city: "Greenlawn", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8529,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY430",GROUP_DESC:"NY430 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8529,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY430",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY430",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8529,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY430", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9529 ,protection_group_id: -8529, protection_element_id:-8529], primaryKey: false);
      insert('organizations', [ id: 108515, nci_institute_code: "NY431", name: "Albany Liver and Pancreas Surgery PC", city: "Albany", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8530,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY431",GROUP_DESC:"NY431 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8530,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY431",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY431",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8530,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY431", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9530 ,protection_group_id: -8530, protection_element_id:-8530], primaryKey: false);
      insert('organizations', [ id: 108516, nci_institute_code: "NY432", name: "Hassenfeld Childrens Center for Cancer and Blood Disorders", city: "New York", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8531,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY432",GROUP_DESC:"NY432 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8531,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY432",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY432",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8531,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY432", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9531 ,protection_group_id: -8531, protection_element_id:-8531], primaryKey: false);
      insert('organizations', [ id: 108517, nci_institute_code: "NY433", name: "Hunter James Kelly Research Institute", city: "Buffalo", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8532,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY433",GROUP_DESC:"NY433 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8532,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY433",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY433",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8532,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY433", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9532 ,protection_group_id: -8532, protection_element_id:-8532], primaryKey: false);
      insert('organizations', [ id: 108518, nci_institute_code: "NY434", name: "Ear Nose and Throat Faculty Practice LLP", city: "Ardsley", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8533,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY434",GROUP_DESC:"NY434 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8533,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY434",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY434",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8533,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY434", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9533 ,protection_group_id: -8533, protection_element_id:-8533], primaryKey: false);
      insert('organizations', [ id: 108519, nci_institute_code: "NY435", name: "Long Island Neurosurgical Associates PC", city: "New Hyde Park", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8534,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY435",GROUP_DESC:"NY435 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8534,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY435",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY435",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8534,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY435", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9534 ,protection_group_id: -8534, protection_element_id:-8534], primaryKey: false);
      insert('organizations', [ id: 108520, nci_institute_code: "NY436", name: "Abraham Mittelman MD LLC", city: "Purchase", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8535,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY436",GROUP_DESC:"NY436 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8535,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY436",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY436",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8535,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY436", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9535 ,protection_group_id: -8535, protection_element_id:-8535], primaryKey: false);
      insert('organizations', [ id: 108521, nci_institute_code: "NY437", name: "The Arthur Smith Institute for Urology", city: "New Hyde Park", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8536,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY437",GROUP_DESC:"NY437 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8536,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY437",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY437",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8536,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY437", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9536 ,protection_group_id: -8536, protection_element_id:-8536], primaryKey: false);
      insert('organizations', [ id: 108522, nci_institute_code: "NY438", name: "Advanced Radiatin Centers of New York-Rockland Country", city: "West Nyack", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8537,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY438",GROUP_DESC:"NY438 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8537,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY438",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY438",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8537,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY438", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9537 ,protection_group_id: -8537, protection_element_id:-8537], primaryKey: false);
    }

    void m21() {
        // all21 (25 terms)
      insert('organizations', [ id: 108523, nci_institute_code: "NY439", name: "Interlakes Foundation-Greece", city: "Rochester", state: "NY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8538,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY439",GROUP_DESC:"NY439 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8538,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.NY439",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.NY439",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8538,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.NY439", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9538 ,protection_group_id: -8538, protection_element_id:-8538], primaryKey: false);
      insert('organizations', [ id: 108524, nci_institute_code: "OH155", name: "Kaiser Permanente", city: "Cleveland", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8539,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH155",GROUP_DESC:"OH155 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8539,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH155",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH155",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8539,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH155", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9539 ,protection_group_id: -8539, protection_element_id:-8539], primaryKey: false);
      insert('organizations', [ id: 108525, nci_institute_code: "OH193", name: "Cancer Care Center, Incorporated", city: "Salem", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8540,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH193",GROUP_DESC:"OH193 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8540,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH193",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH193",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8540,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH193", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9540 ,protection_group_id: -8540, protection_element_id:-8540], primaryKey: false);
      insert('organizations', [ id: 108526, nci_institute_code: "OH402", name: "Atrium Medical Center", city: "Franklin", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8541,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH402",GROUP_DESC:"OH402 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8541,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH402",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH402",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8541,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH402", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9541 ,protection_group_id: -8541, protection_element_id:-8541], primaryKey: false);
      insert('organizations', [ id: 108527, nci_institute_code: "OH403", name: "Hematology Oncology Consultants Incorporated", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8542,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH403",GROUP_DESC:"OH403 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8542,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH403",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH403",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8542,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH403", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9542 ,protection_group_id: -8542, protection_element_id:-8542], primaryKey: false);
      insert('organizations', [ id: 108528, nci_institute_code: "OH404", name: "Bay Park Community Hospital", city: "Oregon", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8543,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH404",GROUP_DESC:"OH404 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8543,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH404",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH404",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8543,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH404", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9543 ,protection_group_id: -8543, protection_element_id:-8543], primaryKey: false);
      insert('organizations', [ id: 108529, nci_institute_code: "OH405", name: "Mid Ohio Surgical Associates Inc", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8544,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH405",GROUP_DESC:"OH405 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8544,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH405",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH405",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8544,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH405", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9544 ,protection_group_id: -8544, protection_element_id:-8544], primaryKey: false);
      insert('organizations', [ id: 108530, nci_institute_code: "OH406", name: "Veterans Affairs Medical Center - Columbus", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8545,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH406",GROUP_DESC:"OH406 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8545,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH406",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH406",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8545,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH406", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9545 ,protection_group_id: -8545, protection_element_id:-8545], primaryKey: false);
      insert('organizations', [ id: 108531, nci_institute_code: "OH407", name: "Foster Boyd MD Regional Cancer Center", city: "Wilmington", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8546,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH407",GROUP_DESC:"OH407 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8546,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH407",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH407",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8546,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH407", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9546 ,protection_group_id: -8546, protection_element_id:-8546], primaryKey: false);
      insert('organizations', [ id: 108532, nci_institute_code: "OH408", name: "Adena Cancer Services", city: "Chillicothe", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8547,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH408",GROUP_DESC:"OH408 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8547,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH408",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH408",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8547,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH408", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9547 ,protection_group_id: -8547, protection_element_id:-8547], primaryKey: false);
      insert('organizations', [ id: 108533, nci_institute_code: "OH409", name: "Toledo Clinic Inc", city: "Toledo", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8548,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH409",GROUP_DESC:"OH409 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8548,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH409",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH409",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8548,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH409", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9548 ,protection_group_id: -8548, protection_element_id:-8548], primaryKey: false);
      insert('organizations', [ id: 108534, nci_institute_code: "OH410", name: "Akron Children's Center for Blood and Cancer", city: "Youngstown", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8549,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH410",GROUP_DESC:"OH410 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8549,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH410",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH410",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8549,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH410", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9549 ,protection_group_id: -8549, protection_element_id:-8549], primaryKey: false);
      insert('organizations', [ id: 108535, nci_institute_code: "OH411", name: "Akron General Hope Comprehensive Cancer Care", city: "Norton", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8550,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH411",GROUP_DESC:"OH411 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8550,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH411",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH411",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8550,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH411", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9550 ,protection_group_id: -8550, protection_element_id:-8550], primaryKey: false);
      insert('organizations', [ id: 108536, nci_institute_code: "OH412", name: "North Coast Cancer Care-Clyde", city: "Clyde", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8551,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH412",GROUP_DESC:"OH412 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8551,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH412",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH412",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8551,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH412", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9551 ,protection_group_id: -8551, protection_element_id:-8551], primaryKey: false);
      insert('organizations', [ id: 108537, nci_institute_code: "OH413", name: "Eastern Woods Radiation Oncology", city: "Findlay", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8552,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH413",GROUP_DESC:"OH413 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8552,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH413",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH413",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8552,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH413", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9552 ,protection_group_id: -8552, protection_element_id:-8552], primaryKey: false);
      insert('organizations', [ id: 108538, nci_institute_code: "OH414", name: "Mercy Cancer Center at Saint Anne Mercy Hospital", city: "Toledo", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8553,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH414",GROUP_DESC:"OH414 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8553,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH414",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH414",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8553,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH414", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9553 ,protection_group_id: -8553, protection_element_id:-8553], primaryKey: false);
      insert('organizations', [ id: 108539, nci_institute_code: "OH415", name: "Wayne Hospital", city: "Greenville", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8554,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH415",GROUP_DESC:"OH415 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8554,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH415",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH415",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8554,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH415", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9554 ,protection_group_id: -8554, protection_element_id:-8554], primaryKey: false);
      insert('organizations', [ id: 108540, nci_institute_code: "OH416", name: "Jean B and Milton N Cooper Cancer Center", city: "Akron", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8555,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH416",GROUP_DESC:"OH416 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8555,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH416",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH416",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8555,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH416", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9555 ,protection_group_id: -8555, protection_element_id:-8555], primaryKey: false);
      insert('organizations', [ id: 108541, nci_institute_code: "OH417", name: "Akron Children's Hospital-Valley Beeghly Campus", city: "Boardman", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8556,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH417",GROUP_DESC:"OH417 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8556,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH417",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH417",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8556,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH417", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9556 ,protection_group_id: -8556, protection_element_id:-8556], primaryKey: false);
      insert('organizations', [ id: 108542, nci_institute_code: "OH418", name: "Columbus Surgical Specialists Inc", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8557,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH418",GROUP_DESC:"OH418 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8557,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH418",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH418",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8557,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH418", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9557 ,protection_group_id: -8557, protection_element_id:-8557], primaryKey: false);
      insert('organizations', [ id: 108543, nci_institute_code: "OH419", name: "Consultants in Laboratory Medicine of Greater Toledo", city: "Toledo", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8558,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH419",GROUP_DESC:"OH419 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8558,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH419",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH419",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8558,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH419", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9558 ,protection_group_id: -8558, protection_element_id:-8558], primaryKey: false);
      insert('organizations', [ id: 108544, nci_institute_code: "OH420", name: "Central Ohio Surgical Specialists Inc-Columbus", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8559,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH420",GROUP_DESC:"OH420 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8559,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH420",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH420",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8559,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH420", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9559 ,protection_group_id: -8559, protection_element_id:-8559], primaryKey: false);
      insert('organizations', [ id: 108545, nci_institute_code: "OH421", name: "Fisher-Titus Medical Center", city: "Norwalk", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8560,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH421",GROUP_DESC:"OH421 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8560,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH421",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH421",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8560,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH421", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9560 ,protection_group_id: -8560, protection_element_id:-8560], primaryKey: false);
      insert('organizations', [ id: 108546, nci_institute_code: "OH422", name: "Akron Pediatric Surgical Associates", city: "Akron", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8561,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH422",GROUP_DESC:"OH422 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8561,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH422",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH422",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8561,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH422", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9561 ,protection_group_id: -8561, protection_element_id:-8561], primaryKey: false);
      insert('organizations', [ id: 108547, nci_institute_code: "OH423", name: "Millennium Wellness Center", city: "Dayton", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8562,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH423",GROUP_DESC:"OH423 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8562,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH423",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH423",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8562,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH423", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9562 ,protection_group_id: -8562, protection_element_id:-8562], primaryKey: false);
    }

    void m22() {
        // all22 (25 terms)
      insert('organizations', [ id: 108548, nci_institute_code: "OH424", name: "Ireland Cancer Center at Firelands Regional Medical Center", city: "Sandusky", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8563,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH424",GROUP_DESC:"OH424 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8563,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH424",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH424",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8563,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH424", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9563 ,protection_group_id: -8563, protection_element_id:-8563], primaryKey: false);
      insert('organizations', [ id: 108549, nci_institute_code: "OH425", name: "Gabrail Cancer Center-Dover", city: "Dover", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8564,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH425",GROUP_DESC:"OH425 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8564,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH425",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH425",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8564,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH425", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9564 ,protection_group_id: -8564, protection_element_id:-8564], primaryKey: false);
      insert('organizations', [ id: 108550, nci_institute_code: "OH426", name: "Kaiser Permanente-Chapel Hill Medical Offices", city: "Akron", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8565,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH426",GROUP_DESC:"OH426 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8565,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH426",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH426",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8565,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH426", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9565 ,protection_group_id: -8565, protection_element_id:-8565], primaryKey: false);
      insert('organizations', [ id: 108551, nci_institute_code: "OK082", name: "Warren Clinic-Tulsa South", city: "Tulsa", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8566,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK082",GROUP_DESC:"OK082 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8566,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK082",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK082",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8566,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK082", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9566 ,protection_group_id: -8566, protection_element_id:-8566], primaryKey: false);
      insert('organizations', [ id: 108552, nci_institute_code: "OK083", name: "Tulsa Medical Laboratory", city: "Tulsa", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8567,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK083",GROUP_DESC:"OK083 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8567,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK083",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK083",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8567,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK083", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9567 ,protection_group_id: -8567, protection_element_id:-8567], primaryKey: false);
      insert('organizations', [ id: 108553, nci_institute_code: "OK084", name: "Pediatric Surgery Inc", city: "Tulsa", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8568,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK084",GROUP_DESC:"OK084 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8568,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK084",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK084",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8568,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK084", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9568 ,protection_group_id: -8568, protection_element_id:-8568], primaryKey: false);
      insert('organizations', [ id: 108554, nci_institute_code: "OK085", name: "ProCure Proton Therapy Center", city: "Oklahoma City", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8569,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK085",GROUP_DESC:"OK085 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8569,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK085",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK085",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8569,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK085", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9569 ,protection_group_id: -8569, protection_element_id:-8569], primaryKey: false);
      insert('organizations', [ id: 108555, nci_institute_code: "OK086", name: "Cancer Care Associates at Midtown", city: "Oklahoma City", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8570,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK086",GROUP_DESC:"OK086 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8570,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK086",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK086",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8570,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK086", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9570 ,protection_group_id: -8570, protection_element_id:-8570], primaryKey: false);
      insert('organizations', [ id: 108556, nci_institute_code: "OR111", name: "Northwest Cancer Specialists-Meridian Park", city: "Tualatin", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8571,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR111",GROUP_DESC:"OR111 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8571,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR111",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR111",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8571,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR111", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9571 ,protection_group_id: -8571, protection_element_id:-8571], primaryKey: false);
      insert('organizations', [ id: 108557, nci_institute_code: "OR112", name: "Providence Oncology and Hematology Care Westside", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8572,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR112",GROUP_DESC:"OR112 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8572,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR112",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR112",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8572,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR112", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9572 ,protection_group_id: -8572, protection_element_id:-8572], primaryKey: false);
      insert('organizations', [ id: 108558, nci_institute_code: "OR113", name: "The Oregon Clinic-Providence Portland", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8573,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR113",GROUP_DESC:"OR113 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8573,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR113",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR113",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8573,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR113", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9573 ,protection_group_id: -8573, protection_element_id:-8573], primaryKey: false);
      insert('organizations', [ id: 108559, nci_institute_code: "OR114", name: "Northwest Cancer Specialists-Saint Vincent Office", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8574,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR114",GROUP_DESC:"OR114 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8574,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR114",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR114",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8574,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR114", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9574 ,protection_group_id: -8574, protection_element_id:-8574], primaryKey: false);
      insert('organizations', [ id: 108560, nci_institute_code: "OR115", name: "The Oregon Clinic- Providence Cancer Center", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8575,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR115",GROUP_DESC:"OR115 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8575,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR115",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR115",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8575,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR115", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9575 ,protection_group_id: -8575, protection_element_id:-8575], primaryKey: false);
      insert('organizations', [ id: 108561, nci_institute_code: "OR116", name: "Pacific Neurosurgical PC", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8576,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR116",GROUP_DESC:"OR116 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8576,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR116",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR116",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8576,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR116", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9576 ,protection_group_id: -8576, protection_element_id:-8576], primaryKey: false);
      insert('organizations', [ id: 108562, nci_institute_code: "OR117", name: "Legacy Emanuel Children's Hospital", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8577,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR117",GROUP_DESC:"OR117 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8577,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR117",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR117",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8577,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR117", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9577 ,protection_group_id: -8577, protection_element_id:-8577], primaryKey: false);
      insert('organizations', [ id: 108563, nci_institute_code: "OR118", name: "Salem Hospital-Radiation Oncology", city: "Salem", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8578,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR118",GROUP_DESC:"OR118 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8578,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR118",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR118",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8578,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR118", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9578 ,protection_group_id: -8578, protection_element_id:-8578], primaryKey: false);
      insert('organizations', [ id: 108564, nci_institute_code: "OR119", name: "Cancer Care of the Cascades-Saint Charles Medical Center", city: "Bend", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8579,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR119",GROUP_DESC:"OR119 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8579,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR119",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR119",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8579,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR119", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9579 ,protection_group_id: -8579, protection_element_id:-8579], primaryKey: false);
      insert('organizations', [ id: 108565, nci_institute_code: "OR120", name: "Deanna K Olson MD", city: "Milwaukie", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8580,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR120",GROUP_DESC:"OR120 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8580,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR120",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR120",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8580,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR120", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9580 ,protection_group_id: -8580, protection_element_id:-8580], primaryKey: false);
      insert('organizations', [ id: 108566, nci_institute_code: "OR121", name: "Pacific Oncology-Tualatin", city: "Tualatin", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8581,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR121",GROUP_DESC:"OR121 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8581,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR121",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR121",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8581,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR121", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9581 ,protection_group_id: -8581, protection_element_id:-8581], primaryKey: false);
      insert('organizations', [ id: 108567, nci_institute_code: "OR122", name: "Pediatric Surgical Associates", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8582,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR122",GROUP_DESC:"OR122 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8582,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR122",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR122",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8582,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR122", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9582 ,protection_group_id: -8582, protection_element_id:-8582], primaryKey: false);
      insert('organizations', [ id: 108568, nci_institute_code: "PA420", name: "Comprehensive Breast Care Institute at DSI of Bucks County", city: "Bensalem", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8583,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA420",GROUP_DESC:"PA420 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8583,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA420",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA420",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8583,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA420", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9583 ,protection_group_id: -8583, protection_element_id:-8583], primaryKey: false);
      insert('organizations', [ id: 108569, nci_institute_code: "PA421", name: "Urology Center of Chester County", city: "West Chester", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8584,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA421",GROUP_DESC:"PA421 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8584,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA421",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA421",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8584,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA421", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9584 ,protection_group_id: -8584, protection_element_id:-8584], primaryKey: false);
      insert('organizations', [ id: 108570, nci_institute_code: "PA422", name: "Saint Luke's Center for Urology", city: "Bethlehem", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8585,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA422",GROUP_DESC:"PA422 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8585,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA422",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA422",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8585,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA422", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9585 ,protection_group_id: -8585, protection_element_id:-8585], primaryKey: false);
      insert('organizations', [ id: 108571, nci_institute_code: "PA423", name: "Yolanda G Barco Oncology Institute", city: "Meadville", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8586,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA423",GROUP_DESC:"PA423 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8586,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA423",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA423",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8586,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA423", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9586 ,protection_group_id: -8586, protection_element_id:-8586], primaryKey: false);
      insert('organizations', [ id: 108572, nci_institute_code: "PA424", name: "Keystone Oncology Associates", city: "Bernville", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8587,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA424",GROUP_DESC:"PA424 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8587,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA424",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA424",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8587,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA424", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9587 ,protection_group_id: -8587, protection_element_id:-8587], primaryKey: false);
    }

    void m23() {
        // all23 (25 terms)
      insert('organizations', [ id: 108573, nci_institute_code: "PA425", name: "UPMC Hillman Cancer Center", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8588,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA425",GROUP_DESC:"PA425 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8588,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA425",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA425",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8588,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA425", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9588 ,protection_group_id: -8588, protection_element_id:-8588], primaryKey: false);
      insert('organizations', [ id: 108574, nci_institute_code: "PA426", name: "Hayman S Salib MD", city: "Easton", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8589,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA426",GROUP_DESC:"PA426 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8589,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA426",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA426",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8589,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA426", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9589 ,protection_group_id: -8589, protection_element_id:-8589], primaryKey: false);
      insert('organizations', [ id: 108575, nci_institute_code: "PA427", name: "Main Line Surgeons Limited", city: "Wynnewood", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8590,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA427",GROUP_DESC:"PA427 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8590,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA427",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA427",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8590,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA427", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9590 ,protection_group_id: -8590, protection_element_id:-8590], primaryKey: false);
      insert('organizations', [ id: 108576, nci_institute_code: "PA428", name: "Bux-Mont Oncology Hematology Associates", city: "Doylestown", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8591,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA428",GROUP_DESC:"PA428 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8591,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA428",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA428",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8591,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA428", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9591 ,protection_group_id: -8591, protection_element_id:-8591], primaryKey: false);
      insert('organizations', [ id: 108577, nci_institute_code: "PA429", name: "Adams Cancer Center", city: "Gettysburg", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8592,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA429",GROUP_DESC:"PA429 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8592,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA429",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA429",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8592,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA429", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9592 ,protection_group_id: -8592, protection_element_id:-8592], primaryKey: false);
      insert('organizations', [ id: 108578, nci_institute_code: "PA430", name: "Cardiothoracic Surgical Associates of Reading", city: "West Reading", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8593,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA430",GROUP_DESC:"PA430 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8593,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA430",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA430",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8593,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA430", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9593 ,protection_group_id: -8593, protection_element_id:-8593], primaryKey: false);
      insert('organizations', [ id: 108579, nci_institute_code: "PA431", name: "Alliance Hematology Oncology - Gettysburg", city: "Gettysburg", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8594,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA431",GROUP_DESC:"PA431 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8594,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA431",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA431",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8594,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA431", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9594 ,protection_group_id: -8594, protection_element_id:-8594], primaryKey: false);
      insert('organizations', [ id: 108580, nci_institute_code: "PA432", name: "Medical Associates of Monroe County PC", city: "East Stroudsburg", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8595,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA432",GROUP_DESC:"PA432 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8595,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA432",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA432",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8595,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA432", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9595 ,protection_group_id: -8595, protection_element_id:-8595], primaryKey: false);
      insert('organizations', [ id: 108581, nci_institute_code: "PA433", name: "UPMC Jameson Cancer Center", city: "New Castle", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8596,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA433",GROUP_DESC:"PA433 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8596,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA433",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA433",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8596,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA433", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9596 ,protection_group_id: -8596, protection_element_id:-8596], primaryKey: false);
      insert('organizations', [ id: 108582, nci_institute_code: "PA434", name: "Gynecology-Oncology of Lancaster", city: "Lancaster", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8597,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA434",GROUP_DESC:"PA434 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8597,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA434",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA434",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8597,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA434", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9597 ,protection_group_id: -8597, protection_element_id:-8597], primaryKey: false);
      insert('organizations', [ id: 108583, nci_institute_code: "PA435", name: "Cardiovascular and Thoracic Surgical Associates of Saint Luke's", city: "Bethlehem", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8598,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA435",GROUP_DESC:"PA435 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8598,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA435",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA435",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8598,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA435", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9598 ,protection_group_id: -8598, protection_element_id:-8598], primaryKey: false);
      insert('organizations', [ id: 108584, nci_institute_code: "PA436", name: "Wills Eye Institute", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8599,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA436",GROUP_DESC:"PA436 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8599,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA436",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA436",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8599,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA436", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9599 ,protection_group_id: -8599, protection_element_id:-8599], primaryKey: false);
      insert('organizations', [ id: 108585, nci_institute_code: "PA437", name: "Marks Colorectal Surgical Associates", city: "Wynnewood", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8600,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA437",GROUP_DESC:"PA437 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8600,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA437",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA437",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8600,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA437", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9600 ,protection_group_id: -8600, protection_element_id:-8600], primaryKey: false);
      insert('organizations', [ id: 108586, nci_institute_code: "PA438", name: "Keystone Surgical Associates-Bethlehem", city: "Bethlehem", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8601,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA438",GROUP_DESC:"PA438 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8601,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA438",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA438",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8601,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA438", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9601 ,protection_group_id: -8601, protection_element_id:-8601], primaryKey: false);
      insert('organizations', [ id: 108587, nci_institute_code: "PA439", name: "Cantor Biermann Fellin and Associates", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8602,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA439",GROUP_DESC:"PA439 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8602,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA439",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA439",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8602,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA439", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9602 ,protection_group_id: -8602, protection_element_id:-8602], primaryKey: false);
      insert('organizations', [ id: 108588, nci_institute_code: "PA440", name: "WellSpan Surgical Oncology", city: "York", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8603,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA440",GROUP_DESC:"PA440 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8603,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA440",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA440",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8603,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA440", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9603 ,protection_group_id: -8603, protection_element_id:-8603], primaryKey: false);
      insert('organizations', [ id: 108589, nci_institute_code: "PA441", name: "Academic Urology Prostate Center", city: "King of Prussia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8604,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA441",GROUP_DESC:"PA441 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8604,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA441",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA441",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8604,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA441", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9604 ,protection_group_id: -8604, protection_element_id:-8604], primaryKey: false);
      insert('organizations', [ id: 108590, nci_institute_code: "PA442", name: "Cardiothoracic Surgeons of Lancaster PC", city: "Lancaster", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8605,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA442",GROUP_DESC:"PA442 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8605,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA442",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA442",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8605,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA442", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9605 ,protection_group_id: -8605, protection_element_id:-8605], primaryKey: false);
      insert('organizations', [ id: 108591, nci_institute_code: "PA443", name: "Drexel University-Executive Leadership in Academic Medicine", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8606,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA443",GROUP_DESC:"PA443 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8606,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA443",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA443",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8606,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA443", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9606 ,protection_group_id: -8606, protection_element_id:-8606], primaryKey: false);
      insert('organizations', [ id: 108592, nci_institute_code: "PA444", name: "Gynecologic Oncology Specialists-South Cedar", city: "Allentown", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8607,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA444",GROUP_DESC:"PA444 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8607,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA444",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA444",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8607,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA444", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9607 ,protection_group_id: -8607, protection_element_id:-8607], primaryKey: false);
      insert('organizations', [ id: 108593, nci_institute_code: "PA445", name: "VITA Hematology Oncology PC", city: "Bethleham", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8608,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA445",GROUP_DESC:"PA445 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8608,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA445",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA445",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8608,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA445", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9608 ,protection_group_id: -8608, protection_element_id:-8608], primaryKey: false);
      insert('organizations', [ id: 108594, nci_institute_code: "PR030", name: "Doctors Cancer Center", city: "Manati", state: "PR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8609,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR030",GROUP_DESC:"PR030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8609,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PR030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PR030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8609,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9609 ,protection_group_id: -8609, protection_element_id:-8609], primaryKey: false);
      insert('organizations', [ id: 108595, nci_institute_code: "PR031", name: "Puerto Rico Hematology Oncology Group", city: "Bayamon", state: "PR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8610,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR031",GROUP_DESC:"PR031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8610,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PR031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PR031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8610,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9610 ,protection_group_id: -8610, protection_element_id:-8610], primaryKey: false);
      insert('organizations', [ id: 108596, nci_institute_code: "PR032", name: "Centro De Cancer De La Muntana", city: "Guaynabo", state: "PR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8611,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR032",GROUP_DESC:"PR032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8611,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PR032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PR032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8611,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9611 ,protection_group_id: -8611, protection_element_id:-8611], primaryKey: false);
      insert('organizations', [ id: 108597, nci_institute_code: "PR033", name: "Metropolitan Oncology Center", city: "San Juan", state: "PR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8612,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR033",GROUP_DESC:"PR033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8612,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PR033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PR033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8612,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9612 ,protection_group_id: -8612, protection_element_id:-8612], primaryKey: false);
    }

    void m24() {
        // all24 (25 terms)
      insert('organizations', [ id: 108598, nci_institute_code: "PR034", name: "Cancer Center-Metro Medical Center Bayamon", city: "Bayamon", state: "PR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8613,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR034",GROUP_DESC:"PR034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8613,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PR034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PR034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8613,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9613 ,protection_group_id: -8613, protection_element_id:-8613], primaryKey: false);
      insert('organizations', [ id: 108599, nci_institute_code: "RI025", name: "Thoracic and Caardiovascular Surgical Center", city: "Providence", state: "RI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8614,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI025",GROUP_DESC:"RI025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8614,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.RI025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.RI025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8614,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9614 ,protection_group_id: -8614, protection_element_id:-8614], primaryKey: false);
      insert('organizations', [ id: 108600, nci_institute_code: "RI026", name: "University Orthopedics", city: "Providence", state: "RI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8615,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI026",GROUP_DESC:"RI026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8615,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.RI026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.RI026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8615,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9615 ,protection_group_id: -8615, protection_element_id:-8615], primaryKey: false);
      insert('organizations', [ id: 108601, nci_institute_code: "RI027", name: "Oncology Hematology Associates-Westerly", city: "Westerly", state: "RI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8616,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI027",GROUP_DESC:"RI027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8616,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.RI027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.RI027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8616,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9616 ,protection_group_id: -8616, protection_element_id:-8616], primaryKey: false);
      insert('organizations', [ id: 108602, nci_institute_code: "SC081", name: "South Carolina Cancer Specialists PC", city: "Hilton Head Island", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8617,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC081",GROUP_DESC:"SC081 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8617,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC081",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC081",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8617,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC081", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9617 ,protection_group_id: -8617, protection_element_id:-8617], primaryKey: false);
      insert('organizations', [ id: 108603, nci_institute_code: "SC082", name: "Rock Hill Radiation Therapy Center", city: "Rock Hill", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8618,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC082",GROUP_DESC:"SC082 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8618,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC082",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC082",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8618,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC082", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9618 ,protection_group_id: -8618, protection_element_id:-8618], primaryKey: false);
      insert('organizations', [ id: 108604, nci_institute_code: "SC083", name: "Coastal Carolina Breast Center", city: "Murrells Inlet", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8619,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC083",GROUP_DESC:"SC083 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8619,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC083",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC083",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8619,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC083", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9619 ,protection_group_id: -8619, protection_element_id:-8619], primaryKey: false);
      insert('organizations', [ id: 108605, nci_institute_code: "SC084", name: "Cancer Centers of the Carolinas-Greer Medical Oncology", city: "Greer", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8620,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC084",GROUP_DESC:"SC084 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8620,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC084",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC084",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8620,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC084", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9620 ,protection_group_id: -8620, protection_element_id:-8620], primaryKey: false);
      insert('organizations', [ id: 108606, nci_institute_code: "SC085", name: "Cancer Centers of the Carolinas-Greer Radiation Oncology", city: "Greer", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8621,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC085",GROUP_DESC:"SC085 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8621,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC085",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC085",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8621,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC085", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9621 ,protection_group_id: -8621, protection_element_id:-8621], primaryKey: false);
      insert('organizations', [ id: 108607, nci_institute_code: "SC086", name: "Southern Surgical Group-West Columbia", city: "West Columbia", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8622,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC086",GROUP_DESC:"SC086 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8622,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC086",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC086",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8622,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC086", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9622 ,protection_group_id: -8622, protection_element_id:-8622], primaryKey: false);
      insert('organizations', [ id: 108608, nci_institute_code: "SC087", name: "Cancer Specialists of Charleston", city: "Charleston", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8623,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC087",GROUP_DESC:"SC087 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8623,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC087",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC087",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8623,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC087", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9623 ,protection_group_id: -8623, protection_element_id:-8623], primaryKey: false);
      insert('organizations', [ id: 108609, nci_institute_code: "SD038", name: "Obstetrics Gynecology and Gynecologic Oncology PC", city: "Sioux Falls", state: "SD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8624,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD038",GROUP_DESC:"SD038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8624,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SD038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SD038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8624,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9624 ,protection_group_id: -8624, protection_element_id:-8624], primaryKey: false);
      insert('organizations', [ id: 108610, nci_institute_code: "SD039", name: "Sanford Children's Specialty Clinic-Sioux Falls", city: "Sioux Falss", state: "SD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8625,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD039",GROUP_DESC:"SD039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8625,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SD039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SD039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8625,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9625 ,protection_group_id: -8625, protection_element_id:-8625], primaryKey: false);
      insert('organizations', [ id: 108611, nci_institute_code: "TN162", name: "Chattanooga's Program in Women's Oncology", city: "Chattanooga", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8626,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN162",GROUP_DESC:"TN162 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8626,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN162",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN162",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8626,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN162", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9626 ,protection_group_id: -8626, protection_element_id:-8626], primaryKey: false);
      insert('organizations', [ id: 108612, nci_institute_code: "TN163", name: "The Sarah Cannon Cancer Center at Natchez Medical Park", city: "Dickson", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8627,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN163",GROUP_DESC:"TN163 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8627,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN163",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN163",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8627,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN163", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9627 ,protection_group_id: -8627, protection_element_id:-8627], primaryKey: false);
      insert('organizations', [ id: 108613, nci_institute_code: "TN164", name: "Diagnostic Imaging Consultants", city: "Chattanooga", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8628,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN164",GROUP_DESC:"TN164 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8628,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN164",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN164",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8628,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN164", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9628 ,protection_group_id: -8628, protection_element_id:-8628], primaryKey: false);
      insert('organizations', [ id: 108614, nci_institute_code: "TN165", name: "Kingsport Hematology and Oncology Associates", city: "Kingsport", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8629,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN165",GROUP_DESC:"TN165 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8629,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN165",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN165",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8629,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN165", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9629 ,protection_group_id: -8629, protection_element_id:-8629], primaryKey: false);
      insert('organizations', [ id: 108615, nci_institute_code: "TN166", name: "Oncology Associates-Alcoa", city: "Alcoa", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8630,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN166",GROUP_DESC:"TN166 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8630,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN166",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN166",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8630,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN166", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9630 ,protection_group_id: -8630, protection_element_id:-8630], primaryKey: false);
      insert('organizations', [ id: 108616, nci_institute_code: "TN167", name: "Howell Allen Clinic-Baptist Office", city: "Nashville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8631,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN167",GROUP_DESC:"TN167 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8631,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN167",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN167",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8631,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN167", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9631 ,protection_group_id: -8631, protection_element_id:-8631], primaryKey: false);
      insert('organizations', [ id: 108617, nci_institute_code: "TN168", name: "Radiology Alliance PC", city: "Nashville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8632,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN168",GROUP_DESC:"TN168 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8632,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN168",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN168",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8632,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN168", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9632 ,protection_group_id: -8632, protection_element_id:-8632], primaryKey: false);
      insert('organizations', [ id: 108618, nci_institute_code: "TN169", name: "Innovative Pathology Services", city: "Knoxville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8633,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN169",GROUP_DESC:"TN169 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8633,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN169",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN169",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8633,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN169", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9633 ,protection_group_id: -8633, protection_element_id:-8633], primaryKey: false);
      insert('organizations', [ id: 108619, nci_institute_code: "TN170", name: "Associates in Diagnostic Radiology PC and Plaza Radiology LLC", city: "Chattanooga", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8634,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN170",GROUP_DESC:"TN170 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8634,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN170",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN170",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8634,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN170", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9634 ,protection_group_id: -8634, protection_element_id:-8634], primaryKey: false);
      insert('organizations', [ id: 108620, nci_institute_code: "TN172", name: "The Jackson Clinic PA", city: "Jackson", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8635,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN172",GROUP_DESC:"TN172 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8635,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN172",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN172",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8635,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN172", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9635 ,protection_group_id: -8635, protection_element_id:-8635], primaryKey: false);
      insert('organizations', [ id: 108621, nci_institute_code: "TN173", name: "Family Cancer Center-Memphis", city: "Memphis", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8636,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN173",GROUP_DESC:"TN173 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8636,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN173",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN173",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8636,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN173", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9636 ,protection_group_id: -8636, protection_element_id:-8636], primaryKey: false);
      insert('organizations', [ id: 108622, nci_institute_code: "TN174", name: "The Center for Biomedical Research", city: "Knoxville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8637,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN174",GROUP_DESC:"TN174 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8637,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN174",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN174",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8637,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN174", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9637 ,protection_group_id: -8637, protection_element_id:-8637], primaryKey: false);
    }

    void m25() {
        // all25 (25 terms)
      insert('organizations', [ id: 108623, nci_institute_code: "TN175", name: "Tennessee Cancer Specialists-Dowell Springs", city: "Knoxville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8638,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN175",GROUP_DESC:"TN175 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8638,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN175",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN175",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8638,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN175", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9638 ,protection_group_id: -8638, protection_element_id:-8638], primaryKey: false);
      insert('organizations', [ id: 108624, nci_institute_code: "TN176", name: "Sarah Cannon Research Institute (SCRI)", city: "Knoxville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8639,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN176",GROUP_DESC:"TN176 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8639,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN176",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN176",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8639,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN176", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9639 ,protection_group_id: -8639, protection_element_id:-8639], primaryKey: false);
      insert('organizations', [ id: 108625, nci_institute_code: "TX318", name: "South Texas Oncology and Hematology PA-The Atrium", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8640,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX318",GROUP_DESC:"TX318 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8640,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX318",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX318",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8640,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX318", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9640 ,protection_group_id: -8640, protection_element_id:-8640], primaryKey: false);
      insert('organizations', [ id: 108626, nci_institute_code: "TX319", name: "US Onology-Research", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8641,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX319",GROUP_DESC:"TX319 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8641,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX319",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX319",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8641,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX319", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9641 ,protection_group_id: -8641, protection_element_id:-8641], primaryKey: false);
      insert('organizations', [ id: 108627, nci_institute_code: "TX320", name: "Texas Tech University Health Science Center-Dallas", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8642,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX320",GROUP_DESC:"TX320 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8642,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX320",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX320",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8642,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX320", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9642 ,protection_group_id: -8642, protection_element_id:-8642], primaryKey: false);
      insert('organizations', [ id: 108628, nci_institute_code: "TX321", name: "Southlake Oncology PA", city: "Southlake", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8643,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX321",GROUP_DESC:"TX321 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8643,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX321",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX321",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8643,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX321", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9643 ,protection_group_id: -8643, protection_element_id:-8643], primaryKey: false);
      insert('organizations', [ id: 108629, nci_institute_code: "TX322", name: "South Texas Oncology and Hematology PA", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8644,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX322",GROUP_DESC:"TX322 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8644,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX322",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX322",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8644,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX322", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9644 ,protection_group_id: -8644, protection_element_id:-8644], primaryKey: false);
      insert('organizations', [ id: 108630, nci_institute_code: "TX323", name: "The Center for Cancer and Blood Disorders-Denton", city: "Denton", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8645,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX323",GROUP_DESC:"TX323 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8645,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX323",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX323",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8645,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX323", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9645 ,protection_group_id: -8645, protection_element_id:-8645], primaryKey: false);
      insert('organizations', [ id: 108631, nci_institute_code: "TX324", name: "Cancer Care Centers of South Texas- Northeast", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8646,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX324",GROUP_DESC:"TX324 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8646,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX324",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX324",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8646,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX324", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9646 ,protection_group_id: -8646, protection_element_id:-8646], primaryKey: false);
      insert('organizations', [ id: 108632, nci_institute_code: "TX325", name: "Lyndon Baines Johnson General Hospital", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8647,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX325",GROUP_DESC:"TX325 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8647,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX325",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX325",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8647,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX325", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9647 ,protection_group_id: -8647, protection_element_id:-8647], primaryKey: false);
      insert('organizations', [ id: 108633, nci_institute_code: "TX326", name: "Breast Surgeons of North Texas", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8648,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX326",GROUP_DESC:"TX326 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8648,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX326",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX326",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8648,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX326", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9648 ,protection_group_id: -8648, protection_element_id:-8648], primaryKey: false);
      insert('organizations', [ id: 108634, nci_institute_code: "TX327", name: "Surgical Group of Memorial City", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8649,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX327",GROUP_DESC:"TX327 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8649,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX327",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX327",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8649,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX327", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9649 ,protection_group_id: -8649, protection_element_id:-8649], primaryKey: false);
      insert('organizations', [ id: 108635, nci_institute_code: "TX328", name: "Memorial Herman Northwest Hospital", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8650,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX328",GROUP_DESC:"TX328 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8650,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX328",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX328",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8650,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX328", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9650 ,protection_group_id: -8650, protection_element_id:-8650], primaryKey: false);
      insert('organizations', [ id: 108636, nci_institute_code: "TX329", name: "Oncology Consultants PA-Park Plaza", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8651,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX329",GROUP_DESC:"TX329 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8651,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX329",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX329",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8651,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX329", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9651 ,protection_group_id: -8651, protection_element_id:-8651], primaryKey: false);
      insert('organizations', [ id: 108637, nci_institute_code: "TX330", name: "Oncology Consultants PA-Memorial City", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8652,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX330",GROUP_DESC:"TX330 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8652,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX330",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX330",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8652,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX330", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9652 ,protection_group_id: -8652, protection_element_id:-8652], primaryKey: false);
      insert('organizations', [ id: 108638, nci_institute_code: "TX331", name: "Scott and White University Medical Campus", city: "Round Rock", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8653,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX331",GROUP_DESC:"TX331 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8653,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX331",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX331",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8653,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX331", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9653 ,protection_group_id: -8653, protection_element_id:-8653], primaryKey: false);
      insert('organizations', [ id: 108639, nci_institute_code: "TX332", name: "Memorial Hermann The Woodlands Hospital", city: "The Woodlands", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8654,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX332",GROUP_DESC:"TX332 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8654,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX332",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX332",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8654,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX332", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9654 ,protection_group_id: -8654, protection_element_id:-8654], primaryKey: false);
      insert('organizations', [ id: 108640, nci_institute_code: "TX333", name: "Memorial Hermann Sugar Land Hospital", city: "Sugar Land", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8655,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX333",GROUP_DESC:"TX333 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8655,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX333",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX333",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8655,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX333", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9655 ,protection_group_id: -8655, protection_element_id:-8655], primaryKey: false);
      insert('organizations', [ id: 108641, nci_institute_code: "TX334", name: "Memorial Hermann Katy Hospital", city: "Katy", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8656,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX334",GROUP_DESC:"TX334 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8656,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX334",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX334",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8656,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX334", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9656 ,protection_group_id: -8656, protection_element_id:-8656], primaryKey: false);
      insert('organizations', [ id: 108642, nci_institute_code: "TX335", name: "Memorial Hermann Memorial City Medical Center", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8657,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX335",GROUP_DESC:"TX335 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8657,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX335",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX335",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8657,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX335", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9657 ,protection_group_id: -8657, protection_element_id:-8657], primaryKey: false);
      insert('organizations', [ id: 108643, nci_institute_code: "TX336", name: "Memorial Hermann Southeast Hospital", city: "Houston", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8658,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX336",GROUP_DESC:"TX336 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8658,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX336",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX336",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8658,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX336", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9658 ,protection_group_id: -8658, protection_element_id:-8658], primaryKey: false);
      insert('organizations', [ id: 108644, nci_institute_code: "TX337", name: "Surgical Oncology Associates of South Texas", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8659,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX337",GROUP_DESC:"TX337 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8659,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX337",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX337",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8659,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX337", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9659 ,protection_group_id: -8659, protection_element_id:-8659], primaryKey: false);
      insert('organizations', [ id: 108645, nci_institute_code: "TX338", name: "Richardson Regional Medical Center-Bush", city: "Richardson", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8660,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX338",GROUP_DESC:"TX338 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8660,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX338",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX338",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8660,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX338", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9660 ,protection_group_id: -8660, protection_element_id:-8660], primaryKey: false);
      insert('organizations', [ id: 108646, nci_institute_code: "TX339", name: "South Texas Oncology and Hematology-START Center for Cancer Care", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8661,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX339",GROUP_DESC:"TX339 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8661,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX339",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX339",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8661,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX339", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9661 ,protection_group_id: -8661, protection_element_id:-8661], primaryKey: false);
      insert('organizations', [ id: 108647, nci_institute_code: "TX340", name: "Neurosurgery Institute of South Texas", city: "Corpus Christi", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8662,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX340",GROUP_DESC:"TX340 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8662,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX340",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX340",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8662,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX340", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9662 ,protection_group_id: -8662, protection_element_id:-8662], primaryKey: false);
    }

    void m26() {
        // all26 (25 terms)
      insert('organizations', [ id: 108648, nci_institute_code: "TX341", name: "Texas Oncology- San Marcos", city: "San Marcos", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8663,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX341",GROUP_DESC:"TX341 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8663,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX341",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX341",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8663,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX341", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9663 ,protection_group_id: -8663, protection_element_id:-8663], primaryKey: false);
      insert('organizations', [ id: 108649, nci_institute_code: "TX342", name: "Las Colinas Cancer Center", city: "Irving", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8664,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX342",GROUP_DESC:"TX342 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8664,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX342",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX342",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8664,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX342", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9664 ,protection_group_id: -8664, protection_element_id:-8664], primaryKey: false);
      insert('organizations', [ id: 108650, nci_institute_code: "TX343", name: "Texas Oncology-Austin North", city: "Austin", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8665,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX343",GROUP_DESC:"TX343 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8665,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX343",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX343",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8665,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX343", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9665 ,protection_group_id: -8665, protection_element_id:-8665], primaryKey: false);
      insert('organizations', [ id: 108651, nci_institute_code: "TX344", name: "Texas Oncology-Denton South", city: "Denton", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8666,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX344",GROUP_DESC:"TX344 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8666,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX344",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX344",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8666,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX344", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9666 ,protection_group_id: -8666, protection_element_id:-8666], primaryKey: false);
      insert('organizations', [ id: 108652, nci_institute_code: "TX345", name: "Pediatric Neurosurgery Center of Central Texas", city: "Austin", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8667,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX345",GROUP_DESC:"TX345 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8667,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX345",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX345",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8667,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX345", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9667 ,protection_group_id: -8667, protection_element_id:-8667], primaryKey: false);
      insert('organizations', [ id: 108653, nci_institute_code: "TX346", name: "Austin Pediatric Surgery", city: "Austin", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8668,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX346",GROUP_DESC:"TX346 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8668,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX346",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX346",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8668,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX346", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9668 ,protection_group_id: -8668, protection_element_id:-8668], primaryKey: false);
      insert('organizations', [ id: 108654, nci_institute_code: "TX347", name: "Texas Oncology-Arlington South", city: "Arlington", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8669,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX347",GROUP_DESC:"TX347 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8669,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX347",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX347",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8669,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX347", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9669 ,protection_group_id: -8669, protection_element_id:-8669], primaryKey: false);
      insert('organizations', [ id: 108655, nci_institute_code: "TX348", name: "Radiology Consultants of North Dallas", city: "Dallas", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8670,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX348",GROUP_DESC:"TX348 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8670,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX348",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX348",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8670,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX348", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9670 ,protection_group_id: -8670, protection_element_id:-8670], primaryKey: false);
      insert('organizations', [ id: 108656, nci_institute_code: "TX349", name: "Texas Oncology-Seton Williamson", city: "Round Rock", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8671,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX349",GROUP_DESC:"TX349 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8671,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX349",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX349",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8671,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX349", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9671 ,protection_group_id: -8671, protection_element_id:-8671], primaryKey: false);
      insert('organizations', [ id: 108657, nci_institute_code: "TX350", name: "Valmed Home Health Solutions-Amarillo", city: "Amarillo", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8672,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX350",GROUP_DESC:"TX350 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8672,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX350",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX350",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8672,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX350", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9672 ,protection_group_id: -8672, protection_element_id:-8672], primaryKey: false);
      insert('organizations', [ id: 108658, nci_institute_code: "TX351", name: "Central Texas Neruosurgery For Children", city: "Austin", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8673,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX351",GROUP_DESC:"TX351 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8673,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX351",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX351",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8673,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX351", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9673 ,protection_group_id: -8673, protection_element_id:-8673], primaryKey: false);
      insert('organizations', [ id: 108659, nci_institute_code: "TX352", name: "Texas Oncology Seton Northwest", city: "Austin", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8674,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX352",GROUP_DESC:"TX352 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8674,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX352",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX352",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8674,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX352", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9674 ,protection_group_id: -8674, protection_element_id:-8674], primaryKey: false);
      insert('organizations', [ id: 108660, nci_institute_code: "TX353", name: "Texas Oncology-Cedar Park", city: "Cedar Park", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8675,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX353",GROUP_DESC:"TX353 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8675,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX353",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX353",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8675,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX353", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9675 ,protection_group_id: -8675, protection_element_id:-8675], primaryKey: false);
      insert('organizations', [ id: 108661, nci_institute_code: "TX354", name: "Austin Cancer Centers-Central Austin", city: "Austin", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8676,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX354",GROUP_DESC:"TX354 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8676,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX354",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX354",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8676,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX354", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9676 ,protection_group_id: -8676, protection_element_id:-8676], primaryKey: false);
      insert('organizations', [ id: 108662, nci_institute_code: "TX355", name: "Scott and White College Station Clinic", city: "College Station", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8677,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX355",GROUP_DESC:"TX355 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8677,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX355",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX355",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8677,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX355", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9677 ,protection_group_id: -8677, protection_element_id:-8677], primaryKey: false);
      insert('organizations', [ id: 108663, nci_institute_code: "TX356", name: "Amarillo Pathology Associates Limited", city: "Amarillo", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8678,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX356",GROUP_DESC:"TX356 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8678,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX356",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX356",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8678,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX356", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9678 ,protection_group_id: -8678, protection_element_id:-8678], primaryKey: false);
      insert('organizations', [ id: 108664, nci_institute_code: "TX357", name: "Greehey Children's Cancer Research Institute", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8679,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX357",GROUP_DESC:"TX357 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8679,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX357",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX357",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8679,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX357", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9679 ,protection_group_id: -8679, protection_element_id:-8679], primaryKey: false);
      insert('organizations', [ id: 108665, nci_institute_code: "TX358", name: "Plano Cancer Institute", city: "Plano", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8680,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX358",GROUP_DESC:"TX358 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8680,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX358",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX358",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8680,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX358", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9680 ,protection_group_id: -8680, protection_element_id:-8680], primaryKey: false);
      insert('organizations', [ id: 108666, nci_institute_code: "TX359", name: "High Plains Radiology Association", city: "Amarillo", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8681,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX359",GROUP_DESC:"TX359 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8681,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX359",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX359",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8681,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX359", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9681 ,protection_group_id: -8681, protection_element_id:-8681], primaryKey: false);
      insert('organizations', [ id: 108667, nci_institute_code: "TX360", name: "Carl Ray Darnall Army Medical Center", city: "Fort Hood", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8682,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX360",GROUP_DESC:"TX360 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8682,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX360",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX360",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8682,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX360", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9682 ,protection_group_id: -8682, protection_element_id:-8682], primaryKey: false);
      insert('organizations', [ id: 108668, nci_institute_code: "TX361", name: "Pediatric Blood and Marrow Stem Cell Transplant Clinic", city: "San Antonio", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8683,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX361",GROUP_DESC:"TX361 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8683,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TX361",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TX361",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8683,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TX361", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9683 ,protection_group_id: -8683, protection_element_id:-8683], primaryKey: false);
      insert('organizations', [ id: 108669, nci_institute_code: "UT061", name: "Intermountain Urological Institute", city: "Murray", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8684,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT061",GROUP_DESC:"UT061 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8684,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT061",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT061",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8684,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT061", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9684 ,protection_group_id: -8684, protection_element_id:-8684], primaryKey: false);
      insert('organizations', [ id: 108670, nci_institute_code: "UT062", name: "American Fork General Surgeons", city: "American Fork", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8685,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT062",GROUP_DESC:"UT062 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8685,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT062",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT062",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8685,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT062", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9685 ,protection_group_id: -8685, protection_element_id:-8685], primaryKey: false);
      insert('organizations', [ id: 108671, nci_institute_code: "UT063", name: "Cache Valley Cancer Treatment Center", city: "Logan", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8686,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT063",GROUP_DESC:"UT063 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8686,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT063",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT063",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8686,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT063", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9686 ,protection_group_id: -8686, protection_element_id:-8686], primaryKey: false);
      insert('organizations', [ id: 108672, nci_institute_code: "UT064", name: "Regina Rosenthal MD", city: "Murray", state: "UT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8687,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT064",GROUP_DESC:"UT064 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8687,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.UT064",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.UT064",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8687,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.UT064", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9687 ,protection_group_id: -8687, protection_element_id:-8687], primaryKey: false);
    }

    void m27() {
        // all27 (25 terms)
      insert('organizations', [ id: 108673, nci_institute_code: "VA163", name: "Carilion Clinic Children's Hospital", city: "Roanoke", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8688,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA163",GROUP_DESC:"VA163 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8688,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA163",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA163",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8688,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA163", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9688 ,protection_group_id: -8688, protection_element_id:-8688], primaryKey: false);
      insert('organizations', [ id: 108674, nci_institute_code: "VA164", name: "Inova Pediatric Speciality Center II", city: "Fairfax", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8689,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA164",GROUP_DESC:"VA164 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8689,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA164",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA164",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8689,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA164", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9689 ,protection_group_id: -8689, protection_element_id:-8689], primaryKey: false);
      insert('organizations', [ id: 108675, nci_institute_code: "VA165", name: "Hematology Oncology Associates of Fredericksburg Inc", city: "Fredericksburg", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8690,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA165",GROUP_DESC:"VA165 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8690,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA165",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA165",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8690,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA165", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9690 ,protection_group_id: -8690, protection_element_id:-8690], primaryKey: false);
      insert('organizations', [ id: 108676, nci_institute_code: "VA166", name: "Cancer Center of Virginia", city: "Fredericksburg", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8691,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA166",GROUP_DESC:"VA166 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8691,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA166",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA166",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8691,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA166", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9691 ,protection_group_id: -8691, protection_element_id:-8691], primaryKey: false);
      insert('organizations', [ id: 108677, nci_institute_code: "VA167", name: "Cardiac Vascular and Thoracic Surgery Associates", city: "Falls Church", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8692,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA167",GROUP_DESC:"VA167 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8692,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA167",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA167",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8692,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA167", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9692 ,protection_group_id: -8692, protection_element_id:-8692], primaryKey: false);
      insert('organizations', [ id: 108678, nci_institute_code: "VA168", name: "Fairfax Radiological Consultants PC", city: "Fairfax", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8693,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA168",GROUP_DESC:"VA168 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8693,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA168",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA168",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8693,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA168", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9693 ,protection_group_id: -8693, protection_element_id:-8693], primaryKey: false);
      insert('organizations', [ id: 108679, nci_institute_code: "VA169", name: "Northern Virginia Pulmonary and Critical Care Associates", city: "Annandale", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8694,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA169",GROUP_DESC:"VA169 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8694,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA169",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA169",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8694,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA169", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9694 ,protection_group_id: -8694, protection_element_id:-8694], primaryKey: false);
      insert('organizations', [ id: 108680, nci_institute_code: "VA170", name: "Blue Ridge Cancer Care-Salem", city: "Salem", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8695,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA170",GROUP_DESC:"VA170 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8695,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA170",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA170",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8695,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA170", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9695 ,protection_group_id: -8695, protection_element_id:-8695], primaryKey: false);
      insert('organizations', [ id: 108681, nci_institute_code: "VA171", name: "Breast Care Specialists of Virginia PC", city: "Roanoke", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8696,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA171",GROUP_DESC:"VA171 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8696,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA171",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA171",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8696,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA171", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9696 ,protection_group_id: -8696, protection_element_id:-8696], primaryKey: false);
      insert('organizations', [ id: 108682, nci_institute_code: "VA172", name: "Urology of Virginia PC-Sentara Medical Group", city: "Norfolk", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8697,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA172",GROUP_DESC:"VA172 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8697,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA172",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA172",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8697,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA172", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9697 ,protection_group_id: -8697, protection_element_id:-8697], primaryKey: false);
      insert('organizations', [ id: 108683, nci_institute_code: "VA173", name: "Fairfax-Northern VA Hematology and Oncology PC-Leesburg", city: "Leesburg", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8698,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA173",GROUP_DESC:"VA173 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8698,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA173",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA173",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8698,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA173", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9698 ,protection_group_id: -8698, protection_element_id:-8698], primaryKey: false);
      insert('organizations', [ id: 108684, nci_institute_code: "VA174", name: "Virginia Cardiovascular and Thoracic Surgery Inc", city: "Fredericksburg", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8699,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA174",GROUP_DESC:"VA174 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8699,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA174",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA174",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8699,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA174", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9699 ,protection_group_id: -8699, protection_element_id:-8699], primaryKey: false);
      insert('organizations', [ id: 108685, nci_institute_code: "VA175", name: "Bon Secours Cancer Institute-Virginia Breast Center", city: "Midlothian", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8700,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA175",GROUP_DESC:"VA175 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8700,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA175",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA175",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8700,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA175", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9700 ,protection_group_id: -8700, protection_element_id:-8700], primaryKey: false);
      insert('organizations', [ id: 108686, nci_institute_code: "VA176", name: "Stark Oncology-Riverside Medical Group", city: "Suffork", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8701,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA176",GROUP_DESC:"VA176 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8701,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA176",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA176",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8701,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA176", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9701 ,protection_group_id: -8701, protection_element_id:-8701], primaryKey: false);
      insert('organizations', [ id: 108687, nci_institute_code: "VA177", name: "Pediatric Surgical Associates of Northern Virginia", city: "Annandale", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8702,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA177",GROUP_DESC:"VA177 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8702,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA177",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA177",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8702,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA177", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9702 ,protection_group_id: -8702, protection_element_id:-8702], primaryKey: false);
      insert('organizations', [ id: 108688, nci_institute_code: "VA178", name: "George Mason University-Prince William Campus", city: "Manassas", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8703,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA178",GROUP_DESC:"VA178 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8703,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA178",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA178",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8703,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA178", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9703 ,protection_group_id: -8703, protection_element_id:-8703], primaryKey: false);
      insert('organizations', [ id: 108689, nci_institute_code: "VA179", name: "Johnston Memorial Cancer Center", city: "Abingdon", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8704,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA179",GROUP_DESC:"VA179 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8704,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA179",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA179",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8704,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA179", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9704 ,protection_group_id: -8704, protection_element_id:-8704], primaryKey: false);
      insert('organizations', [ id: 108690, nci_institute_code: "VA180", name: "The Fairfax MRI Center", city: "Fairfax", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8705,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA180",GROUP_DESC:"VA180 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8705,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VA180",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VA180",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8705,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VA180", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9705 ,protection_group_id: -8705, protection_element_id:-8705], primaryKey: false);
      insert('organizations', [ id: 108691, nci_institute_code: "VT030", name: "Mary A. Stanley MD PC", city: "Williston", state: "VT", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8706,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT030",GROUP_DESC:"VT030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8706,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.VT030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.VT030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8706,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.VT030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9706 ,protection_group_id: -8706, protection_element_id:-8706], primaryKey: false);
      insert('organizations', [ id: 108692, nci_institute_code: "WA167", name: "Northwest Heart and Lung Surgical Associates", city: "Spokane", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8707,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA167",GROUP_DESC:"WA167 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8707,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA167",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA167",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8707,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA167", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9707 ,protection_group_id: -8707, protection_element_id:-8707], primaryKey: false);
      insert('organizations', [ id: 108693, nci_institute_code: "WA168", name: "Rockwood Clinic Cancer Treatment Center-Valley", city: "Spokane Valley", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8708,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA168",GROUP_DESC:"WA168 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8708,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA168",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA168",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8708,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA168", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9708 ,protection_group_id: -8708, protection_element_id:-8708], primaryKey: false);
      insert('organizations', [ id: 108694, nci_institute_code: "WA169", name: "Cascadia PCPT LTFU", city: "Bellingham", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8709,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA169",GROUP_DESC:"WA169 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8709,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA169",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA169",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8709,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA169", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9709 ,protection_group_id: -8709, protection_element_id:-8709], primaryKey: false);
      insert('organizations', [ id: 108695, nci_institute_code: "WA170", name: "Women's Cancer Center of Seattle", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8710,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA170",GROUP_DESC:"WA170 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8710,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA170",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA170",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8710,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA170", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9710 ,protection_group_id: -8710, protection_element_id:-8710], primaryKey: false);
      insert('organizations', [ id: 108696, nci_institute_code: "WA171", name: "Partner Oncology", city: "Puyallup", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8711,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA171",GROUP_DESC:"WA171 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8711,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA171",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA171",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8711,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA171", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9711 ,protection_group_id: -8711, protection_element_id:-8711], primaryKey: false);
      insert('organizations', [ id: 108697, nci_institute_code: "WA172", name: "Cancer Care Northwest-Valley", city: "Spokane", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8712,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA172",GROUP_DESC:"WA172 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8712,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA172",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA172",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8712,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA172", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9712 ,protection_group_id: -8712, protection_element_id:-8712], primaryKey: false);
    }

    void m28() {
        // all28 (25 terms)
      insert('organizations', [ id: 108698, nci_institute_code: "WA173", name: "Cancer Care Northwest-Deaconess Health and Education Building", city: "Spokane", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8713,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA173",GROUP_DESC:"WA173 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8713,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA173",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA173",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8713,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA173", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9713 ,protection_group_id: -8713, protection_element_id:-8713], primaryKey: false);
      insert('organizations', [ id: 108699, nci_institute_code: "WA174", name: "Group Health Cooperative Olympia Medical Center", city: "Olympia", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8714,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA174",GROUP_DESC:"WA174 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8714,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA174",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA174",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8714,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA174", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9714 ,protection_group_id: -8714, protection_element_id:-8714], primaryKey: false);
      insert('organizations', [ id: 108700, nci_institute_code: "WA175", name: "Saint Joseph Cardiothoracic Surgeons", city: "Tacoma", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8715,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA175",GROUP_DESC:"WA175 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8715,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA175",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA175",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8715,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA175", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9715 ,protection_group_id: -8715, protection_element_id:-8715], primaryKey: false);
      insert('organizations', [ id: 108701, nci_institute_code: "WA176", name: "Evergreen Hematology and Oncology PS", city: "Spokane", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8716,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA176",GROUP_DESC:"WA176 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8716,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA176",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA176",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8716,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA176", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9716 ,protection_group_id: -8716, protection_element_id:-8716], primaryKey: false);
      insert('organizations', [ id: 108702, nci_institute_code: "WA177", name: "Seattle CyberKnife Center", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8717,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA177",GROUP_DESC:"WA177 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8717,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA177",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA177",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8717,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA177", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9717 ,protection_group_id: -8717, protection_element_id:-8717], primaryKey: false);
      insert('organizations', [ id: 108703, nci_institute_code: "WA178", name: "Seattle Neuroscience Institute", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8718,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA178",GROUP_DESC:"WA178 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8718,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA178",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA178",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8718,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA178", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9718 ,protection_group_id: -8718, protection_element_id:-8718], primaryKey: false);
      insert('organizations', [ id: 108704, nci_institute_code: "WA179", name: "Legacy Salmon Creek Hospital", city: "Vancouver", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8719,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA179",GROUP_DESC:"WA179 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8719,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA179",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA179",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8719,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA179", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9719 ,protection_group_id: -8719, protection_element_id:-8719], primaryKey: false);
      insert('organizations', [ id: 108705, nci_institute_code: "WA180", name: "Inland Neurosurgery and Spine Associates", city: "Spokane", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8720,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA180",GROUP_DESC:"WA180 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8720,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA180",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA180",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8720,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA180", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9720 ,protection_group_id: -8720, protection_element_id:-8720], primaryKey: false);
      insert('organizations', [ id: 108706, nci_institute_code: "WA181", name: "Harrison Poulsbo Hematology and Onocology", city: "Poulsbo", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8721,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA181",GROUP_DESC:"WA181 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8721,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA181",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA181",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8721,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA181", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9721 ,protection_group_id: -8721, protection_element_id:-8721], primaryKey: false);
      insert('organizations', [ id: 108707, nci_institute_code: "WA182", name: "Seattle Prostate Cancer Center", city: "Seattle", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8722,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA182",GROUP_DESC:"WA182 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8722,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA182",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA182",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8722,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA182", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9722 ,protection_group_id: -8722, protection_element_id:-8722], primaryKey: false);
      insert('organizations', [ id: 108708, nci_institute_code: "WA183", name: "Franciscan Research Center-Northwest Medical Plaza", city: "Tacoma", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8723,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA183",GROUP_DESC:"WA183 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8723,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA183",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA183",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8723,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA183", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9723 ,protection_group_id: -8723, protection_element_id:-8723], primaryKey: false);
      insert('organizations', [ id: 108709, nci_institute_code: "WA184", name: "Cancer Care Center at Island Hospital", city: "Anacortes", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8724,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA184",GROUP_DESC:"WA184 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8724,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA184",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA184",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8724,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA184", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9724 ,protection_group_id: -8724, protection_element_id:-8724], primaryKey: false);
      insert('organizations', [ id: 108710, nci_institute_code: "WA185", name: "Overlake Internal Medicine Associates", city: "Bellevue", state: "WA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8725,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA185",GROUP_DESC:"WA185 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8725,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WA185",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WA185",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8725,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WA185", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9725 ,protection_group_id: -8725, protection_element_id:-8725], primaryKey: false);
      insert('organizations', [ id: 108711, nci_institute_code: "WI164", name: "Michael D Wachtel Cancer Center", city: "Oshkosh", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8726,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI164",GROUP_DESC:"WI164 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8726,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI164",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI164",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8726,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI164", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9726 ,protection_group_id: -8726, protection_element_id:-8726], primaryKey: false);
      insert('organizations', [ id: 108712, nci_institute_code: "WI165", name: "Vince Lombardi Cancer Clinic-Marinette", city: "Marinette", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8727,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI165",GROUP_DESC:"WI165 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8727,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI165",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI165",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8727,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI165", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9727 ,protection_group_id: -8727, protection_element_id:-8727], primaryKey: false);
      insert('organizations', [ id: 108713, nci_institute_code: "WI166", name: "Good Samaritan Health Center", city: "Merrill", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8728,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI166",GROUP_DESC:"WI166 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8728,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI166",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI166",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8728,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI166", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9728 ,protection_group_id: -8728, protection_element_id:-8728], primaryKey: false);
      insert('organizations', [ id: 108714, nci_institute_code: "WI167", name: "Howard Young Medical Center", city: "Woodruff", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8729,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI167",GROUP_DESC:"WI167 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8729,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI167",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI167",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8729,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI167", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9729 ,protection_group_id: -8729, protection_element_id:-8729], primaryKey: false);
      insert('organizations', [ id: 108715, nci_institute_code: "WI168", name: "Lakeview Medical Center-Marshfield Clinic", city: "Rice Lake", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8730,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI168",GROUP_DESC:"WI168 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8730,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI168",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI168",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8730,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI168", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9730 ,protection_group_id: -8730, protection_element_id:-8730], primaryKey: false);
      insert('organizations', [ id: 108716, nci_institute_code: "WI169", name: "Wheaton Franciscan Cancer Care-Wauwatosa", city: "Wauwatosa", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8731,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI169",GROUP_DESC:"WI169 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8731,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI169",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI169",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8731,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI169", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9731 ,protection_group_id: -8731, protection_element_id:-8731], primaryKey: false);
      insert('organizations', [ id: 108717, nci_institute_code: "WI170", name: "Vince Lombardi Cancer Clinic-Oconomowoc", city: "Oconomowoc", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8732,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI170",GROUP_DESC:"WI170 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8732,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI170",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI170",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8732,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI170", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9732 ,protection_group_id: -8732, protection_element_id:-8732], primaryKey: false);
      insert('organizations', [ id: 108718, nci_institute_code: "WI171", name: "Fox Valley Hematology and Oncology SC", city: "Appleton", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8733,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI171",GROUP_DESC:"WI171 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8733,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI171",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI171",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8733,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI171", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9733 ,protection_group_id: -8733, protection_element_id:-8733], primaryKey: false);
      insert('organizations', [ id: 108719, nci_institute_code: "WI172", name: "Fox Valley Hematology and Oncology", city: "Oshkosh", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8734,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI172",GROUP_DESC:"WI172 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8734,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI172",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI172",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8734,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI172", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9734 ,protection_group_id: -8734, protection_element_id:-8734], primaryKey: false);
      insert('organizations', [ id: 108720, nci_institute_code: "WI173", name: "Sursum Corda SC", city: "Green Bay", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8735,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI173",GROUP_DESC:"WI173 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8735,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI173",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI173",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8735,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI173", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9735 ,protection_group_id: -8735, protection_element_id:-8735], primaryKey: false);
      insert('organizations', [ id: 108721, nci_institute_code: "WI174", name: "Surgical Associates SC", city: "Wausau", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8736,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI174",GROUP_DESC:"WI174 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8736,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI174",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI174",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8736,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI174", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9736 ,protection_group_id: -8736, protection_element_id:-8736], primaryKey: false);
      insert('organizations', [ id: 108722, nci_institute_code: "WI175", name: "WiCell Research Institute", city: "Madison", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8737,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI175",GROUP_DESC:"WI175 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8737,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI175",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI175",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8737,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI175", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9737 ,protection_group_id: -8737, protection_element_id:-8737], primaryKey: false);
    }

    void m29() {
        // all29 (8 terms)
      insert('organizations', [ id: 108723, nci_institute_code: "WI176", name: "Marshfield Clinic Cancer Care at Saint Michael's Hospital", city: "Stevens Point", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8738,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI176",GROUP_DESC:"WI176 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8738,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI176",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI176",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8738,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI176", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9738 ,protection_group_id: -8738, protection_element_id:-8738], primaryKey: false);
      insert('organizations', [ id: 108724, nci_institute_code: "WI177", name: "Aurora Thoracic Surgery", city: "Milwaukee", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8739,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI177",GROUP_DESC:"WI177 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8739,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI177",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI177",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8739,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI177", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9739 ,protection_group_id: -8739, protection_element_id:-8739], primaryKey: false);
      insert('organizations', [ id: 108725, nci_institute_code: "WI178", name: "Wisconsin Institute for Medical Research", city: "Madison", state: "WI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8740,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI178",GROUP_DESC:"WI178 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8740,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WI178",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WI178",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8740,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WI178", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9740 ,protection_group_id: -8740, protection_element_id:-8740], primaryKey: false);
      insert('organizations', [ id: 108726, nci_institute_code: "WV050", name: "Saint Mary's Cardiovascular and Thoracic Surgeons", city: "Huntington", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8741,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV050",GROUP_DESC:"WV050 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8741,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV050",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV050",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8741,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV050", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9741 ,protection_group_id: -8741, protection_element_id:-8741], primaryKey: false);
      insert('organizations', [ id: 108727, nci_institute_code: "WV051", name: "University of Charleston", city: "Charleston", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8742,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV051",GROUP_DESC:"WV051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8742,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8742,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9742 ,protection_group_id: -8742, protection_element_id:-8742], primaryKey: false);
      insert('organizations', [ id: 108728, nci_institute_code: "WV052", name: "Mary Babb Randolph Cancer Center at West Virginia University", city: "Morgantown", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8743,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV052",GROUP_DESC:"WV052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8743,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8743,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9743 ,protection_group_id: -8743, protection_element_id:-8743], primaryKey: false);
      insert('organizations', [ id: 108729, nci_institute_code: "WV053", name: "The Schiffler Cancer Center of Wheeling Hospital", city: "Wheeling", state: "WV", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8744,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV053",GROUP_DESC:"WV053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8744,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WV053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WV053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8744,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WV053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9744 ,protection_group_id: -8744, protection_element_id:-8744], primaryKey: false);
      insert('organizations', [ id: 108730, nci_institute_code: "WY016", name: "Rocky Mountain Oncology", city: "Casper", state: "WY", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -8745,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WY016",GROUP_DESC:"WY016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -8745,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.WY016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.WY016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -8745,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.WY016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:9745 ,protection_group_id: -8745, protection_element_id:-8745], primaryKey: false);
    }

    void down() {
        execute("delete from csm_pg_pe where pg_pe_id >= 1015 and  pg_pe_id <= 8013 ");
        execute("delete from CSM_PROTECTION_GROUP where protection_group_id  <= -15 ");
        execute("delete from csm_protection_element where protection_element_id <= -15 ");
        execute("delete from csm_group where group_id <= -15 ");
        execute("DELETE from organizations where id >= 100000 and id < 110000")
    }
}
