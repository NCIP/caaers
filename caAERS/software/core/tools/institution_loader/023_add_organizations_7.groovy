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
        m40()
    }

    void m0() {
        // all0 (25 terms)
      insert('organizations', [ id: 105998, nci_institute_code: "OH220", name: "Mount Carmel Health Center West", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6013,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH220",GROUP_DESC:"OH220 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6013,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH220",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH220",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6013,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH220", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7013 ,protection_group_id: -6013, protection_element_id:-6013], primaryKey: false);
      insert('organizations', [ id: 105999, nci_institute_code: "OH221", name: "Upper Valley Medical Center", city: "Troy", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6014,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH221",GROUP_DESC:"OH221 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6014,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH221",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH221",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6014,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH221", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7014 ,protection_group_id: -6014, protection_element_id:-6014], primaryKey: false);
      insert('organizations', [ id: 106000, nci_institute_code: "OH240", name: "Cleveland Metropolitan General Hospital", city: "Cleveland", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6015,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH240",GROUP_DESC:"OH240 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6015,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH240",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH240",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6015,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH240", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7015 ,protection_group_id: -6015, protection_element_id:-6015], primaryKey: false);
      insert('organizations', [ id: 106001, nci_institute_code: "OH241", name: "Tri-County Hematology Oncology Association Incorporated", city: "Masillon", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6016,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH241",GROUP_DESC:"OH241 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6016,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH241",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH241",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6016,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH241", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7016 ,protection_group_id: -6016, protection_element_id:-6016], primaryKey: false);
      insert('organizations', [ id: 106002, nci_institute_code: "OH242", name: "Lutheran Medical Center (OH)", city: "Cleveland", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6017,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH242",GROUP_DESC:"OH242 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6017,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH242",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH242",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6017,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH242", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7017 ,protection_group_id: -6017, protection_element_id:-6017], primaryKey: false);
      insert('organizations', [ id: 106003, nci_institute_code: "OH243", name: "Toledo Radiation Oncology, Incorporated", city: "Toledo", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6018,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH243",GROUP_DESC:"OH243 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6018,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH243",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH243",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6018,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH243", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7018 ,protection_group_id: -6018, protection_element_id:-6018], primaryKey: false);
      insert('organizations', [ id: 106004, nci_institute_code: "OH245", name: "Southern Ohio Medical Center", city: "Portsmouth", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6019,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH245",GROUP_DESC:"OH245 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6019,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH245",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH245",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6019,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH245", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7019 ,protection_group_id: -6019, protection_element_id:-6019], primaryKey: false);
      insert('organizations', [ id: 106005, nci_institute_code: "OH246", name: "Grant Medical Center", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6020,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH246",GROUP_DESC:"OH246 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6020,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH246",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH246",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6020,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH246", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7020 ,protection_group_id: -6020, protection_element_id:-6020], primaryKey: false);
      insert('organizations', [ id: 106006, nci_institute_code: "OH248", name: "UHHS-Westlake Medical Center", city: "Westlake", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6021,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH248",GROUP_DESC:"OH248 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6021,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH248",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH248",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6021,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH248", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7021 ,protection_group_id: -6021, protection_element_id:-6021], primaryKey: false);
      insert('organizations', [ id: 106007, nci_institute_code: "OH249", name: "Dayton Colon and Rectal Center, Inc.", city: "Dayton", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6022,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH249",GROUP_DESC:"OH249 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6022,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH249",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH249",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6022,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH249", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7022 ,protection_group_id: -6022, protection_element_id:-6022], primaryKey: false);
      insert('organizations', [ id: 106008, nci_institute_code: "OH250", name: "Parma Community General Hospital", city: "Parma", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6023,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH250",GROUP_DESC:"OH250 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6023,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH250",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH250",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6023,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH250", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7023 ,protection_group_id: -6023, protection_element_id:-6023], primaryKey: false);
      insert('organizations', [ id: 106009, nci_institute_code: "OH251", name: "Parma Community General Hospital", city: "Parma", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6024,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH251",GROUP_DESC:"OH251 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6024,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH251",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH251",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6024,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH251", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7024 ,protection_group_id: -6024, protection_element_id:-6024], primaryKey: false);
      insert('organizations', [ id: 106010, nci_institute_code: "OH252", name: "Hematology and Oncology of Dayton, Inc.", city: "Dayton", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6025,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH252",GROUP_DESC:"OH252 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6025,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH252",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH252",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6025,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH252", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7025 ,protection_group_id: -6025, protection_element_id:-6025], primaryKey: false);
      insert('organizations', [ id: 106011, nci_institute_code: "OH253", name: "South Dayton Urological Associates", city: "Kettering", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6026,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH253",GROUP_DESC:"OH253 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6026,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH253",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH253",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6026,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH253", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7026 ,protection_group_id: -6026, protection_element_id:-6026], primaryKey: false);
      insert('organizations', [ id: 106012, nci_institute_code: "OH254", name: "Tri County Hematology and Oncology Associates", city: "Canton", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6027,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH254",GROUP_DESC:"OH254 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6027,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH254",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH254",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6027,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH254", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7027 ,protection_group_id: -6027, protection_element_id:-6027], primaryKey: false);
      insert('organizations', [ id: 106013, nci_institute_code: "OH255", name: "Cincinnati Hematology Oncology, Inc", city: "Cincinnati", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6028,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH255",GROUP_DESC:"OH255 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6028,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH255",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH255",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6028,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH255", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7028 ,protection_group_id: -6028, protection_element_id:-6028], primaryKey: false);
      insert('organizations', [ id: 106014, nci_institute_code: "OH256", name: "Hematology Oncology Consultants, Inc", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6029,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH256",GROUP_DESC:"OH256 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6029,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH256",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH256",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6029,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH256", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7029 ,protection_group_id: -6029, protection_element_id:-6029], primaryKey: false);
      insert('organizations', [ id: 106015, nci_institute_code: "OH257", name: "Oncology and Hematology Care Inc - Blue Ash", city: "Cincinnati", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6030,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH257",GROUP_DESC:"OH257 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6030,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH257",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH257",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6030,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH257", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7030 ,protection_group_id: -6030, protection_element_id:-6030], primaryKey: false);
      insert('organizations', [ id: 106016, nci_institute_code: "OH258", name: "Hipple Cancer Research Center", city: "Dayton", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6031,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH258",GROUP_DESC:"OH258 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6031,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH258",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH258",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6031,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH258", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7031 ,protection_group_id: -6031, protection_element_id:-6031], primaryKey: false);
      insert('organizations', [ id: 106017, nci_institute_code: "OH259", name: "Oncology Hematology Care Inc - Mount Auburn/Midtown", city: "Cincinnati", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6032,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH259",GROUP_DESC:"OH259 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6032,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH259",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH259",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6032,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH259", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7032 ,protection_group_id: -6032, protection_element_id:-6032], primaryKey: false);
      insert('organizations', [ id: 106018, nci_institute_code: "OH260", name: "Oncology Hematology Care Inc - Kenwood", city: "Cincinnati", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6033,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH260",GROUP_DESC:"OH260 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6033,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH260",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH260",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6033,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH260", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7033 ,protection_group_id: -6033, protection_element_id:-6033], primaryKey: false);
      insert('organizations', [ id: 106019, nci_institute_code: "OH261", name: "Surgical Oncology Associates of Columbus", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6034,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH261",GROUP_DESC:"OH261 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6034,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH261",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH261",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6034,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH261", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7034 ,protection_group_id: -6034, protection_element_id:-6034], primaryKey: false);
      insert('organizations', [ id: 106020, nci_institute_code: "OH262", name: "Kaiser Permanente Ohio", city: "Beachwood", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6035,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH262",GROUP_DESC:"OH262 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6035,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH262",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH262",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6035,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH262", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7035 ,protection_group_id: -6035, protection_element_id:-6035], primaryKey: false);
      insert('organizations', [ id: 106021, nci_institute_code: "OH263", name: "Robinson Radiation Oncology", city: "Ravenna", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6036,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH263",GROUP_DESC:"OH263 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6036,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH263",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH263",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6036,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH263", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7036 ,protection_group_id: -6036, protection_element_id:-6036], primaryKey: false);
      insert('organizations', [ id: 106022, nci_institute_code: "OH264", name: "Crystal Plastic Surgeons, Inc.", city: "Akron", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6037,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH264",GROUP_DESC:"OH264 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6037,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH264",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH264",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6037,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH264", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7037 ,protection_group_id: -6037, protection_element_id:-6037], primaryKey: false);
    }

    void m1() {
        // all1 (25 terms)
      insert('organizations', [ id: 106023, nci_institute_code: "OH265", name: "Summa Health System", city: "Akron", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6038,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH265",GROUP_DESC:"OH265 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6038,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH265",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH265",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6038,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH265", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7038 ,protection_group_id: -6038, protection_element_id:-6038], primaryKey: false);
      insert('organizations', [ id: 106024, nci_institute_code: "OH266", name: "Medical Park Cancer Institute", city: "Lima", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6039,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH266",GROUP_DESC:"OH266 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6039,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH266",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH266",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6039,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH266", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7039 ,protection_group_id: -6039, protection_element_id:-6039], primaryKey: false);
      insert('organizations', [ id: 106025, nci_institute_code: "OH267", name: "Clinical Hematology\\Oncology Associates, Inc.", city: "Akron", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6040,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH267",GROUP_DESC:"OH267 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6040,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH267",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH267",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6040,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH267", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7040 ,protection_group_id: -6040, protection_element_id:-6040], primaryKey: false);
      insert('organizations', [ id: 106026, nci_institute_code: "OH268", name: "Columbus Oncology & Hematology Associates Inc", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6041,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH268",GROUP_DESC:"OH268 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6041,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH268",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH268",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6041,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH268", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7041 ,protection_group_id: -6041, protection_element_id:-6041], primaryKey: false);
      insert('organizations', [ id: 106027, nci_institute_code: "OH269", name: "Blood and Cancer Center, Inc", city: "Canfield", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6042,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH269",GROUP_DESC:"OH269 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6042,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH269",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH269",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6042,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH269", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7042 ,protection_group_id: -6042, protection_element_id:-6042], primaryKey: false);
      insert('organizations', [ id: 106028, nci_institute_code: "OH270", name: "Medical Oncology Hematology Associates Inc", city: "Dayton", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6043,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH270",GROUP_DESC:"OH270 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6043,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH270",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH270",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6043,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH270", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7043 ,protection_group_id: -6043, protection_element_id:-6043], primaryKey: false);
      insert('organizations', [ id: 106029, nci_institute_code: "OH271", name: "Gem City Surgical Associates, Incorporated", city: "Dayton", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6044,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH271",GROUP_DESC:"OH271 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6044,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH271",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH271",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6044,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH271", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7044 ,protection_group_id: -6044, protection_element_id:-6044], primaryKey: false);
      insert('organizations', [ id: 106030, nci_institute_code: "OH272", name: "Midwest Surgeons of Dayton, Incorporated", city: "Dayton", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6045,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH272",GROUP_DESC:"OH272 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6045,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH272",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH272",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6045,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH272", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7045 ,protection_group_id: -6045, protection_element_id:-6045], primaryKey: false);
      insert('organizations', [ id: 106031, nci_institute_code: "OH273", name: "Alliance Gynecological Oncology", city: "Dayton", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6046,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH273",GROUP_DESC:"OH273 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6046,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH273",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH273",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6046,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH273", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7046 ,protection_group_id: -6046, protection_element_id:-6046], primaryKey: false);
      insert('organizations', [ id: 106032, nci_institute_code: "OH274", name: "UHHS-Chagrin Highlands Medical Center", city: "Orange Village", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6047,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH274",GROUP_DESC:"OH274 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6047,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH274",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH274",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6047,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH274", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7047 ,protection_group_id: -6047, protection_element_id:-6047], primaryKey: false);
      insert('organizations', [ id: 106033, nci_institute_code: "OH275", name: "Ohio Cancer Specialists", city: "Mansfield", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6048,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH275",GROUP_DESC:"OH275 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6048,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH275",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH275",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6048,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH275", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7048 ,protection_group_id: -6048, protection_element_id:-6048], primaryKey: false);
      insert('organizations', [ id: 106034, nci_institute_code: "OH276", name: "Mid-Ohio Oncology and Hematology", city: "Westerville", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6049,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH276",GROUP_DESC:"OH276 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6049,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH276",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH276",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6049,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH276", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7049 ,protection_group_id: -6049, protection_element_id:-6049], primaryKey: false);
      insert('organizations', [ id: 106035, nci_institute_code: "OH277", name: "Gem City Urologists, Inc.", city: "Dayton", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6050,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH277",GROUP_DESC:"OH277 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6050,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH277",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH277",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6050,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH277", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7050 ,protection_group_id: -6050, protection_element_id:-6050], primaryKey: false);
      insert('organizations', [ id: 106036, nci_institute_code: "OH280", name: "Woodside Medical Group", city: "Mansfield", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6051,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH280",GROUP_DESC:"OH280 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6051,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH280",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH280",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6051,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH280", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7051 ,protection_group_id: -6051, protection_element_id:-6051], primaryKey: false);
      insert('organizations', [ id: 106037, nci_institute_code: "OH281", name: "Henry County Hospital", city: "Napolean", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6052,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH281",GROUP_DESC:"OH281 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6052,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH281",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH281",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6052,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH281", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7052 ,protection_group_id: -6052, protection_element_id:-6052], primaryKey: false);
      insert('organizations', [ id: 106038, nci_institute_code: "OH282", name: "Northwest Ohio Oncology Center", city: "Maumee", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6053,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH282",GROUP_DESC:"OH282 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6053,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH282",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH282",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6053,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH282", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7053 ,protection_group_id: -6053, protection_element_id:-6053], primaryKey: false);
      insert('organizations', [ id: 106039, nci_institute_code: "OH283", name: "American Kidney Stone Management Clinical Research Corp.", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6054,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH283",GROUP_DESC:"OH283 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6054,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH283",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH283",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6054,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH283", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7054 ,protection_group_id: -6054, protection_element_id:-6054], primaryKey: false);
      insert('organizations', [ id: 106040, nci_institute_code: "OH284", name: "Ireland Cancer Center at Community Health Partners", city: "Elyria", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6055,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH284",GROUP_DESC:"OH284 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6055,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH284",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH284",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6055,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH284", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7055 ,protection_group_id: -6055, protection_element_id:-6055], primaryKey: false);
      insert('organizations', [ id: 106041, nci_institute_code: "OH285", name: "Chillicothe Surgical Associates", city: "Chillicothe", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6056,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH285",GROUP_DESC:"OH285 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6056,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH285",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH285",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6056,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH285", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7056 ,protection_group_id: -6056, protection_element_id:-6056], primaryKey: false);
      insert('organizations', [ id: 106042, nci_institute_code: "OH286", name: "Canton Urology Associates", city: "Canton", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6057,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH286",GROUP_DESC:"OH286 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6057,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH286",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH286",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6057,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH286", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7057 ,protection_group_id: -6057, protection_element_id:-6057], primaryKey: false);
      insert('organizations', [ id: 106043, nci_institute_code: "OH287", name: "First Dayton Cancer Care", city: "Kettering", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6058,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH287",GROUP_DESC:"OH287 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6058,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH287",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH287",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6058,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH287", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7058 ,protection_group_id: -6058, protection_element_id:-6058], primaryKey: false);
      insert('organizations', [ id: 106044, nci_institute_code: "OH288", name: "Gynecologic Oncology and Pelvic Surgery Associates, Inc.", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6059,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH288",GROUP_DESC:"OH288 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6059,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH288",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH288",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6059,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH288", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7059 ,protection_group_id: -6059, protection_element_id:-6059], primaryKey: false);
      insert('organizations', [ id: 106045, nci_institute_code: "OH289", name: "Signal Point Hematology Oncology, Inc.", city: "Middletown", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6060,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH289",GROUP_DESC:"OH289 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6060,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH289",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH289",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6060,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH289", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7060 ,protection_group_id: -6060, protection_element_id:-6060], primaryKey: false);
      insert('organizations', [ id: 106046, nci_institute_code: "OH290", name: "Oncology Partners Network", city: "Cincinnati", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6061,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH290",GROUP_DESC:"OH290 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6061,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH290",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH290",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6061,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH290", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7061 ,protection_group_id: -6061, protection_element_id:-6061], primaryKey: false);
      insert('organizations', [ id: 106047, nci_institute_code: "OH291", name: "Saint Joseph Cancer Center", city: "Warren", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6062,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH291",GROUP_DESC:"OH291 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6062,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH291",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH291",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6062,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH291", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7062 ,protection_group_id: -6062, protection_element_id:-6062], primaryKey: false);
    }

    void m2() {
        // all2 (25 terms)
      insert('organizations', [ id: 106048, nci_institute_code: "OH292", name: "University Hospitals/Ireland Cancer Center", city: "Cleveland", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6063,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH292",GROUP_DESC:"OH292 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6063,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH292",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH292",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6063,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH292", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7063 ,protection_group_id: -6063, protection_element_id:-6063], primaryKey: false);
      insert('organizations', [ id: 106049, nci_institute_code: "OH293", name: "Trinity's Tony Teramana Cancer Center", city: "Steubenville", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6064,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH293",GROUP_DESC:"OH293 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6064,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH293",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH293",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6064,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH293", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7064 ,protection_group_id: -6064, protection_element_id:-6064], primaryKey: false);
      insert('organizations', [ id: 106050, nci_institute_code: "OH294", name: "Canton General Surgery Associates", city: "Canton", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6065,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH294",GROUP_DESC:"OH294 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6065,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH294",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH294",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6065,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH294", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7065 ,protection_group_id: -6065, protection_element_id:-6065], primaryKey: false);
      insert('organizations', [ id: 106051, nci_institute_code: "OH295", name: "Wood County Oncology Center", city: "Bowling Green", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6066,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH295",GROUP_DESC:"OH295 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6066,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH295",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH295",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6066,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH295", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7066 ,protection_group_id: -6066, protection_element_id:-6066], primaryKey: false);
      insert('organizations', [ id: 106052, nci_institute_code: "OH296", name: "Surgical Oncology, Inc.", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6067,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH296",GROUP_DESC:"OH296 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6067,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH296",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH296",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6067,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH296", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7067 ,protection_group_id: -6067, protection_element_id:-6067], primaryKey: false);
      insert('organizations', [ id: 106053, nci_institute_code: "OH297", name: "Colon & Rectal Surgery, Inc.", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6068,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH297",GROUP_DESC:"OH297 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6068,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH297",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH297",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6068,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH297", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7068 ,protection_group_id: -6068, protection_element_id:-6068], primaryKey: false);
      insert('organizations', [ id: 106054, nci_institute_code: "OH298", name: "Summit Oncology Associates", city: "Akron", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6069,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH298",GROUP_DESC:"OH298 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6069,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH298",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH298",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6069,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH298", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7069 ,protection_group_id: -6069, protection_element_id:-6069], primaryKey: false);
      insert('organizations', [ id: 106055, nci_institute_code: "OH299", name: "Newark Radiation Oncology", city: "Newark", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6070,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH299",GROUP_DESC:"OH299 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6070,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH299",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH299",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6070,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH299", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7070 ,protection_group_id: -6070, protection_element_id:-6070], primaryKey: false);
      insert('organizations', [ id: 106056, nci_institute_code: "OH300", name: "Regional Medical Associates", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6071,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH300",GROUP_DESC:"OH300 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6071,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH300",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH300",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6071,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH300", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7071 ,protection_group_id: -6071, protection_element_id:-6071], primaryKey: false);
      insert('organizations', [ id: 106057, nci_institute_code: "OH301", name: "Medical Oncology Associates", city: "Lyndhurst", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6072,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH301",GROUP_DESC:"OH301 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6072,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH301",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH301",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6072,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH301", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7072 ,protection_group_id: -6072, protection_element_id:-6072], primaryKey: false);
      insert('organizations', [ id: 106058, nci_institute_code: "OH302", name: "Northwest Surgical Specialist", city: "Maumee", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6073,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH302",GROUP_DESC:"OH302 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6073,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH302",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH302",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6073,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH302", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7073 ,protection_group_id: -6073, protection_element_id:-6073], primaryKey: false);
      insert('organizations', [ id: 106059, nci_institute_code: "OH303", name: "Kenton Oncology", city: "Kenton", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6074,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH303",GROUP_DESC:"OH303 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6074,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH303",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH303",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6074,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH303", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7074 ,protection_group_id: -6074, protection_element_id:-6074], primaryKey: false);
      insert('organizations', [ id: 106060, nci_institute_code: "OH304", name: "Mayfield Clinic", city: "Cincinnatti", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6075,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH304",GROUP_DESC:"OH304 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6075,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH304",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH304",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6075,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH304", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7075 ,protection_group_id: -6075, protection_element_id:-6075], primaryKey: false);
      insert('organizations', [ id: 106061, nci_institute_code: "OH305", name: "Cleveland Clinic Wooster Specialty Center", city: "Wooster", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6076,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH305",GROUP_DESC:"OH305 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6076,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH305",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH305",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6076,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH305", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7076 ,protection_group_id: -6076, protection_element_id:-6076], primaryKey: false);
      insert('organizations', [ id: 106062, nci_institute_code: "OH306", name: "Cleveland Clinic Cancer Center", city: "Independence", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6077,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH306",GROUP_DESC:"OH306 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6077,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH306",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH306",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6077,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH306", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7077 ,protection_group_id: -6077, protection_element_id:-6077], primaryKey: false);
      insert('organizations', [ id: 106063, nci_institute_code: "OH307", name: "Surgical Partners", city: "Toledo", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6078,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH307",GROUP_DESC:"OH307 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6078,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH307",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH307",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6078,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH307", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7078 ,protection_group_id: -6078, protection_element_id:-6078], primaryKey: false);
      insert('organizations', [ id: 106064, nci_institute_code: "OH308", name: "The Boardman Cancer Center", city: "Boardman", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6079,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH308",GROUP_DESC:"OH308 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6079,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH308",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH308",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6079,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH308", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7079 ,protection_group_id: -6079, protection_element_id:-6079], primaryKey: false);
      insert('organizations', [ id: 106065, nci_institute_code: "OH309", name: "Bayview Oncology Associates", city: "Oregon", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6080,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH309",GROUP_DESC:"OH309 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6080,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH309",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH309",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6080,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH309", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7080 ,protection_group_id: -6080, protection_element_id:-6080], primaryKey: false);
      insert('organizations', [ id: 106066, nci_institute_code: "OH310", name: "Hematology and Oncology of Lima, Inc", city: "Lima", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6081,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH310",GROUP_DESC:"OH310 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6081,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH310",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH310",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6081,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH310", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7081 ,protection_group_id: -6081, protection_element_id:-6081], primaryKey: false);
      insert('organizations', [ id: 106067, nci_institute_code: "OH312", name: "University Urologists of Cleveland", city: "Cleveland", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6082,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH312",GROUP_DESC:"OH312 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6082,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH312",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH312",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6082,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH312", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7082 ,protection_group_id: -6082, protection_element_id:-6082], primaryKey: false);
      insert('organizations', [ id: 106068, nci_institute_code: "OH313", name: "Hematology and Oncology of Dayton, Inc.", city: "Kettering", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6083,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH313",GROUP_DESC:"OH313 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6083,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH313",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH313",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6083,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH313", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7083 ,protection_group_id: -6083, protection_element_id:-6083], primaryKey: false);
      insert('organizations', [ id: 106069, nci_institute_code: "OH314", name: "North Coast Cancer Center", city: "Sandusky", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6084,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH314",GROUP_DESC:"OH314 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6084,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH314",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH314",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6084,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH314", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7084 ,protection_group_id: -6084, protection_element_id:-6084], primaryKey: false);
      insert('organizations', [ id: 106070, nci_institute_code: "OH315", name: "Alliance Cancer Center", city: "Alliance", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6085,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH315",GROUP_DESC:"OH315 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6085,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH315",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH315",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6085,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH315", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7085 ,protection_group_id: -6085, protection_element_id:-6085], primaryKey: false);
      insert('organizations', [ id: 106071, nci_institute_code: "OH316", name: "Cardiothoracic & Vascular Surgical Specialist, Inc.", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6086,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH316",GROUP_DESC:"OH316 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6086,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH316",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH316",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6086,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH316", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7086 ,protection_group_id: -6086, protection_element_id:-6086], primaryKey: false);
      insert('organizations', [ id: 106072, nci_institute_code: "OH317", name: "Cancer Center of Northwest Ohio", city: "Findlay", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6087,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH317",GROUP_DESC:"OH317 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6087,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH317",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH317",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6087,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH317", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7087 ,protection_group_id: -6087, protection_element_id:-6087], primaryKey: false);
    }

    void m3() {
        // all3 (25 terms)
      insert('organizations', [ id: 106073, nci_institute_code: "OH318", name: "ProScan Imaging Midtown - Cincinnati Imaging Leasing Co., Ltd.", city: "Cincinnati", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6088,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH318",GROUP_DESC:"OH318 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6088,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH318",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH318",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6088,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH318", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7088 ,protection_group_id: -6088, protection_element_id:-6088], primaryKey: false);
      insert('organizations', [ id: 106074, nci_institute_code: "OH319", name: "ProScan Tylersville, LLC", city: "West Chester", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6089,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH319",GROUP_DESC:"OH319 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6089,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH319",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH319",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6089,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH319", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7089 ,protection_group_id: -6089, protection_element_id:-6089], primaryKey: false);
      insert('organizations', [ id: 106075, nci_institute_code: "OH320", name: "Cleveland Clinic Cancer Center-Strongsville", city: "Strongsville", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6090,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH320",GROUP_DESC:"OH320 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6090,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH320",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH320",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6090,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH320", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7090 ,protection_group_id: -6090, protection_element_id:-6090], primaryKey: false);
      insert('organizations', [ id: 106076, nci_institute_code: "OH321", name: "Gerad Center for Cancer Treatment", city: "Lima", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6091,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH321",GROUP_DESC:"OH321 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6091,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH321",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH321",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6091,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH321", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7091 ,protection_group_id: -6091, protection_element_id:-6091], primaryKey: false);
      insert('organizations', [ id: 106077, nci_institute_code: "OH322", name: "Surgical Associates of Zanesville, Inc.", city: "Zanesville", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6092,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH322",GROUP_DESC:"OH322 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6092,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH322",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH322",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6092,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH322", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7092 ,protection_group_id: -6092, protection_element_id:-6092], primaryKey: false);
      insert('organizations', [ id: 106078, nci_institute_code: "OH323", name: "Appalachian Primary Care", city: "Zanesville", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6093,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH323",GROUP_DESC:"OH323 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6093,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH323",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH323",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6093,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH323", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7093 ,protection_group_id: -6093, protection_element_id:-6093], primaryKey: false);
      insert('organizations', [ id: 106079, nci_institute_code: "OH324", name: "Radiology Consultants, Inc.", city: "Youngstown", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6094,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH324",GROUP_DESC:"OH324 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6094,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH324",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH324",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6094,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH324", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7094 ,protection_group_id: -6094, protection_element_id:-6094], primaryKey: false);
      insert('organizations', [ id: 106080, nci_institute_code: "OH325", name: "Springfield Regional Cancer Center", city: "Springfield", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6095,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH325",GROUP_DESC:"OH325 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6095,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH325",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH325",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6095,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH325", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7095 ,protection_group_id: -6095, protection_element_id:-6095], primaryKey: false);
      insert('organizations', [ id: 106081, nci_institute_code: "OH327", name: "Genesis HealthCare System", city: "Zanesville", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6096,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH327",GROUP_DESC:"OH327 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6096,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH327",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH327",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6096,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH327", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7096 ,protection_group_id: -6096, protection_element_id:-6096], primaryKey: false);
      insert('organizations', [ id: 106082, nci_institute_code: "OH328", name: "Surgical Consultants of Dayton, Inc.", city: "Dayton", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6097,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH328",GROUP_DESC:"OH328 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6097,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH328",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH328",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6097,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH328", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7097 ,protection_group_id: -6097, protection_element_id:-6097], primaryKey: false);
      insert('organizations', [ id: 106083, nci_institute_code: "OH329", name: "Mercy Hospital of Tiffin", city: "Tiffin", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6098,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH329",GROUP_DESC:"OH329 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6098,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH329",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH329",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6098,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH329", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7098 ,protection_group_id: -6098, protection_element_id:-6098], primaryKey: false);
      insert('organizations', [ id: 106084, nci_institute_code: "OH330", name: "University Hospital", city: "Cincinnati", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6099,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH330",GROUP_DESC:"OH330 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6099,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH330",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH330",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6099,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH330", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7099 ,protection_group_id: -6099, protection_element_id:-6099], primaryKey: false);
      insert('organizations', [ id: 106085, nci_institute_code: "OH331", name: "Forum Health Cancer Care Center", city: "Youngstown", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6100,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH331",GROUP_DESC:"OH331 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6100,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH331",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH331",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6100,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH331", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7100 ,protection_group_id: -6100, protection_element_id:-6100], primaryKey: false);
      insert('organizations', [ id: 106086, nci_institute_code: "OH332", name: "Radiation Oncology Center", city: "Alliance", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6101,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH332",GROUP_DESC:"OH332 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6101,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH332",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH332",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6101,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH332", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7101 ,protection_group_id: -6101, protection_element_id:-6101], primaryKey: false);
      insert('organizations', [ id: 106087, nci_institute_code: "OH333", name: "Midwest Community Health Associates", city: "Bryan", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6102,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH333",GROUP_DESC:"OH333 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6102,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH333",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH333",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6102,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH333", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7102 ,protection_group_id: -6102, protection_element_id:-6102], primaryKey: false);
      insert('organizations', [ id: 106088, nci_institute_code: "OH334", name: "Tri-State Regional Cancer Center", city: "Wintersville", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6103,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH334",GROUP_DESC:"OH334 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6103,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH334",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH334",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6103,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH334", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7103 ,protection_group_id: -6103, protection_element_id:-6103], primaryKey: false);
      insert('organizations', [ id: 106089, nci_institute_code: "OH335", name: "Ohio State University", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6104,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH335",GROUP_DESC:"OH335 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6104,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH335",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH335",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6104,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH335", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7104 ,protection_group_id: -6104, protection_element_id:-6104], primaryKey: false);
      insert('organizations', [ id: 106090, nci_institute_code: "OH336", name: "Surgical Associates of Springfield, Inc.", city: "Springfield", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6105,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH336",GROUP_DESC:"OH336 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6105,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH336",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH336",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6105,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH336", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7105 ,protection_group_id: -6105, protection_element_id:-6105], primaryKey: false);
      insert('organizations', [ id: 106091, nci_institute_code: "OH337", name: "Dayton Oncology and Hematology, PA", city: "Dayton", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6106,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH337",GROUP_DESC:"OH337 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6106,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH337",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH337",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6106,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH337", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7106 ,protection_group_id: -6106, protection_element_id:-6106], primaryKey: false);
      insert('organizations', [ id: 106092, nci_institute_code: "OH338", name: "Gabrail Cancer Center", city: "Canton", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6107,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH338",GROUP_DESC:"OH338 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6107,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH338",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH338",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6107,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH338", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7107 ,protection_group_id: -6107, protection_element_id:-6107], primaryKey: false);
      insert('organizations', [ id: 106093, nci_institute_code: "OH339", name: "Delaware Radiation Oncology", city: "Delaware", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6108,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH339",GROUP_DESC:"OH339 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6108,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH339",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH339",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6108,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH339", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7108 ,protection_group_id: -6108, protection_element_id:-6108], primaryKey: false);
      insert('organizations', [ id: 106094, nci_institute_code: "OH340", name: "Breast Care Specialists, Inc.", city: "Westerville", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6109,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH340",GROUP_DESC:"OH340 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6109,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH340",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH340",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6109,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH340", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7109 ,protection_group_id: -6109, protection_element_id:-6109], primaryKey: false);
      insert('organizations', [ id: 106095, nci_institute_code: "OH341", name: "Medical Association of Middletown", city: "Middletown", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6110,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH341",GROUP_DESC:"OH341 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6110,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH341",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH341",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6110,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH341", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7110 ,protection_group_id: -6110, protection_element_id:-6110], primaryKey: false);
      insert('organizations', [ id: 106096, nci_institute_code: "OH342", name: "Magruder Hospital", city: "Port Clinton", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6111,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH342",GROUP_DESC:"OH342 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6111,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH342",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH342",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6111,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH342", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7111 ,protection_group_id: -6111, protection_element_id:-6111], primaryKey: false);
      insert('organizations', [ id: 106097, nci_institute_code: "OH343", name: "Cole, Sharon, K. M.D. (UIA Investigator)", city: "Kenton", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6112,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH343",GROUP_DESC:"OH343 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6112,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH343",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH343",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6112,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH343", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7112 ,protection_group_id: -6112, protection_element_id:-6112], primaryKey: false);
    }

    void m4() {
        // all4 (25 terms)
      insert('organizations', [ id: 106098, nci_institute_code: "OH344", name: "Stark, Michael, Edward. M.D. (UIA Investigator)", city: "Toledo", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6113,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH344",GROUP_DESC:"OH344 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6113,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH344",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH344",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6113,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH344", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7113 ,protection_group_id: -6113, protection_element_id:-6113], primaryKey: false);
      insert('organizations', [ id: 106099, nci_institute_code: "OH345", name: "Toledo Radiation Oncology at Northwest Ohio Onocolgy Center", city: "Maumee", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6114,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH345",GROUP_DESC:"OH345 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6114,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH345",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH345",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6114,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH345", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7114 ,protection_group_id: -6114, protection_element_id:-6114], primaryKey: false);
      insert('organizations', [ id: 106100, nci_institute_code: "OH347", name: "University of Ohio", city: "Athens", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6115,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH347",GROUP_DESC:"OH347 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6115,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH347",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH347",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6115,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH347", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7115 ,protection_group_id: -6115, protection_element_id:-6115], primaryKey: false);
      insert('organizations', [ id: 106101, nci_institute_code: "OH348", name: "Scioto Valley Urology Inc", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6116,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH348",GROUP_DESC:"OH348 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6116,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH348",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH348",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6116,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH348", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7116 ,protection_group_id: -6116, protection_element_id:-6116], primaryKey: false);
      insert('organizations', [ id: 106102, nci_institute_code: "OH349", name: "Cardiothoracic and Vascular Surgery of Akron Inc", city: "Akron", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6117,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH349",GROUP_DESC:"OH349 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6117,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH349",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH349",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6117,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH349", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7117 ,protection_group_id: -6117, protection_element_id:-6117], primaryKey: false);
      insert('organizations', [ id: 106103, nci_institute_code: "OH350", name: "Miami Valley Heart and Lung Surgeons LLC", city: "Dayton", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6118,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH350",GROUP_DESC:"OH350 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6118,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH350",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH350",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6118,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH350", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7118 ,protection_group_id: -6118, protection_element_id:-6118], primaryKey: false);
      insert('organizations', [ id: 106104, nci_institute_code: "OH351", name: "Trilogy Cancer Care", city: "Wooster", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6119,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH351",GROUP_DESC:"OH351 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6119,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH351",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH351",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6119,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH351", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7119 ,protection_group_id: -6119, protection_element_id:-6119], primaryKey: false);
      insert('organizations', [ id: 106105, nci_institute_code: "OH352", name: "Dalton & VanFossen Surgeons", city: "Akron", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6120,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH352",GROUP_DESC:"OH352 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6120,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH352",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH352",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6120,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH352", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7120 ,protection_group_id: -6120, protection_element_id:-6120], primaryKey: false);
      insert('organizations', [ id: 106106, nci_institute_code: "OH353", name: "South Dayton Surgeons Inc", city: "Kettering", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6121,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH353",GROUP_DESC:"OH353 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6121,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH353",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH353",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6121,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH353", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7121 ,protection_group_id: -6121, protection_element_id:-6121], primaryKey: false);
      insert('organizations', [ id: 106107, nci_institute_code: "OH354", name: "Neurosurgical Network Inc", city: "Toledo", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6122,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH354",GROUP_DESC:"OH354 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6122,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH354",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH354",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6122,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH354", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7122 ,protection_group_id: -6122, protection_element_id:-6122], primaryKey: false);
      insert('organizations', [ id: 106108, nci_institute_code: "OH355", name: "SASS Friedman and Associates", city: "Mayfield Village", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6123,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH355",GROUP_DESC:"OH355 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6123,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH355",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH355",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6123,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH355", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7123 ,protection_group_id: -6123, protection_element_id:-6123], primaryKey: false);
      insert('organizations', [ id: 106109, nci_institute_code: "OH356", name: "Mercy Children's Hospital", city: "Toledo", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6124,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH356",GROUP_DESC:"OH356 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6124,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH356",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH356",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6124,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH356", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7124 ,protection_group_id: -6124, protection_element_id:-6124], primaryKey: false);
      insert('organizations', [ id: 106110, nci_institute_code: "OH357", name: "Capital Urology Inc", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6125,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH357",GROUP_DESC:"OH357 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6125,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH357",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH357",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6125,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH357", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7125 ,protection_group_id: -6125, protection_element_id:-6125], primaryKey: false);
      insert('organizations', [ id: 106111, nci_institute_code: "OH358", name: "Gynecologic Oncologist of Northeastern Ohio", city: "Akron", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6126,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH358",GROUP_DESC:"OH358 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6126,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH358",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH358",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6126,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH358", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7126 ,protection_group_id: -6126, protection_element_id:-6126], primaryKey: false);
      insert('organizations', [ id: 106112, nci_institute_code: "OH359", name: "Beachwood Family Health and Surgery Center", city: "Beachwood", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6127,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH359",GROUP_DESC:"OH359 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6127,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH359",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH359",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6127,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH359", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7127 ,protection_group_id: -6127, protection_element_id:-6127], primaryKey: false);
      insert('organizations', [ id: 106113, nci_institute_code: "OH360", name: "Hematology / Oncology Specialists of Northwest Ohio", city: "Findlay", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6128,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH360",GROUP_DESC:"OH360 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6128,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH360",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH360",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6128,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH360", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7128 ,protection_group_id: -6128, protection_element_id:-6128], primaryKey: false);
      insert('organizations', [ id: 106114, nci_institute_code: "OH361", name: "Allison Radiation Oncology Center", city: "Lima", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6129,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH361",GROUP_DESC:"OH361 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6129,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH361",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH361",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6129,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH361", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7129 ,protection_group_id: -6129, protection_element_id:-6129], primaryKey: false);
      insert('organizations', [ id: 106115, nci_institute_code: "OH362", name: "Mount Carmel Saint Ann's Cancer Center", city: "Westerville", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6130,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH362",GROUP_DESC:"OH362 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6130,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH362",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH362",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6130,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH362", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7130 ,protection_group_id: -6130, protection_element_id:-6130], primaryKey: false);
      insert('organizations', [ id: 106116, nci_institute_code: "OH363", name: "Miami County Surgeons Incorporated", city: "Piqua", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6131,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH363",GROUP_DESC:"OH363 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6131,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH363",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH363",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6131,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH363", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7131 ,protection_group_id: -6131, protection_element_id:-6131], primaryKey: false);
      insert('organizations', [ id: 106117, nci_institute_code: "OH364", name: "Mount Carmel Imaging and Therapy Center", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6132,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH364",GROUP_DESC:"OH364 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6132,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH364",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH364",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6132,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH364", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7132 ,protection_group_id: -6132, protection_element_id:-6132], primaryKey: false);
      insert('organizations', [ id: 106118, nci_institute_code: "OH365", name: "Miami Valley Colon and Rectal Surgeons Incorporated", city: "Dayton", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6133,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH365",GROUP_DESC:"OH365 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6133,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH365",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH365",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6133,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH365", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7133 ,protection_group_id: -6133, protection_element_id:-6133], primaryKey: false);
      insert('organizations', [ id: 106119, nci_institute_code: "OH366", name: "Ohio Urology Inc", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6134,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH366",GROUP_DESC:"OH366 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6134,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH366",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH366",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6134,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH366", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7134 ,protection_group_id: -6134, protection_element_id:-6134], primaryKey: false);
      insert('organizations', [ id: 106120, nci_institute_code: "OH367", name: "Lancaster Radiation Oncology", city: "Lancaster", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6135,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH367",GROUP_DESC:"OH367 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6135,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH367",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH367",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6135,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH367", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7135 ,protection_group_id: -6135, protection_element_id:-6135], primaryKey: false);
      insert('organizations', [ id: 106121, nci_institute_code: "OH368", name: "Riverside Gynecologic Oncology", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6136,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH368",GROUP_DESC:"OH368 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6136,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH368",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH368",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6136,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH368", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7136 ,protection_group_id: -6136, protection_element_id:-6136], primaryKey: false);
      insert('organizations', [ id: 106122, nci_institute_code: "OH369", name: "Mid Ohio Oncology and Hematology- West Office", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6137,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH369",GROUP_DESC:"OH369 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6137,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH369",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH369",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6137,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH369", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7137 ,protection_group_id: -6137, protection_element_id:-6137], primaryKey: false);
    }

    void m5() {
        // all5 (25 terms)
      insert('organizations', [ id: 106123, nci_institute_code: "OH370", name: "Heart and Lung Surgery of Akron", city: "Akron", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6138,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH370",GROUP_DESC:"OH370 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6138,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH370",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH370",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6138,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH370", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7138 ,protection_group_id: -6138, protection_element_id:-6138], primaryKey: false);
      insert('organizations', [ id: 106124, nci_institute_code: "OH371", name: "The Regional Cancer Center", city: "Ashtabula", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6139,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH371",GROUP_DESC:"OH371 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6139,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH371",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH371",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6139,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH371", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7139 ,protection_group_id: -6139, protection_element_id:-6139], primaryKey: false);
      insert('organizations', [ id: 106125, nci_institute_code: "OH372", name: "Oncology Hematology Care Inc - Western Hills", city: "Cincinnati", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6140,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH372",GROUP_DESC:"OH372 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6140,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH372",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH372",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6140,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH372", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7140 ,protection_group_id: -6140, protection_element_id:-6140], primaryKey: false);
      insert('organizations', [ id: 106126, nci_institute_code: "OH373", name: "Beed, Elaine A, MD (Office)", city: "Westerville", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6141,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH373",GROUP_DESC:"OH373 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6141,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH373",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH373",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6141,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH373", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7141 ,protection_group_id: -6141, protection_element_id:-6141], primaryKey: false);
      insert('organizations', [ id: 106127, nci_institute_code: "OH374", name: "Martin Surgical Assocaites", city: "Centerville", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6142,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH374",GROUP_DESC:"OH374 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6142,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH374",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH374",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6142,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH374", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7142 ,protection_group_id: -6142, protection_element_id:-6142], primaryKey: false);
      insert('organizations', [ id: 106128, nci_institute_code: "OH375", name: "Mary Ellen Broadstone Gaeke MD LLC Inc", city: "Middletown", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6143,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH375",GROUP_DESC:"OH375 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6143,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH375",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH375",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6143,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH375", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7143 ,protection_group_id: -6143, protection_element_id:-6143], primaryKey: false);
      insert('organizations', [ id: 106129, nci_institute_code: "OH376", name: "Oncology Hematology Care Inc - Anderson", city: "Cincinnati", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6144,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH376",GROUP_DESC:"OH376 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6144,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH376",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH376",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6144,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH376", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7144 ,protection_group_id: -6144, protection_element_id:-6144], primaryKey: false);
      insert('organizations', [ id: 106130, nci_institute_code: "OH377", name: "Wright State Physicians Inc", city: "Dayton", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6145,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH377",GROUP_DESC:"OH377 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6145,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH377",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH377",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6145,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH377", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7145 ,protection_group_id: -6145, protection_element_id:-6145], primaryKey: false);
      insert('organizations', [ id: 106131, nci_institute_code: "OH378", name: "Stoneridge Endoscopy Center", city: "Dublin", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6146,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH378",GROUP_DESC:"OH378 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6146,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH378",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH378",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6146,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH378", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7146 ,protection_group_id: -6146, protection_element_id:-6146], primaryKey: false);
      insert('organizations', [ id: 106132, nci_institute_code: "OH379", name: "Mary Rutan Hospital", city: "Bellefontaine", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6147,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH379",GROUP_DESC:"OH379 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6147,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH379",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH379",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6147,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH379", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7147 ,protection_group_id: -6147, protection_element_id:-6147], primaryKey: false);
      insert('organizations', [ id: 106133, nci_institute_code: "OH380", name: "Central Ohio Surgical Specialists Inc", city: "Hilliard", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6148,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH380",GROUP_DESC:"OH380 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6148,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH380",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH380",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6148,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH380", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7148 ,protection_group_id: -6148, protection_element_id:-6148], primaryKey: false);
      insert('organizations', [ id: 106134, nci_institute_code: "OH381", name: "Orion Cancer Care Inc", city: "Findlay", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6149,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH381",GROUP_DESC:"OH381 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6149,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH381",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH381",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6149,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH381", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7149 ,protection_group_id: -6149, protection_element_id:-6149], primaryKey: false);
      insert('organizations', [ id: 106135, nci_institute_code: "OH382", name: "Oncology Hematology Care Inc - Mount Airy", city: "Cincinnati", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6150,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH382",GROUP_DESC:"OH382 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6150,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH382",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH382",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6150,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH382", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7150 ,protection_group_id: -6150, protection_element_id:-6150], primaryKey: false);
      insert('organizations', [ id: 106136, nci_institute_code: "OH383", name: "Dr Mubashair Dr Marquinez & Dr Rehman Inc", city: "Akron", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6151,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH383",GROUP_DESC:"OH383 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6151,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH383",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH383",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6151,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH383", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7151 ,protection_group_id: -6151, protection_element_id:-6151], primaryKey: false);
      insert('organizations', [ id: 106137, nci_institute_code: "OH384", name: "Clark Radiation Oncology Company", city: "Springfield", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6152,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH384",GROUP_DESC:"OH384 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6152,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH384",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH384",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6152,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH384", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7152 ,protection_group_id: -6152, protection_element_id:-6152], primaryKey: false);
      insert('organizations', [ id: 106138, nci_institute_code: "OH385", name: "Marian A Llenado-Lee MD Inc", city: "Kettering", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6153,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH385",GROUP_DESC:"OH385 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6153,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH385",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH385",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6153,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH385", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7153 ,protection_group_id: -6153, protection_element_id:-6153], primaryKey: false);
      insert('organizations', [ id: 106139, nci_institute_code: "OH386", name: "Dr David Fishman Inc", city: "Euclid", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6154,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH386",GROUP_DESC:"OH386 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6154,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH386",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH386",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6154,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH386", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7154 ,protection_group_id: -6154, protection_element_id:-6154], primaryKey: false);
      insert('organizations', [ id: 106140, nci_institute_code: "OH387", name: "Advanced Breast Care", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6155,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH387",GROUP_DESC:"OH387 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6155,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH387",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH387",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6155,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH387", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7155 ,protection_group_id: -6155, protection_element_id:-6155], primaryKey: false);
      insert('organizations', [ id: 106141, nci_institute_code: "OH388", name: "Doctors Surgical Specialist", city: "Columbus", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6156,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH388",GROUP_DESC:"OH388 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6156,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH388",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH388",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6156,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH388", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7156 ,protection_group_id: -6156, protection_element_id:-6156], primaryKey: false);
      insert('organizations', [ id: 106142, nci_institute_code: "OH389", name: "High Point Regional Cancer Center", city: "Bellefontaine", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6157,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH389",GROUP_DESC:"OH389 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6157,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH389",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH389",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6157,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH389", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7157 ,protection_group_id: -6157, protection_element_id:-6157], primaryKey: false);
      insert('organizations', [ id: 106143, nci_institute_code: "OH390", name: "Country Square Surgeons", city: "Sylvania", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6158,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH390",GROUP_DESC:"OH390 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6158,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH390",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH390",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6158,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH390", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7158 ,protection_group_id: -6158, protection_element_id:-6158], primaryKey: false);
      insert('organizations', [ id: 106144, nci_institute_code: "OH391", name: "Michael T Sheehan MD LLC", city: "Lima", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6159,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH391",GROUP_DESC:"OH391 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6159,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH391",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH391",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6159,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH391", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7159 ,protection_group_id: -6159, protection_element_id:-6159], primaryKey: false);
      insert('organizations', [ id: 106145, nci_institute_code: "OH392", name: "Wright State University Surgeons", city: "Dayton", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6160,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH392",GROUP_DESC:"OH392 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6160,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH392",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH392",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6160,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH392", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7160 ,protection_group_id: -6160, protection_element_id:-6160], primaryKey: false);
      insert('organizations', [ id: 106146, nci_institute_code: "OH393", name: "Oncology Hematology Consultants Of Southeastern Ohio Inc", city: "Zanesville", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6161,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH393",GROUP_DESC:"OH393 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6161,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH393",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH393",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6161,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH393", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7161 ,protection_group_id: -6161, protection_element_id:-6161], primaryKey: false);
      insert('organizations', [ id: 106147, nci_institute_code: "OH394", name: "University Pointe", city: "West Chester", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6162,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH394",GROUP_DESC:"OH394 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6162,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH394",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH394",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6162,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH394", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7162 ,protection_group_id: -6162, protection_element_id:-6162], primaryKey: false);
    }

    void m6() {
        // all6 (25 terms)
      insert('organizations', [ id: 106148, nci_institute_code: "OH395", name: "Cleveland Biolabs Inc", city: "Cleveland", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6163,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH395",GROUP_DESC:"OH395 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6163,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH395",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH395",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6163,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH395", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7163 ,protection_group_id: -6163, protection_element_id:-6163], primaryKey: false);
      insert('organizations', [ id: 106149, nci_institute_code: "OH396", name: "Cancer Care of West Central Ohio", city: "Lima", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6164,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH396",GROUP_DESC:"OH396 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6164,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH396",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH396",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6164,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH396", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7164 ,protection_group_id: -6164, protection_element_id:-6164], primaryKey: false);
      insert('organizations', [ id: 106150, nci_institute_code: "OH397", name: "Ireland Cancer Center Landerbrook Health Center", city: "Mayfield Heights", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6165,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH397",GROUP_DESC:"OH397 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6165,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH397",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH397",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6165,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH397", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7165 ,protection_group_id: -6165, protection_element_id:-6165], primaryKey: false);
      insert('organizations', [ id: 106151, nci_institute_code: "OH398", name: "Comprehensive Breast Center", city: "Gahanna", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6166,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH398",GROUP_DESC:"OH398 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6166,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH398",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH398",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6166,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH398", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7166 ,protection_group_id: -6166, protection_element_id:-6166], primaryKey: false);
      insert('organizations', [ id: 106152, nci_institute_code: "OH399", name: "The Cleveland Clinic Willoughby Hills Family Health Center", city: "Willoughby Hills", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6167,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH399",GROUP_DESC:"OH399 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6167,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH399",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH399",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6167,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH399", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7167 ,protection_group_id: -6167, protection_element_id:-6167], primaryKey: false);
      insert('organizations', [ id: 106153, nci_institute_code: "OH400", name: "Hope Cancer Center of Northwest Ohio", city: "Lima", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6168,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH400",GROUP_DESC:"OH400 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6168,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH400",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH400",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6168,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH400", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7168 ,protection_group_id: -6168, protection_element_id:-6168], primaryKey: false);
      insert('organizations', [ id: 106154, nci_institute_code: "OH401", name: "Central Ohio Oncology", city: "Dublin", state: "OH", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6169,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH401",GROUP_DESC:"OH401 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6169,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OH401",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OH401",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6169,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OH401", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7169 ,protection_group_id: -6169, protection_element_id:-6169], primaryKey: false);
      insert('organizations', [ id: 106155, nci_institute_code: "OK001", name: "Saint Anthony Hospital", city: "Oklahoma City", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6170,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK001",GROUP_DESC:"OK001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6170,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6170,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7170 ,protection_group_id: -6170, protection_element_id:-6170], primaryKey: false);
      insert('organizations', [ id: 106156, nci_institute_code: "OK002", name: "Veterans Administration Medical Center - Oklahoma City", city: "Oklahoma City", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6171,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK002",GROUP_DESC:"OK002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6171,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6171,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7171 ,protection_group_id: -6171, protection_element_id:-6171], primaryKey: false);
      insert('organizations', [ id: 106157, nci_institute_code: "OK003", name: "University of Oklahoma Health Sciences Center", city: "Oklahoma City", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6172,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK003",GROUP_DESC:"OK003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6172,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6172,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7172 ,protection_group_id: -6172, protection_element_id:-6172], primaryKey: false);
      insert('organizations', [ id: 106158, nci_institute_code: "OK004", name: "Oklahoma City Clinic", city: "Oklahoma City", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6173,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK004",GROUP_DESC:"OK004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6173,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6173,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7173 ,protection_group_id: -6173, protection_element_id:-6173], primaryKey: false);
      insert('organizations', [ id: 106159, nci_institute_code: "OK005", name: "Oklahoma Memorial Hospital", city: "Oklahoma City", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6174,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK005",GROUP_DESC:"OK005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6174,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6174,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7174 ,protection_group_id: -6174, protection_element_id:-6174], primaryKey: false);
      insert('organizations', [ id: 106160, nci_institute_code: "OK006", name: "Presbyterian Hospital / The University of Oklahoma HSC", city: "Oklahoma City", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6175,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK006",GROUP_DESC:"OK006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6175,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6175,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7175 ,protection_group_id: -6175, protection_element_id:-6175], primaryKey: false);
      insert('organizations', [ id: 106161, nci_institute_code: "OK007", name: "Southwest Medical Center of Oklahoma", city: "Oklahoma City", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6176,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK007",GROUP_DESC:"OK007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6176,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6176,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7176 ,protection_group_id: -6176, protection_element_id:-6176], primaryKey: false);
      insert('organizations', [ id: 106162, nci_institute_code: "OK008", name: "Integris Baptist Medical Center", city: "Oklahoma City", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6177,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK008",GROUP_DESC:"OK008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6177,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6177,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7177 ,protection_group_id: -6177, protection_element_id:-6177], primaryKey: false);
      insert('organizations', [ id: 106163, nci_institute_code: "OK009", name: "Deaconess Hospital", city: "Oklahoma City", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6178,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK009",GROUP_DESC:"OK009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6178,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6178,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7178 ,protection_group_id: -6178, protection_element_id:-6178], primaryKey: false);
      insert('organizations', [ id: 106164, nci_institute_code: "OK010", name: "Mercy Health Center", city: "Oklahoma City", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6179,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK010",GROUP_DESC:"OK010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6179,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6179,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7179 ,protection_group_id: -6179, protection_element_id:-6179], primaryKey: false);
      insert('organizations', [ id: 106165, nci_institute_code: "OK011", name: "University of Oklahoma", city: "Oklahoma City", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6180,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK011",GROUP_DESC:"OK011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6180,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6180,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7180 ,protection_group_id: -6180, protection_element_id:-6180], primaryKey: false);
      insert('organizations', [ id: 106166, nci_institute_code: "OK013", name: "Saint John Medical Center", city: "Tulsa", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6181,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK013",GROUP_DESC:"OK013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6181,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6181,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7181 ,protection_group_id: -6181, protection_element_id:-6181], primaryKey: false);
      insert('organizations', [ id: 106167, nci_institute_code: "OK014", name: "Natalie W. Bryant Cancer Center", city: "Tulsa", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6182,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK014",GROUP_DESC:"OK014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6182,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6182,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7182 ,protection_group_id: -6182, protection_element_id:-6182], primaryKey: false);
      insert('organizations', [ id: 106168, nci_institute_code: "OK015", name: "Oklahoma Osteopathic Hospital", city: "Tulsa", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6183,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK015",GROUP_DESC:"OK015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6183,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6183,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7183 ,protection_group_id: -6183, protection_element_id:-6183], primaryKey: false);
      insert('organizations', [ id: 106169, nci_institute_code: "OK016", name: "University of Oklahoma College of Medicine", city: "Tulsa", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6184,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK016",GROUP_DESC:"OK016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6184,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6184,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7184 ,protection_group_id: -6184, protection_element_id:-6184], primaryKey: false);
      insert('organizations', [ id: 106170, nci_institute_code: "OK017", name: "University of Oklahoma, Tulsa", city: "Tulsa", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6185,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK017",GROUP_DESC:"OK017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6185,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6185,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7185 ,protection_group_id: -6185, protection_element_id:-6185], primaryKey: false);
      insert('organizations', [ id: 106171, nci_institute_code: "OK019", name: "Oral Roberts University", city: "Tulsa", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6186,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK019",GROUP_DESC:"OK019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6186,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6186,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7186 ,protection_group_id: -6186, protection_element_id:-6186], primaryKey: false);
      insert('organizations', [ id: 106172, nci_institute_code: "OK020", name: "Muskogee Regional Medical Center", city: "Muskogee", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6187,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK020",GROUP_DESC:"OK020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6187,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6187,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7187 ,protection_group_id: -6187, protection_element_id:-6187], primaryKey: false);
    }

    void m7() {
        // all7 (25 terms)
      insert('organizations', [ id: 106173, nci_institute_code: "OK021", name: "W.W. Hastings Hospital", city: "Tahlequah", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6188,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK021",GROUP_DESC:"OK021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6188,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6188,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7188 ,protection_group_id: -6188, protection_element_id:-6188], primaryKey: false);
      insert('organizations', [ id: 106174, nci_institute_code: "OK022", name: "Phs Talihina Indian Hospital", city: "Talihina", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6189,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK022",GROUP_DESC:"OK022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6189,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6189,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7189 ,protection_group_id: -6189, protection_element_id:-6189], primaryKey: false);
      insert('organizations', [ id: 106175, nci_institute_code: "OK023", name: "Norman Regional Hospital", city: "Norman", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6190,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK023",GROUP_DESC:"OK023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6190,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6190,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7190 ,protection_group_id: -6190, protection_element_id:-6190], primaryKey: false);
      insert('organizations', [ id: 106176, nci_institute_code: "OK024", name: "Hillcrest Medical Center", city: "Tulsa", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6191,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK024",GROUP_DESC:"OK024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6191,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6191,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7191 ,protection_group_id: -6191, protection_element_id:-6191], primaryKey: false);
      insert('organizations', [ id: 106177, nci_institute_code: "OK025", name: "University Hospital- OUHSC", city: "Oklahoma City", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6192,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK025",GROUP_DESC:"OK025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6192,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6192,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7192 ,protection_group_id: -6192, protection_element_id:-6192], primaryKey: false);
      insert('organizations', [ id: 106178, nci_institute_code: "OK026", name: "Comanche County Hospital", city: "Lawton", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6193,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK026",GROUP_DESC:"OK026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6193,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6193,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7193 ,protection_group_id: -6193, protection_element_id:-6193], primaryKey: false);
      insert('organizations', [ id: 106179, nci_institute_code: "OK027", name: "Cleo Craig Cancer Research", city: "Lawton", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6194,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK027",GROUP_DESC:"OK027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6194,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6194,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7194 ,protection_group_id: -6194, protection_element_id:-6194], primaryKey: false);
      insert('organizations', [ id: 106180, nci_institute_code: "OK028", name: "Tulsa Regional Medical Center", city: "Tulsa", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6195,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK028",GROUP_DESC:"OK028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6195,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6195,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7195 ,protection_group_id: -6195, protection_element_id:-6195], primaryKey: false);
      insert('organizations', [ id: 106181, nci_institute_code: "OK030", name: "Private Practic Hematology Oncology", city: "Midwest City", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6196,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK030",GROUP_DESC:"OK030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6196,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6196,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7196 ,protection_group_id: -6196, protection_element_id:-6196], primaryKey: false);
      insert('organizations', [ id: 106182, nci_institute_code: "OK032", name: "Parkway Immunology Oncology", city: "Oklahoma City", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6197,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK032",GROUP_DESC:"OK032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6197,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6197,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7197 ,protection_group_id: -6197, protection_element_id:-6197], primaryKey: false);
      insert('organizations', [ id: 106183, nci_institute_code: "OK033", name: "Pathcor, Incorporated", city: "Oklahoma City", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6198,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK033",GROUP_DESC:"OK033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6198,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6198,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7198 ,protection_group_id: -6198, protection_element_id:-6198], primaryKey: false);
      insert('organizations', [ id: 106184, nci_institute_code: "OK034", name: "Lafortune Cancer Center", city: "Tulsa", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6199,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK034",GROUP_DESC:"OK034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6199,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6199,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7199 ,protection_group_id: -6199, protection_element_id:-6199], primaryKey: false);
      insert('organizations', [ id: 106185, nci_institute_code: "OK035", name: "Tulsa Urologic Clinic Incorporated", city: "Tulsa", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6200,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK035",GROUP_DESC:"OK035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6200,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6200,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7200 ,protection_group_id: -6200, protection_element_id:-6200], primaryKey: false);
      insert('organizations', [ id: 106186, nci_institute_code: "OK036", name: "Eastern Oklahoma Hema/Onc., Inc", city: "Tulsa", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6201,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK036",GROUP_DESC:"OK036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6201,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6201,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7201 ,protection_group_id: -6201, protection_element_id:-6201], primaryKey: false);
      insert('organizations', [ id: 106187, nci_institute_code: "OK037", name: "Cancer Care Associates", city: "Tulsa", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6202,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK037",GROUP_DESC:"OK037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6202,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6202,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7202 ,protection_group_id: -6202, protection_element_id:-6202], primaryKey: false);
      insert('organizations', [ id: 106188, nci_institute_code: "OK038", name: "Cancer Treatment Center", city: "Tulsa", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6203,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK038",GROUP_DESC:"OK038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6203,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6203,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7203 ,protection_group_id: -6203, protection_element_id:-6203], primaryKey: false);
      insert('organizations', [ id: 106189, nci_institute_code: "OK039", name: "Gynecologic Oncology", city: "Oklahoma City", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6204,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK039",GROUP_DESC:"OK039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6204,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6204,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7204 ,protection_group_id: -6204, protection_element_id:-6204], primaryKey: false);
      insert('organizations', [ id: 106190, nci_institute_code: "OK040", name: "Mustogee VA Medical Service", city: "Muskogee", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6205,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK040",GROUP_DESC:"OK040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6205,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6205,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7205 ,protection_group_id: -6205, protection_element_id:-6205], primaryKey: false);
      insert('organizations', [ id: 106191, nci_institute_code: "OK041", name: "Cancer Care Associates", city: "Oklahoma City", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6206,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK041",GROUP_DESC:"OK041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6206,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6206,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7206 ,protection_group_id: -6206, protection_element_id:-6206], primaryKey: false);
      insert('organizations', [ id: 106192, nci_institute_code: "OK042", name: "Leta M Chapman Breast Health Center", city: "Tulsa", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6207,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK042",GROUP_DESC:"OK042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6207,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6207,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7207 ,protection_group_id: -6207, protection_element_id:-6207], primaryKey: false);
      insert('organizations', [ id: 106193, nci_institute_code: "OK044", name: "Cancer and Blood Care PC", city: "Ponca City", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6208,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK044",GROUP_DESC:"OK044 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6208,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK044",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK044",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6208,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK044", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7208 ,protection_group_id: -6208, protection_element_id:-6208], primaryKey: false);
      insert('organizations', [ id: 106194, nci_institute_code: "OK045", name: "Cancer Care Associates", city: "Stillwater", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6209,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK045",GROUP_DESC:"OK045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6209,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6209,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7209 ,protection_group_id: -6209, protection_element_id:-6209], primaryKey: false);
      insert('organizations', [ id: 106195, nci_institute_code: "OK046", name: "Sooner State CCOP", city: "Tulsa", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6210,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK046",GROUP_DESC:"OK046 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6210,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK046",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK046",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6210,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK046", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7210 ,protection_group_id: -6210, protection_element_id:-6210], primaryKey: false);
      insert('organizations', [ id: 106196, nci_institute_code: "OK047", name: "Cancer Care Associates", city: "Norman", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6211,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK047",GROUP_DESC:"OK047 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6211,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK047",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK047",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6211,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK047", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7211 ,protection_group_id: -6211, protection_element_id:-6211], primaryKey: false);
      insert('organizations', [ id: 106197, nci_institute_code: "OK048", name: "Cancer Care Assoc- Bartlesville", city: "Bartlesville", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6212,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK048",GROUP_DESC:"OK048 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6212,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK048",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK048",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6212,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK048", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7212 ,protection_group_id: -6212, protection_element_id:-6212], primaryKey: false);
    }

    void m8() {
        // all8 (25 terms)
      insert('organizations', [ id: 106198, nci_institute_code: "OK049", name: "Cancer Care Associates- Duncan", city: "Duncan", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6213,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK049",GROUP_DESC:"OK049 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6213,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK049",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK049",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6213,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK049", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7213 ,protection_group_id: -6213, protection_element_id:-6213], primaryKey: false);
      insert('organizations', [ id: 106199, nci_institute_code: "OK050", name: "Cancer Care Associates - Enid", city: "Enid", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6214,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK050",GROUP_DESC:"OK050 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6214,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK050",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK050",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6214,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK050", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7214 ,protection_group_id: -6214, protection_element_id:-6214], primaryKey: false);
      insert('organizations', [ id: 106200, nci_institute_code: "OK051", name: "Cancer Care Associates-McAlester", city: "Mcalester", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6215,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK051",GROUP_DESC:"OK051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6215,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6215,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7215 ,protection_group_id: -6215, protection_element_id:-6215], primaryKey: false);
      insert('organizations', [ id: 106201, nci_institute_code: "OK052", name: "Cancer Care Associates-Shawnee", city: "Shawnee", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6216,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK052",GROUP_DESC:"OK052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6216,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6216,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7216 ,protection_group_id: -6216, protection_element_id:-6216], primaryKey: false);
      insert('organizations', [ id: 106202, nci_institute_code: "OK053", name: "Cancer Care Associates-Mercy", city: "Oklahoma City", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6217,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK053",GROUP_DESC:"OK053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6217,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6217,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7217 ,protection_group_id: -6217, protection_element_id:-6217], primaryKey: false);
      insert('organizations', [ id: 106203, nci_institute_code: "OK054", name: "Cancer Care Associates-Lakeside", city: "Oklahoma City", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6218,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK054",GROUP_DESC:"OK054 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6218,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK054",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK054",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6218,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK054", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7218 ,protection_group_id: -6218, protection_element_id:-6218], primaryKey: false);
      insert('organizations', [ id: 106204, nci_institute_code: "OK055", name: "Cancer Care Associates-Midtown", city: "Tulsa", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6219,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK055",GROUP_DESC:"OK055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6219,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6219,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7219 ,protection_group_id: -6219, protection_element_id:-6219], primaryKey: false);
      insert('organizations', [ id: 106205, nci_institute_code: "OK056", name: "Cancer Care Assoc-South Mingo", city: "Tulsa", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6220,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK056",GROUP_DESC:"OK056 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6220,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK056",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK056",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6220,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK056", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7220 ,protection_group_id: -6220, protection_element_id:-6220], primaryKey: false);
      insert('organizations', [ id: 106206, nci_institute_code: "OK057", name: "Cancer Care Assoc-Southwest", city: "Oklahoma City", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6221,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK057",GROUP_DESC:"OK057 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6221,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK057",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK057",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6221,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK057", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7221 ,protection_group_id: -6221, protection_element_id:-6221], primaryKey: false);
      insert('organizations', [ id: 106207, nci_institute_code: "OK059", name: "Cancer Care Associates - Frank Love Cancer Institute", city: "Oklahoma City", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6222,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK059",GROUP_DESC:"OK059 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6222,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK059",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK059",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6222,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK059", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7222 ,protection_group_id: -6222, protection_element_id:-6222], primaryKey: false);
      insert('organizations', [ id: 106208, nci_institute_code: "OK060", name: "Cancer Care Associates-Ardmore", city: "Ardmore", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6223,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK060",GROUP_DESC:"OK060 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6223,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK060",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK060",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6223,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK060", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7223 ,protection_group_id: -6223, protection_element_id:-6223], primaryKey: false);
      insert('organizations', [ id: 106209, nci_institute_code: "OK061", name: "Cancer Care Associates - St Anthony", city: "Oklahoma City", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6224,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK061",GROUP_DESC:"OK061 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6224,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK061",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK061",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6224,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK061", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7224 ,protection_group_id: -6224, protection_element_id:-6224], primaryKey: false);
      insert('organizations', [ id: 106210, nci_institute_code: "OK062", name: "Sooner State, Cancer Care Assoc,Yale", city: "Tulsa", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6225,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK062",GROUP_DESC:"OK062 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6225,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK062",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK062",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6225,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK062", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7225 ,protection_group_id: -6225, protection_element_id:-6225], primaryKey: false);
      insert('organizations', [ id: 106211, nci_institute_code: "OK063", name: "Cancer Specialist, Inc.", city: "Tulsa", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6226,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK063",GROUP_DESC:"OK063 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6226,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK063",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK063",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6226,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK063", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7226 ,protection_group_id: -6226, protection_element_id:-6226], primaryKey: false);
      insert('organizations', [ id: 106212, nci_institute_code: "OK064", name: "Cancer Treatment Center of Oklahoma", city: "Midwest", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6227,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK064",GROUP_DESC:"OK064 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6227,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK064",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK064",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6227,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK064", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7227 ,protection_group_id: -6227, protection_element_id:-6227], primaryKey: false);
      insert('organizations', [ id: 106213, nci_institute_code: "OK066", name: "Saint Joseph Regional Medical Center", city: "Ponca City", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6228,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK066",GROUP_DESC:"OK066 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6228,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK066",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK066",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6228,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK066", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7228 ,protection_group_id: -6228, protection_element_id:-6228], primaryKey: false);
      insert('organizations', [ id: 106214, nci_institute_code: "OK067", name: "Pulmonary Medicine Associates", city: "Tulsa", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6229,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK067",GROUP_DESC:"OK067 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6229,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK067",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK067",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6229,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK067", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7229 ,protection_group_id: -6229, protection_element_id:-6229], primaryKey: false);
      insert('organizations', [ id: 106215, nci_institute_code: "OK068", name: "Oklahoma Oncology, Inc.", city: "Tulsa", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6230,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK068",GROUP_DESC:"OK068 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6230,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK068",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK068",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6230,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK068", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7230 ,protection_group_id: -6230, protection_element_id:-6230], primaryKey: false);
      insert('organizations', [ id: 106216, nci_institute_code: "OK069", name: "Edmond Cancer Center", city: "Edmond", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6231,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK069",GROUP_DESC:"OK069 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6231,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK069",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK069",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6231,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK069", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7231 ,protection_group_id: -6231, protection_element_id:-6231], primaryKey: false);
      insert('organizations', [ id: 106217, nci_institute_code: "OK070", name: "Integris Southwest Medical Center", city: "Oklahoma City", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6232,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK070",GROUP_DESC:"OK070 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6232,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK070",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK070",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6232,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK070", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7232 ,protection_group_id: -6232, protection_element_id:-6232], primaryKey: false);
      insert('organizations', [ id: 106218, nci_institute_code: "OK071", name: "Cancer Specialists of Oklahoma", city: "Oklahoma City", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6233,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK071",GROUP_DESC:"OK071 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6233,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK071",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK071",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6233,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK071", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7233 ,protection_group_id: -6233, protection_element_id:-6233], primaryKey: false);
      insert('organizations', [ id: 106219, nci_institute_code: "OK073", name: "Surgical Associates, Inc.", city: "Tulsa", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6234,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK073",GROUP_DESC:"OK073 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6234,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK073",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK073",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6234,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK073", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7234 ,protection_group_id: -6234, protection_element_id:-6234], primaryKey: false);
      insert('organizations', [ id: 106220, nci_institute_code: "OK074", name: "CVT Surgery Inc", city: "Tulsa", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6235,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK074",GROUP_DESC:"OK074 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6235,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK074",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK074",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6235,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK074", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7235 ,protection_group_id: -6235, protection_element_id:-6235], primaryKey: false);
      insert('organizations', [ id: 106221, nci_institute_code: "OK075", name: "Oklahoma University Medical Center", city: "Oklahoma City", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6236,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK075",GROUP_DESC:"OK075 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6236,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK075",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK075",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6236,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK075", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7236 ,protection_group_id: -6236, protection_element_id:-6236], primaryKey: false);
      insert('organizations', [ id: 106222, nci_institute_code: "OK076", name: "Warren Cancer Research Foundation", city: "Tulsa", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6237,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK076",GROUP_DESC:"OK076 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6237,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK076",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK076",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6237,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK076", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7237 ,protection_group_id: -6237, protection_element_id:-6237], primaryKey: false);
    }

    void m9() {
        // all9 (25 terms)
      insert('organizations', [ id: 106223, nci_institute_code: "OK077", name: "Urologic Specialists of Oklahoma, Inc.", city: "Tulsa", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6238,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK077",GROUP_DESC:"OK077 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6238,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK077",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK077",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6238,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK077", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7238 ,protection_group_id: -6238, protection_element_id:-6238], primaryKey: false);
      insert('organizations', [ id: 106224, nci_institute_code: "OK078", name: "Oklahoma Medical Research Foundation", city: "Oklahoma City", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6239,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK078",GROUP_DESC:"OK078 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6239,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK078",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK078",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6239,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK078", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7239 ,protection_group_id: -6239, protection_element_id:-6239], primaryKey: false);
      insert('organizations', [ id: 106225, nci_institute_code: "OK079", name: "Oklahoma University Physicians Breast Institute", city: "Oklahoma City", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6240,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK079",GROUP_DESC:"OK079 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6240,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK079",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK079",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6240,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK079", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7240 ,protection_group_id: -6240, protection_element_id:-6240], primaryKey: false);
      insert('organizations', [ id: 106226, nci_institute_code: "OK080", name: "M Farouk Kanaa MD & Robert E Reynolds MD", city: "Oklahoma City", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6241,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK080",GROUP_DESC:"OK080 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6241,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK080",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK080",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6241,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK080", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7241 ,protection_group_id: -6241, protection_element_id:-6241], primaryKey: false);
      insert('organizations', [ id: 106227, nci_institute_code: "OK081", name: "Gynecologic Oncology", city: "Tulsa", state: "OK", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6242,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK081",GROUP_DESC:"OK081 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6242,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OK081",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OK081",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6242,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OK081", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7242 ,protection_group_id: -6242, protection_element_id:-6242], primaryKey: false);
      insert('organizations', [ id: 106228, nci_institute_code: "ONCTPI", name: "Oncotherapeutics, Incorporated", city: "Cranbury", state: "NJ", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6243,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ONCTPI",GROUP_DESC:"ONCTPI group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6243,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ONCTPI",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ONCTPI",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6243,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ONCTPI", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7243 ,protection_group_id: -6243, protection_element_id:-6243], primaryKey: false);
      insert('organizations', [ id: 106229, nci_institute_code: "OR001", name: "Willamette Falls Community Hospital", city: "Oregon City", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6244,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR001",GROUP_DESC:"OR001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6244,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6244,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7244 ,protection_group_id: -6244, protection_element_id:-6244], primaryKey: false);
      insert('organizations', [ id: 106230, nci_institute_code: "OR002", name: "Mclean Clinic", city: "Oregon City", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6245,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR002",GROUP_DESC:"OR002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6245,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6245,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7245 ,protection_group_id: -6245, protection_element_id:-6245], primaryKey: false);
      insert('organizations', [ id: 106231, nci_institute_code: "OR004", name: "Legacy Meridian Park Hospital", city: "Tualatin", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6246,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR004",GROUP_DESC:"OR004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6246,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6246,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7246 ,protection_group_id: -6246, protection_element_id:-6246], primaryKey: false);
      insert('organizations', [ id: 106232, nci_institute_code: "OR006", name: "Tuality Community Hospital", city: "Hillsboro", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6247,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR006",GROUP_DESC:"OR006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6247,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6247,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7247 ,protection_group_id: -6247, protection_element_id:-6247], primaryKey: false);
      insert('organizations', [ id: 106233, nci_institute_code: "OR008", name: "University Hospital", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6248,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR008",GROUP_DESC:"OR008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6248,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6248,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7248 ,protection_group_id: -6248, protection_element_id:-6248], primaryKey: false);
      insert('organizations', [ id: 106234, nci_institute_code: "OR009", name: "Marquam Medical Center", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6249,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR009",GROUP_DESC:"OR009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6249,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6249,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7249 ,protection_group_id: -6249, protection_element_id:-6249], primaryKey: false);
      insert('organizations', [ id: 106235, nci_institute_code: "OR010", name: "Oregon Health & Science University", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6250,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR010",GROUP_DESC:"OR010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6250,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6250,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7250 ,protection_group_id: -6250, protection_element_id:-6250], primaryKey: false);
      insert('organizations', [ id: 106236, nci_institute_code: "OR011", name: "Eastmoreland General Hospital", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6251,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR011",GROUP_DESC:"OR011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6251,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6251,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7251 ,protection_group_id: -6251, protection_element_id:-6251], primaryKey: false);
      insert('organizations', [ id: 106237, nci_institute_code: "OR012", name: "Portland Veterans Administration Medical Center", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6252,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR012",GROUP_DESC:"OR012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6252,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6252,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7252 ,protection_group_id: -6252, protection_element_id:-6252], primaryKey: false);
      insert('organizations', [ id: 106238, nci_institute_code: "OR013", name: "Good Samaritan Hospital", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6253,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR013",GROUP_DESC:"OR013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6253,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6253,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7253 ,protection_group_id: -6253, protection_element_id:-6253], primaryKey: false);
      insert('organizations', [ id: 106239, nci_institute_code: "OR014", name: "Providence Portland Medical Center", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6254,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR014",GROUP_DESC:"OR014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6254,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6254,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7254 ,protection_group_id: -6254, protection_element_id:-6254], primaryKey: false);
      insert('organizations', [ id: 106240, nci_institute_code: "OR016", name: "Suite 220", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6255,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR016",GROUP_DESC:"OR016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6255,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6255,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7255 ,protection_group_id: -6255, protection_element_id:-6255], primaryKey: false);
      insert('organizations', [ id: 106241, nci_institute_code: "OR017", name: "Dwyer Memorial Hospital", city: "Milwaukee", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6256,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR017",GROUP_DESC:"OR017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6256,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6256,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7256 ,protection_group_id: -6256, protection_element_id:-6256], primaryKey: false);
      insert('organizations', [ id: 106242, nci_institute_code: "OR018", name: "Providence Saint Vincent Medical Center", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6257,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR018",GROUP_DESC:"OR018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6257,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6257,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7257 ,protection_group_id: -6257, protection_element_id:-6257], primaryKey: false);
      insert('organizations', [ id: 106243, nci_institute_code: "OR019", name: "Legacy Emanuel Hospital and Health Center", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6258,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR019",GROUP_DESC:"OR019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6258,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6258,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7258 ,protection_group_id: -6258, protection_element_id:-6258], primaryKey: false);
      insert('organizations', [ id: 106244, nci_institute_code: "OR021", name: "Salem Hospital", city: "Salem", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6259,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR021",GROUP_DESC:"OR021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6259,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6259,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7259 ,protection_group_id: -6259, protection_element_id:-6259], primaryKey: false);
      insert('organizations', [ id: 106245, nci_institute_code: "OR022", name: "Albany General Hospital", city: "Albany", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6260,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR022",GROUP_DESC:"OR022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6260,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6260,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7260 ,protection_group_id: -6260, protection_element_id:-6260], primaryKey: false);
      insert('organizations', [ id: 106246, nci_institute_code: "OR023", name: "Good Samaritan Hospital", city: "Corvallis", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6261,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR023",GROUP_DESC:"OR023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6261,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6261,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7261 ,protection_group_id: -6261, protection_element_id:-6261], primaryKey: false);
      insert('organizations', [ id: 106247, nci_institute_code: "OR024", name: "Corvallis Clinic", city: "Corvallis", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6262,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR024",GROUP_DESC:"OR024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6262,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6262,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7262 ,protection_group_id: -6262, protection_element_id:-6262], primaryKey: false);
    }

    void m10() {
        // all10 (25 terms)
      insert('organizations', [ id: 106248, nci_institute_code: "OR025", name: "Sacred Heart Hospital", city: "Eugene", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6263,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR025",GROUP_DESC:"OR025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6263,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6263,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7263 ,protection_group_id: -6263, protection_element_id:-6263], primaryKey: false);
      insert('organizations', [ id: 106249, nci_institute_code: "OR026", name: "Eugene Hospital and Clinic", city: "Eugene", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6264,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR026",GROUP_DESC:"OR026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6264,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6264,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7264 ,protection_group_id: -6264, protection_element_id:-6264], primaryKey: false);
      insert('organizations', [ id: 106250, nci_institute_code: "OR027", name: "Veterans Administration Medical Center", city: "Roseburg", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6265,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR027",GROUP_DESC:"OR027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6265,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6265,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7265 ,protection_group_id: -6265, protection_element_id:-6265], primaryKey: false);
      insert('organizations', [ id: 106251, nci_institute_code: "OR028", name: "Mckenzie-Willamette Hospital", city: "Springfield", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6266,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR028",GROUP_DESC:"OR028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6266,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6266,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7266 ,protection_group_id: -6266, protection_element_id:-6266], primaryKey: false);
      insert('organizations', [ id: 106252, nci_institute_code: "OR029", name: "Rogue Valley Medical Center", city: "Medford", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6267,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR029",GROUP_DESC:"OR029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6267,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6267,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7267 ,protection_group_id: -6267, protection_element_id:-6267], primaryKey: false);
      insert('organizations', [ id: 106253, nci_institute_code: "OR030", name: "Grants Pass Clinic", city: "Grant Pass", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6268,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR030",GROUP_DESC:"OR030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6268,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6268,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7268 ,protection_group_id: -6268, protection_element_id:-6268], primaryKey: false);
      insert('organizations', [ id: 106254, nci_institute_code: "OR031", name: "Saint Charles Medical Center-Bend", city: "Bend", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6269,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR031",GROUP_DESC:"OR031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6269,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6269,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7269 ,protection_group_id: -6269, protection_element_id:-6269], primaryKey: false);
      insert('organizations', [ id: 106255, nci_institute_code: "OR032", name: "Bend Memorial Clinic", city: "Bend", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6270,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR032",GROUP_DESC:"OR032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6270,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6270,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7270 ,protection_group_id: -6270, protection_element_id:-6270], primaryKey: false);
      insert('organizations', [ id: 106256, nci_institute_code: "OR034", name: "St. Anthony Hospital", city: "Pendleton", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6271,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR034",GROUP_DESC:"OR034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6271,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6271,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7271 ,protection_group_id: -6271, protection_element_id:-6271], primaryKey: false);
      insert('organizations', [ id: 106257, nci_institute_code: "OR035", name: "Kaiser Permanente", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6272,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR035",GROUP_DESC:"OR035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6272,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6272,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7272 ,protection_group_id: -6272, protection_element_id:-6272], primaryKey: false);
      insert('organizations', [ id: 106258, nci_institute_code: "OR036", name: "Kaiser Sunnyside Medical Center", city: "Clackamas", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6273,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR036",GROUP_DESC:"OR036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6273,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6273,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7273 ,protection_group_id: -6273, protection_element_id:-6273], primaryKey: false);
      insert('organizations', [ id: 106259, nci_institute_code: "OR037", name: "Columbia River Oncology Program", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6274,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR037",GROUP_DESC:"OR037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6274,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6274,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7274 ,protection_group_id: -6274, protection_element_id:-6274], primaryKey: false);
      insert('organizations', [ id: 106260, nci_institute_code: "OR038", name: "Sky Lakes Medical Center - Cancer Treatment Center", city: "Klamath Falls", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6275,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR038",GROUP_DESC:"OR038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6275,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6275,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7275 ,protection_group_id: -6275, protection_element_id:-6275], primaryKey: false);
      insert('organizations', [ id: 106261, nci_institute_code: "OR039", name: "Metropolitan Clinic, P.C.", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6276,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR039",GROUP_DESC:"OR039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6276,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6276,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7276 ,protection_group_id: -6276, protection_element_id:-6276], primaryKey: false);
      insert('organizations', [ id: 106262, nci_institute_code: "OR041", name: "Portland Clinic", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6277,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR041",GROUP_DESC:"OR041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6277,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6277,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7277 ,protection_group_id: -6277, protection_element_id:-6277], primaryKey: false);
      insert('organizations', [ id: 106263, nci_institute_code: "OR042", name: "North Bend Medical Center Incorporated", city: "Coos Bay", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6278,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR042",GROUP_DESC:"OR042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6278,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6278,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7278 ,protection_group_id: -6278, protection_element_id:-6278], primaryKey: false);
      insert('organizations', [ id: 106264, nci_institute_code: "OR043", name: "Medford Clinic Professional Corporation", city: "Medford", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6279,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR043",GROUP_DESC:"OR043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6279,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6279,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7279 ,protection_group_id: -6279, protection_element_id:-6279], primaryKey: false);
      insert('organizations', [ id: 106265, nci_institute_code: "OR044", name: "Pacific Oncology PC- Providence Portland Office", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6280,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR044",GROUP_DESC:"OR044 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6280,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR044",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR044",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6280,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR044", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7280 ,protection_group_id: -6280, protection_element_id:-6280], primaryKey: false);
      insert('organizations', [ id: 106266, nci_institute_code: "OR045", name: "Medical Oncology Hematology Center", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6281,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR045",GROUP_DESC:"OR045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6281,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6281,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7281 ,protection_group_id: -6281, protection_element_id:-6281], primaryKey: false);
      insert('organizations', [ id: 106267, nci_institute_code: "OR046", name: "Portland Orthopedic Clinic., P.C.", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6282,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR046",GROUP_DESC:"OR046 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6282,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR046",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR046",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6282,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR046", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7282 ,protection_group_id: -6282, protection_element_id:-6282], primaryKey: false);
      insert('organizations', [ id: 106268, nci_institute_code: "OR048", name: "Providence Seaside Hospital", city: "Seaside", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6283,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR048",GROUP_DESC:"OR048 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6283,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR048",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR048",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6283,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR048", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7283 ,protection_group_id: -6283, protection_element_id:-6283], primaryKey: false);
      insert('organizations', [ id: 106269, nci_institute_code: "OR049", name: "Westside Internal Medicine, P.C.", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6284,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR049",GROUP_DESC:"OR049 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6284,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR049",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR049",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6284,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR049", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7284 ,protection_group_id: -6284, protection_element_id:-6284], primaryKey: false);
      insert('organizations', [ id: 106270, nci_institute_code: "OR050", name: "Oncology Associates of Oregon PC", city: "Springfield", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6285,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR050",GROUP_DESC:"OR050 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6285,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR050",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR050",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6285,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR050", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7285 ,protection_group_id: -6285, protection_element_id:-6285], primaryKey: false);
      insert('organizations', [ id: 106271, nci_institute_code: "OR051", name: "Healthfirst Medical Group, P.C.", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6286,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR051",GROUP_DESC:"OR051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6286,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6286,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7286 ,protection_group_id: -6286, protection_element_id:-6286], primaryKey: false);
      insert('organizations', [ id: 106272, nci_institute_code: "OR052", name: "Northwest Cancer Specialist", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6287,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR052",GROUP_DESC:"OR052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6287,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6287,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7287 ,protection_group_id: -6287, protection_element_id:-6287], primaryKey: false);
    }

    void m11() {
        // all11 (25 terms)
      insert('organizations', [ id: 106273, nci_institute_code: "OR053", name: "Legacy Mount Hood Medical Center", city: "Glesham", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6288,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR053",GROUP_DESC:"OR053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6288,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6288,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7288 ,protection_group_id: -6288, protection_element_id:-6288], primaryKey: false);
      insert('organizations', [ id: 106274, nci_institute_code: "OR054", name: "Providence Milwaukie Hospital", city: "Milwaukie", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6289,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR054",GROUP_DESC:"OR054 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6289,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR054",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR054",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6289,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR054", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7289 ,protection_group_id: -6289, protection_element_id:-6289], primaryKey: false);
      insert('organizations', [ id: 106275, nci_institute_code: "OR055", name: "Bay Area Hospital", city: "Coos Bay", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6290,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR055",GROUP_DESC:"OR055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6290,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6290,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7290 ,protection_group_id: -6290, protection_element_id:-6290], primaryKey: false);
      insert('organizations', [ id: 106276, nci_institute_code: "OR056", name: "Oncology of Southern Oregon", city: "Medford", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6291,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR056",GROUP_DESC:"OR056 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6291,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR056",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR056",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6291,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR056", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7291 ,protection_group_id: -6291, protection_element_id:-6291], primaryKey: false);
      insert('organizations', [ id: 106277, nci_institute_code: "OR057", name: "Asante Health System", city: "Medford", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6292,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR057",GROUP_DESC:"OR057 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6292,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR057",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR057",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6292,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR057", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7292 ,protection_group_id: -6292, protection_element_id:-6292], primaryKey: false);
      insert('organizations', [ id: 106278, nci_institute_code: "OR058", name: "Epic Imaging East", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6293,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR058",GROUP_DESC:"OR058 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6293,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR058",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR058",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6293,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR058", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7293 ,protection_group_id: -6293, protection_element_id:-6293], primaryKey: false);
      insert('organizations', [ id: 106279, nci_institute_code: "OR059", name: "Healthcare Specialists NW, P.C.", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6294,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR059",GROUP_DESC:"OR059 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6294,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR059",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR059",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6294,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR059", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7294 ,protection_group_id: -6294, protection_element_id:-6294], primaryKey: false);
      insert('organizations', [ id: 106280, nci_institute_code: "OR060", name: "Mid-Columbia Medical Center", city: "The Dalles", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6295,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR060",GROUP_DESC:"OR060 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6295,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR060",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR060",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6295,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR060", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7295 ,protection_group_id: -6295, protection_element_id:-6295], primaryKey: false);
      insert('organizations', [ id: 106281, nci_institute_code: "OR061", name: "The Oregon Clinic", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6296,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR061",GROUP_DESC:"OR061 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6296,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR061",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR061",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6296,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR061", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7296 ,protection_group_id: -6296, protection_element_id:-6296], primaryKey: false);
      insert('organizations', [ id: 106282, nci_institute_code: "OR063", name: "Willamette Valley Cancer Center", city: "Eugene", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6297,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR063",GROUP_DESC:"OR063 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6297,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR063",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR063",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6297,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR063", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7297 ,protection_group_id: -6297, protection_element_id:-6297], primaryKey: false);
      insert('organizations', [ id: 106283, nci_institute_code: "OR064", name: "Providence Medford Medical Center", city: "Medford", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6298,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR064",GROUP_DESC:"OR064 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6298,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR064",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR064",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6298,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR064", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7298 ,protection_group_id: -6298, protection_element_id:-6298], primaryKey: false);
      insert('organizations', [ id: 106284, nci_institute_code: "OR065", name: "Providence Cancer Center -The Plaza", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6299,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR065",GROUP_DESC:"OR065 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6299,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR065",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR065",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6299,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR065", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7299 ,protection_group_id: -6299, protection_element_id:-6299], primaryKey: false);
      insert('organizations', [ id: 106285, nci_institute_code: "OR066", name: "Three Rivers Community Hospital", city: "Grants Pass", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6300,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR066",GROUP_DESC:"OR066 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6300,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR066",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR066",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6300,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR066", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7300 ,protection_group_id: -6300, protection_element_id:-6300], primaryKey: false);
      insert('organizations', [ id: 106286, nci_institute_code: "OR067", name: "Hematology Oncology of Salem, LLP", city: "Salem", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6301,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR067",GROUP_DESC:"OR067 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6301,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR067",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR067",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6301,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR067", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7301 ,protection_group_id: -6301, protection_element_id:-6301], primaryKey: false);
      insert('organizations', [ id: 106287, nci_institute_code: "OR068", name: "Northwest Urological Clinic", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6302,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR068",GROUP_DESC:"OR068 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6302,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR068",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR068",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6302,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR068", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7302 ,protection_group_id: -6302, protection_element_id:-6302], primaryKey: false);
      insert('organizations', [ id: 106288, nci_institute_code: "OR069", name: "Hematology Oncology Associates P.C.", city: "Medford", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6303,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR069",GROUP_DESC:"OR069 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6303,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR069",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR069",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6303,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR069", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7303 ,protection_group_id: -6303, protection_element_id:-6303], primaryKey: false);
      insert('organizations', [ id: 106289, nci_institute_code: "OR070", name: "OHSU Cancer Institute, Southern Region", city: "Medford", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6304,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR070",GROUP_DESC:"OR070 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6304,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR070",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR070",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6304,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR070", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7304 ,protection_group_id: -6304, protection_element_id:-6304], primaryKey: false);
      insert('organizations', [ id: 106290, nci_institute_code: "OR072", name: "Northwest Pediatric Oncology", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6305,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR072",GROUP_DESC:"OR072 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6305,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR072",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR072",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6305,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR072", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7305 ,protection_group_id: -6305, protection_element_id:-6305], primaryKey: false);
      insert('organizations', [ id: 106291, nci_institute_code: "OR073", name: "Northwest Gastroenterology Clinic", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6306,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR073",GROUP_DESC:"OR073 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6306,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR073",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR073",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6306,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR073", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7306 ,protection_group_id: -6306, protection_element_id:-6306], primaryKey: false);
      insert('organizations', [ id: 106292, nci_institute_code: "OR074", name: "Kaiser Permanente-Interstate Medical Office Central", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6307,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR074",GROUP_DESC:"OR074 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6307,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR074",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR074",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6307,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR074", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7307 ,protection_group_id: -6307, protection_element_id:-6307], primaryKey: false);
      insert('organizations', [ id: 106293, nci_institute_code: "OR075", name: "Surgical Specialty Group", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6308,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR075",GROUP_DESC:"OR075 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6308,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR075",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR075",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6308,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR075", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7308 ,protection_group_id: -6308, protection_element_id:-6308], primaryKey: false);
      insert('organizations', [ id: 106294, nci_institute_code: "OR076", name: "Head & Neck Associates", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6309,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR076",GROUP_DESC:"OR076 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6309,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR076",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR076",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6309,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR076", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7309 ,protection_group_id: -6309, protection_element_id:-6309], primaryKey: false);
      insert('organizations', [ id: 106295, nci_institute_code: "OR077", name: "Urology Clinic - Portland", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6310,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR077",GROUP_DESC:"OR077 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6310,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR077",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR077",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6310,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR077", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7310 ,protection_group_id: -6310, protection_element_id:-6310], primaryKey: false);
      insert('organizations', [ id: 106296, nci_institute_code: "OR078", name: "Northwest Cancer Specialists - Oregon City", city: "Oregon City", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6311,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR078",GROUP_DESC:"OR078 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6311,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR078",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR078",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6311,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR078", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7311 ,protection_group_id: -6311, protection_element_id:-6311], primaryKey: false);
      insert('organizations', [ id: 106297, nci_institute_code: "OR079", name: "Oregon Breast Center", city: "Lake Oswego", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6312,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR079",GROUP_DESC:"OR079 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6312,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR079",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR079",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6312,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR079", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7312 ,protection_group_id: -6312, protection_element_id:-6312], primaryKey: false);
    }

    void m12() {
        // all12 (25 terms)
      insert('organizations', [ id: 106298, nci_institute_code: "OR080", name: "Pacific Oncology Providence Milwaukie Office", city: "Milwaukee", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6313,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR080",GROUP_DESC:"OR080 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6313,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR080",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR080",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6313,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR080", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7313 ,protection_group_id: -6313, protection_element_id:-6313], primaryKey: false);
      insert('organizations', [ id: 106299, nci_institute_code: "OR081", name: "Providence Cancer Center", city: "Medford", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6314,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR081",GROUP_DESC:"OR081 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6314,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR081",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR081",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6314,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR081", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7314 ,protection_group_id: -6314, protection_element_id:-6314], primaryKey: false);
      insert('organizations', [ id: 106300, nci_institute_code: "OR082", name: "Urologic Consultants", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6315,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR082",GROUP_DESC:"OR082 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6315,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR082",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR082",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6315,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR082", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7315 ,protection_group_id: -6315, protection_element_id:-6315], primaryKey: false);
      insert('organizations', [ id: 106301, nci_institute_code: "OR083", name: "Willamette Valley Cancer Center - Springfield", city: "Springfield", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6316,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR083",GROUP_DESC:"OR083 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6316,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR083",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR083",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6316,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR083", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7316 ,protection_group_id: -6316, protection_element_id:-6316], primaryKey: false);
      insert('organizations', [ id: 106302, nci_institute_code: "OR084", name: "Ashland Pediatrics P.C.", city: "Ashland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6317,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR084",GROUP_DESC:"OR084 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6317,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR084",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR084",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6317,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR084", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7317 ,protection_group_id: -6317, protection_element_id:-6317], primaryKey: false);
      insert('organizations', [ id: 106303, nci_institute_code: "OR085", name: "Radiation Oncologists PC- Main Office", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6318,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR085",GROUP_DESC:"OR085 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6318,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR085",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR085",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6318,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR085", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7318 ,protection_group_id: -6318, protection_element_id:-6318], primaryKey: false);
      insert('organizations', [ id: 106304, nci_institute_code: "OR086", name: "Surgical Associates", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6319,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR086",GROUP_DESC:"OR086 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6319,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR086",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR086",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6319,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR086", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7319 ,protection_group_id: -6319, protection_element_id:-6319], primaryKey: false);
      insert('organizations', [ id: 106305, nci_institute_code: "OR087", name: "Pacific Surgical Specialists", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6320,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR087",GROUP_DESC:"OR087 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6320,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR087",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR087",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6320,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR087", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7320 ,protection_group_id: -6320, protection_element_id:-6320], primaryKey: false);
      insert('organizations', [ id: 106306, nci_institute_code: "OR088", name: "Portland Surgical Oncology, PC", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6321,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR088",GROUP_DESC:"OR088 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6321,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR088",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR088",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6321,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR088", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7321 ,protection_group_id: -6321, protection_element_id:-6321], primaryKey: false);
      insert('organizations', [ id: 106307, nci_institute_code: "OR089", name: "Radiation Oncologists, PC", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6322,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR089",GROUP_DESC:"OR089 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6322,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR089",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR089",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6322,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR089", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7322 ,protection_group_id: -6322, protection_element_id:-6322], primaryKey: false);
      insert('organizations', [ id: 106308, nci_institute_code: "OR090", name: "The Oregon Clinic", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6323,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR090",GROUP_DESC:"OR090 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6323,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR090",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR090",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6323,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR090", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7323 ,protection_group_id: -6323, protection_element_id:-6323], primaryKey: false);
      insert('organizations', [ id: 106309, nci_institute_code: "OR091", name: "General Vascular Surgeons, PC", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6324,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR091",GROUP_DESC:"OR091 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6324,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR091",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR091",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6324,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR091", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7324 ,protection_group_id: -6324, protection_element_id:-6324], primaryKey: false);
      insert('organizations', [ id: 106310, nci_institute_code: "OR092", name: "Oregon Hematology Oncology Associates", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6325,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR092",GROUP_DESC:"OR092 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6325,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR092",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR092",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6325,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR092", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7325 ,protection_group_id: -6325, protection_element_id:-6325], primaryKey: false);
      insert('organizations', [ id: 106311, nci_institute_code: "OR093", name: "Radiation Oncology Consultants PC", city: "Medford", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6326,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR093",GROUP_DESC:"OR093 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6326,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR093",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR093",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6326,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR093", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7326 ,protection_group_id: -6326, protection_element_id:-6326], primaryKey: false);
      insert('organizations', [ id: 106312, nci_institute_code: "OR094", name: "The Surgical Center Inc", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6327,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR094",GROUP_DESC:"OR094 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6327,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR094",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR094",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6327,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR094", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7327 ,protection_group_id: -6327, protection_element_id:-6327], primaryKey: false);
      insert('organizations', [ id: 106313, nci_institute_code: "OR095", name: "University of Oregon", city: "Eugene", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6328,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR095",GROUP_DESC:"OR095 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6328,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR095",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR095",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6328,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR095", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7328 ,protection_group_id: -6328, protection_element_id:-6328], primaryKey: false);
      insert('organizations', [ id: 106314, nci_institute_code: "OR096", name: "Pacific Oncology", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6329,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR096",GROUP_DESC:"OR096 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6329,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR096",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR096",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6329,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR096", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7329 ,protection_group_id: -6329, protection_element_id:-6329], primaryKey: false);
      insert('organizations', [ id: 106315, nci_institute_code: "OR097", name: "Meridian Park Radiation Oncology Center", city: "Tualatin", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6330,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR097",GROUP_DESC:"OR097 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6330,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR097",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR097",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6330,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR097", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7330 ,protection_group_id: -6330, protection_element_id:-6330], primaryKey: false);
      insert('organizations', [ id: 106316, nci_institute_code: "OR098", name: "Advanced Specialty Care - Redmond", city: "Redmond", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6331,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR098",GROUP_DESC:"OR098 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6331,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR098",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR098",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6331,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR098", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7331 ,protection_group_id: -6331, protection_element_id:-6331], primaryKey: false);
      insert('organizations', [ id: 106317, nci_institute_code: "OR099", name: "Northwest Cancer Specialist-Rose Quarter Cancer Center", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6332,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR099",GROUP_DESC:"OR099 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6332,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR099",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR099",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6332,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR099", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7332 ,protection_group_id: -6332, protection_element_id:-6332], primaryKey: false);
      insert('organizations', [ id: 106318, nci_institute_code: "OR100", name: "Oregon Surgical Consultants", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6333,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR100",GROUP_DESC:"OR100 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6333,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR100",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR100",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6333,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR100", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7333 ,protection_group_id: -6333, protection_element_id:-6333], primaryKey: false);
      insert('organizations', [ id: 106319, nci_institute_code: "OR101", name: "Northwest Cancer Specialists - Hematology Clinic - Providence", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6334,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR101",GROUP_DESC:"OR101 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6334,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR101",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR101",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6334,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR101", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7334 ,protection_group_id: -6334, protection_element_id:-6334], primaryKey: false);
      insert('organizations', [ id: 106320, nci_institute_code: "OR102", name: "Hematology Oncology Associates PC", city: "Medford", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6335,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR102",GROUP_DESC:"OR102 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6335,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR102",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR102",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6335,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR102", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7335 ,protection_group_id: -6335, protection_element_id:-6335], primaryKey: false);
      insert('organizations', [ id: 106321, nci_institute_code: "OR103", name: "Northwest Cancer Specialists PC - Northwest Office", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6336,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR103",GROUP_DESC:"OR103 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6336,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR103",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR103",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6336,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR103", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7336 ,protection_group_id: -6336, protection_element_id:-6336], primaryKey: false);
      insert('organizations', [ id: 106322, nci_institute_code: "OR104", name: "Starr-Wood Cardiac Group PC", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6337,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR104",GROUP_DESC:"OR104 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6337,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR104",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR104",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6337,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR104", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7337 ,protection_group_id: -6337, protection_element_id:-6337], primaryKey: false);
    }

    void m13() {
        // all13 (25 terms)
      insert('organizations', [ id: 106323, nci_institute_code: "OR105", name: "Pacific Oncology PC", city: "Beaverton", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6338,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR105",GROUP_DESC:"OR105 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6338,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR105",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR105",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6338,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR105", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7338 ,protection_group_id: -6338, protection_element_id:-6338], primaryKey: false);
      insert('organizations', [ id: 106324, nci_institute_code: "OR106", name: "Celilo Center for Cancer Care", city: "The Dalles", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6339,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR106",GROUP_DESC:"OR106 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6339,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR106",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR106",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6339,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR106", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7339 ,protection_group_id: -6339, protection_element_id:-6339], primaryKey: false);
      insert('organizations', [ id: 106325, nci_institute_code: "OR107", name: "Adventist Medical Center", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6340,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR107",GROUP_DESC:"OR107 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6340,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR107",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR107",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6340,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR107", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7340 ,protection_group_id: -6340, protection_element_id:-6340], primaryKey: false);
      insert('organizations', [ id: 106326, nci_institute_code: "OR108", name: "NW Surgical Oncology", city: "Portland", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6341,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR108",GROUP_DESC:"OR108 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6341,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR108",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR108",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6341,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR108", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7341 ,protection_group_id: -6341, protection_element_id:-6341], primaryKey: false);
      insert('organizations', [ id: 106327, nci_institute_code: "OR109", name: "Hillsboro Hematology and Oncology PC", city: "Hillsboro", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6342,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR109",GROUP_DESC:"OR109 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6342,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR109",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR109",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6342,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR109", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7342 ,protection_group_id: -6342, protection_element_id:-6342], primaryKey: false);
      insert('organizations', [ id: 106328, nci_institute_code: "OR110", name: "Pacific Oncology PC-Mount Hood", city: "Gresham", state: "OR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6343,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR110",GROUP_DESC:"OR110 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6343,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OR110",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OR110",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6343,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OR110", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7343 ,protection_group_id: -6343, protection_element_id:-6343], primaryKey: false);
      insert('organizations', [ id: 106329, nci_institute_code: "ORDER", name: "Temp Address Used for Legacy Migration of Orders", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6344,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ORDER",GROUP_DESC:"ORDER group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6344,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ORDER",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ORDER",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6344,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ORDER", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7344 ,protection_group_id: -6344, protection_element_id:-6344], primaryKey: false);
      insert('organizations', [ id: 106330, nci_institute_code: "ORGTEK", name: "Organon-Teknika", city: "Durham", state: "NC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6345,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ORGTEK",GROUP_DESC:"ORGTEK group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6345,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.ORGTEK",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.ORGTEK",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6345,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.ORGTEK", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7345 ,protection_group_id: -6345, protection_element_id:-6345], primaryKey: false);
      insert('organizations', [ id: 106331, nci_institute_code: "OYSTER", name: "Oyster Point Surgical Associates, Professional Corporation", city: "Newport News", state: "VA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6346,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OYSTER",GROUP_DESC:"OYSTER group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6346,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.OYSTER",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.OYSTER",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6346,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.OYSTER", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7346 ,protection_group_id: -6346, protection_element_id:-6346], primaryKey: false);
      insert('organizations', [ id: 106332, nci_institute_code: "PA001", name: "UPMC - Heritage Valley Health System - The Medical Center - Beaver", city: "Beaver", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6347,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA001",GROUP_DESC:"PA001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6347,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6347,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7347 ,protection_group_id: -6347, protection_element_id:-6347], primaryKey: false);
      insert('organizations', [ id: 106333, nci_institute_code: "PA002", name: "Alle-Kiski Medical Center", city: "Natrona Heights", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6348,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA002",GROUP_DESC:"PA002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6348,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6348,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7348 ,protection_group_id: -6348, protection_element_id:-6348], primaryKey: false);
      insert('organizations', [ id: 106334, nci_institute_code: "PA003", name: "Citizens General Hospital", city: "New Kingston", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6349,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA003",GROUP_DESC:"PA003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6349,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6349,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7349 ,protection_group_id: -6349, protection_element_id:-6349], primaryKey: false);
      insert('organizations', [ id: 106335, nci_institute_code: "PA004", name: "Braddock General Hospital", city: "Braddock", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6350,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA004",GROUP_DESC:"PA004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6350,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6350,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7350 ,protection_group_id: -6350, protection_element_id:-6350], primaryKey: false);
      insert('organizations', [ id: 106336, nci_institute_code: "PA005", name: "Mckeesport Hospital and Medical Center", city: "McKeesport", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6351,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA005",GROUP_DESC:"PA005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6351,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6351,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7351 ,protection_group_id: -6351, protection_element_id:-6351], primaryKey: false);
      insert('organizations', [ id: 106337, nci_institute_code: "PA006", name: "Sewickley Valley Hospital", city: "Sewickley", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6352,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA006",GROUP_DESC:"PA006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6352,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6352,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7352 ,protection_group_id: -6352, protection_element_id:-6352], primaryKey: false);
      insert('organizations', [ id: 106338, nci_institute_code: "PA007", name: "Forbes Regional Hospital", city: "Monroeville", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6353,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA007",GROUP_DESC:"PA007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6353,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6353,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7353 ,protection_group_id: -6353, protection_element_id:-6353], primaryKey: false);
      insert('organizations', [ id: 106339, nci_institute_code: "PA008", name: "Saint Francis Health System", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6354,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA008",GROUP_DESC:"PA008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6354,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6354,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7354 ,protection_group_id: -6354, protection_element_id:-6354], primaryKey: false);
      insert('organizations', [ id: 106340, nci_institute_code: "PA009", name: "Allegheny Cancer Center Network", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6355,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA009",GROUP_DESC:"PA009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6355,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6355,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7355 ,protection_group_id: -6355, protection_element_id:-6355], primaryKey: false);
      insert('organizations', [ id: 106341, nci_institute_code: "PA010", name: "Childrens Hospital of Pittsburgh", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6356,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA010",GROUP_DESC:"PA010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6356,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6356,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7356 ,protection_group_id: -6356, protection_element_id:-6356], primaryKey: false);
      insert('organizations', [ id: 106342, nci_institute_code: "PA011", name: "Magee-Womens Hospital - University of Pittsburgh Medical Center", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6357,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA011",GROUP_DESC:"PA011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6357,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6357,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7357 ,protection_group_id: -6357, protection_element_id:-6357], primaryKey: false);
      insert('organizations', [ id: 106343, nci_institute_code: "PA012", name: "The Eye and Ear Institute", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6358,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA012",GROUP_DESC:"PA012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6358,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6358,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7358 ,protection_group_id: -6358, protection_element_id:-6358], primaryKey: false);
      insert('organizations', [ id: 106344, nci_institute_code: "PA013", name: "Montefiore Hospital", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6359,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA013",GROUP_DESC:"PA013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6359,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6359,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7359 ,protection_group_id: -6359, protection_element_id:-6359], primaryKey: false);
      insert('organizations', [ id: 106345, nci_institute_code: "PA014", name: "University of Pittsburgh Medical Center-Presbyterian Hospital", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6360,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA014",GROUP_DESC:"PA014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6360,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6360,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7360 ,protection_group_id: -6360, protection_element_id:-6360], primaryKey: false);
      insert('organizations', [ id: 106346, nci_institute_code: "PA015", name: "University of Pittsburgh", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6361,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA015",GROUP_DESC:"PA015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6361,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6361,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7361 ,protection_group_id: -6361, protection_element_id:-6361], primaryKey: false);
      insert('organizations', [ id: 106347, nci_institute_code: "PA016", name: "Roger Williams General Hospital", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6362,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA016",GROUP_DESC:"PA016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6362,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6362,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7362 ,protection_group_id: -6362, protection_element_id:-6362], primaryKey: false);
    }

    void m14() {
        // all14 (25 terms)
      insert('organizations', [ id: 106348, nci_institute_code: "PA017", name: "Mercy Hospital", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6363,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA017",GROUP_DESC:"PA017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6363,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6363,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7363 ,protection_group_id: -6363, protection_element_id:-6363], primaryKey: false);
      insert('organizations', [ id: 106349, nci_institute_code: "PA018", name: "Western Pennsylvania Hospital", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6364,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA018",GROUP_DESC:"PA018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6364,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6364,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7364 ,protection_group_id: -6364, protection_element_id:-6364], primaryKey: false);
      insert('organizations', [ id: 106350, nci_institute_code: "PA019", name: "Medical Center Clinic: West Penn Office", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6365,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA019",GROUP_DESC:"PA019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6365,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6365,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7365 ,protection_group_id: -6365, protection_element_id:-6365], primaryKey: false);
      insert('organizations', [ id: 106351, nci_institute_code: "PA020", name: "University of Pittsburgh Medical Center - Shadyside Hospital", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6366,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA020",GROUP_DESC:"PA020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6366,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6366,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7366 ,protection_group_id: -6366, protection_element_id:-6366], primaryKey: false);
      insert('organizations', [ id: 106352, nci_institute_code: "PA021", name: "Southwestern Health Center", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6367,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA021",GROUP_DESC:"PA021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6367,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6367,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7367 ,protection_group_id: -6367, protection_element_id:-6367], primaryKey: false);
      insert('organizations', [ id: 106353, nci_institute_code: "PA022", name: "VA Pittsburgh Healthcare System", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6368,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA022",GROUP_DESC:"PA022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6368,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6368,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7368 ,protection_group_id: -6368, protection_element_id:-6368], primaryKey: false);
      insert('organizations', [ id: 106354, nci_institute_code: "PA023", name: "Saint Clair Hospital", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6369,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA023",GROUP_DESC:"PA023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6369,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6369,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7369 ,protection_group_id: -6369, protection_element_id:-6369], primaryKey: false);
      insert('organizations', [ id: 106355, nci_institute_code: "PA024", name: "Canonsburg General Hospital", city: "Cannonsburg", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6370,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA024",GROUP_DESC:"PA024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6370,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6370,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7370 ,protection_group_id: -6370, protection_element_id:-6370], primaryKey: false);
      insert('organizations', [ id: 106356, nci_institute_code: "PA025", name: "Westmoreland Hospital", city: "Greensburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6371,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA025",GROUP_DESC:"PA025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6371,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6371,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7371 ,protection_group_id: -6371, protection_element_id:-6371], primaryKey: false);
      insert('organizations', [ id: 106357, nci_institute_code: "PA026", name: "Latrobe Hospital", city: "Latrobe", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6372,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA026",GROUP_DESC:"PA026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6372,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6372,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7372 ,protection_group_id: -6372, protection_element_id:-6372], primaryKey: false);
      insert('organizations', [ id: 106358, nci_institute_code: "PA027", name: "Lee Hospital", city: "Johnstown", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6373,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA027",GROUP_DESC:"PA027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6373,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6373,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7373 ,protection_group_id: -6373, protection_element_id:-6373], primaryKey: false);
      insert('organizations', [ id: 106359, nci_institute_code: "PA028", name: "Conemaugh Memorial Medical Center", city: "Johnstown", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6374,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA028",GROUP_DESC:"PA028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6374,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6374,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7374 ,protection_group_id: -6374, protection_element_id:-6374], primaryKey: false);
      insert('organizations', [ id: 106360, nci_institute_code: "PA029", name: "Cambria-Somerset Reg Onc Fac", city: "Johnstown", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6375,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA029",GROUP_DESC:"PA029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6375,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6375,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7375 ,protection_group_id: -6375, protection_element_id:-6375], primaryKey: false);
      insert('organizations', [ id: 106361, nci_institute_code: "PA030", name: "Horizon Hospital System", city: "Greenville", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6376,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA030",GROUP_DESC:"PA030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6376,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6376,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7376 ,protection_group_id: -6376, protection_element_id:-6376], primaryKey: false);
      insert('organizations', [ id: 106362, nci_institute_code: "PA032", name: "Warren General Hospital", city: "Warren", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6377,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA032",GROUP_DESC:"PA032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6377,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6377,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7377 ,protection_group_id: -6377, protection_element_id:-6377], primaryKey: false);
      insert('organizations', [ id: 106363, nci_institute_code: "PA033", name: "Saint Vincent Health Center", city: "Erie", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6378,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA033",GROUP_DESC:"PA033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6378,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6378,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7378 ,protection_group_id: -6378, protection_element_id:-6378], primaryKey: false);
      insert('organizations', [ id: 106364, nci_institute_code: "PA034", name: "Hamot Medical Center", city: "Erie", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6379,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA034",GROUP_DESC:"PA034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6379,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6379,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7379 ,protection_group_id: -6379, protection_element_id:-6379], primaryKey: false);
      insert('organizations', [ id: 106365, nci_institute_code: "PA035", name: "Mercy Hospital", city: "Altoona", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6380,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA035",GROUP_DESC:"PA035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6380,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6380,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7380 ,protection_group_id: -6380, protection_element_id:-6380], primaryKey: false);
      insert('organizations', [ id: 106366, nci_institute_code: "PA036", name: "JC Blair Memorial Hospital Laboratory", city: "Huntingdon", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6381,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA036",GROUP_DESC:"PA036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6381,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6381,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7381 ,protection_group_id: -6381, protection_element_id:-6381], primaryKey: false);
      insert('organizations', [ id: 106367, nci_institute_code: "PA037", name: "Mount Nittany Medical Center", city: "State College", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6382,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA037",GROUP_DESC:"PA037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6382,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6382,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7382 ,protection_group_id: -6382, protection_element_id:-6382], primaryKey: false);
      insert('organizations', [ id: 106368, nci_institute_code: "PA038", name: "Holy Spirit Hospital", city: "Camp Hill", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6383,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA038",GROUP_DESC:"PA038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6383,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6383,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7383 ,protection_group_id: -6383, protection_element_id:-6383], primaryKey: false);
      insert('organizations', [ id: 106369, nci_institute_code: "PA039", name: "Dunham Army Hospital", city: "Carlisle", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6384,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA039",GROUP_DESC:"PA039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6384,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6384,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7384 ,protection_group_id: -6384, protection_element_id:-6384], primaryKey: false);
      insert('organizations', [ id: 106370, nci_institute_code: "PA040", name: "Oncology/Hematology Associates", city: "Washington", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6385,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA040",GROUP_DESC:"PA040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6385,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6385,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7385 ,protection_group_id: -6385, protection_element_id:-6385], primaryKey: false);
      insert('organizations', [ id: 106371, nci_institute_code: "PA041", name: "Carlisle Hospital Cancer Center", city: "Carlisle", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6386,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA041",GROUP_DESC:"PA041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6386,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6386,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7386 ,protection_group_id: -6386, protection_element_id:-6386], primaryKey: false);
      insert('organizations', [ id: 106372, nci_institute_code: "PA042", name: "Penn State Milton S. Hershey Medical Center", city: "Hershey", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6387,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA042",GROUP_DESC:"PA042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6387,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6387,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7387 ,protection_group_id: -6387, protection_element_id:-6387], primaryKey: false);
    }

    void m15() {
        // all15 (25 terms)
      insert('organizations', [ id: 106373, nci_institute_code: "PA043", name: "Lewistown Hospital", city: "Lewistown", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6388,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA043",GROUP_DESC:"PA043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6388,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6388,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7388 ,protection_group_id: -6388, protection_element_id:-6388], primaryKey: false);
      insert('organizations', [ id: 106374, nci_institute_code: "PA044", name: "Harrisburg Hospital", city: "Harrisburg", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6389,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA044",GROUP_DESC:"PA044 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6389,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA044",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA044",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6389,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA044", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7389 ,protection_group_id: -6389, protection_element_id:-6389], primaryKey: false);
      insert('organizations', [ id: 106375, nci_institute_code: "PA045", name: "PinnacleHealth Regional Cancer Center at Polyclinic Hospital", city: "Harrisburg", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6390,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA045",GROUP_DESC:"PA045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6390,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6390,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7390 ,protection_group_id: -6390, protection_element_id:-6390], primaryKey: false);
      insert('organizations', [ id: 106376, nci_institute_code: "PA046", name: "Chambersburg Hospital", city: "Chambersburg", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6391,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA046",GROUP_DESC:"PA046 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6391,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA046",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA046",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6391,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA046", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7391 ,protection_group_id: -6391, protection_element_id:-6391], primaryKey: false);
      insert('organizations', [ id: 106377, nci_institute_code: "PA047", name: "York Hospital", city: "York", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6392,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA047",GROUP_DESC:"PA047 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6392,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA047",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA047",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6392,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA047", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7392 ,protection_group_id: -6392, protection_element_id:-6392], primaryKey: false);
      insert('organizations', [ id: 106378, nci_institute_code: "PA048", name: "Lancaster General Hospital", city: "Lancaster", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6393,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA048",GROUP_DESC:"PA048 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6393,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA048",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA048",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6393,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA048", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7393 ,protection_group_id: -6393, protection_element_id:-6393], primaryKey: false);
      insert('organizations', [ id: 106379, nci_institute_code: "PA049", name: "Saint Joseph Hospital", city: "Lancaster", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6394,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA049",GROUP_DESC:"PA049 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6394,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA049",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA049",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6394,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA049", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7394 ,protection_group_id: -6394, protection_element_id:-6394], primaryKey: false);
      insert('organizations', [ id: 106380, nci_institute_code: "PA050", name: "Divine Providence Hospital", city: "Williamsport", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6395,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA050",GROUP_DESC:"PA050 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6395,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA050",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA050",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6395,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA050", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7395 ,protection_group_id: -6395, protection_element_id:-6395], primaryKey: false);
      insert('organizations', [ id: 106381, nci_institute_code: "PA051", name: "Williamsport Hospital and Medical Center", city: "Williamsport", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6396,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA051",GROUP_DESC:"PA051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6396,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6396,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7396 ,protection_group_id: -6396, protection_element_id:-6396], primaryKey: false);
      insert('organizations', [ id: 106382, nci_institute_code: "PA052", name: "Geisinger Medical Center", city: "Danville", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6397,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA052",GROUP_DESC:"PA052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6397,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6397,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7397 ,protection_group_id: -6397, protection_element_id:-6397], primaryKey: false);
      insert('organizations', [ id: 106383, nci_institute_code: "PA053", name: "Bucknell University", city: "Lewisburg", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6398,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA053",GROUP_DESC:"PA053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6398,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6398,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7398 ,protection_group_id: -6398, protection_element_id:-6398], primaryKey: false);
      insert('organizations', [ id: 106384, nci_institute_code: "PA054", name: "Saint Luke's Hospital", city: "Bethlehem", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6399,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA054",GROUP_DESC:"PA054 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6399,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA054",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA054",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6399,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA054", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7399 ,protection_group_id: -6399, protection_element_id:-6399], primaryKey: false);
      insert('organizations', [ id: 106385, nci_institute_code: "PA055", name: "Lehigh Valley Hospital", city: "Allentown", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6400,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA055",GROUP_DESC:"PA055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6400,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6400,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7400 ,protection_group_id: -6400, protection_element_id:-6400], primaryKey: false);
      insert('organizations', [ id: 106386, nci_institute_code: "PA056", name: "Sacred Heart Hospital", city: "Allentown", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6401,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA056",GROUP_DESC:"PA056 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6401,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA056",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA056",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6401,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA056", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7401 ,protection_group_id: -6401, protection_element_id:-6401], primaryKey: false);
      insert('organizations', [ id: 106387, nci_institute_code: "PA057", name: "Mercy Hospital", city: "Scranton", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6402,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA057",GROUP_DESC:"PA057 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6402,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA057",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA057",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6402,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA057", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7402 ,protection_group_id: -6402, protection_element_id:-6402], primaryKey: false);
      insert('organizations', [ id: 106388, nci_institute_code: "PA058", name: "Moses Taylor Hospital", city: "Scranton", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6403,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA058",GROUP_DESC:"PA058 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6403,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA058",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA058",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6403,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA058", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7403 ,protection_group_id: -6403, protection_element_id:-6403], primaryKey: false);
      insert('organizations', [ id: 106389, nci_institute_code: "PA059", name: "Veterans Administration Hospital", city: "Wilkes Barre", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6404,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA059",GROUP_DESC:"PA059 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6404,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA059",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA059",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6404,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA059", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7404 ,protection_group_id: -6404, protection_element_id:-6404], primaryKey: false);
      insert('organizations', [ id: 106390, nci_institute_code: "PA060", name: "Wilkes-Barre General Hospital", city: "Wilkes-Barre", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6405,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA060",GROUP_DESC:"PA060 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6405,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA060",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA060",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6405,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA060", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7405 ,protection_group_id: -6405, protection_element_id:-6405], primaryKey: false);
      insert('organizations', [ id: 106391, nci_institute_code: "PA061", name: "Mercy Hospital", city: "Wilkes-Barre", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6406,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA061",GROUP_DESC:"PA061 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6406,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA061",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA061",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6406,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA061", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7406 ,protection_group_id: -6406, protection_element_id:-6406], primaryKey: false);
      insert('organizations', [ id: 106392, nci_institute_code: "PA062", name: "Guthrie Clinic - Sayre", city: "Sayre", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6407,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA062",GROUP_DESC:"PA062 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6407,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA062",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA062",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6407,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA062", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7407 ,protection_group_id: -6407, protection_element_id:-6407], primaryKey: false);
      insert('organizations', [ id: 106393, nci_institute_code: "PA063", name: "Grand View Hospital", city: "Sellersville", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6408,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA063",GROUP_DESC:"PA063 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6408,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA063",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA063",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6408,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA063", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7408 ,protection_group_id: -6408, protection_element_id:-6408], primaryKey: false);
      insert('organizations', [ id: 106394, nci_institute_code: "PA064", name: "Delaware Valley Hospital", city: "Bristol", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6409,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA064",GROUP_DESC:"PA064 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6409,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA064",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA064",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6409,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA064", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7409 ,protection_group_id: -6409, protection_element_id:-6409], primaryKey: false);
      insert('organizations', [ id: 106395, nci_institute_code: "PA065", name: "Delaware Country Medical Center", city: "Broomall", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6410,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA065",GROUP_DESC:"PA065 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6410,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA065",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA065",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6410,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA065", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7410 ,protection_group_id: -6410, protection_element_id:-6410], primaryKey: false);
      insert('organizations', [ id: 106396, nci_institute_code: "PA066", name: "Bryn Mawr Hospital", city: "Bryn Mawr", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6411,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA066",GROUP_DESC:"PA066 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6411,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA066",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA066",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6411,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA066", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7411 ,protection_group_id: -6411, protection_element_id:-6411], primaryKey: false);
      insert('organizations', [ id: 106397, nci_institute_code: "PA067", name: "Crozer-Chester Medical Center", city: "Upland", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6412,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA067",GROUP_DESC:"PA067 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6412,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA067",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA067",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6412,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA067", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7412 ,protection_group_id: -6412, protection_element_id:-6412], primaryKey: false);
    }

    void m16() {
        // all16 (25 terms)
      insert('organizations', [ id: 106398, nci_institute_code: "PA068", name: "Mercy Fitzgerald Hospital", city: "Darby", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6413,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA068",GROUP_DESC:"PA068 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6413,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA068",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA068",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6413,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA068", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7413 ,protection_group_id: -6413, protection_element_id:-6413], primaryKey: false);
      insert('organizations', [ id: 106399, nci_institute_code: "PA069", name: "Delaware County Memorial Hospital", city: "Drexel Hill", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6414,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA069",GROUP_DESC:"PA069 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6414,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA069",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA069",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6414,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA069", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7414 ,protection_group_id: -6414, protection_element_id:-6414], primaryKey: false);
      insert('organizations', [ id: 106400, nci_institute_code: "PA070", name: "Delaware Valley Medical Center", city: "Langhorne", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6415,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA070",GROUP_DESC:"PA070 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6415,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA070",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA070",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6415,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA070", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7415 ,protection_group_id: -6415, protection_element_id:-6415], primaryKey: false);
      insert('organizations', [ id: 106401, nci_institute_code: "PA071", name: "Riddle Memorial Hospital", city: "Media", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6416,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA071",GROUP_DESC:"PA071 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6416,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA071",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA071",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6416,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA071", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7416 ,protection_group_id: -6416, protection_element_id:-6416], primaryKey: false);
      insert('organizations', [ id: 106402, nci_institute_code: "PA072", name: "Mercy Community Hospital", city: "Havertown", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6417,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA072",GROUP_DESC:"PA072 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6417,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA072",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA072",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6417,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA072", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7417 ,protection_group_id: -6417, protection_element_id:-6417], primaryKey: false);
      insert('organizations', [ id: 106403, nci_institute_code: "PA073", name: "Wyeth Laboratories Incorporated.,", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6418,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA073",GROUP_DESC:"PA073 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6418,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA073",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA073",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6418,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA073", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7418 ,protection_group_id: -6418, protection_element_id:-6418], primaryKey: false);
      insert('organizations', [ id: 106404, nci_institute_code: "PA074", name: "Drexel University School of Medicine", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6419,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA074",GROUP_DESC:"PA074 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6419,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA074",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA074",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6419,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA074", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7419 ,protection_group_id: -6419, protection_element_id:-6419], primaryKey: false);
      insert('organizations', [ id: 106405, nci_institute_code: "PA075", name: "Abramson Cancer Center of The University of Pennsylvania", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6420,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA075",GROUP_DESC:"PA075 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6420,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA075",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA075",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6420,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA075", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7420 ,protection_group_id: -6420, protection_element_id:-6420], primaryKey: false);
      insert('organizations', [ id: 106406, nci_institute_code: "PA076", name: "Children's Hospital of Philadelphia", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6421,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA076",GROUP_DESC:"PA076 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6421,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA076",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA076",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6421,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA076", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7421 ,protection_group_id: -6421, protection_element_id:-6421], primaryKey: false);
      insert('organizations', [ id: 106407, nci_institute_code: "PA077", name: "Wistar Institute/Anatomy & Biol", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6422,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA077",GROUP_DESC:"PA077 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6422,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA077",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA077",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6422,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA077", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7422 ,protection_group_id: -6422, protection_element_id:-6422], primaryKey: false);
      insert('organizations', [ id: 106408, nci_institute_code: "PA078", name: "Penn Presbyterian Medical Center", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6423,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA078",GROUP_DESC:"PA078 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6423,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA078",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA078",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6423,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA078", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7423 ,protection_group_id: -6423, protection_element_id:-6423], primaryKey: false);
      insert('organizations', [ id: 106409, nci_institute_code: "PA079", name: "Metropolitan Hospital", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6424,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA079",GROUP_DESC:"PA079 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6424,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA079",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA079",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6424,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA079", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7424 ,protection_group_id: -6424, protection_element_id:-6424], primaryKey: false);
      insert('organizations', [ id: 106410, nci_institute_code: "PA080", name: "Elk Regional Health Center of Saint Mary", city: "St. Marys", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6425,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA080",GROUP_DESC:"PA080 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6425,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA080",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA080",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6425,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA080", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7425 ,protection_group_id: -6425, protection_element_id:-6425], primaryKey: false);
      insert('organizations', [ id: 106411, nci_institute_code: "PA081", name: "Jefferson Medical College", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6426,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA081",GROUP_DESC:"PA081 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6426,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA081",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA081",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6426,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA081", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7426 ,protection_group_id: -6426, protection_element_id:-6426], primaryKey: false);
      insert('organizations', [ id: 106412, nci_institute_code: "PA082", name: "Philadelphia Veterans Administration Medical Center", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6427,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA082",GROUP_DESC:"PA082 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6427,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA082",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA082",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6427,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA082", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7427 ,protection_group_id: -6427, protection_element_id:-6427], primaryKey: false);
      insert('organizations', [ id: 106413, nci_institute_code: "PA083", name: "American College of Radiology", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6428,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA083",GROUP_DESC:"PA083 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6428,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA083",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA083",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6428,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA083", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7428 ,protection_group_id: -6428, protection_element_id:-6428], primaryKey: false);
      insert('organizations', [ id: 106414, nci_institute_code: "PA084", name: "Cowley Medical Associates", city: "Camphill", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6429,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA084",GROUP_DESC:"PA084 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6429,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA084",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA084",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6429,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA084", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7429 ,protection_group_id: -6429, protection_element_id:-6429], primaryKey: false);
      insert('organizations', [ id: 106415, nci_institute_code: "PA085", name: "General Surgical Associates., Ltd.", city: "Allentown", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6430,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA085",GROUP_DESC:"PA085 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6430,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA085",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA085",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6430,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA085", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7430 ,protection_group_id: -6430, protection_element_id:-6430], primaryKey: false);
      insert('organizations', [ id: 106416, nci_institute_code: "PA086", name: "Fox Chase Cancer Center", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6431,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA086",GROUP_DESC:"PA086 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6431,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA086",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA086",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6431,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA086", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7431 ,protection_group_id: -6431, protection_element_id:-6431], primaryKey: false);
      insert('organizations', [ id: 106417, nci_institute_code: "PA087", name: "Jeanes Hospital", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6432,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA087",GROUP_DESC:"PA087 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6432,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA087",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA087",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6432,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA087", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7432 ,protection_group_id: -6432, protection_element_id:-6432], primaryKey: false);
      insert('organizations', [ id: 106418, nci_institute_code: "PA088", name: "American Oncologic Hospital", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6433,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA088",GROUP_DESC:"PA088 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6433,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA088",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA088",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6433,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA088", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7433 ,protection_group_id: -6433, protection_element_id:-6433], primaryKey: false);
      insert('organizations', [ id: 106419, nci_institute_code: "PA089", name: "Chestnut Hill Health System", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6434,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA089",GROUP_DESC:"PA089 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6434,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA089",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA089",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6434,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA089", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7434 ,protection_group_id: -6434, protection_element_id:-6434], primaryKey: false);
      insert('organizations', [ id: 106420, nci_institute_code: "PA090", name: "Episcopal Hospital", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6435,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA090",GROUP_DESC:"PA090 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6435,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA090",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA090",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6435,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA090", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7435 ,protection_group_id: -6435, protection_element_id:-6435], primaryKey: false);
      insert('organizations', [ id: 106421, nci_institute_code: "PA091", name: "Allegheny University East Falls", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6436,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA091",GROUP_DESC:"PA091 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6436,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA091",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA091",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6436,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA091", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7436 ,protection_group_id: -6436, protection_element_id:-6436], primaryKey: false);
      insert('organizations', [ id: 106422, nci_institute_code: "PA092", name: "St. Christopher's Hospital for Children", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6437,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA092",GROUP_DESC:"PA092 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6437,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA092",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA092",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6437,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA092", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7437 ,protection_group_id: -6437, protection_element_id:-6437], primaryKey: false);
    }

    void m17() {
        // all17 (25 terms)
      insert('organizations', [ id: 106423, nci_institute_code: "PA093", name: "Temple University Hospital", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6438,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA093",GROUP_DESC:"PA093 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6438,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA093",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA093",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6438,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA093", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7438 ,protection_group_id: -6438, protection_element_id:-6438], primaryKey: false);
      insert('organizations', [ id: 106424, nci_institute_code: "PA094", name: "Albert Einstein Medical Center", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6439,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA094",GROUP_DESC:"PA094 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6439,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA094",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA094",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6439,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA094", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7439 ,protection_group_id: -6439, protection_element_id:-6439], primaryKey: false);
      insert('organizations', [ id: 106425, nci_institute_code: "PA095", name: "Mercy Hospital of Philadelphia", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6440,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA095",GROUP_DESC:"PA095 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6440,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA095",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA095",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6440,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA095", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7440 ,protection_group_id: -6440, protection_element_id:-6440], primaryKey: false);
      insert('organizations', [ id: 106426, nci_institute_code: "PA096", name: "Misericordia Hospital", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6441,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA096",GROUP_DESC:"PA096 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6441,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA096",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA096",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6441,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA096", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7441 ,protection_group_id: -6441, protection_element_id:-6441], primaryKey: false);
      insert('organizations', [ id: 106427, nci_institute_code: "PA097", name: "Germantown Hospital", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6442,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA097",GROUP_DESC:"PA097 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6442,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA097",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA097",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6442,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA097", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7442 ,protection_group_id: -6442, protection_element_id:-6442], primaryKey: false);
      insert('organizations', [ id: 106428, nci_institute_code: "PA098", name: "Naval Hospital", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6443,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA098",GROUP_DESC:"PA098 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6443,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA098",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA098",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6443,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA098", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7443 ,protection_group_id: -6443, protection_element_id:-6443], primaryKey: false);
      insert('organizations', [ id: 106429, nci_institute_code: "PA099", name: "Graduate Hospital", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6444,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA099",GROUP_DESC:"PA099 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6444,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA099",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA099",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6444,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA099", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7444 ,protection_group_id: -6444, protection_element_id:-6444], primaryKey: false);
      insert('organizations', [ id: 106430, nci_institute_code: "PA101", name: "Nazareth Hospital", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6445,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA101",GROUP_DESC:"PA101 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6445,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA101",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA101",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6445,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA101", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7445 ,protection_group_id: -6445, protection_element_id:-6445], primaryKey: false);
      insert('organizations', [ id: 106431, nci_institute_code: "PA102", name: "Chester County Hospital", city: "West Chester", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6446,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA102",GROUP_DESC:"PA102 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6446,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA102",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA102",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6446,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA102", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7446 ,protection_group_id: -6446, protection_element_id:-6446], primaryKey: false);
      insert('organizations', [ id: 106432, nci_institute_code: "PA103", name: "Montgomery Hospital Medical Center", city: "Norristown", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6447,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA103",GROUP_DESC:"PA103 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6447,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA103",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA103",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6447,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA103", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7447 ,protection_group_id: -6447, protection_element_id:-6447], primaryKey: false);
      insert('organizations', [ id: 106433, nci_institute_code: "PA104", name: "Mercy Suburban Hospital", city: "Norristown", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6448,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA104",GROUP_DESC:"PA104 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6448,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA104",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA104",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6448,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA104", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7448 ,protection_group_id: -6448, protection_element_id:-6448], primaryKey: false);
      insert('organizations', [ id: 106434, nci_institute_code: "PA105", name: "Phoenixville Hospital", city: "Phoenixville", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6449,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA105",GROUP_DESC:"PA105 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6449,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA105",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA105",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6449,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA105", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7449 ,protection_group_id: -6449, protection_element_id:-6449], primaryKey: false);
      insert('organizations', [ id: 106435, nci_institute_code: "PA106", name: "Merck, Sharpe and Dohme FCU", city: "West Point", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6450,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA106",GROUP_DESC:"PA106 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6450,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA106",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA106",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6450,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA106", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7450 ,protection_group_id: -6450, protection_element_id:-6450], primaryKey: false);
      insert('organizations', [ id: 106436, nci_institute_code: "PA107", name: "Reading Hospital and Medical Center", city: "West Reading", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6451,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA107",GROUP_DESC:"PA107 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6451,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA107",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA107",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6451,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA107", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7451 ,protection_group_id: -6451, protection_element_id:-6451], primaryKey: false);
      insert('organizations', [ id: 106437, nci_institute_code: "PA108", name: "Saint Joseph Medical Center", city: "Reading", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6452,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA108",GROUP_DESC:"PA108 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6452,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA108",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA108",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6452,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA108", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7452 ,protection_group_id: -6452, protection_element_id:-6452], primaryKey: false);
      insert('organizations', [ id: 106438, nci_institute_code: "PA109", name: "Taylor Hospital", city: "Ridley Park", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6453,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA109",GROUP_DESC:"PA109 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6453,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA109",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA109",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6453,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA109", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7453 ,protection_group_id: -6453, protection_element_id:-6453], primaryKey: false);
      insert('organizations', [ id: 106439, nci_institute_code: "PA110", name: "University of Pittsburgh Cancer Institute", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6454,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA110",GROUP_DESC:"PA110 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6454,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA110",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA110",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6454,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA110", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7454 ,protection_group_id: -6454, protection_element_id:-6454], primaryKey: false);
      insert('organizations', [ id: 106440, nci_institute_code: "PA111", name: "Norristown Regional Cancer Center", city: "Norristown", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6455,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA111",GROUP_DESC:"PA111 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6455,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA111",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA111",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6455,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA111", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7455 ,protection_group_id: -6455, protection_element_id:-6455], primaryKey: false);
      insert('organizations', [ id: 106441, nci_institute_code: "PA112", name: "Jameson Health System North Campus", city: "New Castle", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6456,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA112",GROUP_DESC:"PA112 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6456,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA112",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA112",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6456,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA112", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7456 ,protection_group_id: -6456, protection_element_id:-6456], primaryKey: false);
      insert('organizations', [ id: 106442, nci_institute_code: "PA113", name: "Cancer Treatment Center", city: "Phoenixville", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6457,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA113",GROUP_DESC:"PA113 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6457,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA113",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA113",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6457,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA113", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7457 ,protection_group_id: -6457, protection_element_id:-6457], primaryKey: false);
      insert('organizations', [ id: 106443, nci_institute_code: "PA114", name: "Central Montgomery Medical Center", city: "Lansdale", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6458,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA114",GROUP_DESC:"PA114 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6458,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA114",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA114",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6458,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA114", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7458 ,protection_group_id: -6458, protection_element_id:-6458], primaryKey: false);
      insert('organizations', [ id: 106444, nci_institute_code: "PA115", name: "Hazleton General Hospital", city: "Hazleton", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6459,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA115",GROUP_DESC:"PA115 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6459,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA115",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA115",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6459,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA115", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7459 ,protection_group_id: -6459, protection_element_id:-6459], primaryKey: false);
      insert('organizations', [ id: 106445, nci_institute_code: "PA116", name: "Frankford Hospital", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6460,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA116",GROUP_DESC:"PA116 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6460,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA116",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA116",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6460,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA116", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7460 ,protection_group_id: -6460, protection_element_id:-6460], primaryKey: false);
      insert('organizations', [ id: 106446, nci_institute_code: "PA117", name: "Saint Mary Regional Cancer Center", city: "Langhorne", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6461,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA117",GROUP_DESC:"PA117 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6461,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA117",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA117",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6461,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA117", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7461 ,protection_group_id: -6461, protection_element_id:-6461], primaryKey: false);
      insert('organizations', [ id: 106447, nci_institute_code: "PA118", name: "The Regional Cancer Center", city: "Erie", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6462,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA118",GROUP_DESC:"PA118 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6462,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA118",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA118",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6462,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA118", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7462 ,protection_group_id: -6462, protection_element_id:-6462], primaryKey: false);
    }

    void m18() {
        // all18 (25 terms)
      insert('organizations', [ id: 106448, nci_institute_code: "PA119", name: "North Hills Passive Avant Hosp", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6463,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA119",GROUP_DESC:"PA119 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6463,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA119",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA119",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6463,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA119", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7463 ,protection_group_id: -6463, protection_element_id:-6463], primaryKey: false);
      insert('organizations', [ id: 106449, nci_institute_code: "PA120", name: "Abington Memorial Hospital", city: "Abington", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6464,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA120",GROUP_DESC:"PA120 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6464,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA120",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA120",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6464,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA120", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7464 ,protection_group_id: -6464, protection_element_id:-6464], primaryKey: false);
      insert('organizations', [ id: 106450, nci_institute_code: "PA121", name: "Thomas Jefferson University Hospital", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6465,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA121",GROUP_DESC:"PA121 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6465,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA121",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA121",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6465,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA121", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7465 ,protection_group_id: -6465, protection_element_id:-6465], primaryKey: false);
      insert('organizations', [ id: 106451, nci_institute_code: "PA122", name: "Saint Margaret Memorial Hospital", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6466,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA122",GROUP_DESC:"PA122 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6466,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA122",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA122",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6466,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA122", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7466 ,protection_group_id: -6466, protection_element_id:-6466], primaryKey: false);
      insert('organizations', [ id: 106452, nci_institute_code: "PA123", name: "Hanover General Hospital", city: "Hanover", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6467,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA123",GROUP_DESC:"PA123 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6467,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA123",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA123",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6467,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA123", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7467 ,protection_group_id: -6467, protection_element_id:-6467], primaryKey: false);
      insert('organizations', [ id: 106453, nci_institute_code: "PA124", name: "Paoli Memorial Hospital", city: "Paoli", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6468,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA124",GROUP_DESC:"PA124 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6468,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA124",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA124",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6468,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA124", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7468 ,protection_group_id: -6468, protection_element_id:-6468], primaryKey: false);
      insert('organizations', [ id: 106454, nci_institute_code: "PA125", name: "Lankenau Hospital", city: "Wynnewood", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6469,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA125",GROUP_DESC:"PA125 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6469,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA125",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA125",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6469,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA125", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7469 ,protection_group_id: -6469, protection_element_id:-6469], primaryKey: false);
      insert('organizations', [ id: 106455, nci_institute_code: "PA127", name: "Pottstown Memorial Medical Center", city: "Pottstown", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6470,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA127",GROUP_DESC:"PA127 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6470,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA127",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA127",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6470,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA127", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7470 ,protection_group_id: -6470, protection_element_id:-6470], primaryKey: false);
      insert('organizations', [ id: 106456, nci_institute_code: "PA128", name: "Doylestown Hospital", city: "Doylestown", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6471,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA128",GROUP_DESC:"PA128 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6471,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA128",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA128",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6471,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA128", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7471 ,protection_group_id: -6471, protection_element_id:-6471], primaryKey: false);
      insert('organizations', [ id: 106457, nci_institute_code: "PA129", name: "Mercy Hospital", city: "Johnstown", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6472,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA129",GROUP_DESC:"PA129 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6472,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA129",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA129",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6472,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA129", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7472 ,protection_group_id: -6472, protection_element_id:-6472], primaryKey: false);
      insert('organizations', [ id: 106458, nci_institute_code: "PA130", name: "Frick Hospital and Community Health Center", city: "Mount Pleasant", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6473,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA130",GROUP_DESC:"PA130 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6473,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA130",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA130",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6473,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA130", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7473 ,protection_group_id: -6473, protection_element_id:-6473], primaryKey: false);
      insert('organizations', [ id: 106459, nci_institute_code: "PA131", name: "Pennsylvania Hospital", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6474,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA131",GROUP_DESC:"PA131 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6474,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA131",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA131",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6474,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA131", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7474 ,protection_group_id: -6474, protection_element_id:-6474], primaryKey: false);
      insert('organizations', [ id: 106460, nci_institute_code: "PA133", name: "Dubois Regional Medical Center", city: "Dubois", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6475,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA133",GROUP_DESC:"PA133 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6475,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA133",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA133",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6475,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA133", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7475 ,protection_group_id: -6475, protection_element_id:-6475], primaryKey: false);
      insert('organizations', [ id: 106461, nci_institute_code: "PA134", name: "Monongahela Valley Hospital", city: "Monongahela", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6476,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA134",GROUP_DESC:"PA134 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6476,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA134",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA134",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6476,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA134", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7476 ,protection_group_id: -6476, protection_element_id:-6476], primaryKey: false);
      insert('organizations', [ id: 106462, nci_institute_code: "PA135", name: "Community General Osteopathic Hospital", city: "Harrisburg", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6477,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA135",GROUP_DESC:"PA135 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6477,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA135",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA135",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6477,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA135", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7477 ,protection_group_id: -6477, protection_element_id:-6477], primaryKey: false);
      insert('organizations', [ id: 106463, nci_institute_code: "PA136", name: "Community Medical Center", city: "Scranton", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6478,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA136",GROUP_DESC:"PA136 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6478,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA136",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA136",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6478,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA136", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7478 ,protection_group_id: -6478, protection_element_id:-6478], primaryKey: false);
      insert('organizations', [ id: 106464, nci_institute_code: "PA137", name: "Univerisity of Pittsburgh Medical Center Northwest", city: "Seneca", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6479,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA137",GROUP_DESC:"PA137 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6479,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA137",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA137",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6479,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA137", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7479 ,protection_group_id: -6479, protection_element_id:-6479], primaryKey: false);
      insert('organizations', [ id: 106465, nci_institute_code: "PA138", name: "Geisinger Wyoming Valley", city: "Wilkes-Barre", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6480,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA138",GROUP_DESC:"PA138 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6480,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA138",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA138",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6480,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA138", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7480 ,protection_group_id: -6480, protection_element_id:-6480], primaryKey: false);
      insert('organizations', [ id: 106466, nci_institute_code: "PA140", name: "United Community Hospital", city: "Grove City", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6481,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA140",GROUP_DESC:"PA140 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6481,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA140",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA140",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6481,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA140", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7481 ,protection_group_id: -6481, protection_element_id:-6481], primaryKey: false);
      insert('organizations', [ id: 106467, nci_institute_code: "PA141", name: "Hospital of The University of Pennsylvania", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6482,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA141",GROUP_DESC:"PA141 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6482,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA141",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA141",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6482,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA141", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7482 ,protection_group_id: -6482, protection_element_id:-6482], primaryKey: false);
      insert('organizations', [ id: 106468, nci_institute_code: "PA142", name: "Upper Delaware Valley Cancer Center", city: "Milford", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6483,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA142",GROUP_DESC:"PA142 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6483,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA142",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA142",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6483,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA142", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7483 ,protection_group_id: -6483, protection_element_id:-6483], primaryKey: false);
      insert('organizations', [ id: 106469, nci_institute_code: "PA143", name: "Armstrong Center for Medicine and Health", city: "Kittanning", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6484,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA143",GROUP_DESC:"PA143 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6484,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA143",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA143",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6484,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA143", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7484 ,protection_group_id: -6484, protection_element_id:-6484], primaryKey: false);
      insert('organizations', [ id: 106470, nci_institute_code: "PA144", name: "J & D Morgan Cancer Center", city: "Allentown", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6485,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA144",GROUP_DESC:"PA144 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6485,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA144",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA144",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6485,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA144", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7485 ,protection_group_id: -6485, protection_element_id:-6485], primaryKey: false);
      insert('organizations', [ id: 106471, nci_institute_code: "PA145", name: "Saint. Lawrence Medical Center", city: "Reading", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6486,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA145",GROUP_DESC:"PA145 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6486,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA145",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA145",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6486,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA145", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7486 ,protection_group_id: -6486, protection_element_id:-6486], primaryKey: false);
      insert('organizations', [ id: 106472, nci_institute_code: "PA146", name: "Washington Hospital", city: "Washington", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6487,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA146",GROUP_DESC:"PA146 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6487,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA146",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA146",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6487,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA146", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7487 ,protection_group_id: -6487, protection_element_id:-6487], primaryKey: false);
    }

    void m19() {
        // all19 (25 terms)
      insert('organizations', [ id: 106473, nci_institute_code: "PA147", name: "Medical Oncology Associates", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6488,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA147",GROUP_DESC:"PA147 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6488,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA147",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA147",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6488,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA147", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7488 ,protection_group_id: -6488, protection_element_id:-6488], primaryKey: false);
      insert('organizations', [ id: 106474, nci_institute_code: "PA148", name: "Uniontown Hospital", city: "Uniontown", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6489,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA148",GROUP_DESC:"PA148 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6489,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA148",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA148",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6489,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA148", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7489 ,protection_group_id: -6489, protection_element_id:-6489], primaryKey: false);
      insert('organizations', [ id: 106475, nci_institute_code: "PA149", name: "Windber Hospital", city: "Windber", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6490,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA149",GROUP_DESC:"PA149 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6490,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA149",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA149",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6490,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA149", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7490 ,protection_group_id: -6490, protection_element_id:-6490], primaryKey: false);
      insert('organizations', [ id: 106476, nci_institute_code: "PA150", name: "Marion Community Hospital", city: "Carbondale", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6491,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA150",GROUP_DESC:"PA150 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6491,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA150",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA150",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6491,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA150", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7491 ,protection_group_id: -6491, protection_element_id:-6491], primaryKey: false);
      insert('organizations', [ id: 106477, nci_institute_code: "PA151", name: "University of Pennsylvania Medical Center", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6492,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA151",GROUP_DESC:"PA151 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6492,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA151",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA151",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6492,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA151", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7492 ,protection_group_id: -6492, protection_element_id:-6492], primaryKey: false);
      insert('organizations', [ id: 106478, nci_institute_code: "PA152", name: "Wayne County Memorial Hospital", city: "Honesdale", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6493,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA152",GROUP_DESC:"PA152 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6493,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA152",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA152",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6493,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA152", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7493 ,protection_group_id: -6493, protection_element_id:-6493], primaryKey: false);
      insert('organizations', [ id: 106479, nci_institute_code: "PA153", name: "Hematology - Oncology Associates", city: "Allentown", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6494,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA153",GROUP_DESC:"PA153 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6494,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA153",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA153",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6494,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA153", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7494 ,protection_group_id: -6494, protection_element_id:-6494], primaryKey: false);
      insert('organizations', [ id: 106480, nci_institute_code: "PA154", name: "Cancer Center of Wyoming Valley", city: "Wilkes-Barre", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6495,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA154",GROUP_DESC:"PA154 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6495,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA154",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA154",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6495,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA154", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7495 ,protection_group_id: -6495, protection_element_id:-6495], primaryKey: false);
      insert('organizations', [ id: 106481, nci_institute_code: "PA155", name: "Hematology and Oncology Associates of North East Pennsylvania", city: "Scranton", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6496,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA155",GROUP_DESC:"PA155 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6496,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA155",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA155",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6496,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA155", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7496 ,protection_group_id: -6496, protection_element_id:-6496], primaryKey: false);
      insert('organizations', [ id: 106482, nci_institute_code: "PA156", name: "Radiation Medical Association of Scranton", city: "Scranton", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6497,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA156",GROUP_DESC:"PA156 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6497,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA156",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA156",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6497,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA156", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7497 ,protection_group_id: -6497, protection_element_id:-6497], primaryKey: false);
      insert('organizations', [ id: 106483, nci_institute_code: "PA157", name: "Gettysburg Hospital", city: "Gettysburg", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6498,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA157",GROUP_DESC:"PA157 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6498,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA157",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA157",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6498,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA157", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7498 ,protection_group_id: -6498, protection_element_id:-6498], primaryKey: false);
      insert('organizations', [ id: 106484, nci_institute_code: "PA158", name: "Easton Hospital", city: "Easton", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6499,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA158",GROUP_DESC:"PA158 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6499,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA158",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA158",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6499,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA158", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7499 ,protection_group_id: -6499, protection_element_id:-6499], primaryKey: false);
      insert('organizations', [ id: 106485, nci_institute_code: "PA159", name: "Central PA Hematology-Medical Oncology Associates PC", city: "Lemoyne", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6500,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA159",GROUP_DESC:"PA159 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6500,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA159",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA159",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6500,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA159", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7500 ,protection_group_id: -6500, protection_element_id:-6500], primaryKey: false);
      insert('organizations', [ id: 106486, nci_institute_code: "PA163", name: "Mainline Health CCOP", city: "Wynnewood", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6501,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA163",GROUP_DESC:"PA163 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6501,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA163",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA163",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6501,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA163", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7501 ,protection_group_id: -6501, protection_element_id:-6501], primaryKey: false);
      insert('organizations', [ id: 106487, nci_institute_code: "PA164", name: "Cancer Care Associates/York", city: "York", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6502,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA164",GROUP_DESC:"PA164 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6502,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA164",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA164",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6502,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA164", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7502 ,protection_group_id: -6502, protection_element_id:-6502], primaryKey: false);
      insert('organizations', [ id: 106488, nci_institute_code: "PA165", name: "Butler Memorial Hospital", city: "Butler", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6503,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA165",GROUP_DESC:"PA165 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6503,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA165",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA165",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6503,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA165", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7503 ,protection_group_id: -6503, protection_element_id:-6503], primaryKey: false);
      insert('organizations', [ id: 106489, nci_institute_code: "PA166", name: "Medical Center Clinic:Butler Office", city: "Butler", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6504,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA166",GROUP_DESC:"PA166 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6504,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA166",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA166",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6504,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA166", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7504 ,protection_group_id: -6504, protection_element_id:-6504], primaryKey: false);
      insert('organizations', [ id: 106490, nci_institute_code: "PA167", name: "Andrews and Patel Association P.C.", city: "Camphill", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6505,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA167",GROUP_DESC:"PA167 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6505,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA167",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA167",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6505,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA167", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7505 ,protection_group_id: -6505, protection_element_id:-6505], primaryKey: false);
      insert('organizations', [ id: 106491, nci_institute_code: "PA168", name: "Sharon Regional Health System", city: "Sharon", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6506,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA168",GROUP_DESC:"PA168 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6506,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA168",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA168",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6506,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA168", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7506 ,protection_group_id: -6506, protection_element_id:-6506], primaryKey: false);
      insert('organizations', [ id: 106492, nci_institute_code: "PA169", name: "Viner-Daniels Medical Associates", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6507,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA169",GROUP_DESC:"PA169 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6507,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA169",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA169",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6507,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA169", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7507 ,protection_group_id: -6507, protection_element_id:-6507], primaryKey: false);
      insert('organizations', [ id: 106493, nci_institute_code: "PA170", name: "Abington Hematology/Oncology Associates", city: "North Willow Grove", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6508,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA170",GROUP_DESC:"PA170 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6508,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA170",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA170",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6508,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA170", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7508 ,protection_group_id: -6508, protection_element_id:-6508], primaryKey: false);
      insert('organizations', [ id: 106494, nci_institute_code: "PA171", name: "Associates In Hematology/Oncology, P.C.", city: "Upland", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6509,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA171",GROUP_DESC:"PA171 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6509,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA171",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA171",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6509,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA171", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7509 ,protection_group_id: -6509, protection_element_id:-6509], primaryKey: false);
      insert('organizations', [ id: 106495, nci_institute_code: "PA172", name: "Associates In Internal Medicine", city: "Tarentum", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6510,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA172",GROUP_DESC:"PA172 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6510,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA172",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA172",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6510,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA172", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7510 ,protection_group_id: -6510, protection_element_id:-6510], primaryKey: false);
      insert('organizations', [ id: 106496, nci_institute_code: "PA173", name: "Hematology-Oncology Associates of Philadelphia", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6511,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA173",GROUP_DESC:"PA173 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6511,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA173",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA173",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6511,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA173", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7511 ,protection_group_id: -6511, protection_element_id:-6511], primaryKey: false);
      insert('organizations', [ id: 106497, nci_institute_code: "PA174", name: "U.S. Bioscience, Incorporated", city: "Conshohocken", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6512,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA174",GROUP_DESC:"PA174 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6512,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA174",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA174",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6512,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA174", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7512 ,protection_group_id: -6512, protection_element_id:-6512], primaryKey: false);
    }

    void m20() {
        // all20 (25 terms)
      insert('organizations', [ id: 106498, nci_institute_code: "PA175", name: "Oncology Hematology of Lehigh Valley", city: "Bethlehem", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6513,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA175",GROUP_DESC:"PA175 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6513,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA175",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA175",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6513,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA175", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7513 ,protection_group_id: -6513, protection_element_id:-6513], primaryKey: false);
      insert('organizations', [ id: 106499, nci_institute_code: "PA176", name: "Lancaster Cancer Center", city: "Lancaster", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6514,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA176",GROUP_DESC:"PA176 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6514,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA176",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA176",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6514,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA176", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7514 ,protection_group_id: -6514, protection_element_id:-6514], primaryKey: false);
      insert('organizations', [ id: 106500, nci_institute_code: "PA177", name: "Clearfield Professional Group., Limited", city: "Clearfield", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6515,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA177",GROUP_DESC:"PA177 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6515,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA177",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA177",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6515,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA177", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7515 ,protection_group_id: -6515, protection_element_id:-6515], primaryKey: false);
      insert('organizations', [ id: 106501, nci_institute_code: "PA178", name: "Pocono Medical Center", city: "East Stroudsburg", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6516,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA178",GROUP_DESC:"PA178 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6516,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA178",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA178",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6516,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA178", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7516 ,protection_group_id: -6516, protection_element_id:-6516], primaryKey: false);
      insert('organizations', [ id: 106502, nci_institute_code: "PA180", name: "Hematology Oncology Group PC", city: "Langhorne", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6517,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA180",GROUP_DESC:"PA180 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6517,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA180",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA180",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6517,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA180", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7517 ,protection_group_id: -6517, protection_element_id:-6517], primaryKey: false);
      insert('organizations', [ id: 106503, nci_institute_code: "PA181", name: "The Neurosensory Institute", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6518,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA181",GROUP_DESC:"PA181 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6518,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA181",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA181",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6518,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA181", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7518 ,protection_group_id: -6518, protection_element_id:-6518], primaryKey: false);
      insert('organizations', [ id: 106504, nci_institute_code: "PA182", name: "Chambersburg Health Services", city: "Chambersburg", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6519,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA182",GROUP_DESC:"PA182 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6519,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA182",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA182",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6519,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA182", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7519 ,protection_group_id: -6519, protection_element_id:-6519], primaryKey: false);
      insert('organizations', [ id: 106505, nci_institute_code: "PA183", name: "Center for Urologic Care of Berks County", city: "Wyomissing", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6520,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA183",GROUP_DESC:"PA183 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6520,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA183",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA183",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6520,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA183", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7520 ,protection_group_id: -6520, protection_element_id:-6520], primaryKey: false);
      insert('organizations', [ id: 106506, nci_institute_code: "PA184", name: "Tri-State Surgical Associates", city: "Aliquippa", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6521,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA184",GROUP_DESC:"PA184 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6521,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA184",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA184",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6521,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA184", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7521 ,protection_group_id: -6521, protection_element_id:-6521], primaryKey: false);
      insert('organizations', [ id: 106507, nci_institute_code: "PA185", name: "The Summit", city: "Sellersville", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6522,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA185",GROUP_DESC:"PA185 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6522,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA185",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA185",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6522,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA185", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7522 ,protection_group_id: -6522, protection_element_id:-6522], primaryKey: false);
      insert('organizations', [ id: 106508, nci_institute_code: "PA186", name: "Gynocology/Oncology Associates/Lehigh Valley Incorporated", city: "Allentown", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6523,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA186",GROUP_DESC:"PA186 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6523,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA186",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA186",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6523,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA186", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7523 ,protection_group_id: -6523, protection_element_id:-6523], primaryKey: false);
      insert('organizations', [ id: 106509, nci_institute_code: "PA188", name: "Somerset Hospital", city: "Somerset", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6524,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA188",GROUP_DESC:"PA188 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6524,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA188",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA188",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6524,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA188", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7524 ,protection_group_id: -6524, protection_element_id:-6524], primaryKey: false);
      insert('organizations', [ id: 106510, nci_institute_code: "PA189", name: "Lower Bucks Hospital", city: "Bristol", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6525,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA189",GROUP_DESC:"PA189 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6525,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA189",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA189",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6525,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA189", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7525 ,protection_group_id: -6525, protection_element_id:-6525], primaryKey: false);
      insert('organizations', [ id: 106511, nci_institute_code: "PA190", name: "South Hills Medical Building", city: "Clairton", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6526,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA190",GROUP_DESC:"PA190 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6526,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA190",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA190",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6526,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA190", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7526 ,protection_group_id: -6526, protection_element_id:-6526], primaryKey: false);
      insert('organizations', [ id: 106512, nci_institute_code: "PA191", name: "Oncology - Hematology Associates", city: "Johnstown", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6527,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA191",GROUP_DESC:"PA191 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6527,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA191",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA191",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6527,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA191", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7527 ,protection_group_id: -6527, protection_element_id:-6527], primaryKey: false);
      insert('organizations', [ id: 106513, nci_institute_code: "PA192", name: "Oncology - Hematology Associates", city: "Beaver", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6528,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA192",GROUP_DESC:"PA192 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6528,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA192",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA192",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6528,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA192", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7528 ,protection_group_id: -6528, protection_element_id:-6528], primaryKey: false);
      insert('organizations', [ id: 106514, nci_institute_code: "PA193", name: "Oncology-Hematology Associates", city: "Indiana", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6529,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA193",GROUP_DESC:"PA193 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6529,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA193",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA193",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6529,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA193", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7529 ,protection_group_id: -6529, protection_element_id:-6529], primaryKey: false);
      insert('organizations', [ id: 106515, nci_institute_code: "PA195", name: "Indiana Hospital", city: "Indiana", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6530,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA195",GROUP_DESC:"PA195 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6530,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA195",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA195",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6530,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA195", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7530 ,protection_group_id: -6530, protection_element_id:-6530], primaryKey: false);
      insert('organizations', [ id: 106516, nci_institute_code: "PA196", name: "Medical Oncology Associates", city: "Kingston", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6531,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA196",GROUP_DESC:"PA196 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6531,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA196",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA196",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6531,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA196", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7531 ,protection_group_id: -6531, protection_element_id:-6531], primaryKey: false);
      insert('organizations', [ id: 106517, nci_institute_code: "PA197", name: "University of Pittsburgh Medical Center-Passavant Hospital", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6532,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA197",GROUP_DESC:"PA197 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6532,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA197",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA197",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6532,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA197", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7532 ,protection_group_id: -6532, protection_element_id:-6532], primaryKey: false);
      insert('organizations', [ id: 106518, nci_institute_code: "PA198", name: "Hematology - Oncology Associates of Upci", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6533,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA198",GROUP_DESC:"PA198 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6533,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA198",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA198",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6533,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA198", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7533 ,protection_group_id: -6533, protection_element_id:-6533], primaryKey: false);
      insert('organizations', [ id: 106519, nci_institute_code: "PA199", name: "Altoona Hospital", city: "Altoona", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6534,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA199",GROUP_DESC:"PA199 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6534,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA199",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA199",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6534,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA199", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7534 ,protection_group_id: -6534, protection_element_id:-6534], primaryKey: false);
      insert('organizations', [ id: 106520, nci_institute_code: "PA200", name: "Community Cancer Center", city: "Pottstown", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6535,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA200",GROUP_DESC:"PA200 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6535,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA200",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA200",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6535,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA200", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7535 ,protection_group_id: -6535, protection_element_id:-6535], primaryKey: false);
      insert('organizations', [ id: 106521, nci_institute_code: "PA201", name: "Greenville Medical Center, Incorporated", city: "Greenville", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6536,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA201",GROUP_DESC:"PA201 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6536,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA201",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA201",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6536,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA201", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7536 ,protection_group_id: -6536, protection_element_id:-6536], primaryKey: false);
      insert('organizations', [ id: 106522, nci_institute_code: "PA202", name: "Oncology Hematology Associates", city: "Monroeville", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6537,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA202",GROUP_DESC:"PA202 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6537,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA202",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA202",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6537,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA202", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7537 ,protection_group_id: -6537, protection_element_id:-6537], primaryKey: false);
    }

    void m21() {
        // all21 (25 terms)
      insert('organizations', [ id: 106523, nci_institute_code: "PA203", name: "Meadville Medical Center", city: "Meadville", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6538,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA203",GROUP_DESC:"PA203 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6538,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA203",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA203",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6538,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA203", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7538 ,protection_group_id: -6538, protection_element_id:-6538], primaryKey: false);
      insert('organizations', [ id: 106524, nci_institute_code: "PA204", name: "Gnaden Huetten Memorial Hospital", city: "Lehighton", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6539,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA204",GROUP_DESC:"PA204 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6539,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA204",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA204",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6539,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA204", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7539 ,protection_group_id: -6539, protection_element_id:-6539], primaryKey: false);
      insert('organizations', [ id: 106525, nci_institute_code: "PA205", name: "Bradford Regional Medical Center", city: "Bradford", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6540,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA205",GROUP_DESC:"PA205 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6540,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA205",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA205",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6540,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA205", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7540 ,protection_group_id: -6540, protection_element_id:-6540], primaryKey: false);
      insert('organizations', [ id: 106526, nci_institute_code: "PA206", name: "University of Pittsburgh - Beaver Valley", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6541,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA206",GROUP_DESC:"PA206 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6541,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA206",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA206",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6541,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA206", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7541 ,protection_group_id: -6541, protection_element_id:-6541], primaryKey: false);
      insert('organizations', [ id: 106527, nci_institute_code: "PA207", name: "Oncology Hematology Associates", city: "Mckees Rock", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6542,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA207",GROUP_DESC:"PA207 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6542,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA207",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA207",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6542,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA207", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7542 ,protection_group_id: -6542, protection_element_id:-6542], primaryKey: false);
      insert('organizations', [ id: 106528, nci_institute_code: "PA208", name: "Oncology Hematology Associates", city: "Wexford", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6543,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA208",GROUP_DESC:"PA208 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6543,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA208",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA208",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6543,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA208", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7543 ,protection_group_id: -6543, protection_element_id:-6543], primaryKey: false);
      insert('organizations', [ id: 106529, nci_institute_code: "PA209", name: "North East Medical Oncology Associates", city: "Hazleton", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6544,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA209",GROUP_DESC:"PA209 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6544,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA209",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA209",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6544,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA209", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7544 ,protection_group_id: -6544, protection_element_id:-6544], primaryKey: false);
      insert('organizations', [ id: 106530, nci_institute_code: "PA210", name: "Physician Oncology Specialists, Incorporated.,", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6545,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA210",GROUP_DESC:"PA210 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6545,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA210",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA210",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6545,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA210", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7545 ,protection_group_id: -6545, protection_element_id:-6545], primaryKey: false);
      insert('organizations', [ id: 106531, nci_institute_code: "PA211", name: "Saint Luke's Quakertown Hospital", city: "Quakertown", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6546,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA211",GROUP_DESC:"PA211 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6546,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA211",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA211",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6546,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA211", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7546 ,protection_group_id: -6546, protection_element_id:-6546], primaryKey: false);
      insert('organizations', [ id: 106532, nci_institute_code: "PA212", name: "Greater Harrisburg Cancer Center", city: "Harrisburg", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6547,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA212",GROUP_DESC:"PA212 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6547,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA212",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA212",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6547,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA212", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7547 ,protection_group_id: -6547, protection_element_id:-6547], primaryKey: false);
      insert('organizations', [ id: 106533, nci_institute_code: "PA213", name: "Bon Secours - Holy Family Hospital", city: "Altoona", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6548,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA213",GROUP_DESC:"PA213 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6548,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA213",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA213",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6548,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA213", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7548 ,protection_group_id: -6548, protection_element_id:-6548], primaryKey: false);
      insert('organizations', [ id: 106534, nci_institute_code: "PA215", name: "Hahnemann Medical Center and Hospital", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6549,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA215",GROUP_DESC:"PA215 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6549,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA215",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA215",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6549,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA215", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7549 ,protection_group_id: -6549, protection_element_id:-6549], primaryKey: false);
      insert('organizations', [ id: 106535, nci_institute_code: "PA216", name: "Pennsylvania Oncology Hematology Associates", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6550,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA216",GROUP_DESC:"PA216 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6550,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA216",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA216",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6550,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA216", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7550 ,protection_group_id: -6550, protection_element_id:-6550], primaryKey: false);
      insert('organizations', [ id: 106536, nci_institute_code: "PA217", name: "Gannon University", city: "Erie", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6551,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA217",GROUP_DESC:"PA217 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6551,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA217",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA217",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6551,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA217", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7551 ,protection_group_id: -6551, protection_element_id:-6551], primaryKey: false);
      insert('organizations', [ id: 106537, nci_institute_code: "PA218", name: "Eastern Pennsylvania Gynocologic Oncology Center", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6552,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA218",GROUP_DESC:"PA218 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6552,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA218",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA218",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6552,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA218", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7552 ,protection_group_id: -6552, protection_element_id:-6552], primaryKey: false);
      insert('organizations', [ id: 106538, nci_institute_code: "PA219", name: "Highlands Hospital", city: "Connellsville", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6553,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA219",GROUP_DESC:"PA219 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6553,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA219",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA219",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6553,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA219", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7553 ,protection_group_id: -6553, protection_element_id:-6553], primaryKey: false);
      insert('organizations', [ id: 106539, nci_institute_code: "PA220", name: "Regional Hematology Oncology Associates, P.C.", city: "Langhorne", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6554,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA220",GROUP_DESC:"PA220 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6554,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA220",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA220",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6554,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA220", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7554 ,protection_group_id: -6554, protection_element_id:-6554], primaryKey: false);
      insert('organizations', [ id: 106540, nci_institute_code: "PA223", name: "Ephrata Community Hospital", city: "Ephrata", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6555,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA223",GROUP_DESC:"PA223 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6555,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA223",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA223",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6555,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA223", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7555 ,protection_group_id: -6555, protection_element_id:-6555], primaryKey: false);
      insert('organizations', [ id: 106541, nci_institute_code: "PA224", name: "The Care Group, PC.", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6556,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA224",GROUP_DESC:"PA224 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6556,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA224",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA224",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6556,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA224", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7556 ,protection_group_id: -6556, protection_element_id:-6556], primaryKey: false);
      insert('organizations', [ id: 106542, nci_institute_code: "PA225", name: "Hematology and Medical Oncology", city: "Kingston", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6557,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA225",GROUP_DESC:"PA225 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6557,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA225",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA225",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6557,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA225", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7557 ,protection_group_id: -6557, protection_element_id:-6557], primaryKey: false);
      insert('organizations', [ id: 106543, nci_institute_code: "PA226", name: "Lehigh Valley Hospital - Muhlenberg", city: "Bethlehem", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6558,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA226",GROUP_DESC:"PA226 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6558,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA226",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA226",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6558,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA226", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7558 ,protection_group_id: -6558, protection_element_id:-6558], primaryKey: false);
      insert('organizations', [ id: 106544, nci_institute_code: "PA227", name: "Saint Joseph Medical Center, Hazelton", city: "Hazleton", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6559,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA227",GROUP_DESC:"PA227 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6559,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA227",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA227",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6559,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA227", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7559 ,protection_group_id: -6559, protection_element_id:-6559], primaryKey: false);
      insert('organizations', [ id: 106545, nci_institute_code: "PA229", name: "Forbes Health System", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6560,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA229",GROUP_DESC:"PA229 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6560,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA229",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA229",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6560,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA229", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7560 ,protection_group_id: -6560, protection_element_id:-6560], primaryKey: false);
      insert('organizations', [ id: 106546, nci_institute_code: "PA231", name: "Tyler Memorial Hospital", city: "Woodville", state: "TX", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6561,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA231",GROUP_DESC:"PA231 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6561,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA231",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA231",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6561,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA231", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7561 ,protection_group_id: -6561, protection_element_id:-6561], primaryKey: false);
      insert('organizations', [ id: 106547, nci_institute_code: "PA232", name: "Surgical Specialists of Wyoming Valley", city: "Wilkes-Barre", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6562,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA232",GROUP_DESC:"PA232 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6562,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA232",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA232",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6562,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA232", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7562 ,protection_group_id: -6562, protection_element_id:-6562], primaryKey: false);
    }

    void m22() {
        // all22 (25 terms)
      insert('organizations', [ id: 106548, nci_institute_code: "PA234", name: "VA Pittsburgh Healthcare System, Highland Drive Division", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6563,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA234",GROUP_DESC:"PA234 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6563,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA234",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA234",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6563,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA234", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7563 ,protection_group_id: -6563, protection_element_id:-6563], primaryKey: false);
      insert('organizations', [ id: 106549, nci_institute_code: "PA235", name: "Jennersville Regional Hospital", city: "West Grove", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6564,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA235",GROUP_DESC:"PA235 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6564,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA235",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA235",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6564,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA235", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7564 ,protection_group_id: -6564, protection_element_id:-6564], primaryKey: false);
      insert('organizations', [ id: 106550, nci_institute_code: "PA236", name: "Women's Cancer Center of Central PA PC", city: "Harrisburg", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6565,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA236",GROUP_DESC:"PA236 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6565,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA236",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA236",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6565,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA236", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7565 ,protection_group_id: -6565, protection_element_id:-6565], primaryKey: false);
      insert('organizations', [ id: 106551, nci_institute_code: "PA238", name: "Fox Chase Cancer Center", city: "Rockledge", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6566,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA238",GROUP_DESC:"PA238 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6566,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA238",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA238",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6566,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA238", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7566 ,protection_group_id: -6566, protection_element_id:-6566], primaryKey: false);
      insert('organizations', [ id: 106552, nci_institute_code: "PA240", name: "Oncology and Hematology Association", city: "Hermitage", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6567,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA240",GROUP_DESC:"PA240 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6567,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA240",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA240",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6567,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA240", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7567 ,protection_group_id: -6567, protection_element_id:-6567], primaryKey: false);
      insert('organizations', [ id: 106553, nci_institute_code: "PA241", name: "Temple East (Northeastern Hospital)", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6568,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA241",GROUP_DESC:"PA241 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6568,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA241",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA241",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6568,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA241", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7568 ,protection_group_id: -6568, protection_element_id:-6568], primaryKey: false);
      insert('organizations', [ id: 106554, nci_institute_code: "PA242", name: "Univ Pittsburgh Mdcl Ctr-South Side", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6569,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA242",GROUP_DESC:"PA242 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6569,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA242",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA242",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6569,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA242", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7569 ,protection_group_id: -6569, protection_element_id:-6569], primaryKey: false);
      insert('organizations', [ id: 106555, nci_institute_code: "PA243", name: "Ephrata Cancer Center", city: "Ephrata", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6570,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA243",GROUP_DESC:"PA243 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6570,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA243",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA243",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6570,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA243", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7570 ,protection_group_id: -6570, protection_element_id:-6570], primaryKey: false);
      insert('organizations', [ id: 106556, nci_institute_code: "PA245", name: "Oncology & Hematology Associates", city: "McKees Rock", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6571,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA245",GROUP_DESC:"PA245 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6571,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA245",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA245",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6571,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA245", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7571 ,protection_group_id: -6571, protection_element_id:-6571], primaryKey: false);
      insert('organizations', [ id: 106557, nci_institute_code: "PA246", name: "Saint Clair Memorial Hospital", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6572,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA246",GROUP_DESC:"PA246 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6572,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA246",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA246",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6572,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA246", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7572 ,protection_group_id: -6572, protection_element_id:-6572], primaryKey: false);
      insert('organizations', [ id: 106558, nci_institute_code: "PA248", name: "Hematology and Oncology Associates", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6573,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA248",GROUP_DESC:"PA248 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6573,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA248",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA248",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6573,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA248", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7573 ,protection_group_id: -6573, protection_element_id:-6573], primaryKey: false);
      insert('organizations', [ id: 106559, nci_institute_code: "PA249", name: "Scranton Regional Cancer and Imaging Center", city: "Scranton", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6574,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA249",GROUP_DESC:"PA249 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6574,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA249",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA249",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6574,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA249", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7574 ,protection_group_id: -6574, protection_element_id:-6574], primaryKey: false);
      insert('organizations', [ id: 106560, nci_institute_code: "PA250", name: "Eastwick Primary Care, PC", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6575,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA250",GROUP_DESC:"PA250 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6575,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA250",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA250",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6575,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA250", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7575 ,protection_group_id: -6575, protection_element_id:-6575], primaryKey: false);
      insert('organizations', [ id: 106561, nci_institute_code: "PA251", name: "Associates in Oncology-Hematology, PC", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6576,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA251",GROUP_DESC:"PA251 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6576,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA251",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA251",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6576,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA251", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7576 ,protection_group_id: -6576, protection_element_id:-6576], primaryKey: false);
      insert('organizations', [ id: 106562, nci_institute_code: "PA253", name: "Oncology Hematology Associates", city: "Natrona Heights", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6577,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA253",GROUP_DESC:"PA253 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6577,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA253",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA253",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6577,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA253", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7577 ,protection_group_id: -6577, protection_element_id:-6577], primaryKey: false);
      insert('organizations', [ id: 106563, nci_institute_code: "PA254", name: "York Cancer Center / Wellspan Health", city: "York", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6578,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA254",GROUP_DESC:"PA254 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6578,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA254",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA254",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6578,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA254", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7578 ,protection_group_id: -6578, protection_element_id:-6578], primaryKey: false);
      insert('organizations', [ id: 106564, nci_institute_code: "PA255", name: "Jeannette District Memorial Hospital", city: "Jeannette", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6579,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA255",GROUP_DESC:"PA255 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6579,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA255",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA255",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6579,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA255", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7579 ,protection_group_id: -6579, protection_element_id:-6579], primaryKey: false);
      insert('organizations', [ id: 106565, nci_institute_code: "PA256", name: "University of Pittsburgh Medical Center-Cancer Centers at Uniontown", city: "Uniontown", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6580,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA256",GROUP_DESC:"PA256 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6580,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA256",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA256",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6580,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA256", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7580 ,protection_group_id: -6580, protection_element_id:-6580], primaryKey: false);
      insert('organizations', [ id: 106566, nci_institute_code: "PA257", name: "West Orange Medical Associates", city: "Milford", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6581,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA257",GROUP_DESC:"PA257 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6581,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA257",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA257",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6581,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA257", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7581 ,protection_group_id: -6581, protection_element_id:-6581], primaryKey: false);
      insert('organizations', [ id: 106567, nci_institute_code: "PA258", name: "Oncology/Hematology Associates", city: "New Castle", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6582,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA258",GROUP_DESC:"PA258 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6582,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA258",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA258",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6582,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA258", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7582 ,protection_group_id: -6582, protection_element_id:-6582], primaryKey: false);
      insert('organizations', [ id: 106568, nci_institute_code: "PA259", name: "Venango Oncology Hematology Association", city: "Franklin", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6583,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA259",GROUP_DESC:"PA259 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6583,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA259",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA259",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6583,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA259", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7583 ,protection_group_id: -6583, protection_element_id:-6583], primaryKey: false);
      insert('organizations', [ id: 106569, nci_institute_code: "PA260", name: "Cancer Care of Central Pennsylvania", city: "Selinsgrove", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6584,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA260",GROUP_DESC:"PA260 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6584,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA260",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA260",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6584,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA260", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7584 ,protection_group_id: -6584, protection_element_id:-6584], primaryKey: false);
      insert('organizations', [ id: 106570, nci_institute_code: "PA262", name: "Veteran's Administration Medical Center", city: "Lebanon", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6585,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA262",GROUP_DESC:"PA262 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6585,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA262",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA262",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6585,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA262", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7585 ,protection_group_id: -6585, protection_element_id:-6585], primaryKey: false);
      insert('organizations', [ id: 106571, nci_institute_code: "PA263", name: "UPMC Cancer Center at Jefferson Regional Medical Center", city: "Clairton", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6586,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA263",GROUP_DESC:"PA263 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6586,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA263",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA263",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6586,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA263", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7586 ,protection_group_id: -6586, protection_element_id:-6586], primaryKey: false);
      insert('organizations', [ id: 106572, nci_institute_code: "PA264", name: "The Regional Cancer Center", city: "Meadville", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6587,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA264",GROUP_DESC:"PA264 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6587,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA264",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA264",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6587,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA264", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7587 ,protection_group_id: -6587, protection_element_id:-6587], primaryKey: false);
    }

    void m23() {
        // all23 (25 terms)
      insert('organizations', [ id: 106573, nci_institute_code: "PA265", name: "The Cancer Center at Phoenixville Hospital-Limerick Site", city: "Limerick", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6588,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA265",GROUP_DESC:"PA265 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6588,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA265",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA265",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6588,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA265", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7588 ,protection_group_id: -6588, protection_element_id:-6588], primaryKey: false);
      insert('organizations', [ id: 106574, nci_institute_code: "PA267", name: "Saint Luke's Miners Memorial Hospital", city: "Coaldale", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6589,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA267",GROUP_DESC:"PA267 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6589,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA267",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA267",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6589,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA267", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7589 ,protection_group_id: -6589, protection_element_id:-6589], primaryKey: false);
      insert('organizations', [ id: 106575, nci_institute_code: "PA270", name: "University Pittsburgh Medical Cancer Center/Johnstown", city: "Johnstown", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6590,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA270",GROUP_DESC:"PA270 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6590,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA270",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA270",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6590,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA270", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7590 ,protection_group_id: -6590, protection_element_id:-6590], primaryKey: false);
      insert('organizations', [ id: 106576, nci_institute_code: "PA271", name: "Intercommunity Cancer Center", city: "Monroeville", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6591,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA271",GROUP_DESC:"PA271 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6591,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA271",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA271",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6591,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA271", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7591 ,protection_group_id: -6591, protection_element_id:-6591], primaryKey: false);
      insert('organizations', [ id: 106577, nci_institute_code: "PA272", name: "Vallemont Surgical Associates", city: "Lewistown", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6592,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA272",GROUP_DESC:"PA272 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6592,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA272",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA272",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6592,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA272", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7592 ,protection_group_id: -6592, protection_element_id:-6592], primaryKey: false);
      insert('organizations', [ id: 106578, nci_institute_code: "PA273", name: "Apple Hill Urology, P.C.", city: "York", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6593,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA273",GROUP_DESC:"PA273 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6593,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA273",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA273",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6593,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA273", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7593 ,protection_group_id: -6593, protection_element_id:-6593], primaryKey: false);
      insert('organizations', [ id: 106579, nci_institute_code: "PA274", name: "Apple Hill Surgical Associates", city: "York", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6594,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA274",GROUP_DESC:"PA274 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6594,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA274",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA274",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6594,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA274", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7594 ,protection_group_id: -6594, protection_element_id:-6594], primaryKey: false);
      insert('organizations', [ id: 106580, nci_institute_code: "PA275", name: "Immunicon Corporation", city: "Bryn Athyn", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6595,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA275",GROUP_DESC:"PA275 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6595,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA275",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA275",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6595,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA275", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7595 ,protection_group_id: -6595, protection_element_id:-6595], primaryKey: false);
      insert('organizations', [ id: 106581, nci_institute_code: "PA276", name: "National Medical Imaging", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6596,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA276",GROUP_DESC:"PA276 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6596,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA276",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA276",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6596,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA276", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7596 ,protection_group_id: -6596, protection_element_id:-6596], primaryKey: false);
      insert('organizations', [ id: 106582, nci_institute_code: "PA277", name: "Cherry Tree Cancer Center", city: "Hanover", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6597,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA277",GROUP_DESC:"PA277 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6597,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA277",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA277",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6597,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA277", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7597 ,protection_group_id: -6597, protection_element_id:-6597], primaryKey: false);
      insert('organizations', [ id: 106583, nci_institute_code: "PA278", name: "Medical Center Clinic: New Castle Office", city: "New Castle", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6598,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA278",GROUP_DESC:"PA278 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6598,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA278",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA278",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6598,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA278", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7598 ,protection_group_id: -6598, protection_element_id:-6598], primaryKey: false);
      insert('organizations', [ id: 106584, nci_institute_code: "PA279", name: "Suburban General Hospital", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6599,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA279",GROUP_DESC:"PA279 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6599,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA279",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA279",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6599,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA279", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7599 ,protection_group_id: -6599, protection_element_id:-6599], primaryKey: false);
      insert('organizations', [ id: 106585, nci_institute_code: "PA280", name: "Russellton Medical Group", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6600,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA280",GROUP_DESC:"PA280 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6600,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA280",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA280",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6600,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA280", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7600 ,protection_group_id: -6600, protection_element_id:-6600], primaryKey: false);
      insert('organizations', [ id: 106586, nci_institute_code: "PA281", name: "Geisinger Medical Group", city: "State College", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6601,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA281",GROUP_DESC:"PA281 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6601,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA281",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA281",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6601,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA281", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7601 ,protection_group_id: -6601, protection_element_id:-6601], primaryKey: false);
      insert('organizations', [ id: 106587, nci_institute_code: "PA282", name: "Somerset Oncology Center", city: "Somerset", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6602,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA282",GROUP_DESC:"PA282 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6602,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA282",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA282",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6602,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA282", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7602 ,protection_group_id: -6602, protection_element_id:-6602], primaryKey: false);
      insert('organizations', [ id: 106588, nci_institute_code: "PA283", name: "Valley Surgeons, Inc.", city: "Johnstown", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6603,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA283",GROUP_DESC:"PA283 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6603,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA283",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA283",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6603,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA283", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7603 ,protection_group_id: -6603, protection_element_id:-6603], primaryKey: false);
      insert('organizations', [ id: 106589, nci_institute_code: "PA284", name: "Oncology Hematology Associates of Northern Pennsylvania", city: "Dubois", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6604,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA284",GROUP_DESC:"PA284 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6604,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA284",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA284",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6604,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA284", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7604 ,protection_group_id: -6604, protection_element_id:-6604], primaryKey: false);
      insert('organizations', [ id: 106590, nci_institute_code: "PA285", name: "West Penn Cancer Institute", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6605,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA285",GROUP_DESC:"PA285 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6605,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA285",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA285",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6605,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA285", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7605 ,protection_group_id: -6605, protection_element_id:-6605], primaryKey: false);
      insert('organizations', [ id: 106591, nci_institute_code: "PA286", name: "Medical Center Clinic: Monroeville", city: "Monroeville", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6606,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA286",GROUP_DESC:"PA286 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6606,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA286",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA286",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6606,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA286", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7606 ,protection_group_id: -6606, protection_element_id:-6606], primaryKey: false);
      insert('organizations', [ id: 106592, nci_institute_code: "PA287", name: "Sharon Regional Cancer Center", city: "Hermitage", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6607,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA287",GROUP_DESC:"PA287 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6607,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA287",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA287",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6607,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA287", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7607 ,protection_group_id: -6607, protection_element_id:-6607], primaryKey: false);
      insert('organizations', [ id: 106593, nci_institute_code: "PA288", name: "Medical Center Clinic: Punxsutawney Office", city: "punxsutawney", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6608,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA288",GROUP_DESC:"PA288 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6608,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA288",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA288",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6608,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA288", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7608 ,protection_group_id: -6608, protection_element_id:-6608], primaryKey: false);
      insert('organizations', [ id: 106594, nci_institute_code: "PA289", name: "Medical Center Clinic: Shadyside Office", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6609,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA289",GROUP_DESC:"PA289 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6609,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA289",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA289",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6609,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA289", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7609 ,protection_group_id: -6609, protection_element_id:-6609], primaryKey: false);
      insert('organizations', [ id: 106595, nci_institute_code: "PA290", name: "Medical Center Clinic: Allegheny General Hospital", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6610,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA290",GROUP_DESC:"PA290 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6610,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA290",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA290",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6610,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA290", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7610 ,protection_group_id: -6610, protection_element_id:-6610], primaryKey: false);
      insert('organizations', [ id: 106596, nci_institute_code: "PA291", name: "Medical Center Clinic: Allegeny Valley Office", city: "Natrona Heights", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6611,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA291",GROUP_DESC:"PA291 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6611,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA291",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA291",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6611,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA291", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7611 ,protection_group_id: -6611, protection_element_id:-6611], primaryKey: false);
      insert('organizations', [ id: 106597, nci_institute_code: "PA292", name: "Medical Center Clinic: Canonsburg office", city: "Canonsburg", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6612,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA292",GROUP_DESC:"PA292 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6612,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA292",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA292",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6612,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA292", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7612 ,protection_group_id: -6612, protection_element_id:-6612], primaryKey: false);
    }

    void m24() {
        // all24 (25 terms)
      insert('organizations', [ id: 106598, nci_institute_code: "PA293", name: "Medical Center Clinic: Citizens Ambulatory", city: "New Kingston", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6613,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA293",GROUP_DESC:"PA293 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6613,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA293",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA293",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6613,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA293", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7613 ,protection_group_id: -6613, protection_element_id:-6613], primaryKey: false);
      insert('organizations', [ id: 106599, nci_institute_code: "PA294", name: "Medical Center Clinic: Russellton Medical Group", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6614,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA294",GROUP_DESC:"PA294 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6614,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA294",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA294",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6614,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA294", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7614 ,protection_group_id: -6614, protection_element_id:-6614], primaryKey: false);
      insert('organizations', [ id: 106600, nci_institute_code: "PA295", name: "Medical Center Clinic: Suburban Office", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6615,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA295",GROUP_DESC:"PA295 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6615,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA295",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA295",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6615,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA295", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7615 ,protection_group_id: -6615, protection_element_id:-6615], primaryKey: false);
      insert('organizations', [ id: 106601, nci_institute_code: "PA296", name: "Medical Center Clinic: Kittanning Office", city: "Kittanning", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6616,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA296",GROUP_DESC:"PA296 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6616,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA296",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA296",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6616,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA296", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7616 ,protection_group_id: -6616, protection_element_id:-6616], primaryKey: false);
      insert('organizations', [ id: 106602, nci_institute_code: "PA297", name: "Triangle Urological Group", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6617,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA297",GROUP_DESC:"PA297 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6617,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA297",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA297",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6617,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA297", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7617 ,protection_group_id: -6617, protection_element_id:-6617], primaryKey: false);
      insert('organizations', [ id: 106603, nci_institute_code: "PA298", name: "Fair Grounds Medical Center", city: "Allentown", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6618,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA298",GROUP_DESC:"PA298 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6618,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA298",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA298",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6618,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA298", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7618 ,protection_group_id: -6618, protection_element_id:-6618], primaryKey: false);
      insert('organizations', [ id: 106604, nci_institute_code: "PA300", name: "UPMC Cancer Pavilion", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6619,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA300",GROUP_DESC:"PA300 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6619,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA300",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA300",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6619,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA300", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7619 ,protection_group_id: -6619, protection_element_id:-6619], primaryKey: false);
      insert('organizations', [ id: 106605, nci_institute_code: "PA302", name: "Scranton Hematology Oncology", city: "Scranton", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6620,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA302",GROUP_DESC:"PA302 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6620,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA302",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA302",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6620,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA302", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7620 ,protection_group_id: -6620, protection_element_id:-6620], primaryKey: false);
      insert('organizations', [ id: 106606, nci_institute_code: "PA303", name: "White Rose Surgical Associates", city: "York", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6621,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA303",GROUP_DESC:"PA303 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6621,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA303",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA303",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6621,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA303", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7621 ,protection_group_id: -6621, protection_element_id:-6621], primaryKey: false);
      insert('organizations', [ id: 106607, nci_institute_code: "PA304", name: "General Surgical Care, P.C.", city: "Bethlehem", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6622,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA304",GROUP_DESC:"PA304 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6622,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA304",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA304",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6622,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA304", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7622 ,protection_group_id: -6622, protection_element_id:-6622], primaryKey: false);
      insert('organizations', [ id: 106608, nci_institute_code: "PA305", name: "Albert Einstein Center One", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6623,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA305",GROUP_DESC:"PA305 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6623,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA305",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA305",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6623,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA305", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7623 ,protection_group_id: -6623, protection_element_id:-6623], primaryKey: false);
      insert('organizations', [ id: 106609, nci_institute_code: "PA306", name: "Partridge Comprehensive Health Services, Inc.", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6624,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA306",GROUP_DESC:"PA306 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6624,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA306",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA306",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6624,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA306", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7624 ,protection_group_id: -6624, protection_element_id:-6624], primaryKey: false);
      insert('organizations', [ id: 106610, nci_institute_code: "PA307", name: "Stonybrook University Medical Center", city: "Pottstown", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6625,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA307",GROUP_DESC:"PA307 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6625,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA307",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA307",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6625,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA307", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7625 ,protection_group_id: -6625, protection_element_id:-6625], primaryKey: false);
      insert('organizations', [ id: 106611, nci_institute_code: "PA308", name: "Mountain View Cancer Associates LLP", city: "Greensburg", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6626,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA308",GROUP_DESC:"PA308 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6626,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA308",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA308",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6626,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA308", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7626 ,protection_group_id: -6626, protection_element_id:-6626], primaryKey: false);
      insert('organizations', [ id: 106612, nci_institute_code: "PA309", name: "Blair Medical Associates, Inc.", city: "Altoona", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6627,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA309",GROUP_DESC:"PA309 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6627,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA309",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA309",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6627,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA309", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7627 ,protection_group_id: -6627, protection_element_id:-6627], primaryKey: false);
      insert('organizations', [ id: 106613, nci_institute_code: "PA310", name: "Cancer Care Specialists", city: "Meadville", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6628,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA310",GROUP_DESC:"PA310 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6628,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA310",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA310",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6628,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA310", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7628 ,protection_group_id: -6628, protection_element_id:-6628], primaryKey: false);
      insert('organizations', [ id: 106614, nci_institute_code: "PA311", name: "Pottsville Cancer Clinic", city: "Pottsville", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6629,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA311",GROUP_DESC:"PA311 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6629,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA311",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA311",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6629,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA311", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7629 ,protection_group_id: -6629, protection_element_id:-6629], primaryKey: false);
      insert('organizations', [ id: 106615, nci_institute_code: "PA312", name: "Molecular Medicine Institute", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6630,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA312",GROUP_DESC:"PA312 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6630,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA312",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA312",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6630,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA312", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7630 ,protection_group_id: -6630, protection_element_id:-6630], primaryKey: false);
      insert('organizations', [ id: 106616, nci_institute_code: "PA313", name: "UPMC Cancer Centers", city: "Indiana", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6631,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA313",GROUP_DESC:"PA313 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6631,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA313",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA313",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6631,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA313", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7631 ,protection_group_id: -6631, protection_element_id:-6631], primaryKey: false);
      insert('organizations', [ id: 106617, nci_institute_code: "PA314", name: "Lehigh Valley Women's Cancer Center", city: "Allentown", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6632,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA314",GROUP_DESC:"PA314 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6632,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA314",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA314",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6632,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA314", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7632 ,protection_group_id: -6632, protection_element_id:-6632], primaryKey: false);
      insert('organizations', [ id: 106618, nci_institute_code: "PA315", name: "Bryn Mawr Medical Specialists Association", city: "Bryn Mawr", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6633,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA315",GROUP_DESC:"PA315 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6633,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA315",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA315",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6633,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA315", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7633 ,protection_group_id: -6633, protection_element_id:-6633], primaryKey: false);
      insert('organizations', [ id: 106619, nci_institute_code: "PA316", name: "Andrew J. Zahalsky, MD, Inc", city: "Monongahela", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6634,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA316",GROUP_DESC:"PA316 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6634,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA316",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA316",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6634,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA316", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7634 ,protection_group_id: -6634, protection_element_id:-6634], primaryKey: false);
      insert('organizations', [ id: 106620, nci_institute_code: "PA317", name: "Radiation Therapy Center", city: "York", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6635,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA317",GROUP_DESC:"PA317 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6635,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA317",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA317",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6635,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA317", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7635 ,protection_group_id: -6635, protection_element_id:-6635], primaryKey: false);
      insert('organizations', [ id: 106621, nci_institute_code: "PA318", name: "Allegheny Neurological Associates", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6636,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA318",GROUP_DESC:"PA318 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6636,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA318",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA318",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6636,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA318", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7636 ,protection_group_id: -6636, protection_element_id:-6636], primaryKey: false);
      insert('organizations', [ id: 106622, nci_institute_code: "PA319", name: "UPMC Cancer Center - Monroeville", city: "Monroeville", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6637,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA319",GROUP_DESC:"PA319 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6637,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA319",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA319",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6637,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA319", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7637 ,protection_group_id: -6637, protection_element_id:-6637], primaryKey: false);
    }

    void m25() {
        // all25 (25 terms)
      insert('organizations', [ id: 106623, nci_institute_code: "PA320", name: "UPMC Cancer Center - Pittsburgh", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6638,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA320",GROUP_DESC:"PA320 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6638,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA320",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA320",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6638,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA320", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7638 ,protection_group_id: -6638, protection_element_id:-6638], primaryKey: false);
      insert('organizations', [ id: 106624, nci_institute_code: "PA321", name: "New Age Plastic Surgery-Seneca", city: "Seneca", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6639,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA321",GROUP_DESC:"PA321 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6639,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA321",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA321",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6639,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA321", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7639 ,protection_group_id: -6639, protection_element_id:-6639], primaryKey: false);
      insert('organizations', [ id: 106625, nci_institute_code: "PA322", name: "UPMC Cancer Center Sewickley", city: "Sewickley", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6640,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA322",GROUP_DESC:"PA322 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6640,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA322",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA322",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6640,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA322", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7640 ,protection_group_id: -6640, protection_element_id:-6640], primaryKey: false);
      insert('organizations', [ id: 106626, nci_institute_code: "PA323", name: "Oak Wood Center Radiation Oncology", city: "Mechanicsburg", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6641,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA323",GROUP_DESC:"PA323 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6641,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA323",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA323",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6641,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA323", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7641 ,protection_group_id: -6641, protection_element_id:-6641], primaryKey: false);
      insert('organizations', [ id: 106627, nci_institute_code: "PA324", name: "Mercy Wellness Center", city: "Darby", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6642,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA324",GROUP_DESC:"PA324 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6642,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA324",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA324",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6642,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA324", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7642 ,protection_group_id: -6642, protection_element_id:-6642], primaryKey: false);
      insert('organizations', [ id: 106628, nci_institute_code: "PA325", name: "Weinstein Imaging Associates", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6643,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA325",GROUP_DESC:"PA325 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6643,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA325",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA325",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6643,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA325", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7643 ,protection_group_id: -6643, protection_element_id:-6643], primaryKey: false);
      insert('organizations', [ id: 106629, nci_institute_code: "PA326", name: "University of Pittsburgh Medical Cancer Center - Natrona Heights", city: "Natrona Heights", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6644,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA326",GROUP_DESC:"PA326 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6644,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA326",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA326",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6644,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA326", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7644 ,protection_group_id: -6644, protection_element_id:-6644], primaryKey: false);
      insert('organizations', [ id: 106630, nci_institute_code: "PA327", name: "Surgical Oncology, P.C.", city: "Abington", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6645,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA327",GROUP_DESC:"PA327 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6645,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA327",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA327",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6645,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA327", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7645 ,protection_group_id: -6645, protection_element_id:-6645], primaryKey: false);
      insert('organizations', [ id: 106631, nci_institute_code: "PA328", name: "Oncology Hematology Association", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6646,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA328",GROUP_DESC:"PA328 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6646,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA328",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA328",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6646,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA328", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7646 ,protection_group_id: -6646, protection_element_id:-6646], primaryKey: false);
      insert('organizations', [ id: 106632, nci_institute_code: "PA329", name: "UPMC Cancer Centers - Arnold Palmer Pavilion", city: "Greensburg", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6647,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA329",GROUP_DESC:"PA329 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6647,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA329",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA329",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6647,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA329", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7647 ,protection_group_id: -6647, protection_element_id:-6647], primaryKey: false);
      insert('organizations', [ id: 106633, nci_institute_code: "PA330", name: "Rectal & Colon Surgery Inc.", city: "Erie", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6648,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA330",GROUP_DESC:"PA330 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6648,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA330",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA330",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6648,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA330", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7648 ,protection_group_id: -6648, protection_element_id:-6648], primaryKey: false);
      insert('organizations', [ id: 106634, nci_institute_code: "PA331", name: "Paoli Hematology Oncology Associates, P.C.", city: "Paoli", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6649,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA331",GROUP_DESC:"PA331 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6649,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA331",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA331",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6649,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA331", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7649 ,protection_group_id: -6649, protection_element_id:-6649], primaryKey: false);
      insert('organizations', [ id: 106635, nci_institute_code: "PA332", name: "Lancaster Regional Medical Center", city: "Lancaster", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6650,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA332",GROUP_DESC:"PA332 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6650,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA332",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA332",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6650,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA332", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7650 ,protection_group_id: -6650, protection_element_id:-6650], primaryKey: false);
      insert('organizations', [ id: 106636, nci_institute_code: "PA333", name: "Delaware Valley Surgical Associates", city: "Havertown", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6651,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA333",GROUP_DESC:"PA333 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6651,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA333",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA333",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6651,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA333", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7651 ,protection_group_id: -6651, protection_element_id:-6651], primaryKey: false);
      insert('organizations', [ id: 106637, nci_institute_code: "PA334", name: "Sewickley Medical Oncology & Hematology Group-UPCI", city: "Moon Township", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6652,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA334",GROUP_DESC:"PA334 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6652,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA334",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA334",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6652,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA334", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7652 ,protection_group_id: -6652, protection_element_id:-6652], primaryKey: false);
      insert('organizations', [ id: 106638, nci_institute_code: "PA335", name: "Penn State Children's Hospital", city: "Hershey", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6653,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA335",GROUP_DESC:"PA335 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6653,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA335",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA335",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6653,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA335", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7653 ,protection_group_id: -6653, protection_element_id:-6653], primaryKey: false);
      insert('organizations', [ id: 106639, nci_institute_code: "PA336", name: "Charles Cole Memorial Hospital", city: "Coudersport", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6654,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA336",GROUP_DESC:"PA336 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6654,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA336",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA336",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6654,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA336", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7654 ,protection_group_id: -6654, protection_element_id:-6654], primaryKey: false);
      insert('organizations', [ id: 106640, nci_institute_code: "PA337", name: "Thomas Jefferson University", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6655,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA337",GROUP_DESC:"PA337 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6655,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA337",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA337",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6655,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA337", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7655 ,protection_group_id: -6655, protection_element_id:-6655], primaryKey: false);
      insert('organizations', [ id: 106641, nci_institute_code: "PA338", name: "The Chester County Cancer Center", city: "Westchester", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6656,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA338",GROUP_DESC:"PA338 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6656,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA338",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA338",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6656,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA338", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7656 ,protection_group_id: -6656, protection_element_id:-6656], primaryKey: false);
      insert('organizations', [ id: 106642, nci_institute_code: "PA339", name: "Clinical Care Associates", city: "Phoenixville", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6657,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA339",GROUP_DESC:"PA339 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6657,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA339",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA339",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6657,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA339", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7657 ,protection_group_id: -6657, protection_element_id:-6657], primaryKey: false);
      insert('organizations', [ id: 106643, nci_institute_code: "PA340", name: "Hemotology - Oncology Associates-Ridley Park", city: "Ridley Park", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6658,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA340",GROUP_DESC:"PA340 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6658,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA340",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA340",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6658,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA340", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7658 ,protection_group_id: -6658, protection_element_id:-6658], primaryKey: false);
      insert('organizations', [ id: 106644, nci_institute_code: "PA341", name: "Northeast Radiation Oncology Center", city: "Dunmore", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6659,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA341",GROUP_DESC:"PA341 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6659,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA341",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA341",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6659,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA341", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7659 ,protection_group_id: -6659, protection_element_id:-6659], primaryKey: false);
      insert('organizations', [ id: 106645, nci_institute_code: "PA342", name: "Colon and Rectal Associates, Ltd.", city: "Abington", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6660,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA342",GROUP_DESC:"PA342 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6660,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA342",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA342",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6660,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA342", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7660 ,protection_group_id: -6660, protection_element_id:-6660], primaryKey: false);
      insert('organizations', [ id: 106646, nci_institute_code: "PA343", name: "Cercone Village", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6661,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA343",GROUP_DESC:"PA343 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6661,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA343",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA343",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6661,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA343", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7661 ,protection_group_id: -6661, protection_element_id:-6661], primaryKey: false);
      insert('organizations', [ id: 106647, nci_institute_code: "PA344", name: "Bryn Mawr Urology", city: "Rosemont", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6662,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA344",GROUP_DESC:"PA344 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6662,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA344",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA344",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6662,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA344", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7662 ,protection_group_id: -6662, protection_element_id:-6662], primaryKey: false);
    }

    void m26() {
        // all26 (25 terms)
      insert('organizations', [ id: 106648, nci_institute_code: "PA345", name: "Penn State University", city: "University Park", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6663,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA345",GROUP_DESC:"PA345 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6663,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA345",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA345",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6663,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA345", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7663 ,protection_group_id: -6663, protection_element_id:-6663], primaryKey: false);
      insert('organizations', [ id: 106649, nci_institute_code: "PA346", name: "UPMC Cancer Center at UPMC McKeesport", city: "McKeesport", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6664,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA346",GROUP_DESC:"PA346 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6664,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA346",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA346",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6664,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA346", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7664 ,protection_group_id: -6664, protection_element_id:-6664], primaryKey: false);
      insert('organizations', [ id: 106650, nci_institute_code: "PA347", name: "Abramson Family Cancer Research Institute", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6665,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA347",GROUP_DESC:"PA347 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6665,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA347",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA347",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6665,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA347", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7665 ,protection_group_id: -6665, protection_element_id:-6665], primaryKey: false);
      insert('organizations', [ id: 106651, nci_institute_code: "PA348", name: "Pittsburgh Clinical Research Network Inc", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6666,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA348",GROUP_DESC:"PA348 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6666,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA348",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA348",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6666,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA348", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7666 ,protection_group_id: -6666, protection_element_id:-6666], primaryKey: false);
      insert('organizations', [ id: 106652, nci_institute_code: "PA349", name: "Fox Chase Cancer Center - Cheltenham", city: "Cheltenham", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6667,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA349",GROUP_DESC:"PA349 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6667,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA349",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA349",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6667,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA349", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7667 ,protection_group_id: -6667, protection_element_id:-6667], primaryKey: false);
      insert('organizations', [ id: 106653, nci_institute_code: "PA350", name: "Greater Philadelphia Cancer and Hematology Specialists PC", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6668,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA350",GROUP_DESC:"PA350 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6668,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA350",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA350",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6668,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA350", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7668 ,protection_group_id: -6668, protection_element_id:-6668], primaryKey: false);
      insert('organizations', [ id: 106654, nci_institute_code: "PA351", name: "UPMC Cancer Center at Saint Clair Hospital", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6669,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA351",GROUP_DESC:"PA351 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6669,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA351",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA351",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6669,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA351", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7669 ,protection_group_id: -6669, protection_element_id:-6669], primaryKey: false);
      insert('organizations', [ id: 106655, nci_institute_code: "PA352", name: "Comprehensive Cancer Center", city: "Exton", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6670,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA352",GROUP_DESC:"PA352 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6670,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA352",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA352",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6670,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA352", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7670 ,protection_group_id: -6670, protection_element_id:-6670], primaryKey: false);
      insert('organizations', [ id: 106656, nci_institute_code: "PA353", name: "University of Pittsburgh Medical Center -Moon Township", city: "Moon Township", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6671,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA353",GROUP_DESC:"PA353 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6671,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA353",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA353",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6671,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA353", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7671 ,protection_group_id: -6671, protection_element_id:-6671], primaryKey: false);
      insert('organizations', [ id: 106657, nci_institute_code: "PA354", name: "Pittsburgh Gynecologic Oncology", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6672,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA354",GROUP_DESC:"PA354 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6672,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA354",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA354",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6672,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA354", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7672 ,protection_group_id: -6672, protection_element_id:-6672], primaryKey: false);
      insert('organizations', [ id: 106658, nci_institute_code: "PA355", name: "Franklin Surgical Group", city: "Franklin", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6673,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA355",GROUP_DESC:"PA355 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6673,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA355",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA355",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6673,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA355", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7673 ,protection_group_id: -6673, protection_element_id:-6673], primaryKey: false);
      insert('organizations', [ id: 106659, nci_institute_code: "PA356", name: "Berks Hematology & Oncology Associates", city: "West Reading", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6674,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA356",GROUP_DESC:"PA356 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6674,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA356",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA356",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6674,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA356", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7674 ,protection_group_id: -6674, protection_element_id:-6674], primaryKey: false);
      insert('organizations', [ id: 106660, nci_institute_code: "PA357", name: "Comprehensive Breast Center", city: "Cranberry Township", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6675,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA357",GROUP_DESC:"PA357 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6675,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA357",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA357",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6675,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA357", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7675 ,protection_group_id: -6675, protection_element_id:-6675], primaryKey: false);
      insert('organizations', [ id: 106661, nci_institute_code: "PA358", name: "Bux-Mont Oncology Hematology Medical Associates PC", city: "Sellersville", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6676,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA358",GROUP_DESC:"PA358 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6676,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA358",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA358",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6676,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA358", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7676 ,protection_group_id: -6676, protection_element_id:-6676], primaryKey: false);
      insert('organizations', [ id: 106662, nci_institute_code: "PA359", name: "The Reading Hospital at Wyomissing", city: "Wyomissing", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6677,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA359",GROUP_DESC:"PA359 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6677,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA359",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA359",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6677,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA359", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7677 ,protection_group_id: -6677, protection_element_id:-6677], primaryKey: false);
      insert('organizations', [ id: 106663, nci_institute_code: "PA360", name: "Breast Care Specialists PC", city: "Allentown", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6678,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA360",GROUP_DESC:"PA360 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6678,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA360",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA360",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6678,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA360", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7678 ,protection_group_id: -6678, protection_element_id:-6678], primaryKey: false);
      insert('organizations', [ id: 106664, nci_institute_code: "PA361", name: "Urologic Associates of Allentown Inc", city: "Allentown", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6679,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA361",GROUP_DESC:"PA361 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6679,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA361",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA361",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6679,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA361", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7679 ,protection_group_id: -6679, protection_element_id:-6679], primaryKey: false);
      insert('organizations', [ id: 106665, nci_institute_code: "PA362", name: "East Shore Oncology", city: "Harrisburg", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6680,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA362",GROUP_DESC:"PA362 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6680,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA362",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA362",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6680,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA362", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7680 ,protection_group_id: -6680, protection_element_id:-6680], primaryKey: false);
      insert('organizations', [ id: 106666, nci_institute_code: "PA363", name: "Precision Therapeutics Inc", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6681,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA363",GROUP_DESC:"PA363 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6681,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA363",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA363",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6681,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA363", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7681 ,protection_group_id: -6681, protection_element_id:-6681], primaryKey: false);
      insert('organizations', [ id: 106667, nci_institute_code: "PA364", name: "Meta-Hilberg Hematology & Oncology Associates Inc", city: "McKeesport", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6682,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA364",GROUP_DESC:"PA364 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6682,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA364",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA364",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6682,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA364", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7682 ,protection_group_id: -6682, protection_element_id:-6682], primaryKey: false);
      insert('organizations', [ id: 106668, nci_institute_code: "PA365", name: "TLC Cancer Clinic", city: "York", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6683,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA365",GROUP_DESC:"PA365 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6683,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA365",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA365",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6683,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA365", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7683 ,protection_group_id: -6683, protection_element_id:-6683], primaryKey: false);
      insert('organizations', [ id: 106669, nci_institute_code: "PA366", name: "Hematology Oncology Physicians of Lancaster", city: "Lancaster", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6684,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA366",GROUP_DESC:"PA366 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6684,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA366",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA366",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6684,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA366", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7684 ,protection_group_id: -6684, protection_element_id:-6684], primaryKey: false);
      insert('organizations', [ id: 106670, nci_institute_code: "PA367", name: "Pocono Hematology and Oncology", city: "East Stroudsburg", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6685,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA367",GROUP_DESC:"PA367 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6685,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA367",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA367",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6685,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA367", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7685 ,protection_group_id: -6685, protection_element_id:-6685], primaryKey: false);
      insert('organizations', [ id: 106671, nci_institute_code: "PA368", name: "Reading Surgery Limited", city: "West Reading", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6686,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA368",GROUP_DESC:"PA368 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6686,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA368",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA368",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6686,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA368", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7686 ,protection_group_id: -6686, protection_element_id:-6686], primaryKey: false);
      insert('organizations', [ id: 106672, nci_institute_code: "PA369", name: "UPMC Cancer Center - Oakbrook Commons", city: "Greensburg", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6687,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA369",GROUP_DESC:"PA369 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6687,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA369",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA369",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6687,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA369", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7687 ,protection_group_id: -6687, protection_element_id:-6687], primaryKey: false);
    }

    void m27() {
        // all27 (25 terms)
      insert('organizations', [ id: 106673, nci_institute_code: "PA370", name: "Adams Hanover Urology Associates PC", city: "Gettysburg", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6688,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA370",GROUP_DESC:"PA370 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6688,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA370",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA370",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6688,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA370", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7688 ,protection_group_id: -6688, protection_element_id:-6688], primaryKey: false);
      insert('organizations', [ id: 106674, nci_institute_code: "PA371", name: "UPMC Cancer Center - Robert E Eberly Pavilion", city: "Uniontown", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6689,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA371",GROUP_DESC:"PA371 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6689,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA371",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA371",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6689,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA371", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7689 ,protection_group_id: -6689, protection_element_id:-6689], primaryKey: false);
      insert('organizations', [ id: 106675, nci_institute_code: "PA372", name: "Lancaster Hematology - Oncology Care", city: "Lancaster", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6690,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA372",GROUP_DESC:"PA372 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6690,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA372",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA372",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6690,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA372", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7690 ,protection_group_id: -6690, protection_element_id:-6690], primaryKey: false);
      insert('organizations', [ id: 106676, nci_institute_code: "PA373", name: "Apple Hill Medical Center", city: "York", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6691,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA373",GROUP_DESC:"PA373 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6691,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA373",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA373",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6691,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA373", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7691 ,protection_group_id: -6691, protection_element_id:-6691], primaryKey: false);
      insert('organizations', [ id: 106677, nci_institute_code: "PA374", name: "Susquehanna Breast Care Center", city: "Lemoyne", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6692,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA374",GROUP_DESC:"PA374 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6692,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA374",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA374",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6692,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA374", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7692 ,protection_group_id: -6692, protection_element_id:-6692], primaryKey: false);
      insert('organizations', [ id: 106678, nci_institute_code: "PA375", name: "Medical Center Clinic", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6693,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA375",GROUP_DESC:"PA375 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6693,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA375",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA375",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6693,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA375", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7693 ,protection_group_id: -6693, protection_element_id:-6693], primaryKey: false);
      insert('organizations', [ id: 106679, nci_institute_code: "PA376", name: "Geisinger Henry Cancer Center", city: "Wilkes Barre", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6694,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA376",GROUP_DESC:"PA376 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6694,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA376",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA376",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6694,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA376", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7694 ,protection_group_id: -6694, protection_element_id:-6694], primaryKey: false);
      insert('organizations', [ id: 106680, nci_institute_code: "PA377", name: "Penn Orthopaedic Institute", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6695,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA377",GROUP_DESC:"PA377 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6695,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA377",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA377",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6695,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA377", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7695 ,protection_group_id: -6695, protection_element_id:-6695], primaryKey: false);
      insert('organizations', [ id: 106681, nci_institute_code: "PA378", name: "The Regional Cancer Center", city: "Meadville", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6696,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA378",GROUP_DESC:"PA378 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6696,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA378",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA378",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6696,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA378", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7696 ,protection_group_id: -6696, protection_element_id:-6696], primaryKey: false);
      insert('organizations', [ id: 106682, nci_institute_code: "PA379", name: "Northeast Regional Cancer Institute", city: "Scranton", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6697,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA379",GROUP_DESC:"PA379 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6697,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA379",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA379",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6697,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA379", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7697 ,protection_group_id: -6697, protection_element_id:-6697], primaryKey: false);
      insert('organizations', [ id: 106683, nci_institute_code: "PA380", name: "University of Pennsylvania School of Medicine", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6698,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA380",GROUP_DESC:"PA380 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6698,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA380",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA380",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6698,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA380", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7698 ,protection_group_id: -6698, protection_element_id:-6698], primaryKey: false);
      insert('organizations', [ id: 106684, nci_institute_code: "PA381", name: "Colon Rectal Surgery Associates PC", city: "Allentown", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6699,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA381",GROUP_DESC:"PA381 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6699,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA381",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA381",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6699,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA381", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7699 ,protection_group_id: -6699, protection_element_id:-6699], primaryKey: false);
      insert('organizations', [ id: 106685, nci_institute_code: "PA382", name: "American Association for Cancer Research", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6700,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA382",GROUP_DESC:"PA382 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6700,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA382",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA382",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6700,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA382", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7700 ,protection_group_id: -6700, protection_element_id:-6700], primaryKey: false);
      insert('organizations', [ id: 106686, nci_institute_code: "PA383", name: "Bux-Mont Oncology Hematology Medical Associates PC - Chalfont", city: "Chalfont", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6701,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA383",GROUP_DESC:"PA383 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6701,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA383",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA383",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6701,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA383", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7701 ,protection_group_id: -6701, protection_element_id:-6701], primaryKey: false);
      insert('organizations', [ id: 106687, nci_institute_code: "PA384", name: "Eastern Regional Medical Center", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6702,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA384",GROUP_DESC:"PA384 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6702,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA384",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA384",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6702,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA384", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7702 ,protection_group_id: -6702, protection_element_id:-6702], primaryKey: false);
      insert('organizations', [ id: 106688, nci_institute_code: "PA385", name: "Temple University Children's Medical Center", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6703,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA385",GROUP_DESC:"PA385 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6703,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA385",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA385",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6703,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA385", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7703 ,protection_group_id: -6703, protection_element_id:-6703], primaryKey: false);
      insert('organizations', [ id: 106689, nci_institute_code: "PA386", name: "UPMC Cancer Center at Clarion Hospital", city: "Clarion", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6704,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA386",GROUP_DESC:"PA386 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6704,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA386",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA386",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6704,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA386", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7704 ,protection_group_id: -6704, protection_element_id:-6704], primaryKey: false);
      insert('organizations', [ id: 106690, nci_institute_code: "PA388", name: "Consultants in Medical Oncology and Hematology PC - Drexel Hill", city: "Drexel Hill", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6705,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA388",GROUP_DESC:"PA388 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6705,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA388",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA388",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6705,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA388", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7705 ,protection_group_id: -6705, protection_element_id:-6705], primaryKey: false);
      insert('organizations', [ id: 106691, nci_institute_code: "PA389", name: "Jae T Yang MD FACS Ltd", city: "Kittanning", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6706,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA389",GROUP_DESC:"PA389 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6706,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA389",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA389",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6706,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA389", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7706 ,protection_group_id: -6706, protection_element_id:-6706], primaryKey: false);
      insert('organizations', [ id: 106692, nci_institute_code: "PA390", name: "Oakland Neurosurgical Associates", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6707,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA390",GROUP_DESC:"PA390 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6707,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA390",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA390",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6707,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA390", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7707 ,protection_group_id: -6707, protection_element_id:-6707], primaryKey: false);
      insert('organizations', [ id: 106693, nci_institute_code: "PA391", name: "KSR PC", city: "Allentown", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6708,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA391",GROUP_DESC:"PA391 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6708,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA391",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA391",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6708,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA391", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7708 ,protection_group_id: -6708, protection_element_id:-6708], primaryKey: false);
      insert('organizations', [ id: 106694, nci_institute_code: "PA392", name: "University of Pittsburgh Medical Center - Cancer Center Washington", city: "Washington", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6709,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA392",GROUP_DESC:"PA392 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6709,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA392",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA392",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6709,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA392", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7709 ,protection_group_id: -6709, protection_element_id:-6709], primaryKey: false);
      insert('organizations', [ id: 106695, nci_institute_code: "PA393", name: "Allegheny General Hospital", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6710,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA393",GROUP_DESC:"PA393 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6710,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA393",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA393",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6710,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA393", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7710 ,protection_group_id: -6710, protection_element_id:-6710], primaryKey: false);
      insert('organizations', [ id: 106696, nci_institute_code: "PA394", name: "Lancaster Surgical Group", city: "Lancaster", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6711,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA394",GROUP_DESC:"PA394 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6711,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA394",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA394",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6711,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA394", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7711 ,protection_group_id: -6711, protection_element_id:-6711], primaryKey: false);
      insert('organizations', [ id: 106697, nci_institute_code: "PA395", name: "NSABP Tissue Bank", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6712,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA395",GROUP_DESC:"PA395 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6712,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA395",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA395",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6712,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA395", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7712 ,protection_group_id: -6712, protection_element_id:-6712], primaryKey: false);
    }

    void m28() {
        // all28 (25 terms)
      insert('organizations', [ id: 106698, nci_institute_code: "PA396", name: "Gettysbury Surgical Associates", city: "Gettysburg", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6713,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA396",GROUP_DESC:"PA396 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6713,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA396",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA396",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6713,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA396", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7713 ,protection_group_id: -6713, protection_element_id:-6713], primaryKey: false);
      insert('organizations', [ id: 106699, nci_institute_code: "PA397", name: "Oncology Hematology Associates", city: "Greenville", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6714,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA397",GROUP_DESC:"PA397 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6714,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA397",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA397",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6714,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA397", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7714 ,protection_group_id: -6714, protection_element_id:-6714], primaryKey: false);
      insert('organizations', [ id: 106700, nci_institute_code: "PA398", name: "Gettysburg Cancer Cener", city: "Gettysburg", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6715,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA398",GROUP_DESC:"PA398 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6715,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA398",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA398",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6715,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA398", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7715 ,protection_group_id: -6715, protection_element_id:-6715], primaryKey: false);
      insert('organizations', [ id: 106701, nci_institute_code: "PA399", name: "MNAP Oncology Center", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6716,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA399",GROUP_DESC:"PA399 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6716,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA399",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA399",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6716,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA399", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7716 ,protection_group_id: -6716, protection_element_id:-6716], primaryKey: false);
      insert('organizations', [ id: 106702, nci_institute_code: "PA400", name: "Fox Chase - Temple Bone Marrow Transplant Program", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6717,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA400",GROUP_DESC:"PA400 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6717,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA400",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA400",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6717,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA400", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7717 ,protection_group_id: -6717, protection_element_id:-6717], primaryKey: false);
      insert('organizations', [ id: 106703, nci_institute_code: "PA401", name: "Yorktowne Urology PC", city: "York", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6718,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA401",GROUP_DESC:"PA401 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6718,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA401",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA401",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6718,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA401", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7718 ,protection_group_id: -6718, protection_element_id:-6718], primaryKey: false);
      insert('organizations', [ id: 106704, nci_institute_code: "PA402", name: "WellSpan Cardiothoracic Surgery", city: "York", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6719,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA402",GROUP_DESC:"PA402 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6719,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA402",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA402",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6719,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA402", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7719 ,protection_group_id: -6719, protection_element_id:-6719], primaryKey: false);
      insert('organizations', [ id: 106705, nci_institute_code: "PA403", name: "Brandywine Hospital", city: "Coatesville", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6720,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA403",GROUP_DESC:"PA403 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6720,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA403",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA403",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6720,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA403", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7720 ,protection_group_id: -6720, protection_element_id:-6720], primaryKey: false);
      insert('organizations', [ id: 106706, nci_institute_code: "PA404", name: "Urology Center of Chester County", city: "Coatesville", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6721,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA404",GROUP_DESC:"PA404 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6721,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA404",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA404",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6721,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA404", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7721 ,protection_group_id: -6721, protection_element_id:-6721], primaryKey: false);
      insert('organizations', [ id: 106707, nci_institute_code: "PA405", name: "Jefferson Regional Medical Center", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6722,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA405",GROUP_DESC:"PA405 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6722,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA405",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA405",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6722,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA405", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7722 ,protection_group_id: -6722, protection_element_id:-6722], primaryKey: false);
      insert('organizations', [ id: 106708, nci_institute_code: "PA406", name: "Oncology Hematology Association", city: "Farrell", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6723,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA406",GROUP_DESC:"PA406 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6723,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA406",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA406",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6723,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA406", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7723 ,protection_group_id: -6723, protection_element_id:-6723], primaryKey: false);
      insert('organizations', [ id: 106709, nci_institute_code: "PA407", name: "Rittenhouse Hematology Oncology", city: "Philadelphia", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6724,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA407",GROUP_DESC:"PA407 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6724,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA407",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA407",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6724,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA407", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7724 ,protection_group_id: -6724, protection_element_id:-6724], primaryKey: false);
      insert('organizations', [ id: 106710, nci_institute_code: "PA408", name: "Fox Medical Oncology Center", city: "Paoli", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6725,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA408",GROUP_DESC:"PA408 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6725,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA408",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA408",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6725,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA408", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7725 ,protection_group_id: -6725, protection_element_id:-6725], primaryKey: false);
      insert('organizations', [ id: 106711, nci_institute_code: "PA409", name: "Bronstein and Jeffries PA", city: "Harrisburg", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6726,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA409",GROUP_DESC:"PA409 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6726,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA409",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA409",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6726,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA409", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7726 ,protection_group_id: -6726, protection_element_id:-6726], primaryKey: false);
      insert('organizations', [ id: 106712, nci_institute_code: "PA410", name: "Lancaster NeuroScience and Spine Associates", city: "Lancaster", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6727,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA410",GROUP_DESC:"PA410 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6727,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA410",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA410",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6727,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA410", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7727 ,protection_group_id: -6727, protection_element_id:-6727], primaryKey: false);
      insert('organizations', [ id: 106713, nci_institute_code: "PA411", name: "Holy Redeemer Hospital and Medical Center", city: "Meadowbrook", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6728,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA411",GROUP_DESC:"PA411 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6728,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA411",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA411",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6728,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA411", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7728 ,protection_group_id: -6728, protection_element_id:-6728], primaryKey: false);
      insert('organizations', [ id: 106714, nci_institute_code: "PA412", name: "Susquehanna Cancer Center", city: "Williamsport", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6729,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA412",GROUP_DESC:"PA412 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6729,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA412",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA412",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6729,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA412", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7729 ,protection_group_id: -6729, protection_element_id:-6729], primaryKey: false);
      insert('organizations', [ id: 106715, nci_institute_code: "PA413", name: "Rajen Oza MD PC", city: "Bethlehem", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6730,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA413",GROUP_DESC:"PA413 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6730,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA413",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA413",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6730,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA413", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7730 ,protection_group_id: -6730, protection_element_id:-6730], primaryKey: false);
      insert('organizations', [ id: 106716, nci_institute_code: "PA414", name: "NSABP Foundation Inc", city: "Pittsburgh", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6731,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA414",GROUP_DESC:"PA414 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6731,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA414",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA414",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6731,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA414", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7731 ,protection_group_id: -6731, protection_element_id:-6731], primaryKey: false);
      insert('organizations', [ id: 106717, nci_institute_code: "PA415", name: "Consultants in Medical and Hematology PC - Ridley Park", city: "Ridley Park", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6732,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA415",GROUP_DESC:"PA415 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6732,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA415",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA415",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6732,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA415", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7732 ,protection_group_id: -6732, protection_element_id:-6732], primaryKey: false);
      insert('organizations', [ id: 106718, nci_institute_code: "PA416", name: "Hematology Oncology Associates", city: "Media", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6733,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA416",GROUP_DESC:"PA416 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6733,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA416",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA416",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6733,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA416", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7733 ,protection_group_id: -6733, protection_element_id:-6733], primaryKey: false);
      insert('organizations', [ id: 106719, nci_institute_code: "PA417", name: "Hematology and Oncology Associates of Northeastern Pennsylvania", city: "Dunmore", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6734,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA417",GROUP_DESC:"PA417 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6734,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA417",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA417",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6734,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA417", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7734 ,protection_group_id: -6734, protection_element_id:-6734], primaryKey: false);
      insert('organizations', [ id: 106720, nci_institute_code: "PA418", name: "Bryn Mawr Women's Health Associates", city: "Rosemont", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6735,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA418",GROUP_DESC:"PA418 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6735,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA418",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA418",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6735,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA418", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7735 ,protection_group_id: -6735, protection_element_id:-6735], primaryKey: false);
      insert('organizations', [ id: 106721, nci_institute_code: "PA419", name: "Arnold Palmer Pavilion-Mount Pleasant", city: "Mount Pleasant", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6736,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA419",GROUP_DESC:"PA419 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6736,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PA419",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PA419",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6736,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PA419", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7736 ,protection_group_id: -6736, protection_element_id:-6736], primaryKey: false);
      insert('organizations', [ id: 106722, nci_institute_code: "PAHO", name: "Pan American Health Organization", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6737,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PAHO",GROUP_DESC:"PAHO group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6737,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PAHO",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PAHO",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6737,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PAHO", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7737 ,protection_group_id: -6737, protection_element_id:-6737], primaryKey: false);
    }

    void m29() {
        // all29 (25 terms)
      insert('organizations', [ id: 106723, nci_institute_code: "PPC", name: "Pennsylvania Prostate Cancer Coalition (PPC) Intercultural Cancer Council (ICC)", city: "Camp Hill", state: "PA", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6738,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PPC",GROUP_DESC:"PPC group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6738,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PPC",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PPC",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6738,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PPC", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7738 ,protection_group_id: -6738, protection_element_id:-6738], primaryKey: false);
      insert('organizations', [ id: 106724, nci_institute_code: "PR001", name: "Mayaguez Medical Center", city: "Mayaguez", state: "PR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6739,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR001",GROUP_DESC:"PR001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6739,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PR001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PR001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6739,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7739 ,protection_group_id: -6739, protection_element_id:-6739], primaryKey: false);
      insert('organizations', [ id: 106725, nci_institute_code: "PR002", name: "Damas Hospital", city: "Ponce", state: "PR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6740,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR002",GROUP_DESC:"PR002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6740,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PR002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PR002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6740,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7740 ,protection_group_id: -6740, protection_element_id:-6740], primaryKey: false);
      insert('organizations', [ id: 106726, nci_institute_code: "PR003", name: "Marina Station", city: "Mayaguez", state: "PR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6741,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR003",GROUP_DESC:"PR003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6741,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PR003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PR003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6741,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7741 ,protection_group_id: -6741, protection_element_id:-6741], primaryKey: false);
      insert('organizations', [ id: 106727, nci_institute_code: "PR004", name: "Hospital Metropolitano", city: "Caparra Heights", state: "PR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6742,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR004",GROUP_DESC:"PR004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6742,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PR004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PR004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6742,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7742 ,protection_group_id: -6742, protection_element_id:-6742], primaryKey: false);
      insert('organizations', [ id: 106728, nci_institute_code: "PR005", name: "Caparra Heights Station", city: "Cuaynabo", state: "PR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6743,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR005",GROUP_DESC:"PR005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6743,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PR005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PR005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6743,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7743 ,protection_group_id: -6743, protection_element_id:-6743], primaryKey: false);
      insert('organizations', [ id: 106729, nci_institute_code: "PR006", name: "San Juan Minority Based CCOP", city: "San Juan", state: "PR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6744,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR006",GROUP_DESC:"PR006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6744,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PR006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PR006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6744,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7744 ,protection_group_id: -6744, protection_element_id:-6744], primaryKey: false);
      insert('organizations', [ id: 106730, nci_institute_code: "PR007", name: "Medical Science Center", city: "San Juan", state: "PR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6745,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR007",GROUP_DESC:"PR007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6745,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PR007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PR007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6745,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7745 ,protection_group_id: -6745, protection_element_id:-6745], primaryKey: false);
      insert('organizations', [ id: 106731, nci_institute_code: "PR008", name: "University of Puerto Rico", city: "San Juan", state: "PR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6746,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR008",GROUP_DESC:"PR008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6746,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PR008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PR008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6746,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7746 ,protection_group_id: -6746, protection_element_id:-6746], primaryKey: false);
      insert('organizations', [ id: 106732, nci_institute_code: "PR010", name: "San Juan Veterans Affairs Medical Center", city: "San Juan", state: "PR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6747,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR010",GROUP_DESC:"PR010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6747,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PR010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PR010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6747,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7747 ,protection_group_id: -6747, protection_element_id:-6747], primaryKey: false);
      insert('organizations', [ id: 106733, nci_institute_code: "PR011", name: "Department of Veterans Affairs", city: "San Juan", state: "PR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6748,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR011",GROUP_DESC:"PR011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6748,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PR011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PR011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6748,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7748 ,protection_group_id: -6748, protection_element_id:-6748], primaryKey: false);
      insert('organizations', [ id: 106734, nci_institute_code: "PR012", name: "Metropolitan Hospital", city: "San Juan", state: "PR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6749,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR012",GROUP_DESC:"PR012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6749,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PR012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PR012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6749,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7749 ,protection_group_id: -6749, protection_element_id:-6749], primaryKey: false);
      insert('organizations', [ id: 106735, nci_institute_code: "PR013", name: "Teacher's Hospital", city: "Hato Rey", state: "PR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6750,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR013",GROUP_DESC:"PR013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6750,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PR013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PR013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6750,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7750 ,protection_group_id: -6750, protection_element_id:-6750], primaryKey: false);
      insert('organizations', [ id: 106736, nci_institute_code: "PR014", name: "Hospital Auxilio-Mutuo", city: "Hato-Rey", state: "PR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6751,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR014",GROUP_DESC:"PR014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6751,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PR014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PR014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6751,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7751 ,protection_group_id: -6751, protection_element_id:-6751], primaryKey: false);
      insert('organizations', [ id: 106737, nci_institute_code: "PR015", name: "Puerto Rico Medical Center", city: "San Juan", state: "PR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6752,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR015",GROUP_DESC:"PR015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6752,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PR015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PR015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6752,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7752 ,protection_group_id: -6752, protection_element_id:-6752], primaryKey: false);
      insert('organizations', [ id: 106738, nci_institute_code: "PR016", name: "San Juan City Hospital", city: "San Juan", state: "PR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6753,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR016",GROUP_DESC:"PR016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6753,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PR016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PR016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6753,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7753 ,protection_group_id: -6753, protection_element_id:-6753], primaryKey: false);
      insert('organizations', [ id: 106739, nci_institute_code: "PR017", name: "San Jorge Children's Hospital", city: "Santurce", state: "PR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6754,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR017",GROUP_DESC:"PR017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6754,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PR017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PR017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6754,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7754 ,protection_group_id: -6754, protection_element_id:-6754], primaryKey: false);
      insert('organizations', [ id: 106740, nci_institute_code: "PR018", name: "University Pediatric Hospital", city: "Rio Piedras", state: "PR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6755,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR018",GROUP_DESC:"PR018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6755,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PR018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PR018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6755,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7755 ,protection_group_id: -6755, protection_element_id:-6755], primaryKey: false);
      insert('organizations', [ id: 106741, nci_institute_code: "PR019", name: "University Children's Hospital", city: "San Juan", state: "PR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6756,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR019",GROUP_DESC:"PR019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6756,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PR019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PR019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6756,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7756 ,protection_group_id: -6756, protection_element_id:-6756], primaryKey: false);
      insert('organizations', [ id: 106742, nci_institute_code: "PR020", name: "Clinica Las America", city: "San Juan", state: "PR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6757,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR020",GROUP_DESC:"PR020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6757,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PR020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PR020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6757,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7757 ,protection_group_id: -6757, protection_element_id:-6757], primaryKey: false);
      insert('organizations', [ id: 106743, nci_institute_code: "PR021", name: "I. Gonzales Martinez-Oncologic Hospital", city: "San Juan", state: "PR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6758,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR021",GROUP_DESC:"PR021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6758,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PR021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PR021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6758,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7758 ,protection_group_id: -6758, protection_element_id:-6758], primaryKey: false);
      insert('organizations', [ id: 106744, nci_institute_code: "PR022", name: "Hospital Universitario Ramon Ruiz Arnau", city: "Bayamon", state: "PR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6759,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR022",GROUP_DESC:"PR022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6759,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PR022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PR022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6759,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7759 ,protection_group_id: -6759, protection_element_id:-6759], primaryKey: false);
      insert('organizations', [ id: 106745, nci_institute_code: "PR023", name: "Hato Rey Hematology/Oncology Group", city: "San Juan", state: "PR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6760,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR023",GROUP_DESC:"PR023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6760,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PR023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PR023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6760,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7760 ,protection_group_id: -6760, protection_element_id:-6760], primaryKey: false);
      insert('organizations', [ id: 106746, nci_institute_code: "PR024", name: "Centro Clinico San Patrico", city: "San Juan", state: "PR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6761,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR024",GROUP_DESC:"PR024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6761,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PR024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PR024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6761,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7761 ,protection_group_id: -6761, protection_element_id:-6761], primaryKey: false);
      insert('organizations', [ id: 106747, nci_institute_code: "PR025", name: "Andres Grillasca Hospital", city: "Ponce", state: "PR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6762,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR025",GROUP_DESC:"PR025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6762,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PR025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PR025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6762,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7762 ,protection_group_id: -6762, protection_element_id:-6762], primaryKey: false);
    }

    void m30() {
        // all30 (25 terms)
      insert('organizations', [ id: 106748, nci_institute_code: "PR026", name: "Altamira Family Medicine", city: "San Juan", state: "PR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6763,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR026",GROUP_DESC:"PR026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6763,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PR026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PR026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6763,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7763 ,protection_group_id: -6763, protection_element_id:-6763], primaryKey: false);
      insert('organizations', [ id: 106749, nci_institute_code: "PR027", name: "Orocovis Medical Center", city: "Orocovis", state: "PR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6764,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR027",GROUP_DESC:"PR027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6764,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PR027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PR027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6764,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7764 ,protection_group_id: -6764, protection_element_id:-6764], primaryKey: false);
      insert('organizations', [ id: 106750, nci_institute_code: "PR028", name: "Centro De Cancer De La Muntana", city: "Bayamon", state: "PR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6765,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR028",GROUP_DESC:"PR028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6765,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PR028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PR028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6765,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7765 ,protection_group_id: -6765, protection_element_id:-6765], primaryKey: false);
      insert('organizations', [ id: 106751, nci_institute_code: "PR029", name: "San Jorge Children's Medical Specialties", city: "San Juan", state: "PR", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6766,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR029",GROUP_DESC:"PR029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6766,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PR029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PR029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6766,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PR029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7766 ,protection_group_id: -6766, protection_element_id:-6766], primaryKey: false);
      insert('organizations', [ id: 106752, nci_institute_code: "PVACCG", name: "Pacific Va Cancer Chemotherapy Group", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6767,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PVACCG",GROUP_DESC:"PVACCG group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6767,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PVACCG",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PVACCG",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6767,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PVACCG", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7767 ,protection_group_id: -6767, protection_element_id:-6767], primaryKey: false);
      insert('organizations', [ id: 106753, nci_institute_code: "PVS", name: "Polycythemia Vera Study Group", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6768,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PVS",GROUP_DESC:"PVS group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6768,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.PVS",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.PVS",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6768,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.PVS", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7768 ,protection_group_id: -6768, protection_element_id:-6768], primaryKey: false);
      insert('organizations', [ id: 106754, nci_institute_code: "RI001", name: "Memorial Hospital of Rhode Island", city: "Pawtucket", state: "RI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6769,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI001",GROUP_DESC:"RI001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6769,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.RI001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.RI001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6769,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7769 ,protection_group_id: -6769, protection_element_id:-6769], primaryKey: false);
      insert('organizations', [ id: 106755, nci_institute_code: "RI002", name: "Newport Hospital", city: "Newport", state: "RI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6770,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI002",GROUP_DESC:"RI002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6770,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.RI002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.RI002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6770,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7770 ,protection_group_id: -6770, protection_element_id:-6770], primaryKey: false);
      insert('organizations', [ id: 106756, nci_institute_code: "RI003", name: "Kent County Hospital", city: "Warwick", state: "RI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6771,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI003",GROUP_DESC:"RI003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6771,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.RI003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.RI003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6771,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7771 ,protection_group_id: -6771, protection_element_id:-6771], primaryKey: false);
      insert('organizations', [ id: 106757, nci_institute_code: "RI005", name: "Rhode Island Hospital", city: "Providence", state: "RI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6772,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI005",GROUP_DESC:"RI005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6772,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.RI005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.RI005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6772,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7772 ,protection_group_id: -6772, protection_element_id:-6772], primaryKey: false);
      insert('organizations', [ id: 106758, nci_institute_code: "RI006", name: "Miriam Hospital", city: "Providence", state: "RI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6773,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI006",GROUP_DESC:"RI006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6773,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.RI006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.RI006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6773,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7773 ,protection_group_id: -6773, protection_element_id:-6773], primaryKey: false);
      insert('organizations', [ id: 106759, nci_institute_code: "RI007", name: "Roger Williams Hospital", city: "Providence", state: "RI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6774,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI007",GROUP_DESC:"RI007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6774,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.RI007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.RI007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6774,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7774 ,protection_group_id: -6774, protection_element_id:-6774], primaryKey: false);
      insert('organizations', [ id: 106760, nci_institute_code: "RI008", name: "Providence Veterans Administration Hospital", city: "Providence", state: "RI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6775,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI008",GROUP_DESC:"RI008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6775,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.RI008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.RI008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6775,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7775 ,protection_group_id: -6775, protection_element_id:-6775], primaryKey: false);
      insert('organizations', [ id: 106761, nci_institute_code: "RI009", name: "Oncology Hematology Associates", city: "Providence", state: "RI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6776,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI009",GROUP_DESC:"RI009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6776,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.RI009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.RI009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6776,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7776 ,protection_group_id: -6776, protection_element_id:-6776], primaryKey: false);
      insert('organizations', [ id: 106762, nci_institute_code: "RI010", name: "Brown University", city: "Providence", state: "RI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6777,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI010",GROUP_DESC:"RI010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6777,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.RI010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.RI010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6777,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7777 ,protection_group_id: -6777, protection_element_id:-6777], primaryKey: false);
      insert('organizations', [ id: 106763, nci_institute_code: "RI011", name: "Memorial Hospital of Rhode Island", city: "Providence", state: "RI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6778,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI011",GROUP_DESC:"RI011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6778,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.RI011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.RI011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6778,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7778 ,protection_group_id: -6778, protection_element_id:-6778], primaryKey: false);
      insert('organizations', [ id: 106764, nci_institute_code: "RI012", name: "Women's and Infants Hospital", city: "Providence", state: "RI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6779,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI012",GROUP_DESC:"RI012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6779,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.RI012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.RI012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6779,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7779 ,protection_group_id: -6779, protection_element_id:-6779], primaryKey: false);
      insert('organizations', [ id: 106765, nci_institute_code: "RI013", name: "Randall Surgical Group", city: "Providence", state: "RI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6780,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI013",GROUP_DESC:"RI013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6780,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.RI013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.RI013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6780,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7780 ,protection_group_id: -6780, protection_element_id:-6780], primaryKey: false);
      insert('organizations', [ id: 106766, nci_institute_code: "RI014", name: "Hematology and Oncology Associates of Rhode Island Inc", city: "Cranston", state: "RI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6781,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI014",GROUP_DESC:"RI014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6781,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.RI014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.RI014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6781,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7781 ,protection_group_id: -6781, protection_element_id:-6781], primaryKey: false);
      insert('organizations', [ id: 106767, nci_institute_code: "RI015", name: "Radiation Oncology Associates, Incorporated", city: "Providence", state: "RI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6782,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI015",GROUP_DESC:"RI015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6782,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.RI015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.RI015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6782,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7782 ,protection_group_id: -6782, protection_element_id:-6782], primaryKey: false);
      insert('organizations', [ id: 106768, nci_institute_code: "RI016", name: "Rhode Island Cancer Council, Inc.,", city: "Pawtucket", state: "RI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6783,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI016",GROUP_DESC:"RI016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6783,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.RI016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.RI016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6783,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7783 ,protection_group_id: -6783, protection_element_id:-6783], primaryKey: false);
      insert('organizations', [ id: 106769, nci_institute_code: "RI017", name: "Maddock Center for Radiation Oncology", city: "Warwick", state: "RI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6784,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI017",GROUP_DESC:"RI017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6784,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.RI017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.RI017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6784,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7784 ,protection_group_id: -6784, protection_element_id:-6784], primaryKey: false);
      insert('organizations', [ id: 106770, nci_institute_code: "RI018", name: "Providence Surgical Care Center", city: "Providence", state: "RI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6785,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI018",GROUP_DESC:"RI018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6785,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.RI018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.RI018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6785,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7785 ,protection_group_id: -6785, protection_element_id:-6785], primaryKey: false);
      insert('organizations', [ id: 106771, nci_institute_code: "RI019", name: "Rhode Island Colorectal Clinic", city: "Pawtucket", state: "RI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6786,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI019",GROUP_DESC:"RI019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6786,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.RI019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.RI019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6786,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7786 ,protection_group_id: -6786, protection_element_id:-6786], primaryKey: false);
      insert('organizations', [ id: 106772, nci_institute_code: "RI020", name: "Roger Williams Medical Center", city: "Providence", state: "RI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6787,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI020",GROUP_DESC:"RI020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6787,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.RI020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.RI020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6787,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7787 ,protection_group_id: -6787, protection_element_id:-6787], primaryKey: false);
    }

    void m31() {
        // all31 (25 terms)
      insert('organizations', [ id: 106773, nci_institute_code: "RI021", name: "University Surgical Associates", city: "Providence", state: "RI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6788,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI021",GROUP_DESC:"RI021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6788,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.RI021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.RI021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6788,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7788 ,protection_group_id: -6788, protection_element_id:-6788], primaryKey: false);
      insert('organizations', [ id: 106774, nci_institute_code: "RI022", name: "University Medical Group", city: "Warwick", state: "RI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6789,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI022",GROUP_DESC:"RI022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6789,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.RI022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.RI022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6789,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7789 ,protection_group_id: -6789, protection_element_id:-6789], primaryKey: false);
      insert('organizations', [ id: 106775, nci_institute_code: "RI023", name: "University Surgical Associates- Medical Center", city: "Providence", state: "RI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6790,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI023",GROUP_DESC:"RI023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6790,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.RI023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.RI023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6790,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7790 ,protection_group_id: -6790, protection_element_id:-6790], primaryKey: false);
      insert('organizations', [ id: 106776, nci_institute_code: "RI024", name: "Landmark Medical Center", city: "Woonsocket", state: "RI", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6791,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI024",GROUP_DESC:"RI024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6791,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.RI024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.RI024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6791,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.RI024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7791 ,protection_group_id: -6791, protection_element_id:-6791], primaryKey: false);
      insert('organizations', [ id: 106777, nci_institute_code: "SC001", name: "University of South Carolina School of Medicine", city: "Columbia", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6792,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC001",GROUP_DESC:"SC001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6792,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6792,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7792 ,protection_group_id: -6792, protection_element_id:-6792], primaryKey: false);
      insert('organizations', [ id: 106778, nci_institute_code: "SC002", name: "WJB Dorn Veterans Administration Medical Center", city: "Columbia", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6793,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC002",GROUP_DESC:"SC002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6793,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6793,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7793 ,protection_group_id: -6793, protection_element_id:-6793], primaryKey: false);
      insert('organizations', [ id: 106779, nci_institute_code: "SC003", name: "Palmetto Health Richland", city: "Columbia", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6794,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC003",GROUP_DESC:"SC003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6794,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6794,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7794 ,protection_group_id: -6794, protection_element_id:-6794], primaryKey: false);
      insert('organizations', [ id: 106780, nci_institute_code: "SC004", name: "South Carolina Oncology Associates PA", city: "Columbia", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6795,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC004",GROUP_DESC:"SC004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6795,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6795,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7795 ,protection_group_id: -6795, protection_element_id:-6795], primaryKey: false);
      insert('organizations', [ id: 106781, nci_institute_code: "SC005", name: "Moncreif Army Hospital", city: "Columbia", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6796,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC005",GROUP_DESC:"SC005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6796,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6796,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7796 ,protection_group_id: -6796, protection_element_id:-6796], primaryKey: false);
      insert('organizations', [ id: 106782, nci_institute_code: "SC006", name: "Baptist Medical Center", city: "Columbia", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6797,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC006",GROUP_DESC:"SC006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6797,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6797,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7797 ,protection_group_id: -6797, protection_element_id:-6797], primaryKey: false);
      insert('organizations', [ id: 106783, nci_institute_code: "SC007", name: "Spartanburg General Hospital", city: "Spartanburg", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6798,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC007",GROUP_DESC:"SC007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6798,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6798,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7798 ,protection_group_id: -6798, protection_element_id:-6798], primaryKey: false);
      insert('organizations', [ id: 106784, nci_institute_code: "SC008", name: "Medical University of South Carolina", city: "Charleston", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6799,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC008",GROUP_DESC:"SC008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6799,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6799,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7799 ,protection_group_id: -6799, protection_element_id:-6799], primaryKey: false);
      insert('organizations', [ id: 106785, nci_institute_code: "SC009", name: "Naval Regional Medical Center", city: "Charleston", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6800,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC009",GROUP_DESC:"SC009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6800,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6800,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7800 ,protection_group_id: -6800, protection_element_id:-6800], primaryKey: false);
      insert('organizations', [ id: 106786, nci_institute_code: "SC010", name: "Piedmont Oncology Associates", city: "Greenville", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6801,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC010",GROUP_DESC:"SC010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6801,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6801,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7801 ,protection_group_id: -6801, protection_element_id:-6801], primaryKey: false);
      insert('organizations', [ id: 106787, nci_institute_code: "SC011", name: "McLeod Regional Medical Center", city: "Florence", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6802,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC011",GROUP_DESC:"SC011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6802,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6802,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7802 ,protection_group_id: -6802, protection_element_id:-6802], primaryKey: false);
      insert('organizations', [ id: 106788, nci_institute_code: "SC012", name: "Florence General Hospital", city: "Florence", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6803,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC012",GROUP_DESC:"SC012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6803,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6803,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7803 ,protection_group_id: -6803, protection_element_id:-6803], primaryKey: false);
      insert('organizations', [ id: 106789, nci_institute_code: "SC013", name: "Carolina Health Care", city: "Florence", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6804,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC013",GROUP_DESC:"SC013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6804,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6804,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7804 ,protection_group_id: -6804, protection_element_id:-6804], primaryKey: false);
      insert('organizations', [ id: 106790, nci_institute_code: "SC014", name: "Greenville Cancer Treatment Center", city: "Greenville", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6805,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC014",GROUP_DESC:"SC014 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6805,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC014",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC014",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6805,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC014", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7805 ,protection_group_id: -6805, protection_element_id:-6805], primaryKey: false);
      insert('organizations', [ id: 106791, nci_institute_code: "SC015", name: "Self Memorial Hospital", city: "Greenwood", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6806,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC015",GROUP_DESC:"SC015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6806,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6806,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7806 ,protection_group_id: -6806, protection_element_id:-6806], primaryKey: false);
      insert('organizations', [ id: 106792, nci_institute_code: "SC016", name: "Naval Regional Medical Center", city: "Beaufort", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6807,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC016",GROUP_DESC:"SC016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6807,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6807,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7807 ,protection_group_id: -6807, protection_element_id:-6807], primaryKey: false);
      insert('organizations', [ id: 106793, nci_institute_code: "SC017", name: "Hilton Head Regional Medical Center", city: "Hilton Head", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6808,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC017",GROUP_DESC:"SC017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6808,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6808,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7808 ,protection_group_id: -6808, protection_element_id:-6808], primaryKey: false);
      insert('organizations', [ id: 106794, nci_institute_code: "SC018", name: "Mary Black Memorial Hospital", city: "Spartanburg", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6809,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC018",GROUP_DESC:"SC018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6809,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6809,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7809 ,protection_group_id: -6809, protection_element_id:-6809], primaryKey: false);
      insert('organizations', [ id: 106795, nci_institute_code: "SC019", name: "AnMed Health Hospital", city: "Anderson", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6810,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC019",GROUP_DESC:"SC019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6810,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6810,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7810 ,protection_group_id: -6810, protection_element_id:-6810], primaryKey: false);
      insert('organizations', [ id: 106796, nci_institute_code: "SC020", name: "Children's Hospital", city: "Charleston", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6811,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC020",GROUP_DESC:"SC020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6811,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6811,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7811 ,protection_group_id: -6811, protection_element_id:-6811], primaryKey: false);
      insert('organizations', [ id: 106797, nci_institute_code: "SC021", name: "Upstate Carolina CCOP", city: "Spartanburg", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6812,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC021",GROUP_DESC:"SC021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6812,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6812,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7812 ,protection_group_id: -6812, protection_element_id:-6812], primaryKey: false);
    }

    void m32() {
        // all32 (25 terms)
      insert('organizations', [ id: 106798, nci_institute_code: "SC022", name: "Ralph H. Johnson Veterans Administration Medical Center", city: "Charleston", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6813,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC022",GROUP_DESC:"SC022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6813,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6813,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7813 ,protection_group_id: -6813, protection_element_id:-6813], primaryKey: false);
      insert('organizations', [ id: 106799, nci_institute_code: "SC023", name: "Associate Medical Specialists", city: "Myrtle Beach", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6814,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC023",GROUP_DESC:"SC023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6814,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6814,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7814 ,protection_group_id: -6814, protection_element_id:-6814], primaryKey: false);
      insert('organizations', [ id: 106800, nci_institute_code: "SC024", name: "Spartanburg Regional Medical Center", city: "Spartanburg", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6815,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC024",GROUP_DESC:"SC024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6815,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6815,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7815 ,protection_group_id: -6815, protection_element_id:-6815], primaryKey: false);
      insert('organizations', [ id: 106801, nci_institute_code: "SC025", name: "Memorial Medical Building", city: "Greenville", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6816,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC025",GROUP_DESC:"SC025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6816,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6816,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7816 ,protection_group_id: -6816, protection_element_id:-6816], primaryKey: false);
      insert('organizations', [ id: 106802, nci_institute_code: "SC026", name: "Saint Francis Hospital", city: "Greenville", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6817,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC026",GROUP_DESC:"SC026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6817,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6817,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7817 ,protection_group_id: -6817, protection_element_id:-6817], primaryKey: false);
      insert('organizations', [ id: 106803, nci_institute_code: "SC027", name: "Roper Hospital", city: "Charleston", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6818,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC027",GROUP_DESC:"SC027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6818,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6818,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7818 ,protection_group_id: -6818, protection_element_id:-6818], primaryKey: false);
      insert('organizations', [ id: 106804, nci_institute_code: "SC029", name: "Childrens Hospital Pharmacy", city: "Charleston", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6819,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC029",GROUP_DESC:"SC029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6819,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6819,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7819 ,protection_group_id: -6819, protection_element_id:-6819], primaryKey: false);
      insert('organizations', [ id: 106805, nci_institute_code: "SC032", name: "North Hills Medical Center", city: "Greenville", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6820,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC032",GROUP_DESC:"SC032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6820,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6820,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7820 ,protection_group_id: -6820, protection_element_id:-6820], primaryKey: false);
      insert('organizations', [ id: 106806, nci_institute_code: "SC033", name: "Piedmont Internal Medicine., P.A.", city: "Greenwood", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6821,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC033",GROUP_DESC:"SC033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6821,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6821,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7821 ,protection_group_id: -6821, protection_element_id:-6821], primaryKey: false);
      insert('organizations', [ id: 106807, nci_institute_code: "SC034", name: "Charleston Hematology Oncology Associates PA", city: "Charleston", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6822,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC034",GROUP_DESC:"SC034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6822,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6822,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7822 ,protection_group_id: -6822, protection_element_id:-6822], primaryKey: false);
      insert('organizations', [ id: 106808, nci_institute_code: "SC035", name: "Charleston Hematology Oncology Associates PA", city: "Charleston", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6823,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC035",GROUP_DESC:"SC035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6823,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6823,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7823 ,protection_group_id: -6823, protection_element_id:-6823], primaryKey: false);
      insert('organizations', [ id: 106809, nci_institute_code: "SC036", name: "Greenville CCOP", city: "Greenville", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6824,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC036",GROUP_DESC:"SC036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6824,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6824,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7824 ,protection_group_id: -6824, protection_element_id:-6824], primaryKey: false);
      insert('organizations', [ id: 106810, nci_institute_code: "SC037", name: "Hematology Oncology Associates LLC", city: "Greenville", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6825,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC037",GROUP_DESC:"SC037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6825,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6825,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7825 ,protection_group_id: -6825, protection_element_id:-6825], primaryKey: false);
      insert('organizations', [ id: 106811, nci_institute_code: "SC038", name: "Cancer Centers of The Carolinas", city: "Greenville", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6826,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC038",GROUP_DESC:"SC038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6826,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6826,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7826 ,protection_group_id: -6826, protection_element_id:-6826], primaryKey: false);
      insert('organizations', [ id: 106812, nci_institute_code: "SC040", name: "BJ Workman Memorial Hospital", city: "Woodruff", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6827,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC040",GROUP_DESC:"SC040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6827,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6827,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7827 ,protection_group_id: -6827, protection_element_id:-6827], primaryKey: false);
      insert('organizations', [ id: 106813, nci_institute_code: "SC041", name: "South Carolina Oncology Associates, P.A.", city: "West Columbia", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6828,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC041",GROUP_DESC:"SC041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6828,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6828,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7828 ,protection_group_id: -6828, protection_element_id:-6828], primaryKey: false);
      insert('organizations', [ id: 106814, nci_institute_code: "SC042", name: "Wallace Thompson Hospital", city: "Union", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6829,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC042",GROUP_DESC:"SC042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6829,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6829,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7829 ,protection_group_id: -6829, protection_element_id:-6829], primaryKey: false);
      insert('organizations', [ id: 106815, nci_institute_code: "SC043", name: "Trident Medical Center", city: "Charleston", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6830,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC043",GROUP_DESC:"SC043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6830,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6830,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7830 ,protection_group_id: -6830, protection_element_id:-6830], primaryKey: false);
      insert('organizations', [ id: 106816, nci_institute_code: "SC044", name: "Hematology and Oncology Associates, LLC", city: "Senecca", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6831,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC044",GROUP_DESC:"SC044 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6831,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC044",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC044",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6831,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC044", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7831 ,protection_group_id: -6831, protection_element_id:-6831], primaryKey: false);
      insert('organizations', [ id: 106817, nci_institute_code: "SC045", name: "Greenville Memorial Hospital", city: "Greenville", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6832,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC045",GROUP_DESC:"SC045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6832,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6832,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7832 ,protection_group_id: -6832, protection_element_id:-6832], primaryKey: false);
      insert('organizations', [ id: 106818, nci_institute_code: "SC047", name: "Coastal Cancer Center", city: "Myrtle", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6833,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC047",GROUP_DESC:"SC047 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6833,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC047",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC047",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6833,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC047", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7833 ,protection_group_id: -6833, protection_element_id:-6833], primaryKey: false);
      insert('organizations', [ id: 106819, nci_institute_code: "SC048", name: "Carolina Cancer Center", city: "Aiken", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6834,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC048",GROUP_DESC:"SC048 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6834,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC048",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC048",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6834,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC048", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7834 ,protection_group_id: -6834, protection_element_id:-6834], primaryKey: false);
      insert('organizations', [ id: 106820, nci_institute_code: "SC049", name: "Palmetto Hematology Oncology", city: "Spartansburg", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6835,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC049",GROUP_DESC:"SC049 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6835,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC049",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC049",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6835,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC049", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7835 ,protection_group_id: -6835, protection_element_id:-6835], primaryKey: false);
      insert('organizations', [ id: 106821, nci_institute_code: "SC050", name: "Charleston Cancer Center", city: "Charleston", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6836,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC050",GROUP_DESC:"SC050 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6836,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC050",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC050",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6836,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC050", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7836 ,protection_group_id: -6836, protection_element_id:-6836], primaryKey: false);
      insert('organizations', [ id: 106822, nci_institute_code: "SC051", name: "Low Country Rural Health Education Consortium", city: "Varnville", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6837,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC051",GROUP_DESC:"SC051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6837,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6837,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7837 ,protection_group_id: -6837, protection_element_id:-6837], primaryKey: false);
    }

    void m33() {
        // all33 (25 terms)
      insert('organizations', [ id: 106823, nci_institute_code: "SC052", name: "Cancer Care Institute Carolina", city: "Aiken", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6838,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC052",GROUP_DESC:"SC052 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6838,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC052",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC052",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6838,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC052", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7838 ,protection_group_id: -6838, protection_element_id:-6838], primaryKey: false);
      insert('organizations', [ id: 106824, nci_institute_code: "SC053", name: "Cancer Centers of the Carolinas - Grove Commons", city: "Greenville", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6839,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC053",GROUP_DESC:"SC053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6839,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6839,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7839 ,protection_group_id: -6839, protection_element_id:-6839], primaryKey: false);
      insert('organizations', [ id: 106825, nci_institute_code: "SC054", name: "Cancer Centers of the Carolinas - Seneca", city: "Seneca", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6840,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC054",GROUP_DESC:"SC054 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6840,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC054",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC054",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6840,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC054", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7840 ,protection_group_id: -6840, protection_element_id:-6840], primaryKey: false);
      insert('organizations', [ id: 106826, nci_institute_code: "SC055", name: "Cancer Centers of the Carolinas - Easley", city: "Easley", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6841,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC055",GROUP_DESC:"SC055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6841,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6841,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7841 ,protection_group_id: -6841, protection_element_id:-6841], primaryKey: false);
      insert('organizations', [ id: 106827, nci_institute_code: "SC056", name: "Cancer Centers of the Carolinas - Spartanburg", city: "Spartanburg", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6842,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC056",GROUP_DESC:"SC056 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6842,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC056",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC056",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6842,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC056", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7842 ,protection_group_id: -6842, protection_element_id:-6842], primaryKey: false);
      insert('organizations', [ id: 106828, nci_institute_code: "SC057", name: "Cancer Centers of the Carolinas - Self Memorial Hospital", city: "Greenwood", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6843,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC057",GROUP_DESC:"SC057 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6843,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC057",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC057",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6843,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC057", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7843 ,protection_group_id: -6843, protection_element_id:-6843], primaryKey: false);
      insert('organizations', [ id: 106829, nci_institute_code: "SC058", name: "Georgetown Hospital System", city: "Georgetown", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6844,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC058",GROUP_DESC:"SC058 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6844,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC058",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC058",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6844,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC058", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7844 ,protection_group_id: -6844, protection_element_id:-6844], primaryKey: false);
      insert('organizations', [ id: 106830, nci_institute_code: "SC059", name: "Tri-County Surgical Associates, P.A.", city: "Charleston", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6845,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC059",GROUP_DESC:"SC059 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6845,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC059",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC059",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6845,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC059", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7845 ,protection_group_id: -6845, protection_element_id:-6845], primaryKey: false);
      insert('organizations', [ id: 106831, nci_institute_code: "SC060", name: "Cancer Centers of the Carolinas - Faris", city: "Greenville", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6846,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC060",GROUP_DESC:"SC060 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6846,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC060",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC060",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6846,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC060", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7846 ,protection_group_id: -6846, protection_element_id:-6846], primaryKey: false);
      insert('organizations', [ id: 106832, nci_institute_code: "SC061", name: "Eastside Hematology/Oncology", city: "Taylors", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6847,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC061",GROUP_DESC:"SC061 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6847,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC061",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC061",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6847,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC061", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7847 ,protection_group_id: -6847, protection_element_id:-6847], primaryKey: false);
      insert('organizations', [ id: 106833, nci_institute_code: "SC062", name: "Georgetown Radiation Therapy Center", city: "Georgetown", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6848,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC062",GROUP_DESC:"SC062 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6848,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC062",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC062",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6848,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC062", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7848 ,protection_group_id: -6848, protection_element_id:-6848], primaryKey: false);
      insert('organizations', [ id: 106834, nci_institute_code: "SC063", name: "Winyah Surgical Specialists", city: "Georgetown", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6849,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC063",GROUP_DESC:"SC063 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6849,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC063",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC063",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6849,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC063", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7849 ,protection_group_id: -6849, protection_element_id:-6849], primaryKey: false);
      insert('organizations', [ id: 106835, nci_institute_code: "SC064", name: "AnMed Health Cancer Center", city: "Anderson", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6850,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC064",GROUP_DESC:"SC064 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6850,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC064",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC064",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6850,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC064", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7850 ,protection_group_id: -6850, protection_element_id:-6850], primaryKey: false);
      insert('organizations', [ id: 106836, nci_institute_code: "SC065", name: "South Carolina Oncology Associates PA - Medical Park", city: "Columbia", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6851,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC065",GROUP_DESC:"SC065 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6851,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC065",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC065",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6851,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC065", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7851 ,protection_group_id: -6851, protection_element_id:-6851], primaryKey: false);
      insert('organizations', [ id: 106837, nci_institute_code: "SC066", name: "The Regional Medical Center of Orangeburg & Calhoun Counties", city: "Orangeburg", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6852,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC066",GROUP_DESC:"SC066 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6852,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC066",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC066",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6852,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC066", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7852 ,protection_group_id: -6852, protection_element_id:-6852], primaryKey: false);
      insert('organizations', [ id: 106838, nci_institute_code: "SC067", name: "Waccamaw Oncology - Georgetown", city: "Georgetown", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6853,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC067",GROUP_DESC:"SC067 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6853,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC067",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC067",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6853,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC067", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7853 ,protection_group_id: -6853, protection_element_id:-6853], primaryKey: false);
      insert('organizations', [ id: 106839, nci_institute_code: "SC068", name: "Carolina Regional Cancer Center", city: "Myrtle Beach", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6854,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC068",GROUP_DESC:"SC068 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6854,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC068",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC068",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6854,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC068", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7854 ,protection_group_id: -6854, protection_element_id:-6854], primaryKey: false);
      insert('organizations', [ id: 106840, nci_institute_code: "SC069", name: "Surgical Specialists - Hilton Head General & Laparoscopic Surgery", city: "Hilton Head Island", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6855,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC069",GROUP_DESC:"SC069 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6855,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC069",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC069",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6855,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC069", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7855 ,protection_group_id: -6855, protection_element_id:-6855], primaryKey: false);
      insert('organizations', [ id: 106841, nci_institute_code: "SC070", name: "Lexington Oncology Associates LLC", city: "West Columbia", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6856,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC070",GROUP_DESC:"SC070 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6856,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC070",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC070",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6856,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC070", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7856 ,protection_group_id: -6856, protection_element_id:-6856], primaryKey: false);
      insert('organizations', [ id: 106842, nci_institute_code: "SC071", name: "Lexington Medical Center", city: "West Columbia", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6857,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC071",GROUP_DESC:"SC071 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6857,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC071",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC071",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6857,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC071", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7857 ,protection_group_id: -6857, protection_element_id:-6857], primaryKey: false);
      insert('organizations', [ id: 106843, nci_institute_code: "SC072", name: "Carolina Blood and Cancer Care Associates PA", city: "Rock Hill", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6858,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC072",GROUP_DESC:"SC072 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6858,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC072",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC072",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6858,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC072", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7858 ,protection_group_id: -6858, protection_element_id:-6858], primaryKey: false);
      insert('organizations', [ id: 106844, nci_institute_code: "SC073", name: "Beaufort Memorial Hospital", city: "Beaufort", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6859,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC073",GROUP_DESC:"SC073 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6859,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC073",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC073",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6859,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC073", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7859 ,protection_group_id: -6859, protection_element_id:-6859], primaryKey: false);
      insert('organizations', [ id: 106845, nci_institute_code: "SC074", name: "Sea Island Cancer Center LLC", city: "Port Royal", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6860,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC074",GROUP_DESC:"SC074 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6860,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC074",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC074",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6860,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC074", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7860 ,protection_group_id: -6860, protection_element_id:-6860], primaryKey: false);
      insert('organizations', [ id: 106846, nci_institute_code: "SC075", name: "Beaufort Memorial Keyserling Cancer Center", city: "Port Royal", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6861,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC075",GROUP_DESC:"SC075 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6861,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC075",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC075",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6861,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC075", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7861 ,protection_group_id: -6861, protection_element_id:-6861], primaryKey: false);
      insert('organizations', [ id: 106847, nci_institute_code: "SC076", name: "Intramed Plus", city: "West Columbia", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6862,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC076",GROUP_DESC:"SC076 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6862,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC076",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC076",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6862,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC076", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7862 ,protection_group_id: -6862, protection_element_id:-6862], primaryKey: false);
    }

    void m34() {
        // all34 (25 terms)
      insert('organizations', [ id: 106848, nci_institute_code: "SC077", name: "Charleston Thoracic and Cardiovascular Surgery LLC", city: "Charleston", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6863,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC077",GROUP_DESC:"SC077 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6863,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC077",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC077",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6863,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC077", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7863 ,protection_group_id: -6863, protection_element_id:-6863], primaryKey: false);
      insert('organizations', [ id: 106849, nci_institute_code: "SC078", name: "Low Country Hematology Oncology PA", city: "Mount Pleasant", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6864,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC078",GROUP_DESC:"SC078 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6864,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC078",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC078",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6864,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC078", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7864 ,protection_group_id: -6864, protection_element_id:-6864], primaryKey: false);
      insert('organizations', [ id: 106850, nci_institute_code: "SC079", name: "Santee Hematology Oncology", city: "Sumter", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6865,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC079",GROUP_DESC:"SC079 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6865,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC079",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC079",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6865,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC079", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7865 ,protection_group_id: -6865, protection_element_id:-6865], primaryKey: false);
      insert('organizations', [ id: 106851, nci_institute_code: "SC080", name: "Carolina Blood and Cancer Care Associates PA", city: "Lancaster", state: "SC", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6866,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC080",GROUP_DESC:"SC080 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6866,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SC080",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SC080",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6866,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SC080", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7866 ,protection_group_id: -6866, protection_element_id:-6866], primaryKey: false);
      insert('organizations', [ id: 106852, nci_institute_code: "SD001", name: "Royal C Johnson Veterans Memorial Hospital", city: "Sioux Falls", state: "SD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6867,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD001",GROUP_DESC:"SD001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6867,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SD001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SD001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6867,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7867 ,protection_group_id: -6867, protection_element_id:-6867], primaryKey: false);
      insert('organizations', [ id: 106853, nci_institute_code: "SD002", name: "Avera McKennan Hospital and University Health Center", city: "Sioux Falls", state: "SD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6868,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD002",GROUP_DESC:"SD002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6868,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SD002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SD002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6868,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7868 ,protection_group_id: -6868, protection_element_id:-6868], primaryKey: false);
      insert('organizations', [ id: 106854, nci_institute_code: "SD003", name: "Sanford University of South Dakota Medical Center", city: "Sioux Falls", state: "SD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6869,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD003",GROUP_DESC:"SD003 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6869,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SD003",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SD003",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6869,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD003", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7869 ,protection_group_id: -6869, protection_element_id:-6869], primaryKey: false);
      insert('organizations', [ id: 106855, nci_institute_code: "SD004", name: "Sioux Valley Clinic-Oncology", city: "Sioux Falls", state: "SD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6870,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD004",GROUP_DESC:"SD004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6870,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SD004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SD004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6870,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7870 ,protection_group_id: -6870, protection_element_id:-6870], primaryKey: false);
      insert('organizations', [ id: 106856, nci_institute_code: "SD005", name: "University of South Dakota", city: "Sioux Falls", state: "SD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6871,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD005",GROUP_DESC:"SD005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6871,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SD005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SD005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6871,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7871 ,protection_group_id: -6871, protection_element_id:-6871], primaryKey: false);
      insert('organizations', [ id: 106857, nci_institute_code: "SD006", name: "Rapid City Regional Hospital", city: "Rapid City", state: "SD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6872,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD006",GROUP_DESC:"SD006 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6872,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SD006",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SD006",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6872,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD006", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7872 ,protection_group_id: -6872, protection_element_id:-6872], primaryKey: false);
      insert('organizations', [ id: 106858, nci_institute_code: "SD007", name: "Saint Luke's Midland Regional Medical Center", city: "Aberdeen", state: "SD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6873,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD007",GROUP_DESC:"SD007 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6873,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SD007",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SD007",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6873,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD007", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7873 ,protection_group_id: -6873, protection_element_id:-6873], primaryKey: false);
      insert('organizations', [ id: 106859, nci_institute_code: "SD008", name: "Dakota Midwest Cancer Institute", city: "Sioux Falls", state: "SD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6874,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD008",GROUP_DESC:"SD008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6874,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SD008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SD008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6874,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7874 ,protection_group_id: -6874, protection_element_id:-6874], primaryKey: false);
      insert('organizations', [ id: 106860, nci_institute_code: "SD009", name: "Prairie Lakes Hospital", city: "Watertown", state: "SD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6875,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD009",GROUP_DESC:"SD009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6875,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SD009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SD009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6875,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7875 ,protection_group_id: -6875, protection_element_id:-6875], primaryKey: false);
      insert('organizations', [ id: 106861, nci_institute_code: "SD010", name: "Medical X-Ray Center", city: "Sioux Falls", state: "SD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6876,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD010",GROUP_DESC:"SD010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6876,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SD010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SD010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6876,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7876 ,protection_group_id: -6876, protection_element_id:-6876], primaryKey: false);
      insert('organizations', [ id: 106862, nci_institute_code: "SD011", name: "CCOP Sioux Community Cancer Consortium", city: "Sioux Falls", state: "SD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6877,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD011",GROUP_DESC:"SD011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6877,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SD011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SD011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6877,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7877 ,protection_group_id: -6877, protection_element_id:-6877], primaryKey: false);
      insert('organizations', [ id: 106863, nci_institute_code: "SD013", name: "Cancer Care Institute.", city: "Rapid City", state: "SD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6878,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD013",GROUP_DESC:"SD013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6878,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SD013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SD013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6878,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7878 ,protection_group_id: -6878, protection_element_id:-6878], primaryKey: false);
      insert('organizations', [ id: 106864, nci_institute_code: "SD020", name: "Aberdeen Area Indian Health Service", city: "Aberdeen", state: "SD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6879,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD020",GROUP_DESC:"SD020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6879,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SD020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SD020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6879,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7879 ,protection_group_id: -6879, protection_element_id:-6879], primaryKey: false);
      insert('organizations', [ id: 106865, nci_institute_code: "SD021", name: "Avera Cancer Institute", city: "Sioux Falls", state: "SD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6880,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD021",GROUP_DESC:"SD021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6880,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SD021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SD021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6880,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7880 ,protection_group_id: -6880, protection_element_id:-6880], primaryKey: false);
      insert('organizations', [ id: 106866, nci_institute_code: "SD022", name: "Black Hills Obstetrics and Gynecology", city: "Rapid City", state: "SD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6881,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD022",GROUP_DESC:"SD022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6881,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SD022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SD022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6881,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7881 ,protection_group_id: -6881, protection_element_id:-6881], primaryKey: false);
      insert('organizations', [ id: 106867, nci_institute_code: "SD023", name: "McGreevy Clinic- Avera", city: "Sioux Falls", state: "SD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6882,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD023",GROUP_DESC:"SD023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6882,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SD023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SD023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6882,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7882 ,protection_group_id: -6882, protection_element_id:-6882], primaryKey: false);
      insert('organizations', [ id: 106868, nci_institute_code: "SD024", name: "Physicians Laboratory, Ltd.", city: "Sioux Falls", state: "SD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6883,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD024",GROUP_DESC:"SD024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6883,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SD024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SD024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6883,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7883 ,protection_group_id: -6883, protection_element_id:-6883], primaryKey: false);
      insert('organizations', [ id: 106869, nci_institute_code: "SD025", name: "Avera Research Institute", city: "Sioux Falls", state: "SD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6884,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD025",GROUP_DESC:"SD025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6884,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SD025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SD025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6884,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7884 ,protection_group_id: -6884, protection_element_id:-6884], primaryKey: false);
      insert('organizations', [ id: 106870, nci_institute_code: "SD026", name: "Black Hills Urology PC", city: "Rapid City", state: "SD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6885,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD026",GROUP_DESC:"SD026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6885,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SD026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SD026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6885,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7885 ,protection_group_id: -6885, protection_element_id:-6885], primaryKey: false);
      insert('organizations', [ id: 106871, nci_institute_code: "SD027", name: "Oncology Services of Aberdeen", city: "Aberdeen", state: "SD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6886,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD027",GROUP_DESC:"SD027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6886,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SD027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SD027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6886,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7886 ,protection_group_id: -6886, protection_element_id:-6886], primaryKey: false);
      insert('organizations', [ id: 106872, nci_institute_code: "SD028", name: "Yankton Medical Clinic", city: "Yankton", state: "SD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6887,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD028",GROUP_DESC:"SD028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6887,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SD028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SD028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6887,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7887 ,protection_group_id: -6887, protection_element_id:-6887], primaryKey: false);
    }

    void m35() {
        // all35 (25 terms)
      insert('organizations', [ id: 106873, nci_institute_code: "SD029", name: "Surgical Institute of South Dakota", city: "Sioux Falls", state: "SD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6888,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD029",GROUP_DESC:"SD029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6888,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SD029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SD029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6888,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7888 ,protection_group_id: -6888, protection_element_id:-6888], primaryKey: false);
      insert('organizations', [ id: 106874, nci_institute_code: "SD030", name: "Leukemia and Bone Marrow Transplant Center", city: "Sioux Falls", state: "SD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6889,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD030",GROUP_DESC:"SD030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6889,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SD030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SD030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6889,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7889 ,protection_group_id: -6889, protection_element_id:-6889], primaryKey: false);
      insert('organizations', [ id: 106875, nci_institute_code: "SD031", name: "Sioux Valley Clinic Surgical Associates", city: "Sioux Falls", state: "SD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6890,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD031",GROUP_DESC:"SD031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6890,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SD031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SD031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6890,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7890 ,protection_group_id: -6890, protection_element_id:-6890], primaryKey: false);
      insert('organizations', [ id: 106876, nci_institute_code: "SD032", name: "Avera Sacred Heart Hospital", city: "Yankton", state: "SD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6891,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD032",GROUP_DESC:"SD032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6891,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SD032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SD032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6891,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7891 ,protection_group_id: -6891, protection_element_id:-6891], primaryKey: false);
      insert('organizations', [ id: 106877, nci_institute_code: "SD033", name: "Sioux Valley Clinic-OBGYN, Ltd", city: "Sioux Falls", state: "SD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6892,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD033",GROUP_DESC:"SD033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6892,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SD033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SD033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6892,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7892 ,protection_group_id: -6892, protection_element_id:-6892], primaryKey: false);
      insert('organizations', [ id: 106878, nci_institute_code: "SD034", name: "Clinical Laboratories of the Black Hills", city: "Rapid City", state: "SD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6893,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD034",GROUP_DESC:"SD034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6893,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SD034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SD034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6893,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7893 ,protection_group_id: -6893, protection_element_id:-6893], primaryKey: false);
      insert('organizations', [ id: 106879, nci_institute_code: "SD035", name: "LCM Pathologists PC", city: "Sioux Falls", state: "SD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6894,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD035",GROUP_DESC:"SD035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6894,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SD035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SD035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6894,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7894 ,protection_group_id: -6894, protection_element_id:-6894], primaryKey: false);
      insert('organizations', [ id: 106880, nci_institute_code: "SD036", name: "Sanford Clinic Surgical Associates", city: "Sioux Falls", state: "SD", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6895,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD036",GROUP_DESC:"SD036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6895,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SD036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SD036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6895,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SD036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7895 ,protection_group_id: -6895, protection_element_id:-6895], primaryKey: false);
      insert('organizations', [ id: 106881, nci_institute_code: "SECCC", name: "Southeast Cancer Control Consortium", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6896,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SECCC",GROUP_DESC:"SECCC group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6896,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SECCC",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SECCC",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6896,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SECCC", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7896 ,protection_group_id: -6896, protection_element_id:-6896], primaryKey: false);
      insert('organizations', [ id: 106882, nci_institute_code: "SFOP", name: "Societe Francaise Oncologie Pediatrique", country: "France"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6897,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SFOP",GROUP_DESC:"SFOP group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6897,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SFOP",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SFOP",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6897,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SFOP", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7897 ,protection_group_id: -6897, protection_element_id:-6897], primaryKey: false);
      insert('organizations', [ id: 106883, nci_institute_code: "SG", name: "Southeastern Cancer Study Group", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6898,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SG",GROUP_DESC:"SG group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6898,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SG",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SG",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6898,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SG", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7898 ,protection_group_id: -6898, protection_element_id:-6898], primaryKey: false);
      insert('organizations', [ id: 106884, nci_institute_code: "SSG", name: "Scandinavian Sarcoma Group", country: "Sweden"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6899,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SSG",GROUP_DESC:"SSG group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6899,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.SSG",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.SSG",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6899,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.SSG", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7899 ,protection_group_id: -6899, protection_element_id:-6899], primaryKey: false);
      insert('organizations', [ id: 106885, nci_institute_code: "TN001", name: "Williamson County Hospital", city: "Franklin", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6900,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN001",GROUP_DESC:"TN001 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6900,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN001",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN001",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6900,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN001", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7900 ,protection_group_id: -6900, protection_element_id:-6900], primaryKey: false);
      insert('organizations', [ id: 106886, nci_institute_code: "TN002", name: "Nashville Memorial Hospital", city: "Madison", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6901,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN002",GROUP_DESC:"TN002 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6901,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN002",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN002",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6901,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN002", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7901 ,protection_group_id: -6901, protection_element_id:-6901], primaryKey: false);
      insert('organizations', [ id: 106887, nci_institute_code: "TN004", name: "Veterans Administration Medical Center", city: "Nashville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6902,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN004",GROUP_DESC:"TN004 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6902,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN004",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN004",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6902,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN004", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7902 ,protection_group_id: -6902, protection_element_id:-6902], primaryKey: false);
      insert('organizations', [ id: 106888, nci_institute_code: "TN005", name: "Saint Thomas Hospital", city: "Nashville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6903,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN005",GROUP_DESC:"TN005 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6903,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN005",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN005",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6903,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN005", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7903 ,protection_group_id: -6903, protection_element_id:-6903], primaryKey: false);
      insert('organizations', [ id: 106889, nci_institute_code: "TN008", name: "Vanderbilt University", city: "Nashville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6904,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN008",GROUP_DESC:"TN008 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6904,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN008",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN008",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6904,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN008", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7904 ,protection_group_id: -6904, protection_element_id:-6904], primaryKey: false);
      insert('organizations', [ id: 106890, nci_institute_code: "TN009", name: "Erlanger Medical Center", city: "Chattanooga", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6905,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN009",GROUP_DESC:"TN009 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6905,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN009",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN009",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6905,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN009", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7905 ,protection_group_id: -6905, protection_element_id:-6905], primaryKey: false);
      insert('organizations', [ id: 106891, nci_institute_code: "TN010", name: "Baroness Erlanger Hospital", city: "Chattanooga", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6906,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN010",GROUP_DESC:"TN010 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6906,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN010",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN010",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6906,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN010", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7906 ,protection_group_id: -6906, protection_element_id:-6906], primaryKey: false);
      insert('organizations', [ id: 106892, nci_institute_code: "TN011", name: "Johnson City Medical Center Hospital", city: "Johnson City", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6907,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN011",GROUP_DESC:"TN011 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6907,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN011",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN011",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6907,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN011", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7907 ,protection_group_id: -6907, protection_element_id:-6907], primaryKey: false);
      insert('organizations', [ id: 106893, nci_institute_code: "TN012", name: "East Tennessee State University", city: "Johnson City", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6908,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN012",GROUP_DESC:"TN012 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6908,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN012",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN012",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6908,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN012", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7908 ,protection_group_id: -6908, protection_element_id:-6908], primaryKey: false);
      insert('organizations', [ id: 106894, nci_institute_code: "TN013", name: "Bristol Memorial Hospital", city: "Bristol", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6909,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN013",GROUP_DESC:"TN013 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6909,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN013",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN013",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6909,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN013", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7909 ,protection_group_id: -6909, protection_element_id:-6909], primaryKey: false);
      insert('organizations', [ id: 106895, nci_institute_code: "TN015", name: "Wellmont Holston Valley Hospital and Medical Center", city: "Kingsport", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6910,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN015",GROUP_DESC:"TN015 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6910,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN015",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN015",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6910,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN015", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7910 ,protection_group_id: -6910, protection_element_id:-6910], primaryKey: false);
      insert('organizations', [ id: 106896, nci_institute_code: "TN016", name: "Veterans Administration Medical Center", city: "Mountain Home", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6911,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN016",GROUP_DESC:"TN016 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6911,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN016",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN016",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6911,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN016", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7911 ,protection_group_id: -6911, protection_element_id:-6911], primaryKey: false);
      insert('organizations', [ id: 106897, nci_institute_code: "TN017", name: "Oak Ridge Associated University", city: "Oak Ridge", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6912,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN017",GROUP_DESC:"TN017 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6912,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN017",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN017",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6912,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN017", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7912 ,protection_group_id: -6912, protection_element_id:-6912], primaryKey: false);
    }

    void m36() {
        // all36 (25 terms)
      insert('organizations', [ id: 106898, nci_institute_code: "TN018", name: "East Tennessee Childrens Hospital", city: "Knoxville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6913,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN018",GROUP_DESC:"TN018 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6913,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN018",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN018",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6913,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN018", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7913 ,protection_group_id: -6913, protection_element_id:-6913], primaryKey: false);
      insert('organizations', [ id: 106899, nci_institute_code: "TN019", name: "East Tennessee Baptist Hospital", city: "Knoxville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6914,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN019",GROUP_DESC:"TN019 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6914,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN019",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN019",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6914,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN019", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7914 ,protection_group_id: -6914, protection_element_id:-6914], primaryKey: false);
      insert('organizations', [ id: 106900, nci_institute_code: "TN020", name: "Memorial Research Center & Hospital", city: "Knoxville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6915,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN020",GROUP_DESC:"TN020 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6915,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN020",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN020",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6915,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN020", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7915 ,protection_group_id: -6915, protection_element_id:-6915], primaryKey: false);
      insert('organizations', [ id: 106901, nci_institute_code: "TN021", name: "University of Tennessee - Knoxville", city: "Knoxville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6916,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN021",GROUP_DESC:"TN021 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6916,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN021",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN021",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6916,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN021", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7916 ,protection_group_id: -6916, protection_element_id:-6916], primaryKey: false);
      insert('organizations', [ id: 106902, nci_institute_code: "TN022", name: "Methodist Hospital", city: "Memphis", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6917,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN022",GROUP_DESC:"TN022 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6917,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN022",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN022",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6917,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN022", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7917 ,protection_group_id: -6917, protection_element_id:-6917], primaryKey: false);
      insert('organizations', [ id: 106903, nci_institute_code: "TN023", name: "Naval Hospital", city: "Memphis", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6918,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN023",GROUP_DESC:"TN023 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6918,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN023",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN023",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6918,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN023", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7918 ,protection_group_id: -6918, protection_element_id:-6918], primaryKey: false);
      insert('organizations', [ id: 106904, nci_institute_code: "TN024", name: "Saint Jude Children's Research Hospital", city: "Memphis", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6919,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN024",GROUP_DESC:"TN024 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6919,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN024",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN024",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6919,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN024", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7919 ,protection_group_id: -6919, protection_element_id:-6919], primaryKey: false);
      insert('organizations', [ id: 106905, nci_institute_code: "TN025", name: "City of Memphis Hospital", city: "Memphis", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6920,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN025",GROUP_DESC:"TN025 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6920,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN025",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN025",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6920,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN025", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7920 ,protection_group_id: -6920, protection_element_id:-6920], primaryKey: false);
      insert('organizations', [ id: 106906, nci_institute_code: "TN026", name: "Veterans Affairs Medical Center - Memphis", city: "Memphis", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6921,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN026",GROUP_DESC:"TN026 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6921,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN026",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN026",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6921,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN026", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7921 ,protection_group_id: -6921, protection_element_id:-6921], primaryKey: false);
      insert('organizations', [ id: 106907, nci_institute_code: "TN027", name: "East Tennnessee Medical Group", city: "Merryville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6922,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN027",GROUP_DESC:"TN027 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6922,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN027",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN027",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6922,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN027", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7922 ,protection_group_id: -6922, protection_element_id:-6922], primaryKey: false);
      insert('organizations', [ id: 106908, nci_institute_code: "TN028", name: "Saint Francis Hospital", city: "Memphis", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6923,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN028",GROUP_DESC:"TN028 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6923,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN028",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN028",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6923,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN028", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7923 ,protection_group_id: -6923, protection_element_id:-6923], primaryKey: false);
      insert('organizations', [ id: 106909, nci_institute_code: "TN029", name: "Baptist Memorial Hospital - Memphis", city: "Memphis", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6924,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN029",GROUP_DESC:"TN029 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6924,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN029",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN029",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6924,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN029", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7924 ,protection_group_id: -6924, protection_element_id:-6924], primaryKey: false);
      insert('organizations', [ id: 106910, nci_institute_code: "TN030", name: "University of Tennessee - Memphis", city: "Memphis", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6925,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN030",GROUP_DESC:"TN030 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6925,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN030",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN030",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6925,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN030", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7925 ,protection_group_id: -6925, protection_element_id:-6925], primaryKey: false);
      insert('organizations', [ id: 106911, nci_institute_code: "TN031", name: "Cumberland Medical Center", city: "Crossville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6926,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN031",GROUP_DESC:"TN031 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6926,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN031",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN031",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6926,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN031", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7926 ,protection_group_id: -6926, protection_element_id:-6926], primaryKey: false);
      insert('organizations', [ id: 106912, nci_institute_code: "TN032", name: "Parkridge Medical Center", city: "Chattanooga", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6927,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN032",GROUP_DESC:"TN032 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6927,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN032",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN032",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6927,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN032", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7927 ,protection_group_id: -6927, protection_element_id:-6927], primaryKey: false);
      insert('organizations', [ id: 106913, nci_institute_code: "TN033", name: "Baptist Hospital", city: "Nashville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6928,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN033",GROUP_DESC:"TN033 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6928,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN033",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN033",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6928,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN033", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7928 ,protection_group_id: -6928, protection_element_id:-6928], primaryKey: false);
      insert('organizations', [ id: 106914, nci_institute_code: "TN034", name: "Response Technologies, Incorporated", city: "Memphis", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6929,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN034",GROUP_DESC:"TN034 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6929,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN034",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN034",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6929,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN034", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7929 ,protection_group_id: -6929, protection_element_id:-6929], primaryKey: false);
      insert('organizations', [ id: 106915, nci_institute_code: "TN035", name: "Fort Sanders Regional Medical Center", city: "Knoxville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6930,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN035",GROUP_DESC:"TN035 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6930,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN035",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN035",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6930,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN035", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7930 ,protection_group_id: -6930, protection_element_id:-6930], primaryKey: false);
      insert('organizations', [ id: 106916, nci_institute_code: "TN036", name: "Methodist Medical Center of Oak Ridge", city: "oak Ridge", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6931,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN036",GROUP_DESC:"TN036 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6931,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN036",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN036",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6931,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN036", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7931 ,protection_group_id: -6931, protection_element_id:-6931], primaryKey: false);
      insert('organizations', [ id: 106917, nci_institute_code: "TN037", name: "Meharry Medical College", city: "Nashville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6932,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN037",GROUP_DESC:"TN037 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6932,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN037",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN037",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6932,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN037", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7932 ,protection_group_id: -6932, protection_element_id:-6932], primaryKey: false);
      insert('organizations', [ id: 106918, nci_institute_code: "TN038", name: "Thompson Cancer Survival Center", city: "Knoxville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6933,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN038",GROUP_DESC:"TN038 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6933,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN038",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN038",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6933,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN038", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7933 ,protection_group_id: -6933, protection_element_id:-6933], primaryKey: false);
      insert('organizations', [ id: 106919, nci_institute_code: "TN039", name: "Centennial Medical Center", city: "Nashville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6934,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN039",GROUP_DESC:"TN039 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6934,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN039",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN039",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6934,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN039", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7934 ,protection_group_id: -6934, protection_element_id:-6934], primaryKey: false);
      insert('organizations', [ id: 106920, nci_institute_code: "TN040", name: "Mcleod Cancer and Blood Center", city: "Johnson City", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6935,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN040",GROUP_DESC:"TN040 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6935,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN040",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN040",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6935,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN040", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7935 ,protection_group_id: -6935, protection_element_id:-6935], primaryKey: false);
      insert('organizations', [ id: 106921, nci_institute_code: "TN041", name: "University of Tennessee School of Medicine", city: "Chattanooga", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6936,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN041",GROUP_DESC:"TN041 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6936,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN041",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN041",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6936,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN041", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7936 ,protection_group_id: -6936, protection_element_id:-6936], primaryKey: false);
      insert('organizations', [ id: 106922, nci_institute_code: "TN042", name: "Lebonheur Children's Medical Center", city: "Memphis", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6937,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN042",GROUP_DESC:"TN042 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6937,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN042",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN042",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6937,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN042", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7937 ,protection_group_id: -6937, protection_element_id:-6937], primaryKey: false);
    }

    void m37() {
        // all37 (25 terms)
      insert('organizations', [ id: 106923, nci_institute_code: "TN043", name: "Cumberland Oncology/Hematology", city: "Oak Ridge", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6938,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN043",GROUP_DESC:"TN043 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6938,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN043",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN043",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6938,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN043", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7938 ,protection_group_id: -6938, protection_element_id:-6938], primaryKey: false);
      insert('organizations', [ id: 106924, nci_institute_code: "TN044", name: "Mid State Oncology", city: "Nashville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6939,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN044",GROUP_DESC:"TN044 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6939,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN044",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN044",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6939,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN044", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7939 ,protection_group_id: -6939, protection_element_id:-6939], primaryKey: false);
      insert('organizations', [ id: 106925, nci_institute_code: "TN045", name: "Jackson-Madison County General Hospital", city: "Jackson", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6940,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN045",GROUP_DESC:"TN045 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6940,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN045",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN045",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6940,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN045", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7940 ,protection_group_id: -6940, protection_element_id:-6940], primaryKey: false);
      insert('organizations', [ id: 106926, nci_institute_code: "TN046", name: "Memorial Hospital", city: "Chattanooga", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6941,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN046",GROUP_DESC:"TN046 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6941,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN046",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN046",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6941,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN046", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7941 ,protection_group_id: -6941, protection_element_id:-6941], primaryKey: false);
      insert('organizations', [ id: 106927, nci_institute_code: "TN047", name: "Sarah Cannon Cancer Center", city: "Nashville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6942,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN047",GROUP_DESC:"TN047 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6942,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN047",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN047",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6942,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN047", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7942 ,protection_group_id: -6942, protection_element_id:-6942], primaryKey: false);
      insert('organizations', [ id: 106928, nci_institute_code: "TN048", name: "Veterans Administration Medical Center", city: "Johnson city", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6943,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN048",GROUP_DESC:"TN048 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6943,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN048",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN048",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6943,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN048", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7943 ,protection_group_id: -6943, protection_element_id:-6943], primaryKey: false);
      insert('organizations', [ id: 106929, nci_institute_code: "TN049", name: "Medical Specialty Clinic", city: "Jackson", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6944,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN049",GROUP_DESC:"TN049 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6944,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN049",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN049",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6944,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN049", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7944 ,protection_group_id: -6944, protection_element_id:-6944], primaryKey: false);
      insert('organizations', [ id: 106930, nci_institute_code: "TN050", name: "Bethesda Cancer Treatment Center", city: "Tullahoma", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6945,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN050",GROUP_DESC:"TN050 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6945,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN050",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN050",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6945,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN050", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7945 ,protection_group_id: -6945, protection_element_id:-6945], primaryKey: false);
      insert('organizations', [ id: 106931, nci_institute_code: "TN051", name: "Hematology/Oncology of Knoxville", city: "Knoxville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6946,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN051",GROUP_DESC:"TN051 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6946,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN051",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN051",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6946,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN051", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7946 ,protection_group_id: -6946, protection_element_id:-6946], primaryKey: false);
      insert('organizations', [ id: 106932, nci_institute_code: "TN053", name: "Oncology Hematology Associates", city: "Nashville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6947,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN053",GROUP_DESC:"TN053 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6947,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN053",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN053",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6947,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN053", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7947 ,protection_group_id: -6947, protection_element_id:-6947], primaryKey: false);
      insert('organizations', [ id: 106933, nci_institute_code: "TN054", name: "Biological Therapy Institute", city: "Franklin", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6948,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN054",GROUP_DESC:"TN054 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6948,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN054",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN054",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6948,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN054", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7948 ,protection_group_id: -6948, protection_element_id:-6948], primaryKey: false);
      insert('organizations', [ id: 106934, nci_institute_code: "TN055", name: "Jackson Clinic", city: "Jackson", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6949,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN055",GROUP_DESC:"TN055 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6949,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN055",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN055",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6949,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN055", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7949 ,protection_group_id: -6949, protection_element_id:-6949], primaryKey: false);
      insert('organizations', [ id: 106935, nci_institute_code: "TN056", name: "Memphis Cancer Center, P.C.", city: "Memphis", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6950,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN056",GROUP_DESC:"TN056 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6950,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN056",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN056",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6950,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN056", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7950 ,protection_group_id: -6950, protection_element_id:-6950], primaryKey: false);
      insert('organizations', [ id: 106936, nci_institute_code: "TN057", name: "Can Speciality Clnc", city: "Memphis", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6951,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN057",GROUP_DESC:"TN057 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6951,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN057",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN057",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6951,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN057", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7951 ,protection_group_id: -6951, protection_element_id:-6951], primaryKey: false);
      insert('organizations', [ id: 106937, nci_institute_code: "TN058", name: "Oncology Associates", city: "Knoxville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6952,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN058",GROUP_DESC:"TN058 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6952,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN058",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN058",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6952,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN058", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7952 ,protection_group_id: -6952, protection_element_id:-6952], primaryKey: false);
      insert('organizations', [ id: 106938, nci_institute_code: "TN059", name: "Clarksville Memorial Hospital", city: "Clarksville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6953,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN059",GROUP_DESC:"TN059 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6953,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN059",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN059",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6953,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN059", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7953 ,protection_group_id: -6953, protection_element_id:-6953], primaryKey: false);
      insert('organizations', [ id: 106939, nci_institute_code: "TN060", name: "Van Vleet Cancer Center", city: "Memphis", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6954,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN060",GROUP_DESC:"TN060 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6954,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN060",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN060",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6954,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN060", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7954 ,protection_group_id: -6954, protection_element_id:-6954], primaryKey: false);
      insert('organizations', [ id: 106940, nci_institute_code: "TN061", name: "Tennessee Oncology PC", city: "Nashville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6955,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN061",GROUP_DESC:"TN061 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6955,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN061",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN061",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6955,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN061", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7955 ,protection_group_id: -6955, protection_element_id:-6955], primaryKey: false);
      insert('organizations', [ id: 106941, nci_institute_code: "TN062", name: "Baptist Cancer Institute CCOP", city: "Memphis", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6956,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN062",GROUP_DESC:"TN062 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6956,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN062",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN062",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6956,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN062", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7956 ,protection_group_id: -6956, protection_element_id:-6956], primaryKey: false);
      insert('organizations', [ id: 106942, nci_institute_code: "TN063", name: "Saint Mary's Medical Center", city: "Knoxville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6957,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN063",GROUP_DESC:"TN063 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6957,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN063",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN063",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6957,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN063", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7957 ,protection_group_id: -6957, protection_element_id:-6957], primaryKey: false);
      insert('organizations', [ id: 106943, nci_institute_code: "TN064", name: "Wellmont Bristol Regional Medical Center", city: "Bristol", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6958,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN064",GROUP_DESC:"TN064 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6958,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN064",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN064",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6958,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN064", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7958 ,protection_group_id: -6958, protection_element_id:-6958], primaryKey: false);
      insert('organizations', [ id: 106944, nci_institute_code: "TN065", name: "Tennessee Bcpt Group", city: "Maryville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6959,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN065",GROUP_DESC:"TN065 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6959,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN065",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN065",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6959,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN065", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7959 ,protection_group_id: -6959, protection_element_id:-6959], primaryKey: false);
      insert('organizations', [ id: 106945, nci_institute_code: "TN066", name: "Boston Cancer Group", city: "Memphis", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6960,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN066",GROUP_DESC:"TN066 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6960,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN066",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN066",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6960,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN066", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7960 ,protection_group_id: -6960, protection_element_id:-6960], primaryKey: false);
      insert('organizations', [ id: 106946, nci_institute_code: "TN068", name: "Oncology/Hematology Consultants, Pllc", city: "Knoxville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6961,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN068",GROUP_DESC:"TN068 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6961,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN068",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN068",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6961,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN068", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7961 ,protection_group_id: -6961, protection_element_id:-6961], primaryKey: false);
      insert('organizations', [ id: 106947, nci_institute_code: "TN069", name: "Center for Urological Treatment/Research", city: "Nashville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6962,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN069",GROUP_DESC:"TN069 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6962,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN069",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN069",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6962,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN069", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7962 ,protection_group_id: -6962, protection_element_id:-6962], primaryKey: false);
    }

    void m38() {
        // all38 (25 terms)
      insert('organizations', [ id: 106948, nci_institute_code: "TN070", name: "Blount Memorial Hospital", city: "Maryville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6963,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN070",GROUP_DESC:"TN070 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6963,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN070",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN070",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6963,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN070", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7963 ,protection_group_id: -6963, protection_element_id:-6963], primaryKey: false);
      insert('organizations', [ id: 106949, nci_institute_code: "TN071", name: "Oncology Associates", city: "Maryville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6964,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN071",GROUP_DESC:"TN071 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6964,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN071",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN071",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6964,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN071", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7964 ,protection_group_id: -6964, protection_element_id:-6964], primaryKey: false);
      insert('organizations', [ id: 106950, nci_institute_code: "TN072", name: "Middle Tn Oncology/Hematology", city: "Murfreesboro", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6965,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN072",GROUP_DESC:"TN072 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6965,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN072",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN072",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6965,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN072", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7965 ,protection_group_id: -6965, protection_element_id:-6965], primaryKey: false);
      insert('organizations', [ id: 106951, nci_institute_code: "TN073", name: "Alvin C. York Veterans Administration Medical Center", city: "Murfreesboro", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6966,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN073",GROUP_DESC:"TN073 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6966,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN073",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN073",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6966,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN073", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7966 ,protection_group_id: -6966, protection_element_id:-6966], primaryKey: false);
      insert('organizations', [ id: 106952, nci_institute_code: "TN074", name: "Mid Cumberland Hematology and Oncology", city: "Clarksville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6967,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN074",GROUP_DESC:"TN074 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6967,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN074",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN074",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6967,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN074", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7967 ,protection_group_id: -6967, protection_element_id:-6967], primaryKey: false);
      insert('organizations', [ id: 106953, nci_institute_code: "TN075", name: "Tennessee Oncology PC", city: "Franklin", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6968,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN075",GROUP_DESC:"TN075 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6968,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN075",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN075",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6968,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN075", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7968 ,protection_group_id: -6968, protection_element_id:-6968], primaryKey: false);
      insert('organizations', [ id: 106954, nci_institute_code: "TN076", name: "Northside Hospital", city: "Johnson City", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6969,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN076",GROUP_DESC:"TN076 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6969,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN076",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN076",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6969,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN076", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7969 ,protection_group_id: -6969, protection_element_id:-6969], primaryKey: false);
      insert('organizations', [ id: 106955, nci_institute_code: "TN077", name: "Sycamore Shoals Hospital", city: "Elizabeth", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6970,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN077",GROUP_DESC:"TN077 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6970,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN077",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN077",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6970,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN077", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7970 ,protection_group_id: -6970, protection_element_id:-6970], primaryKey: false);
      insert('organizations', [ id: 106956, nci_institute_code: "TN078", name: "Johnson City Specialty Hospital", city: "Johnson City", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6971,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN078",GROUP_DESC:"TN078 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6971,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN078",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN078",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6971,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN078", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7971 ,protection_group_id: -6971, protection_element_id:-6971], primaryKey: false);
      insert('organizations', [ id: 106957, nci_institute_code: "TN079", name: "Gyneocologic Oncology of Middle Tennessee", city: "Nashville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6972,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN079",GROUP_DESC:"TN079 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6972,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN079",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN079",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6972,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN079", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7972 ,protection_group_id: -6972, protection_element_id:-6972], primaryKey: false);
      insert('organizations', [ id: 106958, nci_institute_code: "TN080", name: "Mid-South Imaging & Therapeutics, P.A.", city: "Memphis", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6973,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN080",GROUP_DESC:"TN080 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6973,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN080",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN080",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6973,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN080", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7973 ,protection_group_id: -6973, protection_element_id:-6973], primaryKey: false);
      insert('organizations', [ id: 106959, nci_institute_code: "TN081", name: "Clarksville Oncology", city: "Clarksville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6974,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN081",GROUP_DESC:"TN081 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6974,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN081",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN081",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6974,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN081", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7974 ,protection_group_id: -6974, protection_element_id:-6974], primaryKey: false);
      insert('organizations', [ id: 106960, nci_institute_code: "TN082", name: "Williamson Medical Center", city: "Franklin", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6975,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN082",GROUP_DESC:"TN082 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6975,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN082",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN082",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6975,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN082", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7975 ,protection_group_id: -6975, protection_element_id:-6975], primaryKey: false);
      insert('organizations', [ id: 106961, nci_institute_code: "TN085", name: "Regional Medical Center", city: "Memphis", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6976,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN085",GROUP_DESC:"TN085 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6976,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN085",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN085",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6976,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN085", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7976 ,protection_group_id: -6976, protection_element_id:-6976], primaryKey: false);
      insert('organizations', [ id: 106962, nci_institute_code: "TN086", name: "Methodist Healthcare- Central Hospital", city: "Memphis", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6977,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN086",GROUP_DESC:"TN086 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6977,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN086",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN086",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6977,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN086", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7977 ,protection_group_id: -6977, protection_element_id:-6977], primaryKey: false);
      insert('organizations', [ id: 106963, nci_institute_code: "TN087", name: "University of Tennesee Health Science Center", city: "Memphis", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6978,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN087",GROUP_DESC:"TN087 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6978,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN087",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN087",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6978,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN087", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7978 ,protection_group_id: -6978, protection_element_id:-6978], primaryKey: false);
      insert('organizations', [ id: 106964, nci_institute_code: "TN088", name: "University of Tennessee Cancer Institute/Boston Cancer Group, PLC", city: "Memphis", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6979,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN088",GROUP_DESC:"TN088 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6979,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN088",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN088",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6979,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN088", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7979 ,protection_group_id: -6979, protection_element_id:-6979], primaryKey: false);
      insert('organizations', [ id: 106965, nci_institute_code: "TN089", name: "The West Clinic- East Memphis", city: "Memphis", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6980,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN089",GROUP_DESC:"TN089 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6980,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN089",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN089",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6980,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN089", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7980 ,protection_group_id: -6980, protection_element_id:-6980], primaryKey: false);
      insert('organizations', [ id: 106966, nci_institute_code: "TN090", name: "Metropolitan Nashville General Hosp", city: "Nashville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6981,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN090",GROUP_DESC:"TN090 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6981,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN090",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN090",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6981,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN090", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7981 ,protection_group_id: -6981, protection_element_id:-6981], primaryKey: false);
      insert('organizations', [ id: 106967, nci_institute_code: "TN091", name: "University Oncology\\Hematology Associates", city: "Chattanooga", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6982,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN091",GROUP_DESC:"TN091 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6982,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN091",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN091",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6982,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN091", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7982 ,protection_group_id: -6982, protection_element_id:-6982], primaryKey: false);
      insert('organizations', [ id: 106968, nci_institute_code: "TN092", name: " Vanderbilt-Ingram Cancer Center Cool Springs", city: "Franklin", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6983,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN092",GROUP_DESC:"TN092 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6983,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN092",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN092",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6983,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN092", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7983 ,protection_group_id: -6983, protection_element_id:-6983], primaryKey: false);
      insert('organizations', [ id: 106969, nci_institute_code: "TN093", name: "Tennessee Oncology PLLC", city: "Murfreesboro", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6984,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN093",GROUP_DESC:"TN093 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6984,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN093",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN093",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6984,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN093", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7984 ,protection_group_id: -6984, protection_element_id:-6984], primaryKey: false);
      insert('organizations', [ id: 106970, nci_institute_code: "TN094", name: "Tennessee Oncology PLLC - Skyline Medical Center", city: "Nashville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6985,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN094",GROUP_DESC:"TN094 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6985,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN094",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN094",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6985,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN094", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7985 ,protection_group_id: -6985, protection_element_id:-6985], primaryKey: false);
      insert('organizations', [ id: 106971, nci_institute_code: "TN095", name: "Tennessee Oncology PLLC", city: "Hermitage", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6986,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN095",GROUP_DESC:"TN095 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6986,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN095",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN095",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6986,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN095", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7986 ,protection_group_id: -6986, protection_element_id:-6986], primaryKey: false);
      insert('organizations', [ id: 106972, nci_institute_code: "TN096", name: "Tennessee Oncology, PLLC", city: "Nashville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6987,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN096",GROUP_DESC:"TN096 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6987,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN096",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN096",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6987,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN096", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7987 ,protection_group_id: -6987, protection_element_id:-6987], primaryKey: false);
    }

    void m39() {
        // all39 (25 terms)
      insert('organizations', [ id: 106973, nci_institute_code: "TN097", name: "Raines-Cox Urology Institute", city: "Memphis", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6988,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN097",GROUP_DESC:"TN097 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6988,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN097",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN097",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6988,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN097", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7988 ,protection_group_id: -6988, protection_element_id:-6988], primaryKey: false);
      insert('organizations', [ id: 106974, nci_institute_code: "TN098", name: "Family Cancer Center PLLC", city: "Collierville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6989,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN098",GROUP_DESC:"TN098 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6989,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN098",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN098",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6989,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN098", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7989 ,protection_group_id: -6989, protection_element_id:-6989], primaryKey: false);
      insert('organizations', [ id: 106975, nci_institute_code: "TN099", name: "Southeast Gynecologic Oncology Associates", city: "Knoxville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6990,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN099",GROUP_DESC:"TN099 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6990,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN099",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN099",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6990,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN099", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7990 ,protection_group_id: -6990, protection_element_id:-6990], primaryKey: false);
      insert('organizations', [ id: 106976, nci_institute_code: "TN100", name: "Chattanooga Oncology Hematology Associates, P.C", city: "Chattanooga", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6991,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN100",GROUP_DESC:"TN100 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6991,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN100",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN100",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6991,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN100", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7991 ,protection_group_id: -6991, protection_element_id:-6991], primaryKey: false);
      insert('organizations', [ id: 106977, nci_institute_code: "TN102", name: "Semmes-Murphey Clinic", city: "Memphis", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6992,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN102",GROUP_DESC:"TN102 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6992,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN102",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN102",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6992,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN102", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7992 ,protection_group_id: -6992, protection_element_id:-6992], primaryKey: false);
      insert('organizations', [ id: 106978, nci_institute_code: "TN103", name: "The Consultant Group, P.C. - Franklin", city: "Franklin", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6993,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN103",GROUP_DESC:"TN103 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6993,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN103",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN103",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6993,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN103", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7993 ,protection_group_id: -6993, protection_element_id:-6993], primaryKey: false);
      insert('organizations', [ id: 106979, nci_institute_code: "TN104", name: "Nashville Surgical Associates", city: "Nashville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6994,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN104",GROUP_DESC:"TN104 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6994,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN104",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN104",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6994,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN104", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7994 ,protection_group_id: -6994, protection_element_id:-6994], primaryKey: false);
      insert('organizations', [ id: 106980, nci_institute_code: "TN105", name: "Nashville Breast Center", city: "Nashville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6995,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN105",GROUP_DESC:"TN105 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6995,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN105",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN105",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6995,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN105", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7995 ,protection_group_id: -6995, protection_element_id:-6995], primaryKey: false);
      insert('organizations', [ id: 106981, nci_institute_code: "TN106", name: "Southern Radiation", city: "Nashville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6996,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN106",GROUP_DESC:"TN106 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6996,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN106",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN106",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6996,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN106", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7996 ,protection_group_id: -6996, protection_element_id:-6996], primaryKey: false);
      insert('organizations', [ id: 106982, nci_institute_code: "TN108", name: "The Consultant Group, P.C. - Nashville", city: "Nashville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6997,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN108",GROUP_DESC:"TN108 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6997,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN108",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN108",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6997,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN108", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7997 ,protection_group_id: -6997, protection_element_id:-6997], primaryKey: false);
      insert('organizations', [ id: 106983, nci_institute_code: "TN109", name: "Tennessee Cancer Specialists PLLC", city: "Knoxville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6998,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN109",GROUP_DESC:"TN109 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6998,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN109",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN109",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6998,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN109", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7998 ,protection_group_id: -6998, protection_element_id:-6998], primaryKey: false);
      insert('organizations', [ id: 106984, nci_institute_code: "TN110", name: "Baptist Clinical Research Center", city: "Memphis", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -6999,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN110",GROUP_DESC:"TN110 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -6999,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN110",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN110",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -6999,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN110", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:7999 ,protection_group_id: -6999, protection_element_id:-6999], primaryKey: false);
      insert('organizations', [ id: 106985, nci_institute_code: "TN111", name: "Brookview Research, Inc", city: "Nashville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7000,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN111",GROUP_DESC:"TN111 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7000,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN111",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN111",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7000,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN111", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8000 ,protection_group_id: -7000, protection_element_id:-7000], primaryKey: false);
      insert('organizations', [ id: 106986, nci_institute_code: "TN112", name: "Walsh Cancer Clinic PC", city: "Memphis", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7001,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN112",GROUP_DESC:"TN112 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7001,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN112",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN112",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7001,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN112", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8001 ,protection_group_id: -7001, protection_element_id:-7001], primaryKey: false);
      insert('organizations', [ id: 106987, nci_institute_code: "TN113", name: "University Surgical Associates", city: "Chattanooga", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7002,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN113",GROUP_DESC:"TN113 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7002,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN113",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN113",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7002,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN113", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8002 ,protection_group_id: -7002, protection_element_id:-7002], primaryKey: false);
      insert('organizations', [ id: 106988, nci_institute_code: "TN114", name: "Women\222s Institute for Specialized Health", city: "Cleveland", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7003,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN114",GROUP_DESC:"TN114 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7003,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN114",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN114",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7003,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN114", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8003 ,protection_group_id: -7003, protection_element_id:-7003], primaryKey: false);
      insert('organizations', [ id: 106989, nci_institute_code: "TN115", name: "Jones Clinic", city: "Germantown", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7004,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN115",GROUP_DESC:"TN115 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7004,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN115",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN115",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7004,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN115", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8004 ,protection_group_id: -7004, protection_element_id:-7004], primaryKey: false);
      insert('organizations', [ id: 106990, nci_institute_code: "TN116", name: "Vanderbilt-Ingram Cancer Center", city: "Nashville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7005,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN116",GROUP_DESC:"TN116 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7005,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN116",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN116",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7005,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN116", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8005 ,protection_group_id: -7005, protection_element_id:-7005], primaryKey: false);
      insert('organizations', [ id: 106991, nci_institute_code: "TN117", name: "Kingsport Urology Group", city: "Kingsport", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7006,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN117",GROUP_DESC:"TN117 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7006,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN117",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN117",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7006,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN117", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8006 ,protection_group_id: -7006, protection_element_id:-7006], primaryKey: false);
      insert('organizations', [ id: 106992, nci_institute_code: "TN118", name: "Nashville Oncology Associates, PC", city: "Nashville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7007,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN118",GROUP_DESC:"TN118 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7007,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN118",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN118",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7007,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN118", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8007 ,protection_group_id: -7007, protection_element_id:-7007], primaryKey: false);
      insert('organizations', [ id: 106993, nci_institute_code: "TN119", name: "Howell Allen Clinic", city: "Nashville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7008,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN119",GROUP_DESC:"TN119 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7008,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN119",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN119",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7008,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN119", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8008 ,protection_group_id: -7008, protection_element_id:-7008], primaryKey: false);
      insert('organizations', [ id: 106994, nci_institute_code: "TN120", name: "Urology Associates of Kingsport, P.C.", city: "Kingsport", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7009,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN120",GROUP_DESC:"TN120 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7009,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN120",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN120",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7009,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN120", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8009 ,protection_group_id: -7009, protection_element_id:-7009], primaryKey: false);
      insert('organizations', [ id: 106995, nci_institute_code: "TN121", name: "T. C. Thompson Children's Hospital", city: "Chattanooga", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7010,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN121",GROUP_DESC:"TN121 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7010,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN121",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN121",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7010,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN121", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8010 ,protection_group_id: -7010, protection_element_id:-7010], primaryKey: false);
      insert('organizations', [ id: 106996, nci_institute_code: "TN122", name: "Blue Ridge Medical Specialists, P.C.", city: "Bristol", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7011,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN122",GROUP_DESC:"TN122 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7011,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN122",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN122",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7011,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN122", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8011 ,protection_group_id: -7011, protection_element_id:-7011], primaryKey: false);
      insert('organizations', [ id: 106997, nci_institute_code: "TN123", name: "The Surgical Clinic, P.L.L.C.", city: "Nashville", state: "TN", country: "USA"], primaryKey: false) 
      insert('csm_group',[GROUP_ID: -7012,GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN123",GROUP_DESC:"TN123 group",application_id: -1], primaryKey: false); 
      insert('csm_protection_element',[protection_element_id: -7012,protection_element_name:"gov.nih.nci.cabig.caaers.domain.Organization.TN123",object_id: "gov.nih.nci.cabig.caaers.domain.Organization.TN123",application_id: -1], primaryKey: false); 
      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: -7012,PROTECTION_GROUP_NAME:"gov.nih.nci.cabig.caaers.domain.Organization.TN123", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);
      insert('csm_pg_pe',[pg_pe_id:8012 ,protection_group_id: -7012, protection_element_id:-7012], primaryKey: false);
    }

    void m40() {
        // all40 (0 terms)
    }

    void down() {
        execute("delete from csm_pg_pe where pg_pe_id >= 1015 and  pg_pe_id <= 8013 ");
        execute("delete from CSM_PROTECTION_GROUP where protection_group_id  <= -15 ");
        execute("delete from csm_protection_element where protection_element_id <= -15 ");
        execute("delete from csm_group where group_id <= -15 ");
        execute("DELETE from organizations where id >= 100000 and id < 110000")
    }
}
